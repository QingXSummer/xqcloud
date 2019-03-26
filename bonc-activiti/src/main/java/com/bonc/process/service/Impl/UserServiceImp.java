package com.bonc.process.service.Impl;

import com.bonc.process.dao.model.ProcessUser;
import com.bonc.process.service.IUserService;
import org.activiti.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {
	
	@Autowired
	private IdentityService identityService;
	
	/**
	 * 传入用户，验证用户是否可以登录
	 * @param user
	 * @return boolean true 成功 false失败
	 */
	@Override
	public boolean userLogind(ProcessUser user) {
		return identityService.checkPassword(user.getUserId(), user.getPassword());
	}

	@Override
	public void addUser(ProcessUser user) {
		org.activiti.engine.identity.User acUser = identityService.newUser(user.getUserId());
		acUser.setFirstName(user.getUserName());
		this.identityService.saveUser(acUser);
	}

	@Override
	public void deleteUser(String userId) {
		this.identityService.deleteUser(userId);
	}
}
