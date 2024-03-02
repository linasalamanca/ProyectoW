package com.example.PrimeraEntregaWeb.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.services.EstrellaService;
import javax.validation.Valid;

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

   /*  @PostMapping(value = "/save")
    public String guardarNave(@Valid Estrella estrella, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estrella-edit";
        }
        estrellaService.guardarEstrella(estrella);
        return "redirect:/estrella/list";
    }*/
     @PostMapping(value = "/update")
    public String actualizarEstrella(@Valid Estrella estrella, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "estrella-edit";
        }
        estrellaService.guardarEstrella(estrella);
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
      /*  Optional<Estrella> estrellaExistente = estrellaService.buscarEstrellaOptional(estrella.getId());
        if (estrellaExistente.isPresent()) {
            result.rejectValue("nombre", "error.estrella", "Ya existe una nave con este nombre.");
            return "estrella-create";
        }*/
        estrellaService.guardarEstrella(estrella);
        return "redirect:/estrella/list";
    }
    @GetMapping("/delete/{id}")
    public String borrarEstrella(Model model, @PathVariable Long id) {
        estrellaService.eliminarEstrella(id);
       // model.addAttribute("nave", nave);
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

}
