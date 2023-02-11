package com.example.demoapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> getByUsername(){
        return personRepository.findByUsername("newuser2");
    }

    public String doStuff(){
        mongoTemplate.update(Person.class)
            .matching(Criteria.where("username").is("newuser2"))
            .apply(new Update().push("myList").value(new Person("bitch2","mypw")))
            .first();

        return "OK";
    }
}
