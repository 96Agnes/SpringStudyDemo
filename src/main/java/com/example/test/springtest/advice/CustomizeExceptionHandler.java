package com.example.test.springtest.advice;

import com.alibaba.fastjson.JSON;
import com.example.test.springtest.dto.ResultDTO;
import com.example.test.springtest.exception.CustomizeErrorCode;
import com.example.test.springtest.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
        ResultDTO resultDTO;
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            if(ex instanceof CustomizeException){
                //已知异常
                resultDTO = ResultDTO.errorOf((CustomizeException)ex);
            }else{
                //无法处理的异常
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter responseWriter = response.getWriter();
                responseWriter.write(JSON.toJSONString(resultDTO));
                responseWriter.close();
            } catch (IOException ioe) {
            }
            return null;
        }
        else{
            //错误页面跳转
            if(ex instanceof CustomizeException){
                //已知异常
                model.addAttribute("message",ex.getMessage());
            }else{
                //无法处理的异常
                model.addAttribute("message",CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
