package vaccination.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple hello world endpoint.
 */
@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    @Secured({"ROLE_USER"})
    public String helloWorld() {
        return "Hello World!";
    }
}
