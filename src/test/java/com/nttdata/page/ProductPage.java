package com.nttdata.page;

import com.nttdata.core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstProduct = By.cssSelector(".product-miniature"); // corregido
    private By addToCartBtn = By.cssSelector(".add-to-cart");       // corregido
    private By popup = By.id("blockcart-modal");                    // corregido
    private By popupTotal = By.cssSelector(".cart-content .value");

    private By proceedBtn = By.xpath("//a[contains(@class,'btn') and contains(text(),'Finalizar compra')]");

    public ProductPage(){
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateCategory(String categoria, String subcategoria) {
        String categoryId = "";

        if (categoria.equalsIgnoreCase("Clothes")) {
            categoryId = "category-3";
        } else if (categoria.equalsIgnoreCase("Accessories")) {
            categoryId = "category-6";
        } else if (categoria.equalsIgnoreCase("Art")) {
            categoryId = "category-9";
        }

        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#" + categoryId + " > a")
        ));

        Actions actions = new Actions(driver);
        actions.moveToElement(categoryElement).perform();

        if (!subcategoria.equalsIgnoreCase("N/A")) {
            WebElement subCategoryElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(@class,'category')]/a[normalize-space(text())='" + subcategoria + "']")
            ));
            subCategoryElement.click();
        } else {
            categoryElement.click();
        }
    }

    public void addProductToCart(int cantidad){
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        product.click();

        for (int i = 0; i < cantidad; i++) {
            // Espera que el popup ya no esté visible antes de hacer clic
            wait.until(ExpectedConditions.invisibilityOfElementLocated(popup));

            WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
            addBtn.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(popup));

            if (i < cantidad - 1) {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.close")
                ));
                closeBtn.click();

                // Espera que desaparezca el popup antes del siguiente ciclo
                wait.until(ExpectedConditions.invisibilityOfElementLocated(popup));
            }
        }
    }

    public boolean validatePopup(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(popup)).isDisplayed();
    }

    public String getPopupTotal(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(popupTotal)).getText();
    }

    public void finalizePurchase(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
    }

    public String getCartTitle(){
        return driver.getTitle();
    }
}
