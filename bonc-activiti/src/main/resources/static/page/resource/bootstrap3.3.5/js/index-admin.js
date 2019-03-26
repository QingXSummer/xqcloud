var path = $("#path").val();

var isadmin = $("#isadmin").val();

function initIndex(isadmin) {

	var urlAssetType = path + '/getAssetTypeList.do?type=dictionarytype';
	$.post(urlAssetType, function(data) {
		var html = "";
		var mainHtml = "<div class='subcontent' name='subcontentTop'  id='subcontentTop' style='border:none;'></div> ";
		var endHtml = "<li><span class='spanClassTop' ></span><a href='#subcontentTop' name='subcontentTop' onclick='click_scroll(\"subcontentTop\")'></a></li>";
		$("#sub").html("");
		for (var i = 0; i < data.length; i++) {
			var desc = data[i].dictionaryDesc;
			desc = subStr(desc, 135);
			html = html + "<li><span class='spanClass" + (i + 1) + "'></span><a href='#subcontent" + data[i].dictionaryCode + "' onclick='click_scroll(\"subcontent" + data[i].dictionaryCode
					+ "\")' name='subcontent" + data[i].dictionaryCode + "' >" + data[i].dictionaryValue + "</a></li><input type='hidden' id='" + data[i].dictionaryType + "' value='"
					+ data[i].dictionaryType + "'/>";
			// html = html + "<div class='tab'><a href='javascript:void(0)'
			// onclick='click_scroll(\"subcontent"+data[i].dictionaryCode+"\")'
			// name='subcontent"+data[i].dictionaryCode+"'
			// ><span>"+data[i].dictionaryValue+"</span></a></div><input
			// type='hidden' id='"+data[i].dictionaryType+"'
			// value='"+data[i].dictionaryType+"'/>"
			var tempHtml = parseHtml(data[i].dictionaryValue, i, data[i].dictionaryCode, desc, data[i].dictionaryDesc, isadmin);
			mainHtml = mainHtml + tempHtml;
		}

		$("#sub").html(html + endHtml);
		$("#main").html(mainHtml);

	}, "json");
}

// 点击获取更多
function pageRight(assetType) {
	// 记录分页的页码
	var pageNum = $("#pageNum" + assetType).val();
	var page = new Number(pageNum)
	page = page + 1;
	$("#pageNum" + assetType).val(page);

	// 增加代码动态判断更多时候显示的样式
	var imgClass = $("#opt_" + assetType).attr("class");
	if (imgClass != 'img3' && imgClass != undefined) {
		imgClass = 'img1';
	} else {
		imgClass = 'img3';
	}
	var html = "";
	var pageHtml = "<div class='page_right' ></div>";
	// var startHtml =
	// " <div class='list' id='list"+assetType+"'> ";
	// var endHtml =
	// " <div class='page_right' ></div> "+
	// " </div> ";
	var url = path + '/getTDataAssetInfoListByJson.do?pageNum=' + page + '&assetType=' + assetType;
	$.post(url, function(data) {
		for (var i = 0; i < data.length; i++) {
			var desc = data[i].assetDesc;
			desc = subStr(desc, 12);

			var title = data[i].assetName;
			title = subStr(title, 8);

			var temp = "		<div class='single'>                                                        " + "			<div class='title'>                                                     "
					+ "				<div class='" + imgClass + "' name='" + data[i].assetId + "' id='opt_" + assetType + "' onclick=\'titleOnClick(this,\"" + data[i].assetId + "\",\"" + data[i].assetName
					+ "\")\'>  " + "					<div style='float:left;width:100%'><font>" + title + "</font></div> " + "					<div class='single_desc'>" + desc + "</div>                    		"
					+ "				</div>                                                              " + "			</div>                                                                  "
					+ "		</div>                                                                      ";
			html = html + temp;
			// var body = $("#list"+assetType).html();
			// $("#list"+assetType).html(body + temp);
		}
		if (data.length > 0) {
			$("#list" + assetType).html(pageHtml + html);
		}
	}, "json");
}

// 点击获取更多
function moreInfo(assetType) {
	// 记录分页的页码
	var pageNum = $("#pageNum" + assetType).val();
	var page = new Number(pageNum)
	page = page + 1;
	$("#pageNum" + assetType).val(page);

	var getAllFlag = false;
	if ($("#pageNum" + assetType).val() > 2) {
		getAllFlag = true;
		page = 1;
	}

	// 增加代码动态判断更多时候显示的样式
	var imgClass = $("#opt_" + assetType).attr("class");
	if (imgClass != 'img3' && imgClass != undefined) {
		imgClass = 'img1';
	} else {
		imgClass = 'img3';
	}

	var html = "";
	// var pageHtml = "<div class='page_right' ></div>";
	var pageHtml = "";
	// var startHtml =
	// " <div class='list' id='list"+assetType+"'> ";
	// var endHtml =
	// " <div class='page_right' ></div> "+
	// " </div> ";
	var url = path + '/getTDataAssetInfoListByJson.do?pageNum=' + page + '&allPage=1&assetType=' + assetType + '&isadmin=' + isadmin;
	$.post(url, function(data) {
		for (var i = 0; i < data.length; i++) {
			var desc = data[i].assetDesc;
			desc = subStr(desc, 12);

			var title = data[i].assetName;
			title = subStr(title, 8);

			var temp =
			// " <div class='single'> "+
			// " <div class='title'> "+
			// " <div class='"+imgClass+"' name='"+data[i].assetId+"'
			// id='opt_"+assetType+"'
			// onclick=\'titleOnClick(this,\""+data[i].assetId+"\",\""+data[i].assetName+"\")\'>
			// "+
			// " <div style='float:left;width:100%'><font>"+title+"</font></div>
			// "+
			// " <div class='single_desc'>"+desc+"</div> "+
			// " </div> "+
			// " </div> "+
			// " </div> ";
			"		<div class='single'>                                                        " + "			<div class='title'>                                                     " + "				<div class='"
					+ imgClass + "' name='" + data[i].assetId + "' id='opt_" + assetType + "' onclick=\'titleOnClick(this,\"" + data[i].assetId + "\")\'>  "
					+ "					<div style='float:left;width:100%'><font title='" + data[i].assetName + "'>" + title + "</font></div> " + "					<div class='single_desc' title='" + data[i].assetDesc
					+ "'>" + desc + "</div>                    		" + "				</div>                                                              "
					+ "			</div>                                                                  " + "		</div>                                                                      ";

			html = html + temp;
			// var body = $("#list"+assetType).html();
			// $("#list"+assetType).html(body + temp);
		}

		if (getAllFlag) {
			$("#list" + assetType).html(pageHtml + html);
		} else {
			var body = $("#list" + assetType).html();
			$("#list" + assetType).html(body + html);
		}

	}, "json");
}

// 根据类型获取type
function getAssetListByType(assetType, isadmin) {
	// var startHtml =
	// " <div class='list' id='list"+assetType+"'> "+
	// " <div class='page_left' ></div> ";
	// var endHtml =
	// " <div class='page_right' ></div> "+
	// " </div> ";
	var url = path + '/getTDataAssetInfoListByJson.do?assetType=' + assetType + '&isadmin=' + isadmin;
	var html = "";
	// var pageHtml = "<div class='page_right' ></div>";
	var pageHtml = "";
	// var bodyType = "";

	// 增加代码动态判断更多时候显示的样式
	var imgClass = $("#opt_" + assetType).attr("class");
	if (imgClass != 'img3' && imgClass != undefined) {
		imgClass = 'img1';
	} else {
		imgClass = 'img3';
	}

	$.post(url, function(data) {
		for (var i = 0; i < data.length; i++) {
			// 截取操作
			var desc = data[i].assetDesc;
			desc = subStr(desc, 12);

			var title = data[i].assetName;
			title = subStr(title, 8);

			var temp = "		<div class='single'>                                                        " + "			<div class='title'>                                                     "
					+ "				<div class='" + imgClass + "' name='" + data[i].assetId + "' id='opt_" + assetType + "' onclick=\'titleOnClick(this,\"" + data[i].assetId + "\")\'>  "
					+ "					<div style='float:left;width:100%'><font title='" + data[i].assetName + "'>" + title + "</font></div> " + "					<div class='single_desc' title='" + data[i].assetDesc
					+ "'>" + desc + "</div>                    		" + "				</div>                                                              "
					+ "			</div>                                                                  " + "		</div>                                                                      ";

			// bodyType = bodyType + temp;
			html = html + temp;
			// var body = $("#list"+assetType).html();
			// $("#list"+assetType).html(body + temp);
		}
		var body = $("#list" + assetType).html();
		$("#list" + assetType).html(body + html);
	}, "json");
}

// 搜索
function searchAssetListByAssetName(id, assetType) {
	// 增加代码动态判断更多时候显示的样式
	var imgClass = $("#opt_" + assetType).attr("class");
	if (imgClass != 'img3' && imgClass != undefined) {
		imgClass = 'img1';
	} else {
		imgClass = 'img3';
	}

	var name = $("#search" + id).val();

	if (name == null || name == "") {
		alertWin("提示", "请输入数据资产名称！");
		return;
	} else {
		$("#list" + assetType).html("");
		var url = path + '/getTDataAssetInfoListByJson.do?assetName=' + name + '&assetType=' + assetType + '&isadmin=' + isadmin;
		$.post(url, function(data) {
			var body = "";
			for (var i = 0; i < data.length; i++) {
				// 截取操作
				var desc = data[i].assetDesc;
				desc = subStr(desc, 12);

				var title = data[i].assetName;
				title = subStr(title, 8);
				var body =
				/*
				 * " <div class='single'> "+ " <div class='title'> "+ " <div
				 * class='"+imgClass+"' name='"+data[i].assetId+"'
				 * id='opt_"+assetType+"'
				 * onclick=\'titleOnClick(this,\""+data[i].assetId+"\",\""+data[i].assetName+"\")\'> "+ "
				 * <div style='float:left;width:100%'><font>"+title+"</font></div> "+ "
				 * <div class='single_desc'>"+desc+"</div> "+ " </div> "+ "
				 * </div> "+ " </div> ";
				 */
				"		<div class='single'>                                                        " + "			<div class='title'>                                                     " + "				<div class='"
						+ imgClass + "' name='" + data[i].assetId + "' id='opt_" + assetType + "' onclick=\'titleOnClick(this,\"" + data[i].assetId + "\")\'>  "
						+ "					<div style='float:left;width:100%'><font title='" + data[i].assetName + "'>" + title + "</font></div> " + "					<div class='single_desc' title='" + data[i].assetDesc
						+ "'>" + desc + "</div>                    		" + "				</div>                                                              "
						+ "			</div>                                                                  " + "		</div>                                                                      ";

				var tempHtml = $("#list" + assetType).html();
				$("#list" + assetType).html(tempHtml + body);
			}

		}, "json");
		// /* $("#list"+assetType).html(body); */
		$("#list" + assetType).html(body);
	}
}

// 根据数据资产类型，查询对应的数据资产
function parseHtml(dictionaryValue, id, assetType, dictionaryDesc, allDesc, isadmin) {
	var starthtml = "<input type='hidden' id='pageNum" + assetType + "' value='1'/>" + "<div class='subcontent' name='subcontent" + assetType + "'  id='subcontent" + assetType + "'> "
			+ "	<div class='header'>                                                            " + "		<div class='icon'></div>                                                    "
			+ "		<div class='title'>" + dictionaryValue + "</div>                                " + "		<div class='more' onclick='moreInfo(\"" + assetType + "\")'>>>更多</div>          "
			+ "		<div class='search'>                                                        " + "			<div class='text'>                                                      "
			+ "				<input type='text' name='' value='' style='' id='search" + id + "'/>    " + "			</div>                                                                  "
			+ "			<div class='button' onclick='searchAssetListByAssetName(\"" + id + "\",\"" + assetType + "\")'></div>    "
			+ "		</div>                                                                      " + "	</div>                                                                          "
			+ "	<div class='desc' title='" + allDesc + "'>" + dictionaryDesc + "</div>                                      ";

	var endHtml = "</div>                                                                              ";

	// 遍历数据资产数据
	var startHtmlType = "	<div class='list' id='list" + assetType + "'>                                       " + "		<div class='' ></div> 											";
	var endHtmlType =
	// " <div class='page_right' onclick='pageRight(\""+assetType+"\")'></div>
	// "+
	"	</div>                                                                          ";

	var finalHtmlType = startHtmlType + endHtmlType;

	getAssetListByType(assetType, isadmin);

	var finalHtml = starthtml + finalHtmlType + endHtml;

	return finalHtml;

}
// 初始化类型
function initAssetList(type, assetName, pageSize, pageNum, path1) {
	var html = "";
	var url = path1 + "/getTDataAssetInfoListByJson.do?assetType=" + type + "&assetName=" + assetName + "&strPageSize=" + pageSize + "&pageNum=" + pageNum;
	$.post(url, function(data) {
		for (var i = 0; i < data.length; i++) {
			html = html + "<div class='tab'><span>" + data[i].dictionaryValue + "</span></div><input type='hidden' id='" + data[i].dictionaryType + "' value='" + data[i].dictionaryType + "'/>"
		}
	}, "json")
}

// 获取assetNumber
function getAssetNumber() {
	$.ajax({
		url : path + "/getDataAssetNumber.do",
		dataType : "json",// 返回值类型Json
		contentType : 'application/json;charset=UTF-8',// 必须要
		type : "POST",
		async : false,
		success : function(result) {
			$('#infoInsertForm').form('load', {
				assetNumber : result["assetNumber"]
			});
		}
	});
}

// 获取选中的值
function getCheckedValue(checkBoxName) {
	var qx = $("input[name='" + checkBoxName + "']:checked").map(function() {
		return $(this).val();
	}).get().join(',');

	// 值设置到隐藏域
	$("#sampleConfigInsert").val(qx);
	$("#sampleConfigUpdate").val(qx);

	return qx;
}

// 重新赋值checkedbox
function resetCheckedValue(name) {
	$("#sampleConfigInsert").val("");
	$("#sampleConfigUpdate").val("");
	// 把选中的值重新赋值到表单中
	var values = getCheckedValue(name);
	// $("input[name='"+name+"']").val(values);
}

// 选择变色
function titleOnClick(obj, assetId, assetName) {
	var $obj = $(obj);
	var attrClass = $obj.attr("class");
	if (attrClass == 'img1') {
		$obj.attr("class", "img");
	}

	if (attrClass == 'img') {
		$obj.attr("class", "img1");
	}
}

// 提交对象1 发布 2 下线 3编辑 4 新增
function submit(type) {
	var param = new Array();
	var $opts = $("div[id^='opt_']");
	$opts.each(function() {
		var classType = $(this).attr("class");
		if (classType == 'img') {
			var id = $(this).attr("name")
			param.push(id);
		}
	});
	var assetIds = param.join(",");

	if (type == 1) { // 资产发布
		if (param.length < 1) {
			alertWin("提示", "请选择一条进行发布！");
			return;
		}
		initPublishModal(assetIds);
		$('#modal_publish').modal({
			backdrop : true,
			keyboard : true,
			show : true
		});
	} else if (type == 2) { // 资产下线
		if (param.length < 1) {
			alertWin("提示", "请选择一条进行下线！");
			return;
		}
		initOfflineModal(assetIds);
		$('#modal_offline').modal({
			backdrop : true,
			keyboard : true,
			show : true
		});
	} else if (type == 3) { // 资产编辑
		if (param.length > 1) {
			alertWin("提示", "请选择一条进行编辑！");
			return;
		} else if (param.length == 1) {
			initUpdateModal(param[0]);
			$('#modal_update').modal({
				backdrop : true,
				keyboard : true,
				show : true
			});
		} else {
			alertWin("提示", "请选择一条记录！");
			return;
		}
	} else if (type == 4) {// 资产新增
		initInsertModal();
		$('#modal_insert').modal({
			backdrop : false,
			keyboard : true,
			show : true
		});
	} else if (type == 5) { // 资产申请
		if (param.length < 1) {
			alertWin("提示", "请选择数据资产！");
			return;
		}
		initApplyModal(assetIds);
		$('#modal_apply').modal({
			backdrop : true,
			keyboard : true,
			show : true
		});
	}
}

function hideOptRight() {
	var $optsRight = $("div[id^='opt']");
	$optsRight.each(function() {
		$(this).hide();
	});
}

var isShow = false;
function showOpt() {
	var $optsRight = $("div[id^='opt']");
	var $opts = $("div[id^='opt_']");

	if (isShow) {
		$optsRight.each(function(index, ele1) {
			isShow = false
			$(ele1).hide();
		});

		$opts.each(function(index, ele2) {
			$(ele2).attr("class", "img3");
			$(ele2).show();
		});
	} else {
		$optsRight.each(function(index, ele) {
			isShow = true;
			$(ele).show();
		});

		$opts.each(function(index, element) {
			$(element).attr("class", "img1");
		});
	}
}

// function click_scroll(id) {
// $("#sub .tab [name='"+id+"']").parent().attr("style","background-color:
// #de7617");
// $("#sub .tab [name='"+id+"']").parent().siblings().attr("style","");
//	
// if(id=='subcontentAll'){
// var $subcontent = $("div[class='subcontent']");
// $subcontent.each(function(){
// $(this).show();
// });
// }
// else{
// $("#"+id).show("slow");
// $("#"+id).siblings().hide("slow");
// }
// }

function click_scroll(id) {
	// $("#sub .tab [name='"+id+"']").parent().attr("style","background-color:
	// #de7617");
	// $("#sub .tab [name='"+id+"']").parent().siblings().attr("style","");
	//	
	// if(id=='subcontentAll'){
	// var $subcontent = $("div[class='subcontent']");
	// $subcontent.each(function(){
	// $(this).show();
	// });
	// }
	// else{
	// $("#"+id).show("slow");
	// $("#"+id).siblings().hide("slow");
	// }
	if (id == 'subcontentTop') {
		// $("#sub [name='"+id+"']").parent().attr("style","background-color:
		// #de7617");
		$("#sub [name='" + id + "']").parent().siblings().attr("style", "");
	} else {
		$("#sub [name='" + id + "']").parent().attr("style", "background-color: #de7617");
		$("#sub [name='" + id + "']").parent().siblings().attr("style", "");
	}

}

function alertWin(title, content) {
	//	$('#modal-window').modal({
	//		backdrop:true,
	//		keyboard:true,
	//		show:true
	//	});

	bootbox.alert(content);

	//$("#modal").show();
	//	$("#modal-title").html(title);
	//	$("#modal-body").html(content);

}