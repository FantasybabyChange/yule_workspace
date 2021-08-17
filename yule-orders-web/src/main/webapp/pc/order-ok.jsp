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
 
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>订单完成 预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
</head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col p8 clearfix w1024">
        <ul class="order-step">
            <li class="order-step-1">
                <span>1. 选择您的娱乐场所</span>
            </li>
            <li class="order-step-2">
                <span>2. 提交您的个人信息</span>
            </li>
            <li class="order-step-2  active">
                <span>3. 成功预订！</span>
            </li>
        </ul>
         <div class="order-title">订单提交成功</div>
        <div class="car-content order-ok">
                 <div class="">
	         		<strong>订单号：${orders.order_num }</strong>	
                    <p>
                        <strong>预计到达时间:</strong>
                        ${estimate_arrive_time }
                        </p>
                      <span class="ok-tips">预订成功,请您及时到达</span>
                </div>
       </div>
         <div class="order-title">订单信息</div>
        <div class="order-top-info clearfix">
            <div class="order-top-pic">
                <a href="http://detail.yuleing.com/company/findCompanyDetails.do?id=${orders.company_id} ">
                <img src="${company_face }">
                </a>
                
            </div>
            <div class="order-top-content">
                <h2><a href="http://detail.yuleing.com/company/findCompanyDetails.do?id=${orders.company_id} ">${orders.company_name }</a>
                </h2>
                <p class="address">
                    <span id="hp_address_subtitle">
                        ${ordersProductVO.company_area_address }		
                    </span>
                   
                </p>
                <div class="order-top-content">
                    <img src="${product_face }">
                </div>
                <div class="order_your_stay">
                    <p>
                        <p class="order-room-name">
                            <strong>包间:</strong>
                            <span class="room-name-pure">${ordersProductVO.product_name }</span>
                        </p>
                        <div class="max-persons-details">
                            <label class="room-detail-label">
                                <strong>最多人数:</strong>
                            </label>${ordersProductVO.person_num }
                            
                         </div>
                </div>
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
    <script src="http://static.yuleing.com/www/js/slides.min.jquery.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript">
    $(function() {
        $('.detailAblum').slides({
            effect: 'slide, fade',
            crossfade: true,
            slideSpeed: 350,
            fadeSpeed: 500,
            generateNextPrev: true,
            generatePagination: false
        });
    });
    </script>
</body>

</html>
