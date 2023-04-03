/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Skill;
import com.portafolio.onex.service.ISkillService;
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
public class SkillController {
    
    @Autowired
    ISkillService iSkill;
    
    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkill(){
        List<Skill> skillList = iSkill.getAllSkills();
        return new ResponseEntity(skillList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/skills")
    public ResponseEntity<Message> addSkill(@RequestBody Skill newSkill){
        if (newSkill.getName().isBlank()){
            return new ResponseEntity(new Message("Skill name is required"), HttpStatus.BAD_REQUEST);
        }
        
        iSkill.addSkill(newSkill);
        return new ResponseEntity(new Message("Skill added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skills/{id}")
    public ResponseEntity<Message> editSkill(@PathVariable Long id,@RequestBody Skill updatedSkill){
        if (updatedSkill.getName().isBlank()){
            return new ResponseEntity(new Message("Skill name is required"), HttpStatus.BAD_REQUEST);
        }
        
        iSkill.editSkill(id, updatedSkill);
        return new ResponseEntity(new Message("Skill updated successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Message> deleteSkill(@PathVariable Long id){
        iSkill.deleteSkill(id);
        return new ResponseEntity(new Message("Skill deleted successfully."), HttpStatus.OK);
    }
    
}
