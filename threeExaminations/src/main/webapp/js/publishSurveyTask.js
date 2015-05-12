$(document).ready(function(){
	setTaskYear();
	$("#cancel").click(function(){
		$("#content").val("");
		var my = document.getElementById("iframe1");
		my.src = my.src;
	});
	$("#publish").click(function(){
		var year = $("#year").val(); 
		var batch = $("#batch").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var content = $("#content").val();
		if(checkTask()){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/taskController/publishSurveyTask.do',
			async : false,
			data : {
				year:year,
				batch:batch,
				startTime:startTime,
				endTime:endTime,
				content:content
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result == true){
					document.getElementById('warn').innerHTML = "发布成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					window.location.href='taskmanagement.html';
					});
				}else{
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
		startTime = startTime.replace("-","/");
		var endTime = $("#endTime").val();
		endTime=endTime.replace("-","/");
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

function checkTaskYear(){
	if($("#year").val()==""){
		document.getElementById("checkYear").style.display="block";
	}else{
	}
}

function checkTaskBatch(){
	if($("#batch").val()==""){
		document.getElementById("checkBatch").style.display="block";
	}else{
		document.getElementById("checkBatch").style.display="none";
	}
}

function checkTaskStartTime(){
	if($("#startTime").val()==""){
		document.getElementById("checkStartTime").style.display="block";
	}else{
		document.getElementById("checkStartTime").style.display="none";
	}
}

function checkTaskEndTime(){
	if($("#endTime").val()==""){
		document.getElementById("checkEndTime1").style.display="block";
		document.getElementById("checkEndTime2").style.display="none";
		return false;
	}else{
		var startTime = $("#startTime").val();
		startTime = startTime.replace("-","/");
		var endTime = $("#endTime").val();
		endTime=endTime.replace("-","/");
		if(endTime < startTime){
			document.getElementById("checkEndTime1").style.display="none";
			document.getElementById("checkEndTime2").style.display="block";
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
	}else if(($("#content").val()).length > 5000){
		document.getElementById("checkTaskContent1").style.display="none";
		document.getElementById("checkTaskContent2").style.display="block";
		document.getElementById("content").focus();
	}else{
		document.getElementById("checkTaskContent1").style.display="none";
		document.getElementById("checkTaskContent2").style.display="none";
	}
}
