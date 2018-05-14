<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  var contextPath = "${ctx}";
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>易买网</title>
	<link type="text/css" rel="stylesheet" href="../../statics/css/style.css" />   
    <script type="text/javascript" src="../../statics/js/common/jquery-1.11.1.min_044d0927.js"></script>
	<script type="text/javascript" src="../../statics/js/common/jquery.bxslider_e88acd1b.js"></script>
    <script type="text/javascript" src="../../statics/js/common/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../../statics/js/common/menu.js"></script> 
	<script type="text/javascript" src="../../statics/js/common/select.js"></script>
	<script type="text/javascript" src="../../statics/js/common/lrscroll.js"></script>
    <script type="text/javascript" src="../../statics/js/common/iban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/fban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/f_ban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/mban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/bban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/hban.js"></script>
    <script type="text/javascript" src="../../statics/js/common/tban.js"></script>
	<script type="text/javascript" src="../../statics/js/common/lrscroll_1.js"></script>
	<link rel="stylesheet" href="../../statics/verify/css/verify.css"/>
    <script src="../../statics/verify/js/jquery.min.js"></script>
    <script src="../../statics/verify/js/verify.js"></script>
</head>
<body>  
	<!--Begin Header Begin-->
	<div class="soubg">
		<div class="sou">
	        <span class="fr">
	        	<span class="fl">你好，请<a href="login.jsp">登录</a>&nbsp; 
	        		<a href="regist.jsp" style="color:#ff4e00;">免费注册 </a> 
	        	</span>
	            <span class="fl">|&nbsp;关注我们：</span>
	            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
	            <span class="fr">|&nbsp;
	            	<a href="#">手机版&nbsp;<img src="../../statics/images/s_tel.png" align="absmiddle" /></a>
	            </span>
	        </span>
	    </div>
	</div>
	<!--End Header End-->
	
	<!--Begin Login Begin-->
	<div class="log_bg">	
	    <div class="top">
	        <div class="logo"><a href="${ctx}/home?action=index"><img src="../../statics/images/logo.png" /></a></div>
	    </div>
		<div class="regist" >	     
	     <img src="../../statics/images/l_img.png" width="611" height="425" style="height:375px;width:367px; "/>
			<div class="reg_c" style="height: 570px; width: 619px; ">
			
	        	<form id="form"  onsubmit="return verify()" action="userServlet" method="post" style="height: 553px; width: 680px; ">
	        		<input type="hidden" name="action" value="register" />
		            <table border="0" style="width: 702px; font-size:14px; margin-top:20px;" cellspacing="0" cellpadding="0">
		              <tr height="50" valign="top">
		              	<td width="95">&nbsp;</td>
		                <td>
		                	<span class="fl" style="font-size:24px;">注册</span>
		                    <span class="fr">已有商城账号，<a href="login.jsp" style="color:#ff4e00;">我要登录</a></span>
		                </td>
		              </tr>
		              <tr height="50" style="width: 398px; ">
		                <td align="right"><font color="#ff4e00">*</font>&nbsp;用户名 &nbsp;</td>
		                <td style="width: 266px; height: 36px"><input type="text" value="" class="l_user" name="loginName" id="loginName" pattern="[a-zA-Z]\w{3,15}" required/></td>
		                <td style="width: 389px; "><span id="userInfo"></span></td>
		                
		              </tr>
		              <tr height="50">
		                <td align="right"><font color="#ff4e00">*</font>&nbsp;密码 &nbsp;</td>
		                <td><input type="password" value="" class="l_pwd" name="password1" id="password1" pattern="^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$" required/></td>
		              	<td><span id="passwordInfo1"></span></td>
		              </tr>
		              <tr height="50">
		                <td align="right"><font color="#ff4e00">*</font>&nbsp;确认密码 &nbsp;</td>
		                <td><input type="password" value="" class="l_pwd" name="password2" id="password2" pattern="^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$" required/></td>
		                <td><span id="passwordInfo2"></span></td>
		              </tr>
		              <tr height="50">
		                <td align="right"><font color="#ff4e00">*</font>&nbsp;邮箱 &nbsp;</td>
		                <td><input type="text" value="" class="l_email" name="email" id="email" 
		                	pattern="^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$" required/></td>
		              	<td><span id="emailInfo"></span></td>
		              </tr>
		              <tr height="50">
		                <td align="right"><font color="#ff4e00">*</font>&nbsp;手机 &nbsp;</td>
		                <td><input type="text" value="" class="l_tel" name="phone" id="phone" pattern="1[0-9]{10}" required/></td>
		              	<td><span id="phoneInfo"></span></td>
		              </tr>		              
		              <!-- <tr height="50" >
		                <td></td>
		                <td>
		                    <input type="text" value="" class="l_ipt"  />
		                    <a href="#" style="font-size:12px; font-family:'宋体';">换一张</a>
		                </td>
		              </tr> -->
		              <tr>
		              	<td>&nbsp;</td>
		                <td style="font-size:12px; padding-top:20px;">
		                	<span style="font-family:'宋体';" class="fl">
		                    	<label class="r_rad"><input type="checkbox" checked="checked" /></label><label class="r_txt">我已阅读并接受《用户协议》</label>
		                    </span>
		                </td>
		              </tr>
		              <tr height="60">
		              	<td>&nbsp;</td>
		                <td><input id="button" type="submit" value="立即注册" class="log_btn" /></td>
		              </tr>
		              <tr>
		                 <td><input type="text" value="0" name="check_verify" id="check_verify" hidden /></td>
                      </tr>                  
		            </table>
		            <div  id="mpanel1"></div>
		            <div ><span id="buttoninfo"></span> </div>
		            
	            </form>
	            
	        </div>
	        
	    </div>
	</div>
	<!--End Login End--> 
	
	<!--Begin Footer Begin-->
	<div class="btmbg">
	    <div class="btm">
	        备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
	        <img src="../../statics/images/b_1.gif" width="98" height="33" /><img src="../../statics/images/b_2.gif" width="98" height="33" /><img src="../../statics/images/b_3.gif" width="98" height="33" /><img src="../../statics/images/b_4.gif" width="98" height="33" /><img src="../../statics/images/b_5.gif" width="98" height="33" /><img src="../../statics/images/b_6.gif" width="98" height="33" />
	    </div>    	
	</div>
	<!--End Footer End -->  
	
	<script src="../../statics/js/common/jquery-1.12.4.js"></script>
	<script>
		//校验用户名
		$("#loginName").blur(function () {
	        var loginName=$("#loginName").val();	       
	        var user=document.getElementById("loginName");
	        if(loginName==null || loginName==""){
	            $("#userInfo").html("用户名不得为空");
	        }
			if(user.validity.patternMismatch==true){
				$("#userInfo").html("以字母开头,与数字组成4-16位");		        
	        }else{
	        	$("#userInfo").html("");
	            $.ajax({
	                "url":"userServlet?action=check",
	                "type":"GET",
	                "data":{"loginName":loginName},
	                "dataType":"text",                
	                "success":callback               
	            });
	        }
	    });
		
		//异步校验邮箱
		$("#email").blur(function(){
			 var email=$("#email").val();			 
			 if(email==null || email==""){
		        $("#emailInfo").html("邮箱不得为空");
		     }	
			 //验证邮箱以某个格式设置
			 var emailEntity=document.getElementById("email");
			 if(emailEntity.validity.patternMismatch==true){
	    		$("#emailInfo").html("邮箱格式有误");
	         }else{
	        	$("#emailInfo").html("");
	            $.ajax({
	                "url":"userServlet?action=check",
	                "type":"GET",
	                "data":{"email":email},
	                "dataType":"text",                
	                "success":callback               
	            });
	         }
		});
		
		//异步校验手机号
		$("#phone").blur(function(){
		     var phone=$("#phone").val();
		     if(phone==null || phone==""){
		        $("#phoneInfo").html("手机号不得为空");
		     }
		   	 //验证手机以某个格式设置
		     var mobile=document.getElementById("phone");
	    	 if(mobile.validity.patternMismatch==true){
	    		$("#phoneInfo").html("以1开头，由11位数字组成");
	         }else{
	        	$("#phoneInfo").html("");
	            $.ajax({
	                "url":"userServlet?action=check",
	                "type":"GET",
	                "data":{"phone":phone},
	                "dataType":"text",                
	                "success":callback               
	            });
	         }
		});		
		
	    function callback(data){
	        if(data=="loginNameError"){
	            $("#userInfo").html("用户名已存在，请更改");
	        }else if(data=="loginNameSuccess"){
	        	$("#userInfo").html("<span style='color:green'>✔</span>");
	        }
	        
	        if(data=="emailError"){
	            $("#emailInfo").html("email已注册，请更改");
	        }else if(data=="emailSuccess"){
	        	$("#emailInfo").html("<span style='color:green'>✔</span>");
	        }
	        
	        if(data=="phoneError"){
	            $("#phoneInfo").html("手机号码已存在，请更改");
	        }else if(data=="phoneSuccess"){
	        	$("#phoneInfo").html("<span style='color:green'>✔</span>");
	        }
	    }	    
	    
	   function verify(){
	      //     alert("verify");
	          var ver=$("#check_verify").val();
	          if (ver==1) {
	           $("#verify").val("0");
				return true;
			} else {
               $("#buttoninfo").text("必须先通过验证码");
               return false;
			}
           
        };
	    //点击用户名框，提示信息消除
	    $("#loginName").click(function(){
	    	$("#userInfo").html("");
	    });
	  	//点击第一个密码框，提示信息消除
	    $("#password1").click(function(){
	    	$("#passwordInfo1").html("");
	    	$("#passwordInfo2").html("");
	    });
	    //点击第二个密码框，提示信息消除
	    $("#password2").click(function(){
	    	$("#passwordInfo2").html("");
	    });
	  	//点击email框，提示信息消除
	    $("#email").click(function(){
	    	$("#emailInfo").html("");
	    });
	  	//点击phone框，提示信息消除
	    $("#phone").click(function(){
	    	$("#phoneInfo").html("");
	    });
	    
	    
	    //验证密码以某个格式设置
	    $("#password1").blur(function(){
	    	var pwd1=document.getElementById("password1");
	    	var password1=$("#password1").val();
	    	if(password1==null || password1==""){
	    		$("#passwordInfo1").html("密码不得为空");
	    	}else if(pwd1.validity.patternMismatch==true){
	    		$("#passwordInfo1").html("密码6位以上，至少包含字母和数字");
			}else{
				$("#passwordInfo1").html("<span style='color:green'>✔</span>");
			}
	    });
	    
	  	//校验两个密码相同
	    $("#password2").blur(function(){
	        var password1=$("#password1").val();
	        var password2=$("#password2").val();
	    	if(password2==null || password2==""){
	    		$("#passwordInfo2").html("密码不得为空");
	    	}else if(password1!=password2){
	            $("#passwordInfo2").html("两个密码不相同");
	            $("#password2").val("");
	        }else{
	        	$("#passwordInfo2").html("<span style='color:green'>✔</span>");
	        }
	    });	
	    //选字验证码
	  	 $('#mpanel1').pointsVerify({
        defaultNum : 4, //默认的文字数量
        checkNum : 2,   //校对的文字数量
        vSpace : 5, //间隔
        mode:"pop",
        imgName : ['1.jpg', '2.jpg'],
        imgSize : {
            width: '450px',
            height: '300px',
        },
        barSize : {
            width : '600px',
            height : '40px',
        },
        ready : function() {
        },
        success : function() {
      //      alert('验证成功！');
           $("#check_verify").val("1");
        //    submit();
        
     
        },
        error : function() {
        //                  alert('验证失败！');
        
        $("#check_verify").val("0");
        }

    });
	</script>
	</body>
</html>
