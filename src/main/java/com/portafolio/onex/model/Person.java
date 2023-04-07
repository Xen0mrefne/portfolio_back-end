/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author x3n0g
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String title;
    private String about;
    private String profileImageUrl;
    private String profileImageName;
    private String bannerImageUrl;
    private String bannerImageName;
    
    @OneToMany(mappedBy = "person", cascade=CascadeType.REMOVE)
    private List<Experience> experiences;
    
    @OneToMany(mappedBy = "person", cascade=CascadeType.REMOVE)
    private List<Tech> techs;
    
    @OneToMany(mappedBy = "person", cascade=CascadeType.REMOVE)
    private List<Degree> degrees;
    
    @OneToMany(mappedBy = "person", cascade=CascadeType.REMOVE)
    private List<Skill> skills;
    
    @OneToMany(mappedBy = "person", cascade=CascadeType.REMOVE)
    private List<Project> projects;
    
}
