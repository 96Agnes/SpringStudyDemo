package com.example.test.springtest.service;

import com.example.test.springtest.dto.CommentDTO;
import com.example.test.springtest.enums.CommentTypeEnum;
import com.example.test.springtest.exception.CustomizeErrorCode;
import com.example.test.springtest.exception.CustomizeException;
import com.example.test.springtest.mapper.CommentMapper;
import com.example.test.springtest.mapper.QuestionExtMapper;
import com.example.test.springtest.mapper.QuestionMapper;
import com.example.test.springtest.mapper.UserMapper;
import com.example.test.springtest.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;

    //下面注解意义：insert函数是一个整体，如果某一步错了就全部回滚。以防出现question表插入了，commentcount却没有增加
    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_ERROR);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }else{
                commentMapper.insert(comment);
            }
        }
        else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            }
        }

    public List<CommentDTO> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if(commentList.size() == 0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = commentList.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转为map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, Object> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转化comment为commenDTO
        List<CommentDTO> commentDTOS = commentList.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment,commentDTO);
            User user = (User)userMap.get(comment.getCommentator());
            commentDTO.setUser(user);
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
