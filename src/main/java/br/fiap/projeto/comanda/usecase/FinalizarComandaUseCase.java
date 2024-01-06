package br.fiap.projeto.comanda.usecase;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import br.fiap.projeto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.exception.IntegracaoPedidoException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;

public class FinalizarComandaUseCase implements IAtualizarComandaUseCase {

    private final IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase;
    private final ComandaPedidoIntegration comandaPedidoIntegration;
    private final IAtualizarComandaRepositoryUseCase finalizaComandaRepositoryUseCase;

    public FinalizarComandaUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            IAtualizarComandaRepositoryUseCase finalizaComandaRepositoryUseCase) {
        this.buscarComandaPorCodigoPedidoRepositoryUseCase = buscarComandaPorCodigoPedidoRepositoryUseCase;
        this.comandaPedidoIntegration = comandaPedidoIntegration;
        this.finalizaComandaRepositoryUseCase = finalizaComandaRepositoryUseCase;
    }

    @Override
    public Comanda alterarStatus(UUID codigoPedido) throws EntradaInvalidaException, IntegracaoPedidoException {
        Comanda comanda = this.buscar(codigoPedido);
        if (!comanda.getStatus().equals(StatusComanda.EM_PREPARACAO)) {
            throw new EntradaInvalidaException(
                    "Status da comanda inválido para esta operação! Precisa estar em EM_PREPARACAO.");
        }

        comanda.atualizaStatus(StatusComanda.FINALIZADO);
        comanda = finalizaComandaRepositoryUseCase.atualizar(comanda);

        String codigo = enviarStatusPedido(comanda.getCodigoPedido());
        if (codigo.equals(null)) {
            throw new EntradaInvalidaException(comanda.getStatus().toString());
        }
        return comanda;
    }

    private Comanda buscar(UUID codigoPedido) throws EntradaInvalidaException {
        Optional<Comanda> comanda = buscarComandaPorCodigoPedidoRepositoryUseCase.buscar(codigoPedido);
        comanda.orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada!"));
        return comanda.get();
    }

    private String enviarStatusPedido(UUID codigoPedido) throws IntegracaoPedidoException {
        try {
            return comandaPedidoIntegration.prontificar(codigoPedido.toString()).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IntegracaoPedidoException("Erro ao tentar informar status pronto ao pedido!");
        }
    }

}
