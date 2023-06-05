package br.com.fiap.pestdetect.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.pestdetect.models.Peste;

public interface PesteRepository extends JpaRepository<Peste, Long>{
    Page<Peste> findByNomePopular(String busca, Pageable pageable);
    Page<Peste> findByNomeCientifico(String busca, Pageable pageable);
}
