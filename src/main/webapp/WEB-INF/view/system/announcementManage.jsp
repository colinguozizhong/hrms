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
// 页面初始化
var ue; 
//	按钮权限显示
var objArrayYM = new Array("add","edite","del");   
$(function(){
	showObj(objArrayYM);//共同方法
	//实例化编辑器
	ue = UE.getEditor( 'editer', {
	        autoHeightEnabled: true,
	        autoFloatEnabled: true,
	        initialFrameWidth: 650,
	        initialFrameHeight:300,
	    });
	//新增
	$("#add").click(function(){
		$('#win').dialog('open');
		$('#form1').form('clear');
		 // 	加载机构树
		$('#orgTree').tree({
			url: '<%=basePath%>sysNotice/loadAnnouncementOrg.do',
			parentField:"pid",
			textFiled:"text",
			idFiled:"businessId"
		})
	})
	//更新
	$("#edite").click(function() {
		var node = $('#dg').datagrid('getSelected');
		if (node == null) {
			$.messager.alert("操作提示", "请选择！");
		} else {
			$('#win').dialog('open').dialog('setTitle','修改公告');
			$('#form1').form('load',node);
			ue.setContent(node.content);
			 // 	加载机构树
			$('#orgTree').tree({
				url: '<%=basePath%>sysNotice/loadAnnouncementOrg.do',
				parentField:"pid",
				textFiled:"text",
				idFiled:"businessId",
				onLoadSuccess : function(treeNode, data) {
					$.ajax({
						type: 'POST',
						url:  '<%=basePath%>sysNotice/searchReciverOrg.do?announcementId='+node.announcementId,
						dataType: "json",
						success: function(data){
							if(data){
							    for(i=0;i<data.length;i++){
							    	  var node = $('#orgTree').tree('find', data[i].reciverOrgId);
									   $('#orgTree').tree('check', node.target);
									   checked:true
							    }
							}
						},
						error: function(){
							alert("获取key失败");
						}
					});
		        }
			})
		}
	});
	//删除 
	$("#del").click(function() {
		var node = $('#dg').datagrid('getSelected');
		if (node == null) {
			$.messager.alert("操作提示", "请选择！");
		} else {
			$.messager.confirm('提示框', '你确定要删除吗?',function(r){
				if(r){
					$.ajax({
						url: '<%=basePath%>sysNotice/deleteAnnouncement.do?announcementId='+node.announcementId,
						success:function(){
							$('#dg').datagrid('reload')
							$.messager.show({
								title:'提示',
								msg:'已启用',
								timeout:3000,
								showType:'slide'
							});
							//插入删除通知公告管理日志
							insertSysLog("通知公告","删除","标题为"+node.title+"的通知公告");
						}
					});
				}
			})
		}
	});
    // 	双击查看公告
	$('#dg').datagrid( {
		  onDblClickRow :function(rowIndex,rowData){
			  top.CreateDiv(rowIndex, '<%=basePath%>system/viewAnnouncement.do?announcementId='+rowData["announcementId"],"查看公告","refresh", null,null,null,null);
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
// 表单提交	
function submitForm(){
	var baseParam = {};
	 var checkedList = $('#orgTree').tree('getChecked');
	 for(var i=0;i<checkedList.length;i++){
		 baseParam["listOrg[" + i + "].reciverOrgId"] = checkedList[i].id;
	 }
	 baseParam["content"] = ue.getContent();
	 baseParam["title"] = $("#title").val();
	 baseParam["announcementId"] = $("#announcementId").val();
		$.ajax({
		type: 'POST',
		url:  '<%=basePath%>sysNotice/editeAnnouncement.do', 
		data:baseParam,
		dataType: 'json',
		success: function(data){
				$('#dg').datagrid('reload')
				$('#win').dialog('close');
				$.messager.show({
					title:'提示',
					msg:'操作成功',
					timeout:3000,
					showType:'slide'
				});
				if(baseParam.announcementId){
					//插入修改通知公告管理日志
					insertSysLog("通知公告","修改","标题为"+baseParam.title+"的通知公告");
				}else{
					//插入新增通知公告管理日志
					insertSysLog("通知公告","新增","标题为"+data.id+"的通知公告");
				}
			
		} ,
		error: function(){
			$.messager.show({
				title:'提示',
				msg:'操作失败',
				timeout:3000,
				showType:'slide'
			});
		}
	});
}
// 关闭div
function clearForm(){
	$('#win').dialog('close');	
}	
</script>

</head>

<body>
<div class="easyui-layout" style="width:100%;"data-options="fit:true">
  <table id="dg"  title="系统公告管理"  style="width:100%;height:85%;fit:ture;" toolbar="#toolbar" data-options="
				url: '<%=basePath%>sysNotice/searchAnnouncementList.do', 
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:20">
	<thead>
		<tr>
		    <th field="ck" checkbox="true"></th>
		    <th field="announcementId"  hidden=ture>公告ID</th>
		    <th field="content"  hidden=ture>公告内容</th>
		    <th field="senderOrgId"  hidden=ture>发布机构ID</th>
			<th field="title" width="33%" >标题</th>
			<th field="senderOrgName" width="33%" >发布机构</th>
			<th field="createTime" width="33%">发布时间</th>
		</tr>
	</thead>
  </table>
  <div id="toolbar">
        <a href="javascript:void(0)" id="add" class="easyui-linkbutton"  iconCls="icon-add" plain="true" style="width:60px">新增</a>
		<a href="javascript:void(0)" id="edite" class="easyui-linkbutton"  iconCls="icon-edit" plain="true" style="width:60px">修改</a>
		<a href="javascript:void(0)" id="del" class="easyui-linkbutton"  iconCls="icon-remove" plain="true" style="width:60px">删除</a>
</div>
  <div id="win" class="easyui-window" title="公告管理" data-options="iconCls:'icon-save',closed:true" style="width:950px;height:450px;">
		<div class="easyui-layout" data-options="fit:true">
		<form action="" name="form1" id="form1" method="post">
		     <input type="hidden" name="announcementId" id="announcementId" />
		     <input type="hidden"  id="listOrg" />
			<div data-options="region:'center'" style="padding:5px;height:10%">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox"  style="width:100%" type="text" name="title" id="title" data-options="label:'公告标题：',required:true" />
			</div>	
			<div style="margin-bottom:20px;width:100%;height:85%">
	      		<script id="editer" type="text/plain" ></script>
			</div>	
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton" onclick="submitForm()" data-options="iconCls:'icon-save'">提交</a>
				<a class="easyui-linkbutton" onclick="clearForm()" data-options="iconCls:'icon-cancel'">关闭</a>
			</div>
			</form>
			<div data-options="region:'east',split:true"  title="发布到以下机构" style="width:250px">
				    <ul id="orgTree" class="easyui-tree" data-options="animate:true,checkbox:true,required:true"></ul>			
	        </div>
		</div>
	</div>
</div>   
</body>
</html>