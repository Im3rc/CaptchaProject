package com.CaptchaProject.app;

import java.io.IOException;
import java.io.PrintWriter;
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
        //   request.getRequestDispatcher("/Form/CaptchaScript.jsp").include(request, response);  
 String clientCheckcode = request.getParameter("validateCode");
 System.out.println(clientCheckcode);
 String serverCheckcode = (String) request.getSession().getAttribute("checkcode");
 System.out.println(serverCheckcode);
 String  n=request.getParameter("Name");
 if (clientCheckcode.equals(serverCheckcode)) {
//     out.println ("<script>");
//     out.println ("window.close()");

	 request.setAttribute("name", n);
//     out.println ("</script>");
	 request.getRequestDispatcher("/Form/Welcome.jsp").forward(request, response);
	  
	 System.out.println("Verification Code Verification!");
 }else {
	 request.setAttribute("errorMessage", "Invalid CAPTCHA!! Please Try Again ");
//     out.print("Incorrect CAPTCHA!! "
//     		+ "Please Try Again");  
     request.getRequestDispatcher("/Form/Index.jsp").include(request, response);  

//response.getWriter().println("foo");
//request.getRequestDispatcher("CaptchaScript.jsp").forward(request, response);
//response.getWriter().println("foo");
	
 }
 out.close();
}
 }
 
