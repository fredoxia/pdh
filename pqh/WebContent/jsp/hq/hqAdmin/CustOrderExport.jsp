<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../common/Style.jsp"%>
<script>
$(document).ready(function(){
	parent.$.messager.progress('close'); 
});

var dataGrid;

$(function() {
	dataGrid = $('#dataGrid').datagrid({
		url : '<%=request.getContextPath()%>/rptController/GetHQOrderExportLog',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : false,
		rownumbers:true,
		nowrap : false,
		singleSelect: true,
		columns : [ [ {
			field : 'orderIdentity',
			title : '订货会名称',
			width : 80
		}, {
			field : 'importTime',
			title : '导出时间',
			width : 80
		}, {
			field : 'numOfOrders',
			title : '导出成功单据',
			width : 50
		}, {	
			field : 'numOfError',
			title : '导出错误',
			width : 50
		}, {	
			field : 'operator',
			title : '操作人员',
			width : 50
		} ] ],
		toolbar : '#toolbar',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$(this).datagrid('unselectAll').datagrid('uncheckAll');
			$(this).datagrid('selectRow', rowIndex);
		}
	});
});

function exportOrders(){
	var params = "";
	$.messager.progress({
		title : '提示',
		text : '正在执行数据导出，请稍后....'
	});
	$.post('<%=request.getContextPath()%>/orderController/ExportOrders', params,  function(result) {
		refresh();
		$.messager.progress('close'); 
		if (result.success) {
			$.messager.alert('消息', result.msg, 'info');	

		} else {
			$.messager.alert('失败警告', result.msg, 'error');
		}
	}, 'JSON');
}
function refresh(){
	dataGrid.datagrid('load', '');
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
			<a onclick="exportOrders();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-syn_data',plain:true">导出当前订货会订单到条码系统</a>
			<a onclick="refresh();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">刷新</a>
	</div>


</body>
</html>