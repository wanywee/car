<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>违章记录</title>
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
					<p>
 						<sp:permission link="/carViolation/addCarViolationRecord" reverse="false">
							<button id="btnAdd" class="btn btn-1 " type="button"
								onclick="editCarViolation(-1)">
								<i class="icon iconfont icon-add"></i>&nbsp;新增
							</button>
						</sp:permission> 
  						<sp:permission link="/carViolation/editCarViolationRecord" reverse="false">
							<button id="btnUpdate" class="btn btn-2 " type="button"
								onclick="editCarViolation(0)">
								<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑
							</button>
  						</sp:permission>
 						<sp:permission link="/carViolation/deleteCarViolationRecord" reverse="false"> 
							<button id="btnDelete" class="btn btn-3 " type="button"
								onclick="deleteCarViolation(0)">
								<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;删除
							</button>
 						</sp:permission> 
						<!-- <button id="add_b" class="btn btn-white btn-default btn-round" onclick="editCarViolation(-1)">
                            <i  style="color: blue" class="ace-icon glyphicon glyphicon-plus"></i>
					                            增加
					            </button>
					            <button id="edit-b" class="btn btn-white btn-default btn-round" onclick="editCarViolation(0)">
					                        <i style="color: green" class="ace-icon fa fa-pencil bigger-130"></i>
					                        编辑
					            </button>
					            <button id="delete_b" class="btn btn-white btn-default btn-round" onclick="deleteCarViolation(0)">
					                            <i style="color: red"  class="ace-icon fa fa-trash-o bigger-130"></i>
					                            删除
					            </button> -->
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
							url : "${ctx}/carViolation/getListCarViolationRecord",
							postData : {},
// 							ignoreCount : true,
							mtype: 'POST',
							pginput : true,
							multiselect : true,
							autowidth : true,
							colModel : {
								frozen : true
							},
							//shrinkToFit: false,
// 							viewrecords : false,
							viewsortcols:[false,'vertical',true],
			                ignoreCount:false,
							colNames : [ "",/* "carID", */"车牌号码", "违章项目","违章日期","罚款","扣分","驾驶员", "备注" ],
							sortname : 'id',
							colModel : [
									{
										name : "",
										width : 50,
										align : 'center',
										formatter : function(value, options,
												row) {
											var opts = new Opts();
											opts
													.add("viewDetails("
															+ row.id + ")",
															"<i class='ace-icon fa fa-eye'></i>");
											return opts.getOpts();
										}
									}/* , {
									                  name: "carID",
									                  sortable:true
									                } */,
									{
										name : "licenseno",
										sortable : true
									}, 
									{
										name : "violationProject",
										sortable:true
									},
									{
										name : "violationDate",
										fixed : true,
										width : 150,
										formatter : function(value, options,
												row) {
											return setColor("blue", utils
													.gettimestamp(value));
										}
									}, 
									{
										name : "fine",
										sortable:true
									},
									{
										name : "points",
										sortable:true
									},
									{
										name : "staffName",
										sortable:true
									}, 
									{
										name : "remark"
									} ]
						});
		$("#table_list").jqGrid('setFrozenColumns').triggerHandler(
				"jqGridAfterGridComplete");
	});
	//表格全局搜索table_list
	function search() {
		var searchStr = $("#serarchStr").val();
		$("#table_list").jqGrid('setGridParam', {
			datatype : 'json',
			mtype: 'POST',
			postData : {
				'searchStr' : searchStr
			}, //发送数据 
			page : 1
		}).trigger("reloadGrid"); //重新载入 

	}

	/*删除*/
	function deleteCarViolation(ID) {

		var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
		var idslen = ids.length;
		ID = ids;
		layer.confirm('你确定删除该' + idslen + '行记录吗？', {
			offset: '200px',
			btn : [ '确定', '取消' ], //按钮
			fix: false, //按钮
			yes : function() {
				var url = "${ctx}/carViolation/deleteCarViolationRecord?id=" + ID;
				$.ajax({
					type : 'POST',
					url : url,
					success : function(data) {
						if (data.flag) {
							layer.msg('删除成功', {
								icon : 1
							});
							jqGrid.trigger("reloadGrid");
						} else {
							layer.msg('删除失败', {
								icon : 2
							});
						}
					}
				});
			},
			no : function() {
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				parent.layer.close(index);
			}

		}, function() {
			/* layer.msg('取消', {
			  time: 20000, //20s后自动关闭
			  btn: ['ok', 'ok']
			}); */
		});
	}

	//查看详情
	function viewDetails(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '<h3>车辆违章详情</h3>',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '600px', '50%' ],
			content : '${ctx}/carViolation/CarViolationDetail?id=' + id,
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}

	//新增或者修改
	function editCarViolation(ID) {
		if (ID != -1) {

			var ids = $("#table_list").jqGrid("getGridParam", "selarrrow");//获得所选取的行
			var rowid;
			if (ids.length == 1) {
				rowid = $("#table_list").jqGrid("getGridParam", "selrow");

				if (ID == '' || ID == 'undefined') {
					ID = rowid;
				}
				index = layer.open({
					type : 2,
					scrollbar : false,
					title : '<h3>编辑违章记录</h3>',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '600px', '75%' ],
					content : '${ctx}/carViolation/editCarViolationRecord?id=' + ID,
					end : function(index) {
						jqGrid.trigger("reloadGrid");
					}
				});
			} else {
				//alert("你没有选取或者选取为多行数据，不允许进入下一级");
				layer.msg('你没有选取或者选取为多行数据，不能对其进行编辑', {
					icon : 2
				});
			}
		} else {
			ID = 0;
			index = layer.open({
				type : 2,
				scrollbar : false,
				title : '<h3>新增违章记录</h3>',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '600px', '75%' ],
				content : '${ctx}/carViolation/editCarViolationRecord?id=' + ID,
				end : function(index) {
					jqGrid.trigger("reloadGrid");
				}
			});

		}
	}
</script>
</body>
</html>