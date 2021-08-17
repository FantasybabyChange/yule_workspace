package com.yule.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.yule.enumerate.DateStyle;

public class DateConverter implements WebBindingInitializer {  
  
    public void initBinder(WebDataBinder binder, WebRequest request) {  
        SimpleDateFormat df = new SimpleDateFormat(DateStyle.YYYY_MM_DD_HH_MM_SS_EN.getValue());  
        //System.out.println("DateConverter implements WebBindingInitializer");  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));  
    }  
  
} 