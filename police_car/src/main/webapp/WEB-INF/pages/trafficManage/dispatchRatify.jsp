<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车务管理</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet"
	href="${ctx }/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${ctx }/js/plugins/ztree/js/jquery.ztree.all.min.js"></script>

<style type="text/css">
ul {
	display: block;
	list-style: none;
	padding-left: 6px;
	z-index: 9999;
}
</style>
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<!-- layer弹出框二级联动 -->
					<form id="deptForm" class="form form-horizontal" role="form">
						<input type="hidden" id= "id" name="id" class="text" readonly="readonly"
									value="${carDailyOutRecordDO.id}" />
						<input type="hidden" id= "carID" name="carID" class="text" readonly="readonly"
									value="${carDailyOutRecordDO.carID}" />
						<div style="padding: 10px;">
							<div>
								驾驶员： <select id="driver" select2="" style="width: 160px"
									name="driver" data-type="${carDailyOutRecordDO.driver}"
									data-value="${carDailyOutRecordDO.stffID}" data-url="${ctx}/common/getSelect2ListDriver?deptId=53"></select>
							</div>
							<div style="margin-top: 10px">
								申请车辆： <select id="deptName" select2="" style="width: 160px"
									name="deptID" data-type="${carDailyOutRecordDO.vehicleDept}"
									data-value="${carDailyOutRecordDO.deptID}" data-url="${ctx}/common/getSelect2ListDeptment" ></select>
								<select id="licenseno" select2="" style="width: 160px"
									name="licenseno" data-type="${carDailyOutRecordDO.licenseno}"
									data-value="${carDailyOutRecordDO.carID}"<%-- data-url="${ctx}/common/getSelect2ListDriver" --%>></select>
							</div>

							<!-- 车辆树形加载 -->
							<div class="ibox-content">
								<div class="">
									<div class="zTreeDemoBackground left">
										<ul id="treeDemo" class="ztree"></ul>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div style="text-align: center; padding: 10px;">
						<button id="btn_comfirm" type="button" class="btn btn-2">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$.loadProjectattr($("#deptName"));
	$.loadProjectattr($("#driver"));
	$.loadProjectattr($("#licenseno"));

	var FdeptId = $("#deptName").val();
	var FcarId = $("#licenseno").val();
	var FcarText = $("#licenseno").text();
	
	//绑定字典内容到指定的Select控件
	function BindSelect(ctrlName, url, deptId) {
		var control = $('#' + ctrlName);
		//设置Select2的处理
		control.select2({
			allowClear : true,
			escapeMarkup : function(m) {
				return m;
			}
		});

		//绑定Ajax的内容
		$.getJSON(url, deptId, function(data) {
			control.empty();//清空下拉框
			if(deptId == FdeptId) {
				control.append("<option value='" + FcarId + "'>&nbsp;"
						+ FcarText + "</option>");
			}
			$.each(data, function(i, item) {
				control.append("<option value='" + item.id + "'>&nbsp;"
						+ item.text + "</option>");
			});
		});
	}

	/* var setting = {
		async : {
			enable : true,
			url : "${ctx}/dispatchCenter/getZTreeCarList",//异步加载时的请求地址
			autoParam : [ "id" ],//提交参数
			type : 'post',
			dataType : 'json'
		},
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		checkable : true,
		showIcon : true,
		showLine : true,
		data : {
			simpleData : {
				enable : true
			}
		},
		expandSpeed : "",
		callback : {
			onClick : zTreeOnClick
		}
	};

	//单击时获取zTree节点的Id,和value的值
	function zTreeOnClick(event, treeId, treeNode, clickFlag) {
		var treeValue = treeNode.id + "," + treeNode.name;
		alert(treeNode.id + "," + treeNode.name);
	}; */

	$(function() {

		var deptId = $("#deptName").val();
		BindSelect("licenseno","${ctx}/common/getSelect2ListCarByDeptId?deptId="+ deptId, deptId);
		$("#deptName").on("change",function(e) {
			var deptId = $("#deptName").val();
			BindSelect("licenseno","${ctx}/common/getSelect2ListCarByDeptId?deptId="+ deptId, deptId);
		});

		/* 获取申请的车辆主键传到后台处理 */
		var carId = $("#carID").val();
		/* 车辆树形加载 */
		/* $.ajax({
			url : '${ctx}/dispatchCenter/getZTreeDeptment?carId=' + carId,
			type : 'get',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$.fn.zTree.init($("#treeDemo"), setting, data);
			}
		}); */

		/* 异步提交表单 */
		$('#btn_comfirm').click(function() {
			/* if (!$('#deptForm').validationEngine('validate')) {
				return;
			} */
			fdata = $('#deptForm').serialize();
			console.log(fdata);
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/dispatchCenter/approveApplyCar',
				data : $('#deptForm').serialize(),
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data) {
					layer.msg(data.message);
					if (data.flag) {
						setTimeout('closeLayer()', 2000);
					}
				},
				error : function(data) {
					setTimeout('closeLayer()', 2000);
					//setTimeout('closeLayer()',2000);
				}
			});
		});
	});
	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}
</script>
</body>
</html>