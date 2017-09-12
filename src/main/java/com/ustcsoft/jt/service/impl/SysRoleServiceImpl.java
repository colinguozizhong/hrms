package com.ustcsoft.jt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysRoleMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysRoleService;
import com.ustcsoft.system.model.SysMenuVO;
import com.ustcsoft.system.model.SysRoleMenuVO;
import com.ustcsoft.system.model.SysRoleObjVO;
import com.ustcsoft.system.model.SysRoleVO;
import com.ustcsoft.system.model.SysUserRoleVO;

@Service("sysRoleService")
public class SysRoleServiceImpl  implements SysRoleService {
	@Resource
	private SysRoleMapper sysRoleMapper;
	
	
	 /**
	  * 删除角色
	  */
	public void deleteSysRole(String roleId) {
		String[] roleIds=roleId.split(",");
		for(int i=0; i<roleIds.length; i++){
			sysRoleMapper.deleteSysRole(roleIds[i]);
	}
		
	}
	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public Page<SysRoleVO> queryAllSysRoleInCurrentOrg(SysRoleVO role,
			String userId, int page, int rows) {
				// 如果不是超级系统管理员，那么就无法看到超级系统管理员，即使是本单位的都不行
				long rsum = sysRoleMapper.isCJGLY(userId);
				// 如果不是主体单位管理员，那么就无法看到主体单位管理员，即使是本单位的都不行
				long rsum2 = sysRoleMapper.isZTDWGLY(userId);
				Page<SysRoleVO> pageVO = Page.buildPageRequest(page, rows);
				List<SysRoleVO> list = null;
				if (rsum >= 1L) {
					// 超级系统管理员
					list = sysRoleMapper.queryAllSysRoleInCurrentOrgPage(role, pageVO);// ?
				} else if (rsum2 >= 1L) {
					// 主体单位管理员
					list = sysRoleMapper.queryAllSysRoleInCurrentOrgNoCJGLYPage(role, pageVO);// ?
				} else {
					// 非主体单位管理员
					list = sysRoleMapper.queryAllSysRoleInCurrentOrgNoGLYPage(role, pageVO);// ?
				}
				pageVO.setItems(list);
				return pageVO;
	}

	@SuppressWarnings("unchecked")
	public List<SysMenuVO> selectSysMenus(String userId,
			SysMenuVO _sysMenu) {
		// 查找用户是否具有管理员权限
				long rsum =sysRoleMapper.isAdmin(userId);
				if(rsum >= 1L){
					return sysRoleMapper.selectSysMenusAndObj4RoleAdmin(_sysMenu);
				}else{
					return sysRoleMapper.selectSysMenusAndObj4Role(_sysMenu);
				}
	}
	/**
	 * 编辑角色
	 */
	public int editRole(SysRoleVO sysRole,UserVO userInfo) {
		int count=sysRoleMapper.validataRoleName(sysRole);
		if(count==0){
			if(StringUtils.isEmpty(sysRole.getRoleId())){
				sysRole.setCeraterUserId(userInfo.getUserId().toString());
				sysRole.setCreaterOrgId(userInfo.getOrgId());
//				Long l=sysRoleMapper.findMaxId();
				sysRole.setRoleId(UUID.randomUUID().toString());
				sysRoleMapper.insertSysRole(sysRole);
				//插入返回0
				return 0;
			}else{
				sysRoleMapper.updateSysRole(sysRole);
				//修改返回2
				return 2;
			}
		}else{
			//用户名重复返回1
			return 1;
		}
		
		
		
	}
	
	/**
	 * 角色设置页面的权限
	 */
	public void setRole(UserVO userInfo, String menuIds,String roleId,String types) {
		
		
		SysRoleVO opRole = new SysRoleVO();
		opRole.setRoleId(roleId);
		opRole.setOperaterId(userInfo.getUserId().toString());
		// 查找用户是否具有管理员权限
		long rsum = (Long) sysRoleMapper.isCJGLY(userInfo.getUserId().toString());
		if(rsum >= 1L){
			// 系统管理员
			sysRoleMapper.deleteSysRoleMenuByRoleId(roleId);
			sysRoleMapper.deleteSysRoleObjByRoleId(roleId);
		}else{
			sysRoleMapper.deleteSysRoleMenuByRoleIdAndUserId(opRole);
			sysRoleMapper.deleteSysRoleObjByRoleIdAndUserId(opRole);
		}
		String[] _ids = menuIds.split(",");
		String[] _types = types.split(",");
		for(int i=0; i<_ids.length; i++){
			String type = _types[i];
			if(type.equals("SysMenu")){
				SysRoleMenuVO _roleMenu = new SysRoleMenuVO();
				_roleMenu.setMenuId(_ids[i]);
				_roleMenu.setRoleId(roleId);
			    sysRoleMapper.insertSysRoleMenu(_roleMenu);
			}else if(type.equals("SysObject")){
				SysRoleObjVO roleObj = new SysRoleObjVO();
				roleObj.setObjId(_ids[i]);
				roleObj.setRoleId(roleId);
				sysRoleMapper.insertSysRoleObj(roleObj);
			}
		}
	}
	/**
	 * 设置用户角色
	 */
	public void setUserRole(Map<String, String> map) {
		
		String userId=map.get("userId");
		//1.设置用户角色之前首先将该用户的角色删除在进行设置
		long rsum = sysRoleMapper.isCJGLY(userId);
		// 如果不是主体单位管理员，那么就无法看到主体单位管理员，即使是本单位的都不行
		long rsum2 = sysRoleMapper.isZTDWGLY(userId);
		if(rsum >= 1L){
			// 超级系统管理员
			sysRoleMapper.deleteSysUserRolebyUserId(userId);
				
		}else if(rsum2 >= 1L){
			// 主体单位管理员
			sysRoleMapper.deleteSysUserRolebyUserIdNotCJGLY(userId);
					
		}else {
			// 非主体单位管理员
			sysRoleMapper.deleteSysUserRolebyUserIdNotGLY(userId);
		}
		//删除完之后将 新的传入角色
		String[] roleIds=map.get("roleId").split(",");
		for(int i=0; i<roleIds.length; i++){
			SysUserRoleVO userRole = new SysUserRoleVO();
			userRole.setRoleId(roleIds[i]);
			userRole.setUserId(userId);
		    sysRoleMapper.insertSysUserRole(userRole);
	}
	}

	/**
	 * 查询页面对象OBJ_CODE
	 */
	public List<SysRoleVO> findRoleQbjs(String userId, String menuId){
		return sysRoleMapper.findRoleQbjs(userId,menuId);
	}
	
	
}
