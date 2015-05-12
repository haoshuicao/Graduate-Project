$(document).ready(function(){
	if ($.cookie("rmbUser") == "true") {
        $("#rmbUser").attr("checked", true);
        $("#username").val($.cookie("username"));
        $("#password").val($.cookie("password"));
        }
	$("#login").click(function(){
		
		var username = $("#username").val();
		var pwd = $("#password").val();
		var password = hex_md5(pwd);
		//alert(password);
	
		if(cheack()){
		$.ajax({
			type : "post",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			url : '/threeExaminations/userController/login.do',
			async : false,
			data : {
				username:username,
				password:password,
			},
			dataType : 'json',
			success : function(msg) {
					if(msg.result == true){
							window.location.href=msg.href;
						}else if(msg.result == false){
						alert(msg.message);
					}
			},error: function(msg){
		        alert("网络超时！");
			}
		});
		}
	});
	
});
 
function cheack(){
	if($("#username").val()==""){
		alert("请填写用户名！");
		return false;
	}
	else if($("#password").val()==""){
		alert("请填写密码！");
		return false;
	}
	else if(validate()){
		return true;
	}
	
}
//记住用户名密码
function Save(){
    if ($("#rmbUser").attr("checked")) {
        var username = $("#username").val();
        var password = $("#password").val();
        $.cookie("rmbUser", "true", { expires: 7 ,}); //存储一个带7天期限的cookie
        $.cookie("username", username, { expires: 7, });
        $.cookie("password", password, { expires: 7, });
    }else{
        $.cookie("rmbUser", "false", { expire: -1, });
        $.cookie("username", "", { expires: -1, });
        $.cookie("password", "", { expires: -1, });
    }
}
var code ; //在全局 定义验证码  
function createCode()  
{   
	//alert("++++++++++");
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
  


 function validate() {
	var inputCode = document.getElementById("identyCode").value;
	if (inputCode.length <= 0) {
		alert("请输入验证码！");
		return false;
	}else if (inputCode.toUpperCase() == code){//不区分大小写
		return true; // 正确
	}else {
		alert("验证码输入错误！");
		createCode();//刷新验证码
		return false;
	}
}
 
 function keyLogin(){  
	    if (event.keyCode==13)   //回车键的键值为13  
	         document.getElementById("login").click(); //调用登录按钮的登录事件  
	    } 
 



