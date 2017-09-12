package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysEmpMapper;
import com.ustcsoft.jt.mapper.SysStudyRecMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStudyRecService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysStationTypeVO;
import com.ustcsoft.system.model.SysStudyRecVO;
import com.ustcsoft.system.model.SystemEmpVO;

/**
 * 学历的服务实现
 * @author  吴金华
 * @since   2017年1月5日
 */
@Service("sysStudyRecService")
public class SysStudyRecServiceImpl implements SysStudyRecService {
	@Resource
	private SysEmpMapper sysEmpMapper;
	@Resource
	private SysStudyRecMapper sysStudyRecMapper;
	
	/**
	 * 分页查询所有学历信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param studyRec
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysStudyRecVO> query(SysStudyRecVO studyRec, int page, int rows) {
		Page<SysStudyRecVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysStudyRecVO> list = sysStudyRecMapper.queryWithPage(studyRec, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 根据人员id加载最新一条学历信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param empId
	 * @return
	 */
	@Override
	public SysStudyRecVO lastByEmpid(String empId) {
		SysStudyRecVO studyRec = new SysStudyRecVO();
		studyRec.setEmpId(empId);
		Page<SysStudyRecVO> pageVO = Page.buildPageRequest(1, 1);
		List<SysStudyRecVO> list = sysStudyRecMapper.queryByEmpidWithPage(studyRec, pageVO);
		return list == null ? null : list.get(0);
	}

	/**
	 * 添加一条学历信息
	 * @author 吴金华
	 * @since 2017年1月12日
	 * @param studyRec
	 * @param user
	 * @return
	 */
	@Override
	public AjaxObj add(SysStudyRecVO studyRec, UserVO user) {
		/* 更新相关联的人员的学历信息 */
		SystemEmpVO emp = new SystemEmpVO();
		emp.setId(studyRec.getEmpId());
		emp.setEduRec(studyRec.getEduRec());
		// 执行更新
		sysEmpMapper.update(emp);
		
		/* 添加相学历信息 */
		// 设置记录id
		studyRec.setId(UUID.randomUUID().toString().replace("-", ""));
		// 设置创建人
		studyRec.setCreater(user.getUserName());
		// 设置创建时间
		studyRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 执行添加
		sysStudyRecMapper.add(studyRec);

		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("添加成功");
		return ao;
	}
	
	/**
	 * 更新一条人员学习经历
	 * @author 谈健
	 * @since 2017年3月17日
	 * @param studyRec
	 * @return
	 */
	@Override
	public AjaxObj updateStudyRec(SysStudyRecVO studyRec) {
		sysStudyRecMapper.updateStudyRec(studyRec);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("更新成功");
		return ao;
	}
	
	
	/**
	 * 根据id删除人员基本信息（以,分割）
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj delete(String id) {
		// 组装list参数
		List<String> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = Arrays.asList(id.split(","));
		}
		
		//根据学历信息ID删除相关对应人员的学历信息
		sysStudyRecMapper.deleteByStudyRecIds(list);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("删除成功");
		return ao;
	}
}
