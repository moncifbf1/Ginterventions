package org.ocp.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ocp.dao.LogRepository;
import org.ocp.entities.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LogMetierImpl implements LogMetier{
	@Autowired
	private LogRepository logRepository;
	
	@Override
	public Log addLog(Log l) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(date);
		l.setDate(strDate);
		l.setHeure(strTime);
		logRepository.save(l);
		return l;
	}

	@Override
	public List<Log> listLog() {
		return logRepository.findAll();
	}
	
	
}
