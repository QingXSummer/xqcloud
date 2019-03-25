var path;
var setting = {
	data : {// 表示tree的数据格式
		simpleData : {
			enable : true,// 表示使用简单数据模式
			idKey : "id",// 设置之后id为在简单数据模式中的父子节点关联的桥梁
			pidKey : "pId",// 设置之后pid为在简单数据模式中的父子节点关联的桥梁和id互相对应
			rootId : "null"// pid为null的表示根节点
		}
	},
	view : {
		selectedMulti : false
	},
	check : {// 表示tree的节点在点击时的相关设置
		enable : true,// 是否显示radio/checkbox
		chkStyle : "radio",// 值为checkbox或者radio表示
		checkboxType : {
			p : "",
			s : ""
		},// 表示父子节点的联动效果
		radioType : "all"// 设置tree的分组
	}
};
$(function() {
			path = $('#path').val();
			$.ajax({
						type : "post",
						url : path + "/group/countTenantUser.do",
						dataType : "json",
						success : function(data) {
							initTree(data);
						}
					});

		});
function initTree(data) {

	var tenantList = data.tenant;
	var userList = data.user;
	var treeData = [];
	for (var i = 0; i < tenantList.length; i++) {
		tenantList[i].pId = null;
		treeData.push(tenantList[i]);
	}
	for (var i = 0; i < userList.length; i++) {
		userList[i].id = userList[i].id;
		userList[i].pId = userList[i].tid;
		treeData.push(userList[i]);
	}
	setting['callback'] = {
		onCheck : function(event, treeId, treeNode) {
			initGroup(treeNode.id);
		}
	};
	$.fn.zTree.init($("#usertree"), setting, treeData);
}
function initGroup(userId) {
	$.ajax({
				type : "post",
				url : path + "/group/countGroupByUser.do",
				dataType : "json",
				data : {
					userId : userId
				},
				success : function(data) {
					setGroupTreeByUserId(data, userId);
				}
			});
}
function setGroupTreeByUserId(data, userId) {
	var groupList = data.group;
	var treeData = [];
	for (var i = 0; i < groupList.length; i++) {
		groupList[i].pId = null;
		treeData.push(groupList[i]);
	}
	setting['callback'] = {
		onCheck : function(event, treeId, treeNode) {
			var type = "add";
			if (treeNode.checked == false) {
				type = "remove";
			}
			changeUserGroup(userId, treeNode.id, type);
		}
	};
	$.fn.zTree.init($("#grouptree"), setting, treeData);

}
function changeUserGroup(userId, groupId, type) {
	$.ajax({
				type : "post",
				url : path + "/group/saveUserInGroup.do",
				dataType : "json",
				data : {
					userId : userId,
					groupId : groupId,
					type : type
				},
				success : function(data) {
				}
			});
}
