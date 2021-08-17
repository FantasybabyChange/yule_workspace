<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>合作伙伴登录</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<img src="http://images.yuleing.com/logo/yuleing.png" width="200px" style="margin:0 auto;"  alt="预乐进行时"><br/>
									<span class="red" id="id-text2">合作伙伴系统</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; 西安预乐网络科技有限公司</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												登录
											</h4>
											<h4 class="header red lighter bigger" style="display:none">
												<i class="ace-icon glyphicon glyphicon-remove red"></i>
												<span error-message=""></span>
											</h4>
											<div class="space-6"></div>
											<form action="/login.do" method="post"  ajaxvalidform="">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="account" class="form-control" placeholder="用户名" nullmsg="请输入用户名!" datatype="" errormsg="" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" nullmsg="请输入密码!" datatype="" errormsg="" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="captcha" class="col-xs-6" placeholder="验证码" nullmsg="请输入验证码!" datatype="" errormsg="" />
															<img alt="验证码" title="验证码"  src="" style="cursor: pointer;" data-src="/captcha.jpg?sessionid=<%=request.getSession().getId()%>" />
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<!-- 
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> Remember Me</span>
														</label>
 														-->
														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
											<div class="social-or-login center">
												<span class="bigger-110">建议使用IE9以上或chrome,firefox浏览器访问</span>
											</div>
											<!-- 
											<div class="space-6"></div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="ace-icon fa fa-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="ace-icon fa fa-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="ace-icon fa fa-google-plus"></i>
												</a>
											</div>
											 -->
										</div><!-- /.widget-main -->
										<div class="toolbar clearfix">
												
											<div>
												<!-- <a href="javascript:;" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													忘记密码!
												</a>
												 -->
											</div>
											<div>
												<a href="javascript:;" data-target="#signup-box" class="user-signup-link">
													申请合作!
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												找回密码
											</h4>
											<h4 class="header red lighter bigger" style="display:none">
												<i class="ace-icon glyphicon glyphicon-remove red"></i>
												<span error-message=""></span>
											</h4>
											<h4 class="header green lighter bigger" style="display:none">
												<span right-message=""></span>
											</h4>
												<div>
														<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="account" class="form-control"  datatype="" nullmsg="请输入用户名!"  placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<div id="newPassWordDiv"style="display:none;">
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="captcha" class="form-control" placeholder="验证码" nullmsg="请输入验证码!" dataReveive="" errormsg="" />
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="新密码" nullmsg="请输入新密码!" dataReveive="" errormsg="" />
														</span>
													</label>
													</div>
											</div>
											<div class="clearfix">
												<button type="button" button-type="sendMail" data-type="/sendMail.do" name="sendButton" class="width-35 pull-left btn btn-sm btn-danger">
													<i class="ace-icon fa fa-envelope-o"></i>
													<span class="bigger-110">发送邮件</span>
												</button>&nbsp;&nbsp;
												<button type="button" button-type="sendPhone" data-type="/sendPhone.do"  name="sendButton" class="width-35 pull-right btn btn-sm btn-danger">
													<i class="ace-icon glyphicon glyphicon-envelope"></i>
													<span class="bigger-110">发送短信</span>
												</button>
												<button type="button" button-type="surePass" style="display:none;"data-type="/backPassword.do"  name="surePass" class="width-35 pull-right btn btn-sm btn-danger">
													<i class="ace-icon glyphicon glyphicon-ok"></i>
													<span class="bigger-110">修改密码</span>
												</button>
											</div>
										</div><!-- /.widget-main -->

										<div class="toolbar center" id="changeToRPass">
											<a href="javascript:;" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

								
									<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												申请合作
											</h4>
											<div class="space-6"></div>
											<p> yuleing.com是国内顶级的网上娱乐场所预订公司，目前拥有超过n家直接签署协议的企业，并提供最优惠的价格。
												yuleing.com合作项目拥有强大的盈利能力，并保持了极佳的业务记录。是娱乐行业中的首选经营模式，能够保障与企业发展长期互惠的业务关系。 </p>
											<form ajaxSubmit="" >
													<label class="block clearfix">
															<input type=text class="form-control" name="account" placeholder="账号名称" />
													</label>
													
													<label class="block clearfix">
															<input type="text" class="form-control" name="phone" placeholder="联系电话" />
													</label>
													
													<label class="block clearfix">
															<input type="text" class="form-control" name="mail" placeholder="个人邮箱" />
													</label>

													<label class="block clearfix">
															<input type="text" class="form-control" name="address" placeholder="地址" />
													</label>
													<label class="block clearfix">
															<textarea class="form-control" name="details" placeholder="备注" ></textarea>
													</label>

													<label class="block">
														<input type="checkbox" id="isRead"class="ace" />
														<span class="lbl">
															我明白并同意这项
															<a href="javascript:;">运营协议</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>
														<button type="button" id="submitButton" class="width-65 pull-right btn btn-sm btn-success">
															<span  class="bigger-110">提交</span>
															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
														
													</div>
											</form>
										</div>

										<div class="toolbar center">
											<a href="javascript:;" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回登录
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->
							<!-- 
							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="javascript:;">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="javascript:;">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="javascript:;">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
							-->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script src="http://static.yuleing.com/assets/js/jquery-2.1.0.min.js"></script>
		<!-- <![endif]-->
		
		<!-- page specific plugin scripts -->
		
		<script src="http://static.yuleing.com/assets/js/bootstrap.min.js"></script>
		<script src="http://static.yuleing.com/assets/js/bootbox.min.js"></script>
		<script src="http://static.yuleing.com/salesman/js/yule.jquery.captcha.js"></script>
		
		<!--[if IE]>
		<script src="http://static.yuleing.com/assets/js/jquery-1.11.0.min.js"></script>
		<![endif]-->
		
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		
		var errorMessage = '${errorMessage}';
		jQuery(function($) {
			if(errorMessage!=''){
				var errorJson = jQuery.parseJSON(errorMessage);
				if(errorJson != null && errorJson.status === false){
					var error = $("#login-box").find("span[error-message]");
					error.html(errorJson.errorMessage);
					error.parent().show();				
					}
			}
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
			//用户合作
			$('#submitButton').on('click', function() {
				var isChecked = $("#isRead").prop("checked"); 
				 if (!isChecked) {
					 $("#isRead").next().css("background-color","red");
					 return;
				 }
				var form = $("form[ajaxSubmit]"); 
				var jsonData = "";
				var tests = form.find("input:text");
				tests.each(function(i){
					var name = $(this).attr("name");
					if(name!=""&&name!=null&&name!=undefined){
						jsonData += '"'+name+'":"'+$(this).val()+'",';
					}
				});
				var textarea = form.find("textarea");
				textarea.each(function(i){
					var name = $(this).attr("name");
					if(name!=""&&name!=null&&name!=undefined){
						jsonData += '"'+name+'":"'+$(this).val()+'",';
					}
				});
				jsonData = "{"+jsonData.substring(0,jsonData.length-1)+"}";
				$.ajax({
					url:'/salesmanCooperator/insertSalesmanCooperator.do',
					type:"post",
					dataType:"json",
					data:jQuery.parseJSON(jsonData),
					async:false,
					beforeSend:function(xhr){
					},
					success:function(data){
						if(data.flag){
							bootbox.alert("企业信息已提交", function() {
				            });
						}
					},
					complete :function(){
						$("form[ajaxSubmit]")[0].reset();
					}
				});
			});
			$("form[ajaxvalidform]").submit(function(){
				var flag = true;
				var form = $(this);
				var erroeMessage = $("#login-box").find("span[error-message]");
				form.find("input[datatype]").each(function(){
					var val = $.trim($(this).val());
					if(val===""||val===undefined||val===null){
						flag = false;
						erroeMessage.html($(this).attr("nullmsg"));
						erroeMessage.parent().show();
						$(this).focus();
						return false;
					}
				});
				if(flag){
					$.ajax({
						url:'/verifyLogin.do',
						type:'post',
						data:{'account':form.find("input[name='account']").val(),'password':form.find("input[name='password']").val(),
							'code':form.find("input[name='code']").val(),'captcha':form.find("input[name='captcha']").val()},
						dataType:'json',
						async:false,
						success:function(data){
							if(data!=null&&!data.status){
								flag = false;
								erroeMessage.html(data.message);
								erroeMessage.parent().show();
								$("img[data-src]").click();
							}
						}
					});
				}
				return flag;
			});
			
			var companyId = '';
			//发送信息或者邮件
			$('button[name="sendButton"]').click(function(){
				var flag = true;
				var forgot = $('#forgot-box');
				var erroeMessage = forgot.find("span[error-message]");
				forgot.find("input[datatype]").each(function(){
					var val = $.trim($(this).val());
					if(val===""||val===undefined||val===null){
						flag = false;
						erroeMessage.html($(this).attr("nullmsg"));
						erroeMessage.parent().show();
						$(this).focus();
						return flag;
					}
				});
				if(flag){
					var loading = $('<img src="/loading1.gif"></img>');
					var succesMessage = forgot.find('span[right-message]')
					$.ajax({
						url:$(this).attr('data-type'),
						type:'post',
						data:{'account':forgot.find("input[name='account']").val()},
						dataType:'json',
						async:false,
						beforeSend :function(){
							forgot.append(loading);
						},
						success:function(data){
							if(data!=null&&!data.status){
								succesMessage.html('');
								succesMessage.parent().hide();
								flag = false;
								erroeMessage.html(data.message);
								erroeMessage.parent().show();
								$('#newPassWordDiv').hide();
							}else{
								companyId = data.id;
								$('button[name="sendButton"]').hide();
								$('button[name="surePass"]').show();
								erroeMessage.html('');
								erroeMessage.parent().hide();
								succesMessage.html('邮件发送成功 请注意查收 然后输入验证码来修改密码!');
								succesMessage.parent().show();
								$('#newPassWordDiv').show();
							}
						},
						complete :function() {
							loading.remove();
						}
					});
				}
			});
			$('button[name="surePass"]').click(function(){
				var flag = true;
				var erroeMessage = $('#forgot-box').find("span[error-message]");
				$('#forgot-box').find("input[dataReveive]").each(function(){
					var val = $.trim($(this).val());
					if(val===""||val===undefined||val===null){
						flag = false;
						erroeMessage.html($(this).attr("nullmsg"));
						erroeMessage.parent().show();
						$(this).focus();
						return flag;
					}
				});
				if(flag){
						$.ajax({
						url:$(this).attr('data-type'),
						type:'post',
						data:{'captcha':$('#forgot-box').find("input[name='captcha']").val(),'password':$('#forgot-box').find("input[name='password']").val(),'id':companyId},
						dataType:'json',
						async:false,
						beforeSend:function(){
							$('span[right-message]').html('<img src="/loading1.gif"></img>');
							$('span[right-message]').parent().show();
						},
						success:function(data){
							if(data != null && data.status === true){
								bootbox.alert('密码修改成功 3秒后跳转到登录界面');
								setTimeout(function(){
									window.location.reload();	
								},3000);		
							}else{
								if(data.message){
									erroeMessage.html(data.message);	
									erroeMessage.parent().show();
								}else{
									erroeMessage.html('修改密码失败');
									erroeMessage.parent().show();
								}
								
							}
						
						}
					});
				}
			});
		});
		</script>
	</body>
</html>