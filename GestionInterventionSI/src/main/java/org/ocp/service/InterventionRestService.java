package org.ocp.service;


import java.util.List;
import org.ocp.entities.Intervention;
import org.ocp.metier.InterventionMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class InterventionRestService {
	@Autowired
	private InterventionMetier interventionMetier;
	
	
	@RequestMapping(value="/interventions/op/{code:.+}/{etat}",method=RequestMethod.GET)
	public List<Intervention> interventionsByOperateurState(@PathVariable("code") String codeOperateur,@PathVariable("etat") int etat) {
		return interventionMetier.interventionsByOperateurState(codeOperateur, etat);
	}

	@RequestMapping(value="/terminerOpFour",method=RequestMethod.PUT)
	public @ResponseBody Intervention terminerOpFour(@RequestBody Intervention intervention) {
		return interventionMetier.terminerOpFour(intervention);
	}

	@RequestMapping(value="/passerniveau",method=RequestMethod.PUT)
	public @ResponseBody Intervention passerNiveau(@RequestBody Intervention intervention) {
		return interventionMetier.passerNiveau(intervention);
	}

	@RequestMapping(value="/EnAttenteIntervsOper/{codeOperateur:.+}",method=RequestMethod.GET)
	public List<Intervention> EnAttenteIntervsOper(@PathVariable String codeOperateur) {
		return interventionMetier.EnAttenteIntervsOper(codeOperateur);
	}

	@RequestMapping(value="/TermineeIntervsOper/{codeOperateur:.+}",method=RequestMethod.GET)
	public List<Intervention> TermineeIntervsOper(@PathVariable String codeOperateur) {
		return interventionMetier.TermineeIntervsOper(codeOperateur);
	}
	
	@RequestMapping(value="/TermineeIntervs",method=RequestMethod.GET)
	public List<Intervention> TermineeIntervs() {
		return interventionMetier.TermineeIntervs();
	}
	
	@RequestMapping(value="/EnAttenteIntervs",method=RequestMethod.GET)
	public List<Intervention> EnAttenteIntervs() {
		return interventionMetier.EnAttenteIntervs();
	}
	
	@RequestMapping(value="/EnCoursIntervs",method=RequestMethod.GET)
	public List<Intervention> EnCoursIntervs() {
		return interventionMetier.EnCoursIntervs();
	}
	
	@RequestMapping(value="/terminer",method=RequestMethod.PUT)
	public @ResponseBody Intervention terminer(@RequestBody Intervention intervention) {
		return interventionMetier.terminer(intervention);
	}
	
	@RequestMapping(value="/intervenir",method=RequestMethod.PUT)
	public @ResponseBody Intervention intervenir(@RequestBody Intervention intervention) {
		return interventionMetier.intervenir(intervention);
	}
	
	@Secured(value={"ROLE_RESPONSABLE"})
	@RequestMapping(value="/affecterIntervention",method=RequestMethod.PUT)
	public @ResponseBody Intervention affecterIntervention(@RequestBody Intervention intervention) {
		return interventionMetier.affecterIntervention(intervention);
	}
	
	@Secured(value={"ROLE_OPERATEUR","ROLE_ADMIN","ROLE_RESPONSABLE"})
	@RequestMapping(value="/interventions/{idIntervention:.+}",method=RequestMethod.GET)
	public Intervention interventionsById(@PathVariable int idIntervention) {
		return interventionMetier.interventionsById(idIntervention);
	}
	
	@Secured(value={"ROLE_UTILISATEUR","ROLE_RESPONSABLE"})
	@RequestMapping(value="/addIntervention",method=RequestMethod.POST)
	public @ResponseBody Intervention saveIntervention(@RequestBody Intervention intervention) {
		return interventionMetier.saveIntervention(intervention);
	}
	
	@Secured(value={"ROLE_UTILISATEUR","ROLE_RESPONSABLE","ROLE_OPERATEUR"})
	@RequestMapping(value="/interventions",method=RequestMethod.PUT)
	public @ResponseBody Intervention updateIntervention(@RequestBody Intervention intervention) {
		return interventionMetier.updateIntervention(intervention);
	}
	
	@Secured(value={"ROLE_RESPONSABLE"})
	@RequestMapping(value="/interventions/{id:.+}",method=RequestMethod.DELETE)
	public boolean deleteIntervention(@PathVariable("id") int idIntervention) {
		return interventionMetier.deleteIntervention(idIntervention);
	}
	
	//@Secured(value={"ROLE_OPERATEUR","ROLE_RESPONSABLE","ROLE_ADMIN"})
	@RequestMapping(value="/interventions")
	public List<Intervention> listeInterventions() {
		return interventionMetier.listeInterventions();
	}
	
	@Secured(value={"ROLE_RESPONSABLE","ROLE_ADMIN","ROLE_OPERATEUR"})
	@RequestMapping(value="/interventions/op/{code:.+}",method=RequestMethod.GET)
	public List<Intervention> interventionsByOperateur(@PathVariable("code") String codeOperateur) {
		return interventionMetier.interventionsByOperateur(codeOperateur);
	}
	
	//SEARCH REQUESTS
	@RequestMapping(value="/searchQuery6/{niveau}/{etat}",method=RequestMethod.GET)
	public List<Intervention> searchNiveauEtat(@PathVariable("niveau") int niveau, @PathVariable("etat") int etat) {
		return interventionMetier.searchNiveauEtat(niveau, etat);
	}
	
	@RequestMapping(value="/searchQuery7/{niveau}/{etat}/{dateDemande}",method=RequestMethod.GET)
	public List<Intervention> searchNiveauEtatDateDemande(@PathVariable("niveau") int niveau, @PathVariable("etat") int etat, @PathVariable("dateDemande") String dateDemande) {
		return interventionMetier.searchNiveauEtatDateDemande(niveau, etat, dateDemande);
	}
	
	@RequestMapping(value="/searchQuery1/{dateDemande}",method=RequestMethod.GET)
	public List<Intervention> searchDate(@PathVariable("dateDemande") String dateDemande) {
		return interventionMetier.searchDate(dateDemande);
	}
	
	@RequestMapping(value="/searchQuery2/{etat}",method=RequestMethod.GET)
	public List<Intervention> searchEtat(@PathVariable("etat") int etat) {
		return interventionMetier.searchEtat(etat);
	}
	
	@RequestMapping(value="/searchQuery3/{etat}/{dateDemande}",method=RequestMethod.GET)
	public List<Intervention> searchEtatDate(@PathVariable("etat") int etat,@PathVariable("dateDemande") String dateDemande) {
		return interventionMetier.searchEtatDate(etat, dateDemande);
	}
	
	@RequestMapping(value="/searchQuery4/{niveau}",method=RequestMethod.GET)
	public List<Intervention> searchNiveau(@PathVariable("niveau") int niveau) {
		return interventionMetier.searchNiveau(niveau);
	}
	
	@RequestMapping(value="/searchQuery5/{niveau}/{dateDemande}",method=RequestMethod.GET)
	public List<Intervention> searchNiveauDate(@PathVariable("niveau") int niveau,@PathVariable("dateDemande") String dateDemande) {
		return interventionMetier.searchNiveauDate(niveau, dateDemande);
	}
	
}
