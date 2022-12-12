package com.projetofinal.api.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.api.domain.interfaces.IAtendimentoDomianService;
import com.projetofinal.api.domain.models.Atendimento;
import com.projetofinal.api.infrastructure.repositories.AtendimentoRepository;

@Service
public class AtendimentoService implements IAtendimentoDomianService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Override
	public Atendimento save(Atendimento atendimento) {
		return atendimentoRepository.save(atendimento);
	}

	@Override
	public List<Atendimento> findAll() {
		return atendimentoRepository.findAllByOrderByAssuntoAsc();
	}

	@Override
	public List<Atendimento> findAllById(Long idAtendimento) {
		return atendimentoRepository.findAllByIdAtendimento(idAtendimento);
	}

	
}
