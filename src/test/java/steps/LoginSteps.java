package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertEquals;

public class LoginSteps {

    private WebDriver driver;
    By LoginUserNameLocator = By.name("user-name");
    By LoginPasswordLocator = By.id("password");
    By LoginButtonLocator = By.id("login-button");
    By LoginSuccessLocator = By.xpath("//span[@class='title']");
    By LoginFailedLocator = By.xpath("//h3[@data-test='error']");

    @Given("El usuario se encuentra en la pantalla de inicio")
    public void el_usuario_se_encuentra_en_la_pantalla_de_inicio() {
        System.setProperty("webdriver.chrome.driver","./src/test/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @When("El usuario introduce sus credenciales correctas")
    public void el_usuario_introduce_sus_credenciales_correctas() {
        driver.findElement(LoginUserNameLocator).sendKeys("standard_user");
        driver.findElement(LoginPasswordLocator).sendKeys("secret_sauce");
    }
    @And("El usuario da click en el boton de inicio de sesion")
    public void el_usuario_da_click_en_el_boton_de_inicio_de_sesion() {
        driver.findElement(LoginButtonLocator).click();
        System.out.println("Hola");
    }
    @Then("El usuario visualiza la pantalla de productos")
    public void el_usuario_visualiza_la_pantalla_de_productos() {
        WebDriverWait ewait =  new WebDriverWait(driver, Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(LoginSuccessLocator)));

        assertEquals("PRODUCTS",driver.findElement(LoginSuccessLocator).getText());
    }

    @When("El usuario introduce sus credenciales incorrectas")
    public void el_usuario_introduce_sus_credenciales_incorrectas() {
        driver.findElement(LoginUserNameLocator).sendKeys("david");
        driver.findElement(LoginPasswordLocator).sendKeys("secret_sauce");
    }
    @Then("El usuario visualiza un mensaje de error")
    public void el_usuario_visualiza_un_mensaje_de_error() {
        WebDriverWait ewait = new WebDriverWait(driver,Duration.ofSeconds(5));
        ewait.until(ExpectedConditions.visibilityOf(driver.findElement(LoginFailedLocator)));

        assertEquals("Epic sadface: Username and password do not match any user in this service",
                driver.findElement(LoginFailedLocator).getText());
    }
}
