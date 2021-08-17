<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
							<h3>业务员基本信息</h3>
					<a class="goback-btn button" href="javascript:;">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
						<form action="/salesman/updateSalesman.do"  method="post" validform="">
							<fieldset>
								<input type="hidden" name="id" value="${salesman.id}" />
								<p>
									<label>姓名</label> 
									<input class="text-input small-input" type="text" name="name" value="${salesman.name }" nullmsg="请输入姓名!" datatype="" errormsg="" />
								</p>
								<p>
							       <label>性别</label><select name="sex"><option  value="0" >男</option><option  value="1">女</option></select>
								<p>
									<label>年龄</label> 
									<input class="text-input" type="text" name="age" value="${salesman.age }" nullmsg="请输入年龄" datatype="n" errormsg="请输入数字" />
								</p>
								<p>
									省&nbsp;<select name="area_province_id" data-init="province" data-ajax="city" data-target="select[name='area_city_id']" data-name="provinceId" ><option value="">请选择</option></select>
									市&nbsp;<select name="area_city_id" data-ajax="county;business" data-target="select[name='area_county_id'];select[name='area_business_id']" data-name="cityId"><option value="">请选择</option></select>
								</p>
								<p>
									<label>地址</label> 
									<input class="text-input medium-input datepicker" type="text" name="address" value="${salesman.address }" nullmsg="请输入地址!" datatype="" errormsg="" />  
								</p>
								<p>
									<label>用户头像</label> 
								</p>
					            <p>
										<input id="head"  type="file"  data-upload="" />
										<img alt="" src="http://images.yuleing.com/salesman/${salesman.id }/150_150head.jpg" id="head_image" style="width: 150;height: 150">
									
								</p>
								<p>
									<label>身份证号码</label> 
									<input class="text-input" type="text" name="identity_card" value="${salesman.identity_card}" nullmsg="请输入身份证号!" datatype="ic" errormsg="请输入正确的身份证号码" />  
								</p>
								<p>
									<label>身份证照片</label> 
								</p>
					            <p>
	            					<input id="identity"  type="file"  data-upload="" />
									<img alt="" src="http://images.yuleing.com/salesman/${salesman.id }/150_150identity.jpg" id="identity_image" style="width: 150;height: 150">
									</p>
					            <p>
									<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
							<div class="clear"></div>
						</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/jquery.wysiwyg.js"></script>
<script language="javascript" type="text/javascript">
	//防止客户端缓存文件，造成uploadify.js不更新，而引起的“喔唷，崩溃啦”
	document.write("<script type='text/javascript' "
			+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date().getTime()
			+ "'></s" + "cript>");
</script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.area.js" charset="UTF-8"></script>
<script type="text/javascript" >
$(function(){
	selectValue("select[name='sex']","${salesman.sex}");
	selectValue("select[name='area_city_id']","${salesman.area_city_id}");
	
});
var errorCodes = ["-100","-110","-120","-130"];
var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(10MB)","零字节的文件","无效的文件类型"];
var fileSystemName = "";	
var fileTypeExts = "*.jpg;*.png;*.gif;*.jpeg";
var flashPath = "/uploadify/uploadify.swf" ;
var uploadifyPath = "http://upload.yuleing.com/salesmanImage/upload.do";
var uploadLimit = 0;
var queueSizeLimit = 5;
var fileSizeLimit = "2MB";
var salesman_id='';
//页面初始化方法
$(function(){
	salesman_id='${salesman.id}';
	$("input[data-upload]").uploadify({
        'swf':flashPath,
        'fileObjName':'files[]',
        'uploader':uploadifyPath,
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
        	var formData;
        	if(id=='#head'){
	    		formData ={"params":'{"upload_dir":"'+salesman_id+'","upload_name":"'+name+'","noinit":[{"name":"50_50","is_watermark":"false","keep":"false"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'};
        	}else{
	    		formData ={"params":'{"upload_dir":"'+salesman_id+'","upload_name":"'+name+'","init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'};
        	}
    		$(id).uploadify("settings","formData",formData);
        },
        'onUploadSuccess':function(file,data,response){
        	var temp=eval(data);
        	var image_path = "http://images.yuleing.com/salesman/"+salesman_id+"/150_150"+this.wrapper.attr("id")+".jpg"+"?t="+new Date().getTime();
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