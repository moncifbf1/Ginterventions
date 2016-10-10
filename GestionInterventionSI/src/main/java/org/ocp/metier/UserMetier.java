package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Administrateur;
import org.ocp.entities.Operateur;
import org.ocp.entities.Responsable;
import org.ocp.entities.User;
import org.ocp.entities.Utilisateur;

public interface UserMetier {
	public User saveUser(User u);
	public List<User> findAllUsers();
	public List<Responsable> getResponsables();
	public List<Administrateur> getAdministrateurs();
	public List<Operateur> getOperateurs();
	public List<Utilisateur> getUtilisateurs();
	public User updateUser(User user);
	public void deleteUser(String username);
	public User findUser(String username);
	public int countOperateurs();
	public void changeStatus(String username, String token);
	public List<User> findRestrictedUsers(String username);
	public PageUsers getUsers(String username, int page, int size);
	public List <User> seachUsers(String search);
}
