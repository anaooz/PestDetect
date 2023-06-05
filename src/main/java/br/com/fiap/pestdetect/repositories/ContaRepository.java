package br.com.fiap.pestdetect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.pestdetect.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findByEmail(String email);
}
