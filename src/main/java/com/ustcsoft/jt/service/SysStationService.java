package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.system.model.SystemStationVO;

/**
 * 岗位类型的服务接口
 * @author  吴金华
 * @since   2017年1月5日
 */
public interface SysStationService {

	/**
	 * 所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	List<SystemStationVO> listAll();

}
