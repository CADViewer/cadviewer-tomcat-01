// File Name SendEmail.java
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.URLDecoder;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.internet.MimeMessage;
 
public class MergeEmailServlet extends HttpServlet {

   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
   }

   
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
		// the pdf_file and pdf_file_name can be either pdf or dwg!
		String dwg_pdf_file = request.getParameter("pdf_file");
		String pdf_file_name = request.getParameter("pdf_file_name");
		String from_name = request.getParameter("from_name");
		String from_mail = request.getParameter("from_mail");
		String cc_mail = request.getParameter("cc_mail");
		String replyto = request.getParameter("replyto");
		String to_mail = request.getParameter("to_mail");
		String mail_title = request.getParameter("mail_title");
		String mail_message = request.getParameter("mail_message");	  
	  
		String host = "";
		String port = "";
		String auth = "";
	  
	  
	  

      try {


		  // Recipient's email ID needs to be mentioned.
		  //String to = "abcd@gmail.com";
	 
		  // Sender's email ID needs to be mentioned
		  //String from = "web@gmail.com";
	 
		  // Assuming you are sending email from localhost
		  //String host = "localhost";

		  // Get system properties
		  Properties properties = System.getProperties();

			
		  if (getServletConfig().getInitParameter("mail_smtp_host") != null)
				host = getServletConfig().getInitParameter("mail_smtp_host");

		  if (getServletConfig().getInitParameter("mail_smtp_port") != null)
				port = getServletConfig().getInitParameter("mail_smtp_port");

		   if (getServletConfig().getInitParameter("mail_smtp_host") != null)
				auth = getServletConfig().getInitParameter("mail_smtp_auth");
		  
		  // Setup mail server
		  properties.setProperty("mail.smtp.host", host);
		  properties.setProperty("mail.smtp.port", port);  


		  Session session;	  
		  
		  // Set response content type
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();	  
			  
			  
		  //out.println(host+"  "+port+"  "+auth);  	

		  if ( auth.indexOf("true")>-1){

			//out.println("1  session TRUE part! ");
		  
			properties.setProperty("mail.smtp.auth", auth); 
			Authenticator authticate = new SMTPAuthenticator();
			// Get the default Session object.
			session = Session.getDefaultInstance(properties, authticate); 
			
			//out.println(" session TRUE part! ");

		  }
		  else{
			session = Session.getDefaultInstance(properties);


			//out.println(" session NOT TRUE part! ");
			

		 }
		  		
		  //out.println(" after session ");
		  
				  
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
 
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from_mail));
 
         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_mail));
         message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc_mail));
	
		 InternetAddress[] addresses = InternetAddress.parse(replyto);
		 message.setReplyTo(addresses);
 

         // Set Subject: header field
         message.setSubject(mail_title);
 
 
          // Now set the actual message
         message.setText(mail_message);

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();
 
         // Fill the message
         messageBodyPart.setText(mail_message);
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();
 
         // Set text message part
         multipart.addBodyPart(messageBodyPart);
 
         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = decode(dwg_pdf_file);
		 
		 		 
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(pdf_file_name);
         multipart.addBodyPart(messageBodyPart);
 
         // Send the complete message parts
         message.setContent(multipart );
  
         // Send message
         Transport.send(message);
 
		 String title = "Send Email";
         String res = "Sent message successfully....";
         //String docType =
         //"<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
		 
         //out.println("OK sent...");
       
		 
      } catch (MessagingException mex) {

		File fdf = null;
		String txtSource = "";
		byte[] bArr = null;
		FileOutputStream fileOut = null;
		String str;
	  
		fdf = File.createTempFile("MergeEmailServlet-log-err",".txt");
		fileOut = new FileOutputStream( fdf );
		  
		txtSource = "Merge Servlet "+mex+"  \n";
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
   
   
   
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "myusername"; 
           String password = "mypassword";
		  		
		  if (getServletConfig().getInitParameter("username") != null)
				username = getServletConfig().getInitParameter("username");
		   
		  if (getServletConfig().getInitParameter("password") != null)
				password = getServletConfig().getInitParameter("password");
		      
		   
           return new PasswordAuthentication(username, password);
        }
    }   
   
 
} 



