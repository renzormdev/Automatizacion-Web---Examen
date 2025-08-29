package com.nttdata.page;

import com.nttdata.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selectores del carrito
    private By cartTitle = By.cssSelector("h1"); // El título de la página del carrito
    private By cartTotal = By.cssSelector(".cart-summary .value"); // total del carrito
    private By cartProducts = By.cssSelector(".cart-item"); // items del carrito

    public CartPage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCartTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle)).getText();
    }

    public String getCartTotal(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTotal)).getText();
    }

    public int getCartProductsCount(){
        return driver.findElements(cartProducts).size();
    }
}
