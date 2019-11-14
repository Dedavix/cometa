<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<script src="../js/articoli.js"></script>
<script src="../js/documenti.js"></script>
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
				<b>Filtra Articoli:</b><input type="text" id="articoliFilter"><br>
				<input type="button" onClick="showArticoli()"><br> <br>
			</div>
			<div id="insert-article-call-content">
				<p align="left">
					<input type=button onClick="mostraAggiungiArticolo()"
						value="Aggiungi Articolo">
				</p>
			</div>
			<div id="articoli-table-container">
				<h1 align="left">Tabella Articoli:</h1>
				<table id="articoli-table">
					<!-- righe generate via JS -->
				</table>
			</div>
			<div id="aggiungi-articolo-container" style="display: none;">
				<h1>Inserisci Articolo</h1>
				Codice: <input type=text id="codiceArticoloIns"><br>
				Descrizione: <input type=text id="descrizioneArticoloIns"><br>
				<input type="button" onClick="inserisciArticolo()" value="OK">
			</div>
			<div id="modifica-articolo-container">
				<h1>Modifica Articolo</h1>
				Codice: <input type=text id="codiceArticoloIns"><br>
				Descrizione: <input type=text id="descrizioneArticoloIns"><br>
				<input id="idModArticolo" type="hidden"> <input
					type="button" onClick="modificaArticolo()" value="OK">
			</div>
			<div id="lotti-content" style="display: none;">
				<h1>Elenco Lotti</h1>
				<br> <br>
				<table id="lotti-table" style="width: 100%">
				</table>
			</div>
		</div>
		<div id="documenti-content" style="display: none;">
			<div id="documenti-filter">
				<p align="left">Filtro:</p>
				<select id="profili-documento" required="required"
					style='margin: 5px'>
				</select> Da: <input type="date" id="data1"> A: <input type="date"
					id="data2"> <br> <input type="button"
					onClick="showDocumenti()" value="VAI"> <br> <br>
			</div>
			<div id="insertDocument-call-content">
				<p align="left">
					<input type=button onClick="mostraAggiungiDocumento()"
						value="Aggiungi Documento">
				</p>
			</div>
			<div id="documenti-table-container">
				<h1 align="left">Tabella Documenti:</h1>
				<table id="documenti-table">
					<!--righe generate via JS -->
				</table>
			</div>
			<div id="aggiungi-documento">
				<select id="profili-documento" required="required"
					style='margin: 5px'>
				</select> Data Documento :<input type="date" id="dataDocIns"> <br>
				<br>
				<div id="riga-documento">
					Codice Articolo: <input type="text" id="codiceArticoloRiga">
					Codice Lotto: <input type="text" id="codiceLottoRiga">
					Quantita: <input type="text" id="quantitaRiga">
				</div>
				<br>
				<br> <input type="button" onclick="AggiungiRigaDocumento()"
					value="Aggiungi Riga"> <input type="button"
					onclick="inviaDocumento()" value="Invia">
			</div>
			<div id="righe-documento-content">
				<h1>Righe Documento:</h1>
				<table id=righe-table></table>
			</div>
		</div>
	</div>
</body>
</html>