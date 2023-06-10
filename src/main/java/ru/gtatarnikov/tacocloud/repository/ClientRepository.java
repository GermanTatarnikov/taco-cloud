package ru.gtatarnikov.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.tacocloud.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByUsername(String username);
}