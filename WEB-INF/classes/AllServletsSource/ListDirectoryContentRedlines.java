
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ListDirectoryContentRedlines extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

        String dirPath = request.getParameter("directory");
		PrintWriter outPut = response.getWriter();

		String csp = getServletConfig().getInitParameter("csp");
		HttpServletResponse httpResponse = (HttpServletResponse)response;		
		httpResponse.addHeader("Content-Security-Policy", csp);


		try{

			var errorFlag = false;
			// OWASP attack prevention			
			if (dirPath.indexOf("http:")>-1 || dirPath.indexOf("https:")>-1){
				outPut.println("Illegal input");
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (dirPath.indexOf("..")>-1 || dirPath.indexOf("%2e%2e")>-1){
				outPut.println("Illegal input");
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (dirPath.indexOf(".ini")>-1 || dirPath.indexOf("system.")>-1) {
				outPut.println("Illegal input");
				errorFlag = true;
			}

			// OWASP attack prevention			
			if (dirPath.indexOf(":")>-1 ) {
				outPut.println("Illegal input");
				errorFlag = true;
			}


			if (!errorFlag){

				File dir = new File(dirPath);
				String[] files = dir.list();
				if (files.length == 0) {
					System.out.println("The directory is empty");
				} else {
					for (String aFile : files) {
	
						if ((aFile.indexOf(".js")>0))
							outPut.println("<br>"+aFile);
					}
				}
	
			}


		}
		catch(Exception e){

				outPut.println("Directory Listing  exception: "+e);

		}
		outPut.close();
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String dirPath = request.getParameter("directory");
		PrintWriter outPut = response.getWriter();

		String csp = getServletConfig().getInitParameter("csp");
		HttpServletResponse httpResponse = (HttpServletResponse)response;		
		httpResponse.addHeader("Content-Security-Policy", csp);


		try{


			var errorFlag = false;
			// OWASP attack prevention			
			if (dirPath.indexOf("http:")>-1 || dirPath.indexOf("https:")>-1 || dirPath.indexOf("https%3A")>-1 || dirPath.indexOf("http%3A")>-1){
				outPut.println("Illegal input");
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (dirPath.indexOf("..")>-1 || dirPath.indexOf("%2e%2e")>-1){
				outPut.println("Illegal input");
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (dirPath.indexOf(".ini")>-1 || dirPath.indexOf("system.")>-1) {
				outPut.println("Illegal input");
				errorFlag = true;
			}

			// OWASP attack prevention			
			if (dirPath.indexOf(":")>-1 ) {
				outPut.println("Illegal input");
				errorFlag = true;
			}


			if (!errorFlag){

				File dir = new File(dirPath);
				String[] files = dir.list();
				if (files.length == 0) {
					System.out.println("The directory is empty");
				} else {
					for (String aFile : files) {

						if (aFile.indexOf(".js")>0)
							outPut.println("<br>"+aFile);
					}
				}

			}

		}
		catch(Exception e){

				outPut.println("Directory Listing  exception: "+e);

		}
		outPut.close();

}

}