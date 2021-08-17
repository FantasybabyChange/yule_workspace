package com.yule.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.yule.exception.YuleException;

public class SerializeUtil<T> {
	
	public byte[] ListTobyte(List<T> value) throws Exception {  
		if(null==value||value.size()<=0){
			return null;
		}
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(T t : value){  
                os.writeObject(t);  
            }  
            os.writeObject(null);  
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
	
	@SuppressWarnings("unchecked")
    public List<T> byteToList(byte[] in) throws Exception  {  
        List<T> list = new ArrayList<T>();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
					T t = (T) is.readObject();  
                    if(t == null){  
                        break;  
                    }else{  
                        list.add(t);  
                    }  
                }  
            }  
        } catch (Exception e) {  
        	new YuleException(e);  
        	throw e;
        } finally {  
        	CloseUtil.close(is);  
            CloseUtil.close(bis);  
        }  
        return list;  
    }  
    
	public byte[] objectToByte(T t) throws Exception{
		ObjectOutputStream os = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(t);
			return  bos.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {  
        	CloseUtil.close(os);  
            CloseUtil.close(bos);  
        }  
	}
	
	@SuppressWarnings("unchecked")
	public T byteToObject(byte[] bytes) throws Exception{
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			bis = new ByteArrayInputStream(bytes);
			is = new ObjectInputStream(bis);
			return (T) is.readObject();
		} catch (Exception e) {
			throw e;
		} finally {  
        	CloseUtil.close(is);  
            CloseUtil.close(bis);  
        }  
	}
	
//	public static void main(String[] args) {
//		SerializeUtil<Province> serialize = new SerializeUtil<Province>();
//		Province province = new Province();
//		province.setName("testtest");
//		Jedis jedis = JedisUtil.getInstance().getJedis();
//	}
	
}
