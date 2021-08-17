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
		<link rel="stylesheet" href="css/mui.css"  />	
		<link rel="stylesheet" href="css/yuleing.css" />
		<link rel="stylesheet" href="css/mobiscrol.css" />
	</head>

	<body>
		<div class="mui-off-canvas-wrap mui-draggable">
			<aside id="offCanvas" class="mui-off-canvas-right">
				<%@ include file="/common/header-menu.jsp" %>
			</aside>
			<div class="mui-inner-wrap">
				<header class="mui-bar mui-bar-nav">
					<%@ include file="/common/header.jsp" %>
				</header>
				<div class="mui-content">
					<div class="mui-search-wapper mui-input-group">
						<h3>搜索</h3>
						<h5>夜总会,娱乐会所,演艺会所,酒吧,量贩KTV及更多…</h5>
						<form search-form="" action="/search/searchCompany.do" method="get">
						<div class="mui-input-row mui-mb10">
							<input id="destination" type="search" class="mui-input" name="name" />
							<span class="mui-icon mui-icon-clear mui-hidden"></span>
							<span class="mui-icon mui-icon-location"></span>
						</div>
						<div class="mui-input-row mui-mb10" >
							<div class="mui-date-wapper mui-pull-left">
								<h6>企业分类</h6>
								<select class="mui-people-select" name="company_category" id="companyCategory">
									<option value="">请选择</option>
									
								</select>
							</div>
							<div class="mui-date-wapper mui-pull-right">
								<h6>企业档次</h6>
									<select class="mui-people-select" name="company_grade" id="companyGrade">
									<option value="">请选择</option>
								</select>
							</div>
						</div>
						<div class="mui-btn-search-wapper">
<%-- 						<input type="hidden" id="search_id" name="area_city_id"value="${cityId }"/>
                        <input type="hidden" id="search_name" name="area_city_name"  value="${cityName }"/> --%>
                        <input type="hidden" id="latId" name="lat"  />
                        <input type="hidden" id="lngId" name="lng" />
						<button  type="submit" class="mui-btn mui-btn-search mui-btn-block" >搜 索</button>
						</div>
						</form>
					</div>
					<ul class="mui-index-place">
						正在加载.....
					</ul>
					<div class="mui-index-table">
						<div class="mui-table-view-cell ">
							<i class="icon-check mui-pull-left  icon-2x"></i>
							<div class="mui-media-body">
								低廉的价格
								<p class='mui-ellipsis'>不收预订费 • 省钱 • 线上最优惠价格</p>
							</div>
						</div>
						<div class="mui-table-view-cell ">
							<i class="icon-check mui-pull-left  icon-2x"></i>
							<div class="mui-media-body">
								优惠折扣
								<p class='mui-ellipsis'>尊享独有的商家优惠</p>
							</div>

						</div>
						<div class="mui-table-view-cell ">
							<i class="icon-check mui-pull-left  icon-2x"></i>
							<div class="mui-media-body">
								安全在线管理您的预订
								<p class='mui-ellipsis'>取消、更改预订或向场所发送要求，简单方便</p>
							</div>
						</div>
					</div>
					<footer>
						<%@ include file="/common/footer.jsp" %>
					</footer>
				</div>
				<!-- off-canvas backdrop -->
				<div class="mui-off-canvas-backdrop"></div>
			</div>
		</div>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/mobiscroll.js"></script>
		<script src="js/mui.min.js"></script>
		<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script> -->
		<script type="text/javascript" charset="utf-8">
			mui.init();
			getUserPostion();
			function showPosition(position){
				if(position != null){
			    	$('#latId').val(position.coords.latitude);
					$('#lngId').val(position.coords.longitude);
			    }else {
			    	//$('#latId').val(0);
				   // $('#lngId').val(0);  
			    }
			}
			function showGEOError(){
				//$('#latId').val(0);
			    //$('#lngId').val(0);
			}
			function getUserPostion(){
				if(navigator.geolocation){
					navigator.geolocation.getCurrentPosition(showPosition,showGEOError);	
				}			
			} 
		/* var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){  
		    if(this.getStatus() == BMAP_STATUS_SUCCESS){
		    	$('#latId').val(r.point.lat);
				$('#lngId').val(r.point.lng);
		    }else {
		    	$('#latId').val(0);
			    $('#lngId').val(0);  
		    	}
		}); */
			$(function() {
				/* $('form[search-form]').submit(function(){
					if($('#latId').val() != null && $('#latId').val() != ''){
						return true;
					}else{
						return false;
					}
				}); */
				$.ajax({
		    		url : "/findCompanyHot.do",
		    		type : "post",
		    		dataType : "html",
		    		success : function(data){
						$(".mui-index-place").html(data);
		    		}
		    	});
				$.ajax({
		    		url : "/findSearchCritera.do",
		    		type : "post",
		    		dataType : "json",
		    		success : function(data){
		    			if(data != null){
		    				$('#companyCategory').html(data.categorySelect);
		    				$('#companyGrade').html(data.gradeSelect);
		    			}
						
		    		}
		    	});
				$('.mui-index-place').on('click','li',function(){
					$(this).find('a')[0].click();
				})
			});
		</script>	
	</body>
</html>