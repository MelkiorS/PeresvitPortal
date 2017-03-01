$(function(){
    if(window.innerWidth <768){
        let sideBarWidth = window.innerWidth*0.865;
        $('.toggle-button').sideNav({
                menuWidth: sideBarWidth , // Default is 300
                edge: 'left', // Choose the horizontal origin
                closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
                draggable: true // Choose whether you can drag to open on touch screens
            }
        );
        // let sideBarCounter = false;
        // $('.toggle-button').on('click',function(){
        //     // $(this).css('transform','rotate(90deg)');
        //     $('.drag-target').css('width',window.innerWidth - sideBarWidth+'px');
        // });
        // $('.drag-target').on('click',function(){
        //     $('.toggle-button').css('transform','rotate(0deg)');
        // })
    }

    $('.sideNav .menu-level1 .btn-my-way').hover(function(){
        $('.sideNav .menu-level1 .btn-my-way img').attr('src','/resources/frontend/image/red-arrow.png');},function(){
        $('.sideNav .menu-level1 .btn-my-way img').attr('src','/resources/frontend/image/blue-arrow.png');

    });

    $('.my-way-main  h3').on('click',function(){
        $(this).parent().children('ol').fadeToggle();
        $(this).parent().toggleClass('level1-active');
    });
    if(window.innerWidth >767) {
        $('.sideNav .menu-level1 li .btn-my-way ').on('click', function () {
            $('.sideNav .menu-level1 .btn-my-way img').toggleClass('transformed', 'slow');
            $('.menu-level2').fadeToggle(100);
            $('.sideNav .menu-level1 .my-way').toggleClass('border-my-way', 100);
        });
    }
});
