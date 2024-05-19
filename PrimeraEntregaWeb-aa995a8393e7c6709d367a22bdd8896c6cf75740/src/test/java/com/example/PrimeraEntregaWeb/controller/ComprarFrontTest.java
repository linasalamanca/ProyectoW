package com.example.PrimeraEntregaWeb.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("system-test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarFrontTest {
    // Para descargar ChromeDriver:
    // https://googlechromelabs.github.io/chrome-for-testing/
    private ChromeDriver driver;
    private WebDriverWait wait;

    private String baseUrl;

    @BeforeEach
    void init() {
        // Boilerplate
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        // options.addArguments("--headless"); // To hide Chrome window
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("start-maximized"); // open Browser in maximized mode

        this.driver = new ChromeDriver(options);

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        this.baseUrl = "http://localhost:4200";
    }

    @Test
    void comprarTest() {
        this.driver.get(baseUrl + "/login");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usename")));
        WebElement username = driver.findElement(By.id("usename"));
        username.sendKeys("jugador1");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("1234");
        WebElement buttonIniciarSesion = driver.findElement(By.tagName("iniciarSesion"));
        buttonIniciarSesion.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("iniciarJuego")));

        WebElement buttonIniciarJuego = this.driver.findElement(By.id("iniciarJuego"));
        buttonIniciarJuego.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("botonComprar")));
        WebElement buttonComprar = this.driver.findElement(By.id("botonComprar"));
        buttonComprar.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cantidadProductoCompra")));
        WebElement cantidad = this.driver.findElement(By.id("cantidadProductoCompra"));
        String cantidadEsperada = "Cantidad: 2";
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(cantidad, cantidadEsperada));
        } catch (TimeoutException e) {
            fail("No se pudo comprar, " + cantidad.getText());
        }
    }

    @AfterEach
    void end() {
        driver.quit();
    }
}
