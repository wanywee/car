<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑保养记录</title>
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
										<input type="hidden" id="id" name="id" value="${recordByID.id}"/>
									<div class="row">
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">车牌号:</label>
												<c:if test="${recordByID == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="licenseno" select2=""
														style="width: 160px" name="carID"
														data-type="${recordByID.licenseno}"
														data-value="${recordByID.carID}"
														data-url="${ctx}/common/getLicenseno"></select>
													</div>
												</c:if>
												<c:if test="${recordByID != null}">
													<div class="col-xs-9">
													<input type="hidden" name="carID" class="text" readonly="readonly" value="${recordDO.carID}" />
													<select class="validate[required]" id="licenseno" select2=""
														style="width: 160px" name="carID"
														data-type="${recordByID.licenseno}"
														data-value="${recordByID.carID}"
														data-url="${ctx}/common/getLicenseno" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保养类别:</label>
												<div class="col-xs-9">
													<c:if test="${not empty recordByID && recordByID.maintainType == '一保' }">
														<select name="maintainType">
															<option value="一保" selected="selected">一保</option>
															<option value="二保">二保</option>
														</select>
													</c:if>
													<c:if test="${not empty recordByID && recordByID.maintainType == '二保' }">
														<select name="maintainType">
															<option value="一保">一保</option>
															<option value="二保" selected="selected">二保</option>
														</select>
													</c:if>
													<c:if test="${empty recordByID}">
														<select name="maintainType">
															<option value="一保">一保</option>
															<option value="二保" s>二保</option>
														</select>
													</c:if>
												</div>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保养日期:</label>
												<div class="col-xs-9">
													<input type="text" id="maintainDate" name="maintainDate" 
													value='<fmt:formatDate value='${recordByID.maintainDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >到期日期:</label>
												<div class="col-xs-9">
													<input type="text" id="endDate" name="endDate" 
													value='<fmt:formatDate value='${recordByID.endDate}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
												</div>
											</div>
											
										</div>
										<div class="col-xs-6 form-group">
										<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">保养单位:</label>
												<c:if test="${recordByID == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="maintainUnit" select2=""
														style="width: 160px" name="maintainUnit"
														data-type="${recordByID.maintainUnitname}"
														data-value="${recordByID.maintainUnit}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=BYZ"></select>
													</div>
												</c:if>
												<c:if test="${recordByID != null}">
													<div class="col-xs-9">
													<input type="hidden" name="maintainUnitID" class="text" readonly="readonly" value="${recordByID.maintainUnit}" />
													<select class="validate[required]" id="maintainUnit" select2=""
														style="width: 160px" name="maintainUnit"
														data-type="${recordByID.maintainUnitname}"
														data-value="${recordByID.maintainUnit}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=BYZ" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保养里程:</label>
												<div class="col-xs-9">
													<input type="text" id="maintainMileage" name="maintainMileage" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordByID.maintainMileage}"/>KM
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >所属地区:</label>
												<div class="col-xs-9">
													<input type="text" id="region" name="region" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"
													class="validate[required] text-input" value="${recordByID.region}"/>
												</div>
											</div>
											
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保养费用:</label>
												<div class="col-xs-9">
													<input type="text" id="maintainMoney" name="maintainMoney" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required] text-input" value="${recordByID.maintainMoney}"/>元
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >保养内容:</label>
												<div class="col-xs-9">
													<input type="text" id="maintainContent" name="maintainContent"
													class="validate[required] text-input" value="${recordByID.maintainContent}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">经手人:</label>
												<c:if test="${recordByID == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="handleid" select2=""
														style="width: 160px" name="handleid"
														data-type="${recordByID.handlename}"
														data-value="${recordByID.handleid}"
														data-url="${ctx}/common/getSelect2ListStaff"></select>
													</div>
												</c:if>
												<c:if test="${recordByID != null}">
													<div class="col-xs-9">
													<input type="hidden" name="handleid" class="text" readonly="readonly" value="${recordByID.handleid}" />
													<select class="validate[required]" id="handleid" select2=""
														style="width: 160px" name="carID"
														data-type="${recordByID.handlename}"
														data-value="${recordByID.handleid}"
														data-url="${ctx}/common/getSelect2ListStaff" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
										</div>
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
									<center>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >备注:</label>
											<div class="col-xs-9">
												<textarea id="remark" class="validate[maxSize[100]] text-input autosize-transition form-control" name="remark" >${recordByID.remark}</textarea>
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
											<button id="reset" class="btn btn-white btn-default btn-round" style="margin-left: 50px;">
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
	$.loadProjectattr($("#maintainUnit"));
	$.loadProjectattr($("#handleid"));
	
	//  保养时间
	laydate.render({
		  elem: '#maintainDate',
		  type: 'datetime'
	});
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
				url : '${ctx}/carDailyMaintain/saveCarDailyMaintain',
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
	var photoUrlString = "${recordByID.photoUrl}";
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
				url : '${ctx}/carDailyMaintain/carDailyMaintainfileDelete?id=${recordByID.id}' + '&imgPath=' + array_element, // 删除url  
				key : '${recordByID.id}' // 删除是Ajax向后台传递的参数  
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