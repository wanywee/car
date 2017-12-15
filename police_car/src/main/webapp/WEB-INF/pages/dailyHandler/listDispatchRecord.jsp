<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>取车记录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
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
		<p>
		   <sp:permission link="/dailyHandle/addDispatchRecord" reverse="false">
				<button id="btnPer" class="btn btn-1 " onclick="editDispatchRecode('add')" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		   </sp:permission>	
		   <sp:permission link="/dailyHandle/editDispatchRecord" reverse="false">
				<button id="btnEdit"  class="btn btn-2" onclick="editDispatchRecode('edit')" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;编辑</button>
	       </sp:permission>
	        <sp:permission link="/dailyHandle/delDispatchRecord" reverse="false">
	        	<button id="btnDelete" class="btn btn-3" onclick="delDispatchRecode()" type="button"><i class="ace-icon fa fa-trash-o bigger-130"></i>&nbsp;删除</button>
	        </sp:permission>
	        <button id="btnPrint" class="btn btn-10 " onclick="print()" type="button" ><i class="ace-icon glyphicon glyphicon-print"></i>&nbsp;打印</button>
	       
	        
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
                url: "${ctx}/dailyHandle/getListDispatchRecord",
                postData: {},
                pginput: true,
                mtype: 'POST',
                multiselect: true,
                autowidth:true,
                //viewrecords: true,
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["ID","车牌号", "送修类别", "维修费用(元)", "取车日期","维修项目","使用材料","备注"],
                sortname: 'id',
                colModel: [{
                  name: "id",
                  hidden:true,
                  key:true,
                  sortable:true
                },{
                  name: "carId",
                  sortable:true,
                  formatter:function(value, options, row){
                 	 return row.licenseno; 
                   }
                },{
                  name: "repairtype",
                  sortable:true,
                  formatter:function(value, options, row){
                	 return getDisplay("REPAIRTYPE",value); 
                  }
                },{
                  name: "repairMoney",
                  sortable:true
                },{
                  name: "tackcarTime",
                  sortable:true,
                  fixed: true,
                  width:150,
                  formatter:function(value, options, row){
                    	  return setColor("blue",utils.gettimestamp(value));
                     }
                },{
                  name: "repairProject",
                  sortable:true
                },{
                  name: "usedmaterial",
                  sortable:true
                },{
                  name: "remark",
                  sortable:true
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
            function editDispatchRecode(flagCode){
            	var openWindTitle="新增取车记录";
            	var url="";
            	if('edit'==flagCode){
            		url="${ctx}/dailyHandle/editDispatchRecord";
            		var ids=$('#table_list').jqGrid('getGridParam','selarrrow');
            		openWindTitle="编辑取车记录";
                	if(ids.length!=1){
                		layer.msg('请选中编辑行!', {icon: 2});
                		return false;
                	}
                	url=url+"?ID="+ids[0];
            	}else{
            		url="${ctx}/dailyHandle/addDispatchRecord";
            		url=url+"?ID="+'';
            	}
            	index = layer.open({
            	    type: 2,
            	    scrollbar: false,
            	    title: openWindTitle,
            	    maxmin: true,
	          		shadeClose: false,
	          		anim: 5,
            	    area: ['95%', '95%'],
            	    content: url,
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            }
            
            
            /*删除*/
            function delDispatchRecode(){
            	var ids=$('#table_list').jqGrid('getGridParam','selarrrow');
            	if(ids.length<1){
            		layer.msg('请选中删除行！', {icon: 2});
            		return false;
            	}
            	layer.confirm('你确定删除吗？', {
            		  btn: ['确定','取消'], //按钮
            		  offset: '200px',
            		  fix: false
            		}, function(){
            			//请求后台
            			$.ajax({type: 'POST',url:"${ctx}/dailyHandle/delDispatchRecord",dataType:'json',data: {'ids':ids.toString()},success:function(data){
            				if(data.flag){
            					layer.msg('操作成功', {icon: 1});
            					jqGrid.trigger("reloadGrid");
            				}else{
            					layer.msg('操作失败', {icon: 2});
            				}
            			}});
            		  
            		}, function(){
            		  /* layer.msg('取消', {
            		    time: 20000, //20s后自动关闭
            		    btn: ['ok', 'ok']
            		  }); */
            		});
            }
            </script>
</body>
</html>