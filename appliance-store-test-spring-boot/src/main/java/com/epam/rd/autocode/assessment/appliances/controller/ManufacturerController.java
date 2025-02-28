package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Employee;
import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;
import com.epam.rd.autocode.assessment.appliances.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Loggable
    @GetMapping
    public String listManufacturers(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Manufacturer> manufacturersPage = manufacturerService.getManufacturersPage(PageRequest.of(page, size));
        model.addAttribute("manufacturers", manufacturersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", manufacturersPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "manufacture/manufacturers";
    }

    @Loggable
    @GetMapping("/add")
    public String showAddManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacture/newManufacturer";
    }

    @Loggable
    @PostMapping("/add-manufacturer")
    public String addManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
        manufacturerService.saveManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteManufacturer(@PathVariable("id") Long id) {
        manufacturerService.deleteManufacturerById(id);
        return "redirect:/manufacturers";
    }
}