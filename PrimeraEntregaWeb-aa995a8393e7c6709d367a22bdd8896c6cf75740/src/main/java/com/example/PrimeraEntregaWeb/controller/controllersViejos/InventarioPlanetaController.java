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
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;

@Controller
@RequestMapping("/iplaneta")
public class InventarioPlanetaController {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private InventarioPlanetaService inventarioPlanetaServicio;

    @GetMapping("/list")
    public String listarInventarioNave(Model model) {
        List<InventarioPlaneta> iNave = inventarioPlanetaServicio.listarInventarioPlaneta();
        model.addAttribute("ip", iNave);
        return "iplaneta-list";
    }

    @GetMapping("/view/{id}")
    String verInventario(Model model, @PathVariable("") Long id) {
        InventarioPlaneta iplaneta = inventarioPlanetaServicio.buscarInventario(id);
        model.addAttribute("iplaneta", iplaneta);
        return "iplaneta-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarInventarioPlaneta(Model model, @PathVariable Long id) {
        InventarioPlaneta iplaneta = inventarioPlanetaServicio.buscarInventario(id);
        model.addAttribute("iplaneta", iplaneta);
        return "iplaneta-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarInventarioPlaneta(@Valid InventarioPlaneta inventarioPlaneta, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "iplaneta-edit";
        }
        try {
            inventarioPlanetaServicio.actualizarInventario(inventarioPlaneta);
        } catch (Exception e) {
            log.error("Error al guardar el inventario", e);
            model.addAttribute("errorMensaje", "Error al guardar el inventario: " + e.getMessage());
            return "iplaneta-error";
        }
        return "redirect:/iplaneta/list";
    }

    @GetMapping("/create")
    public String formularioCrearInventarioPlaneta(Model model) {
        model.addAttribute("iplaneta", new InventarioPlaneta());
        return "iplaneta-create";
    }

    @PostMapping(value = "/save")
    public String guadarInventarioNuevo(@Valid InventarioPlaneta inventarioPlaneta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "iplaneta-create";
        }

        try {
            inventarioPlanetaServicio.guardarInventario(inventarioPlaneta);
        } catch (Exception e) {
            log.error("Error al guardar el inventario: ", e);
            model.addAttribute("error", "Error al guardar el inventario: " + e.getMessage());
            return "iplaneta-error";
        }

        return "redirect:/iplaneta/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarInventario(Model model, @PathVariable Long id) {
        inventarioPlanetaServicio.eliminarInventario(id);
        return "redirect:/iplaneta/list";
    }

    @GetMapping("/search")
    public String listaInvenatrioNave(@RequestParam(required = false) String searchText, Model model) {
        List<InventarioPlaneta> iplaneta;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            iplaneta = inventarioPlanetaServicio.listarInventarioPlaneta();
            model.addAttribute("iplaneta", iplaneta);
        }

        return "iplaneta-search";
    }

}
