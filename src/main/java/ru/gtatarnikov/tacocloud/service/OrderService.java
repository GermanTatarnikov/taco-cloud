package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import ru.gtatarnikov.tacocloud.entity.TacoOrder;
import ru.gtatarnikov.tacocloud.entity.User;
import ru.gtatarnikov.tacocloud.repository.OrderRepository;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String orderForm() {
        return "orderForm";
    }

    @PostAuthorize("hasRole('ADMIN') ||" +
            "returnObject.user.username == authentication.name")
    public TacoOrder getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order doesn't found"));
    }

    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setPlacedAt(new Date());
        order.setUser(user);
        orderRepository.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
