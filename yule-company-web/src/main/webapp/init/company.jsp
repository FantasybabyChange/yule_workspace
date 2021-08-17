<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>企业基本信息</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		<link rel="stylesheet" href="http://static.yuleing.com/css/uploadify.css" type="text/css" media="screen" />
		
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default">
			<%@ include file="/common/header.jsp" %>
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
			</div>

			<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="hr hr-18 hr-double dotted"></div>

								<div class="widget-box">
									<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="widget-title lighter">企业激活必填信息</h4>
									</div>

									<div class="widget-body">
										<div class="widget-main">
											<!-- #section:plugins/fuelux.wizard -->
											<div id="fuelux-wizard" data-target="#step-container">
												<!-- #section:plugins/fuelux.wizard.steps -->
												<ul class="wizard-steps">
													<li data-target="#step1" class="active">
														<span class="step">1</span>
														<span class="title">企业基本信息</span>
													</li>
													<li data-target="#step2">
														<span class="step">2</span>
														<span class="title">地理信息</span>
													</li>
													<li data-target="#step3">
														<span class="step">3</span>
														<span class="title">激活成功</span>
													</li>
													
												</ul>

												<!-- /section:plugins/fuelux.wizard.steps -->
											</div>

											<hr>

											<!-- #section:plugins/fuelux.wizard.container -->
											<div class="step-content pos-rel" id="step-container">
												<div class="step-pane active" id="step1">
												<div class="row ">
													<div class="col-xs-12">
														<div class="profile-user-info profile-user-info-striped">
														<form action="/init/updateCompany.do"  method="post" ajaxvalidform="" data-url="/init/verifyCompany.do">
															<input name="id"  type="hidden" value="${company_id }" />
															<div class="profile-info-row">
																<div class="profile-info-name"> 企业头像 </div>
																<div class="profile-info-value center">
																	<div class="profile-info-value">
																		<input id="company_face"  type="file"  data-upload="" />
																		<img onerror="this.src=''"   src="http://images.yuleing.com/company/${company_id }/150_150company_face.jpg" id="company_face_image" style="width: 150;height: 150"  >
																	</div>
																</div>
															</div>
															<!-- 
															<div class="profile-info-row">
																<div class="profile-info-name"> 邮箱 </div>
																<div class="profile-info-value">
																	<input name="mail" style="width: 600px"  type="text" value="" nullmsg="请输入邮箱!" datatype="e" errormsg=""  ajaxdata=""/>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 手机 </div>
																<div class="profile-info-value">
																	<input name="phone" style="width: 600px" style="width: 600px" ajaxdata=""  value="" type="text"  datatype="p" errormsg="" nullmsg="请输入手机号!"/>
																</div>
															</div>
															 -->
															<div class="profile-info-row">
																<div class="profile-info-name"> 营业时间 </div>
																<div class="profile-info-value">
																<input  name="hours" style="width: 600px" value="" validword="" type="text" nullmsg="请输入营业时间 !" datatype="" />
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 企业简介 </div>
																<div class="profile-info-value">
																<textarea maxlength="500" style="width: 600px ;height: 130px;"  validword="" name="details" nullmsg="请输入企业简介!" datatype="" id="form-field-9" class="form-control limited" style="width: 284px; height: 100px;"></textarea>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 温馨提示 </div>
																<div class="profile-info-value">
																<textarea maxlength="500" style="width: 600px ;height: 130px;" validword="" name="warm_prompt" nullmsg="请输入温馨提示!" datatype="" id="form-field-9" class="form-control limited" style="width: 284px; height: 100px;"></textarea>
																</div>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 周边介绍 </div>
																<div class="profile-info-value">
																<textarea maxlength="500"  style="width: 600px ;height: 130px;" validword="" name="periphery"  nullmsg="请输入周边信息!" datatype="" id="form-field-9" class="form-control limited" style="width: 284px; height: 100px;"></textarea>
																</div>
															</div>
															</form>
														</div>										
													</div>
												</div>
												</div>
											</div>

											<!-- /section:plugins/fuelux.wizard.container -->
											<div class="wizard-actions">
												<!-- #section:plugins/fuelux.wizard.buttons -->
												<button  class="btn btn-success btn-next"  >
													下一步
													
												<i class="ace-icon fa fa-arrow-right icon-on-right"></i></button>

												<!-- /section:plugins/fuelux.wizard.buttons -->
											</div>

											<!-- /section:plugins/fuelux.wizard -->
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div>

							</div>
					</div>
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="http://static.yuleing.com/assets/js/jquery-2.1.0.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script src="http://static.yuleing.com/assets/js/jquery-1.11.0.min.js"></script>
		<![endif]-->

		<script src="http://static.yuleing.com/assets/js/bootstrap.min.js"></script>

		<!-- yule scripts -->
		<!--[if lte IE 8]>
		  <script src="http://static.yuleing.com/assets/js/excanvas.min.js"></script>
		<![endif]-->
		
		<!-- yule scripts -->
		<script src="http://static.yuleing.com/company/js/yule.jquery.ajaxform.js"></script>
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>
		
		<!-- ace scripts -->
		<script src="http://static.yuleing.com/assets/js/bootbox.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/select2.min.js"></script>
	  	<script language="javascript" type="text/javascript">
			document.write("<script type='text/javascript' "
			+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date().getTime()
			+ "'></s" + "cript>");
		</script>
		<script type="text/javascript">
		$(function(){
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
			company_id='${company_id}';
			$("input[data-upload]").uploadify({
		        'swf':"/swf/uploadify.swf",
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
		    		var	formData ={"params":'{"upload_dir":"'+company_id+'","upload_name":"'+name+'","noinit":[{"name":"50_50","is_watermark":"false","keep":"false"},{"name":"100_100","is_watermark":"false","keep":"false"}],"init":[{"name":"150_150","is_watermark":"false","keep":"true"}]}'}
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

			$(".btn-next").click(function(){
				var img =$("#company_face_image");
				if(img.attr("src").length>10){
					var form = $("form[ajaxvalidform]");
					form.submit();
				}else{
					alert("请上传企业图片，企业图片会加大您企业的浏览量");
				}
			});
		});
		</script>
	</body>
</html>
