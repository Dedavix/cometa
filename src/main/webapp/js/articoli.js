
function showArticoli() {
	document.getElementById("documenti-content").style.display = "none";
	document.getElementById("lotti-content").style.display = "none";
	document.getElementById("righe-documento-content").style.display = "none";
	console.log("MOSTRA ARTICOLI");
    var xmlhttp = new XMLHttpRequest();
    var filtro= document.getElementById("articoliFilter").value;
    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articoli = JSON.parse(this.responseText);
			var htmlGen = "<tr><th>Codice</th><th>Descrizione</th><th>Quantita Totale</th><th>Azione<th></tr>";
			for(var a of articoli){
				htmlGen+="<tr><td>"
					+"<p onclick=\"mostraLotto("+a.id+")\">"+ a.codice
					+"</p></td><td><p onClick=\"mostraLotto("+a.id+")\">"+ a.descrizione +"</p></td>"
					+"<td><p onClick=\"mostraLotto("+a.id+")\">"+a.quantitaTot +"</p></td>" 
					+"<td align=\"center\"><input type=\"button\" value=\"Modifica\" onClick=\"mostraModificaArticolo("+a.id+")\"></td></tr>";
			}
			document.getElementById("articoli-content").style.display = "block";
			document.getElementById("articoli-main-page").style.display = "block";
			document.getElementById("articoli-table").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/articoli/list/?filtro="+ filtro ,
			true);
    xmlhttp.send();
    console.log("Ho mandato la chiamata");
}

function mostraAggiungiArticolo(){
	document.getElementById("articoli-main-page").style.display = "none";
	document.getElementById("aggiungi-articolo-container").style.display = "block";
}

function inserisciArticolo(){
	var codiceArticolo=document.getElementById("codiceArticoloIns").value;
	var descrizioneArticolo=document.getElementById("descrizioneArticoloIns").value;
	var xmlhttp = new XMLHttpRequest();
	var articolo = {id:0,codice:codiceArticolo,descrizione:descrizioneArticolo,quantitaTot:0};
	var articoloJSON = JSON.stringify(articolo);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var esito = this.responseText;
            if (esito.localeCompare("OPERAZIONE ESEGUITA CON SUCCESSO")==0){
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
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(articoloJSON);
    console.log("Ho mandato la chiamata");	
}

function mostraModificaArticolo(idArticolo){
	document.getElementById("articoli-main-page").style.display = "none";
	console.log("GET ARTICOLO BY ID");
    var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articolo = JSON.parse(this.responseText);
            document.getElementById("codiceArticoloMod").value =articolo.codice;
        	document.getElementById("descrizioneArticoloMod").value =articolo.descrizione;
        	document.getElementById("idModArticolo").value= articolo.id;
        	document.getElementById("modifica-articolo-container").style.display = "block";
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/articoli/getById/?id="+ idArticolo,
			true);
    xmlhttp.send();
    console.log("Ho mandato la chiamata");
}

function modificaArticolo(){
	var codiceArticolo=document.getElementById("codiceArticoloMod").value;
	var descrizioneArticolo=document.getElementById("descrizioneArticoloMod").value;
	var idArticolo = document.getElementById("idModArticolo").value;
	var xmlhttp = new XMLHttpRequest();
	var articolo = {id:idArticolo,codice:codiceArticolo,descrizione:descrizioneArticolo};
	var articoloJSON = JSON.stringify(articolo);
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var esito = this.responseText;
            if (esito.localeCompare("OPERAZIONE ESEGUITA CON SUCCESSO")==0){
            	document.getElementById("modifica-articolo-container").style.display="none";
            	showArticoli();
            	alert("ARTICOLO MODIFICATO CORRETTAMENTE"); 	
            }else{
            	alert("MODIFICA NON VALIDA");
            } 
		}
    };
    
    xmlhttp.open("POST", "http://localhost:8080/api/articoli/save",
			true);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
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
            var lotti = JSON.parse(this.responseText);
			var htmlGen = "<tr><th>Codice Lotto</th><th>Quantita</th></tr>";
			for(var l of lotti){
				htmlGen+="<tr><td>"+l.codice+"</td><td>"+l.quantita+"</td></tr>";
			}
			document.getElementById("lotti-content").style.display = "block";
			document.getElementById("lotti-table").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/lotti/articolo/"+idArticolo+"/list",
			true);
    xmlhttp.send();
    console.log("Ho mandato la chiamata");
    
}