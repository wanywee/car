<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>用车记录-编辑 新增</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/components/layui/css/layui.css" />
<style type="text/css">
.marTop{
margin-top:10px

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
				<form id="carOut">
					<div class="row">
					<input type="hidden" name="id" class="text" readonly="readonly" value="${carOutAndBackDO.id}" />
					  <div class="col-xs-6">
						<label class="col-xs-4 control-label no-padding-right"style="margin-right: 4px"
							for="nowStatus">车牌号&nbsp;：</label>
							<div class="col-xs-6">
							<c:choose>
								<c:when test="${carOutAndBackDO.id==null}">
								<select class="validate[required]" id="carNumber"
								style="width: 160px" name="carID"
								data-type="${carOutAndBackDO.licenseno}"
								data-value="${carOutAndBackDO.carID}"
								data-url="${ctx}/carAccident/getListLicensenoOut"></select>
								</c:when>
								<c:otherwise>
								<input type="hidden" name="carID" class="text" readonly="readonly" value="${carOutAndBackDO.carID}" />
								<input id="carNumbers" style="width: 160px" type="text" class="validate[required,maxSize[64]]" 
									name="carNumber"  disabled="disabled" value="${carOutAndBackDO.licenseno}" id="form-field-1" placeholder="" />
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="col-xs-6">
					<label class="col-xs-4 control-label no-padding-right"
							for="nowStatus">驾驶员：</label>
							<div class="col-xs-8">
							<c:choose>
								<c:when test="${carOutAndBackDO.id==null}">
								<select class="validate[required]" id="driver"
								style="width: 160px" name="stffID"
								data-type="${carOutAndBackDO.driver}"
								data-value="${carOutAndBackDO.stffID}"
								data-url="${ctx}/common/getSelect2ListStaff"></select>
								</c:when>
								<c:otherwise>
								<input type="hidden" name="stffID" class="text" readonly="readonly" value="${carOutAndBackDO.stffID}" />
								<input id="carNumbers" type="text" class="validate[required,maxSize[64]]" 
									name="driver" style="width: 160px"  disabled="disabled" value="${carOutAndBackDO.driver}" id="form-field-1" placeholder="驾驶员" />
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					</div>
					<div class="row marTop">
						<div class="col-xs-6">
							<label class="col-xs-4 " style="margin-right: 15px">用车部门：</label>
							<c:choose>
								<c:when test="${carOutAndBackDO.id==null}">
								<select class="validate[required]" id="carDept"
								style="width: 160px "name="deptID"
								data-type="${carOutAndBackDO.vehicleDept}"
								data-value="${carOutAndBackDO.deptID}"
								data-url="${ctx}/vehicleRecord/getListDeptByCompanyId"></select>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="carID" class="text" readonly="readonly" value="${carOutAndBackDO.deptID}" />
								<input id="carNumbers" type="text" class="validate[required,maxSize[64]]" 
									name="vehicleDept" style="width: 160px" disabled="disabled" value="${carOutAndBackDO.vehicleDept}" id="form-field-1" placeholder="" />
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-xs-6">
							<label  class="col-xs-4 "style="margin-right: 13px">用车人：</label>
							<input id="outcatMileage" type="text" class="validate[required,maxSize[64]]" 
							style="width: 161px"name="caruser" value="${carOutAndBackDO.caruserDisplay}" id="form-field-1" placeholder="用车人" />
						</div>
					</div>
					<div style="margin-left: 1px">
					<div class="row marTop">
					<div class="col-xs-12">
					<div style="margin-left:-15px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">随行人员：</label>
						<div style="margin-left:155px;">
						<select class="validate[required]" id="entourage"
											style="width: 160px;height:30px;" name="entourage" multiple="multiple"
											data-type=""
											data-value=""
											data-url="${ctx}/common/getSelect2ListStaffGoAlong"></select>
							<%-- <input id="outcatMileage" type="text" class="validate[required,maxSize[64]]" 
							name="entourage" style="width:160px;margin-left:9px;" value="${carOutAndBackDO.entourage}" id="form-field-1" placeholder="随行人员" /> --%>
						</div>
						</div>
						<div style="margin-left:12px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">目的地：</label>
						<div class="col-xs-8">
							<input id="desitination" style="width:160px;margin-left:9px;" type="text" class="validate[required,maxSize[64]]" 
							name="desitination" value="${carOutAndBackDO.desitination}" id="form-field-1" placeholder="目的地" />
						</div>
						</div>
						</div>
					</div>
					<div class="row marTop">
					<div class="col-xs-12">
					<div style="margin-left:-15px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">出车原因：</label>
							<div class="col-xs-8">
							<input id="outCause" style="width:160px;margin-left:9px;" type="text" class="validate[required]" 
							name="outCause" value="${carOutAndBackDO.outCause}" id="form-field-1" placeholder="出车原因" />
							</div>
						</div>
						<div style="margin-left:12px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">联系电话：</label>
							<div class="col-xs-8">
							<input id="phonenumber" style="width:160px;margin-left:9px;" type="text" class="validate[required,custom[deptPhone]]" 
							name="phonenumber" value="${carOutAndBackDO.phonenumber}" id="form-field-1" placeholder="联系电话" />
							</div>
						</div>
						</div>
					</div>
					<div class="row marTop">
					<div class="col-xs-12">
					<div style="margin-left:-15px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">出车里程：</label>
							<div class="col-xs-8">

							<input id="outcarMileage" style="width:160px;margin-left:9px;" type="text" class="validate[required,maxSize[64],custom[positive]]" 
							name="outcarMileage" value="${carOutAndBackDO.outcarMileage}" id="form-field-1" placeholder="出车里程(km)" />

							</div>
						</div>
						<div style="margin-left:12px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right "
									for="form-field-select-3">回车里程：</label>
								<div class="col-xs-8">
									<c:choose>
									<c:when test="${carOutAndBackDO.id==null}">
									<input id="backcarMileage" type="text"
										class="validate[required]"
										name="backcarMileage" value="${carOutAndBackDO.backcarMileage}"
										id="form-field-1" placeholder="回车行程(km)" 
										style="width:160px;margin-left:9px;"/>
									</c:when>
									<c:otherwise>
									<input id="backcarMileage" type="text"
										style="width:160px;margin-left:9px;"
										name="backcarMileage" value="${carOutAndBackDO.backcarMileage}"
										id="form-field-1" placeholder="回车行程(km)" />
									</c:otherwise>
									</c:choose>
								</div>
						</div>
						</div>
					</div>
					<div class="row marTop">
						<div class="col-xs-12">
							<div style="margin-left:-15px;" class="col-xs-6">
							<label class="col-xs-4 control-label no-padding-right">出车时间：</label>
							<div class="col-xs-8">
								<c:choose>
								<c:when test="${carOutAndBackDO.outcarTime==null}">
								<input type="text" id="outcarTime" name="outcarTime"
								        class="validate[required]"
								        style="width:160px;margin-left:9px;"></input>
								</c:when>
								<c:otherwise>
								<input type="text" id="outcarTime" name="outcarTime"
										class="validate[required]"
										style="width:160px;margin-left:9px;"
										value='<fmt:formatDate value='${carOutAndBackDO.outcarTime}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
								</c:otherwise>
								</c:choose>
							</div>
							</div>
							<div style="margin-left:12px;" class="col-xs-6">
							 <label class="col-xs-4 control-label no-padding-right"> 回车时间：</label>

						<div class="col-xs-8">
							<c:choose>
							<c:when test="${carOutAndBackDO.id==null}">
							<input type="text" id="backTime" name="backTime"
									style="width:160px;margin-left:9px;"
									value='<fmt:formatDate value='${carOutAndBackDO.backTime}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>							
							</c:when>
							<c:otherwise>
							<input type="text" id="backTime" name="backTime"
									style="width:160px;margin-left:9px;"
									value='<fmt:formatDate value='${carOutAndBackDO.backTime}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
							</c:otherwise>
							</c:choose>
						</div>							 							
							</div>
						</div>
					</div>
					
					<div class="row marTop">
					<div class="col-xs-12">
						<div style="margin-left:-15px;" class="col-xs-6">
						<label class="col-xs-4 control-label no-padding-right "
									for="form-field-select-4">本次行程：</label>
								<div class="col-xs-8">
								<c:choose>
								<c:when test="${carOutAndBackDO.id==null}">
									<input id="trip" type="text"
										class="validate[required]"
										name="trip" value="${carOutAndBackDO.trip}" id="form-field-1"
										placeholder="本次行程(km)" 
										style="width:160px;margin-left:9px;"/>
								</c:when>
								<c:otherwise>
								<input id="trip" type="text"
										style="width:160px;margin-left:9px;"
										name="trip" value="${carOutAndBackDO.trip}" id="form-field-1"
										placeholder="本次行程(km)" />
								</c:otherwise>
								</c:choose>		
								</div>
						</div>
						<div style="margin-left:12px;" class="col-xs-6">
						<label class="col-xs-4 control-label no-padding-right">备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>
							<div class="col-xs-8">
							<textarea rows="2" style="width:160px;margin-left:9px;" cols="" class="validate[maxSize[128]]" name=outRemark placeholder="备注" >${carOutAndBackDO.outRemark}</textarea>
							</div>
						</div>
					</div>
					</div>
					
					<div class="row marTop">
					<div class="col-xs-12">
						<div style="margin-left:-15px;" class="col-xs-6">
						<label class="col-xs-4 control-label no-padding-right "
									for="form-field-select-4">区&nbsp; &nbsp; &nbsp; &nbsp;域：</label>
								<div class="col-xs-8">
								<c:choose>
								<c:when test="${carOutAndBackDO.id==null}">								
								<label style="width:160px;margin-left:10px;">
								<select id="destArea" class="validate[required]" style="width:160px;"
										name="destArea" 
										data-type=""
										data-value=""
										data-url="${ctx}/outOfCar/getaddress"></select>		
								</label>																
								</c:when>
								<c:otherwise>
								<input type="text" class="validate[required,maxSize[64]]" 
									name="destArea" style="width: 160px;margin-left:10px;" value="${carOutAndBackDO.destArea}" 
									id="form-field-1" placeholder="区域" />								
								</c:otherwise>
								</c:choose>		
								</div>
						</div>
						<div style="margin-left:12px;" class="col-xs-6">
						<label class="col-xs-4 control-label no-padding-right">停放位置：</label>
							<div class="col-xs-8">
							<input id="parkPosition" type="text"
										class="validate[required]"
										style="width:160px;margin-left:9px;"
										name="parkPosition" value="${carOutAndBackDO.parkPosition}" id="form-field-1"
										placeholder="停放位置" />
							</div>
						</div>
					</div>
					</div>
					</div>
					</form>
					<div class="row marTop">
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
</body>
<script type="text/javascript">
/* 部门号码校验 */
$.validationEngineLanguage.allRules.deptPhone = {  
	"regex": /^1[3|4|5|7|8][0-9]{9}$/,
    "alertText": "* 请输入正确的电话号码"
};
	$("select").select2();
	$(function(){
		$("#carOut").validationEngine();
		//日期
		laydate.render({
			elem: '#outcarTime',
			type: 'datetime'
		});
		//日期
		laydate.render({
			elem: '#backTime',
			type: 'datetime'
		});
		//select2
		$.loadProjectattr($("#carNumber"));
		$.loadProjectattr($("#carDept"));
		$.loadProjectattr($("#driver"));
		$.loadProjectattr($("#destArea"));
		$.loadProjectattr($("#entourage"));
		
		//回车里程input  失去焦点 ！=null
		 $("#backcarMileage").blur(function(){
			if($("#backcarMileage").val()==""){
				 layer.msg("输入框不能为空！",{icon: 2,time: 1000});
				 
			}else if(parseInt($("#backcarMileage").val())<parseInt($("#outcarMileage").val())){
				layer.msg("输入有误！",{icon: 2,time: 1000});
				$("#backcarMileage").val('');
				
				
			}else{
				//判断出车里程是否为空  不为空那么就计算 赋值
				if(!$("#outcarMileage").val()==""){
					var trip = parseInt($("#backcarMileage").val())-parseInt($("#outcarMileage").val());
					$("#trip").val(parseInt(trip));
					
				}
			}
			
			

		})		
		
		$("#outcarMileage").focus(function(){
			var data = decodeURIComponent($("#carOut")
					.serialize(), true);	 
			
			var url = "${ctx}/dailyOutBackCar/getOutcarMileage";
			 $.ajax({
				 type:"post",
	             url: url,
	             data:data,
	             success: function(data) {
	            	 	if(data==""){
	            	 		 layer.msg("没有记录！",{icon: 2});
	    	            	 setTimeout('closeLayer()',1000);  
	            	 	}
	            		$("#outcarMileage").val(data.result.outcarMileage);
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown) {
	            	 layer.msg("查询失败",{icon: 2});
	            	 setTimeout('closeLayer()',1000);  
	             },
	           });
		})
		
		})
		
		//保存
		$("#save").click(function(e){
    	var data = decodeURIComponent($("#carOut")
				.serialize(), true);
    	if(!$('#carOut').validationEngine('validate')){ 
			return;
		}
		var url = "${ctx }/dailyOutBackCar/saveCarOutAndBack";
		 $.ajax({
			 type:"post",
             url: url,
             data: data,
             success: function(data) {
            	 var message=data.message;
            	 if(message.indexOf("失败") > 0){
            		 layer.msg(data.message,{icon: 2});
            	 }else{
            		 layer.msg(data.message,{icon: 1});
            	 	 setTimeout('closeLayer()',1000);  
            	 }
             },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
            	 layer.msg(data.message,{icon: 2});
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