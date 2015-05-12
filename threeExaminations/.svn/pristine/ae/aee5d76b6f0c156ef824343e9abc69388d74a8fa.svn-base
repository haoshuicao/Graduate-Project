/**
 * 超级管理员添加用户
 */
$(document).ready(function(){
	//初始化添加用户页面的账号等级和权限设置
	findLevel(0);//设置添加用户的级别选择框
	if($("#userType").val()=="超级管理员"){
		//showAuthority(0);//所有子权限
		showAuthority3($("#userType").val());
	}else if($("#userType").val()=="管理员"){
		showAuthority(34);//管理权限
	}else{
		showAuthority3($("#level").val());//管理员创建某级别所能分配的最大权限
	}
	//添加用户
	$("#addUser").click(function(){
		var userLoginName=$("#userLoginName").val();
		var userName=$("#userName").val();
		//var userLoginPwd=$("#userLoginPwd").val();
		var userPassword=$("#userLoginPwd").val();
		var userLoginPwd=hex_md5(userPassword);//md5加密
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
		//alert(areaId);
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
			//$("#showWarn").click();
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
					//alert(msg.message);
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

//显示超级管理员可分配某一权限组下的子权限
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
				//alert(msg.message);
				document.getElementById('warn').innerHTML =msg.message;
				$('#myWarnModal').modal('show');
			}
		},error: function(msg){
		       alert("网络超时！");
		}
	});
}

//显示当前登录级别的管理员所拥有的初管理权限以外的所有权限
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

//级别选择编辑框设置
function findLevel(flag){
	//alert("级别设置");
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
				//设置可选择的用户级别
				if(flag == 0){
					$("#level").empty().append(level);				
				}
				var areaFlag = 0;
				var area = new Array();
				$.each(msg.areaList,function(key,val){
					//area[0]为当前管理员的最低级别地区
					areaFlag++;
					area[areaFlag] = val;
					});
				//根据级别设置地区
				
				if(levelNum == 3){//超级管理员可添加所有级别的管理员账号
					levelChange();//初始化地区选择框中的地区
					document.getElementById("level").disabled=false;
				    document.getElementById("province").disabled=false;
				    document.getElementById("city").disabled=false;
    				document.getElementById("county").disabled=false;
	    			//document.getElementById("town").disabled=false;
	    			$("#userType").val("管理员");
				}else if(levelNum == 2){
					$("#userType").val("普通用户");
					document.getElementById("level").disabled=false;
					//alert(area[1].areaId);
					if($("#level").val() == "乡/镇级"){
						/*$("#province").empty().append('<option value="'+area[3].areaId+'">'+area[3].areaName+'</optin>');
						$("#city").empty().append('<option value="'+area[2].areaId+'">'+area[2].areaName+'</optin>');
						$("#county").empty().append('<option value="'+area[1].areaId+'">'+area[1].areaName+'</optin>');
						var county = $("#county").val();
						findArea(county,"#town");*/
						document.getElementById("town").disabled=false;
						findArea(0,"#province");//查找所有省
						$("#province").val(area[3].areaId);//设置省
						findArea($("#province").val(),"#city");//查找省下面的市
						$("#city").val(area[2].areaId);//设置市
						findArea($("#city").val(),"#county");//查找市下面的县/区
						$("#county").val(area[1].areaId);//设置县区
						findArea($("#county").val(),"#town");//查找县/区下面的乡/镇
					}else if($("#level").val() == "县/区级"){
						findArea(0,"#province");//查找所有省
						$("#province").val(area[3].areaId);//设置省
						findArea($("#province").val(),"#city");//查找省下面的市
						$("#city").val(area[2].areaId);//设置市
						findArea($("#city").val(),"#county");//查找市下面的县/区
						$("#county").val(area[1].areaId);//设置县区
					}
				}else if(levelNum == 1){
					$("#userType").val("普通用户");
					//用户地区设置
					if($("#level").val() == "省级"){	
						findArea(0,"#province");//查找所有省
						$("#province").val(area[1].areaId);
					}else if($("#level").val() == "市级"){
						findArea(0,"#province");//查找所有省
						$("#province").val(area[2].areaId);//设置省
						findArea($("#province").val(),"#city");//查找省下面的市
						$("#city").val(area[1].areaId);//设置市
					}
				}
			} else{
				//alert(msg.message);
			}
		},
		error : function(msg) {
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
				//alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
};

//地区选择
function provinceChange() {
	//alert("sheng");
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
	//alert("shi");
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
	//alert("xian");
	findArea(county,"#town");
	if($("#level").val() == "县/区级"){
		$("#town").empty();
	}else if($("#level").val() == "乡/镇级"){
	}
};

//账号等级与地区信息的联动
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
	}else if($("#level").val() == "乡/镇级"){//创建乡镇级用户只有县区级管理员，不考虑超级管理员
		/*var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
		var city = $("#city").val();
		findArea(city,"#county");
		var county = $("#county").val();
		findArea(county,"#town");
		document.getElementById("town").disabled=false;
		findLevel(1);//设置对应县区下的乡镇
*/		
		document.getElementById("town").disabled=false;
		findArea($("#county").val(),"#town");//查找县/区下面的乡/镇
	}
	if($("#userType").val()=="超级管理员"){
		showAuthority3($("#userType").val());
	}else if($("#userType").val()=="管理员"){
		//showAuthority(0);//所有子权限
		showAuthority(34);//管理权限
	}else{
		showAuthority3($("#level").val());//管理员创建某级别所能分配的最大权限
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