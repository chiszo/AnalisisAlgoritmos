package edu.upn.CasoPractico1.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class Home {
    @GetMapping("/inicio")
    public String Inicio(){
        return "inicio";
    }
}
