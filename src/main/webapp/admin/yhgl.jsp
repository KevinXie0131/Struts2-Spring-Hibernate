<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
			sortOrder : 'asc',
			/*pagePosition : 'both',*/
			checkOnSelect : false,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'name',
				title : '登录名称',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'pwd',
				title : '密码',
				width : 150,
				formatter : function(value, row, index) {
					return '******';
				}
			}, {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
				}
			}, '-' ]
		});
	});

	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
	}
	function clearFun() {
		$('#admin_yhgl_layout input[name=name]').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
	}
	function append() {
		$('#admin_yhgl_addForm input').val('');
		$('#admin_yhgl_addDialog').dialog('open');
	}
	function remove() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		//var rows = $('#admin_yhgl_datagrid').datagrid('getSelected');
		//var rows = $('#admin_yhgl_datagrid').datagrid('getSelections');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/userAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#admin_yhgl_datagrid').datagrid('load');
							$('#admin_yhgl_datagrid').datagrid('unselectAll');
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}
</script>
<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="admin_yhgl_searchForm">
			检索用户名称(可模糊查询)：<input name="name" /> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>

<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_yhgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								/*$('#admin_yhgl_datagrid').datagrid('load');*/
								/*$('#admin_yhgl_datagrid').datagrid('appendRow',obj.obj);*/
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]" style="width: 500px;height:200px;" align="center">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<th>编号</th>
				<td><input name="id" readonly="readonly" />
				</td>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>创建时间</th>
				<td><input name="createdatetime" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>最后修改时间</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>