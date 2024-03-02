package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.services.EstrellaService;
import com.example.PrimeraEntregaWeb.services.JugadorService;

@Controller
@RequestMapping("/jugador")
public class JugadorController {

    Logger loggy = LoggerFactory.getLogger(getClass());

    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/list")
    public String listarEstrellas(Model model) {
        List<Jugador> jugador = jugadorService.listarJugadores();
        loggy.info("jugador" + jugador.size());
        model.addAttribute("jugador", jugador);
        return "jugador-list";
    }

    @GetMapping("/view/{id}")
    String verEstrellas(Model model, @PathVariable("id") Long id) {
        Jugador jugador = jugadorService.buscarJugador(id);
        loggy.info("jugador" + jugador);

        model.addAttribute("jugador", jugador);
        return "jugador-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarEstrella(Model model, @PathVariable Long id) {
        Jugador jugador = jugadorService.buscarJugador(id);
        model.addAttribute("jugador", jugador);
        return "jugador-edit";
    }

    @PostMapping(value = "/save")
    public String guardarNave(@Valid Jugador jugador, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jugador-edit";
        }
        jugadorService.guardarJugador(jugador);
        return "redirect:/jugador/list";
    }

    @GetMapping("/search")
    public String listaJugadores(@RequestParam(required = false) String searchText, Model model) {
        List<Jugador> jugador;
        if (searchText == null || searchText.trim().equals("")) {
            loggy.info("No hay texto de búsqueda, retornando todo");
            jugador = jugadorService.listarJugadores();
            model.addAttribute("jugador", jugador);
        }

        return "jugador-search";
    }

}
