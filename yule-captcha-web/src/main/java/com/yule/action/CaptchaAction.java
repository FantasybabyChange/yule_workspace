package com.yule.action;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yule.common.BaseAction;
import com.yule.constant.CaptchaConst;
import com.yule.exception.YuleException;
import com.yule.util.ConvertUtil;

@Controller
@Scope("prototype")
public class CaptchaAction extends BaseAction {
	
	private final static BufferedImage DEFAULT_IMAGE = new BufferedImage(CaptchaConst.PIC_WIDTH,CaptchaConst.PIC_HEIGHT,BufferedImage.TYPE_INT_BGR);
	
	
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public String captcha(@RequestParam(value="type",required=false)String type) throws Exception {
		BufferedImage image = null;
		if(!StringUtils.isEmpty(type)){
			String[] types = type.split("_");
			image = new BufferedImage(ConvertUtil.stringToInteger(types[0]),ConvertUtil.stringToInteger(types[1]),BufferedImage.TYPE_INT_BGR);
		}else{
			image = DEFAULT_IMAGE;
		}
//        String randomString = CaptchaUtil.getRandomString(image);
//        session.removeAttribute(CaptchaSessionConst.RANDOM_CODE_KEY);
//        session.setAttribute(CaptchaSessionConst.RANDOM_CODE_KEY, randomString);
        try {
        	//将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, CaptchaConst.JPEG, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            new YuleException("生成验证码发生错误",e);
        }
        return null;
	}
	
}
