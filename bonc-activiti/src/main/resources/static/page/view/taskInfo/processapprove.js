var path = $("#path").val();
$(function(){

	var insCode = $('#insCode').val();
	var taskId = $('#taskId').val();
	var target = $('#target').val();
	$.ajax({
		type : "post",
		url : path+"/instance/getProcessInfo.do",
		dataType : "json",
		async:false,
		data: { insCode: insCode,taskId:taskId },
		success : function(data) {
			renderTitle(data.processVariable);
			renderComment(data.listLocalTasks);
			$('#iframe').html(' <iframe frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" id="content" name="content" onload="dyniframesize()"  width="100%"></iframe>');
			$('#content').attr('src',target);
		}
	});
	

	
	var height=$('#btn_approve_span').height();
	$('#btn_approve').height(height);
	
	// 为审核按钮添加点击事件
	$("#btn_approve")
			.click(
					function() {
						var approvalComments = $("#approvalComments").val();
						var isAgree = $("input[name=isAgree]:checked").val();
						
//						if(isAgree==0){							
//					        Dialog.confirm({ message: "确定审批不同意？"}).on(function (e) {
//					            if(!e){
//					                return;
//					            }
//					        });						
//						}else{
//						}
						if(isAgree == 1){
							var state = $("#content").contents().find("#processState").val();
							if(state == 1){
								$("#content").contents().find("#submitSpecialDataAsset").click();
								var success = $("#content").contents().find("#processstatus").val();
								if (success == 0){
									return;
								}
							}
						}
						approve(insCode,taskId,isAgree,approvalComments);					
					});
	
});




function renderTitle(processVariable){
	var username = processVariable.proposer;
	var tenantName = processVariable.orgName;
	var createTime = processVariable.applyDate;
	var state = processVariable.processStatus;
	
	$('#username').text(username);
	$('#tenantName').text(tenantName);
	$('#createTime').text(createTime);
	$('#state').text(processStatusTransfer(state));
	
}

function renderComment(comments){
	var innerHtml;
	$.each(comments, function(index, value) { 
		   innerHtml='<tr><td style="text-align: center; ">'+value.userName+'</td>'+
							        '<td style="text-align: center; ">'+value.createTime+'</td>'+
							        '<td style="text-align: center; ">'+approveStatus(value.state)+'</td>'+
							        '<td style="text-align: center; ">'+value.comment+'</td></tr>';
		   $('#tbody').append(innerHtml); 
		}); 	
}



function approve(insCode,taskId,agree,comment){
	$.ajax({
		type : "post",
		url : path+"/instance/insApprove.do",
		dataType : "json",
		data: { 
					insCode:insCode,
					taskId:taskId,
					agree: agree,
					comment : comment
				 },
		success : function(data) {
			window.location.href=path+"/page/view/processList/ProcessApprove.jsp"
		}
	});
}
