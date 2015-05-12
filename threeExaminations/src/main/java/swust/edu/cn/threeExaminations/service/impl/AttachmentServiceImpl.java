package swust.edu.cn.threeExaminations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AttachmentMapper;
import swust.edu.cn.threeExaminations.model.Attachment;
import swust.edu.cn.threeExaminations.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	private AttachmentMapper attaMapper;
	public AttachmentMapper getAttaMapper() {
		return attaMapper;
	}
	@Autowired
	public void setAttaMapper(AttachmentMapper attaMapper) {
		this.attaMapper = attaMapper;
	}
	@SuppressWarnings("finally")
	public int addAttachment(Integer attaAnnoid,String systemName, String pageName,String path) {
		Attachment attachment = new Attachment();
		try {
			attachment.setAttaAnnoid(attaAnnoid);
			attachment.setAttaUploadpath(path);
			attachment.setAttaSystemname(systemName);;
			attachment.setAttaPagename(pageName);
			attaMapper.insert(attachment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return 0;
		}
	}
	@SuppressWarnings("finally")
	public List<Attachment> findAttaByAnnoId(Integer attaAnnoid) {
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		try {
			attachmentList=attaMapper.selectByAnnoId(attaAnnoid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return attachmentList;
		}
	}
	@SuppressWarnings("finally")
	public Attachment findAttaByAttaId(Integer attaId) {
		Attachment attachment = new Attachment();
		try {
			attachment=attaMapper.selectByPrimaryKey(attaId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return attachment;
		}
	}
	@SuppressWarnings("finally")
	public Attachment findAttaByAttaPageName(String attaPageName) {
		Attachment attachment = new Attachment();
		try {
			attachment=attaMapper.selectByAttaPageName(attaPageName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return attachment;
		}
	}
	@SuppressWarnings("finally")
	public int deleteAttaByAnnoId(Integer attaAnnoid) {
		try {
			attaMapper.deleteByAnnoId(attaAnnoid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return 0;
		}
	}
	@SuppressWarnings("finally")
	public int deleteAttaByAttaId(Integer attaId) {
		try {
			attaMapper.deleteByPrimaryKey(attaId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return 0;
		}
	}
}
