/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Project;
import java.util.List;

/**
 *
 * @author x3n0g
 */
public interface IProjectService {
    
    public List<Project> getAllProjects();
    
    public void addProject(Project newProject);
    
    public void editProject(Long id, Project updatedProject);
    
    public void deleteProject(Long id);
    
}
