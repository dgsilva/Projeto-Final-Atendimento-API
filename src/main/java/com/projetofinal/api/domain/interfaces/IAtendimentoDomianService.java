package com.projetofinal.api.domain.interfaces;

import java.util.List;

import com.projetofinal.api.domain.models.Atendimento;

public interface IAtendimentoDomianService {

	Atendimento save(Atendimento atendimento);
	
	List<Atendimento> findAll();
	
	List<Atendimento> findAllById(Long idAtendimento);
}
