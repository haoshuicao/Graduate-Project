$(document).ready(function(){ 
	var areaParentId = 0;
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/areaController/searchByAreaParentId.do',
		async : false,
		data : {
			areaParentId : areaParentId
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var province1 = "";
				$.each(msg.areaList, function(key, val) {
					province1 += '<option value="' + val.areaId + '">' + val.areaName + '</option>';
				});
				$("#womanProvince").empty().append(province1);
				var province2 = $("#womanProvince").val();
				findArea(province2,"#womanCity");
				$("#womanCity").val(msg.city);
				var city2 = $("#womanCity").val();
				findArea(city2,"#womanCounty");
				$("#womanCounty").val(msg.county);
				var county2 = $("#womanCounty").val();
				findArea(county2,"#womanTown");
				$("#womanTown").val(msg.town);
				
				$("#womanCurProvince").empty().append(province1); 
				var province2 = $("#womanCurProvince").val();
				findArea(province2,"#womanCurCity");
				$("#womanCurCity").val(msg.city);
				var city2 = $("#womanCurCity").val();
				findArea(city2,"#womanCurCounty");
				$("#womanCurCounty").val(msg.county);
				var county2 = $("#womanCurCounty").val();
				findArea(county2,"#womanCurTown");
				$("#womanCurTown").val(msg.town);
			}
		
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
	$("#save").click(function (){		
		if(cheack()){
			var womanName=$("#womanName").val();
			var womanIdCard=$("#womanIdCard").val();			
			var temObj="";
			var tempValue = "";
			
			temObj = document.getElementById("womanProvince");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanProvince = tempValue;
			temObj = document.getElementById("womanCity");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCity=tempValue;
			temObj = document.getElementById("womanCounty");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCounty=tempValue;
			temObj = document.getElementById("womanTown");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanTown=tempValue;
			
			var womanVallige=$("#womanVallige").val();
					
			temObj = document.getElementById("womanCurProvince");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCurProvince=tempValue;
			temObj = document.getElementById("womanCurCity");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCurCity=tempValue;
			temObj = document.getElementById("womanCurCounty");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCurCounty=tempValue;
			temObj = document.getElementById("womanCurTown");		
			tempValue = temObj.options[temObj.selectedIndex].text; 
			var womanCurTown=tempValue;
			var womanCurVallige=$("#womanCurVallige").val();
			
			var liveState=$("#liveState").val();
			var checkYear=$("#checkYear").val();
			var checkPatch=$("#checkPatch").val();
			var checkDate=$("#checkDate").val();
			var checkPlace=$("#checkPlace").val();
			var hoop=$("#hoop").val();
			var pregnant=$("#pregnant").val();
			var disease=$("#disease").val();
			var checkSuggest=$("#checkSuggest").val();
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				url : '/threeExaminations/threeCheckServiceController/save.do',
				async : false,
				data : {
					womanName:womanName,
					womanIdCard:womanIdCard,
					womanProvince:womanProvince,
					womanCity:womanCity,
					womanCounty:womanCounty,
					womanTown:womanTown,
					womanVallige:womanVallige,
					womanCurProvince:womanCurProvince,
					womanCurCity:womanCurCity,
					womanCurCounty:womanCurCounty,
					womanCurTown:womanCurTown,
					womanCurVallige:womanCurVallige,
					liveState:liveState,
					checkYear:checkYear,
					checkPatch:checkPatch,
					checkDate:checkDate,
					checkPlace:checkPlace,
					hoop:hoop,
					pregnant:pregnant,
					disease:disease,
					checkSuggest:checkSuggest
				},
				dataType : 'json',
				success : function(msg) {
					if(msg.result==true){
						alert("保存成功！");
						window.location.href='lookserverrecord.html';
					}else{
						alert(msg.message);
					}   
				},error: function(msg){
			        alert("网络超时！");
				}
			});
		}else{
			return true;
		}
	});
});

function validateName(){
	if($("#womanName").val()==""){
		document.getElementById("fanWei").style.display = "none";
		document.getElementById("isNull").style.display = "block";
		return false;
	}else if(($("#womanName").val()).length>20){
		document.getElementById("isNull").style.display = "none";
		document.getElementById("fanWei").style.display = "block";
		return false;
	}else{
		document.getElementById("isNull").style.display = "none";
		document.getElementById("fanWei").style.display = "none";
		return true;
	}
}
function empty1(){
	document.getElementById("isNull").style.display = "none";
}
function empty2(){
	document.getElementById("idIsNull").style.display = "none";
}
function empty3(){
	document.getElementById("Valliage").style.display = "none";
}
function empty4(){
	document.getElementById("nowValliage").style.display = "none";
}
function empty5(){
	document.getElementById("dateIsNull").style.display = "none";
}
function empty6(){
	document.getElementById("checkPlaceIsNull").style.display = "none";
}

function validateDate(){
	if($("#checkDate").val()==""){
		document.getElementById("dateIsRight").style.display = "none";
		document.getElementById("dateIsNull").style.display = "block";
		return false;
	}else{
		var yourtime=$("#checkDate").val();
		yourtime = yourtime.replace("-","/");
		var d2=new Date();
		var d1 = new Date(Date.parse(yourtime));
		if(d1>d2){
			document.getElementById("dateIsNull").style.display = "none";
			document.getElementById("dateIsRight").style.display = "block";
			return false;
		}else{
			document.getElementById("dateIsNull").style.display = "none";
			document.getElementById("dateIsRight").style.display = "none";
			return true;
		}
	}
}

function validateCheckPlace(){
	if($("#checkPlace").val()==""){
		document.getElementById("checkPlaceIsRight").style.display = "none";
		document.getElementById("checkPlaceIsNull").style.display = "block";
		return false;
	}else if(($("#checkPlace").val()).length>65){
		document.getElementById("checkPlaceIsNull").style.display = "none";
		document.getElementById("checkPlaceIsRight").style.display = "block";
		return false;
	}else{
		document.getElementById("checkPlaceIsNull").style.display = "none";
		document.getElementById("checkPlaceIsRight").style.display = "none";
		return true;
	}
}

function validateNowvalliage(){
	if($("#womanCurVallige").val()==""){
		document.getElementById("nowValliageRight").style.display = "none";
		document.getElementById("nowValliage").style.display = "block";
		return false;
	}else if(($("#womanCurVallige").val()).length>30){
		document.getElementById("nowValliage").style.display = "none";
		document.getElementById("nowValliageRight").style.display = "block";
		return false;
	}else{
		document.getElementById("nowValliage").style.display = "none";
		document.getElementById("nowValliageRight").style.display = "none";
		return true;
	}
}

function validateValliage(){
	if($("#womanVallige").val()==""){
		document.getElementById("ValliageRight").style.display = "none";
		document.getElementById("Valliage").style.display = "block";
		return false;
	}else if(($("#womanVallige").val()).length>30){
		document.getElementById("Valliage").style.display = "none";
		document.getElementById("ValliageRight").style.display = "block";
		return false;
	}else{
		document.getElementById("Valliage").style.display = "none";
		document.getElementById("ValliageRight").style.display = "none";
		return true;
	}
}
function cheack(){
	if($("#womanName").val()==""){
		document.getElementById("isNull").style.display = "block";
		return false;
	}else if($("#womanIdCard").val()==""){
		document.getElementById("idIsNull").style.display = "block";
		return false;
	}else if(document.getElementById("idIsFemale").style.display == "block"){
		return false;
	}else if(document.getElementById("idIsRight").style.display == "block"){
		return false;
	}else if($("#womanVallige").val()==""){
		document.getElementById("Valliage").style.display = "block";
		return false;
	}else if($("#womanCurVallige").val()==""){
		document.getElementById("nowValliage").style.display = "block";
		return false;
	}else if($("#checkDate").val()==""){
		document.getElementById("dateIsNull").style.display = "block";
		return false;
	}else if($("#checkPlace").val()==""){
		document.getElementById("checkPlaceIsNull").style.display = "block";
		return false;
	}else{
		document.getElementById("isNull").style.display = "none";
		document.getElementById("idIsNull").style.display = "none";
		document.getElementById("Valliage").style.display = "none";
		document.getElementById("nowValliage").style.display = "none";
		document.getElementById("dateIsNull").style.display = "none";
		document.getElementById("checkPlaceIsNull").style.display = "none";
		return true;
	}
}

function womanProvinceChange() {	
	var areaId = $("#womanProvince").val();
	
	$("#womanVallige").val("");
	$("#womanTown").empty();
	$("#womanCounty").empty();
	$("#womanCity").empty();
	if(areaId != null){
		findArea(areaId,"#womanCity");
		areaId = $("#womanCity").val();
		
		$("#womanVallige").val("");
		$("#womanTown").empty();
		$("#womanCounty").empty();
		if(areaId != null){
			findArea(areaId,"#womanCounty");
			areaId = $("#womanCounty").val();
			
			$("#womanVallige").val("");
			$("#womanTown").empty();
			if(areaId != null){
				findArea(areaId,"#womanTown");
				areaId = $("#womanTown").val();
			}
		}
	}
};

function womanCityChange(){
	var areaId = $("#womanCity").val();
	$("#womanVallige").val("");
	$("#womanTown").empty();
	$("#womanCounty").empty();
	if(areaId != null){
		findArea(areaId,"#womanCounty");
		
		areaId = $("#womanCounty").val();
		$("#womanVallige").val("");
		$("#womanTown").empty();
		if(areaId != null){
			findArea(areaId,"#womanTown");
			areaId = $("#womanTown").val();
		}
	}
};

function womanCountyChange(){
	var areaId = $("#womanCounty").val();
	$("#womanVallige").val("");
	$("#womanTown").empty();
	if(areaId != null){
		findArea(areaId,"#womanTown");
		areaId = $("#womanTown").val();
	}
};

function womanCurProvinceChange() {
	var areaId = $("#womanCurProvince").val();
	
	$("#womanCurVallige").val("");
	$("#womanCurTown").empty();
	$("#womanCurCounty").empty();
	$("#womanCurCity").empty();
	if(areaId != null){
		findArea(areaId,"#womanCurCity");		
		areaId = $("#womanCurCity").val();
		
		$("#womanCurVallige").val("");
		$("#womanCurTown").empty();
		$("#womanCurCounty").empty();
		if(areaId != null){		
			findArea(areaId,"#womanCurCounty");
			areaId = $("#womanCurCounty").val();
			
			$("#womanCurVallige").val("");
			$("#womanCurTown").empty();
			if(areaId != null){	
				findArea(areaId,"#womanCurTown");
				areaId = $("#womanCurTown").val();
			}
		}
	}
};

function womanCurCityChange(){
	var areaId = $("#womanCurCity").val();
	
	$("#womanCurVallige").val("");
	$("#womanCurTown").empty();
	$("#womanCurCounty").empty();
	if(areaId != null){
		findArea(areaId,"#womanCurCounty");
		areaId = $("#womanCurCounty").val();
		
		$("#womanCurVallige").val("");
		$("#womanCurTown").empty();
		if(areaId != null){
			findArea(areaId,"#womanCurTown");
			areaId = $("#womanCurTown").val();
		}
	}
};

function womanCurCountyChange(){
	var areaId = $("#womanCurCounty").val();
	$("#womanCurVallige").val("");
	$("#womanCurTown").empty();
	if(areaId != null){
		findArea(areaId,"#womanCurTown");
		areaId = $("#womanCurTown").val();
	}
};


function findArea(areaParentId,selectId){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/areaController/searchByAreaParentId.do',
		async : false,
		data : {
			areaParentId : areaParentId
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var cityName="";
				
				$.each(msg.areaList,function(key,val){
					cityName += '<option value="' + val.areaId + '">' + val.areaName + '</option>';
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
