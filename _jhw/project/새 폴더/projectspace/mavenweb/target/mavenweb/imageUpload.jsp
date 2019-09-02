<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.io.output.*"%>
<%@ page import="kr.or.connect.review.Rev"%>
<%@ page import="kr.or.connect.review.RevDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	File file;
	int maxFileSize = 5000 * 1024;
	int maxMemSize = 5000 * 1024;
	ServletContext context = pageContext.getServletContext();

	String place = "mcdonald";
	String userId;
	int grade;
	String opinion;
	String imagePath;

	// String filePath = context.getInitParameter("file-upload");

	String filePath = config.getServletContext().getRealPath("/upload/");
	System.out.println("realPath :" + filePath);
	File temporaryDir = new File(config.getServletContext().getRealPath("/temp/"));
	System.out.println("temporaryDir :" + temporaryDir);

	// Verify the content type
	String contentType = request.getContentType();

	if (contentType != null && (contentType.indexOf("multipart/form-data") >= 0)) {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		// factory.setRepository(new File("c:\\temp"));
		factory.setRepository(temporaryDir);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {

			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			System.out.println("fileItems.size() :" + fileItems.size());

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>JSP File upload</title>");
			out.println("</head>");
			out.println("<body>");

			RevDAO dao = RevDAO.getInstance();
			Rev rev = new Rev();
			rev.setPlace(place);

			while (i.hasNext()) {
				System.out.println("aaaaaaaaaaaa");
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {

					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					System.out.println("@@  fieldName : " + fieldName);
					String fileName = fi.getName();
					System.out.println("@@  fileName : " + fileName);
					System.out.println("@@ : " + fileName.lastIndexOf("\\") + 1);
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					} else {
						file = new File(filePath + fileName);
					}

					fi.write(file);
					out.println("Uploaded Filename: " + filePath + fileName + "<br>");
					imagePath = place + "/" + fileName;
					rev.setImagePath(imagePath);

				} else {
					switch (fi.getFieldName()) {
					case "userId":
						userId = fi.getString();
						System.out.println(userId);
						rev.setUserId(userId);
						break;
					case "grade":
						grade = Integer.parseInt(fi.getString());
						System.out.println(grade);
						rev.setGrade(grade);
						break;
					case "opinion":
						opinion = fi.getString();
						System.out.println(opinion);
						rev.setOpinion(opinion);
						break;
					default:
						break;
					}

					System.out.println("bbbbbbb");
				}
			}
			out.println("</body>");
			out.println("</html>");
			dao.addRev(rev);

		} catch (Exception ex) {
			System.out.println(ex);
		}

		/*
		 * RevDAO dao = RevDAO.getInstance(); Rev rev = new
		 * Rev(place,userId,grade,opinion); dao.addRev(rev);
		 */

	} else {

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
	Select a file to upload:
	<br />
	<form action="imageUpload.jsp" method="post"
		enctype="multipart/form-data">
		<input type="file" name="fileeeee" size="50" /> <input type="text"
			name="userId" /> <input type="number" name="grade" /> <input
			type="text" name="opinion" /> <br /> <input type="submit"
			value="Upload File" />
	</form>
</body>
</html>


