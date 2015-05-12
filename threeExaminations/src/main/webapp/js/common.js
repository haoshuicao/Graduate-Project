$(document).ready(function(){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/showCurrentUser.do',
		async : false,
		data : {
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result ==true){
				var userName = "";
				var areaName = "";
				var userAuth = "";
				userName +=msg.userName;
				areaName +=msg.areaName;
				$("#unit").empty().append(areaName);
				$("#username").empty().append(msg.user.userName);
				$.each(msg.authList,function(key,val){
					userAuth+="<li><a href=\"" + val.authDescibe + "\">" + val.authName + "</a></li>"; 
					});
				$("#authList").empty().append(userAuth);
			}else{
				alert(msg.message);
				window.location.href="login.html";
			}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	$("#logout").click(function(){	
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/logout.do',
			async : false,
			data : {
				
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					window.location.href="login.html";
				}else{					
					window.location.href="login.html";
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
});
