package com.example.test.springtest.enums;

import com.example.test.springtest.model.Comment;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2)
    ;
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum typeEnum : CommentTypeEnum.values()) {
            if(typeEnum.getType() == type)
                return true;
        }
        return false;
    }

    public Integer getType(){
        return type;
    }
    CommentTypeEnum(Integer type){
        this.type = type;
    }
}
