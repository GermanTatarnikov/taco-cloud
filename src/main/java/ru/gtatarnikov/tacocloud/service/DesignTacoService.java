package ru.gtatarnikov.tacocloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.gtatarnikov.tacocloud.entity.Ingredient;
import ru.gtatarnikov.tacocloud.entity.Taco;
import ru.gtatarnikov.tacocloud.entity.TacoOrder;
import ru.gtatarnikov.tacocloud.repository.IngredientRepository;

@Slf4j
@Service
public class DesignTacoService {
    private final IngredientRepository ingredientRepository;

    public DesignTacoService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Iterable<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public String showDesignForm() {
        return "design";
    }

    public String processTaco(Taco taco, Errors errors, TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
