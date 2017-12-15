<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>提示页面</title>
<%@include file="../common/header.jsp"%>
<link rel="icon" type="image/ico" href="http://tattek.com/minimal/assets/images/favicon.ico" />
    <!-- Bootstrap -->
    <link href="${ctx }/js/plugins/assets/css/vendor/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/css/vendor/animate/animate.min.css">
    <link type="text/css" rel="stylesheet" media="all" href="${ctx }/js/plugins/assets/js/vendor/mmenu/css/jquery.mmenu.all.css" />
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/videobackground/css/jquery.videobackground.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/css/vendor/bootstrap-checkbox.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/rickshaw/css/rickshaw.min.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/morris/css/morris.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/tabdrop/css/tabdrop.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/summernote/css/summernote.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/summernote/css/summernote-bs3.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/chosen/css/chosen.min.css">
    <link rel="stylesheet" href="${ctx }/js/plugins/assets/js/vendor/chosen/css/chosen-bootstrap.css">
    <link href="${ctx }/js/plugins/assets/css/minimal.css" rel="stylesheet">
	<base target="_self">
</head>
	<body class="bg-1">
    <!-- Wrap all page content here -->
	    <div id="wrap">
	      <!-- Make page fluid -->
	      <div class="row">
	        <!-- Page content -->
	        <div id="content" class="col-md-12">
	          <!-- content main container -->
	          <div class="main">
	            <!-- 4 个 cards start -->
	            <div class="row cards">
	              <div class="card-container col-lg-3 col-sm-3 ">
	                <div class="card card-redbrown hover">
	                  <div class="front"> 
	                    <div class="media">        
	                      <span class="pull-left">
	                        <i class="fa fa-users media-object"></i>
	                      </span>
	                      <div class="media-body">
	                        <small>保养到期</small>
	                        <h2 id="baoyang" class="media-heading animate-number"  data-animation-duration="1500">0</h2>
	                      </div>
	                    </div> 
	
	                    <!-- <div class="progress-list">
	                      <div class="details">
	                        <div class="title">This month plan %</div>
	                      </div>
	                      <div class="status pull-right bg-transparent-black-1">
	                        <span class="animate-number" data-value="83" data-animation-duration="1500">0</span>%
	                      </div>
	                      <div class="clearfix"></div>
	                      <div class="progress progress-little progress-transparent-black">
	                        <div class="progress-bar animate-progress-bar" data-percentage="83%"></div>
	                      </div>
	                    </div> -->
	                  </div>
	                  <div class="back">
	                    <a href="#">
	                      <i class="fa fa-bar-chart-o fa-4x"></i>
	                      <span>Check Summary</span>
	                    </a>  
	                  </div>
	                </div>
	              </div>
	
	              <div class="card-container col-lg-3 col-sm-3 ">
	                <div class="card card-blue hover">
	                  <div class="front">        
	                    
	                    <div class="media">                  
	                      <span class="pull-left">
	                        <i class="fa fa-shopping-cart media-object"></i>
	                      </span>
	
	                      <div class="media-body">
	                        <small>年检到期</small>
	                        <h2 id="nianjian" class="media-heading animate-number"  data-animation-duration="1500">0</h2>
	                      </div>
	                    </div> 
	
	                    <!-- <div class="progress-list">
	                      <div class="details">
	                        <div class="title">This month plan %</div>
	                      </div>
	                      <div class="status pull-right bg-transparent-black-1">
	                        <span class="animate-number" data-value="100" data-animation-duration="1500">0</span>%
	                      </div>
	                      <div class="clearfix"></div>
	                      <div class="progress progress-little progress-transparent-black">
	                        <div class="progress-bar animate-progress-bar" data-percentage="100%"></div>
	                      </div>
	                    </div> -->
	
	                  </div>
	                  <div class="back">
	                    <a href="#">
	                      <i class="fa fa-bar-chart-o fa-4x"></i>
	                      <span>Check Summary</span>
	                    </a>
	                  </div>
	                </div>
	              </div>

	              <div class="card-container col-lg-3 col-sm-3 ">
	                <div class="card card-greensea hover">
	                  <div class="front">        
	                    
	                    <div class="media">
	                      <span class="pull-left">
	                        <i class="fa fa-usd media-object"></i>
	                      </span>
	
	                      <div class="media-body">
	                        <small>规费到期</small>
	                        <h2 id="guifei" class="media-heading animate-number"  data-animation-duration="1500">0</h2>
	                      </div>
	                    </div>
	
	                    <!-- <div class="progress-list">
	                      <div class="details">
	                        <div class="title">This month plan %</div>
	                      </div>
	                      <div class="status pull-right bg-transparent-black-1">
	                        <span class="animate-number" data-value="42" data-animation-duration="1500">0</span>%
	                      </div>
	                      <div class="clearfix"></div>
	                      <div class="progress progress-little progress-transparent-black">
	                        <div class="progress-bar animate-progress-bar" data-percentage="42%"></div>
	                      </div>
	                    </div> -->
	
	                  </div>
	                  <div class="back">
	                    <a href="#">
	                      <i class="fa fa-bar-chart-o fa-4x"></i>
	                      <span>Check Summary</span>
	                    </a>
	                  </div>
	                </div>
	              </div>

	              <div class="card-container col-lg-3 col-sm-3 col-xs-3">
	                <div class="card card-slategray hover">
	                  <div class="front"> 
	
	                    <div class="media">                   
	                      <span class="pull-left">
	                        <i class="fa fa-eye media-object"></i>
	                      </span>
	
	                      <div class="media-body">
	                        <small>保险到期</small>
	                        <h2 id="baoxian" class="media-heading animate-number"  data-animation-duration="1500">0</h2>
	                      </div>
	                    </div> 
	
	                   <!--  <div class="progress-list">
	                      <div class="details">
	                        <div class="title">This month plan %</div>
	                      </div>
	                      <div class="status pull-right bg-transparent-black-1">
	                        <span class="animate-number" data-value="25" data-animation-duration="1500">0</span>%
	                      </div>
	                      <div class="clearfix"></div>
	                      <div class="progress progress-little progress-transparent-black">
	                        <div class="progress-bar animate-progress-bar" data-percentage="25%"></div>
	                      </div>
	                    </div> -->
	
	                  </div>
	                  <div class="back">
	                    <a href="#">
	                      <i class="fa fa-bar-chart-o fa-4x"></i>
	                      <span>Check Summary</span>
	                    </a>
	                  </div>
	                </div>
	              </div>
	
	            </div>
	            <!-- 4 个 cards end -->
	            <!-- row  start-->
	            <div class="row">
	              <!-- col 12 -->
	              <div class="col-md-12">
	              
					<div class="col-md-6">
					 <!-- 排行榜 -->
					    <section class="tile transparent recent-activity">
			                  <!-- tile header -->
			                  <div class="tile-header color transparent-black textured rounded-top-corners">
			                    <h1><strong>排行榜</strong> </h1>
			                    <div class="controls">
			                      <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
			                      <a href="#" class="remove"><i class="fa fa-times"></i></a>
			                    </div>
			                  </div>
			                  <!-- /tile header -->
			
			
			                  
			                  <!-- tile widget -->
			                  <div class="tile-widget color transparent-black rounded-top-corners nopadding">
			                    <!-- Nav tabs -->
			                    <ul class="nav nav-tabs tabdrop">
			                      <li class="active"><a href="#users-tab" data-toggle="tab">Users</a></li>
			                      <li><a href="#orders-tab" data-toggle="tab">Orders</a></li>
			                      <li><a href="#messages-tab" data-toggle="tab">Messages</a></li>
			                      <li><a href="#tasks-tab" data-toggle="tab">Tasks</a></li>
			                      <li><a href="#comments-tab" data-toggle="tab">Comments</a></li>
			                    </ul>
			                    <!-- / Nav tabs -->
			                  </div>
			                  <!-- /tile widget -->
			
			                  <!-- tile body -->
			                  <div class="tile-body tab-content nopadding rounded-bottom-corners">
			                    <!-- Tab panes -->
			                    
			                    <ul id="users-tab" class="tab-pane fade in active" >
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">has been <strong>unbanned</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 15 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">has been <strong>unbanned</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 1 hour ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">has been <strong>banned</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 2 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/ici-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Ing. Imrich <strong>Kamarel</strong></span>
			                        <span class="subject">has been <strong>updated</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 4 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">has been <strong>registered</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 8 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">has been <strong>banned</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 12 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">has been <strong>registered</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> yesterday</span>
			                      </li>
			                    </ul>
			
			                    <ul id="orders-tab" class="tab-pane fade">
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">send new order <strong>OR_00012014</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 15 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/george-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">George <strong>McCain</strong></span>
			                        <span class="subject">change order <strong>OR_02172013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 27 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/george-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">George <strong>McCain</strong></span>
			                        <span class="subject">send new order <strong>OR_02172013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 35 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">cancelled order <strong>OR_02162013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 2 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">send new order <strong>OR_02162013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 3 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/ici-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Ing. Imrich <strong>Kamarel</strong></span>
			                        <span class="subject">send new order <strong>OR_02152013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 5 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/ici-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Ing. Imrich <strong>Kamarel</strong></span>
			                        <span class="subject">send new order <strong>OR_02142013</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 5 hours ago</span>
			                      </li>
			                    </ul>
			
			                    <ul id="messages-tab" class="tab-pane fade">
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Peter Kay</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 15 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Peter Kay</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 30 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Ing. Imrich Kamarel</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 35 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Arnold Arnold</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 42 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>George McCain</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 1 hour ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Ing. Imrich Kamarel</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 1 hour ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">received new message from <strong>Ing. Imrich Kamarel</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 1 hour ago</span>
			                      </li>
			                    </ul>
			
			                    <ul id="tasks-tab" class="tab-pane fade">
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">cancelled task <strong>Get drunk</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 15 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">finished task <strong>Gifts!</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 59 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">finished task <strong>Make backup</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 3 hours ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">have a new task <strong>Gifts!</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> yesterday</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">finished task <strong>Send emails</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> yesterday</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">have a new task <strong>Send emails</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> yesterday</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab"><strong>You</strong></span>
			                        <span class="subject">have a new task <strong>Make backup</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> yesterday</span>
			                      </li>
			                    </ul>
			
			                    <ul id="comments-tab" class="tab-pane fade">
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">deleted comment <strong>CM_00019</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 5 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Arnold <strong>Karlsberg</strong></span>
			                        <span class="subject">posted a new comment <strong>CM_00019</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 8 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab">John <strong>Douey</strong></span>
			                        <span class="subject">posted a new comment <strong>CM_00018</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 9 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/peter-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Peter <strong>Kay</strong></span>
			                        <span class="subject">posted a new comment <strong>CM_00017</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 18 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Ing. Imrich <strong>Kamarel</strong></span>
			                        <span class="subject">updated comment <strong>CM_00016</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 25 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/arnold-avatar.jpg" class="w35" alt>
			                        <span class="user font-slab">Ing. Imrich <strong>Kamarel</strong></span>
			                        <span class="subject">posted a new comment <strong>CM_00016</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 42 minutes ago</span>
			                      </li>
			                      <li>
			                        <img src="assets/images/profile-photo.jpg" class="w35" alt>
			                        <span class="user font-slab">John <strong>Douey</strong></span>
			                        <span class="subject">posted a new comment <strong>CM_00015</strong></span>
			                        <span class="time"><i class="fa fa-clock-o"></i> 56 minutes ago</span>
			                      </li>
			                    </ul>
			
			                    <!-- / Tab panes -->
			                  </div>
			                  <!-- /tile body -->
			                
			
			
			                </section>
			                <!-- /tile -->
					 
					 
							<!-- 折线图 -->
			                <!-- tile -->
			                <!-- <section class="tile transparent">
			                  tile header
			                  <div class="tile-header color transparent-black textured rounded-top-corners">
			                    <h1><strong>Statistic</strong> Chart</h1>
			                    <div class="controls">
			                      <a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
			                      <a href="#" class="remove"><i class="fa fa-times"></i></a>
			                    </div>
			                  </div>
			                  /tile header
			                  tile widget
			                  <div class="tile-widget color transparent-black textured">
			                    <div id="statistics-chart" class="chart statistics" style="height: 285px;"></div>
			                  </div>
			                  /tile widget
			                </section> -->
			                <!-- /tile -->
	                </div>
	
	              <!-- 饼状图 -->
				    <div class="col-md-6">
	                <!-- tile -->
		              <section class="tile color transparent-black">
		                  <!-- tile header -->
		                  <div class="tile-header">
		                    <h1><strong>车辆年龄</strong> 情况</h1>
		                    <div class="controls">
		                      <a href="#" class="refresh"><i id="refresh" class="fa fa-refresh"></i></a>
		                      <a href="#" class="remove"><i class="fa fa-times"></i></a>
		                    </div>
		                  </div>
		                  <!-- /tile header -->
		
		                  <!-- tile body -->
		                  <div class="tile-body">
		                    <div id="browser-usage" style="height: 230px;" class="morris-chart"></div>
		                    <ul class="inline text-center chart-legend">
		                      <li><span class="badge badge-outline" style="border-color: #00a3d8"></span> Chrome <small>25%</small>,</li>
		                      <li><span class="badge badge-outline" style="border-color: #1693A5"></span> Other <small>25%</small>,</li>
		                      <li><span class="badge badge-outline" style="border-color: #2fbbe8"></span> Safari <small>20%</small>,</li>
		                      <li><span class="badge badge-outline" style="border-color: #72cae7"></span> Firefox <small>15%</small>,</li>
		                      <li><span class="badge badge-outline" style="border-color: #ffc100"></span> Internet Explorer <small>10%</small>,</li>
		                      <li><span class="badge badge-outline" style="border-color: #d9544f"></span> Opera <small>5%</small></li>
		                    </ul>
		                  </div>
		                  <!-- /tile body --> 
		
		                </section>
	                <!-- /tile -->
	
					</div>
	              </div>
	              <!-- /col 12 -->
	            </div>
	            <!-- /row end-->
	           

	          </div>
	          <!-- /content container -->
	        </div>
	        <!-- Page content end -->
	      </div>
	      <!-- Make page fluid end-->
	    </div>
	<!-- Wrap all page content end -->
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- <script src="assets/js/jquery.js"></script> -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx }/js/plugins/assets/js/vendor/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/mmenu/js/jquery.mmenu.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/nicescroll/jquery.nicescroll.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/animate-numbers/jquery.animateNumbers.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/videobackground/jquery.videobackground.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugins/assets/js/vendor/blockui/jquery.blockUI.js"></script>

    <script src="${ctx }/js/plugins/assets/js/vendor/flot/jquery.flot.min.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/flot/jquery.flot.time.min.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/flot/jquery.flot.selection.min.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/flot/jquery.flot.animator.min.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/flot/jquery.flot.orderBars.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>

    <%-- <script src="${ctx }/js/plugins/assets/js/vendor/rickshaw/raphael-min.js"></script> --%> 
    <script src="${ctx }/js/plugins/assets/js/vendor/rickshaw/d3.v2.js"></script>
    <script src="${ctx }/js/plugins/assets/js/vendor/rickshaw/rickshaw.min.js"></script>

    <script src="${ctx }/js/plugins/assets/js/vendor/morris/morris.min.js"></script>

    <script src="${ctx }/js/plugins/assets/js/vendor/tabdrop/bootstrap-tabdrop.min.js"></script>

    <script src="${ctx }/js/plugins/assets/js/vendor/summernote/summernote.min.js"></script>

    <script src="${ctx }/js/plugins/assets/js/vendor/chosen/chosen.jquery.min.js"></script>

    <script src="${ctx }/js/plugins/assets/js/minimal.min.js"></script>

    <script>
    $(function(){
    	// 4cards
    	show();
    	
    	showNum();
      // Initialize card flip
      $('.card.hover').hover(function(){
        $(this).addClass('flip');
      },function(){
        $(this).removeClass('flip');
      });

     /*  // Initialize 折线图数据
      var d1 =[ [1, 715],
            [2, 985],
            [3, 1525],
            [4, 1254],
            [5, 1861],
            [6, 2621],
            [7, 1987],
            [8, 2136],
            [9, 2364],
            [10, 2851],
            [11, 1546],
            [12, 2541]
      ];
      var d2 =[ [1, 463],
                [2, 578],
                [3, 327],
                [4, 984],
                [5, 1268],
                [6, 1684],
                [7, 1425],
                [8, 1233],
                [9, 1354],
                [10, 1200],
                [11, 1260],
                [12, 1320]
      ];
      var months = ["January", "February", "March", "April", "May", "Juny", "July", "August", "September", "October", "November", "December"];

      // 折线图生成
      var plot = $.plotAnimator($("#statistics-chart"), 
        [
          {
            label: 'Sales', 
            data: d1,    
            lines: {lineWidth:3}, 
            shadowSize:0,
            color: '#ffffff'
          },
          { label: "Visits",
            data: d2, 
            animator: {steps: 99, duration: 500, start:200, direction: "right"},   
            lines: {        
              fill: .15,
              lineWidth: 0
            },
            color:['#ffffff']
          },{
            label: 'Sales',
            data: d1, 
            points: { show: true, fill: true, radius:6,fillColor:"rgba(0,0,0,.5)",lineWidth:2 }, 
            color: '#fff',        
            shadowSize:0
          },
          { label: "Visits",
            data: d2, 
            points: { show: true, fill: true, radius:6,fillColor:"rgba(255,255,255,.2)",lineWidth:2 }, 
            color: '#fff',        
            shadowSize:0
          }
        ],{ 
        
        xaxis: {

          tickLength: 0,
          tickDecimals: 0,
          min:1,
          ticks: [[1,"JAN"], [2, "FEB"], [3, "MAR"], [4, "APR"], [5, "MAY"], [6, "JUN"], [7, "JUL"], [8, "AUG"], [9, "SEP"], [10, "OCT"], [11, "NOV"], [12, "DEC"]],

          font :{
            lineHeight: 24,
            weight: "300",
            color: "#ffffff",
            size: 14
          }
        },
        
        yaxis: {
          ticks: 4,
          tickDecimals: 0,
          tickColor: "rgba(255,255,255,.3)",

          font :{
            lineHeight: 13,
            weight: "300",
            color: "#ffffff"
          }
        },
        
        grid: {
          borderWidth: {
            top: 0,
            right: 0,
            bottom: 1,
            left: 1
          },
          borderColor: 'rgba(255,255,255,.3)',
          margin:0,
          minBorderMargin:0,              
          labelMargin:20,
          hoverable: true,
          clickable: true,
          mouseActiveRadius:6
        },
        
        legend: { show: false}
      });

      $(window).resize(function() {
        // redraw the graph in the correctly sized div
        plot.resize();
        plot.setupGrid();
        plot.draw();
      });

      $('#mmenu').on(
        "opened.mm",
        function()
        {
          // redraw the graph in the correctly sized div
          plot.resize();
          plot.setupGrid();
          plot.draw();
        }
      );

      $('#mmenu').on(
        "closed.mm",
        function()
        {
          // redraw the graph in the correctly sized div
          plot.resize();
          plot.setupGrid();
          plot.draw();
        }
      );

      // tooltips showing
      $("#statistics-chart").bind("plothover", function (event, pos, item) {
        if (item) {
          var x = item.datapoint[0],
              y = item.datapoint[1];

          $("#tooltip").html('<h1 style="color: #418bca">' + months[x - 1] + '</h1>' + '<strong>' + y + '</strong>' + ' ' + item.series.label)
            .css({top: item.pageY-30, left: item.pageX+5})
            .fadeIn(200);
        } else {
          $("#tooltip").hide();
        }
      });

      
      //tooltips options
      $("<div id='tooltip'></div>").css({
        position: "absolute",
        //display: "none",
        padding: "10px 20px",
        "background-color": "#ffffff",
        "z-index":"99999"
      }).appendTo("body"); */

      //获取车辆年龄情况
      $('.pie-chart').easyPieChart();
	  var data1="";
      function show(){
    	   $.ajax({
    		  type:"post",    		 
    		  dataType: "json",
    		  url: "${ctx}/common/getcarAge",
              success: function(data) {
            	  console.log(data);
            	  Morris.Donut({
            	        element: 'browser-usage',
            	        data: data,
            	        colors: ['#00a3d8', '#2fbbe8', '#72cae7', '#d9544f', '#ffc100', '#1693A5']
            	      });
            	  $('#browser-usage').find("path[stroke='#ffffff']").attr('stroke', 'rgba(0,0,0,0)');
            	  var li = "";
            	  $.each(data,function(i,data1){
            		  
            	      li += "<li><span class='badge badge-outline' style='border-color: #00a3d8'></span> "+data1.label+" <small>"+data1.value+"%</small>,</li>";
            	  }); 
            	  $(".inline").html(li);
              }  
    	  });
      }

      // 获取到期列表
      function showNum(){
     	 $.ajax({
	    		  type:"post",    		 
	    		  dataType: "json",
	    		  url: "${ctx}/common/getDailySum",
	              success: function(data) {
	            	  $.each(data,function(i,data1){
	            		  if(data1.label=="保养到期"){
	            			  $("#baoyang").html(data1.value);
	            		  }else if(data1.label=="规费到期"){
	            			 $("#guifei").html(data1.value);
	            		  }else if(data1.label=="保险到期"){
	            			 $("#baoxian").html(data1.value);
	            		  }else if(data1.label=="年检到期"){
	           			 	$("#nianjian").html(data1.value);
	           		  	  }
	            	  }); 
	              }
    		  
    	  		});
     	  };
      
    });
      
    </script>
  </body>
</html>
