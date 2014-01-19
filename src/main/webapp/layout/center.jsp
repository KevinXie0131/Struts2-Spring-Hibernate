<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function addTab(opts) {
		var t = $('#index_centerTabs');
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div id="index_centerTabs" class="easyui-tabs" data-options="fit:true,border:false" style="overflow: hidden;">
	<div title="main"></div>
</div>