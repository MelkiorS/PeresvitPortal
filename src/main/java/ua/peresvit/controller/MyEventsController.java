package ua.peresvit.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.peresvit.entity.Event;
import ua.peresvit.service.EventService;
import ua.peresvit.service.UserService;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MyEventsController {

    @Autowired
    private EventService es;

    @Autowired
    private UserService us;

    @RequestMapping(value="/panel/myevents", method = RequestMethod.GET)
    public String getEvents(){
        return "panel/myevents";
    }

    @RequestMapping(value = "/panel/myeventsdata", method = RequestMethod.GET)
    public String getDateMyEvents(@RequestParam("dt") String date, @RequestParam("qty") int qty, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("events", es.findClosestByCurrentUser(dt, qty));
        model.addAttribute("me", us.getCurrentUser());
        return "panel/events_soon";
    }

    @RequestMapping(value = "/panel/eventsdata", method = RequestMethod.GET)
    public String getDateEvents(@RequestParam("dt") String date, @RequestParam("qty") int qty, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("events", es.findClosest(dt, qty));
        model.addAttribute("me", us.getCurrentUser());
        return "panel/events_soon";
    }

    @RequestMapping(value = "/panel/myeventsdatanext", method = RequestMethod.GET)
    public String getDateMyEventsNext(@RequestParam("dt") String date, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("event", es.findNextByCurrentUser(dt));
        model.addAttribute("me", us.getCurrentUser());
        return "panel/events_next";
    }

    @RequestMapping(value = "/panel/eventsdatanext", method = RequestMethod.GET)
    public String getDateEventsNext(@RequestParam("dt") String date, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("event", es.findNext(dt));
        model.addAttribute("me", us.getCurrentUser());
        return "panel/events_next";
    }

    @RequestMapping(value="/admin/myeventsperiod", method = RequestMethod.GET)
    @ResponseBody
    public String getPeriodEvents(@RequestParam("start") String start, @RequestParam("finish") String finish, Model model)  throws ParseException {

        Gson g = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm").create();
        Date dtStart = (new SimpleDateFormat("yyyyMMdd")).parse(start);
        Date dtFinish = (new SimpleDateFormat("yyyyMMdd")).parse(finish);
        String res = g.toJson(es.getPeriod(dtStart, dtFinish));
        return res;
    }

    @RequestMapping(value = "/admin/addEvent", method = RequestMethod.POST)
    @ResponseBody
//    public String updEvent(@RequestParam("start_date") String start, @RequestParam("end_date") String end, @RequestParam("text") String text, @RequestParam("!nativeeditor_status") String status) throws URISyntaxException {
    public String addEvent(@RequestParam("start_date") Long start, @RequestParam("end_date") Long end, @RequestParam("text") String text) throws URISyntaxException {
        Event ev = new Event();
        ev.setName(text);
        ev.setStart(new Date(start));
        ev.setFinish(new Date(end));

        return String.valueOf(es.create(ev));
    }

    @RequestMapping(value = "/admin/updEvent", method = RequestMethod.POST)
    @ResponseBody
    public String updEvent(@RequestParam("id") long id, @RequestParam("start_date") Long start, @RequestParam("end_date") Long end, @RequestParam("text") String text) {
        Event ev = es.findById(id);
        ev.setName(text);
        ev.setStart(new Date(start));
        ev.setFinish(new Date(end));
        return String.valueOf(es.update(ev));
    }

    @RequestMapping(value = "/admin/delEvent", method = RequestMethod.POST)
    @ResponseBody
    public String updEvent(@RequestParam("id") long id) {
        return String.valueOf(es.delete(es.findById(id)));
    }

    @RequestMapping(value = "/admin/event_edit", method = RequestMethod.GET)
    public String editEvents(){
        return "/admin/event_edit";
    }

}
