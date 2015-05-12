var adminLevel;
var area = new Array();
$(document).ready(function() {
	showAllUser();
	//获取当前管理员信息，地区数组和级别为全局变量
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/findCurrentAdminLevel.do',
		async : false,
		data : {
		
		},
		dataType : 'json',
		success : function(msg) {
			if(msg!=null){
				if (msg.result == true) {
					adminLevel = msg.user.userType;
					var levelNum=0;
					$.each(msg.userLevelList,function(key,val){
					   levelNum++;
					});
					var areaFlag = 0;
					$.each(msg.areaList,function(key,val){
						//area[0]为当前管理员的最低级别地区
						areaFlag++;
						area[areaFlag] = val;
						});
				} else{
					//alert(msg.message);
				}
			}
		},
		complete:function(XMLHttpRequest,textStatus){   
            var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，  
            if(sessionstatus=="timeout"){   
            	//如果超时就处理 ，指定要跳转的页面  
            	alert("ddddddd");
                window.location.replace("userChanged.html");   
            }   
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
	$("#select").click(function(){
		if($("#level").val() == "全部" && $("#primarykey").val() == ""){
			showAllUser();
		}else{
		var level = $("#level").val();
		var primarykey=$("#primarykey").val();
		var province=$("#province").val();
		var city=$("#city").val();
		var county=$("#county").val();
		var town=$("#town").val();
		var userAreaId = 1;
		if($("#level").val() == "省级"){
			userAreaId = province;
		}else if($("#level").val() == "市级"){
			userAreaId = city;
		}else if($("#level").val() == "县/区级"){
			userAreaId = county;
		}else if($("#level").val() == "乡/镇级"){
			userAreaId = town;
		}
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/findUserByAreaIdAndTypeAndPri.do',
			async : false,
			data : {
				level : level,
				userAreaId : userAreaId,
				primarykey : primarykey,
			},
			dataType : 'json',
			success : function(msg) {
				if (msg.result == true) {
					var areaNames = new Array();
					var areaNum = 0;
					// 获取所有用户的部门信息
					$.each(msg.areaListFirst, function(key, val) {
						areaNames[areaNum] = val;
						areaNum++;
					});
					var userListData = "<caption>用户列表</caption><tr class=\"success\"><th>用户账号</th><th>用户姓名</th><th>联系电话</th><th>部门</th><th>账号类型</th><th>行政级别</th><th>操作</th></tr>";
					$.each(msg.recordFirst, function(key, val) {
						if(val.userName.length > 10){
							val.userName = val.userName.substr(0,10);
						}
						if(msg.user.userType != "超级管理员" && val.userType == "管理员" && val.userId != msg.user.userId){
							userListData += "<tr>" + "<td>" + val.userLoginname
							+ "</td>" + "<td>" + val.userName + "</td>"
							+ "<td>" + val.userPhone + "</td>" + "<td>"
							+ areaNames[key] + "</td>" + "<td>"
							+ val.userType + "</td>" + "<td>"
							+ val.userLevel + "</td>"
							+ "<td>" + "修改"
							+ "&nbsp;&nbsp;"
							+ "删除" + "</td>"
				       		+ "</tr>";
						}else if(msg.user.userId == val.userId){
							userListData += "<tr>" + "<td>" + val.userLoginname
						    + "</td>" + "<td>" + val.userName + "</td>"
						    + "<td>" + val.userPhone + "</td>" + "<td>"
						    + areaNames[key] + "</td>" + "<td>"
						    + val.userType + "</td>" + "<td>"
					    	+ val.userLevel + "</td>"
					    	+ "<td><a href=\"edituser.html?id="
						    + val.userId + "\">" + "修改</a>"
					    	+ "&nbsp;&nbsp;"
						    + "删除" + "</td>"
			       		    + "</tr>";
						}else{
						    userListData += "<tr>" + "<td>" + val.userLoginname
						    + "</td>" + "<td>" + val.userName + "</td>"
						    + "<td>" + val.userPhone + "</td>" + "<td>"
						    + areaNames[key] + "</td>" + "<td>"
						    + val.userType + "</td>" + "<td>"
					    	+ val.userLevel + "</td>"
					    	+ "<td><a href=\"edituser.html?id="
						    + val.userId + "\">" + "修改</a>"
					    	+ "&nbsp;&nbsp;"
						    + "<a href=\"javascript:deleteUser("
						    + val.userId + ")\">删除</a>" + "</td>"
			       		    + "</tr>";
						}
					});
					$("#userList").empty().append(userListData);
					$("#demo5").paginate({
						count 		: msg.pageCount,
						start 		: 1,
						display     : 10,
						border					: true,
						border_color			: '#fff',
						text_color  			: '#fff',
						background_color    	: 'rgb(66,139,202)',	
						border_hover_color		: '#ccc',
						text_hover_color  		: '#000',
						background_hover_color	: '#fff', 
						images					: false,
						mouse					: 'press',
						onChange     			: function(page){
													//alert(page);
													$.ajax({
														type : "post",
														contentType : "application/x-www-form-urlencoded;charset=UTF-8",
														url : '/threeExaminations/userController/findRecordByPage1.do',
														async : false,
														data : {
															page:page,
														},
														dataType : 'json',
														success : function(msg) {
															if (msg.result == true) {
																var areaNames = new Array();
																//var areaNum = 0;
																// 获取所有用户的部门信息
																$.each(msg.areaListCurrent, function(key, val) {
																	areaNames[key] = val;
																	//areaNum++;
																});
																var userListData = "<caption>用户列表</caption><tr class=\"success\"><th>用户账号</th><th>用户姓名</th><th>联系电话</th><th>部门</th><th>账号类型</th><th>行政级别</th><th>操作</th></tr>";
																$.each(msg.recordCurrent, function(key, val) {
																	if(val.userName.length > 10){
																		val.userName = val.userName.substr(0,10);
																	}
																	if(msg.user.userType != "超级管理员" && val.userType == "管理员" && val.userId != msg.user.userId){
																		userListData += "<tr>"  + "<td>" + val.userLoginname
																		+ "</td>" + "<td>" + val.userName + "</td>"
																		+ "<td>" + val.userPhone + "</td>" + "<td>"
																		+ areaNames[key] + "</td>" + "<td>"
																		+ val.userType + "</td>" + "<td>"
																		+ val.userLevel + "</td>"
																		+ "<td>" + "修改"
																		+ "&nbsp;&nbsp;"
																		+ "删除" + "</td>"
															       		+ "</tr>";
																	}else if(msg.user.userId == val.userId){
																		userListData += "<tr>"  + "<td>" + val.userLoginname
																	    + "</td>" + "<td>" + val.userName + "</td>"
																	    + "<td>" + val.userPhone + "</td>" + "<td>"
																	    + areaNames[key] + "</td>" + "<td>"
																	    + val.userType + "</td>" + "<td>"
																    	+ val.userLevel + "</td>"
																    	+ "<td><a href=\"edituser.html?id="
																	    + val.userId + "\">" + "修改</a>"
																    	+ "&nbsp;&nbsp;"
																	    + "删除" + "</td>"
														       		    + "</tr>";
																	}else{
																	    userListData += "<tr>" + "<td>" + val.userLoginname
																	    + "</td>" + "<td>" + val.userName + "</td>"
																	    + "<td>" + val.userPhone + "</td>" + "<td>"
																	    + areaNames[key] + "</td>" + "<td>"
																	    + val.userType + "</td>" + "<td>"
																    	+ val.userLevel + "</td>"
																    	+ "<td><a href=\"edituser.html?id="
																	    + val.userId + "\">" + "修改</a>"
																    	+ "&nbsp;&nbsp;"
																	    + "<a href=\"javascript:deleteUser("
																	    + val.userId + ")\">删除</a>" + "</td>"
														       		    + "</tr>";
																	}
																});
																$("#userList").empty().append(userListData);
															}else{
																document.getElementById('warn').innerHTML = "获取失败";
																$('#myWarnModal').modal('show');
															} 
														},
														error : function(msg) {
															alert("网络超时！");
														}
													});
													
							
							/*$('._current','#list').removeClass('_current').hide();
							$('#p'+page).addClass('_current').show();*/
						}
					});
				} else {
					alert(msg.message);
				}
			},
			error : function(msg) {
				alert("网络超时！");
			}
		});
		}
    });
});

// 显示当前级别管理员管理的同级别的本地所有的用户列表
function showAllUser() {
	$("#level").empty().append('<option>' + '全部' + '</option>');
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/showAllUser.do',
		async : false,
		data : {
			
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var areaNames = new Array();
				var areaNum = 0;
				// 获取用户的部门信息
				$.each(msg.areaListFirst, function(key, val) {
					areaNames[areaNum] = val;
					
					areaNum++;
				});
				
				var userListData = "<caption>用户列表</caption><tr class=\"success\"><th>用户账号</th><th>用户姓名</th><th>联系电话</th><th>部门</th><th>账号类型</th><th>行政级别</th><th>操作</th></tr>";
				$.each(msg.recordFirst, function(key, val) {
					if(val.userName.length > 10){
						val.userName = val.userName.substr(0,10);
					}
					if(msg.user.userType != "超级管理员" && val.userType == "管理员" && val.userId != msg.user.userId){
						userListData += "<tr>" + "<td>" + val.userLoginname
						+ "</td>" + "<td>" + val.userName + "</td>"
						+ "<td>" + val.userPhone + "</td>" + "<td>"
						+ areaNames[key] + "</td>" + "<td>"
						+ val.userType + "</td>" + "<td>"
						+ val.userLevel + "</td>"
						+ "<td>" + "修改"
						+ "&nbsp;&nbsp;"
						+ "删除" + "</td>"
			       		+ "</tr>";
					}else if(msg.user.userId == val.userId){
						userListData += "<tr>" + "<td>" + val.userLoginname
					    + "</td>" + "<td>" + val.userName + "</td>"
					    + "<td>" + val.userPhone + "</td>" + "<td>"
					    + areaNames[key] + "</td>" + "<td>"
					    + val.userType + "</td>" + "<td>"
				    	+ val.userLevel + "</td>"
				    	+ "<td><a href=\"edituser.html?id="
					    + val.userId + "\">" + "修改</a>"
				    	+ "&nbsp;&nbsp;"
					    + "删除" + "</td>"
		       		    + "</tr>";
					}else{
					    userListData += "<tr>" + "<td>" + val.userLoginname
					    + "</td>" + "<td>" + val.userName + "</td>"
					    + "<td>" + val.userPhone + "</td>" + "<td>"
					    + areaNames[key] + "</td>" + "<td>"
					    + val.userType + "</td>" + "<td>"
				    	+ val.userLevel + "</td>"
				    	+ "<td><a href=\"edituser.html?id="
					    + val.userId + "\">" + "修改</a>"
				    	+ "&nbsp;&nbsp;"
					    + "<a href=\"javascript:deleteUser("
					    + val.userId + ")\">删除</a>" + "</td>"
		       		    + "</tr>";
					}
				});
				$("#userList").empty().append(userListData);
				$("#demo5").paginate({
					count 		: msg.pageCount,
					start 		: 1,
					display     : 10,
					border					: true,
					border_color			: '#fff',
					text_color  			: '#fff',
					background_color    	: 'rgb(66,139,202)',	
					border_hover_color		: '#ccc',
					text_hover_color  		: '#000',
					background_hover_color	: '#fff', 
					images					: false,
					mouse					: 'press',
					onChange     			: function(page){
												//alert(page);
												$.ajax({
													type : "post",
													contentType : "application/x-www-form-urlencoded;charset=UTF-8",
													url : '/threeExaminations/userController/findRecordByPage.do',
													async : false,
													data : {
														page:page
													},
													dataType : 'json',
													success : function(msg) {
														if (msg.result == true) {
															var areaNames1 = new Array();
														
															// 获取用户的部门信息
															$.each(msg.areaListCurrent, function(key, val) {
																areaNames1[key] = val;
															});
															var userListData1 = "<caption>用户列表</caption><tr class=\"success\"><th>用户账号</th><th>用户姓名</th><th>联系电话</th><th>部门</th><th>账号类型</th><th>行政级别</th><th>操作</th></tr>";
															$.each(msg.recordCurrent, function(key, val) {
																if(val.userName.length > 10){
																	val.userName = val.userName.substr(0,10);
																}
																if(msg.user.userType != "超级管理员" && val.userType == "管理员" && val.userId != msg.user.userId){
																	userListData1 += "<tr>"+ "<td>" + val.userLoginname
																	+ "</td>" + "<td>" + val.userName + "</td>"
																	+ "<td>" + val.userPhone + "</td>" + "<td>"
																	+ areaNames1[key] + "</td>" + "<td>"
																	+ val.userType + "</td>" + "<td>"
																	+ val.userLevel + "</td>"
																	+ "<td>" + "修改"
																	+ "&nbsp;&nbsp;"
																	+ "删除" + "</td>"
														       		+ "</tr>";
																}else if(msg.user.userId == val.userId){
																	userListData1 += "<tr>" + "<td>" + val.userLoginname
																    + "</td>" + "<td>" + val.userName + "</td>"
																    + "<td>" + val.userPhone + "</td>" + "<td>"
																    + areaNames1[key] + "</td>" + "<td>"
																    + val.userType + "</td>" + "<td>"
															    	+ val.userLevel + "</td>"
															    	+ "<td><a href=\"edituser.html?id="
																    + val.userId + "\">" + "修改</a>"
															    	+ "&nbsp;&nbsp;"
																    + "删除" + "</td>"
													       		    + "</tr>";
																}else{						
																    userListData1 += "<tr>" + "<td>" + val.userLoginname
																    + "</td>" + "<td>" + val.userName + "</td>"
																    + "<td>" + val.userPhone + "</td>" + "<td>"
																    + areaNames1[key] + "</td>" + "<td>"
																    + val.userType + "</td>" + "<td>"
															    	+ val.userLevel + "</td>"
															    	+ "<td><a href=\"edituser.html?id="
																    + val.userId + "\">" + "修改</a>"
															    	+ "&nbsp;&nbsp;"
																    + "<a href=\"javascript:deleteUser("
																    + val.userId + ")\">删除</a>" + "</td>"
													       		    + "</tr>";
																}
															});
															$("#userList").empty().append(userListData1);
														}else{
															alert("获取失败！");
														} 
													},
													error : function(msg) {
														alert("网络超时！");
													}
												});
												
						
						/*$('._current','#list').removeClass('_current').hide();
						$('#p'+page).addClass('_current').show();*/
					}
				});
				// 动态设置查看的级别选项
				var level = "";
				var levelNum = 0;
				$.each(msg.userLevelList, function(key, val) {
					level += '<option>' + val + '</option>';
					levelNum++;
				});
				$("#level").empty().append(level);
				if(msg.user.userType == "超级管理员"){
					document.getElementById("level").disabled=false;
				    document.getElementById("province").disabled=false;
				    document.getElementById("city").disabled=false;
    				document.getElementById("county").disabled=false;
	    			//document.getElementById("town").disabled=false;
				}else if(msg.user.userLevel == "县/区级"){
					document.getElementById("level").disabled=false;
				}else{
					
				}
			} else {
				// alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}

function findArea(areaParentId, selectId) {
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/areaController/searchAreasByAreaParentId.do',
		async : false,
		data : {
			areaParentId : areaParentId
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var cityName = "";
				$.each(msg.areaList, function(key, val) {
					cityName += '<option value="' + val.areaId + '">'
							+ val.areaName + '</option>';
				});
				$(selectId).empty().append(cityName);
			} else {
				alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
};

function levelChange() {
if(adminLevel == "超级管理员"){
	ClearArea();
	if ($("#level").val() == "省级") {
		var areaParentId = 0;
		findArea(areaParentId, "#province");
	} else if ($("#level").val() == "市级") {
		var areaParentId = 0;
		findArea(areaParentId, "#province");
		var province = $("#province").val();
		findArea(province, "#city");
	} else if ($("#level").val() == "县/区级") {
		var areaParentId = 0;
		findArea(areaParentId, "#province");
		var province = $("#province").val();
		findArea(province, "#city");
		var city = $("#city").val();
		findArea(city, "#county");
	} else if ($("#level").val() == "乡/镇级") {
		var areaParentId = 0;
		findArea(areaParentId, "#province");
		var province = $("#province").val();
		findArea(province, "#city");
		var city = $("#city").val();
		findArea(city, "#county");
		var county = $("#county").val();
		findArea(county, "#town");
		document.getElementById("town").disabled=false;
	}
}else{
	if($("#level").val() == "乡/镇级"){						
		document.getElementById("town").disabled=false;
		findArea(0,"#province");//查找所有省
		$("#province").val(area[3].areaId);//设置省
		findArea($("#province").val(),"#city");//查找省下面的市
		$("#city").val(area[2].areaId);//设置市
		findArea($("#city").val(),"#county");//查找市下面的县/区
		$("#county").val(area[1].areaId);//设置县区
		findArea($("#county").val(),"#town");//查找县/区下面的乡/镇
	}else if($("#level").val() == "县/区级"){
		$("#town").empty();
		document.getElementById("town").disabled=true;
		findArea(0,"#province");//查找所有省
		$("#province").val(area[3].areaId);//设置省
		findArea($("#province").val(),"#city");//查找省下面的市
		$("#city").val(area[2].areaId);//设置市
		findArea($("#city").val(),"#county");//查找市下面的县/区
		$("#county").val(area[1].areaId);//设置县区
	}
}/*else{
	//省市级不设置地区选择框内容，默认为全部
}*/
};


//地区选择
function provinceChange() {
	var province = $("#province").val();
	findArea(province,"#city");
	var city = $("#city").val();
	findArea(city,"#county");
	var county = $("#county").val();
	findArea(county,"#town");
	if($("#level").val() == "省级"){
		$("#city").empty();
		$("#county").empty();
		$("#town").empty();
	}else if($("#level").val() == "市级"){
		$("#county").empty();
		$("#town").empty();
	}else if($("#level").val() == "县/区级"){
		$("#town").empty();
	}else if($("#level").val() == "乡/镇级"){
		
	}
};

function cityChange(){
	var city = $("#city").val();
	findArea(city,"#county");
	var county = $("#county").val();
	findArea(county,"#town");
	if($("#level").val() == "市级"){
		$("#county").empty();
		$("#town").empty();
	}else if($("#level").val() == "县/区级"){
		$("#town").empty();
	}else if($("#level").val() == "乡/镇级"){
	}
};

function countyChange(){
	var county = $("#county").val();
	findArea(county,"#town");
	if($("#level").val() == "县/区级"){
		$("#town").empty();
	}else if($("#level").val() == "乡/镇级"){
	}
};

// 删除用户
function deleteUser(userId) {
	//$("#isDelete").click();//提示是否删除
	$('#myDeleteModal').modal('show');
	$("#sureDelete").click(function(){//确定删除
		//$("#myDeleteModal").hide();
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/deleteUser.do',
			async : false,
			data : {
				userId : userId
			},
			dataType : 'json',
			success : function(msg) {
				if (msg.result == true) {
					document.getElementById('warn').innerHTML = "删除成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
						location.replace(location.href);
					});
					//showAllUser();
				} else {
					document.getElementById('warn').innerHTML =msg.message;
					$('#myWarnModal').modal('show');
				}
			},
			error : function(msg) {
				alert("网络超时！");
			}
		});
	});
};

function ClearArea(){
	$("#province").empty();
	$("#city").empty();
	$("#county").empty();
	$("#town").empty();
}