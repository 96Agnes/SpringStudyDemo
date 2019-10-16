package com.example.test.springtest.mapper;

import com.example.test.springtest.model.Question;
import com.example.test.springtest.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}