package com.onseo.courses.ds.controllers;

import com.onseo.courses.ds.models.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController {
    @RequestMapping(value = "errors",method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView=new ModelAndView("errors");
        Errors errors;
        int httpErrorCode=Integer.parseInt(httpServletRequest.getQueryString());

        switch (httpErrorCode){
            case 100:{
                errors=new Errors(100,"invalid credential");
                modelAndView.addObject("errCode", errors.getErrorCode());
                modelAndView.addObject("errMsg",errors.getErrorMessage());
                break;
            }
            case 101:{
                errors=new Errors(101,"invalid accessToken");
                modelAndView.addObject("errCode", errors.getErrorCode());
                modelAndView.addObject("errMsg",errors.getErrorMessage());
                break;
            }
            case 102:{
                errors= new Errors(102,"invalid request");
                modelAndView.addObject("errCode", errors.getErrorCode());
                modelAndView.addObject("errMsg",errors.getErrorMessage());
                break;
            }
        }
        return modelAndView;
    }

}
