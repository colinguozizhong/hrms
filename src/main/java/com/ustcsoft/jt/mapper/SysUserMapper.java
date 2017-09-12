package com.ustcsoft.jt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SignDataVO;
import com.ustcsoft.system.model.SysUserVO;
import com.ustcsoft.system.model.XzzfJDzqztpVO;

public interface SysUserMapper {

	@Select("select * from M_user where ORG_ID = #{orgId}")
	List<UserVO> findUserListByOrg(String orgId);

	List<SysUserVO> selectOrgUserPage(@Param("sysUser")  SysUserVO sysUser,@Param("page")  Page<SysUserVO> pageVO);

	List<SysUserVO> selectOrgUserNotAdminPage(@Param("sysUser") SysUserVO sysUser,
			@Param("page") Page<SysUserVO> pageVO);

	String findMaxUserId();

	void insertSysUser(@Param("sysUser") SysUserVO sysUser);

	void updateUser(@Param("sysUser") SysUserVO sysUser);

	void deleteSysUserRoleByUserId(@Param("sysUser") SysUserVO sysUser);

	void deleteSysUserMenuByUserId(@Param("sysUser") SysUserVO sysUser);

	void deleteSysUser(@Param("sysUser") SysUserVO sysUser);

//	void deleteDZQZTP(@Param("sysUser") SysUserVO sysUser);

	void deleteSelectSignByUserId(@Param("sysUser") SysUserVO sysUser);
//
//	void deleteSelectSignBySignId(@Param("sysUser") SysUserVO sysUser);

	void resetPassword(@Param("sysUser") SysUserVO sysUser);

//	void updateSignPassword(@Param("sysUser") SysUserVO sysUser);

	List<SignDataVO> findUnSelectSignListPage(@Param("userId") String userId,@Param("orgId") String orgId,
			@Param("page") Page<SignDataVO> pageVO);

	List<SignDataVO> findSelectSignListPage(@Param("signData") SignDataVO signData,
			@Param("page") Page<SignDataVO> pageVO);

	String findBusinessId(@Param("orgId") String orgId);

	void insertSelectSign(@Param("signData") SignDataVO vo);

	void deleteSelectSign(@Param("signData") SignDataVO vo);

	XzzfJDzqztpVO selectByfileId(@Param("fileId") String fileId);

	SysUserVO findUserByOrgId(@Param("orgId") String orgId);
	@Select("select count(0) from m_user where user_name=#{userName} and user_name!=(select user_name from m_user where user_id =#{userId})")
	int validateUserNameOfUpdate(@Param("userName") String userName,@Param("userId") String userId);
	@Select("select count(0) from m_user where user_name=#{userName}")
	int validateUserName(String userName);

	SysUserVO querySysUserById(@Param("sysUser")SysUserVO sysUser);

}
