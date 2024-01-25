package br.fiap.projeto.comanda.external.configuration;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import br.fiap.projeto.comanda.usecase.port.repository.ICriarComandaRepositoryUseCase;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Configuration
public class PostgresComandaDataLoader {

    @Autowired
    private ICriarComandaRepositoryUseCase comandaRepositoryUseCase;

    @PostConstruct
    @SneakyThrows
    public void init() {
        List<Comanda> list = Arrays.asList(
                new Comanda(UUID.randomUUID(), UUID.fromString("9bd3e7ee-13f0-4197-9cfe-8e29972fffc2"), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.EM_PREPARACAO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.FINALIZADO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO)

        );
        list.stream().forEach(c -> {
            try {
                comandaRepositoryUseCase.criar(c);
            } catch (EntradaInvalidaException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }
}
