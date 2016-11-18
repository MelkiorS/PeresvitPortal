'use strict';
function show_private_office(){
    // var private_office = $($('link[rel=import].private_office').prop('import')).find('.show').get(0);
    // $('.main_section').load('privateOffice');
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
            $('.main_section').load('ourEvents');
        }
        if($.trim($(this).html()) ==='Особистий кабінет'){
            // $('.main_section').load('privateOffice');
            // $('.main_section').append('<div w3-include-html="privateOffice.html"></div>');
            // $('.main_section').load('<div w3-include-html="privateOffice.html"></div>');
            // $('.main_section').append('<div w3-include-html="../../../resources/frontend/workField/html/privateOffice.html"></div>');
            // $('.main_section').load(' <span data-wi-src="../../../resources/frontend/workField/html/privateOffice.html"> </span>');
            $('.main_section').append(' <span data-wi-src="../../../resources/frontend/workField/html/privateOffice.html"> </span>');
            $('.main_section').append(' <span data-wi-src="privateOffice.html"> </span>');
            // $('.main_section').append('<object name="foo" width="100%" height="100%" type="text/html" data="../../../resources/frontend/workField/html/privateOffice.html"></object>');

        }
        if($.trim($(this).html()) ==='мій шлях'){
            $('.main_section').load('my_way_main');
            console.log($('.place_for_add_sidebar'));
            $('.place_for_add_sidebar ').load('my_way_sidebar');
        }
        if($.trim($(this).html()) !=='мій шлях'){
            $('.place_for_add_sidebar').empty();
        }

    });


});

