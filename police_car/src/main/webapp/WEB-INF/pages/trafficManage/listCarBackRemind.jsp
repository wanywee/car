<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>回车提醒</title>
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
		<sp:permission link="/dailyOutBackCar/addCarBack" reverse="false">
	        <button id="btnPer" class="btn btn-1 " onclick="carReminding(0)" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i>&nbsp;提醒还车</button>
	    </sp:permission>    
	    <sp:permission link="/dailyOutBackCar/editCarBack" reverse="false">
	        <button id="btnEdit"  class="btn btn-2" onclick="addTime(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;续时</button>
	    </sp:permission>	
	     <sp:permission link="/dailyOutBackCar/editCarBack" reverse="false">
	        <button id="btnEdit"  class="btn btn-2" onclick="CarBack(0)" type="button"><i class="ace-icon fa fa-pencil bigger-130"></i>&nbsp;已回车</button>
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
                url: "${ctx}/backCarremind/getListCarBack",
                postData: {"isOutAndBack":"isBack"},
                shrinkToFit: true,
                ignoreCount: true,
                multiselect: true,
                pginput: true,
                autowidth:true,
                mtype :"POST",
                viewsortcols:[false,'vertical',true],
                ignoreCount:false,
                colNames: ["","查看详情","用车人","警号","联系方式","车牌号","车辆类型","预计回车时间","出车时长","配载人数","备注"],                
                sortname: 'estimateReturnTime',
                sortorder:'asc',
                colModel: [{
                  name: "id",
                  hidden:true
                },{
					name : "opt",
					width : 50,
					align : 'center',
					formatter : details
				},{
                  name: "caruser",
                  sortable:true,
                  width:100,
                  cellattr: addCellAttr,
                 
                },{
                    name: "stffNo",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                },{
                    name: "phonenumber",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                },{
                    name: "licenseno",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                },{
                    name: "carTypeName",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                },{
                  name: "estimateReturnTime",
                  sortable:true,
                  width:100,
                  cellattr: addCellAttr,
                  formatter : function(value, options,
							row) {
                	    if(value == '' || value == null)return '';
						return setColor("blue", utils
								.gettimestamp(value));
					}
                },{
                    name: "outTime",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                },{
                    name: "entourage",
                    sortable:true,
                    width:100,
                    cellattr: addCellAttr,
                    formatter : function(value, options,
							row) {
                	    if(value == '' || value == null)return '0';
                	    var arr=row.entourage.split(",");
                	    return arr.length;
						
					}
                },{
                  name: "backRemark",
                  sortable:true,
                  width:100,
                  cellattr: addCellAttr,
                }]
              });
            });
            
            //表格全局搜索
            function search(){
            	var searchStr = $("#serarchStr").val();
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr,"isOutAndBack":"isBack"}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            }
            
            //提醒回车
            function carReminding(id){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	layer.msg('推送成功',{icon: 1},1000);
            	/* index = layer.open({
            	    type: 2,
            	    title: '编辑回车记录',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['50%', '450px'],
            	    content: '${ctx}/dailyOutBackCar/addCarBackTime?id='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	}); */
            	
            }
            //续时
            function addTime(id){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	index = layer.open({
            	    type: 2,
            	    title: '续时',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['450px', '360px'],
            	    content: '${ctx}/backCarremind/addCarBackTime?id='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});
            	
            }
            // 已回车
            function CarBack(){
            	var ids=$("#table_list").jqGrid("getGridParam","selarrrow");
            	if(ids.length!=1){
            		layer.msg('请选择行数',{icon: 2});
            		return;
            	}
            	index = layer.open({
            	    type: 2,
            	    title: '已回车',
            	    maxmin: true,
            	    scrollbar: false,
            	    area: ['450px', '350px'],
            	    content: '${ctx}/backCarremind/editCarBack?id='+ids, //iframe的url
            	    end:function(index){
            	    	jqGrid.trigger("reloadGrid");
            	    }
            	});

            }
            /* 表格列字体颜色 */
        	function addCellAttr(rowId, val, rawObject, cm, rdata) {
            	var date1=new Date();
            	var date2=rawObject.estimateReturnTime;            	
        		if (date2-date1.getTime()<0) {
        			return "style='color:red'";
        		}
        	}
            /* 显示详情的操作 */
        	function details(value, options,row) {
        		var imageHtml = "<a  onclick='editDisplayCarBack(event,\""+ row.id+ "\");' id=eye"+row.id+"><i class='ace-icon fa fa-eye'></i></a>";
        		return imageHtml;
        	}
            //根据id查询信息
            function editDisplayCarBack(e,id){
            	 index = layer.open({
             	    type: 2,
             	    title: '<h4>查看用记录</h4>',
             	    maxmin: true,
             	    scrollbar: false,
             	    area: ['50%', '420px'],
             	    content: '${ctx}/dailyOutBackCar/detialDisplayCarOutAndBack?id='+id,//iframe的url
             	    end:function(index){
             	    	jqGrid.trigger("reloadGrid");
             	    }
             	});
            }
            </script>
</body>
</html>