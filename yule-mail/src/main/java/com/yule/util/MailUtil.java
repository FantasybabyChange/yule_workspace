package com.yule.util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.yule.constant.CodeConst;
import com.yule.constant.MailConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.instance.MailInstance;


public class MailUtil {
	
	private Session session;
	
	private MimeMessage mimeMessage;
	
	private Multipart multipart;
	
	private Properties props = null;
	
	private Properties mailTemplate = null;
	
	private Properties mailTitle = null;
	
	public MailUtil() throws Exception{
		try {
			props = MailInstance.getInstance(MailConst.MAIL_RESOURCE);
			mailTemplate = MailInstance.getInstance(MailConst.MAIL_TEMPLATE);
			mailTitle = MailInstance.getInstance(MailConst.MAIL_TITLE);
			session = Session.getDefaultInstance(props);
			mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(MimeUtility.encodeText(MailConst.MAIL_SEND_USERNAME)+"<"+props.getProperty("mail.form")+">"));
			multipart = new MimeMultipart();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 
	 */
	private BodyPart addFile(String file) throws Exception{
		BodyPart bp = new MimeBodyPart();
		try {
			DataSource fileds = new FileDataSource(new File(file));
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(MimeUtility.encodeWord(fileds.getName()));
		} catch (Exception e) {
			throw e;
		}
		return bp;
	}
	
	private String getTemplateContent() throws Exception{
		Class<?> cls = this.getClass();
		InputStream input = MailInstance.class.getClassLoader().getResourceAsStream(mailTemplate.getProperty(cls.getSimpleName().toLowerCase()));
		byte[] buf = new byte[1024];
		int hasRead = 0;
		StringBuffer contentBuffer = new StringBuffer();
		while ((hasRead = input.read(buf)) > 0) {
			contentBuffer.append(new String(buf,0,hasRead,CodeConst.UTF_8));
		}
		String template = contentBuffer.toString();
		Field[] fields = this.getClass().getDeclaredFields();
		String fieldName  = null;
		for(Field f : fields) {
			fieldName = f.getName();
			if(!"serialVersionUID".equals(fieldName)){
				template = template.replace("#{"+f.getName()+"}",
				getFieldValue(this, fieldName));
			}
	    }
		template = template.replace("#{currentDate}", DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_CN));
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
	
	/**
	 * 
	 */
	private BodyPart addContent() throws Exception{
		BodyPart bp = new MimeBodyPart();
		try {
			bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=utf-8>"+ getTemplateContent(), CodeConst.TEXT_HTML);
		} catch (Exception e) {
			throw e;
		}
		return bp;
	}
	
	private void setConnect(Transport transport) throws Exception{
		transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.form"),
				props.getProperty("mail.password"));
	}
	
	public boolean send(String toMail,String... files) throws Exception{
		boolean flag = false;
		Transport transport = null;
		try{
			mimeMessage.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(toMail));
			mimeMessage.setSubject(mailTitle.getProperty(this.getClass().getSimpleName().toLowerCase()));
			multipart.addBodyPart(addContent());
			for(String file :files){
				multipart.addBodyPart(addFile(file));
			}
			mimeMessage.setContent(multipart);
			mimeMessage.saveChanges();
			//log.info("正在发送");
			transport = session.getTransport("smtp");
			setConnect(transport);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			//log.info("发送成功");
			flag = true;
		} catch(Exception e){
			new YuleException("发送失败",e);
			throw e;
		} finally{
			if(null!=transport){
				transport.close();
			}
		}
		return flag;
	}
	
}
