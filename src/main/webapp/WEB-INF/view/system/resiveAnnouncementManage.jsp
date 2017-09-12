<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<title>
				阜阳市交通运输局
			</title>
			<%@include file="/common/common_easyui.jsp" %>
			<script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.config.js"></script>
           <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/ueditor.all.js"> </script>
           <script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
           <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
           <script type="text/javascript" charset="utf-8" src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
           
           <script type="text/javascript" src="<%=basePath%>scripts/jquery.form.min.js"></script>
            <script type="text/javascript">
$(function(){
    // 	双击查看公告
	$('#dg').datagrid( {
		  onDblClickRow :function(rowIndex,rowData){
			  top.CreateDiv(rowIndex, '<%=basePath%>system/viewAnnouncement.do?announcementId='+rowData["announcementId"],"查看公告","refresh", null,null,null,null);
			  $.ajax({
					type: 'POST',
					url: "<%=basePath%>sysNotice/saveReadGGJL.do" ,
					data: {
						'dataId':rowData["announcementId"]
					},
					dataType: 'json',
					success: function(data){
						$('#dg').datagrid('reload');
						window.parent.getUnreadGGS();
					} 
				});
		  }
	});
    //分页
	var pager = $('#dg').datagrid({
		loadFilter : function(data){
			if(data.items==null || data.items==""){
					return {total:0,rows:[]}; 
				}else{
			 //过滤数据
			 var value={
			 total:data.recordCount,
			 rows:[]
			 };
			 var x=0;
			 for (var i = 0; i < data.items.length; i++) {  
				 value.rows[x]=data.items[i];
				 x++;
			 }
			 return value;
		}
		}
	});
});	

function formatReadFlag(val,row){
// 	var announcementId = row["announcementId"];
// 	  $.ajax({
// 			type: 'POST',
<%-- 			url: "<%=basePath%>sysNotice/findGGreadFlag.do" , --%>
// 			data: {
// 				'announcementId':announcementId
// 			},
// 			dataType: 'json',
// 			success: function(result){debugger;
// 				row["readFlag"]=result;
// 				val = result;
				 
// 			} 
// 		});
	  if(val==0){
		  return "未阅读";
	  }else{
		  return "已阅读";
	  }
}
</script>

</head>

<body>
<div class="easyui-layout" style="width:100%;"data-options="fit:true">
  <table id="dg"  style="width:100%;height:85%;fit:ture;" toolbar="#toolbar" data-options="
				url: '<%=basePath%>sysnotice/searchAnnouncementList.do', 
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:20">
	<thead>
		<tr>
		    <th field="announcementId"  hidden=ture>公告ID</th>
		    <th field="content"  hidden=ture>公告内容</th>
		    <th field="senderOrgId"  hidden=ture>发布机构ID</th>
			<th field="title" width="30%" >标题</th>
			<th field="senderOrgName" width="25%" >发布机构</th>
			<th field="createDate" width="25%">发布时间</th>
			<th field="readFlag" width="20%" formatter="formatReadFlag">阅读状态</th>
		</tr>
	</thead>
  </table>
</div>   
</body>
</html>