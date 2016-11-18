'use strict';
$(function() {


    // $('.my_way_sidebar_group_button').on('click', function () {
    //     $(this).addClass('active').removeClass('no_active');
    // });
    $('.my_way_sidebar_group_button').on('click', function () {
        $(this).addClass('active').removeClass('no_active');
        console.log($.trim($(this).html()));
        $('.my_way_sidebar_group_button').not($(this)).addClass('no_active').removeClass('active');
        // $('.control_button').not($(this));
        // $('.main_section').empty();
        // if ($.trim($(this).html()) === 'наші події') {
        //     $('.main_section').load('our_events.html');
        // }
        // if ($.trim($(this).html()) === 'Особистий кабінет') {
        //     $('.main_section').load('private_office.html');
        // }
        // if ($.trim($(this).html()) === 'мій шлях') {
        //     $('.place_for_add_sidebar').load('my_way_sidebar.html');
        //     $('.main_section').load('myWayMain.html');
        // }
        // if ($.trim($(this).html()) !== 'мій шлях') {
        //     $('.place_for_add_sidebar').empty();
        // }

    });
});
