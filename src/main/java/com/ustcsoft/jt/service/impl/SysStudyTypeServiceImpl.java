package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysStudyTypeMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStudyTypeService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SystemStudyTypeVO;

/**
 * 学历类型的服务实现
 * @author  吴金华
 * @since   2017年1月5日
 */
@Service("sysStudyTypeService")
public class SysStudyTypeServiceImpl implements SysStudyTypeService {
	@Resource
	private SysStudyTypeMapper sysStudyTypeMapper;
	
	/**
	 * 所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	@Override
	public List<SystemStudyTypeVO> listAll() {
		return sysStudyTypeMapper.listAll();
	}

	/**
	 * 分页查询所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SystemStudyTypeVO> query(SystemStudyTypeVO studyType, int page,
			int rows) {
		Page<SystemStudyTypeVO> pageVO = Page.buildPageRequest(page, rows);
		List<SystemStudyTypeVO> list = sysStudyTypeMapper.queryWithPage(studyType, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 加载一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	@Override
	public SystemStudyTypeVO load(String id) {
		return sysStudyTypeMapper.load(id);
	}

	/**
	 * 添加一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @param currentUser
	 * @return
	 */
	@Override
	public AjaxObj add(SystemStudyTypeVO studyType, UserVO currentUser) {
		// 设置创建人
		studyType.setCreater(currentUser.getUserName());
		// 设置创建时间
		studyType.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysStudyTypeMapper.add(studyType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setId(studyType.getStudyName());
		ao.setMsg("添加成功");
		return ao;
	}

	/**
	 * 更新一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @return
	 */
	@Override
	public AjaxObj update(SystemStudyTypeVO studyType) {
		sysStudyTypeMapper.update(studyType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setId(studyType.getStudyName());
		ao.setMsg("更新成功");
		return ao;
	}
	
	/**
	 * 根据Id删除职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param studyTypeId
	 * @return
	 */
	@Override
	public AjaxObj deleteStudyType(String studyTypeId) {
		List<String> list = Arrays.asList(studyTypeId.split(","));
		//执删除sql
		sysStudyTypeMapper.deleteStudyType(list);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
}
