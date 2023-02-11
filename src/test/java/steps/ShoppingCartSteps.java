package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartSteps {

    private WebDriver driver;

    By LoginUserNameLocator = By.name("user-name");
    By LoginPasswordLocator = By.id("password");
    By LoginButtonLocator = By.id("login-button");
    String nombreProductoAgregado = "";
    By shoppingCartLogoLocator = By.xpath("//a[@class='shopping_cart_link']");
    By tittlePageLocator = By.className("title");
    By removeButtonLocator = By.className("cart_button");

    @Given("El usuario a iniciado sesion correctamente")
    public void el_usuario_a_iniciado_sesion_correctamente() {
        System.setProperty("webdriver.chrome.driver","./src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(LoginUserNameLocator).sendKeys("standard_user");
        driver.findElement(LoginPasswordLocator).sendKeys("secret_sauce");
        driver.findElement(LoginButtonLocator).click();
    }
    @And("El usuario se encuentra en la pantalla de inventario")
    public void el_usuario_se_encuentra_en_la_pantalla_de_inventario() {
        String urlActual = driver.getCurrentUrl();
        assertTrue("No se encuentra en la pantalla de inventario",urlActual.equals("https://www.saucedemo.com/inventory.html"));
    }
    @When("El usuario da clic en el boton ADD TO CART de un producto")
    public void el_usuario_da_clic_en_el_boton_add_to_cart_de_un_producto() {
        List<WebElement> btnInventory = driver.findElements(By.className("btn_inventory"));
        List<WebElement> nombresProducto = driver.findElements(By.className("inventory_item_name"));

        btnInventory.get(0).click();

        nombreProductoAgregado = nombresProducto.get(0).getText();
    }
    @Then("El usuario visualiza que el producto se añadio al carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_añadio_al_carrito_de_compras() {
        driver.findElement(shoppingCartLogoLocator).click();

        WebDriverWait ewait = new WebDriverWait(driver,Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(tittlePageLocator)));

        assertTrue("El carrito de compras ya se encuentra vacio o tiene mas de un producto",
                driver.findElement(shoppingCartLogoLocator).getText().equals("1"));
    }
    @And("El usuario añade un producto al carrito de compras")
    public void el_usuario_añade_un_producto_al_carrito_de_compras() {
        List<WebElement> btnInventory = driver.findElements(By.className("btn_inventory"));
        List<WebElement> nombresProducto = driver.findElements(By.className("inventory_item_name"));

        btnInventory.get(0).click();

        nombreProductoAgregado = nombresProducto.get(0).getText();
    }
    @When("El usuario ingresa a la pantalla del carrito de compras")
    public void el_usuario_ingresa_a_la_pantalla_del_carrito_de_compras() {
        driver.findElement(shoppingCartLogoLocator).click();
        String urlActual = driver.getCurrentUrl();
        assertTrue("No se encuentra en la pantalla del carrito de compras",
                urlActual.equals("https://www.saucedemo.com/cart.html"));
    }
    @And("El usuario remueve el producto del carrito de compras")
    public void el_usuario_remueve_el_producto_del_carrito_de_compras() {
        driver.findElement(removeButtonLocator).click();
    }
    @Then("El usuario visualiza que el producto se removio del carrito de compras")
    public void el_usuario_visualiza_que_el_producto_se_removio_del_carrito_de_compras() {
        assertTrue("El carrito de compras no esta vacio", driver.findElement(shoppingCartLogoLocator).getText().equals(""));

    }

}
