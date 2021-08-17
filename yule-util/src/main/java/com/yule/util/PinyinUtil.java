package com.yule.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.yule.exception.YuleException;

public class PinyinUtil {
	
	private static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	
	static{
		// 设置汉字拼音输出的格式
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写  
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字  
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// U显示  
	}
	
	/**
	 * 将汉字转换为全拼
	 */
	public static String getPinYin(String src) throws Exception {
		char[] chars = src.toCharArray();
		StringBuffer pinyins = new StringBuffer("");
		int length = chars.length;
		String[] pinyin = new String[length];
		try {
			for (int i = 0; i < length; i++) {
				// 判断能否为汉字字符
				if (Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
					pinyin = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);// 将汉字的几种全拼都存到t2数组中
					pinyins.append(pinyin[0]);// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					pinyins.append(Character.toString(chars[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			new YuleException(e);
			throw e;
		}
		return pinyins.toString();
	}

	public static void main(String[] args) {
		String cnStr = "西安";
		try {
			System.out.println(getPinYin(cnStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
