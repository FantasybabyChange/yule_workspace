package com.yule.action.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yule.common.BaseAction;
import com.yule.constant.Const;
import com.yule.constant.CookieConst;
import com.yule.constant.DoMainConst;
import com.yule.enumerate.DateStyle;
import com.yule.exception.YuleException;
import com.yule.util.CookieUtil;
import com.yule.util.DateUtil;
import com.yule.util.FileUploadUtil;
import com.yule.vo.FileUploadErrorVO;
import com.yule.vo.FileUploadVO;

@Controller
@RequestMapping("/adminAttachment")
public class AdminAttachmentUploadAction  extends BaseAction{

	/**
	 * 后台上传文件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
	public String fileUpload(@RequestParam(value = "files[]", required = false) MultipartFile[] fileUploads) throws Exception {
		FileUploadErrorVO error = null;
		String cookieValue = CookieUtil.getCookieValue(request.getCookies(), CookieConst.ADMINUSER_COOKIE_NAME);
		if(StringUtils.isEmpty(cookieValue)){
			error = new FileUploadErrorVO();
			error.setCode(Const.ERROR_CODE_0);
			error.setMessage(Const.ERROR_MESSAGE_0);
			outputResult(JSONArray.fromObject(error).toString());
			return null;
		}
		try {
//			long stratTime = System.currentTimeMillis();
			List<FileUploadVO> fileUploadVOs = new ArrayList<FileUploadVO>();
			JSONArray array = new JSONArray();
			
			if(null!=fileUploads&&fileUploads.length>0){
				for(MultipartFile fileUpload:fileUploads){
					
					String fileUploadFileName = fileUpload.getOriginalFilename();
					// 文件类型
					String uploadFileType = fileUploadFileName.substring(fileUploadFileName.lastIndexOf(".")+1,fileUploadFileName.length());
		
					int fileSize = (int) fileUpload.getSize();
					
					// 验证文件
					error = FileUploadUtil.checkFile(fileSize,uploadFileType);
					
					// 判断是否有错误消息
					if (null!=error) {
						break;
					}
		
					String doMainBasePath = FileUploadUtil.getDoMainBasePath(uploadFileType, DateUtil.DateToString(new Date(), DateStyle.YYYYMMDD));
					
					String filePath = Const.FILE_UPLOAD_DIR+doMainBasePath;
					
					String systemFileName = UUID.randomUUID().toString() + DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSSSSS) + "." + uploadFileType;
					
					// 本机文件地址
					int status = FileUploadUtil.fileUpload(new File(filePath,systemFileName), fileUpload);
					
		//			fileUpload.transferTo(new File(filePath+systemFileName));
					
					FileUploadVO fileUploadVO = new FileUploadVO();
					
					fileUploadVO.setSize(fileSize);
					fileUploadVO.setName(fileUploadFileName);
					fileUploadVO.setSystem_name(systemFileName);
					fileUploadVO.setType(uploadFileType);
					fileUploadVO.setPath(DoMainConst.P_URL+doMainBasePath);
					fileUploadVO.setStatus(status);
					fileUploadVOs.add(fileUploadVO);
					
				}
			}else{
				error = new FileUploadErrorVO();
				error.setCode(Const.ERROR_CODE_405);
				error.setMessage(Const.ERROR_MESSAGE_405);
			}
			if(null==error){
				array = JSONArray.fromObject(fileUploadVOs);
				fileUploadVOs.clear();
			}else{
				response.setStatus(error.getCode());
				array.add(JSONArray.fromObject(error));
			}
			outputResult(array.toString());
//			long resultTime = System.currentTimeMillis() - stratTime;
//			logger.info("执行时间:" + DateUtil.dateFormat(new Date(resultTime),"yyyy-MM-dd HH:mm:ss"));
			
		} catch (Exception e) {
			response.setStatus(Const.ERROR_CODE_500);
			new YuleException("/adminAttachmentUpload/upload发生错误!",e);
			e.printStackTrace();
		}
		return null;
	}
}
