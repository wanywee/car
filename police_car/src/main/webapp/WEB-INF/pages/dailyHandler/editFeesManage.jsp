<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增规费管理</title>
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
							<form class="form-horizontal" role="form" id="form">
						<input type="hidden" name="id" class="text" readonly="readonly" value="${recodeDo.id}" />
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="licenseNo">车牌号:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="carID" select2=""
												style="width: 160px" name="carID"
												data-type="${recodeDo.licenseNo}"
												data-value="${recodeDo.carID}"
												data-url="${ctx}/common/getSelect2ListLicenseno"></select>
										</div>
									</div>
									<div class="col-xs-6 form-group">
										<label class="col-xs-3 control-label no-padding-right"
											for="moneyname">费用名称：</label>

										<div class="col-xs-9">
											<select class="validate[required]" id="moneyname"
												select2="" name="moneyname"
												data-type="${recodeDo.moneyname}"
												data-value="${recodeDo.moneyname}"
												data-url="${ctx}/getListDisplay?kind=MONEY_NAME"
												style="width: 163px"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="paymentDate">交费日期:</label>
										<div class="col-xs-9">
										<c:choose>
										<c:when test="${recodeDo.id==null}">
										<input id="paymentDate" type="text" name="paymentDate"
												class="validate[required]" >
										</c:when>
										<c:otherwise>
										<input id="paymentDate" type="text" name="paymentDate"
												class="validate[required]" 
												value='<fmt:formatDate value='${recodeDo.paymentDate}'
												type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
										</c:otherwise>
										</c:choose>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="endDate">到期日期:</label>
										<div class="col-xs-9">
										<c:choose>
										<c:when test="${recodeDo.id==null}">
										<input id="endDate" type="text" name="endDate"
												class="validate[required]">
										</c:when>
										<c:otherwise>
											<input id="endDate" type="text" name="endDate"
												class="validate[required]"
												value='<fmt:formatDate value='${recodeDo.endDate}'
												type="both" pattern="yyyy-MM-dd hh:mm:ss"/>'>
										</c:otherwise>
										</c:choose>
										
										</div>
									</div>
									
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="chargeUnitID">收费单位:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="chargeUnitID"
												select2="" style="width: 160px" name="chargeUnitID"
												data-type="${recodeDo.chargeUnitName}"
												data-value="${recodeDo.chargeUnitID}"
												data-url="${ctx}/common/getSelect2ListContactCompany"></select>
										</div>
									</div>
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right "
											for="parmentMoney">交费金额：</label>
										<div class="col-xs-9">
											<div class="col-xs-9">
												<input name="parmentMoney" value="${recodeDo.parmentMoney}"
													type="text" id="parmentMoney"
													class="validate[required,maxSize[16],custom[number]] text-input" />&nbsp;&nbsp;元
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="paymentType">交费方式:</label>

										<div class="col-xs-9">
											<select class="validate[required]" id="paymentType"
												onchange="showRound(this.options[this.options.selectedIndex].value)"
												select2="" name="paymentType"
												data-type="${recodeDo.paymentType}"
												data-value="${recodeDo.paymentType}"
												data-url="${ctx}/getListDisplay?kind=FEES_TYPE"
												style="width: 163px"></select>
										</div>
									</div>
									<c:if test="${recodeDo.paymentType=='周期付'}">
									<div class="form-group col-xs-6" id="periodShow">
										<label class="col-xs-3 control-label no-padding-right"
											for="period">周期:</label>

										<div class="col-xs-9">
											<input id="period" type="text" name="period"
												class="validate[required,maxSize[8]]"
												value="${recodeDo.period}" />&nbsp;&nbsp;月
										</div>
									</div>
									</c:if>
									<c:if test="${recodeDo.paymentType=='一次付'}">
									<div class="form-group col-xs-6" id="periodShow" style="display: none">
										<label class="col-xs-3 control-label no-padding-right"
											for="period">周期:</label>

										<div class="col-xs-9">
											<input id="period" type="text" name="period"
												class="validate[required,maxSize[8]]"
												value="${recodeDo.period}" />&nbsp;&nbsp;月
										</div>
									</div>
									</c:if>
									<c:if test="${recodeDo.paymentType==null}">
									<div class="form-group col-xs-6" id="periodShow" style="display: none">
										<label class="col-xs-3 control-label no-padding-right"
											for="period">周期:</label>

										<div class="col-xs-9">
											<input id="period" type="text" name="period"
												class="validate[required,maxSize[8]]"
												value="${recodeDo.period}" />&nbsp;&nbsp;月
										</div>
									</div>
									</c:if>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="handler">经手人:</label>
										<div class="col-xs-9">
											<select class="validate[required]" id="handler"
												select2="" style="width: 160px" name="handler"
												data-type="${recodeDo.staffName}"
												data-value="${recodeDo.handler}"
												data-url="${ctx}/common/getSelect2ListStaff"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="remark">备注:</label>

										<div class="col-xs-9">
											<textarea id="remark" name="remark"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${recodeDo.remark}</textarea>
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
								<!-- 传ID  "-->
								<div>
									<input type="text" name="brevityId" style="display: none" value="${recodeDo.id}">
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
	$(function() {
		var postId = 0;
		var id = $("#id").val();
		if(id == "" || id == null){
			laydate.render({
				elem : '#paymentDate',
				value: new Date() 
				
			});
			laydate.render({
				elem : '#endDate',
				value: new Date() 
			});
		}
		laydate.render({
			elem : '#paymentDate',
			type : 'datetime',
		});
		laydate.render({
			elem : '#endDate',
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
			url : '${ctx}/dailyHandle/editFeesManageInfo',
			data : $('#form').serialize(),
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function(data) {
				if (data.addFees == 1) {
					layer.alert("增加规费管理成功!", function() {
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					});
				} else if (data.updateFees == 1) {
					layer.alert("修改规费管理成功!", function() {
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					});
				} else {
					layer.alert("操作失败:" + data.dataWrong);
				}
			}
		});
		e.preventDefault();
	});
	//加载select
	$.loadProjectattr($('#carID'));
	$.loadProjectattr($('#chargeUnitID'));
	$.loadProjectattr($('#paymentType'));
	$.loadProjectattr($('#handler'));
	$.loadProjectattr($('#moneyname'));
	
	function showRound(s) {
		if (s == "周期付") {
			$("#periodShow").css("display", "block");
		} else {
			$("#periodShow").css("display", "none");
		}
	}
	/* //重置按钮
	$("#reset_button").click(function(e){
		var confirm = layer.confirm('您确定要重置表单内容么?', { offset: '200px', btn: ['确定','取消'], fix: false}, function(){
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