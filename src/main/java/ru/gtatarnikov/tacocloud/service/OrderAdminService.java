package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.gtatarnikov.tacocloud.repository.OrderRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
public class OrderAdminService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderAdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAllOrders() {
        orderRepository.deleteAll();
        log.info("Orders deleted");
        return "redirect:/admin";
    }
}
