<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>部门信息</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
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
							<form id="deptForm" class="form form-horizontal" role="form">
								<input type="hidden" name="id" class="text" readonly="readonly"
									value="${carBaseDeptment.id}" />
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptCode">部门编号:</label>
										<div class="col-xs-9">
												<c:if test="${carBaseDeptment.id != null}">
													<input name="deptCode" value="${carBaseDeptment.deptCode}" type="text"
													id="deptCode" placeholder="可不填" readonly="readonly" />
												</c:if>
												<c:if test="${carBaseDeptment.id == null}">
													<input name="deptCode" value="${carBaseDeptment.deptCode}" type="text"
													id="deptCode" placeholder="可不填" />
												</c:if>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptName">部门名称:</label>
										<div class="col-xs-9">
											<input name="deptName" value="${carBaseDeptment.deptName}"
												type="text" id="deptName"
												class="validate[required] text-input" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptName">上级部门:</label>
										<div class="col-xs-9">
										    <c:if test="${ carBaseDeptment.level eq '1' || carBaseDeptment.level eq '0' }" var="ableEdit">
										      <c:if test="${carBaseDeptment.level eq '1' }">
										          <input name='deptParentID' value='${carBaseDeptment.companyID}'  type="hidden" />
											  	  <input  type="text"  class="text-input"  value="${deptParent.deptName}" readonly="readonly" />
										      </c:if>
										      <c:if test="${carBaseDeptment.level eq '0' }">
										         <input   name='deptParentID' value='${carBaseDeptment.companyID}'  type="hidden"  />
											  	 <input   type="text" class="text-input"  value="无" readonly="readonly" />
										      </c:if>
										      
											</c:if>
											<c:if test="${not ableEdit}">
											      <select class="validate[required]" id="deptParentID" select2=""
													style="width: 160px" name="deptParentID"
													data-type="${deptParent.deptName}"
													data-value="${deptParent.id}"
													data-url="${ctx}/common/getSelect2ListDeptmentByLevel?levels=0,1"></select>
												
											</c:if>  
										</div>
									</div>
								</div>
								<c:if test="${carBaseDeptment.id !=null}">
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right"
												for="mnemonicCode">助记码:</label>
											<div class="col-xs-9">
												<input name="mnemonicCode"
													value="${carBaseDeptment.mnemonicCode}" type="text"
													id="mnemonicCode" class="validate[required] text-input" />
											</div>
										</div>
									</div>
								</c:if>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="principal">负责人:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="principal" select2=""
												style="width: 160px" name="principal"
												data-type="${carBaseDeptment.principalName}"
												data-value="${carBaseDeptment.principal}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptCall">部门电话:</label>
										<div class="col-xs-9">
											<input name="deptCall" value="${carBaseDeptment.deptCall}"
												type="text" id="deptCall"
												class="validate[custom[deptPhone]] text-input" />
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>
										<div class="col-xs-9">
										<input name="status" value="${carBaseDeptment.status}" type="hidden" id="status" >
											<textarea name="comments" id="comments"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${carBaseDeptment.comments}</textarea>
										</div>
									</div>
								</div>
								<%-- <div class="row">
									<div class="form-group col-xs-12">
										<label class="col-xs-3 control-label no-padding-right"
											for="status">是否停用:</label>
										<div class="col-xs-9">
											<c:if
												test="${carBaseDeptment.id == null || carBaseDeptment.status == 1}">
												
											</c:if>
											<c:if test="${carBaseDeptment.status == 2}">
												<input name="status" value="2" type="checkbox" id="status" checked="checked" readonly="readonly">
											</c:if>
										</div>
									</div>

								</div> --%>
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
</body>
<script type="text/javascript">
	/* 部门号码校验 */
	$.validationEngineLanguage.allRules.deptPhone = {  
		"regex": /^1[3|4|5|7|8][0-9]{9}$|^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/,
	    "alertText": "* 请输入正确的电话号码"
	};
	
	$(function() {
		/* 校验表单 */
		$('#deptForm').validationEngine();
		/* 异步提交表单 */
		$('#btn_comfirm').click(function() {
			if (!$('#deptForm').validationEngine('validate')) {
				return;
			}
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/deptInfo/updateDeptInfo',
				data : $('#deptForm').serialize(),
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data) {
					layer.msg(data.message);
					if (data.message == "操作部门信息成功！") {
						setTimeout('closeLayer()',2000);
					}
					
				},
				error : function(data) {
					layer.msg(data.message);
					//setTimeout('closeLayer()',2000);
				}
			});
		});
		/* 表单提交取消 */
		$('#btn_cancel').click(function() {
			//$("#table_list").trigger("reloadGrid");
			closeLayer();
		});
	});
	
	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}
	
	//加载select
	$.loadProjectattr($("#principal"));
	if($('#deptParentID').length>0){
		$.loadProjectattr($('#deptParentID'));
	}
</script>
</html>