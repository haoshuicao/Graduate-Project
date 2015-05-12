package swust.edu.cn.threeExaminations.dao;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findUserByNAP(User user);

	User findUserByUserLoginName(String userLoginname);
	
	List<User> findAllUser();
	
	List<User> findUserByAreaId(Integer userAreaid);
	
	List<User> findUserByUserType(String userType);
	
	List<User> findUserByAreaIdAndType(User record);
	
	List<User> findUserByPrimaryAndType(User record);
	
	int findExitUserNum(User user);

	User findPassword(User user);

	User updatePwd(User user);

	User findIsExist(User user);
 }