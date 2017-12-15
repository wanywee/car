var utils = {
	getLocalTime : function(inputTime) {
		if(inputTime != null){
		inputTime = inputTime.replace(/-/g, "/");
		var date = new Date(inputTime);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;
		var minute = date.getMinutes();
		var second = date.getSeconds();
		minute = minute < 10 ? ('0' + minute) : minute;
		second = second < 10 ? ('0' + second) : second;
		return y + '/' + m + '/' + d;
		}
		else{
			return inputTime;
		}
	},
	gettimestamp:function(timestamp){
		/*return new Date(parseInt(timestamp)).toLocaleString().replace(/:\d{1,2}$/,' ');*/    
		 var date = new Date(parseInt(timestamp));
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;
		var minute = date.getMinutes();
		var second = date.getSeconds();
		minute = minute < 10 ? ('0' + minute) : minute;
		second = second < 10 ? ('0' + second) : second;
		return y + '-' + m + '-' + d +' '+ h + ':' + minute+':'+second;
	},
	getTimestampToYmd:function(timestamp){
		/*return new Date(parseInt(timestamp)).toLocaleString().replace(/:\d{1,2}$/,' ');*/    
		 var date = new Date(parseInt(timestamp));
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		return y + '-' + m + '-' + d ;
	},
	getLocalTime2 : function(inputTime) {
		var length = inputTime.replace(/-/g, "/").indexOf(".");
		inputTime = inputTime.replace(/-/g, "/").substring(0,length);
		var date = new Date(inputTime);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;
		var minute = date.getMinutes();
		var second = date.getSeconds();
		minute = minute < 10 ? ('0' + minute) : minute;
		second = second < 10 ? ('0' + second) : second;
		return y + '/' + m + '/' + d +' '+ h + ":" + minute;
	},
	getLocalTime3 : function(inputTime) {
		var length = inputTime.replace(/-/g, "/").indexOf(".");
		inputTime = inputTime.replace(/-/g, "/").substring(0,length);
		var date = new Date(inputTime);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		h = h < 10 ? ('0' + h) : h;
		var minute = date.getMinutes();
		var second = date.getSeconds();
		minute = minute < 10 ? ('0' + minute) : minute;
		second = second < 10 ? ('0' + second) : second;
		return y + '/' + m + '/' + d;
	},
	getDateForStringDate:function (strDate){
		//切割年月日与时分秒称为数组
		var s = strDate.split(" "); 
		var s1 = s[0].split("-"); 
		var s2 = s[1].split(":");
		if(s2.length==2){
			s2.push("00");
		}
		return new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
	} ,
	ajax : function(url, data, async, type, dataType, successfn, errorfn) {
		async = (async == null || async == "" || typeof (async) == "undefined") ? "true": async;
		type = (type == null || type == "" || typeof (type) == "undefined") ? "post": type;
		dataType = (dataType == null || dataType == "" || typeof (dataType) == "undefined") ? "json": dataType;
		data = (data == null || data == "" || typeof (data) == "undefined") ? {
			"date" : new Date().getTime()
		} : data;
		$.ajax({
			type : type,
			async : async,
			data : data,
			url : url,
			success : function(d) {
				successfn(d);
			},
			error : function(e) {
				errorfn(e);
			}
		});
	},
	downloadFile:function(url, params, isDel){
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
					window.ifrm.location =  baseURL+"/common/download?url=" +result.message+"&del="+del;
			}
		});
	},
	resetForm:function(objectFrom){
		objectFrom[0].reset();
	},
	stopBubbling: function (event) {
		if (event.stopPropagation) { 
			// this code is for Mozilla and Opera 
			event.stopPropagation(); 
			} 
			else if (window.event) { 
			// this code is for IE 
			window.event.cancelBubble = true; 
			}
    },
    getEvent :function (){
        if(window.event) {return window.event;}
        func = getEvent.caller;
        while(func!=null){
            var arg0=func.arguments[0];
            if(arg0){
                if((arg0.constructor==Event || arg0.constructor ==MouseEvent
                    || arg0.constructor==KeyboardEvent)
                    ||(typeof(arg0)=="object" && arg0.preventDefault
                    && arg0.stopPropagation)){
                    return arg0;
                }
            }
            func=func.caller;
        }
        return null;
    },
    getMap:function() {//初始化map_,给map_对象增加方法，使map_像Map    
        var map_ = new Object();    
        map_.put = function(key, value) {    
            map_[key+'_'] = value;    
        };    
        map_.get = function(key) {    
            return map_[key+'_'];    
        };    
        map_.remove = function(key) {    
            delete map_[key+'_'];    
        };    
        map_.keyset = function() {    
            var ret = "";    
            for(var p in map_) {    
                if(typeof p == 'string' && p.substring(p.length-1) == "_") {    
                    ret += ",";    
                    ret += p.substring(0,p.length-1);    
                }    
            }    
            if(ret == "") {    
                return ret.split(",");    
            } else {    
                return ret.substring(1).split(",");    
            }    
        };    
        return map_;    
},
    //去掉时分秒的格式化
    getYmh:function(time){
    	var oldTime = new Date(time).getTime();
		var curTime = new Date(oldTime).format("yyyy-MM-dd");
		return curTime;
    },
    getWorkDay:function(startTime,endTime){
    	//起始日期，<span style="background-color: rgb(204, 204, 204);"><span style="color:#FF0000;"><strong>/pattern/是正则表达式的界定符，pattern是要匹配的内容，只用于第一个符号的匹配，g为全局匹配标志</strong></span></span>
    	var beginDate = new Date(startTime.replace(/-/g, "/"));
    	//结束日期
    	var endDate = new Date(endTime.replace(/-/g, "/"));
    	//日期差值,即包含周六日、以天为单位的工时，86400000=1000*60*60*24.
    	var workDayVal = (endDate - beginDate)/86400000 + 1;
    	//工时的余数
    	var remainder = workDayVal % 7;
    	//工时向下取整的除数
    	var divisor = Math.floor(workDayVal / 7);
    	var weekendDay = 2 * divisor;

    	//起始日期的星期，星期取值有（1,2,3,4,5,6,0）
    	var nextDay = beginDate.getDay();
    	//从起始日期的星期开始 遍历remainder天
    	for(var tempDay = remainder; tempDay>=1; tempDay--) {
    	    //第一天不用加1
    	    if(tempDay == remainder) {
    	        nextDay = nextDay + 0;
    	    } else if(tempDay != remainder) {
    	        nextDay = nextDay + 1;
    	    }
    	    //周日，变更为0
    	    if(nextDay == 7) {
    	        nextDay = 0;
    	    }

    	    //周六日
    	    if(nextDay == 0 || nextDay == 6) {
    	        weekendDay = weekendDay + 1;
    	    }
    	}
    	//实际工时（天） = 起止日期差 - 周六日数目。
    	workDayVal = workDayVal - weekendDay
    	return workDayVal;
    }
}