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
            <h2 class="user-center-title">我的成长信息</h2>
            <div class="user-p-list clearfix">
                <div class="member_level">
                    ${levelHtmls }
                    <div class="lv_info_box first_lv_info">
                        <div class="lv_info" style="left: ${moneyRatio};">
                            <div class="arrow"></div>近一年内累积的消费${userExpenseVO.money}</div>
                    </div>
                </div>
                <div class="integral">
                    <div class="sub_mode_title">
                        <h3 class="h3_title">
                            	一年内累积的基本消费</h3>
                        <span class="points_tip">
                            ${userExpenseVO.money}</span>
                        <span class="line">
                        </span>
                    </div>
                    <div class="integral_info">
                        <div class="round_right" style="height: 0%;"></div>
                        <div class="info_con">
                            <p>您已超越了</p>
                            <p class="percent_num">
                                <span class="num">${userExpenseVO.ratio}</span>%
                                <span class="user">的用户</span>
                            </p>
                            <p class="tip_txt">
                            	${nextText}
                            <div class="percent">
                                <div style="width:0"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="privilege">
                    <div class="sub_mode_title">
                        <h3 class="h3_title">
                           	 当前享有的特权</h3>
                        <span class="line"></span>
                    </div>
                    <ul class="current_privilege clearfix">
                        <li>
                            <div class="t">
                                <h4 class="h4_title">回馈类</h4>
                            </div>
                            <div class="tab_con">
                                <div class="privilege_item current">
                                    <a href="MyPrivilege.aspx" target="_blank">
                                        <em class="p_feedback01">
                                            <img src="http://static.yuleing.com/www/images/11.jpg">
                                        </em>
                                        <span class="tab_txt">完成订单积分增加${currentUserLevel.score_ratio}%</span>
                                    </a>
                                </div>
                            </div>
                        </li>

                    </ul>

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
    <script type="text/javascript" src="http://static.yuleing.com/www/user/js/yule.jquery.config.js"></script>
    <script type="text/javascript">
    $(function() {
    });
    </script>
</body>
</html>