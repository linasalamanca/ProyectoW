package com.example.PrimeraEntregaWeb.controller.controllersViejos;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PrimeraEntregaWeb.model.Estrella;

import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.services.EstrellaService;

@Controller
@RequestMapping("/estrella")
public class EstrellaController {
    Logger loggy = LoggerFactory.getLogger(getClass());

    @Autowired
    private EstrellaService estrellaService;

    @GetMapping("/list")
    public String listarEstrellas(Model model) {
        List<Estrella> estrella = estrellaService.listarEstrellas();
        loggy.info("estrella" + estrella.size());
        model.addAttribute("estrella", estrella);
        return "estrella-list";
    }

    @GetMapping("/view/{id}")
    String verEstrellas(Model model, @PathVariable("id") Long id) {
        Estrella estrella = estrellaService.buscar(id);
        loggy.info("estrella" + estrella);

        model.addAttribute("estrella", estrella);
        return "estrella-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarEstrella(Model model, @PathVariable Long id) {
        Estrella estrella = estrellaService.buscar(id);
        model.addAttribute("estrella", estrella);
        return "estrella-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarEstrella(@Valid Estrella estrella, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estrella-edit";
        }
        try {
            estrellaService.guardarEstrella(estrella);
        } catch (Exception e) {
            loggy.error("Error al guardar la estrella", e);
            model.addAttribute("errorMensaje", "Error al guardar la estrella: " + e.getMessage());
            return "estrella-error";
        }
        return "redirect:/estrella/list";
    }

    @GetMapping("/create")
    public String formularioCrearEstrella(Model model) {
        model.addAttribute("estrella", new Estrella());
        return "estrella-create";
    }

    @PostMapping(value = "/save")
    public String guadarNaveEstrella(@Valid Estrella estrella, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estrella-create";
        }
        try {
            estrellaService.guardarEstrella(estrella);
        } catch (Exception e) {
            loggy.error("Error al guardar la estrella", e);
            model.addAttribute("errorMensaje", "Error al guardar la estrella: " + e.getMessage());
            return "estrella-error";
        }
        return "redirect:/estrella/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarEstrella(Model model, @PathVariable Long id) {
        estrellaService.eliminarEstrella(id);
        return "redirect:/estrella/list";
    }

    @GetMapping("/search")
    public String listaNaves(@RequestParam(required = false) String searchText, Model model) {
        List<Estrella> estrellas;
        if (searchText == null || searchText.trim().equals("")) {
            loggy.info("No hay texto de búsqueda, retornando todo");
            estrellas = estrellaService.listarEstrellas();
            model.addAttribute("estrella", estrellas);
        }

        return "estrella-search";
    }

    @GetMapping("/planeta-list/{id}")
    public String listarPlanetasEstrella(Model model, @PathVariable("id") Long id) {
        Estrella estrella = estrellaService.buscar(id);
        List<Planeta> planetas = estrella.getPlanetas();
        model.addAttribute("planetas", planetas);
        return "estrella-planeta-list";
    }

}
