package org.ocp.service;

import java.util.List;
import org.ocp.entities.Model;
import org.ocp.metier.ModelMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelService {
	@Autowired
	private ModelMetier modelMetier;
	
	@RequestMapping(value="/addModel", method=RequestMethod.POST)
	public @ResponseBody Model saveModel(@RequestBody Model m) {
		return modelMetier.saveModel(m);
	}
	
	@RequestMapping(value="/updateModel", method=RequestMethod.PUT)
	public @ResponseBody Model updateModel(@RequestBody Model m) {
		return modelMetier.updateModel(m);
	}
	
	@RequestMapping(value="/model/{idModel}", method=RequestMethod.DELETE)
	public boolean deleteModel(@PathVariable("idModel") int idModel) {
		return modelMetier.deleteModel(idModel);
	}
	
	@RequestMapping(value="/getModels", method=RequestMethod.GET)
	public List<Model> listModels() {
		return modelMetier.listModels();
	}
	
	@RequestMapping(value="/getModel/{idModel}")
	public @ResponseBody Model getModel(@PathVariable("idModel") int idModel) {
		return modelMetier.getModel(idModel);
	}
	
	@RequestMapping(value="/getModelByFournisseur/{fournisseur:.+}")
	public List<Model> listModelsByFournisseur(@PathVariable("fournisseur") String nom) {
		return modelMetier.listModelsByFournisseur(nom);
	}
	
	
}
