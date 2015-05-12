package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.UserAuthority;

public interface UserAuthorityMapper {
    int deleteByPrimaryKey(Integer usauId);

    int insert(UserAuthority record);

    int insertSelective(UserAuthority record);

    UserAuthority selectByPrimaryKey(Integer usauId);

    int updateByPrimaryKeySelective(UserAuthority record);

    int updateByPrimaryKey(UserAuthority record);
    
    List<UserAuthority> selectByUserId(Integer userId);
    
    int deleteByUserId(UserAuthority record);
}