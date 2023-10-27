package ru.gtatarnikov.tacocloud.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.tacocloud.model.entity.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    Iterable<Taco> findAll(PageRequest page);
}
