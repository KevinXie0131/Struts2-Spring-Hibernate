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
	<div title="Home" data-options="border:false" style="overflow: hidden;">
		<iframe src="${pageContext.request.contextPath}/portal/index.jsp" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
	</div>
</div>

<div id="layout_center_tabs_menu" class="easyui-menu" style="display:none;width:120px;">
	<div iconCls="icon-reload" onclick="layout_center_tabs_refresh();">Refresh</div>
	<div iconCls="icon-cancel" onclick="layout_center_tabs_close();">Close</div>
</div>