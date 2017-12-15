<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>往来单位-详情</title>
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
	<div class="main-container ace-save-state" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row row-2">
								<div class="col-xs-6">
									<label>单位名称：</label>${carBaseContactsDo.companyName}
								</div>
								<div class="col-xs-6">
									<label>助&nbsp;&nbsp;记&nbsp;&nbsp;码：</label>${carBaseContactsDo.memonicCode}
								</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="row row-2">
								<div class="col-xs-6">
									<label>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</label>${carBaseContactsDo.address}
								</div>
								<div class="col-xs-6">
									<label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label>${carBaseContactsDo.companyTypeName}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-6">
									<label>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</label>${carBaseContactsDo.linkman}
								</div>
								<div class="col-xs-6">
									<label>联系方式：</label>${carBaseContactsDo.phone}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-6">
									<label>传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：</label>${carBaseContactsDo.fax}
								</div>
								<div class="col-xs-6">
									<label>网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;站：</label>${carBaseContactsDo.webSite}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-6">
									<label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编：</label>${carBaseContactsDo.postcode}
								</div>
								<div class="col-xs-6">
									<label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>${carBaseContactsDo.email}
								</div>
								
							</div>
							<div class="row row-2">
								<div class="col-xs-6">
									<label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label>${carBaseContactsDo.companyAccount}
								</div>
								<div class="col-xs-6">
									<label>开&nbsp;&nbsp;户&nbsp;&nbsp;行：</label>${carBaseContactsDo.depositBank}
								</div>
							</div>
							<div class="row row-2">
								
								<div class="col-xs-12">
									<label>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label>${carBaseContactsDo.comments}
								</div>
							</div>
						</div>
					</div>
					<div class="row row-1">
						
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