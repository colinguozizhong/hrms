package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SysProfessionTypeVO;

/**
 * 职称类型的服务接口
 * @author  谈健
 * @since   2017年1月11日
 */
public interface SysPositionTypeService {
	/**
	 * 所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	List<SysPositionTypeVO> listAll();
	
	/**
	 * 所有专业类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	List<SysProfessionTypeVO> loadProfession();
	
	/**
	 * 分页查询所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param positionType
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysPositionTypeVO> query(SysPositionTypeVO positionType, int page,
			int rows);
    
	/**
	 * 新增职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	AjaxObj addPositionType(SysPositionTypeVO positionType, UserVO currentUser);
	

	/**
	 * 更新一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	AjaxObj updatePositionType(SysPositionTypeVO positionType,UserVO currentUser);
	
	/**
	 * 加载一条职称类型信息
	 * @author 吴金华
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	SysPositionTypeVO loadPositionTypeInformation(String positionId);


	/**
	 * 根据id删除职称类型
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	AjaxObj deletePositionType(String positionId);
	
	/**
	 * 检查职称编码是否已存在
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	AjaxObj checkPositionId(String positionId);
	
}
