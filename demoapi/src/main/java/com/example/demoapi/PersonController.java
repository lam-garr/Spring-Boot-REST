package com.example.demoapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Person>> getAllPersons(){
        return new ResponseEntity<List<Person>>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping(path = "/one")
    public ResponseEntity<Optional<Person>> getByUsername(){
        return new ResponseEntity<Optional<Person>>(personService.getByUsername(), HttpStatus.OK);
    }

    @GetMapping(path = "/stuff")
    public ResponseEntity<String> doStuff(){
        return new ResponseEntity<String>(personService.doStuff(), HttpStatus.OK);
    }

    @GetMapping(path = "/delte")
    public ResponseEntity<Optional<Person>> deleteStuff(){
        return new ResponseEntity<Optional<Person>>(personService.deleteStuff(), HttpStatus.OK);
    }
}
