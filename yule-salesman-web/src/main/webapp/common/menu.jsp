<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
</script>

<ul class="nav nav-list">
	<li class="hover"><a href="/company/findCompanyInfo.do"><i
			class="menu-icon fa fa-list"></i><span class="menu-text"> 查看企业 </span></a><b
		class="arrow"></b></li>
	<li class="hover hsub"><a href="javascript:;"
		class="dropdown-toggle"><i class="menu-icon fa fa-list"></i><span
			class="menu-text"> 数据统计<span class="badge badge-primary">1</span></span><b
			class="arrow fa fa-angle-down"></b></a><b class="arrow"></b>
	<ul class="submenu">
			<li class="hover"><a href="/sale/saleStatistics.do"><i
					class="menu-icon fa fa-caret-right"></i>销售统计</a><b class="arrow"></b></li>
		</ul></li>
			<li class="hover"><a href="/orders/findOrders.do"><i
			class="menu-icon fa fa-list"></i><span class="menu-text"> 订单查询</span></a><b
		class="arrow"></b></li>
			<li class="hover"><a href="/balance/findLastMonthBalance.do"><i
			class="menu-icon fa fa-list"></i><span class="menu-text"> 结算</span></a><b
		class="arrow"></b></li>
</ul>	
					<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
	<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>


<!-- #section:basics/sidebar.layout.minimize -->
<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
	<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>

<!-- /section:basics/sidebar.layout.minimize -->
<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
</script>
