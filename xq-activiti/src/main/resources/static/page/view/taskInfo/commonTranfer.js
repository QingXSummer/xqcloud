function processStatusTransfer(status){
	if (status == '0') {
		return '未审批';
	} else if (status == '1') {
		return '未执行';
	}else if (status =='2') {
		return '未实施';
	}else if(status == '-2'){
		return '已实施';
	}else if(status == '-1'){
		return '退回';
	} else {
		return '异常';
	}
}

function approveStatus(status){
	if (status == '-1') {
		return '退回';
	} else if (status == '1') {
		return '通过';
	}else if (status =='2') {
		return '回退上一步'
	} else if (status =='3'){
		return '废弃';
	} else if (status =='4') {
		return '发起';
	}else {
		return '异常';
	}
}
/**
 * iframe调用父页面layer弹框 显示10条数据
 * @param record
 * @returns
 */
function layerShowData(record){
	layui.use(['layer','table'],function(){
		var layer = layui.layer;
		var table = layui.table;
		layer.open({
			  type: 0,
			  content: '<table class="layui-hide" id="demo"></table>',
			  title:'数据预览',
			  area: ['60%','80%'],
			  btn: '关闭',
			  btnAlign: 'c',
			  shade:0.3,
			  shadeClose:true,
			  yes: function(){
			    layer.closeAll();
			  }
		});
		var column=[] ;
		var data=[];
		$.each(record,function(index,element){
			if(index == 0){
				for(p in element){
					column.push({field:p, title:p});
				}
			}
			data.push(element);
		});
		table.render({
		    elem: '#demo',
		    cellMinWidth: 100, 
		    cols:[column],
		    data:data
		  });
	});
}
/**
 * iframe 调用父页面弹框提示
 * @param message
 * @returns
 */
function layerAlert(message){
	layui.use(['layer'],function(){
		layer.alert(message);
	});
}