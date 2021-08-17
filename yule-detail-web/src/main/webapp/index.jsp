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
    <title>${companyVO.company_name}【${companyVO.company_grade_name}】预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/js/skin/layer.css">
</head>
<style>
.modal {
    margin-left: -601px;
    margin-top: -300px;
}
</style>
<body>
    <div id="header-full">
       <%@ include file="/common/header.jsp" %>
    </div>
    <div class="breadcrumb clearfix">
        <div class="g clearfix">
            <div class="breadcrumb-item">
                <p><a href="javascript:;">首页</a>
                    <span>→</span>
                </p>
            </div>
            <div class="breadcrumb-item">
                <p><a target="_blank" href="http://search.yuleing.com//search/searchCompany.do?area_city_id=${companyVO.city_id}&area_city_name=${companyVO.city_name}">${companyVO.city_name}</a>
                 <span>→</span> 
                </p>
                <p class="breadcrumb-tips"><span id="cityCountId"></span>个娱乐场所</p>
            </div>
            <div class="breadcrumb-item">
                <p><a target="_blank"href="http://search.yuleing.com//search/searchCompany.do?area_county=${companyVO.country_id}">${companyVO.country_name}</a>
                <span>→</span> 
                </p>
                <p class="breadcrumb-tips"><span id="countryCountId"></span>个娱乐场所</p>
            </div>
            <div class="breadcrumb-item">
                <p><a href="javascript:;">${companyVO.company_name}</a> 
                </p>
            </div>
            <div class="recent-order-status" id="companyOrdersHtml"></div>
        </div>
    </div>
    <div class="col clearfix" >
        <div class="col-s-left">
            <div class="detail-fav-ctrl">
                <div class="detail-fav-add" id="userFavorite">
                  		 <div class="loading"></div>
                </div>
            </div>
           <div class="detail_highlights_left" id="companyCommentId">
           			 <div class="loading"></div>
            </div>
             <div class="detail-map">
                		<div><img src="http://api.map.baidu.com/staticimage?markers=${companyVO.lng},${companyVO.lat}&width=238&height=198&zoom=15"/></div>
            </div>
            <div class="history" id="companyDistanceId">
            		 <div class="loading2"></div>
            </div>
        </div>
        <div class="col-s-right">
            <div class="sr_header">
                <div class="sr_header-ctrl">
                    <a href="#productInfoDIV" data-show='mainPanel' class="b-button index-order-btn mt10">现在就预订</a>
                </div>
                <h1><span data-name="company_name">${companyVO.company_name}</span>【${companyVO.company_grade_name}】
                </h1>
                <p class="address">
                    <span class="show_map_icon"></span>
                    <span id="hp_address_subtitle" data-name="company_area_address"> ${companyVO.address_detail}</span>
                    –
                    <a id="showMapId" class="show_map" href="javascript:;" title="显示地图" >显示地图</a>
                </p>
            </div>
            <div>
            <ul class="hp_nav_bar">
                <li><a href="#productInfoDIV" data-show='mainPanel'>可供预订的客房</a></li>
                <li><a href="#companyServeDiv" data-show='mainPanel'>设施</a></li>
                <li><a href="#changeToFavorable" data-show='mainPanel'>优惠政策</a></li>
                <li><a href="javascript:;" id="changeExpensePanel" >消费价格</a></li>
                <li class="brn"><a data-click="changeCommentPanel" id='changeCommentPanel'href="#commentPanel">经过核实的评语</a></li>
            </ul>
            </div>
           <div  id="mainPanel" >
            <div class="detail-ablum">
                <div class="detailAblum">
                    <div id='bigImageDiv' class="slides_container" >
						<div class="loading2"></div>
                    </div>
                    <ul class="pagination clearfix" id='smallImageUl'>
                    </ul>
                </div>
                <div class="ablum-over-content">
                    <div class="ablum-over-top" id="commentPointAll"><p class="ablum-over-avg">评分:<span id="commentPointId"></span></p><p>分数来自<span id="commentNumID"></span>条评语</p></div>
                </div>
            </div>
            <div class="detail-base-info">
            ${companyVO.details}
            </div>
            <div class="detail-room-info" id="productInfoDIV">
                <h2>包间信息</h2>
                <br/>
                   <div class="detail-room-list">
                       <table class="detail-room-table">
                           <thead>
                               <tr>
                                   <th>包间类型</th>
                                   <th>可容纳人数</th>
                                   <th>最低消费</th>
                                   <th>有房/无房</th>
                                   <th>预订包间</th>
                                   <th>操作</th>
                               </tr>
                           </thead>
                           <tbody id="productInfo">
                               <tr><td colspan="6"><div class="loading"></div></td></tr>
                           </tbody>
                       </table>
                   </div> 
            </div>
            <div class="detail-service">
               <h2>基本信息</h2>
                <div class="description clearfix">
                    <div class="d-name">营业时间</div>
                    <div class="d-info"> ${companyVO.hours} </div>
                </div>
                <div class="description clearfix">
                    <div class="d-name">支付方式</div>
                    <div class="d-info">${companyVO.pay_type_name}</div>
                </div>
                <h2>服务设施</h2>
                <div class="description clearfix" id="companyServeDiv" data-name="company_serve">
				<div class="loading"></div>
                </div>
                <h2 id="changeToFavorable">优惠政策</h2>
                <div id="companyFavorableDiv">
             	<div class="loading"></div>
                </div>
                <h2>温馨提示</h2>
                <div class="description clearfix">
                	${companyVO.warm_prompt}
                </div>
            </div>
            <div class="clearfix nha_filters_with_photos">
                <h3 class="nha_filters_with_photos_title">
                    	预乐为您推荐${companyVO.city_name}的其他娱乐休闲场所
                </h3>
                <div id="otherCompany">
                	<div class="loading"></div>
                </div>
            </div>
        </div>
        <div id="commentPanel" style="display:none;" class="comment-content clearfix">
        	<%@include file="/comment.jsp" %>
        </div>
        <div style="display:none;" id="expensePanel" class="detail-service">
                        <h2>消费价格</h2>
                <div id="companyExpenseDiv"> </div>
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
    <script src="http://static.yuleing.com/www/js/slides.min.jquery.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.timeago.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/detail/js/yule.jquery.elementObject.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/detail/js/yule.jquery.panelChange.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/layer.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.lazyload.js"></script>
	    <script type="text/javascript">
    $(function() {
		gallery.company_id='${companyVO.id}';
    	companyDistance.lng='${companyVO.lng}';
		companyDistance.lat='${companyVO.lat}';
		companyCount.country_id = '${companyVO.country_id}';
		companyCount.city_id = '${companyVO.city_id}';
		companyCount.province_id = '${companyVO.province_id}';
		companyCount.city_name = '${companyVO.city_name}';
    	element.init();
        $('.detailAblum').slides({
            effect: 'slide, fade',
            crossfade: true,
            slideSpeed: 350,
            fadeSpeed: 500,
            generateNextPrev: true,
            generatePagination: false
        });
    	$.ajax({
			url:'/company/searchCompanyOrderCount.do',
			data:{'companyId':gallery.company_id},
			type:'post',
			dataType:"json",
			async:false,
			success:function(data){
				if(null!=data){
					$('#companyOrdersHtml').html(data.companyOrdersHtml);
					}
			}
		});
        $('#showMapId').click(function(){
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
    });
    </script>
</body>
</html>