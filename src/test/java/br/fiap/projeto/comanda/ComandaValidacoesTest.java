package br.fiap.projeto.comanda;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import br.fiap.projeto.comanda.external.integration.ComandaPedidoIntegration;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import org.junit.jupiter.api.Test;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ComandaValidacoesTest {

    @Autowired
    private MockMvc mockMvc;
    private MvcResult response;

    private String codigoPedido;

    @MockBean
    private ComandaPedidoIntegration comandaPedidoIntegrationMock;

    @Test
    public void codigoComandaNulo() throws EntradaInvalidaException {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(null, UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void codigoPedidoNulo() {

        assertThrows(
                EntradaInvalidaException.class,
                () -> new Comanda(UUID.randomUUID(), null, StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void statusComandaNulo() {

        assertThrows(
                NullPointerException.class,
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), null),
                "Mensagem de erro");
    }

    @Test
    public void comandaOk() {

        assertDoesNotThrow(
                () -> new Comanda(UUID.randomUUID(), UUID.randomUUID(), StatusComanda.RECEBIDO),
                "Mensagem de erro");
    }

    @Test
    public void testaCriarComanda() throws Exception {
        UUID codigoPedido = UUID.randomUUID();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/comandas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoPedido\": \"" + codigoPedido + "\"}"))
                .andExpect(status().is2xxSuccessful())  // Espera um código de resposta 200 (OK)
//                .andExpect(content().string("Codigo Pedido: " + codigoPedido))
                .andReturn();
    }

    @Test
    public void testaCriarComandaDuplicada() throws Exception {
        UUID codigoPedido = UUID.fromString("9bd3e7ee-13f0-4197-9cfe-8e29972fffc2");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/comandas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoPedido\": \"" + codigoPedido + "\"}"))
                .andExpect(status().is4xxClientError())  // Espera um código de resposta 4xxx (Error)
//                .andExpect(content().string("Codigo Pedido: " + codigoPedido))
                .andReturn();
    }

    @Test
    public void testaBuscarStatusRecebido() throws Exception {
        response = mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/comandas/busca-recebido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
//                .andExpect(MockMvcResultMatchers.content().string("Resultado Esperado"));
    }

    @Test
    public void testaPrepararComanda() throws Exception {
//        Chama buscar status preparacao
        testaBuscarStatusRecebido();
//        Serializar o Json
        String resultado = response.getResponse().getContentAsString();
        String codigoPedido = converteEmSubstring(resultado);
        System.out.println(resultado);

//       Mudar Status para Finalizado
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .patch("http://localhost:8080/comandas/"+codigoPedido+"/preparar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"codigoPedido\": \"" + codigoPedido + "\"}"))
                .andExpect(status().is2xxSuccessful())  // Espera um código de resposta 200 (OK)
//                .andExpect(content().string("Codigo Pedido: " + codigoPedido))
                .andReturn();
    }

    @Test
    public void testaBuscarStatusPreparacao() throws Exception {
        response =   mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/comandas/busca-preparacao")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
//                .andExpect(MockMvcResultMatchers.content().string("Resultado Esperado"));
    }

   @Test
    public void testaFinalizarComanda() throws Exception {
//        Chama buscar status preparacao
        testaBuscarStatusPreparacao();
//        Serializar o Json
        String resultado = response.getResponse().getContentAsString();
        codigoPedido = converteEmSubstring(resultado);
        System.out.println(resultado);

       // Configurar o comportamento do mock para prontificar
       Mockito.when(comandaPedidoIntegrationMock.prontificar(codigoPedido))
               .thenReturn(ResponseEntity.ok("200"));
       // Executar o teste para o endpoint PATCH
       MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                       .patch("http://localhost:8080/comandas/" + codigoPedido + "/finalizar")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"codigoPedido\": \"" + codigoPedido + "\"}"))
               .andExpect(status().is2xxSuccessful())
               .andReturn();

       // Verificar o comportamento do mock
       Mockito.verify(comandaPedidoIntegrationMock, Mockito.times(1)).prontificar(codigoPedido);
    }

    @Test
    public void testaBuscarStatusFinalizado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/comandas/busca-finalizado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().string("Resultado Esperado"));
    }

//    Metódos auxiliares==========================================

    private String converteEmSubstring(String responseBody) {
        String[] resultadoDivisao = responseBody.split("},");
        resultadoDivisao = resultadoDivisao[0].split(",", 3);
        resultadoDivisao = resultadoDivisao[1].split(":");
        return (resultadoDivisao[1].substring(1, 37));
    }

}
