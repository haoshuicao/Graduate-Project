package swust.edu.cn.threeExaminations.model;

import java.util.Date;

public class Task {
    private Integer taskId;

    private String taskYear;

    private String taskBatch;

    private Date taskBegintime;

    private Date taskEndtime;

    private Date taskPublishtime;

    private Date taskLastmodifytime;

    private Integer taskIsdelete;

    private Integer taskUserid;

    private String taskContent;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(String taskYear) {
        this.taskYear = taskYear == null ? null : taskYear.trim();
    }

    public String getTaskBatch() {
        return taskBatch;
    }

    public void setTaskBatch(String taskBatch) {
        this.taskBatch = taskBatch == null ? null : taskBatch.trim();
    }

    public Date getTaskBegintime() {
        return taskBegintime;
    }

    public void setTaskBegintime(Date taskBegintime) {
        this.taskBegintime = taskBegintime;
    }

    public Date getTaskEndtime() {
        return taskEndtime;
    }

    public void setTaskEndtime(Date taskEndtime) {
        this.taskEndtime = taskEndtime;
    }

    public Date getTaskPublishtime() {
        return taskPublishtime;
    }

    public void setTaskPublishtime(Date taskPublishtime) {
        this.taskPublishtime = taskPublishtime;
    }

    public Date getTaskLastmodifytime() {
        return taskLastmodifytime;
    }

    public void setTaskLastmodifytime(Date taskLastmodifytime) {
        this.taskLastmodifytime = taskLastmodifytime;
    }

    public Integer getTaskIsdelete() {
        return taskIsdelete;
    }

    public void setTaskIsdelete(Integer taskIsdelete) {
        this.taskIsdelete = taskIsdelete;
    }

    public Integer getTaskUserid() {
        return taskUserid;
    }

    public void setTaskUserid(Integer taskUserid) {
        this.taskUserid = taskUserid;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }
}