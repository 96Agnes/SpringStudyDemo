package com.example.test.springtest.dto;

import com.example.test.springtest.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private String content;
    private User user;
}
