<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>出车申请</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form id="deptForm" class="form form-horizontal" role="form">
								<input type="hidden" name="carID" class="text"
									readonly="readonly" value="${carId}" /> <input type="hidden"
									name="deptID" class="text" readonly="readonly"
									value="${deptID}" />
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-12 control-label no-padding-right"
											for="deptCode">预计用车时间:</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<div class="col-xs-5">
											<input name="outcarTime" value="" type="text" id="outcarTime"
												class="validate[required] text-input" />
										</div>
										 <label class="col-xs-1" style="text-align: center;">至</label>
										<div class="col-xs-6">
											<input name="estimateReturnTime" value="" type="text"
												id="estimateReturnTime"
												class="validate[required] text-input" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="stffID">驾驶员:</label>
										<div class="col-xs-9">
											<select id="stffID" select2="" style="width: 160px"
												name="stffID" 
												data-type="${carBaseVehicleDO.brandName}"
												data-value="${carBaseVehicleDO.brandName}"
												data-url="${ctx}/common/getSelect2ListDriver?deptId=53"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="desitination">目的地:</label>
										<div class="col-xs-9">
											<input name="desitination" value="" type="text" width="160px"
												id="desitination" class="validate[required] text-input" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="entourage">随行人员:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="entourage"
												style="width: 160px" name="entourage" multiple="multiple"
												data-type=""
												data-value=""
												data-url="${ctx}/common/getSelect2ListStaffGoAlong"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="outRemark">用车事由:</label>
										<div class="col-xs-9">
											<textarea name="outRemark" value="" id="outRemark"
												class="validate[required] text-input"></textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="destArea">区域:</label>
										<div class="col-xs-9">
											<select id="destArea"  style="width: 160px"
													name="destArea" 
													data-type=""
													data-value=""
													data-url="${ctx}/outOfCar/getaddress"></select>
										</div>
									</div>
								</div>
							</form>
							<div class="row">
								<div class="col-xs-12" style="text-align: center;">
									<button id="btn_comfirm"
										class="btn btn-white btn-default btn-round"
										style="margin-right: 50px;">
										<i class="ace-icon fa fa-check red2"></i> 确定
									</button>
									<button id="btn_cancel"
										class="btn btn-white btn-default btn-round"
										style="margin-left: 50px;">
										<i class="ace-icon fa fa-times red2"></i> 取消
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$.validationEngineLanguage.allRules.deptPhone = {
		"regex" : /^1[3|4|5|7|8][0-9]{9}$|^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/,
		"alertText" : "* 请输入正确的电话号码"
	};
	$(function() {
		var currentDate=new Date();
		
		// 出车时间，默认现在
		laydate.render({
			elem : '#outcarTime',
			type : 'datetime',
			value : currentDate,
			done : function(value, date) {
				$('#outcarTime').blur();
			}
		});
		currentDate.setTime(currentDate.setHours(currentDate.getHours() + parseInt("${codeMaster.code}")));
		// 预计回车时间
		laydate.render({
			elem : '#estimateReturnTime',
			type : 'datetime',
			value : currentDate,
			done : function(value, date) {
				$('#estimateReturnTime').blur();
			}
		});

		/* 校验表单 */
		$('#deptForm').validationEngine();
		/* 异步提交表单 */
		$('#btn_comfirm')
				.click(
						function() {
							if (!$('#deptForm').validationEngine('validate')) {
								return;
							}
							fdata = $('#deptForm').serialize();
							console.log(fdata);
							$
									.ajax({
										type : 'POST',
										datatype : 'text',
										url : '${ctx}/outOfCar/applyCar',
										data : $('#deptForm').serialize(),
										contentType : 'application/x-www-form-urlencoded; charset=utf-8',
										success : function(data) {
											layer.msg(data.message);
											if (data.message == "操作出车申请成功！") {
												setTimeout('closeLayer()', 2000);
											}
										},
										error : function(data) {
											layer.msg(data.message);
											//setTimeout('closeLayer()',2000);
										}
									});
						});
		/* 表单提交取消 */
		$('#btn_cancel').click(function() {
			closeLayer();
		});
	});

	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}

	//加载select
	$.loadProjectattr($("#principal"));
	$.loadProjectattr($("#entourage"));
	$.loadProjectattr($("#stffID"));
	$.loadProjectattr($("#destArea"));
	$('#adress').val(['1']).trigger('change');//
</script>
<style>
#desitination{
	margin-left: 0px;
	margin-right: 0px;
	padding-left: 0px;
	padding-right: 0px;
}
#outRemark{
width: 160px;
}
</style>
</html>