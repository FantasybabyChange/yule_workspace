<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>企业二维码</title>

		<meta name="description" content="Drag &amp; drop file upload with image preview" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>

	</head>

	<body>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="page-content">

				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<div class="alert alert-info">
						<i class="ace-icon fa fa-hand-o-right"></i>
						请打开微信扫一扫,扫描二维码
						<button class="close" data-dismiss="alert">
							<i class="ace-icon fa fa-times"></i>
						</button>
					</div>
					<div>
						<img src="" />
						<div id="qrcodeCanvas"></div>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-container -->

		<%@ include file="/common/scripts.jsp" %>
		
		<script src="/jquery.qrcode.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery('#qrcodeCanvas').qrcode({
			    text    : "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6d627fb5daa5bad1&redirect_uri=http://weixin.yuleing.com/companyOAuth.do&response_type=code&scope=snsapi_userinfo&state=${id}#wechat_redirect"
			}); 
		</script>
	</body>
</html>
