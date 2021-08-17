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
    <title>订单 预乐进行时 yuleing.com 全国免费预订夜店,夜总会,娱乐会所,酒吧,演艺会所</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/select2.css">
    <link rel="stylesheet" href="http://static.yuleing.com/www/orders/css/odrers.css">
 </head>

<body>
    <div id="header-full">
        <%@ include file="/common/header.jsp" %>
    </div>
    <div class="col clearfix w1024">
        <ul class="order-step">
            <li class="order-step-1">
                <span>1. 选择您的客房</span>
            </li>
            <li class="order-step-2 active">
                <span>2. 提交您的个人信息</span>
            </li>
            <li class="order-step-2">
                <span>3. 成功预订！</span>
            </li>
        </ul>
        <div class="order-left">
            <div class="order-top-info clearfix">
                <div class="order-top-pic">
                    <img src="${ordersProductVO.company_face }">
                </div>
                <div class="order-top-content">
                    <h2>
                   ${ordersProductVO.company_name }
                    </h2>
                    <p class="address">
                        <span class="show_map_icon"></span>
                        <span id="hp_address_subtitle">
                            ${ordersProductVO.company_area_address }
                        </span>
                    
                    <div class="order_your_stay">
                    <div class="order-room-info clearfix">
                        <img src="http://images.yuleing.com/loading/loading.jpg" data-original="${ordersProductVO.product_face }" class="pull-left">

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
            <div class="order-top-service">
                      	<div class="facility-header">
                            <strong>您预订的娱乐场所提供以下设施或服务：</strong>
                        </div>
                        <div class="facilities">${ordersProductVO.company_serve}</div>
                </div>
            <div class="order-edit-info ">
                <div class="order-edit-title">预订信息</div>
                <form action="/orders/insertOrders.do" method="post" validform="">
                <input type="hidden" name="t" value="<%=session.getId()%>"/>
                	<input type="hidden" name="detail_to_order" value="${detail_to_order}">
                <div class="order-edit-content clearfix">
                	<input type="hidden"  name="user_area_city_id" id="" value="${userLoginVO.user_area_city_id }"/>
                	<input type="hidden" name="user_area_city_name" id="" value="${userLoginVO.user_area_city_name }"/>
                    <p class="order-title">联系人：</p>
                    <div class="order-input">
                        <input type="text" validword="" name="customer_name" id="" value="${userLoginVO.name }"  datatype="" nullmsg="请输入的联系人姓名"  errormsg="请输入正确的联系人姓名"/>
                    </div>
                    <p class="order-title">联系电话：</p>
                    <div class="order-input">
                        <input type="text" name="customer_phone" id=""  value="${userLoginVO.phone }" datatype="p" nullmsg="请输入的联系电话"  errormsg="请输入正确的联系电话"/>您将会收到一条订单信息
                    </div>
                    <p class="order-title">电子邮箱地址：</p>
                    <div class="order-input">
                        <input type="text" name="customer_mail" id="" value="${userLoginVO.mail }" datatype="e" nullmsg=""  errormsg="请输入正确的联系邮箱" />
                    </div>
                    <p class="order-title"></p>

                    <div class="eta_holder">
                        <h4>
			                            您预计何时到达${ordersProductVO.company_name }？
                        </h4>
                        <label for="eta_time">
                            <b>到店日期</b> 
                        </label>
    					<input type="text" class="laydate-icon" id="estimate_arrive_time" name="estimate_arrive_time" datatype="" nullmsg="请填写最晚到店时间" >
    					<label for="eta_time">
                            <b>最晚到店时间</b> 
                        </label>
                        	<select id="arrive_hour" style="width:66px;">
                        	<option>请选择</option>
                        	</select>时
                        	<select id="arrive_minute" style="width:66px;">
                        	<option>请选择</option>
                        	</select>分
    					<input type="hidden" name="last_arrive_time" id="last_arrive_time"  >
                    </div>
                    <div class="special_requests">
                        <h4>
                            特殊要求
                        </h4>
                        <em>

                            酒店无法保证满足您的特殊要求，但会尽力尝试。
                        </em>
                        <div class="remarks_holder">
                            <textarea name="desc" cols="50" rows="3" class="ClickTaleSensitive" validword=""></textarea>
                        </div>
                        <p class="order-title">您有可用积分：<span data-name="score">${user_score}</span></p>
                        <input type="checkbox" id="use_score" />使用积分 (100起步)
			             <div class="order-input">
                        <input type="text" maxlength="9" name="user_score" id="user_score" score="" datatype="n1-16" nullmsg="" errormsg="请输入正确的积分"   disabled="disabled" />
               		     </div>
               		     <span class="block input-icon input-icon-right">
<%--                		                 		     取消订单太频繁了 ，请输入验证码</br>
							<input type="text" id="captcha" datatype="captcha" nullmsg="请输入验证码" name="captcha"  placeholder="验证码"  />
							<img alt="验证码" title="验证码" id="captcha-img"  style="cursor: pointer;" src="" data-src="/captcha.jpg?sessionid=<%=request.getSession().getId()%>" />
 --%>						${captchaHtml }
  						</span>
                  	  	</div>
	                    <button class="b-button f18  pull-right br6" type="submit">
	                        <span class="b-button__text">提交订单</span>
	                    </button>
                </div>
			</form>
            </div>


        </div>
        <div class="order-right">
            <ul class="pricing">
                <li class="">
                    1 包间
                </li>
                <li class="">
					公主小费500元
                </li>
                <li class="">
				       不包含15%服务费
                </li>
                <li class="">
                线下支付
                </li>
                <li class="">
                    请在前台索取发票
                </li>

                <li class="pay_today_holder">
<!--                     <span class="pricing_type">
                        今天您需支付
                    </span>
                    <span class="pricing_cost">
                        <span class="nowrap hotel_currency">
                            <span id="cost_with_addons_today">TWD 6,880</span>
                        </span>
                    </span> -->
                </li>
            </ul>
            <div class="pricing_total_cost">
               
                <table class="topDisplayedCurrency">
                    <tbody>
                        <tr colspan="2">
                            <td>
                                <p class="totalPriceText">
                                  		 最低消费
                                </p>

	                            </td>
                            <td class="topCurrencyClar">
                                <span class="total_user_currency pricing_cost">
                                    <span id="cost_with_addons">RMB ${ordersProductVO.min_expense }</span>
                                </span>
                            </td>
                        </tr>
                    </tbody>
                </table>
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
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.lazyload.js"></script>
	<!-- yule js  -->
	<script type="text/javascript" src="http://static.yuleing.com/www/orders/js/yule.jquery.validform.js"></script>
	<script type="text/javascript" src="http://static.yuleing.com/www/laydate/laydate.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	$("img[data-original]").lazyload({
			effect : "fadeIn"
		});
    	$("img[data-src]").live('click',function(){
    		$(this).attr("src",$(this).attr("data-src")+"&t="+new Date().getTime());
    	});
    	$("img[data-src]").click();
    });
    $(function() {
    	var orders_message ='${orders_message}';
    	if(orders_message.length>5){
    		alert(orders_message);
    	}
    	initHour(0);
    	initMiute();
    	var miuteValue = $('#arrive_minute').val();
    	var hourValue = $('#arrive_hour').val();
    	$('#last_arrive_time').val( hourValue + ":" + miuteValue);
    	$("#use_score").click(function(){
    		if($(this).prop("checked")==true){
    			$("#user_score").removeAttr("disabled");
    		}else{
    			$("#user_score").attr("disabled",true);
    			$("#user_score").val("");
    			validator.valid($("#user_score").parent());	
    		}
    	});
        $('.detailAblum').slides({
            effect: 'slide, fade',
            crossfade: true,
            slideSpeed: 350,
            fadeSpeed: 500,
            generateNextPrev: true,
            generatePagination: false
        });
        $('#arrive_hour').live('change',function(){
        	var hourValue = $(this).val();
        	if(nowHour == hourValue ){
        		initMiute(nowMinute);
        	}else{
        		initMiute(0);
        	}
    		
    	    var miuteValue = $('#arrive_minute').val();
    	    $('#last_arrive_time').val(hourValue + ":" + miuteValue);
    	});
    	$('#arrive_minute').live('change',function(){
    		var miuteValue = $(this).val();
    		var hourValue = $('#arrive_hour').val();
    		$('#last_arrive_time').val(hourValue + ":" + miuteValue );
    	});
    });
    var nowHour = 19;
    var nowMinute = 0;
	var estimate_arrive_time = {
		    elem: '#estimate_arrive_time',
		    format: 'YYYY-MM-DD',
		    min: laydate.now(), //设订最小日期为当前日期
		    max: laydate.now(+2), //最大日期
		    istime:false,
		    istoday: true,
		    choose: function(datas){ //选择日期完毕的回调
		    validator.valid($("#estimate_arrive_time").parent());	
		    var chooses = datas.split('-');
		    var chooseDate = new Date();
		    var nowDate  = new Date();
		    chooseDate.setFullYear(chooses[0],chooses[1]-1,chooses[2]);
		    if(chooseDate.getFullYear() == nowDate.getFullYear() 
		    	&& chooseDate.getMonth()==nowDate.getMonth()
		    	&&chooseDate.getDate() == nowDate.getDate()){
		    	if(nowDate.getHours() >= 19 && nowDate.getHours() < 23
		    			||nowDate.getHours() >= 0 && nowDate.getHours() < 4){
		    		nowDate.setMinutes(nowDate.getMinutes()+30);
		    		nowHour = nowDate.getHours();
		    		nowMinute = nowDate.getMinutes();
		    		initHour(nowDate.getHours());
		    		initMiute(nowDate.getMinutes());
		    	}else if(nowDate.getHours() >= 5 && nowDate.getHours() < 19){
		    		nowHour = nowDate.getHours();
		    		nowMinute = nowDate.getMinutes();
		    		initHour(19);
		    		initMiute();	
		    	}else if(nowDate.getHours() == 23){
		    		nowHour = nowDate.getHours();
		    		nowMinute = nowDate.getMinutes();
		    		initHour(nowHour);
		    		initMiute(nowMinute);	
		    	}
		    }else{
		    	initHour(0);
		    	initMiute();
		    }
		    var hourValue = $('#arrive_hour').val();
    	    var miuteValue = $('#arrive_minute').val();
    	    $('#last_arrive_time').val(hourValue + ":" + miuteValue);
		    }
		};
    laydate(estimate_arrive_time);
    function initHour(hour){
    	var hour1 = 0;
    	var hour2 = 19;
    	var options = '';
    	if(hour < 5 && hour >= 0){
    		hour1 = hour;	
    		 for(var i = hour1; i < 5;i++){
         		var show = i;
         		if(i == 0){
         			show ="00";
         		}else if(show < 10){
         			show = '0'+show;
         		}
         		options += "<option value=\""+show+"\">"+show+"</option>";
         }	
     	
    	}else if(hour >= 19){
    	    hour2 = hour;	
    	}
    	
    	for(var i = hour2; i < 24;i++){
    		var show = i;
    		if(i == 0){
    			show ="00";
    		}else if(show < 10){
    			show = '0'+show;
    		}
    		options += "<option value=\""+show+"\">"+show+"</option>";
    	}
    	$('#arrive_hour').html(options);
    	$('#arrive_hour').select2();
    }
    function initMiute(miute){
    	if(!miute){
    		miute = 0;
    	}
    	var options = '';
    	for(var i = miute; i < 60;i++){
    		var show = i;
    		if(i == 0){
    			show ="00";
    		}else if(show < 10){
    			show = '0'+show;
    		}
    		options += "<option value=\""+show+"\">"+show+"</option>";
    	}
    	$('#arrive_minute').html(options);
		$('#arrive_minute').select2();		
    }
    </script>
</body>

</html>
