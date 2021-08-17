package com.yule.util;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class SensitiveWordFilter {
	private static Pattern pattern = null;

	/**
	 * 初始化敏感词文件，读取敏感词汇
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static void initPattern() {
		StringBuffer patternBuf = new StringBuffer("");
		try {
			InputStream in = SensitiveWordFilter.class.getClassLoader().getResourceAsStream("sensitiveword.properties");
			Properties pro = new Properties();
			pro.load(in);
			Enumeration enu = pro.propertyNames();
			patternBuf.append("(");
			while (enu.hasMoreElements()) {
				patternBuf.append((String) enu.nextElement() + "|");
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			patternBuf.append(")");
			// unix换成UTF-8
			// pattern = Pattern.compile(new
			// String(patternBuf.toString().getBytes("ISO-8859-1"), "UTF-8"));
			// win下换成gb2312
			pattern = Pattern.compile(patternBuf.toString());
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

	/**
	 * 过滤对象，利用反射机制过滤
	 * 
	 * @param obj
	 * @param class1
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object doFilter(Object obj) {
		PropertyDescriptor pd = null;
		Class clazz = obj.getClass();
		Method getMethod = null;
		Method setMethod = null;
		Method getSupMethod = null;
		Method setSupMethod = null;
		Field[] fields = clazz.getDeclaredFields();
		Class supClazz = clazz.getSuperclass();
		// 父类如果不是Object则过滤父类中的数据
		if (!supClazz.toString().contains("Object")) {
			Field[] supFields = supClazz.getDeclaredFields();
			for (Field supField : supFields) {
				if (supField.toString().contains("final") || supField.toString().contains("static")) {
					continue;
				}
				try {
					pd = new PropertyDescriptor(supField.getName(), clazz);
					if (pd.getPropertyType().toString().toLowerCase() .contains("string")) {
						getSupMethod = pd.getReadMethod();
						setSupMethod = pd.getWriteMethod();
						String supStr = (String) getSupMethod.invoke(obj);
						if (!StringUtils.isEmpty(supStr)) {
							supStr = doFilter(supStr);
							setSupMethod.invoke(obj, supStr);
						}
					}
				} catch (Exception e) {
				}
			}
		}
		// 过滤对象类中的数据
		for (Field field : fields) {
			if (field.toString().contains("final")
					|| field.toString().contains("static")) {
				continue;
			}
			try {
				pd = new PropertyDescriptor(field.getName(), clazz);
				if (pd.getPropertyType().toString().toLowerCase()
						.contains("string")) {
					getMethod = pd.getReadMethod();
					setMethod = pd.getWriteMethod();
					String str = (String) getMethod.invoke(obj);
					if (str != null) {
						setMethod.invoke(obj, doFilter(str));
					}
				}
			} catch (Exception e) {
			}
		}
		return obj;
	}

//	/**
//	 * 过滤集合中的数据
//	 * 
//	 * @param pagination
//	 * @return
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static Pagination doFilter(Pagination pagination) {
//		List list = new ArrayList();
//		for (Object obj : pagination.getList()) {
//			list.add(doFilter(obj));
//		}
//		pagination.setList(list);
//		return pagination;
//	}

//	 public static void main(String[] args) {
//	 List<People> pl = new ArrayList<People>();
//	 Pagination page = new Pagination();
//	 People p = new People();
//	 try {
//	 p.setRealName("国敏感词一院学位办就敏感词三的报道表示敏感词二");
//	 p = (People) doFilter(p);
//	 pl.add(p);
//	 p = new People();
//	 p.setRealName("敏感词一敏感词三敏感词二");
//	 pl.add(p);
//	 page.setList(pl);
//	 page = doFilter(page);
//	 for (int i = 0; i < page.getList().size(); i++) {
//	 p = (People) page.getList().get(i);
//	 System.out.println(p.getRealName());
//	 }
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }
//	 }

	/**
	 * 过滤字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String doFilter(String str) {
		initPattern();
		Matcher m = pattern.matcher(str);
		str = m.replaceAll("*");
		return str;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(doFilter("我是一个小姐,习近平,怎么办"));
		System.out.println(System.currentTimeMillis()-start);
	}
}
