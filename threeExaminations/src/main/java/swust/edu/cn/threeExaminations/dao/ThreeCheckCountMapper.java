package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.ThreeCheckCount;

public interface ThreeCheckCountMapper {
    int deleteByPrimaryKey(Integer thccId);

    int insert(ThreeCheckCount record);

    int insertSelective(ThreeCheckCount record);

    ThreeCheckCount selectByPrimaryKey(Integer thccId);

    int updateByPrimaryKeySelective(ThreeCheckCount record);

    int updateByPrimaryKey(ThreeCheckCount record);

	List<ThreeCheckCount> searchBySelect1(ThreeCheckCount threeCheckCount);

	List<ThreeCheckCount> searchBySelect2(ThreeCheckCount threeCheckCount);

	List<ThreeCheckCount> searchBySelect3(ThreeCheckCount threeCheckCount);

	List<ThreeCheckCount> searchBySelect4(ThreeCheckCount threeCheckCount);

	void updateInfo(ThreeCheckCount threeCheckCount);

	ThreeCheckCount findRecord(ThreeCheckCount readRecord);
}