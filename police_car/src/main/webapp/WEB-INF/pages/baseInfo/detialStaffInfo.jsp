<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>警员信息-详情</title>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-skins.css" />
<link rel="stylesheet" href="${ctx}/assets/css/ace-rtl.css" />
<link rel="stylesheet" href="${ctx}/components/layui/css/layui.css" />
<style type="text/css">
div {
	color: #222222;
	font-size: 15px;
}

.row.row-1 {
	line-height: 30px;
}

.row.row-2 {
	line-height: 25px;
}

div>label {
	color: #555555;
	font-size: 15px;
}
</style>
</head>
<body class="no-skin">
	<!-- /section:basics/navbar.layout -->
	<div class="main-container ace-save-state" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row row-1">
						<%-- <div class="col-xs-4">
							<label>编&nbsp; &nbsp; &nbsp; &nbsp;码：</label>${recodeDo.staffCode}
						</div> --%>
						<div class="col-xs-4">
								<label>警&nbsp; &nbsp; &nbsp; &nbsp;号：</label>${recodeDo.staffNo}
						</div>
						<div class="col-xs-4">
									<label>姓&nbsp; &nbsp; &nbsp; &nbsp;名：</label>${recodeDo.staffName}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="row row-2">
								<div class="col-xs-4">
									<c:if test="${recodeDo.staffSex==1}">
										<label>性&nbsp; &nbsp; &nbsp; &nbsp;别：</label>男
									</c:if>
									<c:if test="${recodeDo.staffSex==2}">
										<label>性&nbsp; &nbsp; &nbsp; &nbsp;别：</label>女
									</c:if>
								</div>
								<div class="col-xs-4">
									<label>助&nbsp;记&nbsp;码&nbsp;：</label>${recodeDo.mnemonicCode}
								</div>
								<div class="col-xs-4">
									<label>民族：</label>${recodeDo.staffNation}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>出生日期：</label>${recodeDo.staffBirthday}
								</div>
								<div class="col-xs-4">
									<label>籍&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯：</label>${recodeDo.staffNative}
								</div>
								<div class="col-xs-4">
									<label>邮箱：</label>${recodeDo.staffEmail}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>部&nbsp; &nbsp; &nbsp; &nbsp;门：</label>${recodeDo.deptName}
								</div>
								<div class="col-xs-4">
									<label>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务：</label>${recodeDo.staffDuty}
								</div>
								<div class="col-xs-4">
									<label>学历：</label>${recodeDo.education}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>毕业院校：</label>${recodeDo.graduateSchool}
								</div>
								<div class="col-xs-8">
									<label>联系电话：</label>${recodeDo.staffPhone}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-12">
									<label>入职时间：</label>${recodeDo.entryTime}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-12">
									<label>身&nbsp;份&nbsp;证&nbsp;：</label>${recodeDo.idcard}
								</div>
							</div>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>家庭住址：</label>${recodeDo.address}
						</div>
						<div class="col-xs-4">
						</div>
						
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>${recodeDo.comments}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
						<c:if test="${recodeDo.status == 2}">
								<label>当前状态：</label>停用
						</c:if>
						<c:if test="${recodeDo.status == 1}">
								<label>当前状态：</label>启用
						</c:if>
						</div>
					</div> 
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/components/layui/layui.js"></script>
	<script type="text/javascript">
		$(function() {

		});
	</script>
</body>
</html>