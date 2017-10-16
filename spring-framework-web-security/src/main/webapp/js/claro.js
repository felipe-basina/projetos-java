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

function limparAjaxRequest() {
	console.log(' ----> Entrou em limparAjaxRequest');
	$('#response').html('');
}

function ajaxRequest() {
	console.log(' ----> Entrou em ajaxRequest');
    $.ajax({
        url: "ajax-request.do",
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        	console.log(' ----> data: ' + JSON.stringify(data));
            $("#response").html('');
 
            $.each(data, function (index, elemento) {
 
 				$('#response').append('<h3>' + elemento + '</h3></br>');
 
            });
        },
        error: function(data,status,er) {
            console.log("error: "+data+" status: "+status+" er:"+er);
        }
    });
}