<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆事故记录</title>
<%@include file="../../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<p>
						<sp:permission link="/VehicleAccident/addVehicleAccident"
							reverse="false">
							<button id="btnAdd" class="btn btn-1 "
								onclick="addVehicleAccident()" type="button">
								<i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增
							</button>
						</sp:permission>
						<sp:permission link="/VehicleAccident/editVehicleAccident"
							reverse="false">
							<button id="btnEdit" class="btn btn-2"
								onclick="editVehicleAccident()" type="button">
								<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑
							</button>
						</sp:permission>
						<sp:permission link="/VehicleAccident/deleteVehicleAccident"
							reverse="false">
							<button id="btnDelete" class="btn btn-3"
								onclick="deleteVehicleAccident()" type="button">
								<i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除
							</button>
						</sp:permission>
						<button class="btn btn-0 "
							style="float: right; margin-left: 10px;" type="button"
							id="btnTime">
							<i class="fa fa-clock-o"></i>&nbsp;时间
						</button>
						<input placeholder="年份" style="float: right" type="text"
							name="serarchYear" id="serarch" class="search_control">
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
		elem : '#serarch',
		type : 'year',
		done : function(value, date) {
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
		jqGrid = $("#table_list").jqGrid(
				{
					url : "${ctx}/vehicleRecord/getListVehicleRecord",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewrecords : false,
					colNames : [ "事故发生日期", "车辆损失情况", "受损金额", "维修金额", "维修完工日期",
							"接收人", "我方承担金额", "对方承担金额", "保险赔偿金额", "我方情况",
							"对方情况", "发生地点", "事故说明", "处理结果", "备注" ],
					sortname : 'id',
					colModel : [ {
						name : "sgfsrq"
					}, {
						name : "clssqk"
					}, {
						name : "ssje"
					}, {
						name : "wsje"
					}, {
						name : "wswgrq"
					}, {
						name : "jsr"
					}, {
						name : "wfcdje"
					}, {
						name : "dfcdje"
					}, {
						name : "bxpcje"
					}, {
						name : "wfqk"
					}, {
						name : "dfqk"
					}, {
						name : "fsdd"
					}, {
						name : "sgsm"
					}, {
						name : "cljg"
					}, {
						name : "comments"
					} ]
				});

		jQuery("#table_list").jqGrid('setGroupHeaders', {
			useColSpanStyle : false,
			groupHeaders : [ {
				startColumnName : 'clssqk',
				numberOfColumns : 3,
				titleText : '车辆受损情况'
			}, {
				startColumnName : 'wswgrq',
				numberOfColumns : 3,
				titleText : '维修情况'
			}, {
				startColumnName : 'dfcdje',
				numberOfColumns : 2,
				titleText : '承担金额'
			} ]
		});
	});

	/* 新增 */
	function addVehicleAccident() {
		index = layer.open({
					type : 2,
					scrollbar : false,
					title : '<h4>新增车辆事故记录</h4>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '80%', '80%' ],
					content : '${ctx}/baseInfo/detialVehicleRecords/editVehicleAccident?ID=0',
					end : function(index) {
						jqGrid.trigger("reloadGrid");
					}
				});
	}
	//修改
	function editVehicleAccident() {
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
	function deleteVehicleAccident() {
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