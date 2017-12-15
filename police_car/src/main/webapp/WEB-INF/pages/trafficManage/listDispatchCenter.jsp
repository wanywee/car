<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>调度中心</title>
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
					<p>
					<sp:permission link="/vehicleRecords/addVehicleRecord" reverse="false">
						<button id="btnAdd" class="btn btn-1 " onclick="dispatchRatify()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;派车</button>
					</sp:permission>
					<sp:permission link="/addoilRecord/deleteAddoilRecord" reverse="false">  
	        <button id="btnDelete" class="btn btn-3" onclick="deleteAddoilRecord()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;拒绝</button>
		</sp:permission>	
						<button class="btn btn-0 "
							style="float: right; margin-left: 10px;" type="button"
							id="serchBtn" onclick="search()">
							<i class="icon iconfont icon-search"></i>&nbsp;搜索
						</button>
						<input placeholder="搜索" style="float: right" type="text"
							name="appID" id="serarchStr" class="search_control">
					</p>

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
	$.loadProjectattr($("#deptName"));
	$.loadProjectattr($("#dreiver"));
	$("#export").click(function() {
		utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
	});
	$("#return").click(function() {
		window.history.back();
	});

	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list").jqGrid(
				{
					url : "${ctx}/dispatchCenter/getListDispatchCenter",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					multiboxonly:true,
					viewsortcols : [ false, 'vertical', true ],
					ignoreCount : false,
					colNames : [ "", "", "警号", "申请人", "所属单位", "出车时间", "预计回车时间", "出车原因","目的地"],
					sortname : 'id',
					colModel : [ {
						name : "carID",
						hidden : true
					}, {
						name : "id",
						hidden : true
					}, {
						name : "stffNo",
					}, {
						name : "caruser",
						width:100,
					}, {
						name : "vehicleDept",
						width:100,
					}, {
						name : "outcarTime",
						 formatter:function(value, options, row){
	                      	  return setColor("blue",utils.gettimestamp(value));
	                       }
					}, {
						name : "estimateReturnTime",
						 formatter:function(value, options, row){
	                      	  return setColor("blue",utils.gettimestamp(value));
	                       }
					}, {
						name : "outCause",
						width:100,
					}, {
						name : "desitination",
						width:100,
					}]
				});
	});

	
	// 派车
	
	function dispatchRatify() {
		var rowId = $("#table_list").jqGrid("getGridParam", "selarrrow");
		if (rowId.length > 1 || rowId == 0) {
			layer.msg("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData", rowId);
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '批准用车申请',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '60%', '80%' ],
			content : '${ctx}/dispatchCenter/toDispatchRatify?id=' + rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}

	//表格全局搜索
	function search() {
		var searchStr = $("#serarchStr").val();
		$("#table_list").jqGrid('setGridParam', {
			datatype : 'json',
			postData : {
				'searchStr' : searchStr
			}, //发送数据 
			page : 1
		}).trigger("reloadGrid"); //重新载入 

	}

	// 拒绝出车申请
	function deleteAddoilRecord() {
		var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
		if (ids.length < 1) {
			alert("请选择需要拒绝出车申请的信息!");
			return;
		}

		var confirm = layer.confirm('确定拒绝选中的用车申请吗?', {
			offset : '200px',
			btn : [ '确定', '取消' ]
		}, function() {
			layer.close(confirm);
			var index = layer.load(1);
			var data = {
				"ids" : ids
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/dispatchCenter/dispatchTurnDown",
				data : data,
				success : function(result) {
					layer.close(index);
					layer.msg(result.message, {
						icon : 1
					});
					jqGrid.trigger("reloadGrid");
				},
				error : function(result, XMLHttpRequest, textStatus,
						errorThrown) {
					console.log(result)
					layer.msg(result.message, {
						icon : 2
					});
					layer.close(index);
				},
			});
		}, function() {
		});

	}
</script>
</body>
</html>