<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>车辆事故详情</title>
</head>
<body>

	<div class="main-container ace-save-state" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<label>车牌号：</label>${recode.licenseno }
						</div>
					</div>
					<div class="row">
						<div class="col-xs-4">
							<label>驾驶员:</label>${recode.staffName}
						</div>
						<div class="col-xs-8">
							<label>事故日期:</label>
							<fmt:formatDate value='${recode.accidentDate}' type="both"
								pattern="yyyy-MM-dd hh:mm:ss" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>发生地点:</label>${recode.happenAddress}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>我方承担金额(￥):</label>${recode.webearMoney}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>对方承担金额(￥):</label>${recode.opbearMoney}
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12">
							<label>我方损失(￥):</label>${recode.webearLoss}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>对方损失(￥):</label>${recode.opbearLoss}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>保险赔偿金额(￥):</label>${recode.insuranceMoney}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>事故说明:</label>${recode.accidentExplain}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>我方情况:</label>${recode.ourSituation}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>对方情况:</label>${recode.otherSituation}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>处理结果:</label> ${recode.treatmentResult}
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<label>备注:</label>${recode.remark}
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>


</body>
</html>