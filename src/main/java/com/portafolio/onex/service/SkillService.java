/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Skill;
import com.portafolio.onex.repository.SkillRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class SkillService implements ISkillService {
    
    @Autowired
    SkillRepository skillRepo;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepo.findAll();
    }

    @Override
    public void addSkill(Skill newSkill) {
        skillRepo.save(newSkill);
    }

    @Override
    public void editSkill(Long id, Skill updatedSkill) {
        Skill skill = skillRepo.findById(id).orElse(null);
        
        skill.setName(updatedSkill.getName());
        skill.setPercent(updatedSkill.getPercent());
        
        skillRepo.save(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }
    
}
