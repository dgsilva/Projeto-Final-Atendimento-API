package com.projetofinal.api.application.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetofinal.api.application.dtos.response.AtendimentoEmailHelper;
import com.projetofinal.api.application.dtos.response.AtendimentoGetDto;
import com.projetofinal.api.application.dtos.resquest.AtendimentoDto;
import com.projetofinal.api.application.dtos.resquest.EmailMessageDto;
import com.projetofinal.api.domain.models.Atendimento;
import com.projetofinal.api.domain.services.AtendimentoService;
import com.projetofinal.api.infrastructure.repositories.producers.EmailMessageProducer;
import com.projetofinal.api.infrastructure.repositories.security.TokenAuthenticationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api("Atendimento")
@RestController
@RequestMapping("/v1/atendimentos")
public class AtendimentoController {

	@Autowired
	private AtendimentoService atendimentoService;
	
	@Autowired
	private EmailMessageProducer emailMessageProducer;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenAuthenticationService  authenticationService;

	@ApiOperation("Salvando o atendimento")
	@PostMapping()
	public ResponseEntity<AtendimentoGetDto> create(@RequestBody AtendimentoDto dto, HttpServletRequest request) throws JsonProcessingException {
		
		//capturando o token
		//Header -> ['Authorization', 'Bearer <<Token>>']
		String acessToken = request.getHeader("Authorization").replace("Bearer", "").trim();
		String user = authenticationService.getUserFromToken(acessToken);
		ModelMapper modelMapper = new ModelMapper();
		Atendimento atendimento = atendimentoService.save(modelMapper.map(dto, Atendimento.class));
		AtendimentoGetDto response = modelMapper.map(atendimento, AtendimentoGetDto.class);
		EmailMessageDto emailMessageDto = AtendimentoEmailHelper.gerarMensagemDeCriacaoConta(atendimento);
		String message = objectMapper.writeValueAsString(emailMessageDto);
		emailMessageProducer.send(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}

	@GetMapping()
	public ResponseEntity<List<AtendimentoGetDto>> findAll(HttpServletRequest request) {
		// capturando o token
		// Header -> ['Authorization', 'Bearer <<Token>>']
		String acessToken = request.getHeader("Authorization").replace("Bearer", "").trim();
		String user = authenticationService.getUserFromToken(acessToken);
		
		ModelMapper modelMapper = new ModelMapper();
		List<Atendimento> atendimentos = atendimentoService.findAll();
		List<AtendimentoGetDto> dto = modelMapper.map(atendimentos, new TypeToken<List<AtendimentoGetDto>>() {
		}.getType());
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
