var notiDisabled = document.querySelector('notification#notificacao-disabled');
var notActived = document.querySelector('notification#notificacao-actived');

notActived.addEventListener('click', function(){
    addClass('notificacao-actived','noti-none');
    delClass('notificacao-disabled','noti-none');
});

notiDisabled.addEventListener('click', function(){
    addClass('notificacao-disabled','noti-none');
    delClass('notificacao-actived','noti-none');
});