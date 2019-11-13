
function showArticoli() {
	console.log("MOSTRA ARTICOLI");
    var xmlhttp = new XMLHttpRequest();
    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articoli = JSON.parse(this.responseText);
            articoli = articoli.results;
			var htmlGen = "<tr><th>Codice</th><th>Descrizione</th><th>Quantita Totale</th><th>Azione<th></tr>";
			for(var a of articoli){
				htmlGen+="<tr><td><a onClick="mostraLotto(a.id)">"+ a.codice + "</a></td><td><a onClick="mostraLotto(a.id)">"+ a.descrizione +"</a></td><td><a onClick="mostraLotto(a.id)">"+a.quantitaTot +"</a></td><td align="center"><a onClick="modificaArticolo(a.id)"><input type="button" value="Modifica"></a></td></tr>";
			}
			document.getElementById("articoli-table").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/articoli/list",
			true);
    xmlhttp.send();
    console.log("Ho mandato la chiamata");
}
