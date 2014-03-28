<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			fitColumns : true,//If the columns are not too many
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
				title : 'ID',
				width : 150,
				checkbox : true
			}, {
				field : 'name',
				title : 'Username',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'pwd',
				title : 'Password',
				width : 150,
				formatter : function(value, row, index) {
					return '******';
				}
			}, {
				field : 'createdatetime',
				title : 'Create Datetime',
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : 'Modify Datetime',
				width : 150,
				sortable : true
			} ] ],
			toolbar : [ {
				text : 'Add',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : 'Delete',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : 'Modify',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-' ]
		});
	});

	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm'))); //'load' input parameters
		//console.info(serializeObject($('#admin_yhgl_searchForm')));
		//$('#admin_yhgl_datagrid').datagrid('load', {name:$('#admin_yhgl_layout input[name=name]').val()});
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
			$.messager.confirm('Confirm', 'Are you sure to delete?', function(r) {
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
								title : 'Message',
								msg : r.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : 'Message',
				msg : 'Please select one to delete'
			});
		}
	}
	function edit() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length == 1) {			
			ids.push(rows[0].id);
			$.ajax({
				url : '${pageContext.request.contextPath}/userAction!edit.action',
				data : {
					ids : ids.join(',')
				},
				dataType : 'json',
				success : function(r) {
					$('#admin_yhgl_datagrid').datagrid('load');
					$('#admin_yhgl_datagrid').datagrid('unselectAll');
					$.messager.show({
						title : 'Message',
						msg : r.msg
					});
				}
			});
		} else {
			$.messager.show({
				title : 'Message',
				msg : 'Please select one to edit'
			});
		}
	}
</script>
<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'Query',border:false" style="height: 100px;">
		<form id="admin_yhgl_searchForm">
			Fuzzy Query by Username: <input name="name" />
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">Query</a> 
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">Clear</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>

<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="
			closed:true,
			modal:true,
			title:'Add user',
			buttons:[{
				text : 'Add',
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
								title : 'Message',
								msg : obj.msg
							});
						}
					});
				}
			}]" 
			style="width: 500px; height:200px;" 
			align="left">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td><input name="id" readonly="readonly" />
				</td>
				<th>Username</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" />
				</td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" />
				</td>
				<th>Create Datetime</th>
				<td><input name="createdatetime" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>Modify Datetime</th>
				<td><input name="modifydatetime" readonly="readonly" />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>
</div>