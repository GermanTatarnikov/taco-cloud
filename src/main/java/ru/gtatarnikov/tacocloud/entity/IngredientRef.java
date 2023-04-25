package ru.gtatarnikov.tacocloud.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
public class IngredientRef {
    private final String ingredient;
}
