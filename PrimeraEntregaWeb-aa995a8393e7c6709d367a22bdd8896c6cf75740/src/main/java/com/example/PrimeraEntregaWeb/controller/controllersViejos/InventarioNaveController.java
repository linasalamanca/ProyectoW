package com.example.PrimeraEntregaWeb.controller.controllersViejos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PrimeraEntregaWeb.model.InventarioNave;

import com.example.PrimeraEntregaWeb.services.InventarioNaveService;

@Controller
@RequestMapping("/inave")
public class InventarioNaveController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InventarioNaveService inventarioNaveServico;

    @GetMapping("/list")
    public String listarInventarioNave(Model model) {
        List<InventarioNave> iNave = inventarioNaveServico.listarInventarioNave();
        // log.info("producto " + inventNave.size());
        model.addAttribute("in", iNave);
        return "inave-list";
    }

    @GetMapping("/view/{id}")
    String verProductos(Model model, @PathVariable("") Long id) {
        InventarioNave iNave = inventarioNaveServico.buscarInventario(id);
        // log.info("inave " + iNave);
        model.addAttribute("inave", iNave);
        return "inave-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarInventarioNave(Model model, @PathVariable Long id) {
        InventarioNave iNave = inventarioNaveServico.buscarInventario(id);
        model.addAttribute("inventarioNave", iNave);
        return "inave-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarInventarioNave(@Valid InventarioNave inventarioNave, BindingResult result, Model model) {
        List<ObjectError> err = result.getAllErrors();

        for (ObjectError e : err) {
            log.info("errores: " + e.toString());
        }
        if (result.hasErrors()) {
            // model.addAttribute("inave", inventarioNave);

            return "inave-edit";
        }
        try {
            inventarioNaveServico.actualizarInventario(inventarioNave);
        } catch (Exception e) {
            log.error("Error al guardar el inventario: ", e);
            model.addAttribute("errorMensaje", "Error al guardar el inventario: " + e.getMessage());
            return "inave-error";
        }
        // inventarioNaveServico.guardarInventario(inventarioNave);
        return "redirect:/inave/list";
    }

    @GetMapping("/create")
    public String formularioCrearInventarioNave(Model model) {
        model.addAttribute("inventarioNave", new InventarioNave());
        return "inave-create";
    }

    @PostMapping(value = "/save")
    public String guadarInventarioNuevo(@Valid InventarioNave inventarioNave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inave-create";
        }

        try {
            inventarioNaveServico.guardarInventario(inventarioNave);
        } catch (Exception e) {
            log.error("Error al guardar el inventario: ", e);
            model.addAttribute("errorMensaje", "Error al guardar el inventario: " +
                    e.getMessage());
            return "inave-error";
        }

        return "redirect:/inave/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarInventario(Model model, @PathVariable Long id) {
        inventarioNaveServico.eliminarInventario(id);
        return "redirect:/inave/list";
    }

    @GetMapping("/search")
    public String listaInvenatrioNave(@RequestParam(required = false) String searchText, Model model) {
        List<InventarioNave> iNave;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            iNave = inventarioNaveServico.listarInventarioNave();
            model.addAttribute("inave", iNave);
        }

        return "inave-search";
    }

}
