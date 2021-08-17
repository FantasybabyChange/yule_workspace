package com.yule.param;

import java.io.Serializable;

public class FileUploadParam implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4535684679188717622L;
	
	private String name; //文件大小
	private boolean is_watermark; //是否水印
	private boolean keep;//是否保持长宽比
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIs_watermark() {
		return is_watermark;
	}
	public void setIs_watermark(boolean is_watermark) {
		this.is_watermark = is_watermark;
	}
	public boolean isKeep() {
		return keep;
	}
	public void setKeep(boolean keep) {
		this.keep = keep;
	}
	
}
