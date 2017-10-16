/* Aplica mascara de telefone
---------------------------------------------------------------------------------------------------------*/
jQuery.fn.brTelMask = function() {

    return this.each(function(){
        var el = this;
        
        $(el).focus(function(){
            $(el).mask("(99) 9999-9999?9");
        });

        $(el).focusout(function(){
           var phone, element;
           element = $(el);
           element.unmask();
           phone = element.val().replace(/\D/g, '');
           if(phone.length > 10){
               element.mask("(99) 99999-999?9");
           }else{
               element.mask("(99) 9999-9999?9");
           }
        });
    });
};

$(document).ready(function() {
    $(".campoTelefone").brTelMask();
    $(".campoTelefone").blur();
});


function getLastId() {
	console.log(' ----> Entrou em getLastId');
    $.ajax({
        url: "ajax-request.do",
        type: 'get',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        	console.log(' ----> data: ' + JSON.stringify(data));
            $("#response").html('');
            $('#response').append('<h3>' + data + '</h3></br>');
        },
        error: function(data,status,er) {
            console.log("error: "+data+" status: "+status+" er:"+er);
        }
    }); 
}

function getLastIdd() {
	console.log(' ----> Entrou em getLastIdd');
    $.ajax({
        url: "ajax-request2.do",
        type: 'get',
        data: {"vTest": "request-param-hi"},
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
        	console.log(' ----> data: ' + JSON.stringify(data));
            $("#response").html('');
            $('#response').append('<h3>' + data + '</h3></br>');
        },
        error: function(data,status,er) {
            console.log("error: "+data+" status: "+status+" er:"+er);
        }
    });   
}