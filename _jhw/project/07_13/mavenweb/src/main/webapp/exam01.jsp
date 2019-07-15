<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page language="java" import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	String driver = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "hr";
    String pass = "hr";
    Connection conn;
    Statement  stmt;
    PreparedStatement pstmt;
    ResultSet rs;
     
    Class.forName(driver);
    conn = DriverManager.getConnection(url, user, pass);
    stmt = conn.createStatement();
    pstmt=conn.prepareStatement("select * from REGIONS");
    rs=pstmt.executeQuery();
    
    out.println("<table border=\"1\">");
    while(rs.next()){
        out.println("<tr>");
        out.println("<td>"+rs.getString("REGION_ID")+"</td>");
        out.println("<td>"+rs.getString("REGION_NAME")+"</td>");
        out.println("</tr>");
    }
    out.println("</table>");
     
    conn.close();
%>
</body>
</html>