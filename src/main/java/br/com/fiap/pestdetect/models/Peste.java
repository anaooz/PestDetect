package br.com.fiap.pestdetect.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import br.com.fiap.pestdetect.controllers.ContaController;
import br.com.fiap.pestdetect.controllers.PesteController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Peste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomePopular;

    @NotBlank
    private String nomeCientifico;

    @NotBlank
    private String tipo;
    private String metodoDeControle;

    @ManyToOne
    private Conta conta;

    public EntityModel<Peste> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(PesteController.class).show(id)).withSelfRel(),
            linkTo(methodOn(PesteController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(PesteController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ContaController.class).show(this.getConta().getId())).withRel("conta")
        );
    }
}
