<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var centerTabs;
	var tabsMenu;
	$(function() {
		tabsMenu = $('#tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('type');

				if (type === 'refresh') {
					refreshTab(curTabTitle);
					return;
				}

				if (type === 'close') {
					var t = centerTabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						centerTabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = centerTabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					centerTabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});

		centerTabs = $('#centerTabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			}
		});
	});
	function addTab(node) {
		if (centerTabs.tabs('exists', node.text)) {
			centerTabs.tabs('select', node.text);
		} else {
			if (node.attributes.url && node.attributes.url.length > 0) {
			
				centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					content : '<iframe src="${pageContext.request.contextPath}' + node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(node.text);
						}
					} ]
				});
			} else {
				centerTabs.tabs('add', {
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					content : '<iframe src="error/err.jsp" frameborder="0" style="border:0;width:100%;height:99.4%;"></iframe>',
					tools : [ {
						iconCls : 'icon-mini-refresh',
						handler : function() {
							refreshTab(node.text);
						}
					} ]
				});
			}
		}
	}
	function refreshTab(title) {
		var tab = centerTabs.tabs('getTab', title);
		centerTabs.tabs('update', {
			tab : tab,
			options : tab.panel('options')
		});
	}
</script>
<div id="centerTabs">
	<div title="Home" data-options="border:false" style="overflow: hidden;">
		<hr />
		<div style="size:14px;color:blue">Framework</div>
		<ul>
			<li>Java Struts2 Spring Hiberante</li>
			<li>JQuery-EasyUI Javascript JQuery Ajax</li>
			<li>Oracle10g</li>
			<li>Jackson FastJson</li>
			<li>Log4j</li>
			<li>JUnit</li>
			<li>Maven</li>
		</ul>
		<hr />
		<div>About</div>
		<ul>
			<li>Github link</li>
			<li>Linkedin link</li>
		</ul>
		<hr />	
	</div>
</div>
<div id="tabsMenu" style="width: 120px;display:none;">
	<div type="refresh">Refresh</div>
	<div class="menu-sep"></div>
	<div type="close">Close</div>
	<div type="closeOther">Close other</div>
	<div type="closeAll">Close all</div>
</div>