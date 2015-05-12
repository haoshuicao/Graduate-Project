$(document).ready(function(){
	showAnnoList();
});

function showAnnoList(){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/announcementController/showAnnoList.do',
		async : false,
		data : {
		},
		dataType : 'json',
		success : function(msg) {
            var announcementTableData = "";				
            announcementTableData += "<caption><b>公告列表</b></caption><tr class=\"success\"></th><th width=500>公告标题</th><th>发布时间</th><th>操作</th></tr>";
			if(msg.result ==true){
				$.each(msg.annoRecordFirst,function(key,val){
					if(val.annoTitle.length > 30){
						val.annoTitle = val.annoTitle.substr(0,30)+"......";
					}
					announcementTableData += "<tr><td>"+
						"<a href=\"announcement2.html?id=" + val.annoId + "\">" +
						val.annoTitle +"</a></td><td> "+DateFormat(val.annoLastmodifytime)+  "</a></td><td> " +
						"<a href=\"editannouncement.html?id=" + val.annoId + "\">"+"修改"+"</a>"+"&nbsp;&nbsp;"
						+"<a href=\"javascript:deleteAnno("+val.annoId+")\">"+"删除"+"</a></td></tr>";
				});
				$("#announcementtable").empty().append(announcementTableData);
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
														var announcementTableData = "";	
											            announcementTableData += "<caption><b>公告列表</b></caption><tr class=\"success\"><th width=500>公告标题</th><th>发布时间</th><th>操作</th></tr>";
														if (msg.result == true) {
															$.each(msg.annoRecordCurrent,function(key,val){	
																if(val.annoTitle.length > 30){
																	val.annoTitle = val.annoTitle.substr(0,30)+"......";
																}
																announcementTableData += "<tr><td> "+
																	"<a href=\"announcement2.html?id=" + val.annoId + "\">" +
																	val.annoTitle +  "</a></td><td> "+DateFormat(val.annoLastmodifytime)+  "</a></td><td> " +
																	"<a href=\"editannouncement.html?id=" + val.annoId + "\">"+"修改"+"</a>"+"&nbsp;&nbsp;"
																	+"<a href=\"javascript:deleteAnno("+val.annoId+")\">"+"删除"+"</a></td></tr>";
															   
															});
															$("#announcementtable").empty().append(announcementTableData);
																
														}else{
															document.getElementById('warn').innerHTML = "获取失败！";
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
				document.getElementById("announcementTip").style.display="block";
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
}

function deleteAnno(annoId){
	$('#myDeleteModal').modal('show');
	$("#sureDelete").click(function(){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/announcementController/deleteAnnouncement.do',
			async : false,
			data : {
				annoId:annoId
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					document.getElementById('warn').innerHTML = "删除成功！";
					$('#myWarnModal').modal('show');
					$("#sure").click(function(){
					location.replace('announcementmanagement.html');
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