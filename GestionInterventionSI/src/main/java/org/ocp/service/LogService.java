package org.ocp.service;

import java.util.List;

import org.ocp.entities.Log;
import org.ocp.metier.LogMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class LogService {
	@Autowired
	private LogMetier logMetier;
	
	@RequestMapping(value="/addLog",method=RequestMethod.PUT)
	public @ResponseBody Log addLog(@RequestBody Log l) {
		return logMetier.addLog(l);
	}
	
	@RequestMapping(value="/findLog")
	public List<Log> listLog() {
		return logMetier.listLog();
	}
	
	
}
