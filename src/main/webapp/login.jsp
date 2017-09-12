<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<title>系统登录</title>
<%@include file="./common/common_easyui.jsp" %>
<style type="text/css">
	body {margin: 0; padding: 0; background: #387ccd no-repeat fixed top;}
	
	.logo {width: 510px; height: 67px; background: url("images/login_logo.png");margin: 100px auto;}
	
	.login{width: 926px; height: 423px;background: url("images/bg.png");margin: -70px auto;}
	.login .l_username{padding:90px 0 0 200px;}
	.login span{color:#fff;font-weight: bold;}
	.login .l_username input{width:180px;height:30px;border-radius:4px;border: 0;outline: none;}
	.login .l_password{padding:30px 0 0 200px;}
	.login .l_password input{width:180px;height:30px;border-radius:4px;border: 0;outline: none;}
	.login .l_btns{padding:30px 0 0 200px; padding-left: 210px;}
	.login .l_btns input{width:96px; height: 30px; border: 0;font-weight: bold;}
	.login .l_btns input.l_login{background: url("images/login.png");background-size:96px 30px;}
	.login .l_btns input.l_reset{background: url("images/reset.png");background-size:96px 30px;margin-left: 15px;}
</style>
<script>
		var error = '<%=request.getParameter("error")%>';
		if( error != 'null' && error.length > 0){
			alert('${SPRING_SECURITY_LAST_EXCEPTION.message}');
		}
		if (window.top != null && window.top.document.URL != document.URL){            
			window.top.location.href = document.URL;      
		}
		
	
	//是否为空校验
	function isEmpty(s) {
		var lll=trim(s);
		if( lll == null || lll.length == 0 )
			return true;
		else
			return false;
	}
	
	//删除字符串左边的空格
	function ltrim(str) { 
		if(str.length==0)
			return(str);
		else {
			var idx=0;
			while(str.charAt(idx).search(/\s/)==0)
				idx++;
			return(str.substr(idx));
		}
	}

	//删除字符串右边的空格
	function rtrim(str) { 
		if(str.length==0)
			return(str);
		else {
			var idx=str.length-1;
			while(str.charAt(idx).search(/\s/)==0)
				idx--;
			return(str.substring(0,idx+1));
		}
	}

	//删除字符串左右两边的空格
	function trim(str) { 
		return(rtrim(ltrim(str)));
	}

    function login(){
    	var name = document.getElementById("j_username").value;
    	var password = document.getElementById("j_password").value;
	    if( isEmpty(name) || trim(name)=="" || isEmpty(password) || trim(password)== ""){
	       
	      alert("用户名/密码为空,请重新输入!");
	      return false;  
	    }
		document.LoginForm.submit();
		return false;
	}

	function KeyDown(event) {
		event = event ? event : (window.event ? window.event : null);
		if (event.keyCode == 13) {
			event.returnValue = false;
			event.cancel = true;
			window.document.forms[0].submit();
		}
	}
	function fillData(){
		if(browserName.toString().indexOf("mozilla")>-1 && browserVersion.toString().indexOf("11.")>-1){
			browserName="IE";
		}else{
			switch(browserName.toString()) {
			case "maxthon":browserName="遨游";break;
			case "msie":browserName="IE";break;
			case "360se":browserName="360安全浏览器";break;
			case "360ee":browserName="360极速浏览器";break;
			case "theworld":browserName="世界之窗";break;
			case "se":browserName="搜狗浏览器";break;
			case "greenbrowser":browserName="绿色浏览器";break;
			case "qqbrowser":browserName="QQ浏览器";break;
			case "tencenttraveler":browserName="腾讯浏览器";break;
			case "bidubrowser":browserName="百度浏览器";break;
			case "lbbrowser":browserName="猎豹浏览器";break;
			case "trident":browserName="IE";break;
			}
		}
		$("#browsername").val(browserName.toString());
	    $("#browserversion").val(browserVersion.toString());
	}
	
	$(document).ready(function(){  
		var imageArr=document.getElementById("shouyeimg");
		var imageRate = imageArr.offsetWidth / imageArr.offsetHeight;    
		var maxWidth = window.screen.width-22;
		if(imageArr.offsetWidth > maxWidth && maxWidth>1300)
		{
		    imageArr.style.width=maxWidth + "px";
		    imageArr.style.Height=maxWidth / imageRate + "px";
		}

	}); 
</script>
</head>

<body onload="document.forms[0].j_username.focus();fillData();">
	<div class="logo"></div>
    <div class="login">
		<form name="LoginForm" action="<%=basePath%>j_spring_security_check" method="post">
			<input type="hidden" name="browsername" id="browsername"/>
			<input type="hidden" name="browserversion" id="browserversion"/>
			<input type="hidden" name="theip" id="theip"/>
			<div class="l_username">
				<span>用户名：</span><input name="j_username" tabindex="1" id="j_username" value="" type="text" onkeydown="KeyDown(event);" class="tex01"/>
			</div>
			<div class="l_password">
				<span>密　码：</span><input name="j_password"  tabindex="2"  id="j_password" type="password" onkeydown="KeyDown(event);" class="tex02"/>
			</div>
			<div class="l_btns">
				<input type="button" value="登录" class="l_login" onclick="login()" />
				<input type="reset" value="重置" class="l_reset"/>
			</div>
			<input name="nextPath" value="<%=request.getParameter("goto")%>" type="hidden"/>
		</form>
    </div>
</body>
</html>
