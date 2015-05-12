$(document).ready(function(){
	$("#print_button").click(function(){
		 $("div #printAnno").printArea();
	});
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/announcementController/lookAnnoList.do',
		async : false,
		data : {
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				$("#announcementTip").hide();
				var announcement = "<tr class='success'><th width=580>"+"公告标题"+"</th><th>"+"发布时间"+"</th><tr>";
				var title = "";
				var content = "";
				var publishUnit = "";
				var publishUser = "";
				var lastModifyTime = "";
				var attachment = "";
				
				$.each(msg.annoList,function(key,val){
					if(val.annoTitle.length > 35){
						val.annoTitle = val.annoTitle.substr(0,35)+"......";
					}
					announcement+="<tr><td>"+"<a href=\"#\" onclick=\"javascript:showAnnoContent(" + val.annoId + ")\">"+val.annoTitle+"</a></td><td>"+DateFormat(val.annoLastmodifytime)+"</td></tr>";
				});
				$("#announcementTable").empty().append(announcement);
				title += msg.newAnno.annoTitle;
				content += msg.newAnno.annoContent;
				publishUnit += msg.newUnit;
				publishUser += msg.publishUser;
				lastModifyTime += msg.date;
				$.each(msg.attaList, function(key, val){
					attachment+="<li>"+"<a href=\"javascript:download("+val.attaId+")\">"+val.attaPagename+"</a>"+"</li>" ; 
				});
				if(attachment != ""){
					$("#attachment").empty().append("附件："+attachment);
				}else{
					$("#attachment").empty();
				}
				
				$("#title").empty().append(title);
				$("#content").empty().append(content);
				$("#publishUnit").empty().append(publishUnit);
				$("#publishUser").empty().append("发布者："+publishUser);
				$("#lastModifyTime").empty().append(lastModifyTime);
				
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
													url : '/threeExaminations/announcementController/findRecordByPage.do',
													async : false,
													data : {
														page:page
													},
													dataType : 'json',
													success : function(msg) {
														if(msg.result == true){
															var announcement = "<tr class='success'><th width=580>"+"公告标题"+"</th><th>"+"发布时间"+"</th><tr>";
															$.each(msg.annoRecordCurrent,function(key,val){
																if(val.annoTitle.length > 35){
																	val.annoTitle = val.annoTitle.substr(0,35)+"......";
																}
																announcement+="<tr><td>"+"<a href=\"#\" onclick=\"javascript:showAnnoContent(" + val.annoId + ")\">"+val.annoTitle+"</a></td><td>"+DateFormat(val.annoLastmodifytime)+"</td></tr>";
															});
															$("#announcementTable").empty().append(announcement);
														}else{
															document.getElementById('warn').innerHTML = "获取失败！";
															$('#myWarnModal').modal('show');
														}
													},error: function(msg){
												        alert("网络超时！");
													}
												});
					}
				});
			}else{
				$("#announcementAll").hide();
				$("#announcementTip").show();
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	
	
});

function showAnnoContent(annoid){
	var annoId = annoid;
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
				if(attachment != ""){
					$("#attachment").empty().append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;附件："+attachment);
				}else{
					$("#attachment").empty();
				}
				$("#title").empty().append(title);
				$("#content").empty().append(content);
				$("#publishUnit").empty().append(publishUnit);
				$("#publishUser").empty().append("发布者："+publishUser);
				$("#lastModifyTime").empty().append(lastModifyTime);
			}else{
				alert(msg.message);
			};
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}
function DateFormat(dateStr){ 
	  var date = new Date(parseInt(dateStr)); 
	  return date.getFullYear(date) + '年' 
	      + ((date.getMonth() + 1) < 10 ? "0"+(date.getMonth() + 1):(date.getMonth() + 1)) + '月'
	      +(date.getDate()<10 ? "0"+date.getDate():date.getDate())+'日'; 
} 
function download(attaId){
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}
