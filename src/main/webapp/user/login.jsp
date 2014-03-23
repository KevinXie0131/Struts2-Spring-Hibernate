<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		$('#user_login_loginForm').form({
			url : '${pageContext.request.contextPath}/userAction!login.action',
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#user_login_loginDialog').dialog('close');
				}
				$.messager.show({
					title : 'tips',
					msg : obj.msg
				});
			}
		});
		$('#user_login_loginForm input').bind('keyup', function(event) {/* enter to submit */
			if (event.keyCode == '13') {
				$('#user_login_loginForm').submit();
			}
		});

		window.setTimeout(function() {
			$('#user_login_loginForm input[name=name]').focus();
		}, 0);
	});
</script>
<div id="user_login_loginDialog" class="easyui-dialog" data-options="title:'login',modal:true,closable:false,buttons:[{
				text:'register',
				iconCls:'icon-edit',
				handler:function(){
					$('#user_reg_regDialog').dialog('open');
				}
			},{
				text:'login',
				iconCls:'icon-help',
				handler:function(){
					$('#user_login_loginForm').submit();
				}
			}]">
	<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>Username</th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'Username required'" />
				</td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="pwd" class="easyui-validatebox" data-options="required:true,missingMessage:'Password required'" />
				</td>
			</tr>
		</table>
	</form>
</div>