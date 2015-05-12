package swust.edu.cn.threeExaminations.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.TaskMapper;
import swust.edu.cn.threeExaminations.model.Task;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService{
	private TaskMapper taskMapper;
	public TaskMapper getTaskMapper() {
		return taskMapper;
	}
	@Autowired
	public void setTaskMapper(TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
	}
	@SuppressWarnings("finally")
	public int publishTask(String year,String batch,Date startTime, 
			Date endTime, String content, Integer userId){
		Task task = new Task();
		try{
			task.setTaskYear(year);
			task.setTaskBatch(batch);
			task.setTaskBegintime(startTime);
			task.setTaskEndtime(endTime);
			task.setTaskContent(content);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			task.setTaskPublishtime(timestamp);
			task.setTaskLastmodifytime(timestamp);
			task.setTaskUserid(userId);
			task.setTaskIsdelete(0);
			taskMapper.insert(task);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	@SuppressWarnings("finally")
	public int deleteTask(Integer taskId){
		Task task = new Task();
		try{
			task = taskMapper.selectByPrimaryKey(taskId);
			task.setTaskIsdelete(1);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			task.setTaskLastmodifytime(timestamp);
		    taskMapper.updateByPrimaryKeyWithBLOBs(task);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int editTask(Integer taskId,String year,String batch,Date startTime, 
			Date endTime, String content){
		Task task = new Task();
		try{
			task = taskMapper.selectByPrimaryKey(taskId);
			task.setTaskYear(year);
			task.setTaskBatch(batch);
			task.setTaskBegintime(startTime);
			task.setTaskEndtime(endTime);
			task.setTaskContent(content);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			task.setTaskLastmodifytime(timestamp);
			taskMapper.updateByPrimaryKeyWithBLOBs(task);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	@SuppressWarnings("finally")
	public List<Task> showTaskList(Integer userId){
		List<Task> taskList = new ArrayList<Task>();
		try{
			taskList = taskMapper.selectByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return taskList;
		}
	}
	
	@SuppressWarnings("finally")
	public Task showTaskContent(Integer taskId){
		Task task = new Task();
		try{
			task = taskMapper.selectByPrimaryKey(taskId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return task;
		}
	}
	@SuppressWarnings("finally")
	public List<Task> lookTaskList(){
		List<Task> taskList = new ArrayList<Task>();
		try{
			taskList = taskMapper.selectAllTask();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return taskList;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Task> findSameTask(String year, String batch, Integer userId){
		List<Task> taskList = new ArrayList<Task>();
		Task task = new Task();
		try{
			task.setTaskYear(year);
			task.setTaskBatch(batch);
			task.setTaskUserid(userId);
			taskList = taskMapper.selectSameTask(task);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return taskList;
		}
	}
}
