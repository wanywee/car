<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑回车记录</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/css/check/build.css" />
</head>
<body class="no-skin">
		<div class="main-container ace-save-state" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<!-- /section:basics/content.breadcrumbs -->
					<div class="page-content">
					<form  id="role">
							<div class="col-xs-12">
								<input type="hidden" name="id" class="text" readonly="readonly" value="${carBackDO.id}" />
								<input type="hidden" id="outcarMileage" name="outcarMileage" class="text" readonly="readonly" value="${carBackDO.outcarMileage}" />
									<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="nowStatus">车牌号：</label>
										<div class="col-xs-9">
										<c:choose>
											<c:when test="${carBackDO.id==null}">
												<select class="validate[required]" id="carBack"
												style="width: 160px" name="carID"
												data-type="${carBackDO.licenseno}"
												data-value="${carBackDO.carID}"
												data-url="${ctx}/carAccident/getListLicensenoOut?isOutAndBack=isBack" ></select>
											</c:when>
											<c:otherwise>
											<input type="hidden" name="carID" class="text" readonly="readonly" value="${carBackDO.carID}" />
											<input id="carNumbers" type="text" class="validate[required,maxSize[64]]" 
													name="carBack"  disabled="disabled" value="${carBackDO.licenseno}" id="form-field-1" placeholder="车牌号" />
											</c:otherwise>
										</c:choose>
										</div>
									</div>
									</div>
										<div class="row">
										<div class="form-group col-xs-9">
											<label class="col-xs-3 control-label no-padding-right " style="margin-right: 10px" for="form-field-select-3">回车时间：</label>
												<c:choose>
												<c:when test="${empty carBackDO.backTime}">
													<input type="text" id="backTime" name="backTime"
														class="validate[required] col-xs-6">
													</input>
												</c:when>
												<c:otherwise>
													<input type="text" id="backTime" name="backTime"
														style="width: 159px"
														class="validate[required] "
														value='<fmt:formatDate value='${carBackDO.backTime}' 
														type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
												</c:otherwise>
											</c:choose>
											</div>
											</div>
									</div>
									<div style="margin-left: 10px ">
									<div class="row">
										<div class="col-xs-9 form-group">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">回车行程：</label>
											<div class="col-xs-9">
											<input id="backcarMileage" type="text" class="validate[required,maxSize[64],custom[positive]]" 
											name="backcarMileage" value="${carBackDO.backcarMileage}" id="form-field-1" placeholder="回车行程(km)" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-9 form-group">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">本次行程：</label>
											<div class="col-xs-9">
											<input id="trip" type="text" class="validate[required,maxSize[64],custom[positive]]" 
											name="trip" value="${carBackDO.trip}" id="form-field-1" placeholder="本次行程(km)" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-9 form-group">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">停放位置：</label>
											<div class="col-xs-9">
											<input type="text" class="validate[required,maxSize[64]]" 
											name="parkPosition" value="${carBackDO.parkPosition}" id="form-field-1" placeholder="停放位置" />
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group col-xs-9">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">备注:</label>
											<div class="col-xs-9">
											<textarea rows="2" cols="" class="validate[maxSize[128]]" name="backRemark" placeholder="备注" >
											${carBackDO.backRemark}</textarea>
											</div>
										</div>
										</div>
										</div>
								</form>
								<div class="row">
										<div class="col-xs-12" style="text-align: center;">
											<button id="save"  class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
												<i class="ace-icon fa fa-check red2" ></i>
												确定
											</button>
											<button id="cancel" class="btn btn-white btn-default btn-round" style="margin-left: 50px;">
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
	$(function(){
		$('#role').validationEngine();
		//日期
		laydate.render({
			elem: '#backTime',
			type: 'datetime'
			
		});//select2
		$.loadProjectattr($("#carBack"));
		
		//回车里程input  失去焦点 ！=null
		 $("#backcarMileage").blur(function(){
			if($("#backcarMileage").val()==""){
				 layer.msg("输入框不能为空！",{icon: 2});
        	 	 setTimeout('closeLayer()',1000);
        	 	 return;
			}
			var data = decodeURIComponent($("#role")
					.serialize(), true);
			//判断出车里程是否为空  不为空那么就计算 赋值
			if(!$("#outcarMileage").val()==""){
				var trip = parseInt($("#outcarMileage").val())-parseInt($("#backcarMileage").val());
				$("#trip").val(parseInt(trip));
				return;
			}
			//查询本次里程
			var url = "${ctx}/dailyOutBackCar/getTrip";
			 $.ajax({
				 type:"post",
	             url: url,
	             data:data,
	             success: function(data) {
	            	 	if(data==""){
	            	 		 layer.msg("没有出车记录！",{icon: 2});
	    	            	 setTimeout('closeLayer()',1000);  
	            	 	}
	            		$("#trip").val(data.result.trip);
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown) {
	            	 layer.msg("查询失败",{icon: 2});
	            	 setTimeout('closeLayer()',1000);  
	             },
	           });
		}) 
	});
	//保存回车记录
     $("#save").click(function(e){
    	var data = decodeURIComponent($("#role")
				.serialize(), true);
    	if(!$('#role').validationEngine('validate')){ 
			return;
		}
		var url = "${ctx }/dailyOutBackCar/saveCarOutAndBack?isOutAndBack=isBack";
		 $.ajax({
			 type:"post",
             url: url,
             data: data,
             success: function(data) {
            	 	 layer.msg(data.message,{icon: 1});
            	 	 setTimeout('closeLayer()',1000);    
             },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
            	 layer.msg("保存失败",{icon: 2});
            	 setTimeout('closeLayer()',1000);  
             },
           });
       });
    function closeLayer(){
		 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index); 
	 }
    $("#cancel").click(function(){
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    })
	</script>
</html>