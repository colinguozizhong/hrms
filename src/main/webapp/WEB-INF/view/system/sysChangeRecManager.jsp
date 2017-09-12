<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员异动信息管理</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:600px;" data-options="fit:true">
		<!-- 部门列表模块 start -->
		<div data-options="region:'west',iconCls:'icon-ok',split:true" title="部门列表" style="width:20%;">
	  		<div >
				<ul id="zTreeMenuBusiness" class="easyui-tree">
				</ul>
			</div>
	  	</div>
	  	<!-- 部门列表模块 end -->
	  	
	  	<div data-options="region:'center',title:'人员异动申请',iconCls:'icon-ok'">
			<!-- 查询表单模块 start -->
			<div id="search" data-options="region:'north',border:false" style="text-align:left;padding:5px 0 5px 15px;height:65px">
				<form action="" id="searchForm">
					<input id="s_name" name="name" class="easyui-textbox"
						data-options="label:'员工姓名:'" style="width: 250px" /> 
					<!-- <input class="easyui-combotree"  id="deptNOSec" name="deptNO" label="所在部门:" style="width:240px" > -->
					</input>
					<select class="easyui-combobox"  id="s_status" name="status" data-options="editable:false,panelHeight:'auto',label:'申请状态'" style="width:250px" >
						<option value="">--请选择--</option>
						<option value="0">已保存</option>
						<option value="1">已提交</option>
						<option value="2">已审批</option>
					</select>
					<br/>
					<select class="easyui-combobox"  id="s_typeId" name="typeId" data-options="label:'异动类型:',valueField:'typeId',textField:'typeName',url:'<%=basePath%>sysChangeType/all.do'" style="width:250px" >
					</select>
					<input id="changeDateStart" name="changeDateStart" label="异动时间:" class="easyui-datebox" style="width: 250px" />
						~
					<input id="changeDateEnd" name="changeDateEnd" class="easyui-datebox" style="width: 150px" />
					<a name="doSearch" id="doSearch"class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 68px">查询</a>
					<a name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 68px;margin-left:5px;">重置</a>
				</form>
			</div>
			<!-- 查询表单模块 end -->
			<!-- 列表模块 start -->
			<div id="dg" style="display:block;width:100%"></div>  
			<!-- 列表模块 end -->
		</div>
	</div>
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" data-options="modal:true,buttons: '#dlg-buttons'" closed="true" title="新增异动信息" style="width:600px;height: 360px; padding: 10px">
		<form action="" id="sysChangeRecForm" name="changeRec">
			<!-- 异动前的信息模块 start -->
		    <input type="hidden" name="empId" id="empId" /> 
   		    <input type="hidden" name="headId" id="headId" /> 
   		    <input type="hidden" name="detailId" id="detailId" /> 
   		    <input type="hidden" name="orgId" id="orgId" /> 
		    <input type="hidden" name="oldDeptNo" id="oldDeptNo" />
   		    <input type="hidden" name="oldStationId" id="oldStationId" />
		    <input type="hidden" name="oldStationGrade" id="oldStationGrade" />
			<input type="hidden" name="newStationGrade" id="newStationGrade" />
			<input type="hidden" name="info1" id="info1" /> 
		    <input type="hidden" name="info2" id="info2" />
		    <input type="hidden" name="info3" id="info3" />
   		    <input type="hidden" name="info4" id="info4" />
   		    <input type="hidden" name="info5" id="info5" />
		    <input type="hidden" name="info6" id="info6" />
			<input  id="editName"  name="name" class="easyui-textbox" data-options="events:{click:applyPer},label:'申请人:',required:true" style="width:240px"/><span style="color:red;">*</span>
			<select class="easyui-combobox" id="oldStationName" name="oldStationName" labelPosition="right" style="width:240px"
				data-options="label:'岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
			</select><br />
			<select class="easyui-combobox" id="sex" name="sex" data-options="label:'性别:'"  labelPosition="right" style="width:240px" readonly="readonly">
				<option value="">--请选择--</option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select><span style="color:white;">*</span>
			<input id="job" name="oldJob" class="easyui-textbox" data-options="label:'职务:'" style="width:240px" readonly="readonly"/><br />
			<input id="entryDate" name="entryDate" class="easyui-datebox" data-options="label:'入职时间:'" style="width:240px" readonly="readonly"/><span style="color:white;">*</span>
			<select  class="easyui-combobox" id="oldPosition" name="oldPosition" labelPosition="right" style="width:240px"
					data-options="label:'职称:',valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'" readonly="readonly">
					</select>
			<input id="deptName" name="deptName" class="easyui-textbox" data-options="label:'部门:'" style="width:240px" readonly="readonly"/>
			<!-- 异动前的信息模块 end -->
			
			<hr />
			
			<!-- 异动后的信息模块 start -->
			<select class="easyui-combobox" id="typeId" name="typeId" labelPosition="right" style="width:240px"
				data-options="label:'异动类型:',valueField:'typeId',textField:'typeName',url:'<%=basePath%>sysChangeType/all.do'" required="required">
			</select><span style="color:red;">*</span>
			<input id="changeDate" name="changeDate" class="easyui-datebox" data-options="label:'异动时间:'" style="width:240px" required="required"/><span style="color:red;">*</span><br />
			<input id="changeReason" name="changeReason" class="easyui-textbox" data-options="label:'异动原因:'" style="width:500px" />
		
			<div id="01" style="display:none;">
					<select class="easyui-combobox" id="newStationId01" name="newStationId" labelPosition="right" style="width:240px"
						data-options="label:'异动后岗位:',editable:false,valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
					</select>
				<input  id="newJob01" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" /><br />
			</div>
			<div id="02" style="display:none;">
				<input id="retireLevel" name="retireLevel" class="easyui-textbox" data-options="label:'退休级别:'" style="width:240px" />
				<input id="retirePay" name="retirePay" class="easyui-textbox" data-options="label:'享受待遇:'" style="width:240px" /><br />
			</div>
			<div id="03" style="display:none;">
				预计返岗时间:<input id="returnDate" name="returnDate" class="easyui-datebox" data-options="label:''" style="width:140px" /><br />
			</div>
			<div id="04" style="display:none;">
				<select class="easyui-combobox" id="newStationId04" name="newStationId" labelPosition="right" style="width:240px"
						data-options="label:'异动后岗位:',valueField:'id',editable:false,textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
					</select><br />
				<input  id="newJob04" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" />
				<input id="newDeptNo" name="newDeptNo" class="easyui-textbox" data-options="label:'异动后部门:'" style="width:240px" />
			</div>
			<div id="05" style="display:none;">
				<input id="transferUnit" name="transferUnit" class="easyui-textbox" data-options="label:'调入单位:'" style="width:240px" /><br />
			</div>
			<!-- 异动后的信息模块 end -->
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">保存</a>
	</div>
	<!-- 编辑窗口模块 end -->
	<!-- 查看窗口模块 start -->
	<div id="dlg_view" class="easyui-dialog" data-options="modal:true" closed="true" title="查看异动申请单信息" style="width:600px;height: 300px; padding: 10px">
		<form action="" id="sysChangeRecViewForm" name="changeRec">
			<!-- 异动前的信息模块 start -->
			<input  id="editName"  name="name" class="easyui-textbox" data-options="label:'申请人:'" style="width:240px" readonly="readonly"/>
			<select class="easyui-combobox" id="oldStationName" name="oldStationName" labelPosition="right" style="width:240px"
				data-options="label:'岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
			</select><br />
			<select class="easyui-combobox" id="sex" name="sex" data-options="label:'性别:'"  labelPosition="right" style="width:240px" readonly="readonly">
				<option value="">--请选择--</option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select>
			<input id="job" name="oldJob" class="easyui-textbox" data-options="label:'职务:'" style="width:240px" readonly="readonly"/><br />
			<input id="entryDate" name="entryDate" class="easyui-datebox" data-options="label:'入职时间:'" style="width:240px" readonly="readonly"/>
			<select  class="easyui-combobox" id="oldPosition" name="oldPosition" labelPosition="right" style="width:240px"
					data-options="label:'职称:',valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'" readonly="readonly">
					</select>
			<input id="deptName" name="deptName" class="easyui-textbox" data-options="label:'部门:'" style="width:240px" readonly="readonly"/>
			<!-- 异动前的信息模块 end -->
			
			<hr />
			
			<!-- 异动后的信息模块 start -->
			<select class="easyui-combobox" id="v_typeId" name="typeId" labelPosition="right" style="width:240px"
				data-options="label:'异动类型:',valueField:'typeId',textField:'typeName',url:'<%=basePath%>sysChangeType/all.do'" readonly="readonly">
			</select>
			<input id="v_changeDate" name="changeDate" class="easyui-datebox" data-options="label:'异动时间:'" style="width:240px" readonly="readonly"/><br />
			<input id="changeReason" name="changeReason" class="easyui-textbox" data-options="label:'异动原因:'" style="width:500px" />
		
			<div id="01v" style="display:none;">
					<select class="easyui-combobox" id="v_newStationId01" name="newStationId" labelPosition="right" style="width:240px"
						data-options="label:'异动后岗位:',editable:false,valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
					</select>
				<input  id="v_newJob01" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<div id="02v" style="display:none;">
				<input id="v_retireLevel" name="retireLevel" class="easyui-textbox" data-options="label:'退休级别:'" style="width:240px" readonly="readonly"/>
				<input id="v_retirePay" name="retirePay" class="easyui-textbox" data-options="label:'享受待遇:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<div id="03v" style="display:none;">
				预计返岗时间:<input id="v_returnDate" name="returnDate" class="easyui-datebox" data-options="label:''" style="width:140px" readonly="readonly"/><br />
			</div>
			<div id="04v" style="display:none;">
				<select class="easyui-combobox" id="v_newStationId04" name="newStationId" labelPosition="right" style="width:240px"
						data-options="label:'异动后岗位:',valueField:'id',editable:false,textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
					</select><br />
				<input  id="v_newJob04" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" readonly="readonly"/>
				<input id="v_newDeptNo" name="newDeptNo" class="easyui-textbox" data-options="label:'异动后部门:'" style="width:240px" readonly="readonly"/>
			</div>
			<div id="05v" style="display:none;">
				<input id="v_transferUnit" name="transferUnit" class="easyui-textbox" data-options="label:'调入单位:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<!-- 异动后的信息模块 end -->
		</form>
	</div>
	<!-- 查看窗口模块 end -->
	<!-- 审批窗口模块 start -->
	<div id="dlg_approve" class="easyui-dialog" closed="true" title="审批异动申请单" style="width: 600px; height: 350px; padding: 0;margin:0;" data-options="buttons: '#dlg-btns'">
 	<form action="" id="approveForm" style="background-color:#ECF6FA">
 			<!-- 异动前的信息模块 start -->
 			<input type="hidden" name="headId" id="headId" /> 
 			<input type="hidden" name="detailId" id="detailId" /> 
		    <input type="hidden" name="empId" id="empId" /> 
   		    <input type="hidden" name="typeName" id="a_typeName" /> 
		    <input type="hidden" name="oldDeptNo" id="oldDeptNo" />
		    <input type="hidden" name="oldStationId" id="oldStationId" />
		    <input type="hidden" name="newStationId" id="newStationId_a" />
		    <input type="hidden" name="oldStationGrade" id="oldStationGrade" />
			<input type="hidden" name="newStationGrade" id="newStationGrade" />
			<input  id="name"  name="name" class="easyui-textbox" data-options="label:'申请人:'" style="width:240px" readonly="readonly"/>
			<select class="easyui-combobox" id="oldStationName" name="oldStationName" labelPosition="right" style="width:240px"
				data-options="label:'岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
			</select><br />
			<select class="easyui-combobox" id="sex" name="sex" data-options="label:'性别:'"  labelPosition="right" style="width:240px" readonly="readonly">
				<option value="">--请选择--</option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select>
			<input id="job" name="oldJob" class="easyui-textbox" data-options="label:'职务:'" style="width:240px" readonly="readonly"/><br />
			<input id="entryDate" name="entryDate" class="easyui-datebox" data-options="label:'入职时间:'" style="width:240px" readonly="readonly"/>
			<input id="oldPosition" name="oldPosition" class="easyui-textbox" data-options="label:'职称:'" style="width:240px" readonly="readonly"/>
			<input id="deptName" name="deptName" class="easyui-textbox" data-options="label:'部门:'" style="width:240px" readonly="readonly"/>
			<!-- 异动前的信息模块 end -->
			
			<hr />
			
			<!-- 异动后的信息模块 start -->
			<select class="easyui-combobox" id="a_typeId" name="typeId" labelPosition="right" style="width:240px"
				data-options="label:'异动类型:',valueField:'typeId',textField:'typeName',url:'<%=basePath%>sysChangeType/all.do'" readonly="readonly">
			</select>
			<input id="changeDate" name="changeDate" class="easyui-datebox" data-options="label:'异动时间:'" style="width:240px" readonly="readonly"/><br />
			<input id="changeReason" name="changeReason" class="easyui-textbox" data-options="label:'异动原因:'" style="width:495px" readonly="readonly"/>
		
			<div id="01a" style="display:none;">
					<select class="easyui-combobox" id="newStationId01a" name="newStationId" labelPosition="right" style="width:240px" readonly="readonly"
						data-options="label:'异动后岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
					</select>
				<input  id="newJob01a" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<div id="02a" style="display:none;">
				<input id="retireLevel_a" name="retireLevel" class="easyui-textbox" data-options="label:'退休级别:'" style="width:240px" readonly="readonly" />
				<input id="retirePay_a" name="retirePay" class="easyui-textbox" data-options="label:'享受待遇:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<div id="03a" style="display:none;">
				预计返岗时间:<input id="returnDate_a" name="returnDate" class="easyui-datebox" data-options="label:''" style="width:140px" readonly="readonly" /><br />
			</div>
			<div id="04a" style="display:none;">
				<select class="easyui-combobox" id="newStationId04a" name="newStationId" labelPosition="right" style="width:240px" readonly="readonly"
						data-options="label:'异动后岗位:',valueField:'id',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
				</select><br />
				<input  id="newJob04a" name="newJob" class="easyui-textbox" data-options="label:'异动后职务:'" style="width:240px" readonly="readonly"/>
				<select class="easyui-combotree"  id="newDeptNoa" name="newDeptNo" data-options="label:'异动后部门:'"style="width:240px" readonly="readonly">
				</select>
			</div>
			<div id="05a" style="display:none;">
				<input id="transferUnit_a" name="transferUnit" class="easyui-textbox" data-options="label:'调入单位:'" style="width:240px" readonly="readonly"/><br />
			</div>
			<div id="dlg-btns">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="agreeApprove()" data-options="iconCls:'icon-save'">审批通过</a>
<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg_approve').dialog('close')">关闭</a> -->
			</div>
			<!-- 异动后的信息模块 end -->
    </form>
	</div>
	<!-- 审批窗口模块 end -->
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
    // 	按钮权限显示
	var objArrayYM = new Array("add","view","del","edit","submit","approval");   
	showObj(objArrayYM);//共同方法
		var _zTreeMenuBusiness = $('#zTreeMenuBusiness');
		// 当前部门编号
		var curDeptNo = null;
	
		// 查询元素区域
		var _searchForm = $("#searchForm");
		var _sysChangeRecForm = $("#sysChangeRecForm");
		var _doSearch = $("#doSearch");
		var _reset = $("#reset");
		var _dg = $('#dg');
		var _dlgEdit = $('#dlg_edit');
// 		var _deptNOSec = $("#deptNOSec");
		var _status = $("#status");
		var _name = $("#name");
		var _oldDeptNo = $("#oldDeptNo");
		var _newDeptNo = $("#newDeptNoa");
		var _editName = $("#editName");

        var selectType;
		// 定义全局变量区域
		var unknow = '';
		
		//加载业务树
		_zTreeMenuBusiness.tree({
			url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
			parentField:"pid",
			textFiled:"orgName",
			idFiled:"businessId",
			lines:true,
			animate:true,
			onClick: function(node){
				curDeptNo = node.businessId;
				_dg.datagrid('load', {
					oldDeptNo:node.businessId
				}); 
		    },
// 		    onLoadSuccess: function(node, data) {
// 		    	_zTreeMenuBusiness.tree('insert', {
// 		    		before: _zTreeMenuBusiness.tree('getRoot').target,
// 		    		data: [{
// 		    			orgName:"所有部门"
// 		    		}]
// 		    	});
// 		    }
		});
		
		
		// 人员表格数据参数设置
		var datagrid = _dg.datagrid({
		      url:"<%=basePath%>sysChangeRec/findChangeRecApplyList.do",
			pagination : true,//显示分页  
			rownumbers : true,//显示行号
			pageSize : 20,//分页大小  
			pageList : [ 20, 40, 60, 80 ],//每页的个数  
// 			fit:true,//自动补全  
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
				text : '新增',
				iconCls : 'icon-add',
				handler : function() {
					apply();
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
				},
			},{
				id:'del',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				},
			},{
				id:'submit',
				text : '提交',
				iconCls : 'icon-submit',
				handler : function() {
					submit();
				},
			},{
				id:'approval',
				text : '审批',
				iconCls : 'icon-approve',
				handler : function() {
					approve();
				},
			}],
			columns : [ [ //每个列具体内容  
			{
				field : 'ck',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				width : 10,
				hidden : true
			}, {
				field : 'name',
				title : '姓名',
				width : 20,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	 
				}
			},  {
				field : 'changeDate',
				title : '异动时间',
				width : 20,
				formatter : function(value){
					if(value){
						return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
					}
				}
			}, {
				field : 'orgName',
				title : '申请单位',
				width : 25,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	 
				}
			}, {
				field : 'oldStationName',
				title : '岗位',
				width : 25,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	 
				}
			}, {
				field : 'typeName',
				title : '异动类型',
				width : 20,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	 
				}
			}, {
				field : 'status',
				title : '申请状态',
				width : 15,
				formatter : function(value, row, index) {
					if (value == '0') {
						return "已保存";
					} else if (value == '1') {
						return "已提交";
					} else if (value == '2') {
						return "已审批";
					}  else {
						return unknow;
					}
				}
			}] ],
			singleSelect : true,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 470
		});
		
		// 参数化分页页面
		var p = _dg.datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10  
			pageList : [ 20, 40, 60, 80 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		});
		
		//查询
		_doSearch.click(function() {
			_dg.datagrid('load', {
				status:$("#s_status").combobox("getValue"),
				typeId:$("#s_typeId").combobox("getValue"),
				name:$("#s_name").textbox("getValue"),
				changeDateStart:$("#changeDateStart").textbox("getValue"),
				changeDateEnd:$("#changeDateEnd").textbox("getValue")
			});
		});
		
		
		//重置
		_reset.click(function() {
			_searchForm.form('clear');
		});
		
		//所在部门的树状展示
		$("#newDeptNoa").combotree({
		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    }
		});
		
		//所在部门的树状展示
		$("#newDeptNo").combotree({
		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    }
		});
		
		$("#typeId").combobox({  
		       onChange: function () {
				   selectType = $("#typeId").combobox('getText') ;
				   if(selectType=="晋升"||selectType=="岗位调整"){
					   document.getElementById("01").style.display="block";
					   document.getElementById("02").style.display="none";
					   document.getElementById("03").style.display="none";
					   document.getElementById("04").style.display="none";
					   document.getElementById("05").style.display="none";
				   }else if(selectType=="离退休"){
					   document.getElementById("02").style.display="block";
					   document.getElementById("01").style.display="none";
					   document.getElementById("03").style.display="none";
					   document.getElementById("04").style.display="none";
					   document.getElementById("05").style.display="none";
				   }else if(selectType=="离岗"){
					   document.getElementById("03").style.display="block";
					   document.getElementById("01").style.display="none";
					   document.getElementById("02").style.display="none";
					   document.getElementById("04").style.display="none";
					   document.getElementById("05").style.display="none";
				   }else if(selectType=="部门调整"){
					   document.getElementById("04").style.display="block";
					   document.getElementById("01").style.display="none";
					   document.getElementById("02").style.display="none";
					   document.getElementById("03").style.display="none";
					   document.getElementById("05").style.display="none";
				   }else if(selectType=="调出"){
					   document.getElementById("05").style.display="block";
					   document.getElementById("01").style.display="none";
					   document.getElementById("02").style.display="none";
					   document.getElementById("03").style.display="none";
					   document.getElementById("04").style.display="none";
				   }else{
					   document.getElementById("05").style.display="none";
					   document.getElementById("01").style.display="none";
					   document.getElementById("02").style.display="none";
					   document.getElementById("03").style.display="none";
					   document.getElementById("04").style.display="none";
				   }
		       }  
		});
		
		
		function apply(){
			_sysChangeRecForm.form("clear");
			_dlgEdit.dialog({title: "异动信息申请"});
			_dg.datagrid('clearSelections'); 
			_dlgEdit.dialog("open");
		}
		
		//更新申请人信息
		function updateApplyPerson(empId,empName) {
		    $("#empId").val(empId);
			$("#editName").textbox("setValue", empName);
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>sysEmp/load.do" ,
				data: {"id":empId},
				dataType: 'json',
				success: function(data){
					if (data != null) {
						_sysChangeRecForm.form('load',{
							empId : data.id,
							stationGrade : data.stationGrade,
							name : data.name,
							orgnization : data.orgnization,
							oldStationId : data.stationId,
							oldStationName : data.stationName,
							orgId : data.unitNO,
							oldDeptNo : data.deptNO,
							deptName : data.deptName,
							sex : data.sex,
							oldJob : data.job,
							oldPosition : data.position,
							entryDate : data.entryDate
						});
						
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','失败');
				}
			});
		}
		
		function applyPer(){
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择添加的申请人","location=no,width=1150,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		}
		
		//删除
		function del(){
			var rows = $('#dg').datagrid('getSelections');
		    var changeRecId="";
			if(rows.length==1){
				if(rows[0].status!="0"){
					$.messager.alert("操作提示", "只能删除已保存的申请单！");
					return;}
				else{
					changeRecId = rows[0].headId;
					$.messager.confirm('提示框', '你确定要删除吗?',function(r){
						if(r){
							$.ajax({
								url: '<%=basePath%>sysChangeRec/deleteChangeRec.do?id=' + changeRecId,
										success:function(){
											$('#dg').datagrid('reload')
											$.messager.show({
												title:'提示',
												msg:'删除成功',
												timeout:3000,
												showType:'slide'
											});
											//插入删除职称申请日志
											insertSysLog("人员异动申请","删除","单号为"+changeRecId+"的异动申请单");
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
		
		function submit(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				if(rows[0].status!="0"){
					$.messager.alert("操作提示", "只能提交已保存的申请单！");
					return;
				}else{ 
					$.messager.confirm('提示框', '你确定要提交吗?',function(r){
						if(r){
							$.ajax({
								url: '<%=basePath%>sysChangeRec/submitChangeRec.do?headId=' + rows[0].headId,
										success:function(){
											$('#dg').datagrid('reload')
											$.messager.show({
												title:'提示',
												msg:'提交成功',
												timeout:3000,
												showType:'slide'
											});
											//插入提交异动申请日志
											insertSysLog("人员异动申请","提交","单号为"+rows[0].headId+"的异动申请单");
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
		
		//审批
		function agreeApprove(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				$("#a_typeName").val($("#a_typeId").combobox('getText'));
				$.messager.confirm('提示框', '你确定要审核吗?',function(r){
					if(r){	
						$.ajax({  
							type: 'POST',
							url: "<%=basePath%>sysChangeRec/record.do?",   
							data: $("#approveForm").serialize(),
				     		dataType: 'json',
							success: function(data){
								if(data.code==0){
									$.messager.show({
										title:'提示',
										msg:'审批成功',
										timeout:3000,
										showType:'slide'
									});
									//插入审批异动申请日志
									insertSysLog("人员异动申请","审批","单号为"+rows[0].headId+"的异动申请单");
									$('#dg').datagrid('reload');
									$('#dlg_approve').dialog('close')
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
		
		function approve(){
			$('#approveForm').form("clear");
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				if(rows[0].status!="1"){
				$.messager.alert("操作提示", "只能审批已提交的申请单！");
				return;
				}else{
					$('#approveForm').form('load',{
						headId:rows[0].headId,
						typeId:rows[0].typeId,
						changeDate:rows[0].changeDate,
						changeReason:rows[0].changeReason
					});
					//分异动类型加载info1-info6的信息
					if(rows[0].typeName=="晋升"||rows[0].typeName=="岗位调整"){
						   document.getElementById("01a").style.display="block";
						   document.getElementById("02a").style.display="none";
						   document.getElementById("03a").style.display="none";
						   document.getElementById("04a").style.display="none";
						   document.getElementById("05a").style.display="none";
							$("#newJob01a").textbox("setValue", rows[0].info2);
							$("#newStationId01a").combobox("setValue", rows[0].info1);
							$.ajax({
								type: 'POST',
								url: "<%=basePath%>sysStationType/load.do" ,
								data: {"id":rows[0].info1},
								dataType: 'json',
								success: function(data){
									if (data != null) {
										$('#approveForm').form('load',{
											newStationName:data.stationName
										});
									}
								} ,
								error: function(){
									$.messager.alert('错误提示','失败');
								}
							});
					}else if(rows[0].typeName=="离退休"){
						   document.getElementById("02a").style.display="block";
						   document.getElementById("01a").style.display="none";
						   document.getElementById("03a").style.display="none";
						   document.getElementById("04a").style.display="none";
						   document.getElementById("05a").style.display="none";
							$("#retireLevel_a").textbox("setValue", rows[0].info1);
							$("#retirePay_a").textbox("setValue", rows[0].info2);
				    }else if(rows[0].typeName=="离岗"){
						   document.getElementById("03a").style.display="block";
						   document.getElementById("01a").style.display="none";
						   document.getElementById("02a").style.display="none";
						   document.getElementById("04a").style.display="none";
						   document.getElementById("05a").style.display="none";
						   $("#returnDate_a").datebox("setValue", rows[0].info6);
					}else if(rows[0].typeName=="部门调整"){
						   document.getElementById("04a").style.display="block";
						   document.getElementById("01a").style.display="none";
						   document.getElementById("02a").style.display="none";
						   document.getElementById("03a").style.display="none";
						   document.getElementById("05a").style.display="none";
						   //审核时跟带申请单信息
						   	$("#newDeptNoa").combotree("setValue", rows[0].info1);
							$("#newStationId04a").combobox("setValue", rows[0].info2);
							$("#newJob04a").textbox("setValue", rows[0].info3);
				    }else if(rows[0].typeName=="调出"){
						   document.getElementById("05a").style.display="block";
						   document.getElementById("01a").style.display="none";
						   document.getElementById("02a").style.display="none";
						   document.getElementById("03a").style.display="none";
						   document.getElementById("04a").style.display="none";
						   $('#approveForm').form('load',{
							   transferUnit:rows[0].info1
							});
					 }else{
						   document.getElementById("03a").style.display="none";
						   document.getElementById("01a").style.display="none";
						   document.getElementById("02a").style.display="none";
						   document.getElementById("04a").style.display="none";
						   document.getElementById("05a").style.display="none";
					}
					var empId=rows[0].empId;
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysEmp/load.do" ,
						data: {"id":empId},
						dataType: 'json',
						success: function(data){
						if (data != null) {
								$('#approveForm').form('load',{
									empId:data.id,
									stationGrade:data.stationGrade,
									name:data.name,
									orgnization:data.orgnization,
									oldStationId:data.stationId,
									oldStationName:data.stationName,
									oldDeptNo:data.deptNO,
									deptName:data.deptName,
									sex:data.sex,
									oldJob:data.job,
									oldPosition:data.positionName,
									entryDate:data.entryDate
								});
							}
						} ,
						error: function(){
							$.messager.alert('错误提示','失败');
						}
					});
					$('#dlg_approve').dialog('open');
					
				}
				
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//更新
		function update(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				if(rows[0].status!="0"){
					$.messager.alert("操作提示", "只能修改已保存的申请单！");
					return;
				}else{
					var empId = rows[0].empId;
					$('#sysChangeRecForm').form('load',{
						headId:rows[0].headId,
						detailId:rows[0].detailId,
						name:rows[0].name,
						typeId:rows[0].typeId,
						changeDate:rows[0].changeDate,
						changeReason:rows[0].changeReason,
						info1:rows[0].info1,
						info2:rows[0].info2,
						info3:rows[0].info3,
						info4:rows[0].info4,
						info5:rows[0].info5,
						info6:rows[0].info6
					});
					//根据不同异动类型加载和显示不同信息
					if(rows[0].typeName=="晋升"||rows[0].typeName=="岗位调整"){
						   document.getElementById("01").style.display="block";
						   document.getElementById("02").style.display="none";
						   document.getElementById("03").style.display="none";
						   document.getElementById("04").style.display="none";
						   document.getElementById("05").style.display="none";
							$("#newJob01").textbox("setValue", rows[0].info2);
							$("#newStationId01").combobox("setValue", rows[0].info1);
					}else if(rows[0].typeName=="离退休"){
						   document.getElementById("02").style.display="block";
						   document.getElementById("01").style.display="none";
						   document.getElementById("03").style.display="none";
						   document.getElementById("04").style.display="none";
						   document.getElementById("05").style.display="none";
							$("#retireLevel").textbox("setValue", rows[0].info1);
							$("#retirePay").textbox("setValue", rows[0].info2);
				    }else if(rows[0].typeName=="离岗"){
						   document.getElementById("03").style.display="block";
						   document.getElementById("01").style.display="none";
						   document.getElementById("02").style.display="none";
						   document.getElementById("04").style.display="none";
						   document.getElementById("05").style.display="none";
						   $("#returnDate").datebox("setValue", rows[0].info6);
					}else if(rows[0].typeName=="部门调整"){
						   document.getElementById("04").style.display="block";
						   document.getElementById("01").style.display="none";
						   document.getElementById("02").style.display="none";
						   document.getElementById("03").style.display="none";
						   document.getElementById("05").style.display="none";
							$("#newJob04").textbox("setValue", rows[0].info3);
						   //查询岗位id对应的岗位名称
						   $.ajax({
								type: 'POST',
								url: "<%=basePath%>sysStationType/load.do" ,
								data: {"id":rows[0].info2},
								dataType: 'json',
								success: function(data){
									if (data != null) {
										$('#sysChangeRecForm').form('load',{
											newStationId:data.stationName
										});
									}
								} ,
								error: function(){
									$.messager.alert('错误提示','失败');
								}
							});
						   //查询部门id对应的部门名称
						   $.ajax({
								type: 'POST',
								url: "<%=basePath%>sysChangeRec/loadDeptName.do" ,
								data: {"id":rows[0].info1},
								dataType: 'json',
								success: function(data){
									if (data != null) { 
										$('#sysChangeRecForm').form('load',{
											newDeptNo:data.newDeptNo
										});
									}
								} ,
								error: function(){
									$.messager.alert('错误提示','失败');
								}
							});
				    }else if(rows[0].typeName=="调出"){
						   document.getElementById("05").style.display="block";
						   document.getElementById("01").style.display="none";
						   document.getElementById("02").style.display="none";
						   document.getElementById("03").style.display="none";
						   document.getElementById("04").style.display="none";
						   $('#sysChangeRecForm').form('load',{
							   transferUnit:rows[0].info1
							});
					 }else{
						   document.getElementById("03").style.display="none";
						   document.getElementById("01").style.display="none";
						   document.getElementById("02").style.display="none";
						   document.getElementById("04").style.display="none";
						   document.getElementById("05").style.display="none";
					}
					//根据人员id加载人员信息
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysEmp/load.do" ,
						data: {"id":empId},
						dataType: 'json',
						success: function(data){
							if (data != null) {
								_sysChangeRecForm.form('load',{
									empId:data.id,
									stationGrade:data.stationGrade,
									name:data.name,
									orgnization:data.orgnization,
									oldStationId:data.stationId,
									oldStationName:data.stationName,
									oldDeptNo:data.deptNO,
									deptName:data.deptName,
									sex:data.sex,
									oldJob:data.job,
									oldPosition:data.positionName,
									entryDate:data.entryDate
								});
								
							}
						} ,
						error: function(){
							$.messager.alert('错误提示','失败');
						}
					});
					$("#dlg_edit").dialog({title: "修改人员异动申请单信息"});
					$('#dlg_edit').dialog('open');
				}

			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		function view(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				var empId = rows[0].empId;
				$('#sysChangeRecViewForm').form('clear');
				$('#sysChangeRecViewForm').form('load',{
					headId:rows[0].headId,
					detailId:rows[0].detailId,
					name:rows[0].name,
					typeId:rows[0].typeId,
					changeDate:rows[0].changeDate,
					changeReason:rows[0].changeReason,
					info1:rows[0].info1,
					info2:rows[0].info2,
					info3:rows[0].info3,
					info4:rows[0].info4,
					info5:rows[0].info5,
					info6:rows[0].info6
				});
				//根据不同异动类型加载和显示不同信息
				if(rows[0].typeName=="晋升"||rows[0].typeName=="岗位调整"){
					   document.getElementById("01v").style.display="block";
					   document.getElementById("02v").style.display="none";
					   document.getElementById("03v").style.display="none";
					   document.getElementById("04v").style.display="none";
					   document.getElementById("05v").style.display="none";
						$("#v_newJob01").textbox("setValue", rows[0].info2);
						$("#v_newStationId01").combobox("setValue", rows[0].info1);
				}else if(rows[0].typeName=="离退休"){
					   document.getElementById("02v").style.display="block";
					   document.getElementById("01v").style.display="none";
					   document.getElementById("03v").style.display="none";
					   document.getElementById("04v").style.display="none";
					   document.getElementById("05v").style.display="none";
						$("#v_retireLevel").textbox("setValue", rows[0].info1);
						$("#v_retirePay").textbox("setValue", rows[0].info2);
			    }else if(rows[0].typeName=="离岗"){
					   document.getElementById("03v").style.display="block";
					   document.getElementById("01v").style.display="none";
					   document.getElementById("02v").style.display="none";
					   document.getElementById("04v").style.display="none";
					   document.getElementById("05v").style.display="none";
					   $("#v_returnDate").datebox("setValue", rows[0].info6);
				}else if(rows[0].typeName=="部门调整"){
					   document.getElementById("04v").style.display="block";
					   document.getElementById("01v").style.display="none";
					   document.getElementById("02v").style.display="none";
					   document.getElementById("03v").style.display="none";
					   document.getElementById("05v").style.display="none";
						$("#v_newJob04").textbox("setValue", rows[0].info3);
						$("#v_newStationId04").combobox("setValue", rows[0].info2);
					   //查询部门id对应的部门名称
					   $.ajax({
							type: 'POST',
							url: "<%=basePath%>sysChangeRec/loadDeptName.do" ,
							data: {"id":rows[0].info1},
							dataType: 'json',
							success: function(data){
								if (data != null) { 
									$('#sysChangeRecViewForm').form('load',{
										newDeptNo:data.newDeptNo
									});
								}
							} ,
							error: function(){
								$.messager.alert('错误提示','失败');
							}
						});
			    }else if(rows[0].typeName=="调出"){
					   document.getElementById("05v").style.display="block";
					   document.getElementById("01v").style.display="none";
					   document.getElementById("02v").style.display="none";
					   document.getElementById("03v").style.display="none";
					   document.getElementById("04v").style.display="none";
					   $('#sysChangeRecForm').form('load',{
						   transferUnit:rows[0].info1
						});
				 }else{
					   document.getElementById("03v").style.display="none";
					   document.getElementById("01v").style.display="none";
					   document.getElementById("02v").style.display="none";
					   document.getElementById("04v").style.display="none";
					   document.getElementById("05v").style.display="none";
				}
				//根据人员id加载人员信息
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysEmp/load.do" ,
					data: {"id":empId},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							$('#sysChangeRecViewForm').form('load',{
								empId:data.id,
								stationGrade:data.stationGrade,
								name:data.name,
								orgnization:data.orgnization,
								oldStationId:data.stationId,
								oldStationName:data.stationName,
								oldDeptNo:data.deptNO,
								deptName:data.deptName,
								sex:data.sex,
								oldJob:data.job,
								oldPosition:data.positionName,
								entryDate:data.entryDate
							});
							
						}
					} ,
					error: function(){
						$.messager.alert('错误提示','失败');
					}
				});
				$('#dlg_view').dialog('open');
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		// 提交的处理
		function showSubmit() {
			if(selectType=="晋升"||selectType=="岗位调整"){
				$("#info1").val($("#newStationId01").combobox('getValue'));
				$("#info2").val($("#newJob01").textbox("getValue"));
			}else if(selectType=="离退休"){
				$("#info1").val($("#retireLevel").textbox("getValue"));
				$("#info2").val($("#retirePay").textbox("getValue"));
			}else if(selectType=="离岗"){
				$("#info6").val($("#returnDate").datebox('getValue'));
			}else if(selectType=="部门调整"){
				$("#info1").val($("#newDeptNo").combobox('getValue'));
				$("#info2").val($("#newStationId04").combobox('getValue'));
				$("#info3").val($("#newJob04").textbox("getValue"));
			}else if(selectType=="调出"){
				$("#info1").val($("#transferUnit").textbox("getValue"));
			}
			//判断是新增还是更新
			var rows = _dg.datagrid('getSelections');
			if(rows.length==1){//更新
				if(_sysChangeRecForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysChangeRec/updateChangeRec.do" ,
						data: _sysChangeRecForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code == 0){
								$.messager.show({
									title:'提示',
									msg:'修改成功',
									timeout:3000,
									showType:'slide'
								}); 
								//插入修改异动申请日志
								insertSysLog("人员异动申请","修改","单号为"+rows[0].headId+"的异动申请单");
								$('#dg').datagrid('reload')
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
			}else{//新增
				if(_sysChangeRecForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysChangeRec/apply.do",
						data: _sysChangeRecForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'保存成功',
									timeout:3000,
									showType:'slide'
								});
								//插入异动申请日志
								insertSysLog("人员异动申请","新增","单号为"+data.id+"的异动申请单");
								_dg.datagrid('reload');
								_dlgEdit.dialog('close');
							}else if(data==1){
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
			}
		}
	</script>
</body>
</html>