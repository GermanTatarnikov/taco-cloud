package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.gtatarnikov.tacocloud.model.entity.Order;
import ru.gtatarnikov.tacocloud.model.entity.User;
import ru.gtatarnikov.tacocloud.service.OrderService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/orders")
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

    @PutMapping(value = "/{orderId}", consumes = "application/json")
    public Order putOrder(@PathVariable("orderId") Long orderId,
                          @RequestBody Order order) {
        return orderService.putOrder(orderId, order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        return orderService.patchOrder(orderId, patch);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
