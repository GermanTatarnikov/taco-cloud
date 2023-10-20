package ru.gtatarnikov.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ru.gtatarnikov.tacocloud.model.entity.Order;
import ru.gtatarnikov.tacocloud.model.entity.User;
import ru.gtatarnikov.tacocloud.service.OrderService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/current")
    public String orderForm() {
        return orderService.orderForm();
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        return orderService.processOrder(order, errors, sessionStatus, user);
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        return orderService.ordersForUser(user, model);
    }
}
