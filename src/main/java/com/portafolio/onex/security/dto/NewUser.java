/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.security.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author x3n0g
 */

@Getter @Setter
public class NewUser {
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
    
    
}
