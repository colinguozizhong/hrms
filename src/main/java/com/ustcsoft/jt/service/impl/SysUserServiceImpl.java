package com.ustcsoft.jt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysOrgMapper;
import com.ustcsoft.jt.mapper.SysRoleMapper;
import com.ustcsoft.jt.mapper.SysUserMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysUserService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.jt.util.MD5Util;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.OrganizationVO;
import com.ustcsoft.system.model.SignDataVO;
import com.ustcsoft.system.model.SysUserRoleVO;
import com.ustcsoft.system.model.SysUserVO;
import com.ustcsoft.system.model.XzzfJDzqztpVO;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Resource
	private SysOrgMapper sysOrgMapper;

	/**
	 * 查询用户列表
	 */
	public Page<SysUserVO> findUserList(SysUserVO sysUser, UserVO userInfo,
			int page, int rows) {

		Page<SysUserVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysUserVO> list = new ArrayList<SysUserVO>();
		// 1.判断查询方式 "" 01 02,如果为空则跳过该查询方式，如果为01则查询本单位的所有用户，如果为02 则查询该单位的下级单位
		// 查询本单位的的
		if (StringUtils.isEmpty(sysUser.getBusinessId())
				&& StringUtils.isEmpty(sysUser.getAreaId())
				&& StringUtils.isEmpty(sysUser.getIndustryId())) {
			OrganizationVO orgVo = sysOrgMapper.selectByOrgId(userInfo.getOrgId());
			sysUser.setBusinessId(orgVo.getBusinessId());
			sysUser.setAreaId(orgVo.getAreaId());
			sysUser.setIndustryId(orgVo.getIndustryId());
		}
		// 2.判断执法机构：为空则跳过查询所有，业务查询businessId 地区则查询areaId 行业则查询industryId
		sysUser.setSearchBusinessId(CommonUtils.getSearchId(sysUser.getBusinessId()));
		sysUser.setSearchAreaId(CommonUtils.getSearchId(sysUser.getAreaId()));
		sysUser.setSearchIndustryId(CommonUtils.getSearchId(sysUser.getIndustryId()));
		long rsum = sysRoleMapper.isAdmin(userInfo.getUserId().toString());
		if (rsum >= 1L) {
			list = sysUserMapper.selectOrgUserPage(sysUser, pageVO);
		} else {
			list = sysUserMapper.selectOrgUserNotAdminPage(sysUser, pageVO);
		}
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 增加用户
	 */
	public AjaxObj addUser(SysUserVO sysUser) {
		String userName=sysUser.getUserName();
		int c1=sysUserMapper.validateUserName(userName);
		if(c1==0){
//			String maxUserId = sysUserMapper.findMaxUserId();
			String userId = String.valueOf(UUID.randomUUID());
			sysUser.setUserId(userId);
			sysUser.setLoginTime(new Date());
			sysUser.setLoginType("1");
			sysUser.setUserPwd(MD5Util.MD5("888888"));
			sysUserMapper.insertSysUser(sysUser);
			// 给新增用户添加默认角色 固定角色Id ：1000000000000002
			String uid = sysUser.getUserId();
			String fid = "1000000000000002";
			SysUserRoleVO userRole = new SysUserRoleVO();
			userRole.setUserId(uid);
			userRole.setRoleId(fid);
			sysRoleMapper.insertSysUserRole(userRole);
			AjaxObj ao = new AjaxObj();
			ao.setCode(0);
			ao.setId(userId);
			ao.setMsg("添加成功");
			return ao;
		}else{
			AjaxObj ao = new AjaxObj();
			ao.setCode(1);
			return ao;
		}
	}

	/**
	 * 更新用户
	 */
	public int updateUser(SysUserVO sysUser) {
		int c=sysUserMapper.validateUserNameOfUpdate(sysUser.getUserName(),sysUser.getUserId());
		if(c==0){
			sysUserMapper.updateUser(sysUser);
			return 0;
		}else{
			return 1;
		}
		

	}
	/**
	 * 根据userId查询用户VO
	 */
	public SysUserVO querySysUserById(SysUserVO sysUser){
		return sysUserMapper.querySysUserById(sysUser);
	}
	/**
	 * 删除用户
	 */
	public void deleteUser(SysUserVO sysUser) {
		String[] userIds=sysUser.getUserId().split(",");
		for(int i=0;i<userIds.length;i++){
			sysUser.setUserId(userIds[i]);
			sysUserMapper.deleteSysUserRoleByUserId(sysUser);
			sysUserMapper.deleteSysUserMenuByUserId(sysUser);
			sysUserMapper.deleteSysUser(sysUser);
//			sysUserMapper.deleteDZQZTP(sysUser);
			// 删除本用户的签章授权
//			sysUserMapper.deleteSelectSignByUserId(sysUser);
		}
		
		// //删除本用户的授权给其他人的签章
		// sysUserMapper.deleteSelectSignBySignId(sysUser);

	}

	/**
	 * 重置密码
	 */
	public void resetUser(SysUserVO sysUser) {
		String passWord = MD5Util.MD5("888888");
		sysUser.setUserPwd(passWord);
		String[] userIds=sysUser.getUserId().split(",");
		for(int i=0;i<userIds.length;i++){
			sysUser.setUserId(userIds[i]);
			sysUserMapper.resetPassword(sysUser);
			// 修改用户密码 同时更新用户签章密码
//			sysUserMapper.updateSignPassword(sysUser);
		}
		
		
	}

	/**
	 * 修改密码
	 */
	public void updateUserPWD(SysUserVO sysUser) {
			sysUserMapper.resetPassword(sysUser);
	}
	
	/**
	 * 获取未签章列表
	 */
	public Page<SignDataVO> findUnSelectSignList(String userId,
			UserVO userInfo, int page, int rows) {
		Page<SignDataVO> pageVO = Page.buildPageRequest(page, rows);
		List<SignDataVO> list = new ArrayList<SignDataVO>();
		list = sysUserMapper.findUnSelectSignListPage(userId,
				userInfo.getOrgId(), pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 获取已签章列表
	 */
	public Page<SignDataVO> findSelectSignList(String userId, UserVO userInfo,
			int page, int rows) {
		SignDataVO signData = new SignDataVO();
		// if (signType != null && !signType.isEmpty()) {
		// // 签章类型不为空，为页面签章查询
		// param.put("userId", userInfo.getUserId().toString());
		// param.put("createrOrgid", null);
		// param.put("signType", signType);
		// param.put("operatorOrgId", userInfo.getOrgId());
		// } else {
		// 签章授权查询
		// 根据orgid获取businessId
		String tempBusinessId = sysUserMapper.findBusinessId(userInfo
				.getOrgId());
		String businessId = CommonUtils.getSearchId(tempBusinessId);
		Page<SignDataVO> pageVO = Page.buildPageRequest(page, rows);
		List<SignDataVO> list = new ArrayList<SignDataVO>();
		signData.setBusinessId(businessId);
		signData.setUserId(userId);
		signData.setSignType("");
		signData.setCreaterOrgid("");
		signData.setOperatorOrgId(userInfo.getOrgId());
		list = sysUserMapper.findSelectSignListPage(signData, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}

	/**
	 * 新增签章授权
	 */
	public void insertSelectSign(SignDataVO vo) {
		String[] signIds = vo.getSignId().split(",");
		for (int i = 0; i < signIds.length; i++) {
			vo.setSignId(signIds[i]);
			sysUserMapper.insertSelectSign(vo);

		}

	}

	/**
	 * 删除签章授权
	 */
	public void deleteSelectSign(SignDataVO vo) {
		String[] signIds = vo.getSignId().split(",");
		for (int i = 0; i < signIds.length; i++) {
			vo.setSignId(signIds[i]);
			sysUserMapper.deleteSelectSign(vo);

		}

	}

	
	public XzzfJDzqztpVO selectByfileId(String fileId) {
		return  sysUserMapper.selectByfileId(fileId);
		
	}

	@Override
	public SysUserVO findUserByOrgId(String orgId) {
		return sysUserMapper.findUserByOrgId(orgId);
	}

}
