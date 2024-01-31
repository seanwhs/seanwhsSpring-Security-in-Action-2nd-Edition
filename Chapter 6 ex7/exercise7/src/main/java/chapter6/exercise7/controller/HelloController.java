// HelloController.java
package chapter6.exercise7.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello.html";
    }
    @GetMapping("/showError")
    public String getErrorPage(){
        return "error.html";
    }
}
