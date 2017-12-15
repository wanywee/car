<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>年检记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
     <c:if test="${ not empty remindDate }">
        <c:set var="count" value="${remindDate}"/>
     </c:if>
 	<c:if test="${ not empty count }">
     <input type="hidden" value="${count}"  id="count"/>
 	</c:if>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm"></form>
				</div>
				<div class="ibox-content">
					<p>
						<sp:permission link="/yearIPTRecord/insertYearIPTRecord" reverse="false">
							<button id="btnAdd" class="btn btn-1 " type="button"
								onclick="editYearIPTRecord(-1)">
								<i class="icon iconfont icon-add"></i>&nbsp;新增
							</button>
						</sp:permission>
						<sp:permission link="/yearIPTRecord/editDailyYearIPT" reverse="false">
							<button id="btnUpdate" class="btn btn-2 " type="button"
								onclick="editYearIPTRecord(0)">
								<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑
							</button>
						</sp:permission>
						<sp:permission link="/yearIPTRecord/deleteDailyYearIPT" reverse="false">
							<button id="btnDelete" class="btn btn-3 " type="button"
								onclick="deleteYearIPTRecord(0)">
								<i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;删除
							</button>
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
	$.loadProjectattr($("#keyCode"));
	$("#export").click(function() {
		utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
	});
	$("#return").click(function() {
		window.history.back();
	})
	//加载jqGrid
	$(function() {
		var  count = $("#count").val();
		if(typeof(count)==="undefined"){
			count=" ";
			
		}
		jqGrid = $("#table_list")
				.jqGrid(
						{
							url : "${ctx}/yearIPTRecord/getListCarDailyYearIPTRecord?count="+count,
							 postData: {},
				                ignoreCount: true,
				                pginput: true,
				                mtype: 'POST',
				                multiselect: true,
				                autowidth:true,
				                //viewrecords: false,
				                viewsortcols:[false,'vertical',true],
				                ignoreCount:false,
							colNames : [ "","车牌号码", "年检号","年检费用",
									"年检日期","到期日期", "车管所", "经手人", "备注","" ],
							colModel : [
									{
										name : "",
										width : 50,
										align : 'center',
										formatter : function(value, options,row) {
											var opts = new Opts();
											opts.add("viewDetails("+ row.id + ")","<i class='ace-icon fa fa-eye'></i>");
											return opts.getOpts();
										}
									},
									{
										name : "licenseno",
										cellattr: addCellAttr,
										sortable : true
									},
									{
										name : "yearIptNumber",
										cellattr: addCellAttr
									},{
										cellattr: addCellAttr,
										name : "yearIptMoney"
									},
									{
										cellattr: addCellAttr,
										name : "yearIptDate",
										fixed : true,
										width : 150,
										formatter : function(value, options,
												row) {
											return setColor("blue", utils
													.gettimestamp(value));
										}
									}, {
										cellattr: addCellAttr,
										name : "endDate",
										fixed : true,
										width : 150,
										formatter : function(value, options,
												row) {
											return setColor("blue", utils
													.gettimestamp(value));
										}
									},{
										cellattr: addCellAttr,
										name : "companyName",
										 
									}, {
										cellattr: addCellAttr,
										name : "staffName"
									}, {
										cellattr: addCellAttr,
										name : "remark"
									},{
										cellattr: addCellAttr,
										name : "status",
										formatter : function(value, options, row) {
											var reHtml = "<input name='status' onclick='dealYearIPTRecord(\""+ row.id +"\")' value='处理' type='button' id='status" + row.id + "'>";
											var re2Html = "<input type='hidden'/>";
											if(row.status == 1) {
												return reHtml;
											}
											if(row.status == null) {
												return re2Html;
											}
										}
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
	function deleteYearIPTRecord(ID) {

		var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
		var idslen = ids.length;
		ID = ids;
		layer.confirm('你确定删除该' + idslen + '行记录吗？', {
			offset: '200px',
			btn : [ '确定', '取消' ], //按钮
			offset: ['200px', '38%'],
			fix: false,
			yes : function() {
				var url = "${ctx}/yearIPTRecord/deleteDailyYearIPT?id=" + ID;
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

	/* 表格列字体颜色 */
	function addCellAttr(rowId, val, rawObject, cm, rdata) {
		if (rawObject.status == 1) {
			return "style='color:blue'";
		}
	}
	
	
	//查看详情
	function viewDetails(id) {
		index = layer.open({
			type : 2,
			scrollbar : true,
			title : '年检记录详情',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '900px', '90%' ],
			content : '${ctx}/yearIPTRecord/YearIPTRecordDetail?show=showYear&id=' + id,
			end : function(index) {
				//jqGrid.trigger("reloadGrid");
			}
		});
	}

	//处理到期提醒
	function dealYearIPTRecord(id){
		index = layer.open({
			type : 2,
			scrollbar : false,
			title : '编辑年检记录',
			maxmin : true,
			shadeClose : false,
			anim : 5,
			area : [ '800px', '95%' ],
			content : '${ctx}/yearIPTRecord/editYearIPTRecord?id=' + id,
			end : function(index) {
				jqGrid.trigger("reloadGrid");
			}
		});
		
	}
	
	
	//新增或者修改
	function editYearIPTRecord(ID) {
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
					title : '编辑年检记录',
					maxmin : true,
					shadeClose : false,
					anim : 5,
					area : [ '800px', '95%' ],
					content : '${ctx}/yearIPTRecord/editYearIPTRecord?id=' + ID,
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
				title : '新增年检记录',
				maxmin : true,
				shadeClose : false,
				anim : 5,
				area : [ '800px', '95%' ],
				content : '${ctx}/yearIPTRecord/insertYearIPTRecord?id=' + ID,
				end : function(index) {
					jqGrid.trigger("reloadGrid");
				}
			});

		}
	}
</script>
</body>
</html>