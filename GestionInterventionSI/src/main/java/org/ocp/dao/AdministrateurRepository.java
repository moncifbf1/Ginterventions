package org.ocp.dao;

import org.ocp.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {

}
