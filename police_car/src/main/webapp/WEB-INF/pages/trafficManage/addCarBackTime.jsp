<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>续时</title>
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
					<form id="carOut">
						<input type="hidden" name="id" class="text" readonly="readonly" value="${carBackDO.id}" />
						<input type="hidden" name="estimateReturnTime" class="text" readonly="readonly" value="<fmt:formatDate value='${carBackDO.estimateReturnTime}' type="both" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						
						<div class="row" style="margin-left:70px;margin-top:20px;">
						
							<div class="row marTop">
								<div class="col-xs-12">
									<label class="col-xs-3 control-label no-padding-right">续车时长：</label>
									<div class="col-xs-9">
										<input id="day" type="text" style="width:170px;"
											class="validate[required,maxSize[64]]" name="day"
											 id="form-field-1"
											placeholder="续车时长(天)" />
									</div>
								</div>
							</div>


							
							<div class="row marTop" style="margin-top:20px">
									<div class="col-xs-12">
										<label class="col-xs-3 control-label no-padding-right">备&nbsp;
											&nbsp; &nbsp; &nbsp;注：</label>
										<div class="col-xs-9">
											<textarea rows="2" cols="" class="validate[maxSize[128]]" style="width:170px;
												name="outRemark" placeholder="备注"></textarea>
										</div>
									</div>
								</div>
							</div>
					</form>
					<div class="row marTop">
						<div class="col-xs-12" style="text-align: center; margin-top:10px;">
							<button id="save" class="btn btn-white btn-default btn-round"
								style="margin-right: 50px;">
								<i class="ace-icon fa fa-check red2"></i> 确定
							</button>
							<button id="cancel" class="btn btn-white btn-default btn-round"
								style="margin-left: 50px;">
								<i class="ace-icon fa fa-times red2"></i> 取消
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#role').validationEngine();
		//日期
		laydate.render({
			elem : '#backTime',
			type : 'datetime'

		});//select2
		$.loadProjectattr($("#carBack"));


						
	});
	
	//保存续时记录
	$("#save").click(
					function(e) {
						
						var data = decodeURIComponent($("#carOut").serialize(),
								true);
						if (!$('#carOut').validationEngine('validate')) {
							return;
						}
						var url = "${ctx }/backCarremind/addTimeFor?day="+$("#day").val();
						
						$.ajax({
							type : "post",
							url : url,
							data : data,
							success : function(data) {
								layer.msg(data.message, {
									icon : 1
								});
								setTimeout('closeLayer()', 1000);
							},
							error : function(XMLHttpRequest, textStatus,
									errorThrown) {
								layer.msg("保存失败", {
									icon : 2
								});
								setTimeout('closeLayer()', 1000);
							},
						});
					});
	function closeLayer() {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}
	$("#cancel").click(function() {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	})
</script>
</html>