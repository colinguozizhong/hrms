package com.ustcsoft.framework.util.vo;

import com.ustcsoft.framework.vo.BaseVO;

public class Category extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	  public String getName()
	  {
	    return this.name;
	  }

	  public void setName(String name)
	  {
	    this.name = name;
	  }
}
