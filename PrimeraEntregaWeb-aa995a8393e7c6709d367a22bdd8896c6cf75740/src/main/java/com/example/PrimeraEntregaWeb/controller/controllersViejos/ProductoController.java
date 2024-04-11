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
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductoService productoServicio;

    @GetMapping("/list")
    public String listarProductos(Model model) {
        List<Producto> producto = productoServicio.listarProductos();
        log.info("producto " + producto.size());
        model.addAttribute("producto", producto);
        return "producto-list";

    }

    @GetMapping("/view/{id}")
    String verProductos(Model model, @PathVariable("") Long id) {
        Producto producto = productoServicio.buscarProducto(id);
        log.info("producto " + producto);
        model.addAttribute("producto", producto);
        return "producto-view";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarProducto(Model model, @PathVariable Long id) {
        Producto producto = productoServicio.buscarProducto(id);
        model.addAttribute("producto", producto);
        return "producto-edit";
    }

    @PostMapping(value = "/update")
    public String actualizarProducto(@Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "producto-edit";
        }
        try {
            productoServicio.guardarProducto(producto);
        } catch (Exception e) {
            log.error("Error al guardar producto: ", e);
            model.addAttribute("error", "Error al guardar producto: " + e.getMessage());
            return "producto-error";
        }
        return "redirect:/producto/list";
    }

    @GetMapping("/create")
    public String formularioCrearProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto-create";
    }

    @PostMapping(value = "/save")
    public String guadarProductoNuevo(@Valid Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "producto-create";
        }
        try {
            productoServicio.guardarProducto(producto);
        } catch (Exception e) {
            log.error("Error al guardar producto: ", e);
            model.addAttribute("error", "Error al guardar producto: " + e.getMessage());
            return "producto-error";
        }
        return "redirect:/producto/list";
    }

    @GetMapping("/delete/{id}")
    public String borrarProducto(Model model, @PathVariable Long id) {
        productoServicio.eliminarProducto(id);
        return "redirect:/producto/list";
    }

    @GetMapping("/search")
    public String listaProductos(@RequestParam(required = false) String searchText, Model model) {
        List<Producto> producto;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            producto = productoServicio.listarProductos();
            model.addAttribute("producto", producto);
        }

        return "producto-search";
    }
}
