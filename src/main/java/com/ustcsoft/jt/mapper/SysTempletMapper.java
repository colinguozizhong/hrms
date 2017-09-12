package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.KeyWordVO;
import com.ustcsoft.system.model.SysTempletVO;

public interface SysTempletMapper {

	List<SysTempletVO> queryForPage(@Param("vo") SysTempletVO vo, @Param("page") Page<SysTempletVO> pageVO);

	List<KeyWordVO> findKeyWordList1(KeyWordVO vo);

	String findMaxZhuJanId();

	void deleteTemplet(String muBanId);

	SysTempletVO queryTempletById(SysTempletVO vo);

	void insertTemplet(SysTempletVO vo);

	void updateTemplet(SysTempletVO vo);

	List<SysTempletVO> queryWSNameAll();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}