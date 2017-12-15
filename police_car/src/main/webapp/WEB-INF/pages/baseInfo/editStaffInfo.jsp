<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑警员信息</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/components/layui/css/layui.css" />
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
							<form class="form form-horizontal" role="form" id="form">
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffNo">警号:</label>
										<c:if test="${recodeDo.id!=null}">
											<div class="col-xs-9"><!-- class="validate[custom[staffNoVal]] text-input" -->
												<input id="staffNo2" type="text" name="staffNo" readonly="readonly"
													value="${recodeDo.staffNo}" />
											</div>
										</c:if>
										 <c:if test="${recodeDo.id==null}">
											<div class="col-xs-9"><!-- class="validate[custom[staffNoVal]] text-input" -->
												<input id="staffNo" type="text" name="staffNo" 
													value="${recodeDo.staffNo}" />
											</div>
										</c:if>
											
										<label id="staffNoLabel"></label>
									</div>
									<div class="form-group col-xs-6">
										<c:if test="${recodeDo.mnemonicCode!=null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right "
												for="mnemonicCode">助记码:</label>
												<div class="col-xs-9">
													<input name="mnemonicCode" value="${recodeDo.mnemonicCode}"
														type="text" id="mnemonicCode"
														class="validate[required,maxSize[16]] text-input" />
											    </div>
										</c:if>
										<c:if test="${recodeDo.mnemonicCode==null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right "
												for="mnemonicCode">助记码:</label>
												<div class="col-xs-9">
													<input name="mnemonicCode" value="系统自动生成" type="text"
														readonly="readonly" id="mnemonicCode"
														class="validate[required,maxSize[16]] text-input" />
												</div>
										</c:if>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffName">姓名:</label>
										<div class="col-xs-9">
											<input type="text" name="staffName" id="staffName"
												class="validate[required,maxSize[8]] text-input"
												value="${recodeDo.staffName}" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffSex">性别:</label>
										<div class="col-xs-9">
											<c:if test="${recodeDo.staffSex==2}">
												<input type="radio" name="staffSex" value="2"
													checked="checked" />女
												 <input type="radio" name="staffSex" value="1" />男
											</c:if>
											<c:if test="${recodeDo.staffSex==1}">
												<input type="radio" name="staffSex" value="2" />女
												 	<input type="radio" name="staffSex" value="1"
													checked="checked" />男
												</c:if>
											<c:if test="${recodeDo.staffSex==null}">
												<input type="radio" name="staffSex" value="2"
													/>女
												 	<input type="radio" name="staffSex" value="1" 
												 	checked="checked" />男
												</c:if>
										</div>
									</div>
								</div>
								<div class="row">


									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffNation">民族:</label>

										<div class="col-xs-9">
											<input id="staffNation" type="text" name="staffNation"
												class="validate[required,maxSize[8]] text-input"
												value="${recodeDo.staffNation}" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffBirthday">出生日期:</label>
										<div class="col-xs-9">
											<input id="staffBirthday" name="staffBirthday"
												class="validate[required]" value="${recodeDo.staffBirthday}" />
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffPhone">联系电话:</label>
										<div class="col-xs-9">
											<input id="staffPhone" type="text" name="staffPhone"
												class="validate[required,custom[mobile]] text-input"
												value="${recodeDo.staffPhone}" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffNative">籍贯:</label>

										<div class="col-xs-9">
											<input id="staffNative" type="text" name="staffNative"
												class="validate[required,maxSize[8]] text-input"
												value="${recodeDo.staffNative}" />
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffEmail">邮箱:</label>
										<div class="col-xs-9">
											<input id="staffEmail" type="text" name="staffEmail"
												class="validate[required,custom[email]] text-input"
												value="${recodeDo.staffEmail}" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="deptID">部门:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="deptID" select2=""
												style="width: 160px" name="deptID"
												data-type="${recodeDo.deptName}"
												data-value="${recodeDo.deptID}"
												data-url="${ctx}/common/getSelect2ListDeptment"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffDuty">职务:</label>

										<div class="col-xs-9">
											<input type="text" id="staffDuty" name="staffDuty"
												class="validate[required,maxSize[12]] text-input"
												value="${recodeDo.staffDuty}" />
										</div>
									</div>

									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="education">学历:</label>

										<div class="col-xs-9">
											<select class="validate[required]" id="education" select2=""
												name="education" data-type="${recodeDo.education}"
												data-value="${recodeDo.education}"
												data-url="${ctx}/getListDisplay?kind=DRIVER_EDUCATION"
												style="width: 163px"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="graduateSchool">毕业院校:</label>

										<div class="col-xs-9">
											<input type="text" id="graduateSchool" name="graduateSchool"
												class="validate[required,maxSize[64]] text-input"
												value="${recodeDo.graduateSchool}" />
										</div>
									</div>

									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="idcard">身份证号:</label>

										<div class="col-xs-9">
											<input type="text" id="idcard" name="idcard"
												class="validate[required,custom[idCard]] text-input"
												value="${recodeDo.idcard}" />
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="address">家庭住址:</label>

										<div class="col-xs-9">
											<input type="text" id="address" name="address"
												class="validate[required,maxSize[64]] text-input"
												value="${recodeDo.address}" />
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="entryTime">入职时间:</label>
										<div class="col-xs-9">
											<input id="entryTime" name="entryTime"
												class="validate[required]" value="${recodeDo.entryTime}" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>

										<div class="col-xs-9">
											<textarea id="comments" name="comments"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${recodeDo.comments}</textarea>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="staffStatus">是否停用:</label>

										<div class="col-xs-9">
											<c:if test="${recodeDo.status == 1||recodeDo.status==null}">
												<input name="staffStatus" type="checkbox">
											</c:if>
											<c:if test="${recodeDo.status == 2}">
												<input name="staffStatus" type="checkbox" checked="checked">
											</c:if>
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-xs-12" style="text-align: center;">
										<button id="comfirm_button"
											class="btn btn-white btn-default btn-round"
											style="margin-right: 50px;">
											<i class="ace-icon fa fa-check red2"></i> 保存
										</button>
										<button id="btn_cancel"
												class="btn btn-white btn-default btn-round"
												style="margin-left: 50px;">
												<i class="ace-icon fa fa-times red2"></i> 取消
										</button>
									</div>
								</div>
								<!-- 传ID "-->
								<div>
									<input type="text"  id="staffNoVa" name="staffNoVa"  style="display:none" value="${recodeDo.staffNo}" >
									<input type="text" name="brevityId" style="display: none"
										value="${recodeDo.id}">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/* 警号唯一性校验 */
	$.validationEngineLanguage.allRules.staffNoVal = {  
		"regex": /^[Z]{2}13[a]{10}$/,
	    "alertText": "* 该警号已被使用"
	};
	$(function() {
		laydate.render({
			elem : '#staffBirthday',
			type : 'date',
		});
		laydate.render({
			elem : '#entryTime',
			type : 'datetime',
		});
	})
	
	$("#form").validationEngine();
	$('#comfirm_button').click(function(e) {
		if (!$('#form').validationEngine('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			datatype : 'text',
			url : '${ctx}/baseInfo/editStaffInformationData',
			data : $('#form').serialize(),
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function(data) {
				if (data.result != null && data.addDriver == 1) {
					layer.alert("增加警员信息成功!", function() {
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					})
				} else if (data.result != null && data.updateDriver == 1) {
					layer.alert("修改警员信息成功!", function() {
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					})
				} else {
					layer.alert("操作失败:" + data.dataWrong);
				}
			}
		});
		e.preventDefault();
	});
	//加载select
	$.loadProjectattr($('#deptID'));
	$.loadProjectattr($('#education'));
	//工号唯一性验证
	$("#staffNo").blur(function(e) {
		var staffNo = $("#staffNo").val();
		$.ajax({
			type : 'POST',
			datatype : 'text',
			url : '${ctx}/baseInfo/validateStaffNo',
			data : {
				"staffNo" : staffNo
			},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function(data) {
				if(data.result==true){
					$(".staffNoformError").hide();
					$("#staffNo").removeClass("validate[custom[staffNoVal]] text-input");
				}else if(data.result==false){
						$(".staffNoformError").show();
						$("#staffNo").addClass("validate[custom[staffNoVal]] text-input");
				}else if(data.resultStaff==1){
				}
			}
		});
		e.preventDefault();
	});
	/* //重置按钮
	$("#reset_button").click(function(e) {
		var confirm = layer.confirm('您确定要重置表单内容么?', {
			offset : '200px',
			btn : [ '确定', '取消' ],
			fix : false
		}, function() {
			$("#form")[0].reset();
			layer.close(confirm);
		});
	}); */
	 //取消按钮添加方法
    $("#btn_cancel").click(function(){
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    });
</script>
</html>