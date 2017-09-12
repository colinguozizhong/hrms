package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysChangeTypeMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysChangeTypeService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysChangeTypeVO;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 人员异动类型的服务实现
 * @author  吴金华
 * @since   2017年1月9日
 */
@Service("sysChangeTypeService")
public class SysChangeTypeServiceImpl implements SysChangeTypeService {
	@Resource
	private SysChangeTypeMapper sysChangeTypeMapper;
	
	/**
	 * 分页查询所有人员异动类型
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysChangeTypeVO> query(SysChangeTypeVO changeType, int page,
			int rows) {
		Page<SysChangeTypeVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysChangeTypeVO> list = sysChangeTypeMapper.queryWithPage(changeType, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 查询所有异动类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @return
	 */
	@Override
	public List<SystemStationVO> listAll() {
		return sysChangeTypeMapper.listAll();	
	}
	
	/**
	 * 增加人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param currentUser
	 * @return
	 */
	@Override
	public AjaxObj add(SysChangeTypeVO changeType, UserVO currentUser) {
		// 设置创建人
		changeType.setCreater(currentUser.getUserName());
		// 设置创建时间
		changeType.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysChangeTypeMapper.add(changeType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("添加成功");
		return ao;
	}
	
	/**
	 * 更新人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param currentUser
	 * @return
	 */
	@Override
	public AjaxObj update(SysChangeTypeVO changeType, UserVO currentUser) {
		sysChangeTypeMapper.update(changeType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("更新成功");
		return ao;
	}

	/**
	 * 删除人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
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
		
		// 再根据id删除人员异动类型信息
		sysChangeTypeMapper.deletes(list);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("删除成功");
		return ao;
	}
	
	/**
	 * 加载一条人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param typeId
	 * @return
	 */	
	@Override
	public SysChangeTypeVO load(String typeId) {
		return sysChangeTypeMapper.load(typeId);
	}

}
