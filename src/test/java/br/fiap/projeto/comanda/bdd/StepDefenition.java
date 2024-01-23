package br.fiap.projeto.comanda.bdd;

import br.fiap.projeto.comanda.entity.Comanda;
import br.fiap.projeto.comanda.entity.enums.StatusComanda;
import br.fiap.projeto.comanda.usecase.exception.EntradaInvalidaException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class StepDefenition {

    private UUID codigoComanda;

    private UUID codigoPedido;

    private StatusComanda status;

    private Comanda comanda;

    private Response response;

    private final String ENDPOINT_API_CRIAR_COMANDA = "http://localhost:8080/comanda/comandas";

    @Given("que recebi os dados de uma comanda")
    public void que_recebi_os_dados_de_uma_comanda() throws EntradaInvalidaException {
        codigoComanda = UUID.randomUUID();
        codigoPedido = UUID.randomUUID();
        status = StatusComanda.RECEBIDO;



    }
    @When("invoco o endpoint de criar comanda")
    public void invoco_o_endpoint_de_criar_comanda() {

      response = RestAssured.given()
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .body(codigoPedido)
              .when()
              .post(ENDPOINT_API_CRIAR_COMANDA);

    }
    @Then("a comanda é exibida")
    public void a_comanda_é_exibida() {
        response.then()
                .statusCode(HttpStatus.CREATED.value());

    }


    @Then("crio uma nova comanda")
    public void crio_uma_nova_comanda() throws EntradaInvalidaException {
         comanda = new Comanda(codigoComanda, codigoPedido, status);
    }

    @Then("Exibo a comanda")
    public void exibo_a_comanda() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("que possuo comanda para ser preparada")
    public void que_possuo_comanda_para_ser_preparada() {
       String resultado = "falhou";
    }
    @When("realizo a preparacao")
    public void realizo_a_preparacao() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("que possuo comanda para ser finalizada")
    public void que_possuo_comanda_para_ser_finalizada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("realizo a finalizacao")
    public void realizo_a_finalizacao() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("exibo o resultado")
    public void exibo_o_resultado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Given("que possuo comanda com status preparacao")
    public void que_possuo_comanda_com_status_preparacao() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }




    @Given("que possuo comanda com status recebido")
    public void que_possuo_comanda_com_status_recebido() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("busco essas comandas")
    public void busco_essas_comandas() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("exibo a busca")
    public void exibo_a_busca() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("que possuo comanda com status finalizado")
    public void que_possuo_comanda_com_status_finalizado() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
