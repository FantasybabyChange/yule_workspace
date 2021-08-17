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
					<div class="mui-detail-place mui-clearfix">
						<a class="mui-pull-left" href="/search/searchCompany.do?area_city_id=${companyVO.city_id }&area_city_name=${companyVO.city_name}"><span class="mui-icon mui-icon-left-nav "></span>浏览位于${companyVO.city_name}的所有娱乐场所</a>
					</div>
					<div class="mui-p-detail">
						<div id="segmentedControl" class="mui-segmented-control mui-list-tab">
							<a class="mui-control-item mui-active" id="showMainPage" href="#item1mobile">信息</a>
							<a class="mui-control-item" id="showMapId" href="/companyDetails/${companyVO.id}/${companyVO.city_id}/${companyVO.city_name}/showMap.do">地图</a>
							<a class="mui-control-item" id="showCommentPoint" href="#item3mobile">评论<span id="commentPointId" class="mui-review-score"></span></a>
						</div>
						<div class="mui-list-content">
							<div id="item1mobile" class="mui-control-content mui-active">
								<div id="companyGallery" class="mui-slider detail-slider">
									 	正在加载。。。。。。
								</div>
								<div class="mui-detail-title">
									<h4>${companyVO.company_name}【${companyVO.company_grade_name}】</h4>
									<p><span class="mui-icon mui-icon-location"></span>${companyVO.address_detail},${companyVO.business_name},${companyVO.country_name},${companyVO.city_name} ,${companyVO.province_name}</p>
								</div>
							<div class="mui-room-info">
									<ul class="mui-table-view mui-table-view-chevron">
										<li class="mui-table-view-cell mui-collapse" id="serverLi"><a  class="mui-navigate-right" href="#">基本信息</a>
											<ul class="mui-table-view mui-table-view-chevron">
												<li class="mui-room-info-detail" >
													<h4>企业简介</h4>
													<p class="disclaimer">
													${companyVO.details}
													</p>
													<h4>营业时间</h4>
													<p>
														${companyVO.hours}
													</p>
													<h4>支付方式</h4>
													<p>
														${companyVO.pay_type_name}
													</p>
													<h4>温馨提示</h4>
													<p>
														${companyVO.warm_prompt}
													</p>
												</li>
											</ul>
										</li>
										</ul>
								</div>
									<div class="mui-empty-room">
									<h4>包间信息</h4>
									<div id="mui-popover-2" class="mui-popover mui-search-popover">
									</div>
								</div>
								<div class="mui-room-list">
									<ul class="mui-table-view" id="productUL">
										<li class="mui-table-view-cell mui-clearfix">
										正在加载。。。。。
										</li>
									</ul>
								</div>
								<div class="mui-room-info">
									<ul class="mui-table-view mui-table-view-chevron">
										<li class="mui-table-view-cell mui-collapse" id="serverLi"><a  class="mui-navigate-right" href="#">服务设施</a>
											<ul class="mui-table-view mui-table-view-chevron">
												<li class="mui-room-info-detail" id="companyServer">
													正在加载。。。
												</li>
											</ul>
										</li>
										<li class="mui-table-view-cell mui-collapse" id="companyFavorableLi"><a class="mui-navigate-right" href="#">优惠政策</a>
											<ul class="mui-table-view mui-table-view-chevron">
												<li class="mui-room-info-detail" id="companyFavorable">
													正在加载。。。
												</li>
											</ul>
										</li>
									</ul>
									</div>
									<div class="mui-index-table">
										<div class="mui-table-view-cell ">
											<i class="icon-check mui-pull-left  icon-2x"></i>
											<div class="mui-media-body">
												现在预订，到店付款！
												<p class="mui-ellipsis">多数客房可免费取消</p>
											</div>

										</div>
									</div>
									</div>
									<div id="item3mobile" style="display:none;" class="mui-control-content ">
										<div class="mui-score-view">
											<ul class="mui-table-view mui-grid-view ">
												<li class="mui-table-view-cell mui-media mui-col-xs-6 ">
													<p class="mui-score-view-title">总评分</p>
													<span id="sumPointId" class="mui-scoreGradient"></span>
													<p class="mui-score-view-from" >分数来自<span id="allCountCommentP">0</span>条评语
													</p>
												</li>
												<li id="commentPointAvgPoing" class="mui-table-view-cell mui-media mui-col-xs-6 mui-text-left mui-pd10">
													正在加载。。
												</li>
											</ul>
											<span id="userCommentSpan"></span>
												
										</div>
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
			<script type="text/javascript" src="/js/jquery.js"></script>
			<script type="text/javascript" src="/js/mobiscroll.js"></script>
			<script type="text/javascript"  src="/js/mui.min.js"></script>
		<script type="text/javascript" charset="utf-8">
		mui.init();
		var company_id = '${companyVO.id}';
			$(function() {
				$.ajax({
		    		url : "/companyGallery/findCompanyGallery.do",
		    		data:{id:company_id},
		    		type : "post",
		    		dataType : "json",
		    		success : function(data){
		    			if(data != null){
		    				$("#companyGallery").html(data.bigImage);	
		    			}
		    		}
		    	});
				$.ajax({
		    		url : "/productDetails/findProduct.do",
		    		data:{companyId:company_id},
		    		type : "post",
		    		dataType : "json",
		    		success : function(data){
		    			if(data != null){
		    				$("#productUL").html(data.productInfoHTMLs);	
		    			}
		    		}
		    	});
				$.ajax({
		    		url : "/companyDetailsComment/findPointNum.do",
		    		data:{company_id:company_id},
		    		type : "post",
		    		dataType : "json",
		    		success : function(data){
		    			if(data != null){
		    				if(data.commentNum === '0' || data.commentNum === 0){
		    					$("#commentPointId").html("无");
		    					$("#allCountCommentP").html("无评论");
		    				}else{
		    					$("#commentPointId").html(data.hightPoint);	
		    				}
		    				$("#allCountCommentP").html(data.commentNum);
		    			}
		    		}
		    	});
				document.getElementById("serverLi").addEventListener('touchend',function(){
					$.ajax({
			    		url : "/companyDetailsServe/findCompanyServer.do",
			    		data:{id:company_id},
			    		type : "post",
			    		dataType : "json",
			    		success : function(data){
			    			if(data != null){
			    				$("#companyServer").html(data.companyServeHTMLs);	
			    			}
			    		}
			    	});
				})
				document.getElementById("companyFavorableLi").addEventListener('touchend',function(){
					$.ajax({
			    		url : "/companyDetailsFavorable/findCompanyFavorable.do",
			    		data:{id:company_id},
			    		type : "post",
			    		dataType : "json",
			    		success : function(data){
			    			if(data != null){
			    				$("#companyFavorable").html(data.companyFavorableHTMLs);	
			    			}
			    		}
			    	});
				});
				document.getElementById("showMainPage").addEventListener('touchend',function(){
					$('#item3mobile').hide();
				});
				document.getElementById("showCommentPoint").addEventListener('touchend',function(){
					$('#item3mobile').show();
					$.ajax({
			    		url : "/companyDetailsComment/findCompanyCommentPointAVG.do",
			    		data:{company_id:company_id},
			    		type : "post",
			    		dataType : "json",
			    		success : function(data){
			    			if(data != null){
			    				if(data.avgScore){
				    				$('#sumPointId').html(data.avgScore);	
				    				}else{
				    				$('#sumPointId').html("无");	
				    				}
			    				$("#commentPointAvgPoing").html(data.companyCommentHtmls);	
			    			}
			    		}
			    	});
					changePage(1);
					
				});
				document.getElementById("showMapId").addEventListener('touchend',function(){
					location.href = this.href;
				});
				$('body').on('click','a[data-page]',function(){
					changePage($(this).attr('data-page'));
				})
			});
			function changePage(pageNo){
				$.ajax({
		    		url : "/companyDetailsComment/findCompanyCommentPage.do",
		    		data:{company_id:company_id,pageNo:pageNo},
		    		type : "post",
		    		dataType : "json",
		    		success : function(data){
		    			if(data != null){
		    				$('#userCommentSpan').html(data.companyCommentHtmls);
		    			}
		    		}
		    	});
			}
		</script>	
	</body>
</html>