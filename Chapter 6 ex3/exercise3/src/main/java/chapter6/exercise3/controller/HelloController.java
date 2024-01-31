// HelloController.java
package chapter6.exercise3.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
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

    // An @Async method served by a different thread
    // Only future and void can be returned
    // @GetMapping("/bye")
    // @Async
    // public void goodbye() {
    // SecurityContext context = SecurityContextHolder.getContext();
    // String username = context.getAuthentication().getName();
    // System.out.println("Hello, Async - MODE_INHERITABLETHREADLOCAL:: " + username
    // + "!");
    // }

    //passing the Authentication object explicitly to the asynchronous method
    @GetMapping("/bye")
    @Async
    public void goodbye(Authentication authentication) {
        String username = authentication != null ? authentication.getName() : "Unknown";
        System.out.println("Hello, Async - MODE_INHERITABLETHREADLOCAL:: " + username + "!");
    }

    // returning a response body with CompletableFuture.supplyAsync for async
    // operations
    @GetMapping("/CompletableFutureBye")
    public CompletableFuture<String> goodbyeCompletableFuture() {
        return CompletableFuture.supplyAsync(() -> {
            // Your asynchronous logic here
            SecurityContext context = SecurityContextHolder.getContext();
            String username = context.getAuthentication().getName();
            return "Hello, Async (CompletableFuture) - " + username + "!";
        });
    }
}
