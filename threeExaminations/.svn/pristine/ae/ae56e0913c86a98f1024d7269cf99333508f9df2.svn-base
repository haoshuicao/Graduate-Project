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

import swust.edu.cn.threeExaminations.model.Authority;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AuthorityService;

@Controller
@RequestMapping("/authorityController")
public class AuthorityController {
	private AuthorityService authService;

	public AuthorityService getAuthService() {
		return authService;
	}

	@Autowired
	public void setAuthService(AuthorityService authService) {
		this.authService = authService;
	}

	// 查询可分配的权限
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/searchUserAuthority")
	public ModelAndView searchUserAuthority(Integer authParentId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			if(authParentId == 0){
			// 查询全部权限id>0的所有权限
			List<Authority> parentAuthList = authService
					.findAuthByAuthParentId(authParentId);
			List<Authority> authList = new ArrayList<Authority>();
			// System.out.println(authList.get(0).getAuthName()+"hhhhhhhhhhh");
			// 业务逻辑
			for (int i = 0; i < parentAuthList.size(); i++) {
				List<Authority> authListi = new ArrayList<Authority>();
				Integer parentAuthId = parentAuthList.get(i).getAuthId();
				authListi = authService.findAuthByAuthParentId(parentAuthId);
				if (authListi.size() > 0) {
					for (int j = 0; j < authListi.size(); j++) {
						// 有效子权限
						if (!authListi.get(j).getAuthDescibe().equals("#")) {
							authList.add(authListi.get(j));
						}
					}
				}
			}
			map.put("parentAuthList", parentAuthList);
			map.put("authList", authList);
			map.put("result", Boolean.TRUE);
			map.put("message", "查询权限成功！");
			}else{
				List<Authority> authListi = authService.findAuthByAuthParentId(authParentId);
				List<Authority> authList = new ArrayList<Authority>();
				if (authListi.size() > 0) {
					for (int j = 0; j < authListi.size(); j++) {
						// 有效子权限
						if (!authListi.get(j).getAuthDescibe().equals("#")) {
							authList.add(authListi.get(j));
						}
					}
				}
				map.put("authList", authList);
				map.put("result", Boolean.TRUE);
				map.put("message", "success！");
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
	
	//查询创建某级别用户时可分配的权限
	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/showCurrentLevelAuthority")
	public ModelAndView showCurrentLevelAuthority(String level,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			List<Authority> authList = new ArrayList<Authority>();
			if(level.equals("省级") || level.equals("市级")){
				authList.add(authService.findAuthByAuthId(7));//发布公告信息的权限
				authList.add(authService.findAuthByAuthId(8));//管理公告信息的权限
				authList.add(authService.findAuthByAuthId(33));//查看公告信息的权限
				authList.addAll(authService.findAuthByAuthParentId(10));//三查统计的组权限下的所有子权限
			}else if(level.equals("县/区级")){
				authList.addAll(authService.findAuthByAuthParentId(1));//发布公告任务管理组权限下的所有子权限
				authList.add(authService.findAuthByAuthId(32));//查看任务信息的权限
				authList.add(authService.findAuthByAuthId(33));//查看公告信息的权限
				authList.add(authService.findAuthByAuthId(22));//查看服务记录的权限
				authList.addAll(authService.findAuthByAuthParentId(10));//三查统计的组权限下的所有子权限
			}else if(level.equals("乡/镇级")){
				authList.add(authService.findAuthByAuthId(32));//查看任务信息的权限
				authList.add(authService.findAuthByAuthId(33));//查看公告信息的权限
				authList.addAll(authService.findAuthByAuthParentId(9));//三查开展组权限下的所有子权限
				authList.addAll(authService.findAuthByAuthParentId(10));//三查统计的组权限下的所有子权限
			}else{//超级管理员
				authList.add(authService.findAuthByAuthId(35));//设置最大用户数
				authList.addAll(authService.findAuthByAuthParentId(34));//管理员权限组下的所有子权限
			}
			map.put("authList", authList);
			map.put("result", Boolean.TRUE);
			map.put("message", "success！");
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
