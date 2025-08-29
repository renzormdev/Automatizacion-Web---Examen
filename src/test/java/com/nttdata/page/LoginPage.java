package com.nttdata.page;

import com.nttdata.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By userInput = By.id("field-email");
    private By passwordInput = By.id("field-password");
    private By submitBtn = By.id("submit-login");

    public LoginPage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openStore(){
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion");
    }

    public void typeUser(String usuario){
        wait.until(ExpectedConditions.visibilityOfElementLocated(userInput)).sendKeys(usuario);
    }

    public void typePassword(String clave){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(clave);
    }

    public void clickLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public void login(String usuario, String clave){
        typeUser(usuario);
        typePassword(clave);
        clickLogin();
    }
}
