package com.ustcsoft.jt.controller;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.service.SysChangeEmpService;
import com.ustcsoft.system.model.LeaveEmpCharVO;

/**
 * 报表分析的rest控制器
 * 
 * @author 宋真真
 * @since 2017年3月15日
 */
@RestController
public class chartRestController extends AbstractRestController {
	@Resource
	private SysChangeEmpService sysChangeEmpService;
	
	/**
	 * 离职工龄分析报表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chartLZFX/glFXList.do")
	public LeaveEmpCharVO glFXList(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "leaveDate", required = false) String leaveDate) throws Exception {
		LeaveEmpCharVO  leaveEmpCharVO = chuLiCanShu(orgId,leaveDate);
		return sysChangeEmpService.glFXList(leaveEmpCharVO);
	}
	
	/**
	 * 离职受教育分析报表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chartLZFX/sjyFXList.do")
	public LeaveEmpCharVO sjyFXList(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "leaveDate", required = false) String leaveDate) throws Exception {
		LeaveEmpCharVO  leaveEmpCharVO = chuLiCanShu(orgId,leaveDate);
		return sysChangeEmpService.sjyFXList(leaveEmpCharVO);
	}
	
	/**
	 * 离职层次分析报表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chartLZFX/lzccFXList.do")
	public LeaveEmpCharVO lzccFXList(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "leaveDate", required = false) String leaveDate) throws Exception {
		LeaveEmpCharVO  leaveEmpCharVO = chuLiCanShu(orgId,leaveDate);
		return sysChangeEmpService.lzccFXList(leaveEmpCharVO);
	}
	
	/**
	 * 离职岗位分析报表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chartLZFX/gwFXList.do")
	public List<LeaveEmpCharVO> gwFXList(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "leaveDate", required = false) String leaveDate) throws Exception {
		LeaveEmpCharVO  leaveEmpCharVO = chuLiCanShu(orgId,leaveDate);
		return sysChangeEmpService.gwFXList(leaveEmpCharVO);
	}
	
	public LeaveEmpCharVO chuLiCanShu(String orgId,String leaveDate){
		LeaveEmpCharVO leaveEmpCharVO = new LeaveEmpCharVO();
		UserVO userInfo = getCurrentUser();
		if(StringUtils.isEmpty(orgId)){
			leaveEmpCharVO.setOrgId(userInfo.getBusinessId());
		}else{
			leaveEmpCharVO.setOrgId(orgId);
		}
		int year = 0;
		int monthKS = 0;
		int monthJS = 0;
		if(StringUtils.isEmpty(leaveDate)){
			Calendar c = Calendar.getInstance();
			 year = c.get(Calendar.YEAR) ; // 获取年
			 monthKS = Calendar.MONTH +1; // 获取开始月份
			 if(monthKS==12){
				 year = year +1;
				 monthJS=1;
			 }else{
				 monthJS = monthKS +1; // 获取结束月份
			 }
		}else{
			 year = Integer.parseInt(leaveDate.substring(0, 4)); // 获取年
			 String month = leaveDate.substring(5, 7); // 获取月份
			 if(month.substring(0, 1).equals("0")){
				 monthKS = Integer.parseInt(month.substring(1, 2));
				 monthJS = monthKS+1;
			 }else{
				 monthKS = Integer.parseInt(month);
				 if(monthKS==12){
					 year = year +1;
					 monthJS=1;
				 }else{
					 monthJS = monthKS +1; // 获取结束月份
				 }
			 }
		}
		Integer  monthKSI = monthKS;
		Integer  monthJSI = monthJS;
		if(monthKSI.toString().length()==1 && monthJSI.toString().length()==1){
			 leaveEmpCharVO.setLeaveStartDateStr(year+"-0"+monthKS+ "-01");
			 leaveEmpCharVO.setLeaveEndDateStr(year+"-0"+monthJS+ "-01");
		}else if(monthKSI.toString().length()==1 && monthJSI.toString().length()==2){
			 leaveEmpCharVO.setLeaveStartDateStr(year+"-0"+monthKS+ "-01");
			 leaveEmpCharVO.setLeaveEndDateStr(year+"-"+monthJS+ "-01");
		}else if(monthKSI.toString().length()==2 && monthJSI.toString().length()==1){
			 leaveEmpCharVO.setLeaveStartDateStr(year+"-0"+monthKS+ "-01");
			 leaveEmpCharVO.setLeaveEndDateStr(year+"-"+monthJS+ "-01");
		}else{
			leaveEmpCharVO.setLeaveStartDateStr(year+"-"+monthKS+ "-01");
			 leaveEmpCharVO.setLeaveEndDateStr(year+"-"+monthJS+ "-01");
		}
		 
		return leaveEmpCharVO;
	}
}
