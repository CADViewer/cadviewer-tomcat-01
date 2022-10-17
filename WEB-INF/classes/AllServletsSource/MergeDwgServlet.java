
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



public class MergeDwgServlet extends HttpServlet {


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


		outPut.println("call by doGet MergDwgServlet");
		outPut.println("</html>");
		outPut.flush();
		outPut.close();

	//System.out.println("AppendServlet: first in doGet");
}

public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String baseFile = request.getParameter("base_file");
		String mergeFile = request.getParameter("merge_file");
		String outFile = request.getParameter("out_file");
		String zip_output = "false";
		String QR_code = "false";

		
		File fdf = null;
		String txtSource = "";
		byte[] bArr = null;
		FileOutputStream fileOut = null;
		String str;
				
		fdf = File.createTempFile("MergeDwgServlet-log-err",".txt");
		fileOut = new FileOutputStream( fdf );
		

		try{
			zip_output = request.getParameter("zip_output");
			QR_code = request.getParameter("QR_code");
		}
		catch(Exception ee){
			
			txtSource = "get parameters zip_output/QR_code: "+ee+"  \n";
			bArr = txtSource.getBytes();
			fileOut.write(bArr);
		
		}
		
		
		baseFile = baseFile.trim();
		mergeFile = mergeFile.trim();		
		outFile = outFile.trim();


		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		PrintWriter out = response.getWriter();

		
		String dwgmergeLocation = "";
		String dwgmerge2020_executable = "";
			
		if (getServletConfig().getInitParameter("dwgmergeLocation") != null)
			dwgmergeLocation = getServletConfig().getInitParameter("dwgmergeLocation");

		if (getServletConfig().getInitParameter("dwgmerge2020_executable") != null)
			dwgmerge2020_executable = getServletConfig().getInitParameter("dwgmerge2020_executable");
	
		String executable = dwgmergeLocation + '/' + dwgmerge2020_executable;

		
		String[] str_arr = new String[6];   
		str_arr[0] =  executable;
		
			
		str_arr[1] =  "-base=\""+ baseFile+"\"";
		str_arr[2] =  "-merge=\""+ mergeFile+"\"";
		str_arr[3] =  "-out=\""+ outFile + "\"";
		str_arr[4] =  "-over";
		str_arr[5] =  "-lpath=\""+dwgmergeLocation+"\"";

	
		str_arr[1] =  "-base="+ baseFile; 
		str_arr[2] =  "-merge="+ mergeFile; 
		str_arr[3] =  "-out="+ outFile;  
		str_arr[4] =  "-over";
		str_arr[5] =  "-lpath="+dwgmergeLocation; 

		
		
		txtSource = "0 "+str_arr[0]+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		
		txtSource = "1 "+str_arr[1]+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		
		txtSource = "2 "+str_arr[2]+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		
		txtSource = "3 "+str_arr[3]+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		
		txtSource = "4 "+str_arr[4]+"  \n";
		bArr = txtSource.getBytes();
		fileOut.write(bArr);
		
		
		int exitCode = -1;
		
		try{
			
			ProcessBuilder pb = new ProcessBuilder(str_arr);
			pb.command(str_arr);
			 
			Map<String, String> env = pb.environment();
			pb.directory(new File(dwgmergeLocation));

			Process p = pb.start();		 
			exitCode = p.waitFor();
				
			out.print(exitCode);
			
			if (zip_output.equals("true")){  // we have to zip compress the result

				String zip_file_name = outFile.substring(outFile.lastIndexOf("/")+1);
				zip_file_name = zip_file_name.substring(0, zip_file_name.lastIndexOf("."));
				zip_file_name = zip_file_name + ".zip";
				
				String zip_file = outFile.substring(0, outFile.lastIndexOf("."))+".zip";
				
				FileOutputStream fos = new FileOutputStream(zip_file);
				ZipOutputStream zos = new ZipOutputStream(fos);
				
			
				if (QR_code.equals("true")){  // This is a QR code, so we zip compress the QR-code bitmap and the result dwg

			    // we identify the name of the QR_code jpg	
					String QR_code_file = mergeFile.substring(0, mergeFile.lastIndexOf("."))+".png";
										
					txtSource = "QR_code_file: "+QR_code_file+"  \n";
					bArr = txtSource.getBytes();
					fileOut.write(bArr);
					
					addToZipFile(QR_code_file, zos);

					txtSource = "outFile: "+outFile+"  \n";
					bArr = txtSource.getBytes();
					fileOut.write(bArr);

					addToZipFile(outFile, zos);
	
					zos.close();
					fos.close();
					
					
					// now we delete all non used files.
										
					File file = new File(outFile);
					file.delete();
					
					file = new File(mergeFile);
					file.delete();
					
					file = new File(QR_code_file);
					file.delete();
					
				}
				else{  // this is a standard zip, so only zip the result file.


					txtSource = "single file zip outFile: "+outFile+"  \n";
					bArr = txtSource.getBytes();
					fileOut.write(bArr);

					addToZipFile(outFile, zos);

					zos.close();
					fos.close();

					// now we delete all non used files.										
					File file = new File(outFile);
					file.delete();
					
					file = new File(mergeFile);
					file.delete();
					
				}
				
				// we set callback to the new zip file-name
				String myCustomOutputFile = "";
				myCustomOutputFile = "<customoutfilename>http://"+zip_file_name+"</customoutfilename>";
				out.print(myCustomOutputFile);

				txtSource = "custom: "+myCustomOutputFile+"  \n";
				bArr = txtSource.getBytes();
				fileOut.write(bArr);

			
			}
			else{ // no zip compress
				// we set callback to the new file-name
				String myCustomOutputFile = "";
				myCustomOutputFile = "<customoutfilename>"+outFile.substring(outFile.lastIndexOf("/")+1)+"</customoutfilename>";
				out.print(myCustomOutputFile);

				txtSource = "custom: "+myCustomOutputFile+"  \n";
				bArr = txtSource.getBytes();
				fileOut.write(bArr);
			}

			
		}
		catch(Exception e)
		{
		
			txtSource = "Merge Servlet "+e+"  \n";
			bArr = txtSource.getBytes();
			fileOut.write(bArr);
			fileOut.close();
		}

			out.flush();
			out.close();		

}



public void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

	File file = new File(fileName);
	FileInputStream fis = new FileInputStream(file);
	
	ZipEntry zipEntry = new ZipEntry(fileName.substring(fileName.lastIndexOf("/")+1));  // 2019-07-08
	zos.putNextEntry(zipEntry);

	byte[] bytes = new byte[1024];
	int length;
	while ((length = fis.read(bytes)) >= 0) {
		zos.write(bytes, 0, length);
	}

	zos.closeEntry();
	fis.close();
}




}