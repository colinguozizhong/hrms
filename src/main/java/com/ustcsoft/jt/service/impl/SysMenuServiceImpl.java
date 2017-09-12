package com.ustcsoft.jt.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.jt.mapper.SysMenuMapper;
import com.ustcsoft.jt.service.SysMenuService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.SysMenuVO;
import com.ustcsoft.system.model.SysObjVO;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Resource
	private SysMenuMapper sysMenuMapper;

	/**
	 * 查询用户常用菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuVO> selectPurviewMenuAll(String userId) {
		return sysMenuMapper.selectPurviewMenuAll(userId);
	}

	/**
	 * 查询菜单详细
	 */
	public SysMenuVO querySysMenu(String node) {

		return sysMenuMapper.querySysMenu(node);
	}

	/**
	 * 新增系统功能
	 * 
	 */
	public int insertSysMenu(SysMenuVO sysMenuVO) {
		String startmenuId = sysMenuVO.getMenuId().replaceAll("(00)+$", "");
		String maxId = sysMenuMapper.selectMaxId(startmenuId + "%");
		if (sysMenuVO.getMenuId().equals("0")) {
			sysMenuVO.setIsLeaf("0");
			sysMenuVO.setParentId("0");
			sysMenuVO.setState("open");
			sysMenuVO.setIsPop("");
			int temp = Integer.parseInt(maxId.substring(0, 2)) + 1;
			if (temp >= 10) {
				sysMenuVO.setMenuId(StringUtils.rightPad(String.valueOf(temp),
						10, "0"));
			} else if (temp < 10) {
				sysMenuVO.setMenuId("0"
						+ StringUtils.rightPad(String.valueOf(temp), 9, "0"));
			}

		} else {

			int insertMenuId = Integer.parseInt(maxId.substring(1,
					startmenuId.length() + 2)) + 1;
			String menuIdStr = StringUtils.rightPad(startmenuId.substring(0, 1)
					+ String.valueOf(insertMenuId), 10, "0");
			sysMenuVO.setMenuId(menuIdStr);

			if (startmenuId.length() == 2) {
				sysMenuVO.setIsLeaf("1");
				sysMenuVO.setParentId(StringUtils.rightPad(startmenuId, 10, "0"));
				sysMenuVO.setMenuUrl(sysMenuVO.getMenuUrl().trim());
			} else if(startmenuId.length() == 3){
				sysMenuVO.setIsLeaf("1");
				sysMenuVO.setParentId(StringUtils.rightPad(startmenuId, 10, "0")+"0");
				sysMenuVO.setMenuUrl(sysMenuVO.getMenuUrl().trim());
			}else {
				sysMenuVO.setParentId(StringUtils.rightPad(startmenuId, 10, "0"));
				sysMenuVO.setMenuUrl(sysMenuVO.getMenuUrl().trim());
				sysMenuVO.setIsLeaf(String.valueOf(startmenuId.length() / 4));
			}
		}
		int count=sysMenuMapper.validateMenuName(sysMenuVO.getMenuName());
		if(count==0){
			sysMenuMapper.insertSysMenu(sysMenuVO);
			return 0;
		}else{
			return 1;
		}
		

	}

	/**
	 * 修改系统功能
	 * 
	 */
	public int updateSysMenu(SysMenuVO sysMenuVO) {
		if (!"".equals(sysMenuVO.getMenuUrl().trim())) {
			sysMenuVO.setMenuUrl(sysMenuVO.getMenuUrl().trim());
			sysMenuVO.setIsLeaf("1");

			if (sysMenuVO.getIsPop() == null) {
				sysMenuVO.setIsPop("N");
			}
		} else {
			sysMenuVO.setIsLeaf("0");
			sysMenuVO.setIsPop("");

		}
		int count=sysMenuMapper.validateMenuNameOfUpdate(sysMenuVO.getMenuName(),sysMenuVO.getMenuId());
		if(count==0){
			sysMenuMapper.updateSysMenu(sysMenuVO);
			return 0;
		}else{
			return 1;
		}
		
	}

	/**
	 * 删除系统功能
	 */
	public void deleteSysMenu(String node) {
		String nodeId = CommonUtils.getSearchId(node);
		sysMenuMapper.deleteSysMenu(nodeId + "%");
	}

	/**
	 * 系统菜单树（all）
	 */
	public List<SysMenuVO> findAllSysMenuTree() {

//		List<SysMenuVO> list = sysMenuMapper.findAllSysMenuTree();
		List<SysMenuVO> list = sysMenuMapper.selectSysMenusList();
		SysMenuVO menu = new SysMenuVO();
		for(int i = 0;i < list.size();i++){
			if(list.get(i).getParentId().equals("0")){
				list.get(i).setState("closed");
			}
		}
		menu.setMenuId("0");
		menu.setMenuName("系统功能菜单");
		menu.setState("open");
		list.add(0, menu);
		return list;
	}
	/**
	 * 新增系统功能
	 * 
	 */
	public int insertSysObj(SysObjVO sysObj) {
		sysObj.setObjId(UUID.randomUUID().toString().replaceAll("-", ""));
		sysMenuMapper.insertSysObj(sysObj);
		return 0;
	}
	
	/**
	 * 查询按钮详细
	 */
	public SysObjVO querySysObj(String node) {
		return sysMenuMapper.querySysObj(node);
	}
	/**
	 * 修改菜单按钮对象
	 * @param sysObj
	 * @return
	 */
	public int updateSysObj(SysObjVO sysObj){
		sysMenuMapper.updateSysObj(sysObj);
		return 0;
	}
	/**
	 * 删除按钮信息
	 * @param node
	 * @return
	 */
	public int deleteSysObj(String node){
		sysMenuMapper.deleteSysObj(node);
		return 0;
	}
}
