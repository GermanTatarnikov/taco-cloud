package ru.gtatarnikov.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        return tacoService.tacoById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoService.saveTaco(taco);
    }
}
