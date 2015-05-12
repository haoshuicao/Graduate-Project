package swust.edu.cn.threeExaminations.controller;

import org.springframework.beans.factory.annotation.Autowired;

import swust.edu.cn.threeExaminations.service.AttachmentService;

public class AttachmentController {

	private AttachmentService attachmnetService;

	public AttachmentService getAttachmnetService() {
		return attachmnetService;
	}
	@Autowired
	public void setAttachmnetService(AttachmentService attachmnetService) {
		this.attachmnetService = attachmnetService;
	}
}
