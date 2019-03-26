//三个全局变量
var $table = $('#table'), //表格
$remove = $('#btn_delete'), //删除按钮
selections = [];  //所购选的rows数组
var path = $("#path").val();


$(function(){
	layui.use('layer', function(){
		  var layer = layui.layer;
		 
	});
	$('#startTime').datetimepicker({
		format:'yyyy-mm-dd',
	 	language:'zh-CN',
	 	autoclose:true,
	 	startView:2,
	 	minView:2,
	 	todayHighlight:true
	 });  	
	 $('#endTime').datetimepicker({
		 format:'yyyy-mm-dd',
		 language:'zh-CN',
		 autoclose:true,
		 startView:2,
		 minView:2,
		 todayHighlight:true
	 });  
    initTable();//初始化表格   
});


function initTable() {
        //先销毁表格 if exist
        $('#table').bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#table").bootstrapTable({
        	
        	columns: [
                          {
                              field: 'state',
                              checkbox: true,
                              align: 'center'
                          }, {
                              title: '编码',
                              field: 'processInstanceId',
                              visible:false,
                              align: 'center'
                          }, {
                              title: '申请单类型',
                              field: 'processType', //这个地方要根据 业务编码判断
                              align: 'center',
                              formatter:processTypeFormatter
                          },/*{
                              title: '申请单名称',
                              field: 'title',
                              align: 'center'
                          },*/{
                              title: '申请人',
                              field: 'proposer',
                              align: 'center'
                          },
                          {
                              title: '申请时间',
                              field: 'applyDate',
                              align: 'center',

                          },
                          {
                        	  title: '状态',
                        	  field: 'processStatus',
                        	  align: 'center',
                        	  formatter:processStatusFormatter    
                          },
                          {
                        	title:'操作',
                        	field: 'operate',
                        	width:200,
                        	formatter:operateFormatter,
                        	events: operateEvents,
                        	align:'center'	
                          }
                      ],
            url: path+"/instance/listProcessInsByUser.do", //获取数据的Servlet地址



            method:'post',
            contentType:'application/x-www-form-urlencoded',

            striped: true,  //表格显示条纹
            pagination: true, //启动分页
            height:getHeight(),
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            toolbar: '#toolbar',    //工具按钮用哪个容器
            pageList: [5, 10, 15, 20, 30],  //记录数可选列表
//            search: true,  //是否启用查询
//            searchAlign:'left',//
//            showColumns: true,  //显示下拉框勾选要显示的列
//            showRefresh: true,  //显示刷新按钮
            sidePagination: "server", //表示服务端请求
//            showToggle:true,     //是否显示详细视图和列表视图的切换按钮
//            detailView:true,      //表格左侧显示"+"打开详情
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "undefined", 
            queryParams: function queryParams(params) {   //设置查询参数
              var searchOne=$('#processName').val();
              var startTime=$('#startTime').val();
              var endTime=$('#endTime').val();
//              var processType=$('#processType').val();
              var param = {  
                  start: (params.pageNumber-1)*params.pageSize,  
                  limit: params.pageSize,
                  type:'0',
                  startTime:startTime,
                  endTime:endTime
//                  searchOne:searchOne,
//                  searchTwo:searchTwo,
//                  searchThree:searchThree,
              };  
              return param;                 
            },
//            onLoadSuccess: function(){  //加载成功时执行
////              alert('加载成功');
//            },
            onLoadError: function(){  //加载失败时执行
            	Dialog.alert({ message: "加载数据失败"});
            },
            responseHandler: function(res) {
                return {
                    "total": res.total,//总页数
                    "rows": res.data   //数据
                 };
            }
          });
        
        //当点击查询按钮的时候执行
//      $("#search").bind("click", initTable);

//      $('.fixed-table-toolbar .search').css("margin-left",'10px');//设置搜索框和左侧按钮的距离
//      $("[type='text']").attr("placeholder","请输入用户名");//修改搜索框初始字段
      
      var colH=$("[title='列']").height()//jsp页面下  有下拉菜单按钮和普通按钮高度同步
      var refH=$("[name='refresh']").height()
      $("[name='refresh']").height(colH-refH);

      
      //jq勾选触发事件，四个事件分别对应单个勾选，单个取消，勾选所有，取消所有
      $table.on('check.bs.table uncheck.bs.table ' +
              'check-all.bs.table uncheck-all.bs.table', function () {
          $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
          
          // 保存当前页面所购选的rows
//          selections = getIdSelections();

      });
      
      
      //打开左侧dateview按钮
  /*    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
      	$detail.html('Loading from ajax request...'+row.userName);
      });*/
      

      
     //添加页面初始化

      
     
      
 
        
      //动态设置table高度
      $(window).resize(function() {
    	  $table.bootstrapTable('resetView',{
    		  height: getHeight()
    	  })
    	});
      
      //查询框绑定事件
      $('#searchButton').click(function(){
    	  $('#table').bootstrapTable('refresh');
      });
      
      
       
        
      }


//获取当前页面选中的ID集合
function getIdSelections() {
	var userIds=[];
    $.map($table.bootstrapTable('getSelections'), function (row) {
        userIds.push(row.processCode);
    });
	return userIds;
}

function processTypeFormatter(value, row, index){
	
	return '数据定制';
	
}

function processStatusFormatter(value, row, index) {
	if (value == '0') {
		return '未审批';
	} else if (value == '1'){
		return '未执行';
	} else if (value == '-1') {
		return '退回';
	} else if (value == '2'){
		return '未实施';
	} else if (value == '-2'){
		return '已实施';
	}else {
		return '状态异常';
	}
}


function operateFormatter(value, row, index) {
	if (row.processStatus == '0' || row.processStatus == '1' || row.processStatus == '2' || row.processStatus == '-2') {
		return [
		        '<a class="like" href="javascript:void(0)" title="like">',
		        '查看',
		        '</a>'
		        ].join('');
	} else if (row.processStatus == '-1'){
		
    return [            
        '<a class="like" href="javascript:void(0)" title="like">',
        '查看',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '删除',
        '</a>'
    ].join('');
	} else {
		return '---'
	}
}

window.operateEvents = {
        'click .like': function (e, value, row, index) {      
        	window.location.href=path+"/page/view/taskInfo/index.jsp?target="+row.url+"&insCode="+row.processInstanceId;
//        	console.log(row.url)
        },
        'click .remove': function (e, value, row, index) {
        	var processInstanceId=row.processInstanceId;
        	
        	$.ajax({
				  url:path+'/instance/deleteProcessInsByInsCode.do?insCode='+processInstanceId,
				  type:'post',
				  success:function(data){
					  console.log(data.success==true);
					  if(data.success==true){
						  $('#table').bootstrapTable('refresh');
					      layer.msg('删除流程成功');
					  
					  }else{
			    		  layer.msg('删除流程失败');
			    	  }
				},
							
				  error:function(data){
					  layer.msg('删除流程失败');
			    	}
				});
        }
    };


function getHeight() {
    return $(window).height()-$('#searchRow').outerHeight()-10;
}



