package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2025"), "Partiu viagem", "Vou viajar para o Rio de janeiro. Abracos!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("29/03/2023"), "Boa noite", "Noite linda para assistir um filme!", new AuthorDTO(maria));

		CommentDTO c1 = new CommentDTO("Boa viagem amigo!", sdf.parse("10/06/22025"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Que legal para onde vocë vai?", sdf.parse("15/06/2025"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Crazy!", sdf.parse("10/07/2025"),new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2,c3));
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}