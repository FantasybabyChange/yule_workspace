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
					<h3>权限管理</h3>
					${backHtml }
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" >
						<div>
							${companyCategoryHtmls }
						</div>
						<br/>
						<hr/>
						<br/>
						<div>
							<form action="/companyPrivilege/insertCompanyPrivilege.do" method="post" validform="">
									${insertHtmls }
							</form>
						</div>
							<table>
									<thead>
										<tr>
											<th >权限名称</th>
											<th >权限地址</th>
											<th >是否展示</th>
											<th >序号</th>
											<th>操作</th>
										</tr>
									</thead>
									${htmls}
							</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/collapse.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js" ></script>
<script type="text/javascript">
$(function(){
	trRow.html = '${rowHtmls}';
	var gradeNo = '${gradeNo}';
	if(gradeNo === 2 || gradeNo === '2'){
		selectValue('#is_show',1);
		$('#is_show').attr('disabled',true);
	}
});
</script>
</body>
</html>
