package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.model.Client;
import com.epam.rd.autocode.assessment.appliances.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Loggable
    @GetMapping
    public String listClients(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Client> clientsPage = clientService.getClientsPage(PageRequest.of(page, size));
        model.addAttribute("clients", clientsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clientsPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "client/clients";
    }

    @Loggable
    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/newClient";
    }

    @Loggable
    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("client") Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "client/newClient";
        }

        if (clientService.emailExists(client.getEmail())) {
            result.rejectValue("email", "user.email.must.be.unique", "Email is already taken");
            return "client/newClient";
        }

        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClientById(id);
        return "redirect:/clients";
    }
}