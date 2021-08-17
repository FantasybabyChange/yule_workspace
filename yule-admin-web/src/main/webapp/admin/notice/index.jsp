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
					<h3>系统通知${parentIdtest}</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">通知列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
				
					<div class="tab-content default-tab" id="tab1">
	                   	<div>
							<form  action="/adminNotice/findAdminNotice.do" method="get" data-query-form="">		
								<span>标题:<input type="text" class="text-input" name="title" value="${adminNoticeQuery.title }" ></span>
								<span>开始时间:<input type="text" class="text-input datetimepicker"   name="start_time" value="${adminNoticeQuery.start_time }"></span>
								<span>结束时间:<input type="text"  class="text-input datetimepicker"  name="end_time" value="${adminNoticeQuery.end_time }"></span><br/>
								是否删除<select name="is_delete"><option  value="">全部</option><option  value="0" >未删除</option><option  value="1">已删除</option></select>
								类型<select name="type"><option  value="">全部</option><option  value="0" >系统</option><option  value="1">企业</option></select>
								<input type="submit" class="button" value="检索" />
	                   		</form>
	                   	</div>	
	                   <table>
	                       	<thead>
	                       	<tr>
							<th>通知标题</th>
							<th>通知内容</th>
							<th>被通知角色类型</th>
							<th>创建时间</th>
							<th>操作</th>
							</tr>
							</thead>
	                      ${htmls }
	                   </table>
					</div>
					
					<div class="tab-content" id="tab2" style="display: none;">
						<form action="/adminNotice/insertAdminNotice.do" method="post" validform="">
							<fieldset>
								<p>
									<label>通知标题</label> <input class="text-input small-input"
										type="text" id="small-input" name="title" nullmsg="请输入标题!" datatype="" errormsg=""/> <br />
								</p>
								<p>
									<label>被通知角色类别</label>
									<select name="type">
										<option value="0">系统</option>
										<option value="1">企业</option>
									</select>
								</p>
								<p>
									<label>通知内容</label>
									<textarea  name="content" cols="79" rows="15" nullmsg="请输入内容!" datatype="" errormsg=""></textarea> <br />
								</p>
								<p>
								  <label>通知附件</label>
								<ul id="upload-ui" style="height: auto;padding-bottom: 60px;">
								</ul>
								</p>
								<p >
				   				<input data-upload="" type="file" id="attachment-upload"><br>
				   				<a attachment-upload="" href="javascript:;">上传</a>|
				   				<a attachment-cancal="" href="javascript:;">取消</a><br>
				   				</p>
				   			</fieldset>
				   				<p>
									<input class="button" type="submit" value="添加"/>
								</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/WdatePicker.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
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
var imageTitle = "点击查看原图"; 
var len = 0;
$(function(){
	trRow.privilegeHtml = '${privilegeHtml}';
	$(".datetimepicker").click(function(){
		WdatePicker();
	});
});
selectValue("select[name='is_delete']","${adminNoticeQuery.is_delete}");
selectValue("select[name='type']","${adminNoticeQuery.type}");

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
        'buttonText': '添加附件',
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
/*         'onUploadStart':function(file){
        	var len = $("#upload-ui").children().length;
        	//$("#attachment-upload").uploadify("cancal");
        }, */
        'onUploadSuccess':function(file,data,response){
        	var temp=eval(data);
        	$.each(temp,function(i,attachment){
        		if(attachment.status==0){
	           		var input  = "<input type=\"hidden\" name=\"name\""+" value=\""+attachment.name+"\" >";
	       			input += "<input type=\"hidden\" name=\"attachment_type\""+" value=\""+attachment.type+"\" >";
	       			input += "<input type=\"hidden\" name=\"size\""+" value=\""+attachment.size+"\" >";
	       			input += "<input type=\"hidden\" name=\"path\""+" value=\""+attachment.path+"\" >";
	       			input += "<input type=\"hidden\" name=\"system_name\""+" value=\""+attachment.system_name+" \">";
	        		var span = "<li style=\"float: left\" >"+input+"<a href=\"javascript:;\"><img  src=\"http://static.yuleing.com/admin/filetype/"+attachment.type+".gif	\"></a><div >"+attachment.name+"<br><a data-del-row=\"\" href=\"javascript:;\"  class=\"button\">删除</a>&nbsp;</div></li>";
				$("#upload-ui").append(span);
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
                 break;
         }
    	},
    	'onDialogClose':function(queueDat){
    		this.queueData.files;
    	}
    });
}
</script>
</body>
</html>