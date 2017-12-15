<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用车记录</title>
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
<div class="wrapper wrapper-content  animated fadeInRight">
<div class="row">
<div class="col-sm-12">
	<div class="ibox-content">
		<p>
		<sp:permission link="/dailyOutBackCar/addCarOut" reverse="false">
	        <button id="btnPer" class="btn btn-1 " onclick="addCarOut(0)" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	    </sp:permission>    
	    <sp:permission link="/dailyOutBackCar/editCarOut" reverse="false">
	        <button id="btnEdit"  class="btn btn-2" onclick="editCarOut(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	    </sp:permission>
	    <sp:permission link="/dailyOutBackCar/deleteCarOut" reverse="false">  
	        <button id="btnDelete" class="btn btn-3" onclick="deleteCarOut()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
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
            //加载jqGrid
            $(function() {
              jqGrid = $("#table_list").jqGrid({
                url: "${ctx}/dailyOutBackCar/getListCarOutOrBack",
                postData: {},
                shrinkToFit: true,
                ignoreCount: true,
                multiselect: true,
                pginput: true,
                autowidth:true,
                mtype :"POST",
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","查看详情","车辆号","用车人","随行人员","目的地","出车时间","联系方式","回车时间","备注"],
                sortname: 'id',
                colModel: [{
                  name: "id",
                  hidden:true
                },{
					name : "opt",
					width : 50,
					align : 'center',
					formatter : details
				},{
                  name: "licenseno",
                  sortable:true,
                  width:100
                },{
                    name: "caruserDisplay",
                    width:100
                  },{
                  name: "entourageDisplay",
                  width:100,
                },{
                	name: "desitination",
                	width:100
                },{
                	name: "outcarTime",
                    sortable:true,
                    width:100,
                    formatter : function(value, options,
  							row) {
  						return setColor("blue", utils
  								.gettimestamp(value));
  					}
                },{
                  name: "phonenumber",
                  width:100
                },{
                	name: "backTime",
                    sortable:true,
                    width:100,
                    formatter : function(value, options,
  							row) {
                    	if(value == '' || value == null)return '';
  						return setColor("blue", utils
  								.gettimestamp(value));
  					}
                },{
                  name: "outRemark"
                }]
              });
            });
            
            //表格全局搜索
            function search(){
            	var searchStr = $("#serarchStr").val();
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr,"isOutAndBack":""}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	
            }
            
            //新增或者编辑角色
            function editCarOut(id){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	index = layer.open({
            	    type: 2,
            	    title: '编辑用车记录',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['900px', '520px'],
            	    content: '${ctx}/dailyOutBackCar/edidCarOut?id='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            //新增或者编辑角色
            function addCarOut(id){
            	index = layer.open({
            	    type: 2,
            	    title: '新增用车记录',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['900px', '520px'],
            	    content: '${ctx}/dailyOutBackCar/addCarOut', //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            // 删除
            function deleteCarOut(){
				var rowIds = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            	if(rowIds.length<1){
            		alert("请选择需要删除的信息!");
            		return;
            	}//得到行对象
            	var rowData,ids= new Array();
            	for (var i = 0; i < rowIds.length; i++) {
            		rowData = $("#table_list").jqGrid("getRowData",rowIds[i]);
            		ids[i]=rowData.id;
        		}
            	ids = ids.toString();
            	
            	 var confirm = layer.confirm('确定删除选中的出车信息吗?', {offset: '200px', btn: ['确定','取消'] }, function(){
             		layer.close(confirm);		
             		var index = layer.load(1);
             		var data = {"ids":ids}
             		 $.ajax({
             			 type:"POST",
                          url: "${ctx}/dailyOutBackCar/deleteCarBack",
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
        		var imageHtml = "<a  onclick='editDisplayCarOut(\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
        		return imageHtml;
        	}
            //根据id查询信息
            function editDisplayCarOut(id){
            	 index = layer.open({
             	    type: 2,
             	    title: '<h4>查看用车记录</h4>',
             	    maxmin: true,
             	    scrollbar: false,
             	    area: ['50%', '420px'],
             	    content: '${ctx}/dailyOutBackCar/detialDisplayCarOutAndBack?id='+id, //iframe的url
             	    end:function(index){
             	    	jqGrid.trigger("reloadGrid");
             	    }
             	});
            }
            </script>
</body>
</html>