/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Degree;
import com.portafolio.onex.repository.DegreeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class DegreeService implements IDegreeService {
    
    @Autowired
    DegreeRepository degreeRepo;

    @Override
    public List<Degree> getAllDegrees() {
        return degreeRepo.findAll();
    }

    @Override
    public void addDegree(Degree newDegree) {
        degreeRepo.save(newDegree);
    }

    @Override
    public void editDegree(Long id, Degree updatedDegree) {
        Degree degree = degreeRepo.findById(id).orElse(null);
        
        degree.setTitle(updatedDegree.getTitle());
        degree.setInstitution(updatedDegree.getInstitution());
        degree.setStartDate(updatedDegree.getStartDate());
        degree.setEndDate(updatedDegree.getEndDate());
        
        degreeRepo.save(degree);
    }

    @Override
    public void deleteDegree(Long id) {
        degreeRepo.deleteById(id);
    }
    
    
    
}
