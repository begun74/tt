<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<link href="resources/css/style.css" rel="stylesheet">
		<meta charset="utf-8">
		<title>Welcome</title>
		<style type="text/css">
		 .pageStyle {
			  font-family: sans-serif;
			  font-size: 28px;
			  color: #0099FF;
			}
		</style>
	</head> 
	<body>
		<c:url value="/showMessage.html" var="messageUrl" />
		<a href="${messageUrl}">Click to enter</a>
		<p>
			Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor
			<p><strong  class="qwe">Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</strong></p>
			
			<em class="pageStyle">Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</em>
		</p>

					

	</body>
</html>
