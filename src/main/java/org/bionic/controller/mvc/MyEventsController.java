package org.bionic.controller.mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bionic.dao.EventRepository;
import org.bionic.entity.Event;
import org.bionic.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by maximmaximov_2 on 08.11.16.
 */
@Controller
public class MyEventsController {

    @Autowired
    private EventService es;

    @RequestMapping(value="/panel/myevents", method = RequestMethod.GET)
    public String getEvents(){
        return "panel/myevents";
    }

    @RequestMapping(value = "/panel/myeventsdata", method = RequestMethod.GET)
    public String getDateEvents(@RequestParam("dt") String date, @RequestParam("qty") int qty, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("events", es.findClosest(dt, qty));
        return "panel/events_soon";
    }

    @RequestMapping(value = "/panel/myeventsdatanext", method = RequestMethod.GET)
    public String getDateEventsNext(@RequestParam("dt") String date, Model model) throws ParseException {
        Date dt = (new SimpleDateFormat("MM/dd/yyyy")).parse(date);
        model.addAttribute("event", es.findNext(dt));
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
