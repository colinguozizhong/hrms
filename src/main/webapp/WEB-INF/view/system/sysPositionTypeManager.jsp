<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职称编码管理</title>
<%@include file="/common/common_easyui.jsp"%>    
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
</head> 			
<body>
	<!-- 列表模块 start -->
	<div id="dg" style="display:block;"></div>  
	<!-- 列表模块 end -->
	
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" closed="true" title="新增职称类型" style="width: 350px;display: none; height: 250px; padding: 10px 0 0 40px;" data-options="modal:true,buttons:'#dlg-buttons'">
		<form action="" id="sysPositionTypeForm" name="sysPositionTypeForm">
   		    <input type="hidden" id="proName" name="proName"/>	
   		    <input type="hidden" id="browser" name="browser"/>	
		    <input type="hidden" id="positionIdTemp" name="positionIdTemp"/>	
			<input id="positionId" name="positionId" class="easyui-textbox" data-options="events:{blur:checkPositionId},label:'职称编码:'" style="width:240px" required="required"/><span id="display" style="display:none; color:red">该职称编码已存在！</span></br>
			<input id="positionName" name="positionName" class="easyui-textbox" data-options="label:'职称名称:'" style="width:240px" required="required"/></br>
			<input id="positionGrade" name="positionGrade" class="easyui-textbox" data-options="label:'职称等级:'" style="width:240px" required="required"/></br>
			<select  class="easyui-combobox" id="positionPro" name="positionPro"  labelPosition="right" style="width:240px"
				data-options="label:'专业:',editable:false,valueField:'proCode',textField:'proName',url:'<%=basePath%>sysPositionType/loadProfession.do'" required="required" >
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
		var objArrayYM = new Array("add","edit","del");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $dg = $('#dg');
		var $sysPositionTypeForm = $("#sysPositionTypeForm");
		var $dlgEdit = $("#dlg_edit");
		var $positionId = $("#positionId");
		// 岗位类型表格数据参数设置
		var datagrid = $dg.datagrid({
		    url:"<%=basePath%>sysPositionType/findPositionTypeList.do",
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
			}, {
				id:'edit',
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					update();
				},
			} ,{
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
				field : 'positionId',
				title : '职称编码',
				width : 100,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			},  {
				field : 'positionName',
				title : '职称名称',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'positionPro',
				title : '专业',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'positionGrade',
				title : '等级',
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
			$sysPositionTypeForm.form("clear");
			// 将职称编码设置为可编辑
			$dlgEdit.dialog({title: "新增职称类型"});
			$('#dg').datagrid('clearSelections'); 
			document.getElementById("display").style.display="none";
			$dlgEdit.dialog("open");
		}
		
		//弹出更新窗口
		function update(){
			var rows = $dg.datagrid('getSelections');
			if(rows.length == 1){
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysPositionType/loadPositionTypeInformation.do" ,
					data: {"positionId":rows[0].positionId},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							$sysPositionTypeForm.form('load',{
								positionId : data.positionId, 
								positionName : data.positionName,
								positionGrade : data.positionGrade,
								positionPro : data.positionPro
							});
							
							// 将职称编码设置为不可编辑
							$positionId.textbox({ disabled: true }); 
							document.getElementById("display").style.display="none";
							$dlgEdit.dialog({title: "修改职称类型"});
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
			$("#browser").val(getBrowserInfo());
			//proName存储专业名称
			$("#proName").val($("#positionPro").combobox('getText'));
			if(rows.length==1){
				$("#positionIdTemp").val($("#positionId").textbox("getValue"));
				if($sysPositionTypeForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysPositionType/updatePositionType.do" ,
						data: $sysPositionTypeForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'更新成功',
									timeout:3000,
									showType:'slide'
								});
								//插入操作日志
								insertSysLog("职称编码管理","修改",$("#positionName").textbox("getValue"));
								$dg.datagrid('reload');
								$dlgEdit.dialog('close');
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
				if($sysPositionTypeForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysPositionType/addPositionType.do" ,
						data:$sysPositionTypeForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'新增成功',
									timeout:3000,
									showType:'slide'
								});
								//插入操作日志
								insertSysLog("职称编码管理","新增",$("#positionName").textbox("getValue"));
								$dg.datagrid('reload');
								$dlgEdit.dialog('close');
							}else{
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
		    var positionId = "";
		    var positionName ="";
		    for(var i = 0; i < rows.length; i++){
		     	//获取选中节点的值
		    	positionId += rows[i].positionId + ",";
		     	positionName += rows[i].positionName+",";
		    }
		    positionName = positionName.substring(0,positionName.length-1);
			if(rows.length > 0){
				$.messager.confirm('提示框', '你确定要删除吗?', function(r){
					if(r){
						$.ajax({
							url: '<%=basePath%>sysPositionType/deletePositionType.do?positionId=' + positionId,
							success:function(){
								$dg.datagrid('reload');
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入操作日志
								insertSysLog("职称编码管理","删除",positionName);
							}
						});
					}
				});
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//检测职称编码是否已存在
		function checkPositionId(){
			var id =this.value;
			$.ajax({
				url: '<%=basePath%>sysPositionType/checkPositionId.do?positionId=' + id,
				success:function(data){
					if(data.code==1){
						document.getElementById("display").style.display="block";
					}else if(data.code==0){
						document.getElementById("display").style.display="none";
					}
				}
			});
		}
		
	</script>
</body>
</html>