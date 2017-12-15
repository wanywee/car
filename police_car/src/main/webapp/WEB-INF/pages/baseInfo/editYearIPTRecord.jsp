<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增年检记录</title>
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
											for="form-field-select-3">车&nbsp;牌&nbsp;号&nbsp;&nbsp;：</label>
										<div class="col-xs-9">
											<select class="validate[required,maxSize[11]]" id="carID"
												select2="" name="carID" data-type=""
												data-value="${recode.carID}"
												data-url="${ctx}/common/getSelect2ListLicenseno"
												style="width: 163px">
												<c:if test="${not empty recode.carID}">
													<option selected="selected" value="${recode.carID}">${recode.licenseno}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">年&nbsp;检&nbsp;号&nbsp;&nbsp;：</label>
										<div class="col-xs-9">
											<input type="text" id="yearIptNumber" name="yearIptNumber"
												class="validate[required,maxSize[32]]  col-xs-10 col-sm-5"
												value="${recode.yearIptNumber}" />
										</div>
									</div>
									<div class="col-xs-6 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">经&nbsp;手&nbsp;人&nbsp;&nbsp;：</label>
										<div class="col-xs-9">
											<select class="validate[required,maxSize[12]]" id="handler"
												select2="" name=handler data-type=""
												data-value="${recode.handler}"
												data-url="${ctx}/common/getSelect2ListStaff" style="width: 163px">
												<c:if test="${not empty recode.staffName}">
													<option selected="selected" value="${recode.handler}">${recode.staffName}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 form-group">
										<!-- #section:plugins/input.chosen -->
										<label class="col-xs-3 control-label no-padding-right "
											for="form-field-select-3">年检费用：</label>
										<div class="col-xs-9">
											<input type="text" id="yearIptMoney" name="yearIptMoney"
												class="validate[required,custom[positive]]  col-xs-10 col-sm-5"
												value="${recode.yearIptMoney}" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">年检日期&nbsp;&nbsp;&nbsp;:</label>
										<div class="col-xs-9">
											<c:choose>
												<c:when test="${recode.yearIptDate==null}">
													<input type="text" id="yearIPTDate" name="yearIPTDate"
														class="validate[required]  col-xs-10 col-sm-5">
													</input>
												</c:when>
												<c:otherwise>
													<input type="text" id="yearIPTDate" name="yearIPTDate"
														class="validate[required]  col-xs-10 col-sm-5"
														value='<fmt:formatDate value='${recode.yearIptDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">到期日期&nbsp;&nbsp;&nbsp;:</label>
										<div class="col-xs-9">
											<c:choose>
												<c:when test="${recode.endDate==null}">
													<input type="text" id="EndDate" name="EndDate"
														class="validate[required]  col-xs-10 col-sm-5">
													</input>
												</c:when>
												<c:otherwise>
													<input type="text" id="EndDate" name="EndDate"
														class="validate[required]  col-xs-10 col-sm-5"
														value='<fmt:formatDate value='${recode.endDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="start">车&nbsp;管&nbsp;所&nbsp;&nbsp;&nbsp; &nbsp;:</label>
										<div class="col-xs-9">
											<%-- <input type="text" id="unitID" name="unitID"
												class="validate[required]" value="${recode.companyName}" /> --%>
											<select class="validate[required,maxSize[11]]"
												id="vehicleManage" select2="" name="vehicleManage"
												data-type=""
												data-value="${recode.vehicleManage}"
												data-url="${ctx}/common/getSelect2ListContactCompany?kind=JFDW"
												style="width: 163px">
												<c:if test="${not empty recode.companyName}">
													<option selected="selected" value="${recode.vehicleManage}">${recode.companyName}</option>
												</c:if>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label for="uploadImg" class="col-xs-2 control-label">上传照片 &nbsp;&nbsp;:&nbsp; &nbsp; &nbsp;&nbsp;</label>
										<div class="col-xs-10">
											<input id="file-1" type="file" multiple class="file"
												data-overwrite-initial="false" data-min-file-count="2">
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="form-field-1">备&nbsp; &nbsp; &nbsp;注&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;:</label>

										<div class="col-xs-9">
											<textarea id="remark" name="remark"
												class=" validate[maxSize[128]] autosize-transition form-control  ">${recode.remark}</textarea>
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
										url : '${ctx}/yearIPTRecord/saveCarDailyYearIPTRecord',
										data : $('#submitForm').serialize()+ "&"
										+ "photoUrl=" + photoUrl,
										contentType : 'application/x-www-form-urlencoded; charset=utf-8',
										beforeSend : function() {
											if (photoUrl == "" && photoUrlString == "") {
												layer.msg("请先上传照片！");
												return false;
											}
										},
										success : function(data) {
											layer.msg(data.message, {
												icon : 1
											});
											setTimeout('closeLayer()', 1000);
										},
										error : function(data) {
											layer.msg(data.message, {
												icon : 2
											});
											setTimeout('closeLayer()', 1000);
										}
									});
						});
		/* 表单提交取消 */
		$('#cancel').click(function() {
			closeLayer();
		});
		laydate.render({
			elem : '#yearIPTDate',
			type : 'datetime',

		});
		laydate.render({
			elem : '#EndDate',
			type : 'datetime',

		});
	});

	/* 图片地址字符串转单个数组 */
	var photoUrlString = "${recode.photoUrl}";
	var photoUrls = new Array();
	photoUrls = photoUrlString.split(",");
	photoUrls.pop();
	/* 回显图片 */
	function showPhoto() {
		var showPhoto = new Array();
		for (var i = 0; i < photoUrls.length; i++) {
			showPhoto[i] = "<img src='" + baseURL + '/'
					+ photoUrls[i] + "' class='file-preview-image'>";
		}
		return showPhoto;
	}

	/* 配置回显图片参数 */
	function preConfigList() {
		var preConfigList = new Array();
		for (var i = 0; i < photoUrls.length; i++) {
			// 图片地址string
			var array_element = photoUrls[i];
			var ar = array_element.split("/");
			console.log(array_element);
			var tjson = {
				caption : ar.pop(), // 展示的文件名  
				width : '100px',
				//url : '',
				url : '${ctx}/yearIPTRecord/fileDelete?id=${recode.id}' + '&imgPath=' + array_element, // 删除url  
				key : '${recode.id}' // 删除是Ajax向后台传递的参数  
			};
			preConfigList[i] = tjson;
		}
		return preConfigList;
	}

	/* 上传照片 */
	var photoUrl = "";
	$("#file-1")
			.fileinput(
					{
						uploadUrl : '${ctx}/common/multipleFileUpload', // you must set a valid URL here else you will get an error
						allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
						overwriteInitial : false,
						dropZoneEnabled : false,
						maxFileSize : 2000,
						minFileCount : 3,
						maxFileCount : 10,
						//allowedFileTypes: ['image', 'video', 'flash'],
						slugCallback : function(filename) {
							return filename.replace('(', '_').replace(']', '_');
						},
						initialPreview : showPhoto(),
						initialPreviewConfig : preConfigList()
					}).on("fileuploaded",
					function(event, data, previewId, index) {
						photoUrl += data.response.result.msg + ",";
					}).on("filepredelete", function(event, key, jqXHR, data) {  
	                    console.log('Key = ' + key);
	                    console.log(jqXHR);
	                    console.log(data);
					});
	
	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}

	$.loadSelect2();
</script>
</html>