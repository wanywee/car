<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>维修记录-详情</title>
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
							<label>车 &nbsp;牌  &nbsp;号：</label>${carDailyRepairRecord.licenseno}
						</div>
					</div>
					<div class="row">
					<div class="col-xs-12">
					<div class="row row-2">
						<div class="col-xs-6">
							<label>修 &nbsp;理  &nbsp;厂：</label>${carDailyRepairRecord.repairPdName}
						</div>
						<div class="col-xs-6">
							<label>经 &nbsp;手  &nbsp;人：</label>${carDailyRepairRecord.staffName}
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-6">
							<label>送修日期：</label><fmt:formatDate value="${carDailyRepairRecord.repairTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
						<div class="col-xs-6">
							<label>预计取车：</label><fmt:formatDate value="${carDailyRepairRecord.estimateTackcar}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</div>
					</div>
					<div class="row row-2">
						<div class="col-xs-12">
							<label>送修原因：</label>${carDailyRepairRecord.repairReason}
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;注：</label>${carDailyRepairRecord.remark}
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-xs-12 col-md-7">
							<c:if test="${carDailyRepairRecord.photoUrlList == null || carDailyRepairRecord.photoUrlList.size() == 0 }">
								<img alt="暂无图片" src="${ctx }/img/noPhoto.png">
							</c:if>
							<c:if test="${carDailyRepairRecord.photoUrlList != null && carDailyRepairRecord.photoUrlList.size() > 0 }">
								<div id="myCarousel" class="carousel slide">
									<!-- 轮播（Carousel）指标 -->
									<ol class="carousel-indicators">
										<c:forEach items="${carDailyRepairRecord.photoUrlList }" var="photoUrl" varStatus="status">
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
										<c:forEach items="${carDailyRepairRecord.photoUrlList }" var="photoUrl" varStatus="status">
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
</body>
</html>