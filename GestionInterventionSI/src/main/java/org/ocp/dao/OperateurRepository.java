package org.ocp.dao;

import org.ocp.entities.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperateurRepository extends JpaRepository<Operateur, Integer> {
	@Query("SELECT o FROM Operateur o WHERE o.username=:x")
	Operateur findOperateurByCode(@Param("x") String code_operateur);
}
