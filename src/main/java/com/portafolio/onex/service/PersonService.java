/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Person;
import com.portafolio.onex.repository.PersonRepository;
import com.portafolio.onex.repository.TechRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class PersonService implements IPersonService {
    
    @Autowired
    private PersonRepository personRepo;
    
    
    @Override
    public List<Person> getAllPersons(){
        return personRepo.findAll();
    }
    
    @Override
    public Person getPerson(Long id) {
        return personRepo.findById(id).orElse(null);
    }
    
    @Override
    public void addPerson(Person newPerson) {
        personRepo.save(newPerson);
    }
    
    @Override
    public void editPerson(Long id, Person updatedPerson){
        Person person = getPerson(id);
        
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setTitle(updatedPerson.getTitle());
        person.setAbout(updatedPerson.getAbout());
        person.setProfileImage(updatedPerson.getProfileImage());
        person.setBannerImage(updatedPerson.getBannerImage());
        
        
        personRepo.save(person);
    }
    
    @Override
    public void deletePerson(Long id){
        personRepo.deleteById(id);
    }
    
    
}
