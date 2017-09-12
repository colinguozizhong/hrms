/**
 * 共同方法
 */
 
	
//获取用户使用的浏览器信息
 function getBrowserInfo(){

	var regStr_ie = /msie [\d.]+;/gi ;
	var regStr_ff = /firefox\/[\d.]+/gi
	var regStr_chrome = /chrome\/[\d.]+/gi ;
	var regStr_saf = /safari\/[\d.]+/gi ;
	//IE
	if(agent.indexOf("msie") > 0)
	{
	//return agent.match(regStr_ie) ;
		return "IE";
	}

	//firefox
	if(agent.indexOf("firefox") > 0)
	{
	//return agent.match(regStr_ff) ;
		return "firefox";
	}

	//Chrome
	if(agent.indexOf("chrome") > 0)
	{
		//return agent.match(regStr_chrome) ;
		return "Chrome";
	}

	//Safari
	if(agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0)
	{
		//return agent.match(regStr_saf) ;
		return "Safari";
	}
 }
		 
//录入用户操作日志信息
 function insertSysLog(operateMenu,operateType,object){
 		var sysLogManage={};
 		sysLogManage.operateMenu=operateMenu;
 		sysLogManage.operateType=operateType;
 		sysLogManage.browser=getBrowserInfo();
 		sysLogManage.object=object;
 		$.ajax({
 			type: 'POST',
 			url: basePath+"sysLog/insertSysLog.do" ,
 			data:sysLogManage,
 			dataType: 'json',
 			success: function(data){
 			},
 			error: function(){
 				$.messager.alert('错误提示','系统出现异常，请联系管理员');
 			}
 		});
 }	
