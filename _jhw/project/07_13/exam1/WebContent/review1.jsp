<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn=null;
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";

	Boolean connect=false;
	try {
		
   		Class.forName(driver);
   		conn = DriverManager.getConnection(url,"hr","1234");
   		connect = true;
   		conn.close();
	} catch(Exception e) {
    	connect=false;
   		e.printStackTrace();
	}

	if(connect) {
		System.out.println("연결o");
	} else {
		System.out.println("연결x");
	}
%>
</body>
</html>