<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ page language="java" import="com.carTravelsky.common.YSTConstants" pageEncoding="UTF-8"%>
 --%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<% 
pageContext.setAttribute("version", "20170227");
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<!--ace基础 -->
<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" />
<link rel="stylesheet" href="${ctx}/components/font-awesome-4.7.0/css/font-awesome.css" />
<link href="//cdn.bootcss.com/select2/4.0.3/css/select2.min.css" rel="stylesheet">

<!--其他一些样式 -->
<link href="${ctx }/css/iconfonts/iconfont.css" rel="stylesheet">
<link href="${ctx }/js/plugins/select2/css/select2.min.css" rel="stylesheet">
<link href="${ctx }/js/plugins/validation/validationEngine.jquery.css" rel="stylesheet">
<link href="${ctx }/css/jquery-confirm.css" rel="stylesheet" />
<link href="${ctx }/css/animate.min.css" rel="stylesheet">
<link href="${ctx }/css/jquery-ui.theme.css" rel="stylesheet">
<link href="${ctx }/components/fileinput/css/fileinput.css" rel="stylesheet">
<%-- <link href="${ctx }/js/plugins/jqgrid/css/ui.jqgrid.css" rel="stylesheet"> --%>
<link href="${ctx }/js/plugins/jqgrid/css/ui.jqgridffe4.css" rel="stylesheet">




<!-- 公共js -->
<script src="${ctx }/js/jquery-1.9.1.js"></script>
<script src="${ctx }/js/jquery-1.9.1.min.js"></script>
<script src="${ ctx}/assets/js/ace-extra.js"></script>
<script src="${ ctx}/components/bootstrap/dist/js/bootstrap.js"></script>
<script src="${ ctx}/assets/js/src/elements.scroller.js"></script>
<script src="${ ctx}/assets/js/src/elements.colorpicker.js"></script>
<script src="${ ctx}/assets/js/src/elements.fileinput.js"></script>
<script src="${ ctx}/assets/js/src/elements.typeahead.js"></script>
<script src="${ ctx}/assets/js/src/elements.wysiwyg.js"></script>
<script src="${ ctx}/assets/js/src/elements.spinner.js"></script>
<script src="${ ctx}/assets/js/src/elements.treeview.js"></script>
<script src="${ ctx}/assets/js/src/elements.wizard.js"></script>
<script src="${ ctx}/assets/js/src/elements.aside.js"></script>
<script src="${ ctx}/assets/js/src/ace.js"></script>
<script src="${ ctx}/assets/js/src/ace.basics.js"></script>
<script src="${ ctx}/assets/js/src/ace.scrolltop.js"></script>
<script src="${ ctx}/assets/js/src/ace.ajax-content.js"></script>
<script src="${ ctx}/assets/js/src/ace.touch-drag.js"></script>
<script src="${ ctx}/assets/js/src/ace.sidebar.js"></script>
<script src="${ ctx}/assets/js/src/ace.sidebar-scroll-1.js"></script>
<script src="${ ctx}/assets/js/src/ace.submenu-hover.js"></script>
<script src="${ ctx}/assets/js/src/ace.widget-box.js"></script>
<script src="${ ctx}/assets/js/src/ace.settings.js"></script>
<script src="${ ctx}/assets/js/src/ace.settings-rtl.js"></script>
<script src="${ ctx}/assets/js/src/ace.settings-skin.js"></script>
<script src="${ ctx}/assets/js/src/ace.widget-on-reload.js"></script>
<script src="${ ctx}/assets/js/src/ace.searchbox-autocomplete.js"></script>
<script src="${ctx }/js/plugins/select2/js/select2.full.js"></script>
<script src="${ctx }/js/plugins/select2/select2.yst.js"></script>
<script src="${ctx }/js/mc_resources.js?${version}"></script>
<script src="${ctx }/js/plugins/layer/layer.js"></script>
<script src="${ctx }/js/jstool.js"></script>
<script src="${ctx }/js/utils.js"></script>
<script src="${ ctx}/assets/js/src/elements.onpage-help.js"></script>
<script src="${ ctx}/assets/js/src/ace.onpage-help.js"></script>
<script src="${ctx }/js/plugins/validation/languages/jquery.validationEngine-zh_CN.js"></script>
<script src="${ctx }/js/plugins/validation/jquery.validationEngine.js"></script>
<script src="${ctx }/components/laydate/laydate.js"></script>
<script src="${ctx }/components/fileinput/js/fileinput.js"></script>
<script src="${ctx }/components/fileinput/js/fileinput_locale_zh.js"></script>
<script src="${ctx }/js/plugins/jqgrid/js/i18n/grid.locale-cn.js"></script>
<script src="${ctx }/js/plugins/jqgrid/js/jquery.jqGrid.js"></script>


<script> 
var baseURL="<%=request.getContextPath()%>";
document.onkeydown=function(event){   
    var e = event || window.event || arguments.callee.caller.arguments[0];                
    if(e && e.keyCode==13){ // enter 键   
       if($("#serchBtn").length>0){
    	   $("#serchBtn").trigger('click');
       }
    }   
 };
</script>

<style>
.ibox-content p {
	height: 39px;
}
</style>



