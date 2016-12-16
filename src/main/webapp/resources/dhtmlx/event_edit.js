$(function(){
    function init() {
        scheduler.init('scheduler_here', new Date(),"month");
        scheduler.config.xml_date="%Y%m%d %H%i";
        scheduler.templates.xml_date = function(value){ return new Date(value); };
        scheduler.load('/admin/myeventsperiod?start=20161101&finish=20161130','json');

        scheduler.attachEvent("onEventAdded", function(id,ev){
            $.ajax({
                type : "POST",
                url : "/admin/addEvent",
                data : {
                    "start_date" : ev.start_date.getTime(),
                    "end_date" : ev.end_date.getTime(),
                    "text" : ev.text
                },
                success: function(data){
                    scheduler.load('/admin/myeventsperiod?start=20161101&finish=20161130','json');
                    scheduler.deleteEvent(id);
                }
            });
        });

        scheduler.attachEvent("onEventChanged", function(id, ev) {
            $.ajax({
                type : "POST",
                url : "/admin/updEvent",
                data : {
                    "id" : ev.id,
                    "start_date" : ev.start_date.getTime(),
                    "end_date" : ev.end_date.getTime(),
                    "text" : ev.text
                },
                success: function(data){
                }
            });
        });
        scheduler.attachEvent("onEventDeleted", function(id) {
            $.ajax({
                type : "POST",
                url : "/admin/delEvent",
                data : {
                    "id" : id
                },
                success: function(data){
                }
            });
        });
    }
    init();
});