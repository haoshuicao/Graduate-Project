/**
 * 超级管理员修改用户
 */
$(document).ready(function(){
	//获取选中用户信息
	var Request = new Object();
	Request = GetRequest();
	var userId ;	
	userId = Request['id'];
	showUserInfo(userId);
	//保存修改
	$("#editUser").click(function(){
		var userLoginName=$("#userLoginName").val();
		var userName=$("#userName").val();
		var userLoginPwd1=$("#userLoginPwd").val();
		var userLoginPwd2=$("#userLoginPwd2").val();
		var userLoginPwd=hex_md5(userLoginPwd1);//md5加密
		/*if(userPassword != reUserPassword){
			alert("两次输入的密码不一致，请重新输入！！");
			}*/
		var userPhone=$("#userPhone").val();
		var userEmail=$("#userEmail").val();
		//var userLevel=$("#level").val();
		var userLevel=$("#level").val();//超级管理员的级别是关联地区的，类型是“超级管理员”
		var province=$("#province").val();
		var city=$("#city").val();
		var county=$("#county").val();
		var town=$("#town").val();
		var userType=$("#userType").val();
		var areaId = 1;
		if($("#level").val() == "省级"){
			areaId = province;
		}else if($("#level").val() == "市级"){
			areaId = city;
		}else if($("#level").val() == "县/区级"){
			areaId = county;
		}else if($("#level").val() == "乡/镇级"){
			areaId = town;
		}
		//alert(userLevel);
		var checkedNum = 0;
		var getCK = document.getElementsByTagName('input');
		var usauAuthId = "";
		for (var i = 0; i < getCK.length; i++) {
			whichObj = getCK[i];
			if (whichObj.type == "checkbox") {
				if (whichObj.checked == true) {
					usauAuthId += whichObj.value + ",";
					checkedNum++;
				}
			}
		}
		if (checkedNum == 0) {
			document.getElementById('warn').innerHTML = "至少选择一个权限";
			$('#myWarnModal').modal('show');
			return;
		}
		if(checkUser()){
			//alert(checkedNum);
			//alert(usauAuthId);
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/editUser.do',
			async : false,
			data : {
				userId:userId,
				userLoginName:userLoginName,
				userName:userName,
				userLoginPwd:userLoginPwd,
				userPhone:userPhone,
				userEmail:userEmail,
				userLevel:userLevel,
				areaId:areaId,
				userType:userType,
				usauAuthId:usauAuthId
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result == true){
					document.getElementById('warn').innerHTML = "修改成功";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
						document.location.href="lookuserlist.html";
					});
				}else{
					//alert(msg.message);
					document.getElementById('warn').innerHTML =msg.message;
					$('#myWarnModal').modal('show');
				}
			},error: function(msg){
			       alert("网络超时！");
			}
		});
		}
	});
});
//显示选中用户的信息
function showUserInfo(userId){
	$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/findUserByUserId.do',
			async : false,
			data : {
				userId:userId,
			},
			dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				//设置其他值
				$("#userLoginName").val(msg.editUser.userLoginname);
				$("#userName").val(msg.editUser.userName);
				//$("#userLoginPwd").val(msg.editUser.userLoginpwd);
				$("#userPhone").val(msg.editUser.userPhone);
				$("#userEmail").val(msg.editUser.userEmail);
				$("#userType").val(msg.editUser.userType);

				//用户所处区域设置
				var areaFlag = 0;
				var area = new Array();
				$.each(msg.areaList,function(key,val){
					//id 从乡/镇级到省级
					areaFlag++;
					area[areaFlag] = val;
					});
				//超级管理员可修改用户行政级别
				if(msg.user.userType == "超级管理员" && msg.editUser.userType != "超级管理员"){//超级管理员修改其他用户
				    document.getElementById("province").disabled=false;
				    document.getElementById("city").disabled=false;
    				document.getElementById("county").disabled=false;
	    			//document.getElementById("town").disabled=false;
    				document.getElementById("level").disabled=false;
    				/*if(msg.editUser.userType != "超级管理员"){
    					document.getElementById("level").disabled=false;
    				}else{//超级管理员修改自己的信息
    					document.getElementById("town").disabled=false;
    				}*/
				}
				//用户级别设置,1为省级，2为市级，3为县/区级，4为乡/镇级
				//alert(msg.editUser.userLevel);
				$("#level").val(msg.editUser.userLevel);
				//levelChange();
				if(areaFlag == 1){
					findArea(0,"#province");//查找所有省
					$("#province").val(area[1].areaId);
				}else if(areaFlag == 2){
					findArea(0,"#province");//查找所有省
					$("#province").val(area[2].areaId);//设置省
					findArea($("#province").val(),"#city");//查找省下面的市
					$("#city").val(area[1].areaId);//设置市
				}else if(areaFlag == 3){
	    			//document.getElementById("town").disabled=false;
					findArea(0,"#province");//查找所有省
					$("#province").val(area[3].areaId);//设置省
					findArea($("#province").val(),"#city");//查找省下面的市
					$("#city").val(area[2].areaId);//设置市
					findArea($("#city").val(),"#county");//查找市下面的县/区
					$("#county").val(area[1].areaId);//设置县区
				}else if(areaFlag == 4){
	    			//document.getElementById("town").disabled=false;
					findArea(0,"#province");//查找所有省
					$("#province").val(area[4].areaId);//设置省
					findArea($("#province").val(),"#city");//查找省下面的市
					$("#city").val(area[3].areaId);//设置市
					findArea($("#city").val(),"#county");//查找市下面的县/区
					$("#county").val(area[2].areaId);//设置县区
					findArea($("#county").val(),"#town");//查找县/区下面的乡/镇
					$("#town").val(area[1].areaId);//设置乡镇
				}
				/*}else{
					$("#level").empty().append('<option>'+msg.user.userLevel+'</option>');
					if(areaFlag == 1){
						$("#province").empty().append('<option value="'+area[1].areaId+'">'+area[1].areaName+'</option>');
					}else if(areaFlag == 2){
						$("#province").empty().append('<option value="'+area[2].areaId+'">'+area[2].areaName+'</option>');
						$("#city").empty().append('<option value="'+area[1].areaId+'">'+area[1].areaName+'</option>');
					}else if(areaFlag == 3){
						$("#province").empty().append('<option value="'+area[3].areaId+'">'+area[3].areaName+'</option>');
						$("#city").empty().append('<option value="'+area[2].areaId+'">'+area[2].areaName+'</option>');
						$("#county").empty().append('<option value="'+area[1].areaId+'">'+area[1].areaName+'</option>');
					}else if(areaFlag == 4){
						$("#province").empty().append('<option value="'+area[4].areaId+'">'+area[4].areaName+'</option>');
						$("#city").empty().append('<option value="'+area[3].areaId+'">'+area[3].areaName+'</option>');
						$("#county").empty().append('<option value="'+area[2].areaId+'">'+area[2].areaName+'</option>');
						$("#town").empty().append('<option value="'+area[1].areaId+'">'+area[1].areaName+'</option>');
					}
				}*/
				//显示可以分配的权限
				//showAuthority(0);
				if($("#userType").val()=="超级管理员"){
					showAuthority3($("#userType").val());
				}else if($("#userType").val()=="管理员"){
					//showAuthority(0);//所有子权限
					showAuthority(34);//管理权限
				}else{
					showAuthority3($("#level").val());//管理员创建某级别所能分配的最大权限
				}
				//全部的input选项置为未选中
				var getCK = document.getElementsByTagName('input');
				for (var i = 0; i < getCK.length; i++) {
					whichObj = getCK[i];
					if (whichObj.type == "checkbox") {
						if (whichObj.checked == true) {
							whichObj.checked = false;
						}
					}
				}
				//用户当前权限标示,通过用户id获取原来的权限id,将每一个id对应的input选中
				var userAuth = "";
				$.each(msg.authList,function(key,val){
					userAuth+=val.authId; 
					var getCK = document.getElementsByTagName('input');
					for (var i = 0; i < getCK.length; i++) {
						whichObj = getCK[i];
						if (whichObj.type == "checkbox" && whichObj.value == val.authId) {
							whichObj.checked = true;
						    }else if(whichObj.type == "checkbox" && msg.user.userId == msg.editUser.userId){
								whichObj.disabled=true;
							}
						}
					});
			}else{
				document.getElementById('warn').innerHTML =msg.message;
				$('#myWarnModal').modal('show');
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}

//显示可分配的子权限表
function showAuthority(authParentId){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/authorityController/searchUserAuthority.do',
		async : false,
		data : {
			authParentId:authParentId
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result == true){
				var authorityList="";
				var i = 1;
				$.each(msg.authList, function(key, val){
					authorityList += '<div><input id="checkbox' + i + '" value="' + val.authId + '" type="checkbox"><label style="font-weight:400" for="checkbox' + i + '">&nbsp;&nbsp;&nbsp;' + val.authName + '</label></div>';
				    i++;
				$("#authorityList").empty().append(authorityList);
				});
			}else{
				document.getElementById('warn').innerHTML =msg.message;
				$('#myWarnModal').modal('show');
			}
		},error: function(msg){
		       alert("网络超时！");
		}
	});
}

//根据管理员创建的用户的级别所能分配的最大权限
function showAuthority3(level){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/authorityController/showCurrentLevelAuthority.do',
		async : false,
		data : {
			level : level
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result == true){
				var authorityList="";
				var i = 1;
				$.each(msg.authList, function(key, val){
					authorityList += '<div><input id="checkbox' + i + '" value="' + val.authId + '" type="checkbox"><label style="font-weight:400" for="checkbox' + i + '">&nbsp;&nbsp;&nbsp;' + val.authName + '</label></div>';
				    i++;
				$("#authorityList").empty().append(authorityList);
				});
				var getCK = document.getElementsByTagName('input');
				for (var i = 0; i < getCK.length; i++) {
					whichObj = getCK[i];
					if (whichObj.type == "checkbox") {
						if (whichObj.checked == false) {
							whichObj.checked = true;
						}
					}
				}
			}else{
				document.getElementById('warn').innerHTML =msg.message;
				$('#myWarnModal').modal('show');
			}
		},error: function(msg){
		       alert("网络超时！");
		}
	});
}

//地区选择框编辑
function findArea(areaParentId,selectId){
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
				var cityName="";				
				$.each(msg.areaList,function(key,val){
					cityName+='<option value="'+val.areaId+'">' + val.areaName + '</option>'; 
					});
				$(selectId).empty().append(cityName);
			} else{
				document.getElementById('warn').innerHTML =msg.message;
				$('#myWarnModal').modal('show');
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
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

function levelChange(){
	if($("#level").val() == "省级"){
		//alert("jjjj");
		ClearArea();
		var areaParentId = 0;
		findArea(areaParentId,"#province");
	}else if($("#level").val() == "市级"){
		ClearArea();
		var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
	}else if($("#level").val() == "县/区级"){
		if($("#userType").val() != "普通用户"){
			   ClearArea();
			   var areaParentId = 0;
			   findArea(areaParentId,"#province");
			   var province = $("#province").val();
			   findArea(province,"#city");
			   var city = $("#city").val();
			   findArea(city,"#county");
			}else{
				$("#town").empty();
				document.getElementById("town").disabled=true;
			}
	}else if($("#level").val() == "乡/镇级"){
		/*var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
		var city = $("#city").val();
		findArea(city,"#county");
		var county = $("#county").val();
		findArea(county,"#town");*/
		document.getElementById("town").disabled=false;
		findArea($("#county").val(),"#town");//查找县/区下面的乡/镇
	}
}

function ClearArea(){
	$("#province").empty();
	$("#city").empty();
	$("#county").empty();
	$("#town").empty();
}

function checkUser(){
	if($("#userLoginName").val() == ""){
		document.getElementById("checkLoginName1").style.display="block";
		document.getElementById("checkLoginName2").style.display="none";
		document.getElementById("userLoginName").focus();
		return false;
	}else if($("#userName").val() == ""){
		document.getElementById("checkRealName1").style.display="block";
		document.getElementById("checkRealName2").style.display="none";
		document.getElementById("userName").focus();
		return false;
	}else if($("#userLoginPwd").val() == ""){
		document.getElementById("checkPwd1").style.display="block";
		document.getElementById("checkPwd2").style.display="none";
		document.getElementById("userLoginPwd").focus();
		return false;
	}else if($("#userLoginPwd2").val() == ""){
		document.getElementById("checkRePwd1").style.display="block";
		document.getElementById("checkRePwd2").style.display="none";
		document.getElementById("userLoginPwd2").focus();
		return false;
	}else if($("#userPhone").val() == ""){
		document.getElementById("checkPhone1").style.display="block";
		document.getElementById("checkPhone2").style.display="none";
		document.getElementById("userPhone").focus();
		return false;
	}
	else if($("#userEmail").val() == ""){
		document.getElementById("checkEmail1").style.display="block";
		document.getElementById("checkEmail2").style.display="none";
		document.getElementById("userEmail").focus();
		return false;
	}else{
		return true;
	}
}

function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
			}
		}
	return theRequest;
}
