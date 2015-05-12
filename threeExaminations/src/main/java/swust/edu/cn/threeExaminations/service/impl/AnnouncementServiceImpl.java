package swust.edu.cn.threeExaminations.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AnnouncementMapper;
import swust.edu.cn.threeExaminations.model.Announcement;
import swust.edu.cn.threeExaminations.service.AnnouncementService;

@Service("announcementService")
public class  AnnouncementServiceImpl implements AnnouncementService{

	private AnnouncementMapper annoMapper;

	public AnnouncementMapper getAnnoMapper() {
		return annoMapper;
	}

	@Autowired
	public void setAnnoMapper(AnnouncementMapper annoMapper) {
		this.annoMapper = annoMapper;
	}
	
	@SuppressWarnings("finally")
	public Announcement selectAnnoByTitle(String annoTitle){
		Announcement announcement = new Announcement();
		try{
			announcement=annoMapper.selectAnnoByTitle(annoTitle);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return announcement;
		}
	}
	
	@SuppressWarnings("finally")
	public int publishAnnouncement(String title, String content, Integer userId){
		Announcement announcement = new Announcement();
		try{
			announcement.setAnnoTitle(title);
			announcement.setAnnoContent(content);
			announcement.setAnnoUserid(userId);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			announcement.setAnnoPublishtime(timestamp);
			announcement.setAnnoLastmodifytime(timestamp);
			announcement.setAnnoIsdelete(0);
			annoMapper.insert(announcement);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public Announcement selectAnnoByAnnoId(Integer annoId){
		Announcement announcement = new Announcement();
		try{
			announcement = annoMapper.selectByPrimaryKey(annoId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return announcement;
		}
	}
	
	@SuppressWarnings("finally")
	public int editAnnouncement(Integer annoId,String title, String content){
		Announcement announcement = new Announcement();
		try{
			announcement = annoMapper.selectByPrimaryKey(annoId);
			announcement.setAnnoTitle(title);
			announcement.setAnnoContent(content);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			announcement.setAnnoLastmodifytime(timestamp);
			annoMapper.updateByPrimaryKeyWithBLOBs(announcement);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int deleteAnnouncement(Integer annoId){
		Announcement announcement = new Announcement();
		try{
			announcement = annoMapper.selectByPrimaryKey(annoId);
			announcement.setAnnoIsdelete(1);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			announcement.setAnnoLastmodifytime(timestamp);
			annoMapper.updateByPrimaryKeyWithBLOBs(announcement);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Announcement> showAnnoList(Integer userId){
		List<Announcement> annoList = new ArrayList<Announcement>();
		try{
			annoList = annoMapper.selectByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return annoList;
		}
	}
	
	@SuppressWarnings("finally")
	public Announcement showAnnoContent(Integer annoId){
		Announcement announcement = new Announcement();
		try{
			announcement = annoMapper.selectByPrimaryKey(annoId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return announcement;
		}
	}
	

	@SuppressWarnings("finally")
	public List<Announcement> lookAnnoList(){
		List<Announcement> annoList = new ArrayList<Announcement>();
		try{
			annoList = annoMapper.selectAllAnno();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return annoList;
		}
	}
}
