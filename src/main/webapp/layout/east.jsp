<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript" charset="utf-8">
	$(function() {

		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

		$('#layout_east_onlinePanel').panel({
			tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					alert('east refresh');
				}
			} ]
		});

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="layout_east_onlinePanel" data-options="fit:true,border:false" title="Update">
			<div style="margin: 3px;">
				<a href="https://www.google.com" target="_blank">Search</a>
				<hr />
				<div>
					<span>aaa</span>
				</div>
				<div>
					<span>bbb</span>
				</div>
				<div>
					<span>ccc</span>
				</div>
				<hr />
				Feel free to contact me.
			</div>
		</div>
	</div>
	
	<div data-options="region:'north',border:false" style="height:180px; width: 180px; overflow: hidden;">
		<div id="layout_east_calendar"></div>
	</div>
</div>
	
	