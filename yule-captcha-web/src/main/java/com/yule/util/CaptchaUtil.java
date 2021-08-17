package com.yule.util;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.yule.constant.CaptchaConst;

public class CaptchaUtil {
	
    /**
     * 绘制字符串
     */
    public static String getRandomString(BufferedImage image){
    	//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
    	Graphics g = image.getGraphics();
    	g.fillRect(0, 0, image.getWidth(),image.getHeight());
        g.setFont(CaptchaConst.BASE_FONT);
        g.setColor(CaptchaConst.PURPLE_COLOR);
        Random random = new Random();
    	//绘制随机字符
        StringBuffer randomString = new StringBuffer("");
        String rand = null;
        for(int i=1;i<=CaptchaConst.RANDOM_STRING_NUM;i++){
            rand = String.valueOf(CaptchaConst.RAND_STRING.charAt(random.nextInt(CaptchaConst.RAND_STRING.length()-1)));
            randomString.append(rand);
            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(rand, 13*i, 16);
        }
        g.dispose();
        g=null;
        return randomString.toString();
    }
}