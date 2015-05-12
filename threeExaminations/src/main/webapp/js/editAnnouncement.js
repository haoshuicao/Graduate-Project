$(document).ready(function(){
	attaNum=0;
	attaSystemNameList = new Array();
	attaUploadPath = new Array();
	var Request = new Object();
	Request = GetRequest();
	var annoId ;
	annoId = Request['id'];
	showAnnoContent(annoId);
	
	$("#edit").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		var attaPageNameList=new Array();
		var attaPageName="";
		var attaSystemName="";
		for(var i=0;i < $("#attachment li a").length; i++){
			attaPageNameList[i] = $("#attachment li a:eq("+i+")").text();
			attaPageName+=attaPageNameList[i]+",";
		}
		for(var j=0;j < attaSystemNameList.length;j++){
			if(attaSystemNameList[j] != 0){
				attaSystemName+=attaSystemNameList[j]+",";
			}
		}
		if(checkAnno()){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/announcementController/editAnnouncement.do',
			async : false,
			data : {
				annoId:annoId,
				title:title,
				content:content,
				attaSystemName:attaSystemName,
				attaPageName:attaPageName
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result == true){
					document.getElementById('warn').innerHTML = "修改成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					window.location.href='announcementmanagement.html';
					});
				}
				else{
					document.getElementById('warn').innerHTML = msg.message;
					$('#myWarnModal').modal('show');
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
		}
	});
	
	$("#upload_file").click(function(){
		if(check($("#postfile").val())){
			var uploadPath = $("#postfile").val();
			$("#attachmentTip1").hide();
			$("#attachmentTip2").show();
			$.ajaxFileUpload({
				url:'/threeExaminations/announcementController/uplaodAttachment.do',
				secureuri:false,
				fileElementId:'postfile',
				dataType: 'json',
				type:'POST',
				data:{
					
				},success: function(data, status){ 
					$("#textfield").val("");
					$("#attachmentTip1").show();
					$("#attachmentTip2").hide();
					document.getElementById('warn').innerHTML = "上传成功！";
					$('#myWarnModal').modal('show');
					
					var attachment="";
					attaSystemNameList[attaNum]=data.systemName;
					attaUploadPath[attaNum] = uploadPath;
					
					attachment+="<li id='li"+attaNum+"'>"+"<a href=\"#\">"+data.pageName+"</a>"+"</li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;
					$("#attachment").append(attachment);
					attaNum++;
				
				},error: function (data, status, e){
					$("#attachmentTip1").show();
					$("#attachmentTip2").hide();
					document.getElementById('warn').innerHTML = "上传失败！";
					$('#myWarnModal').modal('show');
		         }
			});
		}
	});
	
	$("#addNew").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		var attaPageNameList=new Array();
		var attaPageName="";
		var attaSystemName="";
		for(var i=0;i < $("#attachment li a").length; i++){
			attaPageNameList[i] = $("#attachment li a:eq("+i+")").text();
			attaPageName+=attaPageNameList[i]+",";
		}
		for(var j=0;j < attaSystemNameList.length;j++){
			if(attaSystemNameList[j] != 0){
				attaSystemName+=attaSystemNameList[j]+",";
			}
		}
		if(checkAnno()){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/announcementController/publishAnnouncement.do',
			async : false,
			data : {
				title:title,
				content:content,
				attaSystemName:attaSystemName,
				attaPageName:attaPageName
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result == true){
					document.getElementById('warn').innerHTML = "发布成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					window.location.href='announcementmanagement.html';
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

function showAnnoContent(annoId){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/announcementController/showAnnoContent.do',
		async : false,
		data : {
			annoId:annoId
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				var title = "";
				var content = "";
				var attachment = "";
				title += msg.announcement.annoTitle;
				content += msg.announcement.annoContent;
				$("#title").val(title);
				$("#content").val(content);
				$.each(msg.attaList, function(key, val){
					attaSystemNameList[attaNum] = val.attaSystemname;
					
					attachment+="<li id='li"+attaNum+"'>"+"<a href=\"#\">"+val.attaPagename+"</a>"+"</li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;
					attaNum++;
				});
				
				if(attachment != ""){
					$("#attachment").empty().append(attachment);
				}else{
					$("#attachment").empty();
				}	
			}else{	
				document.getElementById('warn').innerHTML = msg.message;
				$('#myWarnModal').modal('show');
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

function GetRequest() {
	var url = location.search; 
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

function checkAnno(){
	if($("#title").val()==""){
		document.getElementById("checkTitle1").style.display="block";
		document.getElementById("checkTitle2").style.display="none";
		document.getElementById("title").focus();
		return false;
	}else if(($("#title").val()).length > 45){
		document.getElementById("checkTitle1").style.display="none";
		document.getElementById("checkTitle2").style.display="block";
		document.getElementById("title").focus();
		return false;
	}else if($("#content").val()==""){
		document.getElementById("checkAnnoContent1").style.display="block";
		document.getElementById("checkAnnoContent2").style.display="none";
		document.getElementById("content").focus();
		return false;
	}else if(($("#content").val()).length > 5000){
		document.getElementById("checkAnnoContent1").style.display="none";
		document.getElementById("checkAnnoContent2").style.display="block";
		document.getElementById("content").focus();
		return false;
	}
	else
		return true;
}

function check(file){
	if(file==""){
		document.getElementById('warn').innerHTML = "没有选择任何的文件！";
		$('#myWarnModal').modal('show');
		return false;
	}else{
		var name = file.substring(file.lastIndexOf('\\')+1,file.lastIndexOf('.'));
		var reg=/^[^,]*$/;
		if(!reg.test(name)){
			document.getElementById('warn').innerHTML = "文件名不能包含,";
			$('#myWarnModal').modal('show');
			return false;
		}else{
			var flag = 0;
			for(var i = 0; i < attaNum; i++){
				if(attaUploadPath[i] == file){
					flag = 1;
					break;
				}
			}
			if(flag == 1){
				document.getElementById('warn').innerHTML = "该附件已上传，请重新选择！";
				$('#myWarnModal').modal('show');
				return false;
			}else{
				if(file.substring(file.length-3).toLowerCase()==("xls").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("doc").toLowerCase()){
					return true;
				}else if(file.substring(file.length-4).toLowerCase()==("xlsx").toLowerCase()){
					return true;
				}else if(file.substring(file.length-4).toLowerCase()==("docx").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("jpg").toLowerCase()){
					return true;
				}else if(file.substring(file.length-4).toLowerCase()==("jpeg").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("png").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("bmp").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("gif").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("pdf").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("rar").toLowerCase()){
					return true;
				}else if(file.substring(file.length-3).toLowerCase()==("zip").toLowerCase()){
					return true;
				}else{
					document.getElementById('warn').innerHTML = "只支持xls、xlsx、doc、docx、jpg、jpeg、png、bmp、pdf、rar、zip格式的文件上传！";
					$('#myWarnModal').modal('show');
					return false;
				}
			}
		}
	}
}
function download(attaId){
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}
function download2(downloadPath){
	location.href="/threeExaminations/announcementController/downloadAttachment2.do?downloadPath="+downloadPath;
}
function deleteAttachment(attaId){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/announcementController/deleteAttachment.do',
		async : false,
		data : {
			attaId:attaId
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				alert("删除成功！");
				showAnnoContent(msg.annoucemnet.annoId);
			}else{	
				alert(msg.message);
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

function deleteAtta(deletenum){
	var s=document.getElementById("attachment");
	var aId=document.getElementById("a"+deletenum);
	var liId=document.getElementById("li"+deletenum);
    s.removeChild(aId);
    s.removeChild(liId);
    attaSystemNameList[deletenum] = 0;
}

function checkAnnoTitle(){
	if($("#title").val()==""){
		document.getElementById("checkTitle1").style.display="block";
		document.getElementById("checkTitle2").style.display="none";
	}else if(($("#title").val()).length > 45){
		document.getElementById("checkTitle1").style.display="none";
		document.getElementById("checkTitle2").style.display="block";
	}else{
		document.getElementById("checkTitle1").style.display="none";
		document.getElementById("checkTitle2").style.display="none";
	}
}

function checkAnnoContent(){
	if($("#content").val()==""){
		document.getElementById("checkAnnoContent1").style.display="block";
		document.getElementById("checkAnnoContent2").style.display="none";
	}else if(($("#content").val()).length > 5000){
		document.getElementById("checkAnnoContent1").style.display="none";
		document.getElementById("checkAnnoContent2").style.display="block";
		document.getElementById("content").focus();
	}else{
		document.getElementById("checkAnnoContent1").style.display="none";
		document.getElementById("checkAnnoContent2").style.display="none";
	}
}