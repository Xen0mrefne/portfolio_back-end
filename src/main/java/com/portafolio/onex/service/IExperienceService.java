/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Experience;
import java.util.List;

/**
 *
 * @author x3n0g
 */

public interface IExperienceService {
    
    public List<Experience> getAllExperience();
    
    public void addExperience(Experience newExperience);
    
    public void editExperience(Long id, Experience updatedExperience);
    
    public void deleteExperience(Long id);
    
    public boolean existsByName(String name);
}
