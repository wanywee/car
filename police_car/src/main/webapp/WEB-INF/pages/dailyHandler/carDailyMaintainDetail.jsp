<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>加油记录信息-详情</title>
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
					<div class="row row-1">
						<div class="col-xs-4">
							<label>车&nbsp;&nbsp;牌&nbsp;&nbsp;号：</label>${recordByID.licenseno}
						</div>
						<div class="col-xs-4">
							<label>保养类型：</label>${recordByID.maintainType}
						</div>
						<div class="col-xs-4">
							<label>保养日期：</label>${maintainDate}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="row row-2">
								<div class="col-xs-4">
									<label>保养单位：</label>${recordByID.maintainUnitname}
								</div>
								<div class="col-xs-4">
									<label>保养里程：</label>${recordByID.maintainMileage}
								</div>
								<div class="col-xs-4">
									<label>所属地区：</label>${recordByID.region}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-4">
									<label>保养费用：</label>${recordByID.maintainMoney}
								</div>
								<div class="col-xs-4">
									<label>保养内容：</label>${recordByID.maintainContent}
								</div>
								<div class="col-xs-4">
									<label>经&nbsp;&nbsp;手&nbsp;&nbsp;人：</label>${recordByID.handlename}
								</div>
							</div>
							<div class="row row-2">
								<div class="col-xs-6">
									<label>到期时间：</label><fmt:formatDate value="${recordByID.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
							</div>
						</div>
					</div>
					<div class="row row-1">
						<div class="col-xs-12">
							<label>备&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;注：</label>${recordByID.remark}
						</div>
					</div>
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-xs-12 col-md-7">
							<c:if test="${recordByID.photoUrlList == null || recordByID.photoUrlList.size() == 0 }">
								<img alt="暂无图片" src="${ctx }/img/noPhoto.png">
							</c:if>
							<c:if test="${recordByID.photoUrlList != null && recordByID.photoUrlList.size() > 0 }">
								<div id="myCarousel" class="carousel slide">
									<!-- 轮播（Carousel）指标 -->
									<ol class="carousel-indicators">
										<c:forEach items="${recordByID.photoUrlList }" var="photoUrl" varStatus="status">
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
										<c:forEach items="${recordByID.photoUrlList }" var="photoUrl" varStatus="status">
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