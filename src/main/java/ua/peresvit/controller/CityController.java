package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.peresvit.entity.City;
import ua.peresvit.service.CityService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/city")
public class CityController {

    @Autowired
    private CityService cityService;

    //go to manage page
    @RequestMapping(value = "/management", method = RequestMethod.GET)
    public String goToManagement(Model model) {
        return "admin/city/cityManagement";
    }

    //go to addForm
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String goToAddForm(Model model) {
        City city = new City();

        model.addAttribute(city);

        return "admin/city/addCity";
    }

    // create city
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createCity(City city, RedirectAttributes model) {

        cityService.save(city);

        model.addAttribute("cityId", city.getCityId());
        model.addFlashAttribute("city", city);

        return "redirect:/admin/city/{cityId}";
    }

    // show City by id
    @RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public String geCity(@PathVariable("cityId") long cityId, Model model) {
        if (!model.containsAttribute("cityId"))
            model.addAttribute("cityId", cityService.findOne(cityId));
        return "admin/city/cityProfile";
    }

    // show all citys
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listAllUsers(Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cityList", cities);
        model.addAttribute("city", new City());
        return "admin/city/allCities";
    }

    // edit city
    @RequestMapping(value = "/edit/{cityId}", method = RequestMethod.GET)
    public String editCity(@PathVariable("cityId")  long cityId,
                           Model model) {
        City city = cityService.findOne(cityId);
        model.addAttribute("city", city);
        return "admin/city/addCity";
    }

}
