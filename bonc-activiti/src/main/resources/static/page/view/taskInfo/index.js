var path = $("#path").val();
$(function(){
	
	var insCode = $('#insCode').val();
	var target = $('#target').val();
	$.ajax({
		type : "post",
		url : path+"/instance/getProcessInfo.do",
		dataType : "json",
		data: { insCode: insCode },
		success : function(data) {
			
			renderTitle(data.processVariable);
			renderComment(data.listLocalTasks);
			$('#iframe').html('<iframe id="content" name="specialDataAsset" frameborder=0 width="100%" height="500" scrolling="no" onload="javascript:this.height=this.contentWindow.document.body.scrollHeight+30;"></iframe>');
			$('#content').attr('src',target);
		}
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
