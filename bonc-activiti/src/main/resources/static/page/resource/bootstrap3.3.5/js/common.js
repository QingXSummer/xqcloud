function subStr(str,length){
	var finalStr = "";
	if(null != str && "" != str && "null" != str){
		if(str.length > length){
			str = str.substring(0,length);
			str = str + "...";
		}
		finalStr = str;
	}
	return finalStr;
}

//根据code获取对应的码表值
function getCodeInfo(url,value){
	var resultStr = "";
	if(value == null || value==''){
	}
	else{
		$.ajax({  
	    	url:url,
			dataType : "json",
			contentType : 'application/json;charset=UTF-8',//必须要
			type : "POST",
			async: false,//需要同步操作
			success : function(result) {
				resultStr =  result.dictionaryValue;
			}
		}); 
		
	}
	return resultStr;
}

//抽样比例复选框初始化
function checkBoxInit(value)
{
	var index = value.indexOf(",");
	if(index > 0){
		var temp = value.split("\,");
		for(var i = 0 ; i < temp.length; i ++){
			selectCheckBox(temp[i]);
		}
	}
	else{
		selectCheckBox(value);
	}
}

//选中复选框
function selectCheckBox(val){
	$("input[name='sampleConfigUpdate']").each(function(x,value){
		 var $v = $(value)
		 if($v.val() == val){
			 $v.prop("checked","checked");
		 }
	});
	
	$("input[name='sampleConfigDetail']").each(function(x,value){
		 var $v = $(value)
		 if($v.val() == val){
			 $v.prop("checked","checked");
		 }
	});
}

//清空复选框
function clearCheckBox(checkboxname){
	$("input[name='"+checkboxname+"']").each(function(i,value){
		 var $v = $(value)
		 $v.prop("checked","");
	});
}


function tabClick(type){
	if(type=='navIndex1'){
		$("#navIndex1").attr("class","onfocus");
		$("#navIndex2").attr("class","");
		$("#navIndex3").attr("class","");
		$("#navIndex4").attr("class","");
		window.location.href=path + '/pages/jsp/index.jsp';
	}else if(type=='navIndex2'){
		$("#navIndex1").attr("class","");
		$("#navIndex2").attr("class","onfocus");
		$("#navIndex3").attr("class","");
		$("#navIndex4").attr("class","");
		window.location.href=path + '/pages/jsp/TDataAssetApprovalList.jsp';
	}else if(type=='navIndex3'){
		$("#navIndex1").attr("class","");
		$("#navIndex3").attr("class","onfocus");
		$("#navIndex2").attr("class","");
		$("#navIndex4").attr("class","");
		
		window.location.href=path + '/pages/jsp/index_other.jsp';
	}else{
		$("#navIndex1").attr("class","");
		$("#navIndex2").attr("class","");
		$("#navIndex3").attr("class","");
		$("#navIndex4").attr("class","onfocus");
		window.location.href=path + '/pages/jsp/TDataAssetApplyList.jsp';
	}
}