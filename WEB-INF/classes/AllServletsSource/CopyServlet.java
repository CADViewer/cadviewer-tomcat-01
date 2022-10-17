
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CopyServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("localfilename");
		outPut.println("<html>");
		try
		{
			FileWriter fw = new FileWriter(option,false);  //the save will create a new file
			fw.write("..creating...");//appends the string to the file
			fw.close();
		}
		catch(IOException ioe)
		{
			outPut.println(ioe.toString());
		}


		outPut.println("call by doGet CopyServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String localFileName = request.getParameter("localfilename");
        String localDestination = request.getParameter("localdestination");
				
		localFileName = localFileName.trim();
		localDestination = localDestination.trim();



		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(localFileName);
			os = new FileOutputStream(localDestination);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
		
/**		
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try {
			sourceChannel = new FileInputStream(localFileName).getChannel();
			destChannel = new FileOutputStream(localDestination).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		   }finally{
			   sourceChannel.close();
			   destChannel.close();
		}		
		
**/		
	
		
		File fdf = null;
		String txtSource = "";
		byte[] bArr = null;
		FileOutputStream fileOut = null;
		String str;

		String fileContent = "";		
		
	

		fdf = File.createTempFile("CopyServlet-log-err",".txt");
		fileOut = new FileOutputStream( fdf );
		str= fdf.getPath();
		txtSource = "Copy Servlet "+"we have copied"+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		fileOut.close();
		


}



}