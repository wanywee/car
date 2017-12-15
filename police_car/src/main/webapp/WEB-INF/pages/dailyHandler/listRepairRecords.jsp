<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>维修记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<p>
					<sp:permission link="/repairRecords/addRepairRecord" reverse="false">
						<button id="btnAdd" class="btn btn-1 " onclick="addRepairRecord()" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
					</sp:permission>
					<sp:permission link="/repairRecords/editRepairRecord" reverse="false">
						<button id="btnEdit"  class="btn btn-2" onclick="editRepairRecord()" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
					</sp:permission>
					<sp:permission link="/repairRecords/deleteRepairRecord" reverse="false">
				        <button id="btnDelete" class="btn btn-3" onclick="deleteRepairRecord()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
				    </sp:permission>
						<button class="btn btn-0 "
							style="float: right; margin-left: 10px;" type="button"
							id="serchBtn" onclick="search()">
							<i class="icon iconfont icon-search"></i>&nbsp;搜索
						</button>
						<input placeholder="时间格式：yyyy-MM-dd" style="float: right" type="text"
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
					url : "${ctx}/repairRecord/getListRepairRecords",
					mtype : "post",
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					viewsortcols : [false, 'vertical', true],
					ignoreCount:false,
					colNames : [ "", "", "车牌号", "经手人", "修理厂", "送修日期", "预计取车", "备注" ],
					sortname : 'id',
					colModel : [{
						name : "id",
						hidden: true
					}, {
						name : "opt",
						width : 50,
						align : 'center',
						formatter : details
					}, {
						name : "licenseno",
						sortable : true
					}, {
						name : "staffName",
					}, {
						name : "repairPdName",
					}, {
						name : "repairTime",
						sortable : true,
						formatter : function(value, options,
								row) {
							return setColor("blue", utils
									.gettimestamp(value));
						}
					}, {
						name : "estimateTackcar",
						sortable : true,
						formatter : function(value, options,
								row) {
							return setColor("blue", utils
									.gettimestamp(value));
						}
					}, {
						name : "remark",
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
	/* 新增 */
	function addRepairRecord() {
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '<h3>新增维修记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '80%' ],
			content : '${ctx}/repairRecord/addRepairRecords?ID=0',
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	//修改
	function editRepairRecord() {
		var rowId=$("#table_list").jqGrid("getGridParam","selarrrow");
		if(rowId.length > 1 || rowId == 0) {
			alert("请选择一行数据");
			return;
		}
		var rowData = $("#table_list").jqGrid("getRowData",rowId);
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '<h3>修改维修记录</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '80%' ],
			content : '${ctx}/repairRecord/editRepairRecords?ID=' + rowData.id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
	}
	
	 //删除
    function deleteRepairRecord(){
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
    	var confirm = layer.confirm('确认要删除该维修记录吗？', {offset: '200px', btn: ['确定','取消'] }, function(){
    		layer.close(confirm);		
    		var index = layer.load(1);
    		var data = {"repairID":ids}
    		 $.ajax({
    			 type:"POST",
                 url: "${ctx}/repairRecord/deleteRepairRecords",
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
	
	/* 显示维修记录详情 */
	function showRepairRecord(e, id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>维修记录详情</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '95%', '50%' ],
			content : '${ctx}/repairRecord/editRepairRecords?show=showRepair&ID=' + id,
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
		utils.stopBubbling(e);
	}
	
	/* 显示详情的操作 */
	function details(value, options,row) {
		var imageHtml = "<a  onclick='showRepairRecord(event,\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
		return imageHtml;
	}
	
</script>
</body>
</html>