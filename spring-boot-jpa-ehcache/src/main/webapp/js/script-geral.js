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
