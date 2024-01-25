package br.fiap.projeto.comanda.external.configuration;

import br.fiap.projeto.comanda.adapter.gateway.BuscaPorComandaPorCodigoPedidoGatewayAdapter;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaPorCodigoPedidoRepositoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaComandaPorCodigoPedidoBeanConfigurationExternal {

    @Bean
    IBuscarPorComandaPorCodigoPedidoRepositoryUseCase buscarComandaPorCodigoPedidoRepositoryUseCase(
            SpringComandaRepository springComandaRepository) throws EntradaInvalidaException,
            Exception {
        return new BuscaPorComandaPorCodigoPedidoGatewayAdapter(springComandaRepository);
    }
}
