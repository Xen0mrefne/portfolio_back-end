/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.onex.security.service;

import com.portafolio.onex.security.entity.Role;
import com.portafolio.onex.security.enums.RoleName;
import com.portafolio.onex.security.repository.IRoleRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author x3n0g
 */

@Service
@Transactional
public class RoleService {
    
    @Autowired
    IRoleRepository roleRepo;
    
    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepo.findByRoleName(roleName);
    }
    
    public void saveRole(Role role){
        roleRepo.save(role);
    }
}
