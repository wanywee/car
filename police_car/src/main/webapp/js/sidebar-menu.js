(function($) {
	$.fn.sidebarMenu = function(options) {
		options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
		var target = $(this);
		target.addClass('nav');
		target.addClass('nav-list');
		if(options.data) {
			init(target, options.data);
		} else {
			if(!options.url) return;
			$.getJSON(options.url, options.param, function(data) {
				init(target, data);
			});
		}
		var url = window.location.pathname;
		function init(target, data) {
			$.each(data, function(i, item) {
				var li = $('<li id=\'menu_li_' + item.id + '\'></li>');
				var a = $('<a></a>');
				var icon = $('<i></i>');
				icon.addClass("menu-icon fa").addClass(item.menuIcon);
				var text = $('<span></span>');
				text.addClass('menu-text').text(item.menuName);
				a.append(icon);
				a.append(text);
				if(item.menus && item.menus.length > 0) {
					a.attr('href', '#');
					a.addClass('dropdown-toggle');
					var arrow = $('<b></b>');
					arrow.addClass('arrow').addClass('icon-angle-down');
					a.append(arrow);
					li.append(a);
					var menus = $('<ul></ul>');
					menus.addClass('submenu');
					init(menus, item.menus);
					li.append(menus);
				} else {
					var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.menuName + '\',close: true,url: \'' + baseURL+item.menuUrl + '\'});';
					a.attr('href', href);
					li.append(a);
				}
				target.append(li);
			});
		}
	}

	$.fn.sidebarMenu.defaults = {
		url: null,
		param: null,
		data: null
	};
})(jQuery);