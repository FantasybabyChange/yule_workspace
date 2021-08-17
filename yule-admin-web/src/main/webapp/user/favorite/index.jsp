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
					<h3>用户收藏</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<div>
							<form action="/userCollections/findUserCollertions.do" method="get" data-query-form="">
								<input type="hidden" name="userId" value="" />
								<input type="hidden" name="companyId" value="" />
							</form>
						</div>
					    <table>
						 	<thead>
						 		 <tr>
					 			   <th>名称</th>
					 			   <th>创建时间</th>
					 			</tr>
					 		</thead>
					 		 ${htmls }
					    </table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js" ></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.query.page.config.js" ></script>
</body>
</html>