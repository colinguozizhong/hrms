package com.ustcsoft.jt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.jt.mapper.SysStationMapper;
import com.ustcsoft.jt.service.SysStationService;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 岗位类型的服务实现
 * @author  吴金华
 * @since   2017年1月5日
 */
@Service("sysStationService")
public class SysStationServiceImpl implements SysStationService {
	@Resource
	private SysStationMapper sysStationMapper;
	
	/**
	 * 所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	@Override
	public List<SystemStationVO> listAll() {
		return sysStationMapper.listAll();
	}

}
