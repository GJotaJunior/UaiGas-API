package com.uai.uaigas.dto;

import java.util.Calendar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.uai.uaigas.entities.Posto;
import com.uai.uaigas.entities.Reclamacao;
import com.uai.uaigas.entities.enums.ReclamacaoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReclamacaoDTO {
	private Long id;
	@NotBlank(message = "Preenchimento obrigatorio")
	private String descricao;
	@NotNull
	private Calendar dataHora;
	@NotNull
	private ReclamacaoStatus reclamacaoStatus;
	@NotNull
	private Posto posto;
	
	public ReclamacaoDTO(Reclamacao entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
		this.dataHora = entity.getDataHora();
		this.reclamacaoStatus = entity.getReclamacaoStatus();
		this.posto = entity.getPosto();
	}
	
	public Reclamacao toEntity() {
		return new Reclamacao(id, descricao, dataHora, reclamacaoStatus, posto);
	}
}
