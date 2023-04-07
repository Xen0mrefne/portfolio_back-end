/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Tech;
import com.portafolio.onex.repository.TechRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class TechService implements ITechService {
    
    @Autowired
    TechRepository techRepo;

    @Override
    public List<Tech> getAllTechs() {
        return techRepo.findAll();
    }

    @Override
    public List<Tech> getTechsByPerson(Long personId) {
        return techRepo.findByPersonId(personId);
    }

    @Override
    public void addTech(Tech newTech) {
        techRepo.save(newTech);
    }

    @Override
    public void editTech(Long id, Tech updatedTech) {
        Tech tech = techRepo.findById(id).orElse(null);
        
        tech.setName(updatedTech.getName());
        tech.setTechType(updatedTech.getTechType());
        
        techRepo.save(tech);
    }

    @Override
    public void deleteTech(Long id) {
        techRepo.deleteById(id);
    }
    
}
