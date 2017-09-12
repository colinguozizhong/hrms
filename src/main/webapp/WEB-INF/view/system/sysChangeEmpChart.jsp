<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
	    <title>人力资源异动分析报表</title>
	    <%@include file="/common/common_easyui.jsp"%>
	    <link href="<%=basePath%>style/hrms.css" type="text/css" rel="stylesheet" />
	</head>

	<body>
	    <div id='search' data-options='region:"center",iconCls:"icon-ok"'>
	        <!-- 查询表单模块 start -->
            <form action='' id='searchForm'>
                <input id='deptId' name='deptId' class='easyui-textbox' data-options='label:"所在部门:"' style='width:400px;' />
                <input id='changeMonth' name='changeMonth' class='easyui-datetimespinner' value='2017-3' data-options='label:"异动时间：",formatter:formatter,parser:parser' style='width:250px;'/>
                <a name='doSearch' id='doSearch' class='easyui-linkbutton' data-options='iconCls:"icon-search"' style='width:68px;'>查询</a>&nbsp;
                <a name='reset' id='reset' class='easyui-linkbutton' data-options='iconCls:"icon-reload"' style='width:68px;margin-left: 5px'>重置</a>
            </form>
	        <br>
	        <!-- 查询表单模块 end -->

	        <!-- 报表模块 start -->
	        <div style='overflow-y:auto;width:1170px;height:500px;'>
				<table class='gridtable' style='width:1170px;'>
		            <thead id='sysChangeEmpTable'>
		                <tr id='sysChangeEmpRow'>
		                    <th rowspan='3' width='130' align='center'>人力资源异动分析</th>
		                    <th rowspan='2' width='300' align='center'>部门</th>
		                    <th rowspan='2' width='80' align='center'>编制人数</th>
		                    <!-- <th rowspan='2' width='80' align='center'>月初人数</th> -->
		                    <th rowspan='2' width='80' align='center'>入职人数</th>
		                    <th colspan='3' width='150' align='right' style='border-right-style:none;'>本月离职（共</th>
		                    <th width='50' align='center' style='border-left-style:none;border-right-style:none;'></th>
		                    <th width='100' align='left' style='border-left-style:none;'>人）</th>
		                    <th colspan='4' width='200' align='center'>本月异动</th>
		                    <th rowspan='2' width='80' align='center'>月末人数</th>
		                </tr>
		                <tr>
		                    <th width='50' align='center'>辞职</th>
		                    <th width='50' align='center'>辞退</th>
		                    <th width='50' align='center'>自离</th>
		                    <!-- <th width='50' align='center'>开除</th> -->
		                    <th width='50' align='center'>其他</th>
		                    <th width='50' align='center'>离职率</th>
		                    <th width='50' align='center'>调入</th>
		                    <th width='50' align='center'>调出</th>
		                    <th width='50' align='center'>晋升</th>
		                    <th width='50' align='center'>换岗</th>
		                </tr>
		                <tr id='thirdRowId'>
		                    <th width='200' align='center'>合计：</th>
		                    <th width='80' align='center'></th>
		                    <th width='80' align='center'></th>
		                    <!-- <th width='80' align='center'></th> -->
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                    <th width='50' align='center'></th>
		                </tr>
		                <!-- <tr>
		                    <th colspan='15' width='900' align='left'>
		                        公式：离职率=当月离职人数/当月平均人数*100%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月末人数：月初人数-离职人数+调入人数
		                    </th>
		                </tr> -->
		            </thead>
		        </table>
	        </div>
	        <!-- 报表模块 end -->
	        <br>
	        <br>
	    </div>

	    <script type="text/javascript">
	    	var businessId = ''; // 部门/单位/机织机构的业务Id
	    	var numOfDeptTemp = 0; // 报表刷新前部门数量（用于临时存储）

	    	/**
	    	 * [createRow 创建行并插入表格 行数根据查询的部门数量而定]
	    	 * @param  {[Object]} data      [从后台获取到的异动人员相关数据]
	    	 * @param  {[number]} numOfDept [部门数量]
	    	 * @return 
	    	 */
	        var createRow = function(data, numOfDept) {
				numOfDeptTemp = numOfDept;
				// 设置'人力资源异动分析'单元格的合并行数
	            document.getElementById('sysChangeEmpRow').cells[0].rowSpan = 3 + numOfDept;
	            var col = [];
	            for(var i = 0; i < numOfDept; i++) {
	            	var thirdRow = document.getElementById('sysChangeEmpTable').insertRow(2); // 插入新行并返回该行
	            	for (var j = 0; j < 13; j++) {
	            		col[j] = thirdRow.insertCell(j); // 在位置j插入单元格
		                col[j].className = 'myClass';    // 设置插入的Row样式
		                if (j == 0) {
		                	col[j].className = 'myClassLeft'; // 设置部门所在单元格左对齐
		                }
	            	}

	            	// 设置每个单元格值 
	            	col[0].innerHTML = data[i].deptName; // 部门名称
	            	col[1].innerHTML = data[i].numOfBianZhi; // 编制人数
	            	// col[2].innerHTML = data[i].numOfCiZhi; // 月初人数
	            	col[2].innerHTML = data[i].numOfRuZhi; // 入职
	                col[3].innerHTML = data[i].numOfCiZhi; // 辞职
	                col[4].innerHTML = data[i].numOfCiTui; // 辞退
	                col[5].innerHTML = data[i].numOfZiLi; // 自离 离岗
	                // 其它=转正+退休+部门调整
	                col[6].innerHTML = (data[i].numOfZhuanZheng + data[i].numOfTuiXiu + data[i].numOfTiaoZheng);
	                col[7].innerHTML = data[i].numOfZiLi + '%'; // 离职率
	                col[8].innerHTML = data[i].numOfDiaoRu; // 调入
	                col[9].innerHTML = data[i].numOfDiaoChu; // 调出
	                col[10].innerHTML = data[i].numOfJinSheng; // 晋升
	                col[11].innerHTML = data[i].numOfHuanGang; // 换岗
                	col[12].innerHTML = data[i].numOfHuanGang; // 月末人数
	            }

		        var cells = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				// 计算合计部分
            	for (var k = 0; k < numOfDept; k++) {
            		cells[1] += data[k].numOfBianZhi;
            		cells[2] += data[k].numOfRuZhi;
            		cells[3] += data[k].numOfCiZhi;
            		// cells[4] += parseInt(data[k].numOfCiZhi);
            		cells[4] += data[k].numOfCiTui;
            		cells[5] += data[k].numOfZiLi;
            		cells[6] += (data[k].numOfZhuanZheng + data[k].numOfTuiXiu + data[k].numOfTiaoZheng);
            		cells[7] = '-';
            		cells[8] += data[k].numOfDiaoRu;
            		cells[9] += data[k].numOfDiaoChu;
            		cells[10] += data[k].numOfJinSheng;
            		cells[11] += data[k].numOfHuanGang;
            		cells[12] += data[k].numOfHuanGang;
            		if (k == numOfDept - 1) {
						for (var i = 1; i < 13; i++) {
							document.getElementById('thirdRowId').cells[i].innerHTML = cells[i]; // 获取'合计'所在行，并插入值
						}
            		}
            	}
	            // 计算本月离职人数
				document.getElementById('sysChangeEmpRow').cells[5].innerHTML = cells[3] + cells[4] + cells[5] + cells[6];
	        }

	        /**
	         * [refreshTable 在重新查询时，刷新报表数据]
	         * @param  {[type]} numOfDept [刷新前报表显示的部门数]
	         * @return {[type]}
	         */
	        var refreshTable = function(numOfDept) {
				document.getElementById('sysChangeEmpRow').cells[5].innerHTML = ''; // 设置本月离职人数为空
	        	for (var i = 0; i < numOfDeptTemp; i++) {
					document.getElementById('sysChangeEmpTable').deleteRow(2); // 删除之前插入的行
	        	}
				// 还原'人力资源异动分析'单元格的合并行数
	            document.getElementById('sysChangeEmpRow').cells[0].rowSpan = 3;
				var myTable = document.getElementById('thirdRowId'); // 获取'合计'所在行
                for (var k = 1; k < 13; k++){
                	myTable.cells[k].innerHTML = ''; // 合计部分设为空
                }
                numOfDeptTemp = 0; // 还原为0
	        }

	        var formatter = function(date) {
	            if (!date) {
	                return '';
	            }
	            var y = date.getFullYear();
	            var m = date.getMonth() + 1;
	            return y + '-' + (m < 10 ? ('0' + m) : m);
	        }

	        var parser = function(s) {
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

	        // 所在部门的树状展示
	        $('#deptId').combotree({
	            url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
	            idFiled: 'businessId',
	            textFiled: 'orgName',
	            parentField: 'pid',
	            onClick: function(node) {
	            	businessId = node.businessId;
	            }
	        });

			// 查询
			$('#doSearch').click(function() {
				// 如果异动时间输入框值为null（点击重置按钮的情况下）
				if (!$('#changeMonth').textbox('getValue') || businessId == 0) {
					refreshTable(numOfDeptTemp); // 刷新报表
					return;
				}
				var changeEmpInfo = {
				    'businessId': businessId,
				    'changeMonth': $('#changeMonth').textbox('getValue')
				}
				// 向后台请求数据
				$.ajax({
				    type: 'GET',
				    url: '<%=basePath%>chart/findChangeEmpInfo.do',
				    data: changeEmpInfo,
				    dataType: 'json',
				    success: function(data) {
						refreshTable(numOfDeptTemp); // 刷新报表
				    	// 如果成功获取到数据
				    	if (data.sysChangeEmpChartVOList && data.sysChangeEmpChartVOList.length > 0) {
					    	createRow(data.sysChangeEmpChartVOList, data.sysChangeEmpChartVOList.length); // 插入报表数据
				    	}
				    },
				    error: function(request, textStatus, errorThrown) {
				    	console.log(request, textStatus, errorThrown);
				    }
				})
			});

			//重置
			$("#reset").click(function() {
				$("#searchForm").form('clear');
				businessId = 0; // 业务Id还原为0
			});
	    </script>
	</body>
</html>