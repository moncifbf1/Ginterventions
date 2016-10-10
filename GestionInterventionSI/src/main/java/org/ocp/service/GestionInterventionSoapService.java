package org.ocp.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.ocp.entities.Intervention;
import org.ocp.metier.InterventionMetier;
import org.ocp.metier.ResponsableMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebService
public class GestionInterventionSoapService {
	@Autowired
	private InterventionMetier interventionMetier;
	@Autowired
	private ResponsableMetier responsableMetier;
	
	@WebMethod
	public Intervention saveIntervention(@WebParam(name="intervention") Intervention intervention) {
		return interventionMetier.saveIntervention(intervention);
	}
	@WebMethod
	public Intervention updateIntervention(@WebParam(name="intervention")  Intervention intervention) {
		return interventionMetier.updateIntervention( intervention);
	}
	@WebMethod
	public boolean deleteIntervention(@WebParam(name="idIntervention") int idIntervention) {
		return interventionMetier.deleteIntervention(idIntervention);
	}
	@WebMethod
	public List<Intervention> listeInterventions() {
		return interventionMetier.listeInterventions();
	}
	@WebMethod
	public List<Intervention> interventionsByOperateur(@WebParam(name="codeOperateur") String codeOperateur) {
		return interventionMetier.interventionsByOperateur(codeOperateur);
	}
	@WebMethod
	public Intervention interventionsById(@WebParam(name="idIntervention") int idIntervention) {
		return interventionMetier.interventionsById(idIntervention);
	}
	@WebMethod
	public boolean affecterIntervention(@WebParam(name="idIntervention") int idIntervention,
			@WebParam(name="idOperateur")  int idOperateur) {
		return responsableMetier.affecterIntervention(idIntervention, idOperateur);
	}
	
}
