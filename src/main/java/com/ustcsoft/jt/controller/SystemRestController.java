package com.ustcsoft.jt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysMenuService;
import com.ustcsoft.jt.service.SysNoticeService;
import com.ustcsoft.jt.service.SysOrgService;
import com.ustcsoft.jt.service.SysRoleService;
import com.ustcsoft.jt.service.SysUserService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.jt.util.MD5Util;
import com.ustcsoft.system.model.MAnnouncementVO;
import com.ustcsoft.system.model.MUserPesVO;
import com.ustcsoft.system.model.OrganizationVO;
import com.ustcsoft.system.model.SignDataVO;
import com.ustcsoft.system.model.SysMenuVO;
import com.ustcsoft.system.model.SysObjVO;
import com.ustcsoft.system.model.SysRoleVO;
import com.ustcsoft.system.model.SysUserVO;
import com.ustcsoft.system.model.XZQHVO;
@RestController
public class SystemRestController extends AbstractRestController {

	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysNoticeService sysNoticeService;
	
	/**
	 * 查询用户常用菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysMenu/findSysMenuTreeAll.do")
	public List<SysMenuVO> findSysMenuTreeAll() throws Exception {
		UserVO userInfo = getCurrentUser();
		return sysMenuService.selectPurviewMenuAll(userInfo.getUserId());
	}
	
	/**
	 * 查询未阅读公告条数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysNotice/serchUnreadGG.do")
	public int serchUnreadGG() throws Exception {
		UserVO userInfo = getCurrentUser();
		MUserPesVO mUserPesVO = new MUserPesVO();
		mUserPesVO.setTableName("M_ANNOUNCEMENT");
		mUserPesVO.setUserId(userInfo.getUserId().toString());
		return sysNoticeService.serchUnreadGG(mUserPesVO);
	}
	
	/**
	 * 查询公告阅读状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysNotice/findGGreadFlag.do")
	public int findGGreadFlag(@RequestParam(value = "announcementId", required = false) String announcementId
			) throws Exception {
		UserVO userInfo = getCurrentUser();
		MUserPesVO mUserPesVO = new MUserPesVO();
		mUserPesVO.setTableName("M_ANNOUNCEMENT");
		mUserPesVO.setUserId(userInfo.getUserId().toString());
		mUserPesVO.setDataId(announcementId);
		return sysNoticeService.findGGreadFlag(mUserPesVO);
	}
	/**
	 * 保存阅读公告记录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysNotice/saveReadGGJL.do")
	public int saveReadGGJL(MUserPesVO mUserPesVO,
			@RequestParam(value = "dataId", required = false) String dataId) throws Exception {
		UserVO userInfo = getCurrentUser();
		mUserPesVO.setUserId(userInfo.getUserId().toString());
		mUserPesVO.setCreateTime(new Date());
		mUserPesVO.setmUserPesId(UUID.randomUUID().toString().replaceAll("-", ""));
		mUserPesVO.setTableName("M_ANNOUNCEMENT");
		return sysNoticeService.saveReadGGJL(mUserPesVO);
	}
	
	/**
	 * 检索公告列表
	 * 
	 * @throws BusinessException
	 */
	@RequestMapping("sysnotice/searchAnnouncementList.do")
	public Page<MAnnouncementVO> searchAnnouncementList(
			MAnnouncementVO mAnnouncementVO,
//			@RequestParam(value = "startFaBuShiJian", defaultValue = "") String startFaBuShiJian,
//			@RequestParam(value = "endFaBuShiJian", defaultValue = "") String endFaBuShiJian,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		UserVO userInfo = getCurrentUser();
		mAnnouncementVO.setReciverOrgId(userInfo.getOrgId());
		mAnnouncementVO.setUserId(userInfo.getUserId().toString());
//		if(!endFaBuShiJian.isEmpty()){
//			mAnnouncementVO.setEndFaBuShiJian(mAnnouncementVO.getEndFaBuShiJian()+" 23:59:59");
//		}
		return sysNoticeService.findMAnnouncementForPage(mAnnouncementVO, page, rows);
	}
	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRole/findRoleList.do")
	public Page<SysRoleVO> findRoleList(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "roleType", required = false) String roleType,
			@RequestParam(value = "roleHy", required = false) String roleHy,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord)
			throws Exception {
		SysRoleVO role = new SysRoleVO();
		role.setRoleName(roleName);
		UserVO userInfo = getCurrentUser();
		role.setCreaterOrgId(userInfo.getOrgId());
		role.setRoleHy(roleHy);
		role.setRoleType(roleType);
		if(!StringUtils.isEmpty(userId)){
			role.setOperaterId(userId.trim());
		}
		return sysRoleService.queryAllSysRoleInCurrentOrg(role, userInfo
				.getUserId().toString(), page, rows);
	}
	
	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRole/findRoleQbjs.do")
	public List<SysRoleVO> findRoleQbjs(
			@RequestParam(value = "menuId", required = false) String menuId)
			throws Exception {
		UserVO userInfo = getCurrentUser();
		String userId = userInfo.getUserId().toString();
		return sysRoleService.findRoleQbjs(userId,menuId);
	}
	/**
	 * 查询菜单详细
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysMenu/findUserMenuByMENU_ID.do")
	public SysMenuVO findUserMenuByMENU_ID(@RequestParam("node") String node)
			throws Exception {
		return sysMenuService.querySysMenu(node);
	}

	
	/**
	 * 新增系统功能
	 * 
	 */
	@RequestMapping("sysMenu/insertSysMenu.do")
	public int insertSysMenu(SysMenuVO sysMenuVO, String menuId,
			String menuName,  String busiCode, String delFlg,
			String menuIco, String menuUrl, String helpUrl, String isPop) {
		return  sysMenuService.insertSysMenu(sysMenuVO);
		
	}

	/**
	 * 修改系统功能
	 * 
	 */
	@RequestMapping("sysMenu/updateSysMenu.do")
	public int updateSysMenu(
			SysMenuVO sysMenuVO,
			@RequestParam("menuId") String menuId
			) {
		return sysMenuService.updateSysMenu(sysMenuVO);
		 
	}
	
	/**
	 * 查询按钮菜单详细
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysMenu/querySysObj.do")
	public SysObjVO querySysObj(@RequestParam("node") String node)
			throws Exception {
		return sysMenuService.querySysObj(node);
	}

	/**
	 * 删除系统功能
	 */
	@RequestMapping("sysMenu/deleteSysMenu.do")
	public int deleteSysMenu(@RequestParam("node") String node) {
		sysMenuService.deleteSysMenu(node);
		return 0;
	}
	
	/**
	 * 新增按钮系统功能
	 * 
	 */
	@RequestMapping("sysMenu/insertSysObj.do")
	public int insertSysObj(SysObjVO sysObj, String menuId,
			String objId, String objCode,String objName, String busiCodeObj) {
		sysObj.setMenuId(menuId);
		return  sysMenuService.insertSysObj(sysObj);
	}
	/**
	 * 修改按钮对象功能
	 * 
	 */
	@RequestMapping("sysMenu/updateSysObj.do")
	public int updateSysObj(SysObjVO sysObj, String objId) {
		return sysMenuService.updateSysObj(sysObj);
	}
	/**
	 * 删除系统功能
	 */
	@RequestMapping("sysMenu/deleteSysObj.do")
	public int deleteSysObj(@RequestParam("node") String node) {
		return sysMenuService.deleteSysObj(node);
	}
	
	/**
	 * 新增编辑角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRole/editRole")
	public int editRole(SysRoleVO sysRole) throws Exception {
		UserVO userInfo = getCurrentUser();
		return sysRoleService.editRole(sysRole, userInfo);
	}
	/**
	 * 删除角色
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRole/deleteRole.do")
	public int deleteRole(@RequestParam("roleId") String roleId)
			throws Exception {
		sysRoleService.deleteSysRole(roleId);
		return 0;
	}

	/**
	 * 查询系统菜单及页面对象-角色授权(一次性加载树)
	 * 
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping("sysRole/findSysMenuOrObjectTreeAll4Role.do")
	public List<SysMenuVO> findSysMenuOrObjectTreeAll4Role(
			@RequestParam(value = "roleId", required = false) String roleId,
			@RequestParam(value = "node", required = false) String node)
			throws Exception {
				SysMenuVO _sysMenu = new SysMenuVO();
				_sysMenu.setParentId("0");
				_sysMenu.setRoleId(roleId);
				UserVO userInfo = getCurrentUser();
				_sysMenu.setUserId(userInfo.getUserId().toString());
				return sysRoleService.selectSysMenus(userInfo.getUserId().toString(),_sysMenu);
	}
	
	@RequestMapping("sysMenu/findAllSysMenuTree.do")
	public List<SysMenuVO> findAllSysMenuTree(){
		return sysMenuService.findAllSysMenuTree();
	}

	/**
	 * 设置角色-授权
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysRole/setRole.do")
	public int setRole(@RequestParam("roleId") String roleId,
			@RequestParam("menuIds") String menuIds,
			@RequestParam("types") String types) {
		if (!menuIds.equals("")) {
			UserVO userInfo = getCurrentUser();
			this.sysRoleService.setRole(userInfo, menuIds, roleId,types);
		}
		return 0;
	}

	/**
	 * 设置用户角色
	 * 
	 * @return
	 */
	@RequestMapping("sysUserRole/setUserRole.do")
	public int setUserRole(@RequestParam(value = "roleId") String roleId,
			@RequestParam(value = "userId") String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("userId", userId);
		sysRoleService.setUserRole(map);
		return 0;
	}

	/**
	 * 检索组织树
	 * 
	 * @param documentId
	 * @param roleType1
	 */
	@RequestMapping("deptorg/searchOrgTree.do")
	public List<OrganizationVO> searchOrgTree(
			@RequestParam(value = "node") String node) {
		UserVO userinfo = this.getCurrentUser();
		return sysOrgService.searchOrgTree(node, userinfo);
	}
	
	/**
	 * 根据orgId检索下属组织树
	 * 
	 * @param documentId
	 * @param roleType1
	 */
	@RequestMapping("deptorg/searchSubordinateOrgTree.do")
	public List<OrganizationVO> searchSubordinateOrgTree(
			@RequestParam(value = "businessId") String businessId) {
		return sysOrgService.searchSubordinateOrgTree(businessId);
	}
	
	/**
	 * 检索按地区的配置下级单位的树
	 * 
	 * @param areaId
	 * @return
	 */
	@RequestMapping("deptorg/searchOrgTreePeizhi")
	public List<OrganizationVO> searchOrgTreePeizhi(
			@RequestParam(value = "areaId", required = false) String areaId) {
		return sysOrgService.searchOrgTreePeizhi(areaId);
	}

	/**
	 * 检索按地区的配置下级单位的树(行业)
	 * 
	 * @return
	 */
	@RequestMapping("deptorg/searchOrgTreePeizhiIndustry")
	public List<OrganizationVO> searchOrgTreePeizhiIndustry(
			@RequestParam(value = "industryId", required = false) String industryId) {
		return sysOrgService.searchOrgTreePeizhiIndustry(industryId);
	}

	/**
	 * 检索组织列表(业务)
	 */
	@RequestMapping("deptorg/initOrgList.do")
	public Page<OrganizationVO> searchOrgList(
			OrganizationVO org,
			@RequestParam(value = "businessId", required = false) String businessId,
			@RequestParam(value = "orgName", required = false) String orgName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		if (businessId != null) {
			org.setBusinessId(CommonUtils.getSearchId(businessId));
		}
		return sysOrgService.searchOrgList(org, page, rows);
	}
	
	/**
	 * 检索组织(不带page查找)
	 */
	@RequestMapping("deptorg/searchOrg.do")
	public OrganizationVO searchOrgListNoPage(OrganizationVO org,@RequestParam(value = "businessId") String businessId) {
		return sysOrgService.searchOrgListNoPage(org);
	}

	/**
	 * 检索组织列表(地区)
	 */
	@RequestMapping("deptorg/initOrgListArea.do")
	public Page<OrganizationVO> initOrgListArea(OrganizationVO org,
			@RequestParam(value = "areaId", required = false) String areaId,
			@RequestParam(value = "orgName", required = false) String orgName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		if (areaId != null) {
			org.setAreaId(CommonUtils.getSearchId(areaId));
		}
		return sysOrgService.searchOrgListArea(org, page, rows);
	}

	/**
	 * 检索组织列表(行业)
	 */
	@RequestMapping("deptorg/initOrgListIndustry.do")
	public Page<OrganizationVO> initOrgListIndustry(
			OrganizationVO org,
			@RequestParam(value = "industryId", required = false) String industryId,
			@RequestParam(value = "orgName", required = false) String orgName,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		if (industryId != null) {
			org.setIndustryId(CommonUtils.getSearchId(industryId));
		}
		return sysOrgService.initOrgListIndustry(org, page, rows);
	}

	/**
	 * 检索行政区划的树
	 */
	@RequestMapping("deptorg/searchOrgXZTree.do")
	public List<XZQHVO> searchOrgXZTree(XZQHVO xzqh) {
		List<XZQHVO> list = sysOrgService.searchOrgXZTree(xzqh);
		return list;
	}

	/**
	 * 删除组织
	 * 
	 * @param businessId
	 * @return
	 */
	@RequestMapping("deptorg/deleteOrg.do")
	public int deleteOrg(
			@RequestParam(value = "businessId", required = false) String businessId) {
		sysOrgService.deleteOrg(businessId);
		return 0;
	}

	/**
	 * 银行账号管理检索列表
	 */
	@RequestMapping("deptorg/searchOrgBankList.do")
	public Page<OrganizationVO> searchOrgBankList(OrganizationVO org,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		return sysOrgService.searchOrgBankList(org, page, rows);
	}

	/**
	 * 银行账号管理编辑
	 */
	@RequestMapping("deptorg/editBank.do")
	public int addBank(OrganizationVO org,
			@RequestParam(value = "businessId") String businessId,
			@RequestParam(value = "bankName") String bankName,
			@RequestParam(value = "accountNumber") String accountNumber
			) {
		sysOrgService.addBank(org);
		return 0;
	}

	/**
	 * 银行账号管理删除
	 */
	@RequestMapping("deptorg/delBank.do")
	public int delBank(
			OrganizationVO org,
			@RequestParam(value = "businessId", required = false) String businessId,
			@RequestParam(value = "accountNumber", required = false) String accountNumber,
			@RequestParam(value = "bankName", required = false) String bankName) {
		sysOrgService.delBank(org);
		return 0;
	}

	/**
	 * 配置下级单位
	 */
	@RequestMapping("deptorg/setPeizhi.do")
	public int setPeizhi(OrganizationVO org,
			@RequestParam(value = "areaId") String areaId,
			@RequestParam(value = "orgIds") String orgIds) {
		sysOrgService.setPeizhi(areaId, orgIds);
		return 0;
	}

	/**
	 * 配置下级单位(行业)
	 */
	@RequestMapping("deptorg/setPeizhiIndustry.do")
	public int setPeizhiIndustry(OrganizationVO org,
			@RequestParam(value = "industryId") String industryId,
			@RequestParam(value = "orgIds") String orgIds) {
		sysOrgService.setPeizhiIndustry(industryId, orgIds);
		return 0;
	}

	/**
	 * 删除配置(地区)
	 */
	@RequestMapping("deptorg/delPeizhi.do")
	public int delPeizhi(@RequestParam(value = "areaId") String areaId) {
		sysOrgService.delPeizhi(areaId);
		return 0;
	}

	/**
	 * 删除配置(行业)
	 */
	@RequestMapping("deptorg/delPeizhiIndustry.do")
	public int delPeizhiIndustry(
			@RequestParam(value = "industryId") String industryId) {
		sysOrgService.delPeizhiIndustry(industryId);
		return 0;
	}

	/**
	 * 根据orgId查询org
	 */
	@RequestMapping("deptorg/searchOrgById.do")
	public OrganizationVO searchOrgById(@Param("orgId") String orgId) {
		return sysOrgService.searchOrgById(orgId);
	}

	/**
	 * 查询用户列表
	 */
	@RequestMapping("sysUser/findUserList.do")
	public Page<SysUserVO> findUserList(
			SysUserVO sysUser,
			@RequestParam(value = "chaXunFangShi", required = false) String chaXunFangShi,
			@RequestParam(value = "businessId", required = false) String businessId,
			@RequestParam(value = "areaId", required = false) String areaId,
			@RequestParam(value = "industryId", required = false) String industryId,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "orgName", required = false) String orgName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "range", required = false) String userOrgId,
			@RequestParam(value = "idNo", required = false) String idNo,
			@RequestParam(value = "tel", required = false) String tel,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		UserVO userInfo = getCurrentUser();
		if ("".equals(orgId)||null==orgId) {
			sysUser.setOrgId(userInfo.getOrgId());
		}
		return sysUserService.findUserList(sysUser, userInfo, page, rows);

	}
	
	/**
	 * 根据OrgId查询用户VO
	 */
	@RequestMapping("sysUser/findUserByOrgId.do")
	public SysUserVO findUserByOrgId(@RequestParam(value = "orgId") String orgId) {
		SysUserVO sysUserVO=new SysUserVO();
		sysUserVO =sysUserService.findUserByOrgId(orgId);
		if(!StringUtils.isEmpty(sysUserVO.getDuLiZhiFa())&&!("").equals(sysUserVO.getDuLiZhiFa())){
			switch (sysUserVO.getDuLiZhiFa()) {
			case "0":
				sysUserVO.setDuLiZhiFa("否");
				break;
			case "1":
				sysUserVO.setDuLiZhiFa("是");
				break;
			}
		}
	
		if(!StringUtils.isEmpty(sysUserVO.getStatus())&&!("").equals(sysUserVO.getStatus())){
			switch (sysUserVO.getStatus()) {
			case "00":
				sysUserVO.setStatus("停用");
				break;
			case "01":
				sysUserVO.setStatus("启用");
				break;
			}
		}
		
		if(!StringUtils.isEmpty(sysUserVO.getOrgLv())&&!("").equals(sysUserVO.getOrgLv())){
			switch (sysUserVO.getOrgLv()) {
			case "01":
				sysUserVO.setOrgLv("省厅");
				break;
			case "02":
				sysUserVO.setOrgLv("省行业局");
				break;
			case "03":
				sysUserVO.setOrgLv("市局");
				break;
			case "04":
				sysUserVO.setOrgLv("市行业局");
				break;
			case "05":
				sysUserVO.setOrgLv("县/区局");
				break;
			case "06":
				sysUserVO.setOrgLv("县/区行业局");
				break;
			case "07":
				sysUserVO.setOrgLv("基层站所/分局/高速路政大队");
				break;
			}
		}
		
		if(!StringUtils.isEmpty(sysUserVO.getTradeType())&&!("").equals(sysUserVO.getTradeType())){
			switch (sysUserVO.getTradeType()) {
			case "00":
				sysUserVO.setTradeType("交通主管部门");
				break;
			case "01":
				sysUserVO.setTradeType("公路");
				break;
			case "02":
				sysUserVO.setTradeType("运管");
				break;
			case "03":
				sysUserVO.setTradeType("海事");
				break;
			case "04":
				sysUserVO.setTradeType("质监");
				break;
			case "05":
				sysUserVO.setTradeType("港航");
				break;
			case "06":
				sysUserVO.setTradeType("通用");
				break;
			}
		}
		
		return sysUserVO;
	}
		/**
		 * 根据userId查询用户VO
		 */
		@RequestMapping("sysUser/querySysUserById.do")
		public SysUserVO querySysUserById(){
			UserVO userInfo = getCurrentUser();
			SysUserVO sysUser = new SysUserVO();
			sysUser.setUserId(userInfo.getUserId().toString());
			return sysUserService.querySysUserById(sysUser);
		}
	/**
	 * 删除用户
	 */
	@RequestMapping("sysUser/deleteUser.do")
	public int deleteUser(SysUserVO sysUser,
			@RequestParam(value = "userId") String userId) {
		sysUserService.deleteUser(sysUser);
		return 0;

	}

	/**
	 * 重置用户密码
	 */
	@RequestMapping("sysUser/resetUser.do")
	public int resetUser(SysUserVO sysUser,
			@RequestParam(value = "userId") String userId) {
		sysUserService.resetUser(sysUser);
		return 0;

	}
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sysUser/updatePassword.do")
	public int updatePassword(String oldPassword,String newPassword) throws Exception {
		SysUserVO sysUser =  new SysUserVO();
		UserVO userInfo = getCurrentUser();
		sysUser.setUserId(userInfo.getUserId().toString());
		sysUser = sysUserService.querySysUserById(sysUser);
		oldPassword = MD5Util.MD5(oldPassword);
		if (sysUser.getUserPwd().equals(oldPassword)) {
			newPassword = MD5Util.MD5(newPassword);
			SysUserVO sysUser1 =  new SysUserVO();
			sysUser1.setUserId(sysUser.getUserId());
			sysUser1.setUserPwd(newPassword);
			 sysUserService.updateUserPWD(sysUser1);
			return 1;
		}else{
			return 0;
		}
	}
	/**
	 * 获取未签章列表
	 */
	@RequestMapping("sysUser/findUnSelectSignList.do")
	public Page<SignDataVO> findUnSelectSignList(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		UserVO userInfo = getCurrentUser();
		return sysUserService
				.findUnSelectSignList(userId, userInfo, page, rows);

	}

	/**
	 * 查询以获取的签章
	 */
	@RequestMapping("sysUser/findSelectSignList.do")
	public Page<SignDataVO> findSelectSignList(
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			@RequestParam(value = "sidx", required = false) String sidx,
			@RequestParam(value = "sord", required = false) String sord) {
		UserVO userInfo = getCurrentUser();
		return sysUserService.findSelectSignList(userId, userInfo, page, rows);

	}

	/**
	 * 新增签章授权
	 * 
	 * @return
	 */
	@RequestMapping("sysUser/insertSelectSign.do")
	public int insertSelectSign(
			@RequestParam(value = "signIds", required = false) String signId,
			@RequestParam(value = "userId", required = false) String userId) {
		UserVO userInfo = getCurrentUser();
		SignDataVO vo = new SignDataVO();
		vo.setSignId(signId);
		vo.setUserId(userId);
		vo.setCreaterOrgid(userInfo.getOrgId());
		vo.setCreater(userInfo.getUserId().toString());
		vo.setCreateTime(new Date());
		sysUserService.insertSelectSign(vo);
		return 0;
	}

	/**
	 * 删除签章授权
	 */
	@RequestMapping("sysUser/deleteSelectSign.do")
	public int deleteSelectSign(
			@RequestParam(value = "signIds", required = false) String signId,
			@RequestParam(value = "userId", required = false) String userId) {
		UserVO userInfo = getCurrentUser();
		SignDataVO vo = new SignDataVO();
		vo.setSignId(signId);
		vo.setUserId(userId);
		vo.setCreater(userInfo.getUserId().toString());
		vo.setCreateTime(new Date());
		sysUserService.deleteSelectSign(vo);
		return 0;
	}

}
