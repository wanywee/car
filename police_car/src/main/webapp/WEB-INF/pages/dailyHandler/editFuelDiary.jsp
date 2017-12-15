<%@ page language
="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑加油记录</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<script type="text/javascript">


function reset(){
	// 重置表单
	document.getElementById("formid").reset(); 
}
$(function(){
	
	
	 //取消按钮添加方法
    $("#reset").click(function(){
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    });
	
    $("#lastMileage").blur(function(){
		var lastTrip = $("#lastTrip").val();
		var lastMileage = $("#lastMileage").val();
		var reg = "/^\d+(\.\d+)?$/"; 
		var mileage = Number(lastTrip)+Number(lastMileage);
		$("#mileage").val(mileage)
		
	})
	
	$("#lastTrip").blur(function(){
		var lastTrip = $("#lastTrip").val();
		var lastMileage = $("#lastMileage").val();
		var mileage = Number(lastTrip)+Number(lastMileage);
		$("#mileage").val(mileage)
		
	})
	 
	
	$("#price").blur(function(){
		var price = $("#price").val();
		var addiolAmount = $("#addiolAmount").val();
		var reg = "/^\d+(\.\d+)?$/"; 
		var summoney = Number(price)*Number(addiolAmount);
		$("#summoney").val(summoney)
		
	})
	
	$("#addiolAmount").blur(function(){
		var price = $("#price").val();
		var addiolAmount = $("#addiolAmount").val();
		var summoney = Number(price)*Number(addiolAmount);
		$("#summoney").val(summoney)
		
	})
	
	
	//加载select
	$.loadProjectattr($("#licenseno"));
	$.loadProjectattr($("#petrolstation"));
	$.loadProjectattr($("#handleid"));
	$.loadProjectattr($("#oilseedLableID"));
	
	
	// 加油时间
	laydate.render({
		  elem: '#addoilTime',
		  type: 'datetime'
	});
	
	$("#formid").validationEngine();
	
	$("#btn_comfirm").click(function() {
		if (!$('#formid').validationEngine('validate')) {
				return;
			}
		var confirm = layer.confirm('确认保存以上信息吗?', { btn: ['确定','取消'] }, function(){
     		layer.close(confirm);		
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/addoilRecord/saveAddoilRecord',
				data : $('#formid').serialize()+ "&"
				+ "photoUrl=" + photoUrl,
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
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
	

})


</script>
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
												for="licenseno">加油站:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="petrolstation" select2=""
														style="width: 160px" name="unitID"
														data-type="${recordDO.petrolstation}"
														data-value="${recordDO.unitID}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=JYZ"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="unitID" class="text" readonly="readonly" value="${recordDO.unitID}" />
													<select class="validate[required]" id="petrolstation" select2=""
														style="width: 160px" name="unitID"
														data-type="${recordDO.petrolstation}"
														data-value="${recordDO.unitID}"
														data-url="${ctx}/common/getSelect2ListContactCompany?kind=JYZ" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">燃油标号:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="oilseedLableID" select2=""
														style="width: 160px" name="oilseedLableID"
														data-type="${recordDO.oilseedLablename}"
														data-value="${recordDO.oilseedLableID}"
														data-url="${ctx}/addoilRecord/getSelect2ListFueloil"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="carID" class="text" readonly="readonly" value="${recordDO.oilseedLableID}" />
													<select class="validate[required]" id="oilseedLableID" select2=""
														style="width: 160px" name="oilseedLableID"
														data-type="${recordDO.oilseedLablename}"
														data-value="${recordDO.oilseedLableID}"
														data-url="${ctx}/addoilRecord/getSelect2ListFueloil" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >单价:</label>
												<div class="col-xs-9">
													<input type="text" id="price" name="price" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.price}" placeholder="元/升"/>
												</div>
											</div>
											
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >加油量:</label>
												<div class="col-xs-9">
													<input type="text" id="addiolAmount" name="addiolAmount" placeholder="单位为升" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.addiolAmount}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >总金额:</label>
												<div class="col-xs-9">
													<input type="text" id="summoney" name="summoney" readonly="readonly" placeholder="单位为元"
													 value="${recordDO.summoney}"/>
												</div>
											</div>
											
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >加油日期:</label>
												<div class="col-xs-9">
													<input type="text" id="addoilTime" name="addoilTime" 
													value='<fmt:formatDate value='${recordDO.addoilTime}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >上次里程:</label>
												<div class="col-xs-9">
													<input type="text" id="lastMileage" name="lastMileage" placeholder="单位为公里" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.lastMileage}"/>
												</div>
											</div>
											
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >上次行程:</label>
												<div class="col-xs-9">
													<input type="text" id="lastTrip" name="lastTrip"  placeholder="单位为公里" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.lastTrip}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >本次里程:</label>
												<div class="col-xs-9">
													<input type="text" id="mileage" name="mileage" placeholder="单位为公里" readonly="readonly" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													value="${recordDO.mileage}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right"
												for="licenseno">经手人:</label>
												<c:if test="${recordDO == null}">
													<div class="col-xs-9">
													<select class="validate[required]" id="handleid" select2=""
														style="width: 160px" name="handleid"
														data-type="${recordDO.handlename}"
														data-value="${recordDO.handleid}"
														data-url="${ctx}/common/getSelect2ListStaff"></select>
													</div>
												</c:if>
												<c:if test="${recordDO != null}">
													<div class="col-xs-9">
													<input type="hidden" name="handleid" class="text" readonly="readonly" value="${recordDO.handleid}" />
													<select class="validate[required]" id="handleid" select2=""
														style="width: 160px" name="carID"
														data-type="${recordDO.handlename}"
														data-value="${recordDO.handleid}"
														data-url="${ctx}/common/getSelect2ListStaff" disabled="disabled"></select>
													</div>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >上次油耗:</label>
												<div class="col-xs-9">
													<input type="text" id="lastOilConsunption" placeholder="单位为升" name="lastOilConsunption" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.lastOilConsunption}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >上次油量:</label>
												<div class="col-xs-9">
													<input type="text" id="lastOil" name="lastOil" placeholder="单位为升" onkeyup="value=value.replace(/[^\-?\d.]/g,'')"
													class="validate[required,custom[number]] text-input" value="${recordDO.lastOil}"/>
												</div>
											</div>
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right" >所属地区:</label>
												<div class="col-xs-9">
													<input type="text" id="region" name="region" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5]/g,'')"
													class="validate[required] text-input" value="${recordDO.region}"/>
												</div>
											</div>
										</div>
										
										
									</div>
									<center>
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
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >备注:</label>
											<div class="col-xs-9">
												<textarea id="remark" class=" validate[maxSize[128],custom[onlyLetterNumber]] autosize-transition form-control" name="remark" >${recordDO.remark}</textarea>
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
				url : '${ctx}/addoilRecord/fileDelete?id=${recordDO.id}' + '&imgPath=' + array_element, // 删除url  
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