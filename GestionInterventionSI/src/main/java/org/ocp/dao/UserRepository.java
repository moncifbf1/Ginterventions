package org.ocp.dao;

import java.util.List;

import org.ocp.entities.Administrateur;
import org.ocp.entities.Operateur;
import org.ocp.entities.Responsable;
import org.ocp.entities.User;
import org.ocp.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String>{
	@Query("SELECT u FROM User u WHERE u.discrimUser='OPERATEUR'")
    List<Operateur> getAllOperateurs();
	
	@Query("SELECT u FROM User u WHERE u.discrimUser='RESPONSABLE'")
    List<Responsable> getAllReponsables();
	
	@Query("SELECT u FROM User u WHERE u.discrimUser='UTILISATEUR'")
    List<Utilisateur> getAllUtilisateurs();
	
	@Query("SELECT u FROM User u WHERE u.discrimUser='ADMINISTRATEUR'")
    List<Administrateur> getAllAdministrateurs();
	
	@Query("SELECT COUNT(u) FROM User u WHERE u.discrimUser='OPERATEUR'")
	int countOperateurs();
	
	@Query("SELECT u FROM User u WHERE u.username not like :x")
    List<User> getRestrictedUser(@Param("x") String username);
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE :x OR u.lastName LIKE :x OR u.username LIKE :x OR u.telephone LIKE :x OR u.mail LIKE :x")
	List<User> searchUser(@Param("x") String search);
}
