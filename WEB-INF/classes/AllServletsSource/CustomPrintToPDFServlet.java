
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CustomPrintToPDFServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
        String fullPath = request.getParameter("file");
        String param1Name = request.getParameter("param1_name");
        String param1Value = request.getParameter("param1_value");
        String paperSize = request.getParameter("paper_size");
        String paperOrientation = request.getParameter("paperOrientation");

		// here you have to fire off the converter and then send the pdf down to the customer

		// param1_name can be -model, -layout
		// param1_value is blank for -model

		//   ax2017 -i=fullPath -param1_name -f=pdf
		//   ax2017 -i=fullPath -param1_name=param1_value -f=pdf

        String param2Name = request.getParameter("param2_name");
        String param2Value = request.getParameter("param2_value");


		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();

		int exitCode = 0;   // the exit code when running AutoXchange
		String contentStreamData = "http://localhost:8080/cadviewer/converters/files/F25656888.pdf"; //the location of my converted file
		String contentLocation = fullPath;   // location of my originating file,  \ / changed to json syntax

//		String CVJSresponse =param1Name+" "+param1Value+"{\"completedAction\":\"pdf_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"file\",\"contentStreamData\":\""+contentStreamData+"\"}";
		String CVJSresponse ="{\"completedAction\":\"pdf_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"file\",\"contentStreamData\":\""+contentStreamData+"\"}";
		out.print(CVJSresponse);

}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String fullPath = request.getParameter("file");
        String param1Name = request.getParameter("param1_name");
        String param1Value = request.getParameter("param1_value");
        String paperSize = request.getParameter("paper_size");
        String paperOrientation = request.getParameter("paperOrientation");


        String param2Name = request.getParameter("param2_name");  // contains -greyscale if defined
        String param2Value = request.getParameter("param2_value");


		// here you have to fire off the converter and then send the pdf down to the customer

		// param1_name can be -model, -layout
		// param1_value is blank for -model

		//   ax2017 -i=fullPath -param1_name -f=pdf
		//   ax2017 -i=fullPath -param1_name=param1_value -f=pdf


		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();

		int exitCode = 0;   // the exit code when running AutoXchange
		String contentStreamData = "http://localhost:8080/cadviewer/converters/files/F25656888.pdf"; //the location of my converted file
		String contentLocation = fullPath;   // location of my originating file,  \ / changed to json syntax

//		String CVJSresponse =param1Name+" "+param1Value+"{\"completedAction\":\"pdf_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"file\",\"contentStreamData\":\""+contentStreamData+"\"}";
		String CVJSresponse =param1Name+" "+param1Value+"{\"completedAction\":\"pdf_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"file\",\"contentStreamData\":\""+contentStreamData+"\"}";
		out.print(CVJSresponse);

}



}