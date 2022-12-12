package com.projetofinal.api.application.dtos.resquest;

import lombok.Data;

@Data
public class AtendimentoDto {

	private Long idAtendimento;
	private String data;
	private String hora;
	private String assunto;
	private String observacoes;
	private String emailCliente;
}
