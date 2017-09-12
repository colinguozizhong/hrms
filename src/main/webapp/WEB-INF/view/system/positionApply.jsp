<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职称申请管理</title>
<%@include file="/common/common_easyui.jsp" %>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//按钮权限显示
var objArrayYM = new Array("add","edit","view","del","tRecord","submit","approval");   
var curDeptNo;
$(function(){
	showObj(objArrayYM);//共同方法
	var $zTreeMenuBusiness = $('#zTreeMenuBusiness');
	// 用户表格数据
	var datagrid = $("#dg").datagrid({  
	      url:"<%=basePath%>positionApply/findPositionApplyList.do",
	      pagination:true,//显示分页  
	      rownumbers:true,//显示行号
	      pageSize:20,//分页大小  
	      pageList:[20,40,60,80],//每页的个数  
	     // fit:true,//自动补全  
	      fitColumns:true, 
		  loadFilter : function(data){
			 //过滤数据
			if(data.items==null || data.items==""){
					return {total:0,rows:[]}; 
			}else{
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
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					add();
				},
			},{
				id:'edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				},
			},{
				id:'view',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					view();
				}
			},{
				id:'del',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			},{
				id:'tRecord',
				text : '查看职称培训记录',
				iconCls : 'icon-search',
				handler : function() {
					searchTrianRecord();
				}
			},{
				id:'submit',
				text : '提交',
				iconCls : 'icon-submit',
				handler : function() {
					submit();
				}
			} , {
				id:'approval',
				text : '审批',
				iconCls : 'icon-approve',
				handler : function() {
					verify();
				}
			}],
			columns : [ [ //每个列具体内容  
			{field:'ck',checkbox : true}, 
			{field:'applyEmpId',title : '申请人Id',width : 150,hidden:true},
			{field:'applyEmpName',title:'姓名',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'orgName',title:'所在部门',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'oldPositionId',title : '原职称Id',width : 150,hidden:true},
			{field:'oldStationId',title : '原岗位Id',width : 150,hidden:true},
			{field:'oldPositionName',title:'原职称名称',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'oldStationName',title:'原岗位名称',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'appPositionName',title:'申请职称名称',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, 
			{field:'appStationName',title:'申请岗位名称',width:140,hidden:true,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},    
			{field:'appJob',title:'申请职务',width:140,hidden:true,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, 
			{field:'appStatus',title:'申请状态',width:140,
				formatter : function(value, row, index) {
					if(value == "0") return "已保存";
					else if(value == "1") return "已提交";
					else if(value == "2") return "已审批";
				}
			},
			{field:'applyDate',title:'申请日期',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
					}
				}
			},
			{field:'applyReason',title:'申请原因',width:200,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}
			] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 470,
		});

		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10  
			pageList : [ 20, 40, 60, 80 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});
		
		//查询
		$("#doSearch").click(function() {
			$('#dg').datagrid('load', {
				dept : curDeptNo,
				applyEmpName:$("#s_applyEmpName").textbox("getValue"),
				applyDateStart:$("#s_applyDateStart").textbox("getValue"),
				applyDateEnd:$("#s_applyDateEnd").textbox("getValue"),
				appPositionId:$("#s_appPositionId").combobox("getValue"),
				appStatus:$("#s_appStatus").textbox("getValue")
			});
		});

		//重置
		$("#reset").click(function() {
			$("#searchForm").form('clear');
			$('#dg').datagrid('load', {
				dept : curDeptNo,
				applyEmpName:$("#s_applyEmpName").textbox("getValue"),
				applyDateStart:$("#s_applyDateStart").textbox("getValue"),
				applyDateEnd:$("#s_applyDateEnd").textbox("getValue"),
				appPositionId:$("#s_appPositionId").combobox("getValue"),
				appStatus:$("#s_appStatus").textbox("getValue")
			});
		});

		$("#applyEmpName").textbox('textbox').bind("click", function () {
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1200,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		});
		
		//加载业务树
		$zTreeMenuBusiness.tree({
			url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
			parentField:"pid",
			textFiled:"orgName",
			idFiled:"businessId",
			lines:true,
			animate:true,
			onClick: function(node){
				curDeptNo = node.businessId;
				$("#dg").datagrid('load', {
					dept : node.businessId 
				}); 
		    },
// 		    onLoadSuccess: function(node, data) {
// 		    	$zTreeMenuBusiness.tree('insert', {
// 		    		before: $zTreeMenuBusiness.tree('getRoot').target,
// 		    		data: [{
// 		    			orgName:"所有部门"
// 		    		}]
// 		    	});
// 		    }
		});
});



//增加弹窗
function add(){
	$("#positionApplyForm").form("clear");
	$("#dlg_edit").dialog({title : "新增职称申请单信息"});
	$('#dg').datagrid('clearSelections'); 
	$("#dlg_edit").dialog("open");
}

function view(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$('#show_positionApply').form('load',{
			applyId:rows[0].applyId,
			applyEmpId:rows[0].applyEmpId,
			applyEmpName:rows[0].applyEmpName,
			applyDate:rows[0].applyDate,
			applyReason:rows[0].applyReason,
			oldStationId:rows[0].oldStationId,
			oldStationName:rows[0].oldStationName,
			oldPositionId:rows[0].oldPositionId,
			oldPositionName:rows[0].oldPositionName,
			oldJob:rows[0].oldJob,
			appStationId:rows[0].appStationId,
			appPositionId:rows[0].appPositionId,
			appStationName:rows[0].appStationName,
			appPositionName:rows[0].appPositionName,
			appJob:rows[0].appJob,
			verifyPer:rows[0].verifyPer,
			verifyDate:rows[0].verifyDate,
			approvPer:rows[0].approvPer,
			verifyPer:rows[0].approvDate
		});
		$('#dlg_view').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

function searchTrianRecord(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		var id = rows[0].applyEmpId;
		// 加载职称培训记录
		$("#dg_train").datagrid({
		      url:"<%=basePath%>positionTrainRecord/searchPositionTrainRecord.do?empId=" + id,
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
				field : 'id',
				title : 'id',
				width : 100,
				hidden : true   
			}, {
				field : 'trainContent',
				title : '培训内容',
				width : 140
			}, {
				field : 'trainDept',
				title : '培训部门',
				width : 140
			}, {
				field : 'trainScore',
				title : '培训成绩',
				width : 140
			}, {
				field : 'trainResult',
				title : '培训结果',
				width : 140
			}, {
				field : 'trainDate',
				title : '培训日期',
				width : 140,
				formatter : function(value, row, index) {
					return (value == null || value == '') ? unknow : value.replace("00:00:00.0", ""); 
				}
			}] ],
			height : 240
		});
		var pp = $("#dg_train").datagrid('getPager');
		$(pp).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10  
			pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		$('#searchTrainRecordDiv').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//增加弹窗提交
function showSubmit(){
	//判断是新增还是更新
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if($("#positionApplyForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>positionApply/updatePositionApply.do" ,
				data: $('#positionApplyForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code == 0){
						$.messager.show({
							title:'提示',
							msg:'更新成功',
							timeout:3000,
							showType:'slide'
						});
						//插入修改职称申请日志
						insertSysLog("职称申请","修改","单号为"+$("#headId").val()+"的职称申请单");
						$("#dg").datagrid('load', {
							dept : curDeptNo
						});
						$('#dlg_edit').dialog('close')
					}else{
						$.messager.alert('错误提示','系统出现异常，请联系管理员');
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','失败');
				}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
	}else{
		if($("#positionApplyForm").form('validate')){
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>positionApply/addPositionApply.do" ,
				data: $('#positionApplyForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'新增成功',
							timeout:3000,
							showType:'slide'
						});
						//插入职称申请日志
						insertSysLog("职称申请","新增","单号为"+data.id+"的职称申请单");
						$("#dg").datagrid('load', {
							dept : curDeptNo
						});
						$('#dlg_edit').dialog('close')
					}else{
						$.messager.alert('新增发生错误，请于管理员联系');
					}
				} ,
				error: function(){
					$.messager.alert('新增发生错误，请于管理员联系');
				}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
	}
}

//更新
function update(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if(rows[0].appStatus!="0"){
			$.messager.alert("操作提示", "不能修改已提交的申请单！");
			return;
		}else{
			$('#positionApplyForm').form('load',{
				headId:rows[0].headId,
				detailId:rows[0].detailId,
				applyEmpId:rows[0].applyEmpId,
				applyEmpName:rows[0].applyEmpName,
				applyDate:rows[0].applyDate,
				applyReason:rows[0].applyReason,
				oldStationId:rows[0].oldStationId,
				oldStationName:rows[0].oldStationName,
				oldPositionId:rows[0].oldPositionId,
				oldPositionName:rows[0].oldPositionName,
				oldJob:rows[0].oldJob,
				appStationId:rows[0].appStationId,
				appPositionId:rows[0].appPositionId,
				appStationName:rows[0].appStationName,
				appPositionName:rows[0].appPositionName,
				appJob:rows[0].appJob,
				verifyPer:rows[0].verifyPer,
				verifyDate:rows[0].verifyDate
			});
			$("#dlg_edit").dialog({title: "修改职称申请单信息"});
			$('#dlg_edit').dialog('open');
		}
		
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//删除
function del(){
	var rows = $('#dg').datagrid('getSelections');
    var headId = "";
	if(rows.length==1){
		if(rows[0].appStatus!="0"){
			$.messager.alert("操作提示", "只能删除已保存的申请单！");
			return;
		}else{
			headId = rows[0].headId;
			$.messager.confirm('提示框', '你确定要删除吗?',function(r){
				if(r){
					$.ajax({
						url: '<%=basePath%>positionApply/deletePositionApply.do?headId=' + headId,
								success:function(){
									$("#dg").datagrid('load', {
										dept : curDeptNo
									});
									$.messager.show({
										title:'提示',
										msg:'删除成功',
										timeout:3000,
										showType:'slide'
									});
									//插入删除职称申请日志
									insertSysLog("职称申请","删除","单号为"+headId+"的职称申请单");
								}
					});
				}
			})
		}
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

function verify(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){ 
		if(rows[0].appStatus!="1"){
			$.messager.alert("操作提示", "只能审批已提交的申请单！");
			return;
		}else{
			$('#verify_positionApply').form('load',{
				headId:rows[0].headId,
				detailId:rows[0].detailId,
				applyEmpId:rows[0].applyEmpId,
				applyEmpName:rows[0].applyEmpName,
				applyDate:rows[0].applyDate,
				applyReason:rows[0].applyReason,
				oldStationId:rows[0].oldStationId,
				oldStationName:rows[0].oldStationName,
				oldPositionId:rows[0].oldPositionId,
				oldPositionName:rows[0].oldPositionName,
				oldJob:rows[0].oldJob,
				appStationId:rows[0].appStationId,
				appPositionId:rows[0].appPositionId,
				appStationName:rows[0].appStationName,
				appPositionName:rows[0].appPositionName,
				appJob:rows[0].appJob,
				verifyPer:rows[0].verifyPer,
				verifyDate:rows[0].verifyDate
			});
			$('#dlg_verify').dialog('open');
		}
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//审核
function toVerify(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$.messager.confirm('提示框', '你确定要审批吗?',function(r){
		if(r){	
			var headId = rows[0].headId;
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>positionApply/verifyPositionApply.do?headId="+headId,    
				data: headId,
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'审批成功',
							timeout:3000,
							showType:'slide'
						});
						//插入审批职称申请日志
						insertSysLog("职称申请","审批","单号为"+headId+"的职称申请单");
						$("#dg").datagrid('load', {
							dept : curDeptNo
						});
						$('#dlg_verify').dialog('close')
					}else{
						$.messager.alert('系统发生错误，请于管理员联系');
					}
				} ,
				error: function(){
					$.messager.alert('系统发生错误，请于管理员联系');
				}
			})
		}
		});
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

function submit(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if(rows[0].appStatus!="0"){
			$.messager.alert("操作提示", "只能提交已保存的申请单！");
			return;
		}else{
			$.messager.confirm('提示框', '你确定要提交吗?',function(r){
				if(r){
					$.ajax({
						url: '<%=basePath%>positionApply/submitPositionApply.do?headId=' + rows[0].headId,
								success:function(){
									$("#dg").datagrid('load', {
										dept : curDeptNo
									});
									$.messager.show({
										title:'提示',
										msg:'提交成功',
										timeout:3000,
										showType:'slide'
									});
									//插入修改职称申请日志
									insertSysLog("职称申请","提交","单号为"+rows[0].headId+"的职称申请单");
								}
					});
				}
			})
		}
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//更新申请人信息
function updateApplyPerson(empId,empName) {
    $("#applyEmpId").val(empId);
	$("#applyEmpName").textbox("setValue", empName); 
	$.ajax({  
		type: 'POST',
		url: "<%=basePath%>sysEmp/loadPosAndSta.do",    
		data: {"id":empId},
		dataType: 'json',
		success: function(data){
			$("#oldPositionName").textbox("setValue", data.oldPositionName);
			$("#oldStationName").textbox("setValue", data.oldStationName);
			$("#oldJob").textbox("setValue", data.oldJob);
			$("#oldPositionId").val(data.oldPositionId);
			$("#oldStationId").val(data.oldStationId);
		} ,
		error: function(){
			$.messager.alert('系统发生错误，请于管理员联系');
		}
	});
}
</script>
</head>

<body>
	<div class="easyui-layout" style="width:100%;height:600px;" data-options="fit:true">
		<!-- 部门列表模块 start -->
		<div data-options="region:'west',iconCls:'icon-ok',split:true" title="部门列表" style="width:20%">
			<div >
				<ul id="zTreeMenuBusiness" class="easyui-tree">
				</ul>
			</div>
		</div>
		<!-- 部门列表模块 end -->
	<!-- 查询表单模块 start -->
		<div data-options="region:'center',title:'职称申请',iconCls:'icon-ok'">
			<div id="sear" style="text-align:left;padding:5px 0 0 15px;height:36px">
				<form action="" id="searchForm">
					<input  id="s_applyEmpName"  name="applyEmpName" class="easyui-textbox" data-options="label:'申请人:'" style="width:250px"/>
<!-- 					<select class="easyui-combotree"  id="s_dept" name="dept" label="所在部门:" style="width:240px" > -->
<!-- 					</select> -->
					<input  id="s_applyDateStart"  name="applyDateStart" class="easyui-datebox" data-options="label:'申请时间:'" style="width:250px"/>至
					<input  id="s_applyDateEnd"  name="applyDateEnd" class="easyui-datebox" data-options="" style="width:160px"/><br />
					<select  class="easyui-combobox" id="s_appPositionId" name="appPositionId" labelPosition="right" style="width:250px"
					data-options="label:'申请职称:',valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'">
					</select>
						<select  class="easyui-combobox" id="s_appStatus" name="appStatus" data-options="label:'申请状态:'"  labelPosition="right" style="width:250px" >
						<option value="">--请选择--</option>
						<option value="0">已保存</option>
						<option value="1">已提交</option>
						<option value="2">已审批</option>
					</select>		
					<a  name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
					<a  name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px;">重置</a>
				</form>
			</div>
			<!-- 查询表单模块 end -->
		<!-- 列表模块 start -->
		<div  data-options="region:'center',border:false" style="text-align:left;padding:5px 0 0 5px;">
			<table id="dg" style="display:block;width:100%;padding:5px 0 0 5px;margin:5px 10px 0 0;"></table>  
		</div>
		<!-- 列表模块 end -->
		</div>
	</div>
   		
<div id="dlg_edit" class="easyui-dialog" closed="true" title="职称申请" style="width: 600px; height: 260px; padding: 10px" data-options="modal:true,buttons: '#dlg-buttons'">
	<form action="" id="positionApplyForm" name="positionApplyForm">
		    <input type="hidden" name="headId" id="headId" />
		    <input type="hidden" name="detailId" id="detailId" />
		    <input type="hidden" name="applyEmpId" id="applyEmpId" />
		    <input type="hidden" name="oldPositionId" id="oldPositionId" />
		    <input type="hidden" name="oldStationId" id="oldStationId" />
		    <input  id="applyEmpName"  name="applyEmpName" class="easyui-textbox" data-options="label:'申请人:',required:true" style="width:250px"/><span style="color:red;">*</span>
		    <input  id="applyDate"  name="applyDate" class="easyui-datebox" data-options="label:'申请日期:',required:true" style="width:250px"/><span style="color:red;">*</span>
   			<div style="height:5px"></div>
		    <input  id="oldPositionName"  name="oldPositionName" class="easyui-textbox" data-options="label:'原职称:'" style="width:250px" readonly="readonly"/><span style="color:white;">*</span>
			<input  id="oldStationName"  name="oldStationName" class="easyui-textbox" data-options="label:'原岗位:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<!-- <select class="easyui-combotree"  id="dept" name="dept" label="所在部门:" style="width:240px" >
			</select><br /><br /> -->
		<%-- 	<select  class="easyui-combobox" id="oldPositionId" name="oldPositionId" labelPosition="right" style="width:240px"
				data-options="label:'原职称:',valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'" required="required" >
			</select>		
			<select  class="easyui-combobox" id="oldStationId" name="oldStationId"  labelPosition="right" style="width:240px"
				data-options="label:'原岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" required="required" >
			</select><br /><br /> --%>
			<input  id="oldJob"  name="oldJob" class="easyui-textbox" data-options="label:'原职务:'" style="width:250px" readonly="readonly"/><span style="color:white;">*</span>
			<select  class="easyui-combobox" id="appPositionId" name="appPositionId" labelPosition="right" style="width:250px"
				data-options="editable:false,label:'申请职称:',valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'" required="required" >
			</select><span style="color:red;">*</span>
   			<div style="height:5px"></div>
			<select  class="easyui-combobox" id="appStationId" name="appStationId"  labelPosition="right" style="width:250px"
				data-options="editable:false,label:'申请岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" required="required" >
			</select><span style="color:white;">*</span>
			<input  id="appJob"  name="appJob" class="easyui-textbox" data-options="label:'申请职务:'" style="width:250px"/>
   			<div style="height:5px"></div>
			<input  id="applyReason" name="applyReason" class="easyui-textbox" data-options="label:'申请原因:'" style="width:520px"/>
			
	</form>
</div>
<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">保存</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a> -->
</div>

<div id="dlg_view" class="easyui-dialog" closed="true" title="查看职称申请单" style="width: 600px; height: 250px; padding: 0;margin:0;" data-options="modal:true,buttons: '#dlgView-buttons'">
 	<form action="" id="show_positionApply" style="background-color:#ECF6FA">
 			<input type="hidden" name="applyEmpId" id="v_applyEmpId" />
		    <input  id="v_applyEmpName"  name="applyEmpName" class="easyui-textbox" data-options="label:'申请人:'" style="width:250px" readonly="readonly" />&nbsp;
		    <input  id="v_applyDate"  name="applyDate" class="easyui-datebox" data-options="label:'申请日期:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_oldPositionName" name="oldPositionName" class="easyui-textbox" data-options="label:'原岗位:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="v_oldStationName" name="oldStationName" class="easyui-textbox" data-options="label:'原职称:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_oldJob" name="oldJob" class="easyui-textbox" data-options="label:'原职务:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="v_appPositionName"  name="appPositionName" class="easyui-textbox" data-options="label:'申请职称:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_appStationName"  name="appStationName" class="easyui-textbox" data-options="label:'申请岗位:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="v_appJob"  name="appJob" class="easyui-textbox" data-options="label:'申请职务:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_applyReason" name="applyReason" class="easyui-textbox" data-options="label:'申请原因:'" style="width:520px" readonly="readonly"/>
			<!-- <input  id="v_verifyPer"  name="verifyPer" class="easyui-textbox" data-options="label:'审核人:'" style="width:250px" readonly="readonly"/><br /><br />
			<input  id="v_verifyDate"  name="verifyDate" class="easyui-datetimebox" data-options="label:'审核时间:'" style="width:250px" readonly="readonly"/><br /><br />
			<input  id="v_approvPer"  name="approvPer" class="easyui-textbox" data-options="label:'审批人:'" style="width:250px" readonly="readonly"/><br /><br />
			<input  id="v_approvDate"  name="approvDate" class="easyui-datetimebox" data-options="label:'审批时间:'" style="width:250px" readonly="readonly"/><br /><br /> -->
    </form>
</div>


<div id="dlg_verify" class="easyui-dialog" closed="true" title="审批职称申请单" style="width: 600px; height: 250px; padding: 0;margin:0;" data-options="modal:true,buttons: '#verify-buttons'">
 	<form action="" id="verify_positionApply" style="background-color:#ECF6FA">
 			<input type="hidden" name="applyEmpId" id="v_applyEmpId" />
 			<input type="hidden" name="headId" id="a_headId" />
		    <input  id="a_applyEmpName"  name="applyEmpName" class="easyui-textbox" data-options="label:'申请人:'" style="width:250px" readonly="readonly" />&nbsp;
		    <input  id="a_applyDate"  name="applyDate" class="easyui-datetimebox" data-options="label:'申请日期:'" style="width:250px" readonly="readonly"/>
		    <div style="height:5px"></div>
			<input  id="a_oldPositionName" name="oldPositionName" class="easyui-textbox" data-options="label:'原岗位:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="a_oldStationName" name="oldStationName" class="easyui-textbox" data-options="label:'原职称:'" style="width:250px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="a_oldJob" name="oldJob" class="easyui-textbox" data-options="label:'原职务:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="a_appPositionName"  name="appPositionName" class="easyui-textbox" data-options="label:'申请职称:'" style="width:250px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="a_appStationName"  name="appStationName" class="easyui-textbox" data-options="label:'申请岗位:'" style="width:250px" readonly="readonly"/>&nbsp;
			<input  id="a_appJob"  name="appJob" class="easyui-textbox" data-options="label:'申请职务:'" style="width:250px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="a_applyReason" name="applyReason" class="easyui-textbox" data-options="label:'申请原因:'" style="width:520px" readonly="readonly"/>
    </form>
</div>
<div id="verify-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="toVerify()">审批</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_verify').dialog('close')">关闭</a> -->
</div>
<div id="searchTrainRecordDiv" class="easyui-dialog" closed="true" title="职称培训记录" style="width: 750px; height: 300px; padding: 0;margin:0;" data-options="modal:true">
		<table id="dg_train" style="display:block;"></table>  
</div>
<div id="dlgView-buttons">
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_view').dialog('close')">关闭</a> -->
</div>
</body>
</html>
