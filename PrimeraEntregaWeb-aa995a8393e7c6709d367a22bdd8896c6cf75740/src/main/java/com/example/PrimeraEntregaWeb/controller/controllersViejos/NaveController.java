package com.example.PrimeraEntregaWeb.controller.controllersViejos;

import java.util.List;
import java.util.Optional;

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

import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;

import javax.validation.Valid;

@Controller
@RequestMapping("/nave")
public class NaveController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NaveService naveServicio;

    @GetMapping("/list")
    public String listarNaves(Model model) {
        List<Nave> nave = naveServicio.listarNaves();
        log.info("nave " + nave.size());
        model.addAttribute("nave", nave);
        return "nave-list";

    }

    @GetMapping("/view/{nombre}")
    String verNaves(Model model, @PathVariable("") String nombre) {
        Nave nave = naveServicio.buscarNave(nombre);
        log.info("nave " + nave);

        model.addAttribute("nave", nave);
        return "nave-view";
    }

    @GetMapping("/edit-form/{nombre}")
    public String formularioEditarNave(Model model, @PathVariable String nombre) {
        Nave nave = naveServicio.buscarNave(nombre);
        model.addAttribute("nave", nave);
        return "nave-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarNave(@Valid Nave nave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nave-edit";
        }
        try {
            naveServicio.actualizarNave(nave);
        } catch (Exception e) {
            log.error("Error al guardar la nave", e);
            model.addAttribute("error", "Error al guardar la nave: " + e.getMessage());
            return "nave-error";
        }
        // naveServicio.guardarNave(nave);
        return "redirect:/nave/list";
    }

    @GetMapping("/create")
    public String formularioCrearNave(Model model) {
        model.addAttribute("nave", new Nave());
        return "nave-create";
    }

    @PostMapping(value = "/save")
    public String guadarNaveNueva(@Valid Nave nave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "nave-create";
        }
        Optional<Nave> naveExistente = naveServicio.buscarNaveOptional(nave.getNombre());
        if (naveExistente.isPresent()) {
            result.rejectValue("nombre", "error.nave", "Ya existe una nave con este nombre.");
            return "nave-create";
        }
        try {
            naveServicio.guardarNave(nave);
        } catch (Exception e) {
            log.error("Error al guardar la nave", e);
            model.addAttribute("error", "Error al guardar la nave: " + e.getMessage());
            return "nave-error";
        }
        // naveServicio.guardarNave(nave);
        return "redirect:/nave/list";
    }

    @GetMapping("/delete/{nombre}")
    public String borrarNave(Model model, @PathVariable String nombre) {
        naveServicio.eliminarNave(nombre);
        // model.addAttribute("nave", nave);
        return "redirect:/nave/list";
    }

    @GetMapping("/search")
    public String listaNaves(@RequestParam(required = false) String searchText, Model model) {
        List<Nave> naves;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            naves = naveServicio.listarNaves();
            model.addAttribute("nave", naves);
        }

        return "nave-search";
    }

    @GetMapping("/equipo-list/{nombre}")
    public String listarEquipoNave(Model model, @PathVariable("nombre") String nombre) {
        Nave nave = naveServicio.buscarNave(nombre);
        List<Jugador> jugadores = nave.getJugadores();
        model.addAttribute("jugadores", jugadores);
        return "nave-equipo-list";
    }
}
