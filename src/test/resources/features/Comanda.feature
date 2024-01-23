
Feature: Comanda

  Scenario: Cria Comanda
    Given que recebi os dados de uma comanda
    When invoco o endpoint de criar comanda
#    Then a comanda é exibida

  Scenario: Preparo Comanda
    Given que possuo comanda para ser preparada
    When realizo a preparacao
    Then exibo o resultado

  Scenario: Finaliza Comanda
    Given que possuo comanda para ser finalizada
    When realizo a finalizacao
    Then exibo o resultado

  Scenario: Busca Status Preparacao
    Given que possuo comanda com status preparacao
    When busco essas comandas
    Then exibo a busca

  Scenario: Busca Status Recebido
    Given que possuo comanda com status recebido
    When busco essas comandas
    Then exibo a busca

  Scenario: Busca Status Finalizado
    Given que possuo comanda com status finalizado
    When busco essas comandas
    Then exibo a busca


  Scenario: Verifica Validacao Comanda
    Given que recebi os dados de uma comanda







