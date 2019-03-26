package com.bonc.process.service;


import com.bonc.process.dao.model.ProcessUser;

/**
 * 用户接口
 * @author xiaqing
 *
 * Created on  2017年12月26日   下午2:15:43
 */
public interface IUserService {
	
	/**
	 * 验证用户登录
	 * @param user
	 * @return
	 */
	boolean userLogind(ProcessUser user);
	
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(ProcessUser user);
	
	/**
	 * 删除用户
	 */
	void deleteUser(String userId);
}
