package br.edu.romulo.ms_pessoa.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/ola")
public class OlaController {
	
	@GetMapping
    public String olaHelloWord(){
        return "Hello World!";
    }
}
