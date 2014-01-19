<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="title:'navigator',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="system" data-options="iconCls:'icon-save'">
			<ul id="index_tree" class="easyui-tree" data-options="
					url : '${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',
					parentField : 'pid',
					lines : true,
					onClick : function(node) {
						if (node.attributes.url) {
							var url = '${pageContext.request.contextPath}' + node.attributes.url;
							addTab({
								title : node.text,
								closable : true,
								href : url
							});
						}
					}
					"></ul>
		</div>
		<div title="Title2" data-options="iconCls:'icon-reload'"></div>
	</div>
</div>