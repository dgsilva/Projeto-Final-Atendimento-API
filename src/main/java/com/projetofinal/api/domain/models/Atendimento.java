package com.projetofinal.api.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAtendimento;
	private String data;
	private String hora;
	private String assunto;
	private String observacoes;
	@Column(unique = true)
	private String emailCliente;
}
