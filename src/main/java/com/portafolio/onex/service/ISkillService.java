/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Skill;
import java.util.List;

/**
 *
 * @author x3n0g
 */
public interface ISkillService {
    
    public List<Skill> getAllSkills();
    
    public List<Skill> getSkillsByPerson(Long id);
    
    public void addSkill(Skill newSkill);
    
    public void editSkill(Long id, Skill updatedSkill);
    
    public void deleteSkill(Long id);
    
}
