package com.Taller.WebTaller.api;

import com.Taller.WebTaller.modelos.Pieza;
import com.Taller.WebTaller.service.Gestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins= {"*"}, allowCredentials = "false" )
@Controller
public class Controlador {

    private final Gestor gestor;


    @Autowired
    public Controlador(Gestor gestor) {
        this.gestor = gestor;
    }
    @GetMapping
    public String greeting() {
        return "index";
    }
    @GetMapping("/Productos")
    public String greeting(Model model) {
        model.addAttribute("marcas", gestor.listaMarcas());
        return "Productos";
    }
    @GetMapping("/Productos/marca")
    public String paginaMarca(@RequestParam(name="marca",required = true,defaultValue = "TODOS") String name, Model model){
        if (name.equals("TODOS")){
            model.addAttribute("productos",gestor.obtenerPiezasTodas());
            return "paginaMarca";
        }
        model.addAttribute("productos", gestor.obtenerPiezasMarca(name));
        return "paginaMarca";
    }



}