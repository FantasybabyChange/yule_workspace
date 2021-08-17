<%@page import="com.yule.constant.PrivilegeUrlConst"%>
<%@page import="com.yule.util.PrivilegeUtil"%>
<%@page import="com.yule.constant.CompanyCookieConst"%>
<%@page import="com.yule.constant.FileUploadConst"%>
<%@page import="com.yule.constant.CompanyRedisConst"%>
<%@page import="com.yule.redis.util.JedisUtil"%>
<%@page import="com.alibaba.druid.util.StringUtils"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.yule.company.vo.CompanyUserVO"%>
<%@page import="com.yule.util.CookieUtil"%>
<%@page import="com.yule.util.YuLeEncryptUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	try{ace.settings.check('navbar' , 'fixed')}catch(e){}
</script>

<div class="navbar-container" id="navbar-container">
	<div class="navbar-header pull-left">
		<!-- #section:basics/navbar.layout.brand -->
		<a class="navbar-brand">
			<small>
				<i class="fa fa-leaf"></i>
				预乐网ebooking管理系统[ebooking.yuleing.com]
			</small>
		</a>
		
		<!-- /section:basics/navbar.layout.brand -->

		<!-- #section:basics/navbar.toggle -->
		<button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
			<span class="sr-only">Toggle user menu</span>

			<img src="${image_path }" alt="${account}" />
		</button>

		<button class="pull-right navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".sidebar">
			<span class="sr-only">Toggle sidebar</span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>
		</button>

		<!-- /section:basics/navbar.toggle -->
	</div>

	<!-- #section:basics/navbar.dropdown -->
	<div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
		<ul class="nav ace-nav">
			<li class="transparent" notread-data="/findTaskNotRead.do" data-value="task">
				<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
					<label>任务</label>
					<span class="badge badge-danger" data-task=""></span>
				</a>

				<div class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close" >
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active">
								<a data-toggle="tab" href="#navbar-tasks">
									未完成任务(每天统计一次)
									<span class="badge badge-danger"  ></span>
								</a>
							</li>
<!-- 
							<li>
								<a data-toggle="tab" href="#navbar-messages">
									Messages
									<span class="badge badge-danger">5</span>
								</a>
							</li>
							 -->
						</ul><!-- .nav-tabs -->

						<div class="tab-content">
							<div id="navbar-tasks" class="tab-pane in active">
								<ul class="dropdown-menu-right dropdown-navbar dropdown-menu" id="taskShowId">
								</ul>
							</div><!-- /.tab-pane -->
						</div><!-- /.tab-content -->
					</div><!-- /.tabbable -->
				</div><!-- /.dropdown-menu -->
			</li>
			<%
			 String value = YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
			if(PrivilegeUtil.getCompanyPrivilegeUrl(value, PrivilegeUrlConst.PRIVILEGE_URL_ORDER)) {
			%>
			<li class="transparent" notRead-data="/findOrderNotRead.do" data-value="order">
				<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
					<label>订单</label>
					<span class="badge badge-danger" data-order=""></span>
				</a>

				<div class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active">
								<a data-toggle="tab" href="#navbar-tasks">
									未确定的订单
									<span data-order="" class="badge badge-danger"></span>
								</a>
							</li>
<!-- 
							<li>
								<a data-toggle="tab" href="#navbar-messages">
									Messages
									<span class="badge badge-danger">5</span>
								</a>
							</li>
							 -->
						</ul><!-- .nav-tabs -->

						<div class="tab-content">
							<div id="navbar-tasks" class="tab-pane in active">
								<ul id="orderShowId" class="dropdown-menu-right dropdown-navbar dropdown-menu">
								</ul>
							</div><!-- /.tab-pane -->
						</div><!-- /.tab-content -->
					</div><!-- /.tabbable -->
				</div><!-- /.dropdown-menu -->
			</li>
			<%	
			}
			%>
			<%
			 value = YuLeEncryptUtil.decode(CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME));
			if(PrivilegeUtil.getCompanyPrivilegeUrl(value, PrivilegeUrlConst.PRIVILEGE_URL_MESSAGE)) {
			%>
			<li class="transparent" notRead-data="/findMessageNotRead.do" data-value="message">
				<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
					<label>消息</label>
					<span class="badge badge-danger"  message-data=""></span>
				</a>

				<div class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active">
								<a data-toggle="tab" href="#navbar-tasks">
									未查看的消息
									<span class="badge badge-danger" message-data=""></span>
								</a>
							</li>
<!-- 
							<li>
								<a data-toggle="tab" href="#navbar-messages">
									Messages
									<span class="badge badge-danger">5</span>
								</a>
							</li>
							 -->
						</ul><!-- .nav-tabs -->

						<div class="tab-content">
							<div id="navbar-tasks" class="tab-pane in active">
								<ul class="dropdown-menu-right dropdown-navbar dropdown-menu" id="messageShowId">
								</ul>
							</div><!-- /.tab-pane -->
						</div><!-- /.tab-content -->
					</div><!-- /.tabbable -->
				</div><!-- /.dropdown-menu -->
			</li>
			<%} %>
			<%
				String cookie =  CookieUtil.getCookieValue(request.getCookies(), CompanyCookieConst.COMPANYUSER_COOKIE_NAME);
				if(!StringUtils.isEmpty(cookie)){
					String companyUserId = YuLeEncryptUtil.decode(cookie);
					String key = CompanyRedisConst.COMPANY_USER + companyUserId;
					CompanyUserVO companyUserVO = (CompanyUserVO)JSONObject.toBean(JSONObject.fromObject(JedisUtil.getInstance().get(key)),CompanyUserVO.class); 
					request.setAttribute("account", companyUserVO.getAccount());
					request.setAttribute("image_path", FileUploadConst.COMPANY_IMAGE_PATH+companyUserVO.getCompany_id()+"/"+FileUploadConst.PX_50_50+FileUploadConst.COMPANY_FACE+FileUploadConst.IMAGE_TYPE);
				}else{
					request.getRequestDispatcher("/showLogin.do").forward(request, response);
				}
			%>
			<!-- #section:basics/navbar.user_menu -->
			<li class="light-blue user-min">
				<a data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
					<img class="nav-user-photo" id='companyUser_image' src="${image_path }" alt="头像" />
					<span class="user-info">
						<small>欢迎,${account}</small>
						<span id='companyUser_account'></span>
					</span>

					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a href="/companyUser/findCompanyUserPassword.do">
							<i class="ace-icon fa fa-cog"></i>
							修改密码
						</a>
					</li>
					<li class="divider"></li>
					<li>
						<a href="/logout.do">
							<i class="ace-icon fa fa-power-off"></i>
							退出
						</a>
					</li>
				</ul>
			</li>

			<!-- /section:basics/navbar.user_menu -->
		</ul>
	</div>
</div><!-- /.navbar-container -->

	<!-- progress dialog订单流程对话框 -->
							<div id="modal-wizard" class="modal">
								<input type="hidden" id="orderID" name="order_num"/>
								<input type="hidden" id="status" name="status" value="1"/>
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header" data-target="#modal-step-contents">
												<ul class="wizard-steps">
													<li data-target="#modal-step1" class="active">
														<span class="step">1</span>
														<span class="title">已预订</span>
													</li>
													<li data-target="#modal-step2">
														<span class="step">2</span>
														<span class="title">已确认</span>
													</li>
													<li data-target="#modal-step3">
														<span class="step">3</span>
														<span class="title">已到店</span>
													</li>

													<li data-target="#modal-step4">
														<span class="step">4</span>
														<span class="title">已完成</span>
													</li>
												</ul>
											</div>
											<div class="modal-body step-content" id="modal-step-contents">
												<div class="step-pane active" id="modal-step1">
												</div>
											</div>
											<div class="modal-footer wizard-actions">
												<button class="btn btn-success btn-sm btn-next" data-dismiss="modal">
													确认
													<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
