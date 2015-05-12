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
			List<Authority> parentAuthList = authService
					.findAuthByAuthParentId(authParentId);
			List<Authority> authList = new ArrayList<Authority>();
			for (int i = 0; i < parentAuthList.size(); i++) {
				List<Authority> authListi = new ArrayList<Authority>();
				Integer parentAuthId = parentAuthList.get(i).getAuthId();
				authListi = authService.findAuthByAuthParentId(parentAuthId);
				if (authListi.size() > 0) {
					for (int j = 0; j < authListi.size(); j++) {
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
				authList.add(authService.findAuthByAuthId(7));
				authList.add(authService.findAuthByAuthId(8));
				authList.add(authService.findAuthByAuthId(33));
				authList.addAll(authService.findAuthByAuthParentId(10));
			}else if(level.equals("县/区级")){
				authList.addAll(authService.findAuthByAuthParentId(1));
				authList.add(authService.findAuthByAuthId(32));
				authList.add(authService.findAuthByAuthId(33));
				authList.add(authService.findAuthByAuthId(22));
				authList.addAll(authService.findAuthByAuthParentId(10));
			}else if(level.equals("乡/镇级")){
				authList.add(authService.findAuthByAuthId(32));
				authList.add(authService.findAuthByAuthId(33));
				authList.addAll(authService.findAuthByAuthParentId(9));
				authList.addAll(authService.findAuthByAuthParentId(10));
			}else{
				authList.add(authService.findAuthByAuthId(35));
				authList.addAll(authService.findAuthByAuthParentId(34));
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
