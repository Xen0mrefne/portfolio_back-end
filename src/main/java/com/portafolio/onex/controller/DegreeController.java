/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.dto.DegreeDto;
import com.portafolio.onex.model.Degree;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Person;
import com.portafolio.onex.service.IDegreeService;
import com.portafolio.onex.service.IPersonService;
import java.util.ArrayList;
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
@CrossOrigin(origins = {"https://ortega-portfolio.web.app", "http://localhost:4200"})
public class DegreeController {
    
    @Autowired
    IPersonService iPerson;
    
    @Autowired
    IDegreeService iDegree;
    
    @GetMapping("/degrees")
    public ResponseEntity<List<DegreeDto>> getAllDegrees(){
        List<Degree> degreeList = iDegree.getAllDegrees();
        List<DegreeDto> dtoList = new ArrayList<>();
        
        for (Degree degree: degreeList) {
            DegreeDto degreeDto = new DegreeDto(
                degree.getId(),
                degree.getTitle(),
                degree.getInstitution(),
                degree.isFinished(),
                degree.getStartDate(),
                degree.getEndDate(),
                degree.getPerson().getId());
            
            dtoList.add(degreeDto);
        }
        
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
    
    @GetMapping("persons/{personId}/degrees")
    public ResponseEntity<List<DegreeDto>> getPersonDegrees(@PathVariable Long personId){
        List<Degree> degreeList = iDegree.getDegreesByPerson(personId);
        List<DegreeDto> dtoList = new ArrayList<>();
        
        for (Degree degree: degreeList) {
            DegreeDto degreeDto = new DegreeDto(
                degree.getId(),
                degree.getTitle(),
                degree.getInstitution(),
                degree.isFinished(),
                degree.getStartDate(),
                degree.getEndDate(),
                degree.getPerson().getId());
            
            dtoList.add(degreeDto);
        }
        
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("persons/{personId}/degrees")
    public ResponseEntity<Message> addDegree(@PathVariable Long personId, @RequestBody Degree newDegree){
        if (newDegree.getTitle().isBlank()){
            return new ResponseEntity(new Message("Degree title is required"), HttpStatus.BAD_REQUEST);
        }
        if (newDegree.getInstitution().isBlank()){
            return new ResponseEntity(new Message("Institution is required"), HttpStatus.BAD_REQUEST);
        }
        if (newDegree.getStartDate() == null){
            return new ResponseEntity(new Message("Start date is required"), HttpStatus.BAD_REQUEST);
        }
        if (newDegree.isFinished() == false) {
            newDegree.setEndDate(null);
        }
        
        Person person = iPerson.getPerson(personId);
        newDegree.setPerson(person);
        
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
        if (updatedDegree.getStartDate() == null){
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
