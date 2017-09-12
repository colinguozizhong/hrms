<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>
				在职人员分析表
			</title>
			<%@include file="/common/common_easyui.jsp" %>
	    	<link href="<%=basePath%>style/hrms.css" type="text/css" rel="stylesheet" />
			<script type="text/javascript" src="<%=basePath%>scripts/py.js"></script>
				<script type="text/javascript">
				$(function(){
					//所在部门的树状展示
					$('#jigou').combotree({
						url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
					    idFiled:"businessId",
					    textFiled:"orgName",
						parentField:"pid",
					    onClick: function(node){
					    	$("#businessId").val(node.businessId);
					    	$("#orgId").val(node.orgId);
					    	$("input",$("#jigou").next("span")).val(node.orgName);
					    },
					});
					
					$('#doSearch').click(function() {
						var businessId = $("#businessId").val();
						dataLoad(businessId);
					});
				})
				
				function dataLoad(businessId){
					var all_tr = $("table tr td");
			        all_tr.empty();
					//查询在职人员性别组成
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chart/searchSexInfo.do',
					    data: {
						   'businessId':businessId,
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"xbfx");
					    },
						error: function(){
							alert("获取数据失败");
						}
					})
					//查询在职人员学历组成
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chart/searchEduInfo.do',
					    data: {
						   'businessId':businessId,
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"xlfx");
					    },
						error: function(){
							alert("获取数据失败");
						}
					})
					//查询在职人员工龄组成
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chart/searchGLInfo.do',
					    data: {
						   'businessId':businessId,
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"glfx");
					    },
						error: function(){
							alert("获取数据失败");
						}
					})
					//查询在职人员年龄组成
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chart/searchAgeInfo.do',
					    data: {
						   'businessId':businessId,
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"agefx");
					    },
						error: function(){
							alert("获取数据失败");
						}
					})
					//查询在职人员岗位组成
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chart/searchStationInfo.do',
					    data: {
						   'businessId':businessId,
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"stationfx");
				            $.messager.show({
				                title: '提示',
				                msg: '数据获取成功',
				                timeout: 3000,
				                showType: 'slide'
				            });
					    },
						error: function(){
							alert("获取数据失败");
						}
					})
				}
				
//			 	给固定行赋值
				function setShuJu(data,type){
					if(type=="xbfx"){
						document.getElementById('xbCount1').innerHTML += data.inServiceEmpChartVO.total;
						document.getElementById('xbCount2').innerHTML += data.inServiceEmpChartVO.sexMaleCount;
						document.getElementById('xbCount3').innerHTML += data.inServiceEmpChartVO.sexFemaleCount;
						document.getElementById('xbPercent2').innerHTML += data.inServiceEmpChartVO.sexMalePercent.toFixed(2).toString()+"%";
						document.getElementById('xbPercent3').innerHTML += data.inServiceEmpChartVO.sexFemalePercent.toFixed(2).toString()+"%";
						if(data.inServiceEmpChartVO.sexMalePercent==0&&data.inServiceEmpChartVO.sexFemalePercent==0){
							document.getElementById('xbPercent1').innerHTML += 0+"%";
						}else{
							document.getElementById('xbPercent1').innerHTML += 100+"%";
						}
					}else if(type=="xlfx"){
						document.getElementById('xlCount1').innerHTML += data.inServiceEmpChartVO.edu1;
						document.getElementById('xlCount2').innerHTML += data.inServiceEmpChartVO.edu2;
						document.getElementById('xlCount3').innerHTML += data.inServiceEmpChartVO.edu3;
						document.getElementById('xlCount4').innerHTML += data.inServiceEmpChartVO.edu4;
						document.getElementById('xlPercent1').innerHTML += data.inServiceEmpChartVO.edu1Percent.toFixed(2).toString()+"%";
						document.getElementById('xlPercent2').innerHTML += data.inServiceEmpChartVO.edu2Percent.toFixed(2).toString()+"%";
						document.getElementById('xlPercent3').innerHTML += data.inServiceEmpChartVO.edu3Percent.toFixed(2).toString()+"%";
						document.getElementById('xlPercent4').innerHTML += data.inServiceEmpChartVO.edu4Percent.toFixed(2).toString()+"%";

					}else if(type=="glfx"){
						document.getElementById('glCount1').innerHTML += data.inServiceEmpChartVO.gl1;
						document.getElementById('glCount2').innerHTML += data.inServiceEmpChartVO.gl2;
						document.getElementById('glCount3').innerHTML += data.inServiceEmpChartVO.gl3;
						document.getElementById('glCount4').innerHTML += data.inServiceEmpChartVO.gl4;
						document.getElementById('glCount5').innerHTML += data.inServiceEmpChartVO.gl5;
						document.getElementById('glCount6').innerHTML += data.inServiceEmpChartVO.gl6;
						document.getElementById('glPercent1').innerHTML += data.inServiceEmpChartVO.glPercent1.toFixed(2).toString()+"%";
						document.getElementById('glPercent2').innerHTML += data.inServiceEmpChartVO.glPercent2.toFixed(2).toString()+"%";
						document.getElementById('glPercent3').innerHTML += data.inServiceEmpChartVO.glPercent3.toFixed(2).toString()+"%";
						document.getElementById('glPercent4').innerHTML += data.inServiceEmpChartVO.glPercent4.toFixed(2).toString()+"%";
						document.getElementById('glPercent5').innerHTML += data.inServiceEmpChartVO.glPercent5.toFixed(2).toString()+"%";
						document.getElementById('glPercent6').innerHTML += data.inServiceEmpChartVO.glPercent6.toFixed(2).toString()+"%";

					}else if(type=="agefx"){
						document.getElementById('ageCount1').innerHTML += data.inServiceEmpChartVO.age1;
						document.getElementById('ageCount2').innerHTML += data.inServiceEmpChartVO.age2;
						document.getElementById('ageCount3').innerHTML += data.inServiceEmpChartVO.age3;
						document.getElementById('ageCount4').innerHTML += data.inServiceEmpChartVO.age4;
						document.getElementById('ageCount5').innerHTML += data.inServiceEmpChartVO.age5;
						document.getElementById('ageAverage').innerHTML += data.inServiceEmpChartVO.ageAverage.toFixed(2);
						document.getElementById('agePercent1').innerHTML += data.inServiceEmpChartVO.agePercent1.toFixed(2).toString()+"%";
						document.getElementById('agePercent2').innerHTML += data.inServiceEmpChartVO.agePercent2.toFixed(2).toString()+"%";
						document.getElementById('agePercent3').innerHTML += data.inServiceEmpChartVO.agePercent3.toFixed(2).toString()+"%";
						document.getElementById('agePercent4').innerHTML += data.inServiceEmpChartVO.agePercent4.toFixed(2).toString()+"%";
						document.getElementById('agePercent5').innerHTML += data.inServiceEmpChartVO.agePercent5.toFixed(2).toString()+"%";
					}else if(type=="stationfx"){
						document.getElementById('stationCount').innerHTML += data.inServiceEmpChartVO.stationCount;
						document.getElementById('stationPuGongCount').innerHTML += data.inServiceEmpChartVO.stationPuGongCount;
						document.getElementById('stationWenZhiCount').innerHTML += data.inServiceEmpChartVO.stationWenZhiCount;
						document.getElementById('stationJiShuCount').innerHTML += data.inServiceEmpChartVO.stationJiShuCount;
						document.getElementById('stationGuanLiCount').innerHTML += data.inServiceEmpChartVO.stationGuanLiCount;
						document.getElementById('stationQiTaCount').innerHTML += data.inServiceEmpChartVO.stationQiTaCount;
						document.getElementById('stationPuGongPercent').innerHTML += data.inServiceEmpChartVO.stationPuGongPercent.toFixed(2).toString()+"%";
						document.getElementById('stationWenZhiPercent').innerHTML += data.inServiceEmpChartVO.stationWenZhiPercent.toFixed(2).toString()+"%";
						document.getElementById('stationJiShuPercent').innerHTML += data.inServiceEmpChartVO.stationJiShuPercent.toFixed(2).toString()+"%";
						document.getElementById('stationGuanLiPercent').innerHTML += data.inServiceEmpChartVO.stationGuanLiPercent.toFixed(2).toString()+"%";
						document.getElementById('stationQiTaPercent').innerHTML += data.inServiceEmpChartVO.stationQiTaPercent.toFixed(2).toString()+"%";
					}
				}
				</script>
		</head>
		<body>
			<div class="easyui-layout" style="width:100%;height:550px;">
				<div data-options="region:'north'" style="height:36px;padding:0 0 0 15px;">
					<form action="" id="searchForm">
<!-- 						<select class="easyui-combotree"  id="jigou" name="jigou" data-options="label:'查询机构:'" labelPosition="right" style="width:250px" > -->
<!-- 		    			</select> -->
						<input type="hidden" name="orgId" id="orgId" />
						<input type="hidden" name="businessId" id="businessId" />
						<select class="easyui-combotree"  id="jigou" name="jigou" data-options="label:'执法机构:'" labelPosition="right" style="width:350px" >
		    			</select>
						<a name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:60px">查询</a>&nbsp;
						<a name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:60px">重置</a>
			        </form>
				</div>
				<div data-options="region:'center'"  style="height:500px;">
						<table id="table" class="gridtable" style="width:100;">
						    <thead>
						    	<tr >
						        	<th  colspan="14" align="center" >在职人员分析</th>
						        </tr >
						        <tr>
						            <th rowspan="11"  width="120px" align="center">在职人员分析</th>
						            <th rowspan="3"  width="100px" align="center">工龄分析</th>
						            <th  width="80px" align="center">1-3月</th>
						            <th  width="80px" align="center">3-6月</th>
						            <th  width="80px" align="center">6月-1年</th>
						            <th  width="80px" align="center">1年-2年</th>
						            <th  width="80px" align="center">2年-3年</th>
						            <th  width="80px" align="center">3年以上</th>
						            <th  rowspan="3"  width="100px" align="center">受教育结构分析</th>
						            <th  width="80px" align="center">初中及以下</th>
						            <th  width="80px" align="center">高中/中专</th>
						            <th  width="80px" align="center">大专</th>
						            <th  width="80px" align="center">本科及以上</th>
						        </tr>
						        <tr>
						            <td id="glCount1" height="20px" width="80px" align="center"></td>
						            <td id="glCount2" height="20px" width="80px" align="center"></td>
						            <td id="glCount3" height="20px" width="80px" align="center"></td>
						            <td id="glCount4" height="20px" width="80px" align="center"></td>
						            <td id="glCount5" height="20px" width="80px" align="center"></td>
						            <td id="glCount6" height="20px" width="80px" align="center"></td>
						            <td id="xlCount1" width="80px" align="center"></td>
						            <td id="xlCount2" width="80px" align="center"></td>
						            <td id="xlCount3" width="80px" align="center"></td>
						            <td id="xlCount4" width="80px" align="center"></td>
						        </tr>
						        <tr>
						            <td id="glPercent1"  height="20px" width="80px" align="center"></td>
						            <td id="glPercent2"  height="20px" width="80px" align="center"></td>
						            <td id="glPercent3"  height="20px" width="80px" align="center"></td>
						            <td id="glPercent4"  height="20px" width="80px" align="center"></td>
						            <td id="glPercent5"  height="20px" width="80px" align="center"></td>
						            <td id="glPercent6"  height="20px" width="80px" align="center"></td>
						            <td id="xlPercent1" height="20px" width="80px" align="center"></td>
						            <td id="xlPercent2" height="20px" width="80px" align="center"></td>
						            <td id="xlPercent3" height="20px" width="80px" align="center"></td>
						            <td id="xlPercent4" height="20px" width="80px" align="center"></td>
						        </tr>
						         <tr >
						        	<th  colspan="13"></th>
						        </tr >
						        <tr>
						            <th rowspan="3" align="center">年龄结构分析</th>
						            <th  align="center">20以下</th>
						            <th  align="center">20-30</th>
						            <th  align="center">30-40</th>
						            <th  align="center">40-50</th>
									<th  align="center">50以上</th>
									<th  align="center">平均年龄</th>
									<th rowspan="3"  width="100px" align="center">性别分析</th>
									<th  colspan="2"  align="center">总人数</th>
									<th  align="center">男</th>
									<th  align="center">女</th>
						        </tr>
						        <tr>
						            <td id="ageCount1" height="20px" width="80px" align="center"></td>
						            <td id="ageCount2" width="80px" align="center"></td>
						            <td id="ageCount3" width="80px" align="center"></td>
						            <td id="ageCount4" width="80px" align="center"></td>
						            <td id="ageCount5" width="80px" align="center"></td>
						            <td id="ageAverage" rowspan="2" width="80px" align="center"></td>
						            <td id="xbCount1" colspan="2"  align="center"></td>
						            <td id="xbCount2"  align="center"></td>
						            <td id="xbCount3"  align="center"></td>
						        </tr>
						        <tr>
						         	<td id="agePercent1" height="20px" width="80px" align="center"></td>
						            <td id="agePercent2" width="80px" align="center"></td>
						            <td id="agePercent3" width="80px" align="center"></td>
						            <td id="agePercent4" width="80px" align="center"></td>
						            <td id="agePercent5" width="80px" align="center"></td>
						            <td id="xbPercent1" colspan="2"  width="80px" align="center"></td>
						            <td id="xbPercent2" width="80px" align="center"></td>
						            <td id="xbPercent3" width="80px" align="center"></td>
						        </tr>
				                <tr >
						        	<th  colspan="13"></th>
						        </tr >
						        <tr>
						        	<th  rowspan="4" align="center">在职人员层次分析</th>
						        	<th  rowspan="2" align="center">在职总人数</th>
						        	<th  colspan="2" align="center">普工类</th>
				        			<th  colspan="2" align="center">文职类</th>
				        			<th  colspan="2" align="center">技术类</th>
				        			<th  colspan="2" align="center">管理类</th>
				        			<th  colspan="2" align="center">其他类</th>
						        </tr>
						         <tr>
						            <th align="center">在职人数</th>
						            <th align="center">占在职人数比</th>
						            <th align="center">在职人数</th>
						            <th align="center">占在职人数比</th>
						            <th align="center">在职人数</th>
						            <th align="center">占在职人数比</th>
						            <th align="center">在职人数</th>
						            <th align="center">占在职人数比</th>
						            <th align="center">在职人数</th>
						            <th align="center">占在职人数比</th>
						        </tr>
						        <tr >
						            <td id="stationCount" height="20px" align="center"></td>
						            <td id="stationPuGongCount" align="center"></td>
						            <td id="stationPuGongPercent" align="center"></td>
						            <td id="stationWenZhiCount" align="center"></td>
						            <td id="stationWenZhiPercent" align="center"></td>
						            <td id="stationJiShuCount" align="center"></td>
						            <td id="stationJiShuPercent" align="center"></td>
						            <td id="stationGuanLiCount" align="center"></td>
						            <td id="stationGuanLiPercent" align="center"></td>
						            <td id="stationQiTaCount" align="center"></td>
						            <td id="stationQiTaPercent" align="center"></td>
						        </tr >
						    </thead>
						</table>
				</div>
			</div>

		</body>
	</html>