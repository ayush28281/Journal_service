package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserEntryControllerWithMongo {
    @Autowired
    private UserEntryService services;




    @PutMapping
    public ResponseEntity<Users> updateUser(@RequestBody Users newUser){
        Authentication old= SecurityContextHolder.getContext().getAuthentication();
        Users user=services.findByUsername(old.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        services.saveUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
    @DeleteMapping
    public ResponseEntity<Users> DeleteUser(){
        Authentication old= SecurityContextHolder.getContext().getAuthentication();
        services.delete(old.getName());


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
}

