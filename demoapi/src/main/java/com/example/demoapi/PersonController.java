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

    @GetMapping(path = "/delete")
    public ResponseEntity<Optional<Person>> deleteStuff(){
        return new ResponseEntity<Optional<Person>>(personService.deleteStuff(), HttpStatus.OK);
    }

    @GetMapping(path = "/edit")
    public ResponseEntity<Optional<Person>> editStuff(){
        return new ResponseEntity<Optional<Person>>(personService.editStuff(), HttpStatus.OK);
    }

    @GetMapping(path = "/del")
    public void delOne(){
        personService.delDoc();
        return;
    }

    @GetMapping(path = "/change")
    public ResponseEntity<Optional<Person>> change(){
        return new ResponseEntity<Optional<Person>>(personService.change(), HttpStatus.OK);
    }

    @GetMapping(path = "/add")
    public void add(){
        personService.addPerson();
        return;
    }
}
