<%@ page language="java" pageEncoding="UTF-8"%>
<div id="cc" class="easyui-calendar" style="width:180px;height:180px;"></div>

<select id="cc" class="easyui-combogrid" name="dept" style="width:250px;"
        data-options="
            panelWidth:450,
            value:'006',
            idField:'code',
            textField:'name',
            url:'datagrid_data.json',
            columns:[[
                {field:'code',title:'Code',width:60},
                {field:'name',title:'Name',width:100},
                {field:'addr',title:'Address',width:120},
                {field:'col4',title:'Col41',width:100}
            ]]
        "></select>
        
<input class="easyui-slider" value="12"  style="width:300px"
        data-options="showTip:true,rule:[0,'|',25,'|',50,'|',75,'|',100]">