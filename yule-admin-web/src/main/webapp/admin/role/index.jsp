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
					<h3>角色管理</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab">
						<form action="/adminRole/batchUpdateAdminRole.do" method="post" validform="">
							<div>
								${operatorHtml}
							</div>
							<table>
								<thead>
									<tr>
										<th width="40%">角色名称</th>
										<th width="30%">操作</th>
									</tr>
								</thead>
								<tbody id="list">
									${htmls}
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.ajaxsubmit.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.validform.js" charset="UTF-8"></script>
<script type="text/javascript" >
$(function(){
	trRow.privilegeHtml = '${privilegeHtml}';
	trRow.html = '${rowHtml}';
});
</script>
</body>
</html>