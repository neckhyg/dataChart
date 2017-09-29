package io.renren.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.entity.ConferenceUserEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.StudentCardEntity;
import io.renren.service.StudentCardService;

import io.renren.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author neckhyg
 * @email 497273569@qq.com
 * @date 2017-09-26 15:16:32
 */
@RestController
@RequestMapping("studentcard")
public class StudentCardController {
	@Autowired
	private StudentCardService studentCardService;

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("studentcard:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StudentCardEntity> studentCardList = studentCardService.queryList(query);
		int total = studentCardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(studentCardList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{stuno}")
	@RequiresPermissions("studentcard:info")
	public R info(@PathVariable("stuno") String stuno){
		StudentCardEntity studentCard = studentCardService.queryObject(stuno);
		
		return R.ok().put("studentCard", studentCard);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("studentcard:save")
	public R save(@RequestBody StudentCardEntity studentCard){
		studentCardService.save(studentCard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("studentcard:update")
	public R update(@RequestBody StudentCardEntity studentCard){
		studentCardService.update(studentCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("studentcard:delete")
	public R delete(@RequestBody String[] stunos){
		studentCardService.deleteBatch(stunos);
		
		return R.ok();
	}
    /**
     @RequestMapping(value = "exportfeedback")
     @ResponseBody

      * 导出excel
     */
    @ResponseBody
    @RequestMapping("/export")
    @RequiresPermissions("studentcard:export")
    public R exportExcel(@RequestParam Map<String, Object> params)      {


        String fileName = "无锡工艺学生信息"+System.currentTimeMillis()+".xls"; //文件名
        String sheetName = "无锡工艺学生信息";//sheet名

        String []title = new String[]{"学号","姓名","卡号","院系","专业","性别","班级"};//标题

        Map<String, Object> map = new HashMap<>();
        String  className =  (String)params.get("classname") ;
        map.put("classname", className);


        List<StudentCardEntity> list = studentCardService.queryList(map);

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int total = studentCardService.queryTotal(map);

        String [][]values = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
            StudentCardEntity obj = list.get(i);
            values[i][0] = obj.getStuno();
            values[i][1] = obj.getStuname();
            values[i][2] = obj.getStucardid();
            values[i][3] = obj.getDepartment();
            values[i][4] = obj.getMajor();
            values[i][5] = obj.getGender();
            values[i][6] =  obj.getClassname();
//            values[i][7] =  obj.getTestType();

        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);

        //将文件存到指定位置
        try {

            this.setResponseHeader( fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
//           doExportExcel(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return JResponse.success("ok");
        return R.ok();
    }

    public void setResponseHeader( String fileName) {
;
        try {
            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/import")
    @RequiresPermissions("studentcard:import")
    public R importExcel2Db()    throws IOException {
//        List temp = new ArrayList();
        String   filename  = "F:\\temp\\student_tiyu.xls"  ;
        List<StudentCardEntity> list =new ArrayList<StudentCardEntity>();
        try{
            File file=new File(filename);
            FileInputStream fileIn = new FileInputStream(file);
//根据指定的文件输入流导入Excel从而产生Workbook对象
            HSSFWorkbook wb0 = new HSSFWorkbook(fileIn);

//        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new File("F:/tt.xls")));
//获取Excel文档中的第一个表单
            HSSFSheet hssfSheet = wb0.getSheetAt(0);
//对Sheet中的每一行进行迭代
//        for (HSSFRow r : sht0) {
            int totalRows = hssfSheet.getPhysicalNumberOfRows();
            int    totalCells =0;
            int  currentCol =0 ;
            /** 得到Excel的列数 */
            if (totalRows >= 1 && hssfSheet.getRow(0) != null) {
                totalCells = hssfSheet.getRow(0).getPhysicalNumberOfCells();
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)  {
                HSSFRow r = hssfSheet.getRow(rowNum);
                if (r != null) {

                    StudentCardEntity info=new StudentCardEntity();
//取出当前行第1个单元格数据，并封装在info实体stuName属性上
                    for (currentCol = 0; currentCol < totalCells; currentCol++) {
                        HSSFCell cell = r.getCell(currentCol);
                        String cellValue = "";
                        if (null != cell) {
                            // 以下是判断数据的类型
                            switch (cell.getCellType()) {
                                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                                    cellValue = cell.getNumericCellValue() + "";
                                    break;
                                case HSSFCell.CELL_TYPE_STRING: // 字符串
                                    cellValue = cell.getStringCellValue();
                                    break;
                                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                                    cellValue = cell.getBooleanCellValue() + "";
                                    break;
                                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                                    cellValue = cell.getCellFormula() + "";
                                    break;
                                case HSSFCell.CELL_TYPE_BLANK: // 空值
                                    cellValue = "";
                                    break;
                                case HSSFCell.CELL_TYPE_ERROR: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }
                        }
                        switch(currentCol) {
                            case 0:
                                info.setStuno(cellValue);
                                break;
                            case 1:
                                info.setStuname(cellValue);
                                break;
                            case 2:
                                info.setStucardid(cellValue);
                                break;
                            case 3:
                                info.setDepartment(cellValue);
                                break;
                            case 4:
                                info.setMajor(cellValue);
                                break;
                            case 5:
                                info.setGender(cellValue);
                                break;
                            case 6:
                                info.setClassname(cellValue);
                                break;
//                            case 7:
//                                info.setRoom(cellValue);
//                                break;
//                            case 8:
//                                info.setTransport(cellValue);
//                                break;
//                            case 9:
////                                info.setArriveDate(cellValue);
//                                break;
//                            case 10:
//                                info.setTestType(cellValue);
//                                break;

                            default:
                                break;
                        }

                    }
                    list.add(info);
                }
            }
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
//            e.printStackTrace();
            return R.error("未找到指定路径的文件!");
        }


        for(int i=0;i<list.size();i++){

            StudentCardEntity studentCardEntity  = list.get(i);
//            conferenceUserService.save(conferenceUserEntity);
            String stuno=studentCardEntity.getStuno();
            if ( studentCardService.queryObjectByStuno(stuno) ==null ) {
                studentCardService.save(studentCardEntity);
            }else {
                studentCardService.update(studentCardEntity);
            }
        }
        return R.ok();
    }
}
