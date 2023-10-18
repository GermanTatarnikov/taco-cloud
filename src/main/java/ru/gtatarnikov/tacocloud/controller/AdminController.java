package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gtatarnikov.tacocloud.service.OrderAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    OrderAdminService adminService;

    @Autowired
    public AdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        return adminService.deleteAllOrders();
    }
}
