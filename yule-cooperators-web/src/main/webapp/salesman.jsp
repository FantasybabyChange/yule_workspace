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
    <title>成为合作伙伴</title>
    <link rel="stylesheet" href="http://static.yuleing.com/www/css/style.css">
</head>

<body>
    <div id="header-full" class="mb0">
        <div role="navigation" id="menu-bar" class="cont-shadow">
            <div class="mw1004 clearfix">
                <a href="#" style="float: left;">
                    <img src="http://images.yuleing.com/logo/yuleing.png" alt="预乐进行时">
                </a>
                <div class="company-reg-right pull-right">
                    <span>已经是合作伙伴？</span>
                    <a class="b-button text-center" href="http://partner.yuleing.com/" >登录</a>
                </div>
            </div>
        </div>
    </div>

    <div class="company-banner">
        <h1>把握商机</h1>
        <h2>让yuleing.com助您一臂之力。</h2>
    </div>
    <div class="company-reg-content clearfix">
        <div class="company-reg-content-left pull-left">
            <div class="company-reg-content-left-content">
            <form validform="" action="/salesmanCooperator/insertSalesmanCooperator.do" method="POST" >
            	 <input type="hidden" name="t" value="<%=session.getId()%>">
                <div class="company-reg-title">现在就成为合作伙伴！</div>
                <div class="company-item">
                       <label>账号:</label>
                        <input type="text" class="input-text" name="account"  nullmsg="请输入账号!"  datatype="">
                 </div>
                <div class="company-item">
                      <label>邮箱:</label>
                     <input type="text" class="input-text" name="mail" nullmsg="请输入邮箱!"  datatype="e">
              </div>
                <div class="company-item">
                        <label>联系方式:</label>
                        <input type="text" class="input-text" name="phone" nullmsg="请输入手机号!"   datatype="p">
                </div>
                <div class="company-item">
                        <label>地址:</label>
                        <input type="text" class="input-text" name="address" nullmsg="请输入地址!"  datatype="" >
                </div>
                <div class="company-item">
                    <label>备注</label>
                    <textarea rows="3" cols="20" class="input-text" name="details" >
                    </textarea>
                </div>
                <p>点击下方按钮表示您同意接收来自yuleing.com的注册事宜邮件。</p>
                <div class="company-btn">
                    <button class="b-button " type="submit">
                        <span class="b-button__text">立即注册</span>
                    </button>
                </div>
                </form>
            </div>
        </div>
        <div class="company-reg-content-right pull-left">
            <div class="company-reg-content-right-content">
                <h4>为何选择与yuleing.com合作？</h4>
                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>佣金制合作模式
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">yuleing.com将根据完成的预订为您支付一定比例的收入。</span>
                    </h3>
                </div>

                <div class="oneusp usp_tick2 top">
                    <i class=" usp-tick2-small"></i>
                    <h3>佣金结算系统
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">我们提供简单易用的在线工具帮助您查看您的佣金。如需任何协助，欢迎随时联络我们的当地支持团队。</span>
                    </h3>
                </div>
                <div class="oneusp usp_tick2 top">
                    <i class="usp-tick2-small"></i>
                    <h3>卓越的支持团队和详尽的报告
                        <br>
                        <span style="font-size:.9em; font-weight: normal;">借助专业的客户团队以及详尽、完善的报告，深入分析您的业务状况，创造更大优势。</span>
                    </h3>
                </div>
            </div>
        </div>
    </div>
    <div class="compay-footer"><p>© Copyright Booking.com 2014</p></div>
    <script type="text/javascript" src="http://static.yuleing.com/js/jquery.min.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/slides.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/ajax.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/modal.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/panes.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/tab.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/imagePreview.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/select2.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/js/l.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/cooperators/js/yule.jquery.validform.js"></script>
    <script type="text/javascript">
    var isSuccess = '${status}';
    if((isSuccess === 'true')){
    	alert('注册成功');
    }else if(isSuccess === 'false'){
    	alert('请勿重复提交');
    }
    </script>
</body>

</html>
