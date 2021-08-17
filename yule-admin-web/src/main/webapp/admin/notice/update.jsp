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
					<h3>通知信息</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
					    <form action="/adminNotice/updateAdminNotice.do" method="post" validform="">
							<fieldset><input type="hidden" name="id" value="${adminNotice.id }" />
								<p>
								   <label>通知标题:</label>
								   <input class="text-input small-input" type="text" id="small-input" name="title" value="${adminNotice.title }" nullmsg="请输入标题!" datatype="" errormsg=""/><br />
								</p>
								<p>
									<label>被通知角色类别</label>
									<select name="type" id="adminNotice_type_id">
										<option value="0">系统</option>
										<option value="1">企业</option>
									</select>
								</p>
								<p>
								   <label>通知内同:</label>
								   <textarea  id="small-input" cols="79" rows="15" name="content" nullmsg="请输入内容!" datatype="" errormsg="">${adminNotice.content }</textarea> <br />
								</p>
								<p>
								   <label>通知附件</label>
								${htmls}
								</p>
								<input data-upload="" type="file" id="attachment-upload"><br>
				   				<a attachment-upload="" href="javascript:;">上传</a>|
				   				<a attachment-cancal="" href="javascript:;">取消</a><br>
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
<script type="text/javascript" src="http://static.yuleing.com/admin/js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" >
$(function(){
	selectValue("#adminNotice_type_id","${adminNotice.type}");
});
</script>
<script type="text/javascript">
var fileTypeExts = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.zip;*.rar;*.doc;*.docx;*.pdf;*.xls;*.xlsx";
var errorCodes = ["-100","-110","-120","-130"];
var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(999MB)","零字节的文件","无效的文件类型"];
var fileSystemName = "";
var flashPath = "/uploadify/uploadify.swf";
var uploadifyPath = "http://upload.yuleing.com/adminAttachment/fileUpload.do";
var leftLine = "/";
var fileJson= "[";
var num = false;
var uploadLimit = 5;
var queueSizeLimit =5;
var fileSizeLimit = "10MB";
var len = 0;

//页面初始化方法
$(document).ready(function(){
	initUploadify();
 	$("a[attachment-upload]").click(function(){
 		len = $("#upload-ui").children().length;
 		if(len>=5){
 			alert("最多上传附件数为5");
 		}else{
			$("#attachment-upload").uploadify("upload","*");
 		}
	});
	$("a[attachment-cancal]").click(function(){
		//$("#attachment-upload").uploadify("stop");
	});
});

//初始化上传控件
function initUploadify(){

	$("#attachment-upload").uploadify({
        'swf':flashPath,
        'fileObjName':'files[]',
        'uploader':uploadifyPath,
//        'width':'120',
        'auto': false,
//        'removeTimeout':0,
        'multi': true,
        'uploadLimit':uploadLimit,
        'fileSizeLimit':fileSizeLimit,
        'fileTypeDesc':'允许上传范围("*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.zip;*.rar;*.doc;*.docx;*.pdf;*.xls;*.xlsx")',
        'buttonText': '附件上传',
        'fileTypeExts':fileTypeExts,
        //'progressData':'percentage',//speed/percentage
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
        	alert(file.name);
        },
        'onFallback':function(){
        	alert("浏览器不能兼容Flash,请下载最新版!");
        },
        'onClearQueue':function(queueItemCount) {
        	alert("清除上传完成"+queueItemCount);
        },
        'onUploadSuccess':function(file,data,response){
        	var temp=eval(data);
        	$.each(temp,function(i,attachment){
        		if(attachment.status==0){
            		var data=insertAdminNoticeAttachment(attachment);               
            		var li = "<li style=\"float: left\"><a href=\"javascript:;\"><img src=\"http://static.yuleing.com/admin/filetype/"+attachment.type+".gif\"></a><div class=\"title\"><span >"+attachment.name+"</span></div><div ><div ><a  data-id=\""+data.id+"\" data-url=\"/adminNoticeAttachment/deleteAdminNoticeAttachment.do\"  href=\"javascript:;\" gallery-delete=\"\" class=\"button\">删除</a>&nbsp;</div></li>";
            		$("#upload-ul").append(li);
        		}else{
        			 $('#'+file.id).find('.data').html(" - 上传失败，"+attachment.message);
        		}
        	});
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
	function insertAdminNoticeAttachment(attachment){
		var mydata;
		var jsonData = '"admin_notice_id":"'+$("input[name='id']").val()+'","name":"'+attachment.name+'","system_name":"'+attachment.system_name+'","path":"'+attachment.path+'","type":"'+attachment.type+'","size":"'+attachment.size+'"';
		jsonData = "{"+jsonData+"}";
		$.ajax({
			url:"/adminNoticeAttachment/insertAdminNoticeAttachment.do",
			type:"post",
			dataType:"json",
			data:jQuery.parseJSON(jsonData),
			async:false,
			success:function(data){
				mydata=data;
			},
		});
		return mydata;
	}
</script>
</body>
</html>