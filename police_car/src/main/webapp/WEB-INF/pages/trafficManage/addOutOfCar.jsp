<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>车务管理</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />

<style type="text/css">
ul{
	display: block;
	list-style:none;
	padding-left:6px;
	z-index: 9999;
}
</style>
</head>
<body>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<!-- layer弹出框二级联动 -->
					<div style="padding: 10px;">
						<div>
							选择驾驶员： <select id="deptName" select2="" style="width: 160px"
								name="deptName" data-type="${carBaseVehicleDO.brandName}"
								data-value="${carBaseVehicleDO.brandName}"
								<%-- data-url="${ctx}/common/getSelect2ListDeptment" --%>></select> <select
								id="dreiver" select2="" style="width: 160px" name="dreiver"
								data-type="${carBaseVehicleDO.brandName}"
								data-value="${carBaseVehicleDO.brandName}"
								<%-- data-url="${ctx}/common/getSelect2ListDriver" --%>></select>
						</div>
						<div style="text-align: center; padding: 10px;">
							<button type="button" class="btn btn-2">确定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$.loadProjectattr($("#deptName"));
	$.loadProjectattr($("#dreiver"));
	
	//绑定字典内容到指定的Select控件
	function BindSelect(ctrlName, url) {
	    var control = $('#' + ctrlName);
	    //设置Select2的处理
	    control.select2({
	        allowClear: true,
	        escapeMarkup: function (m) {
	            return m;
	        }
	    });

	    //绑定Ajax的内容
	    $.getJSON(url, function (data) {
	        control.empty();//清空下拉框
	        $.each(data, function (i, item) {
	        	console.log(item);
	            control.append("<option value='" + item.id + "'>&nbsp;" + item.text + "</option>");
	        });
	    });
	}
	
	$(function(){
		
		// 绑定省份、城市、行政区（联动处理）
        BindSelect("deptName", "${ctx}/common/getSelect2ListDeptment");
        setTimeout(function() {
        	var deptId = $("#deptName").val();
            BindSelect("dreiver", "${ctx}/common/getSelect2ListDriver?deptId="+ deptId);
        }, 1000);
        $("#deptName").on("change", function (e) {
            var deptId = $("#deptName").val();
            BindSelect("dreiver", "${ctx}/common/getSelect2ListDriver?deptId="+ deptId);
        });

	});
	
</script>
</body>
</html>