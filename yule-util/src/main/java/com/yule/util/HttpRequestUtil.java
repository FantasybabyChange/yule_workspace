package com.yule.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.yule.constant.CodeConst;
import com.yule.exception.YuleException;

/**
 * 模拟http get和post请求工具类
 */
public class HttpRequestUtil {

	//连接超时时间(单位毫秒)
	private static Integer HTTP_TIME_OUT=2000;
	//读数据超时时间(单位毫秒)
	private static Integer HTTP_SO_TIME_OUT=10000;
	
//	public static void main(String[] args) throws Exception {
////		String[] params = {"186","153","135","189","139","130"};
////		StringBuffer phonelast = new StringBuffer("");
////		for(int i=0;i<100000;i++){
////			for(int j=0;j<8;j++){
////				phonelast.append(new Random().nextInt(9));
////			}
////			System.out.println(HttpRequestUtil.doGet("http://account.yeduhui.cn/add.do?info=1=1&password=1=1"));
////			phonelast.setLength(0);
////		}
//		System.out.println(HttpRequestUtil.doGet("http://user.yuleing.com/mailAuthVerifyCaptcha.do"));
//	}

	/**
	 * 读取图片
	 */
	public static byte[] getImage(String urlStr) throws Exception {
		byte[] imageRaw = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(
					urlConnection.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			out.flush();
			imageRaw = out.toByteArray();
			urlConnection.disconnect();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {

		}
		return imageRaw;
	}
	
	/** 
     * 其中部分特殊字符已经处理 
     */  
    public static String doGet(String linkUrl) throws Exception{  
    	HttpURLConnection connection = null;
        try {  
            URL url = new URL(linkUrl);  
            connection = (HttpURLConnection) url.openConnection();  
            connection.setConnectTimeout(HTTP_TIME_OUT);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(HTTP_SO_TIME_OUT);// 设置读取数据超时时间，单位毫秒
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CodeConst.UTF_8));  
            StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
        }catch (Exception e) {  
            throw e; 
        }finally{
        	connection.disconnect();  
        }
    }
    
    /** 
     * 其中部分特殊字符已经处理 
     */  
    public static String doGet(String linkUrl,Integer connectionTimeOut,Integer readTimeout) throws Exception{  
    	HttpURLConnection connection = null;
        try {  
            URL url = new URL(linkUrl);  
            connection = (HttpURLConnection) url.openConnection();  
            connection.setConnectTimeout(connectionTimeOut);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(readTimeout);// 设置读取数据超时时间，单位毫秒
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CodeConst.UTF_8));  
            StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
        }catch (Exception e) {  
        	new YuleException(e);
            throw e; 
        }finally{
        	connection.disconnect();  
        }
    }
    
    /** 
     * 其中部分特殊字符已经处理 
     */  
    public static void doGeVoid(String linkUrl,Integer connectionTimeOut,Integer readTimeout) throws Exception{  
    	HttpURLConnection connection = null;
        try {  
            URL url = new URL(linkUrl);  
            connection = (HttpURLConnection) url.openConnection();  
            connection.setConnectTimeout(connectionTimeOut);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(readTimeout);// 设置读取数据超时时间，单位毫秒
        }catch (Exception e) {  
        	new YuleException(e);
            throw e; 
        }finally{
        	connection.disconnect();  
        }
    }

	/**
	 * post
	 */
	public static String doPost(String linkUrl, String content) throws Exception{
		HttpURLConnection connection = null;
		try {
			URL url = new URL(linkUrl);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(HTTP_TIME_OUT);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(HTTP_SO_TIME_OUT);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestProperty("contentType", CodeConst.UTF_8);  
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.write(content.getBytes());// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), CodeConst.UTF_8));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			new YuleException(e);
			throw e;
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
	}
	
}
