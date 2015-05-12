$(document).ready(function(){
	//打印任务内容
	$("#print_button").click(function(){
		//document.getElementById("line").style.display="none";
		$("div #printTask").printArea();
	});
	//显示当前用户能查看的任务列表
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/taskController/lookTaskList.do',
		async : false,
		data : {
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				$("#taskTip").hide();
				var task = "";
				task+="<tr class='success'><th width=580>"+"任务标题"+"</th><th>"+"发布时间"+"</th></tr>";
				var title = "";
				var year = "";
				var batch ="";
				var startTime = "";
				var endTime = "";
				var content = "";
				var publishUnit = "";
				var publishUser = "";
				var lastModifyTime = "";
				$.each(msg.taskRecordFirst,function(key,val){
					//task +="<li>"+"<a href=\"javascript:showTaskContent(" + val.taskId + ")\">"+val.taskYear+"年第"+val.taskBatch+"批次三查任务"+".........."+DateFormat(val.taskLastmodifytime)+"</a>"+"</li>";
					task +="<tr><td>"+"<a href=\"#\" onclick=\"javascript:showTaskContent(" + val.taskId + ")\">"+val.taskYear+"年第"+val.taskBatch+"批次三查任务"+"</a></td><td>"+DateFormat(val.taskLastmodifytime)+"</a>"+"</li>";
					});
				//$("#taskList").empty().append(task);
				$("#taskTable").empty().append(task);
				title += msg.newTask.taskYear + "年第" +msg.newTask.taskBatch+"批次三查任务";
				year += msg.newTask.taskYear;
				batch += msg.newTask.taskBatch;
				startTime += msg.startTime;
				endTime += msg.endTime;
				content += msg.newTask.taskContent;
				publishUnit += msg.newUnit;
				publishUser += msg.publishUser;
				lastModifyTime += msg.date;				
				$("#title").empty().append(title);
				$("#year").append(year);
				$("#batch").append(batch);
				$("#time").append(startTime+" 至 "+endTime);
				$("#content").empty().append(content);
				$("#publishUnit").append(publishUnit);
				$("#publishUser").append("发布者："+publishUser);
				$("#publishTime").empty().append(lastModifyTime);
				
				//翻页显示
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
													url : '/threeExaminations/taskController/findRecordByPage.do',
													async : false,
													data : {
														page:page
													},
													dataType : 'json',
													success : function(msg) {
														if(msg.result == true){
															var task = "";
															task+="<tr class='success'><th width=580>"+"任务标题"+"</th><th>"+"发布时间"+"</th></tr>";
															
															$.each(msg.taskRecordCurrent,function(key,val){
																//task +="<li>"+"<a href=\"javascript:showTaskContent(" + val.taskId + ")\">"+val.taskYear+"年第"+val.taskBatch+"批次三查任务"+".........."+DateFormat(val.taskLastmodifytime)+"</a>"+"</li>";
																task +="<tr><td>"+"<a href=\"#\" onclick=\"javascript:showTaskContent(" + val.taskId + ")\">"+val.taskYear+"年第"+val.taskBatch+"批次三查任务"+"</a></td><td>"+DateFormat(val.taskLastmodifytime)+"</a>"+"</li>";
																});
															//$("#taskList").empty().append(task);
															$("#taskTable").empty().append(task);
														}else{
															document.getElementById('warn').innerHTML = "获取失败!";
															$('#myWarnModal').modal('show');
														}
													},error: function(msg){
												        alert("网络超时！");
													}
												});
					}
				});
			}else{
				$("#taskAll").hide();
				$("#taskTip").show();
				//alert(msg.message);
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
});

function showTaskContent(taskid){
	//显示某一选中的任务信息
	var taskId = taskid;
	//alert(taskId);
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/taskController/showTaskContent.do',
		async : false,
		data : {
			taskId:taskId
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				var title = "";
				var year = "";
				var batch ="";
				var startTime = "";
				var endTime = "";
				var content = "";
				var publishUnit = "";
				var publishUser = "";
				var lastModifyTime = "";		
				year +=msg.task.taskYear;
				batch += msg.task.taskBatch;
				startTime += msg.startTime;
				endTime += msg.endTime;
				content += msg.task.taskContent;
				publishUnit += msg.publishUnit;
				publishUser += msg.publishUser;
				lastModifyTime += msg.date;
				title += year + "年第" +batch + "批次三查任务";
				$("#title").empty().append(title);
				$("#year").empty().append("年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度："+year);
				$("#batch").empty().append("检查批次："+batch);
				$("#time").empty().append("检查时间："+startTime+" 至 "+endTime);
				$("#content").empty().append(content);
				$("#publishUnit").empty().append(publishUnit);
				$("#publishUser").empty().append("发布者："+publishUser);
				$("#publishTime").empty().append(lastModifyTime);
			}else{
				alert(msg.message);
			};
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}
//将时间戳转换为时间，月和日以两位数显示
function DateFormat(dateStr){ 
	  var date = new Date(parseInt(dateStr)); 
	  return date.getFullYear(date) + '年' 
	      + ((date.getMonth() + 1) < 10 ? "0"+(date.getMonth() + 1):(date.getMonth() + 1)) + '月'
	      +(date.getDate()<10 ? "0"+date.getDate():date.getDate())+'日'; 
} 