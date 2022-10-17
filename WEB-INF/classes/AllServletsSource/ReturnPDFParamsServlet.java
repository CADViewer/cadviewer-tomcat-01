
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ReturnPDFParamsServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("option");
		outPut.println("<html>");
		outPut.println("call by doGet ReturnPDFParamsServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
//	    response.setCharacterEncoding("UTF-8"); //
//	    response.getWriter().write(fileContent);

//		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileLocation = "";
		if (getServletConfig().getInitParameter("fileLocation") != null)
	    	fileLocation = getServletConfig().getInitParameter("fileLocation");

		String returnParameters =   fileLocation + "|";

		String fileLocationUrl = "";
		if (getServletConfig().getInitParameter("fileLocationUrl") != null)
	    	fileLocationUrl = getServletConfig().getInitParameter("fileLocationUrl");

		returnParameters += fileLocationUrl + "|";
		out.println(returnParameters);

}


}