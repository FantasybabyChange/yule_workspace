<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/css/uploadify.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>用户信息</h3>
					<a href="javascript:;" onclick="history.back();" class="goback-btn button">返回</a>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
					    <form action="/user/updateUser.do?" method="post" validform="">
							<fieldset><input type="hidden" name="id" value="${htmls.id }"  />
								<p>
									<label>用户等级</label>
							        <label>${htmls.user_level_name}</label>	
								</p>
								<p>
									<label>头像</label>
									<input 	id="user_face"  type="file"  data-upload="" />
				                    <img alt="" src="http://images.yuleing.com/user/${htmls.id }/150_150user_face.jpg" id="user_face_image" style="width: 150;height: 150">
								</p>
								<p>
								<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script language="javascript" type="text/javascript">
document.write("<script type='text/javascript' "
		+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date()
		+ "'></s" + "cript>");
</script>
<script type="text/javascript">
var errorCodes = ["-100","-110","-120","-130"];
var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(10MB)","零字节的文件","无效的文件类型"];
var fileSystemName = "";	
var fileTypeExts = "*.jpg;*.png;*.gif;*.jpeg";
var flashPath = "/uploadify/uploadify.swf" ;
var uploadifyPath = "http://upload.yuleing.com/userImage/upload.do";
var uploadLimit = 0;
var queueSizeLimit = 5;
var fileSizeLimit = "2MB";
var user_id='';
var imageTitle = "点击查看原图"; 
//页面初始化方法
$(function(){
	user_id='${htmls.id }';
	$("input[data-upload]").uploadify({
        'swf':flashPath,
        'fileObjName':'files[]',
        'uploader':'http://upload.yuleing.com/userImage/upload.do',
        'auto': true,
        'removeTimeout':0,
        'multi': false, //多个文件
        'uploadLimit':uploadLimit,
        'fileSizeLimit':fileSizeLimit,
        'fileTypeDesc' : '图片文件(*.jpg;*.png;*.gif;*.jpeg)',
        'buttonText': '点击上传',
        'fileTypeExts':fileTypeExts,
        'progressData':'percentage',
        'speed':'percentage',
        'queueSizeLimit':queueSizeLimit,
        'removeCompleted':true,
        'onSelect': function (file) {
        	this.queueData.filesErrored=0;
        },
        'onOpen':function(event,ID,fileObj){
        	//alert(ID);
        },
        'onSelectError':function(file,errorCode,errorMsg){
        	for(var i=0;i<errorCodes.length;i++){
        		if(errorCodes[i]==errorCode){
        			this.queueData.errorMsg = errorMsgs[i];
        		}
        	}
        },
        'onCancel':function(file){
        	//alert(file.name);
        },
        'onFallback':function(){
        	alert("浏览器不能兼容Flash,请下载最新版!");
        },
        'onClearQueue':function(queueItemCount) {
        	alert("清除上传完成"+queueItemCount);
        },
        'onUploadStart':function(file){
        	var name = this.wrapper.attr("id");
        	var id =this.wrapper.selector;
    		var formData ={"params":'{"upload_dir":"'+user_id+'","upload_name":"'+name+'","noinit":[{"name":"50_50","is_watermark":"false","keep":"false"},{"name":"100_100","is_watermark":"false","keep":"false"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'};
    		$(id).uploadify("settings","formData",formData);
        },
        'onUploadSuccess':function(file,data,response){
        	var temp=eval(data);
        	var image_path = "http://images.yuleing.com/user/"+user_id+"/150_150"+this.wrapper.attr("id")+".jpg"+"?t="+ new Date().getTime();
        	var id =this.wrapper.selector;
        	$(id).uploadify('settings', 'buttonText', '正在加载');
        	$(id+"_image").attr("src",image_path);
        	$(id).uploadify('settings', 'buttonText', '重新上传');

        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
        	//alert("这个文件: " + file.name + " 不能被上传原因: " + errorString);
        	 switch (errorMsg){
             case '400':
                 $('#'+file.id).find('.data').html(" - 上传失败，文件超过大小限制(2MB)");
                 break;
             case '401':
                 $('#'+file.id).find('.data').html(" - 上传失败，零字节的文件");
                 break;
             case '402':
                 $('#'+file.id).find('.data').html(" - 上传失败，无效的文件类型");
                 break;
             case '500':
                 $('#'+file.id).find('.data').html(" - 上传失败，服务器问题");
                 break;
         }
    	},
    	'onDialogClose':function(queueDat){
    		this.queueData.files;
    	}
    });
});
</script>
</body>
</html>