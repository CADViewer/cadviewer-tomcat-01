
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;

public class LoadServlet extends HttpServlet {




public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

	int bufferSize = 1024;
	char[] buffer = new char[bufferSize];


	var serverUrl = "";
	if (getServletConfig().getInitParameter("ServerUrl") != null){
		serverUrl = getServletConfig().getInitParameter("ServerUrl");
	}

	
	String csp = getServletConfig().getInitParameter("csp");
	HttpServletResponse httpResponse = (HttpServletResponse)response;		
	httpResponse.addHeader("Content-Security-Policy", csp);



// PrintWriter out = response.getWriter();
// OutputStream outputstream=response.getOutputStream();

        String fileName = request.getParameter("file");
        fileName =  fileName.trim();

		if (fileName.indexOf(".png")>0){
			response.setContentType("image/png");
		}
		else{
			if (fileName.indexOf("jpg")>0){
				response.setContentType("image/jpeg");
			} 
			else{
				if (fileName.indexOf("jpeg")>0){
					response.setContentType("image/jpeg");
				} 
				else{
					if (fileName.indexOf("gif")>0) 
						response.setContentType("image/gif");
					else{
						if (fileName.indexOf("txt")>0) 
							response.setContentType("text/plain");
						else
							response.setContentType("APPLICATION/OCTET-STREAM");	
				}
			}
		}

		//PrintWriter out = response.getWriter();
		OutputStream outputstream=response.getOutputStream();

		boolean cvjs_debug = true; 
		File fdf = null;
		FileOutputStream fileOut = null;

		byte[] contentInBytes;
		String stringContent;
		StringBuilder outstreamreader = new StringBuilder();


		//We make a log file in every case!						
		fdf = File.createTempFile("LoadServlet-log_",".txt");
		fileOut = new FileOutputStream( fdf);
		
		if (cvjs_debug){
			stringContent = " /n "+fileName + " .png "+(fileName.indexOf(".png")>0);
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}

		try{
/*

			Reader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
			int charsRead;
			while((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
				outstreamreader.append(buffer, 0, charsRead);
			}
			stringContent = outstreamreader.toString();			
			contentInBytes	= stringContent.getBytes("UTF-8");
			outputstream.write(contentInBytes);

		
			if (cvjs_debug){
				stringContent = outstreamreader.toString();			
				contentInBytes	= stringContent.getBytes("UTF-8");
				fileOut.write(contentInBytes);	
			}		
*/

			var errorFlag = false;
			// OWASP attack prevention			
			var newFilename = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());	
			if (newFilename.indexOf("http:")>-1 || newFilename.indexOf("https:")>-1){
				// this is http / https
				if (newFilename.indexOf(serverUrl)==0){
					// part of the same server is OK!	
				}
				else{
					String error = "Illegal input";
					outputstream.write(error.getBytes("UTF-8"));
					errorFlag = true;	
				}
			}
			// OWASP attack prevention			
			if (fileName.indexOf("..")>-1 || fileName.indexOf("%2e%2e")>-1){
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (fileName.indexOf(".ini")>-1 || fileName.indexOf("system.")>-1) {
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}

			// OWASP attack prevention			
			if (fileName.indexOf("onerror")>-1 || fileName.indexOf("prompt%28%29%")>-1) {
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}




			if (!errorFlag){


				if (fileName.indexOf("http")==0){  // URL


					URL url = new URL(fileName);
					InputStream intemp = url.openStream();
	
					byte[] mybuffer = new byte[1024 * 8];
					int j = -1;
					while ((j = intemp.read(mybuffer)) != -1) {
						outputstream.write(mybuffer, 0, j);
					}
	
	
					/*
	
					URL url = new URL(fileName);
					InputStream intemp = url.openStream();
					int counter = 0;
					int c = 0;
					do {
						c = intemp.read();
						counter++;
						//System.out.println("==============> " + c);
						if (c !=-1) {
							out.write((byte) c);
						}
					} while(c != -1);
	
					*/
				}
				else{
	
					/*
					String fileContent = readFile(fileName);
					out.print(fileContent);
					*/
				
					Reader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
					int charsRead;
					while((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
						outstreamreader.append(buffer, 0, charsRead);
					}
					stringContent = outstreamreader.toString();			
					contentInBytes	= stringContent.getBytes("UTF-8");
					outputstream.write(contentInBytes);
	
				
					if (cvjs_debug){
						stringContent = outstreamreader.toString();			
						contentInBytes	= stringContent.getBytes("UTF-8");
						fileOut.write(contentInBytes);	
					}
	
				
				}
	
	
			}

			fileOut.close();
		}
		catch (Exception e){
			String error = "Error "+e;
			outputstream.write(error.getBytes("UTF-8"));
		}

	}

}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = request.getParameter("file");
        fileName =  fileName.trim();


		var serverUrl = "";
		if (getServletConfig().getInitParameter("ServerUrl") != null)
		serverUrl = getServletConfig().getInitParameter("ServerUrl");



		String csp = getServletConfig().getInitParameter("csp");
		HttpServletResponse httpResponse = (HttpServletResponse)response;		
		httpResponse.addHeader("Content-Security-Policy", csp);


		boolean cvjs_debug = true; 
		File fdf = null;
		FileOutputStream fileOut = null;
		byte[] contentInBytes;
		String stringContent;


		int bufferSize = 1024;
		char[] buffer = new char[bufferSize];
		StringBuilder outstreamreader = new StringBuilder();
	
		//We make a log file in every case!						
		fdf = File.createTempFile("LoadServlet-log_",".txt");
		fileOut = new FileOutputStream( fdf);
		
		if (cvjs_debug){
			stringContent = " /n "+fileName + " .png "+(fileName.indexOf(".png")>0);
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}


		// 6.9.06 - update

		if (fileName.indexOf(".png")>0){
			response.setContentType("image/png");
		}
		else{
			if (fileName.indexOf("jpg")>0){
				response.setContentType("image/jpeg");
			} 
			else{
				if (fileName.indexOf("jpeg")>0){
					response.setContentType("image/jpeg");
				} 
				else{
					if (fileName.indexOf("gif")>0) 
						response.setContentType("image/gif");
					else
						response.setContentType("APPLICATION/OCTET-STREAM");	
				}
			}
		}


		//PrintWriter out = response.getWriter();
		OutputStream outputstream=response.getOutputStream();


		if (cvjs_debug){
			stringContent = " /n "+fileName;
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}

		try{


			var errorFlag = false;
			// OWASP attack prevention
			var newFilename = java.net.URLDecoder.decode(fileName, StandardCharsets.UTF_8.name());	
			if (newFilename.indexOf("http:")>-1 || newFilename.indexOf("https:")>-1){
				// this is http / https
				if (newFilename.indexOf(serverUrl)==0){
					// part of the same server is OK!	
				}
				else{
					String error = "Illegal input";
					outputstream.write(error.getBytes("UTF-8"));
					errorFlag = true;	
				}
			}
			// OWASP attack prevention			
			if (fileName.indexOf("..")>-1 || fileName.indexOf("%2e%2e")>-1){
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}
			// OWASP attack prevention			
			if (fileName.indexOf(".ini")>-1 || fileName.indexOf("system.")>-1) {
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}

			// OWASP attack prevention			
			if (fileName.indexOf("onerror")>-1 || fileName.indexOf("prompt%28%29%")>-1) {
				String error = "Illegal input";
				outputstream.write(error.getBytes("UTF-8"));
				errorFlag = true;
			}



			if (!errorFlag){



				if (fileName.indexOf("http")==0){  // URL

					URL url = new URL(fileName);
					InputStream intemp = url.openStream();
	
	
					byte[] mybuffer = new byte[1024 * 8];
					int j = -1;
					while ((j = intemp.read(mybuffer)) != -1) {
						outputstream.write(mybuffer, 0, j);
					}
	
	
					/*
	
					int counter = 0;
					int c = 0;
					do {
						c = intemp.read();
						counter++;
						//System.out.println("==============> " + c);
						if (c !=-1) {
							out.write((byte) c);
						}
					} while(c != -1);
					*/
				}
				else{
				
					Reader in = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
					int charsRead;
					while((charsRead = in.read(buffer, 0, buffer.length)) > 0) {
						outstreamreader.append(buffer, 0, charsRead);
					}
					stringContent = outstreamreader.toString();			
					contentInBytes	= stringContent.getBytes("UTF-8");
					outputstream.write(contentInBytes);
	
				
					if (cvjs_debug){
						stringContent = outstreamreader.toString();			
						contentInBytes	= stringContent.getBytes("UTF-8");
						fileOut.write(contentInBytes);	
					}
				
	
				}
	



			}


						
		fileOut.close();
		
		}
		catch (Exception e){
			//out.print("null");
			String error = "Error "+e;
			outputstream.write(error.getBytes("UTF-8"));
		}
}


private final String UTF8_CHARSET = StandardCharsets.UTF_8.name();

public static final String UTF_8 = StandardCharsets.UTF_8.name();

String decodeUTF8(byte[] bytes) {

	String defaultstring = "";
	try{
		return new String(bytes, UTF8_CHARSET);
	}
	catch(Exception e){
		return defaultstring;
	}


}

byte[] encodeUTF8(String string) {
	byte[] bytes = null;
	try{
		return string.getBytes(UTF8_CHARSET);
	}
	catch(Exception e){
		return bytes;
	}
}

public byte[] readByteArray(String filename)
{
	byte[] bytes = null;
    File file = new File(filename); //for ex foo.txt
    FileReader reader = null;
    try {
        reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
		bytes = toBytes(chars);

    }catch (Exception e) {
        e.printStackTrace();
    }
    return bytes;
}


public String readFile(String filename)
{
    String content = null;
    File file = new File(filename); //for ex foo.txt
    FileReader reader = null;
    try {
        reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
		byte[] bytes = toBytes(chars);
        content = new String(bytes, UTF_8);
        reader.close();

    }catch (Exception e) {
        e.printStackTrace();
    }
    return content;
}


byte[] toBytes(char[] chars) {

	byte[] bytes = new byte[0];
	try{
		bytes =	new String(chars).getBytes(StandardCharsets.UTF_8);
	}
	catch(Exception e){
        e.printStackTrace();	
	}
/* 
  CharBuffer charBuffer = CharBuffer.wrap(chars);
  //ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
  ByteBuffer byteBuffer = (StandardCharsets.UTF_8.name()).encode(charBuffer);
  
 
  byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
            byteBuffer.position(), byteBuffer.limit());
  Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
  
 */
  return bytes;
}


}