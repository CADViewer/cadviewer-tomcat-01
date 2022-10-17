
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Base64;

public class SaveServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("file");
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


		outPut.println("call by doGet SaveServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String fileChunk = request.getParameter("file_content");

		String base64 = "0";
		
		boolean base64_flag = false;



		File fdf = null;
		String txtSource = "";
		byte[] bArr = null;
		FileOutputStream fileOut = null;
		String str;


		byte[] contentInBytes;
		String stringContent;

		
		fdf = File.createTempFile("SaveServlet-log-err",".txt");
		fileOut = new FileOutputStream( fdf );
		
		stringContent = "SaveServlet-log-err cvjs_debug = true   "+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);		
		
		

		
		try
		{
			base64 = request.getParameter("base64");

			base64_flag = base64.equals("1");
			
		}
		catch(Exception e){
			
			stringContent = "error "+e+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);		

			
		}
		
		
		stringContent = "After "+base64+"   "+base64_flag+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);		
		
				
		
		fileName = fileName.trim();


		
		
		
		/*****        DEBUG

		String current = new java.io.File( "." ).getCanonicalPath();

		// test
		fdf = File.createTempFile("SaveServlet-log",".txt");
		fileOut = new FileOutputStream( fdf );
		str= fdf.getPath();
		txtSource = "Save Servlet     "+current+"  "+fileName +"  "+fileChunk+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		fileOut.close();
		// I have to hardcode my current directory as I cannot get Java to understand where I am
		//String mycurrentDir = "C:\\xampp\\tomcat\\webapps\\CV-JS_2_3\\WEB-INF\\classes\\";

		DEBUG  *****/


		// open and write to file
		try
		{			

			File path = new File(decode(fileName.substring(0, fileName.lastIndexOf("."))));
			if (!path.exists()) {
				path.mkdirs();
			}

			// if a base64 we first decode it, this is a qr bitmap
			if (base64_flag){


			
				FileOutputStream fos = new FileOutputStream(decode(fileName));
				byte[] decoded = Base64.getDecoder().decode(fileChunk);
				fos.write(decoded);
				fos.close();
			}
			else{  // standard case, just save the chunk
								
				
				FileWriter fw = new FileWriter(decode(fileName),false);  //the save will create a new file
	//			FileWriter fw = new FileWriter(fileName,false);  //the save will create a new file
				fw.write(fileChunk);//appends the string to the file
				fw.close();
				
			}
	
			
		}
		catch(IOException ioe)
		{

			fdf = File.createTempFile("SaveServlet-log-err",".txt");
			fileOut = new FileOutputStream( fdf );
			str= fdf.getPath();
			txtSource = "Save Servlet "+ioe+"  \n";
			bArr = txtSource.getBytes();
			fileOut.write(bArr);
			fileOut.close();
		}


}




      public static String decode(String url)  

      {  

                try {  

                     String prevURL="";  

                     String decodeURL=url;  

                     while(!prevURL.equals(decodeURL))  

                     {  

                          prevURL=decodeURL;  

                          decodeURL=URLDecoder.decode( decodeURL, "UTF-8" );  

                     }  

                     return decodeURL;  

                } catch (UnsupportedEncodingException e) {  

                     return "Issue while decoding" +e.getMessage();  

                }  

      }




}