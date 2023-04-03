/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Tech;
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
@CrossOrigin(origins = "**")
public class TechController {
    
    @Autowired
    ITechService iTech;
    
    @GetMapping("/techs")
    public ResponseEntity<List<Tech>> getAllTech(){
        List<Tech> techList = iTech.getAllTechs();
        return new ResponseEntity(techList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/techs")
    public ResponseEntity<Message> addTech(@RequestBody Tech newTech){
        if (newTech.getName().isBlank()){
            return new ResponseEntity(new Message("Tech name is required"), HttpStatus.BAD_REQUEST);
        }
        
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
