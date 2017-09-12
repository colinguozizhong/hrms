<%
	//清空页面缓存  防止用户出现数据没有刷新
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setCharacterEncoding("UTF-8");
	String basePath = request.getContextPath() + "/";
	String menuId = (null==request.getParameter("menuId")?"":request.getParameter("menuId"));
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>scripts/tree.loadFilter.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/pdfInstall.js"></script>
<script type="text/javascript">
var menuId = "<%=menuId%>";
var basePath = <%=basePath%>;
var agent = navigator.userAgent.toLowerCase() ;
var objArray = new Array();
var objArraySJK = new Array();
// 根据按钮对象OBJ_CODE判断页面按钮是否显示
function showObj(objArrayYM){
	// 	根据菜单ID以及用户ID查询页面按钮对象
	$.ajax({
				url: "<%=basePath%>sysRole/findRoleQbjs.do?menuId="+menuId ,
				success: function(data){
					if(data){
						for(i=0;i<data.length;i++){
							objArraySJK.push(data[i].objCode);
						}
						for(var j=0;j<objArrayYM.length;j++){
							var count =0;
							for(var i=0;i<objArraySJK.length;i++){
								 if(objArraySJK[i]==objArrayYM[j]){
									 count = count + 1;
								 }
							}
							if(count>0){
								$("#" + objArrayYM[j] + "").show();
							}else{
								$("#" + objArrayYM[j] + "").hide();
							}
						}
					}
				}
			});
}

// 时间格式化
function dateFormat(type,time){
	if(time&&time!=''){
		var year = time.substring(0,4);
		if(type && type == '1'){
			return year+'年';
		}else if(type && type == '2'){
			var month = time.substring(5,7);
			if(month.substring(0,1) !='0'){
				month = time.substring(5,7);
			}else{
				month = month.substring(1);
			}
			return year+'年'+month+'月';
		}else{
			var month = time.substring(5,7);
			if(month.substring(0,1) !='0'){
				month = time.substring(5,7);
			}else{
				month = month.substring(1);
			}
			var day = time.substring(8,10);
			if(day.substring(0,1) !='0'){
				day = time.substring(8,10);
			}else{
				day = day.substring(1);
			}
			return year+'年'+month+'月'+day+'日';
		}
	}else{
		return '';
	}
}
//身份证号验证  start
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}   

function isCardID(sId){   
    var iSum=0 ;  
    var info="" ;  
    if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";   
    sId=sId.replace(/x$/i,"a");   
    if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";   
    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));   
    var d=new Date(sBirthday.replace(/-/g,"/")) ;  
    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";   
    for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;  
    if(iSum%11!=1) return "你输入的身份证号非法";   
    return true;//aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")   
}   
//身份证号验证  end

(function($){
	$.ajaxSetup({
		global: true,
		cache: false,
		type: "POST"
	});
    var ajax=$.ajax;
    $.ajax=function(s){
        var old = s.error;
        var errHeader = s.errorHeader || "Error-Json";
        s.error = function(xhr,status,err){
           var errMsg = xhr.getResponseHeader(errHeader);
           if(errMsg != null){
        	   if(parent){
        	       parent.xzzfAlert("错误",errMsg+"会话到期",function(){
        	           window.top.location.href = "<%=basePath%>login.jsp";
        		   });
        	   }else{
        		   alert(errMsg+"会话到期");
        	       window.top.location.href = "<%=basePath%>login.jsp";
        	   }
           }
		}
		ajax(s);
	}
})(jQuery);
</script>
