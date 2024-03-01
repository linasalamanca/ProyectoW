package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.TipoNave;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.TipoNaveService;

@Controller
@RequestMapping("/tipoNave")
public class TipoNaveController {
    /*
     * @Autowired
     * private TipoNaveService tipoNaveService;
     * 
     * @Autowired
     * private NaveService naveService;
     * 
     * @GetMapping("/list")
     * public String listaCompanias(Model model) {
     * List<Nave> naves = naveService.listarNaves();
     * model.addAttribute("naves", naves);
     * return "naves-list";
     * }
     */

}
