<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>车辆信息-详情</title>
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
div > img {
	max-width: 100%;
	max-height: 100%;
	margin: 0 auto;
}
div.item {
	width: 600px;
	height: 400px;
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
							<label>车 &nbsp;牌  &nbsp;号：</label>${carBaseVehicleDO.licenseno}
						</div>
					</div>
					<div class="row">
					<div class="col-xs-12">
					<div class="row row-2">
						<div class="col-xs-4">
							<label>品&nbsp; &nbsp; &nbsp; &nbsp;牌：</label>${carBaseVehicleDO.brandName}
						</div>
						<div class="col-xs-4">
							<label>车辆型号：</label>${carBaseVehicleDO.modelName}
						</div>
						<div class="col-xs-4">
							<label>车辆类型：</label>${carBaseVehicleDO.typeName}
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-4">
							<label>车辆颜色：</label>${carBaseVehicleDO.colorName}
						</div>
						<div class="col-xs-4">
							<label>载&nbsp; &nbsp; &nbsp; &nbsp;重：</label>${carBaseVehicleDO.loading} 吨
						</div>
						<div class="col-xs-4">
							<label>座&nbsp; 位&nbsp; 数：</label>${carBaseVehicleDO.seats}
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-4">
							<label>车辆油耗：</label>${carBaseVehicleDO.consumption}L/100km
						</div>
						<div class="col-xs-4">
							<label>初始里程：</label>${carBaseVehicleDO.startMile}km
						</div>
						<div class="col-xs-4">
							<label>保养里程：</label>${carBaseVehicleDO.mileage}km
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-4">
							<label>保养周期：</label>${carBaseVehicleDO.period} 天
						</div>
						<div class="col-xs-4">
							<label>发动机型号：</label>${carBaseVehicleDO.engineno}
						</div>
						<div class="col-xs-4">
							<label>自&nbsp; &nbsp; &nbsp; &nbsp;重：</label>${carBaseVehicleDO.carWeight} 吨
						</div>
					</div>
					</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>车&nbsp;  架&nbsp;  号：</label>${carBaseVehicleDO.chassisno}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>供 &nbsp;应 &nbsp;商：</label>${carBaseVehicleDO.supplyName}
						</div>
						<div class="col-xs-4">
							<label>购入价格：</label>${carBaseVehicleDO.buyPrice} 元
						</div>
						<div class="col-xs-4">
							<label>购入日期：</label><fmt:formatDate value="${carBaseVehicleDO.buyTime }" pattern="yyyy-MM-dd"/>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-4">
							<label>驾 &nbsp;驶 &nbsp;员：</label>${carBaseVehicleDO.dreiverName}
						</div>
						<div class="col-xs-4">
							<label>所属部门：</label>${deptName}
						</div>
						<div class="col-xs-4">
							<label>当前状态：</label>${nowStatus}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>${carBaseVehicleDO.comments}
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-xs-12 col-md-7">
							<c:if test="${carBaseVehicleDO.photoUrlList == null || carBaseVehicleDO.photoUrlList.size() == 0 }">
								<img alt="暂无图片" src="${ctx }/img/noPhoto.png">
							</c:if>
							<c:if test="${carBaseVehicleDO.photoUrlList != null && carBaseVehicleDO.photoUrlList.size() > 0 }">
								<div id="myCarousel" class="carousel slide">
									<!-- 轮播（Carousel）指标 -->
									<ol class="carousel-indicators">
										<c:forEach items="${carBaseVehicleDO.photoUrlList }" var="photoUrl" varStatus="status">
											<c:if test="${status.index ==0 }">
												<li data-target="#myCarousel" data-slide-to="${status.index }" class="active"></li>
											</c:if>
											<c:if test="${status.index > 0 }">
												<li data-target="#myCarousel" data-slide-to="${status.index }"></li>
											</c:if>
										</c:forEach>
									</ol>
									<!-- 轮播（Carousel）项目 -->
									<div class="carousel-inner">
										<c:forEach items="${carBaseVehicleDO.photoUrlList }" var="photoUrl" varStatus="status">
											<c:if test="${status.index == 0}">
												<div class="item active">
													<img
													src="${ctx}/${photoUrl}"
													alt="${status.index } slide">
												</div>
											</c:if>
											<c:if test="${status.index > 0}">
												<div class="item">
													<img src="${ctx}/${photoUrl}"
													alt="${status.index } slide">
												</div>
											</c:if>
										</c:forEach>
									</div>
									<!-- 轮播（Carousel）导航 -->
									<a class="carousel-control left" href="#myCarousel"
										data-slide="prev">&lsaquo;</a> <a
										class="carousel-control right" href="#myCarousel"
										data-slide="next">&rsaquo;</a>
								</div>
							</c:if>
						</div>
						<div class="col-md-3"></div>
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