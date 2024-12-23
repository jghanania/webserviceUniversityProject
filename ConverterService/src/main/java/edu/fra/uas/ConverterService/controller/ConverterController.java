package edu.fra.uas.ConverterService.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/converter")

public class ConverterController {

    @GetMapping("/test")
    public String testEndpoint() {
        return "CurrencyConverterService läuft!";
    }

}
