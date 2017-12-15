<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>到期提醒</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body onload="init()">

	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="search_box">
					<form class="form-inline" id="searchForm">
					</form>
				</div>
				<div class="ibox-content">
					<p>
						<c:if test="not empty ${kcm.code}">
						   <c:set var="res" value="${kcm.code}"/>
						<input type="text" value="${kcm.code}">
						</c:if>
						<sp:permission link="/termination/getListRemindForm" reverse="false">
						提前 <input name="day" readonly unselectable="on"  style="border-style:none;width: 25px;" id="remindDate" type="text"
							style="width: 50px" onchange="remindDateFunc()"
							value="${kcm.code}" />天到期提醒
							</sp:permission>
					</p>

					<div class="jqGrid_wrapper">
						<table id="table_list"></table>
						<!-- <div id="pager_list"></div> -->
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
	})

	function remindDateFunc() {
		//$("#remindDate").removeAttr("value");
		var remindDate = $("#remindDate").val();
		$("#table_list").jqGrid('setGridParam', {
			mtype: 'POST',
			datatype : 'json',
			postData : {
				'remindDate' : remindDate
			}, //发送数据 
			page : 1
		}).trigger("reloadGrid"); //重新载入 

	}

	function  dealRemindDate(id){
		var remindDate = $("#remindDate").val();
		if(id==1||id==2||id==3||id==4){
			window.open("listTerminationInfo",'_blank');
			//window.location.href='${ctx}/termination/dealRemindDate?remindDate='+remindDate+"&dealid="+id;
		}
		   
	}
	
	
	function init() {
		$.ajax({
			type : 'POST',
			url : '${ctx}/termination/getRemindDate?keyID=' + 19,
			success : function(data) {
				$("#remindDate").attr("value", data.code);
				$(function() {
					//$("#remindDate").removeAttr("value");
					var remindDate = $("#remindDate").val();
					var url = "${ctx}/termination/getListRemindForm?remindDate="
							+ remindDate;
					jqGrid = $("#table_list").jqGrid({
						
						
						mtype: 'POST',
						url : url,
						postData : {},
						ignoreCount : true,
						pginput : true,
						// multiselect : true,
						autowidth : true,
						colModel : {
							frozen : true
						},
						//shrinkToFit: false,
						viewrecords : false,
						colNames : [ "id", "类别", "数量", "" ],
						sortname : 'id',
						colModel : [ {
							name : "id",
							sortable : true
						}, {
							name : "type"
						}, {
							name : "numbers"
						}, {
							name : "",
							fixed : true,
							width : 100,
							formatter : function(value, options, row) {
								
								console.info("-------------")
								var S = $("#remindDate").val();
								
								console.info(S);
								if(row.numbers!=0){
									var opts = new Opts();
									
									if(row.id==1){
										
										var M="remindDate="+S
										opts.add("addTabs({id:'140011',title: '保险记录',close: true,url:'${ctx}/carInsurance/listCarInsurance'})", "处理");
									}
									if(row.id==2){
										/* var M='${ctx}/yearIPTRecord/listCarDailyYearIPTRecord?remindDate='+S; */
										
										opts.add("addTabs({id:'140010',title: '年检记录',close: true,url:'${ctx}/yearIPTRecord/listCarDailyYearIPTRecord'})", "处理");
									}
									if(row.id==3){
										opts.add("addTabs({id:'140006',title: '规费记录',close: true,url:'${ctx}/dailyHandle/listFeesManage'})", "处理");
									}
									if(row.id==4){
										opts.add("addTabs({id:'140007',title: '保养记录',close: true,url: '${ctx}/carDailyMaintain/carDailyMaintainList'})", "处理");
									} 
									/* opts.add("dealRemindDate(" +row.id+ ")", "处理"); */
									return opts.getOpts();
								}else{
									var opts = new Opts();
									opts.add(" ", "");
									return opts.getOpts();
								}
								
							}
						} ]
					});
					$("#table_list").jqGrid('setFrozenColumns').triggerHandler(
							"jqGridAfterGridComplete");
				});
			}
		})

	}
	
	
	
	
	var 
	addTabs = function(options) {
		id = "tab_" + options.id;
		$(".active", window.parent.document).removeClass("active");

		// 如果TAB不存在，创建一个新的TAB
		if (!$("#" + id)[0], window.parent.document) {
			// 固定TAB中IFRAME高度
			mainHeight = $(document).height() - 50;
			// 创建新TAB的title
			title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id
							+ '" aria-controls="' + id + '" role="tab" data-toggle="tab">'
					+ options.title;
			// 是否允许关闭
			if (options.close) {
				title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id
								+ '"></i>';
			}
			title += '</a></li>';
			// 是否指定TAB内容
			if (options.content) {
				content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
						+ options.content + '</div>';
			} else { // 没有内容，使用IFRAME打开链接
				content = '<div role="tabpanel" class="tab-pane" id="'
								+ id
								+ '"><iframe src="'
						+ options.url
						+ '" width="100%" height="'
						+ mainHeight
						+ '" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe></div>';
			}
			// 加入TABS
			$(".nav-tabs", window.parent.document).append(title);
			$(".tab-content", window.parent.document).append(content);
		} else {
			closeTab(id);
			// 固定TAB中IFRAME高度
			mainHeight = $(document).height() - 50;
			// 创建新TAB的title
			title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id
							+ '" aria-controls="' + id + '" role="tab" data-toggle="tab">'
					+ options.title;
			// 是否允许关闭
			if (options.close) {
				title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id
								+ '"></i>';
			}
			title += '</a></li>';
			// 是否指定TAB内容
			if (options.content) {
				content = '<div role="tabpanel" class="tab-pane" id="' + id + '">'
						+ options.content + '</div>';
			} else { // 没有内容，使用IFRAME打开链接
				content = '<div role="tabpanel" class="tab-pane" id="'
								+ id
								+ '"><iframe src="'
						+ options.url
						+ '" width="100%" height="'
						+ mainHeight
						+ '" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe></div>';
			}
			// 加入TABS
			$(".nav-tabs", window.parent.document).append(title);
			$(".tab-content", window.parent.document).append(content);
		}
		// 激活TAB
		$("#tab_" + id, window.parent.document).addClass('active');
		$("#" + id, window.parent.document).addClass("active");
	};
	var closeTab = function(id) {
		// 如果关闭的是当前激活的TAB，激活他的前一个TAB
		if ($("li.active").attr('id') == "tab_" + id) {
			$("#tab_" + id).prev().addClass('active');
			$("#" + id).prev().addClass('active');
		}
		// 关闭TAB
		$("#tab_" + id).remove();
		$("#" + id).remove();
	};
	$(function() {
		mainHeight = $(document).height() - 50;
		$('.main-left,.main-right').height(mainHeight);
		$("[addtabs]").click(function() {
			addTabs({
				id : $(this).attr("id"),
				title : $(this).attr('title'),
				close : true
			});
		});

		$(".nav-tabs").on("click", "[tabclose]", function(e) {
			id = $(this).attr("tabclose");
			closeTab(id);
		});
	});
</script>
</body>
</html>