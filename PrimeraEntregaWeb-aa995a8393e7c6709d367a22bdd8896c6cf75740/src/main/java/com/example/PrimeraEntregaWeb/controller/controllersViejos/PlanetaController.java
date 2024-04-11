package com.example.PrimeraEntregaWeb.controller.controllersViejos;

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
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.services.PlanetaService;

@Controller
@RequestMapping("/planeta")
public class PlanetaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlanetaService planetaServicio;

    @GetMapping("/list")
    public String listarPlanetas(Model model) {
        List<Planeta> planeta = planetaServicio.listarPlanetas();
        log.info("planeta " + planeta.size());
        model.addAttribute("planeta", planeta);
        return "planeta-list";

    }

    @GetMapping("/view/{id}")
    String verPlanetas(Model model, @PathVariable("") Long id) {
        Planeta planeta = planetaServicio.buscarPlaneta(id);
        log.info("planeta " + planeta);
        model.addAttribute("planeta", planeta);
        return "planeta-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarPlaneta(Model model, @PathVariable Long id) {
        Planeta planeta = planetaServicio.buscarPlaneta(id);
        model.addAttribute("planeta", planeta);
        return "planeta-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarPlaneta(@Valid Planeta planeta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "planeta-edit";
        }
        planetaServicio.actualizarPlaneta(planeta);
        return "redirect:/planeta/list";
    }

    @GetMapping("/create")
    public String formularioCrearPlaneta(Model model) {
        model.addAttribute("planeta", new Planeta());
        return "planeta-create";
    }

    @PostMapping(value = "/save")
    public String guadarNaveNueva(@Valid Planeta planeta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "planeta-create";
        }

        planetaServicio.guardarPlaneta(planeta);
        return "redirect:/planeta/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarPlaneta(Model model, @PathVariable Long id) {
        planetaServicio.eliminarPlaneta(id);
        return "redirect:/planeta/list";
    }

    @GetMapping("/search")
    public String listaPlanetas(@RequestParam(required = false) String searchText, Model model) {
        List<Planeta> planeta;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            planeta = planetaServicio.listarPlanetas();
            model.addAttribute("planeta", planeta);
        }

        return "planeta-search";
    }
}
