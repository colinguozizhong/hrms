<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>
				离职人员分析表
			</title>
			<%@include file="/common/common_easyui.jsp" %>
	    	<link href="<%=basePath%>style/hrms.css" type="text/css" rel="stylesheet" />
			<script type="text/javascript" src="<%=basePath%>scripts/py.js"></script>

			<script type="text/javascript">
				var inserRowNum=0;
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
					// 	初始化加载数据
		            dataLoad('','');
					
					//查询
					$("#doSearch").click(function() {
						dataLoad($("#businessId").val(),$("#leaveDate").textbox("getValue"));
					});

					//重置
					$("#reset").click(function() {
						$("#searchForm").form('clear');
						$("#leaveDate").datetimespinner('setValue',myFormatter(new Date()));
						dataLoad('','');
					});
					//导出
					$("#export").click(function() {
						AutomateExcel();
					});
				})
				
				// 	动态插入行
				function createRow (data, num) {
						// 设置'人力资源异动分析'单元格的合并行数
			            document.getElementById('leaveEmpRow').cells[0].rowSpan = 11 + num;
			            document.getElementById('zdgwlzfx').cells[0].rowSpan = 2 + num;
			            var col = [];
			            for(var i = 0; i < num; i++) {
			            	var thirdRow = document.getElementById('syleaveEmpTable').insertRow(11); // 插入新行并返回该行
			            	for (var j = 0; j < 5; j++) {
			            		col[j] = thirdRow.insertCell(j); // 在位置j插入单元格
				                col[j].className = 'myClass';    // 设置插入的Row样式
			            	}
			            	// 设置每个单元格值 
			            	col[0].innerHTML = data[i]['stationName']; // 岗位名称
			            	col[1].innerHTML = data[i]['stationNameCount']; // 离职人数
			            	col[2].innerHTML = (data[i]['stationNameCount']/data[i]['lzZRS']).toFixed(2); // 所占比率
//  			            	col[3].innerHTML = (337/1000).toFixed(2); // 离职原因
// 			                col[4].innerHTML = data[i]['C04']; // 改善方法
			            	col[0].colSpan="2";  
			            	col[1].colSpan="2"; 
			            	col[2].colSpan="2"; 
			            	col[3].colSpan="3"; 
			            	col[4].colSpan="3"; 
			            }
			        }
				// 	时间初始化
				function myFormatter(date){
		            var y = date.getFullYear();
		            var m = date.getMonth()+1;
		            return y+'-'+(m<10?('0'+m):m);
		        }
				  function formatter(date) {
			            if (!date) {
			                return myFormatter(new Date());
			            }
			            var y = date.getFullYear();
			            var m = date.getMonth() + 1;
			            return y + '-' + (m < 10 ? ('0' + m) : m);
			        }

			      function parser(s) {
			            if (!s) {
			                return null;
			            }
			            var ss = s.split('-');
			            var y = parseInt(ss[0], 10);
			            var m = parseInt(ss[1], 10);
			            if (!isNaN(y) && !isNaN(m)) {
			                return new Date(y, m - 1, 1);
			            } else {
			                return new Date();
			            }
			        }
				// 	加载数据
				function dataLoad(orgId,leaveDate){
					var all_tr = $("table tr td");
			        all_tr.empty();
			        for (var i = 0; i < inserRowNum; i++) {
						document.getElementById('syleaveEmpTable').deleteRow(11); // 删除之前插入的行
		        	}
			     // 向后台请求数据
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chartLZFX/glFXList.do',
					   data: {
						   'orgId':orgId,
					    	'leaveDate':leaveDate
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
					
					// 向后台请求数据
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chartLZFX/sjyFXList.do',
					    data: {
					    	'orgId':orgId,
					    	'leaveDate':leaveDate
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"sjy");
					    },
					    error: function(){
							alert("获取数据失败");
						}
					})
					
					// 向后台请求数据
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chartLZFX/lzccFXList.do',
					    data: {
					    	'orgId':orgId,
					    	'leaveDate':leaveDate
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	setShuJu(data,"rycc");
					    },
					    error: function(){
							alert("获取数据失败");
						}
					})
					
					// 向后台请求数据
					$.ajax({
					    type: 'GET',
					    url: '<%=basePath%>chartLZFX/gwFXList.do',
					    data: {
					    	'orgId':orgId,
					    	'leaveDate':leaveDate
					    },
					    dataType: 'json',
					    success: function(data) {
					    	// 如果成功获取到数据
					    	createRow(data, data.length);
					    	inserRowNum = data.length;
					    },
					    error: function(){
							alert("获取数据失败");
						}
					})
				}
				// 	给固定行赋值
				function setShuJu(data,type){
					if(type=="glfx"){debugger;
						document.getElementById('glFXRS1').innerHTML += data.glFXRS1;
						document.getElementById('glFXRS2').innerHTML += data.glFXRS2;
						document.getElementById('glFXRS3').innerHTML += data.glFXRS3;
						document.getElementById('glFXRS4').innerHTML += data.glFXRS4;
						document.getElementById('glFXRS5').innerHTML += data.glFXRS5;
						document.getElementById('glFXRS6').innerHTML += data.glFXRS6;
						document.getElementById('glFXBL1').innerHTML += (data.glFXBL1*100).toFixed(2)+"%";
						document.getElementById('glFXBL2').innerHTML += (data.glFXBL2*100).toFixed(2)+"%";
						document.getElementById('glFXBL3').innerHTML += (data.glFXBL3*100).toFixed(2)+"%";
						document.getElementById('glFXBL4').innerHTML += (data.glFXBL4*100).toFixed(2)+"%";
						document.getElementById('glFXBL5').innerHTML += (data.glFXBL5*100).toFixed(2)+"%";
						document.getElementById('glFXBL6').innerHTML += (data.glFXBL6*100).toFixed(2)+"%";
					}else if(type=="sjy"){
						document.getElementById('sjyFXRS1').innerHTML += data.sjyFXRS1;
						document.getElementById('sjyFXRS2').innerHTML += data.sjyFXRS2;
						document.getElementById('sjyFXRS3').innerHTML += data.sjyFXRS3;
						document.getElementById('sjyFXRS4').innerHTML += data.sjyFXRS4;
						document.getElementById('sjyFXRS5').innerHTML += data.sjyFXRS5;
						document.getElementById('sjyFXBL1').innerHTML += (data.sjyFXBL1*100).toFixed(2)+"%";
						document.getElementById('sjyFXBL2').innerHTML += (data.sjyFXBL2*100).toFixed(2)+"%";
						document.getElementById('sjyFXBL3').innerHTML += (data.sjyFXBL3*100).toFixed(2)+"%";
						document.getElementById('sjyFXBL4').innerHTML += (data.sjyFXBL4*100).toFixed(2)+"%";
						document.getElementById('sjyFXBL5').innerHTML += (data.sjyFXBL5*100).toFixed(2)+"%";
					}else if(type=="rycc"){
						document.getElementById('lzZRS').innerHTML += data.lzZRS;
						document.getElementById('lzPGRS').innerHTML += data.lzPGRS;
						document.getElementById('lzPGRSBL').innerHTML += (data.lzPGRSBL).toFixed(2);
						document.getElementById('lzJSRS').innerHTML += data.lzJSRS;
						document.getElementById('lzJSRSBL').innerHTML += (data.lzJSRSBL).toFixed(2);
						document.getElementById('lzGLRS').innerHTML += data.lzGLRS;
						document.getElementById('lzGLRSBL').innerHTML += (data.lzGLRSBL).toFixed(2);
						document.getElementById('lzQLRS').innerHTML += data.lzQLRS;
						document.getElementById('lzQLRSBL').innerHTML += (data.lzQLRSBL).toFixed(2);
					}
				}
				
				function AutomateExcel() {
			        var elTable = document.getElementById("table"); //table1改成你的tableID
			        var oRangeRef = document.body.createTextRange();
			        oRangeRef.moveToElementText(elTable);
			        oRangeRef.execCommand("Copy");
			        try {
			             var appExcel = new ActiveXObject("Excel.Application");
			         } catch (e) {
			             alert("无法调用Office对象，请确保您的机器已安装了Office并已将本系统的站点名加入到IE的信任站点列表中！");
			             return;
			         }
			        appExcel.Visible = true;
			        appExcel.Workbooks.Add().Worksheets.Item(1).Paste();
			        appExcel = null;
			    }
				
				</script>
		</head>
		<body>
			<div class="easyui-layout" style="width:100%;height:580px;">
				<div data-options="region:'north'" style="height:36px;padding:0 0 0 15px;">
					<form action="" id="searchForm">
						<input type="hidden" name="orgId" id="orgId" />
						<input type="hidden" name="businessId" id="businessId" />
						<select class="easyui-combotree"  id="jigou" name="jigou" data-options="label:'所在部门:'" labelPosition="right" style="width:350px" >
		    			</select>
		    			<input id='leaveDate' name='leaveDate' class='easyui-datetimespinner'  data-options='label:"离职时间:",formatter:formatter,parser:parser' style='width:250px;'/>
						<a name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>&nbsp;
						<a name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
			       		<a name="export" id="export" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" style="width:68px;margin-left: 5px;display: none;">导出</a>
			        </form>
				</div>
				<div data-options="region:'center'"  style="height:500px;">
						<table id="table" class='gridtable'  style="width:100%;">
						    <thead id='syleaveEmpTable'>
						    	<tr >
						        	<th  colspan="14" >离职人员分析</th>
						        </tr >
						        <tr  id='leaveEmpRow'>
						            <th rowspan="11" field="itemid" width="120px" align="center">离职人员分析</th>
						            <th rowspan="3"  field="itemid1" width="100px" align="center">工龄分析</th>
						            <th  field="glFXBL1" width="80px" align="center">1-3月</th>
						            <th  field="glFXBL2" width="80px" align="center">3-6月</th>
						            <th  field="glFXBL3" width="80px" align="center">6月-1年</th>
						            <th  field="glFXBL4" width="80px" align="center">1年-2年</th>
						            <th  field="glFXBL5" width="80px" align="center">2年-3年</th>
						            <th  field="glFXBL6" width="80px" align="center">3年以上</th>
						            <th rowspan="3"  field="itemid2" width="100px" align="center">受教育结构分析</th>
						            <th  field="sjyFXBL1" width="80px" align="center">初中及以下</th>
						            <th  field="sjyFXBL2" width="80px" align="center">高中/中专</th>
						            <th  field="sjyFXBL3" width="80px" align="center">大专</th>
						            <th  field="sjyFXBL4" width="80px" align="center">本科及以上</th>
						            <th  field="sjyFXBL5" width="80px" align="center">其他</th>
						        </tr>
						        <tr>
						            <td id="glFXRS1" width="80px" align="center"></td>
						            <td id="glFXRS2" width="80px" align="center"></td>
						            <td id="glFXRS3" width="80px" align="center"></td>
						            <td id="glFXRS4" width="80px" align="center"></td>
						            <td id="glFXRS5" width="80px" align="center"></td>
						            <td id="glFXRS6" width="80px" align="center"></td>
						            <td id="sjyFXRS1" width="80px" align="center"></td>
						            <td id="sjyFXRS2" width="80px" align="center"></td>
						            <td id="sjyFXRS3" width="80px" align="center"></td>
						            <td id="sjyFXRS4" width="80px" align="center"></td>
						            <td id="sjyFXRS5" width="80px" align="center"></td>
						        </tr>
						        <tr>
						            <td id="glFXBL1"  width="80px" align="center">%</td>
						            <td id="glFXBL2"  width="80px" align="center">%</td>
						            <td id="glFXBL3"  width="80px" align="center">%</td>
						            <td id="glFXBL4"  width="80px" align="center">%</td>
						            <td id="glFXBL5"  width="80px" align="center">%</td>
						            <td id="glFXBL6"  width="80px" align="center">%</td>
						            <td id="sjyFXBL1"  width="80px" align="center">%</td>
						            <td id="sjyFXBL2"  width="80px" align="center">%</td>
						            <td id="sjyFXBL3"  width="80px" align="center">%</td>
						            <td id="sjyFXBL4"  width="80px" align="center">%</td>
						            <td id="sjyFXBL5"  width="80px" align="center">%</td>
						        </tr>
						         <tr >
						        	<th  colspan="13" ></th>
						        </tr >
						        <tr>
						            <th rowspan="4" field="itemid4"  align="center">离职层次分析</th>
						            <th field="lzRS" rowspan="2"  colspan="2"  align="center">总离职人数</th>
						            <th  field="lzRS1"   colspan="2"   align="center">工勤类</th>
						            <th  field="lzRS2"  colspan="2" align="center">技术类</th>
						            <th  field="lzPS3"  colspan="2"   align="center">管理类</th>
						            <th  field="lzPS4"  colspan="2"   align="center">其他类</th>
						            <th></th>
						            <th></th>
						        </tr>
						        <tr>
						        	<th  field="lzPGRS"  align="center">离职人数</th>
						        	<th  field="lzPGRSBL"  align="center">占离职人数比</th>
						        	<th  field="lzJSRS"  align="center">离职人数</th>
						        	<th  field="lzJSRSBL"  align="center">占离职人数比</th>
						        	<th  field="lzGLRS"  align="center">离职人数</th>
						        	<th  field="lzGLRSBL"  align="center">占离职人数比</th>
						        	<th  field="lzQLRS"  align="center">离职人数</th>
						        	<th  field="lzQLRSBL"  align="center">占离职人数比</th>
						        	<th></th>
						        	<th></th>
						        </tr>
						        <tr>
						        	<td  id="lzZRS"  colspan="2" align="center"></td>
						        	<td  id="lzPGRS" align="center"></td>
						        	<td  id="lzPGRSBL"  align="center"></td>
						        	<td  id="lzJSRS"  align="center"></td>
						        	<td  id="lzJSRSBL"  align="center"></td>
						        	<td  id="lzGLRS"  align="center"></td>
						        	<td  id="lzGLRSBL"  align="center"></td>
						        	<td  id="lzQLRS"  align="center"></td>
						        	<td  id="lzQLRSBL"  align="center"></td>
						        	<td></td>
						        	<td></td>
<!-- 						        	<td></td> -->
						        </tr>
						         <tr>
						            <th field="zhushi"  colspan="6"  align="left">工勤类：所有中管级以下的一线员工</th>
						            <th field="zhushi1"  colspan="6"  align="right">文职类：办公室人员（含财务人员、秘书、助理）</th>
						        </tr>
						        <tr >
						        	<th  colspan="13" ></th>
						        </tr >
				          		<tr id='zdgwlzfx'>
				          			<th  rowspan="2"  field="itemid3"   width="120px"  align="center">重点岗位离职分析</th>
						            <th  field="position"   colspan="2"   align="center">岗位</th>
						            <th  field="lzZRSP"   colspan="2"   align="center">离职人数</th>
						            <th  field="lzZRSPBL"   colspan="2"    align="center">所占比率</th>
						            <th  field="positionLZYY"   colspan="3"    align="center">离职原因分析</th>
						            <th  field="positionLZGS"  colspan="3"   align="center">主要改善措施</th>
				          		</tr>
				          		<tr>
				          			<th field="zushi3"  colspan="12"  align="left">公式：岗位离职人数所占比率=该岗位离职人数/当月离职总人数</th>
				          		</tr>
						    </thead>
						</table>
				</div>
			</div>
		</body>
	</html>