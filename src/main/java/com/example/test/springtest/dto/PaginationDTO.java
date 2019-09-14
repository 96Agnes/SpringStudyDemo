package com.example.test.springtest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//是否有“前一页”的按钮
    private boolean showFirstPage;//是否有“首页”的按钮
    private boolean showNext;//是否有“后一页”的按钮
    private boolean showEndPage;//是否有“尾页”的按钮

    private Integer currentPage;
    private List<Integer> pages;
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        Integer start,end;
        pages = new ArrayList<>();
        if(page - 3 > 1)
            start = page - 3;
        else
            start = 1;
        if(page + 3 > totalPage)
            end = totalPage;
        else
            end = page + 3;
        for(int i = start; i <= end; i++){
            pages.add(i);
        }
        showPrevious = page == 1 ? false:true;//是否展示上一页
        showNext = page == totalPage ? false:true;//是否展示下一页
        showFirstPage = pages.contains(1) ? false:true;//是否展示首页
        showEndPage = pages.contains(totalPage) ? false:true;//是否展示尾页
        currentPage = page;
    }
}
