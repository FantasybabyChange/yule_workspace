<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
					<div id="segmentedControl" class="mui-segmented-control mui-list-tab">
						<a class="mui-control-item mui-active" href="#item1mobile">	搜索结果（${rowCount}）</a>
						<a class="mui-control-item" id="changeMapDiv" href="#item2mobile">地图	</a>
						<a class="mui-control-item" href="#item3mobile">筛选项目	</a>
					</div>

					<div class="mui-list-content">
						<div id="item1mobile" class="mui-control-content mui-active">
							<div class="mui-list-place mui-clearfix">
								<h4><i class="icon-map-marker"></i> ${companyQuery.area_city_name }</h4>
								<h5 id="city_count">正在加载。。。</h5>
							</div>
							<ul class="mui-table-view mui-table-view-chevron mui-list-filter">
								<li class="mui-table-view-cell mui-collapse mui-clearfix">
									<a class="mui-navigate-right" href="#"> <i class="icon-tasks"></i> 排序方式：<span class="mui-list-filter-name" id="order-by-type">推荐</span>
									</a>
									<ul class="mui-table-view mui-table-view-chevron mui-clearfix" id="order-ui">
										<li class="mui-table-view-cell mui-radio mui-left">
											<span class="mui-badge mui-badge-blue" data-val="">推荐 </span>
										</li>
										<li class="mui-table-view-cell mui-radio mui-left">
											<span class="mui-badge mui-badge-blue" data-val="0">档次 </span>
										</li>
										<li class="mui-table-view-cell mui-radio mui-left">
											<span class="mui-badge mui-badge-blue" data-val="1">评分 </span>
										</li>
									</ul>
								</li>
							</ul>
							<div class="mui-select-type mui-clearfix" id="show-li">
							${searchtabHtml }
							</div>
							<div class="list-wapper">
								<ul class="mui-table-view" id="companyHtml">
									${companyHtml}
 								</ul>
							<ul class="mui-pager">
								${pageHtml }
							</ul>
							</div>
						</div>
						<div id="item2mobile" class="mui-control-content">
						<div id="l-map" style="margin-left:11px;width:98%;height:400px;"></div>
						</div>
						<div id="item3mobile" class="mui-control-content">
						<form data-url="/search/searchCompanyListAjax.do" query-from="" method="post">
							<div>
								<input type="hidden" name="lat" value="${companyQuery.lat}">
								<input type="hidden" name="lng" value="${companyQuery.lng}">
								<input type="hidden" name="name" value="${companyQuery.name}">
								<input type="hidden" name="order" value="">
							</div>
							<ul class="mui-table-view mui-table-view-chevron" id="search_filter">
								${searchCriteriaHtml }
								
							</ul>
						</form>
						</div>
					</div>

					<footer>
						Copyright © 2014-2015 , 预乐进行时版权所有 www.yuleing.com
						<br>网站备案号:陕ICP备14010387号-1
					</footer>
				</div>
				<!-- off-canvas backdrop -->
				<div class="mui-off-canvas-backdrop"></div>
			</div>
		</div>
			<script type="text/javascript" src="/js/jquery.js"></script>
			<script type="text/javascript" src="/js/mobiscroll.js"></script>
			<script src="/js/mui.min.js"></script>
			<script type="text/javascript" charset="utf-8">
			mui.init();
			$(function() {
				$('#Date-changes').mobiscroll().rangepicker({
					theme: 'mobiscroll',
					lang: 'zh',
					display: 'bottom',
					defaultValue: [new Date(), new Date()],
					minDate: new Date(),
					startInput: '#Date-start',
					endInput: '#End-start'
				});
			});
			</script>
 			<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script>
			<script type="text/javascript" src="/searchbaiduMap.js"></script> 
			<script type="text/javascript" charset="utf-8">
			page = {
					getPageNo:function(pageno){
						return '<input type="hidden" value="'+pageno+'" name=\"pageNo\" />';
					},
				}
				function changPage(pageNoHtm){
					var form = $("form[query-from]");
					form.find("input[name='pageNo']").remove();
					form.prepend(pageNoHtm);
					var jsonData = {};
					form.find("input[type='text']").each(function(){
						var val = $(this).val();
						if(val!=""&&val!=undefined){
							jsonData[$(this).attr("name")] = val;
						}
					});
					form.find("input[type='hidden']").each(function(){
						var val = $(this).val();
						if(val!=""&&val!=undefined){
							jsonData[$(this).attr("name")] = val;
						}
					});
					$("#search_filter").find("li").find("ul[data-type]").each(function(){
						var i = 0;
						$(this).find("input:checkbox:checked").each(function(){
							var val = $(this).val();
							if(val!=""&&val!=undefined){
								jsonData[$(this).attr("name")+"["+i+"]"] = val;
								i++;
							}
						});
				
					});
					$("#search_filter").find("li").find("ul[data-type]").each(function(){
						$(this).find("input:radio:checked").each(function(){
							var val = $(this).val();
							if(val!=""&&val!=undefined){
								jsonData[$(this).attr("name")] = val;
							}
						});
					});
					markerObject.jsonData = jsonData;
					$.ajax({
						url:form.attr("data-url"),
						data:jsonData,
						type:form.attr("method"),
						dataType:"json",
						async:false,
/* 						beforeSend :function(data){
						}, */
						success:function(data){
							if(null!=data){
								$(".mui-pager").html(data.pageHtml);
								$("#companyHtml").html(data.listHtml);
								$("a[href='#item1mobile']").html("搜索结果（"+data.rowCount+"）");
								 var a3 = $("a[href='#item3mobile']");
								 if(a3.hasClass("mui-active")){
									 a3.removeClass("mui-active");
									 $("#item3mobile").removeClass("mui-active");
									 $("#item1mobile").addClass("mui-active");
									 $("a[href='#item1mobile']").removeClass("mui-active");
								 }
							}
						}
					});
				}
			var city_area_id = '${companyQuery.area_city_id }';
			var city_area_name = '${companyQuery.area_city_name }';
			$(function() {
/* 				$('form[search-form]').submit(function(){
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
				$.ajax({
					url:"/city/findCityCompany.do",
					data:{"area_city_id":${companyQuery.area_city_id }},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if(null!=data){
							$("#city_count").html(data.count+"家娱乐场所");
						}
					},
				});
				$(".mui-pager").on('click','a[data-page]',function(){
					changPage(page.getPageNo($(this).attr("data-page")));
				});
				$("#order-ui").on('click','span[data-val]',function(){
					$("input[name='order']").val($(this).attr("data-val"));
					$("#order-by-type").text($(this).text());
					changPage(page.getPageNo(1));
				});
				$("#show-li").on('click','span[data-id]',function(){
					var val = $(this).attr("data-id");
					$(this).remove();
					var form = $("form[query-from]");
					form.find("input[type='checkbox'],input[type='radio']").each(function(){
						if($(this).val()==val){
							$(this).removeAttr("checked");
						}
 					});
					changPage(page.getPageNo(1));
				});
				$("#item3mobile").on('click','li[data-val]',function(){
					
					var btn =$(this);
					var span_id = '';
					var span_text = btn.text();
					var span_name = '';
					var input ;
 					btn.find("input[type='checkbox'],input[type='radio']").each(function(){
 						input  = $(this);
 						span_id = input.val();
 						span_name = input.attr("name");
 						if(!$(this).prop("checked")==true){
 							$(this).removeAttr("checked");
 	 					}else{
 	 						$(this).attr("checked", true);
 	 					}
 					});
 					if(span_name=="company_point_category"){
 						$("#show-li").find("span[name='"+span_name+"']").remove();
						if(input.prop("checked",true)){
							var span= "<span  class=\"mui-badge mui-badge-yellow\" name=\""+span_name+"\" data-id=\""+span_id+"\">&nbsp;&nbsp;"+span_text+"\ <span class=\"mui-icon mui-icon-close\"></span></span>";
 	 						$("#show-li").append(span);
						}
 					}else{
 						if($("#show-li").find("span[data-id='"+span_id+"']").length==0){
 	 						var span= "<span  class=\"mui-badge mui-badge-yellow\" name=\""+span_name+"\" data-id=\""+span_id+"\">&nbsp;&nbsp;"+span_text+"\ <span class=\"mui-icon mui-icon-close\"></span></span>";
 	 						$("#show-li").append(span);
 	 					}else{
 	 						$("#show-li").find("span[data-id='"+span_id+"']").remove();
 	 					}	
 					}
 					changPage(page.getPageNo(1));
				});
			});
			document.getElementById('changeMapDiv').addEventListener('touchend',function(){
				if(markerObject.jsonData == null){
					markerObject.jsonData = {};
					markerObject.jsonData['area_city_id']=city_area_id;
					markerObject.jsonData['city_area_name']=city_area_name;	
				}
				initMap(city_area_name);
				markerObject.initMapMarker();
			}) 
		</script>	
	</body>
</html>