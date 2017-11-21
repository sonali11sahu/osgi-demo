package com.test.web;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import org.osgi.service.component.annotations.*;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

import com.test.api.StringConvertor;

@Component(
		property = {
			HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/string"
		})
public class StringConvertorServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	@Reference
    private StringConvertor str;
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

		 	String resStr,output;
	        String input = req.getParameter("value");
	        String method = req.getParameter("method");
	        
	        if (input == null || method == null) {
	            throw new IllegalArgumentException("any of the inputs can not be null");
	        }
	        
	        if(method.equalsIgnoreCase("invert"))
	         output = str.invert(input);
	        else if(method.equalsIgnoreCase("lower"))
		         output = str.lowerCase(input);
	        else if(method.equalsIgnoreCase("upper"))
		         output = str.upperCase(input);
	        else if(method.equalsIgnoreCase("random"))
		         output = str.random();
	        else if(method.equalsIgnoreCase("length"))
		         output = String.valueOf(str.length(input));
	        else
	        	output = "no valid operation inputted";

	        resp.setContentType("text/html");
	        resp.getWriter().write(
	            "<html><body><h1>Result is " + output + "</h1></body></html>");
	        }

}