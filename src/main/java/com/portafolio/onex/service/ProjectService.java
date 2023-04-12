/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.service;

import com.portafolio.onex.model.Project;
import com.portafolio.onex.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
public class ProjectService implements IProjectService {
    
    @Autowired
    ProjectRepository projectRepo;

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }
    
    @Override
    public List<Project> getProjectsByPerson(Long personId){
        return projectRepo.findByPersonId(personId);
    }

    @Override
    public void addProject(Project newProject) {
        projectRepo.save(newProject);
    }

    @Override
    public void editProject(Long id, Project updatedProject) {
        Project project = projectRepo.findById(id).orElse(null);
        
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setUrl(updatedProject.getUrl());
        
        projectRepo.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
    
    
    
}
