$(document).ready(function(){
	$("#print_button2").click(function(){
		var chart = $('#chart1');
        var str = chart.jqplotToImageElem({backgroundColor: chart.css('background-color')});
        $('#image').append(str);
        document.getElementById('chart1').style.display = 'none';
		$("div #baobiao").printArea();
	});
	$("#surveyRate").click(function(){
		 $('#image').empty();
		 document.getElementById('baobiao').style.display = 'block';
		document.getElementById('chart1').style.display = 'block';
		 document.getElementById('printReport').style.display = 'block';
		var year = $("#year").val();
		var patch = $("#patch").val();
		var vallige = $("#vallige").val();
		var peopleClass = $("#peopleClass").val();
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
					var ary1 = new Array(); 
					var ary2 = new Array(); 
					
					$.each(msg.record,function(key,val){
					
					var SurveyRate;
					SurveyRate =  ((val.thccHavechecknumber/val.thccShouldchecknumber)*100);
					ary1.push(val.thccVillage);
					ary2.push(SurveyRate); 
					});
					var size = ary1.length;
					if(size<=10){
						document.getElementById('baobiao').style.width = '700px';
					}else{
						var width = size*70+"px";
						document.getElementById('baobiao').style.width = width;
					}
					
					
					if(vallige=="全部"){
						var title="<h4><b>" +msg.nowArea+year+patch+"的普查率统计报表</b></h4>";
						$('#title').empty().append(title);
						$('#chart1').empty();
						$.jqplot.config.enablePlugins = true;
						plot1 = $.jqplot('chart1', [ary2], {
					         animate: !$.jqplot.use_excanvas,
					        
					         seriesDefaults:{
					             renderer:$.jqplot.BarRenderer,
					             pointLabels: { show: true }
					         },
					         
					         axes: {
					             xaxis: {
					                 renderer: $.jqplot.CategoryAxisRenderer,
					                 ticks: ary1  
					             },
					         	yaxis: {
					         		min: 0,           
					         		max:100,
				                    tickInterval: 20,        
				             }
					         },
					         highlighter: { show: false }
					     });
						$('#info1').empty();
					     $('#chart1').bind('jqplotDataClick', 
					         function (ev, seriesIndex, pointIndex, data) {
					    	 	data[0] = ary1[pointIndex];
					    	 	var rate = data[1].toFixed(2);
					             $('#info1').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+'地区:'+data[0]+"&nbsp;&nbsp;&nbsp;&nbsp;"+"普查率:"+rate+"%");
					         }
					     );
					}
					 
				}else{
					alert("错误！");
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
	$("#outPlanBuJiuRate").click(function(){
		$('#image').empty();
		document.getElementById('baobiao').style.display = 'block';
		document.getElementById('chart1').style.display = 'block';
		document.getElementById('printReport').style.display = 'block';
		var year = $("#year").val();
		var patch = $("#patch").val();
		var vallige = $("#vallige").val();
		var peopleClass = $("#peopleClass").val();
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
					var ary1 = new Array(); 
					var ary2 = new Array(); 
					$.each(msg.record,function(key,val){
					var OutPlanBuJiuRate;
					if(val.thccOutplanprenumber!=0){
						OutPlanBuJiuRate = ((val.thccRemedynumber/val.thccOutplanprenumber)*100);
						ary1.push(val.thccVillage);
						ary2.push(OutPlanBuJiuRate);
					}
					
					});
					var size = ary1.length;
					if(size<=10){
						document.getElementById('baobiao').style.width = '700px';
					}else{
						var width = size*70+"px";
						document.getElementById('baobiao').style.width = width;
					}
					if(vallige=="全部"){
						var title="<h4><b>" +msg.nowArea+year+patch+"的计外孕补救率统计报表</b></h4>";
						$('#title').empty().append(title);
						$('#chart1').empty();
						 $.jqplot.config.enablePlugins = true;
							plot1 = $.jqplot('chart1', [ary2], {
						         animate: !$.jqplot.use_excanvas,
						         seriesDefaults:{
						             renderer:$.jqplot.BarRenderer,
						             pointLabels: { show: true }
						         },
						         axes: {
						             xaxis: {
						                 renderer: $.jqplot.CategoryAxisRenderer,
						                 ticks: ary1
						             },
						             yaxis: {
							         		min: 0,           
							         		max:100,
						                    tickInterval: 20        
						             }
						         },
						         highlighter: { show: false }
						     });
							$('#info1').empty();
						     $('#chart1').bind('jqplotDataClick', 
						         function (ev, seriesIndex, pointIndex, data) {
						    	 data[0] = ary1[pointIndex];
						    	 	var rate = data[1].toFixed(2);
						             $('#info1').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+'地区:'+data[0]+"&nbsp;&nbsp;&nbsp;&nbsp;"+"计外孕补救率:"+rate+"%");
						         }
						     );
						
					}
					
				}else{
					alert("错误！");
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
	$("#fuKeBingRate").click(function(){
		$('#image').empty();
		document.getElementById('baobiao').style.display = 'block';
		document.getElementById('chart1').style.display = 'block';
		document.getElementById('printReport').style.display = 'block';
		var year = $("#year").val();
		var patch = $("#patch").val();
		var vallige = $("#vallige").val();
		var peopleClass = $("#peopleClass").val();
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
					var ary1 = new Array(); 
					var ary2 = new Array(); 
					$.each(msg.record,function(key,val){
					var FuKeBingRate;
					FuKeBingRate = ((val.thccGymornumber/val.thccHavechecknumber)*100);
					ary1.push(val.thccVillage);
					ary2.push(FuKeBingRate); 
					});
					var size = ary1.length;
					if(size<=10){
						document.getElementById('baobiao').style.width = '700px';
					}else{
						var width = size*70+"px";
						document.getElementById('baobiao').style.width = width;
					}
					if(vallige=="全部"){
						var title="<h4><b>" +msg.nowArea+year+patch+"的妇科病率统计报表</b></h4>";
						$('#title').empty().append(title);
						$('#chart1').empty();
						
						 $.jqplot.config.enablePlugins = true;
							plot1 = $.jqplot('chart1', [ary2], {
						         animate: !$.jqplot.use_excanvas,
						         seriesDefaults:{
						             renderer:$.jqplot.BarRenderer,
						             pointLabels: { show: true }
						         },
						         axes: {
						             xaxis: {
						                 renderer: $.jqplot.CategoryAxisRenderer,
						                 ticks: ary1
						             },
						             yaxis: {
							         		min: 0,           
							         		max:100,
						                    tickInterval: 20        
						             }
						         },
						         highlighter: { show: false }
						     });
							$('#info1').empty();
						     $('#chart1').bind('jqplotDataClick', 
						         function (ev, seriesIndex, pointIndex, data) {
						    	 data[0] = ary1[pointIndex];
						    	 	var rate = data[1].toFixed(2);
						             $('#info1').html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+'地区:'+data[0]+"&nbsp;&nbsp;&nbsp;&nbsp;"+"妇科病率:"+rate+"%");
						         }
						     );
					}
					
				}else{
					alert("错误！");
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
});
