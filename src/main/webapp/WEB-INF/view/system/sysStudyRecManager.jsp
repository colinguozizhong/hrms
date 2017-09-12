<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员学历管理</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
	<!-- 查询表单模块 start -->
	<div id="search"    data-options="region:'north',border:false" style="font-size: 12px;text-align:left;padding:5px 0 5px 15px;height:36px">
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
				style="width: 68px;margin-left: 5px">重置</a>
		</form>
	</div>
	<!-- 查询表单模块 end -->
	
	<!-- 列表模块 start -->
	<div id="dg" style="display:block;"></div>  
	<!-- 列表模块 end -->
	
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" data-options="modal:true" closed="true" title="编辑学历信息" style="width: 800px;display: none; height: 460px; padding: 10px" data-options="buttons: '#dlg-buttons'">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit();">添加</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="update();">修改</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="del();">删除</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a> -->
		<!-- 当前人员的学历信息基本信息模块 start -->
		<form action="" id="sysStudyRecForm" name="emp">
		     <input type="hidden" name="empId" id=""empId"" />
		     <input name="name" class="easyui-textbox" data-options="label:'姓名:'" style="width:200px" readonly="readonly"/>
		     <input name="deptName" class="easyui-textbox" data-options="label:'所在部门:'" style="width:200px" readonly="readonly"/><br />
		     <input name="stationName" class="easyui-textbox" data-options="label:'岗位:'" style="width:200px" readonly="readonly"/>
			 <select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:200px"
				data-options="label:'学历:',editable:false,panelHeight:'auto',valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'" >
			 </select>
		     <input name="degree" class="easyui-textbox" data-options="label:'学位:'" style="width:200px" /><br />
		     <input name="studyInfo" class="easyui-textbox" data-options="label:'学习形式:'" style="width:200px" />
		     <input name="studyPro" class="easyui-textbox" data-options="label:'专业:'" style="width:200px" />
		     <input name="studySchool" class="easyui-textbox" data-options="label:'毕业院校:'" style="width:200px" /><br />
		     <input name="statrtDate" class="easyui-datebox" data-options="label:'入学时间:'" style="width:200px" />
		     <input name="finishDate" class="easyui-datebox" data-options="label:'毕业时间:'" style="width:200px" />
		     <input name="reterence" class="easyui-textbox" data-options="label:'证明人:'" style="width:200px" />
		</form>
		<!-- 当前人员的学历信息基本信息模块 end -->
		<div style="height:10px"></div>
		<!-- 学历记录列表模块 start -->
		<div id="dgStudyRec" style="display:block;"></div>
		<!-- 学历记录列表模块 end -->
	</div>
	<!-- 编辑窗口模块 end -->
	
	<!-- 修改窗口模块 start -->
	<div id="dlg_update" class="easyui-dialog" data-options="modal:true" closed="true" title="修改学历信息" style="width: 800px;display: none; height: 280px; padding: 10px" data-options="buttons: '#dlg-buttons'">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submit();">确定</a>
<!-- 		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a> -->
		<!-- 当前人员的学历信息基本信息模块 start -->
		<form action="" id="updateStudyRecForm" name="emp">
		     <input type="hidden" name="empId" id=""empId"" />
<!-- 		     <input name="name" class="easyui-textbox" data-options="label:'姓名:'" style="width:200px" readonly="readonly"/> -->
<!-- 		     <input name="deptName" class="easyui-textbox" data-options="label:'所在部门:'" style="width:200px" readonly="readonly"/><br /> -->
<!-- 		     <input name="stationName" class="easyui-textbox" data-options="label:'岗位:'" style="width:200px" readonly="readonly"/> -->
			 <select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:200px"
				data-options="label:'学历:',editable:false,panelHeight:'auto',valueField:'id',textField:'studyName',url:'<%=basePath%>studyType/all.do'" >
			 </select>
		     <input name="degree" class="easyui-textbox" data-options="label:'学位:'" style="width:200px" /><br />
		     <input name="studyInfo" class="easyui-textbox" data-options="label:'学习形式:'" style="width:200px" />
		     <input name="studyPro" class="easyui-textbox" data-options="label:'专业:'" style="width:200px" />
		     <input name="studySchool" class="easyui-textbox" data-options="label:'毕业院校:'" style="width:200px" /><br />
		     <input name="statrtDate" class="easyui-datebox" data-options="label:'入学时间:'" style="width:200px" />
		     <input name="finishDate" class="easyui-datebox" data-options="label:'毕业时间:'" style="width:200px" />
		     <input name="reterence" class="easyui-textbox" data-options="label:'证明人:'" style="width:200px" />
		</form>
		<!-- 当前人员的学历信息基本信息模块 end -->
		
		<!-- 学历记录列表模块 start -->
		<div id="dgStudyRec" style="display:block;"></div>
		<!-- 学历记录列表模块 end -->
	</div>
	<!-- 修改窗口模块 end -->
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		// 	按钮权限显示
		var objArrayYM = new Array("editS");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $searchForm = $("#searchForm");
		var $sysStudyRecForm = $("#sysStudyRecForm");
		var $doSearch = $("#doSearch");
		var $reset = $("#reset");
		var $dg = $('#dg');
		var $dgStudyRec = $('#dgStudyRec');
		var $deptNO = $("#deptNO");
		var $name = $("#name");
		var $job = $("#job");
		var $dlgedit = $("#dlg_edit");
		
		// 定义全局变量区域
		var unknow = '';
		
		// 人员学历表格数据参数设置
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
				id:'editS',
				text : '编辑学历信息',
				iconCls : 'icon-edit',
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
			},  {
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
			},{
				field : 'deptName',
				title : '所在单位',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
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
				field : 'eduRecName',
				title : '学历',
				width : 50,
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
		                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
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
				
				// 加载当前人员的基本学历信息
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>studyRec/lastByEmpid.do" ,
					data: {"empId":rows[0].id},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							$sysStudyRecForm.form('load',{
								empId:data.empId,
								name:curName,
								deptName:data.deptName,
								stationName:data.stationName/* ,
								eduRec:data.eduRec,
								degree:data.degree,
								studyInfo:data.studyInfo,
								studyPro:data.studyPro,
								studySchool:data.studySchool,
								statrtDate:data.statrtDate,
								finishDate:data.finishDate,
								reterence:data.reterence */
							});
						}
					} ,
					error: function(){
						$.messager.alert('错误提示','失败');
					}
				});
				
				// 加载学历记录列表信息
				$dgStudyRec.datagrid({
				    url:"<%=basePath%>studyRec/findStudyRecList.do?empId=" + rows[0].id,
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
						field : 'studyInfo',
						title : '学习形式',
						width : 120,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					}, {
						field : 'eduName',
						title : '学历',
						width : 120,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					}, {
						field : 'studyPro',
						title : '专业',
						width : 120,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					}, {
						field : 'studySchool',
						title : '毕业院校',
						width : 120,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					}, {
						field : 'statrtDate',
						title : '入学时间',
						width : 160,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
							}	  
						}
					}, {
						field : 'finishDate',
						title : '毕业时间',
						width : 160,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";
							}	  
						}
					}, {
						field : 'reterence',
						title : '证明人',
						width : 160,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					}, {
						field : 'eduType',
						title : '学历类型',
						width : 120,
						formatter : function(value, row, index) {
							if (value == 'first') {
								return "第一学历";
							} else if (value == 'top') {
								return "最高学历";
							} else {
								return "";
							}
						}
					}/* , {
						field : 'deptName',
						title : '部门名称',
						width : 160,
						formatter : function(value) {
							if(value){
				                return "<span title='" + value + "'>" + value + "</span>";
							}	  
						}
					} */] ],
					height : 240,
					singleSelect : false,
					selectOnCheck : true,
					checkOnSelect : true,
					onSelect: function(rowIndex, row){
						// 加载当前人员的基本学历信息
// 						$.ajax({
// 							type: 'POST',
<%-- 							url: "<%=basePath%>studyRec/lastByEmpid.do" , --%>
// 							data: {"empId":row.empId},
// 							dataType: 'json',
// 							success: function(data){
// 								if (data != null) {
// 									$sysStudyRecForm.form('load',{
// 										id:data.id,
// 										name:curName,
// 										deptName:data.deptName,
// 										stationName:data.stationName,
// 										eduRec:data.eduRec,
// 										degree:data.degree,
// 										studyInfo:data.studyInfo,
// 										studyPro:data.studyPro,
// 										studySchool:data.studySchool,
// 										statrtDate:data.statrtDate,
// 										finishDate:data.finishDate,
// 										reterence:data.reterence
// 									});
// 								}
// 							} ,
// 							error: function(){
// 								$.messager.alert('错误提示','失败');
// 							}
// 						});
				   }
				});
				var pp = $dgStudyRec.datagrid('getPager');
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
		
		function update(){
			var rows = $dgStudyRec.datagrid('getSelections');
			if(rows.length==1){
				$('#updateStudyRecForm').form('load',{
					eduRec:rows[0].eduRec,
					degree:rows[0].degree,
					studyInfo:rows[0].studyInfo,
					studyPro:rows[0].studyPro,
					studySchool:rows[0].studySchool,
					statrtDate:rows[0].statrtDate,
					finishDate:rows[0].finishDate,
					reterence:rows[0].reterence
				});
				$("#dlg_update").window('open');
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		function del() { 
			var rows = $dgStudyRec.datagrid('getSelections');
			 var id="";
			    for(var i = 0; i < rows.length; i++){
			     	//获取选中节点的值
			    	id += rows[i].id + ",";
			    }
				if(rows.length > 0){
					$.messager.confirm('提示框', '你确定要删除吗?', function(r){
						if(r){
							$.ajax({
								url: '<%=basePath%>studyRec/delete.do?id=' + id,
								success:function(){
									$dgStudyRec.datagrid('reload');
									$.messager.show({
										title:'提示',
										msg:'删除成功',
										timeout:3000,
										showType:'slide'
									});
								}
							});
						}
					});
				} else {
					$.messager.alert("操作提示", "请选择！");
				}
		}
		
		//修改提交
		function submit(){
			if($("#updateStudyRecForm").form('validate')){
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>studyRec/updateStudyRec.do" ,
					data:$sysStudyRecForm.serialize(),
					dataType: 'json',
					success: function(data){
						if(data.code == 0){
							$.messager.show({
								title:'提示',
								msg:'修改成功',
								timeout:3000,
								showType:'slide'
							});
							$("#dgStudyRec").datagrid('reload');
							$("#dlg_update").dialog('close');
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
		
		//增加弹窗提交
		function showSubmit(){
			//判断是新增还是更新
			var rows = $dgStudyRec.datagrid('getSelections');
			<%--if(rows.length == 1){
				 if($sysStudyRecForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>studyRec/update.do" ,
						data: $sysStudyRecForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'更新成功',
									timeout:3000,
									showType:'slide'
								});
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
			}else{--%>
				if($sysStudyRecForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>studyRec/add.do" ,
						data:$sysStudyRecForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code == 0){
								$.messager.show({
									title:'提示',
									msg:'新增成功',
									timeout:3000,
									showType:'slide'
								});
								$("#dg").datagrid('reload');
								$dgStudyRec.datagrid('reload');
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
// 		}
	</script>
</body>
</html>