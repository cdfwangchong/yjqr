package com.cdfg.yjqr.controller;


import cn.cdfg.exceptionHandle.YjqrNotFoundException;
import com.cdfg.yjqr.pojo.until.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@ControllerAdvice
public class YjqrExceptionController {

    @ExceptionHandler(value = YjqrNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(produces="json/html; charset=UTF-8")
    @ResponseBody
    public Result<String> exception (YjqrNotFoundException exception){
        System.out.println(exception.getMsg());
        return new Result<String>(exception.getRetCode(),exception.getMsg(),"");
    }
}
