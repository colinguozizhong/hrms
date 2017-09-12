package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SignDataVO;
import com.ustcsoft.system.model.SysUserVO;
import com.ustcsoft.system.model.XzzfJDzqztpVO;



public interface SysUserService {

	Page<SysUserVO> findUserList(SysUserVO sysUser,UserVO userInfo,int page,int rows);

	AjaxObj addUser(SysUserVO sysUser);

	int updateUser(SysUserVO sysUser);

	void deleteUser(SysUserVO sysUser);

	void resetUser(SysUserVO sysUser);

	Page<SignDataVO> findUnSelectSignList(String userId, UserVO userInfo, int page,
			int rows);

	Page<SignDataVO> findSelectSignList(String userId, UserVO userInfo,
			int page, int rows);

	void insertSelectSign(SignDataVO vo);

	void deleteSelectSign(SignDataVO vo);

	XzzfJDzqztpVO selectByfileId(String fileId);

	SysUserVO findUserByOrgId(String orgId);

	SysUserVO querySysUserById(SysUserVO sysUser);

	void updateUserPWD(SysUserVO sysUser1);


	

}
