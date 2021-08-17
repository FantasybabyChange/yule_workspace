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
    <title>Document</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
    <div id="header-full" class="mb0">
        <div role="navigation" id="menu-bar" class="cont-shadow">
            <div class="g clearfix">
                <a href="javascript:;" style="float: left;">
                    <img src="http://static.yuleing.com/www/images/logo.png" alt="">
                </a>
                <div class="company-reg-right pull-right">
                    <span>已经是合作伙伴？</span>
                    <a class="b-button text-center" href="javascript:;" data-trigger="modal" data-title="用户登录"> 登录</a>
                </div>

            </div>
        </div>
    </div>
    <div class="breadcrumb-2 g">
        <span>当前位置:</span> <a href="javascript:;">首页</a> &gt; <a href="javascript:;">合作伙伴</a> &gt; 注册
    </div>
    <div class="company-reg-content g clearfix">
        <div class="news-left pull-left">
            <ul>
                <li><a href="javascript:;">关于我们</a>
                </li>
                <li><a href="javascript:;">联系我们</a>
                </li>
                <li><a href="javascript:;">加入我们</a>
                </li>
                <li><a href="javascript:;" class="cur">成为合作伙伴</a>
                </li>
            </ul>
        </div>
        <div class="news-right pull-left">
            <div class="news-right-content">
                <h1 class="specials">业务员注册</h1>

                <p>请按照以下要求提供所有资料。</p>
                <p>若您的网站处于非活跃状态，请务必通知我们您的商业计划。</p>
                <p>您将会在最短的时间内收到与我们网站建立链接的详细实施方案。</p>

                <p>如果贵酒店想注册参加，请点击此处。</p>
                <form validform="" action="/salesmanCooperator/insertSalesmanCooperator.do" method="POST" >
                
                <div class="p-details">
                 <input type="hidden" name="t" value="${t}">
                    <h2>一般信息</h2>
                    <div class="company-item clearfix">
                        <label>账号:</label>
                        <input type="text" class="input-text" name="account"  nullmsg="请输入账号!"  datatype="">
                    </div>
                    <div class="company-item clearfix">
                        <label>邮箱:</label>
                        <input type="text" class="input-text" name="mail" nullmsg="请输入邮箱!"  datatype="e">
                    </div>
                    <div class="company-item clearfix">
                        <label>联系方式:</label>
                        <input type="text" class="input-text" name="phone" nullmsg="请输入手机号!"   datatype="p">
                    </div>
                    <div class="company-item clearfix">
                        <label>地址:</label>
                        <input type="text" class="input-text" name="address" nullmsg="请输入地址!"  datatype="" >
                    </div>
                    <div class="company-item clearfix">
                        <label>备注:</label>
                        <textarea rows="6" cols="52" name="details"></textarea>
                    </div>

                    <div class="agree-por clearfix"><input type="submit" class="b-button pull-right text-center" value=" 下一步 >">
                        <label>
                            <input type="checkbox" name="agree_tc" value="1">&nbsp;我明白并同意这项运营协议！</label>
                    </div>
                </div>
                </form>
            </div>
        </div>

    </div>
    <div class="footer">
        <div class="g">
            <ul class="footer-nav">
                <li><a href="javascript:;">管理预订</a>
                </li>
                <li><a href="javascript:;">客户服务</a>
                </li>
                <li><a href="javascript:;">成为合作伙伴</a>
                </li>
                <li><a href="javascript:;">成为会员伙伴</a>
                </li>

            </ul>
            <div class="apps">
                <span class="text">下载我们的应用：</span>
                <span class="logo">
                    <a href="http://itunes.apple.com/app/id284971959" target="_blank" class="ios"></a>
                    <a href="https://market.android.com/details?id=com.hcom.android" target="_blank" class="android"></a>
                </span>
            </div>
        </div>
    </div>
    <div class="footer-other">
        <div class="g admin-login">
            <a href="javascript:;">后台登录</a>
        </div>
        <div class="g copyright">
            <p>© Copyright Yuleing.com 2014</p>
        </div>
    </div>
    <div class="right-sider-bar w1000">
        <ul>
            <li class="relative weixinCode-wapper"><a href="javascript:void(0)" class="wx-icon" title="扫描关注微信">微信</a>
                <div class="weixinCode"><i class="warr">&nbsp;</i>
                    <img src="http://static.weke.com/2013/images/wx.png" alt="迈克森二维码">
                    <p>关注微信</p>
                </div>
            </li>
            <li><a href="javascript:void(0)" class="kf-icon" title="在线客服">在线客服</a>
            </li>
            <li><a href="javascript:;" class="go-top" title="回到顶部">回到顶部</a>
            </li>
        </ul>
        </div>
        <script type="text/javascript" src="/js/jquery.js"></script>
        <script type="text/javascript" src="/js/slides.js"></script>
        <script type="text/javascript" src="/js/ajax.js"></script>
        <script type="text/javascript" src="/js/modal.js"></script>
        <script type="text/javascript" src="/js/panes.js"></script>
        <script type="text/javascript" src="/js/tab.js"></script>
        <script type="text/javascript" src="/js/imagePreview.js"></script>
        <script type="text/javascript" src="/js/select2.js"></script>
        <script type="text/javascript" src="/js/l.js"></script>
        <script type="text/javascript" src="/yule.jquery.validform.js"></script>
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
