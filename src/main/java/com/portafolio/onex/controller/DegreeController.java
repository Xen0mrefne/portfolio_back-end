/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Degree;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.service.IDegreeService;
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
@CrossOrigin(origins = {"https://angular-portfolio-d72e0.web.app/home", "http://localhost:4200"})
public class DegreeController {
    
    @Autowired
    IDegreeService iDegree;
    
    @GetMapping("/degrees")
    public ResponseEntity<List<Degree>> getAllDegree(){
        List<Degree> degreeList = iDegree.getAllDegrees();
        return new ResponseEntity(degreeList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/degrees")
    public ResponseEntity<Message> addDegree(@RequestBody Degree newDegree){
        if (newDegree.getTitle().isBlank()){
            return new ResponseEntity(new Message("Degree title is required"), HttpStatus.BAD_REQUEST);
        }
        if (newDegree.getInstitution().isBlank()){
            return new ResponseEntity(new Message("Institution is required"), HttpStatus.BAD_REQUEST);
        }
        if (newDegree.getStartDate().isBlank()){
            return new ResponseEntity(new Message("Start date is required"), HttpStatus.BAD_REQUEST);
        }
        
        iDegree.addDegree(newDegree);
        return new ResponseEntity(new Message("Degree added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/degrees/{id}")
    public ResponseEntity<Message> editDegree(@PathVariable Long id,@RequestBody Degree updatedDegree){
        if (updatedDegree.getTitle().isBlank()){
            return new ResponseEntity(new Message("Degree title is required"), HttpStatus.BAD_REQUEST);
        }
        if (updatedDegree.getInstitution().isBlank()){
            return new ResponseEntity(new Message("Institution is required"), HttpStatus.BAD_REQUEST);
        }
        if (updatedDegree.getStartDate().isBlank()){
            return new ResponseEntity(new Message("Start date is required"), HttpStatus.BAD_REQUEST);
        }
        
        iDegree.editDegree(id, updatedDegree);
        return new ResponseEntity(new Message("Degree updated successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/degrees/{id}")
    public ResponseEntity<Message> deleteDegree(@PathVariable Long id){
        iDegree.deleteDegree(id);
        return new ResponseEntity(new Message("Degree deleted successfully."), HttpStatus.OK);
    }
    
}
