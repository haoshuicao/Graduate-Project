package swust.edu.cn.threeExaminations.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
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

import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.ThreeCheckCountService;
import swust.edu.cn.threeExaminations.service.ThreeCheckServiceService;
import swust.edu.cn.threeExaminations.service.UserService;

@Controller
@RequestMapping("/threeCheckServiceController")
public class ThreeCheckServiceController {

	List<ThreeCheckServiceWithBLOBs> publicRecord = new ArrayList<ThreeCheckServiceWithBLOBs>();

	private void setPublicRecord(List<ThreeCheckServiceWithBLOBs> publicRecord) {
		this.publicRecord = publicRecord;
	}

	private ThreeCheckServiceService threeCheckServiceService;

	public ThreeCheckServiceService getThreeCheckServiceService() {
		return threeCheckServiceService;
	}

	@Autowired
	public void setThreeCheckServiceService(
			ThreeCheckServiceService threeCheckServiceService) {
		this.threeCheckServiceService = threeCheckServiceService;
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

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private AreaService areaService;

	public AreaService getAreaService() {
		return areaService;
	}

	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/searchThreeList")
	public ModelAndView searchThreeList(String townName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			System.out.println(townName);
			List<ThreeCheckServiceWithBLOBs> threeCheckServiceList = new ArrayList<ThreeCheckServiceWithBLOBs>();
			threeCheckServiceList = threeCheckServiceService
					.searchByTownName(townName);
			map.put("threeCheckServiceList", threeCheckServiceList);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！!!!!");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showNewRecordByIdCard")
	public ModelAndView showNewRecordByIdCard(String womanIdCard,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			String areaName = (String) session.getAttribute("areaName");
			if(user == null){
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出登录！");
			} else {
				ThreeCheckServiceWithBLOBs record = null;
				Area area = null;
				area = areaService.getAreaByAreaId(user.getUserAreaid());
				String level = user.getUserLevel();
				record = threeCheckServiceService.findNewRecordByIdCard(
						womanIdCard, level, area.getAreaName(), areaName);
				if (record != null) {
					String name = record.getThcsName();
					map.put("name", name);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
					map.put("record", record);
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "本地不存在该妇女的服务记录！");
				}
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showRecordByIdCard")
	public ModelAndView showRecordByIdCard(String womanIdCard,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			String areaName = (String) session.getAttribute("areaName");
			if(user ==null){
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出登录！");
			} else {
				System.out.println(womanIdCard);
				Area area = areaService.getAreaByAreaId(user.getUserAreaid());
				String level = user.getUserLevel();
				List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
				record = threeCheckServiceService.findRecordByIdCard(
						womanIdCard, level, area.getAreaName(), areaName);
				if (record.isEmpty()) {
					map.put("result", Boolean.FALSE);
					map.put("message", "本地不存在该妇女的服务记录！");
				} else {
					int index = record.size() - 1;
					String name = record.get(index).getThcsName();
					map.put("name", name);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
					map.put("record", record);
				}
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked", "unused" })
	@RequestMapping("/searchServiceRecordBySelect")
	public ModelAndView searchServiceRecordBySelect(String year, String patch,
			String areaName, String peopleClass, String characterSelect,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if(user == null){
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出登录！");
			}else{
			int id = user.getUserAreaid();
			String level = user.getUserLevel();
			
			List<ThreeCheckServiceWithBLOBs> record = new ArrayList<ThreeCheckServiceWithBLOBs>();
			if (peopleClass.equals("全部") && (!characterSelect.equals("全部"))
					&& (!areaName.equals("全部"))) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				record1 = threeCheckServiceService
						.findRecordBySelectCondition1(id, level, year, patch,
								areaName, characterSelect);
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut5(id, level, year,
								patch, areaName, characterSelect);
				record.addAll(record1);
				record.addAll(record2);
				setPublicRecord(record);
			}
			else if (peopleClass.equals("全部") && characterSelect.equals("全部")
					&& (!areaName.equals("全部"))) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				record1 = threeCheckServiceService
						.findRecordBySelectCondition2(id, level, year, patch,
								areaName);
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut6(id, level, year,
								patch, areaName);
				record.addAll(record1);
				record.addAll(record2);
				setPublicRecord(record);
			} 
			else if (peopleClass.equals("全部") && characterSelect.equals("全部")
					&& areaName.equals("全部")) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				record1 = threeCheckServiceService
						.findRecordBySelectCondition3(id, level, year, patch);
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut7(id, level, year, patch);
				record.addAll(record1);
				record.addAll(record2);
				setPublicRecord(record);
			} 
			else if (!peopleClass.equals("全部")
					&& characterSelect.equals("全部") && areaName.equals("全部")) {
				if (peopleClass.equals("流出")) {
					record = threeCheckServiceService
							.findRecordBySelectConditionOut2(id, level, year,
									patch, peopleClass);
					setPublicRecord(record);
				} else {
					record = threeCheckServiceService
							.findRecordBySelectCondition4(id, level, year,
									patch, peopleClass);
					setPublicRecord(record);
				}
			} 
			else if (!peopleClass.equals("全部")
					&& (!characterSelect.equals("全部")) && areaName.equals("全部")) {
				if (peopleClass.equals("流出")) {
					record = threeCheckServiceService
							.findRecordBySelectConditionOut3(id, level, year,
									patch, peopleClass, characterSelect);
					setPublicRecord(record);
				} else {
					record = threeCheckServiceService
							.findRecordBySelectCondition5(id, level, year,
									patch, peopleClass, characterSelect);
					setPublicRecord(record);
				}
			} 
			else if (!peopleClass.equals("全部")
					&& (!characterSelect.equals("全部"))
					&& (!areaName.equals("全部"))) {
				if (peopleClass.equals("流出")) {
					record = threeCheckServiceService
							.findRecordBySelectConditionOut4(id, level, year,
									patch, areaName, peopleClass,
									characterSelect);
					setPublicRecord(record);
				} else {
					record = threeCheckServiceService
							.findRecordBySelectCondition6(id, level, year,
									patch, areaName, peopleClass,
									characterSelect);
					setPublicRecord(record);
				}
			}
			else if (peopleClass.equals("全部")
					&& (!characterSelect.equals("全部")) && areaName.equals("全部")) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				record1 = threeCheckServiceService
						.findRecordBySelectCondition7(id, level, year, patch,
								characterSelect);
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut8(id, level, year,
								patch, characterSelect);
				record.addAll(record1);
				record.addAll(record2);
				setPublicRecord(record);
			} 
			else {
				if (peopleClass.equals("流出")) {
					record = threeCheckServiceService
							.findRecordBySelectConditionOut1(id, level, year,
									patch, areaName, peopleClass);
					setPublicRecord(record);
				} else {
					record = threeCheckServiceService
							.findRecordBySelectCondition8(id, level, year,
									patch, areaName, peopleClass);
					System.out.println("常住"+record.size());
					setPublicRecord(record);
				}
			}
			
			String[] title = { "身份证", "姓名", "环", "孕", "病", "检查时间", "建议",
					"是否补救", "户籍", "检查地" };
			long start = System.currentTimeMillis();
			String fileName = "serviceRecordList";
			String filePath = request.getSession().getServletContext()
					.getRealPath("upload/" + fileName + ".xls");
			WritableWorkbook wwb;
			OutputStream os = new FileOutputStream(filePath);
			wwb = Workbook.createWorkbook(os);
			WritableSheet sheet = wwb.createSheet("三查服务记录", 0);

			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_title.setAlignment(Alignment.CENTRE);
			wcf_title.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框
			wcf_title.setBackground(jxl.format.Colour.BRIGHT_GREEN);
			WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
			wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_head.setAlignment(Alignment.CENTRE);
			wcf_head.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框

			sheet.setColumnView(0, 22); 
			sheet.setColumnView(1, 15); 
			sheet.setColumnView(2, 5); 
			sheet.setColumnView(3, 10); 
			sheet.setColumnView(4, 8); 
			sheet.setColumnView(5, 20); 
			sheet.setColumnView(6, 25);
			sheet.setColumnView(7, 10); 
			sheet.setColumnView(8, 40); 
			sheet.setColumnView(9, 20); 

			Label label;
			for (int i = 0; i < title.length; i++) {
				label = new Label(i, 1, title[i], wcf_head);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(0, i + 2, record.get(i).getThcsIdnumber(),
						wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(1, i + 2, record.get(i).getThcsName(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(2, i + 2, record.get(i).getThcsHoop()
						.toString(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(3, i + 2, record.get(i).getThcsPregnancy(),
						wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(4, i + 2, record.get(i).getThcsDisease(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				String sdate = record.get(i).getThcsChecktime();
				label = new Label(5, i + 2, sdate, wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(6, i + 2,
						record.get(i).getThcsChecksuggest(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(7, i + 2, record.get(i).getThcsIsremedy()
						.toString(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(8, i + 2, record.get(i)
						.getThcsHouseholdregister(), wcf);
				sheet.addCell(label);
			}
			for (int i = 0; i < record.size(); i++) {
				label = new Label(9, i + 2, record.get(i).getThcsCheakplace(),
						wcf);
				sheet.addCell(label);
			}
			sheet.mergeCells(0, 0, 9, 0);
			label = new Label(0, 0, year + patch + areaName + "三查服务记录表",
					wcf_title);
			sheet.addCell(label);

			wwb.write();
			wwb.close();

			int recordcount = record.size();
			int pageCount;
			int temp = recordcount % 15;
			if (temp == 0) {
				pageCount = recordcount / 15;
			} else {
				pageCount = recordcount / 15 + 1;
			}

			List<ThreeCheckServiceWithBLOBs> recordFirst = new ArrayList<ThreeCheckServiceWithBLOBs>();
			int max = 0;
			int page = 1;
			max = publicRecord.size() < (15 * page) ? publicRecord.size()
					: (15 * page);

			for (int i = (page - 1) * 15; i < max; i++) {
				recordFirst.add(publicRecord.get(i));
			}
			int noRecord;
			if (recordFirst.isEmpty()) {
				noRecord = 1;
				map.put("noRecord", noRecord);
			} else {
				noRecord = 0;
				map.put("noRecord", noRecord);
			}
			map.put("recordFirst", recordFirst);
			map.put("pageCount", pageCount);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
			}
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/save")
	public ModelAndView save(String womanName, String womanIdCard,
			String womanProvince, String womanCity, String womanCounty,
			String womanTown, String womanVallige, String womanCurProvince,
			String womanCurCity, String womanCurCounty, String womanCurTown,
			String womanCurVallige, String liveState, String checkYear,
			String checkPatch, String checkDate, String checkPlace,
			String hoop, String pregnant, String disease, String checkSuggest,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			int areaId = user.getUserAreaid();
			
			ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
			record = threeCheckServiceService.findRepeat2(womanIdCard,
					checkYear, checkPatch, liveState);
			if (record == null) {
				ThreeCheckServiceWithBLOBs insertRecord = new ThreeCheckServiceWithBLOBs();
				insertRecord = threeCheckServiceService.insert(areaId, womanName, womanIdCard,
						womanProvince, womanCity, womanCounty, womanTown,
						womanVallige, womanCurProvince, womanCurCity,
						womanCurCounty, womanCurTown, womanCurVallige,
						liveState, checkYear, checkPatch, checkDate,
						checkPlace, hoop, pregnant, disease, checkSuggest);
				threeCheckServiceService.updateCountReportByInsertService(areaId, womanVallige, insertRecord);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
			}
			else if(record!=null && record.getThcsCheckstate().equals("已查")
					&& record.getThcsHoop().equals(hoop)
					&& record.getThcsDisease().equals(disease)
					&& record.getThcsPregnancy().equals(pregnant)){
				map.put("result", Boolean.FALSE);
				map.put("message", "已存在该条服务记录！");
			}else{
				String newHouseRegister = womanProvince + womanCity
						+ womanCounty + womanTown + womanVallige;
				String oldHouseRegister = record.getThcsHouseholdregister();
				String newNowLive = womanCurProvince + womanCurCity
						+ womanCurCounty + womanCurTown + womanCurVallige;
				String oldNowLive = record.getThcsNowliveprovince()
						+ record.getThcsNowlivecity()
						+ record.getThcsNowlivecounty()
						+ record.getThcsNowlivetown()+
						record.getThcsNowlivevillage();
				if (newHouseRegister.equals(oldHouseRegister)
						&& newNowLive.equals(oldNowLive)) {
					ThreeCheckServiceWithBLOBs insertRecord = new ThreeCheckServiceWithBLOBs();
					insertRecord = threeCheckServiceService.setServiceAllInfo(
							areaId, womanName, womanIdCard, womanProvince,
							womanCity, womanCounty, womanTown, womanVallige,
							womanCurProvince, womanCurCity, womanCurCounty,
							womanCurTown, womanCurVallige, liveState,
							checkYear, checkPatch, checkDate, checkPlace, hoop,
							pregnant, disease, checkSuggest);
					
					if(record.getThcsCheckstate().equals("未查")){
						insertRecord.setThcsId(record.getThcsId());
						threeCheckServiceService.updateService(insertRecord);
						threeCheckServiceService.updateCountReportByUpdateService(
								areaId, womanVallige, insertRecord, record);
					}else{
						if(record.getThcsPregnancy().equals("计外孕")){
							if(pregnant.equals("计外孕")){
								insertRecord.setThcsIsremedy(0);
							}else{
								insertRecord.setThcsIsremedy(1);
							}
						}else{
							insertRecord.setThcsIsremedy(0);
						}
						threeCheckServiceService.updateCountReportByUpdateService(
								areaId, womanVallige, insertRecord, record);
						
						record.setThcsHoop(hoop);
						record.setThcsDisease(disease);
						record.setThcsIsremedy(insertRecord.getThcsIsremedy());
						if(!record.getThcsPregnancy().equals("计外孕")){
							record.setThcsPregnancy(pregnant);
						}
						threeCheckServiceService.updateService(record);
					}
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "添加的户籍地或现居住地与该批次的原记录地区不一致！");
				}
			}

		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/findRecordByPage")
	public ModelAndView findRecordByPage(int page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<ThreeCheckServiceWithBLOBs> recordCurrent = new ArrayList<ThreeCheckServiceWithBLOBs>();
			int max = 0;
			max = publicRecord.size() < (15 * page) ? publicRecord.size()
					: (15 * page);

			for (int i = (page - 1) * 15; i < max; i++) {
				recordCurrent.add(publicRecord.get(i));
			}
			map.put("recordCurrent", recordCurrent);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}

}
