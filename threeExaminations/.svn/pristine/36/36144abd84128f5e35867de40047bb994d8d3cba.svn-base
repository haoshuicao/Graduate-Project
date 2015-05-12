package swust.edu.cn.threeExaminations.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.ThreeCheckCount;
import swust.edu.cn.threeExaminations.model.ThreeCheckServiceWithBLOBs;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.ThreeCheckServiceService;

@Controller
@RequestMapping("/areaController")
public class AreaController {
	private AreaService areaService;
	private ThreeCheckServiceService threeCheckServiceService;

	public ThreeCheckServiceService getThreeCheckServiceService() {
		return threeCheckServiceService;
	}
	@Autowired
	public void setThreeCheckServiceService(
			ThreeCheckServiceService threeCheckServiceService) {
		this.threeCheckServiceService = threeCheckServiceService;
	}
	public AreaService getAreaService() {
		return areaService;
	}

	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/searchByAreaName")
	public ModelAndView searchByAreaName(String areaName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			int curAreaId = areaService.getAreaIdByName(areaName); // 通过用户选择地区查询到该地区的areaId
			int areaParentId = curAreaId; // 将查询到的areaId作为parentId去查询以该Id为父Id的地区
			List<Area> areaList = areaService.getAreaByParentId(areaParentId);
			map.put("areaList", areaList);
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
	 * 地区选择框由当前登录用户上传的服务记录中截取，这些服务记录为“现居住地+流入/常住”和“户籍地+流出”
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/setArea")
	public ModelAndView setArea(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else if(user.getUserLevel().equals("乡/镇级")){
				String level = user.getUserLevel();
				map.put("level", level);
				int areaid=user.getUserAreaid();
				String areaName = areaService.getAreaNameByAreaId(areaid);
				List<ThreeCheckServiceWithBLOBs> areaList1 = threeCheckServiceService.getValigeByTown(areaName);
				//System.out.println(areaList1.get(0).getThcsNowlivevillage()+"---------");
				// 获取当前用户地区
				String areaName1 =(String)session.getAttribute("areaName");
				//查找户籍地为当前用户地区的列表
				List<ThreeCheckServiceWithBLOBs> huJiAreaList = threeCheckServiceService.getHuJiByUserArea(areaName1);
				
				List<String> areaList2 = new ArrayList<String>();
				List<String> tempList = new ArrayList<String>();
				for(int i=0;i<huJiAreaList.size();i++){
					String tureArea1 = huJiAreaList.get(i).getThcsHouseholdregister();
					//截取检索的户籍地的最后一个地区级别的名称
					String tureArea2 = tureArea1.substring(areaName1.length());
					areaList2.add(tureArea2);
				}
				List<String> areaList= new ArrayList<String>();
				for(int i=0;i<areaList1.size();i++){
					areaList.add(i, areaList1.get(i).getThcsNowlivevillage());
					tempList.add(i,areaList1.get(i).getThcsNowlivevillage());
				}
				//去重
				for(int j=0;j<areaList2.size();j++){
					if(!tempList.contains(areaList2.get(j))){
						areaList.add(areaList2.get(j));
					}
				}
				map.put("areaList", areaList);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
			}
			else{
				List<Area> areaList = areaService.getAreaByParentId(user
						.getUserAreaid());
				map.put("areaList", areaList);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
			}
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
	 * 通过地区的父类ID查找地区
	 * @param areaParentId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/searchByAreaParentId")
	public ModelAndView searchByAreaParentId(Integer areaParentId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			
			List<Area> areaList = areaService.getAreaByParentId(areaParentId);
			
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			List<String> areaList1 = new ArrayList<String>();
			int areaId = user.getUserAreaid();
			Area area = new Area();
			area = areaService.getAreaByAreaId(areaId);
			String town = area.getAreaName();
			
			int parentId = area.getAreaParentid();
			Area area1 = new Area();
			area1 = areaService.getAreaByAreaId(parentId);
			String county = area1.getAreaName();
			
			int parentId1 = area1.getAreaParentid();
			Area area2 = new Area();
			area2 = areaService.getAreaByAreaId(parentId1);
			String city = area2.getAreaName();
			
			int parentId2 = area2.getAreaParentid();
			Area area3 = new Area();
			area3 = areaService.getAreaByAreaId(parentId2);
			String province = area3.getAreaName();
			
			areaList1.add(province);
			areaList1.add(city);
			areaList1.add(county);
			areaList1.add(town);
			
			List<String> tempAreaList= new ArrayList<String>();  
            for(String i:areaList1){  
                if(!tempAreaList.contains(i)){  
                	tempAreaList.add(i);  
                }  
            } 
            
			map.put("areaList", areaList);
			map.put("town", town);
			map.put("county", county);
			map.put("city", city);
			map.put("province", province);
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
	@RequestMapping("/searchAreasByAreaParentId")
	public ModelAndView searchAreasByAreaParentId(Integer areaParentId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑			
			List<Area> areaList = areaService.getAreaByParentId(areaParentId);
			map.put("areaList", areaList);
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
	@RequestMapping("/showUserNum")
	public ModelAndView showUserNum(Integer areaId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			Area area = new Area();
			area = areaService.getAreaByAreaId(areaId);
			map.put("area", area);
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

}
