package com.yule.constant;

import java.awt.Font;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yule.util.FileUploadPropertiesUtil;

public final class Const {
	
	private static Properties properties = FileUploadPropertiesUtil.getInstance();
	/**
	 * 互斥锁static唯一
	 */
	public static Object LOCK = new Object();
	/**
	 * 支持生成大中小图的类型
	 */
	public static final String[] IMAGE_TYPES = properties.get("IMAGE_TYPES").toString().split(",");
	/**
	 * 允许上传的文件类型
	 */
	public static final String[] FILE_TYPES = properties.get("FILE_TYPES").toString().split(",");
	/**
	 * 图片水印文字字体
	 */
	public static final String[] FONT_NAME = {"TimesRoman","Courier","Arial","黑体"};
	/**
	 * 图片水印文字样式
	 */
	public static final Integer[] FONT_STYLE = {Font.PLAIN,Font.BOLD,Font.ITALIC};
	/**
	 * 图片水印文字初始大小
	 */
	public static final Integer FONT_SIZE = 20;
	/**
	 * 图片水印文字位置X
	 */
	public static final Integer FONT_POSITION_X = 10;
	/**
	 * 图片水印文字位置Y
	 */
	public static final Integer FONT_POSITION_Y = 20;
	/**
	 * 编码集
	 */
	public static final String ENCODING = "UTF-8";
	/**
	 * 内容编码
	 */
	public static final String CONTENT_TYPE = "text/html;charset=UTF-8";
	
	/**
	 * 0(上传成功)
	 */
	public static final Integer SUCCESS_CODE_0 = 0;
	/**
	 * 上传成功
	 */
	public static final String SUCCESS_MESSAGE_0 = "上传成功!";
	/**
	 * 0(非法上传)
	 */
	public static final Integer ERROR_CODE_0 = 0;
	/**
	 * 非法上传
	 */
	public static final String ERROR_MESSAGE_0 = "非法上传!";
	/**
	 * 405(请上传文件)
	 */
	public static final Integer ERROR_CODE_405 = 405;
	/**
	 * 请上传文件
	 */
	public static final String ERROR_MESSAGE_405 = "请上传文件!";
	
	/**
	 * 400(文件超过大小限制)
	 */
	public static final Integer ERROR_CODE_400 = 400;
	/**
	 * 文件超过大小限制
	 */
	public static final String ERROR_MESSAGE_400 = "文件超过大小限制";
	
	/**
	 * 401(零字节的文件)
	 */
	public static final Integer  ERROR_CODE_401 = 401;
	/**
	 * 零字节的文件
	 */
	public static final String  ERROR_MESSAGE_401 = "零字节的文件";
	/**
	 * 402(无效的文件类型)
	 */
	public static final Integer  ERROR_CODE_402 = 402;
	/**
	 * 无效的文件类型
	 */
	public static final String  ERROR_MESSAGE_402 = "无效的文件类型";
	/**
	 * 500(服务端发生错误)
	 */
	public static final Integer  ERROR_CODE_500 = 500;
	/**
	 * 服务端发生错误
	 */
	public static final String  ERROR_MESSAGE_500 = "服务端发生错误";
	/**
	 * 水印文本类型
	 */
	public static final String  WATERMARK_TYPE_TEXT = "0";
	/**
	 * 水印图片类型
	 */
	public static final String  WATERMARK_TYPE_IMAGE = "1";
	/**
	 * 文件上传目录
	 */
	public static final String  FILE_UPLOAD_DIR = "/upload";
	/**
	 * 头像存放目录
	 */
	public static final String  FILE_IMAGES_DIR = "/images";
	/**
	 * 城市图片存放目录
	 */
	public static final String  FILE_CITY_DIR = "/area/city";
	/**
	 * 文件宽度
	 */
	public static final Map<String,Integer> widths = new HashMap<String, Integer>();
	/**
	 * 文件高度
	 */
	public static final Map<String,Integer> heights = new HashMap<String, Integer>();
	/**
	 * 初始化文件系统名称
	 */
	public static final String SYSTEM_NAME = "system_name";
	/**
	 * 初始化文件存放目录
	 */
	public static final String UPLOAD_DIR = "upload_dir";
	/**
	 * 初始化文件存放目录
	 */
	public static final String UPLOAD_NAME = "upload_name";
	/**
	 * 初始化时压缩
	 */
	public static final String INIT = "init";
	/**
	 * 不初始化时压缩
	 */
	public static final String NO_INIT = "noinit";

	public static final String IMAGE_UPLOADFILETYPE = "jpg";
	
	static{
		//宽度
		widths.put(FileUploadConst.PX_50_50, 50);
		widths.put(FileUploadConst.PX_100_100, 100);
		widths.put(FileUploadConst.PX_150_150, 150);
		widths.put(FileUploadConst.PX_200_150, 200);
		widths.put(FileUploadConst.PX_600_200, 600);
		widths.put(FileUploadConst.PX_860_460, 860);
		widths.put(FileUploadConst.PX_600_400, 600);
		widths.put(FileUploadConst.PX_300_200, 300);
		
		//高度
		heights.put(FileUploadConst.PX_50_50, 50);
		heights.put(FileUploadConst.PX_100_100, 100);
		heights.put(FileUploadConst.PX_150_150, 150);
		heights.put(FileUploadConst.PX_200_150, 150);
		heights.put(FileUploadConst.PX_600_200, 200);
		heights.put(FileUploadConst.PX_860_460, 460);
		heights.put(FileUploadConst.PX_600_400, 400);
		heights.put(FileUploadConst.PX_300_200, 200);

	}
	
}
