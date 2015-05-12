$(document).ready(function(){
	//显示某一选中的公告信息
	var Request = new Object();
	Request = GetRequest();
	var taskId;
	taskId = Request['id'];
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
				$("#year").append(year);
				$("#batch").append(batch);
				$("#time").append(startTime+" 至 "+endTime);
				$("#content").empty().append(content);
				$("#publishUnit").append(publishUnit);
				$("#publishUser").append("发布者："+publishUser);
				$("#publishTime").empty().append(lastModifyTime);
			}else{
				alert(msg.message);
			};
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	
	//打印任务内容
	$("#print_button").click(function(){
		//document.getElementById("line").style.display="none";
		$("div #printTask").printArea();
	});
});

function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) { 
		var str = url.substr(1);
		strs = str.split("&");
		for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
			}
		}
	return theRequest;
}