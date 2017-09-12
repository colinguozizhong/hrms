package com.ustcsoft.jt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreAppointRecordService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.FamilyVO;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 干部任命申请的rest控制器
 * 
 * @author 谈健
 * @since 2017年1月6日
 */
@RequestMapping("cadreApply")
@RestController
public class CadreAppointRecordRestController extends AbstractRestController {
	@Resource
	private CadreAppointRecordService cadreAppointApplyService;

	/**
	 * 分页查询干部任命申请单信息
	 * 
	 * 
	 */
	@RequestMapping("findCadreAppointRecordList.do")
	public Page<CadreAppointRecordVO> findCadreAppointApplyList(
			String empName, 
			String opPerName, 
			String appDateStart,  
			String appDateEnd,
			String deptNo,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysCadre/findCadreAppointRecordList.do");
		// 初始化查询条件
		CadreAppointRecordVO cadreAppointRecord = new CadreAppointRecordVO();
		cadreAppointRecord.setEmpName(empName);
		cadreAppointRecord.setOpPerName(opPerName);
		cadreAppointRecord.setAppDateStart(appDateStart);
		cadreAppointRecord.setAppDateEnd(appDateEnd);
		if(deptNo==null){
			UserVO currentUser = this.getCurrentUser();
			cadreAppointRecord.setDeptNO(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			cadreAppointRecord.setDeptNO(CommonUtils.getSearchId(deptNo));
		}
		// 查询并返回
		return cadreAppointApplyService.query(cadreAppointRecord, page, rows);
	}
	
	/**
	 * 增加干部任命申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("addCadreAppointRecord.do")
	public AjaxObj insertCadreAppointApply(CadreAppointRecordVO cadreAppointApply) {
		UserVO currentUser = this.getCurrentUser();
		return cadreAppointApplyService.insertCadreAppointRecord(cadreAppointApply, currentUser);
	}
	
	/**
	 * 删除干部任命申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("deleteCadreAppointRecord.do")
	public AjaxObj delete(String id) {
		return cadreAppointApplyService.deleteCadreAppointRecord(id);
	}
	
	/**
	 * 更新干部任命申请单
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param emp
	 * @return
	 */
	@RequestMapping("updateCadreAppointRecord.do")
	public AjaxObj updateCadreAppointApply(CadreAppointRecordVO cadreAppointApply) {
		return cadreAppointApplyService.updateCadreAppointRecord(cadreAppointApply);
	}
	
	@RequestMapping("findJiaTingChengYuan.do")
	public Page<FamilyVO> findJiaTingChengYuan(
			String empId, 
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysCadre/findJiaTingChengYuan.do");
		return cadreAppointApplyService.findJiaTingChengYuan(empId, page, rows);
	}
	/**
	 *  新增/修改 家庭成员
	 * @param familyVO
	 * @return
	 */
	@RequestMapping("saveJiaTingChengYuan.do")
	public String saveJiaTingChengYuan(FamilyVO familyVO) {
		logger.info("sysCadre/saveJiaTingChengYuan.do");
		return cadreAppointApplyService.saveJiaTingChengYuan(familyVO);
	}
	/**
	 *  删除 家庭成员
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteJiaTingChengYuan.do")
	public String deleteJiaTingChengYuan(String id) {
		logger.info("sysCadre/deleteJiaTingChengYuan.do");
		return cadreAppointApplyService.deleteJiaTingChengYuan(id);
	}
	
	/**
	 * @Description:打印办理表
	 * @author dhp
	 * @date 2017年3月30日下午3:41:07
	 * @version V1.0 
	 * @param model
	 * @param flowId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "printCLD.do", method = RequestMethod.GET)
	public ModelAndView printCLD(ModelMap model, String applyId,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception { List<Object> listOb = new ArrayList<Object>();
		Map<String, Object> beanData = new HashMap<String, Object>();
		
		CadreAppointRecordVO cadreAppointApply = new CadreAppointRecordVO();
		cadreAppointApply.setApplyId(applyId);
		cadreAppointApply = cadreAppointApplyService.findCaderAppointById(cadreAppointApply);
		//处理性别数据
		if(("F").equals(cadreAppointApply.getXingBie())){
			cadreAppointApply.setXingBie("女");
		}else if(("M").equals(cadreAppointApply.getXingBie())){
			cadreAppointApply.setXingBie("男");
		}else{
			cadreAppointApply.setXingBie("");
		}
		//处理年龄及出生日期数据
		int  age = 0;
		if(StringUtils.isNotEmpty(cadreAppointApply.getChuShengRiQi())){
			try {  
	            //此处是获得的年龄  
	          age = getAge(parse(cadreAppointApply.getChuShengRiQi()));           //由出生日期获得年龄***  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			String str = cadreAppointApply.getChuShengRiQi().replace("-", "");
			str = str.substring(0, 8);
			cadreAppointApply.setChuShengRiQi(str);
		}
		//处理日期数据
		if(StringUtils.isNotEmpty(cadreAppointApply.getRuDangShiJian())){
			String str = cadreAppointApply.getRuDangShiJian().replace("-", "");
			str = str.substring(0, 8);
			cadreAppointApply.setRuDangShiJian(str);
		}
		if(StringUtils.isNotEmpty(cadreAppointApply.getGongZuoShiJian())){
			String str = cadreAppointApply.getGongZuoShiJian().replace("-", "");
			str = str.substring(0, 8);
			cadreAppointApply.setGongZuoShiJian(str);
		}
		String repstr = 	"</w:t></w:r></w:p><w:p><w:pPr></w:pPr><w:r><w:rPr><w:rFonts w:ascii=\"宋体\" w:fareast=\"宋体\" w:h-ansi=\"宋体\"/><w:color w:val=\"000000\"/><w:sz w:val=\"24\"/></w:rPr><w:t>";	
		beanData.put("xingMing",cadreAppointApply.getXingMing() == null ? "" : cadreAppointApply.getXingMing());
		beanData.put("xingBie",cadreAppointApply.getXingBie() == null ? "" : cadreAppointApply.getXingBie());
		beanData.put("chuShengRiQi",cadreAppointApply.getChuShengRiQi() == null ? "" : cadreAppointApply.getChuShengRiQi());
		beanData.put("suiShu",age == 0 ? "" : age);
		beanData.put("riZhiJaoYuXeWei",cadreAppointApply.getRiZhiZhuanYe() == null ? "" : cadreAppointApply.getRiZhiZhuanYe());
		beanData.put("RiZhiZhuanYe",cadreAppointApply.getRiZhiZhuanYe() == null ? "" :cadreAppointApply.getRiZhiZhuanYe() );
		beanData.put("zaiZhiJiaoYu","");
		beanData.put("zaiZhiXueXiao","");
		beanData.put("zaiZhiXueWei","");
		beanData.put("zaiZhiZhuanYe","");
		beanData.put("chuShengDi",cadreAppointApply.getChuShengDi() == null ? "" : cadreAppointApply.getChuShengDi());
		beanData.put("minZu",cadreAppointApply.getMinZu() == null ? "" :cadreAppointApply.getMinZu() );
		beanData.put("jiGuan",cadreAppointApply.getJiGuan() == null ? "" :cadreAppointApply.getJiGuan()  );
		beanData.put("ruDangShiJian",cadreAppointApply.getRuDangShiJian() == null ? "" :cadreAppointApply.getRuDangShiJian() );
		beanData.put("gongZuoShiJian",cadreAppointApply.getGongZuoShiJian() == null ? "" :cadreAppointApply.getGongZuoShiJian() );
		beanData.put("jianKangZhuangKuang",cadreAppointApply.getJianKangZhuangKuang() == null ? "" :cadreAppointApply.getJianKangZhuangKuang() );
		beanData.put("jiShuZhiWu",cadreAppointApply.getJiShuZhiWu() == null ? "" : cadreAppointApply.getJiShuZhiWu());
		beanData.put("zhuanChang",cadreAppointApply.getZhuanChang() == null ? "" : cadreAppointApply.getZhuanChang());
		beanData.put("riZhiJiaoYu",cadreAppointApply.getRiZhiJiaoYu() == null ? "" : cadreAppointApply.getRiZhiJiaoYu());
		beanData.put("xueXiao",cadreAppointApply.getXueXiao() == null ? "" : cadreAppointApply.getXueXiao());
		beanData.put("xianRenZhiWu",cadreAppointApply.getXianRenZhiWu() == null ? "" :cadreAppointApply.getXianRenZhiWu() );
		beanData.put("niRenZhiWu",cadreAppointApply.getNiRenZhiWu() == null ? "" :cadreAppointApply.getNiRenZhiWu()  );
		beanData.put("niMianZhiWu",cadreAppointApply.getNiMianZhiWu() == null ? "" :cadreAppointApply.getNiMianZhiWu() );
		beanData.put("jianLi",cadreAppointApply.getJianLi() == null ? "" :cadreAppointApply.getJianLi().replaceAll("\r\n",repstr ));
		beanData.put("jiangCheng",cadreAppointApply.getJiangCheng() == null ? "" :cadreAppointApply.getJiangCheng().replaceAll("\r\n",repstr ));
		beanData.put("nianDuKaoHe",cadreAppointApply.getNianDuKaoHe() == null ? "" :cadreAppointApply.getNianDuKaoHe().replaceAll("\r\n",repstr ));
		beanData.put("renMianLiYou",cadreAppointApply.getRenMianLiYou() == null ? "" :cadreAppointApply.getRenMianLiYou().replaceAll("\r\n",repstr ));
		beanData.put("danWeiYiJian",cadreAppointApply.getDanWeiYiJian() == null ? "" :cadreAppointApply.getDanWeiYiJian().replaceAll("\r\n",repstr ));
		beanData.put("shenPiYiJian",cadreAppointApply.getShenPiYiJian() == null ? "" :cadreAppointApply.getShenPiYiJian().replaceAll("\r\n",repstr ));
		beanData.put("jiGuanYiJian",cadreAppointApply.getJiGuanYiJian() == null ? "" :cadreAppointApply.getJiGuanYiJian().replaceAll("\r\n",repstr ));
		String empId = cadreAppointApply.getEmpId();
		List<FamilyVO> familyVOList = cadreAppointApplyService.findJiaTingChengYuan(empId);
		if(familyVOList != null){
			for(int j=1;j<8;j++){
				if(j<=familyVOList.size()){
					beanData.put("chengWei"+j,familyVOList.get(j-1).getChengWei() == null ? "" :familyVOList.get(j-1).getChengWei());
					beanData.put("xingMingJt"+j,familyVOList.get(j-1).getXingMingJt() == null ? "" :familyVOList.get(j-1).getXingMingJt());
					beanData.put("nianLing"+j,familyVOList.get(j-1).getNianLing() == null ? "" :familyVOList.get(j-1).getNianLing());
					beanData.put("zhengZhiMianMao"+j,familyVOList.get(j-1).getZhengZhiMianMao() == null ? "" :familyVOList.get(j-1).getZhengZhiMianMao());
					beanData.put("gongZuoZhiWu"+j,familyVOList.get(j-1).getGongZuoZhiWu() == null ? "" :familyVOList.get(j-1).getGongZuoZhiWu());
				}else{
					beanData.put("chengWei"+j,"");
					beanData.put("xingMingJt"+j,"");
					beanData.put("nianLing"+j,"");
					beanData.put("zhengZhiMianMao"+j,"");
					beanData.put("gongZuoZhiWu"+j,"");
				}
			}
		}
		listOb.add(beanData);
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		// 模板放在com.canyou.template包下面，通过classpath装载
		configuration.setClassForTemplateLoading(this.getClass(),"/com/ustcsoft/jt/util");
		configuration.setWhitespaceStripping(false);
		Template t = null;
		try {
			// test.ftl为要装载的模板
			t = configuration.getTemplate("printCLD.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileName="D:/outFile.doc";
		// 输出文档路径及名称
		File outFile = new File(fileName);
		Writer out = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
			out = new BufferedWriter(oWriter);
			t.process(beanData, out);
			out.flush();
			out.close();
			fos.close();
			
			 if (fileName != null) {
	             String realPath = request.getServletContext().getRealPath(
	                     fileName);
	             File file = new File(realPath, fileName);
	             if (file.exists()) {
	                 response.setContentType("application/force-download");// 设置强制下载不打开
	                 response.addHeader("Content-Disposition", "attachment;fileName=cld.doc");// 设置文件名
	                 byte[] buffer = new byte[1024];
	                 FileInputStream fis = null;
	                 BufferedInputStream bis = null;
	                 try {
	                     fis = new FileInputStream(file);
	                     bis = new BufferedInputStream(fis);
	                     OutputStream os = response.getOutputStream();
	                     int i = bis.read(buffer);
	                     while (i != -1) {
	                         os.write(buffer, 0, i);
	                         i = bis.read(buffer);
	                     }
	                 } catch (Exception e) {
	                     e.printStackTrace();
	                 } finally {
	                     if (bis != null) {
	                         try {
	                             bis.close();
	                         } catch (IOException e) {
	                             e.printStackTrace();
	                         }
	                     }
	                     if (fis != null) {
	                         try {
	                             fis.close();
	                         } catch (IOException e) {
	                             e.printStackTrace();
	                         }
	                     }
	                 }
	             }
	         }
		} catch (FileNotFoundException e1) {
			logger.info(e1.getMessage(), e1);
		}
         return null;
	}
	
	//出生日期字符串转化成Date对象  
    public  Date parse(String strDate) throws ParseException, java.text.ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        return sdf.parse(strDate);  
    } 
    
	 //由出生日期获得年龄  
    public  int getAge(Date birthDay) throws Exception {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.before(birthDay)) {  
            throw new IllegalArgumentException(  
                    "The birthDay is before Now.It's unbelievable!");  
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);  
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);  
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    }  
	
}
