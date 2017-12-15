<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>停机位管理</title>
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
		<sp:permission link="/gateposition/addGatePosition" reverse="false">
	        <button id="btnPer" class="btn btn-1 " onclick="editGatePosition(1)" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
	    </sp:permission>    
	    <sp:permission link="/gateposition/editGatePosition" reverse="false">
	        <button id="btnEdit"  class="btn btn-2" onclick="editGatePosition(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	   	</sp:permission>   
	  	<sp:permission link="/gateposition/deletGatePositions" reverse="false">   
	        <button id="btnDelete" class="btn btn-3" onclick="deleteGatePosition()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
	   	</sp:permission>   
	   	<sp:permission link="/gateposition/updateScanRadii" reverse="false">     
	        <button id="btnEdit"  class="btn btn-warning" onclick="editScanRadii()" type="button"><i class="fa fa-bullseye" aria-hidden="true"></i>&nbsp;范围设定</button>
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
                url: "${ctx}/gateposition/getGatePositionList",
                postData: {},
                shrinkToFit: true,
                multiselect: true,
                mtype :"POST",
                pginput: true,
                autowidth:true,
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["停机位号", "坐标","备注"],
                sortname: 'gatePositionCode',
                colModel: [{
                  name: "gatePositionCode",
                  sortable:true,
                  width:100,
                },{
                  name: "coord",
                  width:100,
                },{
                  name: "remark",
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
            
            //新增或者修改
            function editGatePosition(flag){
            	
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
                	    title: '编辑停机位信息',
                	    maxmin: true,
    	          		shadeClose: false,
    	          		anim: 5,
                	    area: ['95%', '80%'],
                	    content: '${ctx}/gateposition/editGatePosition?ID='+ids,
                	   	end:function(index){
                	    	jqGrid.trigger("reloadGrid");
                	    }
                	});
            	}else if(flag == 1){
	            	index = layer.open({
	            	    type: 2,
	            	    scrollbar: false,
	            	    title: '添加停机位信息',
	            	    maxmin: true,
		          		shadeClose: false,
		          		anim: 5,
	            	    area: ['95%', '80%'],
	            	    content: '${ctx}/gateposition/addGatePosition',
	            	   	end:function(index){
	            	    	jqGrid.trigger("reloadGrid");
	            	    }
	            	});
            	}
            }
            
            // 删除
            function deleteGatePosition(){
            	var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            	
            	if(ids.length<1){
            		alert("请选择需要删除的信息!");
            		return;
            	}
            	
            	 var confirm = layer.confirm('确定删除选中的停机位信息吗?', {offset: '200px', btn: ['确定','取消'] }, function(){
             		layer.close(confirm);		
             		var index = layer.load(1);
             		 $.ajax({
             			 type:"POST",
                          url: "${ctx}/gateposition/deletGatePositions",
                          data: {"ids":ids},
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
            
            /* 修改可扫描半径 */
            function editScanRadii(){
            	var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            	if(ids.length<1){
            		alert("请选择需要修改的信息!");
            		return;
            	}
            	
                layer.open({  
                    type: 1,
                    title: '范围设定',
                    closeBtn: 1,
                    shadeClose: true,
                    skin: 'yourclass',
                    content: "<div style='width:320px;margin-left: 3%;' class='form-group has-feedback'>"+
                    "<center><p>请设定司机以停机位坐标为中心</p></center>"+
                    "<center><p>可被扫描的半径范围</p></center>"+
                    "<input id='ScanRadii' class='form-control validate[required,custom[number]] text-input' style='width: 50%;margin-left: 25%;' type='text'/><h2 style='display: inline;'>&nbsp&nbspM</h2>" +
                    "<button style='margin-top:5%;width: 30%;margin-left: 35%;' type='button' class='btn btn-block btn-success btn-lg' onclick='updateScanRadii()'>确定</button></div>",  
                });  
            }
            
            function updateScanRadii(){
            	var ids = $('#table_list').jqGrid('getGridParam', 'selarrrow');
            	 var scanRadiiNumber = $("#ScanRadii").val();
            	 if(scanRadiiNumber == null || scanRadiiNumber == ""){
            		 alert("请输入扫描半径值!");
            		 return;
            	 }
            	 
            	 $.ajax({
     				type : 'POST',
     				url : '${ctx}/gateposition/updateScanRadii',
     				data : {
     					"scanRadiiNumber":scanRadiiNumber,
     					"ids":ids
     					},
     				contentType : 'application/x-www-form-urlencoded; charset=utf-8',
     				success : function(result) {
     					layer.msg(result.message, {icon: 1});
     					var index = parent.layer.getFrameIndex(window.name); 
     					parent.layer.close(index);   
     				},
     				error : function(result,XMLHttpRequest, textStatus, errorThrown) {
     					layer.msg(result.message, {icon: 2});
     					var index = parent.layer.getFrameIndex(window.name);
     					parent.layer.close(index);   
     				}
     			})
     			layer.closeAll();
            }
            
            </script>
</body>
</html>