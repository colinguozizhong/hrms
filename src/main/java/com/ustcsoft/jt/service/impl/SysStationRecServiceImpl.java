package com.ustcsoft.jt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.jt.mapper.SysStationRecMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStationRecService;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 岗位的服务实现
 * @author  吴金华
 * @since   2017年1月6日
 */
@Service("sysStationRecService")
public class SysStationRecServiceImpl implements SysStationRecService {
	@Resource
	private SysStationRecMapper sysStationRecMapper;
	
	/**
	 * 分页查询所有岗位基本信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param stationRec
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SystemStationRecVO> query(SystemStationRecVO stationRec,
			int page, int rows) {
		Page<SystemStationRecVO> pageVO = Page.buildPageRequest(page, rows);
		List<SystemStationRecVO> list = sysStationRecMapper.queryWithPage(stationRec, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 加载一条岗位信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	@Override
	public SystemStationRecVO load(String id) {
		return sysStationRecMapper.load(id);
	}

}
