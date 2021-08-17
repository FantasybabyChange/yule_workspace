<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>生成静态HTML管理</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" >
						<form action="http://www.yuleing.com/html/createIndexHtml.do" method="post" target="pc">
							<fieldset>
								<input type="hidden" name="flag" value="yuleing" />
								<input type="submit" value="生成静态首页"/>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<iframe src="about:blank" name="pc" id="pc" width="1" height="1" style="height:0px;width:1px;overflow:hidden;visibility:hidden;position:absolute;" frameborder="0" border="0"></iframe>
</body>
</html>