package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysMenuVO;
import com.ustcsoft.system.model.SysRoleMenuVO;
import com.ustcsoft.system.model.SysRoleObjVO;
import com.ustcsoft.system.model.SysRoleVO;
import com.ustcsoft.system.model.SysUserRoleVO;

public interface SysRoleMapper {

	long isCJGLY(@Param("userId") String userId);

	long isZTDWGLY(@Param("userId") String userId);

	List<SysRoleVO> queryAllSysRoleInCurrentOrgPage(@Param("role") SysRoleVO role, @Param("page") Page<SysRoleVO> page);

	List<SysRoleVO> queryAllSysRoleInCurrentOrgNoCJGLYPage(@Param("role") SysRoleVO role,
			@Param("page") Page<SysRoleVO> page);

	List<SysRoleVO> queryAllSysRoleInCurrentOrgNoGLYPage(@Param("role") SysRoleVO role,
			@Param("page") Page<SysRoleVO> page);

	void insertSysRole(SysRoleVO sysRole);

	void updateSysRole(SysRoleVO sysRole);

	void deleteSysRole(String roleId);

	Long findMaxId();

	List selectSysMenusAndObj4RoleAdmin(SysMenuVO _sysMenu);

	List selectSysMenusAndObj4Role(SysMenuVO _sysMenu);

	void deleteSysRoleMenuByRoleId(@Param("roleId") String roleId);

	void deleteSysRoleMenuByRoleIdAndUserId(@Param("opRole") SysRoleVO opRole);

	void insertSysRoleMenu(@Param("roleMenu") SysRoleMenuVO roleMenu);

	long isAdmin(@Param("userId") String userId);

	void insertSysUserRole(@Param("userRole") SysUserRoleVO userRole);
    
	void deleteSysUserRolebyUserId(@Param("userId") String userId);

	void deleteSysUserRolebyUserIdNotCJGLY(@Param("userId") String userId);

	void deleteSysUserRolebyUserIdNotGLY(@Param("userId") String userId);
//	@Select("select count(0) from m_role where role_name=#{roleName} ")
	int validataRoleName(@Param("sysRole") SysRoleVO sysRole);

	void insertSysRoleObj(@Param("roleObj") SysRoleObjVO roleObj);

	void deleteSysRoleObjByRoleId(@Param("roleId") String roleId);

	void deleteSysRoleObjByRoleIdAndUserId(@Param("opRole") SysRoleVO opRole);

	List<SysRoleVO> findRoleQbjs(@Param("userId") String userId, @Param("menuId") String menuId);

}
