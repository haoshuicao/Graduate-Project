package swust.edu.cn.threeExaminations.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import swust.edu.cn.threeExaminations.model.ThreeCheckCount;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.ThreeCheckCountService;

@Controller
@RequestMapping("/threeCheckCountController")
public class ThreeCheckCountController {
	List<ThreeCheckCount> publicRecord = new ArrayList<ThreeCheckCount>();
	private AreaService areaService;
	public AreaService getAreaService() {
		return areaService;
	}

	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
	private void setPublicRecord(List<ThreeCheckCount> publicRecord) {
		this.publicRecord = publicRecord;
	}

	private ThreeCheckCountService threeCheckCountService;

	public ThreeCheckCountService getThreeCheckCountService() {
		return threeCheckCountService;
	}
	@Autowired
	public void setThreeCheckCountService(
			ThreeCheckCountService threeCheckCountService) {
		this.threeCheckCountService = threeCheckCountService;
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/LookStandardReport")
	public ModelAndView LookStandardReport(String year,String patch,String vallige,String peopleClass,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			
			List<ThreeCheckCount> record = new ArrayList<ThreeCheckCount>();
			
			if(peopleClass.equals("全部人口普查率，计外补救率，妇科病率")){
				peopleClass = "全部";
			}
			else if(peopleClass.equals("常住人口普查率，计外补救率，妇科病率")){
				peopleClass = "常住";
			}else if(peopleClass.equals("流入人口普查率，计外补救率，妇科病率")){
				peopleClass = "流入";
			}else if(peopleClass.equals("流出人口普查率，计外补救率，妇科病率")){
				peopleClass = "流出";
			}
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			int id=user.getUserAreaid();
			String level = user.getUserLevel();
			String nowArea = areaService.getAreaNameByAreaId(id);
			if(vallige.equals("全部") && peopleClass.equals("全部")){
				record=threeCheckCountService.searchBySelect1(id,level,year,patch);
				setPublicRecord(record);
			}
			else if(!vallige.equals("全部") && peopleClass.equals("全部")){
				record=threeCheckCountService.searchBySelect2(id,level,year,patch,vallige);
				setPublicRecord(record);
			}
			else if(vallige.equals("全部") && (!peopleClass.equals("全部"))){
				record=threeCheckCountService.searchBySelect3(id,level,year,patch,peopleClass);
				setPublicRecord(record);
			}
			else{
				record=threeCheckCountService.searchBySelect4(id,level,year,patch,vallige,peopleClass);
				setPublicRecord(record);
			}
			String[] title = {"单位","实查总人次","应查总人次","普查率"}; 
            long start = System.currentTimeMillis();     
            String fileName="standardReport";  
            String filePath = request.getSession().getServletContext().getRealPath("upload/"+fileName+".xls");     
            WritableWorkbook wwb;     
            OutputStream os = new FileOutputStream(filePath);
            
            wwb=Workbook.createWorkbook(os);      
            WritableSheet sheet = wwb.createSheet("标准统计报表", 0); 
            
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
            wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf_title.setAlignment(Alignment.CENTRE);
            wcf_title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
            wcf_title.setBackground(jxl.format.Colour.BRIGHT_GREEN);
            WritableFont wf_head = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
            wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf_head.setAlignment(Alignment.CENTRE);
            wcf_head.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
            
            WritableFont wf = new WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE);
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
            
            sheet.setColumnView(0, 25);   
            sheet.setColumnView(1, 25);   
            sheet.setColumnView(2, 25);   
            sheet.setColumnView(3, 25);   
           
            Label label;     
            for(int i=0;i<title.length;i++){     
               label = new Label(i,1,title[i],wcf_head);     
              sheet.addCell(label);  
            }
       
            for(int i = 0;i<record.size();i++){
            	label = new Label(0,i+2,record.get(i).getThccVillage(),wcf);     
                sheet.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(1,i+2,record.get(i).getThccHavechecknumber().toString(),wcf);     
                sheet.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(2,i+2,record.get(i).getThccShouldchecknumber().toString(),wcf);     
                sheet.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	double a=Double.parseDouble(record.get(i).getThccHavechecknumber()+"");
            	double b=Double.parseDouble(record.get(i).getThccShouldchecknumber()+"");
            	double s = a*100/b;

            	DecimalFormat df2 = new DecimalFormat("###.00");
            	String s1= df2.format(s);
            	label = new Label(3,i+2,s1+"%",wcf);   
                sheet.addCell(label);
                        
                sheet.mergeCells(0,0,3,0);     
                label = new Label(0,0,year+patch+"标准统计报表",wcf_title);                  
                sheet.addCell(label);  
            }                
            wwb.write();     
            wwb.close(); 
			String[] title5 = {"单位","应查人数","实查人数","患妇科病数","计外孕人数","已补救人数","普查率","计外孕补救率","妇科病率"}; 
            long start5 = System.currentTimeMillis();     
            String fileName1="characterReport"; 
            String filePath5 = request.getSession().getServletContext().getRealPath("upload/"+fileName1+".xls");;     
            WritableWorkbook wwb5;     
            OutputStream os5 = new FileOutputStream(filePath5);     
            wwb5=Workbook.createWorkbook(os5);      
            WritableSheet sheet5 = wwb5.createSheet("特征统计报表", 0);   
            
            WritableFont wf_title5 = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf_title5 = new WritableCellFormat(wf_title5);
            wcf_title5.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf_title5.setAlignment(Alignment.CENTRE);
            wcf_title5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
            wcf_title5.setBackground(jxl.format.Colour.BRIGHT_GREEN);
            WritableFont wf_head5 = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf_head5 = new WritableCellFormat(wf_head5);
            wcf_head5.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf_head5.setAlignment(Alignment.CENTRE);
            wcf_head5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
            
            WritableFont wf5 = new WritableFont(WritableFont.ARIAL,11,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
            WritableCellFormat wcf5 = new WritableCellFormat(wf5);
            wcf5.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf5.setAlignment(Alignment.CENTRE);
            wcf5.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
            
            sheet5.setColumnView(0, 20);   
            sheet5.setColumnView(1, 20);  
            sheet5.setColumnView(2, 20);  
            sheet5.setColumnView(3, 20); 
            sheet5.setColumnView(4, 20);  
            sheet5.setColumnView(5, 20); 
            sheet5.setColumnView(6, 20);   
            sheet5.setColumnView(7, 20);   
            sheet5.setColumnView(8, 20);   
            
            Label label5;     
            for(int i=0;i<title5.length;i++){     
               label5 = new Label(i,1,title5[i],wcf_head5);     
              sheet5.addCell(label5);  
            }
            for(int i = 0;i<record.size();i++){
            	label5 = new Label(0,i+2,record.get(i).getThccVillage(),wcf5);     
                sheet5.addCell(label5);
            }
          
            for(int i = 0;i<record.size();i++){
            	label5 = new Label(1,i+2,record.get(i).getThccShouldchecknumber().toString(),wcf5);     
                sheet5.addCell(label5);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(2,i+2,record.get(i).getThccHavechecknumber().toString(),wcf5);     
                sheet5.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(3,i+2,record.get(i).getThccGymornumber().toString(),wcf5);     
                sheet5.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(4,i+2,record.get(i).getThccOutplanprenumber().toString(),wcf5);     
                sheet5.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
            	label = new Label(5,i+2,record.get(i).getThccRemedynumber().toString(),wcf5);     
                sheet5.addCell(label);
            }
            for(int i = 0;i<record.size();i++){
                double a=Double.parseDouble(record.get(i).getThccHavechecknumber()+"");
            	double b=Double.parseDouble(record.get(i).getThccShouldchecknumber()+"");
            	double s = a*100/b;

            	DecimalFormat df2 = new DecimalFormat("###.00");
            	String s1= df2.format(s);
            	label5 = new Label(6,i+2,s1+"%",wcf5);   
                sheet5.addCell(label5);
            }    
            for(int i = 0;i<record.size();i++){
                
                double a=Double.parseDouble(record.get(i).getThccRemedynumber()+"");
            	double b=Double.parseDouble(record.get(i).getThccOutplanprenumber()+"");
            	double s = a*100/b;

            	DecimalFormat df2 = new DecimalFormat("###.00");
            	String s1= df2.format(s);
            	label5 = new Label(7,i+2,s1+"%",wcf5);   
                sheet5.addCell(label5);
            } 
            for(int i = 0;i<record.size();i++){
                double a=Double.parseDouble(record.get(i).getThccGymornumber()+"");
            	double b=Double.parseDouble(record.get(i).getThccHavechecknumber()+"");
            	double s = a*100/b;

            	DecimalFormat df2 = new DecimalFormat("###.00");
            	String s1= df2.format(s);
            	label5 = new Label(8,i+2,s1+"%",wcf5);   
                sheet5.addCell(label5);
            } 
            
            sheet5.mergeCells(0,0,8,0);     
            label5 = new Label(0,0,year+patch+"特征统计报表",wcf_title5);                  
            sheet5.addCell(label5); 
            wwb5.write();     
            wwb5.close(); 
            
            int recordcount = record.size();
            int pageCount;
            int temp=recordcount%8;
            if(temp==0){
            	pageCount = recordcount/8;
            }else{
            	pageCount = recordcount/8 +1;
            }
             
            List<ThreeCheckCount> recordFirst = new ArrayList<ThreeCheckCount>();
			int max = 0;
			int page = 1;
			max = publicRecord.size()<(8*page)?publicRecord.size():(8*page);
			
			for(int i = (page-1)*8;i<max;i++){
				recordFirst.add(publicRecord.get(i));
			}
			int noRecord;
			if(recordFirst.isEmpty()){
				noRecord=1;
				map.put("noRecord", noRecord);
			}else{
				noRecord=0;
				map.put("noRecord", noRecord);
			}
			
            map.put("recordFirst", recordFirst);
            map.put("record", publicRecord);
            map.put("nowArea", nowArea);
            map.put("pageCount", pageCount);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
			
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！!!!!");
			e.printStackTrace();
		}finally{
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/findRecordByPage")
	public ModelAndView findRecordByPage(int page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<ThreeCheckCount> recordCurrent = new ArrayList<ThreeCheckCount>();
			int max = 0;
			max = publicRecord.size()<(8*page)?publicRecord.size():(8*page);
			
			for(int i = (page-1)*8;i<max;i++){
				recordCurrent.add(publicRecord.get(i));
			}
				map.put("recordCurrent", recordCurrent);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
			
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		}finally{
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
}
