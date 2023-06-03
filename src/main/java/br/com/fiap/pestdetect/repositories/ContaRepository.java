package br.com.fiap.pestdetect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.pestdetect.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    
}
