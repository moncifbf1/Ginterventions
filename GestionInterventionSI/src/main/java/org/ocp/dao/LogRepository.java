package org.ocp.dao;


import org.ocp.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Integer>{

}
