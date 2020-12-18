package com.uai.uaigas.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb_combustivel_posto")
public class CombustivelPosto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@JoinColumn(name = "tipo_combustivel_id")
	private TipoCombustivel tipo;

	@JoinColumn(name = "combustivel_id")
	private Combustivel combustivel;

	@JoinColumn(name = "posto_id")
	private Posto posto;

	private Cotacao cotacao;

}
