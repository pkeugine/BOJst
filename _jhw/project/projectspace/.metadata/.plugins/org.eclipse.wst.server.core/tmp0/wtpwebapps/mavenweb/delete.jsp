<%@page import="kr.or.connect.review.Rev"%>
<%@page import="kr.or.connect.review.RevDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("userId");
	String opinion = request.getParameter("opinion");

	//String userId = "qwe690";
	//String opinion = "그치?";


	
	RevDAO dao = RevDAO.getInstance();
	dao.deleteRev(userId, opinion);

%>