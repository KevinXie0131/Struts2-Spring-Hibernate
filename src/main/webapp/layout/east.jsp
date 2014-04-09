<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" charset="utf-8">
	var onlineDatagrid;
	var onlinePanel;
	var calendar;
	$(function() {

		calendar = $('#calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

		onlinePanel = $('#onlinePanel').panel({
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					if (onlineDatagrid.datagrid('options').url) {
						onlineDatagrid.datagrid('load');
					}
				}
			} ]
		});

		onlineDatagrid = $('#onlineDatagrid').datagrid({
			url : '${pageContext.request.contextPath}/demo/onlineAction!doNotNeedSession_onlineDatagrid.action',
			title : '',
			iconCls : '',
			fit : true,
			fitColumns : true,
			pagination : false,
			pageSize : 10,
			pageList : [ 10 ],
			nowarp : false,
			border : false,
			idField : 'cid',
			sortName : 'cdatetime',
			sortOrder : 'desc',
			frozenColumns : [ [ {
				title : 'ID',
				field : 'cid',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				title : 'Name',
				field : 'cname',
				width : 150,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return sy.fs('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : 'IP',
				field : 'cip',
				width : 150,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return sy.fs('<span title="{0}">{1}</span>', value, value);
				}
			}, {
				title : 'Time',
				field : 'cdatetime',
				width : 150,
				sortable : true,
				formatter : function(value, rowData, rowIndex) {
					return sy.fs('<span title="{0}">{1}</span>', value, value);
				}
			} ] ],
			onLoadSuccess : function(data) {
				onlinePanel.panel('setTitle', '( ' + data.total + ' ) online');
			}
		});

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height:180px;overflow: hidden;">
		<div id="calendar"></div>
	</div>
	<div data-options="region:'center',border:false" style="overflow: hidden;">
		<div id="onlinePanel" data-options="fit:true,border:false" title="Online User">
			<table id="onlineDatagrid"></table>
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:180px;overflow: hidden;">
		<a href='http://www.google.com'>google</a>
	</div>

</div>