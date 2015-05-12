$(document).ready(function(){
	$("#search").click(function(){
		var townName = $("#townName").val();
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/threeCheckServiceController/searchThreeList.do',
			async : false,
			data : {
				townName:townName
			},
			dataType : 'json',
			success : function(msg) {
				if(msg.result ==true){
					var threeListTableData = "";
					threeListTableData += "<tr class=\"success\"><th>身份证</th><th>姓名</th><th>户籍地</th><th>现居住镇</th><th>现居住村</th><th>居住状况</th><th>是否寄回三查情况</th></tr>";
					$.each(msg.threeCheckServiceList,function(key,val){
						var isreturn;
						if(val.threecheckservicecol==0 || val.threecheckservicecol==""){
							isreturn = "否";
						}
						else{
							isreturn = "是";
						}
						threeListTableData += "<tr><td>" +
							val.thcsIdnumber + "</td><td> " +
							val.thcsName + "</td><td> " +
							val.thcsHouseholdregister +	"</td><td> " +
							val.thcsNowlivetown + "</td><td>" +
							val.thcsNowlivevillage + "</td><td>" +
							val.thcsLivestate + "</td><td>" +
							isreturn + "</td></tr>";
						});
					$("#threeListTable").empty().append(threeListTableData);
					
				}else{
					alert("错误！");
				}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
	});
});