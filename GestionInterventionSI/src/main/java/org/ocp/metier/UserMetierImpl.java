package org.ocp.metier;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.ocp.dao.UserRepository;
import org.ocp.entities.Administrateur;
import org.ocp.entities.Operateur;
import org.ocp.entities.Responsable;
import org.ocp.entities.User;
import org.ocp.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMetierImpl implements UserMetier{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User u) {
//		SecureRandom random = new SecureRandom();
//		byte bytes[] = new byte[20];
//	    random.nextBytes(bytes);
//	    String token = bytes.toString();
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
		Date sysDate = new Date();
		u.setDateCreation(shortDateFormat.format(sysDate));
		return userRepository.save(u);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	
	
	@Override
	public List<User> seachUsers(String search) {
		return userRepository.searchUser(search);
	}

	@Override
	public User updateUser(User user) {
		User u = userRepository.findOne(user.getUsername());
		u.setUsername(user.getUsername());
		u.setDateCreation(user.getDateCreation());
		u.setRoles(user.getRoles());
		u.setActived(user.isActived());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setPassword(user.getPassword());
		u.setTelephone(user.getTelephone());
		u.setDiscrimUser(user.getDiscrimUser());
		u.setUsername(user.getUsername());
		u.setMail(user.getMail());
		userRepository.saveAndFlush(u);
		return u;
	}

	@Override
	public void deleteUser(String username) {
		User u = userRepository.findOne(username);
		userRepository.delete(u);
	}
	
	@Override
	public List<Responsable> getResponsables() {
		return userRepository.getAllReponsables();
	}
	
	@Override
	public List<Administrateur> getAdministrateurs() {
		return userRepository.getAllAdministrateurs();
	}

	@Override
	public List<Operateur> getOperateurs() {
		return userRepository.getAllOperateurs();
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		return userRepository.getAllUtilisateurs();
	}

	@Override
	public User findUser(String username) {
		User user = userRepository.findOne(username);
		return user;
	}

	@Override
	public int countOperateurs() {
		return userRepository.countOperateurs();
	}

	@Override
	public void changeStatus(String username, String token) {
		User u = userRepository.findOne(username);
		u.setActived(true);
		userRepository.save(u);
	}

	@Override
	public List<User> findRestrictedUsers(String username) {
		return userRepository.getRestrictedUser(username);
	}

	@Override
	public PageUsers getUsers(String username, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}	
}
