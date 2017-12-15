<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>警员信息</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm"></form>
				</div>
				<div class="ibox-content">
					<div id="hideNoNeed">
						<p>
							<sp:permission link="/baseInfo/addStaffInformation"
								reverse="false">
								<button id="btnAdd" class="btn btn-1 " type="button"
									onclick="editDispatchRecode(0)">
									<i class="icon iconfont icon-add"></i>&nbsp;新增
								</button>
							</sp:permission>
							<sp:permission link="/baseInfo/editStaffInformation"
								reverse="false">
								<button id="btnUpdate" class="btn btn-2 " type="button"
									onclick="editDispatchRecode(1)">
									<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑
								</button>
							</sp:permission>
							<sp:permission link="/baseInfo/deleteStaffInformation"
								reverse="false">
								<button id="btnDelete" class="btn btn-3 " type="button"
									onclick="editDispatchRecode(2)">
									<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;删除
								</button>
							</sp:permission>
						        <button id="btnPrint" class="btn btn-10 " onclick="printDeptInfo()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
							<!-- <button id="btnImport" class="btn btn-3 " type="button"><i class="icon iconfont icon-import"></i>&nbsp;导入</button> -->
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
	})
	//加载jqGrid
	$(function() {
		jqGrid = $("#table_list")
				.jqGrid(
						{
							url : "${ctx}/baseInfo/getListStaffInfo",
							postData : {},
							ignoreCount : true,
							pginput : true,
							multiselect : true,
							autowidth : true,
							mtype : 'POST',
							viewsortcols : [ false, 'vertical', true ],
							ignoreCount : false,
							colNames : [ "", "警号", "姓名", "部门", "职务",
									"手机", "性别", "备注", "是否停用" ],
							sortname : 'id',
							colModel : [
									{
										name : "opt",
										width : 50,
										align : 'center',
										formatter : details
									},
									{
										name : "staffNo",
										sortable : true,
										cellattr : addCellAttr
									},
									{
										name : "staffName",
										cellattr : addCellAttr
									},
									{
										name : "deptName",
										cellattr : addCellAttr
									},
									{
										name : "staffDuty",
										cellattr : addCellAttr
									},
									{
										name : "staffPhone",
										cellattr : addCellAttr
									},
									{
										name : "staffSex",
										cellattr : addCellAttr,
										formatter : function(value, options,
												row) {
											if (row.staffSex == 1) {
												return "男";
											}
											if (row.staffSex == 2) {
												return "女";
											}
										}
									},
									{
										name : "comments",
										cellattr : addCellAttr
									},
									{
										name : "status",
										align : "center",
										sortable : true,
										formatter : function(value, options,
												row) {
											var reHtml = "<input name='status' onchange='isUse(\""
													+ row.id
													+ "\")' value='2' type='checkbox' id='status"
													+ row.id + "'>";
											var re2Html = "<input name='status' onchange='isUse(\""
													+ row.id
													+ "\")' value='2' type='checkbox' id='status"
													+ row.id
													+ "' checked='checked'>";
											if (row.status == 1) {
												return reHtml;
											}
											if (row.status == 2) {
												return re2Html;
											}
										}
									} ]
						});
	});
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
	//新增或者修改或者删除
	function editDispatchRecode(PID) {
		if (PID == '' || PID == 'undefined') {
			PID = 0;
		}
		if (PID == 0) {//新增
			ID = PID;
			index = layer.open({
				type : 2,
				scrollbar : false,
				title : '新增警员信息',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '95%', '95%' ],
				content : '${ctx}/baseInfo/addStaffInformation',
				end : function(index) {
					jqGrid.trigger("reloadGrid");
				}
			});
		}
		if (PID == 1) {//
			//编辑修改
			ID = $('#table_list').jqGrid('getGridParam', 'selrow');
			var select_ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
			if(select_ids.length>1){
    			layer.alert('你已选择多行！请重新选择一行');
    			return;
    		}
    		if(ID==null){
    			layer.alert('请选择一行');
    			return;
    		}
			index = layer.open({
				type : 2,
				scrollbar : false,
				title : '编辑警员信息',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '95%', '95%' ],
				content : '${ctx}/baseInfo/editStaffInformation?ID=' + ID,
				end : function(index) {
					jqGrid.trigger("reloadGrid");
				}
			});
		}
		if (PID == 2) {
			//删除
			var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
			if (ids.length == 0) {
				layer.alert('请选择一行');
				return;
			}
			var data = {
				"ids" : ids,
				"query" : "yes"
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/baseInfo/deleteStaffInformation",
				data : data,
				success : function(result) {
					if (result.message == 'yes') {/* 选中警员中存在驾驶员信息,确认删除吗？ */
						var confirm = layer.confirm('确认要删除该警员信息吗？', {
							offset : '200px',
							btn : [ '确定', '取消' ],
							fix : false
						}, function() {
							layer.close(confirm);
							var index = layer.load(1);
							var data = {
								"ids" : ids,
								"query" : "no"
							}
							$.ajax({
								type : "POST",
								url : "${ctx}/baseInfo/deleteStaffInformation",
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
					} else if (result.message == 'no') {
						var confirm = layer.confirm('确认要删除该警员信息吗？', {
							offset : '100px',
							btn : [ '确定', '取消' ],
							fix : false
						}, function() {
							layer.close(confirm);
							var index = layer.load(1);
							var data = {
								"ids" : ids,
								"query" : "no"
							}
							$.ajax({
								type : "POST",
								url : "${ctx}/baseInfo/deleteStaffInformation",
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
				},
			});
		}
	}
	/* 是否停用的改变 */
	function isUse(id) {
		var data;
		if ($('#status' + id).is(':checked')) {
			data = {
				StatusId : id,
				status : 2
			};
		} else {
			data = {
				StatusId : id,
				status : 1
			};
		}
		$.ajax({
			type : 'post',
			datatype : 'text',
			url : '${ctx}/baseInfo/isUseStaffInfoRecords',
			data : data,
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function() {
				jqGrid.trigger("reloadGrid");
			},
			error : function(data) {
				layer.msg(data.message);
			}
		});
	}
	/* 表格列字体颜色 */
	function addCellAttr(rowId, val, rawObject, cm, rdata) {
		if (rawObject.status == 2) {
			return "style='color:red'";
		}
	}
	/* 显示详情的操作 */
	function details(value, options, row) {
		var imageHtml = "<a  onclick='detailStaffInfo(\"" + row.id
				+ "\");' id=eye" + row.id
				+ "><i class='ace-icon fa fa-eye'></i></a>";
		return imageHtml;
	}
	/* 显示警员详情信息 */
	function detailStaffInfo(id) {
		index = layer
				.open({
					type : 2,
					scrollbar : true,
					title : '<h3>警员详情信息</h3>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '60%', '80%' ],
					content : '${ctx}/baseInfo/showStaffInfoDetial?show=showCarDriver&ID='
							+ id,
					end : function(index) {
						//jqGrid.trigger("reloadGrid");
					}
				});
	}
	function printDeptInfo(){
		$("#hideNoNeed").hide();
		$("#pager_list").hide(); 
		window.print();
		$("#hideNoNeed").show();
		$("#pager_list").show(); 
	} 
</script>
</body>
</html>