package com.example.demoapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons(){
        return new ResponseEntity<List<Person>>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/one")
    public ResponseEntity<Optional<Person>> getByUsername(){
        return new ResponseEntity<>(personService.getByUsername(), HttpStatus.OK);
    }
}
