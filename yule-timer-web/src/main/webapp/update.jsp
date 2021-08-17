<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"/>
<meta name="renderer" content="webkit">
<!-- IE=10;IE=9;IE=8;IE=7;IE=EDGE -->
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="dns-prefetch" href="//static.yuleing.com">
<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
<title>定时器任务</title>
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="http://static.yuleing.com/admin/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
	<div id="body-wrapper">
		<div id="main-content">
			<div class="content-box">
				<div class="content-box-header">
					<h3>定时器任务</h3>
					<a href="javascript:;" class="goback-btn button">返回</a>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<form action="/updateTimerJob.do" method="post">
							<fieldset>
								<input type="hidden" name="id" value="${timerJob.id }" />
								<!-- 
								<p>
									<label>执行类路径</label>
									<input class="text-input small-input" type="text" name="className" value="${timerJob.className }" />
								</p>
								 -->
								<p>
									<label>表达式</label> 
									<input class="text-input medium-input datepicker" type="text" name="cronExpression" value="${timerJob.cronExpression }" /> 
								</p>
								<p>
									<label>状态</label> 
									<select name="status" class="small-input">
										<option value="0">启用</option>
										<option value="1">关闭</option>
									</select>
								</p>
								<p>
									<input class="button" type="submit" value="更新" />
								</p>
							</fieldset>
							<div class="clear"></div>
						</form>
					</div>
				</div>
			</div>
			<!-- End Notifications -->
			<div id="footer"><small>
					&#169; Copyright 2010 Your Company | Powered by <a href="javascript:;">admin templates</a> | <a href="javascript:;">Top</a>
				</small>
			</div>
		</div>
	</div>
<script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
<script type="text/javascript" src="/yule.jquery.config.js"></script>
<script type="text/javascript" src="http://static.yuleing.com/js/select-util.js"></script>
<script type="text/javascript">
	$(function(){
		selectValue("select[name='status']",'${timerJob.status}');
	});
</script>
</body>
</html>