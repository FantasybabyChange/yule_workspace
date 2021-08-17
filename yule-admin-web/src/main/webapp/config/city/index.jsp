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
						<h3>城市上传</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
					<img id="city-img" src="">
					<input data-upload="" type="file" id="city-upload"><br>
					<input type="text" id="city-id"><br>
					<a href="javascript:;" id="file-upload">上传</a>|<a href="javascript:;" id="file-cancal">取消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.uploadify.min.js"></script>
<script type="text/javascript">

var fileTypeExts = "*";
var errorCodes = ["-100","-110","-120","-130"];
var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(999MB)","零字节的文件","无效的文件类型"];
var fileSystemName = "";
var flashPath = "/uploadify/uploadify.swf";
var uploadifyPath = "http://upload.yuleing.com/cityImage/upload.do";
var leftLine = "/";
var fileJson= "[";
var num = false;
var uploadLimit = 0;
var queueSizeLimit =0;
var fileSizeLimit = "999MB";
var cityId ="";
var imageTitle = "点击查看原图"; 
//页面初始化方法
$(document).ready(function(){
	initUploadify();
	$("#file-upload").click(function(){
		cityId = $("#city-id").val();
		if(cityId==''){
			alert("城市id不能为空");
		}else{
			var formData ={"params":'{"upload_dir":"/'+cityId+'/","upload_name":"city","init":[{"name":"100_100","is_watermark":"false","keep":"false"}],"noinit":[{"name":"600_200","is_watermark":"false","keep":"false"}]}'};
			$("#city-upload").uploadify("settings","formData",formData);	
			$("#city-upload").uploadify("upload");
		}
	});
});

//初始化上传控件
function initUploadify(){

	$("#city-upload").uploadify({
        'swf':flashPath,
        'fileObjName':'files[]',
        'uploader':uploadifyPath,
//        'width':'120',
        'auto': false,
        'removeTimeout':0,
        'multi': false,
        'uploadLimit':uploadLimit,
        'fileSizeLimit':fileSizeLimit,
        'fileTypeDesc':'允许上传范围',
        'buttonText': '添加城市图片',
        'fileTypeExts':fileTypeExts,
        //'progressData':'percentage',
        //speed/percentage
        'queueSizeLimit':queueSizeLimit,
        'removeCompleted':true,
        'onSelect': function (file) {
        	this.queueData.filesErrored=0;
        },
        'onSelectError':function(file,errorCode,errorMsg){
        	for(var i=0;i<errorCodes.length;i++){
        		if(errorCodes[i]==errorCode){
        			this.queueData.errorMsg = errorMsgs[i];
        		}
        	}
        },
        'onCancel':function(file){
       	//	alert("取消"+file.name+"文件");
        },
        'onFallback':function(){
        	alert("浏览器不能兼容Flash,请下载最新版!");
        },
        'onClearQueue':function(queueItemCount) {
        	//alert("清除上传完成"+queueItemCount);
        },
        'onUploadSuccess':function(file,data,response){
			var image_path = "http://images.yuleing.com/area/city/"+cityId+"/100_100city.jpg"+"?t="+new Date().getTime();
			$("#city-img").attr("src",image_path);	
        },
        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
        	//alert("这个文件: " + file.name + " 不能被上传原因: " + errorString);
        	 switch (errorMsg){
             case '400':
                 $('#'+file.id).find('.data').html(" - 上传失败，文件超过大小限制(10MB)");
                 break;
             case '401':
                 $('#'+file.id).find('.data').html(" - 上传失败，零字节的文件");
                 break;
             case '402':
                 $('#'+file.id).find('.data').html(" - 上传失败，无效的文件类型");
                 break;
             case '500':
                 $('#'+file.id).find('.data').html(" - 上传失败，服务器异常");
                 break;
         }
    	},
    	'onDialogClose':function(queueDat){
    		this.queueData.files;
    	}
    });
}

</script>
</html>