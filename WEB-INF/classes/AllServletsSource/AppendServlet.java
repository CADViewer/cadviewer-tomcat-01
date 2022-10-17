
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class AppendServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("file");
		outPut.println("<html>");

		try
		{
			FileWriter fw = new FileWriter(option,true);  //the save will create a new file
			fw.write("... appending..");//appends the string to the file
			fw.close();
		}
		catch(IOException ioe)
		{
			outPut.println(ioe.toString());
		}


		outPut.println("call by doGet AppendServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String fileChunk = request.getParameter("file_content");

		fileName = fileName.trim();

		File fdf = null;
		String txtSource = "";
		byte[] bArr = null;
		FileOutputStream fileOut = null;
		String str;

		/*****        DEBUG
		fdf = File.createTempFile("AppendServlet-log",".txt");
		fileOut = new FileOutputStream( fdf );
		str= fdf.getPath();
		txtSource = "Append Servlet "+fileName +"   "+fileChunk+" \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		fileOut.close();
		DEBUG  *****/


		// open and append to file
		try
		{
			FileWriter fw = new FileWriter(fileName, true);  //the true will append the new data
			fw.write(fileChunk);//appends the string to the file
			fw.close();
		}
		catch(IOException ioe)
		{
			fdf = File.createTempFile("AppenServlet-log-err",".txt");
			fileOut = new FileOutputStream( fdf );
			str= fdf.getPath();
			txtSource = "Append Servlet "+ioe+"  \n";
			bArr = txtSource.getBytes();
			fileOut.write(bArr);
			fileOut.close();
		}

}

}