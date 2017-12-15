<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>违章记录-详情</title>
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
div > label {
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
						<div class="col-xs-12">
							<label>车 &nbsp;牌  &nbsp;号：</label>${recode.licenseno }
						</div>
					</div>
					<div class="row">
					<div class="col-xs-12">
					<div class="row row-2">
						<div class="col-xs-6">
							<label>违章项目：</label>${recode.violationProject}
						</div>
						<div class="col-xs-6">
							<label>驾 &nbsp;驶  &nbsp;员：</label>${recode.staffName}
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-6">
							<label>扣&nbsp; &nbsp; &nbsp; &nbsp;分：</label>${recode.points}
						</div>
						<div class="col-xs-6">
							<label>罚&nbsp; &nbsp; &nbsp; &nbsp;款：</label>${recode.fine}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>违章日期：</label><fmt:formatDate value="${recode.violationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>违章地点：</label>${recode.violationAddress}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>${recode.remark}
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>