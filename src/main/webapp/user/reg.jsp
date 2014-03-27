<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#user_reg_regForm').form({
			url : '${pageContext.request.contextPath}/userAction!reg.action',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#user_reg_regDialog').dialog('close');
				}
				$.messager.show({
					title : 'Message',
					msg : obj.msg
				});
			}
		});
		$('#user_reg_regForm input').bind('keyup', function(event) {/* enter to submit */
			if (event.keyCode == '13') {
				$('#user_reg_regForm').submit();
			}
		});
	});
</script>
<div id="user_reg_regDialog" style="width: 250px;" class="easyui-dialog" data-options="title:'register',closed:true,modal:true,buttons:[{
				text:'register',
				iconCls:'icon-edit',
				handler:function(){
					$('#user_reg_regForm').submit();
				}
			}]">
	<form id="user_reg_regForm" method="post">
		<table>
			<tr>
				<th>Username</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'Username required'" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th>Repassword</th>
				<td><input name="rePwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#user_reg_regForm input[name=pwd]\']'" /></td>
			</tr>
		</table>
	</form>
</div>