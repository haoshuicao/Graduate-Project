$(document).ready(function(){
	attaNum=0;
	attaSystemNameList = new Array();
	attaUploadPath = new Array();
	var Request = new Object();
	Request = GetRequest();
	var annoId ;
	annoId = Request['id'];
	//修改时，显示某一选中的公告原信息
	showAnnoContent(annoId);
	
	//点击保存，修改公告
	$("#edit").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		var attaPageNameList=new Array();
		var attaPageName="";
		var attaSystemName="";
		for(var i=0;i < $("#attachment li a").length; i++){
			attaPageNameList[i] = $("#attachment li a:eq("+i+")").text();
			//alert(attaPathList[i]);
			attaPageName+=attaPageNameList[i]+",";
		}
		for(var j=0;j < attaSystemNameList.length;j++){
			if(attaSystemNameList[j] != 0){
				attaSystemName+=attaSystemNameList[j]+",";
			}
		}
		if(checkAnno()){
			//alert(attaSystemName);return;
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
					//alert("修改成功！");
					document.getElementById('warn').innerHTML = "修改成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					window.location.href='announcementmanagement.html';
					});
				}
				else{
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
	
	//上传公告附件
	$("#upload_file").click(function(){
		if(check($("#postfile").val())){
			//alert($("#postfile").val());
			var uploadPath = $("#postfile").val();
			$("#attachmentTip1").hide();
			$("#attachmentTip2").show();
			$.ajaxFileUpload({
				url:'/threeExaminations/announcementController/uplaodAttachment.do',//用于文件上传的服务器端请求地址
				secureuri:false,//一般设置为false
				fileElementId:'postfile',//文件上传空间的id和name属性（必须都是的）  <input type="file" id="postfile" name="postfile" />
				dataType: 'json',//返回值类型 一般设置为json
				type:'POST',
				data:{
					
				},success: function(data, status){  //服务器成功响应处理函数
					//alert("上传成功！");
					$("#textfield").val("");
					$("#attachmentTip1").show();
					$("#attachmentTip2").hide();
					document.getElementById('warn').innerHTML = "上传成功！";
					$('#myWarnModal').modal('show');
					//$("#sure").click(function(){
					
					var attachment="";
					attaSystemNameList[attaNum]=data.systemName;
					attaUploadPath[attaNum] = uploadPath;
					
					/*var downloadPath=data.systemName+","+data.pageName;				
					attachment+="<li id='li"+attaNum+"'>"+"<a href=\"javaScript:download2('"+downloadPath+"')\">"+data.pageName+"</a>"+"</li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;*/
					
					attachment+="<li id='li"+attaNum+"'>"+"<a href=\"#\">"+data.pageName+"</a>"+"</li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;
					$("#attachment").append(attachment);
					attaNum++;
					//alert(attaNum);
					//});
				
				},error: function (data, status, e){//服务器响应失败处理函数
					//alert("上传失败！");
					$("#attachmentTip1").show();
					$("#attachmentTip2").hide();
					document.getElementById('warn').innerHTML = "上传失败！";
					$('#myWarnModal').modal('show');
		         }
			});
		}
	});
	
	//发布一条新的公告
	$("#addNew").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		var attaPageNameList=new Array();
		var attaPageName="";
		var attaSystemName="";
		for(var i=0;i < $("#attachment li a").length; i++){
			attaPageNameList[i] = $("#attachment li a:eq("+i+")").text();
			//alert(attaPathList[i]);
			attaPageName+=attaPageNameList[i]+",";
		}
		for(var j=0;j < attaSystemNameList.length;j++){
			if(attaSystemNameList[j] != 0){
				attaSystemName+=attaSystemNameList[j]+",";
			}
		}
		if(checkAnno()){
			//alert(attaSystemName);return;
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

//显示某一公告的内容
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
					/*attachment+="<li id='li"+attaNum+"'>"+"<a href=\"javaScript:download("+val.attaId+")\">"+val.attaPagename+"</a>"+"</li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ; */
					
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
				//alert(msg.message);
				document.getElementById('warn').innerHTML = msg.message;
				$('#myWarnModal').modal('show');
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

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
		//alert("没有选择任何的文件！");
		document.getElementById('warn').innerHTML = "没有选择任何的文件！";
		$('#myWarnModal').modal('show');
		return false;
	}else{
		var name = file.substring(file.lastIndexOf('\\')+1,file.lastIndexOf('.'));
		//alert(name);
		var reg=/^[^,]*$/;
		if(!reg.test(name)){
			//alert("文件名不能包含,");
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
					//alert("只支持xls、doc、jgp、pdf格式的文件上传！");
					document.getElementById('warn').innerHTML = "只支持xls、xlsx、doc、docx、jpg、jpeg、png、bmp、pdf、rar、zip格式的文件上传！";
					$('#myWarnModal').modal('show');
					return false;
				}
			}
		}
	}
}
//根据附件id下载附件
function download(attaId){
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}
//根据系统保存的附件名下载附件
function download2(downloadPath){
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment2.do?downloadPath="+downloadPath;
}
//直接从数据库中删除某一公告
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

//删除页面上公告列表中的某一公告
function deleteAtta(deletenum){
	//按标签的id删除某标签
	//alert("删除"+deletenum);
	var s=document.getElementById("attachment");
	var aId=document.getElementById("a"+deletenum);
	var liId=document.getElementById("li"+deletenum);
    s.removeChild(aId);
    s.removeChild(liId);
    attaSystemNameList[deletenum] = 0;
    /*var linum=s.getElementsByTagName("li");
    var anum=s.getElementsByTagName("a");
    alert("li标签个数"+linum.length);
	alert("a标签个数"+anum.length);*/		
}

//即时验证公告信息
function checkAnnoTitle(){
	if($("#title").val()==""){
		document.getElementById("checkTitle1").style.display="block";
		document.getElementById("checkTitle2").style.display="none";
		//document.getElementById("title").focus();
	}else if(($("#title").val()).length > 45){
		document.getElementById("checkTitle1").style.display="none";
		document.getElementById("checkTitle2").style.display="block";
		//document.getElementById("title").focus();
	}else{
		document.getElementById("checkTitle1").style.display="none";
		document.getElementById("checkTitle2").style.display="none";
	}
}

function checkAnnoContent(){//alert(($("#content").val()).length);
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