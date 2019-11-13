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
				htmlGen+="<tr><td><a onClick="mostraDocumento(d.id)">" + d.descrizione + "</a></td><td><a onClick="mostraDocumento(d.id)">"+ d.progressivo + "</a></td><td><a onClick="mostraDocumento(d.id)">"+ d.data +"</a></td></tr>";
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











		
