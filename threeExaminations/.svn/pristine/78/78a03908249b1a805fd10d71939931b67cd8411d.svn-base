package swust.edu.cn.threeExaminations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.edu.cn.threeExaminations.dao.AuthorityMapper;
import swust.edu.cn.threeExaminations.model.Authority;
import swust.edu.cn.threeExaminations.service.AuthorityService;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService{
	private AuthorityMapper authMapper;

	public AuthorityMapper getAuthMapper() {
		return authMapper;
	}

	@Autowired
	public void setAuthMapper(AuthorityMapper authMapper) {
		this.authMapper = authMapper;
	}
	
	@SuppressWarnings("finally")
	public List<Authority> findAuthByAuthParentId(Integer authParentId) {
		List<Authority> authList = new ArrayList<Authority>();
		try {
			authList = authMapper.selectAuthByAuthParentId(authParentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return authList;
		}
	}
	
	@SuppressWarnings("finally")
	public Authority findAuthByAuthId(Integer authId) {
		Authority auth = new Authority();
		try {
			auth = authMapper.selectByPrimaryKey(authId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return auth;
		}
	}
}
