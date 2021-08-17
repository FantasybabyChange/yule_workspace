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
import com.yule.constant.CaptchaSessionConst;
import com.yule.exception.YuleException;
import com.yule.util.CaptchaUtil;
import com.yule.util.ConvertUtil;
import com.yule.util.EncryptUtil;

public class CaptchaServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2525719382140686390L;

	private final static BufferedImage DEFAULT_IMAGE = new BufferedImage(CaptchaConst.PIC_WIDTH,CaptchaConst.PIC_HEIGHT,BufferedImage.TYPE_INT_BGR);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String type = request.getParameter("type");
        	BufferedImage image = null;
    		if(!StringUtils.isEmpty(type)){
    			String[] types = type.split("_");
    			image = new BufferedImage(ConvertUtil.stringToInteger(types[0]),ConvertUtil.stringToInteger(types[1]),BufferedImage.TYPE_INT_BGR);
    		}else{
    			image = DEFAULT_IMAGE;
    		}
            String randomString = CaptchaUtil.getRandomString(image);
            HttpSession session = request.getSession();
            session.removeAttribute(CaptchaSessionConst.SALESMAN_CAPTCHA_KEY);
            session.setAttribute(CaptchaSessionConst.SALESMAN_CAPTCHA_KEY, EncryptUtil.encryptToMD5(randomString.toLowerCase()));
        	//将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, CaptchaConst.JPEG, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            new YuleException("生成验证码发生错误",e);
        }
//    	super.doGet(request, response);
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    }
}