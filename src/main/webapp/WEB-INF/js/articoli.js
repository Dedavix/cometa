
function showArticoli() {
	document.getElementById("documenti-content").style.display = "none";
	console.log("MOSTRA ARTICOLI");
    var xmlhttp = new XMLHttpRequest();
    var filtro= document.getElementById("articoliFilter").value;
    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articoli = JSON.parse(this.responseText);
            articoli = articoli.results;
			var htmlGen = "<tr><th>Codice</th><th>Descrizione</th><th>Quantita Totale</th><th>Azione<th></tr>";
			for(var a of articoli){
				htmlGen+="<tr><td><a onClick=\"mostraLotto("+a.id+")\">"+ a.codice + \"</a></td><td><a onClick=\"mostraLotto("+a.id+")\">"+ a.descrizione +"</a></td><td><a onClick=\"mostraLotto("+a.id+")\">"+a.quantitaTot +"</a></td><td align=\"center\"><p><input type=\"button\" value=\"Modifica\" onClick=\"modificaArticolo("+a.id+","+a.codice+","+a.descrizione+")\"></p></td></tr>";
			}
			document.getElementById("articoli-content").style.display = "block";
			document.getElementById("articoli-table").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/articoli/list",
			true);
    xmlhttp.send(filtro);
    console.log("Ho mandato la chiamata");
}

function mostraAggiungiArticolo(){
	document.getElementById("articoli-content").style.display = "none";
	document.getElementById("aggiungi-articolo-container").style.display = "block";
}

function inserisciArticolo(){
	var codiceArticolo=document.getElementById("codiceArticoloIns").value;
	var descrizioneArticolo=document.getElementById("descrizioneArticoloIns").value;
	var articolo = {id:0,codice:codiceArticolo,descrizione:descrizioneArticolo};
	var articoloJSON = JSON.stringify(articolo);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var esito = JSON.parse(this.responseText);
            esito = esito.results;
            if (esito.localCompare("OPERAZIONE ESEGUITA CON SUCCESSO")==0){
            	document.getElementById("aggiungi-articolo-container").style.display="none";
            	showArticoli();
            	alert("ARTICOLO INSERITO CORRETTAMENTE"); 	
            }else{
            	alert("INSERIMENTO NON VALIDO");
            } 
		}
    };
    
    xmlhttp.open("POST", "http://localhost:8080/api/articoli/save",
			true);
    xmlhttp.send(articoloJSON);
    console.log("Ho mandato la chiamata");	
}

function mostraModificaArticolo(var idArticolo, var codArticolo, var descrizArticolo){
	document.getElementById("articoli-content").style.display = "none";
	document.getElementById("codiceArticoloIns").value =codArticolo;
	document.getElementById("descrizioneArticoloIns").value =descrizArticolo;
	document.getElementById("idModArticolo").value= idArticolo;
	document.getElementById("modifica-articolo-container").style.display = "block";
}

function modificaArticolo(){
	var codiceArticolo=document.getElementById("codiceArticoloIns").value;
	var descrizioneArticolo=document.getElementById("descrizioneArticoloIns").value;
	var idArticolo = document.getElementById("idModArticolo").value;
	var articolo = {id:idArticolo,codice:codiceArticolo,descrizione:descrizioneArticolo};
	var articoloJSON = JSON.stringify(articolo);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var esito = JSON.parse(this.responseText);
            esito = esito.results;
            if (esito.localCompare("OPERAZIONE ESEGUITA CON SUCCESSO")==0){
            	document.getElementById("aggiungi-articolo-container").style.display="none";
            	showArticoli();
            	alert("ARTICOLO MODIFICATO CORRETTAMENTE"); 	
            }else{
            	alert("MODIFICA NON VALIDA");
            } 
		}
    };
    
    xmlhttp.open("POST", "http://localhost:8080/api/articoli/save",
			true);
    xmlhttp.send(articoloJSON);
    console.log("Ho mandato la chiamata");	
}

function mostraLotto(idArticolo){
	document.getElementById("articoli-content").style.display = "none";
	console.log("MOSTRA LOTTI");
    var xmlhttp = new XMLHttpRequest();    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articoli = JSON.parse(this.responseText);
            lotti = lotti.results;
			var htmlGen = "<tr><th>Codice Lotto</th><th>Quantita</th></tr>";
			for(var l of lotti){
				htmlGen+="<tr><td>"+l.codice+"</td><td>"+l.quantita+"</td></tr>";
			}
			document.getElementById("lotti-content").style.display = "block";
			document.getElementById("lotti-table").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/lotti/list",
			true);
    xmlhttp.send(idArticolo);
    console.log("Ho mandato la chiamata");
    
}

