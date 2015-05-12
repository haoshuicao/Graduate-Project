package swust.edu.cn.threeExaminations.service;

import java.util.Date;
import java.util.List;

import swust.edu.cn.threeExaminations.model.Task;

public interface TaskService {

	public int publishTask(String year,String batch,Date startTime, 
			Date endTime, String content, Integer userId);
	
	public List<Task> showTaskList(Integer userId);
	
	public int deleteTask(Integer taskId);
	
	public Task showTaskContent(Integer taskId);
	
	public int editTask(Integer taskId,String year,String batch,Date startTime, 
			Date endTime, String content);
	
	public List<Task> lookTaskList();
	
	public List<Task> findSameTask(String year, String batch, Integer userId);
}
