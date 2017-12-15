<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>车辆管理系统</title>
<%@ include file="./common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
</head>

<body class="skin-2">
	<!-- #section:basics/navbar.layout -->
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<div class="navbar-container ace-save-state" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand -->
				<a href="#" class="navbar-brand"> <small> <!-- 车辆管理系统 -->
						<img src="${ctx}/img/jinghuismall.png" height="30" width="30" />&nbsp;重庆市涪陵公安智能车联系统
				</small>
				</a>
			</div>
	<div class="navbar-header pull-right" role="navigation">
               <ul class="nav ace-nav">	
                <li class="light-blue">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
				 <span class="user-info" style="vertical-align: inherit;">您好,${carSysUserDOLogin.username}</span>
				 <i class="icon-caret-down"></i>
				</a>
				<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
				<li><a href="${ctx}/refreshCache" menuurl="${ctx}/#" title="刷新缓存"><i class="icon iconfont icon-refresh"></i>刷新缓存</a></li>
				 <li class="divider"></li>
				 <li><a href="javascript:void(0)" menuurl="${ctx}/#" id="Exit_system"><i class="fa fa fa-sign-out"></i>退出</a></li>
				</ul>
			   </li>
					</ul>
                </div>

			<!-- /section:basics/navbar.dropdown -->
		</div>
		<!-- /.navbar-container -->
	</div>

	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

		<!-- #section:basics/sidebar -->
		<div id="sidebar" class="sidebar responsive ace-save-state">
			<script type="text/javascript">
				try {
					ace.settings.loadState('sidebar')
				} catch (e) {
				}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<!-- #section:basics/sidebar.layout.shortcuts -->
					<button class="btn btn-warning">
						<i class="ace-icon fa fa-users"></i>
					</button>

					<button class="btn btn-danger">
						<i class="ace-icon fa fa-cogs"></i>
					</button>

					<!-- /section:basics/sidebar.layout.shortcuts -->
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<!--
                	作者：1435738906@qq.com
                	时间：2017-09-25
                	描述：树目录
                -->
			<ul class="" id="menu">

			</ul>

			<!-- #section:basics/sidebar.layout.minimize -->
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<!-- /section:basics/sidebar.layout.minimize -->
		</div>

		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content" style="height: 550px;">
					<!-- #section:settings.box -->
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
							id="ace-settings-btn">
							<i class="ace-icon fa fa-cog bigger-130"></i>
						</div>

						<div style="height: 60px" class="ace-settings-box clearfix"
							id="ace-settings-box">
							<div class="pull-left width-50">
								<!-- #section:settings.skins -->
								<div class="ace-settings-item">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; Choose Skin</span>
								</div>
							</div>
						</div>
						<!-- /.ace-settings-box -->
					</div>
					<!-- /.ace-settings-container -->

					<div class="row">
						<div class="col-xs-12">
							<ul class="nav nav-tabs" role="tablist">
								<li class="active"><a href="#Index" role="tab"
									data-toggle="tab">首页</a></li>

							</ul>
							<div class="tab-content">
								<div role="tabplane" class="tab-pane active" id="Index">
									<iframe src="${ctx }/toHomePagePrompt" width="100%" height="650px" frameborder="no"
										border="0" marginwidth="0" marginheight="0" scrolling="yes"
										allowtransparency="yes"></iframe>
								</div>
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>

	</div>

	<script src="${ctx}/assets/js/src/ace.settings.js"></script>
	<script src="${ctx}/assets/js/src/ace.settings-rtl.js"></script>
	<script src="${ctx}/assets/js/src/ace.settings-skin.js"></script>

	<!--
        	作者：1435738906@qq.com
        	时间：2017-09-25
        	描述：自定义的js
        -->
	<script src="${ctx }/js/bootstrap-tabs.js"></script>
	<script src="${ctx }/js/sidebar-menu.js"></script>
	<%-- <script src="${ctx }/js/scrollQ.js"></script> --%>

	<script type="text/javascript">
		$(function() {
			
			$('#Index iframe').attr('height', $(document).height() - 228);
			//				alert($(window).height()); //浏览器当前窗口可视区域高度958
			//				　　
			//				alert($(document).height()); //浏览器当前窗口文档的高度958
			//				　　
			//				alert($(document.body).height()); //浏览器当前窗口文档body的高度646
			//				　　
			//				alert($(document.body).outerHeight(true)); //浏览器当前窗口文档body的总高度 包括border padding margin
			//				　　
			//				alert($(window).width()); //浏览器当前窗口可视区域宽度
			//				　　
			//				alert($(document).width()); //浏览器当前窗口文档对象宽度
			//				　　
			//				alert($(document.body).width()); //浏览器当前窗口文档body的宽度
			//				　　
			//				alert($(document.body).outerWidth(true)); //浏览器当前窗口文档body的总宽度 包括border padding margin

			$('#menu').sidebarMenu({
				url : "${ctx}/getMenu",
				param : null
			});

		});
		
		//用户退出
		$('#Exit_system').on('click', function(){
	      layer.confirm('是否确定退出系统？', {
	     btn: ['是','否'] ,//按钮
		 icon:5,
	    }, 
		function(){
	    	location.href = "${ctx }/loginOut";
	    });
	});
	</script>

</body>

</html>