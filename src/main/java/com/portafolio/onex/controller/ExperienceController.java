/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.dto.ExperienceDto;
import com.portafolio.onex.model.Experience;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.model.Person;
import com.portafolio.onex.service.IExperienceService;
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
public class ExperienceController {

    @Autowired
    IPersonService iPerson;

    @Autowired
    IExperienceService iExperience;

    @GetMapping("/experiences")
    public ResponseEntity<List<ExperienceDto>> getAllExperience() {
        List<Experience> experienceList = iExperience.getAllExperience();
        List<ExperienceDto> dtoList = new ArrayList<>();

        for (Experience experience : experienceList) {
            ExperienceDto experienceDto = new ExperienceDto(
                    experience.getId(),
                    experience.getName(),
                    experience.getCompany(),
                    experience.getDescription(),
                    experience.getPerson().getId());
            
            dtoList.add(experienceDto);
        }
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }

    @GetMapping("persons/{personId}/experiences")
    public ResponseEntity<List<ExperienceDto>> getPersonExperiences(@PathVariable Long personId) {
        List<Experience> experienceList = iExperience.getExperiencesByPerson(personId);
        List<ExperienceDto> dtoList = new ArrayList<>();

        for (Experience experience : experienceList) {
            ExperienceDto experienceDto = new ExperienceDto(
                    experience.getId(),
                    experience.getName(),
                    experience.getCompany(),
                    experience.getDescription(),
                    experience.getPerson().getId());
            
            dtoList.add(experienceDto);
        }
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("persons/{personId}/experiences")
    public ResponseEntity<Message> addExperience(@PathVariable Long personId, @RequestBody Experience newExperience) {
        if (newExperience.getName().isBlank()) {
            return new ResponseEntity(new Message("Position name is required"), HttpStatus.BAD_REQUEST);
        }
        if (newExperience.getCompany().isBlank()) {
            return new ResponseEntity(new Message("Company name is required"), HttpStatus.BAD_REQUEST);
        }

        Person person = iPerson.getPerson(personId);
        newExperience.setPerson(person);

        iExperience.addExperience(newExperience);
        return new ResponseEntity(new Message("Experience added successfully."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiences/{id}")
    public ResponseEntity<Message> editExperience(@PathVariable Long id, @RequestBody Experience updatedExperience) {
        if (updatedExperience.getName().isBlank()) {
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
    public ResponseEntity<Message> deleteExperience(@PathVariable Long id) {
        iExperience.deleteExperience(id);
        return new ResponseEntity(new Message("Experience deleted successfully."), HttpStatus.OK);
    }
}
