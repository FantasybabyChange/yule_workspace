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
	<link rel="dns-prefetch" href="//p.yuleing.com">
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <title>预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/css/jquery.autocomplete.css">
</head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix">
        <div class="col-left">
            <div class="search-wapper">
                <form action="javascript:;" target="_blank" method="get" data-search-form="" accept-charset="utf-8">
                    <h3 class="c-form-title ">
                        <i class="c-form-title__icon "></i>
                        <span class="c-form-title__text f24">搜索</span>
                    </h3>
                    <div class="c-form-subtitle__text">国内领先的娱乐场所预订平台</div>
                    <h4>
                        <label for="destination">目的地/场所名称：</label>
                    </h4>
                    <div class="input-wrapper">
                        <input class="input-style" autocomplete="off" type="text" placeholder="例如：城市、娱乐场所" id="destination" value="">
                        <input type="hidden" id="search_id" name="area_city_id"value="610100"/>
                        <input type="hidden" id="search_name" name="area_city_name" value="西安"/>
                    </div>
                    <div class="from-btn clearfix">
                        <div class="advanced-search pull-left">
                            <a href="javascript:;" class="advanced-search-title">附加搜索条件<i class="advanced-search-icon"></i></a>
                            <div class="advanced-search-content clearfix" style="display:none">
                            </div>
                        </div>
                        <button class="b-button f16  ml5 pull-right br6" data-url="http://search.yuleing.com/mapSearchAction/searchCompany.do"type="submit">
                            <span class="b-button__text">地图搜索</span>
                        </button>
                        <button class="b-button f16  pull-right br6" data-url="http://search.yuleing.com/search/searchCompany.do" type="submit">
                            <span class="b-button__text">搜索</span>
                        </button>
                    </div>
                </form>
            </div>
            <div class="oneusp usp_tick2 top">
                <i class="usp-tick2-small"></i>
                <h3>超值优惠，物超所值
                    <br>
                    <span style="font-size:.9em; font-weight: normal;">服务一流、评分更佳、性价比更高</span>
                </h3>
            </div>
            <!-- 
            <div class="oneusp usp_tick2 top">
				<i class="b-sprite usp-tick2-small"></i>
				<h3>注册即送1000积分<br>
				<span style="font-size:.9em; font-weight: normal;">
					<a href="/ajax/login.html" data-trigger="modal" data-title="注册yuleing.com账户" >点击此处立即注册</a>
				</span>
				</h3>
			</div>
			 -->
             <div class="oneusp usp_tick2 top">
				<i class="b-sprite usp-tick2-small"></i>
				<h3>24小时全天候为您服务<br>
				<span style="font-size:.9em; font-weight: normal;">需要帮助？
					<a href="javascript:;" data-google-track="Click/Action: index/loc_usp_with_cuca_cjkt_index">联系我们18691821238 15353718993</a>
				</span>
				</h3>
			</div>
			<div class="activity_stream">
                <h2>新预订</h2>
            </div>
            <h2 class="recommendation">热门城市</h2>
            <ul class="hot-city">
                
            </ul>
            <div class="uspsbox">
                <h2>使用我们网站预订的5大理由</h2>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>低廉的价格
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">不收预订费 • 省钱 • 线上最优惠价格</span>
                    </h3>
                </div>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>优惠折扣
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">尊享独有的商家优惠</span>
                    </h3>
                </div>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>到店支付
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">到店消费支付、方便快捷</span>
                    </h3>
                </div>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>真实住客评语
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">我们验证所有评语的真实性</span>
                    </h3>
                </div>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>安全在线管理您的预订
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">取消、更改预订或向住宿发送要求，简单方便</span>
                    </h3>
                </div>
            </div>
        </div>
        <div class="col-right">
            <div class="index-search-suggestion">
                <div class="searchbox_suggestion_on_right_arrow"></div>
                <div class="destination-suggestion destination-suggestion-with-history">

                </div>
            </div>
            <!-- 
            <div class="oneusp usp_tick2 top">
                <i class=" usp-tick2-small"></i>
                <h3>成为合作伙伴
                    <br>
                    <span style="font-size:.9em; font-weight: normal;">
                    	<a href="http://cooperators.yuleing.com/salesmanCooperator/findSalesmanCooperator.do" target="_blank">点击此处立即成为推广伙伴</a>
                    </span>
                </h3>
            </div>
			<div class="oneusp usp_tick2 top">
                <i class=" usp-tick2-small"></i>
                <h3>成为合作企业
                    <br>
                    <span style="font-size:.9em; font-weight: normal;">
                    	<a href="http://cooperators.yuleing.com/companyCooperator/findCompanyCooperator.do" target="_blank">点击此处立即成为合作企业</a>
                    </span>
                </h3>
            </div>
             -->
            <div class="index-focus">
				<a href="/ajax/register.html" data-trigger="modal" data-title="注册yuleing.com账户" ><img src="http://images.yuleing.com/banner/1.jpg"></a>
                <a href="http://ebooking.yuleing.com/" target="_blank" ><img src="http://images.yuleing.com/banner/2.jpg"></a>
                <a href="http://partner.yuleing.com/" target="_blank" ><img src="http://images.yuleing.com/banner/3.jpg"></a>
            </div>
            <div class="recommendation-part">
               
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
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.autocomplete.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/pc/js/yule.jquery.ajaxAreaName.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.timeago.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.lazyload.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("img[data-original]").lazyload({
			effect : "fadeIn"
		});
    	$('button[data-url]').click(function(){
    		var searchName = $('#search_name').attr('name');
    		if(searchName != null && searchName.length > 0){
    			var form = $('form[data-search-form]');
    			form.attr('action',$(this).attr('data-url'));
					    			
    		}else{
    			alert('请输入搜索城市');
    			return false;
    		}
    	});
    	$.ajax({
    		url:"/findAreaCityNav.do",
    		type:"post",
    		dataType:"html",
    		success:function(data){
    			$(".destination-suggestion-with-history").html(data);
    		}
    	});
    	$.ajax({
    		url:"/findSearchCriteria.do",
    		type:"post",
    		dataType:"html",
    		success:function(data){
    			$(".advanced-search-content").html(data);
    		}
    	});
    	$.ajax({
    		url : "/findAreaCityHot.do",
    		type : "post",
    		dataType : "html",
    		success : function(data){
				$(".hot-city").html(data);
				$(".hot-city").find("img[data-original]").lazyload({
					effect : "fadeIn"
				});
    		}
    	});
       	$.ajax({
    		url : "/findAreaCompanyHot.do",
    		type : "post",
    		dataType : "html",
    		success : function(data){
				$(".recommendation-part").html(data);
				initCompanyHot();
    		}
    	});
       	$.ajax({
    		url : "/orders.do",
    		type : "post",
    		dataType : "html",
    		success : function(data){
    			if(data!=""){
    				var html = "<ul class=\"\" id=\"activity_items\">"+data+"</ul>";
    				$(".activity_stream").append(html);
    				new slider({
    			        id: 'activity_items'
    			    });
    				$(".activity_stream").find("img[data-original]").lazyload({
    					effect : "fadeIn"
    				});
    			}
    		}
    	});
       	
       	$("a[data-area]").live('click',function(){
       		$.ajax({
        		url : "/updateArea.do",
        		data:{"areaId":$(this).attr("data-area")},
        		type : "post",
        		dataType : "html",
        		success : function(data){
        			window.location.reload();
        		}
        	});
       	});
       	$('#moreView').live('click',function(){
       		var href = $(this).attr("data-href");
			var value = $('body').find('.postcard-nav').find('.selected').attr("data-category");
			window.open(href+value);
       	});
    });
    function initCompanyHot(){
    	$(".consolidated-tabs").find("a").live("click",function(){
    		var t = $(this).parent();
    		var first = $(".consolidated-tabs").find("li[class='selected']");
    		first.removeClass("selected");
    		first.html("<a href=\"javascript:;\">"+first.text()+"</a>");
    		t.addClass("selected");
    		t.text($(this).text());
       		$.ajax({
        		url : "/findCompanyHot.do",
        		data:{"areaCityId":t.attr("data-city"),"companyCategoryId":t.attr("data-category")},
        		type : "post",
        		dataType : "html",
        		success : function(data){
    				var list = $(".recommendation-list");
    				list.html(data);
    				list.find("img[data-original]").lazyload({
    					effect : "fadeIn"
    				});
    				list.find('.timeago').timeago();
    				list.find('[data-trigger="preview"]').preview();
					//htmls.append("<a href=\"/searchresults.zh-cn.html?sid=d2404c6c064c94cd44ffb89361f14b6b;dcid=4;city=-2631690\" class=\"view-more\" >查看更多信息 »</a>");
        		}
        	});
       	});
    	$(".consolidated-tabs").find("li[class='selected']").click();
    }
    </script>
</body>
</html>