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

    public Person getByUsername(){
        final Query query = new Query();
        query.addCriteria(Criteria.where("username").is("newuser2"));
        final Person person1 = mongoTemplate.findOne(query, Person.class);
        return person1;
    }

    //get single property from object
    public String getProperty(){
        final Query query = new Query();
        query.addCriteria(Criteria.where("username").is("newuser2"));
        final Person person1 = mongoTemplate.findOne(query, Person.class);

        return person1.getUsername();
    }

    //adds new object in array property called 'myList'
    public String doStuff(){
        mongoTemplate.update(Person.class)
            .matching(Criteria.where("username").is("newuser2"))
            .apply(new Update().push("myList").value(new Person("bitch1","mypw")))
            .first();

        return "OK";
    }

    //delete object in array property called 'myList'
    public Optional<Person> deleteStuff(){
        mongoTemplate.updateFirst(
            Query.query(Criteria.where("username").is("newuser2")),
            new Update().pull("myList", new BasicDBObject("username", "bitch2")),
            Person.class
        );

        return personRepository.findByUsername("newuser2");
    }

    //edit object in array property called 'myList'
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

    //delete document by username
    public void delDoc(){
        personRepository.deleteByUsername("newuser420");
    }

    //update docuemnt by username
    public Optional<Person> change(){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("newuser69"));
        
        Update update = new Update();
        update.set("username", "newusername420");
        
        mongoTemplate.findAndModify(query, update, Person.class);

        return personRepository.findByUsername("newusername420");
    }

    //add new document to db
    public void addPerson(){
        personRepository.save(new Person("newuser420", "password"));
        return;
    }
}
