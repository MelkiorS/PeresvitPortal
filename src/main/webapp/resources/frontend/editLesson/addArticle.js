$(function(){
    $('.category-name span').on('click',function(){
        console.log($(this).parent().parent().find($('ol .category-item')));
        $(this).parent().parent().find($('.category-item')).toggle(600);
    });
});