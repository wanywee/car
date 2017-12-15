<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆登记表</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
<style type="text/css">
ul{
	display: block;
	list-style:none;
	padding-left:6px;
	z-index: 9999;
}
</style>
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<!-- <div id="titleReport" style="text-align:center" >车辆登记表</div> -->
					<div id="hideNoNeed">
						<p>
					    <sp:permission link="/vehicleRecords/printVehicleRecord" reverse="false">
					        <button id="btnPrint" class="btn btn-10 " onclick="printVehicleRecord()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
					    </sp:permission>
					    	 <button id="btnExport" class="btn btn-10 " onclick="exportCarReport()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;导出</button>
							<button class="btn btn-0 "
								style="float: right; margin-left: 10px;" type="button"
								id="serchBtn" onclick="search()">
								<i class="icon iconfont icon-search"></i>&nbsp;搜索
							</button>
							<input placeholder="搜索" style="float: right" type="text"
								name="appID" id="serarchString" class="search_control">
						</p>
					</div>
					<div class="jqGrid_wrapper">
						<table id="table_list"></table>
						<div id="pager_list"></div>
					</div>
					<div>
						<input id="searchStr" type="text" style="display:none">
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
	});
	$("#reset_button").click(function(e) {
		
	});
	function exportCarReport(){
		var pagenum = $(".ui-pg-selbox").val();
		var confirm = layer.confirm('您确定要导出车辆登记报表么?', {
			offset : '200px',
			btn : [ '确定', '取消' ],
			fix : false
		}, function() {
			var searchStr = $("#serarchString").val();
			$("#searchStr").val(searchStr);
		    var data={"pagenum":pagenum,"searchStr":$("#searchStr").val()};
		    window.location="${ctx}/reportCenter/exportCarReport?pagenum="+pagenum+"&searchStr="+$("#searchStr").val();
			layer.close(confirm);
		});
	}
	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list").jqGrid(
				{
					url : "${ctx}/reportCenter/getReportsCarCheck",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					autowidth : true,
					viewsortcols : [false, 'vertical', true],
					ignoreCount:false,
					colNames : [ "部门","车牌号", "车辆类型","厂牌型号","购进日期","排量","厂家","购置价格","座位数", "状态"],
					sortname : 'id',
					colModel : [{
						name : "deptName",
						cellattr: addCellAttr
					},{
						name : "licenseno",
						sortable : true,
						cellattr: addCellAttr
					}, {
						name : "typeName",
						cellattr: addCellAttr
					}, {
						name : "modelName",
						cellattr: addCellAttr
					}, /* {
						name : "brandName",
						cellattr: addCellAttr
					}, {
						name : "engineno",
						cellattr: addCellAttr
					}, {
						name : "chassisno",
						cellattr: addCellAttr
					}, */
					{
						name : "buyTime",

						cellattr: addCellAttr,
		                formatter:function(value, options, row){
		                    	  return setColor("blue",utils.getTimestampToYmd(value));
		                }

					}, {
						name : "consumption",
						cellattr: addCellAttr
					}, {
						name : "supplyName",
						cellattr: addCellAttr
					}, {
						name : "buyPrice",
						cellattr: addCellAttr
					}, {
						name : "seats",
						sortable : true,
						cellattr: addCellAttr
					}, {
						name : "nowStatus",
						sortable : true,
						cellattr: addCellAttr,
						formatter : function(value, options, row) {
							if(row.nowStatus == 1) {
								return "可用";
							}
							if(row.nowStatus == 2) {
								return "出车";
							}
							if(row.nowStatus == 3) {
								return "维修";
							}
							if(row.nowStatus == 4) {
								return "其他";
							}
							if(row.nowStatus == 5) {
								return "申请中";
							}
						}
					} ]
			});
	});

	
	/* 表格列字体颜色 */
	function addCellAttr(rowId, val, rawObject, cm, rdata) {
		if (rawObject.status == 2) {
			return "style='color:red'";
		}
	}

	//表格全局搜索
	function search() {
		var searchStr = $("#serarchString").val();
		$("#searchStr").val(searchStr);
		$("#table_list").jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				'searchStr' : searchStr
			}, //发送数据 
			page : 1
		}).trigger("reloadGrid"); //重新载入 
	}
	function printVehicleRecord(){
		$("#hideNoNeed").hide();
		$("#pager_list").hide(); 
		window.print();
		$("#hideNoNeed").show();
		$("#pager_list").show(); 
	} 
</script>
</body>
</html>