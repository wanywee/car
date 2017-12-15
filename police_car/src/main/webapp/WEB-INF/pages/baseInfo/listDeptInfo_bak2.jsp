<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>部门信息</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<p>
					<sp:permission link="/deptInfo/addDeptInfo" reverse="false">
						<button id="btnAdd" class="btn btn-1 " onclick="addDeptInfo()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
					</sp:permission>
					<sp:permission link="/deptInfo/editDeptInfo" reverse="false">
						<button id="btnEdit"  class="btn btn-2" onclick="editDeptInfo()" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
					</sp:permission>
					<sp:permission link="/deptInfo/deleteDeptInfo" reverse="false">
				        <button id="btnDelete" class="btn btn-3" onclick="deleteDeptInfo()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
				    </sp:permission>
				    <sp:permission link="/deptInfo/printDeptInfo" reverse="false">
				        <button id="btnPrint" class="btn btn-10 " onclick="printDeptInfo()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
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
					url : "${ctx}/deptInfo/getListDeptInfo",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					mtype: 'POST',
					viewsortcols : [false, 'vertical', true],
					ignoreCount:false,
					colNames : [ "", "编码", "部门名称", "助记码", "负责人", "部门电话", "备注",
							"是否停用" ],
					sortname : 'id',
					colModel : [{
						name : "id",
						hidden: true
					}, {
						name : "deptCode",
						sortable : true,
						cellattr: addCellAttr
					}, {
						name : "deptName",
						cellattr: addCellAttr
					}, {
						name : "mnemonicCode",
						cellattr: addCellAttr
					}, {
						name : "principalName",
						cellattr: addCellAttr
					}, {
						name : "deptCall",
						cellattr: addCellAttr
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
			url : '${ctx}/deptInfo/isUseDeptInfo',
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
	function addDeptInfo() {
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '新增部门信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '50%', '80%' ],
			content : '${ctx}/deptInfo/addDeptInfo?ID=0',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	//修改
	function editDeptInfo() {
		var rowId=$("#table_list").jqGrid("getGridParam","selarrrow");
		if(rowId.length > 1 || rowId == 0) {
			alert("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData",rowId);
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '新增部门信息',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '50%', '80%' ],
			content : '${ctx}/deptInfo/editDeptInfo?ID=' + rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	 //删除
    function deleteDeptInfo(){
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
    	var confirm = layer.confirm('确认要删除该部门信息吗？', { offset: '200px', btn: ['确定','取消'], fixed:false}, function(){
    		layer.close(confirm);		
    		var index = layer.load(1);
    		var data = {"deptmentID":ids}
    		 $.ajax({
    			 type:"POST",
                 url: "${ctx}/deptInfo/deleteDeptInfo",
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
	function detailDeptInfo(ID) {
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '部门信息详情',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '50%', '80%' ],
			content : '${ctx}/deptInfo/editDeptInfo?ID=' + ID,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
</script>
</body>
</html>