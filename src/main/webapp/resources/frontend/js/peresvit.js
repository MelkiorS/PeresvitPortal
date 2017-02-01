$(function(){
    if(window.innerWidth <768){
        var sideBarWidth = window.innerWidth*0.865;
        $('.toggle-button').sideNav({
                menuWidth: sideBarWidth , // Default is 300
                edge: 'left', // Choose the horizontal origin
                closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
                draggable: true // Choose whether you can drag to open on touch screens
            }
        );
        // let sideBarCounter = false;
        $('.toggle-button').on('click',function(){
            $(this).css('transform','rotate(90deg)');
            console.log();
            $('.drag-target').css('width',window.innerWidth - sideBarWidth+'px');
        });
        $('.drag-target').on('click',function(){
            $('.toggle-button').css('transform','rotate(0deg)');
        })
    }



});
