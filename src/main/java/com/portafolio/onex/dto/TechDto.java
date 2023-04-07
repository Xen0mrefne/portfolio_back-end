/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.dto;

import com.portafolio.onex.model.TechType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author x3n0g
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TechDto {
    
    private Long id;
    private String name;
    private TechType techType;
    private Long personId;
    
}
