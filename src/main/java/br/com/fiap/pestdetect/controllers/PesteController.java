package br.com.fiap.pestdetect.controllers;

import java.util.Optional;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.pestdetect.exceptions.RestNotFoundException;
import br.com.fiap.pestdetect.models.Conta;
import br.com.fiap.pestdetect.models.Peste;
import br.com.fiap.pestdetect.repositories.ContaRepository;
import br.com.fiap.pestdetect.repositories.PesteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/peste")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "peste")
public class PesteController {
    
    @Autowired
    PesteRepository pesteRepository;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
        Page<Peste> pestes = (busca == null)?
            pesteRepository.findAll(pageable):
            pesteRepository.findByNomePopular(busca, pageable);

            return assembler.toModel(pestes.map(Peste::toEntityModel));
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhar peste",
        description = "Endpoint que recebe um id e retorna informações da peste."
    )
    public EntityModel<Peste> show(@PathVariable Long id){
        log.info("Detalhando peste {}", id);
        getPeste(id);

        return getPeste(id).toEntityModel();
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Peste cadastrada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    public ResponseEntity<Object> create(@RequestBody @Valid Peste peste){
        log.info("Cadastrando peste {}", peste);
        pesteRepository.save(peste);
        Optional<Conta> optionalConta = contaRepository.findById(peste.getConta().getId());
        peste.setConta(optionalConta.isPresent() ? optionalConta.get() : null);
        return ResponseEntity.status(HttpStatus.CREATED).body(peste.toEntityModel());
    }

    @PutMapping("{id}")
    public EntityModel<Peste> update(@PathVariable Long id, @RequestBody @Valid Peste peste) {
        log.info("Atualizando peste {}", peste);
        getPeste(id);

        peste.setId(id);
        pesteRepository.save(peste);

        return peste.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Peste> destroy(@PathVariable Long id){
        log.info("Deletando peste {}", id);
        getPeste(id);

        pesteRepository.delete(getPeste(id));

        return ResponseEntity.noContent().build();
    }

    private Peste getPeste(Long id){
        return pesteRepository.findById(id)
                                .orElseThrow(() -> new RestNotFoundException("Peste não encontrada"));
    }
}
