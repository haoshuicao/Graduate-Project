$(document).ready(function(){
	findLevel(0);
	if($("#userType").val()=="超级管理员"){
		showAuthority3($("#userType").val());
	}else if($("#userType").val()=="管理员"){
		showAuthority(34);
	}else{
		showAuthority3($("#level").val());
	}
	$("#addUser").click(function(){
		var userLoginName=$("#userLoginName").val();
		var userName=$("#userName").val();
		var userPassword=$("#userLoginPwd").val();
		var userLoginPwd=hex_md5(userPassword);
		var userLoginPwd2=$("#userLoginPwd2").val();
		var userPhone=$("#userPhone").val();
		var userEmail=$("#userEmail").val();
		var userLevel=$("#level").val();
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

		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/addUser.do',
			async : false,
			data : {
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
					document.getElementById('warn').innerHTML = "添加成功";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
						document.location.href="lookuserlist.html";
					});
				}else{
					document.getElementById('warn').innerHTML = msg.message;
					$('#myWarnModal').modal('show');
				}
			},error: function(msg){
			       alert("网络超时！");
			}
		});
		}
	});
});

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
				var authority="";
				var i = 0;
				$.each(msg.authList, function(key, val){
					authority += '<div><input id="checkbox' + i + '" value="' + val.authId + '" type="checkbox"><label style="font-weight:400" for="checkbox' + i + '">&nbsp;&nbsp;&nbsp;' + val.authName + '</label></div>';
					i++;
				
				    $("#authorityList").empty().append(authority);
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

function showAuthority2(){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/showCurrentUser.do',
		async : false,
		data : {
			
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result == true){
				var authorityList="";
				var i = 1;
				$.each(msg.authList, function(key, val){
					if(val.authParentid != 34){
					authorityList += '<div><input id="checkbox' + i + '" value="' + val.authId + '" type="checkbox"><label style="font-weight:400" for="checkbox' + i + '">&nbsp;&nbsp;&nbsp;' + val.authName + '</label></div>';
				    i++;
					}
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

function findLevel(flag){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/findCurrentAdminLevel.do',
		async : false,
		data : {
		
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var level="";
				var levelNum=0;
				$.each(msg.userLevelList,function(key,val){
				   level+='<option>' + val + '</option>';
				   levelNum++;
				});
				if(flag == 0){
					$("#level").empty().append(level);				
				}
				var areaFlag = 0;
				var area = new Array();
				$.each(msg.areaList,function(key,val){
					areaFlag++;
					area[areaFlag] = val;
					});
				
				if(levelNum == 3){
					levelChange();
					document.getElementById("level").disabled=false;
				    document.getElementById("province").disabled=false;
				    document.getElementById("city").disabled=false;
    				document.getElementById("county").disabled=false;
	    			$("#userType").val("管理员");
				}else if(levelNum == 2){
					$("#userType").val("普通用户");
					document.getElementById("level").disabled=false;
					if($("#level").val() == "乡/镇级"){
						document.getElementById("town").disabled=false;
						findArea(0,"#province");
						$("#province").val(area[3].areaId);
						findArea($("#province").val(),"#city");
						$("#city").val(area[2].areaId);
						findArea($("#city").val(),"#county");
						$("#county").val(area[1].areaId);
						findArea($("#county").val(),"#town");
					}else if($("#level").val() == "县/区级"){
						findArea(0,"#province");
						$("#province").val(area[3].areaId);
						findArea($("#province").val(),"#city");
						$("#city").val(area[2].areaId);
						findArea($("#city").val(),"#county");
						$("#county").val(area[1].areaId);
					}
				}else if(levelNum == 1){
					$("#userType").val("普通用户");
					if($("#level").val() == "省级"){	
						findArea(0,"#province");
						$("#province").val(area[1].areaId);
					}else if($("#level").val() == "市级"){
						findArea(0,"#province");
						$("#province").val(area[2].areaId);
						findArea($("#province").val(),"#city");
						$("#city").val(area[1].areaId);
					}
				}
			} else{
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
}

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
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
};

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
	findArea(county,"#town");
	if($("#level").val() == "县/区级"){
		$("#town").empty();
	}else if($("#level").val() == "乡/镇级"){
	}
};

function levelChange(){
	if($("#level").val() == "省级"){
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
		document.getElementById("town").disabled=false;
		findArea($("#county").val(),"#town");
	}
	if($("#userType").val()=="超级管理员"){
		showAuthority3($("#userType").val());
	}else if($("#userType").val()=="管理员"){
		showAuthority(34);
	}else{
		showAuthority3($("#level").val());
	}	
};

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