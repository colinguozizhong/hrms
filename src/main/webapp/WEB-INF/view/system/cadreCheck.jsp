<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>干部考核</title>
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
	      url:"<%=basePath%>cadreCheck/searchCadreCheckList.do",
	      pagination:true,//显示分页  
	      rownumbers:true,//显示行号
	      pageSize:20,//分页大小  
	      pageList:[20,40,60,80],//每页的个数  
// 	      fit:true,//自动补全  
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
			}, {
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
			}, {
				id:'del',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} ],
			columns : [ [ //每个列具体内容  
			{field : 'ck',checkbox : true}, 
			{field : 'cadreCheckId',title : '干部考核号',width : 150,hidden:true},
     		{field:'empId',title:'',width:140,hidden:true},
     		{field:'checkPerId',title:'',width:140,hidden:true},
     		{field:'empName',title:'姓名',width:140,
     			formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
			},
     		{field:'checkPerName',title:'考核人',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
     		},
			{field:'checkContent',title:'考核内容',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
			},
			{field:'checkScore',title:'总得分',width:140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
			},
			{field:'checkDate',title:'考核时间',width:140,
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
			height : 500,
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
			$("#dg").datagrid('load', {
				deptNO : curDeptNo,
				empName : $("#s_empName").textbox("getValue"),
				checkDateStart : $("#s_checkDateStart").textbox("getValue"),
				checkDateEnd : $("#s_checkDateEnd").textbox("getValue")
			});
		});

		//重置
		$("#reset").click(function() {
			$("#searchForm").form('clear');
			$("#dg").datagrid('load', {
				deptNO : curDeptNo,
				empName : $("#s_empName").textbox("getValue"),
				checkDateStart : $("#s_checkDateStart").textbox("getValue"),
				checkDateEnd : $("#s_checkDateEnd").textbox("getValue")
			});
		});
		
		$("#empName").textbox('textbox').bind("click", function () {
			flag = true;
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1200,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		});
		
		$("#checkPerName").textbox('textbox').bind("click", function () {
			flag = false;
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
	$("#cadreCheckForm").form("clear");
	$('#dlg_edit').dialog({title : "新增干部考核信息"});
	$('#dg').datagrid('clearSelections'); 
	$("#dlg_edit").dialog("open");
}

//增加弹窗提交
function showSubmit(){
	//判断是新增还是更新
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if($("#cadreCheckForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>cadreCheck/updateCadreCheck.do" ,
				data: $('#cadreCheckForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code == 0){
						$.messager.show({
							title:'提示',
							msg:'更新成功',
							timeout:3000,
							showType:'slide'
						});
						//插入修改干部考核日志
						insertSysLog("干部考核","修改","单号为"+$("#cadreCheckId").val()+"的考核记录单");
						$("#dg").datagrid('load', {
							deptNO : curDeptNo
						}); 
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
		if($("#cadreCheckForm").form('validate')){
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>cadreCheck/addCadreCheck.do" ,
				data: $('#cadreCheckForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'新增成功',
							timeout:3000,
							showType:'slide'
						});
						//插入新增干部考核日志
						insertSysLog("干部考核","新增","单号为"+data.id+"的考核记录单");
						$("#dg").datagrid('load', {
							deptNO : curDeptNo
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
		$('#cadreCheckForm').form('load',{
			cadreCheckId:rows[0].cadreCheckId,
			empId:rows[0].empId,
			empName:rows[0].empName,
			stationId:rows[0].stationName,
			job:rows[0].job,
			checkContent:rows[0].checkContent,
			checkResult:rows[0].checkResult,
			checkComment:rows[0].checkComment,
			checkScore:rows[0].checkScore,
			checkPerId : rows[0].checkPerId,
			checkPerName:rows[0].checkPerName,
			checkDate:rows[0].checkDate
		});
		$('#dlg_edit').dialog({title : "修改干部考核信息"});
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
    var cadreCheckId="";
    for(var i=0;i<rows.length;i++){
    	cadreCheckId +=rows[i].cadreCheckId + ",";
     //获取选中节点的值
    }
    //去掉结尾逗号
    cadreCheckId = cadreCheckId.substring(0,cadreCheckId.length-1);
	if(rows.length>0){
		$.messager.confirm('提示框', '你确定要删除吗?',function(r){
			if(r){
				$.ajax({
					url: '<%=basePath%>cadreCheck/deleteCadreCheck.do?id=' + cadreCheckId,
							success:function(){
								$("#dg").datagrid('load', {
									deptNO : curDeptNo
								}); 
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入删除干部考核日志
								insertSysLog("干部考核","删除","单号为"+cadreCheckId+"的记录单");
							}
				});
			}
		})
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

function view(rowIndex){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1||rowIndex){
		$('#show_form').form('load',{
			cadreCheckId:rows[0].cadreCheckId,
			empId:rows[0].empId,
			empName:rows[0].empName,
			stationId:rows[0].stationName,
			job:rows[0].job,
			checkContent:rows[0].checkContent,
			checkResult:rows[0].checkResult,
			checkComment:rows[0].checkComment,
			checkScore:rows[0].checkScore,
			checkPerId:rows[0].checkPerId,
			checkPerName:rows[0].checkPerName,
			checkDate:rows[0].checkDate
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
	if(flag){
		$("#empId").val(empId);
		$("#empName").textbox("setValue", empName);
		//跟带人员岗位和职务
		$.ajax({  
			type: 'POST',
			url: "<%=basePath%>sysEmp/loadPosAndSta.do",    
			data: {"id":empId},
			dataType: 'json',
			success: function(data){
				$("#job").textbox("setValue", data.oldJob);
				$("#stationId").textbox("setValue", data.oldStationName);
			} ,
			error: function(){
				$.messager.alert('系统发生错误，请于管理员联系');
			}
		});
	}else{
		$("#checkPerId").val(empId);
		$("#checkPerName").textbox("setValue", empName);
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
	<div data-options="region:'center',title:'干部考核',iconCls:'icon-ok'">
	
		<div style="text-align:left;padding:5px 0 5px 15px;height:20px" id="sear">
			<div class="cadre_check">
				<div id="sear"  data-options="region:'north',border:false" style="text-align:left;padding:5px 0 0 5px;">
					<form action="" id="searchForm">
						<input  id="s_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:'" style="width:250px"/>
						<input  id="s_checkDateStart"  name="checkDateStart" class="easyui-datebox" data-options="editable:false,label:'考核时间:'" style="width:250px"/>至
						<input  id="s_checkDateEnd"  name="checkDateEnd" class="easyui-datebox" data-options="editable:false," style="width:160px"/>
						<a  name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
						<a  name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
					 </form>
				</div>
				<!-- 列表模块 start -->
				<div data-options="region:'center',border:false" style="text-align:left;padding:5px 0 0 5px;">
					<table id="dg" style="display:block;width:100%"></table> 
				</div>
				<!-- 列表模块end -->
		   </div>
	
	
    	</div> 
    </div>
    </div>
    <!-- 查询表单模块 end -->
   	 <!-- 新增/编辑单模块 start -->	
<div id="dlg_edit" class="easyui-dialog" closed="true" title="干部考核" style="width: 620px; height: 320px; padding: 10px"data-options="modal:true,buttons: '#dlg-buttons'">
	<form action="" id="cadreCheckForm" name="cadreCheckForm">
		    <input type="hidden" name="cadreCheckId" id="cadreCheckId" />
		    <input type="hidden" name="empId" id="empId" />
		    <input type="hidden" name="checkPerId" id="checkPerId" />
		    <input  id="empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:',required:true" style="width:250px"/><span style="color:red;">*</span>
		    <select  class="easyui-combobox" id="stationId" name="stationId"  labelPosition="right" style="width:250px"
				data-options="label:'现岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly" >
			</select>
   			<div style="height:5px"></div>
			<input  id="job" name="job" class="easyui-textbox" data-options="label:'现职务:'" style="width:250px" readonly="readonly"/><span style="color:white;">*</span>
			<input  id="checkScore"  name="checkScore" class="easyui-textbox" data-options="label:'总得分:',required:true" style="width:250px"/><span style="color:red;">*</span>
   			<div style="height:5px"></div>
			<input  id="checkPerName"  name="checkPerName" class="easyui-textbox" data-options="label:'考核人:'" style="width:250px"/><span style="color:white;">*</span>
			<input  id="checkDate"  name="checkDate" class="easyui-datebox" data-options="label:'考核时间:',required:true" style="width:250px"/><span style="color:red;">*</span>
   			<div style="height:5px"></div>
			<input  id="checkContent" name="checkContent" class="easyui-textbox" data-options="label:'考核内容:'" style="width:530px"/>
   			<div style="height:5px"></div>
			<input  id="checkResult" name="checkResult" class="easyui-textbox" data-options="label:'考核结果:',required:true" style="width:530px"/><span style="color:red;">*</span>
   			<div style="height:5px"></div>
			<input  id="checkComment" name="checkComment" class="easyui-textbox" data-options="label:'考核评语:'" style="width:530px"/>
	</form>
</div>
<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save'" onclick="showSubmit()">提交</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a> -->
</div>
    <!-- 新增/编辑单模块 end -->
    <!-- 查看单模块 start -->
<div id="dlg_show" class="easyui-dialog" closed="true" title="查看干部考核信息" style="width: 550px; height: 280px; padding: 0;margin:0;"data-options="modal:true,buttons: '#dlgShow-buttons'">
 	<form action="" id="show_form" style="background-color:#ECF6FA">
		    <input type="hidden" name="cadreCheckId" id="v_cadreCheckId" />
		    <input type="hidden" name="empId" id="v_empId" />
		    <input type="hidden" name="checkPerId" id="v_checkPerId" />
		    <input  id="v_empName"  name="empName" class="easyui-textbox" data-options="label:'姓名:',required:true" style="width:250px" readonly="readonly"/>
		    <input  id="v_stationId"  name="stationId" class="easyui-textbox" data-options="label:'岗位:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_job" name="job" class="easyui-textbox" data-options="label:'职务:'" style="width:250px" readonly="readonly"/>
			<input  id="v_checkScore"  name="checkScore" class="easyui-textbox" data-options="label:'总得分:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_checkPerName"  name="checkPerName" class="easyui-textbox" data-options="label:'考核人:'" style="width:250px" readonly="readonly"/>
			<input  id="v_checkDate"  name="checkDate" class="easyui-datebox" data-options="label:'考核时间:'" style="width:250px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_checkContent" name="checkContent" class="easyui-textbox" data-options="label:'考核内容:'" style="width:500px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_checkResult" name="checkResult" class="easyui-textbox" data-options="label:'考核结果:'" style="width:500px" readonly="readonly"/>
   			<div style="height:5px"></div>
			<input  id="v_checkComment" name="checkComment" class="easyui-textbox" data-options="label:'考核评语:'" style="width:500px" readonly="readonly"/>
    </form>
</div>
   <!-- 查看单模块 end -->
<div id="dlgShow-buttons">
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_show').dialog('close')">关闭</a> -->
</div>
	
</body>
</html>
