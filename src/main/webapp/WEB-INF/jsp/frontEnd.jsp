<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<script src="../js/articoli.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<meta charset="ISO-8859-1">
<title>PaginaJSP</title>
</head>
<body>

	<div align="center">
		<h1>Premi per visualizzare:</h1>
		<input type="button" onClick="showArticoli()" value="Articoli">
		<input type="button" onClick="showDocumenti()" value="Documenti">
	</div>

	<div id="main-content">
		<div id="articoli-content" style="display: none;">
			<div id="articoli-filter">
				<form name="form" onSubmit="showArticoli()">
					<b>Filtra Articoli:</b> <input type="text" name="filtro"><br>
					<input type="submit"> <br> <br>
				</form>
			</div>
			<div id="insert-content">
				<p align="left">
					<input type=button onClick="aggiungiArticolo()"
						value="Aggiungi Articolo">
				</p>
			</div>
			<div id="articoli-table-container">
				<h1 align="left">Tabella Articoli:</h1>
				<table id="articoli-table">			
				<!-- qui ci mettiamo le righe generate via JS -->
				</table>
			</div>
		</div>
		<div id="documenti-content" style="display: none;">
			<div id="documenti-filter">
				<input ... etc etc>
				<button ... blabla>
			
			</div>
			<div id="documenti-table-container">
				<table id="documenti-table">
				
				<!-- qui ci mettiamo le righe generate via JS -->
				
				</table>
			</div>
		</div>
	</div>
</body>
</html>