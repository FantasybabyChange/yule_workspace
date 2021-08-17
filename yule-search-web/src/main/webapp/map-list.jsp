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
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <title>${area_city_name} 预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/js/skin/layer.css">
</head>

<body>
    <div id="header-full">
       <%@ include file="/common/map-header.jsp" %>
    </div>
    
    <div class="list-map-content">
        <div class="list-map-view"  id="l-map" style="float: left;" style="width: 1920px; height: 487px;"></div>
        <div class="list-map-ctrl">
            <div class="btn_side_close" id="fold_btn">
                <a rel="nofollow" href="javascript:;"></a>
            </div>
            <div class="side_inner">
                <div id="sort_box" class="side_filter filter_landmark clearfix">
                    <ul class="side_filter_box cf clearfix">
                        <li id="sort_ctrip" class="selected"><a rel="ofollow" href="javascript:void(0);" class="c_c_yedh">搜索条件</a>
                        </li>
                        <li id="sort_score"><a class="c_sort_btn" href="javascript:;">酒店列表</a>
                        </li>
                    </ul>
                </div>
                <div id="map_list_box" class="side_list">
                    <div class="side_list_box">
                    <form data-query-form="" data-url="/mapSearchAction/searchCompanyMap.do" method="post">
                        <dl id="selectMenu">
                            <dt target-data="priceFilter" class="">
                                <span class="icons price"></span>
                                <span class="name">企业评分</span>
                                <span id="companyPointTitle" class="select" title="不限">不限</span>
                                <span class="cols"></span>
                            </dt>
                            <dd id="priceFilter" style="display: none;">
                                <ul id="ulPointFilter" class="ulMenu" target-data="setPrice">
                                </ul>
                            </dd>

 						<dt target-data="companyGrade" class="">
                                <span class="icons price"></span>
                                <span class="name">企业档次</span>
                                <span id="companyGradeTitle" class="select" title="不限">不限</span>
                                <span class="cols"></span>
                            </dt>
                          <dd id="companyGrade" style="display: none;">
                                <ul id="ulcompanyGrade" class="ulMenu" target-data="setGrade">
                                </ul>
                            </dd>
                            <dt target-data="companyCategory" class="">
                                <span class="icons price"></span>
                                <span class="name">企业分类</span>
                                <span id="companyCategoryTitle" class="select" title="不限">不限</span>
                                <span class="cols"></span>
                            </dt>
                          	<dd id="companyCategory" style="display: none;">
                                <ul id="ulcompanyCategory" class="ulMenu" target-data="setCategory">
                                </ul>
                          	 </dd>
                        </dl>
                        </form>
                    </div>
                    <div id="map_list" class="side_list_box" style="display:none">
                    </div>
                </div>
            </div>
        </div>
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
	<script type="text/javascript" src="http://static.yuleing.com/www/js/layer.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lpLgb0bNGKA2b8Rm3eQCqvqE"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/search/js/yuleing.jquery.company.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/search/js/baiduMap.js"></script>
    <script type="text/javascript">
    $(function() {
    	companyMap.layerIndex = layer.load("地图正在加载。。");
    	companyMap.cityId = '${area_city_id}';
    	companyMap.cityName='${area_city_name}';
    	companyMap.criteriaName='${criteriaName}';
    	companyMap.getJsonCriterName();
    	markerObject.initMapMarker(companyMap.cityId,companyMap.cityName);
    	companyMap.getCriteriaHtml();
    	companyMap.getCompanyListHtml(companyMap.cityId,1);
        mapWidth();
        $(window).resize(function(event) {
            mapWidth();
        });
        $('li[check-li]').live('click',function(){
        	var checkBox = $(this).find('input');
        	var checkType = $(this).attr('check-li');
        	if(checkBox.is(":checked")){
        		checkBox.removeAttr('checked');
        	}else{
        		checkBox.attr('checked','checked');
        	}
        	if(checkType === 'category'){
        	    companyMap.layerIndex = layer.load("地图正在加载。。");
        		changPage(findCommonJSON($('#companyCategoryTitle'),2));
        	}else if(checkType === 'grade'){
        		companyMap.layerIndex = layer.load("地图正在加载。。");
        		changPage(findCommonJSON($('#companyGradeTitle'),1));
        	}else{
        		companyMap.layerIndex = layer.load("地图正在加载。。");
        		changPage(findCommonJSON($('#companyPointTitle'),3));
        	}
        });
        $('.btn_side_close').live('click', function(event) {
            $('.list-map-ctrl').addClass('list-map-ctrl-hide');
            $(this).addClass('btn_side_open').removeClass('btn_side_close');
        });
        $('.btn_side_open').live('click', function(event) {
            $('.list-map-ctrl').removeClass('list-map-ctrl-hide');
            $(this).addClass('btn_side_close').removeClass('btn_side_open');
        });
        $('.hotel_pop_x').click(function(event) {
            $('.hotel_pop').hide();
        });
        $('body').on('mouseover','.side_list_item',function(){
        	if(markerObject.lastMarker === null || markerObject.lastMarker === ''){
        		markerObject.getMarkerById($(this).attr("data-id")).setIcon(markerObject.blueMarkerIcon);	
        	}
            $(this).addClass('item_hover');
        });
        $('body').on('mouseout','.side_list_item',function(){
            	if(markerObject.lastMarker === null || markerObject.lastMarker === ''){
            	markerObject.getMarkerById($(this).attr("data-id")).setIcon(markerObject.redMarkerIcon);
            	}
                $(this).removeClass('item_hover');
        });
        $('body').on('click','.side_list_item',function(event) {
        	/*if(markerObject.lastMarker != null && markerObject.lastMarker != ''){
        		markerObject.lastMarker.setIcon(markerObject.redMarkerIcon);
        	}
        	markerObject.lastMarker = markerObject.getMarkerById($(this).attr("data-id"));
        	markerObject.getMarkerById($(this).attr("data-id")).setIcon(markerObject.blueMarkerIcon);
        	*/
            $('.side_list_item').removeClass('item_hover')
            $(this).addClass('item_hover');
            $('.hotel_pop').show();
        })

        $('.side_filter_box li').click(function(event) {
            var _index = $(this).index();
            $(this).addClass('selected').siblings().removeClass('selected');
            $('#map_list_box .side_list_box').eq(_index).show().siblings().hide();
        });
        $('#selectMenu dt').click(function(event) {
            $('#selectMenu dd').hide();
            var _target = $(this).attr('target-data')
            $('#' + _target).show()
        });

        $('#list-map-text').focus(function(event) {
            $(this).parent().find('#list-map-suggestion').show();
            
        });
        $('body').on('click','#list-map-suggestion ul li a[data-id]',function(event) {
        	companyMap.layerIndex = layer.load("地图正在加载。。");
            $('#list-map-text').val($(this).text());
            $(this).closest('#list-map-suggestion').hide();
            companyMap.cityName=$(this).text();
            companyMap.cityId=$(this).attr('data-id');
          	markerObject.initMapMarker($(this).attr('data-id'),$(this).text());
            companyMap.getCompanyListHtml($(this).attr('data-id'));
            return false;
        });
        $('#searchLocalId').live('click',function(es){
        	companyMap.layerIndex = layer.load("地图正在加载。。");
        	changeMap($('#destination').val(),$('#list-map-text').val(),14);
        });
        $(document).click(function(event) {
            var target = $(event.target);
            if (target.closest(".list-map-city").find('#list-map-suggestion').length == 0) {
                $("#list-map-suggestion").hide();
            }
        });

    });

    function mapWidth() {
        var swidth = $(window).width();
        var sheight = $(window).height() - 50;
        var sheight2 = $(window).height() - 50;
        var sheight3 = $(window).height() - 50 - 44;
        var swidth2 = swidth >= 1004 ? swidth : 1004
        $('.list-map-view').css({
            'width': swidth2,
            'height': sheight
        })
        $('.side_inner').css({
            'height': sheight2
        });
        $('#map_list').css({
            'height': sheight3
        });
    }
    </script>
</body>
</html>