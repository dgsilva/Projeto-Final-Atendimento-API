package com.projetofinal.api.application.dtos.response;

import com.projetofinal.api.application.dtos.resquest.EmailMessageDto;
import com.projetofinal.api.domain.models.Atendimento;

public class AtendimentoEmailHelper {
	
	public static EmailMessageDto gerarMensagemDeCriacaoConta(Atendimento atendimento) {
		String  to = atendimento.getEmailCliente(); 
		String subject = "Parabéns, sua conta de atendimento foi criada com sucesso";
		String body = "Olá " + atendimento.getAssunto()
						+ "\n\n"
						+ "Foi registrado o seu serviço  com sucesso na Empresa DBS"
						+ "\n"
						+ "Seus dados são:"
						+ "\nData: "+atendimento.getData()
						+"\nHora: " + atendimento.getHora()
						+"\nAssunto:" + atendimento.getAssunto()
						+"\nObsrvações:" +atendimento.getObservacoes()
						+"\nEmail:"+ atendimento.getEmailCliente()
						+"\n\nAtt"
						+"\nEquipe DBS info Sistema";
		
		EmailMessageDto dto = new EmailMessageDto();
		
		dto.setTo(to);
		dto.setSubject(subject);
		dto.setBody(body);
		
		return dto;
		
	}

}
