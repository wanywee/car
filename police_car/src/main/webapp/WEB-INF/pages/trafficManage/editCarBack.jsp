<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>编辑回车记录</title>
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
					<form id="role">
						<input type="hidden" name="id" class="text" readonly="readonly" value="${carBackDO.id}" />
						<input type="hidden" name="carID" class="text" readonly="readonly" value="${carBackDO.carID}" />
						<input type="hidden" name="stffID" class="text" readonly="readonly" value="${carBackDO.stffID}" />
						<div style="margin-left:70px;margin-top:20px;">
							<div class="row">
								<div class="col-xs-12 form-group">
								<div class="col-xs-4">
									<label class="control-label no-padding-right "
										for="form-field-select-9">回车里程：</label>
								</div>		
									<div class="col-xs-8">
										<input id="backcarMileage" type="text"
											class="validate[required,maxSize[64],custom[positive]]"
											name="backcarMileage" id="form-field-1" placeholder="回车行程(km)" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 form-group">
								<div class="col-xs-4">
									<label class="control-label no-padding-right "
										for="form-field-select-3">本次行程：</label>
								</div>	
									
									<div class="col-xs-8">
										<input id="trip" type="text"
											class="validate[required,maxSize[64],custom[positive]]"
											name="trip" id="form-field-1" placeholder="本次行程(km)" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 form-group">
								<div class="col-xs-4">
									<label class="control-label no-padding-right "
										for="form-field-select-3">停放位置：</label>
								</div>
									<div class="col-xs-8">
										<input type="text" class="validate[required,maxSize[64]]"
											name="parkPosition" id="form-field-1" placeholder="停放位置" />
									</div>
								</div>
							</div>

						</div>
					</form>
					<div class="row">
						<div class="col-xs-12" style="text-align: center;">
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
	</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$('#role').validationEngine();
		//日期
		laydate.render({
			elem: '#backTime',
			type: 'datetime'
			
		});//select2
		$.loadProjectattr($("#carBack"));
		
		//回车里程input  失去焦点 ！=null
		 $("#backcarMileage").blur(function(){
			if($("#backcarMileage").val()==""){
				layer.msg("输入框不能为空！",{icon: 2,time: 1000});
			}else{
				var data = decodeURIComponent($("#role")
						.serialize(), true);			
				//查询本次里程
				var url = "${ctx}/dailyOutBackCar/getTrip";
				 $.ajax({
					 type:"post",
		             url: url,
		             data:data,
		             success: function(data) {
		            	 	if(data==""){
		            	 		 layer.msg("没有出车记录！",{icon: 2});
		    	            	 setTimeout('closeLayer()',1000);  
		            	 	}
		            		$("#trip").val(data.result.trip);
		             },
		             error: function(XMLHttpRequest, textStatus, errorThrown) {
		            	 layer.msg("查询失败",{icon: 2});
		            	 setTimeout('closeLayer()',1000);  
		             },
		           });
			}

		}) 
	});
	//保存回车记录
     $("#save").click(function(e){
    	var data = decodeURIComponent($("#role")
				.serialize(), true);
    	if(!$('#role').validationEngine('validate')){ 
			return;
		}
		var url = "${ctx }/backCarremind/saveCarBack";
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