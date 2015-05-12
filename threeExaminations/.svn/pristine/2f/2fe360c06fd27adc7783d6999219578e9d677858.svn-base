package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Announcement;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer annoId);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer annoId);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKeyWithBLOBs(Announcement record);

    int updateByPrimaryKey(Announcement record);
    
    List<Announcement> selectByUserId(Integer annoUserId);
    
    List<Announcement> selectAllAnno();
    
    Announcement selectAnnoByTitle(String annoTitle);
}