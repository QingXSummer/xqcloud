$(function(){
	initTable();
})
function initTable(){
layui.use('table', function(){
  var table = layui.table;
    
//展示已知数据
  table.render({
     elem: '#demo',
     url :  path+'/processDefinition/listProcessByCondition.do',
     //where: {token: 'sasasas', id: 123} //如果无需传递额外参数，可不加该参数
     method: 'post' ,//如果无需自定义HTTP类型，可不加该参数
	 height: 400,
	 cols : [ [ // 标题栏 
	            
				{
					field : 'processId',
					title : '流程编号',
					width : 140,
					sort : true
				}, {
					field : 'processName',
					title : '流程名称',
					width : 280
				}, {
					field : 'version',
					title : '版本',
					width : 100
				}, {
					field : 'createTime',
					title : '创建时间',
					width : 300
				}, {
					fixed : 'right',
					field : 'operation',
					title : '操作',
					width : 530,
					align : 'center',
					toolbar : '#barDemo'
				} ] ],
				skin : 'row',
				even : true,
				id : 'idTest',
				page : true,
				limits : [ 5, 7, 10 ],
				limit : 5
			});
	 
  
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;//获得当前行数据
    var modelId=data.processId;    
    var name=data.processName;
    //var imgUrl=path+"/page/image/test.png";
  //获得 lay-event 对应的值
    if(obj.event === 'detail'){
    	//var Odiv=document.getElementById("layer-photos-demo"); 
    	//Odiv.style.cssText="height:525px;width:520px;"
        //Odiv.innerHTML="<img layer-pid="+modelId+"  src="+imgUrl+"  alt="+name+" style="+"cursor: move;>";    	
        //document.body.appendChild(Odiv);        //在body内创建一个div 
    	console.log(path);
    	//页面层
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.open({
		    	  type: 1,
		    	  skin: 'layui-layer-rim', //加上边框
		    	  area: ['600px', '390px'], //宽高
		    	  content: '\<\div style="padding:20px;">'
		    		  +'<img  src=\"'+path+'/processDefinition/getProcessImageById.do?processDefinitionId='+modelId+'" >'
		    		  +'</img> \<\/div>'
		    	});
		});  
    	//弹出图片层
        /*var index=layer.open({
        	  type: 1,
        	  title: false,
        	  closeBtn: 0,
        	  area: '516px',
        	  skin: 'layui-layer-nobg', //没有背景色
        	  shadeClose: true,
        	  content: $('#layer-photos-demo'),
        	  end: function(index, layero){ 
        		  
        			  Odiv.style.cssText="display:none;";
        			  layer.close(index)
        		 
        		  
        		}   
        	});
  */   	   	
    } else if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
    	  
    	$.ajax({
    		type:'post',
    		url:path+'/processDefinition/deleteProcessDefinition.do?processDefinitionId='+modelId,
    		success:function(data){
					
						layer.msg("记录删除成功");
					
				},
		    error:function(data){
					
						layer.msg(data.message);
					
				}
    	});  
        obj.del();//删除对应行（tr）的DOM结构，并更新缓存
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
    	
    	window.location.href=path+"/modeler.html?modelId="+modelId;
      
    } else if(obj.event === 'role'){	
    	
    	var index = layer.open({
  		  type: 2,
  		  content: path+"",
  		  area: ['90%', '90%'],
  		  maxmin: true
  		});
 
      
    } else if(obj.event==='publish'){
        
    	layer.confirm('确定部署该流程么', function(index){
    		
            //具体操作
    		$.ajax({
				type:'post',
				url:path+'/processDefinition/updateProcessToDeploy.do?processDefinitionId='+modelId,
				success:function(data){
					layer.msg("部署成功");			
				},
				error:function(data){
					
						layer.msg(data.message);
					
				}
			});
            layer.close(index);
          });    	
    }
  });
  
  var $ = layui.$, 
    active = {
		add: function(){
    	
		//页面层
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.open({
		    	  type: 1,
		    	  skin: 'layui-layer-rim', //加上边框
		    	  area: ['600px', '390px'], //宽高
		    	  content: '\<\div style="padding:20px;">'
		    		  +'<form class="layui-form">'
		    		  +'<div class="layui-form-item">'
		    		  +'<label class="layui-form-label" style="text-align: left;">流程名称</label>'
		    		  +'<div class="layui-input-block">'
		    		  +'<input type="text" name="processName"  required  lay-verify="required" autocomplete="off" placeholder="流程名称" class="layui-input" style="height: 40px;width: 253px;">'
		    		  +'</div></div>'
		    		  +'<div class="layui-form-item">'
		    		  +'<label class="layui-form-label" style="text-align: left;">流程描述</label>'
		    		  +'<div class="layui-input-block">'
		    		  +'<textarea name="processDesc" lay-verify="required" required  autocomplete="off" placeholder="流程描述" class="layui-input" style="height: 200px;width: 253px;"></textarea>'
		    		  +'</div></div>'
		    		  +'<div class="layui-form-item">'
		    		  +'<div class="layui-input-block">'
		    		  +'<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>'
		    		  +'<button type="reset" class="layui-btn layui-btn-primary">重置</button>'
		    		  +'</div></div>'
		    		  +'</form> \<\/div>'
		    	});
			  
			  layui.use('form', function(){
				  var form = layui.form;				  
				  //监听提交
				  form.on('submit(formDemo)', function(data){					  
					 // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
				    //layer.msg(JSON.stringify(data.field.processName));
					  var processDefinitionName=data.field.processName
					  var processDefinitionDes=data.field.processDesc;
					  $.ajax({
						  url:path+'/processDefinition/saveProcessDefinition.do?processDefinitionName='+processDefinitionName+'&processDefinitionDes='+processDefinitionDes,
						  type:'post',
						  success:function(data){
								if (data.message==0){
									layer.open({
										content: '流程名称重复，请重新输入！'
									});									
								}
								modelId=data.message;
								window.location.href=path+'/modeler.html?modelId='+modelId;
							},
							error:function(data){
					    	  layer.open({
					    		  content:'新增流程失败'
					    	  }
					    	       
					    	  );
					    	  
						}
						});
					
					return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
				  });
				  
				  
				});
			});  
		
		
    },
    //刷新
   refresh: function(){
	   //要传的参数
      //var demoReload = $('#demoReload');
      
      table.reload('idTest', {
    	  url: path+'/processDefinition/listProcessByCondition.do',
        /*where: {
          key: {
            id: demoReload.val()
          }
        }*/
      });
    }
  }
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    console.log('type'+type);
    active[type] ? active[type].call(this) : '';
  });
  
});
}
