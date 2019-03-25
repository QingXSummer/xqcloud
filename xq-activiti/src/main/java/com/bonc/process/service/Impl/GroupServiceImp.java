package com.bonc.process.service.Impl;

import com.bonc.process.service.IGroupService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理组的实现类
 * @author xiaqing
 *
 * Created on  2017年12月26日   上午11:43:01
 */
@Service
public class GroupServiceImp implements IGroupService{

	@Autowired
	private IdentityService identityService;
	
	@Override
	public void addGroup(String groupId, String groupName) {
		Group group = this.identityService.newGroup(groupId);
		group.setName(groupName);
		this.identityService.saveGroup(group);
	}

	@Override
	public void deleteGroup(String groupId) {
		this.identityService.deleteGroup(groupId);
	}

	@Override
	public void addUser2Group(List<String> userIds, String groupId) {
		for (String userId : userIds) {
			this.identityService.createMembership(userId, groupId);
		}
	}

	@Override
	public void deleteGroupUser(List<String> userIds, String groupId) {
		for (String userId : userIds) {
			this.identityService.deleteMembership(userId, groupId);
		}
	}

	@Override
	public List<Map<String, Object>> countUserGroup(String userId) {
		List<Group> list = identityService.createGroupQuery().list();
		List<Group> list2 = identityService.createGroupQuery().groupMember(userId).list();
		List<Map<String, Object>> grouplist= new ArrayList<>();
		for(Group group : list){
			Map<String, Object> map = new HashMap<>();
			map.put("name", group.getName());
			map.put("id", group.getId());
			map.put("flag", false);
			grouplist.add(map);
		}
		for(Group group : list2){
			Map<String, Object> map = new HashMap<>();
			map.put("name", group.getName());
			map.put("id", group.getId());
			map.put("flag", true);
			grouplist.add(map);
		}
		return grouplist;
	}

}
