package org.taskfinder.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.taskfinder.entities.Role;
import org.taskfinder.entities.Task;
import org.taskfinder.entities.User;
import org.taskfinder.repositories.UserRepository;

import com.stripe.model.Balance;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void createAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void createWorker(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("WORKER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public User findOne(String email) {

		return userRepository.findOne(email);
	}

	public boolean isUserPresent(String email) {
		// TODO Auto-generated method stub
		User u = userRepository.findOne(email);
		if (u != null)
			return true;

		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByNameLike("%" + name + "%");
	}

	public void topUpAccount(User user, double topup) {
		user.setBalance(user.getBalance() + topup);
		userRepository.save(user);

	}

	public void makePayment(User user, User admin, double payment) {
		user.setBalance(user.getBalance() - payment);
		admin.setBalance(admin.getBalance() + payment);
		userRepository.save(user);

	}
	
	public void withdrawAccount(User user,  double withdraw) {
		user.setBalance(user.getBalance() - withdraw);
		userRepository.save(user);
		
	}

	public List<User> findByRoles(){
		
		return userRepository.findByRolesLike();
	}

	public List<User> findByWord(String word) {
		
		List<User> workers = new ArrayList<User>();
		
		
		workers.addAll(userRepository.findByNameLike("%" + word + "%"));
		workers.addAll(userRepository.findBySkillLike("%" + word + "%"));
		workers.addAll(userRepository.findByEmailLike("%" + word + "%"));
		
		return workers;
		
	}

	
	

}
