$(document).ready(function(){
	levelChange();
	$("#setting").click(function (){
		var province=$("#province").val();
		var city=$("#city").val();
		var county=$("#county").val();
		var town=$("#town").val();
		var provinceMaxUser=$("#provinceUser").val();
		var cityMaxUser=$("#cityUser").val();
		var countyMaxUser=$("#countyUser").val();
		var townMaxUser = $("#townUser").val();
		var setMaxUser = 0;
		var areaId=0;
		
		if($("#level").val() == "省级"){
			if(provinceMaxUser == ""){
			    document.getElementById('warn').innerHTML = "输入信息不能为空！";
			    $('#myWarnModal').modal('show');
			    return;
			}else{
				areaId = province;
				setMaxUser=provinceMaxUser;
			}
		}else if($("#level").val() == "市级"){
			if(cityMaxUser == ""){
		        document.getElementById('warn').innerHTML = "输入信息不能为空！";
		        $('#myWarnModal').modal('show');
				return;
	        }else{
	        	areaId = city;
				setMaxUser=cityMaxUser;
	        }	
		}else if($("#level").val() == "县/区级"){
			if(countyMaxUser == ""){
			    document.getElementById('warn').innerHTML = "输入信息不能为空！";
			    $('#myWarnModal').modal('show');
			    return;
			}else{
				areaId = county;
				setMaxUser=countyMaxUser;
			}
		}else if($("#level").val() =="乡/镇级"){
			if(town == null){
				return;
			}else if(townMaxUser == ""){
			    document.getElementById('warn').innerHTML = "输入信息不能为空！";
			    $('#myWarnModal').modal('show');
			    return;
			}else{
				areaId = town;
				setMaxUser=townMaxUser;
			}
		}
	
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/setMaxUserNum.do',
			async : false,
			data : {
				areaId:areaId,
				setMaxUser:setMaxUser
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					document.getElementById('warn').innerHTML = "设置成功！";
					$('#myWarnModal').modal('show');
					
					if($("#level").val() == "省级"){
						$("#provinceUser").val("");
						showUserNum($("#province").val(),"#provincemax","#provinceexist");
					}else if($("#level").val() == "市级"){
						$("#cityUser").val("");
						showUserNum($("#city").val(),"#citymax","#cityexist");
					}else if($("#level").val() == "县/区级"){
						$("#countyUser").val("");
						showUserNum($("#county").val(),"#countymax","#countyexist");
					}else {
						$("#townUser").val("");
						showUserNum($("#town").val(),"#townmax","#townexist");
					}
				}else{
					document.getElementById('warn').innerHTML = msg.message;
					$('#myWarnModal').modal('show');
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
});

function showUserNum(areaId,areaMax,areaExist){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/areaController/showUserNum.do',
		async : false,
		data : {
			areaId : areaId
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				$(areaMax).val(msg.area.areaMaxuser);
				$(areaExist).val(msg.area.areaExistuser);
			} else {
				
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
			} else {
				alert(msg.message);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
};

function levelChange(){
	$("#province").empty();
	$("#city").empty();
	$("#county").empty();
	$("#town").empty();
	ClearUserNum();
	if($("#level").val() == "省级"){
		var areaParentId = 0;
		findArea(areaParentId,"#province");
		showUserNum($("#province").val(),"#provincemax","#provinceexist");	
		document.getElementById("provinceUser").disabled=false;
		document.getElementById("cityUser").disabled=true;
		document.getElementById("countyUser").disabled=true;
		document.getElementById("townUser").disabled=true;
	}else if($("#level").val() == "市级"){
		var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		document.getElementById("provinceUser").disabled=true;
		document.getElementById("cityUser").disabled=false;
		document.getElementById("countyUser").disabled=true;
		document.getElementById("townUser").disabled=true;
	}else if($("#level").val() == "县/区级"){		   
		var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
		var city = $("#city").val();
		findArea(city,"#county");
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		document.getElementById("provinceUser").disabled=true;
		document.getElementById("cityUser").disabled=true;
		document.getElementById("countyUser").disabled=false;
		document.getElementById("townUser").disabled=true;
	}else if($("#level").val() == "乡/镇级"){
		var areaParentId = 0;
		findArea(areaParentId,"#province");
		var province = $("#province").val();
		findArea(province,"#city");
		var city = $("#city").val();
		findArea(city,"#county");
		var county = $("#county").val();
		findArea(county,"#town");
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		document.getElementById("provinceUser").disabled=true;
		document.getElementById("cityUser").disabled=true;
		document.getElementById("countyUser").disabled=true;
		if($("#town").val() != null){
			showUserNum($("#town").val(),"#townmax","#townexist");
			document.getElementById("townUser").disabled=false;
		}else{
			document.getElementById("townUser").disabled=true;
		}	
	}
};

function provinceChange() {
	var province = $("#province").val();
	findArea(province,"#city");
	var city = $("#city").val();
	findArea(city,"#county");
	var county = $("#county").val();
	findArea(county,"#town");
	var town = $("#town").val();
	if($("#level").val() =="省级"){
		$("#city").empty();
		$("#county").empty();
		$("#town").empty();		
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
	}else if($("#level").val() == "市级"){
		$("#county").empty();
		$("#town").empty();
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
	}else if($("#level").val() == "县/区级"){
		$("#town").empty();
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
	}else if($("#level").val() == "乡/镇级"){
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		if($("#town").val() != null){
			showUserNum($("#town").val(),"#townmax","#townexist");
			document.getElementById("townUser").disabled=false;
		}else{
			document.getElementById("townUser").disabled=true;
		}	
	}
	
};

function cityChange(){
	var city = $("#city").val();
	findArea(city,"#county");
	var county = $("#county").val();
	findArea(county,"#town");
	var town = $("#town").val();
	if($("#level").val() == "市级"){
		$("#county").empty();
		$("#town").empty();
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
	}else if($("#level").val() == "县/区级"){
		$("#town").empty();		
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
	}else if($("#level").val() == "乡/镇级"){
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		if($("#town").val() != null){
			showUserNum($("#town").val(),"#townmax","#townexist");
			document.getElementById("townUser").disabled=false;
		}else{
			document.getElementById("townUser").disabled=true;
		}	
	}
};

function countyChange(){
	var county = $("#county").val();
	findArea(county,"#town");
	var town = $("#town").val();
	if($("#level").val() == "县/区级"){
		$("#town").empty();
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
	}else if($("#level").val() == "乡/镇级"){
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		if($("#town").val() != null){
			showUserNum($("#town").val(),"#townmax","#townexist");
			document.getElementById("townUser").disabled=false;
		}else{
			document.getElementById("townUser").disabled=true;
		}	
	}
};

function townChange(){
	if($("#level").val() == "乡/镇级"){
		ClearUserNum();
		showUserNum($("#province").val(),"#provincemax","#provinceexist");
		showUserNum($("#city").val(),"#citymax","#cityexist");
		showUserNum($("#county").val(),"#countymax","#countyexist");
		if($("#town") != null){
			showUserNum($("#town").val(),"#townmax","#townexist");
			document.getElementById("townUser").disabled=false;
		}else{
			document.getElementById("townUser").disabled=true;
		}	
	}
}

function ClearArea(){
	$("#province").empty();
	$("#city").empty();
	$("#county").empty();
	$("#town").empty();
}

function ClearUserNum(){
	$("#provincemax").val("");
	$("#citymax").val("");
	$("#countymax").val("");
	$("#townmax").val("");
	$("#provinceexist").val("");
	$("#cityexist").val("");
	$("#countyexist").val("");
	$("#townexist").val("");
	$("#provinceUser").val("");
	$("#cityUser").val("");
	$("#countyUser").val("");
	$("#townUser").val("");
}

function checkNum(obj){
	var num=/^([1-9]\d*|0)(\d*[1-9])?$/;
	var objId=obj.id;
	if(!num.test(obj.value)){
		document.getElementById('warn').innerHTML = "请输入0或正整数！";
		$('#myWarnModal').modal('show');
		document.getElementById(objId).value="";
		document.getElementById(objId).focus();
	}else if(obj.value > 1000){
		document.getElementById('warn').innerHTML = "请输入1000以内的正整数或0！";
		$('#myWarnModal').modal('show');
		document.getElementById(objId).value="";
		document.getElementById(objId).focus();
	}
}
