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

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping(value="/uplaodServerRecord",method=RequestMethod.POST) 
	public ModelAndView uplaodServerRecord(MultipartFile postfile,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			String newFileName=System.currentTimeMillis()+"";
			System.out.println(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls"));
			postfile.transferTo(new File(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls")));
			  Workbook workBook=Workbook.getWorkbook(new File(request.getSession().getServletContext().getRealPath("upload/"+newFileName+".xls")));  
	          Sheet sheet=workBook.getSheet(0);  
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
	              String idNumber=sheet.getCell(0,i).getContents();  
	              String name=sheet.getCell(1,i).getContents();  
	              String householdRegister=sheet.getCell(2,i).getContents();             
	              String nowLiveProvince=sheet.getCell(3,i).getContents();  
	              String nowLiveCity=sheet.getCell(4,i).getContents();  
	              String nowLiveCounty = sheet.getCell(5,i).getContents();  
	              String nowLiveTown=sheet.getCell(6,i).getContents();  
	              String nowLiveVillage=sheet.getCell(7,i).getContents(); 
	              String liveState=sheet.getCell(8,i).getContents();  
	              String isExistCheckList=sheet.getCell(9,i).getContents();  
	              String checkState=sheet.getCell(10,i).getContents();  
	              String checkTime=sheet.getCell(11,i).getContents();
	              String year=sheet.getCell(12,i).getContents();
	              String batch=sheet.getCell(13,i).getContents(); 
	              String noCheckReason=sheet.getCell(14,i).getContents();  
	              String checkSuggest=sheet.getCell(15,i).getContents();  
	              String disease=sheet.getCell(16,i).getContents();  
	              String hoop=sheet.getCell(17,i).getContents();
	              String pregnancy=sheet.getCell(18,i).getContents();
	              String isRemedy=sheet.getCell(19,i).getContents();
	              String cheakPlace=sheet.getCell(20,i).getContents();
	              String threeCheckServicecol=sheet.getCell(21,i).getContents();
	              long t2 = System.currentTimeMillis();
	              long t3 = t2-t1;
	              readFileTime += t3;
	              HttpSession session = request.getSession();
	  			  User user = new User();
	  			  user = (User) session.getAttribute("user");
	  			  int areaId = user.getUserAreaid();
	  			  long t5 = System.currentTimeMillis();
	  			  String userAreaString = (String) session.getAttribute("areaName");
	  			  String userAreaString1 = nowLiveProvince+nowLiveCity+nowLiveCounty+nowLiveTown;
	  			  String userAreaString2 = householdRegister;
	  			  if(userAreaString1.equals(userAreaString) && (liveState.equals("常住")||liveState.equals("流入"))){
	  				threeCheckServiceService.importlist(areaId,userAreaString,idNumber,name,householdRegister,nowLiveProvince,nowLiveCity,nowLiveCounty,nowLiveTown,nowLiveVillage,
		            		  liveState,isExistCheckList,checkState,checkTime,year,batch,
		            		  noCheckReason,checkSuggest,disease,hoop,pregnancy,isRemedy,
		            		  cheakPlace,threeCheckServicecol);
	  				nowLiveVillageList.add(nowLiveVillage);
	  				yearList.add(year);
	  				batchList.add(batch);
	  				count++;
	  			  }else if(userAreaString2.contains(userAreaString) && liveState.equals("流出")){//户籍地为本地
	  				threeCheckServiceService.importlist(areaId,userAreaString,idNumber,name,householdRegister,nowLiveProvince,nowLiveCity,nowLiveCounty,nowLiveTown,nowLiveVillage,
		            		  liveState,isExistCheckList,checkState,checkTime,year,batch,
		            		  noCheckReason,checkSuggest,disease,hoop,pregnancy,isRemedy,
		            		  cheakPlace,threeCheckServicecol);
	  				nowLiveVillageList.add(householdRegister.replace(userAreaString, ""));//将户籍地的村添加到当前用户的村列表
	  				yearList.add(year);
	  				batchList.add(batch);
	  				count++;
	  			  }
	            long t6 = System.currentTimeMillis();
	            long t7 = t6-t5;
	            importSqlTime += t7;
	          }
              List<String> tempYearList= new ArrayList<String>();  
              for(String i:yearList){  
                  if(!tempYearList.contains(i)){  
                	  tempYearList.add(i);  
                  }  
              }  
              List<String> tempBatchList= new ArrayList<String>();  
              for(String i:batchList){  
                  if(!tempBatchList.contains(i)){  
                	  tempBatchList.add(i);  
                  }  
              }  
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
	@RequestMapping(value="/download",method=RequestMethod.GET)  
    public void downloadFile(Integer id,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
		String path=request.getSession().getServletContext().getRealPath("upload/serviceRecordList.xls");
        System.out.println(path);
		File file = new File(path);
        String filename = file.getName();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
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
	@RequestMapping(value="/download1",method=RequestMethod.GET)  
    public void downloadFile1(Integer id,String year,String patch,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
		String path=request.getSession().getServletContext().getRealPath("upload/standardReport.xls");
        System.out.println(path);
		File file = new File(path);
        String filename = file.getName();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
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
	@RequestMapping(value="/download2",method=RequestMethod.GET)  
    public void downloadFile2(Integer id,HttpServletRequest request,
            HttpServletResponse response) throws IOException {
		try {
		String path=request.getSession().getServletContext().getRealPath("upload/characterReport.xls");
        System.out.println(path);
		File file = new File(path);
        String filename = file.getName();
        String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
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
