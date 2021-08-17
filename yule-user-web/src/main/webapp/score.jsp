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
    <title>我的积分</title>
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
            <div class="user-filter clearfix">
            	<form action="/score.do" method="post" data-query-form="">
	                <li>
	                    <div class="user-filter-title">积分类型</div>
	                    <select class="user-choice-select select2-offscreen" data-trigger="select" name="type" onchange="this.form.submit();">
	                        <option value="" selected>所有类型</option>
	                        <option value="1">注册</option>
	                        <option value="2">登录</option>
	                        <option value="3">下单</option>
	                    </select>
	                </li>
                </form>
            </div>
            <div class="caption">积分列表</div>
            <table class="order-list-table">
                <thead>
                    <tr>
                        <th>积分类型</th>
                        <th>积分</th>
                        <th>状态</th>
                    </tr>
                </thead>
                <tbody>
                    ${htmls}
                </tbody>
            </table>
            ${pageHtmls }
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
    <script type="text/javascript" src="http://static.yuleing.com/js/select-util.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.config.js"></script>
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.query.page.config.js"></script>
    <script type="text/javascript">
    $(function() {
    	selectValue("select[name='type']","${userScoreQuery.type}");
    });
    </script>
</body>
</html>