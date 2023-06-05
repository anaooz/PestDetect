package br.com.fiap.pestdetect.controllers;

import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.pestdetect.exceptions.RestNotFoundException;
import br.com.fiap.pestdetect.models.Conta;
import br.com.fiap.pestdetect.models.Credencial;
import br.com.fiap.pestdetect.repositories.ContaRepository;
import br.com.fiap.pestdetect.service.TokenJwtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class ContaController {
    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ContaRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenJwtService tokenJwtService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Conta> registrar(@RequestBody @Valid Conta conta) {
        conta.setSenha(encoder.encode(conta.getSenha()));
        repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenJwtService.generateToken(credencial);
        return ResponseEntity.ok(token);
    }
    
    @GetMapping("/api/contas")
    public List<Conta> index() {
        return repository.findAll();
    }

    @GetMapping("/api/contas/{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("Detalhando conta {}", id);
        return ResponseEntity.ok(getConta(id));
    }



    @PutMapping("/api/contas/{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta conta){
        log.info("Atualizando conta {}", id);
        getConta(id);
        conta.setId(id);
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }

    @DeleteMapping("/api/contas/{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id) {
        log.info("Apagando conta {}", id);
        var conta = getConta(id);
        repository.delete(conta);
        return ResponseEntity.noContent().build();
    }

    private Conta getConta(Long id){
        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Conta não encontrada"));
    }
}
