package swust.edu.cn.threeExaminations.service;

import java.util.List;

import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.Authority;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.model.UserAuthority;

/**
 * @author Shaw Joe
 * @Title:
 * @ClassName：UserService.java
 * @Package: swust.edu.cn.threeExaminations.service.impl
 * @Description: TODO
 * @author Shao Zhou
 * @createDate:2013 下午10:53:46
 * @email:shaozhou@swust.edu.cn
 * @phone:15881615397
 * @Department:Knowledge engineering and Data Mining Lab of Computer Science and
 *                       Technology Academy of SWUST
 * @Address:Southwest University of Science and Technology 59 Qinglong Road,
 *                    Mianyang, 621010, P.R.China
 * @reviseNote:
 * @version:V1.0
 */
public interface UserService {
	public User findUserById(int id);

	public User findUserByNAP(String username, String password);

	public List<UserAuthority> findUserAuthByUserId(Integer userId);

	public Authority findUserAuthByAuthId(Integer authId);
	
	public Area findAreaByAreaId(Integer areaId);

	public User findUserByUserLoginName(String userLoginName);

	public List<User> findAllUser();
	
	public List<User> findUserByUserType(String userType);
	
	public List<User> findUserByAreaId(int userAreaId);
	
	public List<User> findUserByAreaIdAndType(int userAreaId, String userType ,String primarykey);

	public List<User> findUserByPrimaryAndType(String userType, String primarykey);
	
	public int deleteUserByUserId(Integer userId);
	
	public int deleteUserAuthByUserId(Integer userId);

	public int addUser(String userLoginName, String userName,
			String userLoginPwd, String userPhone, String userEmail,
			Integer areaId, String userType, String userLevel, Integer userId);

	public int addUserAuthority(Integer usauUserId, String usauAuthId);
	
	public int findExitUserNum(Integer areaId);

	public int editUser(Integer userId,String userLoginName, String userName,
			String userLoginPwd, String userPhone, String userEmail,
			Integer areaId, String userType,String userLevel, Integer userIdCreate);

	public User findPasswordByLoginName(String username);

	public void updatePwd(String username, String email, String md5Pwd);

	public User findIsExist(String username, String email);
}
