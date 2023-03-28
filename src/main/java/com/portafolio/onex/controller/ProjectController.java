/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.controller;

import com.portafolio.onex.model.Project;
import com.portafolio.onex.model.Message;
import com.portafolio.onex.service.IProjectService;
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
@CrossOrigin(origins = "http://localhost:4200/")
public class ProjectController {
    
    @Autowired
    IProjectService iProject;
    
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProject(){
        List<Project> projectList = iProject.getAllProjects();
        return new ResponseEntity(projectList, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/projects")
    public ResponseEntity<Message> addProject(@RequestBody Project newProject){
        
        iProject.addProject(newProject);
        return new ResponseEntity(new Message("Project added successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/projects/{id}")
    public ResponseEntity<Message> editProject(@PathVariable Long id,@RequestBody Project updatedProject){
        if (updatedProject.getName().isBlank()){
            return new ResponseEntity(new Message("Project name is required"), HttpStatus.BAD_REQUEST);
        }
        
        iProject.editProject(id, updatedProject);
        return new ResponseEntity(new Message("Project updated successfully."), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Message> deleteProject(@PathVariable Long id){
        iProject.deleteProject(id);
        return new ResponseEntity(new Message("Project deleted successfully."), HttpStatus.OK);
    }
    
}
