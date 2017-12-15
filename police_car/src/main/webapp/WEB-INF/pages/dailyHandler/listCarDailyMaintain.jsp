<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>保养记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
<style type="text/css">
ul {
	padding: 0px;
	margin-left: 35px;
	display: none;
	list-style: none;
	z-index: 9999;
	position: absolute;
	background-color: #E0E0E0;
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
	<div class="ibox-content">
		<p>
		<sp:permission link="/carDailyMaintain/addCarDailyMaintain" reverse="false">
	        <button id="btnPer" class="btn btn-1 " onclick="editAddoilRecord(1)" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	    </sp:permission>    
	    <sp:permission link="/carDailyMaintain/editCarDailyMaintain" reverse="false">
	        <button id="btnEdit"  class="btn btn-2" onclick="editAddoilRecord(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	    </sp:permission>
	    <sp:permission link="/carDailyMaintain/deleteCarDailyMaintain" reverse="false">  
	        <button id="btnDelete" class="btn btn-3" onclick="deleteAddoilRecord()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
		</sp:permission>	
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
			$.loadProjectattr($("#keyCode"));
            $("#export").click(function() {
              utils.downloadFile("${ctx }/testDownFilePoi", null, "N");
            });
            $("#return").click(function() {
              window.history.back();
            })
            var  count = $("#count").val();
			if(typeof(count)==="undefined"){
				count=" ";
				
			}
            //加载jqGrid
            $(function() {
              jqGrid = $("#table_list").jqGrid({
                url: "${ctx}/carDailyMaintain/getCarDailyMaintainList?count="+count,
                postData: {},
                shrinkToFit: true,
                multiselect: true,
                pginput: true,
                autowidth:true,
                mtype :"POST",
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","车牌号", "保养类别","保养费用(元)","保养日期","到期日期","保养单位","经手人","备注",""],
                sortname: 'endDate',
                sortorder:'asc',
                colModel: [{
					name : "opt",
					width : 50,
					align : 'center',
					formatter : details
				},{
                  name: "licenseno",
                  sortable:true,
                  width:100,
                },{
                  name: "maintainType",
                  width:100,
                },{
                  name: "maintainMoney",
                  width:100,
                },{
                	name: "maintainDate",
                    fixed: true,
                    width:150,
                    formatter:function(value, options, row){
                      	  return setColor("blue",utils.gettimestamp(value));
                       }
                },{
                	name: "endDate",
                    fixed: true,
                    width:150,
                    formatter:function(value, options, row){
                      	  return setColor("blue",utils.gettimestamp(value));
                       }
                },{
                  name: "maintainUnitname",
                  width:100,
                },{
                  name: "handlename",
                  width:100,
                },{
                  name: "remark",
                },{
					cellattr: addCellAttr,
					name : "status",
					formatter : function(value, options, row) {
						/*
						var reHtml = "<input name='status' value='处理' type='button' id='status" + row.id + "'/>";
						var re2Html = "<input type='hidden'/>";
						if(row.status == 1){
							return reHtml;
						}else{
							return re2Html;
						}
						*/
						var reHtml = "<input name='status' onclick='setSelectthis(\""+ row.id +"\")' value='处理' type='button' id='status" + row.id + "'/>";
						var re2Html = "<input type='hidden'/>";
						if(row.status == 1) {
							return reHtml;
						}
						if(row.status == null) {
							return re2Html;
						}
						return re2Html;
						
					}
				}]
              });
            });
          	function addCellAttr(rowId, val, rawObject, cm, rdata) {
        		if (rawObject.status == 2) {
        			return "style='color:red'";
        		}
        		if (rawObject.status == 1) {
        			return "style='color:blue'";
        		}
        	}
            //表格全局搜索
            function search(){
            	var searchStr = $("#serarchStr").val();
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
            }
          //新增选中本行,并调用编辑功能
            function setSelectthis(rowid){
            	$("#table_list").setSelection(rowid,true);
            	editAddoilRecord(0);
            }
            //新增或者修改
            function editAddoilRecord(flag){
            	
            	// 如果flag等于0，就是修改数据
            	if(flag == 0 ){
            	var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            		if(ids.length == 0){
            			alert("请选中一条数据进行修改!");
            			return;
            		}
            		if(ids.length >1){
                		alert("单次只能修改一条数据!");
                		return;
                	}
            		index = layer.open({
                	    type: 2,
                	    scrollbar: false,
                	    title: '编辑保养记录',
                	    maxmin: true,
    	          		shadeClose: false,
    	          		anim: 5,
                	    area: ['900px', '60%'],
                	    content: "${ctx}/carDailyMaintain/editCarDailyMaintain?ID="+ids,
                	   	end:function(index){
                	    	jqGrid.trigger("reloadGrid");
                	    }
                	});
            	}else if(flag == 1){
            		index = layer.open({
                	    type: 2,
                	    scrollbar: false,
                	    title: '添加保养记录',
                	    maxmin: true,
    	          		shadeClose: false,
    	          		anim: 5,
                	    area: ['900px', '60%'],
                	    content: '${ctx}/carDailyMaintain/addCarDailyMaintain',
                	   	end:function(index){
                	    	jqGrid.trigger("reloadGrid");
                	    }
                	});
            	}
            }
            
            // 删除
            function deleteAddoilRecord(){
            	var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            	if(ids.length<1){
            		alert("请选择需要删除的信息!");
            		return;
            	}
            	
            	 var confirm = layer.confirm('确定删除选中的加油记录信息吗?', {offset: '200px', btn: ['确定','取消'] }, function(){
             		layer.close(confirm);		
             		var index = layer.load(1);
             		var data = {"ids":ids}
             		 $.ajax({
             			 type:"POST",
                          url: "${ctx}/carDailyMaintain/deleteCarDailyMaintain",
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
            
            /* 显示详情的操作 */
        	function details(value, options,row) {
        		var imageHtml = "<a  onclick='detailStaffInfo(\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
        		return imageHtml;
        	}
        		/* 显示职员详情信息 */
           	function detailStaffInfo(id) {
           		index = layer.open({
        			type : 2,
        			scrollbar : true,
        			title : '<h3>保养记录详情</h3>',
        			maxmin : true,
        			shadeClose : false,
        			anim : 5,
        			area : [ '80%', '80%' ],
        			content : '${ctx}/carDailyMaintain/carDailyMaintainDetail?ID=' + id,
        			end : function(index) {
        				//jqGrid.trigger("reloadGrid");
        			}
        		});
           	}
            </script>
</body>
</html>