// HelloController.java
package chapter6.exercise2.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    // Obtaining the SecurityContext from the SecurityContextHolder
    @GetMapping("/ContextHolder")
    public String hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        return "Hello from Security ContextHolder:: " + a.getName() + "!";
    }

    // Spring injects Authentication value in the parameter of the method
    @GetMapping("/parameter")
    public String hello(Authentication a) {
        return "Hello, injecting AuthN Obj into parameter:: " + a.getName() + "!";
    }
}
