package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Task;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKeyWithBLOBs(Task record);

    int updateByPrimaryKey(Task record);
    
    List<Task> selectByUserId(Integer taskUserid);
    
    List<Task> selectAllTask();
    
    List<Task> selectSameTask(Task record);
}