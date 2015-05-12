$(document).ready(function(){ 
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/areaController/setArea.do',
		async : false,
		data : { 
	
		},
		dataType : 'json',
		success : function(msg) {
			if (msg.result == true) {
				var area = "<option>全部</option>";
				
				if(msg.level=="乡/镇级"){
					$.each(msg.areaList, function(key, val) {
						area += "<option>" + val + "</option>";
					});
				}else{
					$.each(msg.areaList, function(key, val) {
						area += "<option>" + val.areaName + "</option>";
					});
				}
				$("#areaName").empty().append(area);
			}
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
	$("#print_button").click(function(){
		 $("div #show").printArea();
	});
	$("#download").click(function(){
		location.href="/threeExaminations/uploadDownloadController/download.do?id=1";
	});
	$("#jiansuo").click(function (){
		$("#history").empty();
		document.getElementById("divide").style.display = "none";
		if(cheack()){
			var womanIdCard = $("#womanIdCard").val();
			document.getElementById("divide").style.display = "none";
			document.getElementById("validateId").style.display = "none";
			document.getElementById("noExist").style.display = "none";
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				url : '/threeExaminations/threeCheckServiceController/showNewRecordByIdCard.do',
				async : false,
				data : {
					womanIdCard:womanIdCard,
				},
				dataType : 'json',
				success : function(msg){
					if(msg.result == true){
							
							var detail="<table  class=\"table table-condensed\"><caption><h4><b>" +
							msg.name+"的最新服务记录</b><h4/><br/></caption>" +
							"<tbody><tr class=\"success\"><td>姓名：</td><td>"+msg.record.thcsName+"</td></tr>" +
							"<tr><td style=\"background-color:rgb(242,242,242)\">身份证号：</td><td style=\"background-color:rgb(242,242,242)\">"+womanIdCard+"</td></tr>" +
							"<tr class=\"success\"><td>户籍：</td><td>"+msg.record.thcsHouseholdregister+"</td></tr>"+
							"<tr><td style=\"background-color:rgb(242,242,242)\">现居住地：</td><td style=\"background-color:rgb(242,242,242)\">"+msg.record.thcsNowliveprovince+msg.record.thcsNowlivecity+msg.record.thcsNowlivecounty+msg.record.thcsNowlivetown+msg.record.thcsNowlivevillage+"</td></tr>"+
							"<tr class=\"success\"><td>检查批次：</td><td>"+msg.record.thcsBatch+"</td></tr>"+
							"<tr><td style=\"background-color:rgb(242,242,242)\">检查日期：</td><td style=\"background-color:rgb(242,242,242)\">"+msg.record.thcsChecktime+"</td></tr>"+
							"<tr class=\"success\"><td>检查地点：</td><td>"+msg.record.thcsCheakplace+"</td></tr>"+
							"<tr><td style=\"background-color:rgb(242,242,242)\">检查情况：</td><td style=\"background-color:rgb(242,242,242)\">"+msg.record.thcsHoop+"&nbsp;&nbsp;"+msg.record.thcsPregnancy+"&nbsp;&nbsp;"+msg.record.thcsDisease+"</td></tr>"+
							"<tr class=\"success\"><td>检查建议：</td><td>"+msg.record.thcsChecksuggest+"</td></tr>"+
							"</tbody></table>";
							$("#history").empty().append(detail);
							
							document.getElementById("divide").style.display = "block";
					} else{
						document.getElementById("noExist").style.display = "block";
					}      
				},error: function(msg){
				 alert("网络超时！");
			}
			});
	}else{
		return true;
	}
	});
	$("#SearchHistory").click(function (){
		$("#history").empty();
		document.getElementById("divide").style.display = "none";
		if(cheack()){
			$("#history").empty();
			document.getElementById("validateId").style.display = "none";
			document.getElementById("noExist").style.display = "none";
		var womanIdCard = $("#womanIdCard").val();
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/threeCheckServiceController/showRecordByIdCard.do',
			async : false,
			data : {
				womanIdCard:womanIdCard,
			},
			dataType : 'json',
			success : function(msg){
				if(msg.result == true){
						var showlist = "";
						showlist += "<caption><h4><b>"+msg.name+"的历史服务记录</b><h4><br/></caption><tr class=\"success\"><th>姓名</th><th>身份证号</th><th>户籍</th><th>现居住地</th><th>检查批次</th><th>检查日期</th><th>检查地点</th><th>检查情况</th><th>检查建议</th></tr>";
						$.each(msg.record,function(key,val){ 
							
							showlist += "<tr><td>" +
								val.thcsName + "</td><td> " +
								val.thcsIdnumber + "</td><td> " +
								"<a data-toggle=\"modal\" data-target=\"#myModal2\" onclick=\"javascript:LookHuJi('"+val.thcsHouseholdregister+"')\">查看</a>"+ "</td><td>"+
								"<a data-toggle=\"modal\" data-target=\"#myModal4\" onclick=\"javascript:LookNowLivePlace('"+val.thcsNowliveprovince+val.thcsNowlivecity+val.thcsNowlivecounty+val.thcsNowlivetown+val.thcsNowlivevillage +"')\">查看</a>"+ "</td><td>"+
								val.thcsBatch + "</td><td>" +
								val.thcsChecktime + "</td><td>" +
								"<a data-toggle=\"modal\" data-target=\"#myModal3\" onclick=\"javascript:LookPlace('"+val.thcsCheakplace+"')\">查看</a>"+ "</td><td>"+
								"<a data-toggle=\"modal\" data-target=\"#myModal5\" onclick=\"javascript:LookSituation('"+val.thcsHoop+"&nbsp;&nbsp;"+val.thcsPregnancy+"&nbsp;&nbsp;"+val.thcsDisease+"')\">查看</a>"+ "</td><td>" +
								"<a data-toggle=\"modal\" data-target=\"#myModal1\" onclick=\"javascript:LookSuggest('"+val.thcsChecksuggest+"')\">查看</a>"+ "</td></tr>";
							});
						$("#history").empty().append(showlist);
						document.getElementById("divide").style.display = "block";
				} else{
					document.getElementById("noExist").style.display = "block";
				}      
			},error: function(msg){
			 alert("网络超时！");
		}
		});
		}else{
			return true;
		}
});
	$("#onok").click(function(){
		var year = $("#year").val();
		var patch = $("#patch").val();
		var areaName = $("#areaName").val();
		var peopleClass = $("#peopleClass").val();
		var characterSelect = $("#characterSelect").val();
		document.getElementById('downloadAndPrint').style.display = 'block';
		
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/threeCheckServiceController/searchServiceRecordBySelect.do',
			async : false,
			data : {
				year:year,
				patch:patch,
				areaName:areaName,
				peopleClass:peopleClass,
				characterSelect:characterSelect,	
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					var showlist = "";
					showlist += "<caption><h4><b>" +
					year+patch+areaName+"的服务记录</b><h4/><br/></caption><tr class=\"success\"><th>身份证</th><th>姓名</th><th>环</th><th>孕</th><th>病</th><th>检查时间</th><th>建议</th><th>是否补救</th><th>户籍</th><th>检查地</th></tr>";
						$.each(msg.recordFirst,function(key,val){
							
							var isbujiu;
							if(val.thcsIsremedy == 0){
								isbujiu = "否";
							}else{
								isbujiu = "是";
							}
							
							showlist += "<tr><td>" +
								val.thcsIdnumber + "</td><td> " +
								val.thcsName + "</td><td> " +
								val.thcsHoop +"</td><td> " +
								val.thcsPregnancy + "</td><td>" +
								val.thcsDisease + "</td><td>" +
								val.thcsChecktime + "</td><td>" +
								"<a data-toggle=\"modal\" data-target=\"#myModal1\" onclick=\"javascript:LookSuggest('"+val.thcsChecksuggest+"')\">查看</a>"+ "</td><td>" +
								isbujiu + "</td><td>" +
								"<a data-toggle=\"modal\" data-target=\"#myModal2\" onclick=\"javascript:LookHuJi('"+val.thcsHouseholdregister+"')\">查看</a>"+ "</td><td>" +
								"<a data-toggle=\"modal\" data-target=\"#myModal3\" onclick=\"javascript:LookPlace('"+val.thcsCheakplace+"')\">查看</a>"+ "</td></tr>";
							});
						$("#list").empty().append(showlist);
						if(msg.noRecord==0){
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
															
															$.ajax({
																type : "post",
																contentType : "application/x-www-form-urlencoded;charset=UTF-8",
																url : '/threeExaminations/threeCheckServiceController/findRecordByPage.do',
																async : false,
																data : {
																	page:page
																},
																dataType : 'json',
																success : function(msg) {
																	if (msg.result == true) {
																		var showlistNow = "";
																		showlistNow += "<caption><h4><b>"+year+patch+areaName+"的服务记录</b><h4/><br/></caption><tr class=\"success\"><th>身份证</th><th>姓名</th><th>环</th><th>孕</th><th>病</th><th>检查时间</th><th>建议</th><th>是否补救</th><th>户籍</th><th>检查地</th></tr>";
																		
																		$.each(msg.recordCurrent,function(key,val){
																			
																			var isbujiu;
																			if(val.thcsIsremedy == 0){
																				isbujiu = "否";
																			}else{
																				isbujiu = "是";
																			}
																			showlistNow += "<tr><td>" +
																				val.thcsIdnumber + "</td><td> " +
																				val.thcsName + "</td><td> " +
																				val.thcsHoop +"</td><td> " +
																				val.thcsPregnancy + "</td><td>" +
																				val.thcsDisease + "</td><td>" +
																				val.thcsChecktime + "</td><td>" +
																				"<a data-toggle=\"modal\" data-target=\"#myModal1\" onclick=\"javascript:LookSuggest('"+val.thcsChecksuggest+"')\">查看</a>"+ "</td><td>" +
																				isbujiu + "</td><td>" +
																				"<a data-toggle=\"modal\" data-target=\"#myModal2\" onclick=\"javascript:LookHuJi('"+val.thcsHouseholdregister+"')\">查看</a>"+ "</td><td>" +
																				"<a data-toggle=\"modal\" data-target=\"#myModal3\" onclick=\"javascript:LookPlace('"+val.thcsCheakplace+"')\">查看</a>"+ "</td></tr>";
																			});
																		$("#list").empty().append(showlistNow);
																	}else{
																		alert("查询失败！");
																	} 
																},
																error : function(msg) {
																	alert("网络超时！");
																}
															});
								}
							});
						}else{
							$("#demo5").empty();
						}
				};
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
});
function empty(){
	document.getElementById("validateId").style.display = "none";
	document.getElementById("noExist").style.display = "none";
}
function DateFormat(dateStr){ 
  var date = new Date(parseInt(dateStr)); 
  return date.getFullYear(date) + '年' + (date.getMonth() + 1) + '月'+date.getDate()+'日'; 
} 
function LookHuJi(val)
{	
	document.getElementById('curHuJi').innerHTML = val;
}
function LookPlace(val)
{	
	document.getElementById('curCheckPlace').innerHTML = val;
}
function LookSuggest(val)
{	
	document.getElementById('curSuggest').innerHTML = val;
}
function LookSituation(val)
{
	document.getElementById('curCheckSituation').innerHTML = val;
}
function LookNowLivePlace(val)
{
	document.getElementById('curLivePlace').innerHTML = val;
}
function cheack(){
	if($("#womanIdCard").val()==""){
		document.getElementById("validateId").style.display = "block";
		return false;
	}else{
		return true;
	}
}



