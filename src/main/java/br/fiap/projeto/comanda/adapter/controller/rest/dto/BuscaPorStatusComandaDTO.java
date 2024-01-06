package br.fiap.projeto.comanda.adapter.controller.rest.dto;

import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaPorStatusComandaDTO {
    private StatusComanda statusComanda;

}
