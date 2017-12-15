<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <title> 角色列表</title>
		<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx }/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/plugins/ztree/js/jquery.ztree.all.min.js"></script>
        </head>

        <body>
          <div class="wrapper wrapper-content  animated fadeInRight">
<div class="row">
<div class="col-sm-12">
	<div class="ibox-content">
		<p>
		<sp:permission link="/role/addRole" reverse="false">
	        <button id="btnAdd" class="btn btn-1 " onclick="addRole(0)" type="button" onclick="editRole(0)"><i class="icon iconfont icon-add"></i>&nbsp;新增</button>
		</sp:permission>
		<sp:permission link="/role/editRole" reverse="false">
		    <button id="btnEdit"  class="btn btn-2" onclick="editRole(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	    </sp:permission>
	    <sp:permission link="/role/deleteRole" reverse="false">
	        <button id="btnDelete" class="btn btn-3" onclick="deleteRole()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
	    </sp:permission>
	    <button id="btnPer" class="btn btn-warning " onclick="editPermission()" type="button"><i class=" fa fa-users"></i>&nbsp;权限</button>
	 <%--    <sp:permission link="/role/setRolePermiss" reverse="false">
		</sp:permission> --%>
			<button class="btn btn-0"style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
			<input placeholder="搜索" style="float: right" type="text" name="appID"  id="serarchStr" class="search_control">
		
		</p>
		
		<div class="jqGrid_wrapper">
	        <table id="table_list"></table>
	        <div id="pager_list"></div>
	    </div>
	</div>    
</div>
</div>
</div>
</body>
	<script type="text/javascript">
            $("#return").click(function() {
              window.history.back();
            })
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
            //加载jqGrid
            $(function() {
              jqGrid = $("#table_list").jqGrid({
                url: "${ctx}/role/getRoleList",
                ignoreCount: true,
                postData: {},
                mtype: 'POST',
                pginput: true,
                viewrecords: true,  
                multiselect: true,//复选框 
                pager: 'pager_list', //分页工具栏，pager:分页DIV的id  
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["ID","角色名称","角色编码", "助记码","","备注","是否停用"],
                sortname: 'id',
                colModel: [{
                  name: "id",
                  sortable:true,
                  hidden:true,
                  cellattr: addCellAttr
                },{
                  name: "roleName",
                  cellattr: addCellAttr
                }
                ,{
                    name: "roleCode",
                    sortable:true,
                  cellattr: addCellAttr
                  }
                ,{
                  name: "mnemonicCode",
                  sortable:true,
                  cellattr: addCellAttr
                	
                },{
                    name: "isAllView",
                    hidden:true
                  	
                  },{
                	name: "comments",
                 cellattr: addCellAttr
                },{
                	 name: "status",
                     sortable:true,
                     formatter:function(value, options, row){
                    	 var reHtml = "<input name='status' onchange='isUse(\""+ row.id +"\")' value='2' type='checkbox' id='status" + row.id + "'>";
							var re2Html = "<input name='status' onchange='isUse(\""+ row.id +"\")' value='2' type='checkbox' id='status" + row.id + "' checked='checked'>";
							if(row.status == 1) {
								return reHtml;
							}
							if(row.status == 2) {
								return re2Html;
							}
                    }
                	  
                }]
              });
            });
            //表格全局搜索
            function search(){
            	var searchStr = $("#serarchStr").val();
            	//searchStr = encodeURI(searchStr);
            		$("#table_list").jqGrid('setGridParam',{ 
            	        postData:{'searchStr':searchStr}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
            }
          //编辑权限
            function editPermission(roleID){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	var rowData = $("#table_list").jqGrid("getRowData",ids[0]);
            	ids = ids.toString();
            	layer.open({
            		  closeBtn: 1,
            		  shift: 2,
            		  title: "权限列表【"+rowData.roleName+"】",
            		  offset:"50px",
            		  scrollbar: false,
            		  area:['500px', '80%'],
            		  content: '<div id="permissionArea"><input type="hidden" id="setRoleID"/><div style="float: right;"><input type="checkbox" id="isAllView" name="isAllView"/>是否可见所有部门数据</div><ul id="tree" class="ztree"></ul></div>',
            		  success:function(){
            			  if(2==rowData.isAllView){
            				  $("#isAllView").attr("checked","checked");
            			  }
            		  },
            		  yes:function(){
            			  savePermissions();
            		  }
            	});
            	$.post("${ctx}/role/getRoleZTreePermissions",{"roleID":ids},function(result){
            		setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
            		$.fn.zTree.init($("#tree"), setting, result);
            		$("#setRoleID").val(roleID);
            	});
            }
            function addCellAttr(rowId, val, rawObject, cm, rdata) {
                if(rawObject.status == 0 ){
                    return "style='color:red'";
                }
            }
            //保存角色
            function savePermissions(){
            	var treeObj = $.fn.zTree.getZTreeObj("tree");
            	var nodes = treeObj.getCheckedNodes(true);
            	var permissionIDs = "";
            	$.each(nodes,function(index, node){
            		permissionIDs += node.id;
            		if(index < nodes.length-1){
            			permissionIDs += ",";
            		}
            	});
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	ids = ids.toString();
            	var roleID = ids;
            	var isAllView=1;
            	if($("#isAllView").is(':checked')){
            		isAllView=2;
            	}
            	$.post("${ctx}/role/savePermissions",{"id":roleID,"permissionIDs":permissionIDs,"isAllView":isAllView},function(result){
            	    if(result.statusCode==200){
            	    	layer.msg('角色权限设置成功',{icon: 1});
            	    }else{
            	    	layer.msg('角色权限设置失败',{icon: 2});
            	    }
            	});
            	jqGrid.trigger("reloadGrid");
            }
            /* 是否停用的改变 */
        	function isUse(id) {
        		var data;
        		if ($('#status' + id).is(':checked')) {
        			data = {
        				id : id,
        				status : 2
        			};
        		} else {
        			data = {
        					id : id,
        					status : 1
        				};
        		}
        		$.ajax({
        			type : 'post',
        			datatype : 'text',
        			url : '${ctx}/role/saveRoleStatus',
        			data : data,
        			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
        			success : function() {
        				jqGrid.trigger("reloadGrid");
        			},
        			error : function(data) {
        				layer.msg(data.message);
        			}
        		});
        		//jqGrid.trigger("reloadGrid");
        	}

        	/* 表格列字体颜色 */
        	function addCellAttr(rowId, val, rawObject, cm, rdata) {
        		if (rawObject.status == 2) {
        			return "style='color:red'";
        		}
        	}

            //删除角色要判断角色下面是否哈有用户
            function deleteRole(){
            	var ids = $("#table_list").jqGrid("getGridParam","selarrrow");
            	ids = ids.toString();
            	if(ids.length<1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	 var confirm = layer.confirm('确认要删除角色吗？', {offset: '200px', btn: ['确定','取消'] }, function(){
            		layer.close(confirm);		
            		var index = layer.load(1);
            		var data = {"roleID":ids}
            		 $.ajax({
            			 type:"GET",
                         url: "${ctx}/role/deleteRole",
                         data: data,
                         success: function(result) {
                        	layer.close(index);
                 			layer.msg(result.message, {icon: 1});
                 			jqGrid.trigger("reloadGrid");   
                         },
                         error: function(result,XMLHttpRequest, textStatus, errorThrown) {
                        	 console.log(result)
                        	 layer.msg(result.message, {icon: 2});
                        	 layer.close(index);
                         },
                       });
            	}, function(){
            		
            	}
            ); 
            }
            //新增或者编辑角色
            function editRole(roleID){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	index = layer.open({
            	    type: 2,
            	    title: '编辑角色',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['600px', '420px'],
            	    content: '${ctx}/role/editRole?roleID='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            //新增或者编辑角色
            function addRole(roleID){
            	index = layer.open({
            	    type: 2,
            	    title: '新增角色',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['600px', '420px'],
            	    content: '${ctx}/role/addRole', //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            </script>
</body>
</html>