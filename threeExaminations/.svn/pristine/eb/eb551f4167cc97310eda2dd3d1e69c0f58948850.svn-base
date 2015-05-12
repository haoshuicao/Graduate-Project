package swust.edu.cn.threeExaminations.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.Authority;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.model.UserAuthority;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.ThreeCheckServiceService;
import swust.edu.cn.threeExaminations.service.UserService;

/**
 * @author Shaw Joe
 * @Title:
 * @ClassName：UserController.java
 * @Package: swust.edu.cn.threeExaminations.controller
 * @Description: TODO
 * @author Shao Zhou
 * @createDate:2013 下午10:53:15
 * @email:shaozhou@swust.edu.cn
 * @phone:15881615397
 * @Department:Knowledge engineering and Data Mining Lab of Computer Science and
 *                       Technology Academy of SWUST
 * @Address:Southwest University of Science and Technology 59 Qinglong Road,
 *                    Mianyang, 621010, P.R.China
 * @reviseNote:
 * @version:V1.0
 */
@Controller
@RequestMapping("/userController")
public class UserController {
	List<User> publicRecord = new ArrayList<User>();
	List<String> publicAreaList = new ArrayList<String>();
	List<User> publicUserList = new ArrayList<User>();
	List<String> publicAreaList1 = new ArrayList<String>();
	private void setPublicAreaList1(List<String> publicAreaList1) {
		this.publicAreaList1 = publicAreaList1;
	}
	private void setPublicUserList(List<User> publicUserList) {
		this.publicUserList = publicUserList;
	}

	private void setPublicAreaList(List<String> publicAreaList) {
		this.publicAreaList = publicAreaList;
	}

	private void setPublicRecord(List<User> publicRecord) {
		this.publicRecord = publicRecord;
	}
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

	private ThreeCheckServiceService threeCheckServiceService;

	public ThreeCheckServiceService getThreeCheckServiceService() {
		return threeCheckServiceService;
	}

	@Autowired
	public void setThreeCheckServiceService(
			ThreeCheckServiceService threeCheckServiceService) {
		this.threeCheckServiceService = threeCheckServiceService;
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/test")
	public ModelAndView test(int id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
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

	// 登录，将登录用户信息放在session中，获取用户的权限、单位等信息。
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/login")
	public ModelAndView login(String username, String password,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			if(session.getAttribute("user")==null){
				User user = new User();
				user = userService.findUserByNAP(username, password);
				if (user.getUserId() != null) {
					// 获取用户权限
					List<UserAuthority> userAuthList = new ArrayList<UserAuthority>();
					userAuthList = userService.findUserAuthByUserId(user
							.getUserId());
					if (userAuthList.size() > 0) {
						List<Authority> authList = new ArrayList<Authority>();
						Authority auth = new Authority();
						for (int i = 0; i < userAuthList.size(); i++) {
							auth = userService.findUserAuthByAuthId(userAuthList
									.get(i).getUsauAuthid());
							authList.add(auth);
						}

						// 获取用户地区
						Area area = new Area();
						int areaId = user.getUserAreaid();
						area = areaService.getAreaByAreaId(areaId);
						String areaName = area.getAreaName();
						while (area.getAreaParentid() != 0) {
							areaId = area.getAreaParentid();
							area = areaService.getAreaByAreaId(areaId);
							areaName = area.getAreaName() + areaName;
						}

						HttpSession session1 = request.getSession();
						session1.setAttribute("user", user);
						session1.setAttribute("areaName", areaName);
						session1.setAttribute("authList", authList);
						map.put("href", authList.get(0).getAuthDescibe());
						map.put("result", Boolean.TRUE);
						map.put("message", "success");
					} else {
						map.put("result", Boolean.FALSE);
						map.put("message", "该用户没有可操作的权限！");
					}
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "用户名或密码错误！");
				}
			}else{
				System.out.println(session.getAttribute("user"));
				map.put("result", Boolean.FALSE);
				map.put("message", "请注销已登录用户或关闭浏览器，再重新登录！");
			}
				
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出错！");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			return mav;
		}
	}

	// 退出，清空session里面的信息
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			/*User user = (User) request.getSession().getAttribute("user");
			request.getSession().invalidate();*/
			HttpSession session = request.getSession();
			session.removeAttribute("user"); 
			session.removeAttribute("areaName");
			session.removeAttribute("authList");
			map.put("result", Boolean.TRUE);
			map.put("message", "用户退出登录！");
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

	// 显示当前登录用户的姓名、单位和权限信息
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showCurrentUser")
	public ModelAndView showCurrentUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			List<Authority> authList = (List<Authority>) session
					.getAttribute("authList");
			String areaName = (String) session.getAttribute("areaName");
			System.out.println(areaName+"$$$$$$$$$$$$$$$$$$$$$");
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				map.put("areaName", areaName);
				map.put("authList", authList);
				map.put("user", user);
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

	// 显示当前级别的管理员管理的所有同级别同地区的所有用户
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showAllUser")
	public ModelAndView showAllUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				List<User> currentLevelAllUserList = new ArrayList<User>();// 可查看的所有用户列表
				List<String> userLevelList = new ArrayList<String>();// 可查看的行政级别
				int i;
				if (user.getUserType().equals("超级管理员")) {
					//currentLevelAllUserList.addAll(userService.findAllUser());//数据库中所有用户列表
					currentLevelAllUserList.addAll(userService
							.findUserByUserType("超级管理员"));
					currentLevelAllUserList.addAll(userService
							.findUserByUserType("管理员"));
					setPublicRecord(currentLevelAllUserList);
					userLevelList.add("全部");
					userLevelList.add("省级");
					userLevelList.add("市级");
					userLevelList.add("县/区级");
					//userLevelList.add("乡/镇级");
				} else if (user.getUserLevel().equals("县/区级")) {
					userLevelList.add("全部");
					userLevelList.add("县/区级");
					userLevelList.add("乡/镇级");
					// 县/区级以下的乡/镇级用户
					List<Area> childAreaList = new ArrayList<Area>();
					childAreaList = areaService.getAreaByParentId(user
							.getUserAreaid());
					//不仅仅是根据管理员的级别查询用户，同时限制用户的类型，以区分出超级管理员
					// 本地同级用户
					/*currentLevelAllUserList.addAll(userService
							.findUserByAreaId(user.getUserAreaid()));*/
					currentLevelAllUserList.addAll(userService
							.findUserByAreaIdAndType(user.getUserAreaid(), "管理员", ""));
					currentLevelAllUserList.addAll(userService
							.findUserByAreaIdAndType(user.getUserAreaid(), "普通用户", ""));
					// 镇级用户
					for (int j = 0; j < childAreaList.size(); j++) {
						/*currentLevelAllUserList.addAll(userService
								.findUserByAreaId(childAreaList.get(j)
										.getAreaId()));*/
						currentLevelAllUserList.addAll(userService
								.findUserByAreaIdAndType(childAreaList.get(j).getAreaId(), "管理员", ""));
						currentLevelAllUserList.addAll(userService
								.findUserByAreaIdAndType(childAreaList.get(j).getAreaId(), "普通用户", ""));
					}
					setPublicRecord(currentLevelAllUserList);
				} else {
					userLevelList.add("全部");
					userLevelList.add(user.getUserLevel());
					currentLevelAllUserList.addAll(userService
							.findUserByAreaIdAndType(user.getUserAreaid(), "管理员", ""));
					currentLevelAllUserList.addAll(userService
							.findUserByAreaIdAndType(user.getUserAreaid(), "普通用户", ""));
					setPublicRecord(currentLevelAllUserList);
				}
				List<String> areaNameList = new ArrayList<String>();
				int areaId;
				String areaName;
				Area area = new Area();
				for (i = 0; i < currentLevelAllUserList.size(); i++) {
					areaId = currentLevelAllUserList.get(i).getUserAreaid();
					area = areaService.getAreaByAreaId(areaId);
					areaName = area.getAreaName();
					while (area.getAreaParentid() != 0) {
						areaId = area.getAreaParentid();
						area = areaService.getAreaByAreaId(areaId);
						areaName = area.getAreaName() + areaName;
					}
					areaNameList.add(areaName);
					
				}
				setPublicAreaList(areaNameList);
				
				 int recordcount = currentLevelAllUserList.size();
		            int pageCount;
		            int temp=recordcount%10;
		            if(temp==0){
		            	pageCount = recordcount/10;
		            }else{
		            	pageCount = recordcount/10 +1;
		            }
		           
		            List<User> recordFirst = new ArrayList<User>();
					int max = 0;
					int page = 1;
					max = publicRecord.size()<(10*page)?publicRecord.size():(10*page);
					
					for(int j = (page-1)*10;j<max;j++){
						recordFirst.add(publicRecord.get(j));
					}
					List<String> areaListFirst = new ArrayList<String>();
					int max1 = 0;
					int page1= 1;
					max1 = publicAreaList.size()<(10*page1)?publicAreaList.size():(10*page1);
					
					for(int j = (page1-1)*10;j<max1;j++){
						areaListFirst.add(publicAreaList.get(j));
					}
				map.put("areaListFirst", areaListFirst);
		        map.put("recordFirst", recordFirst);
		        map.put("pageCount", pageCount);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				//map.put("allUserList", currentLevelAllUserList);
				//map.put("areaNameList", areaNameList);
				map.put("user", user);
				map.put("userLevelList", userLevelList);
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
	public ModelAndView findRecordByPage(int page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			//System.out.println(page+"**********************");
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else{
				List<User> recordCurrent = new ArrayList<User>();
				int max = 0;
				max = publicRecord.size()<(10*page)?publicRecord.size():(10*page);
				
				for(int i = (page-1)*10;i<max;i++){
					recordCurrent.add(publicRecord.get(i));
				}
				
				List<String> areaListCurrent = new ArrayList<String>();
				int max1 = 0;
				max1 = publicAreaList.size()<(10*page)?publicAreaList.size():(10*page);
				
				for(int i = (page-1)*10;i<max1;i++){
					areaListCurrent.add(publicAreaList.get(i));
					System.out.println(areaListCurrent+"**********************");
				}
				    map.put("user", user);
					map.put("areaListCurrent", areaListCurrent);
					map.put("recordCurrent", recordCurrent);
					
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
			}
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
	@SuppressWarnings({ "rawtypes", "unchecked", "finally" })
	@RequestMapping("/findRecordByPage1")
	public ModelAndView findRecordByPage1(int page,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			//System.out.println(page+"**********************");
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else{
				List<User> recordCurrent = new ArrayList<User>();
				int max = 0;
				max = publicUserList.size()<(10*page)?publicUserList.size():(10*page);
				
				for(int i = (page-1)*10;i<max;i++){
					recordCurrent.add(publicUserList.get(i));
				}
				
				List<String> areaListCurrent = new ArrayList<String>();
				int max1 = 0;
				max1 = publicAreaList1.size()<(10*page)?publicAreaList1.size():(10*page);
				
				for(int i = (page-1)*10;i<max1;i++){
					areaListCurrent.add(publicAreaList1.get(i));
					System.out.println(areaListCurrent+"**********************");
				}
				    map.put("user", user);
					map.put("areaListCurrent", areaListCurrent);
					map.put("recordCurrent", recordCurrent);
					
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
			}
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
	// 显示管理员选中的用户的信息
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/findUserByUserId")
	public ModelAndView findUserByUserId(Integer userId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("rusult", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				User editUser = new User();
				editUser = userService.findUserById(userId);
				// 查找用户部门
				int areaId = editUser.getUserAreaid();
				Area area = new Area();
				List<Area> areaList = new ArrayList<Area>();
				area = areaService.getAreaByAreaId(areaId);
				areaList.add(area);
				while (area.getAreaParentid() != 0) {
					areaId = area.getAreaParentid();
					area = areaService.getAreaByAreaId(areaId);
					areaList.add(area);
				}

				// 获取用户权限
				List<Authority> authList = new ArrayList<Authority>();
				List<UserAuthority> userAuthList = userService
						.findUserAuthByUserId(editUser.getUserId());
				if (userAuthList.size() > 0) {
					Authority auth = new Authority();
					for (int i = 0; i < userAuthList.size(); i++) {
						auth = userService.findUserAuthByAuthId(userAuthList
								.get(i).getUsauAuthid());
						authList.add(auth);
					}
				}
				
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				map.put("editUser", editUser);
				map.put("areaList", areaList);
				map.put("authList", authList);
				map.put("user", user);
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

	// 查找某一地区的所有用户
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/findUserByAreaIdAndTypeAndPri")
	public ModelAndView findUserByAreaIdAndTypeAndPri(String level,Integer userAreaId,String primarykey,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("rusult", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				List<User> userList = new ArrayList<User>();
				//级别选择为全部,不使用js传入的userAreaId,根据管理员的级别、用户类型和关键字进行查询(较之showAllUser多了关键字模糊查询部分)
				if(level.equals("全部")){
					if (user.getUserType().equals("超级管理员")) {
						userList.addAll(userService.findUserByPrimaryAndType("超级管理员",primarykey));
						userList.addAll(userService.findUserByPrimaryAndType("管理员",primarykey));
						setPublicUserList(userList);
					} else if (user.getUserLevel().equals("县/区级")) {
						userList.addAll(userService.findUserByAreaIdAndType(user.getUserAreaid(),"管理员",primarykey));
						userList.addAll(userService.findUserByAreaIdAndType(user.getUserAreaid(),"普通用户",primarykey));
						List<Area> childAreaList = new ArrayList<Area>();
						childAreaList.addAll(areaService.getAreaByParentId(user.getUserAreaid()));//获取县区级下面的镇级地区
						for(int i=0; i < childAreaList.size(); i++){
							userList.addAll(userService.findUserByAreaIdAndType(childAreaList.get(i).getAreaId(),"管理员",primarykey));
							userList.addAll(userService.findUserByAreaIdAndType(childAreaList.get(i).getAreaId(),"普通用户",primarykey));
						}
						setPublicUserList(userList);
					}else{
						userList.addAll(userService.findUserByAreaIdAndType(user.getUserAreaid(),"管理员",primarykey));
						userList.addAll(userService.findUserByAreaIdAndType(user.getUserAreaid(),"普通用户",primarykey));
						setPublicUserList(userList);
					}
				}else{
				//选择了用户的级别条件进行筛选，使用js传入的userAreaId，根据选择的地区、用户类型和关键字进行查询
				if (user.getUserType().equals("超级管理员")) {
					userList.addAll(userService.findUserByAreaIdAndType(
							userAreaId, "超级管理员",primarykey));
					userList.addAll(userService.findUserByAreaIdAndType(
							userAreaId, "管理员",primarykey));
					setPublicUserList(userList);
				} else {
					userList.addAll(userService.findUserByAreaIdAndType(
							userAreaId, "管理员",primarykey));
					userList.addAll(userService.findUserByAreaIdAndType(
							userAreaId, "普通用户",primarykey));
					setPublicUserList(userList);
				}
				}

				int i;
				List<String> areaNameList = new ArrayList<String>();
				int areaId;
				String areaName;
				Area area = new Area();
				for (i = 0; i < userList.size(); i++) {
					areaId = userList.get(i).getUserAreaid();
					area = areaService.getAreaByAreaId(areaId);
					areaName = area.getAreaName();
					while (area.getAreaParentid() != 0) {
						areaId = area.getAreaParentid();
						area = areaService.getAreaByAreaId(areaId);
						areaName = area.getAreaName() + areaName;
					}
					areaNameList.add(areaName);
				}
				setPublicAreaList1(areaNameList);
				 	int recordcount = userList.size();
		            int pageCount;
		            int temp=recordcount%10;
		            if(temp==0){
		            	pageCount = recordcount/10;
		            }else{
		            	pageCount = recordcount/10 +1;
		            }
		           
		            List<User> recordFirst = new ArrayList<User>();
					int max = 0;
					int page = 1;
					max = publicUserList.size()<(10*page)?publicUserList.size():(10*page);
					
					for(int j = (page-1)*10;j<max;j++){
						recordFirst.add(publicUserList.get(j));
					}
					List<String> areaListFirst = new ArrayList<String>();
					int max1 = 0;
					int page1= 1;
					max1 = publicAreaList1.size()<(10*page1)?publicAreaList1.size():(10*page1);
					
					for(int j = (page1-1)*10;j<max1;j++){
						areaListFirst.add(publicAreaList1.get(j));
					}
				map.put("areaListFirst", areaListFirst);
		        map.put("recordFirst", recordFirst);
		        map.put("pageCount", pageCount);
				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				//map.put("userList", userList);
				//map.put("areaNameList", areaNameList);
				map.put("user", user);
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

	// 删除某一用户
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(Integer userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			User deleteUser = new User();
			deleteUser = userService.findUserById(userId);
			//该地区存在的用户数-1
			int areaId = deleteUser.getUserAreaid();
			areaService.editUserNum(areaId, areaService.getAreaByAreaId(areaId).getAreaExistuser()-1);
			System.out.println(areaService.getAreaByAreaId(areaId).getAreaExistuser()+"dddddddddddddddddddd");
			userService.deleteUserAuthByUserId(userId);
			userService.deleteUserByUserId(userId);
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

	// 添加用户
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/addUser")
	public ModelAndView addUser(String userLoginName, String userName,
			String userLoginPwd, String userPhone, String userEmail,
			Integer areaId, String userType, String userLevel,
			String usauAuthId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("rusult", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				User addUserList = new User();
				//System.out.println(userLoginName+"++++++++++++++++++++++");
				addUserList = userService
						.findUserByUserLoginName(userLoginName);
				if (addUserList != null) {
					map.put("result", Boolean.FALSE);
					map.put("message", "该账号已经存在，请重新输入！");
				} else {
					// 判断用户数是否已经达到上线
					Area area = new Area();
					area = areaService.getAreaByAreaId(areaId);
					if (area.getAreaExistuser() >= area.getAreaMaxuser()) {
						map.put("result", Boolean.FALSE);
						map.put("message", "该地区用户数已经达到上线，添加失败！");
					} else {
						Integer userId = user.getUserId();
						userService.addUser(userLoginName, userName,
								userLoginPwd, userPhone, userEmail, areaId,
								userType, userLevel, userId);
						User addUser = new User();
						addUser = userService
								.findUserByUserLoginName(userLoginName);
						int addUserId = addUser.getUserId();
						// System.out.println(addUserId);
						userService.addUserAuthority(addUserId, usauAuthId);
						//该地区存在的用户数+1
						areaService.editUserNum(areaId, areaService.getAreaByAreaId(areaId).getAreaExistuser()+1);
						// map.put("addUserId", addUserId);
						map.put("result", Boolean.TRUE);
						map.put("message", "success");
					}
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

	// 获取当前登录的管理员的级别和地区
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/findCurrentAdminLevel")
	public ModelAndView findCurrentAdminLevel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			System.out.println("!!!!!!!!!!!!!执行了！");
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			// System.out.println(user.getUserId()+user.getUserName()+authList.get(0).getAuthDescibe()+areaName);
			if (user == null) {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				List<String> userLevelList = new ArrayList<String>();
				if (user.getUserType().equals("超级管理员")) {
					userLevelList.add("省级");
					userLevelList.add("市级");
					userLevelList.add("县/区级");
					//userLevelList.add("乡/镇级");
				} else if (user.getUserLevel().equals("县/区级")) {
					userLevelList.add("县/区级");
					userLevelList.add("乡/镇级");
				} else {
					userLevelList.add(user.getUserLevel());
				}
				// 获取用户地区
				Area area = new Area();
				int areaId = user.getUserAreaid();
				List<Area> areaList = new ArrayList<Area>();
				area = areaService.getAreaByAreaId(areaId);
				areaList.add(area);
				while (area.getAreaParentid() != 0) {
					areaId = area.getAreaParentid();
					area = areaService.getAreaByAreaId(areaId);
					areaList.add(area);
				}

				map.put("result", Boolean.TRUE);
				map.put("message", "success");
				map.put("areaList", areaList);
				map.put("userLevelList", userLevelList);
				map.put("user", user);
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
	@RequestMapping("/editUser")
	public ModelAndView editUser(Integer userId, String userLoginName,
			String userName, String userLoginPwd, String userPhone,
			String userEmail, Integer areaId, String userType,
			String userLevel, String usauAuthId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			HttpSession session = request.getSession();
			User user = new User();
			user = (User) session.getAttribute("user");
			if (user == null) {
				map.put("rusult", Boolean.FALSE);
				map.put("message", "用户已经退出！");
			} else {
				User editUserList = new User();
				editUserList = userService
						.findUserByUserLoginName(userLoginName);
				if (editUserList != null
						&& !editUserList.getUserId().equals(userId)) {
					map.put("result", Boolean.FALSE);
					map.put("message", "该账号已经存在，请重新输入！");
				} else {
					User oldUser = new User();
					oldUser = userService.findUserById(userId);//修改之前的用户
					//修改用户表
					Integer userIdCreate = user.getUserId();
					userService.editUser(userId, userLoginName, userName,
							userLoginPwd, userPhone, userEmail, areaId,
							userType, userLevel, userIdCreate);
					// 修改权限,先删除，后添加
					
					userService.deleteUserAuthByUserId(userId);
					userService.addUserAuthority(userId, usauAuthId);
					//当用户地区发生变化时，先判断新地区用户数是否已达上线，若没有则修改地区表的用户个数
					Area oldArea = areaService.getAreaByAreaId(oldUser.getUserAreaid());
					Area newArea = areaService.getAreaByAreaId(areaId);
					if(!areaId.equals(oldUser.getUserAreaid())){
						if(newArea.getAreaExistuser() >= newArea.getAreaMaxuser()){
							map.put("result", Boolean.FALSE);
							map.put("message", "该地区用户数已经达到上线，修改失败！");
							return mav;
						}else{
						    areaService.editUserNum(oldArea.getAreaId(), oldArea.getAreaExistuser()-1);
						    areaService.editUserNum(areaId, newArea.getAreaExistuser()+1);
						}
					}
					//当修改的是管理员自己的信息时，更新登录的用户信息
					if(userId.equals(user.getUserId())){
					    User newUser = new User();
					    newUser = userService.findUserById(userId);
					    request.getSession().setAttribute("user", newUser);//修改session用户信息
					    if(!areaId.equals(oldUser.getUserAreaid())){//用户地区发生变化时，修改session中单位信息
					        String areaName = newArea.getAreaName();
					        while(newArea.getAreaParentid() != 0){
						        newArea = areaService.getAreaByAreaId(newArea.getAreaParentid());
						        areaName =newArea.getAreaName() + areaName;
					        }
					        request.getSession().setAttribute("areaName", areaName);
					    }
					}
							
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
	
	// 设置最大用户数
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/setMaxUserNum")
	public ModelAndView setMaxUserNum(Integer areaId, Integer setMaxUser,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			// 业务逻辑
			areaService.setMaxUserNum(areaId, setMaxUser);
			map.put("result", Boolean.TRUE);
			map.put("message", "设置成功！");
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
	@RequestMapping("/returnCode")
	public ModelAndView returnCode(String username, String email,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		Properties props = new Properties();
		// 设置邮箱服务器地址
		//props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        props.setProperty("mail.smtp.host", "smtp.sfnmail.cn");
		
		// 必须有这项设置
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.debug", "true");

		// Session管理与stmp的链接信息
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              //return new PasswordAuthentication("1611564790@qq.com", "i1try1314?");
                return new PasswordAuthentication("info6@sc-linkton.com.cn", "m6355075k");
            }
        });

		// 由邮件会话新建一个消息对象
		MimeMessage msg = new MimeMessage(session);
		try {
			// System.out.println("+++++++++++++++++++++");
		  //msg.setFrom(new InternetAddress("1611564790@qq.com"));
            msg.setFrom(new InternetAddress("info6@sc-linkton.com.cn"));

			// 生成随机密码
			String srandCode = generateString(6);
			// 将随机密码MD5加密后更新至数据库
			String md5Pwd = getMD5Str(srandCode);
			userService.updatePwd(username, email, md5Pwd);
			// 将随机密码发送到用户邮箱
			User userRecord = new User();
			userRecord = userService.findIsExist(username, email);
			if (userRecord != null) {

				// 群发
				// msg.addRecipients(Message.RecipientType.TO,"xieyang036@126.com,664262076@qq.com");
				/*
				 * User user = new User(); user =
				 * userService.findPasswordByLoginName(username); String pwd =
				 * user.getUserLoginpwd(); String mail = user.getUserEmail();
				 */
				// 单发
				msg.addRecipients(Message.RecipientType.TO, email);

				// 设置邮件主题
				msg.setSubject("找回密码", "UTF-8");
				msg.setSentDate(new Date());

				// 设置邮件内容
				msg.setText("亲爱的用户" + email
						+ "您好，欢迎使用生育服务证管理系统密码找回功能！您登录生育服务证管理系统的密码是："
						+ srandCode + ",请谨记！！", "UTF-8");
				// 使用静态的方法发送信息
				// Transport.send(msg,new InternetAddress[]{new
				// InternetAddress("407629512@qq.com")});
				Transport.send(msg);
				map.put("result", Boolean.TRUE);
				// map.put("msg", msg);
				map.put("message", "success");
			} else {
				map.put("result", Boolean.FALSE);
				map.put("message", "用户名或邮箱错误！");
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
	
	private String getMD5Str(String srandCode) {
		// TODO Auto-generated method stub
		MessageDigest messageDigest = null;   
		   
        try {   
            messageDigest = MessageDigest.getInstance("MD5");   
   
            messageDigest.reset();   
   
            messageDigest.update(srandCode.getBytes("UTF-8"));   
        } catch (NoSuchAlgorithmException e) {   
            System.out.println("NoSuchAlgorithmException caught!");   
            System.exit(-1);   
        } catch (UnsupportedEncodingException e) {   
            e.printStackTrace();   
        }   
   
        byte[] byteArray = messageDigest.digest();   
   
        StringBuffer md5StrBuff = new StringBuffer();   
   
        for (int i = 0; i < byteArray.length; i++) {               
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)   
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
            else   
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
        }   
   
        return md5StrBuff.toString(); 
	}

	String numberChar = "0123456789";
	private String generateString(int length) {
		// TODO Auto-generated method stub
		  StringBuffer sb = new StringBuffer();
		     Random random = new Random();
		     for (int i = 0; i < length; i++)
		     {
		      sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		     }
		    return sb.toString();
		    }

	}
