<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>报表中心</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<style type="text/css">
p {
	width:70px;
	height:70px;
	text-align:center;
}
img {
	margin-left:10px;
}
img:hover{
	background-color:#cdcdcd;
	border-radius:5px;
	width:60px;
	height:60px;
}
</style>
<body>
<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm"></form>
				</div>
				<div class="ibox-content" style="height:500px;">
				<div class="row" style="height:100px;">
					<div class="col-sm-2">
					    <a target="_blank"><img src="${ctx}/img/report2.png" height="50" width="50" /></a>
	  					<p><a target="_blank">油料报表</a></p>
					</div>					
   					<div class="col-sm-2" id="carRes">
					    <a  target="_blank"><img src="${ctx}/img/report.png" height="50" width="50" /></a>
	  					<p><a  target="_blank">车辆登记表</a></p>
					</div>
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report10.png" height="50" width="50" /></a>
	  					<p><a  target="_blank">车辆月度保养计划落实表</a></p>
					</div>
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report9.png" height="50" width="50" /></a>
	  					<p><a target="_blank">单车管理档案</a></p>
					</div>
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report3.png" height="50" width="50" /></a>
	  					<p><a target="_blank">单车油料消耗表</a></p>
					</div>
					<div class="col-sm-2">
					   
					</div>
				</div>
				<div></div>
				<div class="row" style="margin-top:30px;">
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report5.png" height="50" width="50" /></a>
	  					<p><a target="_blank">驾驶员管理档案</a></p>
					</div>
					
   					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report6.png" height="50" width="50" /></a>
	  					<p><a target="_blank">专职驾驶员登记表</a></p>
					</div>
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report1.png" height="50" width="50" /></a>
	  					<p><a target="_blank">兼职驾驶员登记表</a></p>
					</div>
					<div class="col-sm-2">
					    <a  target="_blank"><img src="${ctx}/img/report8.png" height="50" width="50" /></a>
	  					<p><a target="_blank">兼职驾驶员配备情况</a></p>
					</div>
					<div class="col-sm-2">
					    <a target="_blank"><img src="${ctx}/img/report7.png" height="50" width="50" /></a>
	  					<p><a target="_blank">危险驾驶记录</a></p>
					</div>
					<div class="col-sm-2">
					   
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$.loadProjectattr($("#keyCode"));
	$("#export").click(function() {
		utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
	});
	$("#return").click(function() {
		window.history.back();
	})
	$("#carRes").click(function(){
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '车辆登记表',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '95%' ],
			content : '${ctx}/reportCenter/reportsCarCheck',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	});
</script>
</body>
</html>