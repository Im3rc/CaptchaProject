<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <title>Use a verification code in the Form form</title>
    <script type="text/javascript">
     //Refresh Code 
    function changeImg(){
    	 
        document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/CaptchaGenerator?"+Math.random();
      
    }

    </script>
  </head>
  
  <body>
        <br/>
        <form action="${pageContext.request.contextPath}/CaptchaVerifier" method="post">
         <img alt="Error!! "src="${pageContext.request.contextPath}/CaptchaGenerator" id="validateCodeImg" onclick="changeImg()">
 
            <br/>
        <p>
 Enter the CAPTCHA: <input type="text" name="validateCode"/></p>
 <a href="javascript:void(0)" onclick="changeImg()">Can’t see, change one</a>
            <br/> 

 <input type="submit" value="Verify CAPTCHA">
        </form>
  
 </body>
