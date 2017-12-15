<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车辆信息</title>
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
					<div id="hideNoNeed">
						<p>
						<sp:permission link="/vehicleRecords/addVehicleRecord" reverse="false">
							<button id="btnAdd" class="btn btn-1 " onclick="addVehicleRecord()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
						</sp:permission>
						<sp:permission link="/vehicleRecords/editVehicleRecord" reverse="false">
							<button id="btnEdit"  class="btn btn-2" onclick="editVehicleRecord()" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
						</sp:permission>
						<sp:permission link="/vehicleRecords/deleteVehicleRecord" reverse="false">
					        <button id="btnDelete" class="btn btn-3" onclick="deleteVehicleRecord()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
					    </sp:permission>
					    <sp:permission link="/vehicleRecords/printVehicleRecord" reverse="false">
					        <button id="btnPrint" class="btn btn-10 " onclick="printVehicleRecord()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
					    </sp:permission>
							<button class="btn btn-0 "
								style="float: right; margin-left: 10px;" type="button"
								id="serchBtn" onclick="search()">
								<i class="icon iconfont icon-search"></i>&nbsp;搜索
							</button>
							<input placeholder="搜索" style="float: right" type="text"
								name="appID" id="serarchStr" class="search_control">
						</p>
 					   </div>
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
	});
	
	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list").jqGrid(
				{
					url : "${ctx}/vehicleRecord/getListVehicleRecords",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewsortcols : [false, 'vertical', true],
					ignoreCount:false,
					colNames : [ "", "", "车牌号", "车辆品牌", "车辆类型", "车辆型号", "载人(个)", "座位数", "状态", "备注",
							"是否停用" ],
					sortname : 'id',
					colModel : [{
						name : "id",
						hidden: true
					},{
						name : "opt",
						width : 50,
						align : 'center',
						formatter : details
					}, {
						name : "licenseno",
						sortable : true,
						cellattr: addCellAttr
					}, {
						name : "brandName",
						cellattr: addCellAttr
					}, {
						name : "typeName",
						cellattr: addCellAttr
					}, {
						name : "modelName",
						cellattr: addCellAttr
					}, {
						name : "loading",
						sortable : true,
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
					}, {
						name : "comments",
						cellattr: addCellAttr,
					}, {
						name : "status",
						align : "center",
						sortable : true,
						formatter : function(value, options, row) {
							var reHtml = "<input name='status' onchange='isUse(\""+ row.id +"\")' value='2' type='checkbox' id='status" + row.id + "'>";
							var re2Html = "<input name='status' onchange='isUse(\""+ row.id +"\")' value='2' type='checkbox' id='status" + row.id + "' checked='checked'>";
							if(row.status == 1) {
								return reHtml;
							}
							if(row.status == 2) {
								return re2Html;
							}
						}
					} ]
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
	/* 新增 */
	function addVehicleRecord() {
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '新增车辆信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '900px', '80%' ],
			content : '${ctx}/vehicleRecord/addVehicleRecords?ID=0',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	//修改
	function editVehicleRecord() {
		var rowId=$("#table_list").jqGrid("getGridParam","selarrrow");
		if(rowId.length > 1 || rowId == 0) {
			alert("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData",rowId);
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '修改车辆信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '900px', '80%' ],
			content : '${ctx}/vehicleRecord/editVehicleRecords?ID=' + rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	 //删除
    function deleteVehicleRecord(){
    	var rowIds = $("#table_list").jqGrid("getGridParam","selarrrow");
    	if(rowIds.length < 1){
    		layer.msg('请选择行数',{icon: 2});
    		return;
    	}
    	var rowData, ids = new Array();
    	for (var i = 0; i < rowIds.length; i++) {
    		rowData = $("#table_list").jqGrid("getRowData",rowIds[i]);
    		ids[i] = rowData.id;
		}
    	ids = ids.toString();
    	var confirm = layer.confirm('确认要删除该车辆信息吗？', {offset: '200px', btn: ['确定','取消'] }, function(){
    		layer.close(confirm);		
    		var index = layer.load(1);
    		var data = {"vehicleID":ids}
    		 $.ajax({
    			 type:"POST",
                 url: "${ctx}/vehicleRecord/deleteVehicleRecords",
                 data: data,
                 success: function(result) {
                	layer.close(index);
         			layer.msg(result.message, {icon: 1});
         			jqGrid.trigger("reloadGrid");   
                 },
                 error: function(result,XMLHttpRequest, textStatus, errorThrown) {
                	 console.log(result)
                	 layer.msg(result.message, {icon: 2});
                	 layer.close(index);
                 },
               });
    	}, function(){
    		
    	}
    ); 
    }
	
	/* 显示详情 */
	function detailVehicleRecord(e,ID) {
		var tipContext="<ul>" +
		"<li><a href='#' onclick='showVehicleRecord(\""+ID+ "\")'>基本信息</a></li>" +
		"<li><a href='#' onclick='showVehicleGross(\""+ID+ "\")'>行驶公里、维修费、燃油耗量</a></li>" +
		"<li><a href='#' onclick='showVehicleRepair(\""+ID+ "\")'>车辆维修记录</a></li>" +
		"<li><a href='#' onclick='showVehicleDinRepair(\""+ID+ "\")'>车辆定(程)维修记录</a></li>" +
		"<li><a href='#' onclick='showVehicleAccident(\""+ID+ "\")'>车辆事故记录</a></li>" +
		"<li><a href='#' onclick='showVehicleChange(\""+ID+ "\")'>车辆变更记录</a></li></ul>";
		layer.tips(tipContext, '#eye'+ID, {
			  tips: [2, '#f0f5f5'],
			  area: ['200px', 'auto'],
			  time: 5000
			});
		  utils.stopBubbling(e);
	}
	
	/* 显示车辆基本信息 */
	function showVehicleRecord(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆基本信息</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '60%', '80%' ],
			content : '${ctx}/vehicleRecord/editVehicleRecords?show=showVehicle&ID=' + id,
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
			area : [ '95%', '80%' ],
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
			area : [ '95%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleRepair',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	/* 车辆定(程)维修记录  */
	function showVehicleDinRepair(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆定(程)维修记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleDinRepair',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	/* 车辆事故记录  */
	function showVehicleAccident(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆事故记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '80%' ],
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
			area : [ '95%', '80%' ],
			content : '${ctx}/baseInfo/detialVehicleRecords/listVehicleChange',
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	/* 显示详情的操作 */
	function details(value, options,row) {
		var imageHtml = "<a  onclick='detailVehicleRecord(event,\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
		return imageHtml;
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