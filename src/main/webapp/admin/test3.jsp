<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
        $(function(){
            $('#cc').combo({
               required:true,
                editable:false
            });

            $('#sp').appendTo($('#cc').combo('panel'));
            $('#sp input').click(function(){
            var v = $(this).val();
            var s = $(this).next('span').text();
            $('#cc').combo('setValue', v).combo('setText', s).combo('hidePanel');
            });
        });
             
        function formatProgress(value){
	    	if (value){
		    	var s = '<div style="width:100%;border:1px solid #ccc">' +
		    			'<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
		    			'</div>';
		    	return s;
	    	} else {
		    	return '';
	    	}
		}
		function collapseAll(){
			$('#tg').treegrid('collapseAll');
		}
		function expandAll(){
			$('#tg').treegrid('expandAll');
		}
		function expandTo(){
			$('#tg').treegrid('expandTo',21).treegrid('select',21);
		}
</script>
  
<h2>Select</h2>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip"></div>
    <div>Hello</div>
</div>
<select id="cc" style="width:150px"></select>
<div id="sp">
    <div style="color:#99BBE8;background:#fafafa;padding:5px;">Choose</div>
    <input type="radio" name="lang" value="01"><span>Java</span><br/>
    <input type="radio" name="lang" value="02"><span>C#</span><br/>
    <input type="radio" name="lang" value="03"><span>Ruby</span><br/>
    <input type="radio" name="lang" value="04"><span>Basic</span><br/>
    <input type="radio" name="lang" value="05"><span>Fortran</span>
</div>

<div style="margin:10px 0;">
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')">open</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">close</a>
</div>
<div id="dlg" class="easyui-dialog" title="basic dialog" data-options="iconCls:'icon-save'" style="width:400px;height:200px;padding:10px">
dialog content
</div>
 
 
<div style="margin:10px 0;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="collapseAll()">squire all</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="expandAll()">expand all</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="expandTo()">expand to</a>
</div>
<table id="tg" class="easyui-treegrid" title="Tree table" style="width:700px;height:250px"
		data-options="
			iconCls: 'icon-ok',
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			url: '../treegrid/treegrid_data2.json',
			idField: 'id',
			treeField: 'name'
		">
	<thead>
		<tr>
			<th data-options="field:'name',width:180">name</th>
			<th data-options="field:'persons',width:60,align:'right'">people</th>
			<th data-options="field:'begin',width:80">start at</th>
			<th data-options="field:'end',width:80">end at</th>
			<th data-options="field:'progress',width:120,formatter:formatProgress">progress</th>
		</tr>
	</thead>
</table>