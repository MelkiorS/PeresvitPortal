	var eventsPath = _all ? "/panel/eventsdatajson" : "/panel/myeventsdatajson";
	init();
	window.onload = function() {
		removeStyle();
	};

	function init() {
		scheduler.config.xml_date="%Y-%m-%d %H:%i";
		scheduler.init('scheduler_here',new Date(),"day");

		scheduler.load(eventsPath + "?dt=" + fmtDate(startMonth(new Date())) + "&qty=1000", 'json',function(){
			scheduler.renderCalendar({
				container:"mini_here",
				date:scheduler._date,
				navigation:true,
				handler:function(date,calendar){
					removeStyle();
				}
			});
		});
		scheduler.afterRender = function(d) {
			fillMonthEvents(startMonth(d));
		};
	}

	function fillMonthEvents(d) {
		$.ajax({
			type: "GET",
			url: eventsPath,
			data: {
				"dt": fmtDate(d),
				"qty": 5
			},
			success: function (data) {
				$('#close5events').empty();
				for (var i=0;i<data.length;i++) {
					if (new Date(data[i].start_date).getMonth() <= d.getMonth()) $('#close5events').append('<li class="event-li red">' + data[i].text + '</li>');
				}
			}
		});
	}

	function removeStyle() {
		$('#mini_here').find(".dhx_calendar_click").removeClass('dhx_calendar_click')
	}

	function d2(v) {
		return v<=9 ? "0"+v : ""+v
	}

	function fmtDate(d) {
		return d2(d.getMonth()+1) + "/" + d2(d.getDate()) + "/" + d.getFullYear()
	}

	function startMonth(d) {
		res = new Date(d.getTime());
		res.setDate(1);
		res.setHours(0);
		res.setMinutes(0);
		res.setSeconds(0);
		res.setMilliseconds(0);
		return res;
	}
	