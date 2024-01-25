package br.fiap.projeto.comanda;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public class AlterarStatusUseCaseTest {

    @InjectMocks
    private FinalizarComandaUseCase finalizarComandaUseCase;

    @Mock
    private IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase;

    @Mock
    private ComandaPedidoIntegration comandaPedidoIntegration;

    @Mock
    private IAtualizarComandaRepositoryUseCase finalizaComandaRepositoryUseCase;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
         autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (autoCloseable != null) {
            autoCloseable.close();
        }
    }

    @Test
    public void alterarStatusUseCaseTest() throws EntradaInvalidaException {

        Comanda comanda = new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO);

        Mockito.when(buscarComandaPorCodigoPedidoRepositoryUseCase.buscar(Mockito.any())).thenReturn(Optional.of(comanda));
        Mockito.when(finalizaComandaRepositoryUseCase.atualizar(Mockito.any())).thenAnswer(in -> {
            comanda.atualizaStatus(StatusComanda.FINALIZADO);
            return comanda;
        });
        Mockito.when(comandaPedidoIntegration.prontificar(Mockito.anyString())).thenReturn(ResponseEntity.ok(null));

        Assertions.assertThrows(EntradaInvalidaException.class, () -> {
            finalizarComandaUseCase.alterarStatus(UUID.randomUUID());
        });

    }
}
