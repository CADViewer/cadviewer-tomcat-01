
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;


public class callApiConversionServlet extends HttpServlet {

/**				
    public static final int DWG_OUTPUT_FORMAT = 1;
    public static final int DXF_OUTPUT_FORMAT = 2;
    public static final int DWF_OUTPUT_FORMAT = 3;
    public static final int PDF_OUTPUT_FORMAT = 4;
    public static final int SVG_OUTPUT_FORMAT = 6;
    public static final int JPG_OUTPUT_FORMAT = 9;
    public static final int GIF_OUTPUT_FORMAT = 10;
    public static final int PNG_OUTPUT_FORMAT = 11;

    public static final String DXF_FORMAT = "dxf";
    public static final String SVG_FORMAT = "svg";
    public static final String PNG_FORMAT = "png";
    public static final String JPG_FORMAT = "jpg";
    public static final String GIF_FORMAT = "gif";



public class AxDllConvert{

	public native int InitDll() throws Error;
    public native int UninitDll() throws Error;
    public native int Shaker(String shake) throws Error;
    public native int SetInputName(String input) throws Error;
    public native int SetOutputName(String output) throws Error;
    public native int SetInputNameUnicode(String input) throws Error;
    public native int SetOutputNameUnicode(String output) throws Error;
    public native int SetInputDirectory(String input) throws Error;
    public native int SetOutputDirectory(String output) throws Error;
    public native int SetQuietMode();
    public native int SetBlackAndWhite();
    public native int SetViewMode(int ViewMode);
    public native int SetLastSavedView();
    public native int SetPaperSpace();
    public native int SetModelSpace();
    public native int SetVersion(int ver);
    public native int SetInputFormat(int fmt);
    public native int SetOutputFormat(int fmt);
    public native int SetDrawingHeight(long hgt);
    public native int SetDrawingWidth(long wid);
    public native int SetEncoding(int enc);
    public native int SetAutoTrim(int trim);
    public native int Convert();
    public native int SaveAsDxf(int version);
    public native int OpenInputFile();
    public native int ZoomToExtents();
    public native boolean FileIsOpen();
    public native int CloseInputFile();
    public native int GetNumberOfLayouts();
    public native String GetLayoutName(int iLayout);
    public native int GetNumberOfNamedViews();
    public native String GetViewName(int iView);
    public native int GetNumberOfLayers();
    public native String GetLayerName(int iLayer);
    public native boolean GetLayerStatus(int iLayer);
    public native int SetLayerStatus(int iLayer, boolean Stat);
				

	public String AX_DLL = "axConverter";
    public static final  String SHAKER = "ShakeIt";
    private static final String LOAD_ERROR_MSG =  "Error while loading the AX2017 image conversion library.";


//	public static final String AX_DLL = "axConverter";
//    public String SHAKER = "ShakeIt";
//    public String LOAD_ERROR_MSG =  "Error while loading the AX2017 image conversion library.";



	
public AxDllConvert(String AX_DLL) throws Exception
{
	try {
//		System.loadLibrary(AX_DLL);
		System.load(AX_DLL);
	} catch ( UnsatisfiedLinkError ulError ) {
		throw new Exception(AxDllConvert.LOAD_ERROR_MSG +
				" (" + ulError.getMessage() + ").");
	} catch ( Exception ex ) {
		throw new Exception(AxDllConvert.LOAD_ERROR_MSG +
				" (" + ex.getMessage() + ").");
	}
}

}

**/
	

public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("option");
		outPut.println("<html>");
		outPut.println("call by doGet LoadRedlinesServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	int remainOnServer = 1;

	String fileLocation = "";
	String fileLocationUrl = "";
	String converterLocation = "";
	String ax2020_executable = "";
	String licenseLocation = "";
	String xpathLocation = "";
	String callbackMethod = "";
	
	String ServerLocation = "";
	String ServerUrl = "";
		
	boolean cvjs_debug = false;

	File fdf = null;
	FileOutputStream fileOut = null;
		// get the content in bytes

	byte[] contentInBytes;
	String stringContent;


	// new 2091-04-01
	if (getServletConfig().getInitParameter("ServerLocation") != null)
		ServerLocation = getServletConfig().getInitParameter("ServerLocation");

	if (getServletConfig().getInitParameter("ServerUrl") != null)
		ServerUrl = getServletConfig().getInitParameter("ServerUrl");
	// new 2091-04-01

	if (getServletConfig().getInitParameter("fileLocation") != null)
		fileLocation = getServletConfig().getInitParameter("fileLocation");

	if (getServletConfig().getInitParameter("fileLocationUrl") != null)
		fileLocationUrl = getServletConfig().getInitParameter("fileLocationUrl");

	if (getServletConfig().getInitParameter("converterLocation") != null)
		converterLocation = getServletConfig().getInitParameter("converterLocation");

	if (getServletConfig().getInitParameter("ax2020_executable") != null)
		ax2020_executable = getServletConfig().getInitParameter("ax2020_executable");

	if (getServletConfig().getInitParameter("licenseLocation") != null)
		licenseLocation = getServletConfig().getInitParameter("licenseLocation");

	if (getServletConfig().getInitParameter("xpathLocation") != null)
		xpathLocation = getServletConfig().getInitParameter("xpathLocation");

	if (getServletConfig().getInitParameter("callbackMethod") != null)
		callbackMethod = getServletConfig().getInitParameter("callbackMethod");

	boolean param_read_from_file = false;



	try {
		//response.addHeader("Access-Control-Allow-Origin", "*");
		// 2022-1-03
		if (getServletConfig().getInitParameter("ServerUrl") != null)
			response.addHeader("Access-Control-Allow-Origin", ServerUrl);
		else 
			response.addHeader("Access-Control-Allow-Origin", "*");
		

		response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
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
	









	//We make a log file in every case!						
	fdf = File.createTempFile("ConversionServlet-log_",".txt");
	fileOut = new FileOutputStream( fdf );

	
	if (!param_read_from_file && getServletConfig().getInitParameter("cvjs_debug") != null){
	
		if ((getServletConfig().getInitParameter("cvjs_debug").indexOf("true"))==0){
			cvjs_debug = true;


//	fdf = File.createTempFile("ConversionServlet-log_",".txt");
//	fileOut = new FileOutputStream( fdf );

			
			stringContent = "ConversionServlet-log-err cvjs_debug = true   "+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
	
		}
		else{
			cvjs_debug = false;
//			out.println("false branch");
		}	
	}

	

	if (cvjs_debug) 
		remainOnServer = 1;
	else 
		remainOnServer = 0;

		

	// In case there are no web-xml parameters, they all have to be loaded from a configuration file	
//	if (getServletConfig().getInitParameter("ServerLocation") == null){
	
	
	
	// we make it so that it will attempt to load from xml file in any case
	if (true) {

	
//		if (cvjs_debug == false){
//			fdf = File.createTempFile("ConversionServlet-log_",".txt");
//			fileOut = new FileOutputStream( fdf );
//		}

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
					
					if (servlet_name.indexOf("callApiConversionServlet")==0){
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
	
								if (param_name.indexOf("ServerLocation")==0 && param_name.length()==14){
									ServerLocation = param_value;

//									stringContent = "\n\rTEST  ServerLocation:"+ServerLocation+" \n\r";
//									contentInBytes	= stringContent.getBytes();
//									fileOut.write(contentInBytes);
									
								}	
									
								if (param_name.indexOf("ServerUrl")==0 && param_name.length()==9  )
									ServerUrl = param_value;
								
								if (param_name.indexOf("fileLocation")==0 && param_name.length()==12)
									fileLocation = param_value;

								if (param_name.indexOf("fileLocationUrl")==0 && param_name.length()==15)
									fileLocationUrl = param_value;

								
								if (param_name.indexOf("converterLocation")==0 && param_name.length()==17)
									converterLocation = param_value;
								
								if (param_name.indexOf("ax2020_executable")==0 && param_name.length()==17)
									ax2020_executable = param_value;
								
								if (param_name.indexOf("licenseLocation")==0 && param_name.length()==15)
									licenseLocation = param_value;
								
								if (param_name.indexOf("xpathLocation")==0 && param_name.length()==13)
									xpathLocation = param_value;
								
								if (param_name.indexOf("callbackMethod")==0 && param_name.length()==14)
									callbackMethod = param_value;
								
								if (param_name.indexOf("cvjs_debug")==0 && param_name.length()==10){
									
									if (param_value.indexOf("true")==0)
										cvjs_debug = true;
									if (param_value.indexOf("false")==0)
										cvjs_debug = false;
									

								}
									
							}
							catch (Exception inner_e2)
							{
//								stringContent = "Exception inner2:"+inner_e2+" \n\r";
//								contentInBytes	= stringContent.getBytes();
//								fileOut.write(contentInBytes);
							}
					


					
						}
						
						
						stringContent = "\n\rServerLocation:"+ServerLocation+" \n\r";
						contentInBytes	= stringContent.getBytes();
						fileOut.write(contentInBytes);


						stringContent = "\n\r cvjs_debug:"+cvjs_debug+" \n\r";
						contentInBytes	= stringContent.getBytes();
						fileOut.write(contentInBytes);
											
						
											
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

	
	
	String myRequest = request.getParameter("request");


	// {"action":"svg_creation","converter":"AutoXchange AX2015","version":"V2.00","contentType":"file","contentLocation":"http%3A%2F%2Flocalhost%3A8080%2Fcadviewer_2_6_0%2Fdrawings%2Fdemo%2Ffloorplans%2F1st%20floor%20architectural.dwg%0A","contentFormat":"DWG","contentUsername":"","contentPassword":"","userLabel":"fromCADViewerJS","contentResponse":"stream","leaveStreamOnServer":0,"parameters":[{"paramName":"f","paramValue":"svg"},{"paramName":"model","paramValue":""},{"paramName":"extents","paramValue":""}]}

	response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	PrintWriter out = response.getWriter();


	
			
	// we add the ability to load a configuration file from //  cadviewer/content/drawings/viewing/%EA%B5%AC%EC%A1%B0%EB%AC%BC%EB%8F%84/%EB%8C%80%EC%82%AC%EA%B3%A8_000128_%EB%8C%80%EC%82%AC%EA%B3%A82%EB%B0%B0%EC%88%98%ED%86%B5%EA%B4%80	



/****   WE BLOCK OUT THE SECURITY MANAGER FOR NOW

	try
	{
	  MySecurityManager secMgr = new MySecurityManager(true);
		// Set the security manager
		stringContent = "Before Setting Security Manager "+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);

		System.setSecurityManager(secMgr);

		stringContent = "After Setting Security Manager "+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);
		
	 }
	catch (SecurityException excp)
	{

		stringContent = "Can't Change the SecurityManager! "+" \n\r";
		contentInBytes	= stringContent.getBytes();
		fileOut.write(contentInBytes);
	}

****/



	//("XX "+(getServletConfig().getInitParameter("cvjs_debug"))+"  "+cvjs_debug+" HELLO 2 ");
	
	
    try {

		// determine source file
		String contentLocation = myRequest.substring(myRequest.indexOf("contentLocation")+18);

		if (cvjs_debug){		
			stringContent = contentLocation+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
	

		contentLocation = contentLocation.substring(0,contentLocation.indexOf('\"'));
		contentLocation = contentLocation.trim();
		//out.println("1 contentLocation "+contentLocation);

		// determine parameters
		String parameters = myRequest.substring(myRequest.indexOf("parameters")+12);
		parameters = parameters.substring(0,parameters.indexOf(']'));
		
		if (cvjs_debug){		
			stringContent = "1 parameters "+parameters;
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
		
		String countString = parameters;
		int paramCount = 0;
		while (countString.indexOf("paramName")>-1){
			countString = countString.substring(countString.indexOf("paramName")+11);
			paramCount++;
		}

		if (cvjs_debug){		
			stringContent = " parameters "+paramCount+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}


	  	String[] param_name = new String[paramCount];
		String[] param_value = new String[paramCount];

		paramCount = 0;
		while (parameters.indexOf("paramName")>-1){

			String string1 = parameters.substring(parameters.indexOf("paramName")+12);


		if (cvjs_debug){		
			stringContent = string1+" ";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}


			string1 = string1.substring(0,string1.indexOf("\""));
			
			
		if (cvjs_debug){		
			stringContent = "param "+string1+"XXX"+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
			
				
			
			param_name[paramCount] = string1;

			string1 = parameters.substring(parameters.indexOf("paramValue")+13);
		if (cvjs_debug){		
			stringContent = "param "+string1+" ";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
			string1 = string1.substring(0,string1.indexOf("\""));
		if (cvjs_debug){		
			stringContent = "param "+string1+"YYY";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
			param_value[paramCount] = string1;

			parameters = parameters.substring(parameters.indexOf("paramValue")+13);
			paramCount++;
		}

		// determine temporary filename
		Random randomGenerator = new Random();
	  	int randomInt = randomGenerator.nextInt(1000000);
		String tempFileName = "F"+randomInt;
		String writeTemp =  (URLDecoder.decode(contentLocation, "UTF-8")).trim();

		if (cvjs_debug){		
			stringContent = "writeTemp "+writeTemp+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}


		String writeFile = fileLocation + tempFileName + "." + writeTemp.substring(writeTemp.lastIndexOf(".")+1);

		if (cvjs_debug){		
			stringContent = "writeFile "+writeFile+" \n\r";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}


		// move file to temporary array

		if (contentLocation.indexOf("http")==0){  // URL

		
		// 2018-10-12   CH Korean file:   http://192.168.10.78:8080/cadviewer/content/drawings/viewing/%EA%B5%AC%EC%A1%B0%EB%AC%BC%EB%8F%84/%EB%8C%80%EC%82%AC%EA%B3%A8_000128_%EB%8C%80%EC%82%AC%EA%B3%A82%EB%B0%B0%EC%88%98%ED%86%B5%EA%B4%80.dwg
		//  if HTTP or HTTPS, we should actually not decode , this should only be for server side files. 
				
			contentLocation = URLDecoder.decode(contentLocation, "UTF-8");
			contentLocation = contentLocation.trim();
//			contentLocation = URLEncoder.encode(contentLocation, "UTF-8");

			// new 2019-04-01
			
			if (contentLocation.indexOf(ServerUrl)==0){
			// if contentLocation is the same server as we are on
			// then we make the writeFile the parameter server location

			// we have an url on this server	
				
				contentLocation =  contentLocation.replace(ServerUrl, ServerLocation);			
				writeFile = contentLocation;
				
			}
			else{ // else standard loop
			

				while (contentLocation.indexOf(" ")>-1){
					contentLocation = contentLocation.substring(0,contentLocation.indexOf(" "))+"%20"+contentLocation.substring(contentLocation.indexOf(" ")+1);
				}

				
				if (cvjs_debug){		
					stringContent = "URL decoded + %20 "+contentLocation+" \n\r";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
				}


				URL url = new URL(contentLocation);
				InputStream intemp = url.openStream();
				FileOutputStream outtemp = new FileOutputStream(writeFile);
				int counter = 0;
				int c = 0;
				do {
					c = intemp.read();
					counter++;
					//System.out.println("==============> " + c);
					if (c !=-1) {
						outtemp.write((byte) c);
					}
				} while(c != -1);
	//			out.println("the buffer counter is "+counter);
				intemp.close();
				outtemp.close();
			
			}
			

			}else{ // Flat file

			contentLocation = URLDecoder.decode(contentLocation, "UTF-8");
			contentLocation = contentLocation.trim();


			
			
			if (cvjs_debug){		
				stringContent = "flat file 2 contentLocation "+contentLocation+" \n\r";
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
			}

			
		
			// new 2019-04-01
			// we are on the same server, so we simply just read in the file
			// this is needed to get the xrefs in the originating folder
			writeFile = contentLocation;

			if (false){ // new 2019-04-01
				InputStream in = new FileInputStream(contentLocation);
				OutputStream outfile = new FileOutputStream(writeFile);

				// Copy the bits from instream to outstream
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					outfile.write(buf, 0, len);
				}
				in.close();
				outfile.close();

			}
			

		}
		
		// NOW the file has been written to:
		boolean linux = true;
		if (linux){
/**
		
			Path path = Paths.get(writeFile);
			if (!Files.exists(path)) Files.createFile(path);
			Set<PosixFilePermission> perms = Files.readAttributes(path,PosixFileAttributes.class).permissions();

			perms.add(PosixFilePermission.OWNER_WRITE);
			perms.add(PosixFilePermission.OWNER_READ);
			perms.add(PosixFilePermission.OWNER_EXECUTE);
			perms.add(PosixFilePermission.GROUP_WRITE);
			perms.add(PosixFilePermission.GROUP_READ);
			perms.add(PosixFilePermission.GROUP_EXECUTE);
			perms.add(PosixFilePermission.OTHERS_WRITE);
			perms.add(PosixFilePermission.OTHERS_READ);
			perms.add(PosixFilePermission.OTHERS_EXECUTE);
			Files.setPosixFilePermissions(path, perms);

   		// we have set the permission to the file on Server
//		try{
			Path path2 = Paths.get(converterLocation+ax2020_executable);
			if (!Files.exists(path2)) Files.createFile(path2);
			Set<PosixFilePermission> perms2 = Files.readAttributes(path2,PosixFileAttributes.class).permissions();
	  
			perms2.add(PosixFilePermission.OWNER_WRITE);
			perms2.add(PosixFilePermission.OWNER_READ);
			perms2.add(PosixFilePermission.OWNER_EXECUTE);
			perms2.add(PosixFilePermission.GROUP_WRITE);
			perms2.add(PosixFilePermission.GROUP_READ);
			perms2.add(PosixFilePermission.GROUP_EXECUTE);
			perms2.add(PosixFilePermission.OTHERS_WRITE);
			perms2.add(PosixFilePermission.OTHERS_READ);
			perms2.add(PosixFilePermission.OTHERS_EXECUTE);
			Files.setPosixFilePermissions(path2, perms2);

//		}
//		catch (Exception ee){	
//		}
**/			
		}
		
		
			
		// let us build a command line
		//String commandLine = converterLocation+ax2020_executable+" -i="+writeFile+" -o="+ fileLocation + tempFileName + ".svg -f=svg -model";


		// let us determine the output format

		String outputFormat = "svg";

		//NOTE: !!!! fpath was creating font setting issue

		for (int i=0; i<paramCount; i++){
			if (param_name[i].indexOf("f")==0 && param_name[i].length()==1 ){
				outputFormat = param_value[i];
			}
		}



		if (cvjs_debug){		
			stringContent = outputFormat;
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}



	  	String[] envp = new String[1];
//		String[] str_arr = new String[4+paramCount+1];   // include trace

//		String[] str_arr = new String[4+paramCount];   		
		String[] str_arr; // = new String[4+paramCount];   
		
		// 2019-07-01 
		String os_name= java.lang.System.getProperty("os.name");
		os_name = os_name.toLowerCase();
		boolean os_flag = false;
		if (os_name.indexOf("windows")>-1){
			os_flag = true;    // we are on windows 
		}	
		
		
		if (cvjs_debug){		
			stringContent = "\n os_name " + os_name+"  os_flag="+os_flag;
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}

		
		// CADViewer handles UNICODE  6.2.66
		os_flag = false;
		// NOTE NOTE NOTE
		
		
//			str_arr = new String[5+paramCount];
		str_arr = new String[4+paramCount];

//			str_arr[0] =  converterLocation+"run_ax2020.bat";
		str_arr[0] =  converterLocation+ax2020_executable;
		
		
// 2022-01-25		
//		str_arr[1] =  "\"-i="+writeFile+"\"";
//		str_arr[2] =  "\"-o="+ fileLocation + tempFileName + "."+outputFormat+"\"";
//		str_arr[3] =  "\"-lpath="+ licenseLocation + "\"";


		// 22-09-30
		// whitespaces to %20
		writeFile = writeFile.replaceAll("\\s","%20");
		// 2022-10-24
		fileLocation = fileLocation.replaceAll("\\s","%20");
		licenseLocation = licenseLocation.replaceAll("\\s","%20");

		

		str_arr[1] =  "-i=\""+writeFile+"\"";
		str_arr[2] =  "-o=\""+ fileLocation + tempFileName + "."+outputFormat+"\"";
		str_arr[3] =  "-lpath=\""+ licenseLocation + "\"";


		if (cvjs_debug){		
			stringContent = "\n str_arr[0] " + str_arr[0]+" "+str_arr[1]+" "+str_arr[2]+" "+str_arr[3]+" ";
			contentInBytes	= stringContent.getBytes();
			fileOut.write(contentInBytes);
		}
		
		for (int i=0; i<paramCount; i++){
						
			if (param_value[i].length() == 0){
				str_arr[4+i] = "-"+param_name[i];
		
				if (cvjs_debug){		
					stringContent = str_arr[4+i]+" ";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
				}

			}
			else{
				
				if (param_name[i].indexOf("layout")!=-1){
					param_value[i] = param_value[i].replace("\\\\","\\");						

					if (cvjs_debug){		
						stringContent = "\n WE HAVE LAYOUT  "+param_value[i];
						contentInBytes	= stringContent.getBytes();
						fileOut.write(contentInBytes);
					}
				}
//				str_arr[4+i] = "\"-"+param_name[i]+"="+param_value[i]+"\"";

				if (param_name[i].indexOf("f")==0 && param_name[i].length() == 1){
					str_arr[4+i] = "-"+param_name[i]+"="+param_value[i];
				}
				else{
					str_arr[4+i] = "-"+param_name[i]+"=\""+param_value[i]+"\"";
				}

				// 
				str_arr[4+i] = str_arr[4+i].replaceAll("\\s","%20");



				if (cvjs_debug){		
					stringContent = str_arr[4+i]+" ";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
				}
			}
		}

		int exitCode = -1;
		
		try{

			for (int i=0; i<paramCount+4; i++){

				if (cvjs_debug){		
					stringContent = " \n\r "+i+"= "+ str_arr[i]+"\n ";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
				}
				
			}

	
			ProcessBuilder pb = new ProcessBuilder(str_arr);
			pb.command(str_arr);


			if (cvjs_debug){		
				stringContent = " \n\r  after pc.command  \n ";
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
			}			

			 
			Map<String, String> env = pb.environment();
//			 env.put("VAR1", "myValue");
//			 env.remove("OTHERVAR");
//			 env.put("VAR2", env.get("VAR1") + "suffix");
			pb.directory(new File(converterLocation));

			 
			if (cvjs_debug){		
				stringContent = " \n\r  before pb.start  \n ";
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
			}
			 
			Process p = pb.start();	
			 //InputStream shellIn = p.getInputStream();		 
			 //exitCode = p.exitValue();

			 
			if (cvjs_debug){		
				stringContent = " \n\r  before p.waitfor  \n ";
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
			}
			 
			 
			if (cvjs_debug){		

				BufferedReader stdInput = new BufferedReader(new 
					 InputStreamReader(p.getInputStream()));

				BufferedReader stdError = new BufferedReader(new 
					 InputStreamReader(p.getErrorStream()));

				if (cvjs_debug){		
					stringContent = " \n\r AutoXchange Command Line - Output of the execution: \n ";
					contentInBytes	= stringContent.getBytes();
					fileOut.write(contentInBytes);
				}
				
				String s = null;
				boolean flag1 = true;
				int counter = 0;

				while ( flag1  &&   ((s = stdInput.readLine()) != null) ) {
					//counter++;
					//if (counter == 30) flag1 = false;
					contentInBytes	= s.getBytes();
					fileOut.write(contentInBytes);
				}

				// read any errors from the attempted command
				stringContent = " \n\r AutoXchange Command Line - Output of standard error of the command (if any):\n ";
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
				flag1 = true;
				counter = 0;

				while (flag1 && ((s = stdError.readLine()) != null) ) {
					//counter++; 
					//if (counter == 10) flag1 = false;
					contentInBytes	= s.getBytes();
					fileOut.write(contentInBytes);
				}									 
			}
	
			exitCode = p.waitFor();
		}
		catch(Exception ee)
		{

			if (cvjs_debug){		
				stringContent = " /n "+ee;;
				contentInBytes	= stringContent.getBytes();
				fileOut.write(contentInBytes);
			}
					
		}

		// delete temp dwg file
		if (true){
			File xx = new File(writeFile);
			if (xx.exists()) {
//			   xx.delete();
			}
		}


		while (contentLocation.indexOf("\\")>-1){
			contentLocation = contentLocation.substring(0,contentLocation.indexOf('\\'))+"/"+contentLocation.substring(contentLocation.indexOf('\\')+1);
		}


		// compose callback message

		if (outputFormat.toLowerCase().indexOf("svg")>-1){
			String CVJSresponse = "{\"completedAction\":\"svg_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"stream\",\"contentStreamData\":\""+callbackMethod+"?remainOnServer="+remainOnServer+"&fileTag="+tempFileName+"&Type=svg\"}";

			// send callback message and terminate
			out.print(CVJSresponse);
			out.flush();
			out.close();
		}
		else{
			if (outputFormat.toLowerCase().indexOf("pdf")>-1){

				String CVJSresponse = "{\"completedAction\":\"pdf_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2019\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"file\",\"contentStreamData\":\""+fileLocationUrl+tempFileName+"."+outputFormat+"\"}";
//				String CVJSresponse = "{\"completedAction\":\"svg_creation\",\"errorCode\":\"E"+exitCode+"\",\"converter\":\"AutoXchange AX2017\",\"version\":\"V1.00\",\"userLabel\":\"fromCADViewerJS\",\"contentLocation\":\""+contentLocation+"\",\"contentResponse\":\"stream\",\"contentStreamData\":\""+callbackMethod+"?remainOnServer=0&fileTag="+tempFileName+"&Type=svg\"}";
				// send callback message and terminate
				out.print(CVJSresponse);
				out.flush();
				out.close();
			}
		}

		
		
		if (cvjs_debug){		
			fileOut.close();
		}
		
		
    }catch (Exception e) {

		String txt = e.toString();
		out.println("We got an exception here!"+txt);

    }

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





}


