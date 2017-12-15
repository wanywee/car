<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="org.apache.xmlbeans.impl.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
	<head>
	 <%@ include file="./common/header.jsp" %> 
	 <link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
	 <link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
	 <link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
     <link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
		<style type="text/css">
			#login_id{
				background-image: url(${ctx }/img/flpo.jpg);
				background-repeat: no-repeat;
				background-position: 100%;
				background-size: 100% 100%;
			}
			#login_row{
				margin-top: 120px;
			}
			.clearfix{
				margin-top: 17px;
			}
		</style>	
	</head>
<%
    String name="";
	String password = "";
    System.out.println(request.getCookies());//第一次访问时，后台输出null，刷新一次即能输出相应内容
    if(null != request.getCookies()){
        Cookie[] cookie = request.getCookies();
        for(int i = 0; i < cookie.length; i++)
        {
            if(cookie[i].getName().equals("username"))
            {
                name = cookie[i].getValue();             
            }
            if(cookie[i].getName().equals("password"))
            {
                password = cookie[i].getValue();
                //解码 
           		/* password = new String(Base64.decode(password.getBytes()), "utf-8"); */
                
            }
        }
    }
    %> 



	<body onload="ready()" class="login-layout" id="login_id">
		<div class="main-container">
			<div class="main-content">
				<div class="row" id="login_row">
					<div class="col-sm-10 col-sm-offset-1" style="opacity: 0.8;">
						<div class="login-container">
							<div class="center">
								<img src="${ctx }/img/brank.png">
							</div>
							<div class="position-relative" style="margin-top: 30px">
								<div id="login-box">
									<div class="">
										<div class="widget-main">
											<form id="form-article-add">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-left">
															<input style="border-radius: 5px !important;border: 1px solid #FFFFFF;"
															  type="text" name="username" value="<%=name %>" class="form-control input3" placeholder="账号"  />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-left">
															<input style="border-radius: 5px !important;border: 1px solid #FFFFFF;"
															 class="form-control input1" type="password"  value="<%=password %>" name="password" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<label class="block clearfix">
													  <div class="form-group form-code">
												      	<div class="code">
													        <input style="border-radius: 5px !important;border: 1px solid #FFFFFF;height:35px;width:195px;float:left;" class=" form-control input2"  id="validateCode" name="validateCode" placeholder="验证码" type="text">
												      		 <img name="vaimg" style="float:right;width:30%" height="30%"  style="border-bottom:1px solid #D6D6D6;margin:0;" alt="点击图片获取新的验证码" onclick="change()"/>
												      	</div>
												      </div>
													</label>
													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" name="remember" class="ace"  />
															<span class="lbl" style="color: white;"> 记住我</span>
														</label>
													</div>
													<div class="clearfix">
														<button type="button" class="width-100 pull-right btn btn-sm btn-primary" onclick="login_skip()">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>

										</div>

									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.login-box -->
							</div>
							<!-- /.position-relative -->

							<!-- <div class="navbar-fixed-top align-right">
								<br /> &nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span> &nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span> &nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$('#form-article-add').validationEngine();
		
			jQuery(function($) {
				$(document).on('click', '.toolbar a[data-target]', function(e) {
					e.preventDefault();
					var target = $(this).data('target');
					$('.widget-box.visible').removeClass('visible'); //hide others
					$(target).addClass('visible'); //show target
				});
			});

			//you don't need this, just used for changing background
			/* jQuery(function($) {
				$('#btn-login-dark').on('click', function(e) {
					$('body').attr('class', 'login-layout');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'blue');
					e.preventDefault();
				});
				$('#btn-login-light').on('click', function(e) {
					$('body').attr('class', 'login-layout light-login');
					$('#id-text2').attr('class', 'grey');
					$('#id-company-text').attr('class', 'blue');
					e.preventDefault();
				});
				$('#btn-login-blur').on('click', function(e) {
					$('body').attr('class', 'login-layout blur-login');
					$('#id-text2').attr('class', 'white');
					$('#id-company-text').attr('class', 'light-blue');

					e.preventDefault();
				});

			}); */
			//输入框验证
			 $(function(){
				/* $(".input3").focus(function(){
					  $(".input3").addClass('validate[required]');
					});
				$(".input1").focus(function(){
					  $(".input1").addClass('validate[required]');
					});
				$(".input2").focus(function(){
					  $(".input2").addClass('validate[required]');
					}); */
				
				 inputBlur();
			})
			function inputBlur() {
				 $(".input3").addClass("validate[custom[onlyLetterNumber],maxSize[32]]");
				
				$(".input1").addClass("validate[validate[custom[onlyLetterNumber],maxSize[32]]");
				
				$(".input2").addClass("validate[validate[custom[onlyLetterNumber],maxSize[32]]");
				

					}
			function login_skip(){
				 if($(".input1").val()=="" || $(".input2").val()=="" || $(".input3").val()==""){
					 layer.msg("输入框不能为空！",{icon: 2});
	            	 setTimeout('closeLayer()',1000); 
	            	 return;
				 }
				 if(!$('#form-article-add').validationEngine('validate')){ 
						return;
					}
			    	
				var data = decodeURIComponent($("#form-article-add").serialize(),true);
				var url ="${ctx}/home";
				 $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function(data) {
                	 console.log(data);
                	if(data == "index"){
                        window.location.href = "${ctx }/main";
                	}
                	else{
                		 layer.alert(data, {
                             icon: 2,
                             skin: 'layer-ext-moon'
                           });
                		 change();
                		 $(".input2").val("");
                	} 
                	
                	},error: function(XMLHttpRequest, textStatus, errorThrown) {
	                  alert(textStatus);
	                } 
                }) 
				
			} 
			$(function(){
				change();
			});
			$(document).keydown(function(event){
				 if(!$('#form-article-add').validationEngine('validate')){ 
						return;
					}
				if(event.keyCode == 13){//keyCode=13是回车键
					login_skip();
				}
			});
			function change(){
				document.vaimg.src="${ctx}/validateCode?"+Math.random();
			}
			  function ready(){
		    	    if(top.location!=self.location)
		    	    {
		    	        top.location=self.location;
		    	    }
		    	}
			
		</script>
	</body>

</html>