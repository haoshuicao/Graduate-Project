$(document).ready(function(){
	//显示自己发布的任务列表
	showTaskList();
});
//显示自己发布的公告列表
function showTaskList(){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/taskController/showTaskList.do',
		async : false,
		data : {
		},
		dataType : 'json',
		success : function(msg) {
			var taskData = "";				
            taskData += "<caption><b>任务列表</b></caption><tr class=\"success\"><th width=500>任务标题</th><th>发布时间</th><th>操作</th></tr>";
			if(msg.result ==true){
				/*var task = "";
				$.each(msg.taskList,function(key,val){
					task +="<li>"+"<a href=\"task2.html?id=" + val.taskId + "\">"+val.taskYear+"年第"+val.taskBatch+"批次三查任务"+".........."+DateFormat(val.taskLastmodifytime)+"</a>"+"</li>"; 
					});
				$("#taskList").empty().append(task);*/
				               
				$.each(msg.taskRecordFirst,function(key,val){					
					taskData += "<tr><td> "+
						"<a href=\"task2.html?id=" + val.taskId + "\">" +
						val.taskYear +"年第"+val.taskBatch+"批次三查任务"+ "</a></td><td> "+DateFormat(val.taskLastmodifytime)+ "</a></td><td> " +
						"<a href=\"editsurveytask.html?id=" + val.taskId + "\">"+"修改"+"</a>"+"&nbsp;&nbsp;"
						+"<a href=\"javascript:deleteTask(" + val.taskId + ")\">"+"删除"+"</a></td></tr>";
					});
				$("#taskTable").empty().append(taskData);
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
														var taskData = "";				
											            taskData += "<caption><b>任务列表</b></caption><tr class=\"success\"><th width=500>任务标题</th><th>发布时间</th><th>操作</th></tr>";
														if (msg.result == true) {						               
															$.each(msg.taskRecordCurrent,function(key,val){					
																taskData += "<tr><td> " +
																	"<a href=\"task2.html?id=" + val.taskId + "\">" +
																	val.taskYear +"年第"+val.taskBatch+"批次三查任务"+ "</a></td><td> "+DateFormat(val.taskLastmodifytime)+ "</a></td><td> " +
																	"<a href=\"editsurveytask.html?id=" + val.taskId + "\">"+"修改"+"</a>"+"&nbsp;&nbsp;"
																	+"<a href=\"javascript:deleteTask(" + val.taskId + ")\">"+"删除"+"</a></td></tr>";
																});
															$("#taskTable").empty().append(taskData);
																
														}else{
															//alert("获取失败！");
															document.getElementById('warn').innerHTML = "获取失败!";
															$('#myWarnModal').modal('show');
														} 
													},
													error : function(msg) {
														alert("网络超时！");
													}
												});
					}
				});
					}else{
				        //$("#taskTable").empty().append(taskData);
				        //alert(msg.message);
						//$("#taskTable").hide();
						$("#taskTip").show();
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

//删除任务
function deleteTask(taskId){
	//$("#isDelete").click();
	$('#myDeleteModal').modal('show');//是否删除模态框
	$("#sureDelete").click(function(){
		$("#myDeleteModal").hide();
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/taskController/deleteTask.do',
			async : false,
			data : {
				taskId:taskId
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					//alert("删除成功！");
					//showTaskList();
					document.getElementById('warn').innerHTML = "删除成功!";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					location.replace(location.href);
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
	});	
};
//将时间戳转换为时间，月和日以两位数显示
function DateFormat(dateStr){ 
	  var date = new Date(parseInt(dateStr)); 
	  return date.getFullYear(date) + '年' 
	      + ((date.getMonth() + 1) < 10 ? "0"+(date.getMonth() + 1):(date.getMonth() + 1)) + '月'
	      +(date.getDate()<10 ? "0"+date.getDate():date.getDate())+'日'; 
} 