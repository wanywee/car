<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>交通事故、违规记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
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
					<p>
						<button id="btnAdd" class="btn btn-1 " type="button"
							onclick="editDispatchRecode(0)">
							<i class="icon iconfont icon-add"></i>&nbsp;新增
						</button>
						<button id="btnUpdate" class="btn btn-2 " type="button"
							onclick="editDispatchRecode(1)">
							<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑
						</button>
						<button id="btnDelete" class="btn btn-3 " type="button"
							onclick="editDispatchRecode(2)">
							<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;删除
						</button>
						<!-- <button id="btnImport" class="btn btn-3 " type="button"><i class="icon iconfont icon-import"></i>&nbsp;导入</button> -->
						<!-- <button class="btn btn-0 "style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button> -->
						<!-- <input placeholder="搜索" style="float: right" type="text" name="appID"  id="serarchStr" class="search_control"> -->
						<input type="text" id="driverID" style="display: none"
							name="driverID" value="${ID}">
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
					url : "${ctx}/baseInfo/getListTrafficRecord?driverID="
							+ $("#driverID").val(),
					postData : {},
					ignoreCount : true,
					pginput : true,
					multiselect : true,
					autowidth : true,
					 mtype:'POST',
					viewrecords : false,
					colNames : ["时间", "情况概述","处理情况", "备注" ],
					sortname : 'id',
					colModel : [ {
						name : "time",
						formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					}, {
						name : "context",
					}, {
						name : "conditions",
					}, {
						name : "comments",
					} ]
				});
	});
	//表格全局搜索
	/* function search(){
		var searchStr = $("#serarchStr").val();
			$("#table_list").jqGrid('setGridParam',{ 
		        datatype:'json', 
		        postData:{'searchStr':searchStr}, //发送数据 
		        page:1 
		    }).trigger("reloadGrid"); //重新载入 
		
	} */
	//新增或者修改或者删除
	function editDispatchRecode(PID) {
		if (PID == '' || PID == 'undefined') {
			PID = 0;
		}
		if (PID == 0) {//新增
			ID=PID;
			index = layer.open({
				type : 2,
				scrollbar : false,
				title : '交通事故违规处理记录',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '95%', '95%' ],
				content : '${ctx}/baseInfo/editTrafficRecord?driverID='
						+ $("#driverID").val() + "&ID=" + ID,
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
				title : '交通事故违规处理记录',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '95%', '95%' ],
				content : '${ctx}/baseInfo/editTrafficRecord?driverID='
						+ $("#driverID").val() + "&ID=" + ID,
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
			var confirm = layer.confirm('确认要删除该交通事故、违规记录吗？', {
				offset: '200px',
				btn : [ '确定', '取消' ]
			}, function() {
				layer.close(confirm);
				var index = layer.load(1);
				var data = {
					"ids" : ids
				}
				$.ajax({
					type : "POST",
					url : "${ctx}/baseInfo/deleteTrafficRecord",
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
	}
</script>
</body>
</html>