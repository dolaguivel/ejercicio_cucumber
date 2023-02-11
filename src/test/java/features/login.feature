Feature: login
  Como usuario quiero entrar a la página con mis credenciales
  Scenario: Realizar login valido
    Given El usuario se encuentra en la pantalla de inicio
    When El usuario introduce sus credenciales correctas
    And El usuario da click en el boton de inicio de sesion
    Then El usuario visualiza la pantalla de productos

  Scenario: Realizar login invalido
    Given El usuario se encuentra en la pantalla de inicio
    When El usuario introduce sus credenciales incorrectas
    And El usuario da click en el boton de inicio de sesion
    Then El usuario visualiza un mensaje de error