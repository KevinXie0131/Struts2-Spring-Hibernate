<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="title:'Navigator',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="System" data-options="iconCls:'icon-save'">
			<ul id="layout_west_tree" class="easyui-tree" data-options="
					url : '${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',
					parentField : 'pid',
					lines : true,
					onLoadSuccess : function(node, data){			
						$(this).tree('expandAll') 
					},
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
		<div title="Title2" data-options="iconCls:'icon-reload'">
		</div>
	</div>
</div>
<!-- console.info(node); -->	
<!--  $(this).tree('collapseAll')  -->