package ru.gtatarnikov.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gtatarnikov.tacocloud.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
