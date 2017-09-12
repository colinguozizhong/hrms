package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SysProfessionTypeVO;

/**
 * 职称类型的Mapper接口
 * @author  谈健
 * @since   2017年1月11日
 */
public interface SysPositionMapper {

	/**
	 * 带分页的查询职称列表
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param sysPositionType
	 * @param pageVO
	 * @return
	 */
	List<SysPositionTypeVO> queryWithPage(@Param("sysPositionType") SysPositionTypeVO sysPositionType,
			@Param("page") Page<SysPositionTypeVO> pageVO);
	
	/**
	 * 所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	public List<SysPositionTypeVO> listAll();
	
	/**
	 * 所有专业类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	public List<SysProfessionTypeVO> loadProfession();
	
	
	/**
	 * 加载一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	SysPositionTypeVO loadPositionTypeInformation(String positionId);
	
	/**
	 * 新增职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	void addPositionType(SysPositionTypeVO sysPositionType);
	
	/**
	 * 更新职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	void updatePositionType(SysPositionTypeVO sysPositionType);
	
	/**
	 * 根据Id删除职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	void deletePositionType(List<String> positionId);
	
	/**
	 * 检查职称编码是否已存在
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	SysPositionTypeVO checkPositionId(String positionId);
	
	
}
