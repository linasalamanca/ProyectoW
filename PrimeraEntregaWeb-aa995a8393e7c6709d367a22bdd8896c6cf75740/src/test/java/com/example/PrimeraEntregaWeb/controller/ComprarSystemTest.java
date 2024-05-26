package com.example.PrimeraEntregaWeb.controller;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.dto.JwtAuthenticationResponse;
import com.example.PrimeraEntregaWeb.dto.LoginDTO;
import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.model.Role;
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

@ActiveProfiles("system-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarSystemTest {

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
    @Autowired
    private PasswordEncoder passwordEncoder;
    private InformacionCompraProductoDTO informacion = new InformacionCompraProductoDTO();

    private String jwtToken;

    @BeforeEach
    void init() {
        // Preparar datos
        prepararDatos();

        // Autenticar y obtener el token JWT
        this.jwtToken = obtenerJwtToken();

        // Configurar el navegador
        configurarNavegador();

        this.baseUrl = "http://localhost:4200";
    }

    private void prepararDatos() {
        List<TipoNave> tipoNaves = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble(), random.nextDouble());
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
        for (int i = 0; i < 5; i++) {
            Nave nave = new Nave(1000.00, random.nextDouble() * (90 - 10) + 10, random.nextDouble() * (90 - 10) + 10,
                    random.nextDouble() * (90 - 10) + 10, "nave" + i, random.nextDouble() * 200 + 15,
                    random.nextDouble() * 500.5);
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

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Jugador jugador = new Jugador(Role.PILOTO, "pilot" + i, passwordEncoder.encode("hola" + i));
            Jugador jugador2 = new Jugador(Role.COMERCIANTE, "comerciant" + i, passwordEncoder.encode("hola" + i));
            Jugador jugador3 = new Jugador(Role.CAPITAN, "capit" + i, passwordEncoder.encode("hola" + i));
            jugadores.add(jugador);
            jugadores.add(jugador2);
            jugadores.add(jugador3);
        }
        List<List<Jugador>> equipos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Jugador> equipo = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                equipo.add(jugadores.get(i * 3 + j));
            }
            equipos.add(equipo);
        }

        for (int i = 0; i < 3; i++) {
            List<Jugador> equipo = equipos.get(i);
            Nave nave = naves.get(i);
            for (Jugador jugador : equipo) {
                jugador.setNave(nave);
                nave.addJugador(jugador);
            }
        }

        jugadorRepository.saveAll(jugadores);

        for (int i = 0; i < 5; i++) {
            Estrella estrella = new Estrella(random.nextDouble() * (90 - 10) + 10, random.nextDouble() * (90 - 10) + 10,
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
                        inventarioPlaneta.setCantidad(2.0);
                        inventarioPlaneta.setfOfertaDemanda(1.0);
                        inventarioPlaneta.setProducto(productos.get(k));
                        inventarioPlanetaRepository.save(inventarioPlaneta);
                    }
                }
            }
        }

        for (Nave nave : naves) {
            Partida partida = new Partida(0.0, nave.getDinero(), 1.0);
            partida.setNave(nave);
            nave.setPartida(partida);
            partidaRepository.save(partida);
            naveRepository.save(nave);
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("start-maximized");

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.baseUrl = "http://localhost:4200";
    }

    @SuppressWarnings("null")
    private String obtenerJwtToken() {
        RestTemplate restTemplate = new RestTemplate();
        LoginDTO loginDto = new LoginDTO("pilot0", "hola0");
        ResponseEntity<JwtAuthenticationResponse> response = restTemplate.postForEntity(
                "http://localhost:8080/api/auth/login", loginDto, JwtAuthenticationResponse.class);
        return response.getBody().getToken();
    }

    private void configurarNavegador() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("start-maximized");

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    void comprarTest() {
        this.driver.get(baseUrl + "/iniciar");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usuario")));
        WebElement username = driver.findElement(By.id("usuario"));
        username.sendKeys("pilot0");

        WebElement password = driver.findElement(By.id("contrasena"));
        password.sendKeys("hola0");

        WebElement buttonIniciarJuego = this.driver.findElement(By.id("iniciarJuego"));
        buttonIniciarJuego.click();

        handleAlertIfPresent();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("botonViajarEstrella")));
        WebElement buttonViajar = this.driver.findElement(By.id("botonViajarEstrella"));
        buttonViajar.click();

        handleAlertIfPresent();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("botonComprar")));
        WebElement buttonComprar = this.driver.findElement(By.id("botonComprar"));
        buttonComprar.click();

        handleAlertIfPresent();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("comprar")));
        WebElement buttonComprar2 = this.driver.findElement(By.id("comprar"));
        buttonComprar2.click();

        handleAlertIfPresent();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("puntaje")));
        WebElement puntaje = this.driver.findElement(By.id("puntaje"));

        String cantidadEsperada = "Puntaje: 999.6666666";
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(puntaje, cantidadEsperada));
        } catch (TimeoutException e) {
            fail("No se pudo comprar, " + puntaje.getText());
        }
    }

    private void handleAlertIfPresent() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                String alertText = alert.getText();
                System.out.println("Alerta presente: " + alertText);
                alert.accept();
            }
        } catch (TimeoutException | NoAlertPresentException e) {
            // No alert found, do nothing
        }
    }

    @AfterEach
    void end() {
        if (driver != null) {
            driver.quit();
        }
    }
}