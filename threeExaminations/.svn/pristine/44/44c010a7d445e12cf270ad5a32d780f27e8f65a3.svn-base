$(document).ready(function(){
	//显示某一选中的公告信息
	var Request = new Object();
	Request = GetRequest();
	var annoId ;
	annoId = Request['id'];
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
				var publishUnit = "";
				var publishUser = "";
				var lastModifyTime = "";
				var attachment = "";
				title += msg.announcement.annoTitle;
				content += msg.announcement.annoContent;
				publishUnit += msg.publishUnit;
				publishUser += msg.publishUser;
				lastModifyTime += msg.date;
				$.each(msg.attaList, function(key, val){
					attachment+="<li>"+"<a href=\"javascript:download("+val.attaId+")\">"+val.attaPagename+"</a>"+"</li>" ; 
				});
				$("#title").empty().append(title);
				$("#content").empty().append(content);//"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				if(attachment != ""){
					$("#attachment").empty().append("附件："+attachment);
				}else{
					$("#attachment").empty();
				}				
				$("#publishUnit").empty().append(publishUnit);
				$("#publishUser").empty().append("发布者："+publishUser);
				$("#lastModifyTime").empty().append(lastModifyTime);
			}else{
				alert(msg.message);					
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	
	//打印公告内容
	$("#print_button").click(function(){
		//document.getElementById("line").style.display="none";
		 $("div #printAnno").printArea();
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

function download(attaId){
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}