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
				预乐网合作伙伴管理系统[partner.yuleing.com]
			</small>
		</a>
		

		<!-- /section:basics/navbar.layout.brand -->

		<!-- #section:basics/navbar.toggle -->
		<button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
			<span class="sr-only">Toggle user menu</span>

			<img id="imagePhoneId" src="http://static.yuleing.com/assets/avatars/user.jpg" alt="Jason's Photo" />
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
			<!-- #section:basics/navbar.user_menu -->
			<li class="light-blue user-min">
				<a data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
					<img class="nav-user-photo" id="salesman_image" alt="Jason's Photo" />
					<span class="user-info">
						<small>欢迎,<span id="salesman_account"></span></small>
					</span>

					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a href="#password-wizard" data-toggle="modal">
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
	<!-- 修改密码 -->		
			<div id="password-wizard" class="modal" >
				<div class="modal-dialog" >
					<div class="modal-content" style="width:70%;">
						<div style="text-align: center"  class="modal-body step-content" id="modal-step-contents">
												<label>原密码:</label>&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="password" name="backPassword" id="oldPassword"  placeholder="请输入原密码"  datatype='' nullmsg="请输入原密码" errormsg=""/></br>
												<br>
												<label>新密码:</label>&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="password" name="password" id="password"  placeholder="请输入新密码"  datatype="*6-16" nullmsg="请输入新密码" errormsg=""/>
												<br>
												<br>
												<button  class="btn btn-sm btn-success" style="margin-left:7%;" data-type="changePass">
													修改密码
												</button>
												<button  class="btn btn-sm btn-success" style="display:none;" data-dismiss="modal" date-type='submit-success'>
												</button>
												</div>
																		
					</div>
				</div>
			</div>
