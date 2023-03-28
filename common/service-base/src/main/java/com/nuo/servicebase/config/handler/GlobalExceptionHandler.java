package com.nuo.servicebase.config.handler;

import com.nuo.servicebase.config.exception.CustomException;
import commonutils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    public R error1(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(CustomException.class)
    public R error2(CustomException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
