$(document).ready(function(){
	/**
	 * 根据当前登录用户，获取当前用户所在地区下的分管地区，设置到选择地区中
	 */
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
	/**
	 * 打印
	 */
	$("#print_button1").click(function(){
		 $("div #show").printArea();
	});
	/**
	 * 下载
	 */
	$("#download").click(function(){
		location.href="/threeExaminations/uploadDownloadController/download2.do?id=1";
	});
	
	/**
	 * 查询
	 */
	$("#onok").click(function(){
		$("#list").empty();
		$('#chart1').empty();
		$('#info1').empty();
		$('#title').empty();
		document.getElementById('baobiao').style.display = 'none';
		document.getElementById('printReport').style.display = 'none';
		document.getElementById('downloadAndPrint').style.display = 'block';
		var year = $("#year").val();
		var patch = $("#patch").val();
		var vallige = $("#vallige").val();
		var peopleClass = $("#peopleClass").val();
		//var peopleClass = "常住人口普查率，计外补救率，妇科病率";
		if(vallige=="全部"){
			document.getElementById('CountRate').style.display = 'block';
		}else{
			document.getElementById('CountRate').style.display = 'none';
		}
		/**
		 * 查看特征统计报表
		 */
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
					
					var characterReport = "";
					
					if(peopleClass=="全部人口普查率，计外补救率，妇科病率"){
						peopleClass = "全部";
						characterReport += "<caption><h4><b>" +
					year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>应查人数</th><th>实查人数</th><th>患妇科病数</th><th>计外孕人数</th><th>已补救人数</th><th>普查率</th><th>计外孕补救率</th><th>妇科病率</th></tr>";
					}
					else if(peopleClass=="常住人口普查率，计外补救率，妇科病率"){
						peopleClass = "常住";
						characterReport += "<caption><h4><b>" +
					year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
					}else if(peopleClass=="流入人口普查率，计外补救率，妇科病率"){
						peopleClass = "流入";
						characterReport += "<caption><h4><b>" +
					year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
					}else{
						peopleClass = "流出";
						characterReport += "<caption><h4><b>" +
					year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
					}
					
					$.each(msg.recordFirst,function(key,val){
						var SurveyRate;
						var OutPlanBuJiuRate;
						var FuKeBingRate;
						SurveyRate = ((val.thccHavechecknumber/val.thccShouldchecknumber)*100).toFixed(2);
						if(val.thccOutplanprenumber==0){
							OutPlanBuJiuRate="——";
						}else{
							OutPlanBuJiuRate = ((val.thccRemedynumber/val.thccOutplanprenumber)*100).toFixed(2)+"%";
						}
						FuKeBingRate = ((val.thccGymornumber/val.thccHavechecknumber)*100).toFixed(2);
						
						characterReport += "<tr><td>" +
							val.thccVillage + "</td><td> " +
							val.thccShouldchecknumber + "</td><td> " +
							val.thccHavechecknumber +	"</td><td> " +
							val.thccGymornumber +	"</td><td> " +
							val.thccOutplanprenumber +	"</td><td> " +
							val.thccRemedynumber +	"</td><td> " +
							SurveyRate+"%" +	"</td><td> " +
							OutPlanBuJiuRate +	"</td><td> " +
							FuKeBingRate+"%" + "</td></tr>";
						});
					$("#list").empty().append(characterReport);
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
																	var characterReport = "";
																	
																	if(peopleClass=="全部"){
																		
																		characterReport += "<caption><h4><b>" +
																	year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>应查人数</th><th>实查人数</th><th>患妇科病数</th><th>计外孕人数</th><th>已补救人数</th><th>普查率</th><th>计外孕补救率</th><th>妇科病率</th></tr>";
																	}
																	else if(peopleClass=="常住"){
																		
																		characterReport += "<caption><h4><b>" +
																	year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
																	}else if(peopleClass=="流入"){
																		
																		characterReport += "<caption><h4><b>" +
																	year+patch+vallige+"的特征统计报表</b><h4/><br/></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
																	}else{
													
																		characterReport += "<caption><h4><b>" +
																	year+patch+vallige+"的特征统计报表</b><h4/></br></caption><tr class=\"success\"><th>单位</th><th>"+peopleClass+"应查人数</th><th>"+peopleClass+"实查人数</th><th>"+peopleClass+"患妇科病数</th><th>"+peopleClass+"计外孕人数</th><th>"+peopleClass+"已补救人数</th><th>"+peopleClass+"普查率</th><th>"+peopleClass+"计外孕补救率</th><th>"+peopleClass+"妇科病率</th></tr>";
																	}
																	
																	$.each(msg.recordCurrent,function(key,val){
																		var SurveyRate;
																		var OutPlanBuJiuRate;
																		var FuKeBingRate;
																		SurveyRate = ((val.thccHavechecknumber/val.thccShouldchecknumber)*100).toFixed(2);
																		if(val.thccOutplanprenumber==0){
																			OutPlanBuJiuRate=100;
																		}else{
																			OutPlanBuJiuRate = ((val.thccRemedynumber/val.thccOutplanprenumber)*100).toFixed(2);
																		}
																		FuKeBingRate = ((val.thccGymornumber/val.thccHavechecknumber)*100).toFixed(2);
																		
																		characterReport += "<tr><td>" +
																			val.thccVillage + "</td><td> " +
																			val.thccShouldchecknumber + "</td><td> " +
																			val.thccHavechecknumber +	"</td><td> " +
																			val.thccGymornumber +	"</td><td> " +
																			val.thccOutplanprenumber +	"</td><td> " +
																			val.thccRemedynumber +	"</td><td> " +
																			SurveyRate+"%" +	"</td><td> " +
																			OutPlanBuJiuRate+"%" +	"</td><td> " +
																			FuKeBingRate+"%" + "</td></tr>";
																		});
																	$("#list").empty().append(characterReport);

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


