package br.fiap.projeto.comanda.external.repository.entity;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
// @NoArgsConstructor
@Table(name = "comandas")

public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID codigoComanda;

    @Column(nullable = false, unique = true)
    private UUID codigoPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusComanda status;

    public ComandaEntity() {
    }

    public ComandaEntity(Comanda comanda) throws EntradaInvalidaException {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.status = comanda.getStatus();
    }

    public void atualizar(ComandaEntity comanda) {
        this.codigoComanda = comanda.getCodigoComanda();
        this.codigoPedido = comanda.getCodigoPedido();
        this.status = comanda.getStatus();
    }

    public Comanda toComanda() throws EntradaInvalidaException {
        return new Comanda(codigoComanda, codigoPedido, status);
    }
}
