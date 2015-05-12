$(document).ready(function(){
	//根据当前登录用户的所在地区，查询所在地区的分管地区设置到页面的地区选择中
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
				//户籍
				$("#vallige").empty().append(area);
			}
		
		},
		error : function(msg) {
			alert("网络超时！");
		}
	});
	//打印
	$("#print_button").click(function(){
		 $("div #show").printArea();
		 });
	//下载
	$("#download").click(function(){
		location.href="/threeExaminations/uploadDownloadController/download1.do?id=1";
	});
	//根据选择条件进行查询
	$("#onok").click(function(){
		var year = $("#year").val();
		var patch = $("#patch").val();
		var vallige = $("#vallige").val();
		var peopleClass = $("#peopleClass").val();
		//var peopleClass = "常住";
		document.getElementById('downloadAndPrint').style.display = 'block';
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/threeCheckCountController/LookStandardReport.do',
			async : false,
			data : {
				year:year,
				patch:patch,
				vallige:vallige,
				peopleClass:peopleClass
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					var standardReport = "";
					if(peopleClass=="全部"){
						standardReport += "<caption><h4><b>" +
					year+patch+vallige+"的标准统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>实查总人次</th><th>应查总人次</th><th>普查率</th></tr>";
					}else{
						standardReport += "<caption><h4><b>" +
					year+patch+vallige+"的标准统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"实查总人次</th><th>"+peopleClass+"应查总人次</th><th>"+peopleClass+"普查率</th></tr>";
					}
					$.each(msg.recordFirst,function(key,val){
						var SurveyRate;
						SurveyRate = ((val.thccHavechecknumber/val.thccShouldchecknumber)*100).toFixed(2);
						standardReport += "<tr><td>" +
							val.thccVillage + "</td><td> " +
							val.thccHavechecknumber + "</td><td> " +
							val.thccShouldchecknumber +	"</td><td> " +
							SurveyRate+"%" + "</td></tr>";
						});
					$("#list").empty().append(standardReport);
					if(msg.noRecord==0){
						//分页处理
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
															url : '/threeExaminations/threeCheckCountController/findRecordByPage.do',
															async : false,
															data : {
																page:page
															},
															dataType : 'json',
															success : function(msg) {
																if (msg.result == true) {
																	var standardReport = "";
																	if(peopleClass=="全部"){
																		standardReport += "<caption><h4><b>" +
																	year+patch+vallige+"的标准统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>实查总人次</th><th>应查总人次</th><th>普查率</th></tr>";
																	}else{
																		standardReport += "<caption><h4><b>" +
																	year+patch+vallige+"的标准统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"实查总人次</th><th>"+peopleClass+"应查总人次</th><th>"+peopleClass+"普查率</th></tr>";
																	}
																	
																	$.each(msg.recordCurrent,function(key,val){
																		var SurveyRate;
																		SurveyRate = ((val.thccHavechecknumber/val.thccShouldchecknumber)*100).toFixed(2);
																		standardReport += "<tr><td>" +
																			val.thccVillage + "</td><td> " +
																			val.thccHavechecknumber + "</td><td> " +
																			val.thccShouldchecknumber +	"</td><td> " +
																			SurveyRate+"%" + "</td></tr>";
																		});
																	$("#list").empty().append(standardReport);
																	
																}else{
																	alert("查询失败！");
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