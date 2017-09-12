package com.ustcsoft.jt.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sx2.utils.ReadExcel;
import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysChangeRecMapper;
import com.ustcsoft.jt.mapper.SysEmpMapper;
import com.ustcsoft.jt.mapper.SysLeaveEmpMapper;
import com.ustcsoft.jt.mapper.SysStationRecMapper;
import com.ustcsoft.jt.mapper.SysStudyRecMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysEmpService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.EmpFileVO;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SysStudyRecVO;
import com.ustcsoft.system.model.SystemEmpVO;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 人员基本信息的服务实现
 * 
 * @author 吴金华
 * @since 2017年1月4日
 */
@Service("sysEmpService")
public class SysEmpServiceImpl implements SysEmpService {
	@Value("${UPLOAD_PATH}")
	private String path;// 文件储存地址
	@Resource
	private SysEmpMapper sysEmpMapper;
	@Resource
	private SysStudyRecMapper sysStudyRecMapper;
	@Resource
	private SysStationRecMapper sysStationRecMapper;
	@Resource
	private SysLeaveEmpMapper sysLeaveEmpMapper;
	@Resource
	private SysChangeRecMapper sysChangeRecMapper;
	
	
	/**
	 * 分页查询所有人员基本信息
	 * 
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @param emp
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SystemEmpVO> query(SystemEmpVO emp, int page, int rows) {
		Page<SystemEmpVO> pageVO = Page.buildPageRequest(page, rows);
		List<SystemEmpVO> list = sysEmpMapper.queryWithPage(emp, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 分页查询所有人员对应附件
	 * 
	 * @author 谈健
	 * @since 2017年3月13日
	 * @param empFile
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<EmpFileVO> queryEmpFile(EmpFileVO empFile, int page, int rows) {
		Page<EmpFileVO> pageVO = Page.buildPageRequest(page, rows);
		List<EmpFileVO> list = sysEmpMapper.queryEmpFilePage(empFile, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 加载一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public SysChangeRecVO getEmpExtraInfo(String id) {
		return sysEmpMapper.getEmpExtraInfo(id);
	}
	
	/**
	 * 添加人员基本信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param emp 要添加的人员信息
	 * @param user 当前登录的用户
	 * @return
	 */
	@Override
	public AjaxObj add(SystemEmpVO emp, UserVO user) {
		/* 添加人员基本信息 */
		String empId = UUID.randomUUID().toString().replace("-", "");
		// 设置人员id
		emp.setId(empId);
		// 设置创建人
		emp.setCreater(user.getUserName());
		// 设置创建时间
		emp.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 正式添加
		sysEmpMapper.add(emp);
		
		/*添加相关联的岗位信息*/
		SystemStationRecVO stationRec = new SystemStationRecVO();
		// 设置记录id
		stationRec.setId(UUID.randomUUID().toString().replace("-", ""));
		// 设置相关联的人员ID
		stationRec.setEmpId(empId);
		// 设置所在部门
		stationRec.setDeptNo(emp.getDeptNO());
		// 设置原岗位信息
		stationRec.setOldStationId(emp.getStationId());
		stationRec.setOldStationGrade(emp.getStationGrade());
		stationRec.setOldPosition(emp.getPosition());
		// 设置创建人
		stationRec.setCreater(user.getUserName());
		// 设置创建时间
		stationRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysStationRecMapper.add(stationRec);
		
		if(StringUtils.isNotEmpty(emp.getEduFirstRec())&&!(emp.getEduRec()).equals(emp.getEduFirstRec())){
			/* 添加相关联的第一学历学历信息 */
			SysStudyRecVO studyRec1 = new SysStudyRecVO();
			// 设置记录id
			studyRec1.setId(UUID.randomUUID().toString().replace("-", ""));
			// 设置人员id
			studyRec1.setEmpId(empId);
			// 设置学历
			studyRec1.setEduRec(emp.getEduFirstRec());
			// 设置创建人
			studyRec1.setCreater(user.getUserName());
			//学历类型:第一学历
			studyRec1.setEduType("first");
			// 设置创建时间
			studyRec1.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
			sysStudyRecMapper.add(studyRec1);
		}
		/* 添加相关联的学历信息 */
		SysStudyRecVO studyRec = new SysStudyRecVO();
		// 设置记录id
		studyRec.setId(UUID.randomUUID().toString().replace("-", ""));
		// 设置人员id
		studyRec.setEmpId(empId);
		// 设置学历
		studyRec.setEduRec(emp.getEduRec());
		// 设置学位
		studyRec.setDegree(emp.getDegree());
		// 设置毕业院校
		studyRec.setStudySchool(emp.getStudySchool());
		// 设置毕业时间
		studyRec.setFinishDate(emp.getFinishDate());
		//学历类型:最高学历
		studyRec.setEduType("top");
		// 设置创建人
		studyRec.setCreater(user.getUserName());
		// 设置创建时间
		studyRec.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysStudyRecMapper.add(studyRec);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("添加成功");
		return ao;
	}

	/**
	 * 更新人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param emp
	 * @return
	 */
	@Override
	public AjaxObj update(SystemEmpVO emp) {
		/* 更新人员基本信息 */
		// 正式更新
		sysEmpMapper.update(emp);
		
		/*更新相关联的岗位信息
		SystemStationRecVO stationRec = new SystemStationRecVO();
		// 设置相关联的人员ID
		stationRec.setEmpId(emp.getId());
		// 设置所在部门
		stationRec.setDeptNo(emp.getDeptNO());
		// 设置原岗位信息
		stationRec.setOldStationId(emp.getStationId());
		stationRec.setOldStationGrade(emp.getStationGrade());
		stationRec.setOldPosition(emp.getPosition());
		sysStationRecMapper.update(stationRec);*/
		
		/* 更新相关联的最高学历信息 */
		SysStudyRecVO studyRec = new SysStudyRecVO();
		// 设置人员id
		studyRec.setEmpId(emp.getId());
		// 设置学历
		studyRec.setEduRec(emp.getEduRec());
		// 设置学位
		studyRec.setDegree(emp.getDegree());
		// 设置毕业院校
		studyRec.setStudySchool(emp.getStudySchool());
		// 设置毕业时间
		studyRec.setFinishDate(emp.getFinishDate());
		//学历类型:最高学历
		studyRec.setEduType("top");
		sysStudyRecMapper.update(studyRec);
		if(StringUtils.isNotEmpty(emp.getEduFirstRec())){
			/* 更新相关联的第一学历学历信息 */
			SysStudyRecVO studyRec1 = new SysStudyRecVO();
			// 设置人员id
			studyRec1.setEmpId(emp.getId());
			// 设置学历
			studyRec1.setEduRec(emp.getEduFirstRec());
			//学历类型:第一学历
			studyRec1.setEduType("first");
			sysStudyRecMapper.update(studyRec1);
		}
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("更新成功");
		return ao;
	}

	/**
	 * 根据id删除人员基本信息（以,分割）
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj delete(String id) {
		// 组装list参数
		List<String> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = Arrays.asList(id.split(","));
		}
		
		// 先根据人员ID删除相关联的学历信息
		sysStudyRecMapper.deleteByEmpids(list);
		// 再根据人员ID删除相关联的岗位信息
		sysStationRecMapper.deleteByEmpids(list);
		// 根据人员ID删除相关联的离职人员信息
		sysLeaveEmpMapper.deleteByEmpids(list);
		//在根据EmpId查询异动单号
		List<String> listC = sysChangeRecMapper.findChangeHeadId(list);
		if(listC != null && listC.size() > 0){
			// 在根据异动单号删除人员异动申请信息（head表）
			sysChangeRecMapper.deleteChangeRecByList(listC);
		}
		// 在根据EmpId删除人员异动申请信息（detail表）
		sysChangeRecMapper.deleteChangeRecDetailByEmpId(list);

		// 再根据id删除人员基本信息
		sysEmpMapper.deletes(list);
		
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("删除成功");
		return ao;
	}

	/**
	 * 加载一条带学历信息的人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param id
	 * @return
	 */
	@Override
	public SystemEmpVO loadWithStudy(String id) {
		// 先根据人员ID查找相关联的学历信息
		List<SysStudyRecVO> list = sysStudyRecMapper.loadByEmpid(id);
//		SysStudyRecVO studyRec = sysStudyRecMapper.loadByEmpid(id);
		// 在根据id查找人员基本信息
		SystemEmpVO emp = sysEmpMapper.load(id);
		if(emp !=null&&list !=null){
			emp.setStudyRec(list.get(0));
		}
		return emp;
	}

	/**
	 * 加载职称和岗位
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public PositionApplyVO loadPosAndSta(String id) {
		return sysEmpMapper.loadPosAndSta(id);
	}
	
	/**
	 * 加载一条人员基本信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@Override
	public SystemEmpVO load(String id) {
		return sysEmpMapper.load(id);
	}
	
	/**
	 * 上传文件
	 * @author 谈健
	 * @since 2017年3月14日
	 * @param 
	 * @return
	 */
	@Override
	public int insertFile( MultipartFile file, String fileName,
			EmpFileVO empFile) {
		empFile.setFileId(UUID.randomUUID().toString().replace("-", ""));

		try {
			//如果文件夹不存在则创建，文件夹以员工Id命名 
			File file2 =new File(path+"//EMP_FILE//"+empFile.getEmpId());
			if  (!file2 .exists()  && !file2 .isDirectory())      
			{       
			    file2 .mkdir();    
			}
			
			InputStream is = file.getInputStream();
			FileOutputStream fos = new FileOutputStream(path+"//EMP_FILE//"+"//"+empFile.getEmpId()+"//"+empFile.getFileId()+fileName.substring(fileName.indexOf(".")));
//			FileOutputStream fos2 = new FileOutputStream(path+"//EMP_FILE//"+empFile.getUUID()+fileName.substring(fileName.indexOf(".")));
			byte[] b = new byte[1024];
			while((is.read(b)) != -1){
				fos.write(b);
//				fos2.write(b);
			}
			is.close();
			fos.close();
//			fos2.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
//		OfficeToPdf otp = new OfficeToPdf(path+"//M_ZCWJ//"+empFile.getUUID()+fileName.substring(fileName.indexOf(".")));
//		Thread otpThread = new Thread(otp);
//		taskExecutor.execute(otp);
		empFile.setFileFormat(fileName.substring(fileName.indexOf(".")));
		empFile.setSavePath(path+"/EMP_FILE/"+"/"+empFile.getEmpId()+"/"+empFile.getFileId()+empFile.getFileFormat());
		empFile.setUploadTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		sysEmpMapper.insertEmpFile(empFile);
		return 0;
	}
	
	/**
	 * 根据id删除职称申请单（以,分割）
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deleteFile(String fileId) {
		// 在根据id删除人员附件
		sysEmpMapper.deleteFile(fileId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	public EmpFileVO getEmpFileByFileId(String fileId){
		return sysEmpMapper.getEmpFileByFileId(fileId);
	}
	
	@Override
	public EmpFileVO upLoadFile(InputStream inputStream, EmpFileVO empFile) {
		return null;
	}
	
	@Override
	public List<SystemEmpVO> queryWithList(SystemEmpVO emp){
		List<SystemEmpVO> list = sysEmpMapper.queryWithList(emp);
		return list;
	}
	
	@Override  
    public String readExcelFile(MultipartFile file, UserVO currentUser) {  
        String result ="";  
        //创建处理EXCEL的类  
        ReadExcel readExcel=new ReadExcel();  
        //解析excel，获取上传的事件单  
        List<SystemEmpVO> useList = readExcel.getExcelInfo(file);  
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作, 
        if(useList != null && !useList.isEmpty()){
        	  String daoRuFlagIsE = "";
	  		  String carIdIsE = "";
	  		  String addR = "";
	  		  String editR = "";
	  		  String delR = "";
	  		  String orgId = "";
	  		  String depId = "";
        	  for(int i=0;i<useList.size();i++){
        		  SystemEmpVO emp = useList.get(i);
        		  if(emp != null){
        			  if(StringUtils.isNotEmpty(emp.getUnitName())){
            			  orgId = sysEmpMapper.findOrgIdByOrgName(emp.getUnitName());
            		  }
            		  if(StringUtils.isNotEmpty(emp.getDeptName())){
            			  depId = sysEmpMapper.findOrgIdByOrgName(emp.getDeptName());
            		  }
            		//单位
    				emp.setUnitNO(orgId);
    				//部门
    				emp.setDeptNO(depId);
            		  if(emp.getCardid().isEmpty()){
            			  carIdIsE = carIdIsE + "第"+ emp.getXuHao() +"行数据身份证号为空无法导入;";
            		  }else{
            			  if(emp.getDaoRuFlag().isEmpty()){
                			  daoRuFlagIsE = daoRuFlagIsE + "第"+ emp.getXuHao() +"行数据操作标志为空无法导入;";
                		  }else{
                			  SystemEmpVO empByCardid = sysEmpMapper.findEmpByCardId(emp.getCardid());
                			  if(("0").equals(emp.getDaoRuFlag())){//新增导入
                				  if(empByCardid != null){
                					  addR = addR + "第"+ emp.getXuHao() +"行数据身份证号已存在无法新增;";
                				  }else{
                					  /* 添加人员基本信息 */
                					add(emp,currentUser);
                				  }
                    		  }else if(("1").equals(emp.getDaoRuFlag())){//修改导入
                    			  if(empByCardid != null){
                    				  emp.setId(empByCardid.getId());
                    				  sysEmpMapper.update(emp);
                				  }else{
                					  editR = editR + "第"+ emp.getXuHao() +"行数据身份证号不存在无法修改;";
                				  }
                    		  }else if(("2").equals(emp.getDaoRuFlag())){//删除导入
                    			  if(empByCardid != null){
                    				  emp.setId(empByCardid.getId());
                    				  delete(emp.getId());
                				  }else{
                					  delR = delR + "第"+ emp.getXuHao() +"行数据身份证号不存在无法删除;";
                				  }
                    		  }
                		  }
            		  }
        		  }
              }
        	  if(daoRuFlagIsE.isEmpty()&&carIdIsE.isEmpty()
        		&&addR.isEmpty()&&editR.isEmpty()
        		&&delR.isEmpty()){
        		  result = "上传成功";  
        	  }else{
        		  result=daoRuFlagIsE+carIdIsE+addR+editR+delR;
        	  }
        }else{  
            result = "上传失败";  
        }  
        return result;  
    } 
	
}
