package swust.edu.cn.threeExaminations.service;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Announcement;

public interface AnnouncementService {

	public int publishAnnouncement(String title, String content, Integer userId);
	
	public List<Announcement> showAnnoList(Integer userId);
	
	public Announcement showAnnoContent(Integer annoId);
	
	public int editAnnouncement(Integer annoId,String title, String content);
	
	public int deleteAnnouncement(Integer annoId);
	
	public List<Announcement> lookAnnoList();
	
	public Announcement selectAnnoByTitle(String annoTitle);
	
	public Announcement selectAnnoByAnnoId(Integer annoId);
}
