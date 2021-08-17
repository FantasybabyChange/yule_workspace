package com.yule.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.yule.constant.SmsConst;
import com.yule.exception.YuleException;
import com.yule.instance.SmsProperties;

public class SmsUtil {
	
	private Properties smsProperties;
	
	public SmsUtil() throws Exception{
		smsProperties = SmsProperties.getInstance();
	}

	/**
	 * 普通方法：内容相同，手机号不同的，多个手机号中间以英文的逗号分隔。
	 */
	public synchronized boolean send(String mobile,String stime) throws Exception {
		boolean flag = false;
		StringBuffer xml = new StringBuffer("");
		xml.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		xml.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		xml.append("<soap:Body>");
		xml.append("<mdSmsSend_u  xmlns=\"http://tempuri.org/\">");
		xml.append("<sn>" + SmsConst.SN + "</sn>");
		xml.append("<pwd>" + getMD5(SmsConst.SN+SmsConst.PWD) + "</pwd>");
		xml.append("<mobile>" + mobile + "</mobile>");
		xml.append("<content>" + URLEncoder.encode(getTemplateContent(),"UTF-8") + "</content>");
		xml.append("<ext></ext>");
		xml.append("<stime>" + stime + "</stime>");
		xml.append("<rrid></rrid>");
		xml.append("</mdSmsSend_u>");
		xml.append("</soap:Body>");
		xml.append("</soap:Envelope>");
		try {
			URL url = new URL(SmsConst.SERVICE_URL);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			//如果是utf-8,这里请改成bout.write(xml.getBytes("GBK"));
			bout.write(xml.toString().getBytes("GBK"));
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type","text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", SmsConst.MD_SMS_SEND_U_ACTION);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);
//			bout.close();
			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();
			InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine = null;
			String result = null;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<mdSmsSend_uResult>(.*)</mdSmsSend_uResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			isr.close();
			if(!StringUtils.isEmpty(result)&&!result.startsWith("-")){
				flag = true;
			}
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally{
			xml.setLength(0);
		}
		return flag;
	}

	/**
	 * 方法名称：getMD5 功 能：字符串MD5加密 参 数：待转换字符串 返 回 值：加密之后字符串
	 * @throws NoSuchAlgorithmException 
	 */
	private String getMD5(String sourceStr) throws NoSuchAlgorithmException {
		StringBuffer resultStr = new StringBuffer("");
		byte[] temp = sourceStr.getBytes();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(temp);
		// resultStr = new String(md5.digest());
		byte[] b = md5.digest();
		for (int i = 0; i < b.length; i++) {
			char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'A', 'B', 'C', 'D', 'E', 'F' };
			char[] ob = new char[2];
			ob[0] = digit[(b[i] >>> 4) & 0X0F];
			ob[1] = digit[b[i] & 0X0F];
			resultStr.append(new String(ob));
		}
		return resultStr.toString();
	}
	
	private String getTemplateContent() throws Exception{
		Class<?> cls = this.getClass();
		String template = smsProperties.getProperty(cls.getSimpleName().toLowerCase());
		Field[] fields = this.getClass().getDeclaredFields();
		String fieldName  = null;
		for(Field f : fields) {
			fieldName = f.getName();
			if(!"serialVersionUID".equals(fieldName)){
				template = template.replace("#{"+f.getName()+"}",
				getFieldValue(this, fieldName));
			}
	    }
		return template;
	}
	
	private String getFieldValue(Object owner, String fieldName)
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
