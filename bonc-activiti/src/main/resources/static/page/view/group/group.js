var path = $('#path').val();

var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;

	if(!("name" in options) && !("type" in options)){
		$data = this._data;//the root tree
		callback({ data: $data });
		return;
	}
	else if("type" in options && options.type == "folder") {
		if("additionalParameters" in options && "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else $data = {}//no data
	}
	
	if($data != null)//this setTimeout is only for mimicking some random delay
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

	//we have used static data here
	//but you can retrieve your data dynamically from a server using ajax call
	//checkout examples/treeview.html and examples/treeview.js for more info
};

function save() {
	var userId = '';
	var groupId = '';
    var items = $('#tree1').tree('selectedItems');
    for (var i in items) if (items.hasOwnProperty(i)) {
        var item = items[i];
        var userId = item.id;
    }
    if(userId == ''){
    	layer.alert('请选择一个用户');
    	return;
    }
    var items = $('#tree2').tree('selectedItems');
    for (var i in items) if (items.hasOwnProperty(i)) {
        var item = items[i];
        var groupId = item.id;
    }
	$.ajax({
		type : "post",
		url : path+"/group/saveUserInGroup.do",
		dataType : "json",
		data:{groupId:groupId,userId:userId},
		success : function(data) {
				if(data.success = true){
					layer.alert('保存成功');
				}
		}});
}

$(function(){
	$.ajax({
		type : "post",
		url : path+"/group/countAllUser.do",
		dataType : "json",
		success : function(data) {
			
			if(data.success == true){
				var tree_data={};
				$.each(data.data,function(index,item){
					var children = {};
					$.each(item.tenantUser,function(i,it){
						var childrenProp = {};
						childrenProp.name = it.userName;
						childrenProp.type = 'item';
						childrenProp.id = it.loginId;
						children[it.userName] = childrenProp;
					});
					var additionalParameters={};
					additionalParameters.children = children;
					var prop = {};
					prop.name = item.tenantName;
					prop.type = 'folder';
					prop.additionalParameters = additionalParameters;
					tree_data[item.tenantName] = prop;
				})
				var treeDataSource1 = new DataSourceTree({data: tree_data});
				$('#tree1').ace_tree({
					dataSource: treeDataSource1 ,
					multiSelect:false,
					loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
					'open-icon' : 'icon-folder-open',
					'close-icon' : 'icon-folder-close',
					'selectable' : true,
					'selected-icon' : 'icon-ok',
					'unselected-icon' : 'icon-null'
				});
				$('#tree1').on('selected',function(e,result){
					var userId = result.info[0].id;	
					$.ajax({
						type : "post",
						url : path+"/group/countGroupByUser.do",
						dataType : "json",
						data:{userId:result.info[0].id},
						success : function(data) {
							var tree_data={};
							$.each(data.data,function(index,item){
								var prop ={};
								prop.name = item.name;
								prop.type = 'item';
								prop.id = item.id;
								if(item.flag == true){
									prop.additionalParameters = {'item-selected': true};
								}
								tree_data[item.name]=prop;
							});
							var treeDataSource2 = new DataSourceTree({data: tree_data});
							$('#tree').empty();
							$('#tree').html( '<div class="widget-box">'
											+'<div class="widget-header header-color-blue2">'
											+'<h4 class="lighter smaller">角色</h4>'
											+'</div>'
											+'<div class="widget-body">'
											+'<div class="widget-main padding-8" >'
											+'<div id="tree2" class="tree tree-selectable"></div>'
											+'</div>'
										    +'</div></div>');
							$('#tree2').ace_tree({
								dataSource: treeDataSource2 ,
								multiSelect:false,
								loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
								'open-icon' : 'icon-folder-open',
								'close-icon' : 'icon-folder-close',
								'selectable' : true,
								'selected-icon' : 'icon-ok',
								'unselected-icon' : 'icon-null'
							});
						}});
					

				});
			}else{
				
			}

		}});
	
	
});