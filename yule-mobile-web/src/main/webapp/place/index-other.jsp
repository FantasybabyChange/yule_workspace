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
		<link rel="stylesheet" href="/css/mui.css"  />	
		<link rel="stylesheet" href="/css/yuleing.css" />
		<link rel="stylesheet" href="/css/mobiscrol.css" />
	</head>

	<body>
		<div class="mui-off-canvas-wrap mui-draggable">
			<aside id="offCanvas" class="mui-off-canvas-right">
				<%@ include file="/common/header-menu.jsp" %>
			</aside>
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav">
					<%@ include file="/common/header-nav.jsp" %>
				</header>
				<div id="mui-popover" class="mui-popover mui-search-popover">
					<%@ include file="/common/header-search.jsp" %>
				</div>
				<div class="mui-content">
					<div class="mui-p-city">
					<h5>以 ${initial} 开头的城市</h5>
						<div class="box">
							<ul class="table">
							${html }
							</ul>
						</div>
						
					<footer>
						<%@ include file="/common/footer.jsp" %>
					</footer>
				</div>
				<!-- off-canvas backdrop -->
				<div class="mui-off-canvas-backdrop"></div>
			</div>
		</div>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<script type="text/javascript" src="/js/mobiscroll.js"></script>
		<script src="/js/mui.min.js"></script>
	    <!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script> -->
		<script type="text/javascript" charset="utf-8">
			mui.init();
			$(function() {
				$("body").on("click","a[data-area]",function(){
					$.ajax({
		        		url : "/updateArea.do",
		        		data:{"areaId":$(this).attr("data-area")},
		        		type : "post",
		        		dataType : "html",
		        		success : function(data){
		        			location.href="/index.do";
		        		}
		        	});
				});
			});
		</script>
	</body>
</html>