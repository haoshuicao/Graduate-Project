package swust.edu.cn.threeExaminations.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AreaMapper;
import swust.edu.cn.threeExaminations.dao.AuthorityMapper;
import swust.edu.cn.threeExaminations.dao.UserAuthorityMapper;
import swust.edu.cn.threeExaminations.dao.UserMapper;
import swust.edu.cn.threeExaminations.model.Area;
import swust.edu.cn.threeExaminations.model.Authority;
import swust.edu.cn.threeExaminations.model.User;
import swust.edu.cn.threeExaminations.model.UserAuthority;
import swust.edu.cn.threeExaminations.service.UserService;

/**
 * @author Shaw Joe
 * @Title: 
 * @ClassName：UserServiceImpl.java
 * @Package: swust.edu.cn.threeExaminations.service.impl
 * @Description: TODO
 * @author Shao Zhou
 * @createDate:2013  下午10:54:23    
 * @email:shaozhou@swust.edu.cn
 * @phone:15881615397
 * @Department:Knowledge engineering and Data Mining Lab of Computer Science and Technology Academy of SWUST
 * @Address:Southwest University of  Science and Technology 59 Qinglong Road, Mianyang, 621010, P.R.China
 * @reviseNote:
 * @version:V1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    
	private AuthorityMapper authMapper;
	
    private AreaMapper areaMapper;
    
    private UserAuthorityMapper userAuthMapper;
    
    public AuthorityMapper getAuthMapper() {
		return authMapper;
	}

    @Autowired
	public void setAuthMapper(AuthorityMapper authMapper) {
		this.authMapper = authMapper;
	}
	 
    public AreaMapper getAreaMapper() {
		return areaMapper;
	}

    @Autowired
	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}
    
    public UserAuthorityMapper getUserAuthMapper() {
		return userAuthMapper;
	}

    @Autowired
	public void setUserAuthMapper(UserAuthorityMapper userAuthMapper) {
		this.userAuthMapper = userAuthMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	@SuppressWarnings("finally")
	public User findUserById(int id) {
		User user = new User();
		try{
			user = userMapper.selectByPrimaryKey(id);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> findUserByAreaId(int userAreaId) {
		List<User> userList = new ArrayList<User>();
		try{
			userList = userMapper.findUserByAreaId(userAreaId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			return userList;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> findUserByAreaIdAndType(int userAreaId, String userType,String primarykey) {
		List<User> userList = new ArrayList<User>();
		User user = new User();
		try{
			user.setUserAreaid(userAreaId);
			user.setUserType(userType);
			user.setUserLoginname("%"+primarykey+"%");
			userList = userMapper.findUserByAreaIdAndType(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			return userList;
		}
	}

	@SuppressWarnings("finally")
	public List<User> findUserByUserType(String userType) {
		List<User> allUserList= new ArrayList<User>();
		try{
			allUserList = userMapper.findUserByUserType(userType);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return allUserList;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> findUserByPrimaryAndType(String userType, String primarykey) {
		List<User> userList= new ArrayList<User>();
		User user = new User();
		try{
			user.setUserType(userType);
			user.setUserLoginname("%"+primarykey+"%");
			userList = userMapper.findUserByPrimaryAndType(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return userList;
		}
	}
	
	@SuppressWarnings("finally")
	public User findUserByNAP(String username, String password){
		User user=new User();
//		System.out.println("执行查询的变量");
//		System.out.println(username+password);
		try{
			User user1=new User();
			user1.setUserLoginname(username);
			user1.setUserLoginpwd(password);
		    List<User> list=userMapper.findUserByNAP(user1);
		    if(list.size() > 0){
		    	user = list.get(0);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
//			System.out.println("查询到的结果值");
//			System.out.println(user.getUserLoginname()+user.getUserLoginpwd());
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public List<UserAuthority> findUserAuthByUserId(Integer userId){
		List<UserAuthority> userAuthList = new ArrayList<UserAuthority>();
		try{
			userAuthList = userAuthMapper.selectByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return userAuthList;
		}
	}
	
	@SuppressWarnings("finally")
	public Area findAreaByAreaId(Integer areaId){
		Area area = new Area();
		try{
			area = areaMapper.selectByPrimaryKey(areaId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return area;
		}
	}
	
	@SuppressWarnings("finally")
	public Authority findUserAuthByAuthId(Integer authId){
		Authority auth = new Authority();
		try{
			auth = authMapper.selectByPrimaryKey(authId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return auth;
		}
	}

	@SuppressWarnings("finally")
	public User findUserByUserLoginName(String userLoginName) {
		User user = new User();
		try{
			user = userMapper.findUserByUserLoginName(userLoginName);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return user;
		}
	}
	
	@SuppressWarnings("finally")
	public List<User> findAllUser() {
		List<User> allUserList= new ArrayList<User>();
		try{
			allUserList = userMapper.findAllUser();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return allUserList;
		}
	}
	
	@SuppressWarnings("finally")
	public int deleteUserByUserId(Integer userId) {
		User user = new User();
		try{
			user.setUserId(userId);
			userMapper.deleteByPrimaryKey(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
		
	@SuppressWarnings("finally")
	public int deleteUserAuthByUserId(Integer userId) {
		UserAuthority userAuth = new UserAuthority();
		try{
			userAuth.setUsauUserid(userId);;
			userAuthMapper.deleteByUserId(userAuth);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int addUser(String userLoginName,
			String userName,
			String userLoginPwd,
			String userPhone,
			String userEmail,
			Integer areaId,
			String userType,
			String userLevel,
			Integer userId) {
		User user = new User();
		try{
			user.setUserLoginname(userLoginName);
			user.setUserName(userName);
			user.setUserLoginpwd(userLoginPwd);
			user.setUserPhone(userPhone);
			user.setUserEmail(userEmail);
			user.setUserAreaid(areaId);
			user.setUserType(userType);
			user.setUserLevel(userLevel);
			user.setUserCreatorid(userId);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setUserCreatetime(timestamp);
			userMapper.insert(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int addUserAuthority(Integer usauUserId ,String usauAuthId) {
		UserAuthority userAuth = new UserAuthority();
		usauAuthId.substring(0, usauAuthId.length() - 2);
		String []termIdString=usauAuthId.split(",");
		int []termIdInt=new int [100];
		try {
			//System.out.println(usauUserId+usauAuthId+"ggggggggg");
			/*userAuth.setUsauUserid(42);
			userAuth.setUsauAuthid(5);
			userAuthMapper.insert(userAuth);*/
			for(int i = 0; i < termIdString.length; i++){
				termIdInt[i] = Integer.parseInt(termIdString[i]);
			}
			for(int i = 0; i < termIdInt.length; i++){
				userAuth.setUsauUserid(usauUserId);
				userAuth.setUsauAuthid(termIdInt[i]);
				userAuthMapper.insert(userAuth);
				//System.out.println(usauUserId+termIdInt[i]+"ffffffff");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return 0;
		}
	}
	
	@SuppressWarnings("finally")
	public int findExitUserNum(Integer areaId) {
		int exitUserNum = 0;
		try{
			//System.out.println(areaId);
			User user = new User();
			user.setUserAreaid(areaId);
			//System.out.println(area.getAreaId()+"++++++++++++");
			exitUserNum=userMapper.findExitUserNum(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return exitUserNum;
		}
	}
	
	@SuppressWarnings("finally")
	public int editUser(Integer userId,String userLoginName,
			String userName,
			String userLoginPwd,
			String userPhone,
			String userEmail,
			Integer areaId,
			String userType,
			String userLevel,Integer userIdCreate) {
		User user = new User();
		try{
			user = userMapper.selectByPrimaryKey(userId);
			user.setUserLoginname(userLoginName);
			user.setUserName(userName);
			user.setUserLoginpwd(userLoginPwd);
			user.setUserPhone(userPhone);
			user.setUserEmail(userEmail);
			user.setUserAreaid(areaId);
			user.setUserType(userType);
			user.setUserLevel(userLevel);
			user.setUserCreatorid(userIdCreate);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setUserCreatetime(timestamp);
			userMapper.updateByPrimaryKey(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return 0;
		}
	}

	@SuppressWarnings("finally")
	public User findPasswordByLoginName(String username) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			user.setUserLoginname(username);
			user = userMapper.findPassword(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return user;
		}
	
	}

	public void updatePwd(String username, String email, String md5Pwd) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			user.setUserLoginname(username);
			user.setUserEmail(email);
			user.setUserLoginpwd(md5Pwd);
			user = userMapper.updatePwd(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("finally")
	public User findIsExist(String username, String email) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			user.setUserLoginname(username);
			user.setUserEmail(email);
			user = userMapper.findIsExist(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return user;
		}
	}
}
