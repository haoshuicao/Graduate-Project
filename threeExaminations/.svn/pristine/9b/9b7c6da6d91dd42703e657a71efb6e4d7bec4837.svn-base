package swust.edu.cn.threeExaminations.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.ThreeCheckServiceService;


@Controller
@RequestMapping("/uploadDownloadController")
public class UploadDownloadController {
	private ThreeCheckServiceService threeCheckServiceService;
	private AreaService areaService;
	public AreaService getAreaService() {
		return areaService;
	}

	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public ThreeCheckServiceService getThreeCheckServiceService() {
		return threeCheckServiceService;
	}

	@Autowired
	public void setThreeCheckServiceService(
			ThreeCheckServiceService threeCheckServiceService) {
		this.threeCheckServiceService = threeCheckServiceService;
	}

	/**
	 * 上传服务记录
	 * @param postfile
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping(value="/uplaodServerRecord",method=RequestMethod.POST) 
	public ModelAndView uplaodServerRecord(MultipartFile postfile,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			//业务逻辑
			//String oriFileName=postfile.getOriginalFilename();
			String newFileName=System.currentTimeMillis()+"";
			System.out.println(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls"));
			postfile.transferTo(new File(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls")));
			 //实例化一个工作簿对象  
			  Workbook workBook=Workbook.getWorkbook(new File(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls")));  
	          //获取该工作表中的第一个工作表   
	          Sheet sheet=workBook.getSheet(0);  
	          //获取该工作表的行数，以供下面循环使用   
	          int rowSize=sheet.getRows();  
	          List<String> yearList=new ArrayList<String>();
	          List<String> batchList=new ArrayList<String>();
	          List<String> nowLiveVillageList=new ArrayList<String>();//现居住地为村列表
	          long readFileTime = 0;
	          long importSqlTime = 0;
	          int count = 0;
	          for(int i=1;i<rowSize;i++)  
	          {  
	        	  long t1 = System.currentTimeMillis();
	              //身份证号   
	              String idNumber=sheet.getCell(0,i).getContents();  
	              //姓名   
	              String name=sheet.getCell(1,i).getContents();  
	              //户籍地   
	              String householdRegister=sheet.getCell(2,i).getContents();             
	              //现居住省   
	              String nowLiveProvince=sheet.getCell(3,i).getContents();  
	              //现居住市  
	              String nowLiveCity=sheet.getCell(4,i).getContents();  
	              //现居住区县   
	              String nowLiveCounty = sheet.getCell(5,i).getContents();  
	              //现居住镇   
	              String nowLiveTown=sheet.getCell(6,i).getContents();  
	              //现居住村   
	              String nowLiveVillage=sheet.getCell(7,i).getContents(); 
	              //nowLiveVillageList.add(nowLiveVillage);
	              //居住状况  
	              String liveState=sheet.getCell(8,i).getContents();  
	              //是否列入检查  
	              String isExistCheckList=sheet.getCell(9,i).getContents();  
	              //检查状态   
	              String checkState=sheet.getCell(10,i).getContents();  
	              //检查日期  
	              String checkTime=sheet.getCell(11,i).getContents();
	              //所属年度   
	              String year=sheet.getCell(12,i).getContents();
	              //yearList.add(year);
	              //所属批次 
	              String batch=sheet.getCell(13,i).getContents(); 
	              //batchList.add(batch);
	              //未查原因
	              String noCheckReason=sheet.getCell(14,i).getContents();  
	              //检查建议 
	              String checkSuggest=sheet.getCell(15,i).getContents();  
	              //病 
	              String disease=sheet.getCell(16,i).getContents();  
	              //环 
	              String hoop=sheet.getCell(17,i).getContents();
	              //孕 
	              String pregnancy=sheet.getCell(18,i).getContents();
	              //是否补救 
	              String isRemedy=sheet.getCell(19,i).getContents();
	              //检查地
	              String cheakPlace=sheet.getCell(20,i).getContents();
	              //是否寄回三查情况
	              String threeCheckServicecol=sheet.getCell(21,i).getContents();
	              long t2 = System.currentTimeMillis();
	              long t3 = t2-t1;
	              readFileTime += t3;
	              HttpSession session = request.getSession();
	  			  User user = new User();
	  			  user = (User) session.getAttribute("user");
	  			  int areaId = user.getUserAreaid();
	  			  
	  			  long t5 = System.currentTimeMillis();
	  			  
	  			  String userAreaString = (String) session.getAttribute("areaName");//从省到镇的登录用户用户地区
	  			  String userAreaString1 = nowLiveProvince+nowLiveCity+nowLiveCounty+nowLiveTown;//从省到镇记录现居住地
	  			  String userAreaString2 = householdRegister;//从省到村的记录户籍地
	  			  //当前登录用户只能上传本地的服务记录，即现居住地为本地（常住和流入），或者户籍地为本地（常住和流出）
	  			  if(userAreaString1.equals(userAreaString) && (liveState.equals("常住")||liveState.equals("流入"))){//现居住地为本地
	  				threeCheckServiceService.importlist(areaId,userAreaString,idNumber,name,householdRegister,nowLiveProvince,nowLiveCity,nowLiveCounty,nowLiveTown,nowLiveVillage,
		            		  liveState,isExistCheckList,checkState,checkTime,year,batch,
		            		  noCheckReason,checkSuggest,disease,hoop,pregnancy,isRemedy,
		            		  cheakPlace,threeCheckServicecol);
	  				nowLiveVillageList.add(nowLiveVillage);//将现居住地的村添加到当前用户的村列表
	  				yearList.add(year);
	  				batchList.add(batch);
	  				count++;
	  			  }else if(userAreaString2.contains(userAreaString) && liveState.equals("流出")){//户籍地为本地
	  				threeCheckServiceService.importlist(areaId,userAreaString,idNumber,name,householdRegister,nowLiveProvince,nowLiveCity,nowLiveCounty,nowLiveTown,nowLiveVillage,
		            		  liveState,isExistCheckList,checkState,checkTime,year,batch,
		            		  noCheckReason,checkSuggest,disease,hoop,pregnancy,isRemedy,
		            		  cheakPlace,threeCheckServicecol);
	  				nowLiveVillageList.add(householdRegister.replace(userAreaString, ""));//将户籍地的村添加到当前用户的村列表
	  				//System.out.println(householdRegister.replace(userAreaString, "")+"=======");
	  				yearList.add(year);
	  				batchList.add(batch);
	  				count++;
	  			  }
	            long t6 = System.currentTimeMillis();
	            long t7 = t6-t5;
	            importSqlTime += t7;
	          }
	          System.out.println("读取Excel文件时间："+readFileTime);
	          System.out.println("导入数据库时间："+importSqlTime);
	          
	          //对上传的年度进行去重
              List<String> tempYearList= new ArrayList<String>();  
              for(String i:yearList){  
                  if(!tempYearList.contains(i)){  
                	  tempYearList.add(i);  
                  }  
              }  
              //对上传的批次信息进行去重
              List<String> tempBatchList= new ArrayList<String>();  
              for(String i:batchList){  
                  if(!tempBatchList.contains(i)){  
                	  tempBatchList.add(i);  
                  }  
              }  
              //对上传的现居住村进行去重
              List<String> tempNowLiveVillageList= new ArrayList<String>();  
              for(String i:nowLiveVillageList){  
                  if(!tempNowLiveVillageList.contains(i)){  
                	  tempNowLiveVillageList.add(i);  
                  }  
              }  
       
              map.put("tempYearList", tempYearList);
          	  map.put("tempBatchList", tempBatchList);
          	  map.put("tempNowLiveVillageList", tempNowLiveVillageList);
          	  map.put("count", count);
              map.put("result", Boolean.TRUE);
	          	  int rowSize1 = rowSize-1;
	          	  int sub;
	          	  if(count < rowSize1){
	          		  sub = rowSize1-count;
		              map.put("message", "上传成功"+count+"条记录，失败"+sub+"条记录，由于现居住地和户籍地都不是当前所属地区！");
	          	  }else{
	          		 map.put("message", "上传成功！");
	          	  }
	          } catch (Exception e) {
	  			map.put("result", Boolean.FALSE);
	  			map.put("message", "执行出现出错！!!!!");
	  			e.printStackTrace();
	  		}finally{
	  			view.setAttributesMap(map);
	  			mav.setView(view);
	  			mav.toString();
	  			return mav;
	  		}
		}
	/**
	 * 下载检索的服务记录
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/download",method=RequestMethod.GET)  
    public void downloadFile(Integer id,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
			
		//System.out.println(id);
		//封装下载
		 // path是指欲下载的文件的路径。
		
		String path=request.getSession().getServletContext().getRealPath("upload/serviceRecordList.xls");
        System.out.println(path);
		File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	}
	
	/**
	 * 下载标准统计报表
	 * @param id
	 * @param year
	 * @param patch
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/download1",method=RequestMethod.GET)  
    public void downloadFile1(Integer id,String year,String patch,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
			
		//System.out.println(id);
		//封装下载
		 // path是指欲下载的文件的路径。
			
		String path=request.getSession().getServletContext().getRealPath("upload/standardReport.xls");
        System.out.println(path);
		File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	}
	/**
	 * 下载特征统计报表
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/download2",method=RequestMethod.GET)  
    public void downloadFile2(Integer id,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
			
		//System.out.println(id);
		//封装下载
		 // path是指欲下载的文件的路径。
		
		String path=request.getSession().getServletContext().getRealPath("upload/characterReport.xls");
        System.out.println(path);
		File file = new File(path);
        // 取得文件名。
        String filename = file.getName();
        // 取得文件的后缀名。
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();
        // 设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	}
}
