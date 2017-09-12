package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.CadreCheckVO;
import com.ustcsoft.system.model.CadreReserveVO;

public interface CadreReserveMapper {

	/**
	 * 带分页的查询后备干部信息
	 * @return
	 */
	List<CadreReserveVO> queryWithPage(@Param("cadreReserve")CadreReserveVO cadreReserve, @Param("page") Page<CadreReserveVO> page);
    
	/**
	 * 新增后备干部(插入head表)
	 * @return
	 */
	void insertCadreReserveHead(CadreReserveVO cadreReserve);
	
	/**
	 * 新增后备干部(插入detail表)
	 * @return
	 */
	void insertCadreReserveDetail(CadreReserveVO cadreReserve);
	
	/**
	 * 删除后备干部(head表)
	 * @return
	 */
	void deleteCadreReserveHead(String headId);
	
	/**
	 * 删除后备干部(Detail表)
	 * @return
	 */
	void deleteCadreReserveDetail(String headId);
	
	/**
	 * 提交
	 * @return
	 */
	void submitCadreReserve(String headId);
	
	/**
	 * 删除后备干部
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param ids
	 */
	void deleteCadreReserve(List<String> ids);
	
	/**
	 * 更新后备干部
	 * @return
	 */
	void updateCadreReserve(CadreReserveVO cadreReserve);
	
	/**
	 * 审批后备干部
	 * @return
	 */
	void approvCadreReserve(CadreReserveVO cadreReserve);
	
	/**
	 * 不予审批(更新后备干部申请表appStatus)
	 * @return
	 */
	void updateAppStatus(CadreReserveVO cadreReserve);
	
	/**
	 * 不予审批(录入后备人才库)
	 * @return
	 */
	void denyCadreReserve(CadreReserveVO cadreReserve);
	/**
	 * 更新不予审批原因
	 * @param cadreReserve
	 */
	void updateFailedReason(CadreReserveVO cadreReserve);
}
