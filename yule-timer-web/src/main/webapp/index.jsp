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
<title>预乐网定时系统</title>
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
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">任务列表</a></li>
						<li><a href="#tab2">新增</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<table>
							<thead>
								<tr>
									<th>名称</th>
									<th>分组</th>
									<th>执行类路径</th>
									<th>表达式</th>
									<th>是否同步</th>
									<th>状态</th>
									<th>执行次数</th>
									<th>最后执行时间</th>
									<th>错误消息</th>
									<th>操作</th>
								</tr>
							</thead>
							${timerJobHtmls}
						</table>
					</div>
					<div class="tab-content" id="tab2" style="display:none">
						<form action="/insertTimerJob.do" method="post">
							<fieldset>
								<p>
									<label>名称</label>
									<input class="text-input small-input" type="text" name="name" />
								</p>
								<p>
									<label>分组</label> 
									<input class="text-input medium-input datepicker" type="text" name="group" /> 
								</p>
								<p>
									<label>执行类路径</label>
									<input class="text-input small-input" type="text" name="className" />
								</p>
								<p>
									<label>表达式</label> 
									<input class="text-input medium-input datepicker" type="text" name="cronExpression" /> 
								</p>
								<p>
									<label>状态</label> 
									<select name="status" class="small-input">
										<option value="0">启用</option>
										<option value="1">关闭</option>
									</select>
								</p>
								<p>
									<input class="button" type="submit" value="新增" />
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
</body>
</html>