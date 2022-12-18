package com.projetofinal.api.application.dtos.resquest;

import lombok.Data;

@Data
public class EmailMessageDto {
	
	private String to;
	private String subject;
	private String body;

}
