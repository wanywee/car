<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>维修记录-新增</title>
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
								<input type="hidden" name="id" class="text" readonly="readonly"
									value="${carDailyRepairRecord.id}" />
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="licenseno">车牌号:</label>
										<c:if test="${carDailyRepairRecord.id == null}">
											<div class="col-xs-9">
											<select class="validate[required]" id="licenseno" select2=""
												style="width: 160px" name="carID"
												data-type="${carDailyRepairRecord.licenseno}"
												data-value="${carDailyRepairRecord.carID}"
												data-url="${ctx}/common/getSelect2ListLicenseno?nowStatus=0"></select>
											</div>
										</c:if>
										<c:if test="${carDailyRepairRecord.id != null}">
											<div class="col-xs-9">
											<input type="hidden" name="carID" class="text" readonly="readonly" value="${carDailyRepairRecord.carID}" />
											<select class="validate[required]" id="licenseno" select2=""
												style="width: 160px" name="carID"
												data-type="${carDailyRepairRecord.licenseno}"
												data-value="${carDailyRepairRecord.carID}"
												data-url="${ctx}/common/getSelect2ListLicenseno?nowStatus=0" disabled="disabled"></select>
											</div>
										</c:if>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="handler">经手人:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="handler" select2=""
												style="width: 160px" name="handler"
												data-type="${carDailyRepairRecord.staffName}"
												data-value="${carDailyRepairRecord.handler}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="repairPd">修理厂:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="repairPd" select2=""
												style="width: 160px" name="repairPd"
												data-type="${carDailyRepairRecord.repairPdName}"
												data-value="${carDailyRepairRecord.repairPd}"
												data-url="${ctx}/common/getSelect2ListContactCompany?kind=WXZ"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="repairTime">送修日期:</label>
										<div class="col-xs-9">
											<input value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
            value="${carDailyRepairRecord.repairTime }"/>" type="text"
												name="repairTimeString" id="repairTime"
												class="validate[required]" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="estimateTackcar">预计取车:</label>
										<div class="col-xs-9">
											<input value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
            value="${carDailyRepairRecord.estimateTackcar }"/>" type="text"
												name="estimateTackcarString" id="estimateTackcar"
												class="validate[required]" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="repairReason">送修原因:</label>
										<div class="col-xs-9">
											<textarea name="repairReason" id="repairReason"
												class="autosize-transition form-control">${carDailyRepairRecord.repairReason}</textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label for="uploadImg" class="col-xs-2 control-label">上传发票:</label>
										<div class="col-xs-10">
											<input id="file-1" type="file" multiple class="file"
												data-overwrite-initial="false" data-min-file-count="2">
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="remark">备注:</label>
										<div class="col-xs-9">
											<textarea name="remark" id="remark"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${carDailyRepairRecord.remark}</textarea>
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
	$(function() {
		/* 送修日期 */
		laydate.render({
			elem : '#repairTime',
			type: 'datetime',
			value : new Date()
		});
		/* 预计取车 */
		laydate.render({
			elem : '#estimateTackcar',
			type: 'datetime'
		});
		
		/* 校验表单 */
		$('#deptForm').validationEngine();
		/* 异步提交表单 */
		$('#btn_comfirm').click(function() {
			if (!$('#deptForm').validationEngine('validate')) {
				return;
			}
			var dataParam = $('#deptForm').serialize() + "&"
			+ "photoUrl=" + photoUrl;
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/repairRecord/updateRepairRecord',
				data : dataParam,
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				//请求前进行图片上传验证
				beforeSend : function() {
					if (photoUrl == "" && photoUrlString == "") {
						layer.msg("请先上传照片！");
						return false;
					}
				},
				success : function(data) {
					layer.msg(data.message);
					if (data.message == "操作维修记录成功！") {
						setTimeout('closeLayer()',2000);
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
	
	/* 图片地址字符串转单个数组 */
	var photoUrlString = "${carDailyRepairRecord.photoUrl}";
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
				url : '${ctx}/common/repairRecordfileDelete?id=${carDailyRepairRecord.id}' + '&imgPath=' + array_element, // 删除url  
				key : '${carDailyRepairRecord.id}' // 删除是Ajax向后台传递的参数  
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
	
	//加载select
	$.loadProjectattr($("#licenseno"));
	$.loadProjectattr($("#handler"));
	$.loadProjectattr($("#repairPd"));
</script>
</html>