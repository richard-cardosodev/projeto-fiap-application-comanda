package br.fiap.projeto.comanda.external.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.fiap.projeto.comanda.adapter.controller.FinalizaComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.port.IAtualizaComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.gateway.FinalizaComandaGatewayAdapter;
import br.fiap.projeto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.FinalizarComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.interfaces.IAtualizarComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IAtualizarComandaRepositoryUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;

@Configuration
public class FinalizarComandaBeanConfigurationExternal {

    @Bean
    IAtualizarComandaUseCase finalizarComandaUseCase(
            IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase,
            ComandaPedidoIntegration comandaPedidoIntegration,
            IAtualizarComandaRepositoryUseCase finalizaComandaGatewayAdapter) {
        return new FinalizarComandaUseCase(buscarComandaPorCodigoPedidoRepositoryUseCase, comandaPedidoIntegration,
                finalizaComandaGatewayAdapter);
    }

    @Bean
    IAtualizaComandaControllerAdapter finalizaComandaControllerAdapter(
            IAtualizarComandaUseCase finalizarComandaUseCase) {
        return new FinalizaComandaControllerAdapter(finalizarComandaUseCase);
    }

    @Bean
    IAtualizarComandaRepositoryUseCase finalizaComandaGatewayAdapter(
            SpringComandaRepository springComandaRepository,
            IBuscarPorComandaRepositoryUseCase buscarPorComandaGatewayAdapter) {
        return new FinalizaComandaGatewayAdapter(springComandaRepository);
    }
}
