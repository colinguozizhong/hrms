<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后备干部记录</title>
<%@include file="/common/common_easyui.jsp" %>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
var flag;
//	按钮权限显示
var objArrayYM = new Array("add","edit","view","del","submit","approval");  
var curDeptNo;
$(function(){
	showObj(objArrayYM);//共同方法
	var $zTreeMenuBusiness = $('#zTreeMenuBusiness');
	// 用户表格数据
	var datagrid = $("#dg").datagrid({  
	      url:"<%=basePath%>cadreReserve/findCadreReserveList.do",
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
				}
			}, {
				id:'edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
			}, {
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
				id:'submit',
				text : '提交',
				iconCls : 'icon-submit',
				handler : function() {
					submit();
				}
			}, {
				id:'approval',
				text : '审批',
				iconCls : 'icon-approve',
				handler : function() {
					approve();
				}
			} ],
			columns : [ [ //每个列具体内容  
			{field : 'ck',checkbox : true}, 
			{field : 'headId',title : 'headId',width : 150,hidden:true},
			{field:'recommendPersonId',title:'推荐人',width:140,hidden:true},
			{field:'empName',title:'姓名',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'dept',title:'所在单位',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'recommendPersonName',title:'推荐人',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'recommendDeptName',title:'推荐单位',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'recommendBallot',title:'总票数',width:140,
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
					else if(value == "3") return "未通过";
				}
			},
			{field:'recommendDate',title:'推荐日期',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
					}
				}
			}
			] ],
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 470
		});

		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10  
			pageList : [ 20, 40, 60, 80 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});


		//查询
		$("#doSearch").click(function() {
			$('#dg').datagrid('load', {
				dept : curDeptNo,
				empName:$("#s_empName").textbox("getValue"),
				recommendDept:$("#s_recommendDept").combotree("getValue"), 
				recommendPersonName:$("#s_recommendPersonName").textbox("getValue"),
				recommendDateStart:$("#s_recommendDateStart").textbox("getValue"),
				recommendDateEnd:$("#s_recommendDateEnd").textbox("getValue"),
				appStatus:$("#s_appStatus").textbox("getValue")
			});
		});
		
		//重置
		$("#reset").click(function() {
			$("#searchForm").form('clear');
			$('#dg').datagrid('load', {
				dept : curDeptNo,
				empName:$("#s_empName").textbox("getValue"),
				recommendDept:$("#s_recommendDept").combotree("getValue"), 
				recommendPersonName:$("#s_recommendPersonName").textbox("getValue"),
				recommendDateStart:$("#s_recommendDateStart").textbox("getValue"),
				recommendDateEnd:$("#s_recommendDateEnd").textbox("getValue"),
				appStatus:$("#s_appStatus").textbox("getValue")
			});
		});
		
		//所属机构
		$('#s_dept').combotree({
			url : '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	$("#businessId").val(node.businessId);
		    	$("#orgId").val(node.orgId);
		    	$("input",$("#orgIdName").next("span")).val(node.orgName);
		    },
		    required: true
		});		
		
		//所属机构
		$('#s_recommendDept').combotree({
			url : '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	$("#businessId").val(node.businessId);
		    	$("#orgId").val(node.orgId);
		    	$("input",$("#orgIdName").next("span")).val(node.orgName);
		    },
		});
		
		$("#empName").textbox('textbox').bind("click", function () {
			flag = true;
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1200,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		});
		
		$("#recommendPersonName").textbox('textbox').bind("click", function () {
			flag = false;
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1200,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		});
		
		$('#recommendDeptNO').combotree({
			url : '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	$("#businessId").val(node.businessId);
		    	$("#orgId").val(node.orgId);
		    	$("input",$("#orgIdName").next("span")).val(node.orgName);
		    },
		    required: true
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
	$("#cadreReserveForm").form("clear");
	$('#dg').datagrid('clearSelections'); 
	$('#dlg_edit').dialog({title : "新增后备干部申请"});
	$("#dlg_edit").dialog("open");
}

function view(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$('#show_cadreReserve').form('load',{
			cadreReserveId:rows[0].cadreReserveId,
			empId:rows[0].empId,
			empName:rows[0].empName,
			recommendDate:rows[0].recommendDate,
			recommendDept:rows[0].recommendDeptName,
			recommendReason:rows[0].recommendReason,
			recommendPersonId:rows[0].recommendPersonId,
			recommendPersonName:rows[0].recommendPersonName,
			recommendJob:rows[0].recommendJob,
			recommendMaterial:rows[0].recommendMaterial,
			recommendBallot:rows[0].recommendBallot,
			failedReasonCK : rows[0].failedReason
		});
		if(rows[0].appStatus == "3"){
			document.getElementById("shenPiWTG").style.display="block";
		}else{
			document.getElementById("shenPiWTG").style.display="none";
		}
		$('#dlg_view').dialog('open');
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
		if($("#cadreReserveForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>cadreReserve/updateCadreReserve.do" ,
				data: $('#cadreReserveForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code == 0){
						$.messager.show({
							title:'提示',
							msg:'更新成功',
							timeout:3000,
							showType:'slide'
						});
						//插入修改后备干部申请日志
						insertSysLog("后备干部申请","修改","单号为"+$("#e_headId").val()+"的职称培训记录");
						$('#dg').datagrid('reload')
						$('#dlg_edit').dialog('close')
					}else if(data==1){
						$.messager.alert('错误提示','更新失败，请联系管理员');
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','更新失败，请联系管理员');
				}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
	}else{
		if($("#cadreReserveForm").form('validate')){
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>cadreReserve/addCadreReserve.do" ,
				data: $('#cadreReserveForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'新增成功',
							timeout:3000,
							showType:'slide'
						});
						$("#dg").datagrid('load', {
							dept : curDeptNo
						}); 
						$('#dlg_edit').dialog('close');
						//插入新增后备干部申请日志
						insertSysLog("后备干部申请","新增","单号为"+data.id+"的职称培训记录");
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
			$('#cadreReserveForm').form('load',{
				headId:rows[0].headId,
				detailId:rows[0].detailId,
				empName:rows[0].empName,
				recommendDate:rows[0].recommendDate,
				recommendDeptNO:rows[0].recommendDeptNO,
				recommendReason:rows[0].recommendReason,
				recommendPersonId:rows[0].recommendPersonId,
				recommendPersonName:rows[0].recommendPersonName,
				recommendJob:rows[0].recommendJob,
				recommendBallot:rows[0].recommendBallot
			});
			$('#dlg_edit').dialog('open');
		}
		
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
						url: '<%=basePath%>cadreReserve/submitCadreReserve.do?headId=' + rows[0].headId,
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
									//插入提交后备干部申请日志
									insertSysLog("后备干部申请","提交","单号为"+rows[0].headId+"的后备干部申请");
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

//删除
function del(){
	var rows = $('#dg').datagrid('getSelections');
    if(rows.length==1){
		if(rows[0].appStatus!="0"){
			$.messager.alert("操作提示", "只能删除已保存的申请单！");
			return;
		}else{
			$.messager.confirm('提示框', '你确定要删除吗?',function(r){
				if(r){
					$.ajax({
						url: '<%=basePath%>cadreReserve/deleteCadreReserve.do?headId=' + rows[0].headId,
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
									//插入删除后备干部申请日志
									insertSysLog("后备干部申请","删除","单号为"+rows[0].headId+"的职称申请单");
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

function denyApprove(){
	var rows = $('#dg').datagrid('getSelections');
		if($("#approve_cadreReserve").form('validate')){
			$.messager.confirm('提示框', '你确定要不予审批吗?',function(r){
			if(r){	
				var applyId = rows[0].headId;
				$.ajax({  
					type: 'POST',
					url: "<%=basePath%>cadreReserve/denyCadreReserve.do?applyId=" +applyId,
					data: $('#approve_cadreReserve').serialize(),
					dataType: 'json',
					success: function(data){
						if(data.code==0){
							$.messager.show({
								title:'提示',
								msg:'不予审批成功',
								timeout:3000,
								showType:'slide'
							});
							//插不予入审批后备干部日志
							insertSysLog("后备干部申请","不予审批","单号为"+applyId+"的后备干部申请");
							$("#dg").datagrid('load', {
								dept : curDeptNo
							}); 
							$('#dlg_approve').dialog('close')
						}else{
							$.messager.alert('不予审批发生错误，请于管理员联系');
						}
					} ,
					error: function(){
						$.messager.alert('不予审批发生错误，请于管理员联系');
					}
				});
			}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
}



function approve(){
	$("#approve_cadreReserve").form("clear");
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){ 
		if(rows[0].appStatus!="1"){
			$.messager.alert("操作提示", "只能审批已提交的申请单！");
			return;
		}else{
			$('#approve_cadreReserve').form('load',{
				headId:rows[0].headId,
				empId:rows[0].empId,
				empName:rows[0].empName,
				recommendDate:rows[0].recommendDate,
				recommendDeptName:rows[0].recommendDeptName,
				recommendReason:rows[0].recommendReason,
				recommendPersonId:rows[0].recommendPersonId,
				recommendPersonName:rows[0].recommendPersonName,
				recommendJob:rows[0].recommendJob,
				recommendBallot:rows[0].recommendBallot
			});
			$('#dlg_approve').dialog('open');
		}
		
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//审批
function agreeApprove(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$.messager.confirm('提示框', '你确定要审批吗?',function(r){
			if(r){	
				var applyId = rows[0].headId;
				$.ajax({  
					type: 'POST',
					url: "<%=basePath%>cadreReserve/approvCadreReserve.do?applyId=" +applyId ,    
		     		dataType: 'json',
					success: function(data){
						if(data.code==0){
							$.messager.show({
								title:'提示',
								msg:'审批成功',
								timeout:3000,
								showType:'slide'
							});
							//插入审批后备干部日志
							insertSysLog("后备干部申请","审批","单号为"+applyId+"的后备干部申请");
							$("#dg").datagrid('load', {
								dept : curDeptNo
							}); 
							$("#dlg_approve").dialog('close');
						}else{
							$.messager.alert('系统发生错误，请于管理员联系');
						}
					} ,
					error: function(){
						$.messager.alert('系统发生错误，请于管理员联系');
					}
				})
			}
		})
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//更新申请人信息
function updateApplyPerson(empId,empName) {
	if(flag){
		$("#empId").val(empId);
		$("#empName").textbox("setValue", empName);
	}else{
		$("#recommendPersonId").val(empId);
		$("#recommendPersonName").textbox("setValue", empName);
	}
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
		<div data-options="region:'center',title:'后备干部申请管理',iconCls:'icon-ok'">
			<div id="sear" style="text-align:left;padding:5px 0 5px 15px;height:65px">
				<form action="" id="searchForm">
					<input  id="s_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px"/>
					<!-- <select class="easyui-combotree"  id="s_dept" name="dept" label="所在部门:" style="width:240px" >
					</select> -->
					</select>
						<select  class="easyui-combobox" id="s_appStatus" name="appStatus" data-options="label:'申请状态:',editable:false"  labelPosition="right" style="width:250px" >
						<option value="">--请选择--</option>
						<option value="0">已保存</option>
						<option value="1">已提交</option>
						<option value="2">已审批</option>
						<option value="3">未通过</option>
					</select>
					<input  id="s_recommendPersonName"  name="recommendPersonName" class="easyui-textbox" data-options="label:'推荐人:'" style="width:250px"/><br />
					<select class="easyui-combotree"  id="s_recommendDept" name="recommendDept" label="推荐单位:"  style="width:250px" />
					<input  id="s_recommendDateStart"  name="recommendDateStart" class="easyui-datebox" data-options="label:'推荐时间:'" style="width:250px"/>~
					<input  id="s_recommendDateEnd"  name="recommendDateEnd" class="easyui-datebox" data-options="" style="width:150px"/>
					<a  name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
					<a  name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
				 </form>
			</div>
		<!-- 查询表单模块 start -->
		
			<!-- 列表模块 start -->
			<div data-options="region:'center',border:false" style="text-align:left;padding:5px 0 0 5px;">
				<table id="dg" style="display:block;width:100%;padding:5px 0 0 5px;margin:5px 5px 0 0;"></table>  
			</div>
			<!-- 列表模块end -->
		</div>
	</div>
	
<div id="dlg_edit" class="easyui-dialog" closed="true" title="后备干部信息" style="width: 600px; height: 240px; padding: 10px" data-options="modal:true,buttons: '#dlg-buttons'">
	<form action="" id="cadreReserveForm" name="cadreReserveForm">
		    <input type="hidden" name="headId" id="e_headId" />
		    <input type="hidden" name="detailId" id="detailId" />
		    <input type="hidden" name="empId" id="empId" />
		    <input type="hidden" name="appStatus" id="appStatus" />
		    <input type="hidden" name="recommendPersonId" id="recommendPersonId" />
		    <input  id="empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:',required:true" style="width:250px"/><span style="color:red;">*</span>
		    <select class="easyui-combotree"  id="recommendDeptNO" name="recommendDeptNO" label="推荐部门:" style="width:250px" >
			</select>
			<div style="height:5px"></div>
		   	<input  id="recommendDate"  name="recommendDate" class="easyui-datebox" data-options="label:'推荐时间:'" style="width:250px" /><span style="color:white;">*</span>
  		    <input  id="recommendPersonName"  name="recommendPersonName" class="easyui-textbox" data-options="label:'推荐人:'" style="width:250px"/>
  		    <div style="height:5px"></div>
		    <input  id="recommendJob"  name="recommendJob" class="easyui-textbox" data-options="label:'推荐职务:'" style="width:250px"/><span style="color:white;">*</span>
     	    <input  id="recommendBallot"  name="recommendBallot" class="easyui-textbox" data-options="label:'推荐票数:'" style="width:250px"/>
     	    <div style="height:5px"></div>
   		    <input  id="recommendReason"  name="recommendReason" class="easyui-textbox" data-options="label:'推荐原因:'" style="width:530px"/>
<!-- 		    <input  id="recommendMaterial"  name="recommendMaterial" class="easyui-textbox" data-options="label:'推荐材料:'" style="width:250px"/><br /><br />-->
	</form>
</div>

<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'"onclick="showSubmit()" >保存</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a> -->
</div>

<div id="dlg_view" class="easyui-dialog" closed="true" title="查看后备干部信息" style="width: 600px; height: 240px; padding: 10px" data-options="modal:true,buttons: '#dlgView-buttons'">
	<form action="" id="show_cadreReserve" name="show_cadreReserve">
		    <input type="hidden" name="headId" id="v_headId" />
		    <input type="hidden" name="detailId" id="v_detailId" />
		    <input type="hidden" name="empId" id="v_empId" />
		    <input type="hidden" name="recommendPersonId" id="v_recommendPersonId" />
		   	<input  id="v_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px" readonly="readonly"/>
		   	<input  id="v_recommendDate"  name="recommendDate" class="easyui-datebox" data-options="label:'推荐时间:'" style="width:250px" readonly="readonly"/>
		   	<div style="height:5px"></div>
		    <input  id="v_recommendDept"  name="recommendDept" class="easyui-textbox" data-options="label:'推荐单位:'" style="width:250px" readonly="readonly"/>
		    <input  id="v_recommendPersonName"  name="recommendPersonName" class="easyui-textbox" data-options="label:'推荐人:'" style="width:250px" readonly="readonly"/>
		    <div style="height:5px"></div>
		    <input  id="v_recommendJob"  name="recommendJob" class="easyui-textbox" data-options="label:'推荐职务:'" style="width:250px" readonly="readonly"/>
<!-- 		    <input  id="v_recommendMaterial"  name="recommendMaterial" class="easyui-textbox" data-options="label:'推荐材料:'" style="width:250px" readonly="readonly"/><br /><br /> -->
 		    <input  id="v_recommendBallot"  name="recommendBallot" class="easyui-textbox" data-options="label:'推荐票数:'" style="width:250px" readonly="readonly"/>
 			<div style="height:5px"></div>
		    <input  id="v_recommendReason"  name="recommendReason" class="easyui-textbox" data-options="label:'推荐原因:'" style="width:520px" readonly="readonly"/>
			<div id="shenPiWTG" style="height:5px;display:none;">
				<input id="failedReasonCK"  name="failedReasonCK" class="easyui-textbox" data-options="label:'未通过原因:'" style="width:520px;" readonly="readonly"/>
			</div>
	</form>
</div>
<div id="dlgView-buttons">
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_view').dialog('close')">关闭</a> -->
</div>

<div id="dlg_approve" class="easyui-dialog" closed="true" title="审批后备干部申请" style="width: 600px; height: 300px; padding: 10px" data-options="modal:true,buttons: '#dlgApprove-buttons'">
	<form action="" id="approve_cadreReserve" name="approve_cadreReserve">
		    <input type="hidden" name="headId" id="v_headId" />
		    <input type="hidden" name="empId" id="v_empId" />
		    <input type="hidden" name="recommendPersonId" id="v_recommendPersonId" />
		   	<input  id="v_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px" readonly="readonly"/>
		   	<input  id="v_recommendDate"  name="recommendDate" class="easyui-datebox" data-options="label:'推荐时间:'" style="width:250px" readonly="readonly"/>
		   	<div style="height:5px"></div>
		    <input  id="v_recommendDeptName"  name="recommendDeptName" class="easyui-textbox" data-options="label:'推荐单位:'" style="width:250px" readonly="readonly"/>
		    <input  id="v_recommendPersonName"  name="recommendPersonName" class="easyui-textbox" data-options="label:'推荐人:'" style="width:250px" readonly="readonly"/>
		    <div style="height:5px"></div>
		    <input  id="v_recommendJob"  name="recommendJob" class="easyui-textbox" data-options="label:'推荐职务:'" style="width:250px" readonly="readonly"/>
<!-- 		    <input  id="v_recommendMaterial"  name="recommendMaterial" class="easyui-textbox" data-options="label:'推荐材料:'" style="width:250px" readonly="readonly"/><br /><br />
 -->		    <input  id="v_recommendBallot"  name="recommendBallot" class="easyui-textbox" data-options="label:'推荐票数:'" style="width:250px" readonly="readonly"/>
 			<div style="height:5px"></div>
		    <input  id="v_recommendReason"  name="recommendReason" class="easyui-textbox" data-options="label:'推荐原因:'" style="width:550px" readonly="readonly"/>
		    <div style="height:5px"></div>
		    <input  id="failedReason"  name="failedReason" class="easyui-textbox" data-options="label:'未通过原因:'" style="width:550px" />
		    <div style="color:red;margin-left: 80px;margin-top: 10px;">（注：如不予审批请录入未通过原因）</div>
	</form>
</div>
<div id="dlgApprove-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="agreeApprove()">审批通过</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="denyApprove()">不予审批</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_approve').dialog('close')">关闭</a>-->
</div>
</body>
</html>
