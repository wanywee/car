<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>系统参数修改</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<script type="text/javascript">
$(function(){
	
	$("#save").click(function() {
		var confirm = layer.confirm('确定保存?', { btn: ['确定','取消'] }, function(){
			layer.close(confirm);		
     		var index = layer.load(1);
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/codeMaster/updateSysPram',
				data : $('#formid').serialize(),
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				success : function(result) {
					layer.close(index);
          			layer.msg(result.message, {icon: 1});
          			jqGrid.trigger("reloadGrid");   
				},
				error : function(result,XMLHttpRequest, textStatus, errorThrown) {
					 console.log(result)
                 	 layer.msg(result.message, {icon: 2});
                 	 layer.close(index);
				},
			});
			e.preventDefault();
	}, function(){
 	})
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
									<%-- <div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >在外车辆闲置提示时间:</label>
											<div class="col-xs-9">
												<input name="idleCarTime" type="number" value="${sysPram.idleCarTime}" min="0"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/><h5 style='display: inline;'>&nbsp&nbsp小时</h5>
											</div>
										</div>
									</div> --%>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >到期提醒提示时间:</label>
											<div class="col-xs-9">
												<input name="expireTime" type="number" value="${sysPram.expireTime}" min="0"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/><h5 style='display: inline;'>&nbsp&nbsp天</h5>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >预计回车时间:</label>
											<div class="col-xs-9">
												<input name="predictTime" type="number" value="${sysPram.predictTime}" min="0"
												onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/><h5 style='display: inline;'>&nbsp&nbsp小时</h5>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label class="col-xs-3 control-label no-padding-right" >回车提醒时间:</label>
											<div class="col-xs-9">
												<input name="remindTime" type="number" value="${sysPram.remindTime}"
												onkeyup="if(value.length==1){value=value.replace(/[^(\-?)\d+]/ig,'')}else{value=value.substring(0,1)+value.substring(1,value.length).replace(/[^\d+]/ig,'');}"/>
												<h5 style='display: inline;'>&nbsp&nbsp小时</h5>
											</div>
										</div>
									</div>
								</form>
								<div class="row">
									<div class="col-xs-12" style="text-align: center;">
											<button id="save" class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
												<i class="ace-icon fa fa-check red2"></i>
											保存
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