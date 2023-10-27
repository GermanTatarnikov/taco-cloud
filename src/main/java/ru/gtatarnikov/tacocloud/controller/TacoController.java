package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gtatarnikov.tacocloud.model.entity.Taco;
import ru.gtatarnikov.tacocloud.service.TacoService;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
    TacoService tacoService;

    @Autowired
    public TacoController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        return tacoService.recentTacos();
    }
}
