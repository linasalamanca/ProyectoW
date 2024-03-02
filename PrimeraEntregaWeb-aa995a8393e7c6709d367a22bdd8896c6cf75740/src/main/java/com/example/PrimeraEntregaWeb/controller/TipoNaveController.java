package com.example.PrimeraEntregaWeb.controller;

import com.example.PrimeraEntregaWeb.services.TipoNaveService;
import com.example.PrimeraEntregaWeb.model.TipoNave;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/tipoNave")
public class TipoNaveController {
    Logger loggy = LoggerFactory.getLogger(getClass());

    @Autowired
    private TipoNaveService tipoNaveServicio;

    @GetMapping("/list")
    public String listarTipoNaves(Model model){
        List<TipoNave> tipoNave = tipoNaveServicio.listarTipoNaves();
        loggy.info("tipoNave " + tipoNave.size());
        model.addAttribute("tipoNave", tipoNave);
        return "tipoNave-list";
    }

    @GetMapping("/view/{id}")
    String verTipoNaves(Model model, @PathVariable Long id) {
        TipoNave tipoNave = tipoNaveServicio.buscar(id);
        loggy.info("tipoNave " + tipoNave);

        model.addAttribute("tipoNave", tipoNave);
        return "tipoNave-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarTipoNave(Model model, @PathVariable Long id) {
        TipoNave tipoNave = tipoNaveServicio.buscar(id);
        model.addAttribute("tipoNave", tipoNave);
        return "tipoNave-edit";
    }

    /*@PostMapping(value = "/save")
    public String guardarTipoNave(@Valid TipoNave tipoNave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tipoNave-edit";
        }
        tipoNaveServicio.guardarTipoNave(tipoNave);
        return "redirect:/tipoNave/list";
    }*/

    @PostMapping(value = "/update")
    public String actualizarTipoNave(@Valid TipoNave tipoNave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tipoNave-edit";
        }
        tipoNaveServicio.guardarTipoNave(tipoNave);
        return "redirect:/tipoNave/list";
    }

    @GetMapping("/create")
    public String formularioCrearTipoNave(Model model) {
        model.addAttribute("tipoNave", new TipoNave());
        return "tipoNave-create";
    }

    @PostMapping(value = "/save")
    public String guardarTipoNaveNueva(@Valid TipoNave tipoNave, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "tipoNave-create";
        }
        /*Optional<TipoNave> tipoNaveExistente = tipoNaveServicio.buscarTipoNaveOptional(tipoNave.getId());
        if(tipoNaveExistente.isPresent()){
            result.rejectValue("id", "error.tipoNave", "Ya existe un tipo de nave con ese id. "); 
            return "tipoNave-create";
        }*/
        tipoNaveServicio.guardarTipoNave(tipoNave);
        return "redirect:/tipoNave/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarTipoNave(Model model, @PathVariable Long id) {
        tipoNaveServicio.eliminarTipoNave(id);
        return "redirect:/tipoNave/list";
    }

    @GetMapping("/search")
    public String listaTipoNave(@RequestParam(required = false) String searchText, Model model) {
        List<TipoNave> tipoNave;
        if(searchText == null ||searchText.trim().equals("")){
            loggy.info("No hay text de búsqueda, retornando todo");
            tipoNave = tipoNaveServicio.listarTipoNaves();
            tipoNave = tipoNaveServicio.listarTipoNaves();
            model.addAttribute("tipo-Nave", tipoNave);
        }
        
        return "tipoNave-search";
    }
}
