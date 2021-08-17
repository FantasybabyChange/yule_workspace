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
    	 Random random = new Random();
    	//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
    	Graphics g = image.getGraphics();
    	g.fillRect(0, 0, image.getWidth(),image.getHeight());
        g.setFont(CaptchaConst.BASE_FONTS[random.nextInt(4)]);
        g.setColor(CaptchaConst.PURPLE_COLOR);
    	//绘制随机字符
        StringBuffer randomString = new StringBuffer("");
        String rand = null;
        int stringPoint = CaptchaConst.TRANSLATE_START*random.nextInt(3);
        for(int i=1;i<=CaptchaConst.RANDOM_STRING_NUM;i++){
            rand = String.valueOf(CaptchaConst.RAND_STRING.charAt(random.nextInt(CaptchaConst.RAND_STRING.length()-1)));
            randomString.append(rand);
            g.translate(0,0);
            g.drawString(rand,stringPoint+CaptchaConst.FONT_SPACING*i , 16);
        }
        g.dispose();
        g=null;
        return randomString.toString();
    }
    /**
     * 生成随机字符串
     */
    public static String getRandomString(){
    	 Random random = new Random();
        StringBuffer randomString = new StringBuffer("");
        String rand = null;
        for(int i=1;i<=CaptchaConst.RANDOM_STRING_NUM;i++){
            rand = String.valueOf(CaptchaConst.RAND_STRING.charAt(random.nextInt(CaptchaConst.RAND_STRING.length()-1)));
            randomString.append(rand);
        }
        return randomString.toString();
    }
    
    /**
     * 生成随机字符串
     */
    public static String getRandomString(int randomLength){
    	 Random random = new Random();
        StringBuffer randomString = new StringBuffer("");
        String rand = null;
        for(int i=1;i<=randomLength;i++){
            rand = String.valueOf(CaptchaConst.RAND_STRING.charAt(random.nextInt(CaptchaConst.RAND_STRING.length()-1)));
            randomString.append(rand);
        }
        return randomString.toString();
    }
    
    /**
     * 生成随机数字字符串
     */
    public static String getRandomNumString(int randomLength){
    	 Random random = new Random();
        StringBuffer randomString = new StringBuffer("");
        String rand = null;
        for(int i=1;i<=randomLength;i++){
            rand = String.valueOf(CaptchaConst.RAND_NUM_STRING.charAt(random.nextInt(CaptchaConst.RAND_NUM_STRING.length()-1)));
            randomString.append(rand);
        }
        return randomString.toString();
    }
}