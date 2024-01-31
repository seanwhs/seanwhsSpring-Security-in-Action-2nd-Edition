// HelloController.java
package chapter6.exercise6.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
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

    // passing the Authentication object explicitly to the asynchronous method
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

    // Running the task decorated by DelegatingSecurityContextCallable
    // Running the task decorated by DelegatingSecurityContextCallable
    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();

        try {
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    // Propagating the SecurityContext
    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();
    
            // Check if authentication is not null before accessing its properties
            String username = (authentication != null) ? authentication.getName() : "Anonymous";
            return "Hola, " + username + "!";
        };
    
        ExecutorService e = Executors.newCachedThreadPool();
        e = new DelegatingSecurityContextExecutorService(e);
    
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }
    
}
