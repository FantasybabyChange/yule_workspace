<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//zh-CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
   	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link rel="dns-prefetch" href="//static.yuleing.com">
	<link rel="shortcut icon" href="http://images.yuleing.com/ico/yuleing.ico" type="image/x-icon" />
	<link title="yuleing.com预乐" href="http://static.yuleing.com/search/yuleing.xml" type="application/opensearchdescription+xml" rel="search"/>
    <title>订单详情</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
</head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix mt-10">
        <div class="user-center-left">
        	<%@ include file="/common/menu.jsp" %>
        </div>
         <div class="user-center-right">
            <h2 class="user-center-title">我的订单</h2>
            <div class="caption2">订单信息</div>
            <div class="order-detail-content clearfix">
                <ul class="order-detail-left">
                    <li>
                        <span>订单编号：</span>${orders.order_num}</li>
                      <li>
                        <span>下单日期：</span>${create_time}</li>
                    <li>
                        <span>订单状态：</span>
                      <!--  <span class="order-status">已提交</span>
                        <span class="order-status-confim">已确认</span>-->
                        <span class="order-status-confim">${order_status}</span>
                    </li>
                    <li>
                    <span>评论状态：</span>${isComment}</li>
                </ul>
                <div class="order-detail-price">
                    <p>总金额：
                        <span class="order-price-num">${expense_money}元</span>
                    </p>
                    <span class="order-price-tips">不含小费</span>
                </div>
                <div class="order-detail-right-ctrl">
                    ${buttomHtml}
                </div>
            </div>
            <div class="caption2">订单内容</div>
            <div class="order-detail-info">
                <h2 ><span>${orders.company_name}</span></h2>
                <p class="address">
                    <span class="show_map_icon"></span>
                    <span id="hp_address_subtitle" >
                        ${comapny.address_detail}, ${comapny.business_name},${comapny.country_name},${comapny.city_name},${comapny.province_name}
                    </span>
                    <!--  <a class="show_map" href="javascript:void(0);" title="旅乐序精品旅馆, 台北 - 显示地图" id="show_id604572">显示地图</a>-->
                </p>
                <div class="order_your_stay order-room-detail">
                <p>
                        <span>包间名称:</span><span>${orders.product_name}</span></p>
                    <p>
                        <span>预计到店时间:</span>${emarrive_time}</p>
                    <p>
                        <span>实际到店时间：</span>${arrive_time}</p>
                        <p>
                    <span>使用积分：</span>${user_score}</p>
                        
                </div>
            </div>
             <div class="caption2">联系信息</div>
             <div class="order-detail-content clearfix">
                <ul class="order-detail-left">
                    <li>
                        <span>联系人：</span>${orders.customer_name }</li>
                    <li>
                        <span>联系电话：</span>${orders.customer_phone}</li>
                        <li>
                        <span>联系人邮箱：</span>${orders.customer_mail}</li>
                </ul>
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
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.config.js"></script>
    <script type="text/javascript">
    $(function() {
    	$('.user-icon-2').addClass('cur');
    	$('a[data-cancle]').live('click',function(){
    		var order_num = $(this).attr('data-cancle');
    		$.ajax({
    			type:'POST',
    			url:'/ordersCancle.do',
    			dataType:'json',
    			async:false,
    			data:{'order_num':order_num},
    			success:function(data){
    				if(data != null && data.status){
    					alert('订单取消成功');
    				}else{
    					alert('订单取消失败');
    				}
    				location.href='/findOrdersDetails.do?order_num='+order_num;
    			}
    		});
    	});
    });
    </script>
</body>
</html>