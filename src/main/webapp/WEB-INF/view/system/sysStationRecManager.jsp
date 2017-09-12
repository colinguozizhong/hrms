<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员岗位管理</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
	<!-- 查询表单模块 start -->
	<div id="search"  data-options="region:'north',border:false" style="font-size: 12px;text-align:left;padding:5px 0 5px 15px;height:36px">
		<form action="" id="searchForm">
			<select class="easyui-combotree"  id="deptNO" name="deptNO" label="所在单位:" style="width:250px" >
			</select>
			<input id="name" name="name" class="easyui-textbox"
				data-options="label:'　　姓名:'" style="width: 250px" /> 
			<input id="job" name="job" class="easyui-textbox"
				data-options="label:'　　职务:'" style="width: 250px" />
			<a href="#" name="doSearch" id="doSearch"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'"
				style="width: 68px">查询</a> <a href="#" name="reset" id="reset"
				class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
				style="width: 68px;margin-left:5px;">重置</a>
		</form>
	</div>
	<!-- 查询表单模块 end -->
	
	<!-- 列表模块 start -->
	<div id="dg" style="display:block;"></div>  
	<!-- 列表模块 end -->
	
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" data-options="modal:true" closed="true" title="编辑人员岗位信息" style="width: 800px;display: none; height: 360px; padding: 10px" data-options="buttons: '#dlg-buttons'">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit();">更新</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	
		<!-- 当前人员的岗位变更信息基本信息模块 start -->
		<form action="" id="sysStationRecForm" name="emp">
		     <input type="hidden" name="id" id="id" />
		     <input  id="name"  name="name" class="easyui-textbox" data-options="label:'姓名:'" style="width:200px"/>
		     <input name="oldDeptName" class="easyui-textbox" data-options="label:'所在部门:'" style="width:200px" />
		     <input name="newDeptName" class="easyui-textbox" data-options="label:'变更后部门:'" style="width:200px" /><br />
		     <input name="oldStationName" class="easyui-textbox" data-options="label:'原岗位:'" style="width:200px" />
		     <input name="oldStationGrade" class="easyui-textbox" data-options="label:'等级:'" style="width:200px" />
		     <input name="oldPosition" class="easyui-textbox" data-options="label:'职称:'" style="width:200px" /><br />
		     <input name="newStationName" class="easyui-textbox" data-options="label:'变更后岗位:'" style="width:200px" />
		     <input name="newStationGrade" class="easyui-textbox" data-options="label:'岗位等级:'" style="width:200px" />
		     <input name="newPrositon" class="easyui-textbox" data-options="label:'职称:'" style="width:200px" /><br />
		     <input name="changeReason" class="easyui-textbox" data-options="label:'变更原因:'" style="width:612px" />
		</form>
		<!-- 当前人员的岗位变更信息基本信息模块 end -->
		
		<!-- 岗位变更记录列表模块 start -->
		<div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;">
			<div id="dgStationRec" style="display:block;"></div>
		</div>
		<!-- 岗位变更记录列表模块 end -->
	</div>
	<!-- 编辑窗口模块 end -->
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	// 	按钮权限显示
		var objArrayYM = new Array("add");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $searchForm = $("#searchForm");
		var $sysStationRecForm = $("#sysStationRecForm");
		var $doSearch = $("#doSearch");
		var $reset = $("#reset");
		var $dg = $('#dg');
		var $dgStationRec = $('#dgStationRec');
		var $deptNO = $("#deptNO");
		var $name = $("#name");
		var $job = $("#job");
		var $dlgedit = $("#dlg_edit");
		
		// 定义全局变量区域
		var unknow = '';
		
		// 人员岗位表格数据参数设置
		var datagrid = $dg.datagrid({
		    url:"<%=basePath%>sysEmp/findEmpList.do",
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
			toolbar : [ {
				id:'add',
				text : '编辑岗位信息',
				iconCls : 'icon-add',
				handler : function() {
					edit();
				},
			}],
			columns : [ [ //每个列具体内容  
			{
				field : 'ck',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				width : 100,
				hidden : true
			}, {
				field : 'deptName',
				title : '所在单位',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'name',
				title : '姓名',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'sex',
				title : '性别',
				width : 50,
				formatter : function(value, row, index) {
					if (value == 'M') {
						return "男";
					} else if (value == 'F') {
						return "女";
					} else {
						return unknow;
					}
				}
			}, {
				field : 'stationName',
				title : '岗位',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'job',
				title : '职务',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'positionName',
				title : '职称',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'entryDate',
				title : '入职时间',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value.replace("00:00:00.0","") + "'>" + value.replace("00:00:00.0","") + "</span>";
					}
				}
			}, {
				field : 'phone',
				title : '联系电话',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}] ],
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 500
		});
		
		// 参数化分页页面
		var p = $dg.datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10  
			pageList : [ 20, 40, 60, 80 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		
		//查询
		$doSearch.click(function() {
			$dg.datagrid('load', {
				deptNO:$deptNO.combotree("getValue"), 
				name:$name.textbox("getValue"),
				job:$job.textbox("getValue")
			});
		});

		//重置
		$reset.click(function() {
			$searchForm.form('clear');
		});
		
		//所在部门的树状展示
		$deptNO.combotree({
		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	// 将部门id传递到form中便于后端作为查询条件查询
		    	$deptNO.val(node.businessId);
		    }
		});
		
		function edit() {
			var rows = $dg.datagrid('getSelections');
			if (rows.length == 1) {
				var curName = rows[0].name;// 当前所相关联的人员的姓名
				// 加载岗位变更记录列表信息
				$dgStationRec.datagrid({
				    url:"<%=basePath%>sysStationRec/findStationRecList.do?empId=" + rows[0].id,
					pagination : true,//显示分页  
					rownumbers : true,//显示行号
					pageSize : 10,//分页大小  
					pageList : [ 10, 20, 40, 60 ],//每页的个数  
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
					columns : [ [ //每个列具体内容  
		            {
		  				field : 'ck',
		  				checkbox : true
		  			}, {
						field : 'id',
						title : 'id',
						width : 100,
						hidden : true
					}, {
						field : 'newStationName',
						title : '现岗位',
						width : 120
					}, {
						field : 'newStationGrade',
						title : '现岗位等级',
						width : 120
					}, {
						field : 'oldStationName',
						title : '原岗位',
						width : 120
					}, {
						field : 'oldStationGrade',
						title : '原岗位等级',
						width : 120
					}, {
						field : 'alterDate',
						title : '变更时间',
						width : 160
					}, {
						field : 'deptName',
						title : '变更单位',
						width : 160
					}] ],
					height : 240,
					singleSelect : true,
					onSelect: function(rowIndex, rowData) {
						$.ajax({
							type: 'POST',
							url: "<%=basePath%>sysStationRec/load.do" ,
							data: {"id":rowData.id},
							dataType: 'json',
							success: function(data){
								if (data != null) {
									// 加载当前人员的岗位变更信息基本信息
									$sysStationRecForm.form('load',{
										id:data.id,
										name:curName,
										oldDeptName:data.deptName,
										newDeptName:data.newDeptName,
										oldStationName:data.oldStationName,
										oldStationGrade:data.oldStationGrade,
										oldPosition:data.oldPosition,
										newStationName:data.newStationName,
										newStationGrade:data.newStationGrade,
										newPrositon:data.newPrositon,
										changeReason:data.changeReason
									});
								}
							} ,
							error: function(){
								$.messager.alert('错误提示','失败');
							}
						});
					}
				});
				var pp = $dgStationRec.datagrid('getPager');
				$(pp).pagination({
					pageSize : 10,//每页显示的记录条数，默认为10  
					pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
					beforePageText : '第',//页数文本框前显示的汉字  
					afterPageText : '页    共 {pages} 页',
					displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
				});
				
				$dlgedit.dialog("open");
			} else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
	</script>
</body>
</html>