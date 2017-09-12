package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

public class SysObjVO extends BaseVO
{
    private static final long serialVersionUID = 1L;
    private String objId;
    private String objCode;
    private String objName;
    private String busiCodeObj;
    private String menuId;
    private String roleId;
    private String flag;
    
	
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjCode() {
		return objCode;
	}
	public void setObjCode(String objCode) {
		this.objCode = objCode;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}

	
	public String getBusiCodeObj() {
		return busiCodeObj;
	}
	public void setBusiCodeObj(String busiCodeObj) {
		this.busiCodeObj = busiCodeObj;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}