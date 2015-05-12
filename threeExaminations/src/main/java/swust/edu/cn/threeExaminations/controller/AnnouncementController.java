package swust.edu.cn.threeExaminations.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import swust.edu.cn.threeExaminations.model.Announcement;
import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.Attachment;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.AnnouncementService;
import swust.edu.cn.threeExaminations.service.AreaService;
import swust.edu.cn.threeExaminations.service.AttachmentService;
import swust.edu.cn.threeExaminations.service.UserService;

@Controller
@RequestMapping("/announcementController")
public class AnnouncementController {
	List<Announcement> publicAnnoList = new ArrayList<Announcement>();

	public void setPublicAnnoList(List<Announcement> publicAnnoList) {
		this.publicAnnoList = publicAnnoList;
	}

	private AnnouncementService annoService;

	private UserService userService;

	private AreaService areaService;

	private AttachmentService attaService;

	public AttachmentService getAttaService() {
		return attaService;
	}

	@Autowired
	public void setAttaService(AttachmentService attaService) {
		this.attaService = attaService;
	}

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

	public AnnouncementService getAnnoService() {
		return annoService;
	}

	@Autowired
	public void setAnnoService(AnnouncementService annoService) {
		this.annoService = annoService;
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/publishAnnouncement")
	public ModelAndView publishAnnouncement(String title, String content,
			String attaSystemName, String attaPageName,HttpServletRequest request,
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
				Announcement announcement = new Announcement();
				announcement = annoService.selectAnnoByTitle(title);
				if (announcement != null) {
					map.put("result", Boolean.FALSE);
					map.put("message", "已存在该主题的公告，请重新命名！");
				} else {
					annoService.publishAnnouncement(title, content,
							user.getUserId());
					announcement = annoService.selectAnnoByTitle(title);
					int annoId = announcement.getAnnoId();
					
					if (attaPageName.length() > 0) {
						attaPageName.substring(0, attaPageName.length() - 2);
						attaSystemName.substring(0,attaSystemName.length()-2);
						String[] termIdStringP = attaPageName.split(",");
						String[] termIdStringS = attaSystemName.split(",");
						for (int i = 0; i < termIdStringP.length; i++) {
							attaService.addAttachment(annoId,  termIdStringS[i],
									termIdStringP[i],"/upload2/"+ termIdStringS[i]);
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

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping("/editAnnouncement")
	public ModelAndView editAnnouncement(Integer annoId, String title,
			String content, String attaSystemName, String attaPageName, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Announcement announcement = new Announcement();
			announcement = annoService.selectAnnoByTitle(title);
			if (announcement != null && !announcement.getAnnoId().equals(annoId)) {
				map.put("result", Boolean.FALSE);
				map.put("message", "已存在该主题的公告，请重新命名！");
			} else {
			annoService.editAnnouncement(annoId, title, content);
			attaService.deleteAttaByAnnoId(annoId);
			if (attaPageName.length() > 0) {
				attaPageName.substring(0, attaPageName.length() - 2);
				attaSystemName.substring(0, attaSystemName.length() - 2);
				String[] termIdStringP = attaPageName.split(",");
				String[] termIdStringS = attaSystemName.split(",");
				for (int i = 0; i < termIdStringP.length; i++) {
					attaService.addAttachment(annoId,termIdStringS[i], 
							termIdStringP[i],"/upload2/" + termIdStringS[i]);
				}
			}
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
	@RequestMapping("/deleteAttachment")
	public ModelAndView deleteAttachment(Integer attaId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Announcement anno = new Announcement();
			Attachment atta = new Attachment();
			atta = attaService.findAttaByAttaId(attaId);
			anno = annoService.selectAnnoByAnnoId(atta.getAttaAnnoid());
			attaService.deleteAttaByAttaId(attaId);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
			map.put("annoucemnet", anno);
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
	@RequestMapping("/deleteAnnouncement")
	public ModelAndView deleteAnnouncement(Integer annoId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			annoService.deleteAnnouncement(annoId);
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
	@RequestMapping("/showAnnoList")
	public ModelAndView showAnnoList(HttpServletRequest request,
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
				List<Announcement> annoList = new ArrayList<Announcement>();
				annoList = annoService.showAnnoList(user.getUserId());
				if (annoList.size() > 0) {
					setPublicAnnoList(annoList);
					int recordCount = annoList.size();
					int pageCount;
					int temp = recordCount % 10;
					if(temp == 0){
						pageCount = recordCount/10;
					}else{
						pageCount = recordCount/10 + 1;
					}
					List<Announcement> recordFirst = new ArrayList<Announcement>();
					int max = 0;
					int page = 1;
					max = publicAnnoList.size()<(10*page)?publicAnnoList.size():(10*page);
					for(int j = (page-1)*10;j<max;j++){
						recordFirst.add(publicAnnoList.get(j));
					}
					map.put("annoRecordFirst", recordFirst);
					map.put("annoList", annoList);
					map.put("pageCount", pageCount);
					map.put("result", Boolean.TRUE);
					map.put("message", "success");
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "还未发布公告，公告列表为空！");
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
					List<Announcement> recordCurrent = new ArrayList<Announcement>();
					int max = 0;
					max = publicAnnoList.size()<(10*page)?publicAnnoList.size():(10*page);
					for(int j = (page-1)*10;j<max;j++){
						recordCurrent.add(publicAnnoList.get(j));
					}
					map.put("annoRecordCurrent", recordCurrent);
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
	@RequestMapping("/showAnnoContent")
	public ModelAndView showAnnoContent(Integer annoId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Announcement announcement = new Announcement();
			announcement = annoService.showAnnoContent(annoId);

			Date lastModiftTime = announcement.getAnnoLastmodifytime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(lastModiftTime);

			User annoUser = new User();
			int annoUserId = announcement.getAnnoUserid();
			annoUser = userService.findUserById(annoUserId);
			int areaId = annoUser.getUserAreaid();
			Area area = new Area();
			area = areaService.getAreaByAreaId(areaId);
			String areaName = area.getAreaName();
			while (area.getAreaParentid() != 0) {
				areaId = area.getAreaParentid();
				area = areaService.getAreaByAreaId(areaId);
				areaName = area.getAreaName() + areaName;
			}

			List<Attachment> attaList = new ArrayList<Attachment>();
			attaList = attaService.findAttaByAnnoId(annoId);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
			map.put("announcement", announcement);
			map.put("date", date);
			map.put("publishUnit", areaName);
			map.put("publishUser", annoUser.getUserName());
			map.put("attaList", attaList);
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
	@RequestMapping("/lookAnnoList")
	public ModelAndView lookAnnoList(HttpServletRequest request,
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
				List<Announcement> annoList = new ArrayList<Announcement>();
				List<Announcement> annoList1 = new ArrayList<Announcement>();
				annoList1 = annoService.lookAnnoList();
				if (annoList1.size() > 0) {
					int areaId;
					Area area = new Area();
					List<Area> areaList = new ArrayList<Area>();
					areaId=user.getUserAreaid();
					area = areaService.getAreaByAreaId(areaId);
					areaList.add(area);
					while(area.getAreaParentid() != 0){
						areaId = area.getAreaParentid();
						area = areaService.getAreaByAreaId(areaId);
						areaList.add(area);
					}
					
					int i,j;
					User annoUser = new User();
					Area annoArea = new Area();
					for (i = 0; i < annoList1.size(); i++) {
						annoUser = userService.findUserById(annoList1.get(i)
								.getAnnoUserid());
						annoArea = areaService.getAreaByAreaId(annoUser
								.getUserAreaid());
						for(j=0;j<areaList.size();j++){
							if(annoArea.getAreaId().equals(areaList.get(j).getAreaId())){
								annoList.add(annoList1.get(i));
								break;
							}
						}
					}
					if (annoList.size() == 0) {
						map.put("result", Boolean.FALSE);
						map.put("message", "还未发布公告，公告列表为空！");
					} else {
						setPublicAnnoList(annoList);
						int recordCount = annoList.size();
						int pageCount;
						int temp = recordCount % 10;
						if(temp == 0){
							pageCount = recordCount/10;
						}else{
							pageCount = recordCount/10 + 1;
						}
						List<Announcement> recordFirst = new ArrayList<Announcement>();
						int max = 0;
						int page = 1;
						max = publicAnnoList.size()<(10*page)?publicAnnoList.size():(10*page);
						for(int k = (page-1)*10;k<max;k++){
							recordFirst.add(publicAnnoList.get(k));
						}
						Announcement newAnno = annoList
								.get(0);
						Date lastModiftTime = newAnno.getAnnoLastmodifytime();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String date = sdf.format(lastModiftTime);
						User annoUserNew = new User();
						int annoUserId = newAnno.getAnnoUserid();
						annoUserNew = userService.findUserById(annoUserId);
						int areaIdNew = annoUserNew.getUserAreaid();
						Area areaNew = new Area();
						areaNew = areaService.getAreaByAreaId(areaIdNew);
						String areaName = areaNew.getAreaName();
						while (areaNew.getAreaParentid() != 0) {
							areaIdNew = areaNew.getAreaParentid();
							areaNew = areaService.getAreaByAreaId(areaIdNew);
							areaName = areaNew.getAreaName() + areaName;
						}
						
						List<Attachment> attaList = new ArrayList<Attachment>();
						attaList = attaService.findAttaByAnnoId(newAnno
								.getAnnoId());
						map.put("annoList", recordFirst);
						map.put("pageCount", pageCount);
						map.put("result", Boolean.TRUE);
						map.put("message", "success");
						map.put("newAnno", newAnno);
						map.put("date", date);
						map.put("newUnit", areaName);
						map.put("publishUser", annoUserNew.getUserName());
						map.put("attaList", attaList);
					}
				} else {
					map.put("result", Boolean.FALSE);
					map.put("message", "还未发布公告，公告列表为空！");
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
	@RequestMapping(value = "/uplaodAttachment", method = RequestMethod.POST)
	public ModelAndView uplaodAttachment(MultipartFile postfile,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			String oriFileName = postfile.getOriginalFilename();
			String extension=oriFileName.substring(oriFileName.lastIndexOf(".")).toLowerCase();
			String newFileName = System.currentTimeMillis()+extension;
			String projectPath = request.getSession().getServletContext()
					.getRealPath("");
			
			String path = projectPath.substring(0, projectPath.indexOf("threeExaminations")-1);
			path = path + "/upload2/" + newFileName;
			System.out.println(path);
			postfile.transferTo(new File(path));
			
			map.put("pageName", oriFileName);
			map.put("systemName", newFileName);
			map.put("path", path);
			map.put("result", Boolean.TRUE);
			map.put("message", "success");
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！!!!!");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			mav.toString();
			return mav;
		}
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping(value = "/downloadAttachment", method = RequestMethod.GET)
	public ModelAndView downloadAttachment(Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			Attachment attachment = new Attachment();
			attachment = attaService.findAttaByAttaId(id);

			String projectPath = request.getSession().getServletContext()
					.getRealPath("");
			String path = projectPath.substring(0, projectPath.indexOf("threeExaminations")-1);
			path = path + attachment.getAttaUploadpath();
			File file = new File(path);
			
			String filename = attachment.getAttaPagename();
			System.out.println(filename+"文件名");
			
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("GBK"),"ISO8859_1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！!!!!");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			mav.toString();
			return mav;
		}
	}

	@SuppressWarnings({ "finally", "rawtypes", "unchecked" })
	@RequestMapping(value = "/downloadAttachment2", method =RequestMethod.GET)
	public ModelAndView downloadAttachment2(String downloadPath,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		MappingJacksonJsonView view = new MappingJacksonJsonView();
		Map map = new HashMap();
		try {
			
			String[] m=downloadPath.split(",");
			String systemName = m[0];
			String filename = m[1];
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/"+systemName);
			File file = new File(path);
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("GBK"),"ISO8859_1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			map.put("result", Boolean.FALSE);
			map.put("message", "执行出现出错！!!!!");
			e.printStackTrace();
		} finally {
			view.setAttributesMap(map);
			mav.setView(view);
			mav.toString();
			return mav;
		}
	}

}
