package org.ocp.dao;

import java.util.List;

import org.ocp.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ModelRepository extends JpaRepository<Model, Integer>{
	@Query("SELECT m FROM Model m WHERE m.nom_model=:x")
    Model findModelByNom(@Param("x") String nom);
	
	@Query("SELECT m FROM Model m WHERE m.fournisseur.nom_fournisseur=:x")
    List<Model> findModelbyFournisseur(@Param("x") String nom);
}
