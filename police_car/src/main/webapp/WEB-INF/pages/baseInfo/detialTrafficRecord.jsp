<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>交通事故、违规记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm">
					</form>
				</div>
				<div class="ibox-content">
						<input type="text" id="driverID" style="display: none"
							name="driverID" value="${driverID}">
					<div class="jqGrid_wrapper">
						<table id="table_list"></table>
						<div id="pager_list"></div>
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
	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list").jqGrid(
				{
					url : "${ctx}/baseInfo/getListTrafficRecord?driverID="
							+ $("#driverID").val(),
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewrecords : false,
					colNames : ["时间", "情况概述","处理情况", "备注" ],
					sortname : 'id',
					colModel : [ {
						name : "time",
						formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					}, {
						name : "context",
					}, {
						name : "conditions",
					}, {
						name : "comments",
					} ]
				});
	});
</script>
</body>
</html>