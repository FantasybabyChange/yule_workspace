<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>权限基本信息</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
					    <form action="/privilege/updateAdminPrivilege.do?parentId=${parentIds }" method="post" validform="">
							<fieldset><input type="hidden" name="id" value="${htmls.id }"  />
								<p>
								  	<label>权限名称</label>								
								    <input class="text-input small-input" type="text" id="small-input" name="name" value="${htmls.name }" nullmsg="请输入权限名称!" datatype="" errormsg=""/><br />
								</p>
								<p>
									<label>URL</label>
									<input class="text-input small-input" type="text" id="small-input" name="url" value="${htmls.url }" nullmsg="请输入URL!" datatype="" errormsg=""/><br />
								</p>
								<p>
									<label>CODE</label>
									<input class="text-input small-input" type="text" id="small-input" name="code" value="${htmls.code }" /><br />
								</p>		
								<p>
									<label>请选择状态</label>
									 ${statusHtml }	
								</p>
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
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript">
	$(function (){
		selectValue("#status",${htmls.status});
	});
</script>
</body>
</html>