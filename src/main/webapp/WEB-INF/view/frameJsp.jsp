<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ustcsoft.framework.vo.UserVO"%>
<%@page import="com.ustcsoft.jt.vo.User"%>
<%@page
	import="org.springframework.security.core.context.SecurityContext"%>
<%
	SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
	User user = (User) context.getAuthentication().getPrincipal();
	UserVO userInfo = user.getUserVo();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人力资源管理系统</title>
<%@include file="/common/common_easyui.jsp"%>
<script type="text/javascript" src="<%=basePath%>scripts/frameJsp.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/slider.js"></script>
<style>
body, html {
}
.spanStyle{display:inline-block;width:100px;text-align:left;}
.notice { width:25px; height: 25px; position: absolute; z-index:999; top:14px; right: 286px; background:url(<%=basePath%>style/layout/images/icon.png) no-repeat 0 -390px; }
.notice a { width:20px; height: 20px; position: absolute; z-index:1000; top:-7px; right: -5px; text-align:center; line-height:20px; background: red; color: #fff; font-size: 12px; border-radius:10px; }

#div_pannel {
	height: 770px;
}

* {
	margin: 0;
	padding: 0;
}

#div_tab {
	position: relative;
	top: 5px;
	border-bottom: 1px solid #e0e0e0;
	padding-left: 10px;
}

#div_tab li {
	float: left;
	position: relative;
}

#div_tab li a {
	display: inline-block;
	background: #eee;
	border: 1px solid #e0e0e0;
	border-bottom: none;
	padding: 4px 20px;
	margin: 0 3px;
	border-radius: 5px 5px 0 0;
	font-size: 14px;
	font-family: "Microsoft YaHei", SimHei, arial;
}

#div_tab li.crent a {
	color: #fff;
	background: #3c96d2;
	border: 1px solid #2c86c3;
}

#div_tab li .win_close, #div_tab li.crent .win_close {
	position: absolute;
	top: 5px;
	right: 8px;
	display: inline-block;
	width: 11px;
	height: 11px;
	cursor: pointer;
	background: none;
	background: url(<%=basePath%>style/layout/images/icon.png) no-repeat center center;
	border: 0;
	padding: 0;
	margin: 0;
}

#div_tab li .win_close {
	background-position: -117px -437px;
}

#div_tab li.crent .win_close {
	background-position: -117px -458px;
}

#div_tab li .win_close:hover {
	background-position: -117px -437px;
	top: 4px;
	right: 8px;
}

#div_tab li.crent .win_close:hover {
	background-position: -117px -458px;
	top: 4px;
	right: 8px;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

* html .clearfix {
	height: 1%;
}

*+html .clearfix {
	height: 1%;
}

.clearfix {
	display: inline-block;
}
/* Hide from IE Mac */
.clearfix {
	display: block;
}
</style>
<link href="<%=basePath%>style/layout/style.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>style/layout/nav.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>style/layout/styleKuaiJie.css" type="text/css"	rel="stylesheet" />
<script type="text/javascript">
	var path="<%= basePath%>";
	var marqueeContent;
	var marqueeInterval=new Array(10);  //定义一些常用而且要经常用到的变量
	var marqueeId=0;
	var marqueeDelay=3000;
	var marqueeHeight=20;
	function pageLoad(){
		var menu=document.getElementById("sidebar_inner");
		var ifm = document.createElement("iframe");
		ifm.height='100%';
		ifm.width='100%';
		ifm.frameBorder = '0';
		ifm.style.zIndex=10;
		ifm.style.visibility='hidden';
		ifm.style.display='block';
		menu.appendChild(ifm);
		shouYeInit();
	}
	function showMenu(){
			if(document.getElementById("sidebar_inner").style.display=="block"){
				document.getElementById("gongNengCaiDan").title="点击展开菜单";
				$("#gongNengCaiDan").css("background",'url(' + path + 'pages/layoutNew/images/k.png) no-repeat center #145987');
			 	$("#center").animate({marginLeft:"-160px"},200,null,function(){
					document.getElementById("sidebar_inner").style.display="none";
					
				}); 
			}else{
				document.getElementById("gongNengCaiDan").title="点击收起菜单";
				$("#gongNengCaiDan").css("background",'url(' + path + 'pages/layoutNew/images/s.png) no-repeat center #145987');
				document.getElementById("sidebar_inner").style.display="block";
			  	$("#center").animate({marginLeft:"0px"},200,null,function(){
				});  
			  	var fff = ($(window).width() -160)+"px";
			  	$("#center").animate({width:fff},200,null,function(){
			  		$("#center").css("width","");
				}); 
			}
			
	}
	function showHidden(){
		if(document.getElementById("hiddenDiv")){
			$("#hiddenDiv").show();
//			document.getElementById("hiddenDiv").style.display="block";
		document.getElementById("showMore").style.display="none";
		document.getElementById("showLess").style.display="block";
		}
	}
	function Hidden(){
		if(document.getElementById("hiddenDiv")){
			$("#hiddenDiv").hide();
//			document.getElementById("hiddenDiv").style.display="none";
		document.getElementById("showMore").style.display="block";
		document.getElementById("showLess").style.display="none";
		}
	}
	function hideHeader(){
		if(document.getElementById("header").style.display=="block"){//隐藏
				$("#sidebar").animate({top:"0px"},200,null,function(){
				});
				$("#header").animate({height:"0px"},200,null,function(){
					document.getElementById("header").style.display="none";	
					document.getElementById("div_pannel").style.height=document.documentElement.clientHeight-100+"px";
				 	var fff = ($(window).height() -20)+"px";
					$("#leftAndCenter").animate({height:fff},200,null,function(){
				  		$("#leftAndCenter").css("height","");		
					}); 
					$("#center").animate({height:fff},200,null,function(){
					});
				}); 
				$("#quanPing").animate({top:"5px"},200,null,function(){
				});
				
				if(document.getElementById("sidebar_inner").style.display=="block"){
					showMenu();
					}else{
					}
		}else{
				$("#sidebar").animate({top:"80px"},200,null,function(){
				});
				$("#header").animate({height:"80px"},200,null,function(){
					document.getElementById("header").style.display="block";
					document.getElementById("div_pannel").style.height=document.documentElement.clientHeight-180+"px";	
				}); 
			 	var fff = ($(window).height() -80)+"px";
				$("#leftAndCenter").animate({height:fff},200,null,function(){
			  		$("#leftAndCenter").css("height","");
				}); 
				$("#center").animate({height:fff},200,null,function(){
				});
				$("#quanPing").animate({top:"85px"},300,null,function(){
				});
				 if(document.getElementById("sidebar_inner").style.display=="block"){
					}else{
						showMenu();
					}
		  }
	}

	var unreadGGS=0;
	$.ajax({
		url: "<%=basePath%>sysNotice/serchUnreadGG.do" ,
		success: function(data){
			$("#uGGTS").text(data);
		},
		error: function(){
			alert("获取未读公告条数失败");
		}
	});
	
	function showGG(){
		CreateDiv("0700200000",'system/viewRAnnouncement.do','系统公告','notrefresh')
	}
	
	function getUnreadGGS(){
		$.ajax({
			url: "<%=basePath%>sysNotice/serchUnreadGG.do" ,
			success: function(data){
				$("#uGGTS").text(data);
			},
			error: function(){
				alert("获取未读公告条数失败");
			}
		});
	}
	function ownInfoSetWinInit(){
		$("#sysUserForm").form("clear");
		$.ajax({
			type: 'POST',
			url: "<%=basePath%>sysUser/querySysUserById.do" ,
			success: function(data){
				if(data){
					$('#sysUserForm').form('load',{
						userId:data.userId,
						orgId:data.orgId,
						userName:data.userName,
						name:data.name,
						sex:data.sex,
						idNo:data.idNo,
						tel:data.tel,
						email:data.email
					});
					$("#dlg_user").dialog("open");
				}
			},
			error: function(){
				alert("获取个人信息失败");
			}
		});
	}
	function submitUser(){
		if($("#sysUserForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>sysUser/updateUser.do" ,
				data: $('#sysUserForm').serialize(),
				dataType: 'text',
				success: function(data){
					if(data == 0){
						$.messager.show({
							title:'提示',
							msg:'更新成功',
							timeout:3000,
							showType:'slide'
						});
						$('#dlg_user').dialog('close');
					}else if(data==1){
						$.messager.alert('错误提示','用户名重复');
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','失败');
				}
			});
		}
	}
	$.extend($.fn.validatebox.defaults.rules, {
	    equals: {
			validator: function(value,param){
				return value == $(param[0]).val();
			},
			message: '新密码不一致'
	    }
	});
	function updatePasswordWindowInit(){
		$("#userPWDForm").form("clear");
		$("#dlg_PWD").dialog("open");
	}
	
	function submitPWD(){
		if($("#userPWDForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>sysUser/updatePassword.do" ,
				data: {
					oldPassword: $("#oldPWD").val(),
					newPassword : $("#pwd").val()
				},
				dataType: 'JSON',
				success: function(data){
					if(data==1){
						$.messager.alert('提示','修改成功');
						$('#dlg_PWD').dialog('close');
					}else if(data==0){
						$.messager.alert('错误提示','旧密码错误');
					}
				} ,
				error: function(){
					$.messager.alert('错误提示','用户名重复');
				}
			});
		}
	}
</script>
</head>

<body onload="pageLoad()" style="overflow:hidden;background:#fff;">
	<div class="notice">
		<a href="javascript:;" onclick="showGG()" id='uGGTS'></a>
	</div>

	<div class="warp_con">
		<a name="maoDingWei"></a>
		<div class="wrap header" id="header" style="display: block">
			<div class="logo">
				<a  target="_blank"><img
					src="<%=basePath%>style/layout/images/logo.png" alt="人力资源管理系统"
					title="人力资源管理系统" /></a><%-- span class="logo_nm"><img
					src="<%=basePath%>style/layout/images/xtm.png" /></span> --%>
			</div>
			<div class="qlink">
				<span class="qlink_t float_l"><a title='查看当前用户个人信息' 	href="javascript:ownInfoSetWinInit();">个人信息</a>-
				<a title='修改当前用户密码' href="javascript:updatePasswordWindowInit();">修改密码</a>-
<!-- 				<a title='综合执法用户切换' href="javascript:com.ustcsoft.jt.layout.header.switchUserWinInit();">切换用户</a>- -->
<!-- 				<a title='帮助信息' href="javascript:com.ustcsoft.jt.layout.header.openHelpDocumentWindow('D');">帮助</a>- -->
				<a title='退出系统' href="j_spring_security_logout">退出</a></span><br/> 
				<span class="qlink_b float_r">单位：<%=userInfo.getOrgName()%>
				&nbsp;&nbsp; 当前用户：<span class="yl"><%=userInfo.getName()%></span></span>
			</div>
		</div>
		<div id="leftAndCenter">
			<div class="sidebar" id="sidebar">
				<h3 class="font_yh" onclick="showMenu()">
					功能菜单<i title="点击收起菜单" id="gongNengCaiDan" class="gongNengCaiDan"></i>
				</h3>
				<div id="sidebar_inner" class="sidebar_inner" style="display: block">
					<ul id="sidebar_innerUL">
					<!-- 菜单存放处 -->
					</ul>
					<ul id="sidebar_innerUL2">
					<!-- 菜单存放处 -->
					</ul>
					<ul id="sidebar_innerUL3">
					<!-- 菜单存放处 -->
					</ul>
				</div>

			</div>

			<div class="main" id="main">
				<div id="center" style="background-color: white">
<!-- 					<div class="main_t"> -->
<!-- 					</div> -->

					<ul class="clearfix" id="div_tab"></ul>
					<div class="main_tab" id="main_tab">
						<div id="div_pannel"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

<div id="dlg_user" class="easyui-dialog" closed="true" title="个人信息" style="width: 320px; height: 310px; padding: 5px 0 0 20px"data-options="buttons: '#dlg-buttons', modal:true">
	<form action="" id="sysUserForm" name="sysUserForm">
		     <input type="hidden" name="userId" id="userId" />
		     <div style="padding-bottom: 5px">
		     	 <input  id="userName"  name="userName" class="easyui-textbox" data-options="label:'用户名:'" style="width:250px;" readonly="readonly"/>
		     </div>
 		     <div style="padding-bottom: 5px">
 		     		<input  id="name" name="name" class="easyui-textbox" data-options="label:'姓名:',required:true" style="width:250px;"/><span style="color:red;"> * </span>
 		     </div>
			 <div style="padding-bottom: 5px">
			 		<select  class="easyui-combobox" id="sex" name="sex" data-options="label:'性别:',required:true,editable:false,panelHeight:'auto'"  labelPosition="right" style="width:250px">
						<option value=""></option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select><span style="color:red;"> * </span>
			 </div>
			 <div style="padding-bottom: 5px">
			 		<input  id="idNo" name="idNo" class="easyui-textbox" data-options="label:'身份证号码:'" style="width:250px"/>
			 </div>
			<div style="padding-bottom: 5px">
			      <input  id="tel" name="tel" class="easyui-textbox" data-options="label:'联系号码:'" style="width:250px"/>
			</div>
			<div style="padding-bottom: 5px">
				<input  id="email" name="email" class="easyui-textbox" data-options="label:'邮箱:'" style="width:250px"/>
			</div>
	</form>
</div>	
<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'icon-save'"onclick="submitUser()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"onclick="javascript:$('#dlg_user').dialog('close')">关闭</a>
</div>
<div id="dlg_PWD" class="easyui-dialog" closed="true" title="修改密码" style="width: 320px; height: 180px; padding: 10px 0 0 20px"data-options="buttons: '#dlgPWD-buttons', modal:true">
	<form action="" id="userPWDForm" name="userPWDForm">
		     <div style="padding-bottom: 5px">
		     	 <span class="spanStyle">旧密码:</span><input  id="oldPWD"  name="oldPWD" class="easyui-validatebox" required="required"  style="width:150px;border: 1px solid #95B8E7;"/><span style="color:red;"> * </span>
		     </div>
 		     <div style="padding-bottom: 5px">
 		     	<span class="spanStyle">新密码:</span><input id="pwd" name="pwd" type="password" class="easyui-validatebox" required="required"  style="width:150px;border: 1px solid #95B8E7;"/><span style="color:red;"> * </span>
 		     </div>
			 <div style="padding-bottom: 5px">
				<span class="spanStyle">确认新密码:</span><input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"  style="width:150px;border: 1px solid #95B8E7;" required="required" validType="equals['#pwd']"/><span style="color:red;"> * </span>
			 </div>
	</form>
</div>
<div id="dlgPWD-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="submitPWD()" data-options="iconCls:'icon-save'">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="javascript:$('#dlg_PWD').dialog('close')" data-options="iconCls:'icon-cancel'">关闭</a>
</div>
</body>
</html>
