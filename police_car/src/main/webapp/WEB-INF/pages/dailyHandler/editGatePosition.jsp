<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑停机位信息</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<script type="text/javascript">
function reset(){
	document.getElementById("formid").reset(); 
}
$(function(){
	// 创建时间
	laydate.render({
		  elem: '#createTime',
		  type: 'datetime'
	});
	//修改时间
	laydate.render({
		  elem: '#updateTime',
		  type: 'datetime',
	});
	
	$("#formid").validationEngine();
	
	
	/* 异步提交表单 */
	$('#btn_comfirm').click(function(e) {
		if (!$('#formid').validationEngine('validate')) {
			return;
		}
		$.ajax({
			type : 'POST',
			datatype : 'text',
			url : '${ctx}/gateposition/saveGatePosition',
			data : $('#formid').serialize(),
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function(result) {
				layer.msg(result.message, {icon: 1});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			},
			error : function(result,XMLHttpRequest, textStatus, errorThrown) {
				layer.msg(result.message, {icon: 2});
				var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(index); //再执行关闭
			}
		});
		e.preventDefault();
	});

})
</script>
</head>
<body class="no-skin">
		<div class="main-container ace-save-state" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<form class="form-horizontal" role="form" id="formid">
										<input type="hidden" id="id" name="id" value="${gatePosition.id}"/>
									<div class="row">
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
												<label class="col-xs-3 control-label no-padding-right">停机位号:</label>
												<div class="col-xs-9">
													<input type="text" id="gatePositionCode" name="gatePositionCode" 
													class="validate[required] text-input"  value="${gatePosition.gatePositionCode}"/>
												</div>
											</div>
										</div>
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
													<label class="col-xs-3 control-label no-padding-right" >扫描半径:</label>
													<div class="col-xs-9">
														<input type="text" id="scanRadii" name="scanRadii" placeholder="单位为米" 
														class="validate[required,custom[number]] text-input" value="${gatePosition.scanRadii}"/>
													</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
													<label class="col-xs-3 control-label no-padding-right" >纵坐标:</label>
													<div class="col-xs-9">
														<input style="display: inline;width: 80px" type="text" name="zLimit" class="validate[required] text-input" value="${zLimit}"/>°
														<input style="display: inline;width: 80px" type="text" name="zMinute" class="validate[required] text-input" value="${zMinute}"/>′
														<input style="display: inline;width: 80px" type="text" name="zSecond" class="validate[required] text-input" value="${zSecond}"/>″
														<c:if test="${not empty latitude && latitude == 'N' }">
															<select name="latitude">
																<option value="N" selected="selected">N</option>
																<option value="S">S</option>
															</select>
														</c:if>
														<c:if test="${not empty latitude && latitude == 'S' }">
															<select name="latitude">
																<option value="N" >N</option>
																<option value="S" selected="selected">S</option>
															</select>
														</c:if>
														<c:if test="${empty latitude}">
														<select name="latitude">
															<option value="N">N</option>
															<option value="S">S</option>
														</select>
														</c:if>
													</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-xs-6 form-group">
											<div class="form-group col-xs-12">
													<label class="col-xs-3 control-label no-padding-right" >横坐标:</label>
													<div class="col-xs-9">
														<input style="display: inline;width: 80px" type="text" name="hLimit" class="validate[required] text-input" value="${hLimit}"/>°
														<input style="display: inline;width: 80px" type="text" name="hMinute" class="validate[required] text-input" value="${hMinute}"/>′
														<input style="display: inline;width: 80px" type="text" name="hSecond" class="validate[required] text-input" value="${hSecond}"/>″
														<c:if test="${!empty longitude &&  longitude == 'E'}">
															<select name="longitude">
																<option value="E" selected="selected">E</option>
																<option value="W">W</option>
															</select>
														</c:if>
														<c:if test="${!empty longitude &&  longitude == 'W'}">
															<select name="longitude">
																<option value="E">E</option>
																<option value="W" selected="selected">W</option>
															</select>
														</c:if>
														<c:if test="${empty longitude}">
														<select name="longitude">
															<option value="E">E</option>
															<option value="W">W</option>
														</select>
														</c:if>
													</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >备注:</label>
											<div class="col-xs-9">
												<textarea id="remark" class="validate[maxSize[100]] text-input autosize-transition form-control" name="remark" >${gatePosition.remark}</textarea>
											</div>
										</div>
									</div>
								</form>
								<div class="row">
									<div class="col-xs-12" style="text-align: center;">
										<button id="btn_comfirm" class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
											<i class="ace-icon fa fa-check red2"></i>
											保存
										</button>
										<button id="reset" class="btn btn-white btn-default btn-round" style="margin-left: 50px;" onclick="reset()">
											<i class="ace-icon fa fa-times red2"></i>
											重置
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

</html>