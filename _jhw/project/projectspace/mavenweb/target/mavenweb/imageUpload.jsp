<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>


<%
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   //String filePath = context.getInitParameter("file-upload");
   
   String filePath = config.getServletContext().getRealPath("/upload/");   
   System.out.println("realPath :"+filePath);
   File temporaryDir = new File(config.getServletContext().getRealPath("/temp/"));
   System.out.println("temporaryDir :"+temporaryDir);

   // Verify the content type

   String contentType = request.getContentType();

   if (contentType!=null && (contentType.indexOf("multipart/form-data") >= 0)) {

      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      //factory.setRepository(new File("c:\\temp"));
      factory.setRepository(temporaryDir);
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 

         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);
         System.out.println("fileItems.size() :"+fileItems.size());


         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         out.println("<html>");
         out.println("<head>");
         out.println("<title>JSP File upload</title>");  
         out.println("</head>");
         out.println("<body>");

         while ( i.hasNext () ) 
         {
        	System.out.println("aaaaaaaaaaaa");
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () ){

            	// Get the uploaded file parameters
            	String fieldName = fi.getFieldName();
            	System.out.println("@@  fieldName : "+fieldName);
            	String fileName = fi.getName();
            	System.out.println("@@  fileName : "+fileName);
            	System.out.println("@@ : "+fileName.lastIndexOf("\\")+1);
            	boolean isInMemory = fi.isInMemory();
            	long sizeInBytes = fi.getSize();
         
            	// Write the file
            	if( fileName.lastIndexOf("\\") >= 0 ){
            		file = new File( filePath + 
            		fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            	}
	            fi.write( file ) ;
	            out.println("Uploaded Filename: " + filePath + 
	            fileName + "<br>");
            }else{
            	System.out.println("bbbbbbb");
            }
         }
         out.println("</body>");
         out.println("</html>");
      }catch(Exception ex) {
         System.out.println(ex);
      }
   }else{
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
%>

<html>
<head>
<title>File Uploading Form</title>
</head>
<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action="imageUpload.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="fileeeee" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
</body>
</html>


