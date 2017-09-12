package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

public class SysRoleObjVO extends BaseVO
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
    private String objId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
    



}
