$(document).ready(function(){
	showTaskList();
});
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
				$.each(msg.taskRecordFirst,function(key,val){					
					taskData += "<tr><td> "+
						"<a href=\"task2.html?id=" + val.taskId + "\">" +
						val.taskYear +"年第"+val.taskBatch+"批次三查任务"+ "</a></td><td> "+DateFormat(val.taskLastmodifytime)+ "</a></td><td> " +
						"<a href=\"editsurveytask.html?id=" + val.taskId + "\">"+"修改"+"</a>"+"&nbsp;&nbsp;"
						+"<a href=\"javascript:deleteTask(" + val.taskId + ")\">"+"删除"+"</a></td></tr>";
					});
				$("#taskTable").empty().append(taskData);
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
						$("#taskTip").show();
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

function deleteTask(taskId){
	$('#myDeleteModal').modal('show');
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
					document.getElementById('warn').innerHTML = "删除成功!";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					location.replace(location.href);
					});
				}else{
					document.getElementById('warn').innerHTML = msg.message;
					$('#myWarnModal').modal('show');
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});	
};
function DateFormat(dateStr){ 
	  var date = new Date(parseInt(dateStr)); 
	  return date.getFullYear(date) + '年' 
	      + ((date.getMonth() + 1) < 10 ? "0"+(date.getMonth() + 1):(date.getMonth() + 1)) + '月'
	      +(date.getDate()<10 ? "0"+date.getDate():date.getDate())+'日'; 
} 