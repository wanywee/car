<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>车辆变更记录-新增</title>
<%@ include file="../../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/components/layui/css/layui.css" />
<style type="text/css">
.btn.btn-app.btn-primary.btn-round {
	text-align: center;
	width: 130px;
	height: 40px;
	font-size: 12px;
	line-height: 20px;
	padding: 0px;
}

input {
	width: 160px;
}

.layui-upload-img {
	width: 92px;
	height: 92px;
	margin: 0 10px 10px 0;
}
</style>
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
									value="${carBaseVehicleDO.id}" />
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="dreiver">原部门:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="dreiver" select2=""
												style="width: 160px" name="dreiver"
												data-type="${carBaseVehicleDO.dreiverName}"
												data-value="${carBaseVehicleDO.dreiver}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="dreiver">交车人:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="dreiver" select2=""
												style="width: 160px" name="dreiver"
												data-type="${carBaseVehicleDO.dreiverName}"
												data-value="${carBaseVehicleDO.dreiver}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="dreiver">接车人:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="dreiver" select2=""
												style="width: 160px" name="dreiver"
												data-type="${carBaseVehicleDO.dreiverName}"
												data-value="${carBaseVehicleDO.dreiver}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="buyTime">交接日期:</label>
										<div class="col-xs-9">
											<input value="<fmt:formatDate pattern="yyyy-MM-dd"
            value="${carBaseVehicleDO.buyTime }"/>" type="text"
												name="buyTimeString" id="buyTime"
												class="validate[required] text-input" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">交接内容:</label>
										<div class="col-xs-9">
											<textarea name="comments" id="comments"
												class="autosize-transition form-control">${carBaseVehicleDO.comments}</textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>
										<div class="col-xs-9">
											<textarea name="comments" id="comments"
												class="autosize-transition form-control">${carBaseVehicleDO.comments}</textarea>
										</div>
									</div>
								</div>
							</form>
						</div>
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
	<script type="text/javascript" src="${ctx }/components/layui/layui.js"></script>
</body>
<script type="text/javascript">
	$(function() {
		/* 车辆购入日期 */
		laydate.render({
			elem : '#buyTime',
			value : ''
		});
		
		/* 校验表单 */
		$('#deptForm').validationEngine();
		/* 异步提交表单 */
		$('#btn_comfirm').click(function() {
			if (!$('#deptForm').validationEngine('validate')) {
				return;
			}
			var dataParam = $('#deptForm').serialize() + "&"+ "photoUrl=" + photoUrl;
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/vehicleRecord/updateVehicleRecords',
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
					if (data.message == "操作车辆档案成功！") {
						setTimeout('closeLayer()', 2000);
					}
				},
				error : function(data) {
					layer.msg(data.message);
					//setTimeout('closeLayer()', 2000);
				}
			});
		});
		/* 表单提交取消 */
		$('#btn_cancel').click(function() {
			closeLayer();
		});

	});

	/* 车牌号校验 */
	$.validationEngineLanguage.allRules.carID = {  
    	"regex": /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}-[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/,  
        "alertText": "* 请输入正确的车牌号"
    }; 
	
	/* 发动机号校验 */
	$.validationEngineLanguage.allRules.engineno = {  
    	"regex": /^[a-zA-Z0-9]{8}$/,  
        "alertText": "* 请输入正确的发动机号"
    };
	
	/* 车架号校验 */
	$.validationEngineLanguage.allRules.chassisno = {  
    	"regex": /^[a-zA-Z0-9]{17}$/,  
        "alertText": "* 请输入正确的车架号"
    };
	
	/* 图片地址字符串转单个数组 */
	var photoUrlString = "${carBaseVehicleDO.photoUrl}";
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
				url : '${ctx}/common/fileDelete?id=${carBaseVehicleDO.id}' + '&imgPath=' + array_element // 删除url  
				//key : ${carBaseVehicleDO.id} // 删除是Ajax向后台传递的参数  
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
						uploadUrl : 'http://localhost:8080/carTravelsky/common/multipleFileUpload', // you must set a valid URL here else you will get an error
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
					});

	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}

	//加载select
	$.loadProjectattr($("#brandName"));
	$.loadProjectattr($("#typeName"));
	$.loadProjectattr($("#modelName"));
	$.loadProjectattr($("#colorName"));
	$.loadProjectattr($("#supply"));
	$.loadProjectattr($("#deptName"));
	$.loadProjectattr($("#dreiver"));
	$.loadProjectattr($("#nowStatus"));
</script>
</html>