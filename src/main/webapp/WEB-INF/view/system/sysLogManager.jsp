<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统日志管理</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
<div class="easyui-layout" style="width:100%;"data-options="fit:true">	
	<!-- 查询表单模块 start -->
	<div id="sear" data-options="region:'north',border:false" style="text-align:left;padding:5px 0 5px 15px;height:72px">
		<form action="" id="searchForm">
			<input  id="operatorName"  name="operatorName" class="easyui-textbox" data-options="label:'操作人:'" style="width:250px"/>
			<input  id="operateType"  name="operateType" class="easyui-textbox" data-options="label:'操作事项:'" style="width:250px"/>
			<input  id="operateMenu"  name="operateMenu" class="easyui-textbox" data-options="label:'操作模块:'" style="width:250px"/><br/>
			<input  id="searchDateStart"  name="searchDateStart" class="easyui-datebox" data-options="label:'操作时间:'" style="width:250px"/>至
			<input  id="searchDateEnd"  name="searchDateEnd" class="easyui-datebox" data-options="" style="width:160px"/>
			<a  name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px;margin-left: 165px;">查询</a>
			<a  name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
			<a name="export" id="export" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:80px;margin-left:5px;" onclick="exportExcel();">导出excel</a>
		</form>
	</div>
	<!-- 查询表单模块 end -->
	<div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;">
		<!-- 列表模块 start -->
		<table id="dg" style="display:block;"></table>  
		<!-- 列表模块 end -->
	</div>
</div>
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
// 	按钮显示
	var objArrayYM = new Array("export");   
	showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $dg = $('#dg');
		// 岗位类型表格数据参数设置
		var datagrid = $dg.datagrid({
		    url:"<%=basePath%>sysLog/findSysLogList.do",
			pagination : true,//显示分页  
			rownumbers : true,//显示行号
			pageSize : 20,//分页大小  
			pageList : [ 20, 40, 60, 80 ],//每页的个数  
			fitColumns : true,
			loadFilter : function(data) {
				//过滤数据
				if (data.items == null || data.items == "") {
					return {
						total : 0,
						rows : []
					};
				} else {
					var value = {
						total : data.recordCount,
						rows : []
					};
					var x = 0;
					for (var i = 0; i < data.items.length; i++) {
						value.rows[x] = data.items[i];
						x++;
					}
					return value;
				}
			},
			/* toolbar : [ {
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					add();
				},
			}, "-", {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				},
			} , "-", {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} ], */
			columns : [ [ //每个列具体内容  
			{
				field : 'operatorName',
				title : '操作人',
				width : 50
			},  {
				field : 'operateMenu',
				title : '操作模块',
				width : 80
			}, {
				field : 'operateType',
				title : '操作事项',
				width : 40
			},{
				field : 'operateContent',
				title : '操作详细',
				width : 280,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
			},{
				field : 'browser',
				title : '浏览器',
				width : 50
			},{
				field : 'createTime',
				title : '创建时间',
				width : 80,
				formatter : function(value, row, index) {
					return (value == null || value == '') ? unknow : value.replace(".0", ""); 
				}
			} ] ],
				height : 470
			});
		
		//查询
		$("#doSearch").click(function() {
			$('#dg').datagrid('load', {
				operatorName:$("#operatorName").textbox("getValue"),
				operateType:$("#operateType").textbox("getValue"),
				operateMenu:$("#operateMenu").textbox("getValue"),
				searchDateStart:$("#searchDateStart").textbox("getValue"),
				searchDateEnd:$("#searchDateEnd").textbox("getValue")
			});
		});
		
		function exportExcel() {
			window.location = "<%=basePath%>sysLog/exportExcel.do?date=" + new Date() 
								+ "&operatorName=" + $("#operatorName").textbox("getValue")
								+ "&operateType=" + $("#operateType").textbox("getValue")
								+ "&operateMenu=" + $("#operateMenu").textbox("getValue")
								+ "&searchDateStart=" + $("#searchDateStart").textbox("getValue")
								+ "&searchDateEnd=" + $("#searchDateEnd").textbox("getValue");
		}
		
	</script>
</body>
</html>