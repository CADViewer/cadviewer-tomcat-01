
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetQRCodeServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("file");
		
		
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = request.getParameter("file");

		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();
		
		// based on the file name the implementer can send back what QR code there has to be in the drawing
		
		// <no_QR_code_available>	
		
		// here we just return the file-name which is then written as QR code to the file
		out.write(fileName);

		
        // if we want to return a predefined QR file and embed this file into the drawing we use
//        out.write(fileName+"<use_as_QR_code_bitmap>");

		

}




}