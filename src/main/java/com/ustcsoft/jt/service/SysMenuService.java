package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.system.model.SysMenuVO;
import com.ustcsoft.system.model.SysObjVO;

public interface SysMenuService {

	List<SysMenuVO> selectPurviewMenuAll(String string);
	
	/**
	 *  查询菜单详细
	 */

	SysMenuVO querySysMenu(String node);
	
	/**
	 * 新增系统功能
	 * 
	 */
	int insertSysMenu(SysMenuVO sysMenuVO);
	
	/**
	 * 修改系统功能
	 * 
	 */
    int updateSysMenu(SysMenuVO sysMenuVO);

    /**
	 * 删除系统功能
	 */
	void deleteSysMenu(String node);

	List<SysMenuVO> findAllSysMenuTree();
	/**
	 * 新增菜单按钮对象
	 * @param sysObj
	 * @return
	 */
	int insertSysObj(SysObjVO sysObj);
	/**
	 * 查询按钮信息
	 * @param node
	 * @return
	 */
	SysObjVO querySysObj(String node);
	/**
	 * 修改菜单按钮对象
	 * @param sysObj
	 * @return
	 */
	int updateSysObj(SysObjVO sysObj);
	/**
	 * 删除按钮信息
	 * @param node
	 * @return
	 */
	int deleteSysObj(String node);
}
