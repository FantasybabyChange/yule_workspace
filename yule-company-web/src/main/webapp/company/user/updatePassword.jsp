<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>更新密码</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		
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
				<%@ include file="/common/menu.jsp" %>
			</div>

			<div class="main-content">
				
				<div class="page-content">
					<div class="row">
						<div class="modal-content" style="width:100%;" id="changePassdiv">
							<div style="text-align: center"  class="modal-body step-content" id="modal-step-contents">
							<div>
							<label>原密码:</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="password" name="backPassword" id="oldPassword"  placeholder="请输入原密码"  datatype='*6-16' nullmsg="请输入原密码" errormsg=""/></br>
							<br>
							</div>
							<div>
							<label>新密码:</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="password" name="password" id="password"  placeholder="请输入新密码"  datatype="*6-16" nullmsg="请输入新密码" errormsg=""/>
							<br>
							</div>
							<br>
							<button  class="btn btn-sm btn-success" style="margin-left:7%;" data-type="changePass" id="changePass">
								修改密码
							</button>
							<button  class="btn btn-sm btn-success" style="display:none;" data-dismiss="modal" date-type='submit-success'>
							</button>
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
		
		<%@ include file="/common/scripts.jsp" %>

		<!-- page specific plugin scripts -->
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxsubmit.js"></script>
		<script type="text/javascript" src="http://static.yuleing.com/company/js/yule.jquery.ajaxform.js"></script>	
		<script src="http://static.yuleing.com/company/js/yule.jquery.validform.js"></script>	
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$("#changePass").on('click',function(){
			var passParent = $(this).parent();
			passParent.parent().find(".help-block").remove();
			passParent.parent().find(".ace-icon").remove();
			if(validator.valid($("#changePassdiv"))){
				$.ajax({
					url:"/companyUser/updateCompanyUserPassword.do",
					type:"post",
					dataType:"json",
					data:{'password':$('#password').val(),'oldPassword':$('#oldPassword').val()},
					async:false,
					success:function(data){
						if(data.status){
							alert('修改密码成功');
							$('button[date-type="submit-success"]').click();
						}else{
							passParent.append("<i class=\"red ace-icon fa fa-times-circle\"></i> <div class=\"red help-block col-xs-12 col-sm-reset inline\">原密码不正确 </div>");
							
						}
						}
				});
			}else{
			}

		});
		</script>
	</body>
</html>
