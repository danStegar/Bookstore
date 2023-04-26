package com.app.bookstore.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.data.jpa.repository.JpaRepository;

//pt ca o clasa sa fie un Bean (o clasa managed de springboot), trebuie sa punem adnotarea-de exemplu- @Service
//Corect: Un Bean este un OBIECT care este instantiat si manage uit de SpringBoot (IOC- Inversion of Controller)

@Service
public class UserService {
	// V1:
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User update(User userToUpdate, Integer userId) {
		// existingUser -> un obiect in starea "peristed" -> exista in baza de date,
		// altfel s-a aruncat exceptie
		User existingUser = userRepository.findById(userId).orElseThrow();
		existingUser.setFirstName(userToUpdate.getFirstName());
		existingUser.setLastName(userToUpdate.getLastName());
		existingUser.setAge(userToUpdate.getAge());

		return userRepository.save(existingUser);
	}
}
