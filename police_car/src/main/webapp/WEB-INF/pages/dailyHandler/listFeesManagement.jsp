<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>规费管理</title>
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
	<c:if test="${ not empty remindDate }">
        <c:set var="count" value="${remindDate}"/>
    </c:if>
 	<c:if test="${ not empty count }">
    	<input type="hidden" value="${count}"  id="count"/>
 	</c:if>
<div class="wrapper wrapper-content  animated fadeInRight">
<div class="row">
<div class="col-sm-12">
	<div class="search_box">
		<form class="form-inline" id="searchForm">
		</form>
	</div>
	<div class="ibox-content">
		<p>
			<sp:permission link="/dailyHandle/addFeesManage" reverse="false">
	        	<button id="btnAdd" class="btn btn-1 " type="button" onclick="editDispatchRecode(0)"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	        </sp:permission>
	        <sp:permission link="/dailyHandle/editFeesManage" reverse="false">
	            <button id="btnUpdate" class="btn btn-2 " type="button" onclick="editDispatchRecode(1)"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	         </sp:permission>
	         <sp:permission link="/dailyHandle/deleteFeesManage" reverse="false">
	       			 <button id="btnDelete" class="btn btn-3 " type="button" onclick="editDispatchRecode(2)"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
	       	 </sp:permission>
	        <!-- <button id="btnImport" class="btn btn-3 " type="button"><i class="icon iconfont icon-import"></i>&nbsp;导入</button> -->
			<button class="btn btn-0 "style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
			<input placeholder="搜索" style="float: right" type="text" name="appID"  id="searchStr" class="search_control">
		
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
			$.loadProjectattr($("#keyCode"));
            $("#export").click(function() {
              utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
            });
            $("#return").click(function() {
              window.history.back();
            })
            //加载jqGrid
            $(function() {
              	var  count = $("#count").val();
        	 	if(typeof(count)==="undefined"){
        		count=" ";
        	  	}
              jqGrid = $("#table_list").jqGrid({
                url: "${ctx}/dailyHandle/getListFeesMangement?count="+count,
                postData: {},
                ignoreCount: true,
                pginput: true,
                multiselect : true,
                autowidth:true,
                mtype:'POST',
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","车牌号","费用名称", "交费金额(元)", "交费日期","到期日期","收费单位","经手人","备注",""],
                sortname: 'endDate',
                sortorder:'asc',
                colModel: [{
                	name : "opt",
					width : 50,
					align : 'center',
					formatter : details
                },{
            	  name: "licenseNo",
            	  sortable:true,
                  cellattr:addCellAttr
                },{
                  name: "moneyname",
                  cellattr:addCellAttr
                },{
                  name: "parmentMoney",
                  sortable:true,
                  cellattr:addCellAttr
                },{
                	name: "paymentDate",
                	sortable:true,
                	cellattr:addCellAttr,
                	 formatter:function(value, options, row){
                   	  return setColor("blue",utils
								.gettimestamp(value));
                    }
                },{
                	name: "endDate",
                	sortable:true,
                	cellattr:addCellAttr,
                	 formatter:function(value, options, row){
                   	  return setColor("blue",utils
								.gettimestamp(value));
					}
                },{
                	name: "chargeUnitName",
                	cellattr:addCellAttr
                },{
                	 name: "staffName",
                	 cellattr:addCellAttr
                },{
                	 name : "remark",
                	 cellattr:addCellAttr,
                },{
					cellattr: addCellAttr,
					name : "status",
					formatter : function(value, options, row) {
						//var reHtml = "<input name='status' onclick='editDispatchRecode(\""+ row.id +"\")' value='处理' type='button' id='status" + row.id + "'>";
						reHtml = "<input name='status' onclick='setSelectthis(\""+row.id+"\")' value='处理' type='button' id='status" + row.id + "'>";
						var re2Html = "<input type='hidden'/>";
						if(row.status == 1) {
							return reHtml;
						}
						if(row.status == null) {
							return re2Html;
						}
					}
				} ]
              });
            });
            //表格全局搜索
            function search(){
            	var searchStr = $("#searchStr").val();
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
            }
            //新增选中本行,并调用编辑功能
            function setSelectthis(id){
            	index = layer.open({
        			type : 2,
        			scrollbar : false,
        			title : '编辑规费管理',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [  '80%', '80%' ],
        			content : '${ctx}/dailyHandle/editFeesManage?ID='+id,
        			end : function(index) {
        				jqGrid.trigger("reloadGrid");
        			}
        		});
            }
            //新增或者修改或者删除
            function editDispatchRecode(PID){
            	if(PID == ''||PID == 'undefined' ){
            		PID = 0;
            	}
            	if(PID==0){//新增
            		ID=PID;
	            	index = layer.open({
	            	    type: 2,
	            	    scrollbar: false,
	            	    title: '新增规费管理',
	            	    maxmin: true,
		          		shadeClose: false,
		          		anim: 5,
	            	    area: ['95%', '95%'],
	            	    content: '${ctx}/dailyHandle/addFeesManage',
	            	    end:function(index){
	            	    	jqGrid.trigger("reloadGrid");
	            	    }
	            	});
            	}
            	if(PID==1){//
            		//编辑修改
            		ID=$('#table_list').jqGrid('getGridParam', 'selrow');
            		var select_ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            		if(select_ids.length>1){
            			layer.alert('你已选择多行！请重新选择一行');
            			return;
            		}
            		if(ID==null){
            			layer.alert('请选择一行');
            			return;
            		}
	            	index = layer.open({
	            	    type: 2,
	            	    scrollbar: false,
	            	    title: '编辑规费管理',
	            	    maxmin: true,
		          		shadeClose: false,
		          		anim: 5,
	            	    area: ['95%', '95%'],
	            	    content: '${ctx}/dailyHandle/editFeesManage?ID='+ID,
	            	    end:function(index){
	            	    	jqGrid.trigger("reloadGrid");
	            	    }
	            	});
            	}
            	if(PID==2){
            		 //删除
            		var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            		if (ids.length == 0) {
        				layer.alert('请选择一行');
        				return;
        			}
               	    var confirm = layer.confirm('确认要删除该规费管理记录吗？', {offset: '200px', btn: ['确定','取消'] }, function(){
                		layer.close(confirm);		
                		var index = layer.load(1);
                		var data = {"ids":ids}
                		 $.ajax({
                			 type:"POST",
                             url: "${ctx}/dailyHandle/deleteFeesManage",
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
            }
            /* 表格列字体颜色,红色代表停用 */
        	function addCellAttr(rowId, val, rawObject, cm, rdata) {
        		if (rawObject.status == 2) {
        			return "style='color:red'";
        		}
        		if (rawObject.status == 1) {
        			return "style='color:blue'";
        		}
        	}
        	
        	/* 显示详情的操作 */
        	function details(value, options,row) {
        		var imageHtml = "<a  onclick='detailStaffInfo(\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
        		return imageHtml;
        	}
        		/* 显示规费管理详情信息 */
           	function detailStaffInfo(id) {
           		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>规费管理详情</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '60%' ],
        			content : '${ctx}/dailyHandle/showFeesManageDetial?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
           	}
            </script>
</body>
</html>