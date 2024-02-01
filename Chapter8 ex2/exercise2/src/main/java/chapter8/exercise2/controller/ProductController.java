//ProductController.java
package chapter8.exercise2.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    //The definition of an endpoint with a path variable in a controller class
    @GetMapping("/product/{code}")
    public String productCode(@PathVariable String code) {
        return code;
    }
}
