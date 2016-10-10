package org.ocp.dao;

import java.util.List;

import org.ocp.entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterventionRepository extends JpaRepository<Intervention, Integer>{
	@Query("SELECT i FROM Intervention i WHERE i.operateur.username=:x")
    List<Intervention> findInterventionByCode(@Param("x") String codeOperateur);
	
	@Query("SELECT i FROM Intervention i WHERE i.operateur.username=:x AND i.etat_intervention=:y")
    List<Intervention> findInterventionByCodeState(@Param("x") String codeOperateur, @Param("y") int etat);
	
	@Query("SELECT i FROM Intervention i WHERE i.id_intervention=:x")
    Intervention findInterventionById(@Param("x") int idIntervention);
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=0 ORDER BY i.id_intervention DESC")
	List<Intervention> lastEnAttenteIntervs();
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=1 ORDER BY i.id_intervention DESC")
	List<Intervention> lastEnCoursIntervs();
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=2 ORDER BY i.id_intervention DESC")
	List<Intervention> lastTermineesIntervs();
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=0 AND i.operateur.username=:x ORDER BY i.id_intervention DESC")
	List<Intervention> lastEnAttenteIntervsOper(@Param("x") String codeOperateur);
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=1 AND i.operateur.username=:x ORDER BY i.id_intervention DESC")
	List<Intervention> lastEnCoursIntervsOper(@Param("x") String codeOperateur);
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=2 AND i.operateur.username=:x ORDER BY i.id_intervention DESC")
	List<Intervention> lastTermineesIntervsOper(@Param("x") String codeOperateur);
	
	//SEARCH QUERIES
	
	@Query("SELECT i FROM Intervention i WHERE i.date_demande=:date")
    List<Intervention> searchDate(@Param("date") String date);
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=:etat")
    List<Intervention> searchEtat(@Param("etat") int etat);
	
	@Query("SELECT i FROM Intervention i WHERE i.etat_intervention=:etat AND i.date_demande=:date")
    List<Intervention> searchEtatDate(@Param("etat") int etat, @Param("date") String date);
	
	@Query("SELECT i FROM Intervention i WHERE i.niveau_intervention=:niveau")
    List<Intervention> searchNiveau(@Param("niveau") int niveau);
	
	@Query("SELECT i FROM Intervention i WHERE i.niveau_intervention=:niveau AND i.date_demande=:date")
    List<Intervention> searchNiveauDate(@Param("niveau") int niveau, @Param("date") String date);
	
	@Query("SELECT i FROM Intervention i WHERE i.niveau_intervention=:niveau AND i.etat_intervention=:etat")
    List<Intervention> searchNiveauEtat(@Param("niveau") int niveau, @Param("etat") int etat);
	
	@Query("SELECT i FROM Intervention i WHERE i.niveau_intervention=:niveau AND i.etat_intervention=:etat AND i.date_demande=:dateDemande")
    List<Intervention> searchNiveauEtatDateDemande(@Param("niveau") int niveau, @Param("etat") int etat, @Param("dateDemande") String dateDemande);
}
