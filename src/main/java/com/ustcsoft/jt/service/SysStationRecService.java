package com.ustcsoft.jt.service;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 岗位的服务接口
 * @author  吴金华
 * @since   2017年1月6日
 */
public interface SysStationRecService {
	/**
	 * 分页查询所有岗位基本信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param stationRec
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SystemStationRecVO> query(SystemStationRecVO stationRec, int page,
			int rows);
	
	/**
	 * 加载一条岗位信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	SystemStationRecVO load(String id);

}
