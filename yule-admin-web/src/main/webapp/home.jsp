<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
<div id="body-wrapper">
	<div id="main-content">
	    		<!-- 
                <div class="content-box-header">
                    <h3>欢迎您使用预乐网E-BOOK管理系统</h3>
                    <div class="clear"></div>
                </div>
                 -->
                <div class="content-box">
	                 <div class="content-box-header">
	                    <h3>系统通知</h3>
	                </div>
	                <div class="content-main-content">
	                	<table>
							<thead>
								<tr>
									<th style="width:50%">标题</th> 
									<th style="width:50%">创建时间</th>
								</tr>
							</thead>
	                         ${htmls }
	                    </table> 
	                </div>
                </div>
                <div class="content-box">
	                 <div class="content-box-header">
	                    <h3>系统未读消息</h3>
	                </div>
	                <div class="content-main-content">
	                    <table>
							<thead>
								<tr>
									<th style="width:50%">标题</th> 
									<th style="width:50%">创建时间</th>
								</tr>
							</thead>
	                          ${adminMessageHtmls }
	                    </table> 
	                </div>
                </div>
         </div>
 </div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/admin/js/yule.jquery.config.js"></script>
</body>
</html>