package com.yule.constant;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class CaptchaConst {
	/**
	 * 默认图片大小
	 */
	public static final BufferedImage DEFAULT_IMAGE = new BufferedImage(CaptchaConst.PIC_WIDTH,CaptchaConst.PIC_HEIGHT,BufferedImage.TYPE_INT_BGR);
	/**
	 * 图片宽
	 */
	public static final int PIC_WIDTH = 100;
	/**
	 * 图片高
	 */
    public static final int PIC_HEIGHT = 26;
    /**
     * 随机产生字符数量
     */
    public static final int RANDOM_STRING_NUM = 4;
    /**
     * 图片字体颜色紫色
     */
    public static final Color PURPLE_COLOR = new Color(147,41,140);
    /**
     * 随机的字符串
     */
    public static final String RAND_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 随机的字符串
     */
    public static final String RAND_NUM_STRING = "0123456789";
    /**
     * 字体
     */
    public static final Font BASE_FONT = new Font("宋体",Font.CENTER_BASELINE,18);
    /**
     * Bernard MT 字体
     */
    public static final Font FONT_BERNARD = new Font("Bernard MT",Font.CENTER_BASELINE,18);
    /**
     * 楷体
     */
    public static final Font FONT_KAITI = new Font("楷体",Font.CENTER_BASELINE,18);
    /**
     * 新罗马
     */
    public static final Font FONT_NEWROMAN = new Font("Times New Roman",Font.CENTER_BASELINE,18);
    /**
     * 字体数组
     */
    public static final Font [] BASE_FONTS = {BASE_FONT,FONT_BERNARD,FONT_KAITI,FONT_NEWROMAN};
    
    /**
     * 输出图片类型
     */
    public static final String JPEG = "JPEG";
    /**
     * 字体间距
     */
    public static final int FONT_SPACING = 11;
    
    /**
     * 坐标变换的距离
     */
    public static final int TRANSLATE_START = 16;
        
}
