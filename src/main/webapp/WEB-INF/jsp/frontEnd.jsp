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
				<b>Filtra Articoli:</b><input type= "text" id="articoliFilter"><br>
					<input type="button" onClick="showArticoli()"><br><br>
			</div>
			<div id="insertArticle-content">
				<p align="left">
					<input type=button onClick="aggiungiArticolo()"
						value="Aggiungi Articolo">
				</p>
			</div>
			<div id="articoli-table-container">
				<h1 align="left">Tabella Articoli:</h1>
				<table id="articoli-table">			
				<!-- righe generate via JS -->
				</table>
			</div>
		</div>
		<div id="documenti-content" style="display: none;">
			<div id="documenti-filter">
				<p align ="left">Filtro: </p>
				<select id="profili-documento" required="required" style='margin:5px'>
				</select>
				Da: <input type="date" id="data1">
				A: <input type = "date" id="data2">
				<br>
				<input type = "button" onClick="showDocumenti()" value = "VAI">
				<br><br>
			</div>
			<div id="insertDocument-content">
				<p align="left">
					<input type=button onClick="aggiungiDocumento()"
						value="Aggiungi Documento">
				</p>
			</div>
			<div id="documenti-table-container">
			<h1 align="left">Tabella Documenti:</h1>
				<table id="documenti-table">		
				<!--righe generate via JS -->		
				</table>
			</div>
		</div>
	</div>
</body>
</html>