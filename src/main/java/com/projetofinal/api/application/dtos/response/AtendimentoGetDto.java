package com.projetofinal.api.application.dtos.response;

import lombok.Data;

@Data
public class AtendimentoGetDto {
	
	private Long idAtendimento;
	private String data;
	private String hora;
	private String assunto;
	private String observacoes;
	private String emailCliente;

}
