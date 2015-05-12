$(document).ready(function(){
	//打印公告内容
	$("#print_button").click(function(){
		//document.getElementById("line").style.display="none";
		 $("div #printAnno").printArea();
	});
	
    //显示当前用户能查看的公告列表
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
				//var announcement = "";
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
					//announcement+="<li>"+"<a href=\"javascript:showAnnoContent(" + val.annoId + ")\">"+val.annoTitle+".........."+DateFormat(val.annoLastmodifytime)+"</a>"+"</li>"; 				
				});
				//$("#announcementList").empty().append(announcement);
				$("#announcementTable").empty().append(announcement);
				//默认显示最新的公告
				title += msg.newAnno.annoTitle;
				content += msg.newAnno.annoContent;
				publishUnit += msg.newUnit;
				publishUser += msg.publishUser;
				lastModifyTime += msg.date;
				//附件
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
													url : '/threeExaminations/announcementController/findRecordByPage.do',
													async : false,
													data : {
														page:page
													},
													dataType : 'json',
													success : function(msg) {
														if(msg.result == true){
															var announcement = "<tr class='success'><th width=580>"+"公告标题"+"</th><th>"+"发布时间"+"</th><tr>";
				                                           // var announcement = "";															
															$.each(msg.annoRecordCurrent,function(key,val){
																if(val.annoTitle.length > 35){
																	val.annoTitle = val.annoTitle.substr(0,35)+"......";
																}
																announcement+="<tr><td>"+"<a href=\"#\" onclick=\"javascript:showAnnoContent(" + val.annoId + ")\">"+val.annoTitle+"</a></td><td>"+DateFormat(val.annoLastmodifytime)+"</td></tr>";
																//announcement+="<li>"+"<a href=\"javascript:showAnnoContent(" + val.annoId + ")\">"+val.annoTitle+".........."+DateFormat(val.annoLastmodifytime)+"</a>"+"</li>"; 			
															});
															$("#announcementTable").empty().append(announcement);
															//$("#announcementList").empty().append(announcement);
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
				//alert(msg.message);					
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	
	
});

//显示某一选中的公告信息
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
//将时间戳转换为时间，月和日以两位数显示
function DateFormat(dateStr){ 
	  var date = new Date(parseInt(dateStr)); 
	  return date.getFullYear(date) + '年' 
	      + ((date.getMonth() + 1) < 10 ? "0"+(date.getMonth() + 1):(date.getMonth() + 1)) + '月'
	      +(date.getDate()<10 ? "0"+date.getDate():date.getDate())+'日'; 
} 

function download(attaId){
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}
