<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增汽车事故记录</title>
<%@ include file="../common/header.jsp"%>
<script type="text/javascript" src="${ctx}/components/layui/layui.js"></script>
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
							<form class="form-horizontal" role="form" id="submitForm">
								<input value="${recode.id}" name="id" type="hidden" />
								<div class="row">
									<div class="col-xs-6 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">车牌号：</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="carID" select2=""
												name="carID" data-type="${recode.licenseno}" data-value="${recode.licenseno}"
												data-url="${ctx}/common/getLicenseno"
												style="width: 163px">
												<c:if test="${not empty recode.driver}">
													<option selected="selected" value="${recode.carID}">${recode.licenseno}</option>
												</c:if>
											</select>
										</div>
									</div>
									<div class="col-xs-6 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">驾驶员：</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="driver" select2=""
												name="driver" data-type="" data-value=""
												data-url="${ctx}/common/getSelect2ListDriver"
												style="width: 163px">
												<c:if test="${not empty recode.staffName}">
													<option selected="selected" value="${recode.driver}">${recode.staffName}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class=" col-xs-3 control-label no-padding-right"
											for="form-field-1">我方承担金额</label>
										<div class="col-xs-9">
											<input type="text" id="webearMoney" name="webearMoney"
												value="${recode.webearMoney}" placeholder="单位（￥）"
												class="validate[required,custom[positive]] col-xs-10 col-sm-5 ;  " />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1-1">对方承担金额:</label>
										<div class="col-xs-9">
											<input type="text" id="opbearMoney" name="opbearMoney"
												value="${recode.opbearMoney}" placeholder="单位（￥）"
												class="validate[custom[positive]] col-xs-10 col-sm-5 ;  validate[required min[0]]" />
										</div>
									</div>
									
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1-1">我方损失:</label>
										<div class="col-xs-9">
											<input type="text" id="webearLoss" name="webearLoss"
												value="${recode.webearLoss}" placeholder="单位（￥）"
												class="validate[custom[positive]] col-xs-10 col-sm-5 ;  validate[required min[0]]" />
										</div>
									</div>
									
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1-1">对方损失:</label>
										<div class="col-xs-9">
											<input type="text" id="opbearLoss" name="opbearLoss"
												value="${recode.opbearLoss}" placeholder="单位（￥）"
												class="validate[custom[positive]] col-xs-10 col-sm-5 ;  validate[required min[0]]" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">事故日期:</label>
										<div class="col-xs-9">
											<c:choose>
												<c:when test="${recode.accidentDate==null}">
													<input type="text" id="accidentDate" name="accidentTime"
														class="validate[required]  col-xs-10 col-sm-5">
													</input>
												</c:when>
												<c:otherwise>
													<input type="text" id="accidentDate" name="accidentTime"
														class="validate[required]  col-xs-10 col-sm-5"
														value='<fmt:formatDate value='${recode.accidentDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
												</c:otherwise>
											</c:choose>
										</div>
									</div>

									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">保险赔偿金额:</label>
										<div class="col-xs-9">
											<input type="text" id="insuranceMoney" name="insuranceMoney"
												value="${recode.insuranceMoney}" placeholder="单位（￥）"
												class="validate[required,custom[positive] ]   col-xs-10 col-sm-5" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">发生地点:</label>
										<div class="col-xs-9">
											<input type="text" id="happenAddress" name="happenAddress"
												class="validate[required]" value="${recode.happenAddress}" />
										</div>
									</div>

								</div>

								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">事故说明:</label>

										<div class="col-xs-9">
											<textarea id="accidentExplain" name="accidentExplain"
												class="validate[required,maxSize[64]] autosize-transition form-control">${recode.accidentExplain}</textarea>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">我方情况:</label>

										<div class="col-xs-9">
											<textarea id="ourSituation" name="ourSituation"
												class="validate[required,maxSize[64]] autosize-transition form-control">${recode.ourSituation}</textarea>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">对方情况:</label>

										<div class="col-xs-9">
											<textarea id="otherSituation" name="otherSituation"
												class="validate[required ,maxSize[64]] autosize-transition form-control">${recode.otherSituation}</textarea>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">处理结果:</label>
										<div class="col-xs-9">
											<textarea id="treatmentResult" name="treatmentResult"
												class="validate[required,maxSize[64]] autosize-transition form-control">${recode.treatmentResult}</textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">备注:</label>

										<div class="col-xs-9">
											<textarea id="remark" name="remark"
												class=" validate[maxSize[128]] autosize-transition form-control ">${recode.remark}</textarea>
										</div>
									</div>

								</div>

							</form>
							<div class="row">
								<div class="col-xs-12" style="text-align: center;">
									<button class="btn btn-white btn-default btn-round"
										style="margin-right: 50px;" id="save">
										<i class="ace-icon fa fa-check red2"></i> 确定
									</button>
									<button id="cancel" class="btn btn-white btn-default btn-round"
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
	$(function() {

		/* 校验表单 */
		$('#submitForm').validationEngine();
		/* 异步提交表单 */
		$('#save')
				.click(
						function() {
							if (!$('#submitForm').validationEngine('validate')) {
								return;
							}
							$.ajax({
										type : 'POST',
										datatype : 'text',
										url : '${ctx}/carAccident/saveCarDailyAccidentRecord',
										data : $('#submitForm').serialize(),
										contentType : 'application/x-www-form-urlencoded; charset=utf-8',
										success : function(data) {
											layer.msg(data.message,{
												icon : 1
											});
											if(data.flag)
											setTimeout('closeLayer()', 1000);
										},
										error : function(data) {
											layer.msg(data.message,{
												    icon : 2
											});
											/* setTimeout('closeLayer()', 1000); */
										}
									});
						});
		/* 表单提交取消 */
		$('#cancel').click(function() {
			closeLayer();
		});
		laydate.render({
			elem : '#accidentDate',
			type : 'datetime',

		});
	});

	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}

	$.loadSelect2();
</script>
</html>