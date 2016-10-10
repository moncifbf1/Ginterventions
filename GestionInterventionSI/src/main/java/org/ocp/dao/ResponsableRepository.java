package org.ocp.dao;

import org.ocp.entities.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResponsableRepository extends JpaRepository<Responsable, Integer> {
	@Query("SELECT r FROM Responsable r WHERE r.username LIKE '%:x%'")
    Responsable findResponsableByCode(@Param("x") String username);
}
