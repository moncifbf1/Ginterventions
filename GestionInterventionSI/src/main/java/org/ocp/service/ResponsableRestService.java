package org.ocp.service;

import java.util.List;

import org.ocp.entities.Responsable;
import org.ocp.metier.ResponsableMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponsableRestService {
	@Autowired
	private ResponsableMetier responsableMetier;
	@Secured(value={"ROLE_RESP"})
	@RequestMapping(value="/responsable/desaffecter",method=RequestMethod.PUT)
	@ResponseBody
	public boolean desaffecterIntervention(@RequestParam int idIntervention) {
		return responsableMetier.desaffecterIntervention(idIntervention);
	}
	@Secured(value={"ROLE_RESP","ROLE_ADMIN"})
	public List<Responsable> listeResponsables() {
		return responsableMetier.listeResponsables();
	}
	@Secured(value={"ROLE_RESP","ROLE_ADMIN"})
	public Responsable getResponsableByCode(String code_responsable) {
		return responsableMetier.getResponsableByCode(code_responsable);
	}
	@Secured(value={"ROLE_RESP"})
	@RequestMapping(value="/responsable/affecter",method=RequestMethod.PUT)
	@ResponseBody
	public boolean affecterIntervention(@RequestParam int idIntervention,@RequestParam int idOperateur) {
		return responsableMetier.affecterIntervention(idIntervention, idOperateur);
	}
	
}
