package org.ocp.dao;

import java.util.List;
import org.ocp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	@Query("SELECT m FROM Message m WHERE m.codeDestinataire like %?1")
    List<Message> findMessagesByCode(String codeUser);
	
	@Query("SELECT m FROM Message m WHERE m.codeEmetteur like %?1")
    List<Message> findMessagesByCodeEnv(String codeUser);
	
	@Query("SELECT m FROM Message m WHERE m.etat_message=:x")
	List<Message> findMessagesByState(@Param("x") int stateMessage);
	
	@Query("SELECT m FROM Message m WHERE m.etat_message=:x AND m.codeDestinataire=:y")
	List<Message> findMessagesByStateAndSend(@Param("x") int stateMessage, @Param("y") String codeDestinataire);
}
