<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" />
</head>
<body>
<div class="wrapper wrapper-content  animated fadeInRight">
<div class="row">
<div class="col-sm-12">
	<div class="search_box">
		<form class="form-inline" id="searchForm">
		<div class="search_group">
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
		</div>
		</form>
	</div>
	<div class="ibox-content">
		<p>
	        <button id="btnAdd" class="btn btn-1 " type="button"><i class="icon iconfont icon-add"></i>&nbsp;新增</button>
	        <!-- <button id="btnImport" class="btn btn-3 " type="button"><i class="icon iconfont icon-import"></i>&nbsp;导入</button> -->
	        <button id="btnExport" class="btn btn-2 " type="button"><i class="icon iconfont icon-export"></i>&nbsp;导出</button>
	        <button id="btnSync" class="btn btn-10 " type="button"><i class="icon iconfont icon-refresh"></i>&nbsp;同步门店</button>
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
<div id="importDiv"  style="display:none;overflow: hidden;">
		<div class="ibox-content" >
		<form method="get" class="form-horizontal" id="form" enctype="multipart/form-data">
		<div class="form-group">
			<label class="col-sm-2 control-label "><i class="must"></i>商户</label>
			<div class="col-sm-6">
				<select id="merchantID" name="merchantID" select2="url:'${ctx}/aliopen/getAliMerchantList4Select2.action'" class="validate[required]" ></select>
			</div>
		</div>
		<div class="form-group ">
			<label class="col-sm-2 control-label "><i class="must"></i>导入文件</label>
			<div class="col-sm-6 ">
	               <input name="file" id="file" type="file" accept=".xlsx,.xls" class="validate[required]">
			</div>
		</div>
		<div class="form-group">
		     <div class="col-sm-6 col-sm-offset-3">
                  <button class="btn  btn-primary" type="button" onclick="importStores()"><i class="icon iconfont icon-saves"></i>&nbsp;保存</button>
              </div>
		</div>
		</form>
	</div>
</div>
<div id="syncDiv"  style="display:none;overflow: hidden;">
	<div class="ibox-content" >
	<form method="get" class="form-horizontal"  id="syncFrom">
	<div class="form-group">
		<label class="col-sm-2 control-label "><i class="must"></i>商户</label>
		<div class="col-sm-10">
			<select id="syncMerchantID" name="merchantID" select2="url:'${ctx}/aliopen/getAliMerchantList4Select2.action'" class="validate[required]" ></select>
			<span class="help-block m-b-none">注：从支付宝平台同步商户门店列表至本系统</span>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-6 col-sm-offset-3">
            <button class="btn  btn-primary" type="button" onclick="syncAliStores()"><i class="icon iconfont icon-refresh"></i>&nbsp;同步</button>
        </div>
	</div>
	</form>
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
                url: "${ctx}/getListKeyCodesjqGrid",
                postData: {},
                ignoreCount: true,
                pginput: true,
                viewrecords: false,
                colNames: ["类型", "编码", "反编码", "描述", "操作"],
                sortname: 'keyType',
                colModel: [
                {
                  name: "keyType",
                  fixed: true,
                  width: 151,
                  sortable:true
                },{
                  name: "code",
                },{
                  name: "decode"
                },{
                  name: "display",
                  formatter:function(value, options, row){
                    	  return setColor("blue", value);
                     }
                },{
                  name: "opt",
                  fixed: true,
                  width: 60,
                  formatter: function(value, options, row) {
                    var opts = new Opts();
                    opts.add("viewRefundDetails(" + row.keyType + ")", "查看");
                    return opts.getOpts();
                  }
                }]
              });
            });
            function search(){
            	var searchStr = $("#serarchStr").val();
            	if(searchStr==''){
					layer.msg('搜索不能为空', {icon: 2});
            	}else{
            		$("#table_list").jqGrid('setGridParam',{ 
            	        datatype:'json', 
            	        postData:{'searchStr':searchStr}, //发送数据 
            	        page:1 
            	    }).trigger("reloadGrid"); //重新载入 
            	}
            }
            </script>
</body>
</html>