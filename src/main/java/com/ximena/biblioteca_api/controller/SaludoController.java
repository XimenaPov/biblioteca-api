package com.ximena.biblioteca_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo() {
        return "¡Hola desde Spring Boot!";
    }

    @GetMapping("/saludo/{nombre}")
    public String saludarPersona(@PathVariable String nombre) {
        return "¡Hola, " + nombre + "! Bienvenida a tu primera API.";
    }
}