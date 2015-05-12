package swust.edu.cn.threeExaminations.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String userLoginname;

    private String userLoginpwd;

    private String userName;

    private String userEmail;

    private String userPhone;

    private String userType;

    private Integer userCreatorid;

    private Date userCreatetime;

    private Integer userAreaid;
    
    private String userLevel;

    public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLoginname() {
        return userLoginname;
    }

    public void setUserLoginname(String userLoginname) {
        this.userLoginname = userLoginname == null ? null : userLoginname.trim();
    }

    public String getUserLoginpwd() {
        return userLoginpwd;
    }

    public void setUserLoginpwd(String userLoginpwd) {
        this.userLoginpwd = userLoginpwd == null ? null : userLoginpwd.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Integer getUserCreatorid() {
        return userCreatorid;
    }

    public void setUserCreatorid(Integer userCreatorid) {
        this.userCreatorid = userCreatorid;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public Integer getUserAreaid() {
        return userAreaid;
    }

    public void setUserAreaid(Integer userAreaid) {
        this.userAreaid = userAreaid;
    }
}