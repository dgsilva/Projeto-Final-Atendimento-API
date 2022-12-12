package com.projetofinal.api.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetofinal.api.domain.models.Atendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	List<Atendimento>findAllByOrderByAssuntoAsc();
	
	List<Atendimento>findAllByIdAtendimento(Long idAtendimento); 
	
}
