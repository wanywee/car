<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>回车记录-详情</title>
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
							<label>车 &nbsp;牌  &nbsp;号：</label>${carOutAndBackDO.licenseno}
						</div>
					</div>
					<div class="row">
					<div class="col-xs-12">
					<div class="row row-2"style="margin-top: 20px">
						<div class="col-xs-8">
							<label>回车时间：</label>
							<fmt:formatDate value="${carOutAndBackDO.backTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</div>
					</div>
					<div class="row row-2"style="margin-top: 20px">
						<div class="col-xs-6">
							<label>回车行程：</label>${carOutAndBackDO.backcarMileage} (km)
						</div>
					</div>
					<div class="row row-2"style="margin-top: 20px">
					<div class="col-xs-6">
							<label>本次行程：</label>${carOutAndBackDO.trip} (km)
						</div>
					</div>
					<div class="row row-2"style="margin-top: 20px">
						<div class="col-xs-6">
							<label>停放位置：</label>${carOutAndBackDO.parkPosition}
						</div>
					</div>
				<div class="row row-2"style="margin-top: 20px">
						<div class="col-xs-12">
						<label>备 &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;注：</label>
							${carOutAndBackDO.backRemark}
					</div>
					</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/components/layui/layui.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>