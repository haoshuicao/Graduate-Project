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
	/**
	 * 通过身份证号查询最新的服务记录，只能查看当前登录用户本地的最新服务记录（户籍地+流出，现居住地+常住、流出），不包括其他地区的
	 * @param womanIdCard
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showNewRecordByIdCard")
	public ModelAndView showNewRecordByIdCard(String womanIdCard,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			//获取登录用户的地区ID以及用户级别
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
					System.out.println(record.getThcsName() + "+++++++++++++++++++++++");
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
	/**
	 * 通过身份证号检索历史服务记录，只能查看当前登录用户本地的历史服务记录（户籍地+流出，现居住地+常住、流出），不包括其他地区的
	 * @param womanIdCard
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showRecordByIdCard")
	public ModelAndView showRecordByIdCard(String womanIdCard,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			//获取登录用户的地区ID以及用户级别
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
					System.out.println(record.get(0).getThcsName()
							+ "+++++++++++++++++++++");
					// String name = record.get(0).getThcsName();
					int index = record.size() - 1;
					String name = record.get(index).getThcsName();
					map.put("name", name);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
					map.put("record", record);
					// map.put("count",count);
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
	/**
	 * 通过选择条件进行服务记录的检索
	 * @param year
	 * @param patch
	 * @param areaName
	 * @param peopleClass
	 * @param characterSelect
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
			// 业务逻辑
			
			//获取登录用户的地区ID以及用户级别
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
			//人口类别选择全部，特征选择非全部，地区选择非全部
			if (peopleClass.equals("全部") && (!characterSelect.equals("全部"))
					&& (!areaName.equals("全部"))) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				//查询现居住地为本地的记录常住和流入记录
				record1 = threeCheckServiceService
						.findRecordBySelectCondition1(id, level, year, patch,
								areaName, characterSelect);
				//查询户籍地为当前用户所在地区的流出记录
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut5(id, level, year,
								patch, areaName, characterSelect);
				record.addAll(record1);
				record.addAll(record2);
				/*//常住
				record.addAll(threeCheckServiceService
						.findRecordBySelectCondition6(id, level, year,
								patch, areaName, peopleClass,"常住"));
				//流入
				record.addAll(threeCheckServiceService
						.findRecordBySelectCondition6(id, level, year,
								patch, areaName, "流入",characterSelect));
				//人口类别为流出时				
				record.addAll(threeCheckServiceService
							.findRecordBySelectConditionOut4(id, level, year,
									patch, areaName, "流出",characterSelect));	*/			
				setPublicRecord(record);
			}
			//人口类别选择全部，特征选择为全部，地区选择为非全部
			else if (peopleClass.equals("全部") && characterSelect.equals("全部")
					&& (!areaName.equals("全部"))) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				//查询现居住地为本地的记录常住和流入记录
				record1 = threeCheckServiceService
						.findRecordBySelectCondition2(id, level, year, patch,
								areaName);
				//查询户籍地为当前用户所在地区流出记录
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut6(id, level, year,
								patch, areaName);
				record.addAll(record1);
				record.addAll(record2);
				/*//常住
				record.addAll(threeCheckServiceService
						.findRecordBySelectCondition8(id, level, year,
								patch, areaName, "常住"));
				//流入
				record.addAll(threeCheckServiceService
						.findRecordBySelectCondition8(id, level, year,
								patch, areaName, "流入"));
				//当人口类别为流出时
				record.addAll(threeCheckServiceService
							.findRecordBySelectConditionOut1(id, level, year,
									patch, areaName, "流出"));*/
				setPublicRecord(record);
			} 
			//人口类别为全部，特征选择为全部，地区选择为全部
			else if (peopleClass.equals("全部") && characterSelect.equals("全部")
					&& areaName.equals("全部")) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				//查询现居住地为本地的常住和流入记录
				record1 = threeCheckServiceService
						.findRecordBySelectCondition3(id, level, year, patch);
				//查询户籍地为当前用户所在地区流出记录
				System.out.println(record1.size()+"流出流出流出流出流出流出"+"0000000000000000000000000000");
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut7(id, level, year, patch);
				System.out.println(record2.size()+"流出流出流出流出流出流出"+"0000000000000000000000000000");
				record.addAll(record1);
				record.addAll(record2);
				System.out.println(record.size()+"流出流出流出流出流出流出"+"0000000000000000000000000000");
				/*//人口类别为常住
				record.addAll(threeCheckServiceService
							.findRecordBySelectCondition4(id, level, year,
									patch, "常住"));
				//人口类别为流入
				record.addAll(threeCheckServiceService
							.findRecordBySelectCondition4(id, level, year,
									patch, "流入"));
				//人口类别为流出时				
				record.addAll(threeCheckServiceService
							.findRecordBySelectConditionOut2(id, level, year,
									patch, "流出"));*/
				setPublicRecord(record);
			} 
			//人口类别选择非全部，特征选择为全部，地区选择为全部
			else if (!peopleClass.equals("全部")
					&& characterSelect.equals("全部") && areaName.equals("全部")) {
				//人口类别为流出时
				if (peopleClass.equals("流出")) {
					record = threeCheckServiceService
							.findRecordBySelectConditionOut2(id, level, year,
									patch, peopleClass);
					setPublicRecord(record);
				} else {//人口类别为常住或流入
					record = threeCheckServiceService
							.findRecordBySelectCondition4(id, level, year,
									patch, peopleClass);
					setPublicRecord(record);
				}
			} 
			//人口类别非全部，特征选择为非全部，地区选择为全部
			else if (!peopleClass.equals("全部")
					&& (!characterSelect.equals("全部")) && areaName.equals("全部")) {
				//人口类别为流出时
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
			//人口类别为非全部，特征选择非全部，地区非全部
			else if (!peopleClass.equals("全部")
					&& (!characterSelect.equals("全部"))
					&& (!areaName.equals("全部"))) {
				//人口类别为流出时
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
			//人口类别为全部，特征选择为非全部，地区选择为全部
			else if (peopleClass.equals("全部")
					&& (!characterSelect.equals("全部")) && areaName.equals("全部")) {
				List<ThreeCheckServiceWithBLOBs> record1 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				List<ThreeCheckServiceWithBLOBs> record2 = new ArrayList<ThreeCheckServiceWithBLOBs>();
				//查询现居住地为本地的常住和流入记录
				record1 = threeCheckServiceService
						.findRecordBySelectCondition7(id, level, year, patch,
								characterSelect);
				//查询户籍地为当前用户所在地区，现居住地为其他地区的记录流出记录
				record2 = threeCheckServiceService
						.findRecordBySelectConditionOut8(id, level, year,
								patch, characterSelect);
				record.addAll(record1);
				record.addAll(record2);
				/*//人口为常住
				record.addAll(threeCheckServiceService
						.findRecordBySelectCondition5(id, level, year,
								patch, "常住", characterSelect));
			    //人口为流入
				record.addAll(threeCheckServiceService
							.findRecordBySelectCondition5(id, level, year,
									patch, "流入", characterSelect));
				//人口类别为流出时
				record.addAll(threeCheckServiceService
							.findRecordBySelectConditionOut3(id, level, year,
									patch, "流出", characterSelect));*/
				setPublicRecord(record);
			} 
			//人口类别选择非全部，特征选择为全部，地区选择非全部
			else {
				//当人口类别为流出时
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
			
			/* 生成EXCEL表格 */
			// 准备设置excel工作表的标题
			String[] title = { "身份证", "姓名", "环", "孕", "病", "检查时间", "建议",
					"是否补救", "户籍", "检查地" };
			// 获得开始时间
			long start = System.currentTimeMillis();
			// 输出的excel的路径
			String fileName = "serviceRecordList";
			String filePath = request.getSession().getServletContext()
					.getRealPath("upload/" + fileName + ".xls");
			// 创建Excel工作薄
			WritableWorkbook wwb;
			// 新建立一个jxl文件,即在e盘下生成testJXL.xls
			OutputStream os = new FileOutputStream(filePath);
			wwb = Workbook.createWorkbook(os);
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet("三查服务记录", 0);

			// 设置标题的格式
			WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 16,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
			wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_title.setAlignment(Alignment.CENTRE);
			wcf_title.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框
			wcf_title.setBackground(jxl.format.Colour.BRIGHT_GREEN);
			// 设置头标题的格式
			WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
			wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf_head.setAlignment(Alignment.CENTRE);
			wcf_head.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框

			// 设置单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 11,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); // 设置边框

			// 设置列宽
			sheet.setColumnView(0, 22); // 设置列的宽度
			sheet.setColumnView(1, 15); // 设置列的宽度
			sheet.setColumnView(2, 5); // 设置列的宽度
			sheet.setColumnView(3, 10); // 设置列的宽度
			sheet.setColumnView(4, 8); // 设置列的宽度
			sheet.setColumnView(5, 20); // 设置列的宽度
			sheet.setColumnView(6, 25); // 设置列的宽度
			sheet.setColumnView(7, 10); // 设置列的宽度
			sheet.setColumnView(8, 40); // 设置列的宽度
			sheet.setColumnView(9, 20); // 设置列的宽度

			Label label;
			for (int i = 0; i < title.length; i++) {
				// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i, 1, title[i], wcf_head);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(label);
			}

			// 下面是填充数据
			/*
			 * 保存数字到单元格，需要使用jxl.write.Number 必须使用其完整路径，否则会出现错误
			 */

			// 填充身份证号
			for (int i = 0; i < record.size(); i++) {
				label = new Label(0, i + 2, record.get(i).getThcsIdnumber(),
						wcf);
				sheet.addCell(label);
			}
			// 填充姓名
			for (int i = 0; i < record.size(); i++) {
				label = new Label(1, i + 2, record.get(i).getThcsName(), wcf);
				sheet.addCell(label);
			}
			// 填充环
			for (int i = 0; i < record.size(); i++) {
				label = new Label(2, i + 2, record.get(i).getThcsHoop()
						.toString(), wcf);
				sheet.addCell(label);
			}
			// 填充孕
			for (int i = 0; i < record.size(); i++) {
				label = new Label(3, i + 2, record.get(i).getThcsPregnancy(),
						wcf);
				sheet.addCell(label);
			}
			// 填充病
			for (int i = 0; i < record.size(); i++) {
				label = new Label(4, i + 2, record.get(i).getThcsDisease(), wcf);
				sheet.addCell(label);
			}
			// 填充检查时间
			for (int i = 0; i < record.size(); i++) {
				String sdate = record.get(i).getThcsChecktime();
				/*
				 * Date ddate = record.get(i).getThcsChecktime(); sdate=(new
				 * SimpleDateFormat("yyyy-MM-dd HH-m")).format(ddate);
				 */
				label = new Label(5, i + 2, sdate, wcf);
				sheet.addCell(label);
			}
			// 填充建议
			for (int i = 0; i < record.size(); i++) {
				label = new Label(6, i + 2,
						record.get(i).getThcsChecksuggest(), wcf);
				sheet.addCell(label);
			}
			// 填充是否补救
			for (int i = 0; i < record.size(); i++) {
				label = new Label(7, i + 2, record.get(i).getThcsIsremedy()
						.toString(), wcf);
				sheet.addCell(label);
			}
			// 填充户籍
			for (int i = 0; i < record.size(); i++) {
				label = new Label(8, i + 2, record.get(i)
						.getThcsHouseholdregister(), wcf);
				sheet.addCell(label);
			}
			// 填充检查地
			for (int i = 0; i < record.size(); i++) {
				label = new Label(9, i + 2, record.get(i).getThcsCheakplace(),
						wcf);
				sheet.addCell(label);
			}

			/*
			 * 合并单元格 通过writablesheet.mergeCells(int x,int y,int m,int n);来实现的
			 * 表示将从第x+1列，y+1行到m+1列，n+1行合并
			 */
			sheet.mergeCells(0, 0, 9, 0);
			label = new Label(0, 0, year + patch + areaName + "三查服务记录表",
					wcf_title);
			sheet.addCell(label);

			// 写入数据
			wwb.write();
			// 关闭文件
			wwb.close();

			int recordcount = record.size();
			int pageCount;
			int temp = recordcount % 15;
			if (temp == 0) {
				pageCount = recordcount / 15;
			} else {
				pageCount = recordcount / 15 + 1;
			}

			//recordFirst为分页的首页记录
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
	/**
	 * 添加个人服务记录
	 * @param womanName
	 * @param womanIdCard
	 * @param womanProvince
	 * @param womanCity
	 * @param womanCounty
	 * @param womanTown
	 * @param womanVallige
	 * @param womanCurProvince
	 * @param womanCurCity
	 * @param womanCurCounty
	 * @param womanCurTown
	 * @param womanCurVallige
	 * @param liveState
	 * @param checkYear
	 * @param checkPatch
	 * @param checkDate
	 * @param checkPlace
	 * @param hoop
	 * @param pregnant
	 * @param disease
	 * @param checkSuggest
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
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
			//获取当前登录用户信息
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			int areaId = user.getUserAreaid();
			
			//保存之前查找是否已经有该条记录（根据身份证号，年度，批次，居住状态，孕，环，病查重）
			ThreeCheckServiceWithBLOBs record = new ThreeCheckServiceWithBLOBs();
			/*record = threeCheckServiceService.findRepeat(womanIdCard,
					checkYear, checkPatch, liveState, pregnant, hoop, disease);*/
			record = threeCheckServiceService.findRepeat2(womanIdCard,
					checkYear, checkPatch, liveState);
			//如果不存在该年度、批次、身份证号、居住状态的记录，则直接插入
			if (record == null) {
			    //插入三查记录表
				ThreeCheckServiceWithBLOBs insertRecord = new ThreeCheckServiceWithBLOBs();//保存插入的记录
				insertRecord = threeCheckServiceService.insert(areaId, womanName, womanIdCard,
						womanProvince, womanCity, womanCounty, womanTown,
						womanVallige, womanCurProvince, womanCurCity,
						womanCurCounty, womanCurTown, womanCurVallige,
						liveState, checkYear, checkPatch, checkDate,
						checkPlace, hoop, pregnant, disease, checkSuggest);
				//根据新插入的记录更新三查统计表
				/*threeCheckCountService.update(insertRecord,areaId, womanName, womanIdCard,
						womanProvince, womanCity, womanCounty, womanTown,
						womanVallige, womanCurProvince, womanCurCity,
						womanCurCounty, womanCurTown, womanCurVallige,
						liveState, checkYear, checkPatch, checkDate,
						checkPlace, hoop, pregnant, disease, checkSuggest);*/
				threeCheckServiceService.updateCountReportByInsertService(areaId, womanVallige, insertRecord);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
			}
			//如果存在年度、批次、居住状态、身份证号、环病孕都一样的已查记录，就不做改变，提示已存在该条服务记录
			else if(record!=null && record.getThcsCheckstate().equals("已查")
					&& record.getThcsHoop().equals(hoop)
					&& record.getThcsDisease().equals(disease)
					&& record.getThcsPregnancy().equals(pregnant)){
				map.put("result", Boolean.FALSE);
				map.put("message", "已存在该条服务记录！");
			}else{//存在该年度、批次、居住状态、身份证号的记录，但是环病孕情况不都一样
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
				// 户籍地和现居住地要一致才能更新
				if (newHouseRegister.equals(oldHouseRegister)
						&& newNowLive.equals(oldNowLive)) {
					ThreeCheckServiceWithBLOBs insertRecord = new ThreeCheckServiceWithBLOBs();// 保存插入的记录
					// 设置新的逐条添加的新服务记录内容
					insertRecord = threeCheckServiceService.setServiceAllInfo(
							areaId, womanName, womanIdCard, womanProvince,
							womanCity, womanCounty, womanTown, womanVallige,
							womanCurProvince, womanCurCity, womanCurCounty,
							womanCurTown, womanCurVallige, liveState,
							checkYear, checkPatch, checkDate, checkPlace, hoop,
							pregnant, disease, checkSuggest);
					
					//查到的原记录检查状态为未查，则直接覆盖更新原纪录所有内容，并更新统计表
					if(record.getThcsCheckstate().equals("未查")){
						insertRecord.setThcsId(record.getThcsId());
						threeCheckServiceService.updateService(insertRecord);
						// 通过新保存的更新记录和查找到的原纪录更新统计表中已有的各级的统计信息
						threeCheckServiceService.updateCountReportByUpdateService(
								areaId, womanVallige, insertRecord, record);
					}else{
						//根据两次记录的内容，设置补救状态
						if(record.getThcsPregnancy().equals("计外孕")){
							if(pregnant.equals("计外孕")){
								insertRecord.setThcsIsremedy(0);
							}else{
								insertRecord.setThcsIsremedy(1);
							}
						}else{
							insertRecord.setThcsIsremedy(0);
						}
						// 通过新保存的更新记录和查找到的原纪录更新统计表中已有的各级的统计信息
						//因为要用更新前的原记录和新纪录进行比较更新统计表，所有先比较更新统计表，然后才更新原记录
						threeCheckServiceService.updateCountReportByUpdateService(
								areaId, womanVallige, insertRecord, record);
						
						//更新原记录
						record.setThcsHoop(hoop);
						record.setThcsDisease(disease);
						record.setThcsIsremedy(insertRecord.getThcsIsremedy());//计外孕变成非计外孕，计外孕状态不变，只是补救状态变为已补救
						if(!record.getThcsPregnancy().equals("计外孕")){
							record.setThcsPregnancy(pregnant);//非计外孕变成计外孕，则孕状态要改变
						}
						threeCheckServiceService.updateService(record);//更新原记录
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
	/**
	 * 对于检索的服务记录进行分页
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/findRecordByPage")
	public ModelAndView findRecordByPage(int page, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			//默认15条记录为一页
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
