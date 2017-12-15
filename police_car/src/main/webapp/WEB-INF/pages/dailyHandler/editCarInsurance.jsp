<%@ page language
="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑保险记录</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />

</head>
<body class="no-skin">
		<div class="main-container ace-save-state" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
			
						<div class="row">
							<div class="col-xs-12">
								<form class="form-horizontal" role="form" id="formid">
										<input type="hidden" id="id" name="id" value="${recordDO.id}"/>
									<div class="row">
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">车牌号:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="licenseno" select2=""
														style="width: 160px" name="carID"
														data-type="${recordDO.licenseno}"
														data-value="${recordDO.carID}"
														data-url="${ctx}/common/getSelect2ListLicenseno"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="carID" class="text" readonly="readonly" value="${recordDO.carID}" />
													<select class="validate[required]" id="licenseno" select2=""
														style="width: 160px" name="carID"
														data-type="${recordDO.licenseno}"
														data-value="${recordDO.carID}"
														data-url="${ctx}/common/getSelect2ListLicenseno" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">经手人:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="handler" select2=""
														style="width: 160px" name="handler"
														data-type="${recordDO.handlename}"
														data-value="${recordDO.handler}"
														data-url="${ctx}/common/getSelect2ListStaff"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="handler" class="text" readonly="readonly" value="${recordDO.handler}" />
													<select class="validate[required]" id="handler" select2=""
														style="width: 160px" name="carID"
														data-type="${recordDO.handlename}"
														data-value="${recordDO.handler}"
														data-url="${ctx}/common/getSelect2ListStaff" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保单号:</label>
												<div class="col-xs-9">
													<input type="text" id="policyNumber" name="policyNumber" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
													class="validate[required] text-input" value="${recordDO.policyNumber}" />
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >投保金额:</label>
												<div class="col-xs-9">
													<input type="text" id="insureMoney" name="insureMoney" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.insureMoney}"/>
												</div>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >投保日期:</label>
												<div class="col-xs-9">
													<input type="text" id="insureDate" name="insureDate" 
													value='<fmt:formatDate value='${recordDO.insureDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >到期日期:</label>
												<div class="col-xs-9">
													<input type="text" id="endDate" name="endDate" 
													value='<fmt:formatDate value='${recordDO.endDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
												</div>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">保险公司:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="insuranceCpID" select2=""
														style="width: 160px" name="insuranceCpID"
														data-type="${recordDO.insuranceCpNm}"
														data-value="${recordDO.insuranceCpID}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=BXGS"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="insuranceCpID" class="text" readonly="readonly" value="${recordDO.insuranceCpID}" />
													<select class="validate[required]" id="insuranceCpID" select2=""
														style="width: 160px" name="unitID"
														data-type="${recordDO.insuranceCpNm}"
														data-value="${recordDO.insuranceCpID}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=BXGS" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">保险种类:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="insuranceType" select2=""
														style="width: 160px" name="insuranceType"
														data-type="${recordDO.insuranceTypeNm}"
														data-value="${recordDO.insuranceType}"
														data-url="${ctx}/carInsurance/getSelect2ListInsuranceType"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="insuranceType" class="text" readonly="readonly" value="${recordDO.insuranceType}" />
													<select class="validate[required]" id="insuranceType" select2=""
														style="width: 160px" name="unitID"
														data-type="${recordDO.insuranceTypeNm}"
														data-value="${recordDO.insuranceType}"
														data-url="${ctx}/carInsurance/getSelect2ListInsuranceType" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >所属地区:</label>
												<div class="col-xs-9">
													<input type="text" id="region"  name="region"  onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"
													class="validate[required] text-input" value="${recordDO.region}"/>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label for="uploadImg" class="col-xs-2 control-label">保单照片:</label>
											<div class="col-xs-10">
												<input id="file-1" type="file" multiple class="file"
													data-overwrite-initial="false" data-min-file-count="2">
											</div>
										</div>
									</div>
									<center>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >备注:</label>
											<div class="col-xs-9">
												<textarea id="remark" class="validate[maxSize[100]] text-input autosize-transition form-control" name="remark" >${recordDO.remark}</textarea>
											</div>
										</div>
									</div>
									</center>
								</form>
								<div class="row">
									<div class="col-xs-12" style="text-align: center;">
										<button id="btn_comfirm" class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
											<i class="ace-icon fa fa-check red2"></i>
											保存
										</button>
										<button id="reset" class="btn btn-white btn-default btn-round" style="margin-left: 50px;" >
											<i class="ace-icon fa fa-times red2"></i>
											取消
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
function reset(){
	document.getElementById("formid").reset(); 
}
	
	
$(function(){	
	 //取消按钮添加方法
    $("#reset").click(function(){
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    });
	
	//加载select
	$.loadProjectattr($("#licenseno"));
	$.loadProjectattr($("#handler"));
	$.loadProjectattr($("#insuranceCpID"));
	$.loadProjectattr($("#insuranceType"));
	// 投保时间
	laydate.render({
		  elem: '#insureDate',
		  type: 'datetime'
	});
	// 到期时间
	laydate.render({
		  elem: '#endDate',
		  type: 'datetime'
	});
	
	$("#formid").validationEngine();
	
	$("#btn_comfirm").click(function() {
		if (!$('#formid').validationEngine('validate')) {
				return;
			}
		var dataParam = $('#formid').serialize() + "&"
		+ "photoUrl=" + photoUrl;
		var confirm = layer.confirm('确认保存以上信息吗?', { btn: ['确定','取消'] }, function(){
     		layer.close(confirm);		
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/carInsurance/saveCarInsurance',
				data : dataParam,
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				//请求前进行图片上传验证
				beforeSend : function() {
					if (photoUrl == "" && photoUrlString == "") {
						layer.msg("请先上传照片！");
						return false;
					}
				},
				success : function(result) {
					//当你在iframe页面关闭自身时
					layer.msg(result.message, {icon: 1});
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭   
				},
				error : function(result,XMLHttpRequest, textStatus, errorThrown) {
					//当你在iframe页面关闭自身时
					layer.msg(result.message, {icon: 2});
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭   
				}
			});
		e.preventDefault();
		}, function(){
     	})
	});

});

/* 图片地址字符串转单个数组 */
	var photoUrlString = "${recordDO.photoUrl}";
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
				url : '${ctx}/carInsurance/carInsurancefileDelete?id=${recordDO.id}' + '&imgPath=' + array_element, // 删除url  
				key : '${recordDO.id}' // 删除是Ajax向后台传递的参数  
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
</script>
</html>