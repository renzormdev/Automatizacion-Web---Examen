package com.nttdata.stepsdefinitions;

import com.nttdata.core.DriverManager;
import com.nttdata.page.LoginPage;
import com.nttdata.page.ProductPage;
import com.nttdata.page.CartPage;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class ProductStoreStepsDef {
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    CartPage cartPage = new CartPage();

    @Given("abrir pagina principal")
    public void abrirPaginaPrincipal() {
        loginPage.openStore();
        DriverManager.screenShot();
    }

    @When("me logueo con usuario {string} y clave {string}")
    public void meLogueo(String usuario, String clave) {
        loginPage.login(usuario, clave);
        DriverManager.screenShot();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegarCategoria(String categoria, String subcategoria) {
        productPage.navigateCategory(categoria, subcategoria);
        DriverManager.screenShot();
    }

    @When("agrego {int} unidades del primer producto al carrito")
    public void agregarProducto(int cantidad) {
        productPage.addProductToCart(cantidad);
        DriverManager.screenShot();
    }

    @Then("valido en el popup la confirmacion del producto agregado")
    public void validarPopup() {
        assertTrue(productPage.validatePopup());
        DriverManager.screenShot();
    }

    @Then("valido en el popup que el monto total sea calculado correctamente")
    public void validarTotalPopup() {
        String total = productPage.getPopupTotal();
        assertTrue(total != null && !total.isEmpty());
        DriverManager.screenShot();
    }

    @When("finalizo la compra")
    public void finalizarCompra() {
        productPage.finalizePurchase();
        DriverManager.screenShot();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validarTituloCarrito() {
        String titulo = cartPage.getCartTitle();
        assertTrue(titulo.equalsIgnoreCase("CARRITO"));
        DriverManager.screenShot();
    }

    @Then("vuelvo a validar el calculo de precios en el carrito")
    public void validarCarritoFinal() {
        String totalCarrito = cartPage.getCartTotal();
        assertTrue(totalCarrito != null && !totalCarrito.isEmpty());
        assertTrue(cartPage.getCartProductsCount() > 0);
        DriverManager.screenShot();
    }
}
