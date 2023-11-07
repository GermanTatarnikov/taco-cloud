package ru.gtatarnikov.tacocloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gtatarnikov.tacocloud.model.entity.Taco;
import ru.gtatarnikov.tacocloud.repository.TacoRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TacoService {
    TacoRepository tacoRepository;

    @Autowired
    public TacoService(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page);
    }

    public ResponseEntity<Taco> tacoById(Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        return optTaco.map(taco
                -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Taco saveTaco(Taco taco) {
        return tacoRepository.save(taco);
    }
}
