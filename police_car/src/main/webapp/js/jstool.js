function Opts(){ 
	var opts = ""; 
	return {
		add:function(funcName, label){
			if( opts != "" ) {opts += " | ";}
			opts += "<a href=\"javascript:void(0)\" onclick=\"javascript:"+ funcName +"\">"+ label +"</a>";
		},
		getOpts:function(){
			return opts;
		}
	}
} 

function getOpts(funcName, label){
	return "<a href=\"javascript:void(0)\" onclick=\"javascript:"+ funcName +"\">"+ label +"</a>";
}

function setColor(color, content){
	return "<font color='"+ color +"'>"+ content +"</font>"
}

function subTitle(value, length){
	if(value == null){
		return "";
	}else if(value.length <= length){
		return "<span title='"+ value +"'>"+ value +"</span>";
	}else{
		return "<span title='"+ value +"'>"+ value.substring(0, length) +"...</span>";
	}
}

function downloadFile(url, params, isDel){
	var index = layer.load(1);
	$.post(url, params, function(result){
		layer.close(index);
		if(result == null && result == "FAILED"){
			layer.msg('导出失败', {icon:2});
		}else if(result == "NOT_EXIST"){
			layer.msg('文件不存在', {icon:2});
		}else if(result == "NOT_DATA"){
			layer.msg('无数据可导出', {icon:2});
		}else{
			if($("#ifrm")){
				$("body").append('<div style="display: none;"><iframe id="ifrm" name="ifrm" width="0" height="0" style="display: none;"></iframe></div>');
			}
			var del = isDel ? "Y" : "N";
			window.ifrm.location = getPageDomain() + "/common/download.action?url=" + encodeURI(result) +"&del="+del;
		}
	});
}

function download(url,isDel){
	if($("#ifrm")){
		$("body").append('<div style="display: none;"><iframe id="ifrm" name="ifrm" width="0" height="0" style="display: none;"></iframe></div>');
	}
	var del = isDel ? "Y" : "N";
	window.location.href = getPageDomain() + "/common/download.action?url=" +  encodeURI(url) +"&del="+del;
}

/**
 * 用于向文本域中当前光标位置插入文本
 */
function insertText(id, str) {
	var obj = document.getElementById(id);
	if (document.selection) {
    	document.selection.createRange().text = str
	}else {  
		var val = obj.value, startPos = obj.selectionStart;
		obj.value = val.substr(0, startPos) + str + val.substr(obj.selectionEnd); 
		obj.selectionStart = obj.selectionEnd = startPos + str.length;
	} 
}



/**检查js和css是否存在再引入**/
function isInclude(name, suffix){
	if(suffix == "js"){
	    var js= /js$/i.test(name);
	    var es=document.getElementsByTagName(js?'script':'link');
	    for(var i=0;i<es.length;i++) 
	    if(es[i][js?'src':'href'].indexOf(name)!=-1)return true;
	    return false;
	}else if(suffix == "css"){
		
	}
}

function GetHttpRequest(){  
    if ( window.XMLHttpRequest ) // Gecko  
        return new XMLHttpRequest() ;  
    else if ( window.ActiveXObject ) // IE  
        return new ActiveXObject("MsXml2.XmlHttp") ;  
}  
  
  
function include(sId, url){  
	var suffix = sId.substring(sId.lastIndexOf(".") + 1).toLowerCase();
	if(isInclude(sId, suffix)){
		return;
	}
    var oXmlHttp = GetHttpRequest() ;  
    oXmlHttp.onreadystatechange = function(){  
        if (oXmlHttp.readyState == 4){  
        	if(suffix == "js"){
        		includeJS(sId, url, oXmlHttp.responseText );  
        	}else if(suffix == "css"){
        		includeCSS(sId, url, oXmlHttp.responseText ); 
        	}
        }  
    }  
    oXmlHttp.open('GET', url, false);//同步操作  
    oXmlHttp.send(null);  
}  
  
function includeJS(sId, fileUrl, source){  
    if ((source != null) && (!document.getElementById(sId))){  
        var oHead = document.getElementsByTagName('HEAD').item(0);  
        var oScript = document.createElement( "script" );  
        oScript.type = "text/javascript";  
        oScript.id = sId;  
        oScript.text = source;  
        oHead.appendChild(oScript);  
    }  
} 

function includeCSS(sId, fileUrl, source){ 
	var oCss = document.createElement("link"); 
    oCss.setAttribute("rel", "stylesheet"); 
    oCss.setAttribute("type", "text/css");  
    oCss.setAttribute("href", fileUrl);
    document.getElementsByTagName("head")[0].appendChild(oCss);
} 
