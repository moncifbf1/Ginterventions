package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Log;

public interface LogMetier {
	public Log addLog(Log l);
	public List<Log> listLog();
}
