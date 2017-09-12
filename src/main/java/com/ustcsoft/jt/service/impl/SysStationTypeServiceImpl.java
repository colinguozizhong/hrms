package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysStationTypeMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStationTypeService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysStationTypeVO;

/**
 * 岗位类型的服务实现
 * @author  吴金华
 * @since   2017年1月10日
 */
@Service("sysStationTypeService")
public class SysStationTypeServiceImpl implements SysStationTypeService {
	@Resource
	private SysStationTypeMapper sysStationTypeMapper;
	
	/**
	 * 分页查询所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysStationTypeVO> query(SysStationTypeVO stationType, int page,
			int rows) {
		Page<SysStationTypeVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysStationTypeVO> list = sysStationTypeMapper.queryWithPage(stationType, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 加载一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public SysStationTypeVO load(String id) {
		return sysStationTypeMapper.load(id);
	}

	/**
	 * 添加一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	@Override
	public AjaxObj add(SysStationTypeVO stationType, UserVO currentUser) {
		// 设置创建人
		stationType.setCreater(currentUser.getUserName());
		// 设置创建时间
		stationType.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysStationTypeMapper.add(stationType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setId(stationType.getStationName());
		ao.setMsg("添加成功");
		return ao;
	}

	/**
	 * 更新一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	@Override
	public AjaxObj update(SysStationTypeVO stationType) {
		sysStationTypeMapper.update(stationType);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("更新成功");
		return ao;
	}

	/**
	 * 根据id删除岗位类型
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj delete(String id) {
		List<String> list = Arrays.asList(id.split(","));
		sysStationTypeMapper.deletes(list);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("删除成功");
		return ao;
	}

}
