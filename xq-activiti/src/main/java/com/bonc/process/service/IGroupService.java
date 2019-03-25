package com.bonc.process.service;

import java.util.List;
import java.util.Map;

/**
 * 用于处理组和用户间的关系
 * @author xiaqing
 *
 * Created on  2017年12月26日   上午11:29:21
 */
public interface IGroupService {
	
	/**
	 * 添加组信息
	 * @param groupId
	 * @param groupName
	 */
	void addGroup(String groupId, String groupName);

	/**
	 * 删除组信息
	 * @param groupId
	 */
	void deleteGroup(String groupId);

	/**
	 * 添加用户和组的关系
	 * @param userIds 用户集合
	 */
	void addUser2Group(List <String> userIds, String groupId);

	/**
	 * 删除组和用户的关系
	 * @param userIds 用户集合
	 */
	void deleteGroupUser(List <String> userIds, String groupId);
	
	
	List<Map<String,Object>> countUserGroup(String userId);
}
