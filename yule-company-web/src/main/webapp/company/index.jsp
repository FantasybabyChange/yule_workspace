<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>企业信息</title>
		
		<link rel="dns-prefetch" href="//static.yuleing.com">
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		
		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/select2.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/assets/css/bootstrap-editable.css" />
		<link rel="stylesheet" href="http://static.yuleing.com/css/uploadify.css" type="text/css" media="screen" />
	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default navbar-collapse h-navbar">
			<%@ include file="/common/header.jsp" %>
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
				<%@ include file="/common/menu.jsp" %>
			</div>
			
			<div class="main-content">	
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="alert alert-block alert-success">
								<button type="button" class="close" data-dismiss="alert">
									<i class="ace-icon fa fa-times"></i>
								</button>

								<i class="ace-icon fa fa-check green"></i>
								点击图片改变图片,点击文字更新文字
							</div>
							<div class="hr dotted"></div>
							<div class="user-profile">
								<div class="tabbable">
									<ul class="nav nav-tabs padding-18">
										<li class="active">
											<a data-toggle="tab" href="#home">
												<i class="green ace-icon fa fa-user bigger-120"></i>
												基本信息
											</a>
										</li>

										<li>
											<a data-toggle="tab" href="#feed">
												<i class="orange ace-icon fa fa-rss bigger-120"></i>
												公司信息
											</a>
										</li>

									</ul>

									<div class="tab-content no-border padding-24">
										<div id="home" class="tab-pane in active" >
											<div class="row">

												<div class="col-xs-12 col-sm-12">
													<div class="profile-user-info" data-url="/company/updateCompany.do">
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业名称 </div>

															<div class="profile-info-value">
																<span   data-id="name">${company.name}</span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业头像</div>

															<div class="profile-info-value">
																<input id="company_face"  type="file"  data-upload="" />
																<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150company_face.jpg" id="company_face_image" style="width: 150;height: 150">
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 支付方式</div>

															<div class="profile-info-value">
																<span    >${company.pay_type_value}</span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 折扣</div>

															<div class="profile-info-value">
																<span  data-id="rebast">${company.rebast} </span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 提成</div>

															<div class="profile-info-value">
																<span  data-id="rebast">${company.commision} %</span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 营业时间</div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="text"  data-id="hours">${company.hours}</span>
															</div>
														</div>														
														<div class="profile-info-row">
															<div class="profile-info-name" data-editor="text" > 企业简介 </div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="textarea" data-id="details">${company.details}</span>
															</div>
														</div>

														<div class="profile-info-row">
															<div class="profile-info-name" id="warm_prompt"> 温馨提示 </div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="textarea"  data-id="warm_prompt">${company.warm_prompt}</span>
															</div>
														</div>

														<div class="profile-info-row">
															<div class="profile-info-name"> 周边介绍 </div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="textarea"  data-id="periphery">${company.periphery}</span>
															</div>
														</div>


													</div>

													<div class="hr hr-8 dotted"></div>
												</div><!-- /.col -->
											</div><!-- /.row -->
										</div><!-- /#home -->

										<div id="feed" class="tab-pane">
											<div class="row">
												<div class="col-xs-12 col-sm-12">

													<div class="profile-user-info" data-url="/companyEnterprise/updateCompanyEnterprise.do">
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业公司名 </div>

															<div class="profile-info-value">
																<span   data-id="name">${company.title}</span>
															</div>
														</div>
														
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业法人姓名</div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="text"  data-id="legal_person_name">${company.legal_person_name}</span>
															</div>
														</div>
														
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业法人照片</div>

															<div class="profile-info-value">
																	<input id="legal_person"  type="file"  data-upload="" />
																	<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150legal_person.jpg" id="legal_person_image" style="width: 150;height: 150">
															</div>
														</div>
														
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业经营许可证</div>

															<div class="profile-info-value">
																<span class="editable editable" data-editor="text"  data-id="business_license">${company.business_license}</span>
															</div>
														</div>
														<div class="profile-info-row">
															<div class="profile-info-name"> 企业经营许可证照</div>

															<div class="profile-info-value">
																<input id="business_license"  type="file"  data-upload="" />
																<img alt="" src="http://images.yuleing.com/company/${company.id }/150_150business_license.jpg" id="business_license_image" style="width: 150;height: 150">
															</div>
														</div>


													<div class="hr hr-8 dotted"></div>

												</div><!-- /.col -->
											</div><!-- /.row -->									
											</div>
										</div>
									</div>
									<!-- PAGE CONTENT ENDS -->
								</div><!-- /.col -->
							</div><!-- /.row -->
						</div><!-- /.page-content -->
					</div><!-- /.main-content -->
				</div>
			</div>
			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
		<%@ include file="/common/scripts.jsp" %>
	  	
		<!-- page specific plugin scripts -->
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>
		<script src="http://static.yuleing.com/assets/js/select2.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/x-editable/ace-editable.min.js"></script>
	  	<script language="javascript" type="text/javascript">
	  		document.write("<script type='text/javascript' "
				+ "src='http://static.yuleing.com/company/js/company-upload.js?" + new Date().getTime()
				+ "' charset='utf-8'></s" + "cript>");
			document.write("<script type='text/javascript' "
			+ "src='http://static.yuleing.com/js/jquery.uploadify.min.js?" + new Date().getTime()
			+ "'></s" + "cript>");
		</script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			$(function($) {
				company_id='${company.id}';
				$.fn.editable.defaults.mode = 'inline';
				$.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
			    $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
			                                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';
			   
			   var editables = $('body').find('.editable') ;
			   editables.each(function(){
				   $(this).editable({
						type: $(this).attr("data-editor"),
			    		name:$(this).attr("data-id"),
			    		emptytext:'编辑',
				   });
			   });
			    var companyValue;
			    $('.editable').on('save', function(e, params) {
			    	var newValue = params.newValue;
			    	if(companyValue == newValue) return;
			    	companyValue = newValue;
			    	$(this).editable('setValue',companyValue);
			    	var val = $(this).text().replace("编辑","");
			    	if(val!=""){
			    		if(validator.ajaxvalidword($(this).text())==true){
					   		 ajaxSubmit($(this));
			    		}
			    	}else{
			    		alert("输入值不能为空");
			    	}
			    });
			    
			    function ajaxSubmit(tar){
			    	var jsonData ="";
			    	jsonData= "{ \""+tar.attr("data-id")+"\":\""+tar.text()+"\"}";
			    	$.ajax({
						url:tar.parent().parent().parent().attr("data-url"),
						type:"post",
						dataType:"json",
						data:jQuery.parseJSON(jsonData),
						async:false,
						beforeSend:function(data){
							tar.text($.fn.editableform.loading);
						},
						success:function(data){
						},
						complete :function(data){
							
						}
					});
			    }
				function commonSubmit(){
					 $('.editable').editable('submit', {  
				        url: '/company/updateCompany.do', 
				        ajaxOptions: {
				        	type:'get',
				            dataType: 'json' //assuming json response
				        },
				        success: function(data) {
						}
					});
				}
			});
		</script>
	</body>
</html>
