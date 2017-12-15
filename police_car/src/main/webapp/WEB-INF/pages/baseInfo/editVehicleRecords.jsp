<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>车辆信息-新增</title>
<%@ include file="../common/header.jsp"%>
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

.btn {
	border: 0px solid #FFF;
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
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="licenseno">车牌号:</label>
										<div class="col-xs-9">
											<input class="validate[required]" name="licenseno"
												value="${carBaseVehicleDO.licenseno}" type="text"
												id="licenseno" placeholder="川A-88888" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="brandName">品牌:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="brandName" select2=""
												style="width: 160px" name="brandName"
												data-type="${carBaseVehicleDO.brandName}"
												data-value="${carBaseVehicleDO.brandName}"
												data-url="${ctx}/getListDisplay?kind=CAR_BRAND"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="modelName">型号:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="modelName" select2=""
												style="width: 160px" name="modelName"
												data-type="${carBaseVehicleDO.modelName}"
												data-value="${carBaseVehicleDO.modelName}"
												data-url="${ctx}/getListDisplay?kind=CAR_MODEL"></select>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="typeName">类型:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="typeName" select2=""
												style="width: 160px" name="typeName"
												data-type="${carBaseVehicleDO.typeName}"
												data-value="${carBaseVehicleDO.typeName}"
												data-url="${ctx}/getListDisplay?kind=CAR_TYPE"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="colorName">颜色:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="colorName" select2=""
												style="width: 160px" name="colorName"
												data-type="${carBaseVehicleDO.colorName}"
												data-value="${carBaseVehicleDO.colorName}"
												data-url="${ctx}/getListDisplay?kind=CAR_COLOR"></select>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="loading">载人:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="loading" value="${carBaseVehicleDO.loading}"
												type="text" id="loading"
												class="validate[required,custom[integer],min[0]] text-input" /> <i
												class="ace-icon">个</i>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="loading">自重:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="carWeight" value="${carBaseVehicleDO.carWeight}"
												type="text" id="carWeight"
												class="validate[required,custom[number],min[0]] text-input" /> <i
												class="ace-icon">吨</i>
											</span>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="consumption">油耗:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="consumption" value="${carBaseVehicleDO.consumption}"
												type="text" id="consumption"
												class="validate[required,custom[number],min[0]] text-input" /> <i
												class="ace-icon">L/100KM</i>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="startMile">初始里程:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="startMile" value="${carBaseVehicleDO.startMile}"
												type="text" id="startMile"
												class="validate[required,custom[number],min[0]] text-input" /> <i
												class="ace-icon">KM</i>
											</span>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="mileage">保养里程:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="mileage" value="${carBaseVehicleDO.mileage}"
												type="text" id="mileage"
												class="validate[required,custom[number],min[0]] text-input" /> <i
												class="ace-icon">KM</i>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="period">保养周期:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="period" value="${carBaseVehicleDO.period}" type="text"
												id="period"
												class="validate[required,custom[number],min[30]]" /> <i
												class="ace-icon">天</i>
											</span>
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="engineno">发动机号:</label>
										<div class="col-xs-9">
											<input name="engineno" value="${carBaseVehicleDO.engineno}"
												type="text" id="engineno"
												class="validate[required,custom[engineno]] text-input" />
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="chassisno">车架号:</label>
										<div class="col-xs-9">
											<input name="chassisno" value="${carBaseVehicleDO.chassisno}"
												type="text" id="chassisno"
												class="validate[required,custom[chassisno]] text-input" />
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="supply">供应商:</label>
										<div class="col-xs-9">
											<select class="" id="supply" select2="" style="width: 160px"
												name="supply" data-type="${carBaseVehicleDO.supplyName}"
												data-value="${carBaseVehicleDO.supply}"
												data-url="${ctx}/common/getSelect2ListContactCompany?kind=GYS"></select>
										</div>
										<div class="col-xs-6"></div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="buyPrice">购入价格:</label>
										<div class="col-xs-9">
											<span class="input-icon input-icon-right"> <input
												name="buyPrice" value="${carBaseVehicleDO.buyPrice}"
												type="text" id="buyPrice"
												class="validate[required,custom[number],min[0]] text-input" /> <i
												class="ace-icon">元</i>
											</span>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="buyTime">购入日期:</label>
										<div class="col-xs-9">
											<input value="<fmt:formatDate pattern="yyyy-MM-dd"
            value="${carBaseVehicleDO.buyTime }"/>" type="text"
												name="buyTimeString" id="buyTime"
												class="validate[required] text-input" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="dreiver">驾驶员:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="dreiver" select2=""
												style="width: 160px" name="dreiver"
												data-type="${carBaseVehicleDO.dreiverName}"
												data-value="${carBaseVehicleDO.dreiver}"
												data-url="${ctx}/common/getSelect2ListDriver"></select>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptName">所属部门:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="deptName" select2=""
												style="width: 160px" name="deptID" data-type="${deptName}"
												data-value="${carBaseVehicleDO.deptID}"
												data-url="${ctx}/common/getSelect2ListDeptment"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="nowStatus">当前状态:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="nowStatus" select2=""
												style="width: 160px" name="nowStatus"
												data-type="${nowStatus}" data-value="${carBaseVehicleDO.nowStatus}"
												data-url="${ctx}/getListKeyCode?kind=NOW_STATUS"></select>
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label for="uploadImg" class="col-xs-2 control-label">车辆照片:</label>
										<div class="col-xs-10">
											<input id="file-1" type="file" multiple class="file"
												data-overwrite-initial="false" data-min-file-count="2">
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>
										<div class="col-xs-9">
											<textarea name="comments" id="comments"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${carBaseVehicleDO.comments}</textarea>
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="status">是否停用:</label>
										<div class="col-xs-9">
											<c:if
												test="${carBaseVehicleDO.id == null || carBaseVehicleDO.status == 1}">
												<input name="status" value="2" type="checkbox" id="status">
											</c:if>
											<c:if test="${carBaseVehicleDO.status == 2}">
												<input name="status" value="2" type="checkbox" id="status"
													checked="checked">
											</c:if>
										</div>
									</div>
									<div class="col-xs-6"></div>
								</div>
								<hr align=center width=100% color=gray>
						</div>
						<!-- <div class="row">
							<div style="text-align: center;" class="form-group col-xs-12">
								<button class="btn btn-app btn-primary btn-round">
									行驶公里、维修费、<br>燃油耗量
								</button>
								<button class="btn btn-app btn-primary btn-round">车辆维修记录</button>
								<button class="btn btn-app btn-primary btn-round">车辆定(程)维护记录</button>
								<button class="btn btn-app btn-primary btn-round">车辆事故记录</button>
								<button class="btn btn-app btn-primary btn-round">车辆变更记录</button>
							</div>
						</div> -->
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
		$('#btn_comfirm')
				.click(
						function() {
							if (!$('#deptForm').validationEngine('validate')) {
								return;
							}
							var dataParam = $('#deptForm').serialize() + "&"
									+ "photoUrl=" + photoUrl;
							$
									.ajax({
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
			var tjson = {
				caption : ar.pop(), // 展示的文件名  
				width : '100px',
				//url : '',
				url : '${ctx}/common/fileDelete?id=${carBaseVehicleDO.id}' + '&imgPath=' + array_element, // 删除url  
				key : '${carBaseVehicleDO.id}' // 删除是Ajax向后台传递的参数  
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
						showRemove : false,
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
					}).on("filepredelete", function(event, key, jqXHR, data) { //没用 
	                    console.log('Key = ' + key);
	                    console.log('jqxhr = ' + jqXHR);
	                    console.log('data = ' + data);
					}).on("filebatchselected", function(event, files) { //没用
						console.log(files);
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