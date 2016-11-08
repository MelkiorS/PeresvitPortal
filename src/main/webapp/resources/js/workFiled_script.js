'use strict';

$(function(){
    function show_private_office(){
        // var private_office = $($('link[rel=import].private_office').prop('import')).find('.show').get(0);
        $('.main_section').load('html/private_office.html');
        display_data_for_private_office();
    }
    function display_data_for_private_office() {
        var answer =  $.ajax({
            url: server + "user/3",
            crossDomain: true,
            xhrFields: {withCredentials: true},

            dataType: 'json'
        })
            .done(function(data){
                insert_data_in_private_office_fields( JSON.stringify(data));
            });


    }
    function insert_data_in_private_office_fields(data) {
        var userData = JSON.parse(data);
        // var userData = JSON.parse(generalInfo);

        // function load(data) {
        //     var userData = data;

        var fio = userData.fname + ' ' + userData.lname + ' ' + userData.mname;

        var $body = $('body');
        //при нажатии на кнопку меняем ее цвет на серый, а остальные делаем елыми
        // $(' .dropdown-toggle').on('click', function () {
        //     var $self = $(this);
        //     $self.css('background-color', 'rgb(189,189,189)');
        //     $(' .dropdown-toggle').not($self).css('background-color', '#fff');
        // });
        //вставляем ФИО в соответствующее поле
        $('.main_section_name').text(fio);

        //вставляем аватар по ссылке, которая пришла с json'а
        $('.main_section_basic_avatar img').attr('src', userData.avatarURL);

        //получаем дополнительную информацию по юзеру и вставляем ее на страницу
        // console.log($('.main_section_userInf_item '));
        //при загрузке делаем кнопку 'Особистий кабінет' активной
        for(var i in $('.control_button')){
            if($.trim($('.control_button')[i].innerHTML) =='Особистий кабінет'){
                $($('.control_button')[i]).addClass('active_button');
            }
        }

        // console.log($('.main_section_photoGallery_images'));
        // for(var key in photoalbum){
        var img = document.createElement('img');
        img.src = 'http://combat-arnis.ru/wp-content/uploads/2014/01/mixed-martial-arts-2.jpg';
        $('.main_section_photoGallery_images').append(img);
        // }
    }


    // при переходе на данную страницу сначала загружаем личный кабинет пользователя
    show_private_office();

    //при нажатии на кнопку меню, она становится серой, а все остальные становятся светлыми
    $('.control_button').on('click',function(){
        $(this).addClass('active_button').removeClass('no_active_buttons');
        // $(this);
        $('.control_button').not($(this)).addClass('no_active_buttons').removeClass('active_button');
        // $('.control_button').not($(this));

    });
    var dataFor = {
        fname: "Alexander",
        lname: "Mercury",
        mname: "Stepanovych",
        login: "Johny",
        email: "Wylsacom@gmail.com",
        avatarURL: "https://pickaface.net/gallery/avatar/20131210_213603_2798_lachu.png"
    };
    $('.control_button').on('click',function(){
        for(var i in $('.control_button')){
            if($.trim($('.control_button')[i].innerHTML) =='наші події'){
                $.ajax({
                    method: "POST",
                    url: server + "user/",
                    data: JSON.stringify($(dataFor).serializeArray()),
                    dataType: "json",
                    contentType : "application/json"
                })
                    .done(function( msg ) {
                        alert( "Data Saved: " + msg );
                    });
            }
        }
    });
});

