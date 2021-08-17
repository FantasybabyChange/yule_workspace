package com.yule.util;

import java.lang.reflect.Method;

public class ReflectUtil {
	
	public String getFieldValue(Object owner, String fieldName)
    {
        return invokeMethod(owner, fieldName,null).toString();
    }
	
	/**
     * 
     * 执行某个Field的getField方法
     */
    private Object invokeMethod(Object owner, String fieldName, Object[] args)
    {
        Class<? extends Object> ownerClass = owner.getClass();
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        Method method = null;
        try 
        {
            method = ownerClass.getMethod("get" + methodName);
        } 
        catch (SecurityException e) 
        {
        } 
        catch (NoSuchMethodException e) 
        {
            return "";
        }
        try
        {
            return method.invoke(owner);
        } 
        catch (Exception e)
        {
            return "";
        }
    }
	
}
