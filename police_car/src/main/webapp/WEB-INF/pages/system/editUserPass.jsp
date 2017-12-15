<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>修改密码</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/css/check/build.css" />
<style>
		ul li{
			list-style-type: none;
			margin-top: 40px;
		}
		ul{
		float: left;
		margin-top: 60px;
		}
		.labelUl li{
			margin-top: 47px;
		}
</style>
</head>

<body>
<div class="container">
<div class="col-xs-12 col-md-offset-2" >
<form id="editPass" method="post">
	<input type="hidden" id="id" name="id" class="text" readonly="readonly" value="${carSysUser.id}" />
	<input type="hidden" id="Upassword" name="password" class="text" readonly="readonly" value="${carSysUser.password}" />
	<ul class="labelUl">
	<li>  <label class="label_name">原&nbsp;&nbsp;密&nbsp;码</label></li>
	<li><label class="label_name">新&nbsp;&nbsp;密&nbsp;码</label></li>
	<li>  <label class="label_name">确认密码</label></li>
	</ul>
		<ul  class="col-xs-6">
		 <li>
           
             <input class="form-control validate[required,maxSize[64],custom[onlyLetterNumber]]" type="password" name="password" placeholder="密码" id="password"/>
             </li>
             <li>
             
              <input class=" form-control validate[required,maxSize[64],custom[onlyLetterNumber]]"  type="password" name="newPass" placeholder="密码" id="Nes_pas"/>
             </li>
             <li>
              <input class=" form-control validate[required,maxSize[64],custom[onlyLetterNumber]]"  type="password" name="再次确认密码" placeholder="密码" id="c_mew_pas"/>
             </li>
             
            </ul>
					</form>
					<div class="row" style="margin-top: 40px">
				<div class="col-xs-8" style="text-align: center;">
					<button id="savePassword"  class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
					<i class="ace-icon fa fa-check red2" ></i>
						确定
					</button>
					</div>
					</div>
					</div>
					</div>
</body>
<script type="text/javascript">
$(function(){
	$('#editPass').validationEngine();
});
$("#savePassword").click(function(e){
	if(!$('#editPass').validationEngine('validate')){ 
		return;
	}	
	/* 判断新密码 确认密码，是否一致  */
	else if($("#Nes_pas").val()!=$("#c_mew_pas").val()){
		 layer.msg("输入新密码与确认密码不一致,请输入与上面相同的密码！",{icon: 2});
		 return;
	}else{
		/* 判断旧密码 原密码，是否一致  */
	
	        	
	        	 var data = {"newPass":$("#Nes_pas").val(),"id":$("#id").val(),"password":$("#password").val()}; 
	     		 var url = "${ctx }/user/modifyPass";
	     		 $.ajax({
	     			 type:"post",
	     	         url: url,
	     	    	 data:data,
	     	         success: function(data) {
	     	        	 if(data.message=="error"){
	    	        		 layer.msg("输入旧密码与原密码不一致",{icon: 2});
	    	     			return;
	    	        	 }
	     	        	 	 layer.msg(data.message,{icon: 1});
	     	        	 	 setTimeout('closeLayer()',1000);
	     	        	 	window.location.href="${ctx }/loginOut";
	     	         },
	     	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	     	        	 layer.msg("修改失败",{icon: 2});
	     	        	 setTimeout('closeLayer()',1000);  
	     	         },
	     	       });
		 } 
	   });
		
		
		
		
		
   
</script>
</html>