package com.ustcsoft.jt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.helpers.Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ustcsoft.jt.mapper.SysChangeEmpMapper;
import com.ustcsoft.jt.service.SysChangeEmpService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.LeaveEmpCharVO;
import com.ustcsoft.system.model.SysChangeEmpChartVO;

@Service
public class SysChangeEmpServiceImpl implements SysChangeEmpService {
	@Resource
	private SysChangeEmpMapper sysChangeEmpMapper;
	
	/**
	 * 查询人员异动相关信息
	 * @param map，Map对象，如{businessId:"3401040000000000", changeMonth:"2017-03"}
	 * businessId 所查询的单位或部门Id
	 * changeMonth 所查询的月份，如：2017-03
	 * @return
	 */
	@Override
	public List<SysChangeEmpChartVO> findChangeEmpInfo(Map<String, String> map) {
		String businessId = map.get("businessId");
		String changeMonth = map.get("changeMonth"); // 获取所要查询的异动月份
		
		// 如果传过来的businessId为空字符串的话
		if (businessId.equals("")) {
			return null;
		}
		int index = businessId.indexOf("00");
		// 如果businessId字符串存在‘00’的话
		if (index != -1) {
			// 把businessId字符串后面所有的0去除，为了在SQL查询到该部门的所有子部门
			map.put("businessId", businessId.substring(0, index));
		}

		String changeStartDateStr = changeMonth + "-01"; // 异动开始时间 转换为'yyyy-mm-dd'
		int year = Integer.parseInt(changeMonth.substring(0, 4)); // 获取年
		int month = Integer.parseInt(changeMonth.substring(5, 7)); // 获取月
		if (month == 12) {
			year++;
			month = 1;
		} else {
			month++;
		}
		String changeEndDateStr = "" + year + "-" + (month < 10 ? ("0" + month) : month) + "01"; // 异动结束时间转换为 'yyyy-mm-dd'
		
		map.remove("changeMonth");
		map.put("changeStartDateStr", changeStartDateStr);
		map.put("changeEndDateStr", changeEndDateStr);
		
		// 获取查询结果集		
		List<Map<String, String>> result = sysChangeEmpMapper.findChangeEmpInfo(map);
		
		List<SysChangeEmpChartVO> mapList = new ArrayList<SysChangeEmpChartVO>();
		List<String> idList = new ArrayList<String>();
		List<String> cach = new ArrayList<String>();
		
		for (int i = 0; i < result.size(); i++) {
			idList.add(result.get(i).get("ORG_ID"));
			result.get(i).put("TYPE_ID", "NULL"); // 如果TYPE_ID为null的话，设为"NULL"
		}
		for (int i = 0; i < idList.size(); i++) {
			String type = result.get(i).get("TYPE_ID"); // 获取当前的遍历部门ORG_ID对应的异动类型
			int pos = cach.indexOf(idList.get(i));
			if (pos == -1) { // 如果cash链表中不存在该ORG_ID 则插入
				cach.add(idList.get(i));
				SysChangeEmpChartVO vo = new SysChangeEmpChartVO();
				vo.setDeptId(idList.get(i));
				vo.setDeptName(result.get(i).get("ORG_NAME"));
				mapList.add(getVO(type, vo));
			} else {
				mapList.set(pos, getVO(type, mapList.get(pos)));
			}
		}

		// 获取编制人数结果集
		List<Map<String, String>> resultOfBianZhi = sysChangeEmpMapper.findNumOfBianZhi(map);

		// 插入编制人数
		for (int i = 0; i < mapList.size(); i++) {
			int num = 0;
			for (int j = 0; j < resultOfBianZhi.size();) {
				if (mapList.get(i).getDeptId().equals(resultOfBianZhi.get(j).get("ORG_ID")) && 
						resultOfBianZhi.get(j).get("EMP_ORGNIZATION") != null) {
					num++;
					resultOfBianZhi.remove(j);
				} else {
					j++;
				}
			}
			SysChangeEmpChartVO changeVo = mapList.get(i);
			changeVo.setNumOfBianZhi(num);
			mapList.set(i, changeVo);
		}
		
		return mapList;
	}
	
	/**
	 * 将所有异动类型设置为玫举型
	 */
	enum TypeChange {
		C01, C02, C03, C04, C05, C06, C07, C08, C09, C10, C11, NULL; // NULL 是空
		private static TypeChange getType(String type) {
			return valueOf(type);
		}
	}
	
	/**
	 * 传入一种异动类型，对应类型的人数加1
	 * @param type 异动类型String
	 * @param vo 人员异动分析表vo对象
	 * @return 返回变动后的vo对象
	 */
	private SysChangeEmpChartVO getVO(String type, SysChangeEmpChartVO vo) {
		switch(TypeChange.getType(type)) {
			case C01: // 转正
				vo.setNumOfZhuanZheng(vo.getNumOfZhuanZheng() + 1);
				break;
			case C02: // 晋升
				vo.setNumOfJinSheng(vo.getNumOfJinSheng() + 1);
				break;
			case C03: // 换岗
				vo.setNumOfHuanGang(vo.getNumOfHuanGang() + 1);
				break;
			case C04: // 辞职
				vo.setNumOfCiZhi(vo.getNumOfCiZhi() + 1);
				break;
			case C05: // 辞退
				vo.setNumOfCiTui(vo.getNumOfCiTui() + 1);
				break;
			case C06: // 退休 
				vo.setNumOfTuiXiu(vo.getNumOfTuiXiu() + 1);
				break;
			case C07: // 离岗
				vo.setNumOfZiLi(vo.getNumOfZiLi() + 1);
				break;
			case C08: // 部门调整
				vo.setNumOfTiaoZheng(vo.getNumOfTiaoZheng() + 1);
				break;
			case C09: // 调出
				vo.setNumOfDiaoChu(vo.getNumOfDiaoChu() + 1);
				break;
			case C10: // 调入
				vo.setNumOfDiaoRu(vo.getNumOfDiaoRu() + 1);
				break;
			case C11: // 入职
				vo.setNumOfRuZhi(vo.getNumOfRuZhi() + 1);
				break;
			default:
				return vo;
		}
		return vo;
	}
	
	@Override
	public LeaveEmpCharVO glFXList(LeaveEmpCharVO leaveEmpCharVO){
		if(!StringUtils.isEmpty(leaveEmpCharVO.getOrgId())){
			leaveEmpCharVO.setOrgId(CommonUtils.getSearchId(leaveEmpCharVO.getOrgId()).toString());
		}
		LeaveEmpCharVO list = sysChangeEmpMapper.glFXList(leaveEmpCharVO);
		
		if(StringUtils.isEmpty(list.getLzZRS()) || list.getLzZRS() == 0){
			list.setGlFXBL1(0);
			list.setGlFXBL2(0);
			list.setGlFXBL3(0);
			list.setGlFXBL4(0);
			list.setGlFXBL5(0);
			list.setGlFXBL6(0);
		}else{
			list.setGlFXBL1(list.getGlFXRS1()/list.getLzZRS());
			list.setGlFXBL2(list.getGlFXRS2()/list.getLzZRS());
			list.setGlFXBL3(list.getGlFXRS3()/list.getLzZRS());
			list.setGlFXBL4(list.getGlFXRS4()/list.getLzZRS());
			list.setGlFXBL5(list.getGlFXRS5()/list.getLzZRS());
			list.setGlFXBL6(list.getGlFXRS6()/list.getLzZRS());
		}
		return list;
	}
	
	@Override
	public LeaveEmpCharVO sjyFXList(LeaveEmpCharVO leaveEmpCharVO){
		if(!StringUtils.isEmpty(leaveEmpCharVO.getOrgId())){
			leaveEmpCharVO.setOrgId(CommonUtils.getSearchId(leaveEmpCharVO.getOrgId()));
		}
		List<LeaveEmpCharVO> list = sysChangeEmpMapper.sjyFXList(leaveEmpCharVO);
		int count =0;
		int count1 =0;
		int count2 =0;
		int count3 =0;
		int count4 =0;
		int count5 =0;
		for(int i = 0; i< list.size(); i++){
			count = count  + list.get(i).getEduRaceCount();
			if(!StringUtils.isEmpty(list.get(i).getEduRace())){
				if(list.get(i).getEduRace().equals("A08")||list.get(i).getEduRace().equals("A09")){//初中以下
					count1 = count1 + list.get(i).getEduRaceCount();
				}else if(list.get(i).getEduRace().equals("A06")||list.get(i).getEduRace().equals("A07")){//高中中专
					count2 = count2 + list.get(i).getEduRaceCount();
				}else if(list.get(i).getEduRace().equals("A05")){//大专
					count3 = count3 + list.get(i).getEduRaceCount();
				}else if(list.get(i).getEduRace().equals("A01")||list.get(i).getEduRace().equals("A02")
						||list.get(i).getEduRace().equals("A03")||list.get(i).getEduRace().equals("A04")){
					count4 = count4 + list.get(i).getEduRaceCount();
				}else{
					count5= count5 + list.get(i).getEduRaceCount();
				}
			}else{
				count5= count5 + list.get(i).getEduRaceCount();
			}
		}
		LeaveEmpCharVO leaveEmpCharVO1 = new LeaveEmpCharVO();
		if(count != 0){
			leaveEmpCharVO1.setSjyFXRS1(count1);
			leaveEmpCharVO1.setSjyFXRS2(count2);
			leaveEmpCharVO1.setSjyFXRS3(count3);
			leaveEmpCharVO1.setSjyFXRS4(count4);
			leaveEmpCharVO1.setSjyFXRS5(count5);
			leaveEmpCharVO1.setSjyFXBL1(count1/count);
			leaveEmpCharVO1.setSjyFXBL2(count2/count);
			leaveEmpCharVO1.setSjyFXBL3(count3/count);
			leaveEmpCharVO1.setSjyFXBL4(count4/count);
			leaveEmpCharVO1.setSjyFXBL5(count5/count);
		}else{
			leaveEmpCharVO1.setSjyFXRS1(0);
			leaveEmpCharVO1.setSjyFXRS2(0);
			leaveEmpCharVO1.setSjyFXRS3(0);
			leaveEmpCharVO1.setSjyFXRS4(0);
			leaveEmpCharVO1.setSjyFXRS5(0);
			leaveEmpCharVO1.setSjyFXBL1(0);
			leaveEmpCharVO1.setSjyFXBL2(0);
			leaveEmpCharVO1.setSjyFXBL3(0);
			leaveEmpCharVO1.setSjyFXBL4(0);
			leaveEmpCharVO1.setSjyFXBL5(0);
		}
		return leaveEmpCharVO1;
	}
	
	@Override
	public LeaveEmpCharVO lzccFXList(LeaveEmpCharVO leaveEmpCharVO){
		if(!StringUtils.isEmpty(leaveEmpCharVO.getOrgId())){
			leaveEmpCharVO.setOrgId(CommonUtils.getSearchId(leaveEmpCharVO.getOrgId()));
		}
		List<LeaveEmpCharVO> list = sysChangeEmpMapper.lzccFXList(leaveEmpCharVO);
		int count =0;
		float count1 =0;
		float count2 =0;
		float count3 =0;
		float count4 =0;
		for(int i = 0; i< list.size(); i++){
			count = count  + list.get(i).getStationTypeCount();
			if(list.get(i).getStationType().equals("工勤类")){
				count1 = count1 + list.get(i).getStationTypeCount();
			}else if(list.get(i).getStationType().equals("管理类")){
				count2 = count2 + list.get(i).getStationTypeCount();
			}else if(list.get(i).getStationType().equals("专业技术类")){
				count3 = count3 + list.get(i).getStationTypeCount();
			}else{//其他类
				count4= count4 + list.get(i).getStationTypeCount();
			}
		}
		LeaveEmpCharVO leaveEmpCharVO1 = new LeaveEmpCharVO();
		if(count != 0){
			leaveEmpCharVO1.setLzPGRSBL(count1/count);
			leaveEmpCharVO1.setLzJSRSBL(count3/count);
			leaveEmpCharVO1.setLzGLRSBL(count2/count);
			leaveEmpCharVO1.setLzQLRSBL(count4/count);
		}else{
			leaveEmpCharVO1.setLzPGRSBL(0);
			leaveEmpCharVO1.setLzJSRSBL(0);
			leaveEmpCharVO1.setLzGLRSBL(0);
			leaveEmpCharVO1.setLzQLRSBL(0);
		}
		leaveEmpCharVO1.setLzZRS(count);
		leaveEmpCharVO1.setLzPGRS((int) count1);
		leaveEmpCharVO1.setLzGLRS((int)count2);
		leaveEmpCharVO1.setLzJSRS((int)count3);
		leaveEmpCharVO1.setLzQLRS((int)count4);
		return leaveEmpCharVO1;
	}
	
	@Override
	public List<LeaveEmpCharVO> gwFXList(LeaveEmpCharVO leaveEmpCharVO){
		if(!StringUtils.isEmpty(leaveEmpCharVO.getOrgId())){
			leaveEmpCharVO.setOrgId(CommonUtils.getSearchId(leaveEmpCharVO.getOrgId()));
		}
		List<LeaveEmpCharVO> list = sysChangeEmpMapper.gwFXList(leaveEmpCharVO);
		return list;
	}
}
