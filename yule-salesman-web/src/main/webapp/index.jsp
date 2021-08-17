<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>合作伙伴首页</title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<%@ include file="/common/styles.jsp" %>
		<!-- page specific plugin styles -->

	</head>

	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default navbar-collapse h-navbar">
			<%@ include file="/common/header.jsp" %>
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
				<%@ include file="/common/menu.jsp" %>
			</div>

			<div class="main-content">
				<div class="page-content">
					<!-- 
					<div class="page-header">
						<h1>
							Gallery
							<small>
								<i class="ace-icon fa fa-angle-double-right"></i>
								responsive photo gallery using colorbox
							</small>
						</h1>
					</div>
					 -->
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="alert alert-block alert-success">
								<button type="button" class="close" data-dismiss="alert">
									<i class="ace-icon fa fa-times"></i>
								</button>

								<i class="ace-icon fa fa-check green"></i>
								欢迎使用
								<strong class="green">
									预乐网业务人员后台管理系统
									<small>(v1.0)</small>
								</strong>,
								轻便,功能丰富和易于使用的管理系统
							</div>

							<div class="row">
								<div class="col-sm-12">
									<div class="widget-box transparent" id="recent-box">
										<div class="widget-header">
											<h4 class="widget-title lighter smaller">
												<i class="ace-icon fa fa-rss orange"></i>系统通知
											</h4>
										</div>

										<div class="widget-body">
											<div class="widget-main padding-4">
												<div class="tab-content padding-8">
													<div id="task-tab" class="tab-pane active">
														<ul id="tasks" class="item-list">
															${adminNoticeHtmls}
														</ul>
													</div>
												</div>
											</div><!-- /.widget-main -->
										</div><!-- /.widget-body -->
									</div><!-- /.widget-box -->
								</div><!-- /.col -->
							</div><!-- /.row -->
							
							<div class="hr hr32 hr-dotted"></div>
							<!-- 
							<div class="row">
								<div class="col-sm-6">
									<div class="widget-box transparent" id="recent-box">
										<div class="widget-header">
											<h4 class="widget-title lighter smaller">
												<i class="ace-icon fa fa-rss orange"></i>
												新订单
											</h4>
										</div>

										<div class="widget-body">
											<div class="widget-main padding-4">
												<div class="tab-content padding-8">
													<div id="task-tab" class="tab-pane active">
														<ul id="tasks" class="item-list">

															<li class="item-blue clearfix">
																<label class="inline">
																	<span class="lbl"> Upgrading scripts used in template</span>
																</label>
															</li>

															<li class="item-grey clearfix">
																<label class="inline">
																	<span class="lbl"> Adding new skins</span>
																</label>
															</li>

															<li class="item-green clearfix">
																<label class="inline">
																	<span class="lbl"> Updating server software up</span>
																</label>
															</li>

															<li class="item-pink clearfix">
																<label class="inline">
																	<span class="lbl"> Cleaning up</span>
																</label>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="widget-box transparent" id="recent-box">
										<div class="widget-header">
											<h4 class="widget-title lighter smaller">
												<i class="ace-icon fa fa-rss orange"></i>
												新消息
											</h4>
										</div>

										<div class="widget-body">
											<div class="widget-main padding-4">
												<div class="tab-content padding-8">
													<div id="task-tab" class="tab-pane active">
														<ul id="tasks" class="item-list">

															<li class="item-blue clearfix">
																<label class="inline">
																	<span class="lbl"> Upgrading scripts used in template</span>
																</label>
															</li>

															<li class="item-grey clearfix">
																<label class="inline">
																	<span class="lbl"> Adding new skins</span>
																</label>
															</li>

															<li class="item-green clearfix">
																<label class="inline">
																	<span class="lbl"> Updating server software up</span>
																</label>
															</li>

															<li class="item-pink clearfix">
																<label class="inline">
																	<span class="lbl"> Cleaning up</span>
																</label>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="hr hr32 hr-dotted"></div>
							 -->
							
							<div class="row">

								<div class="col-sm-12">
									<div class="widget-box">
										<div class="widget-header">
											<h4 class="widget-title lighter smaller">
												<i class="ace-icon fa fa-comment blue"></i>
												最新评论
												<span  style="float:right;margin-top:5px;margin-right:5px;" class="btn btn-white btn-sm btn-primary"><a href="/companyComment/findCompanyComment.do">更多</a></span>
											</h4>
										</div>

										<div class="widget-body">
											<div class="widget-main no-padding">
												<div class="dialogs">
														${companyCommentHtmls}
												</div>
												<!-- 
												<form>
													<div class="form-actions">
														<div class="input-group">
															<input placeholder="Type your message here ..." type="text" class="form-control" name="message" />
															<span class="input-group-btn">
																<button class="btn btn-sm btn-info no-radius" type="button">
																	<i class="ace-icon fa fa-share"></i>
																	Send
																</button>
															</span>
														</div>
													</div>
												</form>
												-->
											</div><!-- /.widget-main -->
										</div><!-- /.widget-body -->
									</div><!-- /.widget-box -->
								</div><!-- /.col -->
							</div><!-- /.row -->
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<div class="footer">
				<%@ include file="/common/footer.jsp" %>
			</div>

			<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<%@ include file="/common/scripts.jsp" %>

		<!-- page specific plugin scripts -->

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			});
			
		</script>
	</body>
</html>
