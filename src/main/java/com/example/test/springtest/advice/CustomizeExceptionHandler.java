package com.example.test.springtest.advice;

import com.example.test.springtest.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            //已知异常
            model.addAttribute("message",ex.getMessage());
        }else{
            //无法处理的异常
            model.addAttribute("message","服务出错啦！");
        }
        return new ModelAndView("error");
    }
}
