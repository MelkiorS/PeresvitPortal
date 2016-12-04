$(
    function () {
        $("#datepicker").datepicker({
            onSelect: function (dt, ob) {
                $.ajax({
                    type: "GET",
                    url: "/panel/myeventsdata",
                    data: {
                        "dt": dt,
                        "qty": 5
                    },
                    success: function (data) {
                        $('#closest').html(data);
                    }
                });
                $.ajax({
                    type: "GET",
                    url: "/panel/myeventsdatanext",
                    data: {
                        "dt": dt
                    },
                    success: function (data) {
                        $('#next').html(data);
                    }
                });
            }
        });
    }
);
