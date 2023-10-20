package ru.gtatarnikov.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gtatarnikov.tacocloud.model.entity.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
