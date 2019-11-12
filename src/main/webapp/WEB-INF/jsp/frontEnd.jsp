<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
html, body {
	height: 100%;
	width: 100%;
}

table, th, td {
	border: 1px solid black;
}

a {
	color: #7FFFD4;
}

.bg {
	/* The image used */
	background-image: url(img/capelli-background.jpg);
	width: 100%;
	height: auto;
	color: #dc05f1;
}
</style>

 <script src="..js/articoli.js"></script>
 
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div align = "center"> <h1>Premi per visualizzare:</h1>
    <input type = "button" onClick="showArticoli()" value = "Articoli">
    <input type = "button" onClick="showDocumenti()" value = "Documenti">
    </div>
	<p id="mostraArticoli" class=bg></p>
</body>
</html>