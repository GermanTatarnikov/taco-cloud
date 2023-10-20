package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;
import ru.gtatarnikov.tacocloud.model.entity.Order;
import ru.gtatarnikov.tacocloud.model.entity.User;
import ru.gtatarnikov.tacocloud.props.OrderProps;
import ru.gtatarnikov.tacocloud.repository.OrderRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProps orderProps;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    public String orderForm() {
        return "orderForm";
    }

    @PostAuthorize("hasRole('ADMIN') ||" +
            "returnObject.user.username == authentication.name")
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order doesn't found"));
    }

    @Transactional
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, User user) {
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

    public String ordersForUser(User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
