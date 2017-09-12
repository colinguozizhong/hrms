<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>查看公告</title>
    <%
    	String announcementId = (null==request.getParameter("announcementId")?"":request.getParameter("announcementId"));
	%>
	<%@include file="/common/common_easyui.jsp" %>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<style type="text/css">
		.head{text-align: center;font-size: 30px;font-weight: bold;padding-top: 30px;}
		.info{text-align: right;font-size: 14px;}
		.content{padding: 30px}
	</style>
	<script type="text/javascript">
			// 页面初始化
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>sysNotice/loadAnnouncement.do?announcementId=<%=announcementId%>" ,
				dataType: "json",
				success: function(data){
					if(data){
						if(data.content)
							$("#content").html(data.content);
						if(data.title)
							$("#title").text(data.title);
						if(data.senderOrgName)
							$("#orgName").text(data.senderOrgName);
						if(data.createTime)
							$("#createTime").text(data.createTime);
					}
				},
				error: function(){
					alert("获取key失败");
				}
			});
	</script>
  </head>
  <body>
  	<div class="head" id="title"></div>
  	<div class="info">发布机构：<span id="orgName"></span>&nbsp;&nbsp;&nbsp;发布时间：<span id="createTime"></span></div>
  	<hr/>
  	<div class="content" id="content"></div>
  	<div class="foot"></div>
  </body>
</html>
