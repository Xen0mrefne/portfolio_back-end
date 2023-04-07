/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.dto.TechDto;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Person;
import com.portafolio.onex.model.Tech;
import com.portafolio.onex.service.IPersonService;
import com.portafolio.onex.service.ITechService;
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
public class TechController {
    
    @Autowired
    IPersonService iPerson;
    
    @Autowired
    ITechService iTech;
    
    @GetMapping("/techs")
    public ResponseEntity<List<TechDto>> getAllTech(){
        List<Tech> techList = iTech.getAllTechs();
        List<TechDto> dtoList = new ArrayList<>();

        for (Tech tech : techList) {
            TechDto techDto = new TechDto(
                    tech.getId(),
                    tech.getName(),
                    tech.getTechType(),
                    tech.getPerson().getId());
            
            dtoList.add(techDto);
        }
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
    
    @GetMapping("persons/{personId}/techs")
    public ResponseEntity<List<TechDto>> getAllTech(@PathVariable Long personId){
        List<Tech> techList = iTech.getTechsByPerson(personId);
        List<TechDto> dtoList = new ArrayList<>();

        for (Tech tech : techList) {
            TechDto techDto = new TechDto(
                    tech.getId(),
                    tech.getName(),
                    tech.getTechType(),
                    tech.getPerson().getId());
            
            dtoList.add(techDto);
        }
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("persons/{personId}/techs")
    public ResponseEntity<Message> addTech(@PathVariable Long personId, @RequestBody Tech newTech){
        if (newTech.getName().isBlank()){
            return new ResponseEntity(new Message("Tech name is required"), HttpStatus.BAD_REQUEST);
        }
        
        Person person = iPerson.getPerson(personId);
        newTech.setPerson(person);
        
        iTech.addTech(newTech);
        return new ResponseEntity(new Message("Tech added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/techs/{id}")
    public ResponseEntity<Message> editTech(@PathVariable Long id,@RequestBody Tech updatedTech){
        if (updatedTech.getName().isBlank()){
            return new ResponseEntity(new Message("Tech name is required"), HttpStatus.BAD_REQUEST);
        }
        
        iTech.editTech(id, updatedTech);
        return new ResponseEntity(new Message("Tech updated successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/techs/{id}")
    public ResponseEntity<Message> deleteTech(@PathVariable Long id){
        iTech.deleteTech(id);
        return new ResponseEntity(new Message("Tech deleted successfully."), HttpStatus.OK);
    }
    
}
