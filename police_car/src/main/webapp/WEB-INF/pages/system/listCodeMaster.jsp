<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <title> 系统字典列表</title>
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
		<sp:permission link="/dictay/addCodeMaster" reverse="false">
	        <button id="btnAdd" class="btn btn-1 " onclick="addRole(0)" type="button" onclick="editRole(0)"><i class="icon iconfont icon-add"></i>&nbsp;新增</button>
		</sp:permission>
		<sp:permission link="/dictay/editCodeMaster" reverse="false">
		    <button id="btnEdit"  class="btn btn-2" onclick="editRole(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	    </sp:permission>
	    <sp:permission link="/dictay/deleteCodeMaster" reverse="false">
	        <button id="btnDelete" class="btn btn-10" onclick="deleteRole()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
	    </sp:permission>
			<button class="btn btn-0 validate[maxSize[32]]"style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
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
                url: "${ctx}/dictay/getCodeMasterList",
                postData: {},
                mtype: 'POST',
                pginput: true,
                viewrecords: true,  
                multiselect: true,//复选框 
                pager: 'pager_list', //分页工具栏，pager:分页DIV的id  
                //viewrecords: false,
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","字典类型","编码","解码","字典名称","默认值","是否可修改","描述","是否停用"],
                sortname: 'keyType',
                colModel: [{
                  name: "keyID",
                  hidden:true
                },{
                    name: "keyType",
                    sortable:true,
                    cellattr: addCellAttr
                },{
                    name: "code",
                    sortable:true,
                    cellattr: addCellAttr
                },{
                    name: "decode",
                    sortable:true,
                    cellattr: addCellAttr
                },{
                    name: "display",
                    sortable:true,
                    cellattr: addCellAttr
                },{
                	name: "defaultInd",
                	sortable:true,
                	cellattr: addCellAttr
                },{
                    name: "editableInd",
                    cellattr: addCellAttr,
                    sortable:true,
                    formatter:function(value, options, row){
                   	 if(row.editableInd == 'N') {
								return "否";
					}else if(row.editableInd == 'Y') {
								return "是";
					}
                   }
                },{
                	name: "description",
                	sortable:true,
                	cellattr: addCellAttr
                },{
                	 name: "deleteCode",
                     sortable:true,
                     formatter:function(value, options, row){
                    	 var reHtml = "<input name='deleteCode' onchange='isUse(\""+ row.keyID +"\")' value='Y' type='checkbox' id='deleteCodeId" + row.keyID + "'>";
						 var re2Html = "<input name='deleteCode' onchange='isUse(\""+ row.keyID +"\")' value='Y' type='checkbox' id='deleteCodeId" + row.keyID + "' checked='checked'>";
							if(row.deleteCode == '1') {
								return reHtml;
							}else if(row.deleteCode == '3') {
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
          
            /* 是否停用的改变 */
        	function isUse(keyID) {
        		var data;
        		if ($('#deleteCodeId' + keyID).is(':checked')) {
        			data = {
        				keyID : keyID,
        				deleteCode : "3"
        			};
        		} else {
        			data = {
        					keyID : keyID,
        					deleteCode : "1"
        				};
        		}
        		$.ajax({
        			type : 'post',
        			datatype : 'text',
        			url : '${ctx}/dictay/saveCodeMasterStatus',
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
        		if (rawObject.deleteCode == "3") {
        			return "style='color:red'";
        		}
        	}

            //删除角色要判断角色下面是否哈有用户
            function deleteRole(){
            	var rowIds = $("#table_list").jqGrid("getGridParam","selarrrow");
            	var rowData, ids = new Array();
            	$.each(rowIds,function(i,item){
            		rowData = $("#table_list").jqGrid("getRowData",rowIds[i]);
            		ids[i] = rowData.keyID;
            	});
            	ids = ids.toString();
            	if(ids.length<1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	
            	 var confirm = layer.confirm('确认要删除吗？', {offset: '200px', btn: ['确定','取消'] }, function(){
            		layer.close(confirm);		
            		var index = layer.load(1);
            		var data = {"keyID":ids}
            		 $.ajax({
            			 type:"GET",
                         url: "${ctx}/dictay/deleteCodeMaster",
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
            function editRole(){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	console.log(ids);
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	rowData= $("#table_list").jqGrid("getRowData",ids);
            	if(rowData.editableInd=="否"){
            		layer.msg('当前行不可编辑!',{icon: 2});
            		return;
            	}
            	index = layer.open({
            	    type: 2,
            	    title: '编辑字典',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['60%', '490px'],
            	    content: '${ctx}/dictay/editCodeMaster?keyID='+rowData.keyID, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            //新增或者编辑角色
            function addRole(keyID){
            	index = layer.open({
            	    type: 2,
            	    title: '新增字典',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['50%', '420px'],
            	    content: '${ctx}/dictay/addCodeMaster?keyID='+keyID, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            </script>
</body>
</html>