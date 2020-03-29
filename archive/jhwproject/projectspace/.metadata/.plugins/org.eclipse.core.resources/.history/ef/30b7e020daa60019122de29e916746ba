<%@page import="kr.or.connect.review.Rev"%>
<%@page import="kr.or.connect.review.RevDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	//request.setCharacterEncoding("UTF-8");
	//String place = request.getParameter("deptno");
	request.setCharacterEncoding("UTF-8");

	String place = "맥도날드";
	String userId = request.getParameter("userId");
	int grade = Integer.parseInt(request.getParameter("grade"));
	String opinion = request.getParameter("opinion");

	
	RevDAO dao = RevDAO.getInstance();
	Rev rev = new Rev(place,userId,grade,opinion);
	dao.addRev(rev);

%>