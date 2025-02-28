package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.model.Category;
import com.epam.rd.autocode.assessment.appliances.model.PowerType;
import com.epam.rd.autocode.assessment.appliances.service.ApplianceService;
import com.epam.rd.autocode.assessment.appliances.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appliances")
public class ApplianceController {

    private final ApplianceService applianceService;
    private final ManufacturerService manufacturerService;

    @Autowired
    public ApplianceController(ApplianceService applianceService, ManufacturerService manufacturerService) {
        this.applianceService = applianceService;
        this.manufacturerService = manufacturerService;
    }

    @Loggable
    @GetMapping
    public String listAppliances(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Appliance> appliancePage = applianceService.getAppliancesPage(PageRequest.of(page, size));
        model.addAttribute("appliances", appliancePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", appliancePage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "appliance/appliances";
    }

    @Loggable
    @GetMapping("/add")
    public String showAddApplianceForm(Model model) {
        model.addAttribute("appliance", new Appliance());

        model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());

        model.addAttribute("categories", Category.values());
        model.addAttribute("powerTypes", PowerType.values());

        return "appliance/newAppliance";
    }

    @Loggable
    @PostMapping("/add-appliance")
    public String addAppliance(@Valid @ModelAttribute("appliance") Appliance appliance, BindingResult result,  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("appliance", appliance);
            model.addAttribute("manufacturers", manufacturerService.getAllManufacturers());

            model.addAttribute("categories", Category.values());
            model.addAttribute("powerTypes", PowerType.values());
            return "appliance/newAppliance";
        }

        applianceService.saveAppliance(appliance);
        return "redirect:/appliances";
    }

    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteAppliance(@PathVariable("id") Long id) {
        applianceService.deleteApplianceById(id);
        return "redirect:/appliances";
    }
}