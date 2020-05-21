<%@ page import = "model.Pais" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<% Pais pais = (Pais) request.getAttribute("pais");%>
	
	id: <%= pais.getId() %>
	Nome: <%= pais.getNome() %>
	Pupulação: <%= pais.getPopulacao() %>
	Area: <%= pais.getArea() %>
	
</body>
</html>