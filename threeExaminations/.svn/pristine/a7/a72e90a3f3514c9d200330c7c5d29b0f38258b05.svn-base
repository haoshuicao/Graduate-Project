$(document).ready(function(){
	//设置任务的年度只能为今年和明年
	setTaskYear();
	//点击取消发布时，清空任务内容
	$("#cancel").click(function(){
		$("#content").val("");
		//刷新编辑器，清空内容
		var my = document.getElementById("iframe1");
		my.src = my.src;
	});
	//发布公告信息
	$("#publish").click(function(){
		//alert($("#content").val()+"cas");
		var year = $("#year").val(); 
		var batch = $("#batch").val();
		//var title = $("#title").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var content = $("#content").val();
		//alert(($("#content").val()).length + ":"+$("#content").val());
		if(checkTask()){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/taskController/publishSurveyTask.do',
			async : false,
			data : {
				year:year,
				batch:batch,
				//title:title,
				startTime:startTime,
				endTime:endTime,
				content:content
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result == true){
					//alert("发布成功！");
					document.getElementById('warn').innerHTML = "发布成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					window.location.href='taskmanagement.html';
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
		}
	});
});

//获取系统时间，设置任务的年度只能为今年和明年
function setTaskYear(){
	var timestamp = new Date();
	var nowYear = timestamp.getFullYear();
	var nextYear = timestamp.getFullYear() + 1;
	var year="<option>"+nowYear+"</option>"
	          +"<option>"+nextYear+"</option>";
	$("#year").empty().append(year);
}

function checkTask(){
	if($("#year").val()==""){
		document.getElementById("checkYear").style.display="block";
		document.getElementById("year").focus();
	}else if($("#startTime").val()==""){
		document.getElementById("checkStartTime").style.display="block";
		document.getElementById("startTime").focus();
		return false;
	}else if($("#endTime").val()==""){
		document.getElementById("checkEndTime1").style.display="block";
		document.getElementById("checkEndTime2").style.display="none";
		document.getElementById("endTime").focus();
		return false;
	}else if($("#content").val()==""){
	    document.getElementById("checkTaskContent1").style.display="block";
	    document.getElementById("checkTaskContent2").style.display="none";
	    document.getElementById("content").focus();
	    return false;
    }else if(($("#content").val()).length > 5000){
	    document.getElementById("checkTaskContent1").style.display="none";
	    document.getElementById("checkTaskContent2").style.display="block";
	    document.getElementById("content").focus();
	    return false;
    }else if($("#endTime").val()!=""){
		var startTime = $("#startTime").val();
		startTime = startTime.replace("-","/");//替换字符，变成标准格式
		var endTime = $("#endTime").val();
		endTime=endTime.replace("-","/");//替换字符，变成标准格式
		if(endTime < startTime){
			document.getElementById("checkEndTime1").style.display="none";
			document.getElementById("checkEndTime2").style.display="block";
			document.getElementById("endTime").focus();
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

//即时验证任务信息
function checkTaskYear(){
	if($("#year").val()==""){
		document.getElementById("checkYear").style.display="block";
		//document.getElementById("year").focus();
	}else{
	}
}

function checkTaskBatch(){
	if($("#batch").val()==""){
		document.getElementById("checkBatch").style.display="block";
		//document.getElementById("batch").focus();
	}else{
		document.getElementById("checkBatch").style.display="none";
	}
}

function checkTaskStartTime(){
	if($("#startTime").val()==""){
		document.getElementById("checkStartTime").style.display="block";
		//document.getElementById("startTime").focus();
	}else{
		document.getElementById("checkStartTime").style.display="none";
	}
}

function checkTaskEndTime(){
	if($("#endTime").val()==""){
		document.getElementById("checkEndTime1").style.display="block";
		document.getElementById("checkEndTime2").style.display="none";
		//document.getElementById("endTime").focus();
		return false;
	}else{
		var startTime = $("#startTime").val();
		startTime = startTime.replace("-","/");//替换字符，变成标准格式
		var endTime = $("#endTime").val();
		endTime=endTime.replace("-","/");//替换字符，变成标准格式
		if(endTime < startTime){
			document.getElementById("checkEndTime1").style.display="none";
			document.getElementById("checkEndTime2").style.display="block";
			//document.getElementById("endTime").focus();
		}else{
			document.getElementById("checkEndTime1").style.display="none";
			document.getElementById("checkEndTime2").style.display="none";
		}
	}
}

function checkTaskContent(){
	if($("#content").val()==""){
		document.getElementById("checkTaskContent1").style.display="block";
		document.getElementById("checkTaskContent2").style.display="none";
		//document.getElementById("content").focus();
	}else if(($("#content").val()).length > 5000){
		document.getElementById("checkTaskContent1").style.display="none";
		document.getElementById("checkTaskContent2").style.display="block";
		document.getElementById("content").focus();
	}else{
		document.getElementById("checkTaskContent1").style.display="none";
		document.getElementById("checkTaskContent2").style.display="none";
	}
}
