package com.CaptchaProject.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class CaptchaVerifier
 */
@WebServlet("/CaptchaVerifier")
public class CaptchaVerifier extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2594931829301505674L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   response.setContentType("text/html");  
           PrintWriter out=response.getWriter();  
  
 String clientCheckcode = request.getParameter("validateCode");

 String serverCheckCode = DataStorage.getDataMap().get("key").toString();
 out.print(serverCheckCode);  
 String  n=request.getParameter("Name");
 if (clientCheckcode.equals(serverCheckCode)) {

	 request.setAttribute("name", n);
	 request.getRequestDispatcher("/Form/Welcome.jsp").forward(request, response);
	  
	 System.out.println("Verification Code Verification!");
 }else {
	 request.setAttribute("errorMessage", "Invalid CAPTCHA!! Please Try Again "); 
     request.getRequestDispatcher("/Form/Index.jsp").include(request, response);  
 }
 out.close();
	}
}
