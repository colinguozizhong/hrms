package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.constants.Constants;
import com.ustcsoft.jt.mapper.SysChangeRecMapper;
import com.ustcsoft.jt.mapper.SysEmpMapper;
import com.ustcsoft.jt.mapper.SysLeaveEmpMapper;
import com.ustcsoft.jt.mapper.SysStationRecMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysChangeRecService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SysLeaveEmpVO;
import com.ustcsoft.system.model.SystemEmpVO;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 人员异动的服务实现
 * @author  吴金华
 * @since   2017年1月10日
 */
@Service("sysChangeRecService")
public class SysChangeRecServiceImpl implements SysChangeRecService {
	@Resource
	private SysEmpMapper sysEmpMapper;
	@Resource
	private SysStationRecMapper sysStationRecMapper;
	@Resource
	private SysChangeRecMapper sysChangeRecMapper;
	@Resource
	private SysLeaveEmpMapper sysLeaveEmpMapper;
	
	/**
	 * 分页查询所有人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRecVO
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysChangeRecVO> query(SysChangeRecVO changeRec, int page,
			int rows) {
		Page<SysChangeRecVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysChangeRecVO> list = sysChangeRecMapper.queryWithPage(changeRec, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 分页查询所有人员异动申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRecVO
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysChangeRecVO> queryApplyList(SysChangeRecVO changeRec, int page,
			int rows) {
		Page<SysChangeRecVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysChangeRecVO> list = sysChangeRecMapper.queryApplyWithPage(changeRec, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}
	
	
	@Override
	public AjaxObj record(SysChangeRecVO sysChangeRec, UserVO user) {
		sysChangeRec.setNewJob(sysChangeRec.getNewJob().replaceAll(",", ""));
		sysChangeRec.setNewStationId(sysChangeRec.getNewStationId().replaceAll(",", ""));
		SystemEmpVO empVO = new SystemEmpVO();
		empVO.setId(sysChangeRec.getEmpId());
		if("晋升".equals(sysChangeRec.getTypeName())||"岗位调整".equals(sysChangeRec.getTypeName())||"部门调整".equals(sysChangeRec.getTypeName())){
			// 先将新的信息更新到人员基本信息表
			SystemEmpVO emp = new SystemEmpVO();
			// 设置要变更的人员信息的主键id
			emp.setId(sysChangeRec.getEmpId());
			// 设置新的部门到人员基本信息表
			emp.setDeptNO(sysChangeRec.getNewDeptNo());
			// 设置新的职务到人员基本信息表
			emp.setJob(sysChangeRec.getNewJob());
			// 设置新的岗位到人员基本信息表
			emp.setStationId(sysChangeRec.getNewStationId());
			// 设置新的岗位级别到人员基本信息表
			emp.setStationGrade(sysChangeRec.getNewStationGrade());
			// 设置新的职称到人员基本信息表
			emp.setPosition(sysChangeRec.getNewPrositon());
			// 执行更新操作
			sysEmpMapper.update(emp);
			// 再形成一条新的岗位变更信息
			SystemStationRecVO stationRec = new SystemStationRecVO();
			// 设置主键id
			stationRec.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			// 设置当前人员的id
			stationRec.setEmpId(sysChangeRec.getEmpId());
			// 设置原部门
			stationRec.setDeptNo(sysChangeRec.getOldDeptNo());
			// 设置原岗位
			stationRec.setOldStationId(sysChangeRec.getOldStationId());
			// 设置原岗位等级
			stationRec.setOldStationGrade(sysChangeRec.getOldStationGrade());
			// 设置原职称
			stationRec.setOldPosition(sysChangeRec.getOldPosition());
			// 设置变更后部门
			stationRec.setNewDeptNo(sysChangeRec.getNewDeptNo());
			// 设置变更后岗位
			stationRec.setNewStationId(sysChangeRec.getNewStationId());
			// 设置变更后岗位等级
			stationRec.setNewStationGrade(sysChangeRec.getNewStationGrade());
			// 设置变更后职称
			stationRec.setNewPrositon(sysChangeRec.getNewPrositon());
			// 设置变更时间
			stationRec.setAlterDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
			// 设置创建人
			stationRec.setCreater(user.getUserName());
			// 设置创建时间
			stationRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
			// 执行插入
			sysStationRecMapper.add(stationRec);
			empVO.setStatus(Constants.EMP_SUTUS_ONLINE);
		}else if("离退休".equals(sysChangeRec.getTypeName())||"退休".equals(sysChangeRec.getTypeName())
				||"离休".equals(sysChangeRec.getTypeName())){
			SysLeaveEmpVO leaveEmp = new SysLeaveEmpVO();
			//设置主键Id
			leaveEmp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			leaveEmp.setEmpId(sysChangeRec.getEmpId());
			leaveEmp.setLeaveType("0");
			leaveEmp.setLeaveDate(sysChangeRec.getChangeDate());
			leaveEmp.setLeaveReason(sysChangeRec.getChangeReason());
			leaveEmp.setRemark(sysChangeRec.getRemark());
			leaveEmp.setCreater(user.getUserName());
			leaveEmp.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
			sysLeaveEmpMapper.insertLeaveEmp(leaveEmp);
			empVO.setStatus(Constants.EMP_SUTUS_RETIRED);
		}else if ("转正".equals(sysChangeRec.getTypeName())){
			//更新人员表状态  将员工状态改为在职
			empVO.setStatus(Constants.EMP_SUTUS_ONLINE);
		}else if("入职".equals(sysChangeRec.getTypeName())){
			empVO.setStatus(Constants.EMP_SUTUS_PROBATION);
		}else if("辞退".equals(sysChangeRec.getTypeName())){
			empVO.setStatus(Constants.EMP_SUTUS_FIRED);
		}else if("离岗".equals(sysChangeRec.getTypeName())){
			empVO.setStatus(Constants.EMP_SUTUS_OUTSERVICE);
		}else{
			//更新人员表状态  将员工状态改为在职
			empVO.setStatus(Constants.EMP_SUTUS_ONLINE);
		}
		//审批异动申请单后更新对应人员状态
		sysChangeRecMapper.updateEmpStatus(empVO);

		// 更新异动head信息
		// 设置审核人
		sysChangeRec.setVerifyPer(user.getUserName());
		// 设置审核时间
		sysChangeRec.setVerifyDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysChangeRec.setStatus(Constants.PERSON_CHANGE_STATUS_PASS);
		// 执行插入
		sysChangeRecMapper.updateHeadInfo(sysChangeRec);
		sysChangeRec.setChangeReocrdId(UUID.randomUUID().toString().replaceAll("-", ""));
		sysChangeRec.setCreater(user.getUserName());
		sysChangeRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		//生成人员异动记录
		sysChangeRecMapper.insertChangeRecord(sysChangeRec);
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("添加成功");
		return ao;
	}
	
	/**
	 * 异动信息申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@Override
	public AjaxObj apply(SysChangeRecVO sysChangeRec, UserVO user) {
		// 先将异动申请信息更新到T_CHANGE_HEAD表
		String headId = UUID.randomUUID().toString().replaceAll("-", "");
		sysChangeRec.setHeadId(headId);
		sysChangeRec.setStatus(Constants.PERSON_CHANGE_STATUS_SAVE);
		sysChangeRec.setOrgId(sysChangeRec.getOrgId());
		sysChangeRec.setCreater(user.getUserName());
		// 设置创建时间
		sysChangeRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 执行插入sql
		sysChangeRecMapper.insertChangeHead(sysChangeRec);
		//将异动申请信息更新到T_CHANGE_DETAIL表
		sysChangeRec.setDetailId(UUID.randomUUID().toString().replaceAll("-", ""));
		sysChangeRecMapper.insertChangeDetail(sysChangeRec);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setId(headId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 加载一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public SysChangeRecVO load(String id) {
		return sysChangeRecMapper.load(id);
	}
	
	/**
	 * 加载部门名称
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public SysChangeRecVO loadDeptName(String id) {
		return sysChangeRecMapper.loadDeptName(id);
	}
	
	/**
	 * 根据id删除干部考核（以,分割）
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deleteChangeRec(String headId) {
		// 在根据id删除人员异动申请信息（head表）
		sysChangeRecMapper.deleteChangeRec(headId);
		// 在根据id删除人员异动申请信息（detail表）
		sysChangeRecMapper.deleteChangeRecDetail(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	/**
	 * 提交人员异动申请
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param String
	 * @return
	 */
	@Override
	public AjaxObj submitChangeRec(String headId) {
		// 正式更新
		sysChangeRecMapper.submitChangeRec(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 更新人员异动申请
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param changeRec
	 * @return
	 */
	@Override
	public AjaxObj updateChangeRec(SysChangeRecVO changeRec) {
		// 正式更新
		sysChangeRecMapper.updateChangeRec(changeRec);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
}
