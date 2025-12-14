package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserEntryService services;
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user){
        services.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
