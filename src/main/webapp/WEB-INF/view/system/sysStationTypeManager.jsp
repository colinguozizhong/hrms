<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>岗位类型管理</title>
<%@include file="/common/common_easyui.jsp"%>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>

</head>
<body>
	<!-- 列表模块 start -->
	<div id="dg" style="display:block;"></div>  
	<!-- 列表模块 end -->
	
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" closed="true" title="新增岗位类型" style="width: 560px;display: none; height: 200px; padding: 10px" data-options="modal:true,buttons:'#dlg-buttons'">
		<form action="" id="sysStationTypeForm" name="emp">
			<input type="hidden" name="id" />
			<input id="stationCode" name="stationCode" class="easyui-textbox" data-options="label:'岗位编码:'" style="width:240px"/>
			<input id="e_stationName" name="stationName" class="easyui-textbox" data-options="label:'岗位名称:'" style="width:240px"/>
			<input name="stationGrade" class="easyui-textbox" data-options="label:'岗位等级:'" style="width:240px"/>
			<input name="totalPer" class="easyui-textbox" data-options="label:'岗位人员数:'" style="width:240px"/>
			<input name="complementPer" class="easyui-textbox" data-options="label:'编制人员数:'" style="width:240px"/>
			<select class="easyui-combobox"  id="stationType" name="stationType" label="岗位类型:" style="width:250px" data-options="editable:false,panelHeight:'auto'" >
						<option value="管理类">管理类</option>
						<option value="专业技术类">专业技术类</option>
						<option value="工勤类">工勤类</option>
						<option value="其他类">其他类</option>
			</select>
		</form>
	</div>
	<div id='dlg-buttons'>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit();">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	</div>
	<!-- 编辑窗口模块 end -->
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		// 	按钮权限显示
		var objArrayYM = new Array("add","edit","edit");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $dg = $('#dg');
		var $sysStationTypeForm = $("#sysStationTypeForm");
		var $dlgEdit = $("#dlg_edit");
		var $stationCode = $("#stationCode");
		
		// 岗位类型表格数据参数设置
		var datagrid = $dg.datagrid({
		    url:"<%=basePath%>sysStationType/findStationTypeList.do",
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
            {
  				field : 'ck',
  				checkbox : true
  			},
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden : true
			}, {
				field : 'stationCode',
				title : '岗位编码',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'stationName',
				title : '岗位名称',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'stationGrade',
				title : '岗位等级',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'totalPer',
				title : '岗位人员总数',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'complementPer',
				title : '编制人员数',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			} ] ],
			height : 500
		});
		
		//弹窗添加窗口
		function add(){
			$sysStationTypeForm.form("clear");
			
			// 将岗位编码设置为可编辑
			$stationCode.textbox({ disabled: false }); 
			$('#dg').datagrid('clearSelections'); 
			$dlgEdit.dialog({title: "新增岗位类型"});
			
			$dlgEdit.dialog("open");
		}
		
		//弹出更新窗口
		function update(){
			var rows = $dg.datagrid('getSelections');
			if(rows.length == 1){
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysStationType/load.do" ,
					data: {"id":rows[0].id},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							$sysStationTypeForm.form('load',{
								id:data.id,
								stationCode:data.stationCode,
								stationName:data.stationName,
								stationGrade:data.stationGrade,
								totalPer:data.totalPer,
								complementPer:data.complementPer
							});
							
							// 将岗位编码设置为不可编辑
							$stationCode.textbox({ disabled: true }); 
							
							$dlgEdit.dialog({title: "修改岗位类型"});
							$dlgEdit.dialog("open");
						}
					} ,
					error: function(){
						$.messager.alert('错误提示','失败');
					}
				});
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//增加弹窗提交
		function showSubmit(){
			//判断是新增还是更新
			var rows = $dg.datagrid('getSelections');
			if(rows.length==1){
				if($sysStationTypeForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysStationType/update.do" ,
						data: $sysStationTypeForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'更新成功',
									timeout:3000,
									showType:'slide'
								});
								//插入修改岗位类型管理日志
								insertSysLog("岗位类型管理","修改","名称为"+$("#e_stationName").textbox("getValue")+"的岗位类型");
								$dg.datagrid('reload');
								$dlgEdit.dialog('close');
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
			}else{
				if($sysStationTypeForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysStationType/add.do" ,
						data:$sysStationTypeForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'新增成功',
									timeout:3000,
									showType:'slide'
								});
								//插入新增岗位类型管理日志
								insertSysLog("岗位类型管理","新增","名称为"+data.id+"的岗位类型");
								$dg.datagrid('reload');
								$dlgEdit.dialog('close');
							}else if(data==1){
								$.messager.alert('错误提示','系统出现异常，请联系管理员');
							}
						},
						error: function(){
							$.messager.alert('错误提示','系统出现异常，请联系管理员');
						}
					});
				}else{
					$.messager.alert('错误提示','请完善红色必填项');
				}
			}
		}
		
		//删除
		function del(){
			var rows = $dg.datagrid('getSelections');
		    var id = "";
		    var stationName = "";
		    for(var i = 0; i < rows.length; i++){
		     	//获取选中节点的值
		    	id += rows[i].id + ",";
		    	stationName += rows[i].stationName+",";
		    }
		    //去掉结尾逗号
		    stationName = stationName.substring(0,stationName.length-1);
			if(rows.length > 0){
				$.messager.confirm('提示框', '你确定要删除吗?', function(r){
					if(r){
						$.ajax({
							url: '<%=basePath%>sysStationType/delete.do?id=' + id,
							success:function(){
								$dg.datagrid('reload');
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入删除干部考核日志
								insertSysLog("岗位类型管理","删除","名称为"+stationName+"的岗位类型");
							}
						});
					}
				});
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
	</script>
</body>
</html>