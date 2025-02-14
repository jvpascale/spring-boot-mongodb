package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public User fromDTO(UserDTO user) {
		User user1 = new User(user.getId(), user.getName(), user.getEmail());
		return user1;
	}

	public void delete(String id) {
		userRepository.deleteById(id);

	}

	public User updateData(User newUser, User oldUser) {
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
		return oldUser;
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}

}
