package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Authority;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKeyWithBLOBs(Authority record);

    int updateByPrimaryKey(Authority record);
    
    List<Authority> selectAuthByAuthParentId(Integer authParentid);
}