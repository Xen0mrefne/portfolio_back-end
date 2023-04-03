/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Experience;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.service.IExperienceService;
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
@CrossOrigin(origins = {"https://angular-portfolio-d72e0.web.app", "http://localhost:4200"})
public class ExperienceController {
    
    @Autowired
    IExperienceService iExperience;
    
    @GetMapping("/experiences")
    public ResponseEntity<List<Experience>> getAllExperience(){
        List<Experience> experienceList = iExperience.getAllExperience();
        return new ResponseEntity(experienceList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/experiences")
    public ResponseEntity<Message> addExperience(@RequestBody Experience newExperience){
        if (newExperience.getName().isBlank()){
            return new ResponseEntity(new Message("Position name is required"), HttpStatus.BAD_REQUEST);
        }
        if (newExperience.getCompany().isBlank()) {
            return new ResponseEntity(new Message("Company name is required"), HttpStatus.BAD_REQUEST);
        }
        if(iExperience.existsByName(newExperience.getName())){
            return new ResponseEntity(new Message("Experience already exists"), HttpStatus.BAD_REQUEST);
        }
        
        iExperience.addExperience(newExperience);
        return new ResponseEntity(new Message("Experience added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiences/{id}")
    public ResponseEntity<Message> editExperience(@PathVariable Long id,@RequestBody Experience updatedExperience){
        if (updatedExperience.getName().isBlank()){
            return new ResponseEntity(new Message("Position name is required"), HttpStatus.BAD_REQUEST);
        }
        if (updatedExperience.getCompany().isBlank()) {
            return new ResponseEntity(new Message("Company name is required"), HttpStatus.BAD_REQUEST);
        }
        
        iExperience.editExperience(id, updatedExperience);
        return new ResponseEntity(new Message("Experience updated successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/experiences/{id}")
    public ResponseEntity<Message> deleteExperience(@PathVariable Long id){
        iExperience.deleteExperience(id);
        return new ResponseEntity(new Message("Experience deleted successfully."), HttpStatus.OK);
    }
}
