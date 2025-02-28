package com.epam.rd.autocode.assessment.appliances.controller;

import com.epam.rd.autocode.assessment.appliances.aspect.Loggable;
import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.model.OrderRow;
import com.epam.rd.autocode.assessment.appliances.model.Orders;
import com.epam.rd.autocode.assessment.appliances.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService ordersService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final ApplianceService applianceService;
    private final OrderRowService orderRowService;

    @Autowired
    public OrdersController(OrderService ordersService, ClientService clientService,
                            EmployeeService employeeService, ApplianceService applianceService,
                            OrderRowService orderRowService) {
        this.ordersService = ordersService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.applianceService = applianceService;
        this.orderRowService = orderRowService;
    }

    @Loggable
    @GetMapping
    public String listOrders(Model model) {
        List<Orders> orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @Loggable
    @GetMapping("/add")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new Orders());

        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("employees", employeeService.getAllEmployees());

        return "order/newOrder";
    }

    @Loggable
    @PostMapping("/add-order")
    public String addOrder(@ModelAttribute("order") Orders order) {
        ordersService.saveOrder(order);
        return "redirect:/orders";
    }

    @Loggable
    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable("id") Long id) {
        ordersService.deleteOrderById(id);
        return "redirect:/orders";
    }

    @Loggable
    @GetMapping("/{id}/approved")
    public String approveOrder(@PathVariable("id") Long id) {
        Orders order = ordersService.getOrderById(id);
        order.setApproved(true);
        ordersService.saveOrder(order);
        return "redirect:/orders";
    }

    @Loggable
    @GetMapping("/{id}/unapproved")
    public String unapproveOrder(@PathVariable("id") Long id) {
        Orders order = ordersService.getOrderById(id);
        order.setApproved(false);
        ordersService.saveOrder(order);
        return "redirect:/orders";
    }

    @Loggable
    @GetMapping("/{id}/edit")
    public String showEditOrderForm(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrderById(id);

        model.addAttribute("order", order);
        model.addAttribute("rows", order.getOrderRowSet());

        return "order/editOrder";
    }

    @Loggable
    @GetMapping("/{id}/choice-appliance")
    public String choiceAppliance(@PathVariable("id") Long id, Model model) {
        Orders order = ordersService.getOrderById(id);

        List<Appliance> appliances = applianceService.getAllAppliances();

        model.addAttribute("appliances", appliances);
        model.addAttribute("ordersId", id);
        return "order/choiceAppliance";
    }

    @Loggable
    @PostMapping("/{id}/add-into-order")
    public String addIntoOrder(
            @PathVariable("id") Long id,
            @RequestParam("ordersId") Long ordersId,
            @RequestParam("applianceId") Long applianceId,
            @RequestParam("numbers") Integer numbers,
            @RequestParam("price") Double price
    ) {
        Orders order = ordersService.getOrderById(ordersId);

        Appliance appliance = applianceService.getApplianceById(applianceId);

        OrderRow orderRow = new OrderRow();
        orderRow.setAppliance(appliance);
        orderRow.setNumber(Long.valueOf(numbers));
        orderRow.setAmount(new BigDecimal(price));
        orderRowService.saveOrderRow(orderRow);

        order.getOrderRowSet().add(orderRow);
        ordersService.saveOrder(order);

        return "redirect:/orders/" + ordersId + "/choice-appliance";
    }

    @Loggable
    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable("id") Long id, @ModelAttribute("order") Orders updatedOrder) {
        Orders existingOrder = ordersService.getOrderById(id);
        existingOrder.setApproved(updatedOrder.getApproved());

        ordersService.saveOrder(existingOrder);

        return "redirect:/orders";
    }
}