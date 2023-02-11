Feature: Carrito de compras
  Como usuario quiero usar el carrito de compras para añadir o quitar productos
    @AgregarProducto
    Scenario: Añadir un producto al carrito de compras
      Given El usuario a iniciado sesion correctamente
      And El usuario se encuentra en la pantalla de inventario
      When El usuario da clic en el boton ADD TO CART de un producto
      Then El usuario visualiza que el producto se añadio al carrito de compras

    @QuitarProducto
    Scenario: Quitar un producto del carrito de compras
      Given El usuario a iniciado sesion correctamente
      And El usuario añade un producto al carrito de compras
      When El usuario ingresa a la pantalla del carrito de compras
      And El usuario remueve el producto del carrito de compras
      Then El usuario visualiza que el producto se removio del carrito de compras