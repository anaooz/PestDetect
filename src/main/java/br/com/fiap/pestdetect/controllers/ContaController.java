package br.com.fiap.pestdetect.controllers;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.pestdetect.exceptions.RestNotFoundException;
import br.com.fiap.pestdetect.models.Conta;
import br.com.fiap.pestdetect.repositories.ContaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contas")
public class ContaController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ContaRepository repository;

    @GetMapping
    public List<Conta> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("Detalhando conta {}", id);
        return ResponseEntity.ok(getConta(id));
    }

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody @Valid Conta conta) {
        log.info("Cadastrando conta {}", conta);
        repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta conta){
        log.info("Atualizando conta {}", id);
        getConta(id);
        conta.setId(id);
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping
    public ResponseEntity<Conta> destroy(@PathVariable Long id) {
        log.info("Apagando conta {}", id);
        var conta = getConta(id);
        conta.setAtiva(false);
        repository.save(conta);
        return ResponseEntity.noContent().build();
    }

    private Conta getConta(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Conta não encontrada"));
    }
}
