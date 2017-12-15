<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>往来单位-新增</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/components/layui/css/layui.css" />
<style type="text/css">
.btn.btn-app.btn-primary.btn-round {
	text-align: center;
	width: 130px;
	height: 40px;
	font-size: 12px;
	line-height: 20px;
	padding: 0px;
}

.btn {
	border: 0px solid #FFF;
}

input {
	width: 160px;
}

.layui-upload-img {
	width: 92px;
	height: 92px;
	margin: 0 10px 10px 0;
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
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form id="contactsForm" class="form form-horizontal" role="form">
								<input type="hidden" name="id" class="text" readonly="readonly"
									value="${carBaseContactsDo.id}" />
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="companyName">公司名称:</label>
										<div class="col-xs-9">
											<input class="validate[required]" name="companyName"
												value="${carBaseContactsDo.companyName}" type="text"
												id="companyName"/>
										</div>
									</div>
									<!--  
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="memonicCode">助记码:</label>
										<div class="col-xs-9">
											<input class="validate[required]" name="memonicCode"
												value="${carBaseContactsDo.memonicCode}" type="text"
												id="memonicCode"  />
										</div>
									</div>
									-->
									<div class="form-group col-xs-6">
									<c:if test="${carBaseContactsDo.memonicCode!=null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="mnemonicCode">助记码:</label>
											<div class="col-xs-9">
												<input name="memonicCode"
													value="${carBaseContactsDo.memonicCode}" type="text" readonly="readonly"
													id="memonicCode" class="validate[required,maxSize[16]] text-input" />
											</div>
										</c:if>
										<c:if test="${carBaseContactsDo.memonicCode==null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="mnemonicCode">助记码：</label>
											<div class="col-xs-9">
												<input name="mnemonicCode"
													value="系统自动生成" type="text" readonly="readonly"
													id="memonicCode" class="validate[required,maxSize[16]] text-input" />
											</div>
										</c:if>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="address">地址:</label>
										<div class="col-xs-9">
											<input name="address" value="${carBaseContactsDo.address}"
												type="text" id="address"
												class="validate[required] text-input" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="companyType">公司类型:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="companyType" select2=""
												style="width: 160px" name="companyType"
												data-type="${carBaseContactsDo.companyTypeName}"
												data-value="${carBaseContactsDo.companyType}"
												data-url="${ctx}/getListKeyCode?kind=CONTACTS_TYPE"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="linkman">联系人:</label>
										<div class="col-xs-9">
											<input class="validate[required]" name="linkman"
												value="${carBaseContactsDo.linkman}" type="text"
												id="linkman"  />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="phone">联系电话:</label>
										<div class="col-xs-9">
											<input class="validate[required,custom[phonenum]]" name="phone"
												value="${carBaseContactsDo.phone}" type="text"
												id="phone"  />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="postcode">邮编:</label>
										<div class="col-xs-9">
											<input name="postcode" value="${carBaseContactsDo.postcode}"
												type="text" id="postcode"
												class="validate[required,custom[PostalCode]]" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="fax">传真:</label>
										<div class="col-xs-9">
											<input name="fax" value="${carBaseContactsDo.fax}"
												type="text" id="fax"
												class="validate[required,custom[faxnum]]" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="email">邮箱:</label>
										<div class="col-xs-9">
											<input name="email" value="${carBaseContactsDo.email}"
												type="text" id="email"
												class="validate[required,custom[email]]" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="companyAccount">账号:</label>
										<div class="col-xs-9">
											<input name="companyAccount" value="${carBaseContactsDo.companyAccount}"
												type="text" id="companyAccount"
												class="validate[required],custom[account]" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="depositBank">开户行:</label>
										<div class="col-xs-9">
											<input name="depositBank" value="${carBaseContactsDo.depositBank}"
												type="text" id="depositBank"
												class="validate[required]" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="webSite">网站:</label>
										<div class="col-xs-9">
											<input name="webSite" value="${carBaseContactsDo.webSite}"
												type="text" id="webSite"
												class="validate[required]" />
										</div>
									</div>
								</div>
								<div class="row">
									
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>
										<div class="col-xs-9">
											<input name="comments" value="${carBaseContactsDo.comments}"
												type="text" id="comments"
												class="validate[required,maxSize[100]] text-input" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="status">是否停用:</label>
										<div class="col-xs-9">
											<c:if
												test="${carBaseContactsDo.id == null || carBaseContactsDo.status == 1}">
												<input name="status-id" value="1" type="checkbox" id="status-id">
											</c:if>
											<c:if test="${carBaseContactsDo.status == 2}">
												<input name="status-id" value="2" type="checkbox" id="status-id"
													checked="checked">
											</c:if>
										</div>
									</div>
								</div>
								
								<hr align=center width=100% color=gray>
						</div>
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
	<script type="text/javascript" src="${ctx }/components/layui/layui.js"></script>
</body>
<script type="text/javascript">
	$(function() {
		/* 校验表单 */
		$('#contactsForm').validationEngine();
		/* 异步提交表单 */
		$('#btn_comfirm')
				.click(
						function() {
							if (!$('#contactsForm').validationEngine('validate')) {
								return;
							}
							var dataParam = $('#contactsForm').serialize();
							if ($("#status-id").is(":checked")){
								dataParam=dataParam+'&status=2';
							}else{
								dataParam=dataParam+'&status=1';
							}
							console.log(dataParam);
							$
									.ajax({
										type : 'POST',
										datatype : 'text',
										url : '${ctx}/contactInfo/addAndEditContacts',
										data : dataParam,
										contentType : 'application/x-www-form-urlencoded; charset=utf-8',
										success : function(data) {
											if (data.message == "success") {
												layer.msg("操作成功", {icon: 1});
												setTimeout('closeLayer()', 2000);
											}else{
												layer.msg("操作失败，请重试", {icon: 2});
											}
										},
										error : function(data) {
											layer.msg("操作失败，请重试");
											//setTimeout('closeLayer()', 2000);
										}
									});
						});
		/* 表单提交取消 */
		$('#btn_cancel').click(function() {
			closeLayer();
		});

	});
	$.validationEngineLanguage.allRules.phonenum = {  
			"regex": /^1[3|4|5|7|8][0-9]{9}$|^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/,
		    "alertText": "* 请输入正确的电话号码"
	    }; 
	$.validationEngineLanguage.allRules.PostalCode  = {  
			"regex": /^[1-9][0-9]{5}$/,
			"alertText": "* 请输入正确的邮政编码"
		 }; /^(\d{3,4}-)?\d{7,8}$/
	$.validationEngineLanguage.allRules.faxnum  = {  
			"regex": /^(\d{3,4}-)?\d{7,8}$/,
			"alertText": "* 请输入正确的传真号"
		};
	$.validationEngineLanguage.allRules.account  = {  
			"regex": /^(\d{16}|\d{19})$/,
			"alertText": "* 请输入正确的公司账号"
		};
	$.validationEngineLanguage.allRules.website  = {  
			"regex": /^(\d{16}|\d{19})$/,
			"alertText": "* 请输入正确的公司账号"
		};
	function closeLayer() {
		//当你在iframe页面关闭自身时
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭
	}

	//加载select
	$.loadProjectattr($("#companyType"));
</script>
</html>