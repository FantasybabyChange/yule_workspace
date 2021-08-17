<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=10;IE=9;IE=8;IE=7;IE=EDGE;">
	<meta name="keywords" content="夜店预订,特价夜店,夜店查询,夜总会预订,特价夜总会,夜总会查询查询,酒吧预订,特价酒吧,酒吧查询,娱乐会所预订,特价娱乐会所,娱乐会所查询,演艺会所预订,特价演艺会所,演艺会所查询" />
	<meta name="description" content="预乐进行时是中国领先的O2O娱乐休闲预订公司,夜店点评及特价夜店查询,娱乐会所预订,酒吧预订,演艺会所预订,为您提供全方位娱乐休闲场所预订咨询服务"/>
	<link rel="dns-prefetch" href="//static.yuleing.com">
	<link rel="dns-prefetch" href="//images.yuleing.com">
	<link rel="dns-prefetch" href="//static.yuleing.com">
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>${area_city_name} 预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/js/skin/layer.css">
    <link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css">
</head>

<body>
    <div id="header-full">
      <%@ include file="/common/header.jsp" %>
    </div>
    <div class="breadcrumb clearfix">
        <div class="g clearfix">
            <div class="breadcrumb-item">
                <p><a href="http://www.yuleing.com">首页</a>
                    <span>→</span>
                </p>
            </div>
            <div class="breadcrumb-item" id="provinceCountHtml">
            </div>
            <div class="breadcrumb-item" id="cityCountHtml">
            </div>
            <div class="recent-order-status" id="cityOrdersHtml"></div>
        </div>
    </div>
    <div class="col clearfix">
        <form data-query-form="" action="javascript:;" data-url="/search/searchCompanyListAjax.do" method="post">
        <div class="col-s-left">
            <div class="search-wapper mb15">
                       <div class="from-btn clearfix">
                        </div>
                    <div class="search-wapper-top">
                        <h3 class="c-form-title mb15">
                            <span class="c-form-title__text f24">搜索</span>
                        	
                        </h3>
                        <h4>
                            <label for="destination">目的地/酒店名称：</label>
                        </h4>
                        <div class="input-wrapper">
                        	<input type="hidden" id="area_city_id" name="area_city_id" value="${area_city_id}">
                        	<input type="hidden" id="area_city_name" name="area_city_name" value="${area_city_name}">
                            <input class="input-style w195" autocomplete="off" type="text" placeholder="例如：西安" id="destination"  value="">
                        	<input type="hidden" name="sort_company_point" value="true">
                        	<input type="hidden" name="sort_company_grade" value="true">
                        </div>
                        <div class="from-btn clearfix">
                            <button id="search-button" class="b-button f24  pull-right br6" type="button">
                                <span class="b-button__text">搜索</span>
                            </button>
                        </div>
                    </div>
                    <!--  <div class="search-wapper-bottom">
                        <h3 class="c-form-title mb15">
                            <span class="c-form-title__text f24">您的搜索</span>
                        </h3>
                        <h4>
                            <label for="destination">地区：西安</label>
                        </h4>
                        <div class="text-right clearfix">
                            <a href="javascript:;" class="b-button  index-order-btn">更改搜索</a>
                        </div>
                        </h3>
                    </div> -->
                
            </div>
            <div class="filters">
                <h3 class="filter_by">通过以下分类搜索：</h3>
                <div class="filterbox-list" id="searchCriteriaHtml">
                ${searchCriteriaHtml }
                </div>
            </div>
            <div class="detail-map">
                		<div id="baiduMapDiv"style="cursor:pointer;"><img src="http://api.map.baidu.com/staticimage?center=${area_city_name }&width=238&height=198&zoom=10"/></div>
            </div>
<!--             <div class="history">
                <span class="history-del"></span>
                <h3>我浏览过的酒店</h3>
                <ul class="history-list-2">
                    <li class="history-list-item history-list-item-list clearfix">
                        <span class="history-list-del"></span>
                        <a href="/city/cn/shanghai.zh-cn.html?sid=b02dab10595e6485e28e61112667ffe0;dcid=1;city=-1924465;ilp=1">
                            <img src="" data-trigger="preview" data-href="" data-title="上海 中国 酒店" alt="上海 中国 酒店" title="位于上海的酒店, 中国" width="30" height="30">
                        </a>
                        <p><a href="javascript:;" class="history-list-title">五脚基旅馆中国城</a> <i class="b-sprite stars ratings_stars_2_sm" title="5星级"></i>
                        </p>
                        <span class="lastbooking">最新的预订：27分钟之前</span>
                         <p>1位客人正在浏览这家酒店。</p>
                    </li>
                    <li class="history-list-item history-list-item-list clearfix">
                        <span class="history-list-del"></span>
                        <a href="/city/cn/shanghai.zh-cn.html?sid=b02dab10595e6485e28e61112667ffe0;dcid=1;city=-1924465;ilp=1">
                            <img src="" data-trigger="preview" data-href="" data-title="上海 中国 酒店" alt="上海 中国 酒店" title="位于上海的酒店, 中国" width="30" height="30">
                        </a>
                        <p><a href="javascript:;" class="history-list-title">五脚基旅馆中国城</a> <i class="b-sprite stars ratings_stars_2_sm" title="5星级"></i>
                        </p>
                        <span class="lastbooking">最新的预订：27分钟之前</span>
                         <p>1位客人正在浏览这家酒店。</p>
                    </li>
                </ul>
            </div> -->
        </div>
        </form>
        <div class="col-s-right">
            <div class="sr_header">
<!--                 <h1>成都共有310个住处有空房。</h1>
                <p class="property_types_filters_links">
                    <a href="javascript:;">
                        <span class="filter_count">377</span>
                        酒店
                    </a>
                    <a href="javascript:;">
                        <span class="filter_count">41</span>
                        旅舍
                    </a>
                    <a href="javascript:;">
                        <span class="filter_count">41</span>
                        宾馆
                    </a>
                    <a href="javascript:;">
                        <span class="filter_count">57</span>
                        公寓
                    </a>
                    <a href="javascript:;">
                        <span class="filter_count">7</span>
                        旅馆
                    </a>
                    <a href="javascript:;">
                        <span class="filter_count">1</span>
                        民宿
                    </a>
                </p> -->
<!--                 <h2>
                    <span>
                        显示结果
                        <span class="sr_showed_amount_first">1</span>-
                        <span class="sr_showed_amount_last">15</span>（按距离排序）
                    </span>
                </h2> -->
            </div>
            <div class="sort_by">
                <ul class="sort_option_list bar clearfix">
                    <li class="sort_category selected">排序方式</li>
                    <li class="sort_category"><a href="javascript:;" data-sort="sort_company_grade">档次<i class="b-sprite down_dark"></i></a>
                    </li>
                    <li class="sort_category"><a href="javascript:;" data-sort="sort_company_point">评分<i class="b-sprite down_dark"></i></a>
                    </li>
                </ul>
            </div>
            <div id="hotellist_inner" >
				${companyHtml }

            </div>
            <div class="results-meta" id="results_prev_next">
                <div class="try_another_search_3">
                    <span>
                        未找到理想的酒店？
                        <a href="javascript:;">返回搜索页面。</a>
                    </span>
                </div>
            </div>
            <div class="results-paging" id="pageHtml">
            ${pageHtml }
<!--                 <span class="paging-start">前一页</span>
                <ul class="x-list">
                    <li class="sr_pagination_item current"><a class="sr_pagination_link" href="javascript:;">1</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">2</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">3</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">4</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">5</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">6</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">7</a>
                    </li>
                    <li class="sr_pagination_item sr_pagination_break">
                        <span class="sr_pagination_break_inner">…</span>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">20</a>
                    </li>
                    <li class="sr_pagination_item"><a class="sr_pagination_link" href="javascript:;">21</a>
                    </li>
                </ul>
                <a class="paging-next ga_sr_gotopage_2_21" href="javascript:;">下一页</a> -->
                
            </div>
            <div class="clearfix nha_filters_with_photos">
                <h3 class="nha_filters_with_photos_title">
                   ${area_city_name }的其他娱乐场所
                </h3>
				<div id="otherCompanyHTMLs"></div>
            </div>
        </div>
    </div>
    <div class="footer">
        <%@ include file="/common/footer.jsp" %>
    </div>
    <div class="footer-other">
        <%@ include file="/common/footer-other.jsp" %>
    </div>
    <div class="right-sider-bar w1000">
    	<%@ include file="/common/right-sider-bar.jsp" %>
    </div>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/slides.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/ajax.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/modal.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/panes.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/tab.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/imagePreview.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/collapse.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/search/js/yule.query.page.config.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/layer.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/search/js/yule.jquery.ajaxAreaName.js"></script>
    <script type="text/javascript">
   	 	var city_id='${area_city_id }';
    	var city_name='${area_city_name }';
    	function getCity(){
    		return $('#area_city_id').val();
    	}
    	function getCityName(){
    		return $('#area_city_name').val();
    	}
    	$(function($){
			$("form").find("input:radio:checked,input:checkbox:checked").each(function(){
				var radio = $(this);
				var btn = radio.parent().parent(); 
				if(!btn.hasClass("active")){
					radio.removeAttr("checked");
				}
			});
    	    $('#baiduMapDiv').click(function(){
            	var pageii = $.layer({
                    type: 2,
                    title: false,
                    area:[($(window).width() - 100) +'px',($(window).height() - 50) +'px'],
                    border: [0], 
                    shade: [0.5, '#000'], 
                    closeBtn: [0, true], 
                    shadeClose: true,
                    iframe: {
                    	src: '/mapFrame.html',
                    	scrolling:'no'
                    	}
                });
            });
			$("#search-button").click(function(){
				var form =$("form[data-query-form]");
				form.attr("method","get");
				form.attr("action","/search/searchCompany.do");
				form.submit();
			});    		
			$.ajax({
				url:'/company/findOtherCompany.do',
				data:{'area_city_id':${area_city_id}},
				type:'post',
				dataType:"json",
				async:false,
				success:function(data){
					if(null!=data){
						$("#otherCompanyHTMLs").html(data.otherCompanyHTMLs);
					}
				}
			});
     		$.ajax({
    			type: "POST",
    			url: "/city/findCityCompany.do",
    			dataType:"json",
    			data:{area_city_id:${area_city_id}},
    			async:false,
    			success: function(msg){
    				if(msg.status=true){
    	 				$(".sr_header").html(msg.cityCompanyHtml.replace(new RegExp("#{area_city_name}",'g'),'${area_city_name}'));
        				$("#cityCountHtml").html(msg.cityCountHtml.replace(new RegExp("#{area_city_name}",'g'),'${area_city_name}'));
        				$("#provinceCountHtml").html(msg.provinceCountHtml);
        				$("#cityOrdersHtml").html(msg.cityOrdersHtml);
    				}
      			}
    		});
    	});
    </script>
</body>
</html>
