<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人员基本信息管理</title>
<%@include file="/common/common_easyui.jsp"%>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/jquery.form.min.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:600px;"data-options="fit:true">
		<!-- 部门列表模块 start -->
		<div data-options="region:'west',iconCls:'icon-ok',split:true" title="部门列表" style="width:20%">
	  		<div >
				<ul id="zTreeMenuBusiness" class="easyui-tree">
				</ul>
			</div>
	  	</div>
	  	<!-- 部门列表模块 end -->
	
		<div data-options="region:'center',title:'人员管理',iconCls:'icon-ok'">
			<div class="emp_manage">
		
				<!-- 查询表单模块 start -->
				<div id="search"  data-options="region:'north',border:false" style="text-align:left;padding:5px 0 0 15px;">
					<form action="" id="searchForm">
						<select class="easyui-combobox"  id="status" name="status" label="人员状态:" style="width:250px" data-options="editable:false,panelHeight:'auto'" >
							<option value="">--请选择--</option>
							<option value="0">在职</option>
							<option value="1">离职</option>
							<option value="2">辞退</option>
							<option value="3">离退休</option>
							<option value="4">试用期</option>
							<option value="5">不在职</option>
						</select>
						<input id="name" name="name" class="easyui-textbox"data-options="label:'　　姓名:'" style="width: 250px" /> 
						<input id="job" name="job" class="easyui-textbox"data-options="label:'　　职务:'" style="width: 250px" />
						<a name="doSearch" id="doSearch"class="easyui-linkbutton" data-options="iconCls:'icon-search'"style="width: 68px">查询</a>
					    <a name="reset" id="reset"class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width: 68px;margin-left:5px;">重置</a>
					</form>
				</div>
				<!-- 查询表单模块 end -->
				<div data-options="region:'center',border:false" style="text-align:left;padding:5px 0 0 5px;">
						<table id="dg" style="display:block;width:100%">  
						</table>
		     	</div>
	     	</div>
		</div>
	</div>
	
	<!-- 编辑窗口模块 start -->
	<div id="dlg_edit" class="easyui-dialog" data-options="modal:true, buttons:'#dlg-btns'" closed="true" title="新增入职人员信息" style="width: 600px;display: none; height: 380px; padding: 10px">
		<form action="" id="sysEmpForm" name="emp">
		     <input type="hidden" name="id" id="id" />
		     <input type="hidden" name="stationGrade" id="stationGrade" />
		     <span class="spanStyle">姓名:</span><input  id="e_name"  name="name" class="easyui-textbox"  style="width:150px" required="required"/><span style="color:red;">*</span>
		     <span class="spanStyle">单位编制:</span><select  class="easyui-combobox" id="orgnization" name="orgnization" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" >
				<option value="">--请选择--</option>
				<option value="0">行政编制</option>
				<option value="1">事业编制</option>
				<option value="2">合同工</option>
			</select><br/>
			<span class="spanStyle">性别:</span><select  class="easyui-combobox" id="sex" name="sex" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" required="required" >
				<option value=""></option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select><span style="color:red;">*</span>
			<span class="spanStyle">出生日期:</span><input  id="birthday"  name="birthday" class="easyui-datebox" data-options="label:''" style="width:150px" required="required"/><span style="color:red;">*</span><br />
			<span class="spanStyle">出生地:</span><input  id="birthPlace"  name="birthPlace" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
			<span class="spanStyle">岗位:</span><select  class="easyui-combobox" id="stationId" name="stationId" labelPosition="right" style="width:150px"
				data-options="editable:false,valueField:'stationCode',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
			</select><br />
			 <span class="spanStyle">所在单位:</span><select class="easyui-combotree"  id="unitNOSec" name="unitNO"  style="width:150px" data-options="editable:false" >
			</select><span style="color:red;">*</span>
			<span class="spanStyle">身份证号:</span><input  id="cardid"  name="cardid" class="easyui-textbox"  style="width:150px"  data-options="required:true,validType:'idcared'"/><span style="color:red;">*</span></br>
			<span class="spanStyle">所在部门:</span><select class="easyui-combotree"  id="deptNOSec" name="deptNO" style="width:150px"data-options="editable:false" >
			</select><span style="color:white;">*</span>
			<span class="spanStyle">曾用名:</span><input  id="useName"  name="useName" class="easyui-textbox" style="width:150px"/></br>
			<span class="spanStyle">职务:</span><input  id="job"  name="job" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
			<span class="spanStyle">职级:</span><input  id="jobRank"  name="jobRank" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span></br>
			<span class="spanStyle">民族:</span><input  id="nation"  name="nation" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
			<span class="spanStyle">籍贯:</span><input  id="nativePlace"  name="nativePlace" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
			<span class="spanStyle">职称:</span><select  class="easyui-combobox" id="position" name="position" labelPosition="right" style="width:150px"
				data-options="editable:false,valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'"  >
			</select><span style="color:white;">*</span>
			<span class="spanStyle">政治面貌:</span><select  class="easyui-combobox" id="pStatus" name="pStatus" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" >
				<option value="">--请选择--</option>
				<option value="0">党员</option>
				<option value="1">团员</option>
				<option value="2">群众</option>
			</select><span style="color:white;">*</span><br />
			<span class="spanStyle">入党（团）时间:</span><input  id="entryPartyDate"  name="entryPartyDate" class="easyui-datebox" style="width:150px" /><span style="color:white;">*</span>
			<span class="spanStyle">入职方式:</span><input  id="entryInfo"  name="entryInfo" class="easyui-textbox" style="width:150px" /><br />
			<span class="spanStyle">参加工作时间:</span><input  id="workDate"  name="workDate" class="easyui-datebox" data-options="label:''" style="width:150px" /><span style="color:white;">*</span>
			<span class="spanStyle">联系电话:</span><input  id="phone"  name="phone" class="easyui-textbox" style="width:150px"/><br />
			<span class="spanStyle">入职时间:</span><input  id="entryDate"  name="entryDate" class="easyui-datebox" style="width:150px" /><span style="color:white;">*</span>
			<span class="spanStyle">身份信息:</span><select  class="easyui-combobox" id="identityInfo" name="identityInfo" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" >
				<option value="">--请选择--</option>
				<option value="0">干部</option>
				<option value="1">聘干</option>
				<option value="2">工人</option>
			</select><br />
			<span class="spanStyle">员工状态:</span><select  class="easyui-combobox" id="status" name="status" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" >
				<option value="">--请选择--</option>
				<option value="0">在职</option>
				<option value="1">离职</option>
				<option value="2">辞退</option>
				<option value="3">离退休</option>
				<option value="4">试用期</option>
				<option value="5">不在职</option>
			</select><span style="color:white;">*</span>
			<span class="spanStyle">联系地址:</span><input  id="address"  name="address" class="easyui-textbox" style="width:150px"/><br />
			<span class="spanStyle">电子邮箱:</span><input  id="email"  name="email" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
			<span class="spanStyle">第一学历:</span><select  class="easyui-combobox" id="eduFirstRec" name="eduFirstRec" labelPosition="right" style="width:150px" data-options="editable:false,panelHeight:'auto',valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'"  required="required">
			</select><span style="color:red;">*</span>
			<span class="spanStyle">最高学历:</span><select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:150px" data-options="editable:false,panelHeight:'auto',valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'"  required="required">
			</select><span style="color:red;">*</span>
			<span class="spanStyle">学位:</span><input  id="degree"  name="degree" class="easyui-textbox" style="width:150px" /><br />
			<span class="spanStyle">毕业院校:</span><input  id="studySchool"  name="studySchool" class="easyui-textbox" style="width:150px" required="required"/><span style="color:red;">*</span>
			<span class="spanStyle">毕业时间:</span><input  id="finishDate"  name="finishDate" class="easyui-datebox" style="width:150px" /><br />
			<span class="spanStyle">备注信息:</span><input  id="remark"  name="remark" class="easyui-textbox" style="width:150px"/>
		</form>
	</div>
	<div id="dlg-btns">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">提交</a>
	</div>
	<!-- 编辑窗口模块 end -->
	
	<!-- 查看窗口模块 start -->
	<div id="dlgWatch" class="easyui-dialog" title="查看在职人员信息" data-options="modal:true" closed="true" style="width:700px;height:460px;display:none;">
		<div id="tabsWatch" class="easyui-tabs" style="width:680px;height:420px;">
			<!-- 人员基本信息模块 start -->
		    <div title="人员基本信息" style="padding:20px;display:none;" data-options="selected:true">
		    	
		    	<!-- 人员基本信息form模块 start -->
		    	<form action="" id="sysEmpWatchForm" name="emp" >
					<input type="hidden" name="id" id="id" />
				    <input type="hidden" name="stationGrade" id="stationGrade" />
				    <span class="spanStyle">姓名:</span><input id="name"  name="name" class="easyui-textbox"  style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">单位编制:</span><select  class="easyui-combobox" id="orgnization" name="orgnization" labelPosition="right" style="width:150px" readonly="readonly">
						<option value="">--请选择--</option>
						<option value="0">行政编制</option>
						<option value="1">事业编制</option>
						<option value="2">合同工</option>
					</select><br/>
					<span class="spanStyle">性别:</span><select  class="easyui-combobox" id="sex" name="sex" labelPosition="right" style="width:150px" readonly="readonly">
						<option value=""></option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>&nbsp;&nbsp;
					<span class="spanStyle">出生日期:</span><input  id="w_birthday"  name="birthday" class="easyui-datebox" data-options="label:''" style="width:150px" readonly="readonly"/><br />
					<span class="spanStyle">出生地:</span><input  id="birthPlace"  name="birthPlace" class="easyui-textbox" data-options="label:''" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">岗位:</span><select  class="easyui-combobox" id="stationId" name="stationId" labelPosition="right" style="width:150px" 
						data-options="valueField:'stationCode',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" readonly="readonly">
					</select><br/>
					<span class="spanStyle">身份证号:</span><input  id="cardid"  name="cardid" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">所在单位:</span><select class="easyui-combotree"  id="unitName" name="unitName" style="width:150px" readonly="readonly">
					</select><br/>
					<span class="spanStyle">所在部门:</span><select class="easyui-combotree"  id="deptName" name="deptName" style="width:150px" readonly="readonly">
					</select>&nbsp;&nbsp;
					<span class="spanStyle">曾用名:</span><input  id="useName"  name="useName" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">职务:</span><input  id="job"  name="job" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">职级:</span><input  id="jobRank"  name="jobRank" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">名族:</span><input  id="nation"  name="nation" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">籍贯:</span><input  id="nativePlace"  name="nativePlace" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">职称:</span><input  id="positionName"  name="positionName" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">政治面貌:</span><select  class="easyui-combobox" id="pStatus" name="pStatus" labelPosition="right" style="width:150px" readonly="readonly">
						<option value="">--请选择--</option>
						<option value="0">党员</option>
						<option value="1">团员</option>
						<option value="2">群众</option>
					</select><br/>
					<span class="spanStyle">入党（团）时间:</span><input  id="entryPartyDate"  name="entryPartyDate" class="easyui-datebox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">参加工作时间:</span><input  id="workDate"  name="workDate" class="easyui-datebox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">入职方式:</span><input  id="entryInfo"  name="entryInfo" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">联系电话:</span><input  id="phone"  name="phone" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">入职时间:</span><input  id="entryDate"  name="entryDate" class="easyui-datebox" style="width:150px" required="required" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">身份信息:</span><select  class="easyui-combobox" id="identityInfo" name="identityInfo" labelPosition="right" style="width:150px" readonly="readonly" >
						<option value="">--请选择--</option>
						<option value="0">干部</option>
						<option value="1">聘干</option>
						<option value="2">工人</option>
					</select><br/>
					<span class="spanStyle">员工状态:</span><select  class="easyui-combobox" id="status" name="status" labelPosition="right" style="width:150px" readonly="readonly">
					<option value="">--请选择--</option>
					<option value="0">在职</option>
					<option value="1">离职</option>
					<option value="2">辞退</option>
					<option value="3">离退休</option>
					<option value="4">试用期</option>
					<option value="5">不在职</option>
					</select>&nbsp;&nbsp;
					<span class="spanStyle">联系地址:</span><input  id="address"  name="address" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">电子邮箱:</span><input  id="email"  name="email" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">第一学历:</span><select  class="easyui-combobox" id="eduFirstRec" name="eduFirstRec" labelPosition="right" style="width:150px"
					data-options="valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'" readonly="readonly">
					</select><br/>
					<span class="spanStyle">最高学历:</span><select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:150px"
					data-options="valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'" readonly="readonly">
					</select>&nbsp;&nbsp;
					<span class="spanStyle">学位:</span><input  id="degree"  name="degree" class="easyui-textbox" style="width:150px" readonly="readonly"/><br />
					<span class="spanStyle">毕业院校:</span><input  id="studySchool"  name="studySchool" class="easyui-textbox" style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
					<span class="spanStyle">毕业时间:</span><input  id="finishDate"  name="finishDate" class="easyui-datebox" style="width:150px" readonly="readonly"/><br />
					<span class="spanStyle">备注信息:</span><input  id="remark"  name="remark" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					<div id="liGangDiv" style="display:none;">
						<span class="spanStyle">预计返岗时间:</span><input  id="returnDate" name="returnDate" class="easyui-datebox"  style="width:150px" readonly="readonly"/><br />
					</div>
					<div id="liTuiXiuDiv" style="display:none;">
						<span class="spanStyle">退休级别:</span><input id="retireLevel" name="retireLevel" class="easyui-textbox"  style="width:150px" readonly="readonly"/>&nbsp;&nbsp;
						<span class="spanStyle">享受待遇:</span><input id="retirePay" name="retirePay" class="easyui-textbox"  style="width:150px" readonly="readonly" /><br />
					</div>
				</form>
				<!-- 人员基本信息form模块 end -->
				
		    </div>
		    <!-- 人员基本信息模块 end -->
		    
		    <!-- 岗位信息 start -->
		    <div title="岗位信息" style="overflow:auto;padding:20px;display:none;">
				<!-- 属于该人员岗位信息的基本信息模块 start -->
				<form action="" id="stationSearchForm">
<!-- 					<input name="stationName" class="easyui-textbox" -->
<!-- 						data-options="label:'岗位:'" style="width: 250px" readonly="readonly"/> -->
					<select  class="easyui-combobox" id="stationId" name="stationName" labelPosition="right" style="width:240px" readonly="readonly"
					data-options="label:'岗位:',valueField:'stationCode',textField:'stationName',url:'<%=basePath%>sysStation/all.do'" >
					</select>
					<input name="stationGrade" class="easyui-textbox"
						data-options="label:'岗位等级:'" style="width: 240px" readonly="readonly"/><br/>
					<input name="job" class="easyui-textbox"
						data-options="label:'职务:'" style="width: 240px" readonly="readonly"/>
					<select  class="easyui-combobox" id="position" name="position" labelPosition="right" style="width:240px"
					data-options="label:'职称:',editable:false,valueField:'positionId',textField:'positionName',url:'<%=basePath%>sysPositionType/loadPositionType.do'"  >
					</select><br/>
<!-- 					<input name="position" class="easyui-textbox" -->
<!-- 						data-options="label:'职称:'" style="width: 250px" readonly="readonly"/><br/> -->
					<input name="name" class="easyui-textbox"
						data-options="label:'姓名:'" style="width: 240px" readonly="readonly"/>
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
<!-- 					<input name="eduRec" class="easyui-textbox" -->
<!-- 						data-options="label:'学历:'" style="width: 250px" readonly="readonly"/><br/> -->
					<select  class="easyui-combobox" id="eduRec" name="eduRec" labelPosition="right" style="width:250px"
					data-options="label:'学历',editable:false,panelHeight:'auto',valueField:'id',textField:'studyName',url:'<%=basePath%>studyType/all.do'" readonly="readonly">
					</select><br/>
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
		     <!-- 附件信息 start -->
		    <div title="附件信息" style="overflow:auto;padding:20px;display:none;">
		       	<div id="sear2" data-options="region:'north',border:false" style="text-align:left;padding:0 0 0;">
					<!-- 属于该人员岗位信息的基本信息模块 start -->
					<form action="" id="searchFile">
					<input id="s_fileName" name="fileName" class="easyui-textbox" data-options="label:'文件名:'" style="width: 400px" />
						<br/>
						<input  id="s_searchDateStart"  name="searchDateStart" class="easyui-datebox" data-options="label:'上传时间:'" style="width:230px" />~
						<input  id="s_searchDateEnd"  name="searchDateEnd" class="easyui-datebox" data-options="label:''" style="width:150px" />
					<a name="fileSearch" id="fileSearch"
							class="easyui-linkbutton" data-options="iconCls:'icon-search'"
							style="width: 68px">查询</a>
					<a name="fileReset" id="fileReset"
							class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
							style="width: 68px;margin-left:5px;">重置</a>
					</form>
					
				</div>
				 <div data-options="region:'center',border:false" style="text-align:left;padding:5px 0 0;">
		 				  <!-- 列表模块 start -->
				    	<!-- <table id="dgFile" style="display:block;">  
				        </table> -->
				        <div id="dgFile" style="display:block;" style="width:80%"></div> 
	        			<!-- 列表模块 end -->
				  </div>
		    </div>
		    <!-- 附件信息 end -->
		</div>
	</div>
	<!-- 查看窗口模块 end -->
	
	 <!-- 新增附件 start -->
 	<div id="addFileDiv" class="easyui-dialog" title="新增附件" data-options="modal:true, buttons:'#file-btns'" closed="true" style="width:500px;height:200px;display:none;">
   		<div class="easyui-layout" data-options="fit:true">
		
			<form method="post" target="_blank" action="<%=basePath%>sysEmp/uploadFile.do" id="addFileForm" name="addFileForm" enctype="multipart/form-data">
				<input type="hidden" name="empId" id="f_empId" />
				<input class="easyui-filebox" id="file" name="file"  label="文件:" labelPosition="left" data-options="prompt:'请选择文件',required:true,buttonText:'选择文件'" style="width:300px" /><span style="color:red;"> * </span><br />
				<input class="easyui-textbox" id="fileName" name="fileName" data-options="label:'文件名:',required:true" style="width:300px"/><span style="color:red;"> * </span><br />
				<input  id="f_remark"  name="remark" class="easyui-textbox" data-options="label:'备注信息:'" style="width:400px"/>
			</form>
 		</div>
 		<!-- <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="fileSubmit()" style="width:80px">提交</a>
		</div> -->
 	</div>
 	<div id="file-btns">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="fileSubmit()">提交</a>
	</div>
	 <!-- 新增附件 end -->
	 
	<!-- 导入数据 start -->
 	<div id="daoRuExcel" class="easyui-dialog" title="导入数据" data-options="modal:true, buttons:'#excel-btns'" closed="true" style="width:500px;height:200px;display:none;padding: 20px 0 0 20px;">
   		<div class="easyui-layout" data-options="fit:true">
			<form method="post" target="_blank" action="<%=basePath%>sysEmp/daoRuExcel.do" id="excelForm" name="excelForm" enctype="multipart/form-data">
				<input class="easyui-filebox" id="fileD" name="fileD"  label="文件:" labelPosition="left" data-options="prompt:'请选择文件',required:true,buttonText:'选择文件'" style="width:300px" /><span style="color:red;"> * </span><br />
				<input class="easyui-textbox" id="fileNameD" name="fileNameD" data-options="label:'文件名:',required:true,validType:'excel'" style="width:300px"/><span style="color:red;"> * </span><br />
			</form>
 		</div>
 	</div>
 	<div id="excel-btns">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="excelSubmit()">提交</a>
	</div>
	<!-- 导入数据 end -->
	 
	 
	
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		// 	按钮显示
		var objArrayYM = new Array("add","edit","view","del","daoChu","daoRu");   
		showObj(objArrayYM);//共同方法
		var _zTreeMenuBusiness = $('#zTreeMenuBusiness');
		// 当前部门编号
		var curDeptNo = null;
	
		// 查询元素区域
		var _searchForm = $("#searchForm");
		var _doSearch = $("#doSearch");
		var _reset = $("#reset");
		var _dg = $('#dg');
		var _dgFile = $('#dgFile');
		var _stationDg = $('#stationDg');
		var _studyRecDg = $('#studyRecDg');
		var _sysEmpForm = $("#sysEmpForm");
		var _sysEmpWatchForm = $("#sysEmpWatchForm");
		var _stationSearchForm = $("#stationSearchForm");
		var _studyRecSearchForm = $("#studyRecSearchForm");
		var _dlgEdit = $("#dlg_edit");
		var _dlgWatch = $("#dlgWatch");
		var _tabsWatch = $("#tabsWatch");
// 		var _deptNO = $("#deptNO");
		var _status = $("#status");
		var _name = $("#name");
		var _job = $("#job");
		var _position = $("#position");
		var _deptNOSec = $("#deptNOSec");
		var _unitNOSec = $("#unitNOSec");
		var _stationId = $("#stationId");
		var _stationIdHid = $("#stationIdHid");
		var _stationGrade = $("#stationGrade");
		
		
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
					deptNO:curDeptNo
				}); 
		    },
		    onLoadSuccess: function(node, data) {
// 		    	_zTreeMenuBusiness.tree('insert', {
// 		    		before: _zTreeMenuBusiness.tree('getRoot').target,
// 		    		data: [{
// 		    			orgName:"所有部门"
// 		    		}]
// 		    	});
		    }
		});
		
		var hasSec = "${param.hasSec}";
		
		
		// 人员表格数据参数设置
		var datagrid = _dg.datagrid({
			
		    url:"<%=basePath%>sysEmp/findEmpList.do",
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
			toolbar : hasSec ? [{
				text : '确定',
				iconCls : 'icon-add',
				handler : function() {
					sure();
				},
			}] : [  {
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
			}, {
				id:'view',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					watch();
				},
			},  {
				id:'del',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			},{   
				  id:'daoChu',
		          text: '导出',  
		          iconCls: 'icon-print',  
		          handler: function() {  
		        	  print();  
		          },
		      },{  
		    	  id:'daoRu',
		          text: '导入',  
		          iconCls: 'icon-undo',  
		          handler: function() {  
		        	  daoRuExcel();  
		          },
		      }
			],
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
				field : 'name',
				title : '姓名',
				width : 20,
				formatter: function (value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
				 	}	 
	            }
			} ,{
				field : 'unitName',
				title : '所在单位',
				width : 80,
				formatter: function (value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
				 	}	 
	            }
			},{
				field : 'deptName',
				title : '所在部门',
				width : 80,
				formatter: function (value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
				 	}	 
	            }
			}, {
				field : 'sex',
				title : '性别',
				width : 15,
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
				field : 'stationName',
				title : '岗位',
				width : 80,
				formatter: function (value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
				 	}	 
	            }
			}, {
				field : 'job',
				title : '职务',
				width : 50,
				formatter: function (value) {
					 if(value){
			                return "<span title='" + value + "'>" + value + "</span>";
					 }	           
				}
			}, {
				field : 'positionName',
				title : '职称',
				width : 50,
				 formatter: function (value) {
					 if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					 }
	            }
			}, {
				field : 'entryDate',
				title : '入职时间',
				width : 35,
				formatter : function(value, row, index) {
					if(value){
						return "<span title='" + value.replace("00:00:00.0", "") + "'>" + value.replace("00:00:00.0", "") + "</span>";				
					}
				}
			}, {
				field : 'phone',
				title : '联系电话',
				width : 40,
				formatter: function (value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
				 	}	 
	            }
			}, {
				field : 'status',
				title : '状态',
				width : 20,
				formatter : function(value, row, index) {
					if (value == '0') {
						return "在职";
					} else if (value == '1') {
						return "离职";
					} else if (value == '2') {
						return "辞退";
					} else if (value == '3') {
						return "离退休";
					} else if (value == '4') {
						return "试用期";
					} else if (value == '5') {
						return "不在职";
					} else {
						return unknow;
					}
				}
			}] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 500
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
				deptNO:curDeptNo,
				status:_status.combotree("getValue"), 
				name:_name.textbox("getValue"),
				job:_job.textbox("getValue")
			});
		});
		
		//查询
		$("#fileSearch").click(function() {
			$("#dgFile").datagrid('load', {
				fileName:$("#s_fileName").textbox("getValue"),
				searchDateStart:$("#s_searchDateStart").textbox("getValue"),
				searchDateEnd:$("#s_searchDateEnd").textbox("getValue")
			});
		});
		
		//重置
		_reset.click(function() {
			_searchForm.form('clear');
			curDeptNo = null;
			_dg.datagrid('load', {
				deptNO:curDeptNo,
				status:_status.combotree("getValue"), 
				name:_name.textbox("getValue"),
				job:_job.textbox("getValue")
			});
		});
		
// 		$("#file").filebox({
// 			onChange:function() {
// 				alert(11)
// 				var path;
// 			    path=$(this).val();      
// 			    var name;
// 			    name=path.split('\\'); 
// 			    var bb=name[name.length-1];             
// 			    var timestamp = Date.parse(new Date());
// 			    $("#fileName").textbox('setValue',bb);
// 			    $('#fileName').textbox('textbox').attr('readonly',true);
// 	 			$("input",$("#fileName").next("span")).val(bb);
// 			}
// 		}); 
		
		$(function(){
			$("input",$("#file").next("span")).change(function(){
				var path;
			    path=$(this).val();      
			    var name;
			    name=path.split('\\'); 
			    var bb=name[name.length-1];             
			    var timestamp = Date.parse(new Date());
			    $("#fileName").textbox('setValue',bb);
			    $('#fileName').textbox('textbox').attr('readonly',true);
			});
			
			$("input",$("#fileD").next("span")).change(function(){
				var path;
			    path=$(this).val();      
			    var name;
			    name=path.split('\\'); 
			    var bb=name[name.length-1];             
			    var timestamp = Date.parse(new Date());
			    $("#fileNameD").textbox('setValue',bb);
			    $('#fileNameD').textbox('textbox').attr('readonly',true);
			});
		});
	
		
	
		
		
		//所在单位的树状展示
		_unitNOSec.combotree({
		    url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	businessId=node.businessId;
		    	
		    },
		    required: true
		});
		
		
		//所在部门的树状展示
		_deptNOSec.combotree({
			url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	/* // 将部门id传递到form中便于后端保存
		    	_deptNOHid.val(node.businessId); */
		    },
		    required: false
		});
		
		//弹窗添加窗口
		function add(){
			_sysEmpForm.form("clear");
			// 将岗位设置为不可编辑
			_stationId.combobox({ disabled: false }); 
			// 将职称设置为不可编辑
			_position.textbox({ disabled: false }); 
			$('#dg').datagrid('clearSelections'); 
			_dlgEdit.dialog({title: "新增入职人员信息"});
			
			_dlgEdit.dialog("open");
		}
		_sysEmpForm.form("clear");
		//弹出更新窗口
		function update(){
			_sysEmpForm.form("clear");
			var rows = _dg.datagrid('getSelections');
			if(rows.length==1){
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysEmp/loadWithStudy.do" ,
					data: {"id":rows[0].id},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							_sysEmpForm.form('load',{
								id:data.id,
								deptNO:data.deptNO,
								unitNO:data.unitNO,
								birthday:data.birthday,
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
								status:data.status,
								email:data.email,
								eduRec:data.eduRec,
								degree:data.studyRec.degree,
								studySchool:data.studyRec.studySchool,
								finishDate:data.studyRec.finishDate,
								remark:data.remark,
								entryPartyDate:data.entryPartyDate,
								workDate:data.workDate,
								identityInfo : data.identityInfo,
								nativePlace : data.nativePlace,
								birthPlace : data.birthPlace,
								jobRank : data.jobRank,
								eduFirstRec : data.eduFirstRec
								
							});
							// 将岗位设置为不可编辑
							_stationId.combobox({ disabled: true }); 
							// 将职称设置为不可编辑
							_position.textbox({ disabled: true }); 
							
							_stationId.combobox("setValue", data.stationId); 
							_deptNOSec.combotree("setValue", data.deptNO);
							
							_dlgEdit.dialog({title: "修改入职人员信息"});
							_dlgEdit.dialog("open");
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
		
		//弹出查看窗口
		function watch() {
			var rows = _dg.datagrid('getSelections');
			if (rows.length==1) {
				if(rows[0].status=="5"){//人员状态5为不在职  显示预计返岗时间
					document.getElementById("liGangDiv").style.display="block";
				   	document.getElementById("liTuiXiuDiv").style.display="none";
				}else if(rows[0].status=="3"){//人员状态3为离退休，显示退休级别和享受待遇
					document.getElementById("liGangDiv").style.display="none";
				   	document.getElementById("liTuiXiuDiv").style.display="block";
				}else{
					document.getElementById("liGangDiv").style.display="none";
				   	document.getElementById("liTuiXiuDiv").style.display="none";
				}
				if(rows[0].status!="0"){//如果人员状态不是在职  查询不同状态的额外人员信息
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysEmp/getEmpExtraInfo.do?id=" + rows[0].id ,
						dataType: 'json',
						success: function(data){
							if(rows[0].status=="5"){//不在职人员跟带预计返岗时间
								$("#returnDate").datebox('setValue',data.info6);
							}else if(rows[0].status=="3"){//离退休人员跟带退休级别和退休待遇
								$("#retireLevel").textbox('setValue',data.info1);
								$("#retirePay").textbox('setValue',data.info2);
							}
						} ,
						error: function(){
							$.messager.alert('错误提示','失败');
						}
					})
				}
				$("#sysEmpWatchForm").form("clear");
				$.ajax({
					type: 'POST',
					url: "<%=basePath%>sysEmp/loadWithStudy.do" ,
					data: {"id":rows[0].id},
					dataType: 'json',
					success: function(data){
						if (data != null) {
							// 加载人员基本信息
							_sysEmpWatchForm.form('load',{
								id:data.id,
								deptNO:data.deptNO,
								deptName:data.deptName,
								unitName:data.unitName,
								stationGrade:data.stationGrade,
								name:data.name,
								orgnization:data.orgnization,
								sex:data.sex,
								birthday:data.birthday,
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
								identityInfo:data.identityInfo,
								entryPartyDate:data.entryPartyDate,
								workDate:data.workDate,
								remark:data.remark,
								status:data.status,
								positionName:data.positionName,
								nativePlace : data.nativePlace,
								birthPlace : data.birthPlace,
								jobRank : data.jobRank,
								eduFirstRec : data.eduFirstRec
							});
							
							// 加载人员基本信息的岗位基本信息
							_stationSearchForm.form('load',{
								stationName:data.stationName,
								stationGrade:data.stationGrade,
								job:data.job,
								position:data.position,
								name:data.name,
							});
							
							// 加载人员基本信息的学位基本信息
							_studyRecSearchForm.form('load',{
								stationName:data.stationName,
								eduRec:data.eduRec,
								degree:data.studyRec.degree,
								name:data.name,
							});

							// 加载岗位变更记录
							//$("span:contains(人员基本信息)").parent().trigger("click");
							_stationDg.datagrid({
							      url:"<%=basePath%>sysStationRec/findStationRecList.do?empId=" + data.id,
								pagination : true,//显示分页  
								rownumbers : true,//显示行号
								pageSize : 10,//分页大小  
								pageList : [ 10, 20, 40, 60 ],//每页的个数  
								fitColumns : true,
								title:'岗位变更记录',
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
									width : 160
								}, {
									field : 'deptName',
									title : '变更单位',
									width : 160
								}] ],
								height : 265
							});
							var pp = _stationDg.datagrid('getPager');
							$(pp).pagination({
								pageSize : 10,//每页显示的记录条数，默认为10  
								pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
								beforePageText : '第',//页数文本框前显示的汉字  
								afterPageText : '页    共 {pages} 页',
								displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
							});
							
							
							var datagridFile = $("#dgFile").datagrid({  
							       url:"<%=basePath%>sysEmp/findFileList.do?empId="+ rows[0].id,
							       pagination:true,//显示分页  
							       rownumbers:true,//显示行号
							       pageSize:10,//分页大小  
							       pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
							      // fit:true,//自动补全  
							       fitColumns:true, 
							       			loadFilter : function(data){
							       				 //过滤数据
							       				 var value={
							       				 total:data.recordCount,
							       				 rows:[]
							       				 };
							       				 if(data.items){
								       				 var x=0;
								       				 for (var i = 0; i < data.items.length; i++) {  
								       					 value.rows[x]=data.items[i];
								       					 x++;
								       				 }
							       				 }
							       				 return value;
							       			},
							        toolbar: [{  
								           text: '新增',  
								           iconCls: 'icon-add',  
								           handler: function() {
								        	   addFile();  
								           },
								       },{
								    	   text: '删除',  
								           iconCls: 'icon-remove',  
								           handler: function() {
								        	   delFile();  
								           }  
								       }],
							       columns:[[      //每个列具体内容  
							                { field:'ck',checkbox:true },
							                {field:'fileId',title:'fileId',width:100,hidden:true},
							                {field:'fileName',title:'文件名',width:120,
							                	formatter: function (value) {
												if(value){
									                return "<span title='" + value + "'>" + value + "</span>";
											 	}	 
								            }},
							                {field:'fileFormat',title:'文件格式',width:50,
							                	formatter: function (value) {
							    					if(value){
							    		                return "<span title='" + value + "'>" + value + "</span>";
							    				 	}	 
							    	            }},
							                {field:'uploadPer',title:'上传人',width:50,
							                	formatter: function (value) {
							    					if(value){
							    		                return "<span title='" + value + "'>" + value + "</span>";
							    				 	}	 
							    	            }},
							                {field:'remark',title:'备注',width:50,
							                	formatter: function (value) {
							    					if(value){
							    		                return "<span title='" + value + "'>" + value + "</span>";
							    				 	}	 
							    	            }},
							                {field:'uploadTime',title:'上传时间',width:100,
							    	            	formatter: function (value) {
							    						if(value){
							    			                return "<span title='" + value.replace(".0","") + "'>" + value.replace(".0","") + "</span>";
							    					 	}	 
							    		            }
							    			}
							                ,{field:'operate',title:'操作',width:40,
							                	formatter:function(value, row, index){
							                		var str = '<a href="<%=basePath%>sysEmp/downloadFile.do?fileId='+row.fileId+'"' +'class="easyui-linkbutton" >下载</a>';
													return str;
							            		  }
							                },
							            	 ]],
							             singleSelect: true,
							  	         selectOnCheck: true,
							  	         checkOnSelect: true,
							  	         height:300,
							});
							var pppp = $("#dgFile").datagrid('getPager');
							$(pppp).pagination({
								pageSize : 10,//每页显示的记录条数，默认为10  
								pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
								beforePageText : '第',//页数文本框前显示的汉字  
								afterPageText : '页    共 {pages} 页',
								displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
							});
							
							// 加载学历变更记录
							//$("span:contains(人员基本信息)").parent().trigger("click");
							_studyRecDg.datagrid({
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
									width : 160,
			    	            	formatter: function (value) {
			    						if(value){
			    			                return "<span title='" + value.replace("00:00:00.0","") + "'>" + value.replace("00:00:00.0","") + "</span>";
			    					 	}	 
			    		            }
								}, {
									field : 'finishDate',
									title : '毕业时间',
									width : 160,
			    	            	formatter: function (value) {
			    						if(value){
			    			                return "<span title='" + value.replace("00:00:00.0","") + "'>" + value.replace("00:00:00.0","") + "</span>";
			    					 	}	 
			    		            }
								}, {
									field : 'reterence',
									title : '证明人',
									width : 120
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
								}] ],
								height : 290
							});
							var ppp = _studyRecDg.datagrid('getPager');
							$(ppp).pagination({
								pageSize : 10,//每页显示的记录条数，默认为10  
								pageList : [ 10, 20, 40, 60 ],//可以设置每页记录条数的列表  
								beforePageText : '第',//页数文本框前显示的汉字  
								afterPageText : '页    共 {pages} 页',
								displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
							});
						}
						_dlgWatch.dialog("open");
						//默认初始化显示人员信息
						$("#tabsWatch").tabs("select",0);
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
		
		 function callbackUrl(value,row,index){
// 			 var row = $("#dgFile").datagrid('getSelections');
			  	return	'<a  href="<%=basePath%>sysEmp/downloadFile.do?fileId='+row.fileId+'"'+'>'+ "下载" + '</a>';
		  }
		
		//删除
		function del(){
			var rows = _dg.datagrid('getSelections');
		    var id="";
		    var name="";
		    for(var i = 0; i < rows.length; i++){
		     	//获取选中节点的值
		    	id += rows[i].id + ",";
		     	name += rows[i].name + ","
		    }
		    name = name.substring(0,name.length-1);
			if(rows.length > 0){
				$.messager.confirm('提示框', '你确定要删除吗?', function(r){
					if(r){
						$.ajax({
							url: '<%=basePath%>sysEmp/delete.do?id=' + id,
							success:function(){
								_dg.datagrid('reload');
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入删除人员日志
								insertSysLog("人员信息管理","删除","人员"+name);
							}
						});
					}
				});
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//增加弹窗提交
		function showSubmit(){
			//判断是新增还是更新
			var rows = _dg.datagrid('getSelections');
			if(rows.length==1){
				if(_sysEmpForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysEmp/update.do" ,
						data: _sysEmpForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'更新成功',
									timeout:3000,
									showType:'slide'
								});
								//插入修改人员日志
								insertSysLog("人员信息管理","修改","人员"+$("#e_name").textbox("getValue"));
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
			}else{
				if(_sysEmpForm.form('validate')){
					$.ajax({
						type: 'POST',
						url: "<%=basePath%>sysEmp/add.do" ,
						data:_sysEmpForm.serialize(),
						dataType: 'json',
						success: function(data){
							if(data.code==0){
								$.messager.show({
									title:'提示',
									msg:'新增成功',
									timeout:3000,
									showType:'slide'
								});
								//插入新增人员日志
								insertSysLog("人员信息管理","新增","人员"+$("#e_name").textbox("getValue"));
								_dg.datagrid('reload');
								_dlgEdit.dialog('close');
							}else if(data==1){
								$.messager.alert('错误提示','系统出现异常，请联系管理员');
							}
						},
						error: function(){
							$.messager.alert('错误提示','系统出现异常，请联系管理员');
						}
					});
				}else{
					$.messager.alert('错误提示','请完善红色星号必填项');
				}
			}
		}
		
		// 确定选择的内容
		function sure() {
			var rows = _dg.datagrid('getSelections');
			if(rows.length == 1){
				window.opener.updateApplyPerson(rows[0].id,rows[0].name);
				window.close();  
			} else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			} else {
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		function addFile(){
			var rows = _dg.datagrid('getSelections');
			$("#f_empId").val((rows[0].id));
			$("#addFileDiv").window("open");
		}
		
		function fileSubmit(){ 
			  $("#addFileForm").ajaxSubmit({
// 		            dataType :'json',//返回数据类型
		            url: "<%=basePath%>sysEmp/uploadFile.do" ,
// 		            dataType:'html',
		            beforeSend:function(){
		            },
		            //更新进度条事件处理代码
		            uploadProgress:function(event,position,total,percentComplete){
		            },
		            success:function(data){//图片上传成功时
			        	if(data=="succ"){
				  			$("#addFileDiv").dialog("close");
							_dgFile.datagrid('reload')
							$.messager.show({
								title:'提示',
								msg:'上传成功',
								timeout:3000,
								showType:'slide'
							});  
			        	}else{
							$.messager.alert('错误提示','上传失败');
			        	}
		            },
		            error:function(xhr){
		            }
		        });
		    }         
		
		function delFile(){
			var rows = _dgFile.datagrid('getSelections');
		    var fileId=rows[0].fileId;
			if(rows.length==1){
				$.messager.confirm('提示框', '你确定要删除吗?',function(r){
					if(r){
						$.ajax({
							url: '<%=basePath%>sysEmp/deleteFile.do?fileId=' + fileId,
									success:function(){
										_dgFile.datagrid('reload')
										$.messager.show({
											title:'提示',
											msg:'删除成功',
											timeout:3000,
											showType:'slide'
										});
										//插入删除职称申请日志
										//insertSysLog("职称申请","删除","单号为"+headId+"的职称申请单");
									}
						});
					}
				})
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		function download(){
			var row = $("#dgFile").datagrid('getSelections');
			var fileId = row[0].fileId;
			$.messager.confirm('提示框', '你确定要下载吗?',function(r){
				if(r){
					$.ajax({
						url: '<%=basePath%>sysEmp/downloadFile.do?fileId='+fileId,
								success:function(){
									_dgFile.datagrid('reload')
									$.messager.show({
										title:'提示',
										msg:'删除成功',
										timeout:3000,
										showType:'slide'
									});
									//插入删除职称申请日志
									//insertSysLog("职称申请","删除","单号为"+headId+"的职称申请单");
								}
					});
				}
			})
		}
	//导出人员信息表格
	function print() {
		location.href="<%=basePath%>sysEmp/exportEmpDealxls.do?deptNO="+curDeptNo+"&status="+_status.combotree("getValue")+"&name="+_name.textbox("getValue")+"&job="+_job.textbox("getValue")+"";
	}
	
	
	function daoRuExcel(){
		$("#daoRuExcel").window("open");
	}
	
	function excelSubmit(){
		var fileName = $("#fileNameD").textbox("getValue");
		var index = fileName.lastIndexOf(".");
    	var ext = fileName.substr(index+1);
    	if(ext != "xls" && ext != "xlsx"){
    		$.messager.alert("操作提示", "只能导入excel文件！");
		}else{
			$("#excelForm").ajaxSubmit({
// 	            dataType :'json',//返回数据类型
	            url: "<%=basePath%>sysEmp/daoRuExcel.do" ,
// 	            dataType:'html',
	            success : function(data){
	             	if(data){
	             		$("#daoRuExcel").dialog("close");
			  			_dg.datagrid('reload');
			  			$.messager.alert('错误提示',data);
	             	}
	            },
	            error:function(xhr){
// 	            	$.messager.alert('错误提示','数据导入失败');
	            }
	        });
		}
	}
    //身份证号验证	  
	$.extend($.fn.validatebox.defaults.rules, {     
	    idcared: {     
	        validator: function(value,param){    
	            var flag= isCardID(value);  
	            return flag==true?true:false;    
	        },     
	        message: '不是有效的身份证号码'    
	    }     
	}); 
</script>
<style>
	.spanStyle{
		display:inline-block;
		width:100px;
		text-align:left;
	}
</style>
</body>
</html>
