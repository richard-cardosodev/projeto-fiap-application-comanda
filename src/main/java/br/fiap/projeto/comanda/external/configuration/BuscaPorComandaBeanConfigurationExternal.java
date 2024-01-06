package br.fiap.projeto.comanda.external.configuration;

import br.fiap.projeto.comanda.adapter.controller.BuscaPorComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.controller.port.IBuscaPorComandaControllerAdapter;
import br.fiap.projeto.comanda.adapter.gateway.BuscaPorComandaGatewayAdapter;
import br.fiap.projeto.comanda.external.repository.postgres.SpringComandaRepository;
import br.fiap.projeto.comanda.usecase.BuscaComandaUseCase;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.interfaces.IBuscaPorComandaUseCase;
import br.fiap.projeto.comanda.usecase.port.repository.IBuscarPorComandaRepositoryUseCase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuscaPorComandaBeanConfigurationExternal {

    @Bean
    IBuscaPorComandaUseCase buscaComandaUseCase(
            IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase)
            throws EntradaInvalidaException, Exception {
        return new BuscaComandaUseCase(buscarComandaRepositoryUseCase);
    }

    @Bean
    IBuscaPorComandaControllerAdapter buscaPorComandaControllerAdapter(
            IBuscaPorComandaUseCase buscaPorComandaUseCase) throws EntradaInvalidaException, Exception {
        return new BuscaPorComandaControllerAdapter(buscaPorComandaUseCase);
    }

    @Bean
    IBuscarPorComandaRepositoryUseCase buscarComandaRepositoryUseCase(
            SpringComandaRepository springComandaRepository) throws EntradaInvalidaException,
            Exception {
        return new BuscaPorComandaGatewayAdapter(springComandaRepository);
    }
}
