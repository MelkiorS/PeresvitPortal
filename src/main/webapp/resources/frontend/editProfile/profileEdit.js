$(function(){
    $('main').on('keydown',function(e){
        var self =$(this) ,form = self.find('form').eq(0),focusable,next;
        if(e.keyCode == 13){
            focusable = form.find('input,a,select,button,textarea').filter(':visible');
            next = focusable.eq(focusable.index(e.target)+1);
            if($(e.target).val() == 'Зберегти'){
                form.submit();
            } else if(next){
                next.focus();
            }
            return false;
        }
    });
    $('.passwords > p').on('click',function(){
        $('.passwords fieldset').toggle();
    });
    $('#oldPasswd').on('keyup',function(){
        if($.trim($(this).val()) === $.trim($('#password').val())){
            $('.checkOldPass').text('Пароль підтверджено');
            $('.checkOldPass').css('color','#00b200');
            $('#newPasswd').removeAttr('disabled');
            $('#confirmPasswd').removeAttr('disabled');
        }else{
            $('.checkOldPass').text('Введений неправильний старий пароль');
        }
    });

    $('#confirmPasswd').on('keyup',function(){
        if($.trim($(this).val()) == $.trim($('#newPasswd').val())){
            $('.checkNewPass').text('Паролі збігаються');
            $('.checkNewPass').css('color','#00b200');
        }else{
            $('.checkNewPass').text('Паролі не збігаються');
            $('.checkNewPass').css('color','#f44336');
        }
    });
    $('.buttons input[type="reset"]').on('click',function(){
        history.back();
    });

});