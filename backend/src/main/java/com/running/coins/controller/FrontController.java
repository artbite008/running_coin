package com.running.coins.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class FrontController {

    @PostMapping("/list/users")
    public String listUsers(@RequestParam String userId, @RequestParam String groupId) {
        return null;
    }

    @PostMapping("/submit/distance")
    public String submitDistance(@RequestParam String userId, @RequestParam String distance) {
        return null;
    }

    @PostMapping("/vote")
    public String vote(@RequestParam String userId, @RequestParam String voteStatus) {
        return null;
    }

    @PostMapping("/submit/target")
    public String submitTarget(@RequestParam String userId, @RequestParam String targetDisdance) {
        return null;
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable("id") String userId) {
        return null;
    }

}
