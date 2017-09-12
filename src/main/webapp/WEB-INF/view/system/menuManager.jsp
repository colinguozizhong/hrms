<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>
				交通运输综合执法系统
			</title>
			<%@include file="/common/common_easyui.jsp" %>
			
			<script type="text/javascript" src="<%=basePath%>scripts/py.js"></script>
				<script type="text/javascript">
				var cureetObj = null;
				var objArrayYM = new Array("add","save","del"); 
					$(function() {
						showObj(objArrayYM);//共同方法
						//拼音
						$("input",$("#menuName").next("span")).blur(function(){  
							$("#busiCode").textbox("setValue", makePy($("#menuName").textbox("getValue")));
						})  
						$('#add').menubutton({menu:'#mm'});
						$("#mm1").click(function(){
							cureetObj = 1;//功能菜单
							$("#type").val("add");
							$('#form1').form('clear');
							$("#gnca" ).css("display", "block"); 
							$("#anca" ).css("display", "none"); 
						})
						$("#mm2").click(function(){
							cureetObj = 2;//按钮对象
							$("#type").val("add");
							$('#form2').form('clear');
							$("#gnca" ).css("display", "none"); 
							$("#anca" ).css("display", "block"); 
						})
						
						//点击保存
						$("#save").click(function() {
							if(cureetObj == 1){
								if($("#type").val()=="add"){
									var menuId=$("#menuId").val();
									//新增
									if($("#form1").form('validate')){
										$.ajax({
											type: 'POST',
											url: "<%=basePath%>sysMenu/insertSysMenu.do?menuId="+menuId ,
											data: $('#form1').serialize(),
											dataType: 'text',
											success: function(data){
												if(data==0){
													$.messager.show({
														title:'提示',
														msg:'新增成功',
														timeout:3000,
														showType:'slide'
													});
													$("#zTreeMenu").tree("reload");
													$("#type").val("");
													$("#gnca" ).css("display", "none"); 
													$("#anca" ).css("display", "none"); 
												}else if(data==1){
													$.messager.alert('错误提示','菜单名重复');
												}
											} ,
											error: function(){
												$.messager.alert('错误提示','失败');
											}
										});
									}else{
										$.messager.alert('错误提示','红色星号的选项必填');
									}
								}else{
									//更新
									var menuId = $("#menuId").val();
									if (menuId == null || menuId == "") {
										return false;
									} else {
										if($("#form1").form('validate')){
											$.ajax({
												type: 'POST',
												url: "<%=basePath%>sysMenu/updateSysMenu.do?menuId="+menuId ,
												data: $('#form1').serialize(),
												dataType: 'text',
												success: function(data){
													
													if(data==0){
														$.messager.show({
															title:'提示',
															msg:'更新成功',
															timeout:3000,
															showType:'slide'
														});
														$("#zTreeMenu").tree("reload");
														$("#gnca" ).css("display", "none"); 
														$("#anca" ).css("display", "none"); 
													}else if(data==1){
														$.messager.alert('错误提示','菜单名重复');
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
							}else if(cureetObj == 2){
								if($("#type").val()=="add"){
									var menuId=$("#menuId").val();
									//新增按钮对象
									if($("#form2").form('validate')){
										$.ajax({
											type: 'POST',
											url: "<%=basePath%>sysMenu/insertSysObj.do?menuId="+menuId ,
											data: $('#form2').serialize(),
											dataType: 'text',
											success: function(data){
												if(data==0){
													$.messager.show({
														title:'提示',
														msg:'新增成功',
														timeout:3000,
														showType:'slide'
													});
													$("#zTreeMenu").tree("reload");
													$("#type").val("");
													$("#gnca" ).css("display", "none"); 
													$("#anca" ).css("display", "none"); 
												}else if(data==1){
													$.messager.alert('错误提示','菜单名重复');
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
									//更新
									var objId = $("#objId").val();
									if (objId == null || objId == "") {
										return false;
									} else {
										if($("#form2").form('validate')){
											$.ajax({
												type: 'POST',
												url: "<%=basePath%>sysMenu/updateSysObj.do?objId="+objId ,
												data: $('#form2').serialize(),
												dataType: 'text',
												success: function(data){
													
													if(data==0){
														$.messager.show({
															title:'提示',
															msg:'更新成功',
															timeout:3000,
															showType:'slide'
														});
														$("#zTreeMenu").tree("reload");
														$("#gnca" ).css("display", "none"); 
														$("#anca" ).css("display", "none"); 
													}else if(data==1){
														$.messager.alert('错误提示','菜单名重复');
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
							}
						});
							
						//删除 
						$("#del").click(function() {
							if(cureetObj == 1){
								var node = $("#menuId").val();
								if (node == null || node == "") {
									$.messager.alert('错误提示','请选择功能|对象节点或点击新增功能|');
								} else {
									$.messager.confirm('提示框', '你确定要删除吗?',function(r){
										if(r){
											$.ajax({
												url: '<%=basePath%>sysMenu/deleteSysMenu.do?node=' + node,
														success:function(){
															$.messager.show({
																title:'提示',
																msg:'删除成功',
																timeout:3000,
																showType:'slide'
															});
															$("#zTreeMenu").tree("reload");
															$("#gnca" ).css("display", "none"); 
															$("#anca" ).css("display", "none"); 
														}
											});
										}
									})
								}
							}else{
								var node = $("#objId").val();
								if (node == null || node == "") {
									$.messager.alert('错误提示','请选择功能|对象节点或点击新增功能|');
								} else {
									$.messager.confirm('提示框', '你确定要删除吗?',function(r){
										if(r){
											$.ajax({
												url: '<%=basePath%>sysMenu/deleteSysObj.do?node=' + node,
														success:function(){
															$.messager.show({
																title:'提示',
																msg:'删除成功',
																timeout:3000,
																showType:'slide'
															});
															$("#zTreeMenu").tree("reload");
															$("#gnca" ).css("display", "none"); 
															$("#anca" ).css("display", "none"); 
														}
											});
										}
									})
								}
							}
						});
						$('#zTreeMenu').tree({
							url: '<%=basePath%>sysMenu/findAllSysMenuTree.do',
							parentField:"parentId",
							textFiled:"menuName",
							idFiled:"menuId",
							onClick: function(node){
								if(node.type == "SysMenu"){
									$('#form1').form('load', '<%=basePath%>sysMenu/findUserMenuByMENU_ID.do?node=' + node.menuId);
									$("#gnca" ).css("display", "block"); 
									$("#anca" ).css("display", "none"); 
									cureetObj = 1;
								}else if(node.type == "SysObject"){
									$('#form2').form('load', '<%=basePath%>sysMenu/querySysObj.do?node=' + node.menuId);
									$("#gnca" ).css("display", "none"); 
									$("#anca" ).css("display", "block"); 
									cureetObj = 2;
									$("#objId").val(node.menuId);
								}else{
									$("#gnca" ).css("display", "none"); 
									$("#anca" ).css("display", "none"); 
								}
								$("#menuId").val(node.menuId);
								$('#save').linkbutton('enable');
								$('#del').linkbutton('enable');
								return false;
						    }
						});
					});
				</script>
		</head>
		<body>
			<div class="easyui-layout" style="width:100%;height:450px;">
			<input type="hidden" name="type" id="type" />
				<div data-options="region:'north'" style="height:32px;padding-left:3px;">
					<a href="javascript:void(0)" id="add" style="width:80px" data-options="iconCls:'icon-add'">新增</a>
					<a href="javascript:void(0)" id="save" class="easyui-linkbutton" style="width:68px" data-options="iconCls:'icon-save',disabled:true" >保存</a>
					<a href="javascript:void(0)" id="del" class="easyui-linkbutton" style="width:68px"  disabled:true data-options="iconCls:'icon-remove',disabled:true">删除</a>
				</div>
				<div  id="mm"  style="width:150px;" closed="true">
						<div  id="mm1" >下级功能菜单</div>
						<div  id="mm2" >下级对象</div>
				</div>
				<div data-options="region:'west'" title="功能菜单" style="width:200px;">
					<ul id="zTreeMenu" class="easyui-tree">
				</ul>
				</div>
				<div data-options="region:'center'"  style="padding:10px 0 0 10px;">
					<div id="gnca"  data-options="title:'功能',iconCls:'icon-ok'"  style="display:none">
							 <input type="hidden" name="menuId" id="menuId" />
							<form action="" name="form1" id="form1" data-options="novalidate:true" method="post">
								
								<div style="margin-bottom:6px">
									<input class="easyui-textbox"  style="width:300px" type="text" name="menuName" id="menuName" data-options="label:'功能名称：',required:true" /><span style="color:red;"> *</span>
								</div>
								<div style="margin-bottom:6px">
									<input class="easyui-textbox" style="width:300px" type="text" name="busiCode" id="busiCode" data-options="label:'业务编码：',required:true" /><span style="color:red;"> * </span>
								</div>
								<div style="margin-bottom:6px">
									<select class="easyui-combobox"  label="状态：" name="delFlg" id="delFlg" style="width:300px;"data-options="editable:false,panelHeight:'auto'" >
										<option value="0">启用</option>
										<option value="1">停用</option>
									</select>
								</div>
								<div style="margin-bottom:6px">
									打开方式：
								<input type="radio" name="isPop" id="isPop1" value="N" checked="checked"/>
									跟框架一致
								<input type="radio" name="isPop" id="isPop2" value="Y" />
									window形式弹出
								<input type="radio" name="isPop" id="isPop3" value="O" />
									浏览器形式弹出
								</div>
								<div style="margin-bottom:6px">
									<input class="easyui-textbox"  style="width:300px" type="text" name="menuUrl" id="menuUrl" data-options="label:'功能连接：',required:true"/><span style="color:red;"> * </span>
								</div>
								<div style="margin-bottom:6px">
									<input class="easyui-textbox"  style="width:300px" type="text" name="helpUrl" id="helpUrl" data-options="label:'帮助链接：'"/>
								</div>
							</form>
					</div>
					<div id="anca"  data-options="title:'对象',iconCls:'icon-ok'"  style="display:none">
							<input type="hidden" name="objId" id="objId" />
							<form action="" name="form2" id="form2" data-options="novalidate:true" method="post">
								<div style="margin-bottom:6px">
									<input class="easyui-textbox"  style="width:300px" type="text" name="objCode" id="objCode" data-options="label:'对象编号：',required:true" /><span style="color:red;"> * </span>
								</div>
								<div style="margin-bottom:6px">
									<input class="easyui-textbox"  style="width:300px" type="text" name="objName" id="objName" data-options="label:'对象名称：',required:true" /><span style="color:red;"> * </span>
								</div>
								<div style="margin-bottom:6px">
									<input class="easyui-textbox" style="width:300px" type="text" name="busiCodeObj" id="busiCodeObj" data-options="label:'业务编码：',required:true" /><span style="color:red;"> * </span>
								</div>
							</form>
					</div>
				</div>
			</div>
		</body>
	
	</html>