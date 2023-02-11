package com.example.demoapi;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "newusers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    @Id
    private ObjectId id;
    private String username;
    private String password;

    public Person(String username, String password){
        this.username = username;
        this.password = password;
    }
}
