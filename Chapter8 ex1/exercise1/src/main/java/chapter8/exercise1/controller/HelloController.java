// HelloController.java
package chapter8.exercise1.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/ciao")    
    public String sayCiao(){
        return "Ciao";
    }

    @GetMapping("/niHao")
    public String sayNiHao(){
        return "你好";
    }
   
    @GetMapping("/hola")
    public String sayHola(){
        return "Hola";
    }

}
