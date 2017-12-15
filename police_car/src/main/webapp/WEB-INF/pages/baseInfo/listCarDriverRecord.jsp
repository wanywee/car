<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>驾驶员档案</title>
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
	<div class="search_box">
		<form class="form-inline" id="searchForm">
		<%-- <div class="search_group">
		    <label>门店</label>
		    <select class="validate[required]" id="keyCode" select2=""
					name="empID" data-type="12" data-value="12"
					data-url="${ctx}/getListKeyCode?kind=KEY_ALIPAY_CONFIG" style="width: 163px"></select>
		</div>
		<div class="search_group">
		   <label>支付宝门店ID</label>
		   <input type="text" name="appID" class="search_control">
		</div>
		<div class="search_group">
			<button class="btn btn-0 " type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
		</div> --%>
		</form>
	</div>
	<div class="ibox-content">
		<div id="hideNoNeed">
			<div id="hideNoNeed">
				<p>
					<sp:permission link="/baseInfo/addCarDriverRecord" reverse="false">
			        	<button id="btnAdd" class="btn btn-1 " type="button" onclick="editDispatchRecode(0)"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
			        </sp:permission>
			        <sp:permission link="/baseInfo/editCarDriverRecord" reverse="false">
			            <button id="btnUpdate" class="btn btn-2 " type="button" onclick="editDispatchRecode(1)"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
			         </sp:permission>
			         <sp:permission link="/baseInfo/deleteCarDriverRecord" reverse="false">
			       			 <button id="btnDelete" class="btn btn-3 " type="button" onclick="editDispatchRecode(2)"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
			       	 </sp:permission>
					<button id="btnPrint" class="btn btn-10 " onclick="printDeptInfo()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
			        <!-- <button id="btnImport" class="btn btn-3 " type="button"><i class="icon iconfont icon-import"></i>&nbsp;导入</button> -->
					<button class="btn btn-0 "style="float: right;margin-left: 10px;" type="button" id="serchBtn" onclick="search()"><i class="icon iconfont icon-search"></i>&nbsp;搜索</button>
					<input placeholder="搜索" style="float: right" type="text" name="appID"  id="serarchStr" class="search_control">
					
				</p>
			</div>
		</div>
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
              jqGrid = $("#table_list").jqGrid({
                url: "${ctx}/baseInfo/getListCarDriverRecord",
                postData: {"needCompany":true,"companyID":53},
                ignoreCount: true,
                pginput: true,
                multiselect : true,
                autowidth:true,
                mtype:'POST',
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","工号","姓名","部门", "驾驶证号", "准驾车型","类型","手机","备注","是否停用"],
                sortname: 'id',
                colModel: [{
                	name : "opt",
					width : 50,
					align : 'center',
					formatter : details
				}, {
                  name: "staffNo",
                  sortable:true,
                  cellattr:addCellAttr
                },{
                  name: "staffName",
                  cellattr:addCellAttr
                },{
             	   name: "dept",
                    cellattr:addCellAttr
                },{
                  name: "driverLicense",
                  sortable:true,
                  cellattr:addCellAttr
                },{
                	name: "drivingType",
                	cellattr:addCellAttr
                },{
                	name: "driverType",
                	cellattr:addCellAttr,
                	formatter : function(value, options, row) {
						if(row.driverType == 1) {
							return "兼职";
						}
						if(row.driverType == 2) {
							return "专职";
						}
					}
                },{
                	name: "staffPhone",
                	cellattr:addCellAttr
                },{
                	 name: "comments",
                	 cellattr:addCellAttr
                },{
                	name : "status",
					align : "center",
					sortable : true,
					formatter : function(value, options, row) {
						var reHtml = "<input name='status"+row.id+"' onchange='isUse(\""+ row.id +"\")'  type='checkbox' id='status" + row.id + "'>";
						var re2Html = "<input name='status' onchange='isUse(\""+ row.id +"\")'  type='checkbox' id='status" + row.id + "' checked='checked'>";
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
            /* 是否停用的改变 */
        	function isUse(id) {
        		var data;
        		if ($('input[name=status'+ id+']').is(':checked')) {
        			data = {
        				StatusId : id,
        				status : 2
        			};
        		} else {
        			data = {
        					StatusId : id,
        					status : 1
        				};
        		}
        		$.ajax({
        			type : 'post',
        			datatype : 'text',
        			url : '${ctx}/baseInfo/isUseCarDriverRecords',
        			data : data,
        			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
        			success : function() {
        				jqGrid.trigger("reloadGrid");
        			},
        			error : function(data) {
        				layer.msg(data.message);
        			}
        		});
        	}
            //表格全局搜索
            function search(){
            	var searchStr = $("#serarchStr").val();
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr,"needCompany":true,"companyID":53}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
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
	            	    title: '新增驾驶员档案记录',
	            	    maxmin: true,
		          		shadeClose: false,
		          		anim: 5,
	            	    area: ['95%', '95%'],
	            	    content: '${ctx}/baseInfo/addCarDriverRecord',
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
	            	    title: '编辑驾驶员档案记录',
	            	    maxmin: true,
		          		shadeClose: false,
		          		anim: 5,
	            	    area: ['95%', '95%'],
	            	    content: '${ctx}/baseInfo/editCarDriverRecord?ID='+ID,
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
               	    var confirm = layer.confirm('确认要删除该驾驶员档案信息吗？', { offset: '200px', btn: ['确定','取消'], fix: false}, function(){
                		layer.close(confirm);		
                		var index = layer.load(1);
                		var data = {"ids":ids}
                		 $.ajax({
                			 type:"POST",
                             url: "${ctx}/baseInfo/deleteCarDriverRecord",
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
            /* 表格列字体颜色 */
        	function addCellAttr(rowId, val, rawObject, cm, rdata) {
        		if (rawObject.status == 2) {
        			return "style='color:red'";
        		}
        	}
        	/* 显示详情 */
        	function detailVehicleRecord(e,ID) {
        		var tipContext="<ul>" +
        		"<li><a href='#' onclick='showVehicleRecord(\""+ID+ "\")'>基本信息</a></li>" +
        		"<li><a href='#' onclick='showTrainingRecord(\""+ID+ "\")'>上岗、转岗培训记录</a></li>" +
        		"<li><a href='#' onclick='showTrainingAfterRecord(\""+ID+ "\")'>在岗复训记录</a></li>" +
        		"<li><a href='#' onclick='showTrafficRecord(\""+ID+ "\")'>交通事故违规记录</a></li>" +
        		"<li><a href='#' onclick='showDeptChangeRecord(\""+ID+ "\")'>部门变更记录</a></li>";
        		layer.tips(tipContext, '#eye'+ID, {
        			  tips: [2, '#f0f5f5'],
        			  area: ['200px', 'auto'],
        			  time: 5000
        			});
        		  utils.stopBubbling(e);
        	}
        	
        	/* 显示驾驶员档案详情信息 */
        	function showVehicleRecord(id) {
        		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>驾驶员档案基本信息</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '80%' ],
        			content : '${ctx}/baseInfo/showCarDriverDetial?show=showCarDriver&ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
        	}
        	
        	/* 显示在岗转岗培训记录信息 */
        	function showTrainingRecord(id) {
        		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>上岗、转岗培训记录</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '80%' ],
        			content : '${ctx}/baseInfo/showTrainingRecord?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
        	}
        	/* 显示在岗复训记录信息 */
        	function showTrainingAfterRecord(id) {
        		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>在岗复训记录</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '80%' ],
        			content : '${ctx}/baseInfo/showTrainingAfterRecord?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
        	}
        	
        	/* 显示交通事故违规记录信息 */
        	function showTrafficRecord(id) {
        		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>交通事故违规记录</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '80%' ],
        			content : '${ctx}/baseInfo/showTrafficRecord?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
        	}
        	/* 显示部门变更记录信息 */
        	function showDeptChangeRecord(id) {
        		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3部门变更记录</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '600px', '80%' ],
        			content : '${ctx}/baseInfo/showDeptChangeRecord?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
        	}
        	
        	
        	/* 显示详情的操作 */
        	function details(value, options,row) {
        		var imageHtml = "<a  onclick='detailVehicleRecord(event,\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
        		return imageHtml;
        	}
        	function printDeptInfo(){
        		$("#hideNoNeed").hide();
        		$("#pager_list").hide(); 
        		window.print();
        		$("#hideNoNeed").show();
        		$("#pager_list").show(); 
        	} 
            </script>
</body>
</html>