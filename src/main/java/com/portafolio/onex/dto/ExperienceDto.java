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
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;

    public ExperienceDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
}
