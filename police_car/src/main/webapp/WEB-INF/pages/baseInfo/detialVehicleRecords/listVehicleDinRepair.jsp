<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆定(程)维护记录</title>
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<p>
					<sp:permission link="/VehicleDinRepair/addVehicleDinRepair" reverse="false">
						<button id="btnAdd" class="btn btn-1 " onclick="addVehicleDinRepair()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
					</sp:permission>
					<sp:permission link="/VehicleDinRepair/editVehicleDinRepair" reverse="false">
						<button id="btnEdit"  class="btn btn-2" onclick="editVehicleDinRepair()" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
					</sp:permission>
					<sp:permission link="/VehicleDinRepair/deleteVehicleDinRepair" reverse="false">
				        <button id="btnDelete" class="btn btn-3" onclick="deleteVehicleDinRepair()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
				    </sp:permission>
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
	//年选择器
	laydate.render({
	  elem: '#serarch',
	  type: 'year',
	  done: function(value, date){
		    alert('你选择的日期是：' + value + '\n获得的对象是' + JSON.stringify(date));
		  }
	});
	$.loadProjectattr($("#keyCode"));
	$("#export").click(function() {
		utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
	});
	$("#return").click(function() {
		window.history.back();
	})
	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list").jqGrid({
			url : "${ctx}/vehicleRecord/getListVehicleRecord",
			mtype : "post",
			postData : {},
			ignoreCount : true,
			pginput : true,
			multiselect : true,
			autowidth : true,
			viewrecords : false,
			colNames : [ "", "时间", "维护类别", "验收人", "验收时间" ],
			sortname : 'licenseno',
			colModel : [ {
				name : "id",
				hidden : true
			}, {
				name : "licenseno",
				width : 50
			}, {
				name : "licenseno"
			}, {
				name : "typeName"
			}, {
				name : "modelName"
			} ]
		});
	});

	/* 新增 */
	function addVehicleDinRepair() {
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '<h4>新增车辆定程维护记录</h4>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '80%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/editVehicleDinRepair?ID=0',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	//修改
	function editVehicleDinRepair() {
		var rowId = $("#table_list").jqGrid("getGridParam", "selarrrow");
		if (rowId.length > 1 || rowId == 0) {
			alert("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData", rowId);
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '修改车辆信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '80%', '80%' ],
			content : '${ctx}/vehicleRecord/editVehicleRecords?ID='
					+ rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}

	//删除
	function deleteVehicleDinRepair() {
		var rowIds = $("#table_list").jqGrid("getGridParam", "selarrrow");
		if (rowIds.length < 1) {
			layer.msg('请选择行数', {
				icon : 2
			});
			return;
		}
		var rowData, ids = new Array();
		for (var i = 0; i < rowIds.length; i++) {
			rowData = $("#table_list").jqGrid("getRowData", rowIds[i]);
			ids[i] = rowData.id;
		}
		ids = ids.toString();
		var confirm = layer.confirm('确认要删除该车辆信息吗？', {
			btn : [ '确定', '取消' ]
		}, function() {
			layer.close(confirm);
			var index = layer.load(1);
			var data = {
				"vehicleID" : ids
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/vehicleRecord/deleteVehicleRecords",
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