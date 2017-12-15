<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>新增驾驶员档案记录</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
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
											<label class="col-xs-3 control-label no-padding-right" for="staffNo">工号:</label>
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
										
										
										<div class="col-xs-6 form-group">
										<c:if test="${recodeDo.mnemonicCode!=null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="mnemonicCode">助记码:</label>
											<div class="col-xs-9">
												<input name="mnemonicCode"
													value="${recodeDo.mnemonicCode}" type="text"
													id="mnemonicCode" class="validate[required,maxSize[16]] text-input" />
											</div>
										</c:if>
										<c:if test="${recodeDo.mnemonicCode==null}">
											<!-- #section:plugins/input.chosen -->
											<label class="col-xs-3 control-label no-padding-right " for="mnemonicCode">助记码：</label>
											<div class="col-xs-9">
												<input name="mnemonicCode"
													value="系统自动生成" type="text" readonly="readonly"
													id="mnemonicCode" class="validate[required,maxSize[16]] text-input" />
											</div>
										</c:if>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="staffName">姓名:</label>
											<div class="col-xs-9">
												 <input type="text" name="staffName" id="staffName" class="validate[required,maxSize[8]] text-input" value="${recodeDo.staffName}"/>
											</div>
										</div>

										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="staffSex">性别:</label>
											<div class="col-xs-9" >
												<c:if test="${recodeDo.staffSex==2}">
													<input type="radio"  name="staffSex" value="2" checked="checked"/>女
												 	<input type="radio"  name="staffSex" value="1" />男
												</c:if>
												<c:if test="${recodeDo.staffSex==1}">
													<input type="radio"  name="staffSex" value="2" />女
												 	<input type="radio"  name="staffSex" value="1" checked="checked"/>男
												</c:if>
												<c:if test="${recodeDo.staffSex==null}">
													<input type="radio"  name="staffSex" value="2"  />女
												 	<input type="radio"  name="staffSex" value="1" checked="checked"/>男
												</c:if>
											</div>
										</div>
									</div>
									<div class="row">
										
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="nation">民族:</label>

											<div class="col-xs-9">
												<input id="nation" type="text" name="nation" class="validate[required,maxSize[8]] text-input" value="${recodeDo.nation}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="staffPhone">联系电话:</label>
											<div class="col-xs-9">
												<input id="staffPhone" type="text" name="staffPhone" class="validate[required,custom[mobile]] text-input" value="${recodeDo.staffPhone}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="birth">出生日期:</label>
											<div class="col-xs-9">
												<input id="birth"  name="birth"  class="validate[required] text-input" value="${recodeDo.birth}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="staffNative">籍贯:</label>

											<div class="col-xs-9">
												<input id="staffNative" type="text" name="staffNative" class="validate[required,maxSize[8]] text-input" value="${recodeDo.staffNative}" />
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="email">邮箱:</label>
											<div class="col-xs-9">
												<input id="email" type="text" name="email"  class="validate[required,custom[email]] text-input" value="${recodeDo.email}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="dept">部门:</label>
												<div class="col-xs-9">
														<%-- <select class="validate[required]" id="dept" select2=""
															style="width: 160px" name="dept" readonly="readonly"
															data-type="${recodeDo.dept}"
															data-value="${recodeDo.deptmentId}"
															data-url="${ctx}/common/getSelect2ListDeptment">
														</select> --%>
														<input id="dept" type="text" name="dept"  readonly="readonly" value="涉外"/>
												</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="duty">职务:</label>

											<div class="col-xs-9">
												<input type="text" id="duty" name="duty" class="validate[required,maxSize[12]] text-input" value="${recodeDo.duty}" />
											</div>
										</div>

										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="education">学历:</label>
											
											<div class="col-xs-9">
												 <select class="validate[required]" id="education" select2=""
													name="education" data-type="${recodeDo.education}" data-value="${recodeDo.education}"
													data-url="${ctx}/getListDisplay?kind=DRIVER_EDUCATION" style="width: 163px"></select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="graduteSchool">毕业院校:</label>

											<div class="col-xs-9">
												<input type="text" id="graduteSchool" name="graduteSchool" class="validate[required,maxSize[64]] text-input" value="${recodeDo.graduteSchool}"/>
											</div>
										</div>

										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="idCard">身份证号:</label>

											<div class="col-xs-9">
												<input type="text" id="idCard" name="idCard" class="validate[required,custom[idCard]] text-input" value="${recodeDo.idCard}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="address">家庭住址:</label>

											<div class="col-xs-9">
												<input type="text" id="address" name="address"  class="validate[required,maxSize[64]] text-input"  value="${recodeDo.address}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="entryTime">入职时间:</label>
											<div class="col-xs-9">
												<input id="entryTime" name="entryTime"  class="validate[required]" value="${recodeDo.entryTime}"/>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="driverType">类型:</label>
											
											<div class="col-xs-9">
												 <select class="validate[required]" id="driverType" select2=""
													name="driverType" data-type="${recodeDo.drivertypeName}" data-value="${recodeDo.driverType}"
													data-url="${ctx}/getListKeyCode?kind=DRIVER_TYPE" style="width: 163px"></select>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="fromThe">从业证:</label>

											<div class="col-xs-9">
													<input id="fromThe" type="text" name="fromThe" class="validate[required,maxSize[32]] text-input"  value="${recodeDo.fromThe}"/>
											</div>
										</div>
										<%-- <div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="location">所在地区:</label>

											<div class="col-xs-9">
												<input type="text" id="location" name="location" value="${recodeDo.location}"/>
											</div>
										</div> --%>

									</div>

									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="professional">专业职称:</label>
											<div class="col-xs-9">
													<input id="professional" type="text" name="professional" class="validate[required,maxSize[32]] text-input"  value="${recodeDo.professional}" />
											</div>
										</div>
									</div>
									<p>民用车驾驶证：</p>
									<p>-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="driverLicense">驾驶证号:</label>
											<div class="col-xs-9">
													<input id="driverLicense" type="text" name="driverLicense"  class="validate[required,maxSize[32]] text-input" value="${recodeDo.driverLicense}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="getTime">初领日期:</label>
											<div class="col-xs-9">
												<input id="getTime" name="getTime"  class="validate[required]" value="${recodeDo.getTime}" />
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="validTime">有效期:</label>
											<div class="col-xs-9">
													<input id="validTime"  name="validTime" class="validate[required]" value="${recodeDo.validTime}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="licenceIssuing">发证机关:</label>
											<div class="col-xs-9">
												<input id="licenceIssuing" type="text" name="licenceIssuing" class="validate[required,maxSize[32]] text-input" value="${recodeDo.licenceIssuing}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="drivingType">准驾车型:</label>
											<div class="col-xs-9">
													<input id="drivingType" type="text" name="drivingType" class="validate[required,maxSize[12]] text-input"  value="${recodeDo.drivingType}" />
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="inspectionTime">年检时间:</label>
											<div class="col-xs-9">
												<input id="inspectionTime"  name="inspectionTime"  class="validate[required]" value="${recodeDo.inspectionTime}"/>
											</div>
										</div>

									</div>
									
									<p>警车驾驶证：</p>
									<p>-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="driverLicense1">驾驶证号:</label>
											<div class="col-xs-9">
													<input id="driverLicense1" type="text" name="driverLicense1"    value="${recodeDo.driverLicense1}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="getTime1">初领日期:</label>
											<div class="col-xs-9">
												<input id="getTime1" name="getTime1"   value="${recodeDo.getTime1}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="validTime1">有效期:</label>
											<div class="col-xs-9">
													<input id="validTime1"  name="validTime1"  value="${recodeDo.validTime1}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="licenceIssuing1">发证机关:</label>
											<div class="col-xs-9">
												<input id="licenceIssuing1" type="text" name="licenceIssuing1"  value="${recodeDo.licenceIssuing1}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="drivingType1">准驾车型:</label>
											<div class="col-xs-9">
													<input id="drivingType1"  type="text" name="drivingType1" value="${recodeDo.drivingType1}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="inspectionTime1">年检时间:</label>
											<div class="col-xs-9">
												<input id="inspectionTime1"  name="inspectionTime1"  value="${recodeDo.inspectionTime1}"/>
											</div>
										</div>

									</div>
									<p>特种车驾驶证：</p>
									<p>-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="driverLicense1">驾驶证号:</label>
											<div class="col-xs-9">
													<input id="driverLicense2" type="text" name="driverLicense2"    value="${recodeDo.driverLicense2}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="getTime1">初领日期:</label>
											<div class="col-xs-9">
												<input id="getTime2" name="getTime2"   value="${recodeDo.getTime2}"/>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="validTime2">有效期:</label>
											<div class="col-xs-9">
													<input id="validTime2"  name="validTime2"  value="${recodeDo.validTime2}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="licenceIssuing1">发证机关:</label>
											<div class="col-xs-9">
												<input id="licenceIssuing2" type="text" name="licenceIssuing2" /> <%-- value="${recodeDo.licenceIssuing2}" --%>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="drivingType1">准驾车型:</label>
											<div class="col-xs-9">
													<input id="drivingType2"  type="text" name="drivingType2" value="${recodeDo.drivingType2}"/>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="inspectionTime1">年检时间:</label>
											<div class="col-xs-9">
												<input id="inspectionTime2"  name="inspectionTime2"  value="${recodeDo.inspectionTime2}"/>
											</div>
										</div>

									</div>
									
									<%-- <div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="drivingType2">准驾车型:</label>
											<div class="col-xs-9">
													<input id="drivingType2" type="text" name="drivingType2" value="${recodeDo.drivingType2}"/>
											</div>
										</div>
									</div> --%>
									<p>-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</p>
									 <div class="row">
									 	<c:if test="${recodeDo.id!=null}">
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_workOn"   class="btn btn-white btn-default btn-round" type="button">&nbsp;转岗、上岗培训记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_training"  class="btn btn-white btn-default btn-round" type="button">&nbsp;在岗复训记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_traffic"  class="btn btn-white btn-default btn-round" type="button">&nbsp;交通事故、违规记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_dept"  class="btn btn-white btn-default btn-round" type="button">&nbsp;部门变更记录</button>
											</div>
									 	</c:if>
										<c:if test="${recodeDo.id==null}">
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_workOn" style="display:none" class="btn btn-white btn-default btn-round" type="button">&nbsp;转岗、上岗培训记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_training" style="display:none" class="btn btn-white btn-default btn-round" type="button">&nbsp;在岗复训记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_traffic" style="display:none" class="btn btn-white btn-default btn-round" type="button">&nbsp;交通事故、违规记录</button>
											</div>
											<div class="col-xs-3" style="text-align: center;">
												<button id="btn_dept" style="display:none" class="btn btn-white btn-default btn-round" type="button">&nbsp;部门变更记录</button>
											</div>
									 	</c:if>
									</div>
									<p></p>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="comments">备注:</label>

											<div class="col-xs-9">
												<textarea id="comments" name="comments" class="validate[maxSize[100]] text-input autosize-transition form-control" >${recodeDo.comments}</textarea>
											</div>
										</div>

									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label class="col-xs-3 control-label no-padding-right" for="status">是否停用:</label>

											<div class="col-xs-9">
											<c:if
												test="${recodeDo.status == 1||recodeDo.status==null}">
												<input name="status"  type="checkbox">
											</c:if>
											<c:if test="${recodeDo.status == 2}">
												<input name="status" type="checkbox" 
													checked="checked" >
											</c:if>
											</div>
										</div>

									</div>
									
									<div class="row">
										<div class="col-xs-12" style="text-align: center;">
											<button  id="comfirm_button"  class="btn btn-white btn-default btn-round" style="margin-right: 50px;">
												<i class="ace-icon fa fa-check red2"></i>
												保存
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
									<input type="text"  id="brevityId" name="brevityId" style="display:none" value="${recodeDo.id}" >
									<input type="text"  name="staffId"  style="display:none" value="${recodeDo.staffId}" >
									<input type="text"  name="licenseId" style="display:none" value="${recodeDo.licenseId}" >
									<input type="text"  name="licenseId1" style="display:none" value="${recodeDo.licenseId1}" >
									<input type="text"  name="licenseId2" style="display:none" value="${recodeDo.licenseId2}" >
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
	    "alertText": "* 该工号已被使用"
	};
	$(function(){
		var postId=0;
		laydate.render({
			elem:'#birth',
			type:'date',
		});
		laydate.render({
			elem:'#entryTime',
			type:'datetime',
		});
		laydate.render({
			elem:'#validTime',
			type:'date',
		});
		laydate.render({
			elem:'#inspectionTime',
			type:'date',
		});
		laydate.render({
			elem:'#getTime',
			type:'date',
		});
		laydate.render({
			elem:'#validTime1',
			type:'date',
		});
		laydate.render({
			elem:'#inspectionTime1',
			type:'date',
		});
		laydate.render({
			elem:'#getTime1',
			type:'date',
		});	laydate.render({
			elem:'#validTime2',
			type:'date',
		});
		laydate.render({
			elem:'#inspectionTime2',
			type:'date',
		});
		laydate.render({
			elem:'#getTime2',
			type:'date',
		});
	
	})
	
	 //上岗转岗培训记录
    function editWorkRecord(ID){
    	if(ID == ''||ID == 'undefined' ){
    		ID = 0;
    	}
    	index = layer.open({
    	    type: 2,
    	    scrollbar: false,
    	    title: '上岗转岗培训记录',
    	    maxmin: true,
	   		shadeClose: false,
	   		anim: 5,
    	    area: ['95%', '95%'],
    	    content: '${ctx}/baseInfo/listWorkOnRecord?ID='+ID,
    	    end:function(index){
    	    	jqGrid.trigger("reloadGrid");
    	    }
    	});
    }
	//在岗复训记录
	function editTrainingRecord(ID){
    	if(ID == ''||ID == 'undefined' ){
    		ID = 0;
    	}
    	index = layer.open({
    	    type: 2,
    	    scrollbar: false,
    	    title: '在岗复训记录',
    	    maxmin: true,
	   		shadeClose: false,
	   		anim: 5,
    	    area: ['95%', '95%'],
    	    content: '${ctx}/baseInfo/listTrainingRecord?ID='+ID,
    	    end:function(index){
    	    	jqGrid.trigger("reloadGrid");
    	    }
    	});
    } 
	//交通事故、违规记录记录
	function editTrafficRecord(ID){
    	if(ID == ''||ID == 'undefined' ){
    		ID = 0;
    	}
    	index = layer.open({
    	    type: 2,
    	    scrollbar: false,
    	    title: '交通事故、违规记录',
    	    maxmin: true,
	   		shadeClose: false,
	   		anim: 5,
    	    area: ['95%', '95%'],
    	    content: '${ctx}/baseInfo/listTrafficRecord?ID='+ID,
    	    end:function(index){
    	    	jqGrid.trigger("reloadGrid");
    	    }
    	});
    } 
	//部门变更记录
	function editDeptRecord(ID){
    	if(ID == ''||ID == 'undefined' ){
    		ID = 0;
    	}
    	index = layer.open({
    	    type: 2,
    	    scrollbar: false,
    	    title: '部门变更记录',
    	    maxmin: true,
	   		shadeClose: false,
	   		anim: 5,
    	    area: ['95%', '95%'],
    	    content: '${ctx}/baseInfo/listDeptRecord?ID='+ID,
    	    end:function(index){
    	    	jqGrid.trigger("reloadGrid");
    	    }
    	});
    } 
	 $("#form").validationEngine();
	 $('#comfirm_button').click(function(e) {
		   if (!$('#form').validationEngine('validate')) {
				return;
			} 
			$.ajax({
				type : 'POST',
				datatype : 'text',
				url : '${ctx}/baseInfo/editCarDriverInfo',
				data : $('#form').serialize(),
				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
				success : function(data) {
					if(data.result!=null&&data.addDriver==1){
						postId=data.result;
						$('#comfirm_button').attr('disabled',true);
						layer.alert("增加档案成功");
						$('#btn_workOn').css('display',"block");
						$('#btn_training').css('display',"block");
						$('#btn_traffic').css('display',"block");
						$('#btn_dept').css('display',"block");
					}else if(data.result!=null&&data.updateDriver==1){
						postId=data.driverID;
						layer.alert("修改档案成功");
					}else{
						layer.alert("操作失败:"+data.dataWrong);
					}
				}
			});
			e.preventDefault();
		});
	//在岗、上岗培训点击事件
	$("#btn_workOn").click(function(){
		if($("#brevityId").val()==''||$("#brevityId").val()=='undefined'){
			editWorkRecord(postId);
		}else{
			editWorkRecord($("#brevityId").val());
		} 
	});
	//在岗复训记录
	$("#btn_training").click(function(){
		if($("#brevityId").val()==''||$("#brevityId").val()=='undefined'){
			editTrainingRecord(postId);
		}else{
			editTrainingRecord($("#brevityId").val());
		} 
		
	});
	//交通事故违规记录
	$("#btn_traffic").click(function(){
		if($("#brevityId").val()==''||$("#brevityId").val()=='undefined'){
			editTrafficRecord(postId);
		}else{
			editTrafficRecord($("#brevityId").val());
		} 
	});
	//部门变更记录
	$("#btn_dept").click(function(){
		if($("#brevityId").val()==''||$("#brevityId").val()=='undefined'){
			editDeptRecord(postId);
		}else{
			editDeptRecord($("#brevityId").val());
		} 
	});
	//加载select
/* 	$.loadProjectattr($('#dept')); */
	$.loadProjectattr($('#education'));
	$.loadProjectattr($('#driverType'));
	//工号唯一性验证
	$("#staffNo").blur(function(e){
		var staffNo=$("#staffNo").val();
		$.ajax({
			type : 'POST',
			datatype : 'text',
			url : '${ctx}/baseInfo/validateStaffNo',
			data : {"staffNo":staffNo},
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