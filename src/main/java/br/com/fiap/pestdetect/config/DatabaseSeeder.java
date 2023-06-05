package br.com.fiap.pestdetect.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.pestdetect.models.Conta;
import br.com.fiap.pestdetect.models.Peste;
import br.com.fiap.pestdetect.models.Usuario;
import br.com.fiap.pestdetect.repositories.ContaRepository;
import br.com.fiap.pestdetect.repositories.PesteRepository;
import br.com.fiap.pestdetect.repositories.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    ContaRepository contaRepository;

    @Autowired
    PesteRepository pesteRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta(1L, "Mateus", true);
        Conta c2 = new Conta(2L, "Amanda", true);
        Conta c3 = new Conta(3L, "Jean", true);
        Conta c4 = new Conta(4L, "Kaio", true);
        Conta c5 = new Conta(5L, "João", false);
        contaRepository.saveAll(List.of(c1, c2, c3, c4, c5));

        pesteRepository.saveAll(List.of(
            Peste.builder().nomePopular("Antracnose").nomeCientifico("Colletotrichum truncatum").tipo("Fungo").metodoDeControle("Fungicidas").conta(c4).build(),
            Peste.builder().nomePopular("Lagarta-do-cartucho").nomeCientifico("Spodoptera frugiperda").tipo("Lagarta").metodoDeControle("Inseticidas").conta(c5).build(),
            Peste.builder().nomePopular("Pulgão").nomeCientifico("Aphidoidea").tipo("Inseto").metodoDeControle("Inseticidas, predadores naturais.").conta(c3).build(),
            Peste.builder().nomePopular("Ferrugem da soja").nomeCientifico("Phakopsora pachyrhizi").tipo("Fungo").metodoDeControle("Fungicidas").conta(c1).build(),
            Peste.builder().nomePopular("Broca-do-café").nomeCientifico("Hypothenemus hampei").tipo("Besouro").metodoDeControle("Manejo integrado de pragas, aplicação de produtos químicos").conta(c2).build()
        ));

        usuarioRepository.save(Usuario.builder()
            .nome("Mateus Marchetti Vieira")
            .email("rm94075@fiap.com.br")
            .senha("$2a$12$QHVhfeJfzXr.My1gWJkZYe3gQYLuxGoOyHYEVOIXuAbDwJGBaC.iW")
            .build()
        );
    }
}
