package swust.edu.cn.threeExaminations.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import swust.edu.cn.threeExaminations.model.Task;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.TaskService;
import swust.edu.cn.threeExaminations.service.UserService;

@Controller
@RequestMapping("/taskController")
public class TaskController {
	List<Task> publicTaskList = new ArrayList<Task>();
	
	List<Task> lookTaskList = new ArrayList<Task>();

	public void setPublicTaskList(List<Task> publicTaskList) {
		this.publicTaskList = publicTaskList;
	}

	public void setLookTaskList(List<Task> lookTaskList){
		this.lookTaskList = lookTaskList;
	}
	
	private TaskService taskService;

	private UserService userService;

	private AreaService areaService;

	public AreaService getAreaService() {
		return areaService;
	}

	@Autowired
	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/getTaskYear")
	public ModelAndView getTaskYear(HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try{
			long date = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String NowYear = sdf.format(date);
			String NextYear = Integer.toString((Integer.parseInt(NowYear)+1));
			List<String> yearList = new ArrayList<String>();
			yearList.add(NowYear);
			yearList.add(NextYear);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
			map.put("yearList", yearList);
		}catch (Exception e) {
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
	@RequestMapping("/publishSurveyTask")
	public ModelAndView publishSurveyTask(String year, String batch,
			Date startTime, Date endTime, String content,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
			} else {
				List<Task> taskList1 = new ArrayList<Task>();
				List<Task> taskList = new ArrayList<Task>();
				Integer userAreaId = user.getUserAreaid();
				List<User> userList = new ArrayList<User>();
				userList = userService.findUserByAreaId(userAreaId);
				for (int i = 0; i < userList.size(); i++) {
					taskList1 = taskService.findSameTask(year, batch, userList
							.get(i).getUserId());
					taskList.addAll(taskList1);
				}
				if (taskList.size() != 0) {
					map.put("result", Boolean.FALSE);
					map.put("message", "该地区已经发布该年度、批次的任务，请重新选择！");
				} else {
					taskService.publishTask(year, batch, startTime, endTime,
							content, user.getUserId());
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
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
	@RequestMapping("/editTask")
	public ModelAndView editTask(Integer taskId, String year, String batch,
			Date startTime, Date endTime, String content,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
			} else {
				List<Task> taskList1 = new ArrayList<Task>();
				List<Task> taskList = new ArrayList<Task>();
				Integer userAreaId = user.getUserAreaid();
				List<User> userList = new ArrayList<User>();
				userList = userService.findUserByAreaId(userAreaId);
				for (int i = 0; i < userList.size(); i++) {
					taskList1 = taskService.findSameTask(year, batch, userList
							.get(i).getUserId());
					for (int j = 0; j < taskList1.size(); j++) {
						if (!taskList1.get(j).getTaskId().equals(taskId)) {
							taskList.addAll(taskList1);
						}
					}
				}
				if (taskList.size() != 0) {
					map.put("result", Boolean.FALSE);
					map.put("message", "该地区已经发布该年度、批次的任务，请重新选择！");
				} else {
					taskService.editTask(taskId, year, batch, startTime,
							endTime, content);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
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
	@RequestMapping("/deleteTask")
	public ModelAndView deleteTask(Integer taskId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			taskService.deleteTask(taskId);
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

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showTaskList")
	public ModelAndView showTaskList(HttpServletRequest request,
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
			} else {
				List<Task> taskList = new ArrayList<Task>();
				taskList = taskService.showTaskList(user.getUserId());
				if (taskList.size() > 0) {
					setPublicTaskList(taskList);
					int recordCount = taskList.size();
					int pageCount;
					int temp = recordCount % 10;
					if(temp == 0){
						pageCount = recordCount/10;
					}else{
						pageCount = recordCount/10 + 1;
					}
					List<Task> recordFirst = new ArrayList<Task>();
					int max = 0;
					int page = 1;
					max = publicTaskList.size()<(10*page)?publicTaskList.size():(10*page);
					for(int j = (page-1)*10;j<max;j++){
						recordFirst.add(publicTaskList.get(j));
					}
					map.put("taskRecordFirst", recordFirst);
					map.put("pageCount", pageCount);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
					map.put("taskList", taskList);
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "还未发布任务，任务列表为空！");
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
	@RequestMapping("/findRecordByPage")
	public ModelAndView findRecordByPage(int page,HttpServletRequest request,
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
			} else {
					List<Task> recordCurrent = new ArrayList<Task>();
					int max = 0;
					max = publicTaskList.size()<(10*page)?publicTaskList.size():(10*page);
					for(int j = (page-1)*10;j<max;j++){
						recordCurrent.add(publicTaskList.get(j));
					}
					map.put("taskRecordCurrent", recordCurrent);
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

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showTaskContent")
	public ModelAndView showTaskContent(Integer taskId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Task task = new Task();
			task = taskService.showTaskContent(taskId);
			
			Date lastModiftTime = task.getTaskLastmodifytime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(lastModiftTime);
			String startTime = sdf2.format(task.getTaskBegintime());
			String endTime = sdf2.format(task.getTaskEndtime());

			User taskUser = new User();
			int userId = task.getTaskUserid();
			taskUser = userService.findUserById(userId);
			Area area = new Area();
			int areaId = taskUser.getUserAreaid();
			area = areaService.getAreaByAreaId(areaId);
			String areaName = area.getAreaName();
			while (area.getAreaParentid() != 0) {
				areaId = area.getAreaParentid();
				area = areaService.getAreaByAreaId(areaId);
				areaName = area.getAreaName() + areaName;
			}

			map.put("task", task);
			map.put("date", date);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("publishUnit", areaName);
			map.put("publishUser", taskUser.getUserName());
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

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/lookTaskList")
	public ModelAndView lookTaskList(HttpServletRequest request,
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
			} else {
				List<Task> taskList = new ArrayList<Task>();
				List<Task> taskList1 = new ArrayList<Task>();
				taskList1 = taskService.lookTaskList();
				if (taskList1.size() > 0) {
					Area area = new Area();
					List<Area> areaList = new ArrayList<Area>();
					int areaId;
					areaId = user.getUserAreaid();
					area = areaService.getAreaByAreaId(areaId);
					areaList.add(area);
					while(area.getAreaParentid() != 0 ){
						areaId=area.getAreaParentid();
						area=areaService.getAreaByAreaId(areaId);
						areaList.add(area);
					}
					
					int i,j;
					User taskUser = new User();
					Area taskArea = new Area();
					for (i = 0; i < taskList1.size(); i++) {
						
						taskUser = userService.findUserById(taskList1.get(i)
								.getTaskUserid());
						taskArea = areaService.getAreaByAreaId(taskUser
								.getUserAreaid());
						for(j = 0; j < areaList.size(); j++){
							if(taskArea.getAreaId().equals(areaList.get(j).getAreaId())){
								taskList.add(taskList1.get(i));
								break;
							}
						}
					}
					if (taskList.size() == 0) {
						map.put("result", Boolean.FALSE);
						map.put("message", "还未发布任务，任务列表为空！");
					} else {
						setPublicTaskList(taskList);
						int recordCount = taskList.size();
						int pageCount;
						int temp = recordCount % 10;
						if(temp == 0){
							pageCount = recordCount/10;
						}else{
							pageCount = recordCount/10 + 1;
						}
						List<Task> recordFirst = new ArrayList<Task>();
						int max = 0;
						int page = 1;
						max = publicTaskList.size()<(10*page)?publicTaskList.size():(10*page);
						for(int k = (page-1)*10;k<max;k++){
							recordFirst.add(publicTaskList.get(k));
						}
						
						Task newTask = taskList.get(0);
						User newUser = userService.findUserById(newTask
								.getTaskUserid());
						int newAreaId = newUser.getUserAreaid();
						Area newArea = areaService.getAreaByAreaId(newAreaId);
						Date lastModiftTime = newTask.getTaskLastmodifytime();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat sdf2 = new SimpleDateFormat(
								"yyyy-MM-dd");
						String date = sdf.format(lastModiftTime);
						String startTime = sdf2.format(newTask
								.getTaskBegintime());
						String endTime = sdf2.format(newTask.getTaskEndtime());
						String areaName = newArea.getAreaName();
						while (newArea.getAreaParentid() != 0) {
							newAreaId = newArea.getAreaParentid();
							newArea = areaService.getAreaByAreaId(newAreaId);
							areaName = newArea.getAreaName() + areaName;
						}
					
						map.put("taskRecordFirst", recordFirst);
						map.put("pageCount", pageCount);
						map.put("newTask", newTask);
						map.put("date", date);
						map.put("startTime", startTime);
						map.put("endTime", endTime);
						map.put("newUnit", areaName);
						map.put("publishUser", newUser.getUserName());
						map.put("result", Boolean.TRUE);
						map.put("message", "success");
					}
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "还未发布任务，任务列表为空！");
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
}
