package com.example.demoapi;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;

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
            .apply(new Update().push("myList").value(new Person("bitch1","mypw")))
            .first();

        return "OK";
    }

    public Optional<Person> deleteStuff(){
        mongoTemplate.updateFirst(
            Query.query(Criteria.where("username").is("newuser2")),
            new Update().pull("myList", new BasicDBObject("username", "bitch2")),
            Person.class
        );

        return personRepository.findByUsername("newuser2");
    }

    public Optional<Person> editStuff(){

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("newuser2").and("myList")
                .elemMatch(Criteria.where("username").is("bitch1")));

        Update update = new Update();
        update.set("myList.$.username", "notbitch");
        
        mongoTemplate.findAndModify(query, update, Person.class);
        //mongoTemplate.updateFirst(query, update, Person.class);
  

        return personRepository.findByUsername("newuser2");
    }
}
