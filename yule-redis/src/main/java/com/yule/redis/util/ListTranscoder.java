package com.yule.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.yule.exception.YuleException;

public class ListTranscoder {
	
	 public static byte[] serialize(Object value) throws Exception {  
         if (value == null) {  
             throw new NullPointerException("Can't serialize null");  
         }  
         byte[] rv=null;  
         ByteArrayOutputStream bos = null;  
         ObjectOutputStream os = null;  
         try {  
             bos = new ByteArrayOutputStream();  
             os = new ObjectOutputStream(bos);  
             os.writeObject(value);  
             os.close();  
             bos.close();  
             rv = bos.toByteArray();  
         } catch (IOException e) {  
        	 new YuleException(e);
             throw e; 
         } finally {  
        	 CloseUtil.close(os);  
             CloseUtil.close(bos);  
         }  
         return rv;  
     }  

     public static Object deserialize(byte[] in) throws Exception {  
         Object rv=null;  
         ByteArrayInputStream bis = null;  
         ObjectInputStream is = null;  
         try {  
             if(in != null) {  
                 bis=new ByteArrayInputStream(in);  
                 is=new ObjectInputStream(bis);  
                 rv=is.readObject();  
                 is.close();  
                 bis.close();  
             }  
         } catch (Exception e) {
             new YuleException(e);
             throw e;
         } finally {  
             CloseUtil.close(is);  
             CloseUtil.close(bis);  
         }  
         return rv;  
     }  
}
