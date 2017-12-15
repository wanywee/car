<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增角色</title>
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
								<input type="hidden" name="id" class="text" readonly="readonly" value="${carSysRole.id}" />
									<div class="row">
										<div class="col-xs-8 form-group">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">角色名字：</label>
											<div class="col-xs-9">
											<input type="text" class="validate[required,maxSize[16],custom[onlyLetterNumber]]" name="roleName" value="${carSysRole.roleName}" id="form-field-1" placeholder="角色名字" />
											</div>
										</div>
									</div>
										<c:if test="${carSysRole.id !=null}">
										<div class="row">
										<div class="col-xs-8 form-group">
											<label class="col-xs-3 control-label no-padding-right " for="form-field-select-3">角色编码：</label>
											<div class="col-xs-9">
											<input type="text" class="validate[required,maxSize[64],custom[onlyLetterNumber]]" name= "roleCode" value="${carSysRole.roleCode}" id="form-field-1" placeholder="角色名字" />
											</div>
										</div>
									</div>
									</c:if>
									
									<c:if test="${carSysRole.id !=null}">
										<div class="row">
										<div class="form-group col-xs-8">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">助记码:</label>
											<div class="col-xs-9">
											<input type="text" class="validate[required,maxSize[16],custom[onlyLetterNumber]]" name="mnemonicCode" value="${carSysRole.mnemonicCode}" id="form-field-1" placeholder="助记码" />
											</div>
										</div>
										</div>
									</c:if>
										<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-4 control-label no-padding-right" for="form-field-1-1">是否停用:</label>
											<div class="col-xs-5">
											  <div class="checkbox checkbox-primary" style=" margin-top: 2px;">
											 <c:if test="${carSysRole.status ==1}">
						                        <input id="checkbox2" name ="check" class="styled" type="checkbox">
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                         <c:if test="${carSysRole.status ==2}">
						                        <input id="checkbox2"  name ="check" class="styled" type="checkbox" checked>
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                          <c:if test="${carSysRole.status == null}">
						                        <input id="checkbox2"  name ="check" class="styled" type="checkbox" >
						                        <label for="checkbox2">
						                        </label>
						                        </c:if>
						                    </div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-8">
											<label class="col-xs-3 control-label no-padding-right" for="form-field-1">备注:</label>
											<div class="col-xs-9">
											<textarea rows="2" cols="30" class="validate[maxSize[128]]" name="comments" placeholder="备注" >${carSysRole.comments}</textarea>
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
    	var data = decodeURIComponent($("#role")
				.serialize(), true);
    	if(!$('#role').validationEngine('validate')){ 
			return;
		}
		var url = "${ctx }/role/saveRole";
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