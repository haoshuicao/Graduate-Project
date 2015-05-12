$(document).ready(function(){
	$("#upload_file").click(function(){
		$("#showRecord").empty();
		document.getElementById("tip1").style.display = "none";
		document.getElementById("tip2").style.display = "none";
		document.getElementById("tip3").style.display = "none";
		if(check($("#postfile").val())){
			document.getElementById('loadTip').style.display = 'block';
			$.ajaxFileUpload({
				url:'/threeExaminations/uploadDownloadController/uplaodServerRecord.do',
				secureuri:false,
				fileElementId:'postfile',
				dataType: 'json',
				type:'POST',
				data:{
					
				},success: function(data, status){ 
					if(data.result==true){
							alert(data.message);
							document.getElementById('span1').innerHTML = "<input type='text' name='textfield' id='textfield' class='txt' />";
							document.getElementById('span2').innerHTML = "<input type='file' name='postfile' class='file' id='postfile' size='28' onchange=\"document.getElementById('textfield').value=this.value.slice(12)\" />";
							document.getElementById('loadTip').style.display = 'none';
							document.getElementById('tip1').style.display = 'none';
							document.getElementById('tip2').style.display = 'none';
							document.getElementById('tip3').style.display = 'none';
							var time;
							var myDate = new Date();   
							time = myDate.getFullYear()+'年'+(myDate.getMonth()+1)+'月'+myDate.getDate()+'日';
							var detail="<table  class=\"table table-condensed\"><caption><h4><b>上传的信息记录</b><h4/><br/></caption>" +
							"<tbody><tr class=\"success\"><td>年度：</td><td>"+data.tempYearList+"</td></tr>" +
							"<tr><td style=\"background-color:rgb(242,242,242)\">批次：</td><td style=\"background-color:rgb(242,242,242)\">"+data.tempBatchList+"</td></tr>" +
							"<tr class=\"success\"><td>村/社区：</td><td>"+data.tempNowLiveVillageList+"</td></tr>"+
							"<tr><td style=\"background-color:rgb(242,242,242)\">上传时间：</td><td style=\"background-color:rgb(242,242,242)\">"+time+"</td></tr>"+
							"<tr class=\"success\"><td>记录数：</td><td>"+data.count+"</td></tr>"+
							"</tbody></table>";
							$("#showRecord").empty().append(detail);	
							
					}else{
						document.getElementById("tip1").style.display = "none";
						document.getElementById("tip2").style.display = "none";
						document.getElementById("tip3").style.display = "block";
						document.getElementById('loadTip').style.display = 'none';
					}
					
				},error: function (data, status, e){
					alert("上传失败！");
		         }
			});
		}
	});
});


function check(file){
	if(file==""){
		document.getElementById("tip3").style.display = "none";
		document.getElementById("tip2").style.display = "none";
		document.getElementById("tip1").style.display = "block";
		return false;
	}else{
		if(file.substring(file.length-3)=="xls" || file.substring(file.length-4)=="xlsx"){
			return true;
		}else{
			document.getElementById("tip1").style.display = "none";
			document.getElementById("tip3").style.display = "none";
			document.getElementById("tip2").style.display = "block";
			return false;
		}
	}
}