package com.app.bookstore.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//nivelul de comunicare cu clientul/aplicatia de pe front-end
@RestController
@RequestMapping("/users") // mereu se foloseste forma de plural ca standard
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@PostMapping()
	public UserGetDTO create(@RequestBody UserCreateDTO userCreateDTO) {
		User user = userMapper.userCreateDTO2User(userCreateDTO);
		User createdUser = userService.create(user);

		return userMapper.user2UserGetDTO(createdUser);
	}

	@GetMapping()
	public List<UserGetDTO> findAll() {
		return userService.findAll().stream().map(user -> userMapper.user2UserGetDTO(user)).toList();
	}

	@PutMapping("/{id}")
	public UserGetDTO update(@RequestBody UserCreateDTO userCreateDTO, @PathVariable Integer id) {
		User user = userMapper.userCreateDTO2User(userCreateDTO);
		User updatedUser = userService.update(user, id);

		return userMapper.user2UserGetDTO(updatedUser);
	}

}
