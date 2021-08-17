package com.yule.action.company;

import java.io.File;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yule.common.BaseAction;
import com.yule.constant.Const;
import com.yule.constant.CookieConst;
import com.yule.exception.YuleException;
import com.yule.param.FileUploadParam;
import com.yule.runnable.FileUploadRunnable;
import com.yule.util.CookieUtil;
import com.yule.util.FileUploadUtil;
import com.yule.vo.FileUploadErrorVO;

@Controller
@RequestMapping("/companyImage")
public class CompanyImageUploadAction extends BaseAction{
	

	private static final String upload_dir ="/company/";
	/**
	 * 后台上传文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public String upload(@RequestParam(value = "files[]", required = false) MultipartFile[] fileUploads,@RequestParam(value = "params", required = false)String params) throws Exception {
		FileUploadErrorVO error = null;
		String cookieValue = CookieUtil.getCookieValue(request.getCookies(), CookieConst.COMPANYUSER_COOKIE_NAME);
		if(StringUtils.isEmpty(cookieValue)){
			error = new FileUploadErrorVO();
			error.setCode(Const.ERROR_CODE_0);
			error.setMessage(Const.ERROR_MESSAGE_0);
			outputResult(JSONArray.fromObject(error).toString());
			return null;
		}
		try {
//			long stratTime = System.currentTimeMillis();
			JSONObject obj = null;
			if(!StringUtils.isEmpty(params)){
				obj = JSONObject.fromObject(params);
			}
			JSONArray array = new JSONArray();
			JSONArray initArray = new JSONArray();
			if(null!=fileUploads&&fileUploads.length>0){
				for(MultipartFile fileUpload:fileUploads){
					
					String fileUploadFileName = fileUpload.getOriginalFilename();
					// 文件类型
					String uploadFileType = fileUploadFileName.substring(fileUploadFileName.lastIndexOf(".")+1,fileUploadFileName.length()); 
		
					int fileSize = (int) fileUpload.getSize();
		
					// 验证文件
					error = FileUploadUtil.checkImageFile(fileSize,uploadFileType);
					
					// 判断是否有错误消息
					if (null!=error) {
						break;
					}
					String filePath = Const.FILE_IMAGES_DIR+upload_dir+obj.getString(Const.UPLOAD_DIR)+"/";
					String systemFileName= obj.getString(Const.UPLOAD_NAME)+ "." + Const.IMAGE_UPLOADFILETYPE;
					// 本机文件地址
					FileUploadUtil.fileUpload(new File(filePath,systemFileName), fileUpload);
					
					fileUpload.transferTo(new File(filePath+systemFileName));
					if(null!=obj){
						if(obj.containsKey(Const.INIT)){
							initArray = JSONArray.fromObject(obj.get(Const.INIT));
							FileUploadParam fileUploadParam = null;
							for(int i=0;i<initArray.size();i++){
								fileUploadParam = (FileUploadParam)JSONObject.toBean(initArray.getJSONObject(i), FileUploadParam.class);
								FileUploadUtil.createCondenseAndWatermark(filePath, systemFileName,fileUploadParam);
							}
							initArray.clear();
							initArray = null;
						}
						
						if(obj.containsKey(Const.NO_INIT)){
							initArray = JSONArray.fromObject(obj.get(Const.NO_INIT));
							FileUploadParam fileUploadParam = null;
							for(int i=0;i<initArray.size();i++){
								fileUploadParam = (FileUploadParam)JSONObject.toBean(initArray.getJSONObject(i), FileUploadParam.class);
								FileUploadRunnable r = new FileUploadRunnable(filePath, systemFileName,fileUploadParam);
								Thread t = new Thread(r);
								t.start();
							}
							initArray.clear();
							initArray = null;
						}
					}
				}
			}else{
				error = new FileUploadErrorVO();
				error.setCode(Const.ERROR_CODE_405);
				error.setMessage(Const.ERROR_MESSAGE_405);
			}
			if(null==error){
			}else{
//				response.setStatus(error.getCode());
				array.add(JSONArray.fromObject(error));
			}
			outputResult(array.toString());
			array.clear();
			array = null;
			
//			long resultTime = System.currentTimeMillis() - stratTime;
//			logger.info("执行时间:" + DateUtil.dateFormat(new Date(resultTime),"yyyy-MM-dd HH:mm:ss"));
			
		} catch (Exception e) {
			response.setStatus(Const.ERROR_CODE_500);
			new YuleException("/admin/upload发生错误!",e);
			e.printStackTrace();
		}
		return null;
	}
}
