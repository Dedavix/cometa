var righeDocumento = [];

// Mostra lista documenti
function showDocumenti() {
	
	$(document).ready(function(){
		showDocumentFilter();
		$("#articoli-content").hide();
		$("#lotti-content").hide();
		$("#righe-documento-content").hide();
		var profilo = document.getElementById("profili-documento").value;
		var dataFrom = document.getElementById("data1").value;
		var dataTo = document.getElementById("data2").value;
		//document.getElementById("articoli-content").style.display = "none";
		//document.getElementById("lotti-content").style.display = "none";
		//document.getElementById("righe-documento-content").style.display = "none";	
		 $.ajax({
             url: "http://localhost:8080/api/documenti/list/?profilo=" + profilo + "&data1=" + dataFrom + "&data2=" + dataTo
             , method: "GET"
             , success: function (response) {
                 var documenti = response;
                 var htmlGen = "<tr><th>Codice</th><th>Progressivo</th><th>Data</th><th>Dettaglio</th></tr>";
                 $(documenti).each(function (index) {
                     htmlGen += "<tr><td><p>" + documenti[index].profilo + "</p></td><td><p>" + documenti[index].progressivo + "</p></td><td><p>" + documenti[index].data + "</p></td><td><input type=\"button\" value=\"dettagli\" onClick=\"showRigheDocumento(" + documenti[index].id + ")\"></td></tr>";
                 });
                 $("#documenti-content").show();
                 $("#main-documenti-page").show();
                 $("#documenti-table").html(htmlGen);
             }                
             , error: function(response) {
                 alert("Non ho trovato documenti");
             }
         });
		
	/*	//Chiamata AJAX Javascript
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {
				console.log("Ho ricevuto la risposta");
				var documenti = JSON.parse(this.responseText);
				var htmlGen = "<tr><th>Codice</th><th>Progressivo</th><th>Data</th></tr>";
				for (var d of documenti) {
					htmlGen += "<tr><td><p onClick=\"showRigheDocumento(" + d.id + ")\">" + d.profilo + "</p></td><td><p onClick=\"showRigheDocumento(" + d.id + ")\">" + d.progressivo + "</p></td><td><p onClick=\"showRigheDocumento(" + d.id + ")\">" + d.data + "</p></td></tr>";
				}
				document.getElementById("documenti-content").style.display = "block";
				document.getElementById("main-documenti-page").style.display = "block";
				document.getElementById("documenti-table").innerHTML = htmlGen;
			}
		};
	
		xmlhttp.open("GET", "http://localhost:8080/api/documenti/list/?profilo=" + profilo + "&data1=" + dataFrom + "&data2=" + dataTo,
			true);
		xmlhttp.send();
		console.log("Ho mandato la chiamata");
	 */
	});
}

// Prepara filtro
function showDocumentFilter() {
	console.log("Ricava ProfiliDocumento");
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			console.log("Ho ricevuto la risposta");
			var profili = JSON.parse(this.responseText);
			htmlGen = "<option value=\"0\">Nessun Profilo</option>";

			for (var p of profili) {
				htmlGen += "<option value=\"" + p.id + "\">" + p.codice + "</option>";
			}

			document.getElementById("profili-documento").innerHTML = htmlGen;
			document.getElementById("profili-documento2").innerHTML = htmlGen;
		}
	};
	xmlhttp.open("GET", "http://localhost:8080/api/profiliDocumento/list",
		true);
	xmlhttp.send();
	console.log("Ho mandato la chiamata");

}

function showRigheDocumento(idDocumento) {

	document.getElementById("main-documenti-page").style.display = "none";
	console.log("MOSTRA RIGHE DOCUMENTO");
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			console.log("Ho ricevuto la risposta");
			var righe = JSON.parse(this.responseText);
			var htmlGen = "<tr><th>Codice Articolo</th><th>Descrizione Articolo</th><th>Codice Lotto</th><th>Quantita</th><th>Dettagli</th></tr>";
			for (var r of righe) {
				htmlGen += "<tr><td>" + r.codiceArticolo + "</td><td>" + r.descrizioneArticolo + "</td><td>" + r.codiceLotto + "</td><td>" + r.quantita + "</td></tr>";
			}
			document.getElementById("righe-documento-content").style.display = "block";
			document.getElementById("righe-table").innerHTML = htmlGen;
		}
	};

	xmlhttp.open("GET", "http://localhost:8080/api/righeDocumento/" + idDocumento + "/list",
		true);
	xmlhttp.send();
	console.log("Ho mandato la chiamata");
}

function mostraAggiungiDocumento() {
	righeDocumento = [];
	showRigheDocumentoForm();
	document.getElementById("main-documenti-page").style.display = "none";
	showDocumentFilter();
	document.getElementById("aggiungi-documento").style.display = "block";
}

function aggiungiRigaDocumento() {
	var codiceArticolo = document.getElementById("codiceArticoloRiga").value;
	var codiceLotto = document.getElementById("codiceLottoRiga").value;
	var quantita = document.getElementById("quantitaRiga").value;
	var rigaDocumento = { codiceArticolo: codiceArticolo, codiceLotto: codiceLotto, quantita: quantita };
	righeDocumento.push(rigaDocumento);

	document.getElementById("codiceArticoloRiga").value = "";
	document.getElementById("codiceLottoRiga").value = "";
	document.getElementById("quantitaRiga").value = "";

	showRigheDocumentoForm();
}

function showRigheDocumentoForm() {
	var htmlGen = "<tr><th>Codice Articolo</th><th>Codice Lotto</th><th>Quantita</th></tr>";
	for (var riga of righeDocumento) {
		htmlGen += "<tr><td>" + riga.codiceArticolo + "</td><td>" + riga.codiceLotto + "</td><td>" + riga.quantita + "</td></tr>";
	}
	document.getElementById("righe-documento-aggiunte").innerHTML = htmlGen;
}

function inviaDocumento() {
	var idProfilo = document.getElementById("profili-documento2").value;
	var dataDoc = document.getElementById("dataDocIns").value
	var xmlhttp = new XMLHttpRequest();
	var documento = { idProfilo: idProfilo, data: dataDoc, righe: righeDocumento };
	var documentoJSON = JSON.stringify(documento);
	xmlhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			console.log("Ho ricevuto la risposta");
			var esito = this.responseText;
			if (esito.localeCompare("OK") == 0) {
				document.getElementById("aggiungi-documento").style.display = "none";
				showDocumenti();
				alert("DOCUMENTO INSERITO CORRETTAMENTE");
			} else {
				alert("INSERIMENTO NON VALIDO");
			}
		}
	};

	xmlhttp.open("POST", "http://localhost:8080/api/documenti/save",
		true);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(documentoJSON);
	console.log("Ho mandato la chiamata");
}











