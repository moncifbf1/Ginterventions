package org.ocp.service;

import java.util.List;

import org.ocp.entities.Operateur;
import org.ocp.metier.OperateurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperateurRestService {
	@Autowired
	private OperateurMetier operateurMetier;
	
	@Secured(value={"ROLE_OPERATEUR"})
	@RequestMapping(value="/operateurs/startIntervention",method=RequestMethod.PUT)
	@ResponseBody
	public boolean demarrerIntervention(@RequestParam int idIntervention) {
		return operateurMetier.demarrerIntervention(idIntervention);
	}
	
	@Secured(value={"ROLE_OPERATEUR"})
	@RequestMapping(value="/operateurs/endIntervention",method=RequestMethod.PUT)
	@ResponseBody
	public boolean finirIntervention(@RequestParam int idIntervention) {
		return operateurMetier.finirIntervention(idIntervention);
	}
	
	@Secured(value={"ROLE_OPERATEUR","ROLE_ADMIN","ROLE_RESPONSABLE"})
	public List<Operateur> listOperateurs() {
		return operateurMetier.listOperateurs();
	}
	
	@Secured(value={"ROLE_OPERATEUR","ROLE_ADMIN","ROLE_RESPONSABLE"})
	@RequestMapping(value="/operateurs/{code}",method=RequestMethod.GET)
	public Operateur getOperateurByCode(@PathVariable("code") String code_operateur) {
		return operateurMetier.getOperateurByCode(code_operateur);
	}
}
