<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <title> 用户列表</title>
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
			<sp:permission link="/user/addUser" reverse="false">
		        <button id="btnAdd" class="btn btn-1 "  onclick="addRole(0)" type="button"><i class="icon iconfont icon-add"></i>&nbsp;新增</button>
		    </sp:permission>
		    <sp:permission link="/user/editUser" reverse="false">
		        <button id="btnEdit"  class="btn btn-2" onclick="editRole(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button> 
		   	</sp:permission>
		   	<sp:permission link="/user/deleteUser" reverse="false">
		        <button id="btnDelete"  class="btn btn-3" onclick="deleteRole(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;删除</button> 
	        </sp:permission>
	        <!-- <button id="btnPer" class="btn btn-4 " onclick="editPermission()" type="button"><i class="icon iconfont icon-import"></i>&nbsp;权限</button> -->
			<button class="btn btn-0 "style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
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
                url: "${ctx}/user/getUserList",
                postData: {},
                mtype: 'POST',
                ignoreCount: true,
                pginput: true,
                viewrecords: true,  
                multiselect: true,//复选框 
                pager: 'pager_list', //分页工具栏，pager:分页DIV的id  
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["ID","用户名称","姓名", "所属角色","是否停用"],
                sortname: 'id',
                colModel: [{
                  name: "id",
                  sortable:true,
                  hidden:true,
                  cellattr: addCellAttr,
                },{
                    name: "username",
                    cellattr: addCellAttr,
                  	
                  },{
                  name: "staffName",
                  sortable:true,
                  cellattr: addCellAttr,
                }
                ,{
                    name: "roleName",
                    cellattr: addCellAttr,
                  }
                ,{
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
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
            }
          /* //编辑权限
            function editPermission(roleID){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
	            	var select= '<div class="form-group col-xs-12">'
	            				+'<label class="col-xs-4 control-label no-padding-right"'
								+'for="nowStatus">所属角色:</label>'
								+'<div  class="col-xs-8">'
								+'<select class="validate[required]" id="nowStatus" select2=""'
								+'style="width: 160px" name="nowStatus"'
								+'data-type="hha"'
								+'data-value="hahah"'
								+'data-url="${ctx}/role/getRoleListSelect"></select>'
								+'</div></div>';
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	layer.open({
            		  closeBtn: 1,
            		  shift: 2,
            		  title: false,
            		  offset:"50px",
            		  scrollbar: false,
            		  area:['350px', '80%'],
            		  content: select+'<div class="form-group col-xs-12" id="permissionArea"><input type="hidden" id="setRoleID"/><ul id="tree" class="ztree"></ul></div>',
            		  success: function(layero, index){
            				 alert(1);
            				 //加载select2
            	                $.loadSelect2();
            			},
            		  yes:function(){
            			  savePermissions();
            		  }
            	});
            	$.post("${ctx}/role/getRoleZTreePermissions",{roleID:ids},function(result){
            		setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
            		$.fn.zTree.init($("#tree"), setting, result);
            		$("#setRoleID").val(roleID);
            	});
            } */
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
        			url : '${ctx}/user/saveUserStatus',
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
            	var roleID = $("#setRoleID").val();
            	$.post("${ctx}/role/savePermissions",{roleID:roleID,permissionIDs:permissionIDs},function(result){
            	    if(result=="SUCCESS"){
            	    	layer.msg('角色权限设置成功',{icon: 1});
            	    }else{
            	    	layer.msg('角色权限设置失败',{icon: 2});
            	    }
            	});
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
            	    title: '编辑用户',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['50%', '420px'],
            	    content: '${ctx}/user/editUserRole?userID='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            //新增或者编辑角色
            function addRole(){
            	index = layer.open({
            	    type: 2,
            	    title: '新增用户',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['50%', '420px'],
            	    content: '${ctx}/user/addUser', //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
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
            		var data = {"userID":ids}
            		 $.ajax({
            			 type:"GET",
                         url: "${ctx}/user/deleteUser",
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
            
           
            </script>
</body>
</html>