$(document).ready(function(){
	attaNum=0;//上传附件次数，用户删除标记显示每个附件的唯一id
	attaPageNameList=new Array();
	attaSystemNameList = new Array();
	attaUploadPath = new Array();
	//发布公告信息
	$("#publish").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		var attaPageName="";
		var attaSystemName="";
		//获取所有附件的页面显示名
		for(var i=0;i < $("#attachment li a").length; i++){
			attaPageNameList[i] = $("#attachment li a:eq("+i+")").text();
			//alert(attaPageNameList[i]);
			attaPageName+=attaPageNameList[i]+",";
		}
		//获取所有附件的系统名
		for(var j=0;j<attaSystemNameList.length;j++){
			if(attaSystemNameList[j] != 0)
			{
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
				attaPageName:attaPageName,
				
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
					document.getElementById('warn').innerHTML = "上传成功!";
					$('#myWarnModal').modal('show');
					attaUploadPath[attaNum] = uploadPath;//保存上传成功的附件路径，以便检验下次上传的附件是否相同
					var attachment="";					
					attaSystemNameList[attaNum] = data.systemName;//保存文件的系统名，从下标0开始
					
					//提供上传完附件就进行下载。
					/*var downloadPath=data.systemName+","+data.pageName;
					attachment +="<li id='li"+attaNum+"'>"+"<a href=\"javaScript:download2('"+downloadPath+"')\">"+data.pageName+"</a></li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;*/
					//不提供上传完附近就下载
					attachment +="<li id='li"+attaNum+"'>"+"<a href=\"#\">"+data.pageName+"</a></li>"+"&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a id='a"+attaNum+"' href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;
					$("#attachment").append(attachment);
					attaNum++;//附件数增加
					//alert(attaNum);
				
					//动态创建li标签
					/*attachment+="<a href=\"#\">"+data.path+"</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+"<a href=\"javaScript:deleteAtta("+attaNum+")\">"+"删除"+"</a>" ;
					var li= document.createElement("li");
					li.innerHTML=attachment;
					var s= document.getElementById("attachment");
					s.appendChild(li);*/
					
					/*var num=document.getElementById("attachment").getElementsByTagName("li");
					alert(num.length);
					var a=new Array();
					for(var i=0;i < $("#attachment li a").length; i++){
						a[i] = $("#attachment li a:eq("+i+")").text();
						alert(a[i]);
					}*/
					
					/*//正则表达式进行<a>标签匹配
					var strXml=document.getElementById("attachment").innerHTML;
					alert(strXml);
				    var regExp = /<a.*>(.*)<\/a>/ig;
				    //exec返回一个数组对象
				    var arr = strXml.match(regExp);
				    alert(arr);
				    alert(RegExp.$1);*/
				},error: function (data, status, e){//服务器响应失败处理函数
					$("#attachmentTip1").show();
					$("#attachmentTip2").hide();
					document.getElementById('warn').innerHTML = "上传失败!";
					$('#myWarnModal').modal('show');
		         }
			});
		}
	});
	
});

//根据附件id下载附件
function download(attaId){
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment.do?id="+attaId;
}
//根据附件在服务器上的保存的系统名下载附件
function download2(downloadPath){
	//alert("下载"+downloadPath);
	//下载公告附件
	location.href="/threeExaminations/announcementController/downloadAttachment2.do?downloadPath="+downloadPath;
	
}

function deleteAtta(deletenum){//alert(attaSystemNameList.length);
	//按标签的id删除某标签
	//alert("删除"+deletenum);
	//删除页面上显示的附件名
	var s=document.getElementById("attachment");
	var aId=document.getElementById("a"+deletenum);
	var liId=document.getElementById("li"+deletenum);
    s.removeChild(aId);
    s.removeChild(liId);
    //标记删除对应保存的系统附件名
    attaSystemNameList[deletenum] = 0;
    attaUploadPath[deletenum] = 0;//标记删除保存的对应的附件路径
   //attaSystemNameList.splice(deletenum, 1);//alert(attaSystemNameList.length);
   
    /*var linum=s.getElementsByTagName("li");
    var anum=s.getElementsByTagName("a");
    alert("li标签个数"+linum.length);
	alert("a标签个数"+anum.length);*/
	
	/*//按顺序删除某个没有id的标签
	alert("删除第"+deletenum+"个");
	var s=document.getElementById("attachment");
    var linum=s.getElementsByTagName("li");
    var anum=s.getElementsByTagName("a");
    alert("li标签个数"+linum.length);
    alert("a标签个数"+anum.length);
    s.removeChild(anum[2*(deletenum-1)+1]);	
    s.removeChild(linum[deletenum-1]);
    alert("li标签个数"+linum.length);
    alert("2a标签个数"+anum.length); */   		
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
		}else {
			var flag = 0;
			for(var i = 0; i < attaNum; i++){
				if(file == attaUploadPath[i]){
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