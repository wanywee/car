<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <%@ include file="../common/header.jsp" %>
		<title>车辆管理系统-车辆状态图</title>
		<link rel="stylesheet" href="${ctx }/css/vehicle_state.css" />
		<link rel="stylesheet" href="${ctx }/assets/css/ace.css" />
		<link rel="stylesheet" href="${ctx}/css/style.css" />
<%-- 		<script src="${ctx }/js/bootstrap-tabs.js"></script> --%>
		<script src="${ctx }/js/sidebar-menu.js"></script>
	</head>

	<body class="main-container" style="overflow:inherit;">
	<div id="main">
		<div class="main-content">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12 col-lg-12 col-sm-12 col-xs-12 vehicle_state">
						<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state selector_state" id="state_1" value="">全部</div>
	  					<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state" id="state_2" value="可用"><img class="state_img" src="${ctx }/img/vehicle_state/canusecar.png"/>可用</div>
						<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state" id="state_3" value="出车"><img class="state_img" src="${ctx }/img/vehicle_state/outcar.png"/>出车</div>
						<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state" id="state_4" value="维修"><img class="state_img" src="${ctx }/img/vehicle_state/maintaincar.png"/>维修</div>
						<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state" id="state_5" value="其他"><img class="state_img" src="${ctx }/img/vehicle_state/othercar.png"/>其他</div>
						<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_state" id="state_6" value="申请中">申请中</div>
						<div>
							<button class="btn btn-0 " style="float: right; margin-left: 10px;" type="button" id="serchBtn" onclick="search()">
								<i class="icon iconfont icon-search"></i>&nbsp;搜索
							</button>
							<input placeholder="车牌搜索" style="float: right" type="text" name="searrchStr" id="searchStr" class="search_control">
						</div>
					</div>
				</div>
				<div class="vehicle_pannel">
					<div class="row">
						<div class="col-xs-12 col-lg-12 col-sm-12 col-xs-12 vehicle_type">
							<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_type selector_type" id="type_1" value="">全部</div>
							<%int count = 1; %>
							<c:forEach items="${carTypeList}" var="carType">
							<%count++; %>
							     <div class="col-md-1 col-lg-1 col-sm-1 col-xs-1 v_type" id="type_<%=count %>" value="${carType.display}"><img class="vehicle_type_img" src="${ctx }${carType.decode}" style="width: 20px;height: 20px;"/>${carType.display}</div>
							</c:forEach>
						</div>
						<!--<div class="col-xs-1"></div>-->
					</div>
				</div>
				<div class="vehicle_pannel2">
					<div class="col-xs-48 col-lg-48 col-sm-48 col-xs-48" id="vehicle_data">
						<c:choose>
							<c:when test="${fn:length(vehicleStateList)==0}">
								<font color="red">没有匹配的车辆信息</font>
							</c:when>
							<c:otherwise>
								<c:forEach var="vehicle" items="${vehicleStateList}">
									<div class='col-md-1 col-lg-1 col-sm-1 col-xs-1 '>
										<div href='#' class='dropdown-hover'>
												<img class='vehicle_img' src='${vehicle.photoUrl}'/><br>${vehicle.licenseno}
												<ul class='dropdown-menu dropdown-danger' id='vehicle_bounces'>
													<li><a href="javascript:addTabs({id:'140001',title: '用车记录',close: true,url: '${ctx }/dailyOutBackCar/listCarOut'});">用车记录</a></li>
													<li><a href="javascript:addTabs({id:'140003',title: '加油记录',close: true,url: '${ctx }/addoilRecord/listFuelDiary'});">加油记录</a></li>
													<li><a href="javascript:addTabs({id:'140004',title: '维修记录',close: true,url: '${ctx }/repairRecord/listRepairRecords'});">维修记录</a></li>
													<li><a href="javascript:addTabs({id:'140006',title: '规费管理',close: true,url: '${ctx }/dailyHandle/listFeesManage'});">规费管理</a></li>
													<li><a href="javascript:addTabs({id:'140005',title: '维修取车',close: true,url: '${ctx }/dailyHandle/listDispatchRecord'});">维修取车</a></li>
													<li><a href="javascript:addTabs({id:'140005',title: '车辆位置',close: true,url: '${ctx }/gateposition/carLocation'});">车辆位置</a></li>
												</ul>
										</div>
									</div>
								</c:forEach>
							</c:otherwise> 
						</c:choose>
					</div>

				</div>
				<div id="pages_count" class="pageFooter_left">
					<c:if test="${pageBean.totalCount gt 0}">
						<span>${pageBean.beginIndex + 1} - ${pageBean.currentCount}  共${pageBean.totalCount}条</span>
					</c:if>
					<c:if test="${pageBean.totalCount eq 0} ">
						<span>${pageBean.currentCount} - ${pageBean.currentCount}  共${pageBean.totalCount}条</span>
					</c:if>
				</div>
				<div class="pageFooter">
					<select id="pageSizeSel" class="pageSizeSel" title="每页显示数量" onchange="javascript:gotoPageSize(this.options[this.options.selectedIndex].value);">
						<option role="option" value="5" selected="selected">5</option>
						<option role="option" value="10">10</option>
						<option role="option" value="20">20</option>
						<option role="option" value="30">30</option>
						<option role="option" value="40">40</option>
						<option role="option" value="50">50</option>
					</select>
				</div>
				<div id="pages" class="pageFooter">
					<a href="javascript:gotoPage(${pageBean.firstPage});" class="vehicle_page" title="首页"><span class="glyphicon glyphicon-step-backward"></span></a>
					<a href="javascript:gotoPage(${pageBean.previousPage});" class="vehicle_page" title="上一页"><span class="glyphicon glyphicon-backward"></span></a>
					<input type="text" size="2" maxlength="9" value="${pageBean.currentPage}"; id="inputPage" onkeydown="if(event.keyCode==13)gotoPage(this.value)">共${pageBean.totalPage}页
					<a href="javascript:gotoPage(${pageBean.nextPage});" class="vehicle_page" title="下一页"><span class="glyphicon glyphicon-forward"></span></a>
					<a href="javascript:gotoPage(${pageBean.lastPage});" class="vehicle_page" title="尾页"><span class="glyphicon glyphicon-step-forward"></span></a><br/>
					<input type="hidden" id="current_page" value="1">
				</div>

			</div>
		</div>

		<script src="${ctx }/components/jquery/dist/jquery.js"></script>

<script src="${ctx }/components/jquery.1x/dist/jquery.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${ctx }/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>" + "<" + "/script>");
		</script>

		<script type="text/javascript">
			//跳转页面
			function toTab(status) {
// 				直接在本页面跳转
				if (status==1 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140001' aria-controls='tab_140001' role='tab' data-toggle='tab'>出车记录 <i class='glyphicon glyphicon-remove' tabclose='tab_140001'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/dailyOutBackCar/listCarOut";
				}
				if (status==2 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140003' aria-controls='tab_140003' role='tab' data-toggle='tab'>加油记录 <i class='glyphicon glyphicon-remove' tabclose='tab_140003'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/addoilRecord/listFuelDiary";
				}
				if (status==3 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140004' aria-controls='tab_140004' role='tab' data-toggle='tab' aria-expanded='true'>维修记录 <i class='glyphicon glyphicon-remove' tabclose='tab_140004'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/repairRecord/listRepairRecords";
				}
				if (status==4 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140006' aria-controls='tab_140006' role='tab' data-toggle='tab' aria-expanded='true'>规费管理 <i class='glyphicon glyphicon-remove' tabclose='tab_140006'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/dailyHandle/listFeesManage";
				}
				if (status==5 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140002' aria-controls='tab_140002' role='tab' data-toggle='tab' aria-expanded='true'>回车记录 <i class='glyphicon glyphicon-remove' tabclose='tab_140002'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/dailyOutBackCar/listCarBack";
				}
				if (status==6 ) {
					$('.nav-tabs li#tab_tab_130001',window.parent.document).html("<a href='#tab_140005' aria-controls='tab_140005' role='tab' data-toggle='tab'>维修取车 <i class='glyphicon glyphicon-remove' tabclose='tab_140005'></i></a>");
					location.href = "http://localhost:8080/carTravelsky/dailyHandle/listDispatchRecord";
				}
				if (status==7 ) {
					alert("未开发的页面");
				}
			}	
			
			//翻页
			function gotoPage(current_page) {
				$.ajax({
					type:"POST",
					url:"selectorVehicles",
					data:{
						"state":$(".selector_state").attr("value"),
						"type":$(".selector_type").attr("value"),
						"pageSize":$(".pageSizeSel").val(),
						"current_page":current_page
					},
					dataType:"json",
					success:function(jsonData){
						var beginIndex = parseInt(jsonData.pageBean.beginIndex)+1;
						var currentCount = parseInt(jsonData.pageBean.currentCount);
						var pageSize = parseInt(jsonData.pageBean.pageSize);
						var currentPage = parseInt(jsonData.pageBean.currentPage);
						var totalCount = parseInt(jsonData.pageBean.totalCount);
						var endIndex = currentCount >= pageSize ? currentCount + currentPage * pageSize - pageSize : totalCount;
						if (totalCount > 0) {
							$("#pages_count").html("<span>"+beginIndex+" - "+endIndex+"  共"+totalCount+"条</span>");
						} else {
							$("#pages_count").html("<span>"+0+" - "+0+"  共"+0+"条</span>");
						}
						$("#pages").html("<a href='javascript:gotoPage("+jsonData.pageBean.firstPage+");' class='vehicle_page'  title='首页'><span class='glyphicon glyphicon-step-backward'></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.previousPage+");' class='vehicle_page'  title='上一页'><span class='glyphicon glyphicon-backward'></span></a>"+
								"<input type='text' size='2' maxlength='7' value='"+jsonData.pageBean.currentPage+"'; id='inputPage' onkeydown='if(event.keyCode==13)gotoPage(this.value)'>共"+jsonData.pageBean.totalPage+"页"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.nextPage+");' class='vehicle_page'  title='下一页'><span class='glyphicon glyphicon-forward'></span></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.lastPage+");' class='vehicle_page'  title='尾页'><span class='glyphicon glyphicon-step-forward'></span></a><br/>"+
								"<input type='hidden' id='current_page' value="+jsonData.pageBean.currentPage+">");
						if (jsonData.results.length>0) {
							$("#vehicle_data").html("");
							for(i in jsonData.results){
								$("#vehicle_data").append("<div class='col-md-1'><div href='#' class='dropdown-hover' id>"+
										"<img class='vehicle_img' src='"+jsonData.results[i].photoUrl+"'/><br>"+jsonData.results[i].licenseno+""+
										"<ul class='dropdown-menu dropdown-danger' id='vehicle_bounces'>"+
										"<li><a href='javascript:toTab(1);'>用车记录</a></li>"+
										"<li><a href='javascript:toTab(2);'>加油记录</a></li>"+
										"<li><a href='javascript:toTab(3);'>维修记录</a></li>"+
										"<li><a href='javascript:toTab(4);'>规费管理</a></li>"+
										
										"<li><a href='javascript:toTab(6);'>维修取车</a></li>"+
										"<li><a href='javascript:toTab(7);'>车辆位置</a></li></ul></div></div>");
							}
						}else {
							$("#vehicle_data").html("<font color='red'>未查询到相关车辆数据</font>");
						}
					}
				});
			}
			
			//选择每页显示数量
			function gotoPageSize(pageSizeSel) {
				var pageSizeSel = document.getElementById('pageSizeSel').options[document.getElementById('pageSizeSel').selectedIndex].value;
				$.ajax({
					type:"POST",
					url:"selectorVehicles",
					data:{
						"state":$(".selector_state").attr("value"),
						"type":$(".selector_type").attr("value"),
						"pageSize":$(".pageSizeSel").val(),
						"current_page":1
					},
					dataType:"json",
					success:function(jsonData){
						var beginIndex = parseInt(jsonData.pageBean.beginIndex)+1;
						var currentCount = parseInt(jsonData.pageBean.currentCount);
						var pageSize = parseInt(jsonData.pageBean.pageSize);
						var currentPage = parseInt(jsonData.pageBean.currentPage);
						var totalCount = parseInt(jsonData.pageBean.totalCount);
						var endIndex = currentCount >= pageSize ? currentCount + currentPage * pageSize - pageSize : totalCount;
						if (totalCount > 0) {
							$("#pages_count").html("<span>"+beginIndex+" - "+endIndex+"  共"+totalCount+"条</span>");
						} else {
							$("#pages_count").html("<span>"+0+" - "+0+"  共"+0+"条</span>");
						}
						$("#pages").html("<a href='javascript:gotoPage("+jsonData.pageBean.firstPage+");' class='vehicle_page'  title='首页'><span class='glyphicon glyphicon-step-backward'></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.previousPage+");' class='vehicle_page'  title='上一页'><span class='glyphicon glyphicon-backward'></span></a>"+
								"<input type='text' size='2' maxlength='7' value='"+jsonData.pageBean.currentPage+"'; id='inputPage' onkeydown='if(event.keyCode==13)gotoPage(this.value)'>共"+jsonData.pageBean.totalPage+"页"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.nextPage+");' class='vehicle_page'  title='下一页'><span class='glyphicon glyphicon-forward'></span></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.lastPage+");' class='vehicle_page'  title='尾页'><span class='glyphicon glyphicon-step-forward'></span></a><br/>"+
								"<input type='hidden' id='current_page' value="+jsonData.pageBean.currentPage+">");
						if (jsonData.results.length>0) {
							$("#vehicle_data").html("");
							for(i in jsonData.results){
								$("#vehicle_data").append("<div class='col-md-1'><div href='#' class='dropdown-hover' id>"+
										"<img class='vehicle_img' src='"+jsonData.results[i].photoUrl+"'/><br>"+jsonData.results[i].licenseno+""+
										"<ul class='dropdown-menu dropdown-danger' id='vehicle_bounces'>"+
										"<li><a href='javascript:toTab(1);'>用车记录</a></li>"+
										"<li><a href='javascript:toTab(2);'>加油记录</a></li>"+
										"<li><a href='javascript:toTab(3);'>维修记录</a></li>"+
										"<li><a href='javascript:toTab(4);'>规费管理</a></li>"+
										"<li><a href='javascript:toTab(6);'>维修取车</a></li>"+
										"<li><a href='javascript:toTab(7);'>车辆位置</a></li></ul></div></div>");
							}
						}else {
							$("#vehicle_data").html("<font color='red'>未查询到相关车辆数据</font>");
						}
					}
				});
			}
			
			$(".v_state").click(function(e){
				var state = $(this).attr("id");
				$(".v_state").removeClass("selector_state");
				$("#"+state).addClass("selector_state");
				$("#current_page").val("1");
				$("#searchStr").val("");
			});
			
			$(".v_type").click(function(e){
				var type = $(this).attr("id")
				$(".v_type").removeClass("selector_type");
				$("#"+type).addClass("selector_type");
				$("#current_page").val("1");
				$("#searchStr").val("");
			});
			
			//按车辆状态、车辆类型嵌套查询
			$(".v_state,.v_type,#serchBtn").bind("click",function(){
				$.ajax({
					type:"POST",
					url:"selectorVehicles",
					data:{
						"state":$(".selector_state").attr("value"),
						"type":$(".selector_type").attr("value"),
						"searchStr":$("#searchStr").val(),
						"pageSize":$(".pageSizeSel").val(),
						"current_page":1
					},
					dataType:"json",
					success:function(jsonData){
						var beginIndex = parseInt(jsonData.pageBean.beginIndex)+1;
						var currentCount = parseInt(jsonData.pageBean.currentCount);
						var pageSize = parseInt(jsonData.pageBean.pageSize);
						var currentPage = parseInt(jsonData.pageBean.currentPage);
						var totalCount = parseInt(jsonData.pageBean.totalCount);
						var endIndex = currentCount >= pageSize ? currentCount + currentPage * pageSize - pageSize : totalCount;
						if (totalCount > 0) {
							$("#pages_count").html("<span>"+beginIndex+" - "+endIndex+"  共"+totalCount+"条</span>");
						} else {
							$("#pages_count").html("<span>"+0+" - "+0+"  共"+0+"条</span>");
						}
						$("#pages").html("<a href='javascript:gotoPage("+jsonData.pageBean.firstPage+");' class='vehicle_page' title='首页'><span class='glyphicon glyphicon-step-backward'></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.previousPage+");' class='vehicle_page' title='上一页'><span class='glyphicon glyphicon-backward'></span></a>"+
								"<input type='text' size='2' maxlength='7' value='"+jsonData.pageBean.currentPage+"'; id='inputPage' onkeydown='if(event.keyCode==13)gotoPage(this.value)'>共"+jsonData.pageBean.totalPage+"页"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.nextPage+");' class='vehicle_page' title='下一页'><span class='glyphicon glyphicon-forward'></span></a>"+
								"<a href='javascript:gotoPage("+jsonData.pageBean.lastPage+");' class='vehicle_page' title='尾页'><span class='glyphicon glyphicon-step-forward'></span></a><br/>"+
								"<input type='hidden' id='current_page' value="+jsonData.pageBean.currentPage+">");
						if (jsonData.results.length>0) {
							$("#vehicle_data").html("");
							for(i in jsonData.results){
								$("#vehicle_data").append("<div class='col-md-1'><div href='#' class='dropdown-hover' id><img class='vehicle_img' src='"+jsonData.results[i].photoUrl+"'/><br>"+jsonData.results[i].licenseno+""+
										"<ul class='dropdown-menu dropdown-danger' id='vehicle_bounces'>"+
										"<li><a href='javascript:toTab(1);'>出车记录</a></li>"+
										"<li><a href='javascript:toTab(2);'>加油记录</a></li>"+
										"<li><a href='javascript:toTab(3);'>维修记录</a></li>"+
										"<li><a href='javascript:toTab(4);'>规费管理</a></li>"+
										"<li><a href='javascript:toTab(5);'>回车记录</a></li>"+
										"<li><a href='javascript:toTab(6);'>维修取车</a></li>"+
										"<li><a href='javascript:toTab(7);'>车辆位置</a></li></ul></div></div>");
							}
						}else {
							$("#vehicle_data").html("<font color='red'>未查询到相关车辆数据</font>");
						}
					}
				});
			});
			
			var addTabs = function(options) {
				id = "tab_" + options.id;
				$(".active",window.parent.document).removeClass("active");

				// 如果TAB不存在，创建一个新的TAB
				if (!$("#" + id)[0],window.parent.document) {
					// 固定TAB中IFRAME高度
					mainHeight = $(document).height()-50;
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
					$(".nav-tabs",window.parent.document).append(title);
					$(".tab-content",window.parent.document).append(content);
				}
				else{
					closeTab(id);
					// 固定TAB中IFRAME高度
					mainHeight = $(document).height()-50;
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
					$(".nav-tabs",window.parent.document).append(title);
					$(".tab-content",window.parent.document).append(content);
				}
				// 激活TAB
				$("#tab_" + id,window.parent.document).addClass('active');
				$("#" + id,window.parent.document).addClass("active");
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
				mainHeight = $(document).height()-50;
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
		</div>
	</body>

</html>