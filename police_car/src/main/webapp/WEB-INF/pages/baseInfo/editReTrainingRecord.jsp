<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>在岗复训记录</title>
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
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="time">时间:</label>
										<div class="col-xs-9">
											<input id="time" type="text" name="time" class="validate[required]"
												value="${recodeDo.time}" />
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="context">培训内容:</label>

										<div class="col-xs-9">
											<textarea id="context" name="context" 
												class="autosize-transition form-control validate[required,maxSize[255]]">${recodeDo.context}</textarea>
										</div>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label class="col-xs-3 control-label no-padding-right"
											for="comments">备注:</label>

										<div class="col-xs-9">
											<textarea id="comments" name="comments" placeholder="可不填"
												class="validate[maxSize[100]] text-input autosize-transition form-control">${recodeDo.comments}</textarea>
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
								<!-- 传ID -->
								<div>
									<input type="text" id="driverID"  style="display:none" name="driverID" value="${driverID}">
									<input type="text"  name="brevityId"  style="display:none" value="${recodeDo.id}" >
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
		laydate.render({
			elem : '#traniningTime',
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
			url : '${ctx}/baseInfo/editReTrainingInfo',
			data : $('#form').serialize(),
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success : function(data) {
				if(data.result!=null&&data.addRecord==1){
					layer.alert("增加在岗复训记录成功!",function(){
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					})  
				}else if(data.result!=null&&data.updateRecord==1){
					layer.alert("修改在岗复训记录成功!",function(){
						window.parent.location.reload();//刷新父页面
						parent.layer.close(index);//关闭当前弹窗
					})  
				}else{
					layer.alert("操作失败:"+data.dataWrong);
				}
			}
		});
		e.preventDefault();
	});
	//加载select
	$.loadSelect2();
	 //取消按钮添加方法
    $("#btn_cancel").click(function(){
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    });
</script>
</html>