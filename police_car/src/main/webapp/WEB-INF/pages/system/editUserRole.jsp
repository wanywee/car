<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增用户</title>
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
								<input id = "userID" type="hidden" name="id" class="text" readonly="readonly" value="${carSysUser.id}" />
									<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="nowStatus">所属角色:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="userRole"
												style="width: 160px" name="userRole"
												data-type="${carSysUser.roleID}"
												data-value="${carSysUser.roleName}"
												data-url="${ctx}/role/getRoleListSelect"></select>
										</div>
									</div>
									</div>
									<div class="row">
									<div class="form-group col-xs-9">
										<label class="col-xs-3 control-label no-padding-right"
											for="nowStatus">姓名:</label>
										<div class="col-xs-9">
											<select class="validate[required,custom[onlyLetterNumber]]" id="userDept" 
												style="width: 160px" name="staffID"
												data-type="${carSysUser.staffName}"
												data-value="${carSysUser.staffID}"
												data-url="${ctx}/common/getSelect2ListStaff"
												 ></select>
										</div>
									</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-9">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">用户名称：</label>
											<div class="col-xs-9">
											<input placeholder="开头是字母或者数字,不能超过16" type="text" class="validate[required,maxSize[64],custom[onlyLetterNumber]]"  name= "username" value="${carSysUser.username}" id="form-field-1" placeholder="用户名称" />
											</div>
										</div>
									</div>
									<c:if test="${carSysUser.id !=null}">
										<div class="row">
										<div class="form-group col-xs-9">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">助记码:</label>
											<div class="col-xs-9">
											<input type="text" class="validate[required,maxSize[32],custom[onlyLetterNumber]]" name="mnemonicCode" value="${carSysUser.mnemonicCode}" id="form-field-1" placeholder="助记码" />
											</div>
										</div>
										</div>
									</c:if>
										<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-4 control-label no-padding-right" for="form-field-1-1">是否停用:</label>
											<div class="col-xs-5">
											  <div class="checkbox checkbox-primary" style=" margin-top: 2px;">
											 <c:if test="${carSysUser.status==1}">
						                        <input id="checkbox2" value="2" name ="status" class="styled" type="checkbox">
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                         <c:if test="${carSysUser.status ==2}">
						                        <input id="checkbox2"  value="2" name ="status" class="styled" type="checkbox" checked>
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                          <c:if test="${carSysUser.status == null}">
						                        <input id="checkbox2"  value="2" name ="status" class="styled" type="checkbox" >
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                    </div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-9">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">备注:</label>
											<div class="col-xs-9">
											<textarea rows="2" cols="30" class="validate[maxSize[128]]" name="comments" placeholder="备注" >${carSysUser.comments}</textarea>
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
		if($("#userID").val() != 0 || $("#userID").val()!=''){
			$("#userDept").attr("disabled",true);
		}
	});
	$.loadSelect2Role($("#userRole"));
	$.loadProjectattr($("#userDept"));
	
     $("#save").click(function(e){
    	
    	var data = decodeURIComponent($("#role")
				.serialize(), true);
    	if(!$('#role').validationEngine('validate')){ 
			return;
		}
    	
		var url = "${ctx }/user/saveUserRole";
		 $.ajax({
			 type:"post",
             url: url,
             data: data,
             success: function(data) {
            	 if(data.message === "保存成功"){
            		 layer.msg(data.message,{icon: 1});
             	 	setTimeout('closeLayer()',1000);
            	 }else{
            		 layer.msg(data.message,{icon: 2});
            	 }
            	 	     
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