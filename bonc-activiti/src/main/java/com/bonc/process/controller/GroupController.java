//package com.bonc.process.controller;
//
//import com.deepcloud.portal.dao.model.TrbacTenant;
//import com.deepcloud.portal.dao.model.TrbacTenantExample;
//import com.deepcloud.portal.dao.model.TrbacUser;
//import com.deepcloud.portal.dao.model.TrbacUserExample;
//import com.deepcloud.portal.rpc.api.TrbacTenantService;
//import com.deepcloud.portal.rpc.api.TrbacUserService;
//import com.bonc.process.service.IGroupService;
//import com.bonc.process.util.MsgReturn;
//import org.activiti.engine.IdentityService;
//import org.activiti.engine.identity.Group;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/group")
//public class GroupController {
//
//	@Autowired
//	private IGroupService iGroupService;
//	@Autowired
//	private TrbacUserService trbacUserService;
//	@Autowired
//	private TrbacTenantService trbacTenantService;
//	@Autowired
//	private IdentityService identityService;
//
//	/**
//	 * 获取所有用户的组织机构（ace fuelux tree）
//	 * @return
//	 */
////	@RequestMapping(value = "/countAllUser")
////	@ResponseBody
////	public Map<String, Object> countAllUser() {
////		try {
////			Map<String, Object> userMap = new HashMap<>();
////			List<Map<String, Object>> userList = trbacUserService.getUserTenantOrg();
////			userMap.put("message", userList);
////			return MsgReturn.mapOk(userList.size(), userList);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return MsgReturn.mapError("数据异常");
////		}
////	}
//	/**
//	 * 获取某一用户的角色组织机构树
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping(value = "/countGroupByUser")
//	@ResponseBody
//	public Map<String, Object> countGroupByUser(String userId) {
//		try {
//			List<Group> belongGroup = identityService.createGroupQuery().groupMember(userId).list();
//			List<Group> allGroup = identityService.createGroupQuery().list();
//			List<Map<String, Object>> list = new ArrayList<>();
//			for (Group group : allGroup) {
//				Map<String, Object> map = new HashMap<>();
//				map.put("id", group.getId());
//				map.put("name", group.getName());
//				list.add(map);
//			}
//			for (Group group : belongGroup) {
//				for (Map<String, Object> map : list) {
//					if (group.getId().equals((String) map.get("id"))) {
//						map.put("checked", "true");
//						break;
//					}
//				}
//			}
//			Map<String, Object> resultMap = new HashMap<>();
//			resultMap.put("group", list);
//			return resultMap;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgReturn.mapError("数据异常");
//		}
//	}
//
//	/**
//	 * 获取所有用户的组织机构（ztree）
//	 * @return
//	 */
//	@RequestMapping(value = "/countTenantUser")
//	@ResponseBody
//	public Map<String, Object> countTenantUser() {
//		try {
//			TrbacTenantExample tenantExample = new TrbacTenantExample();
//			tenantExample.setOrderByClause("CREATE_DATETIME");
//			List<TrbacTenant> list = trbacTenantService.selectByExample(tenantExample);
//
//			// 我要组装两个List
//			List<Map<String, Object>> tenantList = new ArrayList<>();
//			List<Map<String, Object>> userList = new ArrayList<>();
//			for (TrbacTenant trbacTenant : list) {
//				Map<String, Object> map = new HashMap<>();
//				map.put("id", trbacTenant.getTenantId());
//				map.put("name", trbacTenant.getTenantName());
//				map.put("nocheck", "true");
//				tenantList.add(map);
//				TrbacUserExample userExample = new TrbacUserExample();
//				userExample.createCriteria().andTenantIdEqualTo(trbacTenant.getTenantId());
//				List<TrbacUser> uList = trbacUserService.selectByExample(userExample);
//				for (TrbacUser trbacUser : uList) {
//					Map<String, Object> m = new HashMap<>();
//					m.put("id", trbacUser.getLoginId());
//					m.put("name", trbacUser.getUserName());
//					m.put("tid", trbacUser.getTenantId());
//					userList.add(m);
//				}
//			}
//			// 最后返回回去的应该是一个map 里面有两个key值 一个是租户 一个是用户
//			// value分别为
//			Map<String, Object> resultMap = new HashMap<>();
//			resultMap.put("tenant", tenantList);
//			resultMap.put("user", userList);
//			return resultMap;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgReturn.mapError("数据异常");
//		}
//	}
//
//	/**
//	 * 用户的组（角色）变动
//	 * @param groupId
//	 * @param userId
//	 * @param type
//	 * @return
//	 */
//	@RequestMapping(value = "/saveUserInGroup")
//	@ResponseBody
//	public Map<String, Object> saveUserInGroup(String groupId, String userId, String type) {
//		try {
//			List<Group> list = identityService.createGroupQuery().list();
//			for (Group group : list) {
//				identityService.deleteMembership(userId, group.getId());
//			}
//			if ("add".equals(type)) {
//				List<String> userIds = new ArrayList<>();
//				userIds.add(userId);
//				iGroupService.addUser2Group(userIds, groupId);
//			}
//			return MsgReturn.mapOk("分配成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgReturn.mapError("数据异常");
//		}
//	}
//}
