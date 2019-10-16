package com.example.test.springtest.service;

import com.example.test.springtest.enums.CommentTypeEnum;
import com.example.test.springtest.exception.CustomizeErrorCode;
import com.example.test.springtest.exception.CustomizeException;
import com.example.test.springtest.mapper.CommentMapper;
import com.example.test.springtest.mapper.QuestionExtMapper;
import com.example.test.springtest.mapper.QuestionMapper;
import com.example.test.springtest.model.Comment;
import com.example.test.springtest.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

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
    }
