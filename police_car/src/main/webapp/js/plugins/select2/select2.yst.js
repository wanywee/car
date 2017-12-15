(function($) {
	$.loadSelect2 = function(){
		console.log($('select[select2]').size())
		var url = "";
		var defaultValue ="";
		var type = "";
		$('select[select2]').each(function(index){
			var url = $(this).attr("data-url");
			defaultValue = $(this).attr("data-value");
			type = $(this).attr("data-type");
			$(this).select2({
				placeholder: "",
				  ajax: {
					  	type: 'POST',
		                url     : url,//请求的API地址
		                dataType: 'json',//数据类型
		                contentType: "application/x-www-form-urlencoded; charset=utf-8",
		                data    : function(params){
		                    return {
		                        q   : params.term//此处是最终传递给API的参数
		                    }
		                },
		                processResults: function (data) {
		                    return {
		                      results: data
		                    };
		                  },
		            },
		              escapeMarkup: function (markup) { return markup; }, 
					  templateResult : formatState,
					  allowClear: true,
					  createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
					        return { id: term, text: term };
					    }
					}); 
			if(typeof(getDisplay(type,defaultValue))!="undefined"){
		        $(this).html('<option value='+defaultValue+'>'+getDisplay(type,defaultValue)+'</option>');
			}
			 function formatState (state) {
		            if (!state.id) { return state.text; }//未找到结果时直接跳出函数
		            var $state = $(
		                    '<span><option id='+state.id+' />' + state.text + '</span>'
		            );//将API返回的结果转换为模板
		            return $state;
		        }
		});
	}
	})(jQuery);
/**
 * 加载项目名称的
 * @param $
 */
(function($) {
	$.loadSelect3 = function(){
		var url = $('#projectName').attr("data-url");
		defaultValue = 	$('#projectName').attr("data-value");
		type = 	$('#projectName').attr("data-type");
			$('#projectName').select2({
				placeholder: "",
			  ajax: {
				  	type: 'POST',
	                url     : url,//请求的API地址
	                dataType: 'json',//数据类型
	                contentType: "application/x-www-form-urlencoded; charset=utf-8",
	                data    : function(params){
	                    return {
	                        q   : params.term//此处是最终传递给API的参数
	                    }
	                },
	                processResults: function (data) {
	                    return {
	                      results: data
	                    };
	                  },
	            },
			  escapeMarkup: function (markup) { return markup; }, 
			  templateResult : formatState,
			  allowClear: true,
			  createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
			        return { id: term, text: term };
			    }
			}); 
		$('#projectName').html('<option value='+defaultValue+'>'+type+'</option>');
		 function formatState (state) {
	            if (!state.id) { return state.text; }//未找到结果时直接跳出函数
	            var $state = $(
	                    '<span><option id='+state.id+' />' + state.text + '</span>'
	            );//将API返回的结果转换为模板
	            return $state;
	        }
		
}
})(jQuery);

/**
 * 加载项目人员的select
 */
(function($) {
$.loadSelectName =function(obj){
	var url = obj.attr("data-url");
	var type = obj.attr("data-type");
	defaultValue = 	obj.attr("data-value");
	obj.select2({
			placeholder: "",
		  ajax: {
			  	type: 'POST',
                url     : url,//请求的API地址
                dataType: 'json',//数据类型
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data    : function(params){
                    return {
                        q   : params.term//此处是最终传递给API的参数
                    }
                },
                processResults: function (data) {
                    return {
                      results: data
                    };
                  },
            },
		  escapeMarkup: function (markup) { return markup; }, 
		  templateResult : formatState,
		  allowClear: true,
		  createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
		        return { id: term, text: term };
		    }
		}); 
	
	if(typeof(getDisplay(type,defaultValue))!="undefined"){
		obj.html('<option value='+defaultValue+'>'+getDisplay(type,defaultValue)+'</option>');
	}	 function formatState (state) {
            if (!state.id) { return state.text; }//未找到结果时直接跳出函数
            var $state = $(
                    '<span><option id='+state.id+' />' + state.text + '</span>'
            );//将API返回的结果转换为模板
            return $state;
        }
}
})(jQuery);



/**
 * 加载项目计划任务和活动的select
 */
(function($) {
$.loadProjectattr =function(obj){
	var url = obj.attr("data-url");
	var type = obj.attr("data-type");
	defaultValue = 	obj.attr("data-value");
	obj.select2({
			placeholder: "",
		  ajax: {
			  	type: 'POST',
                url     : url,//请求的API地址
                dataType: 'json',//数据类型
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data    : function(params){
                    return {
                        q   : params.term//此处是最终传递给API的参数
                    }
                },
                processResults: function (data) {
                    return {
                      results: data
                    };
                  },
            },
		  escapeMarkup: function (markup) { return markup; }, 
		  templateResult : formatState,
		  allowClear: true,
		  createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
			  if (!term) { return state.text; }//未找到结果时直接跳出函数
			  return { id: term, text: term };
		    }
		}); 
	console.log(defaultValue)
	if(defaultValue!=''){
		obj.html('<option value='+defaultValue+'>'+type+'</option>');
	}
	 function formatState (state) {
            if (!state.id) { return state.text; }//未找到结果时直接跳出函数
            var $state = $(
                    '<span><option id='+state.id+' />' + state.text + '</span>'
            );//将API返回的结果转换为模板
            return $state;
        }
}
})(jQuery);
(function($) {
	$.loadSelect2Role =function(obj){
		var url = obj.attr("data-url");
		var type = obj.attr("data-type");
		defaultValue = 	obj.attr("data-value");
		obj.select2({
			   placeholder: "", 
				multiple: true,
			  ajax: {
				  	type: 'POST',
	                url     : url,//请求的API地址
	                dataType: 'json',//数据类型
	                contentType: "application/x-www-form-urlencoded; charset=utf-8",
	                data    : function(params){
	                    return {
	                        q   : params.term//此处是最终传递给API的参数
	                    }
	                },
	                processResults: function (data) {
	                    return {
	                      results: data
	                    };
	                  },
	            },
	            initSelection : function (element, callback) {
	            	var data = [];
	            	
	            	 if(defaultValue.indexOf(",")!=-1){
	            		var strs = new Array(); //定义一数组 
	            		var strsID = new Array(); //定义一数组 
	        			strs = defaultValue.split(","); //字符分割 
	        			strsID =  type.split(",");
	        			for (var i=0;i<strs.length ;i++ ) { 
	    					obj.append('<option selected="selected" value='+strsID[i]+'>'+strs[i]+'</option>');
	        				 data.push({id: strsID[i], text: strs[i]});
	        			}
	            	}else{
	            		if(defaultValue!=""){
	            			obj.append('<option selected="selected" value='+type+'>'+defaultValue+'</option>');
	            			data.push({id: type, text: defaultValue});
	            		}
	            		
	            	}
			        callback(data);
			    },
			  escapeMarkup: function (markup) { return markup; }, 
			  templateResult : formatState,
			  allowClear: true,
			  createSearchChoice   : function(term, data) {// 创建搜索结果（使用户可以输入匹配值以外的其它值）
			        return { id: term, text: term };
			    }
			}); 
		
		//如果 含有多个角色 先切割字符串
		/*if(defaultValue.indexOf(",")!=-1){
			var strs = new Array(); //定义一数组 
			strs = defaultValue.split(","); //字符分割 
			for (var i=0;i<strs.length ;i++ ) { 
				$(".select2-search--inline").remove();
				$(".select2-selection__rendered").append(
						"<span class='select2-selection__clear'>×</span>" +
						"<li class='select2-selection__choice' title='"+24+"'><span class='select2-selection__choice__remove' role='presentation'>×</span>"+strs[i]+"</li>");
						obj.append('<option value='+24+'>'+strs[i]+'</option>');
			} 
			
		}else{
			$(".select2-search--inline").remove();
			$(".select2-selection__rendered").append(
					"<span class='select2-selection__clear'>×</span>" +
					"<li class='select2-selection__choice' title='"+defaultValue+"'><span class='select2-selection__choice__remove' role='presentation'>×</span>"+defaultValue+"</li>");
					obj.append('<option value='+defaultValue+'>'+defaultValue+'</option>');
		}
		$(".select2-selection__rendered").append("<li class='select2-search select2-search--inline'>"+
          "<input class='select2-search__field' type='search' tabindex='0' autocomplete='off' autocorrect='off' autocapitalize='off' spellcheck='false' role='textbox' aria-autocomplete='list' placeholder='' style='width: 0.75em;'></li>")*/
		function formatState (state) {
	            if (!state.id) { return state.text; }//未找到结果时直接跳出函数
	            var $state = $(
	                    '<span><option id='+state.id+' />' + state.text + '</span>'
	            );//将API返回的结果转换为模板
	            return $state;
	        }
	}
	})(jQuery);