<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<title>权限管理</title>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="${ctx }/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/plugins/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript">
	var isDraging = false;
	var isNew = false;
	var newCount = 1;
	var log, className = "dark";
	var log, className = "dark", curDragNodes, autoExpandNode;
	var isParent = false;
	var selectedNode;
	var permissionIndex;
	var windowHtml = "";
	
	var settings = {
			view: {
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false,
				showIcon: false
			},
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext
				},
				enable: true,
				removeTitle: "删除",
				renameTitle: "修改",
				showRemoveBtn: setRemoveBtn,
				showRenameBtn: setRenameBtn
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeDrag: beforeDrag,
				beforeEditName: beforeEditName,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				
				onRemove: onRemove,
				onRename: onRename,
				
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onDrag: onDrag,
				onDrop: onDrop
			}
		};
	
	$(function(){
		loadTree();
		 $("#form").validationEngine();
	});
	
	function loadTree(){
		$.post("${ctx}/role/getZTreePermissionList",null,function(zNodes){
			$.fn.zTree.init($("#treeDemo"), settings, zNodes);
		});
	}

	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	function beforeEditName(treeId, treeNode) {
		$.post("${ctx}/role/getPermissionByID",{permissionID:treeNode.id},function(result){
			if(result != null){
				isNew = false;
				selectedNode = treeNode;
				openWindow();
				$("#permissionID").val(result.id);
				$("#name").val(result.menuName);
				$("#icon").val(result.menuIcon);
				$("#linkURI").val(result.menuUrl);
				$("#menuType").attr("data-value",result.menuType)
				$("#menuType").attr("data-type","MNEU_TYPE")
					$.loadSelectName($("#menuType"));
			}else{
				layer.msg('加载失败', {icon: 2});
			}
		});
		return false;
	}
	var setting = {
			check: {
				enable: true
			},
			view: {
				showIcon: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		
		var sIndex = layer.confirm("删除后将无法恢复，确认删除吗？", {offset: '200px'}, function(){
				layer.close(sIndex);
				var index = layer.load(1);
				$.post("${ctx}/role/deletePermissions",{id:treeNode.id},function(result){
					layer.close(index);
					if(result.statusCode = 200){		
						layer.msg('删除成功', {icon: 1});
						zTree.removeNode(treeNode);
					}else{
						layer.msg('删除失败', {icon: 2});
					}
				});
			}, function(){}
		);
		return false;
	}
	
	function onRemove(e, treeId, treeNode) {
		
	}
	
	function beforeRename(treeId, treeNode, newName) {
		className = (className === "dark" ? "":"dark");
		if(isNew){
			if(newName.length == 0){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.removeNode(treeNode);
				return false;
			}
		}
		if (newName.length == 0) {
			$("#"+treeNode.tId+"_input").validationEngine("showPrompt","地区名称不能为空","error").focus();
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	
	function onRename(e, treeId, treeNode) {
		
	}
	
	function addHoverDom(treeId, treeNode) {
		if(isDraging || treeNode.level >= 3) return false;
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.id
			+ "' title='新增' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.id);
		if (btn) btn.bind("click", function(){
			isNew = true;
			isParent = false;
			selectedNode = treeNode;
			openWindow();
			return false;
		});
	};
	
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.id).unbind().remove();
	};
	
	function dropPrev(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function dropInner(treeId, nodes, targetNode) {
		if (targetNode && targetNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				if (!targetNode && curDragNodes[i].dropRoot === false) {
					return false;
				} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function dropNext(treeId, nodes, targetNode) {
		var pNode = targetNode.getParentNode();
		if (pNode && pNode.dropInner === false) {
			return false;
		} else {
			for (var i=0,l=curDragNodes.length; i<l; i++) {
				var curPNode = curDragNodes[i].getParentNode();
				if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
					return false;
				}
			}
		}
		return true;
	}
	
	function beforeDrag(treeId, treeNodes) {
		className = (className === "dark" ? "":"dark");
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].drag === false) {
				curDragNodes = null;
				return false;
			} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
				curDragNodes = null;
				return false;
			}
		}
		curDragNodes = treeNodes;
		return true;
	}
	
	function beforeDragOpen(treeId, treeNode) {
		autoExpandNode = treeNode;
		return true;
	}
	
	function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
		return true;
	}
	
	function onDrag(event, treeId, treeNodes) { 
		className = (className === "dark" ? "":"dark");
	}
	
	function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
		className = (className === "dark" ? "":"dark");
		$("#addAreaBtn").css("display", "none");
		$("#saveSortBtn").css("display", "");
		$("#cancelSortBtn").css("display", "");
		isDraging = true;
	}
	
	function onExpand(event, treeId, treeNode) {
		if (treeNode === autoExpandNode) {
			className = (className === "dark" ? "":"dark");
		}
	}
	
	function setRemoveBtn(treeId, treeNode){
		return !isDraging;
	}
	
	function setRenameBtn(treeId, treeNode){
		return !isDraging;
	}
	
	function addParent() {
		isNew = true;
		isParent = true;
		selectedNode = null;
		openWindow();
	};
	
	function cancelSort() {
		$("#addAreaBtn").css("display", "");
		$("#saveSortBtn").css("display", "none");
		$("#cancelSortBtn").css("display", "none");
		isDraging = false;
		loadTree();
	}
	
	function saveSort() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var params = "";
		var index = layer.load(3);
		var nodes = zTree.getNodes();
		var treeNodes = zTree.transformToArray(nodes);
		$.each(treeNodes, function(index, node){
			var pId = node.pId == null ? 0 : node.pId;
			var priority = zTree.getNodeIndex(node);
			var level = node.level;
			params += "permissionList["+index+"].permissionID="+node.id+"&permissionList["+index+"].parentID="+pId+"&permissionList["+index+"].priority="+priority+"&permissionList["+index+"].type="+level+"&";
		});
		$.post("${ctx}/role/saveSortPermissions",params,function(result){
			layer.close(index);
			if(result.indexOf("FAILED")!=-1){
				layer.msg(result, {icon: 2}); 
			}else{
				layer.msg('保存成功', {icon: 1});
				$("#addAreaBtn").css("display", "");
				$("#saveSortBtn").css("display", "none");
				$("#cancelSortBtn").css("display", "none");
				isDraging = false;
			}
		});
	}
	
	function openWindow(){
		if(windowHtml == "" ){;
			windowHtml =  $("#permissionDiv").html();
			$("#permissionDiv").empty();
		}
		permissionIndex = layer.open({
			type: 1,
		    title: '编辑角色',
		    maxmin: true, //开启最大化最小化按钮
		    area: ['50%', '60%'],
			content: windowHtml//这里content是一个普通的String
		});
		if(isNew){
			if(selectedNode != null && selectedNode.level == 0){
				$("#iconTr").show();
			}else if(selectedNode == null){
				$("#iconTr").show();
				
			}else{
				$("#iconTr").hide();
			}
		}else{
			if(selectedNode != null && selectedNode.level <= 1){
				$("#iconTr").show();
			}else{
				$("#iconTr").hide();
			}
		}
			$.loadSelectName($("#menuType"));
	}
	
	function closeWindow(){
		layer.close(permissionIndex);
	}
	function savePermission(){
		if(!$('#form').validationEngine('validate')){ 
			return;
		}
		var layerIndex = layer.load(3);
		var id = $("#permissionID").val();
		var menuName = $("#name").val();
		var menuIcon = $("#icon").val();
		var menuUrl = $("#linkURI").val();
		var menuType = $("#menuType").select2("val");
		var params = {menuName:menuName, menuIcon:menuIcon, menuUrl:menuUrl,menuType:menuType};
		if(isNew){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var index = 0;
			if(selectedNode == null){
				var nodes = zTree.getNodes();
				index = nodes.length;
			}else{
				if(selectedNode.children != null){
					index = selectedNode.children.length;
				}
			}
			var level = selectedNode == null ? 0 : selectedNode.level + 1;
			var pId = selectedNode == null ? 0 : selectedNode.id;
			params.menuSort = index;
			params.menuType = level;
			params.parentID = pId;
			$.post("${ctx}/role/savePermission",params,function(result){
				layer.close(layerIndex);
				if(result.indexOf("FAILED")!=-1){
					layer.msg(result, {icon: 2});
				}else{
					var iconClass = "icon icon-default";
					if(level <= 1){
						if(!icon){
							if(level == 0) iconClass = "icon icon-default";
							if(level == 1) iconClass = "icon icon-nav";
						}else{
							iconClass = "icon " + icon;
						}
					}else if(level == 2){
						iconClass = "icon icon-item";
					}else if(level == 3){
						iconClass = "icon icon-action";
					}
					treeNode = zTree.addNodes(selectedNode, {id:result, pId:pId, isParent:isParent, name:menuName, iconClass:menuIcon});
					layer.msg('保存成功', {icon: 1});
					closeWindow();
				}
			});
		}else{
			console.log(params)
			params.id = id;
			$.post("${ctx}/role/savePermission",params,function(result){
				layer.close(layerIndex);
				if(result.indexOf("FAILED")!=-1){
					layer.msg(result, {icon: 2});
				}else{
					selectedNode.name = $("#name").val();
					$("#"+selectedNode.tId+"_span").html($("#name").val());
					var level = selectedNode == null ? 0 : selectedNode.level;
					if(level <= 1){
						var iconClass = "";
						if(!icon){
							if(level == 0) iconClass = "icon icon-default";
							if(level == 1) iconClass = "icon icon-nav";
						}else{
							iconClass = "icon " + icon;
						}
						selectedNode.iconClass = iconClass;
						$("#iconClass_"+id).removeClass().addClass(iconClass);
					}
					layer.msg('保存成功', {icon: 1});
					closeWindow();
				}
			});
		}
	}
	
</script>
<style type="text/css">
.ztree li span.button.add {margin-left:4px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
.form-horizontal .form-group{
/* 	margin-left:0px; */
	margin-right: 0px;
}
</style>
</head>

<body >
<div class="wrapper wrapper-content  animated fadeInRight">
<div class="container-fluid">
<div class="row" style="font-size: 15px">
<div class="col-sm-12">
	<div class="search_box">
		<div class="search_group">
			<button class="btn btn-1 " style="background-color: #00bb9c!important;border-color: #00bb9c;color: #FFF;" type="button" id="addAreaBtn" onclick="addParent()"><i class="icon iconfont icon-add"></i>&nbsp;新增</button>
			<button class="btn btn-2" style="display:none" type="button" id="saveSortBtn" onclick="saveSort()"><i class="icon iconfont icon-saves"></i>&nbsp;保存排序</button>
			<button class="btn " style="display:none"type="button" id="cancelSortBtn" onclick="cancelSort()"><i class="icon iconfont icon-cancel"></i>&nbsp;取消排序</button>
		</div>
	</div>
	<div class="ibox-content">
		<div class="">
	        <div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
	    </div>
	</div>    
</div>
</div>
</div>

<div id="permissionDiv" style="display:none; ">
      <div class="ibox-content">
          <form class="form-horizontal" id="form">
              <input type="hidden" name="permissionID" id="permissionID"/>
               <div class="form-group">
               	 <label class="col-sm-2 control-label"><i class="must"></i>名称</label>

                  <div class="col-sm-10">
                  	<input type="text" name="name" id="name" class="form-control validate[required,maxSize[20],custom[onlyLetterNumber]]" >
                  </div>
               </div>
               
              <div id="iconTr" class="form-group">
                  <label class="col-sm-2 control-label"><i class="head"></i>图标</label>

				 <div class="col-sm-10">
				 	<input type="text" name="icon" id="icon" class="form-control validate[maxSize[20]]" >
                  </div>
              </div>
              
              <div class="form-group">
	              <label class="col-sm-2 control-label"><i class="must"></i>URI</label>
	                  
				  <div class="col-sm-10">
					 <input type="text" name="linkURI" id="linkURI" class="form-control validate[required,maxSize[50]]" >
	               </div>
              </div>
              
                <div class="form-group">
	              <label class="col-sm-2 control-label"><i class="must"></i>类型</label>
	                  
				  <div class="col-sm-10">
				  <select class="validate[required]" id="menuType" select2=""
						  name="menuType" data-type="" data-value=""
						  data-url="${ctx}/common/getListKeyCode?kind=MNEU_TYPE" style="width: 163px"></select>	               </div>
              </div>
              
               <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button class="btn btn-primary" type="button" onclick="savePermission()"><i class="icon iconfont icon-saves"></i>&nbsp;保存</button>
                    </div>
                </div>
          </form>
      </div>
</div>
</div>
</body>
</html>
