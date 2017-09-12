package com.ustcsoft.jt.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.service.SysEmpService;
import com.ustcsoft.jt.util.StrUtil;
import com.ustcsoft.system.model.EmpFileVO;

/**
 * 人员基本信息控制器
 * @author  吴金华
 * @since   2017年1月4日
 */
@RequestMapping("sysEmp")
@Controller
public class SystemEmpController extends AbstractRestController {
	@Resource
	private SysEmpService sysEmpService;
	
	/**
	 * 跳转到人员基本信息的主页面
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @return
	 */
	@RequestMapping("index.do")
	public String frameJsp() {
		logger.info("sysDep/index.do");
		return "system/sysEmpManager";
	}
	
//	private String removeFileSuffix(String fileName) {
//		return fileName.substring(0, fileName.indexOf("."));
//	}
	
	/**
	 * 文件下载
	 */
	@RequestMapping("downloadFile.do")
	public void downloadFile(@RequestParam(value = "fileId") String fileId,
			@RequestParam(value = "mulu",required=false) String mulu, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("downloadFile.do fileId={} mulu={}", fileId,mulu);
		String filePath = "";
		String fileName = "";
		File file = null;
		if(!StrUtil.isNullString(fileId) /*&& 
				!StrUtil.isNullString(mulu)*/){
			// 根据参数获取文件地址
//			if("M_JCYJ".equals(mulu)){
				EmpFileVO empFile = sysEmpService.getEmpFileByFileId(fileId);
				if(empFile!=null){
					filePath = empFile.getSavePath();
					fileName = empFile.getFileName();
//					String realPath = fileId + fileName.substring(fileName.lastIndexOf("."));
					//file = new File(PropertyUtil.getProperty("UPLOAD_PATH")+"/"+mulu+"/"+realPath);
					file = new File(filePath);
				}
//			}
		}
		if(file!=null && file.isFile() && file.exists()){
			response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition",
                    "attachment;fileName=" + new String(fileName.getBytes("UTF-8"),"iso-8859-1"));// 设置文件名
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
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
		}
//		AjaxObj ao = new AjaxObj();
//		 .setCode(0);
//		ao.setMsg("下载成功");
//		return ao;
	}
}
