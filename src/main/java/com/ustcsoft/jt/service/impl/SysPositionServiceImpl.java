package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysLogManageMapper;
import com.ustcsoft.jt.mapper.SysPositionMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysPositionTypeService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysLogManageVO;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SysProfessionTypeVO;

/**
 * 职称类型的服务实现
 * @author  谈健
 * @since   2017年1月11日
 */
@Service("sysPositionService")
public class SysPositionServiceImpl implements SysPositionTypeService {
	@Resource
	private SysPositionMapper sysPositionMapper;
	@Resource
	private SysLogManageMapper sysLogManageMapper;
	
	/**
	 * 分页查询所有职称基本信息
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param sysPositionType
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysPositionTypeVO> query(SysPositionTypeVO sysPositionType,
			int page, int rows) {
		Page<SysPositionTypeVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysPositionTypeVO> list = sysPositionMapper.queryWithPage(sysPositionType, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}
   
	/**
	 * 所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	@Override
	public List<SysPositionTypeVO> listAll() {
		return sysPositionMapper.listAll();
	}
	
	/**
	 * 所有专业类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	@Override
	public List<SysProfessionTypeVO> loadProfession() {
		return sysPositionMapper.loadProfession();
	}
	
	/**
	 * 加载一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	@Override
	public SysPositionTypeVO loadPositionTypeInformation(String positionId) {
		return sysPositionMapper.loadPositionTypeInformation(positionId);
	}
	
	/**
	 * 新增职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	@Override
	public AjaxObj addPositionType(SysPositionTypeVO sysPositionType, UserVO currentUser) {
		// 设置创建人
		sysPositionType.setCreater(currentUser.getUserName());
		// 设置创建时间
		sysPositionType.setCreateDate( new Date());
		//执行插入sql
		sysPositionMapper.addPositionType(sysPositionType);
		
		/*//录入系统日志
		SysLogManageVO sysLogManage = new SysLogManageVO();
		//设置操作时间					   
		sysLogManage.setCreateTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		//设置操作人姓名
		sysLogManage.setOperatorName(currentUser.getUserName());
		//设置操作人
		sysLogManage.setOperatorId(currentUser.getUserId()+"");
		sysLogManage.setBrowser(sysPositionType.getBrowser());
		sysLogManage.setOperateMenu("职称编码管理");
		sysLogManage.setOperateType("新增");
		sysLogManage.setObject(sysPositionType.getPositionName());
		sysLogManage.setOperateContent(sysLogManage.getOperatorName()+"在"+sysLogManage.getOperateMenu()+sysLogManage.getOperateType()+sysLogManage.getObject());
		//插入系统日志表
		sysLogManageMapper.insertSysLog(sysLogManage);*/
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 更新一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	@Override
	public AjaxObj updatePositionType(SysPositionTypeVO sysPositionType, UserVO currentUser) {
		sysPositionType.setPositionId(sysPositionType.getPositionIdTemp());
		//执行更新sql
		sysPositionMapper.updatePositionType(sysPositionType);

		/*//录入系统日志
		SysLogManageVO sysLogManage = new SysLogManageVO();
		//设置操作时间					   
		sysLogManage.setCreateTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		//设置操作人姓名
		sysLogManage.setOperatorName(currentUser.getUserName());
		//设置操作人
		sysLogManage.setOperatorId(currentUser.getUserId()+"");
		sysLogManage.setBrowser(sysPositionType.getBrowser());
		sysLogManage.setOperateMenu("职称编码管理");
		sysLogManage.setOperateType("修改");
		sysLogManage.setObject(sysPositionType.getPositionName());
		sysLogManage.setOperateContent(sysLogManage.getOperatorName()+"在"+sysLogManage.getOperateMenu()+sysLogManage.getOperateType()+sysLogManage.getObject());
		//插入系统日志表
		sysLogManageMapper.insertSysLog(sysLogManage);*/
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 根据Id删除职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param position
	 * @return
	 */
	@Override
	public AjaxObj deletePositionType(String positionId) {
		List<String> list = Arrays.asList(positionId.split(","));
		//执删除sql
		sysPositionMapper.deletePositionType(list);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 检查职称编码是否已存在
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param position
	 * @return
	 */
	@Override
	public AjaxObj checkPositionId(String positionId) {
		//执删除sql
		SysPositionTypeVO result = sysPositionMapper.checkPositionId(positionId);
		AjaxObj obj = new AjaxObj();
		if(result==null){
			obj.setCode(0);
		}else{
			obj.setCode(1);
		}
		obj.setMsg("更新成功");
		return obj;
	}
	
}
