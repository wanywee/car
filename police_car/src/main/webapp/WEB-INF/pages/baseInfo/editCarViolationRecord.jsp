<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增汽车违章记录</title>
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
									<div class="col-xs-9 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">车牌号：</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="carID" select2=""
												name="carID" data-type="${recode.licenseno}" data-value="${recode.licenseno}"
												data-url="${ctx}/common/getLicenseno?status=1"
												style="width: 163px">
												<c:if test="${not empty recode.driver}">
													<option selected="selected" value="${recode.carID}">${recode.licenseno}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-9 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">驾驶员：</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="driver" select2=""
												name="driver" data-type="${recode.staffName}" data-value="${recode.staffName}"
												data-url="${ctx}/vehicleRecord/getListDriverByCompanyId"
												style="width: 163px">
												<c:if test="${not empty recode.driver}">
													<option selected="selected" value="${recode.driver}">${recode.staffName}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">违章项目：</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="violationProject" select2="" name="violationProject" 
												data-type="${recode.violationProject}" 
												data-value="${recode.violationProject}"
												data-url="${ctx}/getListDisplay?kind=VIOLATIONS_PROJECT"
												style="width: 163px">
												<c:if test="${not empty recode.violationProject}">
													<option selected="selected" value="${recode.violationProject}">${recode.violationProject}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
																<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">违章日期:</label>
										<div class="col-xs-9">
											<c:choose>
												<c:when test="${recode.violationDate==null}">
													<input type="text" id="violationDate" name="violationTime"
														class="validate[required]  col-xs-6 col-sm-5">
													</input>
												</c:when>
												<c:otherwise>
													<input type="text" id="violationDate" name="violationTime"
														class="validate[required]  col-xs-6 col-sm-5"
														value='<fmt:formatDate value='${recode.violationDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">违章地点:</label>
										<div class="col-xs-9">
											<input type="text" id="violationAddress" name="violationAddress"
												class="validate[required]  col-xs-10 col-sm-5" value="${recode.violationAddress}" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">罚款:</label>
										<div class="col-xs-9">
											<input type="text" id="fine" name="fine"
												value="${recode.fine}" placeholder="单位（￥）"
												class="validate[required,custom[positive] ]   col-xs-6 col-sm-5" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">扣分:</label>
										<div class="col-xs-9">
											<input type="text" id="points" name="points"
											value="${recode.points}"
											class="validate[required,custom[positive] ]   col-xs-6 col-sm-5" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">备注:</label>

										<div class="col-xs-9">
											<textarea id="remark" name="remark" style="width: 450px;"
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
							$
									.ajax({
										type : 'POST',
										datatype : 'text',
										url : '${ctx}/carViolation/addCarViolationRecord',
										data : $('#submitForm').serialize(),
										contentType : 'application/x-www-form-urlencoded; charset=utf-8',
										success : function(data) {
											layer.msg(data.message,{
												icon : 1
											});
											setTimeout('closeLayer()', 2000);
										},
										error : function(data) {
											layer.msg(data.message,{
												    icon : 2
											});
											setTimeout('closeLayer()', 2000);
										}
									});
						});
		/* 表单提交取消 */
		$('#cancel').click(function() {
			closeLayer();
		});
		laydate.render({
			elem : '#violationDate',
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