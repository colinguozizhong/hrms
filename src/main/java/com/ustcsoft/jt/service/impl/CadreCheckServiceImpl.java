package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.CadreCheckMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreCheckService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.CadreCheckVO;

@Service
public class CadreCheckServiceImpl implements CadreCheckService {

	@Resource
	private CadreCheckMapper cadreCheckMapper;


	/**
	 * 分页查询所有干部任命申请单信息
	 * 
	 */
	@Override
	public Page<CadreCheckVO> query(CadreCheckVO cadreCheck, int page, int rows) {
		Page<CadreCheckVO> pageVO = Page.buildPageRequest(page, rows);
		List<CadreCheckVO> list = cadreCheckMapper.queryWithPage(cadreCheck, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	/**
	 * 添加干部考核
	 * 
	 */
	public AjaxObj insertCadreCheck(CadreCheckVO cadreCheck, UserVO user){
		//生成主键Id
		String cadreCheckId = UUID.randomUUID().toString().replace("-", "");
		cadreCheck.setCadreCheckId(cadreCheckId);
		// 设置创建人
		cadreCheck.setCreatePer(user.getUserName());
		// 设置创建时间
		cadreCheck.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));

		// 正式添加
		cadreCheckMapper.insertCadreCheck(cadreCheck);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		//返回考核记录单
		obj.setId(cadreCheckId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 根据id删除干部考核（以,分割）
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deleteCadreCheck(String id) {
		// 组装list参数
		List<String> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = Arrays.asList(id.split(","));
		}
		// 在根据id删除人员基本信息
		cadreCheckMapper.deleteCadreCheck(list);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	/**
	 * 更新干部考核信息
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param cadreAppointApply
	 * @return
	 */
	@Override
	public AjaxObj updateCadreCheck(CadreCheckVO cadreCheck) {
		// 正式更新
		cadreCheckMapper.updateCadreCheck(cadreCheck);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}

}
