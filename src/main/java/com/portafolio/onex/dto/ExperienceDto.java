/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author x3n0g
 */

@Getter @Setter
public class ExperienceDto {
    
    private Long id;
    private String name;
    private String company;
    private String description;
    private Long personId;

    public ExperienceDto(Long id, String name, String company, String description, Long personId) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.description = description;
        this.personId = personId;
    }
    
    
    
}
