<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增字典</title>
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
						<div class="row">
							<div class="col-xs-12">
								<form  id="role">
								<input type="hidden" name="keyID" class="text" readonly="readonly" value="${keyCodeMasterDO.keyID}" />
									<div class="row">
										<div class=" col-xs-12 ">
                                            <div class="col-xs-3 no-padding-right" >
												<label   class=" control-label no-padding-right "  for="form-field-select-3">字典类型：</label>
											</div>
											<div class="col-xs-9">
												<input type="text" class="validate[required ,maxSize[30]]" name= "keyType" value="${keyCodeMasterDO.keyType}" id="form-field-1" placeholder="字典类型" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class=" col-xs-12">
										  <div class="col-xs-3 no-padding-right" >
											<label class="control-label no-padding-right " for="form-field-select-3">字典名称：</label>
										  </div>
											<div class="col-xs-9">
											<input type="text" class="validate[required ,maxSize[30],custom[onlyLetterNumber]]" name= "display" value="${keyCodeMasterDO.display}" id="form-field-1" placeholder="字典名称" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class=" col-xs-12">
											<div class="col-xs-3 no-padding-right" >
												<label class="control-label no-padding-right  " for="form-field-select-3">字典描述：</label>
											</div>
											<div class="col-xs-3">
												<input type="text" class="validate[maxSize[50]]" name= "description" value="${keyCodeMasterDO.description}" id="form-field-1" placeholder="字典描述" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<div class="col-xs-3 no-padding-right" >
												<label class=" control-label no-padding-right  " for="form-field-select-3">字典编码：</label>
											</div>
											<div class="col-xs-3">
												<input type="text" class="validate[required,maxSize[30],custom[onlyLetterNumber]]" name= "code" value="${keyCodeMasterDO.code}" id="form-field-1" placeholder="字典编码" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<div class="col-xs-3 no-padding-right" >
												<label class=" control-label no-padding-right  " for="form-field-select-3">字典解码：</label>
											</div>
											<div class="col-xs-3">
											<input type="text" class="validate[required,maxSize[500],custom[onlyLetterNumber]]" name= "decode" value="${keyCodeMasterDO.decode}" id="form-field-1" placeholder="字典解码" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<div class="col-xs-3 no-padding-right" >
												<label  class=" control-label no-padding-right  " for="form-field-1-1">是否可编辑：</label>
											</div>
											<div class="col-xs-3">
												  <div class="checkbox checkbox-primary" style=" margin-top: 2px;">
													 <c:if test="${keyCodeMasterDO.editableInd=='Y'}">
								                        <input id="editableIndId" value="Y" name ="editableInd" class="styled" type="checkbox" checked>
								                        <label for="editableIndId">
								                        </label>
								                     </c:if>
								                     <c:if test="${keyCodeMasterDO.editableInd =='N'}">
								                        <input id="editableIndId"  value="Y" name ="editableInd" class="styled" type="checkbox" >
								                        <label for="editableIndId">
								                        </label>
								                     </c:if>
								                     <c:if test="${keyCodeMasterDO.editableInd == null}">
								                        <input id="editableIndId"  value="Y" name ="editableInd" class="styled" type="checkbox" >
								                        <label for="editableIndId">
								                        </label>
								                     </c:if>
							                    </div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<div class="col-xs-3 no-padding-right" >
												<label class=" control-label no-padding-right  " for="form-field-select-3">默认值：</label>
											</div>
											<div class="col-xs-3">
											<input type="text" class="validate[maxSize[1]]" name= "defaultInd" value="${keyCodeMasterDO.defaultInd}" id="form-field-1" placeholder="默认值" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
										    <div class="col-xs-3 no-padding-right" >
												<label  class=" control-label no-padding-right  " for="form-field-1-1">是否停用：</label>
											</div>
											<div class="col-xs-3">
											  <div class="checkbox checkbox-primary" style=" margin-top: 2px;">
											 <c:if test="${keyCodeMasterDO.deleteCode=='1'}">
						                        <input id="deleteCodeId" value="3" name ="deleteCode" class="styled" type="checkbox">
						                        <label for="deleteCodeId">
						                        </label>
						                     </c:if>
						                     <c:if test="${keyCodeMasterDO.deleteCode =='3'}">
						                        <input id="deleteCodeId"  value="3" name ="deleteCode" class="styled" type="checkbox" checked>
						                        <label for="deleteCodeId">
						                        </label>
						                     </c:if>
						                     <c:if test="${keyCodeMasterDO.editableInd == null}">
						                        <input id="deleteCodeId"  value="3" name ="deleteCode" class="styled" type="checkbox" >
						                        <label for="deleteCodeId"></label>
						                   </c:if>
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
	});
     $("#save").click(function(e){
    	var data = decodeURIComponent($("#role").serialize(), true);
    	if(!$('#role').validationEngine('validate')){ 
			return;
		}
    	if(!$("#editableIndId").is(':checked')){
    		data=data+"&editableInd=N";
    	}
    	if(!$("#deleteCodeId").is(':checked')){
    		data=data+"&deleteCode=1";
    	}
		var url = "${ctx }/dictay/saveCodeMaster";
		 $.ajax({
			 type:"post",
             url: url,
             data: data,
             success: function(data) {
            	 layer.msg(data.message,{icon: 1});
            	 if(data.message!="字典编码 或 名称已存在"){
            	 	 setTimeout('closeLayer()',1000);  
            	 	jqGrid.trigger("reloadGrid");  
            	 } 
            	 	
             },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
            	 layer.msg("保存失败",{icon: 2});
            	 setTimeout('closeLayer()',1000);  
            	 jqGrid.trigger("reloadGrid");   
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