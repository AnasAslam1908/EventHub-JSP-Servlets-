<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%

     session = request.getSession(false);
    if (session != null) {
        session.invalidate();
    }


    response.sendRedirect("login.jsp?message=You have successfully logged out.");
%>