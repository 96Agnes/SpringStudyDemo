package com.example.test.springtest.controller;

import com.example.test.springtest.dto.CommentDTO;
import com.example.test.springtest.dto.QuestionDTO;
import com.example.test.springtest.mapper.QuestionMapper;
import com.example.test.springtest.service.CommentService;
import com.example.test.springtest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id);
        //累加阅读数
        questionService.incview(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
