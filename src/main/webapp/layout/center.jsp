<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#layout_center_tabs').tabs({
			onContextMenu: function(e, title,index){
				e.preventDefault();
				$('#layout_center_tabs_menu').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		});
	});
	function addTab(opts) {
		var t = $('#layout_center_tabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
	layout_center_tabs_refresh = function(){
		alert("Refresh");
	}
	layout_center_tabs_close = function(){
		var tab = $('#layout_center_tabs').tabs('getSelected');
		var index = $('#layout_center_tabs').tabs('getTabIndex',tab);
		if(index != 0){
			$('#layout_center_tabs').tabs('close',index);
		}else{
			$.messager.show({
				title : 'Message',
				msg : 'Do not close Home page'
			});
		}
	}
</script>
<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false" style="overflow: hidden;">
	<div title="Home">
		<p><font size="4" face="arial" color="blue">Struts2+Spring+Hibernate</font></p>
		<a href="http://localhost:8080/zhibing_ssh/tutorial_layout.html"><font size="4" color="blue">Demo link</font></a>
	</div>
</div>

<div id="layout_center_tabs_menu" class="easyui-menu" style="display:none;width:120px;">
	<div iconCls="icon-reload" onclick="layout_center_tabs_refresh();">Refresh</div>
	<div iconCls="icon-cancel" onclick="layout_center_tabs_close();">Close</div>
</div>