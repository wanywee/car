<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>公司信息</title>
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
							<div class="row col-md-offset-2" >
										<div class="form-group col-xs-9" style="color: red">
											<h2 class="col-xs-4">提示：</h2>
										</div>
										<div class="form-group col-xs-12 col-md-offset-2" style="color: red">
											<h4>公司信息是指本单位信息，在打印报表时会用到</h4>
										</div>
									</div>
								<form  id="role">
									<div class="row  col-md-offset-2"  style="margin-top: 40px">
										<div class="form-group col-xs-9">
											<h3 style="text-align: center;" class="col-xs-3 control-label no-padding-right " for="form-field-select-3">公司名称：</h3>
											<input style="margin-top: 18px" class="col-xs-9 validate[required,maxSize[64],custom[onlyLetterNumber]] " type="text"name= "companyName" value="${carSysUser.companyName }" id="form-field-1" placeholder="公司名称" />
										</div>
									</div>
								</form>
								<div class="row">
										<div class="col-xs-12" style="text-align: center;margin-top:40px">
											<button id="save"  class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
												<i class="ace-icon fa fa-check red2" ></i>
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
		var url = "${ctx }/user/saveCompany";
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