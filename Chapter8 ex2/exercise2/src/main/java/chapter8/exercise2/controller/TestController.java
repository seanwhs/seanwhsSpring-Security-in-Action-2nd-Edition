// TestController.java`
package chapter8.exercise2.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//Definition of the four endpoints for which we configure authorization
@RestController
public class TestController {
    @PostMapping("/a")
    public String postEndpointA() {
        return "Works!";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return "Works!";
    }

    @GetMapping("/a/b")
    public String getEnpointB() {
        return "Works!";
    }

    @GetMapping("/a/b/c")
    public String getEnpointC() {
        return "Works!";
    }
}