package edu.upn.CasoPractico1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Home {
    @GetMapping("/inicio")
    public String Inicio(){
        return "inicio";
    }

    @GetMapping(value = "")
    public String Principal() {
        return "redirect:/inicio";
    }
}
