<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>交通运输综合执法系统</title>
<%@include file="/common/common_easyui.jsp" %>
<%-- <script type="text/javascript" src="<%=basePath%>scripts/py.js"></script> --%>
<style>
	div.content_wrap {width: 600px;height:380px;}
</style>
<script type="text/javascript">
var objArrayYM = new Array("addRol","updateRol","delRol","SetRol"); 
$(function(){
	showObj(objArrayYM);//共同方法
	   //拼音
	   $("input",$("#roleName").next("span")).blur(function(){  
			$("#roleCode").textbox("setValue", makePy($("#roleName").textbox("getValue")));
		})  
	
	     var datagrid = $("#dg").datagrid({  
	       url:"<%=basePath%>sysRole/findRoleList.do",
	       pagination:true,//显示分页  
	       rownumbers:true,//显示行号
	       pageSize:20,//分页大小  
	      
	       pageList:[5,10,15,20],//每页的个数  
	      // fit:true,//自动补全  
	       fitColumns:true, 
	       			loadFilter : function(data){
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
	       			},
	        toolbar: [{  
	           id:'addRol',
	           text: '新增角色',  
	           iconCls: 'icon-add',  
	           handler: function() {
	        	   add();  
	           },
	       },{
	    	   id:'updateRol',
	    	   text: '修改',  
	           iconCls: 'icon-edit',  
	           handler: function() {  
	        	   update();  
	           },  
	       },{
	    	   id:'delRol',
	    	   text: '删除',  
	           iconCls: 'icon-remove',  
	           handler: function() {
	        	   del();  
	           }  
	       },{
	    	   id:'SetRol',
	    	   text: '设置页面权限',  
	           iconCls: 'icon-approve',  
	           handler: function() {
	        	   page_right();  
	           }  
	       }
	       ],
	       columns:[[      //每个列具体内容  
	                { field:'ck',checkbox:true },
	                {field:'roleId',title:'roleId',width:100,hidden:true},
	                {field:'roleName',title:'角色名称',width:50},
	                {field:'roleCode',title:'角色编码',width:50},     
	                {field:'roleType',title:'所属类别',width:50,formatter:function(value,row,index){
	                    if(value==0){
	                        return "系统管理";
	                    }else if(value==1){
	                        return "业务录入";
	                    }else if(value==2){
	                        return "业务查看";
	                    }else if(value==3){
	                        return "业务审批";
	                    }else if(value==4){
	                        return "其他";
	                    }
	                  }
	                },     
	                {field:'roleHy',title:'所属行业',width:50,formatter:function(value,row,index){
	                    if(value==00){
	                        return "交通主管部门";
	                    }else if(value==01){
	                        return "公路";
	                    }else if(value==02){
	                        return "运管";
	                    }else if(value==03){
	                        return "海事";
	                    }else if(value==04){
	                        return "质监";
	                    }else if(value==05){
	                        return "港航";
	                    }else if(value==06){
	                        return "通用";
	                    }
	                  }
	                		
	                },    
	                {field:'remark',title:'说明',width:50},    
	                {field:'isSysRole',title:'角色级别',width:50},
	                {field:'createrOrgName',title:'创建人单位',width:50},
	                {field:'ceraterUserName',title:'创建人',width:50}
	                 
	             ]],
	             singleSelect: false,
	  	         selectOnCheck: true,
	  	         checkOnSelect: true,
	  	         height:640,
	       
	});
	   
	   var p = $('#dg').datagrid('getPager');  
	   $(p).pagination({  
	       pageSize: 5,//每页显示的记录条数，默认为10  
	       pageList: [5,10,15,20],//可以设置每页记录条数的列表  
	       beforePageText: '第',//页数文本框前显示的汉字  
	       afterPageText: '页    共 {pages} 页',  
	       displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	      
	   });  
	   
		function add(){
			$('#dlg').form('clear');
			$('#dlg').dialog('open');
			
			$('#yemian').style.display='block'; 
		}
		
		//更新
		function update(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				$('#addForm').form('load',{
					roleId:rows[0].roleId,
					roleName:rows[0].roleName,
					roleCode:rows[0].roleCode,
					remark:rows[0].remark,
					roleType:rows[0].roleType,
					roleHy:rows[0].roleHy,
					isSysRole:rows[0].isSysRole
				});
				$('#dlg').dialog('open');
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
			
		}
		
		function del(){
			var rows = $('#dg').datagrid('getSelections');
		    var roleId="";
            for(var i=0;i<rows.length;i++){
            	roleId +=rows[i].roleId + ",";
             //获取选中节点的值
            }
			if(rows.length>0){
				$.messager.confirm('提示框', '你确定要删除吗?',function(r){
					if(r){
						$.ajax({
							url: '<%=basePath%>sysRole/deleteRole.do?roleId=' + roleId,
									success:function(){
										$.messager.show({
											title:'提示',
											msg:'删除成功',
											timeout:3000,
											showType:'slide'
										});
										//插入删除系统角色日志
										insertSysLog("系统角色管理","删除","名称为"+rows[0].roleName+"的角色");
										$('#dg').datagrid('reload')
									}
						});
					}
				})
			
				
				
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		
		//设置页面权限
		function page_right(){
			//页面权限的树
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length==1){
				var roleId=rows[0].roleId;
				$('#zTreeMenu').tree({
					url: '<%=basePath%>sysRole/findSysMenuOrObjectTreeAll4Role.do?roleId='+roleId,
					checkbox:true,
					parentField:"parentId",
					textFiled:"menuName",
					idFiled:"menuId",
                    onCheck:function(node,checked){                 //当点击 checkbox 时触发
                     	   var childNode = $('#zTreeMenu').tree('getChildren', node.target);
                           var parentNode = $('#zTreeMenu').tree('getParent', node.target);
                           if(checked){
                               if(parentNode != null){//如果不是根节点
                                   $('#zTreeMenu').tree('check', parentNode.target);//父节点勾选
                               }
//                                if(childNode.length > 0) {
//                                    for (var i = 0; i < childNode.length; i++) {
//                                        $('#zTreeMenu').tree('check', childNode[i].target);//子节点勾选
//                                    }
//                                }
                           }else{
//                                if(parentNode != null){//如果不是根节点
//                                    $('#zTreeMenu').tree('uncheck', parentNode.target);//父节点取消勾选
//                                }
                               if (childNode.length > 0) {
                                   for (var i = 0; i < childNode.length; i++) {
                                       $('#zTreeMenu').tree('uncheck', childNode[i].target);//子节点取消勾选
                                   }
                               }
                           }
                        
                      }
				});
				$('#dlg_pageRight').dialog('open');
			}else if(rows.length>1){
				$.messager.alert("操作提示", "只能选择一行！");
			}else{
				$.messager.alert("操作提示", "请选择！");
			}
		}
		 //重置
		  $("#reset").click(function(){
			  $("#ser").form('clear');
			  var roleName=$("input",$("#role_Name").next("span")).val();
				var roleType=$("#roleTypeSelect").combobox("getValue");
				var roleHy=$("#roleHySelect").combobox("getValue");
			   	$('#dg').datagrid('load', {  
			    	roleName:roleName,  
			    	roleType:roleType,
			    	roleHy:roleHy
				}); 
		  });
		//搜索功能
		   $("#search").click(function(){
			    var roleName=$("input",$("#role_Name").next("span")).val();
				var roleType=$("#roleTypeSelect").combobox("getValue");
				var roleHy=$("#roleHySelect").combobox("getValue");
			   	$('#dg').datagrid('load', {  
			    	roleName:roleName,  
			    	roleType:roleType,
			    	roleHy:roleHy
				});  
			});
		
	   });
	   
	function showSubmit(){
		if($("#addForm").form('validate')){
			$.ajax({
				type: 'POST',
				url: "<%=basePath%>sysRole/editRole.do" ,
				data: $('#addForm').serialize(),
				dataType: 'text',
				success: function(data){
					if(data==0||data==2){
						$.messager.show({
							title:'提示',
							msg:'操作成功',
							timeout:3000,
							showType:'slide'
						});
						if(data==0){
							//插入新增系统角色日志
							insertSysLog("系统角色管理","新增","名称为"+$("#roleName").textbox("getValue")+"的角色");
						}else if(data==2){
							//插入修改系统角色日志
							insertSysLog("系统角色管理","修改","名称为"+$("#roleName").textbox("getValue")+"的角色");
						}
						
						$('#dg').datagrid('reload')
					}else if(data==1){
						$.messager.alert('错误提示','角色名重复');
					}
					$('#dlg').dialog('close');
				} ,
				error: function(){
					$.messager.alert('错误提示','失败');
				}
			});
		}else{
			$.messager.alert('错误提示','红色星号的选项必填');
		}
		
	}
  //设置页面提交
  function showSubmitPage(){
	 	  var rows = $('#dg').datagrid('getSelections');
          var nodes = $('#zTreeMenu').tree('getChecked');
          var menuIds = '';
          var types ='';
          for (var i = 0; i < nodes.length; i++) {
              if (menuIds != ''){
            	  menuIds += ',';
              } 
              if (types != ''){
            	  types += ',';
              } 
              menuIds += nodes[i].menuId;
              types += nodes[i].type;
          }
          $.ajax({
				url: '<%=basePath%>sysRole/setRole.do?roleId=' + rows[0].roleId+'&menuIds='+menuIds+'&types='+types,
				success:function(){
					$("#zTreeMenu").tree("reload");
					$('#dlg_pageRight').dialog('close');
					$.messager.show({
						title:'提示',
						msg:'操作成功',
						timeout:3000,
						showType:'slide'
					});
				}	 	
			});
      }
	  
	
		
	
</script>


</head>

<body>
<div id='yemian' class="easyui-layout" style="width:100%;"data-options="fit:true">	
   	<div id="sear" data-options="region:'north',border:false" style="height:36px;text-align:left;padding:5px 0 5px 15px ;">
				<form id="ser">
					<input type="text" name="role_Name" id="role_Name" class="easyui-textbox" data-options="label:'角色名称：'" style="width:250px"/>
					<select class="easyui-combobox" id="roleTypeSelect" name="roleType" label="所属类别：" labelPosition="right" style="width:250px;"data-options="editable:false,panelHeight:'auto'">
								<option></option>
										<option value="0">系统管理</option>
										<option value="1">业务录入</option>
										<option value="2">业务查看</option>
										<option value="3">业务审批</option>
										<option value="4">其他</option>
					</select>
						
						<select class="easyui-combobox" id="roleHySelect" name="roleHy" label="所属行业：" labelPosition="right" style="width:250px;"data-options="editable:false,panelHeight:'auto'">
								<option></option>
								<option value="00">交通主管部门</option>
								<option value="01">公路</option>
								<option value="02">运管</option>
								<option value="03">海事</option> 
								<option value="04">质监</option> 
								<option value="05">港航</option> 
								<option value="06">通用</option> 
						</select>
						<a href="#" name="search" id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:68px">查询</a>
						<a href="#" name="reset" id="reset" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:68px;margin-left:5px;">重置</a>
				</form>
	</div>
   <div data-options="region:'center',border:false" style="text-align:left;padding:5px 5px 5px 5px;margin:0 10px 10px 0;">
    	<table id="dg" style="display:block;">  
        </table>
   </div>

	
</div>
<div id="dlg" class="easyui-dialog" closed="true" title="角色窗口" style="width:400px;height:330px;padding:10px"data-options="buttons: '#dlg-buttons', modal:true">
			<div id="p" class="easyui-panel"  style="width:350px;height:230px;padding:10px;">
			   <form action="" id="addForm">
			   		<input type="hidden" name="roleId" id="roleId" />
			   		<input type="text" name="roleName" id="roleName" class="easyui-textbox" data-options="label:'角色名称：',required:true" style="width:300px"/><span style="color:red;"> * </span>
					<input type="text" name="roleCode" id="roleCode" class="easyui-textbox" data-options="label:'角色编码：',required:true" style="width:300px"/><span style="color:red;"> * </span>
					<input type="text" name="remark" id="remark" class="easyui-textbox" data-options="label:'角色说明：'" style="width:300px"/>
					<select class="easyui-combobox" id="roleType" name="roleType" label="所属类别：" labelPosition="right" style="width:300px;" data-options="editable:false,panelHeight:'auto'">
										<option></option>
										<option value="0">系统管理</option>
										<option value="1">业务录入</option>
										<option value="2">业务查看</option>
										<option value="3">业务审批</option>
										<option value="4">其他</option>
					</select>
					<select class="easyui-combobox" id="roleHy" name="roleHy" label="所属行业：" labelPosition="right" style="width:300px;" data-options="editable:false,panelHeight:'auto'">
										<option></option>
										<option value="00">交通主管部门</option>
										<option value="01">公路</option>
										<option value="02">运管</option>
										<option value="03">海事</option> 
										<option value="04">质监</option> 
										<option value="05">港航</option> 
										<option value="06">通用</option> 
					</select>
					<select class="easyui-combobox" id="isSysRole" name="isSysRole" label="角色级别：" labelPosition="right" style="width:300px;" data-options="editable:false,panelHeight:'auto'">
										<option></option>
										<option value="0">0级</option>
										<option value="1">1级</option>
										<option value="2">2级</option>
					</select>
			   </form>
					
			</div>
	</div>

	<div id="dlg_pageRight" class="easyui-dialog" closed="true" title="角色设置窗口" style="width:400px;height:360px;padding:10px"data-options="buttons: '#dlgPage-buttons'">
			<div id="pan" class="easyui-panel"  style="width:350px;height:260px;padding:10px;">
					<div  style="width:200px">
						<ul id="zTreeMenu" class="easyui-tree" data-options="cascadeCheck :false">
						</ul>
					</div>
			</div>
	</div>

	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmit()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
	</div>
	<div id="dlgPage-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="showSubmitPage()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dlg_pageRight').dialog('close')">关闭</a>
	</div>
</body>
</html>
