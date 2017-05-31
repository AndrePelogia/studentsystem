<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário</title>
</head>
<body>
	<form action="resultado.jsp" method="post">
		<label> Texto</label>
		<input type="text" name="texto">

		<label>Idade</label>
		<input type="number" name="idade" min="0" max="100">

		<input type="submit" value="Enviar">
		
		<a href="resultado.jsp?idade=${idade}">Enviar via GET</a>
	</form>
</body>
</html>