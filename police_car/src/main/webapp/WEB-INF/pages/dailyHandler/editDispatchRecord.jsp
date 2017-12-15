<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增取车记录</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
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
								<form id="dispatchRecordForm" class="form-horizontal" role="form">
								<input type="hidden" name="id" class="text" readonly="readonly" value="${recode.id }" />
									<div class="row">
										<div class="col-xs-6 form-group">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">车牌号：</label>
											<div class="col-xs-9">
												   <select class="validate[required]" id="editLicensenoId" select2=""
													name="carId" data-type="" data-value=""
													data-url="${ctx}/common/getLicenseno?nowStatus=3" style="width: 163px"></select>
													<!-- <select class="validate[required]" id="editLicensenoId"  name="carId"  style="width: 163px"></select> -->
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">维修类型:</label>
											<div class="col-xs-9">
												  <select class="validate[required]" id="editKeyCodeId" select2=""
													name="repairtype"  data-type="REPAIRTYPE" data-value="${recode.repairtype}"
													data-url="${ctx}/common/getListKeyCode?kind=REPAIRTYPE" style="width: 163px"></select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">取车日期:</label>
											<div class="col-xs-9">
												 
												<input class="validate[required]" type="text" id="editBackDateId" name="tackcarTime" style="width: 163px"/>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">维修费用：</label>
											<div class="col-xs-9">
											    <input class="validate[required,max[1000000],custom[positive]] text-input" type="text" id="editRepairMoneyId" name="repairMoney" style="width: 163px" value="${recode.repairMoney}"/>元
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" for="start">维修项目:</label>
											<div class="col-xs-9">
												<textarea id="editRepairProjectId" name="repairProject" class="validate[required,maxSize[100]] text-input autosize-transition form-control" >${recode.repairProject}</textarea>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" for="end">使用材料:</label>
											<div class="col-xs-9">
												<textarea id="editUsedmaterialId" name="usedmaterial" class="validate[required,maxSize[100]] text-input autosize-transition form-control" >${recode.usedmaterial}</textarea>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">备注:</label>

											<div class="col-xs-9">
												<textarea id="editRemarkId" name="remark" class="validate[maxSize[100]] text-input autosize-transition form-control" >${recode.remark }</textarea>
											</div>
										</div>
									</div>
								</form>
									<div class="row">
										<div class="col-xs-12" style="text-align: center;">
											<button id="save" class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
												<i class="ace-icon fa fa-check red2"></i>
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
	//加载select
	$.loadSelect2();
	$(function(){
		//日期
		laydate.render({
			elem: '#editBackDateId',
			type: 'datetime',
			value: new Date()
		});
		//验证
		$('#dispatchRecordForm').validationEngine();
		
	  //编辑时，处理select2选中
	   if("${recode}"){
	      $("#editLicensenoId").append("<option value=${recode.carId}>${recode.licenseno}</option>");
	  } 
		
	 //"保存" 添加方法
	     $("#save").click(function(e){
	    	var paramData = decodeURIComponent($("#dispatchRecordForm").serialize(), true);
	    	if(!$('#dispatchRecordForm').validationEngine('validate')){ 
				return;
			}
			var url = "${ctx }/dailyHandle/saveDispatchRecord";
			 $.ajax({
				type:"post",
	            url: url,
	            data: paramData,
	            success: function(data) {
	           	 	 layer.msg(data.message,{icon: 1});
	           	 	 setTimeout('closeLayer()',2000);	  
	            },
	            error: function(XMLHttpRequest, textStatus, errorThrown) {
	           	   layer.msg("操作失败",{icon: 2});
	           	    setTimeout('closeLayer()',3000);  
	            },
	          }); 
	       });
	     //取消按钮添加方法
	     $("#cancel").click(function(){
	     	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	         parent.layer.close(index);
	     });
	 
	});
	
	//关闭layer的方法
    function closeLayer(){
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index); 
	 }
    
	
	
	</script>
</html>