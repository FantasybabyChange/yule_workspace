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
<link rel="stylesheet" href="http://static.yuleing.com/www/css//select2.css" type="text/css" media="screen" />
</head>
<body>
<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
				<a class="goback-btn button" href="/company/findCompany.do">返回</a>
					<ul class="content-box-tabs">
						<li><a href="#tab4" >公司类别</a></li>
						<li><a href="#tab3" >基本信息</a></li>
						<li><a href="#tab1" class="default-tab">企业信息</a></li>
						<li><a href="#tab2">公司信息</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content" id="tab4" style="display: none;">
						<form action="/company/updateCompanyCategory.do"  method="post" validform="">
							<fieldset>
								<input type="hidden" name="id" value="${company.id}" />
								<p>
									<label>企业类别</label> 
									${categoryHtml}
								</p>
								<p>
									<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
						</form>
					</div>
					<div class="tab-content" id="tab3" style="display: none;">
						<form action="/company/updateCompanyInfo.do"  method="post" validform="">
							<fieldset>
								<input type="hidden" name="id" value="${company.id}" />
								<p>
									<label>企业名称</label> 
									<input class="text-input small-input" type="text" name="name" value="${company.name }" nullmsg="请输入用户名!" datatype="" errormsg="" />
								</p>
								<p>
									<label>企业档次</label> 
									${gradeHtml}
								</p>
								<p>
							       <label>托管类型</label> 
									<select name="cooperatory_type" id="cooperator_type">
									<option value="0">企业托管</option>
									<option value="1">合作伙伴托管</option>
									</select>
								</p>
								<p style="display:none;" id="salesmanP">
							       <label>合作伙伴</label> 
									<select id="salesman" style="width:120px;" name="salesman_id" >
									<option value="">暂无销售人员</option>
									</select>
								</p>
								<p>
							       <label>企业支付方式</label> 
									<select name="pay_type" id="pay_type">
									<option value="0">线下支付</option>
									<option value="1">在线支付</option>
									</select>
								</p>
								<p>
									<label>提成</label> 
									<input class="text-input" type="text" name="commision" value="${company.commision}" nullmsg="请输入提成!" datatype="n" errormsg="请输入数字!" /> 
								</p>
								<p>
									<label>折扣</label> 
									<input class="text-input" type="text" name="rebast" value="${company.rebast }" nullmsg="请输入折扣!" datatype="n" errormsg="请输入数字!" /> 
								</p>
								<p>
									<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
						</form>
					</div>
					<div class="tab-content default-tab" id="tab1" style="display: none;">
						<form action="/company/updateCompany.do"  method="post" validform="">
							<fieldset>
								<input type="hidden" name="id" value="${company.id}" />
								<p>
									<label>企业头像</label> 
								</p>
					            <p>
										<input id="company_face"  type="file"  data-upload="" />
										<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150company_face.jpg" id="company_face_image" style="width: 150;height: 150">
								</p>	
								<p>
									<label>营业时间</label> 
									<input class="text-input medium-input datepicker" type="text" name="hours" value="${company.hours }" nullmsg="请输入营业时间!" datatype="" errormsg="" /> 
								</p>
								<p>
					              <label>商家介绍</label>
					              <textarea class="text-input textarea wysiwyg" name="details" cols="79" rows="15" nullmsg="请输入商家介绍!" datatype="" errormsg="">${company.details }</textarea>
					            </p>
					            <p>
					              <label>温馨提示</label>
					              <textarea class="text-input textarea wysiwyg" name="warm_prompt" cols="79" rows="15" nullmsg="请输入周边介绍!" datatype="" errormsg="">${company.warm_prompt }</textarea>
					            </p>		
					            <p>
					              <label>周边介绍</label>
					              <textarea class="text-input textarea wysiwyg" name="periphery" cols="79" rows="15" nullmsg="请输入周边介绍!" datatype="" errormsg="">${company.periphery }</textarea>
					            </p>
					            <p>
									<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
							<div class="clear"></div>
						</form>
					</div>
					<div class="tab-content" id="tab2" style="display: none;">
					
						<form action="/companyEnterprise/updateCompanyEnterprise.do" method="post" validform="">
							<fieldset>
								<input type="hidden" name="id" value="${company.id}" />
								<p>
									<label>企业公司名</label> 
									<input class="text-input small-input" type="text" id="name" name="name"  nullmsg="请输入企业公司名!" value="${company.title }" datatype="" errormsg="" /> <br />
								</p>
								<p>
									<label>法人姓名</label> 
									<input class="text-input small-input" type="text"  id="legal_person_name" name="legal_person_name"  value="${company.legal_person_name}" nullmsg="请输入法人姓名!" datatype="" errormsg="" /> <br />
								</p>
								<p>
									<label>法人头像</label>
								</p>
					            <p>
					            	<input id="legal_person"  type="file"  data-upload="" />
																	<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150legal_person.jpg" id="legal_person_image" style="width: 150;height: 150">
								</p>								
					            <p>
									<label>企业经营许可证号</label>
									<input class="text-input small-input" type="text" id="small-input" name="business_license"  value="${company.business_license }"  nullmsg="企业经营许可证号!" /> <br />
								</p>
					            <p>
									<label>企业经营许可证图片</label> 
								</p>
					            <p>
					            		<input id="business_license"  type="file"  data-upload="" />
																<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150business_license.jpg" id="business_license_image" style="width: 150;height: 150">
													
								</p>
								<p>
									<input class="button" type="submit" value="添加" />
								</p>
							</fieldset>
						</form>
						
					</div>
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
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js" ></script>
<script language="javascript" type="text/javascript">
	document.write("<script type='text/javascript' "
			+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date()
			+ "'></s" + "cript>");
</script>
<script type="text/javascript">
	$(function(){
		selectValue("#company_category_id","${company.company_category_id}");
		selectValue("#company_grade_id","${company.company_grade_id}");
		selectValue("#pay_type","${company.pay_type}");
		var cooperator_type = '${company.cooperatory_type}';
		var salesman = '${company.salesman_id}';
		selectValue("#cooperator_type",cooperator_type);
		if(cooperator_type === '1'){
			$('#salesmanP').show();
		}else{
			$('#salesmanP').hide();
		}
		$('#cooperator_type').change(function(){
			if($(this).val() === '1'){
				$('#salesmanP').show();
			}else{
				$('#salesmanP').hide();
			}
		});
		$.ajax({
			url:"/salesmanLogin/findSalesmanAjax.do",
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data != null){
					var salesmans = data.salesmans;
					if(salesmans){
					var optionHtml = '';
	    			$.each(salesmans,function(i){
	    				if(salesman != null && salesmans[i].id === salesman){
	    					optionHtml += "<option selected value='"+salesmans[i].id+"'>"+salesmans[i].name+"</option>";	
	    				}else{
	    					optionHtml += "<option value='"+salesmans[i].id+"'>"+salesmans[i].name+"</option>";
	    				}
	    				
					});
	    			$('#salesman').html(optionHtml);
	    			$('#salesman').select2();
					}
					
				}
				}
			});
		
		$(".wysiwyg").wysiwyg();
	});
	var errorCodes = ["-100","-110","-120","-130"];
	var errorMsgs = ["文件数量不能超过(5)","文件超过大小限制(10MB)","零字节的文件","无效的文件类型"];
	var fileTypeExts = "*.jpg;*.png;*.gif;*.jpeg";
	var uploadifyPath = "http://upload.yuleing.com/companyImage/upload.do";
	var uploadLimit = 0;
	var queueSizeLimit = 5;
	var fileSizeLimit = "2MB";
	var company_id='';
	var imageTitle = "点击查看原图"; 
	//页面初始化方法
	$(function(){
		company_id = '${company.id}';
		$("input[data-upload]").uploadify({
	        'swf':"/uploadify/uploadify.swf",
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
	    		var formData ={};
				if(name=='company_face'){
					formData ={"params":'{"upload_dir":"'+company_id+'","upload_name":"'+name+'","noinit":[{"name":"50_50","is_watermark":"false","keep":"false"},{"name":"100_100","is_watermark":"false","keep":"false"},{"name":"200_150","is_watermark":"false","keep":"false"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'}
				}else{
					formData ={"params":'{"upload_dir":"'+company_id+'","upload_name":"'+name+'","init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'}
				}
				$(id).uploadify("settings","formData",formData);
	        },
	        'onUploadSuccess':function(file,data,response){
	        	var temp=eval(data);
	        	var image_path = "http://images.yuleing.com/company/"+company_id+"/150_150"+this.wrapper.attr("id")+".jpg"+"?t="+new Date().getTime();
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