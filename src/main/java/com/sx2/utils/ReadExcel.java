package com.sx2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ustcsoft.system.model.SystemEmpVO;


public class ReadExcel {
	//总行数  
    private int totalRows = 0;    
    //总条数  
    private int totalCells = 0;   
    //错误信息接收器  
    private String errorMsg;  
    //构造方法  
    public ReadExcel(){}  
    //获取总行数  
    public int getTotalRows()  { return totalRows;}   
    //获取总列数  
    public int getTotalCells() {  return totalCells;}   
    //获取错误信息  
    public String getErrorInfo() { return errorMsg; }    
      
  /** 
   * 读EXCEL文件，获取信息集合 
   * @param fielName 
   * @return 
   */  
    public List<SystemEmpVO> getExcelInfo(MultipartFile mFile) {  
    	List<SystemEmpVO> userList = null;
        String fileName = mFile.getOriginalFilename();//获取文件名  
        try {  
            if (!validateExcel(fileName)) {// 验证文件名是否合格  
                return null;  
            }  
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本  
            if (isExcel2007(fileName)) {  
                isExcel2003 = false;  
            }  
            userList = createExcel(mFile.getInputStream(), isExcel2003);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return userList;  
    }  
    
  /** 
   * 根据excel里面的内容读取客户信息 
   * @param is 输入流 
   * @param isExcel2003 excel是2003还是2007版本 
   * @return 
   * @throws IOException 
   */  
    public List<SystemEmpVO> createExcel(InputStream is, boolean isExcel2003) {
    	List<SystemEmpVO> userList = null;
        try{  
            Workbook wb = null;  
            if (isExcel2003) {// 当excel是2003时,创建excel2003  
                wb = new HSSFWorkbook(is);  
            } else {// 当excel是2007时,创建excel2007  
                wb = new XSSFWorkbook(is);  
            }  
            userList = readExcelValue(wb);// 读取Excel里面客户的信息  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		return userList;  
    }  
    
  /** 
   * 读取Excel里面客户的信息 
   * @param wb 
   * @return 
   */  
    private List<SystemEmpVO> readExcelValue(Workbook wb) {  
        // 得到第一个shell  
        Sheet sheet = wb.getSheetAt(0);  
        // 得到Excel的行数  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
        // 得到Excel的列数(前提是有行数)  
        if (totalRows > 1 && sheet.getRow(0) != null) {  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        List<SystemEmpVO> userList = new ArrayList<SystemEmpVO>();  
        // 循环Excel行数  
        for (int r = 1; r < totalRows; r++) {  
            Row row = sheet.getRow(r);  
            if (row == null){  
                continue;  
            }  
            SystemEmpVO emp = new SystemEmpVO();
            // 循环Excel的列  
            for (int c = 0; c < this.totalCells; c++) {  
                Cell cell = row.getCell(c);  
                if (null != cell) {
                	if (c == 0) {
                		if(cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC || cell.getNumericCellValue()==0){
                			break;
                		}
                		emp.setXuHao(r);//姓名
                	}else if (c == 1) {
                    		emp.setName(cell.getStringCellValue());//姓名 
                    } else if (c == 2) {  
                            emp.setSex(cell.getStringCellValue());//性别  
                    } else if (c == 3){  
                    	emp.setCardid(cell.getStringCellValue());//身份证号码
                    } else if (c == 4){  
                    	emp.setBirthday(cell.getStringCellValue());//出生日期
                    }  else if (c == 5){  
                    	emp.setBirthPlace(cell.getStringCellValue());//出生地
                    }  else if (c == 6){  
                    	emp.setNation(cell.getStringCellValue());//民族
                    }  else if (c == 7){  
                    	emp.setNativePlace(cell.getStringCellValue());//籍贯
                    }  else if (c == 8){  
                    	emp.setPhone(cell.getStringCellValue());//联系电话
                    }  else if (c == 9){  
                    	emp.setAddress(cell.getStringCellValue());//联系地址
                    }  else if (c == 10){  
                    	emp.setEmail(cell.getStringCellValue());//邮箱
                    }  else if (c == 11){  
                    	emp.setStationId(cell.getStringCellValue());//岗位CODE
                    }  else if (c == 12){  
                    	emp.setJob(cell.getStringCellValue());//职务
                    }  else if (c == 13){  
                    	emp.setJobRank(cell.getStringCellValue());//职级
                    }  else if (c == 14){  
                    	emp.setPosition(cell.getStringCellValue());//职称
                    }  else if (c == 15){  
                    	emp.setUnitName(cell.getStringCellValue());//单位名称
                    }  else if (c == 16){  
                    	emp.setDeptName(cell.getStringCellValue());//部门名称
                    }  else if (c == 17){  
                    	emp.setEduRec(cell.getStringCellValue());//学历CODE
                    } else if (c == 18){  
                    	emp.setEduFirstRec(cell.getStringCellValue());//第一学历
                    }  else if (c == 19){ 
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String flag = String.valueOf(cell.getNumericCellValue());  
                            emp.setpStatus(flag.substring(0, flag.length()-2>0?flag.length()-2:1));//政治面貌
                        }else{  
                        	emp.setpStatus(cell.getStringCellValue());//政治面貌
                        } 
                    }  else if (c == 20){  
                    	emp.setEntryPartyDate(cell.getStringCellValue());//入党团时间
                    }  else if (c == 21){  
                    	emp.setWorkDate(cell.getStringCellValue());//参加工作时间
                    }  else if (c == 22){  
                    	emp.setEntryDate(cell.getStringCellValue());//入职时间
                    }  else if (c == 23){ 
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String flag = String.valueOf(cell.getNumericCellValue());  
                            emp.setStatus(flag.substring(0, flag.length()-2>0?flag.length()-2:1));//员工状态
                        }else{  
                        	emp.setStatus(cell.getStringCellValue());//员工状态
                        } 
                    }  else if (c == 24){  
                    	if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){  
                            String flag = String.valueOf(cell.getNumericCellValue());  
                            emp.setDaoRuFlag(flag.substring(0, flag.length()-2>0?flag.length()-2:1));//操作标志
                        }else{  
                        	emp.setDaoRuFlag(cell.getStringCellValue());//操作标志
                        } 
                    }  
                }  
            }  
            if(emp !=null&& emp.getXuHao() != 0){
            	 // 添加到list  
                userList.add(emp);  
            }
        }  
        return userList;  
    }  
      
    /** 
     * 验证EXCEL文件 
     *  
     * @param filePath 
     * @return 
     */  
    public boolean validateExcel(String filePath) {  
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;  
    }  
      
    // @描述：是否是2003的excel，返回true是2003   
    public static boolean isExcel2003(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xls)$");    
     }    
     
    //@描述：是否是2007的excel，返回true是2007   
    public static boolean isExcel2007(String filePath)  {    
         return filePath.matches("^.+\\.(?i)(xlsx)$");    
     }    
}