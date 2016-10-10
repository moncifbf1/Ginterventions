package org.ocp.dao;

import org.ocp.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer>{
	@Query("SELECT f FROM Fournisseur f WHERE f.nom_fournisseur=:x")
    Fournisseur findFournisseurByNom(@Param("x") String nom);
}
