package com.example.PrimeraEntregaWeb.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.model.TipoNave;
import com.example.PrimeraEntregaWeb.repository.EstrellaRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import com.example.PrimeraEntregaWeb.repository.PartidaRepository;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.ProductoRepository;
import com.example.PrimeraEntregaWeb.repository.TipoNaveRepository;

@ActiveProfiles("systemtest")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarSystemTest {
    // Para descargar ChromeDriver:
    // https://googlechromelabs.github.io/chrome-for-testing/
    private ChromeDriver driver;
    private WebDriverWait wait;

    private String baseUrl;

    @Autowired
    private NaveRepository naveRepository;
    @Autowired
    private EstrellaRepository estrellaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PlanetaRepository planetaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private TipoNaveRepository tipoNaveRepository;
    @Autowired
    private InventarioNaveRepository inventarioNaveRepository;
    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepository;
    @Autowired
    private PartidaRepository partidaRepository;

    @BeforeEach
    void init() {
    
        List<TipoNave> tipoNaves = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                        TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble(),
                                        random.nextDouble());
                        tipoNaves.add(tipoNave);
                }

                tipoNaveRepository.saveAll(tipoNaves);

                List<Producto> productos = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                        productos.add(new Producto((double) i + 1, "producto-prueba" + i));
                        productos.get(i).setPrecio(50.0);
                }
                productoRepository.saveAll(productos);

                List<Nave> naves = new ArrayList<>();
                int min = 100;
                int cont = 0;
                for (int i = 0; i < 5; i++) {
                        Nave nave = new Nave(
                                        1000.52,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        "nave" + i,
                                        random.nextDouble() * 200 + 15, random.nextDouble() * 500.5);
                        nave.setTipo(tipoNaves.get(i));
                        naves.add(nave);

                }
                naveRepository.saveAll(naves);

                for (Nave nave : naves) {
                        for (int k = 0; k < productos.size(); k++) {
                                InventarioNave inventarioNave = new InventarioNave();
                                inventarioNave.setNave(nave);
                                inventarioNave.setCantidad(20.2 + k * 2);
                                inventarioNave.setfOfertaDemanda(random.nextDouble() * 1000000);
                                inventarioNave.setProducto(productos.get(k));
                                inventarioNaveRepository.save(inventarioNave);
                        }
                }

                int cont2 = 0;
                /* Generar las estrellas */
                for (int i = 0; i < 5; i++) {
                        Estrella estrella = new Estrella(
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10);

                        estrellaRepository.save(estrella);
                        if (random.nextDouble() < 1) {
                                int numPlanets = random.nextInt(3) + 1;
                                for (int j = 0; j < numPlanets; j++) {
                                        Planeta planeta = new Planeta("Planeta_" + i + "_" + j);
                                        planeta.setEstrella(estrella);
                                        estrella.addPlaneta(planeta);
                                        planetaRepository.save(planeta);

                                        for (int k = 0; k < 3; k++) {
                                                InventarioPlaneta inventarioPlaneta = new InventarioPlaneta();
                                                inventarioPlaneta.setPlaneta(planeta);
                                                inventarioPlaneta.setCantidad(20.2 + k * 2);

                                                Double randomNumber = random.nextDouble(1000001);
                                                inventarioPlaneta.setfOfertaDemanda(randomNumber);

                                                if (cont2 == 3) {
                                                        cont2 = 0;
                                                }
                                                if (cont2 < 3) {
                                                        inventarioPlaneta.setProducto(productos.get(cont2));
                                                        cont2++;
                                                }
                                                inventarioPlanetaRepository.save(inventarioPlaneta);
                                        }

                                }

                        }
                }

                Partida partida = new Partida(0.0, naves.get(0).getDinero(), 1.0);
                partidaRepository.save(partida);

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
