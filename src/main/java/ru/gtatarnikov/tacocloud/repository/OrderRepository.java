package ru.gtatarnikov.tacocloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.tacocloud.model.entity.Order;
import ru.gtatarnikov.tacocloud.model.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}