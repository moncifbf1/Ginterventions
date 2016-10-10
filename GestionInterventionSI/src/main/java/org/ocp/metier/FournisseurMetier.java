package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Fournisseur;
import org.ocp.entities.Model;

public interface FournisseurMetier {
	public Fournisseur saveFournisseur(Fournisseur f);
	public Fournisseur updateFournisseur(Fournisseur f);
	public boolean deleteFournisseur(int id_fournisseur);
	public List<Fournisseur> listFournisseurs();
	public boolean addModelToFournisseur(Fournisseur f, Model m);
	public boolean deleteModelFromFournisseur(Fournisseur f, Model m);
	public Fournisseur findFournisseurByNom(String nom);
	public Fournisseur findFournisseurById(int idFournisseur);
}
