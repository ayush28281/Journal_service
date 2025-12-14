package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UserEntryRepository;
import net.engineeringdigest.journalApp.services.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserEntryRepository repository;
    private UserEntryService userService;

    @GetMapping("get-all")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("create-admin")
    public ResponseEntity<Users> createAdmin(@RequestBody Users user){
        userService.saveAdmin(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
