
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ConvertPDFServlet extends HttpServlet {


public final void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{

		PrintWriter outPut = response.getWriter();
		String option = request.getParameter("option");
		outPut.println("<html>");
		outPut.println("call by doGet ConvertPDFServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pdfFile = request.getParameter("pdf_file");
        String tempDir = request.getParameter("temp_dir");
        String fileName = request.getParameter("file_name");

        String requestType = request.getParameter("request_type");
        String customFolder = request.getParameter("custom_folder");

		
		boolean debug = false;
		
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
//	    response.setCharacterEncoding("UTF-8"); //
//	    response.getWriter().write(fileContent);

//		response.setContentType("text/html");
		PrintWriter out = response.getWriter();



		// find the right parameters
		String fileLocation = "";
		if (getServletConfig().getInitParameter("fileLocation") != null)
	    	fileLocation = getServletConfig().getInitParameter("fileLocation");

		String pdfConverterFolder = "";
		if (getServletConfig().getInitParameter("pdfConverterFolder") != null)
	    	pdfConverterFolder  = getServletConfig().getInitParameter("pdfConverterFolder");

		String pdfBatchExecutable = "";
		if (getServletConfig().getInitParameter("pdfBatchExecutable") != null)
	    	pdfBatchExecutable = getServletConfig().getInitParameter("pdfBatchExecutable");


		String pdfGetPagesExecutable = "";
		if (getServletConfig().getInitParameter("pdfGetPagesExecutable") != null)
	    	pdfGetPagesExecutable = getServletConfig().getInitParameter("pdfGetPagesExecutable");
		
		
		String javaFolder = "";
		if (getServletConfig().getInitParameter("javaFolder") != null)
	    	javaFolder = getServletConfig().getInitParameter("javaFolder");

		String batikFolder = "";
		if (getServletConfig().getInitParameter("batikFolder") != null)
	    	batikFolder = getServletConfig().getInitParameter("batikFolder");

		String batikVersion = "";
		if (getServletConfig().getInitParameter("batikVersion") != null)
	    	batikVersion = getServletConfig().getInitParameter("batikVersion");

		String pdfboxFolder = "";
		if (getServletConfig().getInitParameter("pdfboxFolder") != null)
	    	pdfboxFolder = getServletConfig().getInitParameter("pdfboxFolder");

		String pdfboxVersion = "";
		if (getServletConfig().getInitParameter("pdfboxVersion") != null)
	    	pdfboxVersion = getServletConfig().getInitParameter("pdfboxVersion");

		String output_directory = fileLocation  + tempDir;
		
	// if we have customFolder set to 1, we check if the folder exists, then we get the number of pages and return
	// because it is already pre-converted in the folder
		
		
		if (debug) out.println("In servlet requestType "+requestType+"  customFolder "+customFolder);
		
		
		if (requestType.indexOf("pages")==0 && customFolder.indexOf("1")==0){
		
			try {
		
				File file = new File(output_directory);
				if (file.exists()) {
		
					String fileContent = readFile(output_directory + "/Pages.txt");	
					
					if (fileContent==null){
							// there is no content, just continue to the conversion
					
					}
					else{
						out.print(fileContent+" 1");
						return ;	
					}
				}
			
			}
			catch (Exception e){
				
				out.print("Exception "+e+"YYY");
				
				
			}
		
		}
		
	
		// we got two modes, full conversion or find pages number, convert first page and return
		String batch_executable = pdfConverterFolder + "/"+ pdfBatchExecutable;

		if (requestType.indexOf("pages")==0)
			batch_executable = pdfConverterFolder + "/" + pdfGetPagesExecutable;


		if (debug) out.println(" requestType "+requestType+" batch_executable"+batch_executable+"XXXXX");

		

		// echo !file_exists($output_directory);
		File file = new File(output_directory);
		if (!file.exists()) {
			if (file.mkdir()) {
				 if (debug) out.println("Directory is created!");
			} else {
				if (debug) out.println("Failed to create directory!");
			}
		}
		
		
//	$cp_command_line = $batch_executable . ' '. $pdfConverterFolder . ' '. $javaFolder . ' '. $batikFolder . ' '. $batikVersion . ' '. $pdfboxFolder . ' '. $pdfboxVersion . ' '. $output_directory . ' "'. $pdfFile . '" '. $fileName ;	
		String cp_command_line = batch_executable + " \"" + pdfConverterFolder + "\" \"" + javaFolder + "\" \"" + batikFolder + "\" \"" + batikVersion + "\" \"" + pdfboxFolder + "\" \"" + pdfboxVersion + "\" \"" + output_directory + "\" \"" +  pdfFile + "\" \"" +  fileName +"\" ";

//		String cp_command_line = batch_executable + " " + pdfConverterFolder + " " + javaFolder + " " + batikFolder + " " + batikVersion + " " + pdfboxFolder + " " + pdfboxVersion + " " + output_directory + " " +  pdfFile + " " +  fileName +" ";

		if (debug) out.println("before runtime XXX"+cp_command_line+"XXXXX");

		try{

			Runtime runtime = Runtime.getRuntime();
			Process process = null;

			process = runtime.exec(cp_command_line);

			process.waitFor();

		//	out.println("after waitfor :"+cp_command_line);

		}
		catch (Exception e){

			out.println(e);

		}
	
	
		if (requestType.indexOf("pages")==0){
		
			file = new File(output_directory);
			if (file.exists()) {
	
				String fileContent = readFile(output_directory + "/Pages.txt");	
				out.print(fileContent+" 0");
				return ;	
			}
		
		}
	
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




}