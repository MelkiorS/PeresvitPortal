'use strict';
function show_private_office(){
    // var private_office = $($('link[rel=import].private_office').prop('import')).find('.show').get(0);
    $('.main_section').load('privateOffice');
    //при загрузке делаем кнопку 'Особистий кабінет' активной
    for(var i in $('.control_button')){
        if($.trim($('.control_button')[i].innerHTML) =='Особистий кабінет'){
            $($('.control_button')[i]).addClass('active_button');
        }
    }
}

$(function(){

    // при переходе на данную страницу сначала загружаем личный кабинет пользователя
    show_private_office();

    //при нажатии на кнопку меню, она становится серой, а все остальные становятся светлыми
    $('.control_button').on('click',function(){
        $(this).addClass('active_button').removeClass('no_active_buttons');
        console.log($.trim($(this).html()));
        $('.control_button').not($(this)).addClass('no_active_buttons').removeClass('active_button');
        // $('.control_button').not($(this));
        $('.main_section').empty();
        if($.trim($(this).html()) ==='наші події'){
            $('.main_section').load('ourEvents.html');
        }
        if($.trim($(this).html()) ==='Особистий кабінет'){
            $('.main_section').load('privateOffice.html');
        }

    });


});

