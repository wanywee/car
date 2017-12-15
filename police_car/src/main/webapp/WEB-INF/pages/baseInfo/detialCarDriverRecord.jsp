<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>驾驶员档案-详情</title>
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
							<label>编码：</label>${recodeDo.staffCode}
						</div> --%>
						<div class="col-xs-4">
							<label>姓名：</label>${recodeDo.staffName}
							
						</div>
						<div class="col-xs-4">
							<label>工号：</label>${recodeDo.staffNo}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="row row-2">
								<div class="col-xs-4">
									<label>助记码：</label>${recodeDo.mnemonicCode}
								</div>
								<div class="col-xs-4">
									<c:if test="${recodeDo.staffSex==1}">
										<label>性别：</label>男
						</c:if>
									<c:if test="${recodeDo.staffSex==2}">
										<label>性别：</label>女
						</c:if>
								</div>
								<div class="col-xs-4">
									<label>从业证：</label>${recodeDo.fromThe}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>民族：</label>${recodeDo.nation}
								</div>
								<div class="col-xs-4">
									<label>出生日期：</label>${recodeDo.birth}
								</div>
								<div class="col-xs-4">
									<label>籍贯：</label>${recodeDo.staffNative}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>邮箱：</label>${recodeDo.email}
								</div>
								<div class="col-xs-4">
									<label>部门：</label>${recodeDo.dept}
								</div>
								<div class="col-xs-4">
									<label>职务：</label>${recodeDo.duty}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>学历：</label>${recodeDo.education}
								</div>
								<div class="col-xs-4">
									<label>毕业院校：</label>${recodeDo.graduteSchool}
								</div>
								<div class="col-xs-4"></div>
							</div>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>身份证号：</label>${recodeDo.idCard}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>入职时间:：</label>${recodeDo.entryTime}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>家庭住址：</label>${recodeDo.address}
						</div>
						<div class="col-xs-4">
							<c:if test="${recodeDo.driverType == 2}">
								<label>类型：</label>专职
							</c:if>
							<c:if test="${recodeDo.driverType == 1}">
								<label>类型：</label>兼职
							</c:if>
						</div>
						<div class="col-xs-4">
							<label>联系电话：</label>${recodeDo.staffPhone}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>专业职称:</label>${recodeDo.professional}
						</div>
					</div>
					<p>外场机动车驾驶证：</p>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>驾驶证号：</label>${recodeDo.driverLicense}
						</div>
						<div class="col-xs-4">
							<label>初领日期:</label>${recodeDo.getTime}
						</div>
						<div class="col-xs-4">
							<label>有效期:</label>${recodeDo.validTime}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>发证机关:</label>${recodeDo.licenceIssuing}
						</div>
						<div class="col-xs-4">
							<label>准驾车型:</label>${recodeDo.drivingType}
						</div>
						<div class="col-xs-4">
							<label>年检时间:</label>${recodeDo.inspectionTime}
						</div>
					</div>
					<p>内场机动车驾驶证：</p>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>驾驶证号：</label>${recodeDo.driverLicense1}
						</div>
						<div class="col-xs-4">
							<label>初领日期:</label>${recodeDo.getTime1}
						</div>
						<div class="col-xs-4">
							<label>有效期:</label>${recodeDo.validTime1}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>发证机关:</label>${recodeDo.licenceIssuing1}
						</div>
						<div class="col-xs-4">
							<label>准驾车型:</label>${recodeDo.drivingType1}
						</div>
						<div class="col-xs-4">
							<label>年检时间:</label>${recodeDo.inspectionTime1}
						</div>
					</div>
					<p>控制区域通行证：</p>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>驾驶证号：</label>${recodeDo.driverLicense2}
						</div>
						<div class="col-xs-4">
							<label>初领日期:</label>${recodeDo.getTime2}
						</div>
						<div class="col-xs-4">
							<label>有效期:</label>${recodeDo.validTime2}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>准驾车型:</label>${recodeDo.drivingType2}
						</div>
						<div class="col-xs-4">
							<label>年检时间:</label>${recodeDo.inspectionTime2}
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