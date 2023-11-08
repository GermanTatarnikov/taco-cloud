package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public Order putOrder(Long orderId, Order order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @Transactional
    public Order patchOrder(Long orderId, Order patch) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order doesn't found"));
        patchOrder(order, patch);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }

    public String ordersForUser(User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }

    private static void patchOrder(Order order, Order patch) {
        if (patch.getDeliveryName() != null)
            order.setDeliveryName(patch.getDeliveryName());

        if (patch.getDeliveryCity() != null)
            order.setDeliveryCity(patch.getDeliveryCity());

        if (patch.getDeliveryState() != null)
            order.setDeliveryState(patch.getDeliveryState());

        if (patch.getDeliveryZip() != null)
            order.setDeliveryZip(patch.getDeliveryZip());

        if (patch.getCcNumber() != null)
            order.setCcNumber(patch.getCcNumber());

        if (patch.getCcExpiration() != null)
            order.setCcExpiration(patch.getCcExpiration());

        if (patch.getCcCVV() != null)
            order.setCcCVV(patch.getCcCVV());
    }
}