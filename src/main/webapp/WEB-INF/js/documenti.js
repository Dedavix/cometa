var righeDocumento = [];

function showDocumenti(){
	document.getElementById("articoli-content").style.display = "none";
	console.log("MOSTRA Documenti");
	showDocumentFilter();
    var xmlhttp = new XMLHttpRequest();
    var profilo = document.getElementById("profiloDocumento").value;
    var dataFrom = document.getElementById("data1").value;
    var dataTo = document.getElementById("data2").value;
    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var documenti = JSON.parse(this.responseText);
            documenti = documenti.results;
			var htmlGen = "<tr><th>Codice</th><th>Progressivo</th><th>Data</th></tr>";
			for(var d of documenti){
				htmlGen+="<tr><td><a onClick=\"showRigheDocumento(d.id)\">" + d.descrizione + "</a></td><td><a onClick=\"showRigheDocumento(d.id)\">"+ d.progressivo + "</a></td><td><a onClick=\"showRigheDocumento(d.id)\">"+ d.data +"</a></td></tr>";
			}
			document.getElementById("documenti-content").style.display = "block";
			document.getElementById("documenti-table").innerHTML = htmlGen;		
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/documenti/list",
			true);
    xmlhttp.send(profilo&dataFrom&dataTo);
    console.log("Ho mandato la chiamata");
}

function showDocumentFilter(){
	 console.log("Ricava ProfiliDocumento");
	    var xmlhttp = new XMLHttpRequest();    
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
	            console.log("Ho ricevuto la risposta");
	            var profili = JSON.parse(this.responseText);
	            profili = profili.results;
	            htmlGen="<option value=\"0\">Nessun Profilo</option>";

	            for(var p of profili){
					htmlGen+="<option value=\"" + p.id +"\">"+ p.codice + "</option>";
				}
	            				
				document.getElementById("profili-documento").innerHTML = htmlGen;
			}
	    };    
		xmlhttp.open("GET", "http://localhost:8080/api/profiliDocumento/list",
				true);
	    xmlhttp.send();
	    console.log("Ho mandato la chiamata");
	 
 }
 
 function showRigheDocumento(idDocumento){
	 
	 document.getElementById("documenti-content").style.display = "none";
		console.log("MOSTRA RIGHE DOCUMENTO");
	    var xmlhttp = new XMLHttpRequest();    
		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
	            console.log("Ho ricevuto la risposta");
	            var righe = JSON.parse(this.responseText);
	            righe= righe.results;
				var htmlGen = "<tr><th>Codice Articolo</th><th>Descrizione Articolo</th><th>Codice Lotto</th><th>Quantita</th></tr>";
				for(var r of righe){
					htmlGen+="<tr><td>"+r.codiceArticolo+"</td><td>"+r.descrizioneArticolo+"</td><td>"+r.codiceLotto+"</td><td>"+r.quantita+"</td></tr>";
				}
				document.getElementById("righe-documento-content").style.display = "block";
				document.getElementById("righe-table").innerHTML = htmlGen;
			}
	    };
	    
		xmlhttp.open("GET", "http://localhost:8080/api/documenti/{"+idDocumento+"}/list",
				true);
	    xmlhttp.send();
	    console.log("Ho mandato la chiamata");	    	 
 }
 
 function mostraAggiungiDocumento(){
	 document.getElementById("documenti-content").style.display = "none";
	 showDocumentFilter();
	 document.getElementById("aggiungi-documento").style.display ="block";	 
 }

 function aggiungiRigaDocumento(){
	 var codiceArticolo = document.getElementById("codiceArticoloRiga").value;
	 var codiceLotto= document.getElementById("codiceLottoRiga").value;
	 var quantita= document.getElementById("quantitaRiga").value;
	 var rigaDocumento = {codiceArticolo:codiceArticolo,codiceLotto:codiceLotto, quantita:quantita};
	 righeDocumento.push(rigaDocumento);
	 var htmlGen = "Codice Articolo: <input type=\"text\" id=\"codiceArticoloRiga\">Codice Lotto: <input type=\"text\" id=\"codiceLottoRiga\">Quantita: <input type=\"text\" id=\"quantitaRiga\">";
	 document.getElementById("riga-documento").innerHTML = htmlGen;
     alert("RIGA DOCUMENTO AGGIUNTA")
 }
 
function inviaDocumento(){
	var idProfilo= document.getElementById("profili-documento").value;
	var dataDoc=document.getElementById("dataDocIns").value
	var documento = {idProfilo:idProfilo,data:dataDoc, righe:righeDocumento};
	var documentoJSON = JSON.stringify(documento);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var esito = JSON.parse(this.responseText);
            esito = esito.results;
            if (esito.localCompare("OK")==0){
            	document.getElementById("aggiungi-documento").style.display ="none";
            	showDocumenti();
            	alert("DOCUMENTO INSERITO CORRETTAMENTE"); 	
            }else{
            	alert("INSERIMENTO NON VALIDO");
            } 
		}
    };
    
    xmlhttp.open("POST", "http://localhost:8080/api/documenti/save",
			true);
    xmlhttp.send(documentoJSON);
    console.log("Ho mandato la chiamata");
}










		