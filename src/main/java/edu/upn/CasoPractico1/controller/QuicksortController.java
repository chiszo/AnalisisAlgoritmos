package edu.upn.CasoPractico1.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuicksortController {
    @GetMapping("/quicksort")
    public String Inicio(){
        return "quicksort";
    }
}
