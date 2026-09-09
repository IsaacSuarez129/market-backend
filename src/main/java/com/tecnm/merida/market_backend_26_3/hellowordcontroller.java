package com.tecnm.merida.market_backend_26_3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/saludar")
public class hellowordcontroller {
    @GetMapping ( "/saludo")
    public String helloword(){
        return "hello word";
    }
}
