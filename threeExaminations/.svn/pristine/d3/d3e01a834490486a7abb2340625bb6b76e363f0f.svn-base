$(document).ready(function(){
	
});
//返回登陆页面
function Return(){
	window.location.href="login.html";
}
//找回密码，输入用户名和邮箱进行密码找回功能
function ResetPwd(){
	var username = $("#username").val();
	var email = $("#email").val();
	if(cheack()){
	$.ajax({
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		url : '/threeExaminations/userController/returnCode.do',
		async : false,
		data : {
			username:username,
			email:email
		},
		dataType : 'json',
		success : function(msg) {
			if(msg.result == true && validate()==true){
				alert("密码已发至你的邮箱！");
				window.location.href="login.html";
			}else{
			alert(msg.message);
		}
		},error: function(msg){
	        alert("网络超时！");
		}
	});
	}
}
function cheack(){
	if($("#username").val()==""){
		alert("请填写用户名！");
		return false;
	}
	else if($("#email").val()==""){
		alert("请填写邮箱！");
		return false;
	}
	else
		return true;
}
var code ; //在全局 定义验证码  
function createCode()  
{   
  code = "";  
  var codeLength = 4;//验证码的长度  
  var checkCode = document.getElementById("checkCode");  
  var selectChar = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//所有候选组成验证码的字符，当然也可以用中文的  
     
  for(var i=0;i<codeLength;i++)  
  {  
   
     
  var charIndex = Math.floor(Math.random()*36);  
  code +=selectChar[charIndex];  
    
    
  }  
 
  if(checkCode)  
  {  
    checkCode.className="code";  
    checkCode.value = code;  
  }  
    
}  
  
 function validate ()  
{  
  var inputCode = document.getElementById("identyCode").value;  
  if(inputCode.length <=0)  
  {  
      alert("请输入验证码！"); 
      return false;
  }  
 
//不区分大小写
  else if(inputCode.toUpperCase () == code)
  {
     return true; // 正确
  }
  else{
	  alert("验证码输入错误！");  
	  createCode();//刷新验证码
	  return false;
  }
    
  } 
