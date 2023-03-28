/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Person;
import com.portafolio.onex.model.Tech;
import com.portafolio.onex.service.IPersonService;
import com.portafolio.onex.service.ITechService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author x3n0g
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class PersonController {
    
    @Autowired
    private IPersonService iPerson;
    
    @Autowired
    private ITechService iTech;
    
    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> personList = iPerson.getAllPersons();
        return new ResponseEntity(personList, HttpStatus.OK);
    }
    
    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person person = iPerson.getPerson(id);
        return new ResponseEntity(person, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persons")
    public ResponseEntity<Message> addPerson(@RequestBody Person newPerson){
        iPerson.addPerson(newPerson);
        return new ResponseEntity(new Message("Person added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/persons/{id}")
    public ResponseEntity<Message> editPerson(@PathVariable Long id, @RequestBody Person updatedPerson){
        iPerson.editPerson(id, updatedPerson);
        return new ResponseEntity(new Message("Person edited successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Message> deletePerson(@PathVariable Long id){
        iPerson.deletePerson(id);
        return new ResponseEntity(new Message("Person deleted successfully."), HttpStatus.OK);
    }
}
