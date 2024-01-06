package br.fiap.projeto.comanda.adapter.controller.rest.dto;

import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

import java.util.UUID;

public class ComandaDTO {

	private UUID codigoComanda;

	private UUID codigoPedido;

	private StatusComanda status;

	public ComandaDTO() {
	}

	public ComandaDTO(UUID codigoComanda, UUID codigoPedido, StatusComanda status) {
		this.codigoComanda = codigoComanda;
		this.codigoPedido = codigoPedido;
		this.status = status;
	}

	public UUID getCodigoPedido() {
		return codigoPedido;
	}

	public StatusComanda getStatus() {
		return status;
	}

	public UUID getCodigoComanda() {
		return codigoComanda;
	}

	public Comanda toComanda() throws EntradaInvalidaException {
		return new Comanda(codigoComanda, codigoPedido, status);
	}

	public static ComandaDTO newInstanceFromComanda(Comanda comanda) {
		return new ComandaDTO(comanda.getCodigoComanda(), comanda.getCodigoPedido(), comanda.getStatus());
	}

	public void atualizaStatus(StatusComanda statusComanda) {
		this.status = statusComanda;

	}

}
