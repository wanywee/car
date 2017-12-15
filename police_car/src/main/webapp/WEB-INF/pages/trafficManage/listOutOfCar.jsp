<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车务管理</title>
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
						<button id="btnAdd" class="btn btn-1 " onclick="addOutOfCar()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;出车</button>
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
					url : "${ctx}/outOfCar/getListVehicleRecords",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewsortcols : [ false, 'vertical', true ],
					ignoreCount : false,
					colNames : [ "", "", "车牌号", "车辆类型", "车辆型号", "载人(个)", "座位数",
							"状态", "所属", ""],
					sortname : 'id',
					colModel : [ {
						name : "id",
						hidden : true
					}, {
						name : "opt",
						width : 50,
						align : 'center',
						formatter : details
					}, {
						name : "licenseno",
						sortable : true,
						cellattr : addCellAttr
					}, {
						name : "typeName",
						cellattr : addCellAttr
					}, {
						name : "modelName",
						cellattr : addCellAttr
					}, {
						name : "loading",
						sortable : true,
						cellattr : addCellAttr
					}, {
						name : "seats",
						sortable : true,
						cellattr : addCellAttr
					}, {
						name : "nowStatus",
						sortable : true,
						cellattr : addCellAttr,
						formatter : function(value, options, row) {
							return row.displayStatus;
						}
					}, {
						name : "deptName",
						cellattr : addCellAttr,
					}, {
						name : "deptID",
						hidden : true
					} ],
					loadComplete: function() {
						/* var re_records = $("#table_list").jqGrid('getGridParam', 'records'); //获取数据总条数  
						if(re_records == 0) {
							layer.confirm('你是否需要查询其他单位车辆？', {
								  btn: ['是','不用，谢谢'] //按钮
								}, function(){
									layer.msg('正在请求...',{time:500});
									// 从新设置请求地址，加载数据
									// jqGrid
									$("#table_list").setGridParam( //G,P要大写
										    {
										    url:"${ctx}/outOfCar/getListVehicleRecords",
										    postData:{"All":"yes"},
										    loadComplete: function() {
										    	var re_records = $("#table_list").jqGrid('getGridParam', 'records');
										    	if(re_records == 0) {
										    		layer.alert("暂无数据...");
										    		return null;
										    	}
										    }
										    }
										) .trigger("reloadGrid");
								}, function(){
								  layer.msg('也可以打电话给相关部门', {
								    time: 5000, //20s后自动关闭
								    btn: ['明白了']
								  });
								});
							
						} */
					}
				});
	});

	/* 是否停用的改变 */
	function isUse(id) {
		var data;
		if ($('#status' + id).is(':checked')) {
			data = {
				ID : id,
				status : 2
			};
		} else {
			data = {
				ID : id,
				status : 1
			};
		}
		$.ajax({
			type : 'post',
			datatype : 'text',
			url : '${ctx}/vehicleRecord/isUseVehicleRecords',
			data : data,
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function() {
				jqGrid.trigger("reloadGrid");
			},
			error : function(data) {
				layer.msg(data.message);
			}
		});
		//jqGrid.trigger("reloadGrid");
	}

	/* 表格列字体颜色 */
	function addCellAttr(rowId, val, rawObject, cm, rdata) {
		if (rawObject.status == 2) {
			return "style='color:red'";
		}
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

	var layerHtml = "";

	/* 出车 */
	function addOutOfCar() {
		var rowId=$("#table_list").jqGrid("getGridParam","selarrrow");
		if(rowId.length > 1 || rowId == 0) {
			layer.msg("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData",rowId);
		console.log(rowData);
		console.log(rowData.id);
		console.log(rowData.deptID);
		layer.open({
			type : 2,
			title : false,
			area: ['500px','450px'],
			content : '${ctx}/outOfCar/toAddOutOfCar?carId=' + rowData.id + '&deptID=' + rowData.deptID,
			end : function(index) {
				 $("#table_list").trigger("reloadGrid");
				//jqGrid.trigger("table_list");
			}
		});
		/* index = layer.open({
			type : 1,
			closeBtn : 0,
			scrollbar : false,
			title : '新增车辆信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '60%', '80%' ],
			content : '这是弹出框',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		}); */
	}
	//修改
	function editVehicleRecord() {
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
			area : [ '60%', '80%' ],
			content : '${ctx}/vehicleRecord/editVehicleRecords?ID='
					+ rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}

	//删除
	function deleteVehicleRecord() {
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
			offset : '200px',
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

	/* 显示详情 */
	function detailVehicleRecord(e, ID) {
		var tipContext = "<ul>"
				+ "<li><a href='#' onclick='showVehicleRecord(\"" + ID
				+ "\")'>基本信息</a></li>"
				+ "<li><a href='#' onclick='showVehicleGross(\"" + ID
				+ "\")'>行驶公里、维修费、燃油耗量</a></li>"
				+ "<li><a href='#' onclick='showVehicleRepair(\"" + ID
				+ "\")'>车辆维修记录</a></li>"
				+ "<li><a href='#' onclick='showVehicleDinRepair(\"" + ID
				+ "\")'>车辆定(程)维修记录</a></li>"
				+ "<li><a href='#' onclick='showVehicleAccident(\"" + ID
				+ "\")'>车辆事故记录</a></li>"
				+ "<li><a href='#' onclick='showVehicleChange(\"" + ID
				+ "\")'>车辆变更记录</a></li></ul>";
		layer.tips(tipContext, '#eye' + ID, {
			tips : [ 2, '#f0f5f5' ],
			area : [ '200px', 'auto' ],
			time : 5000
		});
		utils.stopBubbling(e);
	}

	/* 显示车辆基本信息 */
	function showVehicleRecord(id) {
		index = layer
				.open({
					type : 2,
					scrollbar : true,
					title : '<h3>车辆基本信息</h3>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '60%', '80%' ],
					content : '${ctx}/vehicleRecord/editVehicleRecords?show=showVehicle&ID='
							+ id,
					end : function(index) {
						//jqGrid.trigger("reloadGrid");
					}
				});
	}

	/* 行驶公里、维修费、燃油耗量 */
	function showVehicleGross(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>行驶公里、维修费、燃油耗量</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '80%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleGross',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}

	/* 车辆维修记录 */
	function showVehicleRepair(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆维修记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '80%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleRepair',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}

	/* 车辆定(程)维修记录  */
	function showVehicleDinRepair(id) {
		index = layer
				.open({
					type : 2,
					scrollbar : true,
					title : '<h3>车辆定(程)维修记录</h3>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '80%', '80%' ],
					content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleDinRepair',
					end : function(index) {
						//jqGrid.trigger("reloadGrid");
					}
				});
	}

	/* 车辆事故记录  */
	function showVehicleAccident(id) {
		index = layer
				.open({
					type : 2,
					scrollbar : true,
					title : '<h3>车辆事故记录</h3>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '80%', '80%' ],
					content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleAccident',
					end : function(index) {
						//jqGrid.trigger("reloadGrid");
					}
				});
	}

	/* 车辆变更记录  */
	function showVehicleChange(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆变更记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '80%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleChange',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}

	/* 显示详情的操作 */
	function details(value, options, row) {
		var imageHtml = "<a  onclick='detailVehicleRecord(event,\"" + row.id
				+ "\");' id=eye" + row.id
				+ "><i class='ace-icon fa fa-eye'></i></a>";
		return imageHtml;
	}
</script>
</body>
</html>