<%@ page language="java" pageEncoding="UTF-8"%>
<form id="admin_yhgl_userEditForm" method="post">
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
				<td><input name="createdatetime" />
				</td>
			</tr>
			<tr>
				<th>Modify Datetime</th>
				<td><input name="modifydatetime"  />
				</td>
				<th></th>
				<td></td>
			</tr>
		</table>
	</form>