package org.ocp.service;

import java.util.List;

import org.ocp.entities.Fournisseur;
import org.ocp.entities.Model;
import org.ocp.metier.FournisseurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FournisseurService {
	@Autowired
	private FournisseurMetier fournisseurMetier;
	
	@RequestMapping(value="/addFournisseur",method=RequestMethod.POST)
	public @ResponseBody Fournisseur saveFournisseur(@RequestBody Fournisseur f) {
		return fournisseurMetier.saveFournisseur(f);
	}
	
	@RequestMapping(value="/updateFournisseur", method=RequestMethod.PUT)
	public @ResponseBody Fournisseur updateFournisseur(@RequestBody Fournisseur f) {
		return fournisseurMetier.updateFournisseur(f);
	}
	
	@RequestMapping(value="/fournisseur/{idfournisseur}", method=RequestMethod.DELETE)
	public boolean deleteFournisseur(@PathVariable("idfournisseur") int id_fournisseur) {
		return fournisseurMetier.deleteFournisseur(id_fournisseur);
	}
	
	@RequestMapping(value="/findFournisseurs")
	public List<Fournisseur> listFournisseurs() {
		return fournisseurMetier.listFournisseurs();
	}
	
	
	@RequestMapping(value="/findFournisseur/{idfournisseur}")
	public Fournisseur findFournisseurById(@PathVariable("idfournisseur") int idFournisseur) {
		return fournisseurMetier.findFournisseurById(idFournisseur);
	}

	public boolean addModelToFournisseur(@RequestBody Fournisseur f,@RequestBody Model m) {
		return fournisseurMetier.addModelToFournisseur(f, m);
	}

	public boolean deleteModelFromFournisseur(@RequestBody Fournisseur f,@RequestBody Model m) {
		return fournisseurMetier.deleteModelFromFournisseur(f, m);
	}
	
	@RequestMapping(value="/getFournisseur/{fournisseur:.+}")
	public @ResponseBody Fournisseur findFournisseurByNom(@PathVariable("fournisseur") String nom) {
		return fournisseurMetier.findFournisseurByNom(nom);
	}
}
