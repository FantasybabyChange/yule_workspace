package com.yule.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

import com.yule.constant.CodeConst;
import com.yule.exception.YuleException;

public class FileContentUtil {
	
	/**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) throws Exception{
//        File file = new File(fileName);
        InputStream in = null;
//        try {
//            // 一次读一个字节
//            in = new FileInputStream(file);
//            int tempbyte;
//            while ((tempbyte = in.read()) != -1) {
//                System.out.write(tempbyte);
//            }
//            in.close();
//        } catch (IOException e) {
//        	throw new YuleException(e);
//        }
        try {
            // 一次读多个字节
            byte[] tempbytes = new byte[1024];
            int byteread = 0;
            in = new FileInputStream(fileName);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e) {
        	throw new YuleException(e);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) throws Exception{
    	StringBuffer content = new StringBuffer();
//        File file = new File(fileName);
        Reader reader = null;
//        try {
//            // 一次读一个字符
//            reader = new InputStreamReader(new FileInputStream(file));
//            int tempchar;
//            while ((tempchar = reader.read()) != -1) {
//                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
//                // 但如果这两个字符分开显示时，会换两次行。
//                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
//                if (((char) tempchar) != '\r') {
//                    System.out.print((char) tempchar);
//                }
//            }
//            reader.close();
//        } catch (Exception e) {
//        	throw new YuleException(e);
//        }
        try {
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                	content.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                        	content.append(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e) {
        	throw new YuleException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) throws Exception{
    	StringBuffer content = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        InputStream in = null;
        try {
        	in = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(in,CodeConst.UTF_8));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                content.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
        	throw new YuleException(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (null != in){
            	in.close();
            }
        }
        return content.toString();
    }

    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) throws Exception{
        RandomAccessFile randomFile = null;
        try {
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
        	throw new YuleException(e);
        } finally {
            if (randomFile != null) {
                randomFile.close();
            }
        }
    }
    
    /**
     * 方法追加文件：使用RandomAccessFile
     */
    public static void aileAppendContentRandomAccess(String fileName, String content) throws Exception{
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
        	throw new YuleException(e);
        }
    }

    /**
     * 方法追加文件：使用FileWriter
     */
    public static void fileAppendContentFileWriter(String fileName, String content) throws Exception{
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
        	throw new YuleException(e);
        }
    }

}
