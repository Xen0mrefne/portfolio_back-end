/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Experience;
import com.portafolio.onex.repository.ExperienceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class ExperienceService implements IExperienceService {
    
    @Autowired
    ExperienceRepository experienceRepo;

    @Override
    public List<Experience> getAllExperience() {
        return experienceRepo.findAll();
    }

    @Override
    public List<Experience> getExperiencesByPerson(Long id) {
        return experienceRepo.findByPersonId(id);
    }

    @Override
    public void addExperience(Experience newExperience) {
        experienceRepo.save(newExperience);
    }

    @Override
    public void editExperience(Long id, Experience updatedExperience) {
        Experience experience = experienceRepo.findById(id).orElse(null);
        
        experience.setName(updatedExperience.getName());
        experience.setCompany(updatedExperience.getCompany());
        experience.setDescription(updatedExperience.getDescription());
        
        experienceRepo.save(experience);
    }

    @Override
    public void deleteExperience(Long id) {
        experienceRepo.deleteById(id);
    }
    
}
