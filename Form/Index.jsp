<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Form/contact-form.css" /> 
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>

<script>

function changeImg(){
	
    document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/CaptchaGenerator?"+Math.random();
}

</script>


<body>
<div class="fcf-body">

    <div id="fcf-form">
    <h3 class="fcf-h3">Contact us</h3>

     <form action="${pageContext.request.contextPath}/CaptchaVerifier" method="post">
        
        <div class="fcf-form-group">
            <label for="Name" class="fcf-label">Name</label>
            <div class="fcf-input-group">
                <input type="text" id="Name" name="Name" class="fcf-form-control" required>
            </div>
        </div>

        <div class="fcf-form-group">
            <label for="Email" class="fcf-label">Email Address</label>
            <div class="fcf-input-group">
                <input type="email" id="Email" name="Email" class="fcf-form-control" required>
            </div>
        </div>

        <div class="fcf-form-group">
            <label for="Message" class="fcf-label">Message</label>
            <div class="fcf-input-group">
                <textarea id="Message" name="Message" class="fcf-form-control" rows="6" maxlength="3000" required></textarea>
            </div>
        </div>
	         <img alt="Error!! "src="${pageContext.request.contextPath}/CaptchaGenerator" id="validateCodeImg" onclick="changeImg()" width="150">
	         <!--<button onClick="changeImg()">Refresh</button>  -->  
	              <div class="fcf-form-group">
	        <button id="fcf-button" class="fcf-btn fcf-btn-primary fcf-btn-lg" onClick="changeImg()">Refresh</button>
	    </div>
      
 <!--   <p> Enter the CAPTCHA: <input type="text" name="validateCode"/></p> -->
   <div class="fcf-form-group">
            <label for="validateCode" class="fcf-label">Enter the CAPTCHA:</label>
            <div class="fcf-input-group">
                <input type="text" name="validateCode" class="fcf-form-control" maxlength=6 required>
            </div>
        </div>
  <%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
        
    }
%>
  <div class="fcf-form-group">
        <button type="submit" id="fcf-button" class="fcf-btn fcf-btn-primary fcf-btn-lg fcf-btn-block">Send Message</button>
    </div>
        </form>
    </div>

</div>

        
</body>
</html>