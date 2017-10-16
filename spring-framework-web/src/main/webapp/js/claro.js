var c = 0;
var du = "";

function escondediv(dv, n) {
	for (var i = 1; i <= n; i++) {
		if (i == dv) {
			if (du != dv) {
				document.getElementById('mdiv' + i).style.display = "inline";
				du = dv;
			} else {
				du = "";
				document.getElementById('mdiv' + i).style.display = "none";
			}
		} else {
			document.getElementById('mdiv' + i).style.display = "none";
		}
	}
}

function reveza(qq) {
	document.getElementById(qq).className = "itens_menu_r";
}

function volta(qq) {
	document.getElementById(qq).className = "itens_menu";
}