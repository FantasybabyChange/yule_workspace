package com.yule.servlet;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.yule.constant.CaptchaConst;
import com.yule.constant.LoginCaptchaSessionConst;
import com.yule.exception.YuleException;
import com.yule.util.CaptchaUtil;
import com.yule.util.ConvertUtil;
import com.yule.util.EncryptUtil;

public class CaptchaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 402379118957196623L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String type = request.getParameter("type");
    	BufferedImage image = null;
		if(!StringUtils.isEmpty(type)){
			String[] types = type.split("_");
			try {
				image = new BufferedImage(ConvertUtil.stringToInteger(types[0]),ConvertUtil.stringToInteger(types[1]),BufferedImage.TYPE_INT_BGR);
			} catch (Exception e) {
				new YuleException("生成图片发生错误",e);
			}
		}else{
			image = CaptchaConst.DEFAULT_IMAGE;
		}
		try {
            String randomString = EncryptUtil.encryptToMD5(CaptchaUtil.getRandomString(image).toLowerCase());
            HttpSession session = request.getSession();
            session.setAttribute(LoginCaptchaSessionConst.USER_LOGIN_CAPTCHA, randomString);
        	//将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, CaptchaConst.JPEG, response.getOutputStream());
        } catch (Exception e) {
            new YuleException("生成验证码发生错误",e);
        }
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    }
}