<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>规费管理-详情</title>
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
						<div class="col-xs-6">
							<label>车牌号:</label>${recodeDo.licenseNo}
						</div>
						<div class="col-xs-6">
							<label>费用名称：</label>${recodeDo.moneyname}
						</div>
					
					</div>
					<div class="row row-1">
						<div class="col-xs-6">
							<label>交费日期：</label>
							<fmt:formatDate value="${recodeDo.paymentDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</div>
							<div class="col-xs-6">
							<label>到期日期：</label>
							<fmt:formatDate value="${recodeDo.endDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-6">
							<label>交费金额:</label>${recodeDo.parmentMoney}
						</div>
						<div class="col-xs-6">
							<label>交费方式:</label>${recodeDo.paymentType}
						</div>
						<c:if test="${recodeDo.paymentType=='周期付'}">
						<div class="col-xs-6">
							<label>周期：</label>${recodeDo.period}&nbsp;月
						</div>
						</c:if>
					</div>
					<div class="row row-1">
						<div class="col-xs-6">
							<label>收费单位:</label>${recodeDo.chargeUnitName}
						</div>
						<div class="col-xs-6">
							<label>经手人:</label>${recodeDo.staffName}
						</div>
					</div>
					
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>${recodeDo.remark}
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