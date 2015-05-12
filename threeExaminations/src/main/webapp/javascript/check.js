$(document).ready(function(){
	
});
//验证用户信息
function checkLoginName(){
	if($("#userLoginName").val() == ""){
		document.getElementById("checkLoginName1").style.display="block";
		document.getElementById("checkLoginName2").style.display="none";
	}else if(($("#userLoginName").val()).length < 4 || ($("#userLoginName").val()).length > 20){
		document.getElementById("checkLoginName1").style.display="none";
		document.getElementById("checkLoginName2").style.display="block";
		$("#userLoginName").val("");
		//document.getElementById("userLoginName").focus();
	}else{
		document.getElementById("checkLoginName1").style.display="none";
		document.getElementById("checkLoginName2").style.display="none";
	}
}

function checkRealName(){
	if($("#userName").val() == ""){
		document.getElementById("checkRealName1").style.display="block";
		document.getElementById("checkRealName2").style.display="none";
	}else if(($("#userName").val()).length > 45){
		document.getElementById("checkRealName1").style.display="none";
		document.getElementById("checkRealName2").style.display="block";
		$("#userName").val("");
		//document.getElementById("userName").focus();
	}else{
		document.getElementById("checkRealName1").style.display="none";
		document.getElementById("checkRealName2").style.display="none";
	}
}

function checkPwd(){
	if($("#userLoginPwd").val() == ""){
		document.getElementById("checkPwd1").style.display="block";
		document.getElementById("checkPwd2").style.display="none";
	}else if(($("#userLoginPwd").val()).length < 4 || ($("#userLoginPwd").val()).length > 20){
		document.getElementById("checkPwd1").style.display="none";
		document.getElementById("checkPwd2").style.display="block";
		$("#userLoginPwd").val("");
		//document.getElementById("userLoginPwd").focus();
	}else{
		document.getElementById("checkPwd1").style.display="none";
		document.getElementById("checkPwd2").style.display="none";
	}
}

function checkPwd2(){
	if($("#userLoginPwd2").val() == ""){
		document.getElementById("checkRePwd1").style.display="block";
		document.getElementById("checkRePwd2").style.display="none";
	}else if($("#userLoginPwd").val() != $("#userLoginPwd2").val()){
		document.getElementById("checkRePwd1").style.display="none";
		document.getElementById("checkRePwd2").style.display="block";
		$("#userLoginPwd").val("");
		$("#userLoginPwd2").val("");
		//document.getElementById("userLoginPwd").focus();
	}else{
		document.getElementById("checkRePwd1").style.display="none";
		document.getElementById("checkRePwd2").style.display="none";
	}
}

function checkPhone(){
	if($("#userPhone").val() == ""){
		document.getElementById("checkPhone1").style.display="block";
		document.getElementById("checkPhone2").style.display="none";
	}else{
	var mobile=$("#userPhone").val(); 
	//联通130、131、132、155、156、186、185
	//移动134、135、136、137、138、139、150、151、152、157、158、159、182、183、188、187
	//电信133、153、180、181、189
	var reg0 = /^13\d{5,9}$/;  
	var reg1 = /^0\d{10,11}$/; 
	
	var reg3 = /^150\d{4,8}$/;
	var reg4 = /^151\d{4,8}$/;
	var reg5 = /^152\d{4,8}$/;
	var reg6 = /^153\d{4,8}$/;
	var reg7 = /^155\d{4,8}$/;
	var reg8 = /^156\d{4,8}$/;
	var reg9 = /^157\d{4,8}$/;
	var reg10 = /^158\d{4,8}$/;
	var reg11 = /^159\d{4,8}$/;
	
	var reg12 = /^180\d{4,8}$/;
	var reg13 = /^181\d{4,8}$/;
	var reg14 = /^182\d{4,8}$/;
	var reg15 = /^183\d{4,8}$/;
	var reg16 = /^185\d{4,8}$/;
	var reg17 = /^186\d{4,8}$/;
	var reg18 = /^187\d{4,8}$/;
	var reg19 = /^188\d{4,8}$/;
	var reg20 = /^189\d{4,8}$/;
	
	var reg21 = /^170\d{4,8}$/;
	var reg2 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	var my = false; 
	if (reg0.test(mobile))my=true; 
	if (reg1.test(mobile))my=true; 
	if (reg2.test(mobile))my=true; 
	if (reg3.test(mobile))my=true; 
	if (reg4.test(mobile))my=true; 
	if (reg5.test(mobile))my=true; 
	if (reg6.test(mobile))my=true; 
	if (reg7.test(mobile))my=true;
	if (reg8.test(mobile))my=true; 
	if (reg9.test(mobile))my=true; 
	if (reg10.test(mobile))my=true; 
	if (reg11.test(mobile))my=true;
	if (reg12.test(mobile))my=true; 
	if (reg13.test(mobile))my=true; 
	if (reg14.test(mobile))my=true; 
	if (reg15.test(mobile))my=true;
	if (reg16.test(mobile))my=true; 
	if (reg17.test(mobile))my=true; 
	if (reg18.test(mobile))my=true; 
	if (reg19.test(mobile))my=true;
	if (reg20.test(mobile))my=true;
	if (reg21.test(mobile))my=true;
	if (!my){ 
		$("#userPhone").val("");
		document.getElementById("checkPhone1").style.display="none";
		document.getElementById("checkPhone2").style.display="block";
	//document.getElementById("userPhone").focus();
	}else{
		document.getElementById("checkPhone1").style.display="none";
		document.getElementById("checkPhone2").style.display="none";
	}
	}
}

function checkEmail(){
	if($("#userEmail").val() == ""){
		document.getElementById("checkEmail1").style.display="block";
		document.getElementById("checkEmail2").style.display="none";
	}else{	
	var strEmail=$("#userEmail").val();
	var p=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if (!p.test(strEmail)){
		document.getElementById("checkEmail1").style.display="none";
		document.getElementById("checkEmail2").style.display="block";
		document.getElementById("userEmail").value="";
		//document.getElementById("userEmail").focus();
	}else{
		document.getElementById("checkEmail1").style.display="none";
		document.getElementById("checkEmail2").style.display="none";
	}
	}
}
