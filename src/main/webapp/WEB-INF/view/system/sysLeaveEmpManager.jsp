<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>离退休人员基本信息管理</title>
<%@include file="/common/common_easyui.jsp"%>
</head>
<body>
	<!-- 查询表单模块 start -->
	<div id="search"  data-options="region:'north',border:false" style="text-align:left;font-size:12px;padding:5px 0 5px 15px;height:36px">
		<form action="" id="searchForm">
			<select class="easyui-combobox"  id="leaveType" name="leaveType" label="离职类型:" style="width:250px" data-options="editable:false,panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="0">离休</option>
				<option value="1">退休</option>
			</select>
			<input id="name" name="name" class="easyui-textbox"
				data-options="label:'　　姓名:'" style="width: 250px" /> 
			<input id="phone" name="phone" class="easyui-textbox"
				data-options="label:'联系电话:'" style="width: 250px" />
			<a href="#" name="doSearch" id="doSearch"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'"
				style="width: 68px">查询</a> <a href="#" name="reset" id="reset"
				class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
				style="width: 68px;margin-left:5px;">重置</a>
		</form>
	</div>
	<!-- 查询表单模块 end -->
	
	<!-- 列表模块 start -->
	 <div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;margin:0 10px 10px 0;">
		<div id="dg" style="display:block;"></div> 
	</div> 
	<!-- 列表模块 end -->
	
	<!-- 查看窗口模块 start -->
	<div id="dlgWatch" class="easyui-dialog" title="查看在职人员信息" data-options="modal:true" closed="true" style="width:700px;height:460px;display:none;">
		<div id="tabsWatch" class="easyui-tabs" style="width:680px;height:420px;">
			<!-- 人员基本信息模块 start -->
		    <div title="人员基本信息" style="padding:20px 0 0 80px;display:none;" data-options="selected:true">
		    	
		    	<!-- 人员基本信息form模块 start -->
		    	<form action="" id="sysEmpWatchForm" name="emp" >
					<input type="hidden" name="id" id="id" />
				    <input type="hidden" name="stationGrade" id="stationGrade" />
				    <input id="name"  name="name" class="easyui-textbox" data-options="label:'姓名:'" style="width:240px" readonly="readonly"/>
					<select  class="easyui-combobox" id="orgnization" name="orgnization" data-options="label:'单位编制:'"  labelPosition="right" style="width:240px" readonly="readonly">
						<option value="">--请选择--</option>
						<option value="0">行政编制</option>
						<option value="1">事业编制</option>
						<option value="2">合同工</option>
					</select><br/>
					<select  class="easyui-combobox" id="sex" name="sex" data-options="label:'性别:'"  labelPosition="right" style="width:240px" readonly="readonly">
						<option value="">--请选择--</option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>
					<select  class="easyui-combobox" id="stationId" name="stationId" labelPosition="right" style="width:240px" readonly="readonly"
						data-options="label:'岗位:',valueField:'id',textField:'stationGrade',url:'<%=basePath%>sysStation/all.do', onSelect: function(rec){
																																				    $('#stationGrade').val(rec.stationGrade);
																																			   }" >
					</select><br />
					<input  id="cardid"  name="cardid" class="easyui-textbox" data-options="label:'身份证号:'" style="width:240px" readonly="readonly"/>
					<select class="easyui-combotree"  id="deptNOSec" name="deptNO" label="所在部门:" style="width:240px" readonly="readonly">
					</select><br />
					<input  id="useName"  name="useName" class="easyui-textbox" data-options="label:'曾用名:'" style="width:240px" readonly="readonly"/>
					<input  id="job"  name="job" class="easyui-textbox" data-options="label:'职务:'" style="width:240px" readonly="readonly"/><br />
					<input  id="nation"  name="nation" class="easyui-textbox" data-options="label:'民族:'" style="width:240px" readonly="readonly"/>
					<input  id="position"  name="position" class="easyui-textbox" data-options="label:'职称:'" style="width:240px" readonly="readonly"/><br />
					<input  id="pStatus"  name="pStatus" class="easyui-textbox" data-options="label:'政治面貌:'" style="width:240px" readonly="readonly"/>
					<input  id="entryInfo"  name="entryInfo" class="easyui-textbox" data-options="label:'入职方式:'" style="width:240px" readonly="readonly"/><br />
					<input  id="phone"  name="phone" class="easyui-textbox" data-options="label:'联系电话:'" style="width:240px" readonly="readonly"/>
					<input  id="entryDate"  name="entryDate" class="easyui-datebox" data-options="label:'入职时间:'" style="width:240px" required="required" readonly="readonly"/><br />
					<input  id="address"  name="address" class="easyui-textbox" data-options="label:'联系地址:'" style="width:240px" readonly="readonly"/>
					<input  id="email"  name="email" class="easyui-textbox" data-options="label:'电子邮箱:'" style="width:240px" readonly="readonly"/><br />
					<select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:240px"
					data-options="label:'学历:',valueField:'id',textField:'studyName',url:'<%=basePath%>studyType/all.do'" required="required" readonly="readonly">
					</select>
					<input  id="degree"  name="degree" class="easyui-textbox" data-options="label:'学位:'" style="width:240px" required="required" readonly="readonly"/><br />
					<input  id="studySchool"  name="studySchool" class="easyui-textbox" data-options="label:'毕业院校:'" style="width:240px" required="required" readonly="readonly"/>
					<input  id="finishDate"  name="finishDate" class="easyui-datebox" data-options="label:'毕业时间:'" style="width:240px" required="required" readonly="readonly"/><br />
					<input  id="remark"  name="remark" class="easyui-textbox" data-options="label:'备注信息:'" style="width:240px" readonly="readonly"/>
				</form>
				<!-- 人员基本信息form模块 end -->
				
		    </div>
		    <!-- 人员基本信息模块 end -->
		    
		    <!-- 岗位信息 start -->
		    <div title="岗位信息" style="overflow:auto;padding:20px;display:none;">
				<!-- 属于该人员岗位信息的基本信息模块 start -->
				<form action="" id="stationSearchForm">
					<input name="stationName" class="easyui-textbox"
						data-options="label:'岗位:'" style="width: 250px" readonly="readonly"/>
					<input name="stationGrade" class="easyui-textbox"
						data-options="label:'岗位等级:'" style="width: 250px" readonly="readonly"/><br/>
					<input name="job" class="easyui-textbox"
						data-options="label:'职务:'" style="width: 250px" readonly="readonly"/>
					<input name="position" class="easyui-textbox"
						data-options="label:'职称:'" style="width: 250px" readonly="readonly"/><br/>
					<input name="name" class="easyui-textbox"
						data-options="label:'姓名:'" style="width: 250px" readonly="readonly"/>
				</form>
				<!-- 属于该人员岗位信息的基本信息模块 end -->
				
				<!-- 岗位信息列表模块 start -->
				<div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;">
					<div id="stationDg" style="display:block;"></div>  
				</div>
				<!-- 岗位信息列表模块 end -->
		    </div>
		    <!-- 岗位信息 end -->
		    
		    <!-- 学历信息 start -->
		    <div title="学历信息" style="padding:20px;display:none;">
				<!-- 属于该人员学历信息的基本信息模块 start -->
				<form action="" id="studyRecSearchForm">
					<input name="stationName" class="easyui-textbox"
						data-options="label:'岗位:'" style="width: 250px" readonly="readonly"/>
					<input name="eduRec" class="easyui-textbox"
						data-options="label:'学历:'" style="width: 250px" readonly="readonly"/><br/>
					<input name="degree" class="easyui-textbox"
						data-options="label:'学位:'" style="width: 250px" readonly="readonly"/>
					<input name="name" class="easyui-textbox"
						data-options="label:'姓名:'" style="width: 250px" readonly="readonly"/>
				</form>
				<!-- 属于该人员学历信息的基本信息模块 end -->
				
				<!-- 学历信息列表模块 start -->
				<div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;">
					<div id="studyRecDg" style="display:block;"></div>  
				</div>
				<!-- 学历信息列表模块 end -->
		    </div>
		    <!-- 学历信息 end -->
		</div>
	</div>
	<!-- 查看窗口模块 end -->
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		// 		按钮显示
		var objArrayYM = new Array("view");   
		showObj(objArrayYM);//共同方法
		// 查询元素区域
		var $searchForm = $("#searchForm");
		var $doSearch = $("#doSearch");
		var $reset = $("#reset");
		var $dg = $('#dg');
		var $stationDg = $('#stationDg');
		var $studyRecDg = $('#studyRecDg');
		var $sysEmpWatchForm = $("#sysEmpWatchForm");
		var $stationSearchForm = $("#stationSearchForm");
		var $studyRecSearchForm = $("#studyRecSearchForm");
		var $dlgWatch = $("#dlgWatch");
		var $leaveType = $('#leaveType');
		var $name = $('#name');
		var $phone = $('#phone');
		
		// 第一全局变量区域
		var unknow = '';
		
		// 离职人员表格数据参数设置
		var datagrid = $dg.datagrid({
		      url:"<%=basePath%>sysLeaveEmp/findLeaveEmpList.do",
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
				id:'view',
				text : '查看',
				iconCls : 'icon-reload',
				handler : function() {
					watch();
				},
			}],
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
			},
			{
				field : 'empId',
				title : 'empId',
				width : 100,
				hidden : true
			}, {
				field : 'leaveType',
				title : '离退类型',
				width : 140,
				formatter : function(value, row, index) {
					if (value == '0') {
						return "离休";
					} else if (value == '1') {
						return "退休";
					} else {
						return unknow;
					}
				}
			}, {
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
			}, {
				field : 'deptName',
				title : '原单位',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			}, {
				field : 'job',
				title : '原职务',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
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
			}, {
				field : 'leaveDate',
				title : '离退时间',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value.replace("00:00:00.0","") + "'>" + value.replace("00:00:00.0","") + "</span>";
					}
				}
			}, {
				field : 'leaveReason',
				title : '离退原因',
				width : 140,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}
				}
			} ] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 500
		});
		
		//查询
		$doSearch.click(function() {
			$dg.datagrid('load', {
				leaveType:$leaveType.combotree("getValue"), 
				name:$name.textbox("getValue"),
				phone:$phone.textbox("getValue")
			});
		});

		//重置
		$reset.click(function() {
			$searchForm.form('clear');
		});
		
		//弹出查看窗口
		function watch() {
			var rows = $dg.datagrid('getSelections');
			if (rows.length==1) {
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysEmp/loadWithStudy.do" ,
					data: {"id":rows[0].empId},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							// 加载人员基本信息
							$sysEmpWatchForm.form('load',{
								id:data.id,
								deptNO:data.deptNO,
								stationGrade:data.stationGrade,
								name:data.name,
								orgnization:data.orgnization,
								sex:data.sex,
								stationId:data.stationId,
								cardid:data.cardid,
								useName:data.useName,
								job:data.job,
								nation:data.nation,
								position:data.position,
								pStatus:data.pStatus,
								entryInfo:data.entryInfo,
								phone:data.phone,
								entryDate:data.entryDate,
								address:data.address,
								email:data.email,
								eduRec:data.eduRec,
								degree:data.studyRec.degree,
								studySchool:data.studyRec.studySchool,
								finishDate:data.studyRec.finishDate,
								remark:data.remark
							});
							
							// 加载人员基本信息的岗位基本信息
							$stationSearchForm.form('load',{
								stationName:data.stationName,
								stationGrade:data.stationGrade,
								job:data.job,
								position:data.position,
								name:data.name,
							});
							
							// 加载人员基本信息的学位基本信息
							$studyRecSearchForm.form('load',{
								stationName:data.stationName,
								eduRec:data.eduRec,
								degree:data.studyRec.degree,
								name:data.name,
							});

							// 加载岗位变更记录
							//$("span:contains(人员基本信息)").parent().trigger("click");
							$stationDg.datagrid({
							      url:"<%=basePath%>sysStationRec/findStationRecList.do?empId=" + data.id,
								pagination : true,//显示分页  
								rownumbers : true,//显示行号
								pageSize : 10,//分页大小  
								pageList : [ 10, 20, 40, 60 ],//每页的个数  
								fitColumns : true,
								title:'岗位变更记录',
								loadFilter : function(data) {
									//过滤数据
									if (data.page.items == null || data.page.items == "") {
										return {
											total : 0,
											rows : []
										};
									} else {
										var value = {
											total : data.page.recordCount,
											rows : []
										};
										var x = 0;
										for (var i = 0; i < data.page.items.length; i++) {
											value.rows[x] = data.page.items[i];
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
									field : 'newStationName',
									title : '现岗位',
									width : 120
								}, {
									field : 'newStationGrade',
									title : '现岗位等级',
									width : 120
								}, {
									field : 'oldStationName',
									title : '原岗位',
									width : 120
								}, {
									field : 'oldStationGrade',
									title : '原岗位等级',
									width : 120
								}, {
									field : 'alterDate',
									title : '变更时间',
									width : 150
								}, {
									field : 'deptName',
									title : '变更单位',
									width : 120
								}] ],
								height : 240
							});
							var pp = $stationDg.datagrid('getPager');
							$(pp).pagination({
								pageSize : 10,//每页显示的记录条数，默认为10  
								pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
								beforePageText : '第',//页数文本框前显示的汉字  
								afterPageText : '页    共 {pages} 页',
								displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
							});
							
							
							// 加载学历变更记录
							//$("span:contains(人员基本信息)").parent().trigger("click");
							$studyRecDg.datagrid({
							      url:"<%=basePath%>studyRec/findStudyRecList.do?empId=" + data.id,
								pagination : true,//显示分页  
								rownumbers : true,//显示行号
								pageSize : 10,//分页大小  
								pageList : [ 10, 20, 40, 60 ],//每页的个数  
								fitColumns : true,
								title:'学历变更记录',
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
									field : 'studyInfo',
									title : '学习形式',
									width : 120
								}, {
									field : 'eduRec',
									title : '学历',
									width : 120
								}, {
									field : 'studyPro',
									title : '专业',
									width : 120
								}, {
									field : 'studySchool',
									title : '毕业院校',
									width : 120
								}, {
									field : 'statrtDate',
									title : '入学时间',
									width : 160
								}, {
									field : 'finishDate',
									title : '毕业时间',
									width : 160
								}, {
									field : 'reterence',
									title : '证明人',
									width : 120
								}] ],
								height : 240
							});
							var ppp = $studyRecDg.datagrid('getPager');
							$(ppp).pagination({
								pageSize : 10,//每页显示的记录条数，默认为10  
								pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
								beforePageText : '第',//页数文本框前显示的汉字  
								afterPageText : '页    共 {pages} 页',
								displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
							});
						}
						
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
	</script>
</body>
</html>