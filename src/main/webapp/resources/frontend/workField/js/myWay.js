'use strict';
$(function() {


    // $('.my_way_sidebar_group_button').on('click', function () {
    //     $(this).addClass('active').removeClass('no_active');
    // });
    $('.my_way_sidebar_group_button').on('click', function () {
        $(this).addClass('active').removeClass('no_active');
        console.log($.trim($(this).html()));
        $('.my_way_sidebar_group_button').not($(this)).addClass('no_active').removeClass('active');
        $('.main_section').empty();
        if ($.trim($(this).html()) === 'Базова техніка') {
            $('.main_section').load('myWay/myWayBasic');
        }
        if ($.trim($(this).html()) === 'Базові технічні комплекси') {
            $('.main_section').load('myWay/myWayBaseComplex');
        }
        if ($.trim($(this).html()) === 'Парна робота') {
            $('.main_section').load('myWay/myWayPairWork');
        }
        if ($.trim($(this).html()) === 'Спеціальна фізпідготовка') {
            $('.main_section').load('myWay/myWaySpecPhysical');
        }
        if ($.trim($(this).html()) === 'Загальна фізпідготовка') {
            $('.main_section').load('myWay/myWayGeneralPhysical');
        }
        if ($.trim($(this).html()) === 'Розбивання предметів') {
            $('.main_section').load('myWay/myWayBreakingObj');
        }


    });
});
