package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Attachment;

public interface AttachmentMapper {
    int deleteByPrimaryKey(Integer attaId);

    int insert(Attachment record);

    int insertSelective(Attachment record);

    Attachment selectByPrimaryKey(Integer attaId);

    int updateByPrimaryKeySelective(Attachment record);

    int updateByPrimaryKey(Attachment record);
    
    List<Attachment> selectByAnnoId(Integer attaAnnoid);
    
    Attachment selectByAttaPageName(String attaPageName);
    
    int deleteByAnnoId(Integer attaAnnoid);
}