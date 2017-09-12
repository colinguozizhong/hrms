<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.ustcsoft.framework.vo.UserVO"%>
<%@page import="com.ustcsoft.jt.vo.User"%>
<%@page
	import="org.springframework.security.core.context.SecurityContext"%>
<%
	SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
	User user = (User) context.getAuthentication().getPrincipal();
	UserVO userInfo = user.getUserVo();
%>
<head>
<style>
	.spanStyle{
		display:inline-block;
		width:120px;
		text-align:left;
	}
</style>
<title>干部任命记录</title>
<%@include file="/common/common_easyui.jsp" %>
<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
<script type="text/javascript">
var flag;
//	按钮权限显示
var objArrayYM = new Array("add","edit","view","del","print");   
$(function(){
	showObj(objArrayYM);//共同方法
	var $zTreeMenuBusiness = $('#zTreeMenuBusiness');
	// 用户表格数据
	var datagrid = $("#dg").datagrid({  
	      url:"<%=basePath%>cadreApply/findCadreAppointRecordList.do",
	      pagination:true,//显示分页  
	      rownumbers:true,//显示行号
	      pageSize:20,//分页大小  
	      pageList:[20,40,60,80],//每页的个数  
		 // fit:true,//自动补全  
	      fitColumns:true, 
		  loadFilter : function(data){
			 //过滤数据
			if(data.items==null || data.items==""){
					return {total:0,rows:[]}; 
			}else{
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
			}, {
				id:'view',
				text : '查看',
				iconCls : 'icon-search',
				handler : function() {
					view();
				}
			}, {
				id:'del',
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					del();
				}
			} , {
				id:'print',
				text : '导出任免审批表',
				iconCls : 'icon-print',
				handler : function() {
					print();
				}
			} ],
			columns : [ [ //每个列具体内容  
			{
				field : 'ck',
				checkbox : true
			}, {field : 'applyId',title : '干部任命任命单',width : 150,hidden:true},
		    {field:'empId',title:'职员Id',width:100,hidden:true},
 			{field:'xingMing',title:'任命人',width:250,
 				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				}
		    },
		    {field:'xianRenZhiWu',title:'现任职务',width:250,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
			},
			{field:'niRenZhiWu',title:'拟任职务',width:250,
					formatter : function(value) {
						if(value){
			                return "<span title='" + value + "'>" + value + "</span>";
						}	  
					},
			},
			{field:'renMianLiYou',title:'任免理由',width:250,
				formatter : function(value) {
					if(value){
		                return "<span title='" + value + "'>" + value + "</span>";
					}	  
				},
		},
			] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			height : 470,
		});

		var p = $('#dg').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,//每页显示的记录条数，默认为10  
			pageList : [ 20, 40, 60, 80 ],//可以设置每页记录条数的列表  
			beforePageText : '第',//页数文本框前显示的汉字  
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});


		//查询
		$("#doSearch").click(function() {
			var appStatus = $("input", $("#s_appStatus").next("span")).val();
			$('#dg').datagrid('load', {
				empName:$("#s_empName").textbox("getValue")
// 				opPerName:$("#s_opPerName").textbox("getValue"), 
// 				appDateStart:$("#s_appDateStart").textbox("getValue"),
// 				appDateEnd:$("#s_appDateEnd").textbox("getValue")
			});
		});

		//重置
		$("#reset").click(function() {
			$("#searchForm").form('clear');
			$('#dg').datagrid('load', {
				empName:$("#s_empName").textbox("getValue")
// 				opPerName:$("#s_opPerName").textbox("getValue"), 
// 				appDateStart:$("#s_appDateStart").textbox("getValue"),
// 				appDateEnd:$("#s_appDateEnd").textbox("getValue")
			});
		});
	
		$("#xingMing").textbox('textbox').bind("click", function () {
			flag = true;
			window.open("<%=basePath%>sysEmp/index.do?hasSec=true","请选择人员","location=no,width=1250,height=550,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100"  );
		});
		
		//所属机构
		$('#deptNO').combotree({
			url : '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
		    idFiled:"businessId",
		    textFiled:"orgName",
			parentField:"pid",
		    onClick: function(node){
		    	$("#businessId").val(node.businessId);
		    	$("#orgId").val(node.orgId);
		    	$("input",$("#orgIdName").next("span")).val(node.orgName);
		    },
		    required: true
		});
		
		//加载业务树
		$zTreeMenuBusiness.tree({
			url: '<%=basePath%>deptorg/searchOrgTree.do?node=3401000000000000',
			parentField:"pid",
			textFiled:"orgName",
			idFiled:"businessId",
			lines:true,
			animate:true,
			onClick: function(node){
				curDeptNo = node.businessId;
				$("#dg").datagrid('load', {
					deptNo:curDeptNo
				}); 
		    }
		});
});

//增加弹窗
function add(){
	$("#cadreAppointRecordForm").form("clear");
	jiaTDG('');
	$("#dlg_edit").dialog({title : "新增干部任命单信息"});
	$('#dg').datagrid('clearSelections'); 
	$("#dlg_edit").dialog("open");
}

function view(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$('#show_cadreAppointApply').form('load',{
			applyId : rows[0].applyId,
			empId : rows[0].empId,
			xingMing : rows[0].xingMing,
			xingBie : rows[0].xingBie,
			chuShengRiQi : rows[0].chuShengRiQi,
			chuShengDi : rows[0].chuShengDi,
			minZu : rows[0].minZu,
			jiGuan : rows[0].jiGuan,
			ruDangShiJian : rows[0].ruDangShiJian,
			gongZuoShiJian : rows[0].gongZuoShiJian,
			jianKangZhuangKuang : rows[0].jianKangZhuangKuang,
			jiShuZhiWu : rows[0].jiShuZhiWu,
			zhuanChang : rows[0].zhuanChang,
			riZhiJiaoYu : rows[0].riZhiJiaoYu,
			xueXiao : rows[0].xueXiao,
			xianRenZhiWu : rows[0].xianRenZhiWu,
			niRenZhiWu : rows[0].niRenZhiWu,
			niMianZhiWu : rows[0].niMianZhiWu,
			jianLi : rows[0].jianLi,
			jiangCheng : rows[0].jiangCheng,
			nianDuKaoHe : rows[0].nianDuKaoHe,
			renMianLiYou : rows[0].renMianLiYou,
			danWeiYiJian : rows[0].danWeiYiJian,
			shenPiYiJian : rows[0].shenPiYiJian,
			jiGuanYiJian : rows[0].jiGuanYiJian
		});
		 var jTgrid = $("#jiaTDGView").datagrid({  
		     url:"<%=basePath%>cadreApply/findJiaTingChengYuan.do?empId="+rows[0].empId,
		      pagination:false,//显示分页  
		      rownumbers:true,//显示行号
		      pageSize:8,//分页大小  
		      fitColumns:true, 
			  loadFilter : function(data){
				 //过滤数据
				if(data.items==null || data.items==""){
						return {total:0,rows:[]}; 
				}else{
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
				{field : 'id',width : 150,hidden:true},
				{field:'chengWei',title:'称为',width:100},
			    {field:'xingMingJt',title:'姓名',width:100},
				{field:'nianLing',title:'年龄',width:100},
				{field:'zhengZhiMianMao',title:'政治面貌',width:100},
				{field:'gongZuoZhiWu',title:'工作单位及职务',width:100}
				] ],
 			    singleSelect: true,  //是否单选   
				selectOnCheck : true,
				checkOnSelect : true,
				height : 300,
			});
		$('#dlg_view').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//增加弹窗提交
function showSubmit(){
	//判断是新增还是更新
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		if($("#cadreAppointRecordForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>cadreApply/updateCadreAppointRecord.do" ,
				data: $('#cadreAppointRecordForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code == 0){
						$.messager.show({
							title:'提示',
							msg:'更新成功',
							timeout:3000,
							showType:'slide'
						});
						//插入修改干部任命信息日志
						insertSysLog("干部任命信息","修改","单号为"+$("#e_applyId").val()+"的记录单");
						$('#dg').datagrid('reload');
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
	}else{
		if($("#cadreAppointRecordForm").form('validate')){
			$.ajax({  
				type: 'POST',
				url: "<%=basePath%>cadreApply/addCadreAppointRecord.do" ,
				data: $('#cadreAppointRecordForm').serialize(),
				dataType: 'json',
				success: function(data){
					if(data.code==0){
						$.messager.show({
							title:'提示',
							msg:'新增成功',
							timeout:3000,
							showType:'slide'
						});
						//插入新增干部任命信息日志
						insertSysLog("干部任命信息","新增","单号为"+data.id+"的记录单");
						$('#dg').datagrid('reload');
						$('#dlg_edit').dialog('close');
					}else{
						$.messager.alert('新增发生错误，请于管理员联系');
					}
				} ,
				error: function(){
					$.messager.alert('新增发生错误，请于管理员联系');
				}
			});
		}else{
			$.messager.alert('错误提示','请完善红色必填项');
		}
	}
}

//更新
function update(){
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length==1){
		$('#cadreAppointRecordForm').form('load',{
			applyId : rows[0].applyId,
			empId : rows[0].empId,
			xingMing : rows[0].xingMing,
			xingBie : rows[0].xingBie,
			chuShengRiQi : rows[0].chuShengRiQi,
			chuShengDi : rows[0].chuShengDi,
			minZu : rows[0].minZu,
			jiGuan : rows[0].jiGuan,
			ruDangShiJian : rows[0].ruDangShiJian,
			gongZuoShiJian : rows[0].gongZuoShiJian,
			jianKangZhuangKuang : rows[0].jianKangZhuangKuang,
			jiShuZhiWu : rows[0].jiShuZhiWu,
			zhuanChang : rows[0].zhuanChang,
			riZhiJiaoYu : rows[0].riZhiJiaoYu,
			xueXiao : rows[0].xueXiao,
			xianRenZhiWu : rows[0].xianRenZhiWu,
			niRenZhiWu : rows[0].niRenZhiWu,
			niMianZhiWu : rows[0].niMianZhiWu,
			jianLi : rows[0].jianLi,
			jiangCheng : rows[0].jiangCheng,
			nianDuKaoHe : rows[0].nianDuKaoHe,
			renMianLiYou : rows[0].renMianLiYou,
			danWeiYiJian : rows[0].danWeiYiJian,
			shenPiYiJian : rows[0].shenPiYiJian,
			jiGuanYiJian : rows[0].jiGuanYiJian
		});
		jiaTDG(rows[0].empId);
		$("#dlg_edit").dialog({title: "修改干部任命信息"});
		$('#dlg_edit').dialog('open');
	}else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}


//删除
function del(){
	var rows = $('#dg').datagrid('getSelections');
    var applyId="";
    for(var i=0;i<rows.length;i++){
    	applyId +=rows[i].applyId + ",";
     //获取选中节点的值
    }
    applyId = applyId.substring(0,applyId.length-1);
	if(rows.length>0){
		$.messager.confirm('提示框', '你确定要删除吗?',function(r){
			if(r){
				$.ajax({
					url: '<%=basePath%>cadreApply/deleteCadreAppointRecord.do?id=' + applyId,
							success:function(){
								$('#dg').datagrid('reload')
								$.messager.show({
									title:'提示',
									msg:'删除成功',
									timeout:3000,
									showType:'slide'
								});
								//插入删除干部任命信息日志
								insertSysLog("干部任命信息","删除","单号为"+applyId+"的记录单");
							}
				});
			}
		})
	}else{
		$.messager.alert("操作提示", "请选择！");
	}
}

//更新任命人信息
function updateApplyPerson(empId,empName) {
	if(flag){//干部姓名
		$("#empId").val(empId);
		$("#empName").textbox("setValue", empName);
		//选择干部后跟带现岗位，现职称，现职务
		$.ajax({  
			type: 'POST',
			url: "<%=basePath%>sysEmp/load.do",    
			data: {"id":empId},
			dataType: 'json',
			success: function(data){
				$('#cadreAppointRecordForm').form('load',{
					empId : data.id,
					xingMing : data.name,
					xingBie : data.sex,
					chuShengRiQi : data.birthday,
					chuShengDi : data.birthPlace,
					minZu : data.nation,
					jiGuan : data.nativePlace,
					ruDangShiJian : data.entryDate,
					gongZuoShiJian : data.workDate,
// 					jiShuZhiWu : data.jiShuZhiWu,
					riZhiJiaoYu : data.eduRec,
					xianRenZhiWu : data.job
				});
			} ,
			error: function(){
				$.messager.alert('系统发生错误，请于管理员联系');
			}
		});
	}else{
		$("#opPerId").val(empId);
		$("#opPerName").textbox("setValue", empName);
	}
}
//弹出打印任免审批表窗口
function print(){debugger;
	var rows = $('#dg').datagrid('getSelections');
	if (rows.length==1) {
		location.href="<%=basePath%>cadreApply/printCLD.do?applyId="+rows[0].applyId+"";;
	} else if(rows.length>1){
		$.messager.alert("操作提示", "只能选择一行！");
	} else {
		$.messager.alert("操作提示", "请选择！");
	}
}
function jiaTDG(id){
	 var jTgrid = $("#jiaTDG").datagrid({  
	     url:"<%=basePath%>cadreApply/findJiaTingChengYuan.do?empId="+id,
	      pagination:false,//显示分页  
	      rownumbers:true,//显示行号
	      pageSize:8,//分页大小  
// 	      pageList:[20,40,60,80],//每页的个数  
		 // fit:true,//自动补全  
	      fitColumns:true, 
		  loadFilter : function(data){
			 //过滤数据
			if(data.items==null || data.items==""){
					return {total:0,rows:[]}; 
			}else{
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
				{field : 'id',width : 150,hidden:true},
				{field:'chengWei',title:'称为',width:100},
			    {field:'xingMingJt',title:'姓名',width:100},
				{field:'nianLing',title:'年龄',width:100},
				{field:'zhengZhiMianMao',title:'政治面貌',width:100},
				{field:'gongZuoZhiWu',title:'工作单位及职务',width:100},
				{field:'_operate',width:80,align:'center',formatter:formatOper,title:'操作'}
			] ],
			toolbar : [ { 
				text : '添加家庭成员', 
				iconCls : 'icon-add', 
				handler : function() {
						$("#jiaTDG").datagrid('endEdit', 0); 
						jTgrid.datagrid('insertRow', { index : 0, row : {
							id : '0',
							chengWei : '',
							xingMingJt : '',
							nianLing : '',
							zhengZhiMianMao : '',
							gongZuoZhiWu : ''
						} }); 
						editRow = 0; 
						jTgrid.datagrid('selectRow', 0); 
						jTgrid.datagrid('beginEdit', 0); 
					} 
				},{
					text : '删除家庭成员', 
					iconCls : 'icon-remove', 
					handler : function() {
							deleteFamily();
						} 
				}
				], 
			 onDblClickRow: function ( field, row) {
				 updateGroups(field,row);
		        },
		    singleSelect: true,  //是否单选   
			selectOnCheck : true,
			checkOnSelect : true,
			height : 300,
		});
}
function formatOper(val,row,index){  
    return '<a href="#" onclick="updateName(\''+row.id+'\')">保存</a>';  
} 
//这个用行rows写的 一次修改一行
function updateGroups(field, row) {
        $('#jiaTDG').datagrid('updateRow', {
            index: field,
            row: {
            	chengWei: '<input  type="text"   id="chengWei" name="chengWei"  value="' + row.chengWei+ '"  />',
            	xingMingJt: '<input  type="text"   id="xingMingJt" name="xingMingJt"  value="' + row.xingMingJt+ '"   />',
                nianLing: '<input  type="text"   id="nianLing" name="nianLing"  value="' + row.nianLing+ '"   />',
                zhengZhiMianMao: '<input  type="text"   id="zhengZhiMianMao" name="zhengZhiMianMao"  value="' + row.zhengZhiMianMao+ '"  />',
                gongZuoZhiWu: '<input  type="text"   id="gongZuoZhiWu" name="gongZuoZhiWu"  value="' + row.gongZuoZhiWu+ '"  />',
                iconCls: 'icon-save'
            }
        });
}
//这个是离开输入框时间 提交
function updateName(id){
		$("#jiaTDG").datagrid('endEdit', 0); 
		 var   id = id;
		 var   chengWei=document.getElementById("chengWei").value;
		 var   xingMingJt=document.getElementById("xingMingJt").value;
		 var   nianLing=document.getElementById("nianLing").value;
		 var   zhengZhiMianMao=document.getElementById("zhengZhiMianMao").value;
		 var   gongZuoZhiWu=document.getElementById("gongZuoZhiWu").value;
		 if($("#empId").val()== null || $("#empId").val() ==''){
			 $.messager.alert("操作提示", "请选择人员后再添加！");
		 }else{
		      $.ajax({
			        type:'post',
			        url:'<%=basePath%>cadreApply/saveJiaTingChengYuan.do?',
			        data: {
			        	"id":id,
			        	"chengWei":chengWei,
			        	"xingMingJt":xingMingJt,
			        	"nianLing":nianLing,
			        	"zhengZhiMianMao":zhengZhiMianMao,
			        	"gongZuoZhiWu":gongZuoZhiWu,
			        	"empId" : $("#empId").val()
			        	},
			        success:function  (data){
			        	if(data==0){
			        		var id = $("#empId").val();
			        		jiaTDG(id);
			        	}
			        }
			      })
		 }
}
//删除家庭成员
function deleteFamily(){
	var rows = $('#jiaTDG').datagrid('getSelections');
	if(rows.length !=1 ){
		$.messager.alert("操作提示", "请选择所需删除的数据！");
	}else{
		  $.ajax({
		        type:'post',
		        url:'<%=basePath%>cadreApply/deleteJiaTingChengYuan.do?id='+rows[0].id,
		        success:function  (data){
		        	if(data==0){
		        		var id = $("#empId").val();
		        		jiaTDG(id);
		        	}
		        }
		  })
	}
}

</script>
</head>

<body>
	<div class="easyui-layout" style="width:100%;height:600px;" data-options="fit:true">
		<!-- 部门列表模块 start -->
		<div data-options="region:'west',iconCls:'icon-ok',split:true" title="部门列表" style="width:20%">
			<div >
				<ul id="zTreeMenuBusiness" class="easyui-tree">
				</ul>
			</div>
		</div>
		<!-- 部门列表模块 end -->
	<!-- 查询表单模块 start -->
	<div  data-options="region:'center',title:'干部任命信息',iconCls:'icon-ok'">
		<div id="search" style="text-align:left;padding:5px 0 25px 15px;height:36px">
			<form action="" id="searchForm">
				<input  id="s_empName"  name="empName" class="easyui-textbox" data-options="label:'干部姓名:'" style="width:250px"/>
<!-- 				<input  id="s_opPerName"  name="opPerName" class="easyui-textbox" data-options="label:'经办人:'" style="width:250px"/><br/> -->
<!-- 				<input  id="s_appDateStart"  name="appDateStart" class="easyui-datebox" data-options="label:'任命时间:'" style="width:250px"/>至 -->
<!-- 				<input  id="s_appDateEnd"  name="appDateEnd" class="easyui-datebox" data-options="" style="width:160px"/> -->
				<a  name="doSearch" id="doSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
				<a  name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left: 5px">重置</a>
			 </form>
		</div>
	
	<!-- 查询表单模块end -->
	<!-- 列表模块 start -->
	<div style="display:block;padding:5px 5px 5px 5px;margin:5px 0 10px 0;">
		<table id="dg" style="width:100%;"></table>  
	</div>
	<!-- 列表模块 end -->
	</div>
   </div>
<div id="dlg_edit" class="easyui-dialog" closed="true" title="干部任命信息记录" style="width: 700px; height: 550px; padding: 10px;display:none; top:40px; left:150px"  data-options="buttons: '#dlg-dealButtons',modal:true">
		<!-- 干部任命信息form模块 start -->
		<form action="" id="cadreAppointRecordForm" name="cadreAppointRecordForm">
			<input type="hidden" name="applyId" id="e_applyId" />
		    <input type="hidden" name="empId" id="empId" /> 
		    <input type="hidden" name="opPerId" id="opPerId" /> 

 			<div style="border: solid 1px #95B8E7;  border-radius: 4px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					 <span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">基本信息</span>
				</div>
			    <div style="padding: 20px 20px;">
					<span class="spanStyle">姓名:</span><input  id="xingMing"  name="xingMing" class="easyui-textbox" data-options="required:true,editable:false" style="width:150px"/><span style="color:red;">*</span>
					&nbsp;<span class="spanStyle">性别:</span><select  class="easyui-combobox" id="xingBie" name="xingBie" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" required="required" >
						<option value=""></option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select><span style="color:red;">*</span><br />
					<span class="spanStyle">出生年月:</span><input  id="chuShengRiQi"  name="chuShengRiQi" class="easyui-datebox" data-options="label:''" style="width:150px" required="required"/><span style="color:red;">*</span>
					&nbsp;<span class="spanStyle">出生地:</span><input  id="chuShengDi"  name="chuShengDi" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span><br />
					<span class="spanStyle">民族:</span><input  id="minZu"  name="minZu" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">籍贯:</span><input  id="jiGuan"  name="jiGuan" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span><br />
					<span class="spanStyle">入党时间:</span><input  id="ruDangShiJian"  name="ruDangShiJian" class="easyui-datebox" style="width:150px" /><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">参加工作时间:</span><input  id="gongZuoShiJian"  name="gongZuoShiJian" class="easyui-datebox" data-options="label:''" style="width:150px" /><span style="color:white;">*</span><br />
					<span class="spanStyle">健康状况:</span><input  id="jianKangZhuangKuang"  name="jianKangZhuangKuang" class="easyui-textbox"  style="width:150px"/><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">专业技术职务:</span><input  id="jiShuZhiWu"  name="jiShuZhiWu" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span><br/>
					<span class="spanStyle">专长:</span><input  id="zhuanChang"  name="zhuanChang" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">全日制教育:</span><select  class="easyui-combobox" id="riZhiJiaoYu" name="riZhiJiaoYu" labelPosition="right" style="width:150px" data-options="editable:false,panelHeight:'auto',valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'"  required="required">
					</select><span style="color:red;">*</span><br />
					<span class="spanStyle">毕业院校（全日制）:</span><input  id="xueXiao"  name="xueXiao" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">现任职务:</span><input  id="xianRenZhiWu"  name="xianRenZhiWu" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span><br />
					<span class="spanStyle">拟任职务:</span><input  id="niRenZhiWu"  name="niRenZhiWu" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
					&nbsp;<span class="spanStyle">拟免职务:</span><input  id="niMianZhiWu"  name="niMianZhiWu" class="easyui-textbox" style="width:150px"/><span style="color:white;">*</span>
					<br /> <br />
				</div>
			</div>
			<div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	               <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					 <span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">简历</span>
				   </div>
				   <div style="padding: 20px 20px;">	       
						<span class="spanStyle">简历:</span><textarea id="jianLi" name="jianLi"
						class="easyui-textbox" data-options="multiline:true"
						style="width: 475px;height: 80px;"></textarea>
						<span class="spanStyle">奖惩情况:</span><textarea id="jiangCheng" name="jiangCheng"
						class="easyui-textbox" data-options="multiline:true"
						style="width: 475px;height: 80px;"></textarea>
						<span class="spanStyle">年度考核结果:</span><textarea id="nianDuKaoHe" name="nianDuKaoHe"
						class="easyui-textbox" data-options="multiline:true"
						style="width: 475px;height: 80px;"></textarea>
						<span class="spanStyle">任免理由:</span><textarea id="renMianLiYou" name="renMianLiYou"
						class="easyui-textbox" data-options="multiline:true"
						style="width: 475px;height: 80px;"></textarea>
						<br /> <br />
				   </div>
            </div>
              
            <div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					<span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">家庭成员</span>
				</div>
				<div style="padding: 20px 20px;">
					 <table id="jiaTDG"  title="家庭主要成员及社会关系"  style="display:block;" style="width:80%"></table> 
				</div>
            </div>
			
			<div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					<span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">审批情况</span>
				</div>
				<div style="padding: 20px 20px;">
					<span class="spanStyle">呈报单位:</span><textarea id="danWeiYiJian" name="danWeiYiJian"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 475px;height: 80px;"></textarea>
					<span class="spanStyle">审批机关意见:</span><textarea id="shenPiYiJian" name="shenPiYiJian"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 475px;height: 80px;"></textarea>
					<span class="spanStyle">行政机关意见:</span><textarea id="jiGuanYiJian" name="jiGuanYiJian"
					class="easyui-textbox" data-options="multiline:true"
					style="width: 475px;height: 80px;"></textarea>
				</div>
            </div>
			
		</form>
		<!-- 干部任命信息form模块 end -->
	</div>
	<div id="dlg-dealButtons" style="display: none;">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'"  onclick="javascript:$('#dlg_edit').dialog('close')">关闭</a>
	</div>
	
	
	
	

<div id="dlg_view" class="easyui-dialog" closed="true" title="查看干部任命信息记录" style="width: 700px; height: 550px; padding: 10px;display:none; top:40px; left:150px"  data-options="modal:true,buttons: '#dlgView-buttons'">
 	<form action="" id="show_cadreAppointApply" style="background-color:#ECF6FA">
 			<div style="border: solid 1px #95B8E7;  border-radius: 4px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					 <span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">基本信息</span>
				</div>
			    <div style="padding: 20px 20px;">
					<span class="spanStyle">姓名:</span><input  id="xingMing"  name="xingMing" class="easyui-textbox"  style="width:150px"readonly="readonly"/>
					&nbsp;<span class="spanStyle">性别:</span><select  class="easyui-combobox" id="xingBie" name="xingBie" data-options="editable:false,panelHeight:'auto'"  labelPosition="right" style="width:150px" required="required" readonly="readonly">
						<option value=""></option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select><br />
					<span class="spanStyle">出生年月:</span><input  id="chuShengRiQi"  name="chuShengRiQi" class="easyui-datebox" data-options="label:''" style="width:150px"readonly="readonly"/>
					&nbsp;<span class="spanStyle">出生地:</span><input  id="chuShengDi"  name="chuShengDi" class="easyui-textbox" style="width:150px"readonly="readonly"/><br />
					<span class="spanStyle">民族:</span><input  id="minZu"  name="minZu" class="easyui-textbox" style="width:150px"readonly="readonly"/>
					&nbsp;<span class="spanStyle">籍贯:</span><input  id="jiGuan"  name="jiGuan" class="easyui-textbox" style="width:150px"readonly="readonly"/><br />
					<span class="spanStyle">入党时间:</span><input  id="ruDangShiJian"  name="ruDangShiJian" class="easyui-datebox" style="width:150px" readonly="readonly"/>
					&nbsp;<span class="spanStyle">参加工作时间:</span><input  id="gongZuoShiJian"  name="gongZuoShiJian" class="easyui-datebox" data-options="label:''" style="width:150px" readonly="readonly"/><br />
					<span class="spanStyle">健康状况:</span><input  id="jianKangZhuangKuang"  name="jianKangZhuangKuang" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					&nbsp;<span class="spanStyle">专业技术职务:</span><input  id="jiShuZhiWu"  name="jiShuZhiWu" class="easyui-textbox" style="width:150px" readonly="readonly"/><br/>
					<span class="spanStyle">专长:</span><input  id="zhuanChang"  name="zhuanChang" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					&nbsp;<span class="spanStyle">全日制教育:</span><select  class="easyui-combobox" id="riZhiJiaoYu" name="riZhiJiaoYu" labelPosition="right" style="width:150px" data-options="editable:false,panelHeight:'auto',valueField:'studyCode',textField:'studyName',url:'<%=basePath%>studyType/all.do'"  required="required">
					</select><br />
					<span class="spanStyle">毕业院校（全日制）:</span><input  id="xueXiao"  name="xueXiao" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					&nbsp;<span class="spanStyle">现任职务:</span><input  id="xianRenZhiWu"  name="xianRenZhiWu" class="easyui-textbox" style="width:150px" readonly="readonly"/><br />
					<span class="spanStyle">拟任职务:</span><input  id="niRenZhiWu"  name="niRenZhiWu" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					&nbsp;<span class="spanStyle">拟免职务:</span><input  id="niMianZhiWu"  name="niMianZhiWu" class="easyui-textbox" style="width:150px" readonly="readonly"/>
					<br /> <br />
				</div>
			</div>
			<div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	               <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					 <span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">简历</span>
				   </div>
				   <div style="padding: 20px 20px;">	       
						<span class="spanStyle">简历:</span><textarea id="jianLi" name="jianLi"
						class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 475px;height: 80px;" readonly="readonly"></textarea>
						<span class="spanStyle">奖惩情况:</span><textarea id="jiangCheng" name="jiangCheng"
						class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 475px;height: 80px;" readonly="readonly"></textarea>
						<span class="spanStyle">年度考核结果:</span><textarea id="nianDuKaoHe" name="nianDuKaoHe"
						class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 475px;height: 80px;" readonly="readonly"></textarea>
						<span class="spanStyle">任免理由:</span><textarea id="renMianLiYou" name="renMianLiYou"
						class="easyui-textbox" data-options="multiline:true,required:true"
						style="width: 475px;height: 80px;" readonly="readonly"></textarea>
						<br /> <br />
				   </div>
            </div>
              
            <div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					<span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">家庭成员</span>
				</div>
				<div style="padding: 20px 20px;">
						<table id="jiaTDGView"  title="家庭主要成员及社会关系"  style="display:block;" style="width:80%"></table>
				</div>
            </div>
			
			<div style="border: solid 1px #95B8E7;  border-radius: 4px;margin-top: 10px;">
	            <div style="  background-color: #E0FFFF;  padding: 10px 15px;  border-radius: 4px 4px 0 0;  border-bottom: solid 1px #ddd;">
					<span style="margin-left: 5px;  font-size: 14px;  font: inherit;font-weight:bold;">审批情况</span>
				</div>
				<div style="padding: 20px 20px;">
					<span class="spanStyle">呈报单位:</span><textarea id="danWeiYiJian" name="danWeiYiJian"
					class="easyui-textbox" data-options="multiline:true,required:true"
					style="width: 475px;height: 80px;" readonly="readonly"></textarea>
					<span class="spanStyle">审批机关意见:</span><textarea id="shenPiYiJian" name="shenPiYiJian"
					class="easyui-textbox" data-options="multiline:true,required:true"
					style="width: 475px;height: 80px;" readonly="readonly"></textarea>
					<span class="spanStyle">行政机关意见:</span><textarea id="jiGuanYiJian" name="jiGuanYiJian"
					class="easyui-textbox" data-options="multiline:true,required:true"
					style="width: 475px;height: 80px;" readonly="readonly"></textarea>
				</div>
            </div>
    </form>
</div>

	
</body>
</html>
