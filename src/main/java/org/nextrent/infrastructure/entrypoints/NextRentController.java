package org.nextrent.infrastructure.entrypoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NextRentController {

    @GetMapping("/hello")
    public String hello() {
        return "Conexión exitosa";
    }


}
