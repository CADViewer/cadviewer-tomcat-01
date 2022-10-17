
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class getFileServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

	String ServerUrl = "";

	try {
			
		// 2022-10-03
		if (getServletConfig().getInitParameter("ServerUrl") != null)
			ServerUrl = getServletConfig().getInitParameter("ServerUrl");
		// 2022-1-03
		if (getServletConfig().getInitParameter("ServerUrl") != null){
			response.addHeader("Access-Control-Allow-Origin", ServerUrl);
		}
		else {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}

		response.addHeader("Access-Control-Allow-Methods", "GET");
//		response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Max-Age", "86400");
	} catch (Exception e) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("text/plain");
		response.getWriter().println(buildErrorMessage(e));
	} catch (Throwable e) {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("text/plain");
		response.getWriter().println(buildErrorMessage(e));
	}




	String fileTag = request.getParameter("fileTag");
	String fileType = request.getParameter("Type");
	String remainOnServer = "0";

	if (request.getParameter("remainOnServer")!=null);
		remainOnServer = request.getParameter("remainOnServer");

	String fileLocation = "";

	if (getServletConfig().getInitParameter("fileLocation") != null)
				fileLocation = getServletConfig().getInitParameter("fileLocation");

				
			
	// OWASP
	if (oWaspCheck(fileTag, ServerUrl) || oWaspCheck(fileType, ServerUrl) || oWaspCheck(remainOnServer, ServerUrl)){
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();
		out.write("Illegal input");
		return;
	}


				
// 2019-05-03


	File fdf = null;
	FileOutputStream fileOut = null;
		// get the content in bytes

	byte[] contentInBytes;
	String stringContent;
	boolean param_read_from_file = false;
	boolean cvjs_debug = false;


	// we make it so that it will attempt to load from xml file in any case
	if (true) {

	
		if (cvjs_debug == false){
			fdf = File.createTempFile("getFileServlet-log-err",".txt");
			fileOut = new FileOutputStream( fdf );
		}

		String relativeWebPath = "./cvjs_config_servlets.xml";
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		
		stringContent = "absoluteDiskPath  "+absoluteDiskPath+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);

		
		
		stringContent = "getServletConfig().getInitParameter(fileLocation)  "+getServletConfig().getInitParameter("fileLocation")+ "ServerUrl="+ServerUrl +"  \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);
		
		
		try{	


			File config_file = new File(absoluteDiskPath);
			InputStream input_config = new FileInputStream(config_file);
			
	//		String xml_result = IOUtils.toString(input_config, StandardCharsets.UTF_8);
		
			Scanner s = new Scanner(input_config).useDelimiter("\\A");
			String xml_result = s.hasNext() ? s.next() : "";

		
			stringContent = "\n\r xml_result  "+xml_result+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
			
			
			// here we have to parse the string and determine servlet name, parameters, etc. 
			
			String[] servlet_list = xml_result.split("<servlet>"); 

			stringContent = "\n\r A servlet_list.length  "+servlet_list.length+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);

			
			for (int j=0; j<servlet_list.length; j++){
			
				// find servlet name

				//stringContent = "\n\r B servlet_list[j]="+servlet_list[j]+"XX \n\r";
				//contentInBytes	= stringContent.getBytes();
				//fileOut.write(contentInBytes);
				
				try{				
					String servlet_name = 	servlet_list[j].substring(servlet_list[j].indexOf("<servlet-name>")+14);	

					//stringContent = "\n\r servlet_name  "+servlet_name+" \n\r";
					//contentInBytes	= stringContent.getBytes();
					//fileOut.write(contentInBytes);

					servlet_name =  servlet_name.substring(0, servlet_name.indexOf("</servlet-name>"));
					
					
					stringContent = "\n\r servlet_name="+servlet_name+" \n\r";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
					
					if (servlet_name.indexOf("getFileServlet")==0){
						// we have the right servlet, here we split for the parameters
												
						String[] param_list = servlet_list[j].split("<init-param>");
						for (int i=0; i<param_list.length; i++){
						
							String param_pair = param_list[i];

							try{
							
								String param_name = param_pair.substring(param_pair.indexOf("<param-name>")+12);	
								String param_value = param_pair.substring(param_pair.indexOf("<param-value>")+13);	
							
								param_name =  param_name.substring(0, param_name.indexOf("</param-name>"));
								param_value =  param_value.substring(0, param_value.indexOf("</param-value>"));

								stringContent = "\n\r param_name:"+param_name+" \n\r";
								contentInBytes	= stringContent.getBytes();
								fileOut.write(contentInBytes);
								
								stringContent = "\n\r param_value:"+param_value+" \n\r";
								contentInBytes	= stringContent.getBytes();
								fileOut.write(contentInBytes);
	
								param_read_from_file = true;
	
								if (param_name.indexOf("fileLocation")==0 && param_name.length()==12  ){
									fileLocation = param_value;
								}	
									
									
							}
							catch (Exception inner_e2)
							{
//								stringContent = "Exception inner2:"+inner_e2+" \n\r";
//								contentInBytes	= stringContent.getBytes();
//								fileOut.write(contentInBytes);
							}
					
						}
											
					}

				 }
				catch (Exception inner_e)
				{
//					stringContent = "Exception inner:"+inner_e+" \n\r";
//					contentInBytes	= stringContent.getBytes();
//					fileOut.write(contentInBytes);
				}
				
			}
				
		}
		catch (Exception e)
		{
			stringContent = "Here we continue the loop - Exception:"+e+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
	
	}
				
				
		String returnFile = fileLocation + fileTag + "." + fileType;

		stringContent = "Loading this returnfile location:"+returnFile+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);


		String fileContent = readFile(returnFile);


//		stringContent = "The Content is:"+fileContent+" \n\r";
//		contentInBytes	= stringContent.getBytes();
//		fileOut.write(contentInBytes);


		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
//		response.setContentType("image/svg+xml");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();

//		out.print("XXX"+remainOnServer+"XXX"+(remainOnServer.indexOf("0")));

		// delete svg file if remainonserver is zero
		if (remainOnServer.indexOf("0")>-1){
			File xx = new File(returnFile);
			if (xx.exists()) {
			   xx.delete();
			}
		}

		out.print(fileContent);

}


public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileTag = request.getParameter("fileTag");
        String fileType = request.getParameter("Type");
        String remainOnServer = "0";

        if (request.getParameter("remainOnServer")!=null);
        	remainOnServer = request.getParameter("remainOnServer");

		String fileLocation = "";

		if (getServletConfig().getInitParameter("fileLocation") != null)
					fileLocation = getServletConfig().getInitParameter("fileLocation");

				
		var ServerUrl = "";
		if (getServletConfig().getInitParameter("ServerUrl") != null)
			ServerUrl = getServletConfig().getInitParameter("ServerUrl");
		
		// OWASP
		if (oWaspCheck(fileTag, ServerUrl) || oWaspCheck(fileType, ServerUrl) || oWaspCheck(remainOnServer, ServerUrl)){
			response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
			PrintWriter out = response.getWriter();
			out.write("Illegal input");
			return;
		}



// 2019-05-03


	File fdf = null;
	FileOutputStream fileOut = null;
		// get the content in bytes

	byte[] contentInBytes;
	String stringContent;
	boolean param_read_from_file = false;
	boolean cvjs_debug = false;


	// we make it so that it will attempt to load from xml file in any case
	if (true) {

	
		if (cvjs_debug == false){
			fdf = File.createTempFile("getFileServlet-log-err",".txt");
			fileOut = new FileOutputStream( fdf );
		}

		String relativeWebPath = "./cvjs_config_servlets.xml";
		String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
		
		stringContent = "absoluteDiskPath  "+absoluteDiskPath+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);

		
		
		stringContent = "getServletConfig().getInitParameter(ServerLocation)  "+getServletConfig().getInitParameter("ServerLocation")+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);
		
		
		try{	


			File config_file = new File(absoluteDiskPath);
			InputStream input_config = new FileInputStream(config_file);
			
	//		String xml_result = IOUtils.toString(input_config, StandardCharsets.UTF_8);
		
			Scanner s = new Scanner(input_config).useDelimiter("\\A");
			String xml_result = s.hasNext() ? s.next() : "";

		
			stringContent = "\n\r xml_result  "+xml_result+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
			
			
			// here we have to parse the string and determine servlet name, parameters, etc. 
			
			String[] servlet_list = xml_result.split("<servlet>"); 

			stringContent = "\n\r A servlet_list.length  "+servlet_list.length+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);

			
			for (int j=0; j<servlet_list.length; j++){
			
				// find servlet name

				//stringContent = "\n\r B servlet_list[j]="+servlet_list[j]+"XX \n\r";
				//contentInBytes	= stringContent.getBytes();
				//fileOut.write(contentInBytes);
				
				try{				
					String servlet_name = 	servlet_list[j].substring(servlet_list[j].indexOf("<servlet-name>")+14);	

					//stringContent = "\n\r servlet_name  "+servlet_name+" \n\r";
					//contentInBytes	= stringContent.getBytes();
					//fileOut.write(contentInBytes);

					servlet_name =  servlet_name.substring(0, servlet_name.indexOf("</servlet-name>"));
					
					
					stringContent = "\n\r servlet_name="+servlet_name+" \n\r";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
					
					if (servlet_name.indexOf("getFileServlet")==0){
						// we have the right servlet, here we split for the parameters
												
						String[] param_list = servlet_list[j].split("<init-param>");
						for (int i=0; i<param_list.length; i++){
						
							String param_pair = param_list[i];

							try{
							
								String param_name = param_pair.substring(param_pair.indexOf("<param-name>")+12);	
								String param_value = param_pair.substring(param_pair.indexOf("<param-value>")+13);	
							
								param_name =  param_name.substring(0, param_name.indexOf("</param-name>"));
								param_value =  param_value.substring(0, param_value.indexOf("</param-value>"));

								stringContent = "\n\r param_name:"+param_name+" \n\r";
								contentInBytes	= stringContent.getBytes();
								fileOut.write(contentInBytes);
								
								stringContent = "\n\r param_value:"+param_value+" \n\r";
								contentInBytes	= stringContent.getBytes();
								fileOut.write(contentInBytes);
	
								param_read_from_file = true;
	
								if (param_name.indexOf("fileLocation")==0 && param_name.length()==12  ){
									fileLocation = param_value;
								}	
									
									
							}
							catch (Exception inner_e2)
							{
//								stringContent = "Exception inner2:"+inner_e2+" \n\r";
//								contentInBytes	= stringContent.getBytes();
//								fileOut.write(contentInBytes);
							}
					


					
						}
						
										
											
					}

				 }
				catch (Exception inner_e)
				{
//					stringContent = "Exception inner:"+inner_e+" \n\r";
//					contentInBytes	= stringContent.getBytes();
//					fileOut.write(contentInBytes);
				}

				
			}
			
				
		}
		catch (Exception e)
		{
			stringContent = "Here we continue the loop - Exception:"+e+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
	
	}


									
				
		String returnFile = fileLocation + fileTag + "." + fileType;


		stringContent = "Loading this returnfile location:"+returnFile+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);



		String fileContent = readFile(returnFile);


//		stringContent = "The Content is:"+fileContent+" \n\r";
//		contentInBytes	= stringContent.getBytes();
//		fileOut.write(contentInBytes);



//		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		response.setContentType("image/svg+xml");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();



//		out.print("XXX"+remainOnServer+"XXX"+(remainOnServer.indexOf("0")));


		// delete svg file if remainonserver is zero
		if (remainOnServer.indexOf("0")>-1){
			File xx = new File(returnFile);
			if (xx.exists()) {
			   xx.delete();
			}
		}

		out.print(fileContent);

//		out.flush();
//		out.close();
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
        content = new String(chars);
        reader.close();

    }catch (Exception e) {
        e.printStackTrace();
    }
    return content;
}





private static String buildErrorMessage(Exception e) {
    String msg = e.toString() + "\r\n";

    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
        msg += "\t" + stackTraceElement.toString() + "\r\n";
    }

     return msg;
}

private static String buildErrorMessage(Throwable e) {
    String msg = e.toString() + "\r\n";

    for (StackTraceElement stackTraceElement : e.getStackTrace()) {
        msg += "\t" + stackTraceElement.toString() + "\r\n";
    }

    return msg;
}

public boolean oWaspCheck(String parameter, String ServerUrl){

	var errorFlag = false;
	try{

		// OWASP attack prevention			
		var newparameter = java.net.URLDecoder.decode(parameter, StandardCharsets.UTF_8.name());	
		if (newparameter.indexOf("http:")>-1 || newparameter.indexOf("https:")>-1){
			// this is http / https
			if (newparameter.indexOf(ServerUrl)==0){
				// part of the same server is OK!	
			}
			else{
				String error = "Illegal input";
				errorFlag = true;	
			}
		}
	// OWASP attack prevention			
		if (parameter.indexOf("..")>-1 || parameter.indexOf("%2e%2e")>-1){
			String error = "Illegal input";
			errorFlag = true;
		}
		// OWASP attack prevention			
		if (parameter.indexOf(".ini")>-1 || parameter.indexOf("system.")>-1) {
			String error = "Illegal input";
			errorFlag = true;
		}
	
		// OWASP attack prevention			
		if (parameter.indexOf("onerror")>-1 || parameter.indexOf("prompt%28%29%")>-1) {
			String error = "Illegal input";
			errorFlag = true;
		}
	
		return(errorFlag);
	
	

	} catch (Exception e) {
		return(errorFlag);
	} 



}






}