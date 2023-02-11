package com.example.demoapi;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, ObjectId>{
    
    @Query(value = "{'username' : ?0}", fields = "{'password' : ?0, 'id' : ?0}")
    Optional<Person> findByUsername(String username);
}
