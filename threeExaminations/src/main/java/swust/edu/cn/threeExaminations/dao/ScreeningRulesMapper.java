package swust.edu.cn.threeExaminations.dao;

import swust.edu.cn.threeExaminations.model.ScreeningRules;

public interface ScreeningRulesMapper {
    int deleteByPrimaryKey(Integer scruId);

    int insert(ScreeningRules record);

    int insertSelective(ScreeningRules record);

    ScreeningRules selectByPrimaryKey(Integer scruId);

    int updateByPrimaryKeySelective(ScreeningRules record);

    int updateByPrimaryKey(ScreeningRules record);
}