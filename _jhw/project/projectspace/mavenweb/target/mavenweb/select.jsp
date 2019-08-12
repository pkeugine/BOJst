<%@page import="java.io.PrintWriter"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.connect.review.Rev"%>
<%@page import="kr.or.connect.review.RevDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request.setCharacterEncoding("UTF-8");
	//String place = request.getParameter("deptno");

	
	RevDAO dao = RevDAO.getInstance();
	List<Rev> list = dao.getRevs();

	ObjectMapper objectMapper = new ObjectMapper();

	String json = objectMapper.writeValueAsString(list);
	
	
	/* 
	buffer.append("");
	for(Rev rev : list) {
		String place = rev.getPlace();
		String user_id = rev.getUserId();
		int grade = rev.getGrade();
		String opinion = rev.getOpinion();
		Date date = rev.getDate();
		buffer.append("");
		buffer.append(""+place+" ");
		buffer.append(""+user_id+" ");
		buffer.append(""+grade+" ");
		buffer.append(""+opinion+" ");
		buffer.append(""+date+" ");
		buffer.append("\n");
	}
	buffer.append(""); */
%>
<%= json %>