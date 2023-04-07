/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.onex.repository;

import com.portafolio.onex.model.Degree;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author x3n0g
 */

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Long> {
    public List<Degree> findByPersonId(Long personId);
    
}
