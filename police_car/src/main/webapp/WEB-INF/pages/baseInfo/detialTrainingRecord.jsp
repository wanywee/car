<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>上岗、转岗培训记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
<style type="text/css">
.ui-jqgrid .ui-jqgrid-view {
	    position: relative;
	    left: 0;
	    top: 0;
	    padding: 0;
	    width: "102%";
    }
</style>
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm">
						<%-- <div class="search_group">
		    <label>门店</label>
		    <select class="validate[required]" id="keyCode" select2=""
					name="empID" data-type="12" data-value="12"
					data-url="${ctx}/getListKeyCode?kind=KEY_ALIPAY_CONFIG" style="width: 163px"></select>
		</div>
		<div class="search_group">
		   <label>支付宝门店ID</label>
		   <input type="text" name="appID" class="search_control">
		</div>
		<div class="search_group">
			<button class="btn btn-0 " type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
		</div> --%>
					</form>
				</div>
				<div class="ibox-content">
							<input type="text" id="driverID"  style="display: none" value="${driverID}">
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
					url : "${ctx}/baseInfo/getListWorkOnRecord?driverID="
							+ $("#driverID").val(),
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewrecords : false,
					colNames : ["时间", "培训内容", "备注" ],
					sortname : 'id',
					colModel : [ {
						name : "traniningTime",
						formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					}, {
						name : "content",
					}, {
						name : "comments",
					} ]
				});
	});
</script>
</body>
</html>