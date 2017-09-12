<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员异动记录</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
<div class="easyui-layout" style="width:100%;height:600px;" data-options="fit:true">
	<!-- 查询表单模块 start -->
	<div id="search" data-options="region:'north',border:false" style="text-align:left;font-size:12px;padding:5px 0 5px 15px;height:36px">
		<form action="" id="searchForm">
			异动类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="easyui-combobox" id="typeId" name="typeId" labelPosition="right" style="width:200px;"
				data-options="editable:false,panelHeight:'auto',valueField:'typeId',textField:'typeName',url:'<%=basePath%>sysChangeType/all.do'">
			</select>&nbsp;&nbsp;
<!-- 			异动前部门:&nbsp;&nbsp;<input type="hidden" id="oldDeptNo" name="oldDeptNo" class="easyui-textbox" style="width:200px;;"/> -->
<!-- 			<br/> -->
<!-- 			异动后部门:&nbsp;&nbsp;<input input type="hidden"  id="newDeptNo" name="newDeptNo" class="easyui-textbox" style="width:200px;l"/>	 -->
			异动时间段:&nbsp;&nbsp;<input id="changeDateStart" name="changeDateStart" data-options="editable:false,panelHeight:'auto'" class="easyui-datebox" style="width: 150px;" />
			~
			<input id="changeDateEnd" name="changeDateEnd" data-options="editable:false,panelHeight:'auto'" class="easyui-datebox" style="width: 150px" />
			<a href="#" name="doSearch" id="doSearch"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'"
				style="width: 68px">查询</a> <a href="#" name="reset" id="reset"
				class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
				style="width: 68px;margin-left:5px;">重置</a>
		</form>
	</div>
	<!-- 查询表单模块 end -->
	
	<!-- 列表模块 start -->
	 <div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;">
		<div id="dg" style="width:100%;display:block;"></div>  
	</div> 
	<!-- 列表模块 end -->
	
	<!-- 查看窗口模块 start -->
	<div id="dlgWatch" class="easyui-dialog" title="异动信息查看" data-options="modal:true" closed="true" style="width:550px;height:230px;display:none;padding:10px 0 0 10px;">
		<!-- 人员异动信息form模块 start -->
    	<form action="" id="sysChangeRecWatchForm" name="emp" >
    		<input name="name" class="easyui-textbox" data-options="label:'姓名:'" style="width:240px" readonly="readonly"/>
    		<input name="typeName" class="easyui-textbox" data-options="label:'异动类型:'" style="width:240px" readonly="readonly"/><br />
    		<input name="oldDeptName" class="easyui-textbox" data-options="label:'原部门:'" style="width:240px" readonly="readonly"/>
    		<input name="newDeptName" class="easyui-textbox" data-options="label:'异动后部门:'" style="width:240px" readonly="readonly"/><br />
    		<input name="oldStationName" class="easyui-textbox" data-options="label:'原岗位:'" style="width:240px" readonly="readonly"/>
    		<input name="newStationName" class="easyui-textbox" data-options="label:'异动后岗位:'" style="width:240px" readonly="readonly"/><br />
    		<input name="oldJob" class="easyui-textbox" data-options="label:'原职务:'" style="width:240px" readonly="readonly"/>
    		<input name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" readonly="readonly"/><br />
    		<input name="changeDate" class="easyui-textbox" data-options="label:'异动时间:'" style="width:240px" readonly="readonly"/>
		</form>
		<!-- 人员异动信息form模块 end -->
	</div>
</div>
	<!-- 查看窗口模块 end -->
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		// 	按钮权限显示
		var objArrayYM = new Array("view");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $searchForm = $("#searchForm");
		var $sysChangeRecWatchForm = $("#sysChangeRecWatchForm");
		var $doSearch = $("#doSearch");
		var $reset = $("#reset");
		var $dg = $('#dg');
		var $dlgWatch = $('#dlgWatch');
		var $typeId = $('#typeId');
		var $changeDateStart = $('#changeDateStart');
		var $changeDateEnd = $('#changeDateEnd');
// 		var $oldDeptNo = $('#oldDeptNo');
// 		var $newDeptNo = $('#newDeptNo');
		
		// 定义全局变量区域
		var unknow = '';
		
		// 人员表格数据参数设置
		var datagrid = $dg.datagrid({
		      url:"<%=basePath%>sysChangeRec/findChangeRecList.do",
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
			toolbar : [{
				id:'view',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					watch();
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
				field : 'name',
				title : '姓名',
				width : 30,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'typeName',
				title : '异动类型',
				width : 30,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'newDeptName',
				title : '异动后部门',
				width : 50,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'newStationName',
				title : '异动后岗位',
				width : 50,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'newJob',
				title : '异动后职务',
				width : 30,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'oldDeptName',
				title : '原部门',
				width : 50,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'oldStationName',
				title : '原岗位',
				width : 50,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'oldJob',
				title : '原职务',
				width : 30,
				formatter : function(value){
					if(value){
						return "<span title='" + value	 + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'changeDate',
				title : '异动时间',
				width : 65,
				formatter : function(value, row, index) {
					if(value){
					return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";				}
					}
				} , {
				field : 'verifyDate',
				title : '审核时间',
				width : 65,
				formatter : function(value, row, index) {
					if(value){
					return "<span title='" + value.replace(".0", "") + "'>" + value.replace(".0", "") + "</span>";				}
					}
				} 
			] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 470
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
				typeId:$typeId.combobox("getValue"),
				changeDateStart:$changeDateStart.textbox("getValue"),
				changeDateEnd:$changeDateEnd.textbox("getValue")
// 				oldDeptNo:$oldDeptNo.textbox("getValue"),
// 				newDeptNo:$newDeptNo.textbox("getValue")
			});
		});

		//重置
		$reset.click(function() {
			$searchForm.form('clear');
			$dg.datagrid('load', {
				typeId:$typeId.combobox("getValue"),
				changeDateStart:$changeDateStart.textbox("getValue"),
				changeDateEnd:$changeDateEnd.textbox("getValue")
// 				oldDeptNo:$oldDeptNo.textbox("getValue"),
// 				newDeptNo:$newDeptNo.textbox("getValue")
			});
		});
		
		//弹出查看窗口
		function watch() {
			var rows = $dg.datagrid('getSelections');
			if (rows.length==1) {
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysChangeRec/load.do" ,
					data: {"id":rows[0].changeReocrdId},
					dataType: 'json',
					success: function(data){
						// 填充数据
						$sysChangeRecWatchForm.form('load',{
				    		name:data.name,
				    		typeName:data.typeName,
				    		oldDeptName:data.oldDeptName,
				    		newDeptName:data.newDeptName,
				    		oldStationName:data.oldStationName,
				    		newStationName:data.newStationName,
				    		oldJob:data.oldJob,
				    		newJob:data.newJob,
				    		changeDate:data.changeDate
						});
						$dlgWatch.dialog("open");
					} ,
					error: function(){
						$.messager.alert('错误提示','失败');
					}
				});
			} else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//所在部门的树状展示
// 		$oldDeptNo.combotree({
<%-- 		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000', --%>
// 		    idFiled:"businessId",
// 		    textFiled:"orgName",
// 			parentField:"pid",
// 		    onClick: function(node){
// 		    	/* // 将部门id传递到form中便于后端保存
// 		    	$deptNOHid.val(node.businessId); */
// 		    }
// 		});
		//所在部门的树状展示
// 		$newDeptNo.combotree({
<%-- 		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000', --%>
// 		    idFiled:"businessId",
// 		    textFiled:"orgName",
// 			parentField:"pid",
// 		    onClick: function(node){
// 		    	// 将部门id传递到form中便于后端作为查询条件查询
// 		    	$deptNO.val(node.businessId);
// 		    }
// 		});
	</script>
</body>
</html>