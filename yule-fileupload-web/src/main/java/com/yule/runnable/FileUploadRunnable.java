package com.yule.runnable;

import com.yule.exception.YuleException;
import com.yule.param.FileUploadParam;
import com.yule.util.FileUploadUtil;


public class FileUploadRunnable implements Runnable {

	// 系统文件名称
	private String systemFileNames;
	// 源文件路径
	private String filePaths;
	
	private FileUploadParam fileUploadParam;
	
	public FileUploadRunnable(String filePaths,String systemFileNames,FileUploadParam fileUploadParam) {
		super();
		this.systemFileNames = systemFileNames;
		this.filePaths = filePaths;
		this.fileUploadParam = fileUploadParam;
	}

	public void run() {
		try {
			//logger.info("调用创建缩略图和水印方法FileUploadUtil.createThumbnailAndWatermark");
			FileUploadUtil.createCondenseAndWatermark(filePaths, systemFileNames,fileUploadParam);
		} catch (Exception e) {
			new YuleException("发生错误!",e);
			e.printStackTrace();
		}finally{
			System.gc();
		}
	}
}
