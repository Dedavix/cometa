function showArticoli() {
	console.log("MOSTRA ARTICOLI");
    var xmlhttp = new XMLHttpRequest();
    
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
            console.log("Ho ricevuto la risposta");
            var articoli = JSON.parse(this.responseText);
            articoli = articoli.results;
			var htmlGen = "";
			
			htmlGen += "<div align="right">
			<h1 align="left">Tabella Articoli:</h1>
			<p>${msg}</p>
			<div align="left" style="margin-top: 40px;">
				<p align="left">
					<input type=button onClick="aggiungiArticolo()"
						value="Aggiungi Articolo">
				</p>
			</div>
			<form name="form" onSubmit="filtraArticoli()">
				<b>Filtra Articoli:</b> <input type="text" name="filtro"
					value="${filtro}"><br> <input type="submit"> <br>
				<br>
			</form>
		</div>
		<table style="width: 100%">
		<tr>
			<th>Codice</th>
			<th>Descrizione</th>
			<th>Quantita Totale</th>
			<th>Azione
			<th>
		</tr>";
			for(var a of articoli){
				htmlGen+="<tr><td><a onClick="mostraLotto(a.id)">"+ a.codice + "</a></td><td><a onClick="mostraLotto(a.id)">"+ a.descrizione +"</a></td><td><a  onClick="mostraLotto(a.id)">"+a.quantitaTot +"</a></td><td align="center"><a onClick="modificaArticolo(a.id)"><input type="button" value="Modifica"></a></td></tr>";
			}
			htmlGen+="</table>";
			document.getElementById("mostraArticoli").innerHTML = htmlGen;
		}
    };
    
	xmlhttp.open("GET", "http://localhost:8080/api/articoli/list",
			true);
    xmlhttp.send();
    console.log("Ho mandato la chiamata");
}