<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="aplus-terminal" content="1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no,minimal-ui" />
		<meta name="keywords" content="夜店预订,特价夜店,夜店查询,夜总会预订,特价夜总会,夜总会查询查询,酒吧预订,特价酒吧,酒吧查询,娱乐会所预订,特价娱乐会所,娱乐会所查询,演艺会所预订,特价演艺会所,演艺会所查询" />
		<meta name="description" content="预乐进行时是中国领先的O2O娱乐休闲预订公司,夜店点评及特价夜店查询,娱乐会所预订,酒吧预订,演艺会所预订,为您提供全方位娱乐休闲场所预订咨询服务"/>
		<link rel="dns-prefetch" href="//static.yuleing.com">
		<link rel="dns-prefetch" href="//images.yuleing.com">
		<link rel="dns-prefetch" href="//p.yuleing.com">
		<title>预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
		<meta name="apple-mobile-web-app-capable" content="yes"/>
		<meta name="apple-touch-fullscreen" content="no"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
		<meta name="format-detection" content="telephone=no"/>
		<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
		<meta name="aplus-terminal" content="1">
		<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="http://images.yuleing.com/logo/logo.png">
		<link href="/css/mui.css" rel="stylesheet" />
		<link rel="stylesheet" href="/css/yuleing.css" />
		<link rel="stylesheet" href="/css/mobiscrol.css" />
	</head>

		<body>
			<div class="mui-map-wapper">
			<div class="mui-map-nav">
				<a href="javascript:history.go(-1)">
					<span class="mui-icon mui-icon-back mui-pull-left"></span>
					<span id="infoSpan">
					
					</span>
				</a>
			</div>
			<div class="mui-map-content" style="margin-top:15%;height:700px;width:100%;" id="l-map">
			</div>
		</div>	
	</body>
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script>
	<script type="text/javascript" src="/detailsBaiduMap.js"></script> 
<script>
$(function() {
	markerObject.company_id = '${company_id}';
	markerObject.initMapMarker('${cityId}','${cityName}');	
	
});
</script>
</html>