

@login_categories_validations
Feature: Product - Store

  Scenario Outline: Validacion de flujo de compra y autenticacion
    Given abrir pagina principal
    When me logueo con usuario "<usuario>" y clave "<clave>"
    And navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    And agrego 1 unidades del primer producto al carrito
    Then valido en el popup la confirmacion del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

    Examples:
      | usuario                    | clave       | categoria | subcategoria | resultado  |
      | rremuzgo_prueba@gmail.com  | rremuzgoabc | Clothes   | Men          | correcto   |