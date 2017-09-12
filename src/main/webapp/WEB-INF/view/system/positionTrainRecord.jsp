<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职称培训记录</title>
<%@include file="/common/common_easyui.jsp" %>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//按钮权限显示
var objArrayYM = new Array("add","edit","view","del"); 
var curDeptNo;
$(function(){
	showObj(objArrayYM);//共同方法
	var $zTreeMenuBusiness = $('#zTreeMenuBusiness');

	// 用户表格数据
	var datagrid = $("#dg").datagrid({  
	      url:"<%=basePath%>positionTrainRecord/searchPositionTrainRecord.do",
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
			},{
				id:'edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				}
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
			} ],
			columns : [ [ //每个列具体内容  
			{field : 'ck',checkbox : true}, 
			{field : 'positionTrainId',title : 'positionTrainId',width : 150,hidden:true},
     		{field:'empId',title:'empId',width:140,hidden:true},
			{field:'empName',title:'姓名',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
     		},
			/* {field:'stationName',title:'岗位',width:140},     
			{field:'job',title:'职务',width:140},  */   
			{field:'trainContent',title:'培训内容',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},     
			{field:'trainDept',title:'培训部门',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},
			{field:'trainScore',title:'培训成绩',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},    
			{field:'trainDate',title:'培训日期',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
					}
				}
			}
			] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 500
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
			$("#dg").datagrid('load', {
				deptNO : curDeptNo,
				empName:$("#s_empName").textbox("getValue"),
				trainDateStart:$("#s_trainDateStart").textbox("getValue"),
				trainDateEnd:$("#s_trainDateEnd").textbox("getValue")
			});
		});

		//重置
		$("#reset").click(function() {
			$("#searchForm").form('clear');
			$("#dg").datagrid('load', {
				deptNO : curDeptNo,
				empName:$("#s_empName").textbox("getValue"),
				trainDateStart:$("#s_trainDateStart").textbox("getValue"),
				trainDateEnd:$("#s_trainDateEnd").textbox("getValue")
			});
		});
        
		$("#empName").textbox('textbox').bind("click", function () {
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1200,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
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
					deptNO : node.businessId  
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
	$("#positionTrainRecord").form("clear");
	$('#dlg_edit').dialog({title : "新增职称培训记录"});
	$('#dg').datagrid('clearSelections'); 
	$("#dlg_edit").dialog("open");
}

//增加弹窗提交
function showSubmit(){
	//判断是新增还是更新
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if($("#positionTrainRecord").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>positionTrainRecord/updatePositionTrainRecord.do" ,
				data: $('#positionTrainRecord').serialize(),
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
						insertSysLog("职称培训记录","修改","单号为"+$("#e_positionTrainId").val()+"的职称培训记录");
						$('#dg').datagrid('reload')
						$('#dlg_edit').dialog('close')
					}else if(data==1){
						$.messager.alert('错误提示','系统发生错误，请联系管理员');
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','系统发生错误，请联系管理员');
				}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
	}else{
		if($("#positionTrainRecord").form('validate')){
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>positionTrainRecord/insertPositionTrainRecord.do" ,
				data: $('#positionTrainRecord').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'新增成功',
							timeout:3000,
							showType:'slide'
						});
						//插入新增职称培训记录日志
						insertSysLog("职称培训记录","新增","单号为"+data.id+"的职称培训记录");
						$('#dg').datagrid('reload')
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
		$('#positionTrainRecord').form('load',{
			positionTrainId:rows[0].positionTrainId,
			empId:rows[0].empId,
			empName:rows[0].empName,
			trainDate:rows[0].trainDate,
			trainContent:rows[0].trainContent,
			trainDept:rows[0].trainDept,
			trainTime:rows[0].trainTime,
			trainInfo:rows[0].trainInfo,
			trainScore:rows[0].trainScore,
			trainResult:rows[0].trainResult,
			job:rows[0].job
		});
		$('#dlg_edit').dialog({title : "修改职称培训记录"});
		$('#dlg_edit').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}
//删除
function del(){
	var rows = $('#dg').datagrid('getSelections');
    var positionTrainId="";
    for(var i=0;i<rows.length;i++){
    	positionTrainId +=rows[i].positionTrainId + ",";
     //获取选中节点的值
    }
    //去掉结尾逗号
    positionTrainId = positionTrainId.substring(0,positionTrainId.length-1);
	if(rows.length>0){
		$.messager.confirm('提示框', '你确定要删除吗?',function(r){
			if(r){
				$.ajax({
					url: '<%=basePath%>positionTrainRecord/deletePositionTrainRecord.do?id=' + positionTrainId,
							success:function(){
								$('#dg').datagrid('reload')
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入删除干部考核日志
								insertSysLog("职称培训记录","删除","单号为"+positionTrainId+"的职称培训记录");
							}
				});
			}
		})
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

function view(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$('#show_form').form('load',{
			positionTrainId:rows[0].positionTrainId,
			empId:rows[0].empId,
			empName:rows[0].empName,
			trainDate:rows[0].trainDate,
			trainContent:rows[0].trainContent,
			trainDept:rows[0].trainDept,
			trainTime:rows[0].trainTime,
			trainInfo:rows[0].trainInfo,
			trainScore:rows[0].trainScore,
			trainResult:rows[0].trainResult,
			job:rows[0].job
		});
		$('#dlg_show').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//更新申请人信息
function updateApplyPerson(empId,empName) {
    $("#empId").val(empId);
	$("#empName").textbox("setValue", empName); 
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
		<div data-options="region:'center',title:'职称培训记录',iconCls:'icon-ok'">
			<div style="text-align:left;padding:5px 0 5px 15px;height:36px" id="sear">
				<form action="" id="searchForm">
					<input  id="s_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px"/>
<!-- 					<select class="easyui-combotree"  id="s_dept" name="dept" label="所在部门:" style="width:340px" > -->
<!-- 					</select><br /> -->
					<input  id="s_trainDateStart"  name="trainDateStart" class="easyui-datebox" data-options="label:'培训时间:'" style="width:250px"/>至
					<input  id="s_trainDateEnd"  name="trainDateEnd" class="easyui-datebox" data-options="" style="width:200px"/>
					<a name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
					<a name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
				 </form>
			</div>
			<div>
				<table id="dg" style="display:block;width:100%;padding:5px 0 0 5px;margin:5px 10px 0 0;"></table>  
			</div>
			</div>
	</div>
	   		
   		
<div id="dlg_edit" class="easyui-dialog" closed="true" title="职称培训记录" style="width: 600px; height: 280px; padding: 10px"data-options="modal:true,buttons: '#dlg-buttons'">
	<form action="" id="positionTrainRecord" name="positionTrainRecord">
		    <input type="hidden" name="positionTrainId" id="e_positionTrainId" />
 		    <input type="hidden" name="empId" id="empId" />
		    <input  id="empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:',required:true" style="width:250px"/><span style="color:red;">*</span>
		    <input  id="trainDate"  name="trainDate" class="easyui-datebox" data-options="label:'培训日期:'" style="width:250px" />
			<div style="height:5px"></div>	
			<input  id="trainScore"  name="trainScore" class="easyui-textbox" data-options="label:'培训成绩:'" style="width:250px"/><span style="color:white;">*</span>
			<input  id="trainDept" name="trainDept" class="easyui-textbox" data-options="label:'培训部门:'" style="width:250px"/>
			<div style="height:5px"></div>
			<input  id="trainTime" name="trainTime" class="easyui-textbox" data-options="label:'培训学时:'" style="width:250px"/><span style="color:white;">*</span>
			<select  class="easyui-combobox" id="trainInfo" name="trainInfo" data-options="editable:false,panelHeight:'auto',label:'培训方式:'"  labelPosition="right" style="width:250px" >
						<option value="">--请选择--</option>
						<option value="脱产">脱产</option>
						<option value="半脱产">半脱产</option>
						<option value="业余">业余</option>
						<option value="其他">其他</option>
					</select><div style="height:5px"></div>
			<input  id="trainContent" name="trainContent" class="easyui-textbox" data-options="label:'培训内容:'" style="width:520px"/>
			<div style="height:5px"></div>
			<input  id="trainResult"  name="trainResult" class="easyui-textbox" data-options="label:'培训结果:'" style="width:520px"/>
	</form>
</div>
<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">提交</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
 --></div>

<div id="dlg_show" class="easyui-dialog" closed="true" title="查看干部考核信息" style="width: 600px; height: 250px; padding: 0;margin:0;"data-options="modal:true,buttons: '#dlgShow-buttons'">
 	<form action="" id="show_form" style="background-color:#ECF6FA">
		   <input type="hidden" name="positionTrainId" id="positionTrainId" />
 		    <input type="hidden" name="empId" id="empId" />
		    <input  id="empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px" readonly="readonly"/>
		    <input  id="trainDate"  name="trainDate" class="easyui-datebox" data-options="label:'培训日期:'" style="width:250px" readonly="readonly"/>
		    <div style="height:5px"></div>
			<input  id="trainContent" name="trainContent" class="easyui-textbox" data-options="label:'培训内容:'" style="width:250px" readonly="readonly"/>
			<input  id="trainDept" name="trainDept" class="easyui-textbox" data-options="label:'培训部门:'" style="width:250px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="trainInfo" name="trainInfo" class="easyui-textbox" data-options="label:'培训部门:'" style="width:250px" readonly="readonly"/>
			<input  id="trainTime" name="trainTime" class="easyui-textbox" data-options="label:'培训学时:'" style="width:250px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="trainScore"  name="trainScore" class="easyui-textbox" data-options="label:'培训成绩:'" style="width:500px" readonly="readonly"/>
			<div style="height:5px"></div>
			<input  id="trainResult"  name="trainResult" class="easyui-textbox" data-options="label:'培训结果:'" style="width:500px" readonly="readonly"/><br /><br />
    </form>
</div>

<div id="dlgShow-buttons">
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_show').dialog('close')">关闭</a>
 --></div>
	
</body>
</html>
